package io.pivotal.workshop.directory.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "directory.web.client")
public class DirectoryWebClientProperties {

    String username = "admin";
    String password = "admin";
    String uri = "http://localhost:8585/api/persons";

}
