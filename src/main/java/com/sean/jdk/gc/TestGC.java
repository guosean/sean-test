package com.sean.jdk.gc;

import org.junit.Test;

public class TestGC {
	private static final int _MB = 1024*1024;
	/**
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
	 */
    @Test
	public void testAlloction(){
		byte[] alloc1,alloc2,alloc3,alloc4;
		alloc1 = new byte[2 * _MB];
		alloc2 = new byte[2 * _MB];
		alloc3 = new byte[2 * _MB];
		alloc4 = new byte[4 * _MB];
	}
	
}
