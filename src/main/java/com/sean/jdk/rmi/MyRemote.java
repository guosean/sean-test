package com.sean.jdk.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * Remote是个标记性的接口,意味着没有方法,然而它对RMI有特殊的意义,所以必须遵守这项规则
 * @author sean
 *
 */
public interface MyRemote extends Remote {
	/**
     * 远程的接口定义了客户端可以远程调用的方法,它是作为服务的多态化类,也就是说,客户端会
     * 调动有实现此接口的stub,而此stub因为会执行网络和输入/输出工作,所以可能会发生各种
     * 问题,客户端必须处理或声明异常来认知这一类风险,如果该方法在接口中声明异常,调用该方
     * 法的所有程序都必须处理或再声明此异常.
     * 
     * 远程方法的参数和返回值必须是primitive或serializable的.任何远程方法的参数都会被
     * 打包通过网络传送,而这时通过序列化完成的,返回值也是一样.所以,如果使用的是自定义类型
     * 时,必须对其序列化
     * @return
     * @throws RemoteException    
     *                         所有接口中的方法都必须声明RemoteException
     */
	public String sayHello() throws RemoteException;

}
