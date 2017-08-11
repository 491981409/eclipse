package com.xxx.demo.thread;


public class SynchronizationComparisons {
	
	static LockTest lock = new LockTest();

	static Accumulator syn = new SynchronizedTest();
	public static void main(String[] args) {
		lock.timeTest();
		syn.timeTest();
		Accumulator.exec.shutdown();
	}

}
