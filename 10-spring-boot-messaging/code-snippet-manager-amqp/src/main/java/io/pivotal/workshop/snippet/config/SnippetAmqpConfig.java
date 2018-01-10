package io.pivotal.workshop.snippet.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnippetAmqpConfig {

	private final String SNIPPET_EXCHANGE = "snippet.manager";
	private final String SNIPPET_NOTIFICATION_QUEUE_ROUTING_KEY = "snippet.notifications";
	
	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
		SimpleRabbitListenerContainerFactory container = new SimpleRabbitListenerContainerFactory();
		container.setConnectionFactory(connectionFactory);
		container.setMessageConverter(new Jackson2JsonMessageConverter());
		return container;
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setExchange(SNIPPET_EXCHANGE);
		template.setRoutingKey(SNIPPET_NOTIFICATION_QUEUE_ROUTING_KEY);
		template.setMessageConverter(new Jackson2JsonMessageConverter());
		return template;
	}
	
	@Bean
	public DirectExchange directExchange(){
		return new DirectExchange(SNIPPET_EXCHANGE, true, false);
	}
	
	@Bean
	public Queue upsert(){
		return new Queue("snippet.upsert");
	}
	
	@Bean
	public Queue remove(){
		return new Queue("snippet.remove");
	}
	@Bean
	public Queue notifications(){
		return new Queue(SNIPPET_NOTIFICATION_QUEUE_ROUTING_KEY);
	}
	
	@Bean
	public CommandLineRunner queuesAndBindings(AmqpAdmin admin){
		return args -> {
			
			admin.declareBinding(new Binding("snippet.upsert", DestinationType.QUEUE, SNIPPET_EXCHANGE, "snippet.save", null));
			admin.declareBinding(new Binding("snippet.remove", DestinationType.QUEUE, SNIPPET_EXCHANGE, "snippet.delete", null));
			admin.declareBinding(new Binding("snippet.upsert", DestinationType.QUEUE, SNIPPET_EXCHANGE, "snippet.update", null));
			admin.declareBinding(new Binding(SNIPPET_NOTIFICATION_QUEUE_ROUTING_KEY, DestinationType.QUEUE, SNIPPET_EXCHANGE, SNIPPET_NOTIFICATION_QUEUE_ROUTING_KEY, null));
			
		};
	}
}
