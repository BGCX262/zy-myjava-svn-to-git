package com.def.springmvc.web.session;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.def.springmvc.web.session.event.EventType;
import com.def.springmvc.web.session.event.DefSessionListenerImpl;
import com.def.springmvc.web.session.event.UserSessionEvent;
import com.def.springmvc.web.session.event.UserSessionListener;

@SuppressWarnings("unchecked")
public class Config {
	private static final Logger logger = Logger.getLogger(Config.class);

	private final static String DEF_PROPERTIES_FILENAME = "def_usersession.properties";

	private final static String CONSUMER_PROPERTIES_FILENAME = "usersession.properties";
	private static Properties properties;

	private static List<Class<? extends UserSessionListener>> sessionListeners;

	static {
		//加载用户配制文件
		Properties p1 = loadProperties(CONSUMER_PROPERTIES_FILENAME, "load session context");
		//如果没有用户配制文件，则加载默认文件
		if (p1 == null) {
			p1 = loadProperties(DEF_PROPERTIES_FILENAME, "load session context");
		}
		properties = p1;
		//初始化监听器
		initListener();
	}

	private Config() {
	}
	
	private static void initListener() {
		sessionListeners = new ArrayList<Class<? extends UserSessionListener>>(5);
		// 默认监听事件
		sessionListeners.add(DefSessionListenerImpl.class);
		// 用户实现的监听事件
		String listeners = properties.getProperty("session.event.listeners");
        if(listeners==null || listeners.equals("")){
        	return;
        }
		String classesArray[] = listeners.split(",");

		for (int i = 0; i < classesArray.length; i++) {
			String className = classesArray[i];

			try {
				Class<? extends UserSessionListener> clazz = (Class<? extends UserSessionListener>)Class.forName(className);
				if (!UserSessionListener.class.isAssignableFrom(clazz)) {
					logger.error("Specified listener class '" + className
							+ "' does not implement UserSessionListener. Ignoring this listener.");
					continue;
				}
				sessionListeners.add(clazz);
			} catch (ClassNotFoundException e) {
				logger.error("class :" + className + "' not found.", e);
			}
		}
	}

	public static void set(String key, String value) {
		if (key == null) {
			throw new IllegalArgumentException("key is null");
		}

		if (value == null) {
			return;
		}

		if (properties == null) {
			properties = new Properties();
		}

		properties.put(key, value);
	}

	@SuppressWarnings("static-access")
	public static void fireSessionEvent(EventType eventType, UserSessionEvent event) {
		List<Class<? extends UserSessionListener>> sessionListeners = Config.getSessionListeners();
		int len = sessionListeners == null ? 0 : sessionListeners.size();
		for (int i = (len - 1); i >= 0; i--) {
			UserSessionListener listener;
			try {
				listener = (UserSessionListener) sessionListeners.get(i).newInstance();
				if (eventType.equals(eventType.SESSION_CREATEED)) {
					listener.sessionCreated(event);
				} else if (eventType.equals(eventType.SESSION_DESTROYED)) {
					listener.sessionDestroyed(event);
				} else if (eventType.equals(eventType.SESSION_SET_ATTRIBUTE)) {
					listener.onAddAttribute(event);
				} else if (eventType.equals(eventType.SESSION_REMOVE_ATTRIBUTE)) {
					listener.onRemoveAttribute(event);
				}
			} catch (InstantiationException e) {
				logger.error("UserSessionListener InstantiationException:", e);
			} catch (IllegalAccessException e) {
				logger.error("UserSessionListener IllegalAccessException:", e);
			}

		}
	}

	public static String getNodeId() {
		return properties.getProperty("session.node.id");
	}

	public static Long getSessionTimeout() {
		return Long.valueOf(properties.getProperty("session.timeout"));
	}

	public static List<Class<? extends UserSessionListener>> getSessionListeners() {
		return sessionListeners;
	}

	public static long getRefreshPeriod() {
		return Long.valueOf(properties.getProperty("session.cache.refresh.period"));
	}

	public static String getKeepLiveUrl() {
		return properties.getProperty("passport.live.url");
	}

	public static String getRegion() {
		return properties.getProperty("session.cache.region");
	}

	public static String getCacheServers() {
		return properties.getProperty("session.cache.servers");
	}

	public static String getCookiePath() {
		return properties.getProperty("session.cookie.path");
	}

	public static String getCookieDomain() {
		return properties.getProperty("session.cookie.domain");
	}

	public static String getCookieMaxage() {
		return properties.getProperty("session.cookie.maxAge");
	}

	private static Properties loadProperties(URL url, String info) {
		logger.info("userSession: Getting properties from URL " + url + " for " + info);

		Properties properties = new Properties();
		InputStream in = null;

		try {
			in = url.openStream();
			properties.load(in);
			logger.info("userSession: Properties read " + properties);
		} catch (Exception e) {
			logger.error("userSession: Error reading from " + url, e);
			logger.error("userSession: Ensure the properties information in " + url
					+ " is readable and in your classpath.");
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				logger.warn("userSession: IOException while closing InputStream: " + e.getMessage());
			}
		}

		return properties;
	}

	private static Properties loadProperties(String filename, String info) {
		URL url = null;

		ClassLoader threadContextClassLoader = Thread.currentThread().getContextClassLoader();
		if (threadContextClassLoader != null) {
			url = threadContextClassLoader.getResource(filename);
		}
		if (url == null) {
			url = Config.class.getResource(filename);
			if (url == null) {
				logger.warn("userSession: No properties file found in the classpath by filename " + filename);
				return null;
			}
		}

		return loadProperties(url, info);
	}
}
