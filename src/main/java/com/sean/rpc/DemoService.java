
package com.sean.rpc;

import java.rmi.RemoteException;

/**
 * @PURPOSE:
 * @AUTHOR:zhenbin.guo
 * @DATE:2015年9月2日
 */

public interface DemoService {
    
    String sayHello(String name) throws RemoteException;
    
}
