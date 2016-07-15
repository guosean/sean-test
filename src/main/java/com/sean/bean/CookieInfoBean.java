
package com.sean.bean;


import lombok.Getter;
import lombok.Setter;

public class CookieInfoBean {
	/**
	 * 用户名
	 */
	@Getter
	@Setter
	private String username;
	/**
	 * URL
	 */
	@Getter
	@Setter
	private String url;
	/**
	 * cookie值
	 */
	@Getter
	@Setter
	private String cookie;
	
	
	public static CookieInfoBean getInstance(final String userName,final String url,final String cookie){
		CookieInfoBean bean = new CookieInfoBean();
		bean.setCookie(cookie);
		bean.setUrl(url);
		bean.setUsername(userName);
		return bean;
	}
	public static CookieInfoBean getInstance(final String userName,final String url){
		return getInstance(userName, url, null);
	}
	
	public String toString(){
		return this.username+":"+this.cookie;
	}
}
