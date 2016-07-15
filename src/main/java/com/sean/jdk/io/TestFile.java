package com.sean.jdk.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

public class TestFile {
   @Test
	public void testWrite() throws IOException{
		String path = "D:"+File.separator+"tmp"+File.separator+"test.txt";
		File file = new File(path);
		OutputStream out = new FileOutputStream(file);
		out.write("测试".getBytes());
		out.close();
	}
	
}
