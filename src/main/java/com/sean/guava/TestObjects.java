package com.sean.guava;

import com.google.common.base.Objects;
import junit.framework.Assert;
import org.junit.Test;

public class TestObjects {

	@Test
	public void testEquals(){
		Assert.assertTrue(!Objects.equal(null, ""));
		Assert.assertTrue(Objects.equal(new String("abc"), "abc"));
	}

	@Test
	public void testHashcode(){
		Object obj = new Object();
		System.out.println(obj.hashCode());
		System.out.println(Objects.hashCode(obj));
	}

    
	static class Person{
		String name;
	}
}
