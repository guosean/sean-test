
package com.sean.jedis;

import redis.clients.jedis.Jedis;

/**
 * <p> Copyright 200 by Asiainfo-Linkage Software corporation
 * <p>All rights reserved.
 * <p>版权所有：亚信联创科技（中国）有限公司
 * <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分，
 * <p>侵权者将受到法律追究。
 * @PURPOSE:
 * @DESCRIPTION:
 * @AUTHOR: guozb
 * @DATE:2013-12-15
 * @VERSION PSMA
 * @SINCE 
 * @HISTORY: 1.0
 */

public class RedisClient {
	
	public static void main(String[] args) {
		String host = "192.168.0.105";
		int port = 6379;
		Jedis client = new Jedis(host, port);
		client.set("test", "value");
	}
	
}
