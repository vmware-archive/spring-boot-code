package io.pivotal.workshop.snippet;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit4.SpringRunner;

import io.pivotal.workshop.snippet.domain.Language;

@RunWith(SpringRunner.class)
@JsonTest
public class JsonTests {
	
	private final Logger log = LoggerFactory.getLogger(JsonTests.class);
	
	@Autowired
    private JacksonTester<Language> langJson;
	private Language  lang = new Language("HTML", "xml");
	
	@Before
	public void setup(){
		lang.setId("8a8080875d4d48f6015d4d48fc1e0001");
	}
	

	@Test
    public void serializeTest() throws Exception {
		
		JsonContent<Language> write = this.langJson.write(lang);
		log.info(write.getJson());
		
		assertThat(this.langJson.write(lang)).isEqualToJson("language-expected.json");
	}
	
	@Test
    public void deserializeTest() throws Exception {
	
		String language = "{ \"id\":\"8a8080875d4d48f6015d4d48fc1e0001\", \"name\":\"HTML\", \"syntax\":\"xml\"}";
		
		assertThat(this.langJson.parse(language)).isEqualTo(this.lang);
	}
}
