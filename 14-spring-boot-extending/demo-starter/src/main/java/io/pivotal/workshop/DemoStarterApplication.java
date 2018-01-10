package io.pivotal.workshop;

import io.pivotal.workshop.directory.annotation.Algorithm;
import io.pivotal.workshop.directory.annotation.EnableDirectoryWebClientUtils;
import io.pivotal.workshop.directory.client.DirectoryWebClient;
import io.pivotal.workshop.directory.utils.DirectoryWebClientUtils;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableDirectoryWebClientUtils(algorithm = Algorithm.PBKDF2)
@SpringBootApplication
public class DemoStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoStarterApplication.class, args);
	}

	//SOLUTION
	/*
	@Bean
	ApplicationRunner addPersons(DirectoryWebClient client){
		return args ->{
			Person person = client.add(new Person("dan@email.com", "Dan B.", "simplepwd", "1975-05-03", "ADMIN", true));
			System.out.println(">>> " + person + " was addedd successfully");
		};
	}
	*/


	@Bean
	ApplicationRunner getPersons(DirectoryWebClient client){
		return args -> {
			client.getAll().forEach(System.out::println);
		};
	}


	@Bean
	ApplicationRunner encode(DirectoryWebClientUtils utils){
		return args -> {
		    String text = "This text will be encrypted";
			String hash = utils.getEncoder().encode(text);
			System.out.println(">>> ENCRYPT: " + hash);
			System.out.println(">>> Verify: " + utils.getEncoder().matches(text,hash));
		};
	}


}
