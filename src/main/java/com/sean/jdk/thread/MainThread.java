package com.sean.jdk.thread;

import java.util.Date;

public class MainThread {
	
	public static void main(String[] args) {
		Thread p1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("p1 thread");
			}
		});
		System.out.println(p1.getState().toString());
		p1.start();
		System.out.println(p1.getState().toString());

	    /*new Thread() {
			@Override
			public void run() {
				while(true) {
				   System.out.println(Thread.currentThread().getName());
				   try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		}.start();*/

	}

}

class PrinterThread implements Runnable{
   int pauseTime;
   String name;
   
   public PrinterThread(int pauseTime,String name) {
	   this.pauseTime = pauseTime;
	   this.name = name;
}
	public void run() {
		long st = System.currentTimeMillis();
		System.out.println(name+" startTime:" + st);
		while(true){
			 try{
				 long et = System.currentTimeMillis();
				 System.out.println(name+"endTime:" + et);
				 if(et-st<3000){
					 Thread.currentThread().interrupt(); 
					 System.out.println(name+"interrupt");
				 }
				 else{
					  System.out.println(name+":"+new Date(System.currentTimeMillis()));
					  Thread.sleep(pauseTime);
				 }
				  
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		}
	}
	
}
