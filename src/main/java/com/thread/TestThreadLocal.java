package com.thread;

import junit.framework.Assert;

import org.junit.Test;

public class TestThreadLocal {
	
	@Test
	public void testLocaleThread(){
		String value = "test";
		StaticThreadLocal.setString(value);
		Assert.assertEquals(value, StaticThreadLocal.getString());
	    new Thread(){
	    	public void run() {
	    		Assert.assertEquals(null, StaticThreadLocal.getString());
	    	};
	    };
	}

}
class StaticThreadLocal{
	private static final ThreadLocal<String> tl = new ThreadLocal<String>();
	
	public static void setString(String value){
		tl.set(value);
	}
	public static String getString(){
		return tl.get();
	}
}


