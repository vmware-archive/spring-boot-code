package io.pivotal.workshop.snippet.amqp;

import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import io.pivotal.workshop.snippet.domain.Snippet;
import io.pivotal.workshop.snippet.domain.SnippetNotification;
import io.pivotal.workshop.snippet.repository.SnippetRepository;

@Component
public class SnippetAmqpListener {
	
	private SnippetRepository repo;
	public SnippetAmqpListener(SnippetRepository repo){
		this.repo = repo;
	}


	@RabbitListener(queues={"snippet.upsert"})
	@SendTo
	public SnippetNotification save(Snippet snippet){
		Snippet result = this.repo.save(snippet);
		return new SnippetNotification(result.getId(),"SAVE",new Date(),null);
	}
	
	@RabbitListener(queues={"snippet.remove"})
	@SendTo
	public SnippetNotification delete(Snippet snippet){
		this.repo.deleteById(snippet.getId());
		return new SnippetNotification(snippet.getId(),"DELETE",new Date(),null);
	}
	
}
