package com.sean.spring;

import java.lang.reflect.Method;

import org.springframework.aop.support.NameMatchMethodPointcut;

public class Pointcut extends NameMatchMethodPointcut {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5898640779089864785L;

	@Override
	public boolean matches(Method method, @SuppressWarnings("rawtypes") Class targetClass) {
		  // 设置单个方法匹配
        this.setMappedName("delete");
        // 设置多个方法匹配
        String[] methods = { "delete", "modify" };
        
        //也可以用“ * ” 来做匹配符号
        // this.setMappedName("get*");
        
        this.setMappedNames(methods);

        return super.matches(method, targetClass);
	}

}
