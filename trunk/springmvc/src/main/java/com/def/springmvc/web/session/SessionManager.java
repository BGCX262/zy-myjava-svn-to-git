/* 
 * SessionManager.java
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.def.springmvc.web.session.event.EventType;
import com.def.springmvc.web.session.event.UserSessionEvent;
import com.def.springmvc.web.session.store.SessionStore;
import com.def.springmvc.web.session.store.SessionStoreFactory;

public class SessionManager {

	/**
	 * 通过当前用户的Cookies 里存的SessionID，获取用户当前请求的Session
	 * 
	 * @return UserSession User's Session create date： 2009-10-30 author:
	 *         pengqing
	 */
	public static UserSession getCurrentSession() {
		return userSessioQueue.get();
	}

	private SessionStore sessionStore;

	private static final ThreadLocal<StandardSession> userSessioQueue = new ThreadLocal<StandardSession>();

	public SessionManager() {
		this.sessionStore = SessionStoreFactory.getSessionStore();
	}

	public void updateSessionLastVisitedTime() {
		userSessioQueue.get().setLastVisitedTime(System.currentTimeMillis());
	}
	/**
	 * 根据id查找session，httpId不为null时，优先按httpId查询和生成session
	 * 
	 * @param id
	 * @param httpId
	 *            HttpSessionId
	 * @return
	 * @throws UserSessionException
	 * 
	 *             create date: 2009-11-04 author: liujia
	 */
	public StandardSession findSessionById(String id, String httpId) throws UserSessionException {

		StandardSession session = sessionStore.loadSession(id);
		/** 如果没有找到则新建一个 */
		if (session == null) {
			session = new StandardSession(new ConcurrentHashMap<String, String>(), idGenerate.getId(httpId));
			/** handle session listener */
			Config.fireSessionEvent(EventType.SESSION_CREATEED, new UserSessionEvent(session.getId()));
		}
		userSessioQueue.set(session);
		return session;
	}

	public void saveSession() throws UserSessionException {
		StandardSession session = userSessioQueue.get();
		if (session == null) {
			throw new UserSessionException("SessionManager saveSession() session 不能为null!!");
		}
		sessionStore.saveOrUpdateSession(session);
	}

	/** 生成新的session 标识 */
	private static final SessionIdGenerate idGenerate = new SessionIdGenerate();

	private static class SessionIdGenerate {
		private Log log = LogFactory.getLog(SessionIdGenerate.class);
		private Random random = null;

		private int sessionIdLength = 16;

		private String randomClass = "java.security.SecureRandom";

		protected static final String DEFAULT_ALGORITHM = "MD5";

		private Random getRandom() {
			long seed = System.currentTimeMillis();
			if (random == null) {
				try {
					Class<?> clazz = Class.forName(this.randomClass);
					random = (Random) clazz.newInstance();
					random.setSeed(seed);
				} catch (Exception e) {
					log.error("getRandom() Error!");
					random = new java.util.Random();
					random.setSeed(seed);
				}
			}
			return random;
		}

		private String getId(String seed) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(seed + Config.getNodeId());
			return buffer.toString();
		}

		@SuppressWarnings("unused")
		private synchronized String getId() {
			byte random[] = new byte[16];
			String result = null;

			StringBuffer buffer = new StringBuffer();

			int resultLenBytes = 0;

			while (resultLenBytes < this.sessionIdLength) {
				getRandom().nextBytes(random);
				try {
					random = MessageDigest.getInstance(DEFAULT_ALGORITHM).digest(random);
				} catch (NoSuchAlgorithmException e) {
					log.error("digest() Error!");
				}
				for (int j = 0; j < random.length && resultLenBytes < this.sessionIdLength; j++) {
					byte b1 = (byte) ((random[j] & 0xf0) >> 4);
					byte b2 = (byte) (random[j] & 0x0f);
					if (b1 < 10)
						buffer.append((char) ('0' + b1));
					else
						buffer.append((char) ('A' + (b1 - 10)));
					if (b2 < 10)
						buffer.append((char) ('0' + b2));
					else
						buffer.append((char) ('A' + (b2 - 10)));
					resultLenBytes++;
				}
			}
			buffer.append(Config.getNodeId());
			result = buffer.toString();

			return (result);
		}
	}
}
