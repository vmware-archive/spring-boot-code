package io.pivotal.workshop.snippet.repository;

import org.springframework.data.repository.CrudRepository;

import io.pivotal.workshop.snippet.domain.Language;

public interface LanguageRepository extends CrudRepository<Language, String> {

}
