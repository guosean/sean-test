package com.sean.jdk.annotation;

import java.lang.reflect.Method;

/**
 * @AUTHOR: guozb
 * @DATE: 2014-7-1
 */
@MyAnn(id=1,desc="class")
public class TestAnn {
    
//	@MyAnn(id=1,desc="m1")
	public void m1(){};
	
//	@MyAnn(id=2,desc="m2")
	public void m2(){};
	
	public static void main(String[] args) {
		Method[] ms = TestAnn.class.getDeclaredMethods();
        for (Method method : ms) {
			if(method.isAnnotationPresent(MyAnn.class)){
				MyAnn mn = method.getAnnotation(MyAnn.class);
				System.out.println("method name:"+method.getName()+" myann "+mn.id()+mn.desc());
			}
		}
        MyAnn man = TestAnn.class.getAnnotation(MyAnn.class);
        System.out.println(String.format("MyAnn id{%d} desc{%s}", man.id(),man.desc()));
	}
}
