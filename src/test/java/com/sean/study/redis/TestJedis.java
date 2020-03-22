
package com.sean.study.redis;

import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Transaction;

public class TestJedis {
	JedisPool pool = null;
	Jedis cli = null;
	String host = null;
	int port = 6379;
	
	@Before
	public void setUp(){
		host = "192.168.0.105";
		pool = new JedisPool(new GenericObjectPoolConfig(),host,port);
//		cli = new Jedis(host, port);
		cli = pool.getResource();
	}
	
	@Test
	public void testString(){
		String key = "key";
		String value = "kValue";
		Transaction transaction = cli.multi();
		transaction.exec();

		cli.set(key, value);
		Assert.assertEquals(value, cli.get(key));
		cli.append(key, "1");
		Assert.assertEquals(value+"1", cli.get(key));
		cli.expire(key, 10);
		Assert.assertEquals(Long.valueOf(10), cli.ttl(key));
		cli.persist(key);
		Assert.assertEquals(Long.valueOf(-1), cli.ttl(key));
		Assert.assertEquals("kVa", cli.getrange(key, 0, 2));
		Assert.assertEquals("string", cli.type(key));
//		cli.sadd(key, "value2");
//		Assert.assertEquals("set", cli.type(key));
		Assert.assertTrue(cli.exists(key));
		Assert.assertEquals(Long.valueOf(1), cli.del(key));
		Assert.assertTrue(!cli.exists(key));
		Assert.assertEquals(null, cli.get(key));
		
		cli.set("intK", "1");
		Assert.assertEquals("1", cli.get("intK"));
		cli.incr("intK");
		Assert.assertEquals("2", cli.get("intK"));
	}
	
	@Test
	public void testHash(){
		String hKey = "hashK";
		String field = "field1";
		String value = "hashV";
		cli.hset(hKey, field, value);
		Assert.assertEquals(value, cli.hget(hKey, field));
		cli.hset(hKey, field+"1", value+"1");
		Map<String, String> hash = cli.hgetAll(hKey);
		Assert.assertEquals(value, cli.hget(hKey, field));
		Assert.assertTrue(null!=hash && hash.size()==2);
	}
	
	@Test
	public void testList(){
	      String lKey = "listK";
	      cli.lpush(lKey, "l1","l2");
	      Assert.assertEquals("l2", cli.lpop(lKey));
	      cli.rpush(lKey,"l3");	
	      Assert.assertEquals("l3", cli.rpop(lKey));
	}
	@Test
	public void testPub(){
		 cli.publish("channel", "message");
	}
	@Test
	public void testSub(){
		 JedisPubSub jedisPubSub = new JedisPubSub() {
			
			@Override
			public void onUnsubscribe(String channel, int subscribedChannels) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onSubscribe(String channel, int subscribedChannels) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPUnsubscribe(String pattern, int subscribedChannels) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPSubscribe(String pattern, int subscribedChannels) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPMessage(String pattern, String channel, String message) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onMessage(String channel, String message) {
				System.out.println(message);
				
			}
		};
		String channels = "channel";
		cli.subscribe(jedisPubSub, channels);
	}
	
	
	@After
	public void after(){
//		cli.shutdown();
//		cli.disconnect();
	}
	
}
