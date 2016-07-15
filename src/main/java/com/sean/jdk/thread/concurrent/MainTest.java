package com.sean.jdk.thread.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.junit.Test;

public class MainTest {
	static Singleton sg = new Singleton();
	static int thread_num = 200;
	static int client_num =100;
	static int count=0;
	static SumObj obj = new SumObj();
	public static void main(String[] args) {
//		System.out.println(sg.getObj()==(sg.getObj()));
		int num = 100;
		final Semaphore semp = new Semaphore(thread_num);
		ExecutorService exec = Executors.newFixedThreadPool(3);
		
		for (int i=0; i<client_num; i++){
			exec.execute(new Runnable() {
				public void run() {
					try{
//							semp.acquire();
							Random random = new Random();
							obj.setX(random.nextInt(10));
							obj.setY(random.nextInt(10));
							obj.setSum();
							System.out.println(obj.toString());
						/*	System.out.println(Thread.currentThread().getName());
							Singleton singleton = new Singleton();
							System.out.println("obj"+singleton.getCount());
							System.out.println(singleton.getName());
							System.out.println(count++);*/
//							semp.release();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			});
		}
	  	exec.shutdown();
	  	System.out.println(count);
	}
	
	private static void print(String str){
		synchronized (str) {
			System.out.println(str);
		}
	}
   @Test
	public void testSum(){
		SumObj obj = new SumObj();
		obj.setX(new Random().nextInt(10));
		obj.setSum();
		System.out.println(obj.toString());
	}
}
