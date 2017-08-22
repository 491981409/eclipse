package com.xxx.utils.listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;

	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	/** 
	* 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型. 
	*/  
	@SuppressWarnings("unchecked")  
	public static <T> T getBean(String name) {  
	return (T) applicationContext.getBean(name);  
	}  
	  
	/** 
	* 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型. 
	*/  
	@SuppressWarnings("unchecked")  
	public static <T> T getBean(Class<T> clazz) {  
	return (T) applicationContext.getBeansOfType(clazz);  
	}  
	  
	
	
}
