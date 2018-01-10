package io.pivotal.workshop.snippet.service;


import io.pivotal.workshop.snippet.domain.Language;
import io.pivotal.workshop.snippet.domain.Snippet;
import io.pivotal.workshop.snippet.repository.LanguageRepository;
import io.pivotal.workshop.snippet.repository.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.EmitterProcessor;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class SnippetService {

    private LanguageRepository languageRepository;
    private SnippetRepository snippetRepository;
    private EmitterProcessor<Snippet> snippetEmitterProcessor;

    @Autowired
    public SnippetService(LanguageRepository languageRepository, SnippetRepository snippetRepository, EmitterProcessor<Snippet> snippetEmitterProcessor) {
        this.languageRepository = languageRepository;
        this.snippetRepository = snippetRepository;
        this.snippetEmitterProcessor = snippetEmitterProcessor;
    }

    public Stream<Snippet> findAll(){
        return this.snippetRepository.findAllAsStream();
    }

    public Optional<Snippet> findById(String snippetId){
        return this.snippetRepository.findById(snippetId);
    }

    public Snippet save(Snippet snippet){
        Language language = new Language("Unknown","txt");

        if(snippet.getLang()!=null){
            language = this.languageRepository.findByName(snippet.getLang().getName());
            if(language == null) {
                language = this.languageRepository.save(snippet.getLang());
            }
        }else
            language = this.languageRepository.save(language);

        snippet.setLang(language);
        Snippet result = this.snippetRepository.save(snippet);
        this.snippetEmitterProcessor.onNext(result);
        return result;
    }
}
