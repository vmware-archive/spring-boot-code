package io.pivotal.workshop.directory.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.pivotal.workshop.directory.annotation.Audit;

@Aspect
public class SimpleAudit {

	private static Logger log = LoggerFactory.getLogger("[AUDIT]");
	
	@Before("@annotation(audit)")
	public void audit(JoinPoint jp, Audit audit){
		log.info("[EXECUTING] " + jp.getSignature());
	}
}
