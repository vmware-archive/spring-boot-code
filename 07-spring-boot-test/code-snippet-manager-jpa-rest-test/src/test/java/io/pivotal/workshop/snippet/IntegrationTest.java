package io.pivotal.workshop.snippet;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.StreamSupport;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import io.pivotal.workshop.snippet.domain.Snippet;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() {
		RestAssuredMockMvc.mockMvc(MockMvcBuilders.webAppContextSetup(context).build());
	}

	@Test
	public void homePageTest() {
		String body = this.restTemplate.getForObject("/", String.class);
		assertThat(body).contains("Hello World");
	}

	@Test
	public void linksTests() {
		when().get("/api/snippets").then().assertThat(status().isOk()).body("_links.self.href",
				equalTo("http://localhost/api/snippets"));
	}

	@Test
	public void restControllerTest() throws Exception {

		ResponseEntity<Resources<Resource<Snippet>>> responseEntity = restTemplate.exchange("/api/snippets",
				HttpMethod.GET, null, new ParameterizedTypeReference<Resources<Resource<Snippet>>>() {
				}, Collections.emptyMap());

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			Resources<Resource<Snippet>> userResource = responseEntity.getBody();
			Collection<Resource<Snippet>> snippets = userResource.getContent();
			assert userResource != null;
			assert !snippets.isEmpty();

			Snippet snippet = StreamSupport.stream(snippets.spliterator(), false).findAny().get().getContent();

			assert snippet.getTitle().contains("Hello");
		}
	}
}
