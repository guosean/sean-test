package com.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * <p>
 * Copyright 200 by Asiainfo-Linkage Software corporation
 * <p>
 * All rights reserved.
 * <p>
 * 版权所有：亚信联创
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分，
 * <p>
 * 侵权者将受到法律追究。
 * 
 * @PURPOSE: TODO
 * @DESCRIPTION: TODO
 * @AUTHOR: guozb
 * @DATE: 2014-3-3
 * @VERSION PSMA
 * @SINCE 1.0
 * @HISTORY: 2.0
 */
public class CmTxtProcess {

	/**
	 * 
	 */
	private static final String CONTENT_TXT = "content.txt";
	/**
	 * 列分隔符
	 */
	private static final String COL_SEP = "\t";
	static List<String> EXCEPT = Arrays.asList("undefined");
	static int count = 0;
	private static String preDataPath = "D:/tmp/bj/tousu";
    
	@Test
	public void processTxt() {
		String inFile = preDataPath+"/";
		String outFile = "";
		try {
			for(String fileName:new String[]{/*"整理_DW_CS_SR_COMPLAIN_DM_201312.csv",*/"201403170601_4G_DW_CS_SR_COMPLAIN_DM_201401.csv"}){
			   processFile(inFile+fileName, outFile);
			}
			System.out.println("完成任务了！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void processFile(String inFileName, String outFileName)
			throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(inFileName));
		String lineStr = null;//
		String _str = null;
		// StringBuffer sb = new StringBuffer("");
		do {
			lineStr = reader.readLine();
			if (null != lineStr) {
				count++;
				_str = processRow(lineStr);
				/*
				 * sb.append(_str).append(StringUtils.isEmpty(_str)?"":"\n");
				 * if(!StringUtils.isEmpty(_str) && count%NUMS==0){
				 * writeFile(outFileName, sb.toString()); sb.delete(0,
				 * sb.length()); }
				 */
				System.out.println(_str);
			}
		} while (null != lineStr);
		reader.close();
	}

	private static String processRow(String lineStr) throws IOException {
		return switchProcess('t', lineStr);
	}

	private static String switchProcess(char chr, String lineStr)
			throws IOException {
		String str = "";
		switch (chr) {
		case 's':
			str = processRowSplit(lineStr);
			break;
		case 'r':
			str = processRowReplace(lineStr, "->", COL_SEP);
			break;
		case 't':
			str = processType(lineStr);
		default:
			break;
		}
		return str;
	}

	public static String processRowSplit(String lineStr) {
		StringBuffer sb = new StringBuffer("");
		if (StringUtils.isNotEmpty(lineStr)) {
			String[] strs = lineStr.split(COL_SEP);
			int size = strs.length;
			if (size > 2) {
				String str = strs[2];
				if (StringUtils.isNotEmpty(str) && !EXCEPT.contains(str)) {
					sb.append(strs[0]).append(COL_SEP).append(str);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * 
	 * @param lineStr
	 * @return
	 */
	public static String processRowReplace(String lineStr, String ori,
			String tar) {
		StringBuffer sb = new StringBuffer("");
		if (StringUtils.isNotEmpty(lineStr)) {
			String[] strs = lineStr.split(COL_SEP);
			String str1 = strs[0];
			String str2 = strs[1];
			sb.append(str1.replaceAll(ori, tar)).append(COL_SEP).append(str2);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param lineStr
	 * @return
	 * @throws IOException
	 */
	public static String processType(String lineStr) throws IOException {
		StringBuffer sb = new StringBuffer("");
		File file = null;
		if (StringUtils.isNotEmpty(lineStr)) {
			String[] strs = lineStr.split(COL_SEP);
//			int size = strs.length;
			StringBuffer path = new StringBuffer(
					new File(preDataPath).getPath());
			/*
			 * if(size>3){ for(int i=0; i<3; i++){
			 * path.append(File.separator).append(strs[i]); file = new
			 * File(path.toString()); if(!file.exists()){ file.mkdirs(); } }
			 * for(int j=3; j<size; j++){ sb.append(strs[j]); if(j < size-1){
			 * sb.append("\t"); } } sb.append("\n");
			 * FileUtils.appendFile(path.toString
			 * ()+File.separator+"content.txt", sb.toString()); }
			 */
			/*for (int i = 0; i < size - 1; i++) {
				path.append(File.separator)
						.append(strs[i].replaceAll("/", "_"));
			}*/
			
			String type = strs[0];
			if(!(type.contains(",")||type.contains(":")||type.contains("：")||type.contains("，"))){
				path.append(File.separator).append(type);
			}
			
			file = new File(path.toString());
			if (!file.exists()) {
				file.mkdirs();
			}
			for (int i = 1; i < strs.length-1; i++) {
				sb.append(strs[i]);
			}
			try{
			org.apache.commons.io.FileUtils.writeLines(new File(path.toString()+File.separator+CONTENT_TXT), Arrays.asList(sb.toString(),strs[strs.length-1]), true);
			}catch (Exception e){
				e.printStackTrace();
			}
			System.out.println(path.toString());
		}
		return "sucess";
	}
}
