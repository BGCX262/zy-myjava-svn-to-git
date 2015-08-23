/* 
 * UserSession.java
 * 
 * Created on 2009-10-30
 * 
 * Copyright(C) 2009, by Ambow Develope & Research Branch.
 * 
 * Original Author: pengqing
 * Contributor(s):
 * 
 * Changes 
 * -------
 * $Log$
 */
package com.def.springmvc.web.session;

import java.io.Serializable;

public interface UserSession extends Serializable {

	/**
	 * 向Session 中设置一个属性
	 * 
	 * @param key Session key
	 * @param value  Session value
	 * 创建日期： 2009-10-30
	 */
	public void setAttribute(String key, String value);
	
	/**
	 * 
	 * 通过key 从Session 中获取属性值
	 *
	 * @param key session key
	 * @return  String 获取的session value，如果当前Session 中没有此key，则返回null
	 * create date： 2009-10-30
	 * author: pengqing
	 */
	public String getAttribute(String key);
	
	/**
	 * 
	 * 从session 中移除此key
	 *
	 * @param key  session key
	 * create date： 2009-10-30
	 * author: pengqing
	 */
	public void removeAttribute(String key);
	
	/**
	 * 
	 * 获取用户最后一次访问该session 的时间
	 *  @return Date 最后一次访问的时间
	 * create date： 2009-10-30
	 * author: pengqing
	 */
	public long getLastVisitedTime();
	
	/**
	 * 
	 * 通过用户最后一次访问的时间，和服务器设置的Session 过期时间判断该Session 是否过期
	 *
	 * @return  boolean Session 是否过期
	 * create date： 2009-10-30
	 * author: pengqing
	 */
	public boolean isValidate();
	
	/**
	 * 
	 * 设置该Session 过期
	 *  
	 * create date： 2009-10-30
	 * author: pengqing
	 */
	public void invalidate();
}
