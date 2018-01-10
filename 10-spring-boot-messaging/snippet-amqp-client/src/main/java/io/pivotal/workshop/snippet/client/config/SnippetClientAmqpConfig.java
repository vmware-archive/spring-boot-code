package io.pivotal.workshop.snippet.client.config;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.JavaType;

@Configuration
public class SnippetClientAmqpConfig {
	
	private final String SNIPPET_EXCHANGE = "snippet.manager";

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
		Jackson2JavaTypeMapper mapper = new DefaultJackson2JavaTypeMapper() {

			@Override
			public JavaType toJavaType(MessageProperties properties) {
				properties.setHeader("__TypeId__", "io.pivotal.workshop.snippet.client.domain.SnippetNotification");
				return super.toJavaType(properties);
			}
			
		};
		converter.setJavaTypeMapper(mapper);
		
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(converter);
		template.setExchange(SNIPPET_EXCHANGE);
		return template;
	}
	
}
