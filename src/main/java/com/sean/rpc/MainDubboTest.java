
package com.sean.rpc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @PURPOSE:
 * @AUTHOR:zhenbin.guo
 * @DATE:2015年9月2日
 */

public class MainDubboTest {
    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dubbo-provider.xml");
        context.start();
        System.out.println("press any key to exit");
        try{
            System.in.read();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
