package com.def.springmvc.web.session.store;

import com.def.springmvc.web.session.StandardSession;
import com.def.springmvc.web.session.UserSessionException;

public interface SessionStore {
	/**
	 * 根据id返回获取到的session，当session不存在或失效时返回null
	 * 
	 * @param id  sessionId 
	 * @return  返回session对象
	 * @throws UserSessionException 
	 * 
	 * create date: 2009-11-03
	 * author: liujia
	 */
	StandardSession loadSession(String id) throws UserSessionException;

	/**
	 * 保存或删除给定的session
	 * 
	 * @param session 如果session还在有效期保存更新，否则删除
	 * @throws UserSessionException
	 * 
	 * create date: 2009-11-03
	 * author: liujia 
	 */
	void saveOrUpdateSession(StandardSession session) throws UserSessionException;

	/**
	 * 根据id删除session
	 * 
	 * @param id session的id
	 * @throws UserSessionException
	 * 
	 * create date: 2009-11-03
	 * author: liujia 
	 */
	void removeSession(String id) throws UserSessionException;

	/**
	 * 用来批量判端在线用户的状态，如果该用户id存在，判断所在session是否失效，失效删除改id及其session
	 * 
	 * @param ids  用户id的数组
	 * @return 返回一个标志数组flag，与ids数组的下标一一对应。如果ids里该用户在线，对应的flag里元素值为1，否则为0；ids为null时，返回null
	 * @throws UserSessionException
	 * 
	 * create date: 2009-11-03
	 * author: liujia
	 */
	//String[] isOnline(String... ids) throws UserSessionException;

	/**
	 * 增加线上用户的状态，key值为userId，value为sessionId
	 * 
	 * @param sessionId
	 * @param userInfo  可能的形式是 userId,...,... 第一位肯定是userId
	 * @throws UserSessionException
	 *  
	 * create date: 2009-11-03
	 * author: liujia
	 */
	//void addOnlineUser(String sessionId, String userInfo) throws UserSessionException;

	/**
	 * 根据userId，删除该用户的在线状态
	 * 
	 * @param userInfo 可能的形式是 userId,...,... 第一位肯定是userId
	 * @throws UserSessionException
	 *  
	 * create date: 2009-11-03
	 * author: liujia
	 */
	//void removeOnlineUser(String userInfo) throws UserSessionException;

}
