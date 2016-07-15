package com.sean.exception;

public class MemcachedOpException extends Exception {

	private static final long serialVersionUID = 7926963086477593514L;
	private static final String opmsg = ":memcached数据操作出现异常-";

	public MemcachedOpException(String msg) {
		 super(opmsg+msg);
	}
}
