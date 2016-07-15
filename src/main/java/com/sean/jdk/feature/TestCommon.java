package com.sean.jdk.feature;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Test;

import com.google.common.base.Joiner;

public class TestCommon {
	@Test
	public void testSplit(){
		String[] arrays = Pattern.compile(",").split("a");
		Assert.assertTrue(arrays.length==1);
		System.out.println(arrays[0]);
		arrays = Pattern.compile(",").split("a,b,");
		Assert.assertTrue(arrays.length==2);
	}
	@Test
	public void testArrayCopy(){
		int len = 10000;
		String[] oriArray = new String[len];
		String[] loopArray = new String[len];
		String[] copyArray = new String[len];
		for(int i=0; i<len; i++){
			oriArray[i] = String.valueOf(i);
		}
		long start = System.nanoTime();
		for(int i=0; i<len; i++){
			loopArray[i] = oriArray[i];
		}
		System.out.println(System.nanoTime() - start);
		System.out.println(Joiner.on(',').join(loopArray));
		start = System.nanoTime();
		System.arraycopy(oriArray, 0, copyArray, 0, len);
		System.out.println(System.nanoTime() - start);
		System.out.println(Joiner.on(',').join(copyArray));
	}
	@Test
	public void testSwitch(){
		int i=3;
		switch(i){
		case 1:
			System.out.println(1);
			break;
		case 2:
			System.out.println(2);
			break;
		default:
			System.out.println("default");
		}
	}
	@Test
	public void testVars(){
		List<String> list = Arrays.asList("1","2");
		processList(list);
		for (String p : list) {
			System.out.println(p);
		}
	}
	private void processList(final List<String> list) {
		String p=null;  
		List<String> pl = list;
		for (int i = 0; i < pl.size(); i++) {
			p = pl.get(i);
			p = p+"t";
//			p.name = "t"+p.name;
		}
	}
	class Person{
		String name;
		public Person(String name){
			this.name = name;
		}
		public String toString(){
			return this.name;
		}
	}

}
