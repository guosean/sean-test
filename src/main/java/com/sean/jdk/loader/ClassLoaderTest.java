package com.sean.jdk.loader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.junit.Test;
/**
 * 类加载器
 * @author sean
 *
 */
public class ClassLoaderTest {
	/**
	 * SystemClassLoader:  sun.misc.Launcher$AppClassLoader
	 * ExtClassLoader:     sun.misc.Launcher$ExtClassLoader
	 */
    @Test
	public void testParent(){
		ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
		while(classLoader!=null){
			System.out.println(classLoader.getClass());
			classLoader = classLoader.getParent();
		}
	}
    /**
     * 测试两个类加载器加载同一个类
     */
    @Test
    public void testTwoClassLoader(){
    	String classPath = "D:\\myworkspace\\sean-study\\target\\classes";
    	FileSystemClassLoader fsc1 = new FileSystemClassLoader(classPath);
    	FileSystemClassLoader fsc2 = new FileSystemClassLoader(classPath);
    	String className = "com.sean.jdk.loader.Sample";
        try{
        	Class<?> c1 = fsc1.findClass(className);
        	System.out.println(c1.getClassLoader());
        	Object o1 = c1.newInstance();
        	Class<?> c2 = fsc2.findClass(className);
        	System.out.println(c1.getClassLoader());
        	Object o2 = c2.newInstance();
        	Method md = c1.getMethod("setSample", Object.class);
        	md.invoke(o1, o2);
        }catch(Exception e){
        	e.printStackTrace();
        }
    	
    }
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
	  	ClassLoader loader = new ClassLoader() {
	  		@Override
	  		public Class<?> loadClass(String name)
	  				throws ClassNotFoundException {
	  			try{
	  			   String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
	  			   InputStream is = getClass().getResourceAsStream(fileName);
	  			   if(is ==null){
	  				   return super.loadClass(name);
	  			   }
	  			   byte[] b = new byte[is.available()];
	  			   is.read(b);
	  			   return defineClass(name,b, 0, b.length);
	  			} catch(IOException e){
	  				throw new ClassNotFoundException();
	  			}
	  		}
		};
		Object obj = loader.loadClass("com.sean.jdk.loader.ClassLoaderTest").newInstance();
	    System.out.println(obj.getClass());
	    System.out.println(obj instanceof com.sean.jdk.loader.ClassLoaderTest);
	}
   
}
