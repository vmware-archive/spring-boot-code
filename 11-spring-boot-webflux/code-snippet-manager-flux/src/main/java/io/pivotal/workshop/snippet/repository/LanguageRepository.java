package io.pivotal.workshop.snippet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.pivotal.workshop.snippet.domain.Language;

public interface LanguageRepository extends MongoRepository<Language, String> {

    Language findByName(String name);

}
