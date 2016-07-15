
package com.sean.rpc;

import java.rmi.RemoteException;

/**
 * @PURPOSE:
 * @AUTHOR:zhenbin.guo
 * @DATE:2015年9月2日
 */

public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) throws RemoteException {
        System.out.println(name);
        return "Hello " + name;
    }

}
