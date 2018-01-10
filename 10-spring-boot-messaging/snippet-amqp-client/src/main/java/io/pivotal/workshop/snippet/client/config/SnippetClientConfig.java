package io.pivotal.workshop.snippet.client.config;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.pivotal.workshop.snippet.client.amqp.SnippetProducer;
import io.pivotal.workshop.snippet.client.amqp.SnippetProducer.SnippetAction;
import io.pivotal.workshop.snippet.client.domain.Code;
import io.pivotal.workshop.snippet.client.domain.Language;
import io.pivotal.workshop.snippet.client.domain.Snippet;

@Configuration
public class SnippetClientConfig {

	private final Logger log = LoggerFactory.getLogger("SNIPPET-SENDER");
	
	@Bean
	public CommandLineRunner sendSnippets(SnippetProducer producer){
		return args -> {
			Object obj = producer.send(SnippetAction.SAVE, new Snippet("Hello World", new Language("Kotlin", "java"),new Code(new String(Files.readAllBytes(Paths.get("code/Hello.kt"))))));
			log.info(obj.toString());
		};
	}
}
