package com.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.junit.Test;

public class ZipFileWithPassword {

    
    public static final String zipDir = "D:\\test\\download";
    public static final String EncryptZipFile = "D:\\test\\Encrypt\\Encrypt.zip";
    public static final String password = "sean";
    
     @Test
    public  void TestEncryptZipFile() {
            /*File file = new File(zipDir);   
            byte[] zipByte = EncryptZipUtil.getEncryptZipByte(file.listFiles(), password);
            FileUtils.writeByteFile(zipByte, new File(EncryptZipFile));*/
    	 zip(zipDir,EncryptZipFile);
            
    }
    
     /**
 	 * 功能：把 sourceDir 目录下的所有文件进行 zip 格式的压缩，保存为指定 zip 文件
 	 * @param sourceDir
 	 * @param zipFile
 	 */

 	public static void zip(String sourceDir, String zipFile) {
 		OutputStream os;
 		try {
 			os = new FileOutputStream(zipFile);
 			BufferedOutputStream bos = new BufferedOutputStream(os);
 			ZipOutputStream zos = new ZipOutputStream(bos);
// 			zos.setEncoding("gbk");
 			File file = new File(sourceDir);
 			String basePath = null;
 			if (file.isDirectory()) {
 				basePath = file.getPath();
 			} else {//直接压缩单个文件时，取父目录
 				basePath = file.getParent();
 			}
 			zipFile(file, basePath, zos);
 			zos.closeEntry();
 			zos.close();
 		} catch (Exception e) {
 			e.printStackTrace();

 		}

 	}
 	
 	/**
 	 * 功能：执行文件压缩成zip文件
 	 * @param source
 	 * @param basePath  待压缩文件根目录
 	 * @param zos
 	 */

 	private static void zipFile(File source, String basePath,ZipOutputStream zos) {

 		File[] files = new File[0];
 		if (source.isDirectory()) {
 			files = source.listFiles();
 		} else {
 			files = new File[1];
 			files[0] = source;
 		}
 		String pathName;//存相对路径(相对于待压缩的根目录)
 		byte[] buf = new byte[1024];
 		int length = 0;
 		try {
 			for (File file : files) {
 				if (file.isDirectory()) {
 					pathName = file.getPath().substring(basePath.length() + 1)+ "/";
 					zos.putNextEntry(new ZipEntry(pathName));
 					zipFile(file, basePath, zos);

 				} else {
 					pathName = file.getPath().substring(basePath.length() + 1);
 					InputStream is = new FileInputStream(file);
 					BufferedInputStream bis = new BufferedInputStream(is);
 					zos.putNextEntry(new ZipEntry(pathName));
 					while ((length = bis.read(buf)) > 0) {
 						zos.write(buf, 0, length);
 					}
 					is.close();

 				}

 			}

 		} catch (Exception e) {
 			e.printStackTrace();
 		}

 	}

   /* @Test
    public void TestDecryptZipFile() {
            
            File file = new File(EncryptZipFile);   
            byte[] zipByte = FileUtils.readFileByte(file);
            try {
            	ZipOutputWithPwd.unzipFiles(zipByte,password, zipDir);
            } catch (IOException e) {
                    e.printStackTrace();
            }
            
    }*/

}