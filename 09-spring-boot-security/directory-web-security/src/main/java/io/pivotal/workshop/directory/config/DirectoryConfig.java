package io.pivotal.workshop.directory.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import io.pivotal.workshop.directory.domain.Person;
import io.pivotal.workshop.directory.repository.PersonRepository;

@Configuration
public class DirectoryConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("views/home");
	}

	@Bean
	public CommandLineRunner directoryProcess(PersonRepository repo) {
		return args -> {
            repo.save(new Person("admin", "Administrator", "admin", "1980-08-22", "ADMIN", true));
			repo.save(new Person("john@email.com", "John C.", "simplepwd", "1980-08-03", "USER", true));
			repo.save(new Person("mike@email.com", "Mike H.", "simplepwd", "1980-04-10", "USER", true));
			repo.save(new Person("mark@email.com", "Mark S.", "simplepwd", "1981-10-08", "USER", true));
            repo.save(new Person("dan@email.com", "Dan B.", "simplepwd", "1981-10-08", "ACTUATOR", true));
		};
	}


	//SOLUTION: SSL
	/* http://localhost:8080 -> https://localhost:8443
	
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};

		tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
		return tomcat;
	}

	private Connector initiateHttpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(8443);

		return connector;
	}
	
	*/
}
