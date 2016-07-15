package com.sean.guava;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class TestMulti {
	@Test
	public void testHashMulti(){
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("1");
		list.add("1");
		Multiset<String> mt = HashMultiset.create(list);
		Set<String> set = mt.elementSet();
		for (String string : set) {
			System.out.println(mt.count(string));
		}
	}

}
