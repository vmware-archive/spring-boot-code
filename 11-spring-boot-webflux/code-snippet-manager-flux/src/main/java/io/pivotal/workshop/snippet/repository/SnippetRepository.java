package io.pivotal.workshop.snippet.repository;

import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import io.pivotal.workshop.snippet.domain.Snippet;

public interface SnippetRepository extends MongoRepository<Snippet, String> {

    @Query("{}")
    Stream<Snippet> findAllAsStream();
}
