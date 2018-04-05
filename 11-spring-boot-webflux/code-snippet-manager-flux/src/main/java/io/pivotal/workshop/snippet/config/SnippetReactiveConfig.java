package io.pivotal.workshop.snippet.config;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.pivotal.workshop.snippet.domain.Snippet;
import io.pivotal.workshop.snippet.reactive.SnippetHandler;
import reactor.core.publisher.EmitterProcessor;
import reactor.ipc.netty.http.server.HttpServer;


@Configuration
public class SnippetReactiveConfig {


    @Bean
    public HttpServer httpServer(RouterFunction<ServerResponse> router){        ////<1>
        HttpServer server = HttpServer.create("localhost", 8080);
        server.newHandler(new ReactorHttpHandlerAdapter(RouterFunctions.toHttpHandler(router))).block();
        return server;
    }

    @Bean
    public RouterFunction<ServerResponse> router(SnippetHandler handler){       ////<2>
        return RouterFunctions
                .route(GET("/snippets").and(accept(APPLICATION_JSON)), handler::findAll)
                .andRoute(GET("/snippets/{id}").and(accept(APPLICATION_JSON)),handler::findById)
                .andRoute(POST("/snippets").and(accept(APPLICATION_JSON)),handler::createSnippet);
    }

    @SuppressWarnings("rawtypes")
	@Bean
    public EmitterProcessor snippetStream(){                                   ////<3>
        return EmitterProcessor.<Snippet>create();
    }
}
