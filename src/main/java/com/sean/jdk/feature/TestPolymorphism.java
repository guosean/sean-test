package com.sean.jdk.feature;
/**
 * 测试多态
 * @author sean
 *
 */
public class TestPolymorphism {
	
	public static void main(String[] args) {
		A a1 = new A();
		A a2 = new B();
		B b = new B();
		C c = new C();
		D d = new D();
		System.out.println(a1.show(b));   //① A and A
        System.out.println(a1.show(c));   //② A and A
        System.out.println(a1.show(d));   //③ A and D
        System.out.println(a2.show(b));  //④ B and A
        System.out.println(a2.show(c));   //⑤B and A
        System.out.println(a2.show(d));   //⑥A and D
        System.out.println(b.show(b));     //⑦B and B
        System.out.println(b.show(c));     //⑧B and B
        System.out.println(b.show(d));     //⑨A and D   
	}
	
}
class A{
		public String show(D obj){
			return "A and D";
		}
		public String show(A obj){
			return "A and A";
		}
}
class B extends A{
	  public String show(B obj){
		  return "B and B";
	  }
	  public String show(A obj){
		  return "B and A";
	  }

}
class C extends B{}
class D extends B{}