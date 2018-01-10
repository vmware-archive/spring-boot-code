package io.pivotal.workshop.snippet.repository;

import org.springframework.data.repository.CrudRepository;

import io.pivotal.workshop.snippet.domain.Code;

public interface CodeRepository extends CrudRepository<Code, String> {

}
