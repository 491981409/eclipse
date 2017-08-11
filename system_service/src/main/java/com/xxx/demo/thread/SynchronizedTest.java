package com.xxx.demo.thread;

public class SynchronizedTest extends Accumulator {
	{id ="synchronized";}
	
	@Override
	public synchronized void accumulate() {
		try {
			value += preLoaded[index++];
			if(index>= SIZE) index = 0;
		} catch (Exception e) {
			
		}
	}

	@Override
	public long read() {
		return value;
	}

}
