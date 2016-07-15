package com.sean.service.imp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import net.rubyeye.xmemcached.exception.MemcachedClientException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import com.sean.bean.CookieInfoBean;
import com.sean.exception.MemcachedFlushException;
import com.sean.exception.MemcachedOpException;
import com.sean.service.IMemCached;

public class RedisService implements IMemCached {
	/**
	 * 连接池
	 */
	@Getter
	@Setter
	private JedisPool jedisPool;
	@Getter
	@Setter
    private Jedis jedis;
	private static final String OK = "OK";
    /**
     * 
     * @Title: initJedis
     * @Description: 从连接池获取连接
     */
	public void initJedis(){
    	this.jedis = jedisPool.getResource();
    }
	/**
	 * 
	 * @Title: returnJedis
	 * @Description: 将连接放回连接池
	 */
    public void returnJedis(){
    	jedisPool.returnResource(jedis);
    }
	/*public static IMemCached getInstance() {
		return  new ProxyHandler().bind((RedisService)BeanUtils.getBean(CookieGenConstants.Beans4Spring.RedisService)) ;
	}*/
    
	public boolean setValue(String key, Object value)
			throws MemcachedOpException {
		return OK.equals(jedis.set(key,
				null == value ? StringUtils.EMPTY : value.toString()));
	}

	@Override
	public boolean setValue(String key, Object value, int expTime)
			throws MemcachedOpException {
		return  OK.equals(jedis.setex(key,expTime,
				null == value ? StringUtils.EMPTY : value.toString()));
	}

	@Override
	public Object getValue(String key) throws MemcachedOpException {
		return jedis.get(key);
	}

	@Override
	public Object getValue(String key, long timeout)
			throws MemcachedOpException {
		return getValue(key);
	}

	public List<String> getValue(String... keys) throws MemcachedOpException {
		return jedis.mget(keys);
	}

	@Override
	public boolean updateValue(String key, Object value)
			throws MemcachedOpException {
		return setValue(key, value);
	}

	@Override
	public boolean updateValue(String key, Object value, int expTime)
			throws MemcachedOpException {
		return setValue(key, value, expTime);
	}

	@Override
	public boolean delete(String key) throws MemcachedOpException {
		return 1 == jedis.del(key);
	}

	@Override
	public boolean delete(String key, long opTimeout)
			throws MemcachedOpException {
		return delete(key);
	}

	@Override
	public void startService() throws MemcachedClientException {
		throw new MemcachedClientException("不支持的操作");
	}

	@Override
	public void stopService() throws MemcachedClientException {
		throw new MemcachedClientException("不支持的操作");
	}

	@Override
	public void flushAll() throws MemcachedFlushException {
		jedis.flushAll();
	}
    /**
     * 
     * @Title: sadd
     * @Description: 添加一个或者多个元素到集合(set)里
     * @param key
     * @param members
     * @return
     */
    public boolean sadd(final String key,String... members) throws MemcachedOpException{
		return 1==jedis.sadd(key, members);
    }
    /**
     * 
     * @Title: sRandomMember
     * @Description: 从集合里面随机获取一个值
     * @param key
     * @return
     */
    public String sRandomMember(final String key) throws MemcachedOpException{
    	return jedis.srandmember(key);
    }
	
	@Override
	public Map<String, Object> getValue(Collection<String> keys)
			throws MemcachedOpException {
		Map<String, Object> map = null;
		if (CollectionUtils.isNotEmpty(keys)) {
			map = new HashMap<String, Object>(keys.size());
			Pipeline pl = jedis.pipelined();
			for (String key : keys) {
				pl.get(key);
			}
			List<Object> result = pl.syncAndReturnAll();
			Iterator<String> it = keys.iterator();
			Iterator<Object> rit = result.iterator();
			while(it.hasNext() && rit.hasNext()){
				map.put(it.next(), rit.next());
			}
		}
		return map;
	}

	@Override
	public boolean saveCookieInfos(List<CookieInfoBean> cookieInfos)
			throws Exception {
		boolean isOk = true;
		if(CollectionUtils.isNotEmpty(cookieInfos)){
			Pipeline pl = jedis.pipelined();
			/*for (CookieInfoBean cookieInfoBean : cookieInfos) {
				pl.sadd(cookieInfoBean.getUrl(), JsonUtil.Object2JsonString(cookieInfoBean));
			}*/
			pl.sync();
		}
		return isOk;
	}

	public IMemCached getProxy() {
		return new ProxyHandler().bind(this);
	}
	public void flush() throws MemcachedFlushException {
          jedis.flushDB();		
	}
	@Override
	public CookieInfoBean getCookieByUrl(String url) throws Exception {
		return null;//(CookieInfoBean) JsonUtil.JsonString2Bean(sRandomMember(url),CookieInfoBean.class);
	}
	
}
/**
 * 动态代理类 用于解决jedis需手工放回连接池的问题
 * @author guozb
 *
 */
class ProxyHandler implements InvocationHandler{
    private RedisService redis;
    /**
     * 
     * @Title: bind
     * @Description: 绑定代理对象
     * @param imem
     * @return
     */
    public IMemCached bind(RedisService imem){
    	this.redis = (RedisService) imem;
    	return (IMemCached)Proxy.newProxyInstance(RedisService.class.getClassLoader(), RedisService.class.getInterfaces(), this);
    }
	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2)
			throws Throwable {
		//从连接池获取资源
		redis.initJedis();
		Object obj = arg1.invoke(redis, arg2);
		//将资源放回连接池
		redis.returnJedis();
		return obj;
	}
	
}
