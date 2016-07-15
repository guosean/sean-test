package com.sean.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.management.timer.Timer;

import org.junit.Test;

import com.google.common.base.Ticker;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class TestCache {
	@Test
	public void testBasic() throws ExecutionException, InterruptedException{
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();
		Cache<String, String> cache = builder.maximumSize(10).expireAfterWrite(1, TimeUnit.SECONDS).build();
		cache.put("test", "cache");
		System.out.println(cache.get("key", new Callable<String>() {
			public String call() throws Exception {
				return "value";
			}
		}));
		builder.ticker(new Ticker() {
			
			@Override
			public long read() {
				return System.nanoTime()+Timer.ONE_SECOND*1000000;
			}
		});
//		Thread.currentThread().sleep(100);
//		Assert.assertEquals("cache", cache.getIfPresent("test"));
	}

}
