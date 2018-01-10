package io.pivotal.workshop.snippet.reactive;

import io.pivotal.workshop.snippet.domain.Language;
import io.pivotal.workshop.snippet.domain.Snippet;
import io.pivotal.workshop.snippet.repository.LanguageRepository;
import io.pivotal.workshop.snippet.repository.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class SnippetHandler {

    private EmitterProcessor<Snippet> snippetEmitterProcessor;
    private SnippetRepository snippetRepository;
    private LanguageRepository languageRepository;

    @Autowired  ////<1>
    public SnippetHandler(SnippetRepository snippetRepository, LanguageRepository languageRepository, EmitterProcessor<Snippet> snippetEmitterProcessor) {
        this.snippetRepository = snippetRepository;
        this.languageRepository = languageRepository;
        this.snippetEmitterProcessor = snippetEmitterProcessor;
    }

    public Mono<ServerResponse> findAll(ServerRequest request){     ////<2>
        return ok().body(BodyInserters.fromPublisher(Flux.fromStream(this.snippetRepository.findAllAsStream()),Snippet.class));
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String snippetId = request.pathVariable("id");
        Optional<Snippet> result = this.snippetRepository.findById(snippetId);
        if(result.isPresent())
            return ok().body(BodyInserters.fromPublisher(Mono.just(result.get()),Snippet.class));
        else
            return notFound().build();
    }

    public Mono<ServerResponse> createSnippet(ServerRequest request) {
        Mono<Snippet> snippetMono = request.bodyToMono(Snippet.class);
        return ok().build(snippetMono.doOnNext(snippetConsumer -> {
            Language language = new Language("Unknown","txt");
            if(snippetConsumer.getLang()!=null){
                language = this.languageRepository.findByName(snippetConsumer.getLang().getName());
                if(language == null) {
                    language = this.languageRepository.save(snippetConsumer.getLang());
                }
            }else
                language = this.languageRepository.save(language);

            snippetConsumer.setLang(language);
            Snippet result = this.snippetRepository.save(snippetConsumer);
            this.snippetEmitterProcessor.onNext(result);   ////<3>
        }).then());
    }


}
