package com.def.springmvc.web.session.event;

import org.apache.log4j.Logger;

import com.def.springmvc.web.session.UserSessionConstants;
import com.def.springmvc.web.session.UserSessionException;
import com.def.springmvc.web.session.store.SessionStore;
import com.def.springmvc.web.session.store.SessionStoreFactory;

public class DefSessionListenerImpl implements UserSessionListener {
	protected static final Logger logger = Logger.getLogger(DefSessionListenerImpl.class);

	private SessionStore sessionStore;

	public DefSessionListenerImpl() {
		super();
		this.sessionStore = SessionStoreFactory.getSessionStore();
	}

	public void sessionCreated(UserSessionEvent event) throws UserSessionException {
		logger.info("#######session is created -->>" + event.getSessionId());
	}

	public void sessionDestroyed(UserSessionEvent event) throws UserSessionException {
		logger.info("#######session is destoryed -->>" + event.getSessionId());
		sessionStore.removeSession(event.getSessionId());

	}

	public void onAddAttribute(UserSessionEvent event) throws UserSessionException {
		String key = event.getKey();
		if (key.equals(UserSessionConstants.USER_INFO_KEY)) {
			//sessionStore.addOnlineUser(event.getSessionId(), event.getVal());
		}
		logger.info("#################session addAtribute key=" + event.getKey() + "  val=" + event.getVal());
	}

	public void onRemoveAttribute(UserSessionEvent event) throws UserSessionException {
		String key = event.getKey();

		if (key.equals(UserSessionConstants.USER_INFO_KEY)) {
			//String userInfo = event.getAttributesMap().get(UserSessionConstants.USER_INFO_KEY);
			//sessionStore.removeOnlineUser(userInfo);
		}
		logger.info("#################session removeAtribute key=" + event.getKey() + "  val=" + event.getAttributesMap().get(key));

	}

}
