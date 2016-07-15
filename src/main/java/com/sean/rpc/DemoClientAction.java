
package com.sean.rpc;

import java.rmi.RemoteException;

/**
 * @PURPOSE:
 * @AUTHOR:zhenbin.guo
 * @DATE:2015年9月2日
 */

public class DemoClientAction {
    
    private DemoService demoService;
    
    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }
 
    public void start() {
        try {
            String hello = demoService.sayHello("world");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
