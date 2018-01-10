package io.pivotal.workshop.directory.utils;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;


@Data
public class DirectoryWebClientUtils {

    private PasswordEncoder encoder;

    public DirectoryWebClientUtils(PasswordEncoder encoder){
        this.encoder = encoder;
    }

}
