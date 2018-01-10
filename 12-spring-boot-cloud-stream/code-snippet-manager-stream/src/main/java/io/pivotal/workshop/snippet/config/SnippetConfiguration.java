package io.pivotal.workshop.snippet.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

@Configuration
public class SnippetConfiguration {
	

    private Environment environment;

    public SnippetConfiguration(Environment environment) {
        this.environment = environment;
    }


    @Bean
    @DependsOn("embeddedMongoServer")           ////<1>
    public MongoClient reactiveMongoClient() {
        int port = this.environment.getProperty("local.mongo.port", Integer.class);
        return new MongoClient("localhost",port);
    }


}
