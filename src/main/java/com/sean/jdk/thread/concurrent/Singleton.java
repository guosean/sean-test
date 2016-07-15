package com.sean.jdk.thread.concurrent;

public class Singleton {
	
	private  Object obj = null;
	static int count=0;
	public  Object getObj(){
		if(null == obj){
			obj = new Object();
		}
		return obj;
	}
	public synchronized int getCount(){
		return count++;
	}
	public  String getName(){
		return "test"+count;
	}

}
