package com.def.springmvc.web.session.event;

import com.def.springmvc.web.session.UserSessionException;


public interface UserSessionListener {
	/**
	 * 创建session事件
	 * 
	 * @param event
	 * @throws UserSessionException
	 *  
	 * create date: 2009-11-04
	 * author: liujia
	 */
	void sessionCreated(UserSessionEvent event) throws UserSessionException;
	
	/**
	 * 销毁session事件
	 * 
	 * @param event
	 * @throws UserSessionException
	 *  
	 * create date: 2009-11-04
	 * author: liujia
	 */
	void sessionDestroyed(UserSessionEvent event)throws UserSessionException;
	
	/**
	 * 向session里增加属性事件，当key为USER_INFO_KEY时，增加线上用户状态
	 * 
	 * @param event
	 * @throws UserSessionException
	 *  
	 * create date: 2009-11-04
	 * author: liujia
	 */
    void onAddAttribute(UserSessionEvent event)throws UserSessionException;
    
    /**
     * 删除session里的属性事件，当key是USER_INFO_KEY时，移除线上用户状态
     * 
     * @param event
     * @throws UserSessionException
     *  
	 * create date: 2009-11-04
	 * author: liujia
     */
    void onRemoveAttribute(UserSessionEvent event)throws UserSessionException;
}
