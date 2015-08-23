package com.def.springmvc.web.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.def.springmvc.web.session.event.EventType;
import com.def.springmvc.web.session.event.UserSessionEvent;

@SuppressWarnings("serial")
public class StandardSession implements UserSession {
	private long lastVisitedTime;
	private long createTime;
	private Boolean valid;
	private Map<String, String> attributes = new ConcurrentHashMap<String, String>();
	private String id;
    
	public StandardSession(){
		super();
		this.createTime = System.currentTimeMillis();
		this.lastVisitedTime = System.currentTimeMillis();
		this.valid=true;
	};
	
	public StandardSession(Map<String, String> attributes, String id) {
		super();
		this.attributes = attributes;
		this.createTime = System.currentTimeMillis();
		this.id = id;
		this.lastVisitedTime = System.currentTimeMillis();
		this.valid=true;
	}

//	public void updateLastVisitedTime() {
//		this.lastVisitedTime = new Date();
//	}


	public String getAttribute(String key) {
		if(key==null){
			return null;
		}
		return attributes.get(key);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public long getLastVisitedTime() {
		return this.lastVisitedTime;
	}


	public void invalidate() {
		this.valid=false;
		Config.fireSessionEvent(EventType.SESSION_DESTROYED, new UserSessionEvent(this.getId()));
		this.setAttributes(new ConcurrentHashMap<String, String>());
	}

	/** 判断是否超时 */
	private boolean isTimeOut(long lastAccessedTime) {
		if (lastAccessedTime == 0) {
			return true;
		}
		if ((lastAccessedTime + (Config.getSessionTimeout().longValue() * 60 * 1000)) < System
				.currentTimeMillis()) {
			return true;
		}
		return false;
	}


	public boolean isValidate() {
		boolean flag = true;
		if (isTimeOut(this.lastVisitedTime)) {
			flag = false;
		}
		if(!valid){
			flag=false;
		}
		return flag;
	}


	public void removeAttribute(String key) {
		if(!this.valid){
			throw new UserSessionException("session is invalid!!!");
		}
		if(key==null){
			return;
		}
		Config.fireSessionEvent(EventType.SESSION_REMOVE_ATTRIBUTE, new UserSessionEvent(getId(), key,
				null, this.getAttributes()));
		attributes.remove(key);
	}


	public void setAttribute(String key, String value) {
		if(!this.valid){
			throw new UserSessionException("session is invalid!!!");
		}
		if(key==null){
			throw new UserSessionException("key must not be null!!!");
		}
		if(value==null){
			throw new UserSessionException("value must not be null!!!");
		}
		attributes.put(key, value);
		Config.fireSessionEvent(EventType.SESSION_SET_ATTRIBUTE, new UserSessionEvent(getId(), key, value,
				this.getAttributes()));

	}

	public void setLastVisitedTime(long lastVisitedTime) {
		this.lastVisitedTime = lastVisitedTime;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

}
