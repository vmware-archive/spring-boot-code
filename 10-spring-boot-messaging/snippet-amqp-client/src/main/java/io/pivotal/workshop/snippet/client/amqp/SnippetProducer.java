package io.pivotal.workshop.snippet.client.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import io.pivotal.workshop.snippet.client.domain.Snippet;

@Component
public class SnippetProducer {

	public enum SnippetAction {
		SAVE("snippet.save"), DELETE("snippet.delete"), UPDATE("snippet.update");
		
		private final String routingKey;
		private SnippetAction(String routingKey){
			this.routingKey = routingKey;
		}
		
		public String getRoutingKey(){
			return this.routingKey;
		}
	}
	
	private RabbitTemplate template;
	
	public SnippetProducer(RabbitTemplate template){
		this.template = template;
	}
	
	public Object send(SnippetAction action,Snippet snippet){
		return this.template.convertSendAndReceive(action.getRoutingKey(),snippet);
	}
	
}
