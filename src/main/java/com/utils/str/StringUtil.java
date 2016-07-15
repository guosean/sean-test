package com.utils.str;

import java.io.UnsupportedEncodingException;

public class StringUtil {
  
	public static String encodeStr2GBK(String src) throws UnsupportedEncodingException{
		return new String(src.getBytes(),"GB2312");
	}
	
	
}

