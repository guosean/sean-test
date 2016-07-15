package com.sean.jdk.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @AUTHOR: guozb
 * @DATE: 2014-6-27
 */
public class ProducerAndConsumer {
	
    public static void main(String[] args) {
    	final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
    	ExecutorService producer = Executors.newFixedThreadPool(2);
    	ExecutorService consumer = Executors.newFixedThreadPool(10);
    	producer.submit(new Runnable() {
			int count= 0;
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
						queue.add(Thread.currentThread().getName()+count++);
						System.out.println("put"+Thread.currentThread().getName()+"-"+count);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
    	consumer.submit(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						String str = queue.take();
						System.out.println(str);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
    	System.out.println("over");
	}
}
