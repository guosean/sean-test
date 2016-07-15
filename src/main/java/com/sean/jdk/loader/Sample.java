package com.sean.jdk.loader;

/**
 * @AUTHOR: guozb
 * @DATE: 2014-5-30
 */
public class Sample {
   static int count=1;
   private Sample instance;
   public Sample(){
	   ++count;
   }
   public void setSample(Object sample){
//	   this.instance = (Sample)sample;
	   System.out.println(count);
   }
}
