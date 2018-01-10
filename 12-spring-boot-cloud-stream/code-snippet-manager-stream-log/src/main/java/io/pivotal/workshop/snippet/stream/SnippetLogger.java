package io.pivotal.workshop.snippet.stream;

import io.pivotal.workshop.snippet.domain.Snippet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class SnippetLogger {

    private Logger log = LoggerFactory.getLogger("[SNIPPET LOGGER]");

    @StreamListener(Sink.INPUT) ////<1>
    public void log(Snippet snippet){
        log.info(snippet.toString());
    }
}
