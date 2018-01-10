package io.pivotal.workshop.directory.aop;

import java.util.stream.IntStream;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.pivotal.workshop.directory.annotation.Audit;
import io.pivotal.workshop.directory.config.DirectoryProperties;

@Aspect
public class DirectoryAudit {

	private DirectoryProperties props;
	
	public DirectoryAudit(DirectoryProperties props){
		this.props = props;
	}
	
	private static Logger log = LoggerFactory.getLogger("[AUDIT]");

	@Around("execution(* *(..)) && @annotation(audit)")
	public Object audit(ProceedingJoinPoint jp, Audit audit) throws Throwable {
		// Step. Get the Arguments
		Object[] args = jp.getArgs();

		// Step. Print execution information
		this.printBar();
		this.print("[executing] " + (props.getInfo().toLowerCase().equals("short") ? jp.getSignature().getName() : jp.getSignature() ));

		// Step. Print arguments if any
		switch (audit.value()) {
		case BEFORE:
		case BEFORE_AND_AFTER:
			this.printArgs(args);
		default:
			break;
		}

		// Step. Proceed
		Object obj = jp.proceed(args);

		// Step. Print result if needed
		switch (audit.value()) {
		case AFTER:
		case BEFORE_AND_AFTER:
			this.print("[result] " + obj);
		default:
			this.printBar();
			break;
		}
		
		// Step. Return
		return obj;
	}

	private void print(Object obj) {
		log.info(obj.toString());
	}

	private void printArgs(Object[] args) {
		IntStream.range(0, args.length).forEach(idx -> {
			log.info(String.format("[params] arg%d = %s", idx, args[idx]));
		});

	}
	
	private void printBar(){
		log.info("===========");
	}
}
