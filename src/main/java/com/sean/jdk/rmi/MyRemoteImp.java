package com.sean.jdk.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
/**
 * 为了要成为远程服务对象,对象必须要有与远程有关的功能,其中最简单的方法就是继承UnicastRemoteObject
 *  (来自java.rmi.server)以让这个父类处理这些工作	
 * @author sean
 *
 */
public class MyRemoteImp extends UnicastRemoteObject implements MyRemote {

	private static final long serialVersionUID = -8756765781080616918L;
	/**
     * 父类的构造函数声明了异常,所有你必须写出构造函数,因为它代表你的构造函数会调用有风险的程序代码
     * 
     * UnicastRemoteObject有个小问题,它的构造函数会抛出RemoteException.处理它的唯一方式就是
     * 对自己的实现声明一个构造,如此才会有地方可以声明出RemoteException.当类被初始化的时候,父类
     * 的构造函数一定会被调用,如果父类的构造函数抛出异常,我们也必须声明的自定义的构造函数会抛出异常
     * @throws RemoteException
     */
	protected MyRemoteImp() throws RemoteException {	}

	public String sayHello() throws RemoteException {
		return "MyRemoteImp say Hello!";
	}
	
	public static void main(String[] args) {
		try{
		/**
		 * 我们已经有了远程服务,还必须要让远程用户存取,这可以通过将它初始化并加进RMI Registry
         * (它一定要运行起来,不然此程序就会失败).当注册对象时,RMI系统会把stub加到registry中,
         * 因为这是客户端所需要的.使用java.rmi.Naming的rebind()来注册服务	
		 */
	    MyRemote service = new MyRemoteImp();
	    LocateRegistry.createRegistry(1097);
	    /**
         * 创建出远程对象,然后使用静态的Naming.rebind()来产生关联,所注册的名称会提供客户端查询
         */
	    Naming.rebind("rmi://127.0.0.1:1097/hello",service);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
