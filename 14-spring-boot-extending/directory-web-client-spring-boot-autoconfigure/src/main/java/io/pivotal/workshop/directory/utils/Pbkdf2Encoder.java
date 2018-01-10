package io.pivotal.workshop.directory.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class Pbkdf2Encoder {

    @Bean
    public DirectoryWebClientUtils utils(){
        return new DirectoryWebClientUtils(new Pbkdf2PasswordEncoder());
    }
}
