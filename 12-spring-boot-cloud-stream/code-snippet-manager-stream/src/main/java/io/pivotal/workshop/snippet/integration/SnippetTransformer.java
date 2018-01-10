package io.pivotal.workshop.snippet.integration;


import io.pivotal.workshop.snippet.domain.Snippet;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

@Component
public class SnippetTransformer implements Converter<String,Snippet> {

    @Nullable
    @Override
    public Snippet convert(String snippet) {
        return new Yaml().loadAs(snippet,Snippet.class);
    }


    //SOLUTION
    /*
    private SnippetService snippetService;

    public SnippetTransformer(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @Nullable
    @Override
    public Snippet convert(String snippetYml) {
        Snippet snippet = new Yaml().loadAs(snippetYml,Snippet.class);
        return this.snippetService.save(snippet);
    }
    */
}
