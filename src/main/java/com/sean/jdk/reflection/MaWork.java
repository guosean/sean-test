package com.sean.jdk.reflection;

import java.util.List;


public class MaWork<K> implements IWork {
	
	private String name;
	private K k;
	private List<String> list;
	
	public MaWork(String name){
		this.name = name;
	}
   public <K> K generic(K k){
	   System.out.println(k);
	   return k;
   }
	public void work() {
		System.out.println(MaWork.class.toString());
	}

}
