package io.pivotal.workshop.snippet.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SnippetAmqpClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnippetAmqpClientApplication.class, args);
	}
}
