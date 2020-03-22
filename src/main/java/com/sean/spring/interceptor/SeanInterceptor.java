package com.sean.spring.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;

/**
 * Created by guozhenbin on 2017/5/5.
 */
public class SeanInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        Object[] args = methodInvocation.getArguments();
        System.out.println(String.format("method:%s,args:%s",method,args));
        if(StringUtils.equals("modify",method.getName())){

           return "拦截了"+methodInvocation.proceed();
        }
        return null;
    }
}
