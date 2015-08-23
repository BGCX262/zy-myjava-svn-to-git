package com.def.springmvc.web.session.store;

public class SessionStoreFactory {
    public static SessionStore getSessionStore(){
    	return new MemCacheSessionStore();
    }
}
