package io.pivotal.workshop.directory.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BCryptEncoder {

    @Bean
    public DirectoryWebClientUtils utils(){
        return new DirectoryWebClientUtils(new BCryptPasswordEncoder(16));
    }
}
