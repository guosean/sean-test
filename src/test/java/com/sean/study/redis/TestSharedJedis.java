package com.sean.study.redis;

import org.junit.Test;

import com.utils.BeanUtils;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class TestSharedJedis {
	
	@Test
	public void testSlave(){
		ShardedJedisPool sp = (ShardedJedisPool) BeanUtils.getBean("shardedPool");
		ShardedJedis sj = sp.getResource();
		System.out.println(sj.get("key"));
		//sj.set("test", "dgs"); //slave只读，不能写
		sp.returnResource(sj);
	}

}
