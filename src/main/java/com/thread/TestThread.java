package com.thread;

import org.junit.Test;

public class TestThread {
	@Test
	public void testCurrentThread() throws InterruptedException{
		System.out.println(Thread.currentThread());
		System.out.println("main 0:"+System.currentTimeMillis());
		Thread.sleep(2000);
		System.out.println("main 1:"+System.currentTimeMillis());
		new Thread(){
			public void run() {
				System.out.println(Thread.currentThread());
				try {
					System.out.println(Thread.currentThread()+" begin:"+System.currentTimeMillis());
//					Thread.currentThread();
					this.sleep(1000);
					System.out.println(Thread.currentThread()+" end:"+System.currentTimeMillis());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
	}

}
