package io.pivotal.workshop.directory.configuration;

import io.pivotal.workshop.directory.client.DirectoryWebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Resource;
import org.springframework.web.client.RestTemplate;

////<1>
@Configuration
@ConditionalOnClass({Resource.class,RestTemplateBuilder.class})
@EnableConfigurationProperties(DirectoryWebClientProperties.class)
public class DirectoryWebClientAutoConfiguration {

    private final Logger log = LoggerFactory.getLogger(DirectoryWebClientAutoConfiguration.class);
    private DirectoryWebClientProperties webClientProperties;
    private RestTemplateBuilder restTemplateBuilder;

    ////<2>
    public DirectoryWebClientAutoConfiguration(DirectoryWebClientProperties webClientProperties, RestTemplateBuilder restTemplateBuilder) {
        this.webClientProperties = webClientProperties;
        this.restTemplateBuilder = restTemplateBuilder;
    }

    ////<3>
    @Bean
    public DirectoryWebClient directoryClient(){
        log.info("Creating a Directory Web Client...");
        return new DirectoryWebClient(restTemplate(),this.webClientProperties);
    }


    ////<4>
    @Bean
    public RestTemplate restTemplate(){
        // Make sure the directory-web-security has the httpBasic() security enabled and NOT the formLogin()
        log.info("Setting up admin credentials for Directory Web Client ...");
        return this.restTemplateBuilder.basicAuthorization(webClientProperties.getUsername(),webClientProperties.getPassword()).build();
    }


}
