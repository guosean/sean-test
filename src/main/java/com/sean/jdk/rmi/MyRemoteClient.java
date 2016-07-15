package com.sean.jdk.rmi;

import java.rmi.Naming;

public class MyRemoteClient {
	
	public void exec(){
		try{
			/**
             * 客户端必须取得stub对象,因为客户端必须要调用它的方法.这就得靠RMI registry了.客户端会像查询电话
             * 簿一样地搜索,找出上面有相符的名称的服务.
             * 客户端查询RMIRegistry,返回stub对象
             * Naming.lookup("rmi://127.0.0.1/Remote Hello World");
             * 参数说明
             * rmi://127.0.0.1/Remote Hello World
             * 127.0.0.1表示主机名称或主机IP地址
             * Remote Hello World必须要跟注册的名称一样
             * 
             */
			MyRemote service = (MyRemote)Naming.lookup("rmi://127.0.0.1:1097/hello");
			System.out.println(service.sayHello());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
   public static void main(String[] args) {
	new MyRemoteClient().exec(); 
   }
}
