package com.sean.guava;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class TestImmutableCollections {
	@Test
	public void testCopy(){
		List<String> list = new ArrayList<String>();
		for(int i=0; i<1000000; i++){
			list.add(String.valueOf(i));
		}
       ImmutableList<String> il = ImmutableList.copyOf(list);
       long start = System.nanoTime();
       String value = "999999";
	   list.contains(value);
       System.out.println(System.nanoTime()-start);
       start = System.nanoTime();
       il.contains(value);
       System.out.println(System.nanoTime()-start);
	}
	
}
