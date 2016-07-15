package com.sean.jdk.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @AUTHOR: guozb
 * @DATE: 2014-5-30
 */
public class FileSystemClassLoader extends ClassLoader {
    /**
     * 根路径
     */
	private String rootDir;
	
	/**
	 * 类的根路径
	 */
	public FileSystemClassLoader(String rootPath) {
		this.rootDir = rootPath;
	}
	
	protected Class<?> findClass(String className) throws ClassNotFoundException{
		byte[] classData = getClassData(className);
		if(null == classData){
			throw new ClassNotFoundException();
		}
		else{
			return defineClass(className, classData, 0, classData.length);
		}
	}

	/**
	 * @param className
	 * @return
	 */
	private byte[] getClassData(String className) {
		 String path = classNameToPath(className); 
	        try { 
	            InputStream ins = new FileInputStream(path); 
	            ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
	            int bufferSize = 4096; 
	            byte[] buffer = new byte[bufferSize]; 
	            int bytesNumRead = 0; 
	            while ((bytesNumRead = ins.read(buffer)) != -1) { 
	                baos.write(buffer, 0, bytesNumRead); 
	            } 
	            return baos.toByteArray(); 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } 
	        return null; 
	}

	/**
	 * @param className
	 * @return
	 */
	private String classNameToPath(String className) {
		return rootDir+File.separator+className.replace(".", File.separator)+".class";
	}
}
