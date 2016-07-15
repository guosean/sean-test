package com.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.utils.file.FileUtils;

public class TxtProcess {
	
	private static final int NUMS = 10000;
	static int line = 0;
	static int count=0;
	public static void main(String[] args) throws IOException {
		// String inFile = "D:/tmp/test.txt";
		// String outFile = "D:/tmp/out.txt";
		String inFile = "D:/tmp/complain_201311_201312_0113.txt";
		String outFile = "D:/tmp/complain_201311_201312_0113_out.txt";
		try {
			processFile(inFile, outFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeFile(String fileName, String content)
			throws IOException {
		File file = new File(fileName);
		if (StringUtils.isNotEmpty(content)) {
			if (file.exists()) {
				FileUtils.appendFile(fileName, "\n");
				FileUtils.appendFile(fileName, content);
			} else {
				FileUtils.appendFile(fileName, content);
			}
		}
	}
	
	public static void processFile(String inFileName, String outFileName)
			throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(inFileName));
		String lineStr = null;
		String _str = null;
		StringBuffer sb = new StringBuffer("");
		do {
			lineStr = reader.readLine();
			if (null != lineStr) {
				line++;
				System.out.println(lineStr);
				_str = processRow(lineStr);
				sb.append(_str).append("\n");
				if (!StringUtils.isEmpty(_str) && line%NUMS==0) {
					writeFile(outFileName, sb.toString());
					sb.delete(0, sb.length());
				}
			}
		} while (null != lineStr);
		reader.close();
	}
	
	private static String processRow(String lineStr) {
		StringBuffer sb = new StringBuffer("");//中国
		System.out.println("处理至:"+(++count));
		if (StringUtils.isNotEmpty(lineStr)) {
			String[] strs = lineStr.split("\t");
			int size = strs.length;
			if (size > 2) {
				// System.out.println(strs.length);
				/*
				 * for (int i = 0; i < size; i++) {
				 * System.out.print(strs[i]+"#"); } System.out.println();
				 */
				sb.append(strs[0]).append("\t").append(strs[2]);
			}
		}
		
		return sb.toString();
	}
	
}