package io.pivotal.workshop.snippet.config;

import io.pivotal.workshop.snippet.domain.Code;
import io.pivotal.workshop.snippet.domain.Language;
import io.pivotal.workshop.snippet.domain.Snippet;
import io.pivotal.workshop.snippet.repository.SnippetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
  
@Configuration
public class SnippetConfiguration {
	

	@Bean
	public CommandLineRunner runner(SnippetRepository snippetRepo) {
		return args -> {
			@SuppressWarnings("serial")
			List<Snippet> snippets = new ArrayList<Snippet>() {
				{
					add(new Snippet("Hello World", new Language("HTML", "xml"),new Code(new String(Files.readAllBytes(Paths.get("code/html-code.txt"))))));
					add(new Snippet("Hello World", new Language("C#", "c#"),new Code(new String(Files.readAllBytes(Paths.get("code/cs-code.txt"))))));
					add(new Snippet("Hello World", new Language("Pascal", "py"),new Code(new String(Files.readAllBytes(Paths.get("code/pas-code.txt"))))));
					add(new Snippet("Hello World", new Language("Erlang", "erl"),new Code(new String(Files.readAllBytes(Paths.get("code/erl-code.txt"))))));
					add(new Snippet("Hello World", new Language("JavaScript", "js"),new Code(new String(Files.readAllBytes(Paths.get("code/js-code.txt"))))));
					add(new Snippet("Hello World", new Language("Groovy", "groovy"),new Code("println 'Hello World'")));
				}
			};

			snippetRepo.saveAll(snippets);

		};
	}
}
