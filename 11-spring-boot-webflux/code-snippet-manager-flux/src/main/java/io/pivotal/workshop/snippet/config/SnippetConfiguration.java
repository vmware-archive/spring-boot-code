package io.pivotal.workshop.snippet.config;

import com.mongodb.MongoClient;
import io.pivotal.workshop.snippet.domain.Language;
import io.pivotal.workshop.snippet.domain.Snippet;
import io.pivotal.workshop.snippet.repository.LanguageRepository;
import io.pivotal.workshop.snippet.repository.SnippetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@Configuration
public class SnippetConfiguration {
	
    /*
	@Bean
	public CommandLineRunner runner(LanguageRepository languageRepository, SnippetRepository snippetRepository) {
		return args -> {

		    Language javaScript = languageRepository.save(new Language("JavaScript", "js"));
            Language groovy = languageRepository.save(new Language("Groovy", "groovy"));


			snippetRepository.saveAll(Arrays.asList(new Snippet("Hello World", javaScript,"console.log(\"Hello World\");")
					,new Snippet("Hello World", groovy,"println 'Hello World'")));

		};
	}
	*/



    // SOLUTION: Adding an Embedded Mongo.
    /*
    private Environment environment;

    public SnippetConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @DependsOn("embeddedMongoServer")
    public MongoClient reactiveMongoClient() {
        int port = this.environment.getProperty("local.mongo.port", Integer.class);
        return new MongoClient("localhost",port);
    }
    */

}
