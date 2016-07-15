package com.sean.guava;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

public class TestComparator {
	
	static class Foo{
		 String sorteBy;
		 int notSortedBy;
		 public String toString(){
			 return sorteBy;
		 }
	}
	@Test
	public void testCompare(){
		Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
			@Override
			public String apply(Foo input) {
				return input.sorteBy;
			}
		});
		List<Foo> fList = new LinkedList<Foo>();
		String max = "";
		for(int i=0; i<10; i++){
			Foo foo = new Foo();
			int nextInt = new Random().nextInt();
			max = max.compareTo(String.valueOf(nextInt))>0?max:String.valueOf(nextInt);
			foo.sorteBy = String.valueOf(nextInt);
			fList.add(foo);
		}
		ordering.isOrdered(fList);
		Assert.assertEquals(String.valueOf(max), ordering.max(fList.iterator()).sorteBy);
//		System.out.println(ordering.leastOf(fList.iterator(), 5));
	}

}
