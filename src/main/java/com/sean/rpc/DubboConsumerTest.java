
package com.sean.rpc;

import java.rmi.RemoteException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @PURPOSE:
 * @AUTHOR:zhenbin.guo
 * @DATE:2015年9月2日
 */

public class DubboConsumerTest {
    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dubbo-consumer.xml");
        DemoService service = (DemoService) context.getBean("demoService");
        try {
            String result = service.sayHello(" world");
            System.out.println("consumer:"+result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        
    }

}
