
package com.sean.jdk.feature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * <p> Copyright 200 by Asiainfo-Linkage Software corporation
 * <p>All rights reserved.
 * <p>版权所有：亚信联创科技（中国）有限公司
 * <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分，
 * <p>侵权者将受到法律追究。
 * @PURPOSE:
 * @DESCRIPTION:
 * @AUTHOR: guozb
 * @DATE:2014-1-12
 * @VERSION PSMA
 * @SINCE 
 * @HISTORY: 1.0
 */

public class Varargs {
	
	public static void main(String[] args) {
		testArgs("1","2");
		testArgs(new String[]{"3","4"});
		List<? extends Object> list = Arrays.asList("1","2");
//		testListGeneric(list);
		testClass(ArrayList.class);
	}
	
	public static void testArgs(String... strings){
        if(null==strings){
        	System.out.println("null");
        	return;
        }
		for (int s = 0; s < strings.length; s++) {
			System.out.println(strings[s]);
		}
	}
	
	public static void testListGeneric(List<?> test){
		System.out.println(null!=test?test.size():0);
	}
	
	public static void testClass(@SuppressWarnings("rawtypes") Class<? extends List> clzz){
		System.out.println(clzz);
	}
	
}
