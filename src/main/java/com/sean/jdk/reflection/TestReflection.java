package com.sean.jdk.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;

public class TestReflection {
	@Test
	public void testInterfaces() throws ClassNotFoundException{
	  Class clz = ChildWork.class;//MaWork.class;
      Class[] clzs = clz.getInterfaces();
      for (Class class1 : clzs) {
		System.out.println(class1.getName());
	  }
	}
	@Test
	public void testPrivateField() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		MaWork mw = new MaWork("mw");
		Class clz = MaWork.class;
		Field[] fields = clz.getFields();
		Field fd = clz.getDeclaredField("name");
		fd.setAccessible(true);
		System.out.println(fd.get(mw));
	}
	@Test
	public void testGenericField() throws Exception{
		MaWork<String> mw = new MaWork<String>("test");
        Class clz = mw.getClass();
        Field mds = clz.getDeclaredField("k");
       Type type = mds.getType();
       System.out.println(type);
       if(type instanceof ParameterizedType){
    	  ParameterizedType pt = (ParameterizedType)type;
    	  Type[] tps = pt.getActualTypeArguments();
    	  for (Type type2 : tps) {
			System.out.println(type2);
		}
      }
	}
	
}
