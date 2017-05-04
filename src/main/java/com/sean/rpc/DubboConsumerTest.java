
package com.sean.rpc;

import java.rmi.RemoteException;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @PURPOSE:
 * @AUTHOR:zhenbin.guo
 * @DATE:2015年9月2日
 */

public class DubboConsumerTest {
    
    public static void main(String[] args) {
        /*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dubbo-consumer.xml");
        DemoService service = (DemoService) context.getBean("demoService");*/
          DemoService service = buildConsumerWithApi();
        try {
            String result = service.sayHello(" world");
            System.out.println("consumer:"+result);
//            System.in.read();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public static DemoService buildConsumerWithApi(){
        ApplicationConfig applicationConfig = new ApplicationConfig("sean_consumer");
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");
        registryConfig.setProtocol("zookeeper");
        registryConfig.setGroup("/sean/test");
        ReferenceConfig<DemoService> referenceConfig = new ReferenceConfig<DemoService>();
        referenceConfig.setInterface(DemoService.class);
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setVersion("1.0.0");
        return referenceConfig.get();
    }

}
