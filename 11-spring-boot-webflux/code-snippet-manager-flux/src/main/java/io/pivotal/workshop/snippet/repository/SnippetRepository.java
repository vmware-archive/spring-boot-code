package io.pivotal.workshop.snippet.repository;

import io.pivotal.workshop.snippet.domain.Snippet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public interface SnippetRepository extends MongoRepository<Snippet, String> {

    @Query("{}")
    Stream<Snippet> findAllAsStream();
}
