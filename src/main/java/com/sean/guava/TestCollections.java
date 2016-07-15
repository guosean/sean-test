package com.sean.guava;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;

public class TestCollections {
	@Test
	public void testCount(){
		List<Integer> list = Arrays.asList(1,2,1,4);
		Assert.assertEquals(2, Iterables.frequency(list, 1));
	}
	@Test
   public void testConcat(){
	   Iterable<Integer> its = Iterables.concat(Ints.asList(1,2), Ints.asList(3,2,1));
	   Assert.assertTrue(1==Iterables.getLast(its));
   }
}
