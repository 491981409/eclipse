package com.xxx.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {
	
	private Object proxied;
	
	public DynamicProxyHandler(Object prox){
		this.proxied = prox;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("invoke:"+ proxy.getClass()+" ,"+method.getName()+", "+ args);
		args[0] ="age";
		return method.invoke(proxied, args);
	}
	

}
