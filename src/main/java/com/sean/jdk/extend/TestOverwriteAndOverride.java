package com.sean.jdk.extend;

import org.junit.Test;

/**
 * 测试重写 和 重载 另个概念：静态类型 和 实际类型 如 Human man = new Man(); |静态类型 |实际类型
 * 
 * @author sean
 * 
 */
public class TestOverwriteAndOverride {

	static class A{
		public String show(D obj){
			return "A and D";
		}
		public String show(A obj){
			return "A and A";
		}
	}
	static class B extends A{
		 public String show(B obj){  
             return ("B and B");  
      }  
      public String show(A obj){  
             return ("B and A");  
      }   
      public String show(D obj){
    	    return "B and D";
      }
	}
	static class C extends B{
		 public String show(B obj){  
             return ("C and B");  
      }  
      public String show(A obj){  
             return ("C and A");  
      }   
      public String show(D obj){
    	    return "C and D";
      }
	};
	static class D {};
	public static void main(String[] args) {
//	   A a1 = new A();
	   A a2 = new B();
	   A a3 = new C();
	   B b = new B();
	   D d = new D();
	   System.out.println(a2.show(d));
	   System.out.println(a3.show(d));
//	   System.out.println(a1.show(b));
	}

	@Test
	public void testOverWrite() {
		Human man = new Man();
		Human wm = new Woman();
		TestOverwriteAndOverride to = new TestOverwriteAndOverride();
		to.sayHello(man);
		to.sayHello(wm);
		to.sayHello((Man)man);
		to.sayHello((Woman)wm);
	}
	@Test
	public void testOverRide(){
		Human man = new Man();
		Human wm = new Woman();
		Man manT = new Man();
/*		man.sayHello();
		wm.sayHello();*/
		man.checkMan(man);
		man.checkMan(wm);
		wm.checkMan(man);
		wm.checkMan(wm);
		man.checkMan(manT);
	}

	static  class Human {
		public  void sayHello(){
			System.out.println("human hello");
		};
		public void checkMan(Human human){
			System.out.println("human");
		}
		public void checkMan(Man man){
			System.out.println("man");
		}
		public void checkMan(Woman wm){
			System.out.println("woman");
		}
		
	}

	static class Man extends Human {
		public void sayHello() {
			System.out.println("hello man!");
		}
		public void checkMan(Human human){
			System.out.println("Man.human");
		}
		public void checkMan(Man man){
			System.out.println("Man.man");
		}
		public void checkMan(Woman wm){
			System.out.println("Man.woman");
		}
	}

	static class Woman extends Human {
		public void sayHello() {
			System.out.println("hello woman!");
		}
		public void checkMan(Human human){
			System.out.println("Woman.human");
		}
		public void checkMan(Man man){
			System.out.println("Woman.man");
		}
		public void checkMan(Woman wm){
			System.out.println("Woman.woman");
		}
	}

	public void sayHello(Human human) {
		System.out.println("hello guy!");
	}

	public void sayHello(Man man) {
		System.out.println("hello man!");
	}

	public void sayHello(Woman woman) {
		System.out.println("hello woman!");
	}
	
}
