package io.pivotal.workshop.snippet.repository;

import io.pivotal.workshop.snippet.domain.Language;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LanguageRepository extends MongoRepository<Language, String> {

    Language findByName(String name);

}
