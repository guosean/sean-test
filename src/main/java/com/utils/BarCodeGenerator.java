package com.utils;

import java.util.Date;

public class BarCodeGenerator {
	
private static long suff = 100;	
	/**
	 * (non-javadoc)
	 * 产生条形码编码
	 * @return
	 */
	public static synchronized String genBarCode(){
		suff += 1;
		if ( suff > 999)
			suff = 100;
		String tem = ("" + new Date().getTime() ) + suff;
		return  tem;
	}
	
	public static void main(String args[])
	{
		
		
			System.out.println(BarCodeGenerator.genBarCode());
		
	}
	

}
