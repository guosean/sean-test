package com.sean.jdk.thread.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class TestVolatile {
	
	static  AtomicInteger race= new AtomicInteger(0);

	public static void increase(){
		race.incrementAndGet();
	}

	static final int THREADS_COUNT = 20;
	
	public static void main(String[] args) {
		  Thread[] thds = new Thread[THREADS_COUNT];
		  for (int i = 0; i < thds.length; i++) {
			thds[i] = new Thread(new Runnable() {
				
				public void run() {
                    for(int i=0; i<10000; i++){
                    	increase();
                    }					
				}
			});
			thds[i].start();
		}
		  while(Thread.activeCount()>1){
			  Thread.yield();
		  }
	     System.out.println(race);
	}
	
}
