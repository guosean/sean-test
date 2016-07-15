package com.sean.jmm;

import java.lang.reflect.Field;

import org.junit.Test;

/**
 * @AUTHOR: guozb
 * @DATE: 2014-6-5
 */
public class TestString {
	@Test
	public void testStringRef() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
      String s1 = "Hello world";
      String s2 = new String("Hello world");
      Field field = s1.getClass().getDeclaredField("value");
      field.setAccessible(true);
      char[] values = (char[]) field.get(s2);
      values[1] = 'j';
      values[2] = 'a';
      System.out.println(s1);
      System.out.println(s2);
	}
}