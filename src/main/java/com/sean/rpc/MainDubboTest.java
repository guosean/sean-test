
package com.sean.rpc;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @PURPOSE:
 * @AUTHOR:zhenbin.guo
 * @DATE:2015年9月2日
 */

public class MainDubboTest {
    
    public static void main(String[] args) {
       /* ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dubbo-provider.xml");
        context.start();
        System.out.println("press any key to exit");*/
        buildProviderWithApi();
        try{
            System.in.read();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void buildProviderWithApi(){
        ApplicationConfig applicationConfig = new ApplicationConfig("sean_provider");
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");
        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo",20880);
        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<DemoService>();
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setRef(new DemoServiceImpl());
        serviceConfig.export();
    }

}
