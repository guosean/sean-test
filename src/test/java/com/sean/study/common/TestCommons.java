package com.sean.study.common;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;

public class TestCommons {
	private static final String LINE_SEP = "\n";
	private static final Object TAB = "\t";
	@Test
	public void test(){
            System.out.println(24%7);
	}
	@Test
	public void testIntTochar(){
		int i = 1234560;
		char[] chrs = String.valueOf(i).toCharArray();
		for (int j = 0; j < chrs.length; j++) {
			System.out.println((int)chrs[j]);
		}
	}
	@Test
	public void testFetchInfo(){
		String str="人民网/nz 1月1日/t 讯/ng 据/p 《/w [纽约/nsf 时报/n]/nz 》/w 报道/v";
		String delim=" ";
		StringTokenizer tokenzier=new StringTokenizer(str, delim);
		String token;
		String[] infos;
		String regex="/";
		List<String> exclude= Arrays.asList("w","t","m");
		String word="";
		String nature="";
		StringBuilder combWord=new StringBuilder();
		while(tokenzier.hasMoreTokens()){
			token=tokenzier.nextToken();
			infos=token.split(regex);
			word = infos[0];
			nature = infos[1];
			if(word.startsWith("[")){
				combWord.append(word.substring(1));
			}
			else if(nature.endsWith("]")){
				combWord.append(word);
				System.out.println(toBME(combWord.toString()));
				combWord.setLength(0);
			}
			else{
				if(!exclude.contains(nature)){
					System.out.println(toBME(word));
				}
			}
		}
	}
   
	public void print(String[] strs){
		for (String string : strs) {
			System.out.println(string);
		}
	}
	/**
	 * 解析为BME格式
	 * @param str
	 * @return
	 */
	public String toBME(String word){
		StringBuilder sb=new StringBuilder();
		if(StringUtils.isNotEmpty(word)){
				if(StringUtils.isNotEmpty(word)){
				String tmp;
				if (word.length() == 1) {
					sb.append(word).append(TAB).append("S").append(LINE_SEP);
				} else {
					int size = word.length();
					tmp = String.valueOf(word.charAt(0));
					sb.append(tmp).append(TAB).append("B").append(LINE_SEP);
					if (size == 2) {
						tmp = String.valueOf(word.charAt(1));
						sb.append(tmp).append(TAB).append("E").append(LINE_SEP);
					} else {
						for (int i = 1; i < size; i++) {
							tmp = String.valueOf(word.charAt(i));
							if (i == size - 1) {
								sb.append(tmp).append(TAB).append("E").append(LINE_SEP);
							} else {
								sb.append(tmp).append(TAB).append("M").append(LINE_SEP);
							}
						}
					}
				}
				}
			}
		return sb.toString();
	}

	@Test
	public void testDiff(){
		System.out.println(StringUtils.difference("abc",""));
	}
}
