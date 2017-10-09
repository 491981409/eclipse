package com.xxx.utils.log;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.xxx.utils.RequestParamUtil;

@Aspect
@Component
public class LoggerAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggerAspect.class);
	
	@Around("execution (* com.xxx.controller..*.*(..))")
	public Object aroundLogger(ProceedingJoinPoint jp) throws Throwable{
		try{
			String param ="";
			for (Object item : jp.getArgs()) {
				if(item instanceof DefaultMultipartHttpServletRequest){
					DefaultMultipartHttpServletRequest request = (DefaultMultipartHttpServletRequest)item;
					param =RequestParamUtil.formJSONObject(request).toJSONString();
				}
				if(item instanceof ShiroHttpServletRequest){
					ShiroHttpServletRequest request = (ShiroHttpServletRequest)item;
					param =RequestParamUtil.formJSONObject(request).toJSONString();
				}
			}
			log.info("=============={class:"+jp.getTarget().getClass().getName()+ "},{"+
					  "method:" +jp.getSignature().getName()+"},{"+
					  "param:" + param + "}==========");
			Object result = jp.proceed();
			return result;
		}catch(Throwable ex){
			throw ex;
		}
	}
}
