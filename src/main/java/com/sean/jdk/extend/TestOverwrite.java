package com.sean.jdk.extend;

public class TestOverwrite {
	
	public static void main(String[] args) {
//		TestOverwrite to = new TestOverwrite();
		Human man = new Man();
		Human wm = new Woman();
		man.sayHello();
		wm.sayHello();
		man = new Woman();
		man.sayHello();
	}
	class InnerClass{
		int i=0;
		int getI(){
			return i;
		}

	}
   static abstract class Human{
	   public abstract  void sayHello();

	   public static void main(String[] args) {
		   System.out.println("i'm human");
	   }
   }
   
   static class Man extends Human{ 
	   public  void sayHello(){
				System.out.println("hello man!");
			}
   }
   
   static class Woman extends Human{
		   public   void sayHello(){
				System.out.println("hello woman!");
			}
   }
   
	public void sayHello(Human human){
		System.out.println("hello guy!");
	}
	/*public  void sayHello(Man man){
		System.out.println("hello man!");
	}
	public   void sayHello(Woman woman){
		System.out.println("hello woman!");
	}*/
}
