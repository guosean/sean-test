package com.sean.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config/aop.xml");
		 IBaseBusiness business = (IBaseBusiness ) ctx.getBean("businessProxy");
//	     business.delete("猫");
	     business.modify("狗");
	}

}
