package com.xxx.utils.log;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggerAspect.class);
	
	@Around("execution (* com.xxx.controller..*.*(..))")
	public Object aroundLogger(ProceedingJoinPoint jp) throws Throwable{
		log.debug("==============class:"+jp.getTarget()+ "method:"
				+jp.getSignature().getName()+",param:"+Arrays.toString(jp.getArgs())+"==========");
		try{
			Object result = jp.proceed();
			return result;
		}catch(Throwable ex){
			throw ex;
		}
	}
}
