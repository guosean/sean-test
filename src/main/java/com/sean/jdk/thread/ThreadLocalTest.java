package com.sean.jdk.thread;

public class ThreadLocalTest  implements Runnable{

	public void run() {
		int serialNum = SerialNum.get();
		System.out.println("线程 " + Thread.currentThread().getName()
                + " 获取到的序列号是" + serialNum);
        System.out.println("线程 " + Thread.currentThread().getName()
                + " 修改了序列号为" + (serialNum * 3));
        SerialNum.set(serialNum * 3);
        System.out.println("线程 " + Thread.currentThread().getName()
                + " 再次获得的序列号是" + SerialNum.get());
	}
	
	public static void main(String[] args) {
		ThreadLocalTest tt = new ThreadLocalTest();
		Thread t1 = new Thread(tt, "Thread A");
		Thread t2 = new Thread(tt, "Thread B");
		t1.start();
		try{
			t1.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		t2.start();
	}
	
}

class SerialNum{
	private static int nextSerialNum = 1;
	
	private static ThreadLocal<Integer> serialNum = new ThreadLocal<Integer>(){
		protected synchronized Integer initialValue() {
			return Integer.valueOf(nextSerialNum++);
		};
	};
	
	public static int get(){
		return serialNum.get().intValue();
	}
	
	public static void set(Integer num){
		serialNum.set(num);
	}
}
