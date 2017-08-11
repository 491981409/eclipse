package com.xxx.util.proxy;

import java.lang.reflect.Proxy;

import org.junit.Test;

public class SimpleProxDemo {

	@Test
	public void test() {
		Interface face = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
				new Class[] { Interface.class }, new DynamicProxyHandler(new RealObject()));
		face.doSomething("name");
	}

}
