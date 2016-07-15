package com.resource;

import java.util.Locale;
import java.util.ResourceBundle;

import junit.framework.Assert;

import org.junit.Test;


public class TestResourceBundle {
  private String baseName = "config.test";
  
  Locale zh = new Locale("zh", "CN"); 
  Locale en = new Locale("en", "US");
@Test
  public void testEn(){
	  Assert.assertEquals("test",ResourceBundle.getBundle(baseName,en).getString("test"));
  }
@Test
  public void testCn(){
	 Assert.assertEquals("测试",ResourceBundle.getBundle(baseName,zh).getString("test"));
  }
	
}
