package com.xxx.demo.thread;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Accumulator {
	
	public static long cycles = 5L;
	
	private static final int N = 4;
	
	public static ExecutorService exec  = Executors.newFixedThreadPool(N*2);
	
	private static CyclicBarrier barrier = new CyclicBarrier(7);
	
	protected volatile int index = 0;
	
	protected volatile long value = 0;
	
	protected long duration = 0;
	
	protected String id ="error";
	
	protected final static int SIZE =100000;
	
	protected static int[] preLoaded = new int[SIZE];
	
	static {
		Random random = new Random(47);
		for (int i = 0; i < SIZE; i++) {
			preLoaded[i] = random.nextInt();
		}
	}
	
	
	public abstract void accumulate();
	
	public abstract long read();
	
	private class Modifier implements Runnable{

		@Override
		public void run() {
			for (int i = 0; i < cycles; i++) {
				accumulate();
			}
			try {
				barrier.await();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	public void timeTest(){
		long start = System.nanoTime();
		for (int i = 0; i < N; i++) {
			exec.execute(new Modifier());
		}
		try {
			barrier.await();
		} catch (Exception e) {
			// TODO: handle exception
		}
		duration = System.nanoTime() - start;
		System.out.printf("%-13s: %13d\n",id,duration);
	}
	

}
