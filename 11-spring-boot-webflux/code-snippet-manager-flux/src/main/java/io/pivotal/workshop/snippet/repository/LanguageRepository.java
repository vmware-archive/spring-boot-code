package io.pivotal.workshop.snippet.repository;

import io.pivotal.workshop.snippet.domain.Language;
import io.pivotal.workshop.snippet.domain.Snippet;
import org.springframework.data.mongodb.repository.MongoRepository;
import reactor.core.publisher.Mono;

public interface LanguageRepository extends MongoRepository<Language, String> {

    Language findByName(String name);

}
