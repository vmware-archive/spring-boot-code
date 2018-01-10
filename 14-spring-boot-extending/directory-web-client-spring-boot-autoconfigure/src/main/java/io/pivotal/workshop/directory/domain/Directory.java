package io.pivotal.workshop.directory.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Directory {

    @JsonProperty("_embedded")
    public Embedded embedded;

    @Data
    public class Embedded {

        public List<Person> persons;

    }
}

