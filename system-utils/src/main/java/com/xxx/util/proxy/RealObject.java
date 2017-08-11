package com.xxx.util.proxy;


public class RealObject implements Interface {

	@Override
	public void doSomething(String name) {
		System.out.println(name);
	}
	
}
