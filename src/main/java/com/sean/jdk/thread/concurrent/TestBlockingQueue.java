package com.sean.jdk.thread.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TestBlockingQueue {
	ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<String>(10);
   static AtomicInteger count = new AtomicInteger(0); 
   CountDownLatch latch = new CountDownLatch(1000);
	@Test
	public void testArrayBlockingQueue() throws InterruptedException{
		ExecutorService es = Executors.newFixedThreadPool(10);
		es.submit(new Runnable() {
			@Override
			public void run() {
				try {
					while(count.intValue()<1000){
						System.out.println(Thread.currentThread()+" put "+count.intValue());
						abq.put("test"+count.incrementAndGet());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		es.submit(new Runnable() {
			
			@Override
			public void run() {
			      for(;;){
			    	 String content;
					try {
						content = abq.take();
						 System.out.println(Thread.currentThread()+" poll:"+content);
						 latch.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//			    	 if(StringUtils.isNotEmpty(content)){
			    	
//			    	 }
				}
			}
		});
		latch.await();
		es.shutdown();
		System.out.println("blocking");
	}
	

}
