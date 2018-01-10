package io.pivotal.workshop.directory.client;


import io.pivotal.workshop.directory.configuration.DirectoryWebClientProperties;
import io.pivotal.workshop.directory.domain.Directory;
import io.pivotal.workshop.directory.domain.Person;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;

import static java.net.URI.create;

public class DirectoryWebClient {


    private RestTemplate restTemplate;
    private DirectoryWebClientProperties props;

    public DirectoryWebClient(RestTemplate restTemplate, DirectoryWebClientProperties props){
        this.restTemplate = restTemplate;
        this.props = props;
    }

    public Person add(Person person){
        ResponseEntity<Resource<Person>> response =
                this.restTemplate.exchange(
                        RequestEntity.post(
                            create(this.props.getUri()))
                            .body(person)
                        ,new ParameterizedTypeReference<Resource<Person>>() {});

        return response.getBody().getContent();
    }

    public Collection<Person> getAll() {

        ResponseEntity<Resource<Directory>> responseEntity =
                this.restTemplate.exchange(RequestEntity.get(create(this.props.getUri()))
                        .accept(MediaTypes.HAL_JSON)
                        .build(),new ParameterizedTypeReference<Resource<Directory>>() {});

        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            Directory company = responseEntity.getBody().getContent();
            return company.getEmbedded().getPersons();
        }else
            return Collections.emptyList();
    }

}
