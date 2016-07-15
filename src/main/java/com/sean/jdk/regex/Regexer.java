package com.sean.jdk.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class Regexer {
	@Test
	public void testRegex(){
		String reg=".*[0-9]+";//".+$";
		Pattern patt=Pattern.compile(reg);
		String input = "rege 12321";
		Matcher mat=patt.matcher(input);
		while(mat.find()){
			System.out.println("pi");
			System.out.println(mat.group());
		}
	}

}
