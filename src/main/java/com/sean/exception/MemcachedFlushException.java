package com.sean.exception;

public class MemcachedFlushException  extends Exception{

	private static final long serialVersionUID = -355957289894951378L;
	private static final String flushmsg = "memcached flush出现异常-";

	public MemcachedFlushException(String msg) {
		 super(flushmsg+msg);
	}
}
