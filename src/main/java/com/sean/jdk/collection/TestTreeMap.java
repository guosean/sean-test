package com.sean.jdk.collection;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.junit.Test;
/**
 * TreeMap基于红黑树实现，有序
 * @author sean
 *
 */
public class TestTreeMap {
	@Test
	public void testTreeMap(){
		Map<Integer,Integer> map = new TreeMap<Integer, Integer>();
		Random rd = new Random();
		for(int i=0; i<10; i++){
			int nextInt = rd.nextInt(100);
			System.out.println(nextInt);
			map.put(nextInt, rd.nextInt(10));
		}
		for(Integer key:map.keySet()){
			System.out.println(key);
		}
	}

}
