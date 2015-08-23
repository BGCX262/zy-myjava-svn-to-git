package com.def.springmvc.web.session.event;

import java.util.Map;

public class UserSessionEvent {
	private String sessionId;
	
    private String key;
    private String val;
    
	private Map<String,String> attributesMap;
	
	
	public UserSessionEvent() {
	}
	
	public UserSessionEvent(String sessionId) {
		super();
		this.sessionId = sessionId;
	}
	

	public UserSessionEvent(String sessionId,String key, String val, Map<String,String> attributesMap) {
		super();
		this.key = key;
		this.sessionId = sessionId;
		this.attributesMap = attributesMap;
		this.val = val;
	}

	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public Map<String, String> getAttributesMap() {
		return attributesMap;
	}

	public void setAttributesMap(Map<String, String> attributesMap) {
		this.attributesMap = attributesMap;
	}

	
}
