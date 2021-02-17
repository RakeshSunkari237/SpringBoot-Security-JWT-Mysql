package com.jwtsampleapp.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.jwtsampleapp.api.controller.ProductRestController;

@Aspect
@Component
public class AspectExample {

	private static Logger log=Logger.getLogger(ProductRestController.class);
	
	@Around("execution(public * com.jwtsampleapp.api.controller.ProductRestController.*(..) )")
	public Object addLoggingInController(ProceedingJoinPoint jp) throws Throwable {
		Object ob=new Object();
		String functionName = jp.getSignature().getName()+"()";
		log.info("invoked method: "+functionName);
		ob=jp.proceed();
		log.info("method ended :" +functionName);
		return ob;
	}
}
