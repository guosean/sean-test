package com.sean.jmm;

import java.util.ArrayList;
import java.util.List;

public class MonitoringTest {
	
	static class OOMObject{
		public byte[] placehloder = new byte[64*1024];
	}

	public static void fillHeap(int num) throws InterruptedException {
		List<OOMObject> list = new ArrayList<OOMObject>();
		for(int i=0; i<num; i++){
			Thread.sleep(1000);
			list.add(new OOMObject());
		}

		System.gc();
	}
	
	public static void main(String[] args) throws InterruptedException {
//		fillHeap(1000);
		Object o = new Object();
		o.wait();
	}
}
