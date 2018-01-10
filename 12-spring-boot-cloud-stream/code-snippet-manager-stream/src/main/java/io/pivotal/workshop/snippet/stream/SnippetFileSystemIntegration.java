package io.pivotal.workshop.snippet.stream;

import io.pivotal.workshop.snippet.config.SnippetProperties;
import io.pivotal.workshop.snippet.integration.SnippetTransformer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.transformer.FileToStringTransformer;

import java.io.File;

@EnableConfigurationProperties(SnippetProperties.class)
@EnableBinding(Source.class)
public class SnippetFileSystemIntegration {

    private SnippetProperties snippetProperties;
    private SnippetTransformer snippetTransformer;

    public SnippetFileSystemIntegration(SnippetProperties snippetProperties, SnippetTransformer snippetTransformer) {  ////<1>
        this.snippetProperties = snippetProperties;
        this.snippetTransformer = snippetTransformer;
    }

    @Bean
    public IntegrationFlow snippetFlow(){   ////<2>
        return
                IntegrationFlows.from(
                        Files
                            .inboundAdapter(new File(this.snippetProperties.getPath()))   ////<3>
                            .preventDuplicates(true)
                            .patternFilter(this.snippetProperties.getExtension()),
                        e -> e.poller(Pollers.fixedDelay(5000L)))                  ////<4>
                .transform(new FileToStringTransformer())                                 ////<5>
                .transform(Transformers.converter(this.snippetTransformer))               ////<6>
                .channel(Source.OUTPUT)                                                   ////<7>
                .get();

    }

}
