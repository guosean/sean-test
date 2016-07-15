package com.exception;

import org.junit.Test;


public class TestException {
	
	@Test
	public void testExcRun(){
		try{
			test1();
//			test2();
			System.out.println("teeest");
		}catch(NullPointerException e){
			System.out.println(e.getMessage());
	}/*	catch(NullArgumentException e1){
			System.out.println(e1.getMessage());
		}*/catch(Exception e2){
			System.out.println(e2.getMessage());
		}
		
	}
	
	public void test1() throws NullPointerException{
		throw new NullPointerException("踩踩踩");
	}
/*	public void test2() throws NullArgumentException{
		throw new NullArgumentException("测试");
	}*/

}
