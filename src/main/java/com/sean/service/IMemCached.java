
package com.sean.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.rubyeye.xmemcached.exception.MemcachedClientException;

import com.sean.bean.CookieInfoBean;
import com.sean.exception.MemcachedFlushException;
import com.sean.exception.MemcachedOpException;

public interface IMemCached {
	/**
	 * 
	 * @Title: setValue
	 * @Description: 将键值对存入cache库 
	 * @param key  键
	 * @param value 值
	 * @throws Exception
	 */
	public boolean setValue(final String key,final Object value) throws MemcachedOpException;
    /**
     * 
     * @Title: setValue
     * @Description: 将键值对存入cache库 ，并指定失效时间
     * @param key 键
     * @param value 值
     * @param expTime 失效时间
     * @throws Exception 
     */
	public boolean setValue(final String key,final Object value,final int expTime)throws MemcachedOpException;
	/**
	 * 
	 * @Title: getValue
	 * @Description: 获取键对应的值
	 * @param key 键
	 * @return
	 * @throws Exception
	 */
	public Object getValue(final String key) throws MemcachedOpException;
	
	/**
	 * 
	 * @Title: getValue
	 * @Description: 在指定的时间内获取键对应的值
	 * @param key 键
	 * @param timeout 超时时间
	 * @return
	 * @throws Exception
	 */
	public Object getValue(final String key,final long timeout) throws MemcachedOpException;
    /**
     * 
     * @Title: getValue
     * @Description: 批量获取值
     * @param keys 键集合
     * @return
     * @throws Exception
     */
	public Map<String,Object> getValue(final Collection<String> keys) throws MemcachedOpException;
	/**
	 * 
	 * @Title: updateValue
	 * @Description: 更新键值
	 * @param key 键
	 * @param value 值
	 * @return
	 * @throws Exception
	 */
	public boolean updateValue(final String key,final Object value) throws MemcachedOpException;
	/**
	 * 
	 * @Title: updateValue
	 * @Description: 更新键值
	 * @param key 键
	 * @param value 值
	 * @param expTime 失效时间
	 * @return
	 * @throws Exception
	 */
	public boolean updateValue(final String key,final Object value,final int expTime) throws MemcachedOpException;
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除键值
	 * @param key 键
	 * @return
	 * @throws Exception
	 */
	public boolean delete(final String key) throws MemcachedOpException;
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除键值
	 * @param key 键
	 * @param opTimeout 操作超时时间
	 * @return
	 */
	public boolean delete(final String key,final long opTimeout) throws MemcachedOpException;
	/**
	 * 
	 * @Title: startService
	 * @Description: 启动服务
	 * @throws Exception
	 */
	public void startService() throws MemcachedClientException;
	/**
	 * 
	 * @Title: stopService
	 * @Description: 停止服务
	 * @throws CookieGenException
	 */
	public void stopService() throws MemcachedClientException;
	/**
	 * 
	 * @Title: flushAll
	 * @Description: 清空所有数据项
	 * @throws Exception
	 */
	public void flushAll() throws MemcachedFlushException;
    /**
     * 
     * @Title: flush
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @throws MemcachedFlushException
     */
	public void flush() throws MemcachedFlushException;
	 /**
     * 
     * @Title: sadd
     * @Description: 添加一个或者多个元素到集合(set)里
     * @param key
     * @param members
     * @return
     */
    public boolean sadd(final String key,String... members) throws MemcachedOpException;
    
    /**
     * 
     * @Title: sRandomMember
     * @Description: 从集合里面随机获取一个值
     * @param key
     * @return
     */
    public String sRandomMember(final String key) throws MemcachedOpException;
    /**
	 * 
	 * @Title: saveCookieInfos
	 * @Description: 以键值对保存cookie信息
	 * @param cookieInfos list of <code>CookieInfoBean
	 * @return
	 * @throws CookieGenException
	 */
	public boolean saveCookieInfos(List<CookieInfoBean> cookieInfos)
			throws Exception;
	/**
	 * 
	 * @Title: getCookieByUrl
	 * @Description: 获取URL下的cookie
	 * @param url url
	 * @return
	 * @throws Exception
	 */
	public CookieInfoBean getCookieByUrl(final String url)
			throws Exception;
	/**
	 * 
	 * @Title: getProxy
	 * @Description: 获取代理
	 * @return
	 * @throws Exception
	 */
	public IMemCached getProxy();
    
}
