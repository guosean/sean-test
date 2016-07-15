package com.sean.jdk.loader;
/**
 * <p>
 * 1.对于静态字段，只有直接定义该字段的类才会被初始化，因此通过子类
 * 调用父类中定义的静态字段，只会触发父类的初始化
 * <p>
 * 2.java会在编译过程中将类A引用到的类B中的常量存储到类A的常量池中，
 * 编译完成后两个类即不存在关系
 * @author sean
 *
 */
public class NoInitialization {
	
	public static void main(String[] args) {
		//System.out.println(ConstClass.value);
	    System.out.println(SubClass.value);
	}

}

class ConstClass{
	
   static{
	   System.out.println("ConstClass init!");
   }
   public static final String value = "value";
	
}

class SuperClass{
	static{
		System.out.println("SuperClass init!");
	}
	public static String value ="value";
}
class SubClass extends SuperClass{
	 static{
		 System.out.println("SubClass init!");
	 }
}