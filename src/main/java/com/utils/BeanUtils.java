package com.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanUtils {
	/**
	 * spring配置上下文
	 */
	private static ApplicationContext springCtx = null;
	
	private static String SPRING_FILE = "config/Context-configure.xml";
	
	private static String JOB_FILE = "config/Quartzjob-configure.xml";
	/**
	 * 初始化Spring配置
	 */
	static {
		//默认只加载memcached
		springCtx = new ClassPathXmlApplicationContext(
				SPRING_FILE);
	}

	/**
	 * 
	 * @Title: getBean
	 * @Description: 获取指定name的bean对象
	 * @param name
	 *            spring中配置的beanid
	 * @return
	 */
	public static Object getBean(final String name) {
		return springCtx.getBean(name);
	}

	/**
	 * 
	 * @Title: initJob
	 * @Description: 初始化定时任务
	 */
	public static void initJob() {
		springCtx = new ClassPathXmlApplicationContext(
				new String[]{SPRING_FILE,JOB_FILE});
	}
}
