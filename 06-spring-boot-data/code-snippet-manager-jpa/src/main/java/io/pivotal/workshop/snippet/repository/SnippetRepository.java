package io.pivotal.workshop.snippet.repository;

import org.springframework.data.repository.CrudRepository;

import io.pivotal.workshop.snippet.domain.Snippet;

public interface SnippetRepository extends CrudRepository<Snippet, String> {

}
