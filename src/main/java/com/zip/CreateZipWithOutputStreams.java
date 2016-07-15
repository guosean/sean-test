/*
 * Copyright 2010 Srikanth Reddy Lingala  
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */

package com.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.ZipOutputStream;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.apache.commons.lang3.StringUtils;

import com.utils.file.FileUtils;

/**
 * This example demonstrates the use of ZipOutputStreams.
 * ZipOutputStreams can be used to create zip files "on the fly"
 * with all compression and encryption being done in memory
 */
public class CreateZipWithOutputStreams {
	
	
	public static void zipFilesAndEncrypt(String srcFileName,String zipFileName,String password){
		ZipOutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			File srcFile = new File(srcFileName);   
			File[] files = new File[0];
			if (srcFile.isDirectory()) {
				files = srcFile.listFiles();
			} else {
				files = new File[1];
				files[0] = srcFile;
			}
			outputStream = new ZipOutputStream(new FileOutputStream(new File(zipFileName)));
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			if(!StringUtils.isEmpty(password)){
				parameters.setEncryptFiles(true);
				//Zip4j supports AES or Standard Zip Encryption (also called ZipCrypto)
				//If you choose to use Standard Zip Encryption, please have a look at example
				//as some additional steps need to be done while using ZipOutputStreams with
				//Standard Zip Encryption
				parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
				parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
				parameters.setPassword(password);
			}
			
			//Now we loop through each file and read this file with an inputstream
			//and write it to the ZipOutputStream.
			int fileNums = files.length;
			File tmpFile = null;
			OutputStream os = null;
			for (int i = 0; i < fileNums; i++) {
				File file = (File)files[i];
				
				//This will initiate ZipOutputStream to include the file
				//with the input parameters
//				parameters.setFileNameInZip("test.txt");
				tmpFile = new File(new String(file.getPath().getBytes("ISO-8859-1"),"GBK"));
//				tmpFile.createNewFile();
				FileUtils.writeByteFile(FileUtils.readFileByte(file), tmpFile);
//                tmpFile = new File("");
				outputStream.putNextEntry(tmpFile,parameters);
				
				//If this file is a directory, then no further processing is required
				//and we close the entry (Please note that we do not close the outputstream yet)
				if (file.isDirectory()) {
					outputStream.closeEntry();
					continue;
				}
				
				inputStream = new FileInputStream(file);
				byte[] readBuff = new byte[4096];
				int readLen = -1;
				//Read the file content and write it to the OutputStream
				while ((readLen = inputStream.read(readBuff)) != -1) {
					outputStream.write(readBuff, 0, readLen);
				}
				//Once the content of the file is copied, this entry to the zip file
				//needs to be closed. ZipOutputStream updates necessary header information
				//for this file in this step
				outputStream.closeEntry();
				inputStream.close();
			}
			//ZipOutputStream now writes zip header information to the zip file
			outputStream.finish();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void unzipEncryptFile(String zipFileName,String unzipPath,String password){
		try {
			// Initiate ZipFile object with the path/name of the zip file.
			ZipFile zipFile = new ZipFile(zipFileName);
			// Extracts all files to the path specified
			if(!StringUtils.isEmpty(password)){
				zipFile.setPassword(password);
			}
			zipFile.extractAll(unzipPath);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
//		new CreateZipWithOutputStreams();
//		zipFilesAndEncrypt("D:\\test\\download","D:\\test\\download\\新建文本文档1.zip","12345");
//		unzipFile("D:\\test\\download\\新建文本文档1.zip","D:\\test\\download",null);
		String str = "/usr/local/aiomni/27/temp/offLineFolder/admin8a21848b4056b2e4014056b533240000/??????_1.xls";
		System.out.println(new String(str.getBytes("ISO8859-1"),"GBK"));
		System.out.println(System.getProperty("file.encoding"));
	}

}
