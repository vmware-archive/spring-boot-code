package io.pivotal.workshop.snippet.aop;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import io.pivotal.workshop.snippet.domain.Snippet;
import io.pivotal.workshop.snippet.domain.SnippetError;
import io.pivotal.workshop.snippet.domain.SnippetNotification;

@Component
@Aspect
public class SnippetAmqpNotification {

	private final Logger log = LoggerFactory.getLogger("Snippet Notification");
	private RabbitTemplate template;
	private SnippetNotification notification;
	
	public SnippetAmqpNotification(RabbitTemplate template){
		this.template = template;
	}
	
	@Around("execution(* io.pivotal.workshop.*.amqp.*Listener.*(*)) && args(snippet)")
	public Object amqpLog(ProceedingJoinPoint pjp, Snippet snippet) throws Throwable{
		Object[] args = pjp.getArgs();
		
		Exception exception = null;
		SnippetError error = null;
		Object obj = null;
		
		try{
			obj = pjp.proceed(args);
		}catch(Exception ex){
			exception = ex;
		}
		
		if (exception != null){
			error = new SnippetError(exception.getMessage(),"ERROR:456");
			
		}
		
		try {
			notification = new SnippetNotification(snippet.getId(), pjp.getSignature().getName(), new Date(), error);
			this.template.convertAndSend(notification);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		log.info("[NOTIFICATION] processed: " + pjp.getSignature().getName());
		return obj;
	}
}
