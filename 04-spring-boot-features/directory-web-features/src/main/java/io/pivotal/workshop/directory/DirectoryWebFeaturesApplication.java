package io.pivotal.workshop.directory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class DirectoryWebFeaturesApplication {
	
	// SOLUTION: Using Application Listeners
	//private static final Logger log = LoggerFactory.getLogger("[EVENTS]");
	
	private static final Logger log = LoggerFactory.getLogger("[ARGS]");
	
	public static void main(String[] args) {
		//SpringApplication.run(DirectoryWebFeaturesApplication.class, args);
		
		//SOLUTION:
		/* Turn off Banner
		SpringApplication app = new SpringApplication(DirectoryWebFeaturesApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		*/
		
		/* Using Application Listeners
		ApplicationListener<ApplicationEvent> event = e -> {
			log.info(e.toString());
		};
		
		SpringApplication app = new SpringApplication(DirectoryWebFeaturesApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.setListeners(Arrays.asList(event));
		app.run(args); 
		*/
		
		/* Turn off Web Context
		SpringApplication app = new SpringApplication(DirectoryWebFeaturesApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
		*/
		
		SpringApplication app = new SpringApplication(DirectoryWebFeaturesApplication.class);
		app.run(args);

	}
	
	@Bean
	public ApplicationRunner appRunner() {
		return args -> { 
			args.getOptionNames().forEach( s -> {
				log.info("Option Name: " + s);
			});
			
			if (args.containsOption("option")) log.info("Found Value:" +  args.getOptionValues("option").toString());
			if (args.containsOption("enable-audit")) log.info("Found Value:" +  args.getOptionValues("enable-audit").toString());
			
			Stream.of(args.getSourceArgs()).forEach( s -> { 
				log.info("Argument: " + s);
			});
		};
	}
	
	@Bean
	public CommandLineRunner commandRunner() {
		return args -> { 
			Stream.of(args).forEach(s -> {
				log.info("CommandLine: " + s);
			});
		};
	}
}
