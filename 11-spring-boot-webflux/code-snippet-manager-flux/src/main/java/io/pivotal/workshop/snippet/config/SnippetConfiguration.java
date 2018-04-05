package io.pivotal.workshop.snippet.config;

import org.springframework.context.annotation.Configuration;

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
