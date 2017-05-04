package com.sean.jdk.extend;
/**
 * 1、父类与子类的加载顺序
 * 2、继承的JVM处理
 * 3、声明类型与实际引用类型在实际调用时关系
 *    如 Parent cld = new Child();
 *      cld.invoke();
 * 4、 静态处理
 *     
 * 
 * 
 */
public class TestExtend extends Parent{

	static {
		System.out.println("TestExtend static block");
	}

	public static void main(String[] args) {
		/*Parent pt = new Child();
		// Child cd = new Child();
		System.out.println(pt.getName() + pt.getAge() + " " + pt.age);
//		pt.imStatic();
		System.out.println(pt.getClass().toString());
		pt.imGeneral();*/
		System.out.println("test");
		// System.out.println(cd.getName()+cd.getAge()+" "+cd.age);
	}

}

class Parent {
	static {
		System.out.println("Parent static");
		/*if(1 != 2){
			throw new RuntimeException();
		}*/
	}

	public Parent() {
		this.name = "parent";
		this.age = 10;
		System.out.println("Parent build");
	}

	private String name;
	public int age;
    protected String pro;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public static void imStatic(){
		System.out.println("parent static");
	}
	
	public void imGeneral(){
		System.out.println("parent general");
	};

	protected final void testFinal(){
		System.out.println("parent final");
	}

	private void testPrivate(){
		System.out.println("private method");
	}


}

class Child extends Parent {
	static {
		System.out.println("Child static");
	}

	public Child() {
		System.out.println("Child build");
		this.age = 20;
	}

	// public int age;
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public static void imStatic(){
		System.out.println("child static");
	}
	public void imGeneral(){
		System.out.println("child general");
	}

}
