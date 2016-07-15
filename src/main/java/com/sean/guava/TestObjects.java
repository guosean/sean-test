package com.sean.guava;

import junit.framework.Assert;

import org.junit.Test;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

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
	@Test
	public void testToStringhelper(){
		Person p = new Person();
		p.name = "name";
		ToStringHelper sh = Objects.toStringHelper(p);
		Assert.assertEquals("Person{}", sh.toString());
		sh.add("name", p.name);
		Assert.assertEquals("Person{name=name}", sh.toString());
	}
    
	static class Person{
		String name;
	}
}
