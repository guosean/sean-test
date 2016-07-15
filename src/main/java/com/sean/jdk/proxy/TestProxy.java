package com.sean.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestProxy {
	@Test
	public void testProxy(){
		List<String> list = new ArrayList<String>();
		list.add("1");
		System.out.println(list);
		ListProxy<String> proxy = new ListProxy<String>();
		list = proxy.bind(list);
		list.add("2");
		System.out.println(list);
	}

}

class ListProxy<T> implements InvocationHandler{

	private List<T> list;

	@SuppressWarnings("unchecked")
	public List<T> bind(List<T> list){
		this.list = list;
		return (List<T>)Proxy.newProxyInstance(list.getClass().getClassLoader(), list.getClass().getInterfaces(), this);
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		 if ("add".equals(method.getName())) {
			   return method.invoke(list, "proxydone");
          }
          else {
              return method.invoke(list, args);
          }
	}
	
}
