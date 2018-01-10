package io.pivotal.workshop.snippet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.StreamSupport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import io.pivotal.workshop.snippet.domain.Language;
import io.pivotal.workshop.snippet.repository.LanguageRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaTests {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private LanguageRepository langRepo;

	@Test
	public void langRepositoryTest() throws Exception {
		Language expected = new Language("HTML","xml");
		entityManager.persist(expected);
		
		Iterable<Language> result = langRepo.findAll();
		Language lang = StreamSupport.stream(result.spliterator(), false).findAny().get();
		assertThat(lang,is(expected));
	}

}
