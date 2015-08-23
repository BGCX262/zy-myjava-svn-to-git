/* 
 * CookieUtils.java
 * 
 * Created on 2010-5-28
 * 
 * Copyright(C) 2010, by Ambow Develop & Research Branch.
 * 
 * Original Author: sunhaibo
 * Contributor(s):
 * 
 * Changes 
 * -------
 * $Log$
 */
package com.def.springmvc.web.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.def.springmvc.web.session.Config;
import com.def.springmvc.web.session.UserSessionConstants;

public abstract class CookieUtils {
	public static void addSessionIdToCookie(HttpServletResponse res,String sessionId){
		addCookie(res,
				Config.getCookieDomain(),
				Config.getCookiePath(),
				UserSessionConstants.USER_SESSION_COOKIE_NAME,
				sessionId,
				Integer.valueOf(Config.getCookieMaxage()));
	}
	
	public static void addCookie(HttpServletResponse res,String cookieName, String cookieValue,int maxAge) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(maxAge);
		cookie.setPath(Config.getCookiePath());
		cookie.setDomain(Config.getCookieDomain());
		res.addCookie(cookie);
	}
	
	public static String getSessionIdFromCookie(HttpServletRequest request){
		Cookie c=getCookie(request,UserSessionConstants.USER_SESSION_COOKIE_NAME);
		if(c!=null){
			return c.getValue();
		}
		return null;
	}
	public static Cookie getCookie(HttpServletRequest request,String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					return cookie;
				}
			}
		}
		return null;
	}
	public static void addCookie(HttpServletResponse res,String domain,String path,String cookieName, String cookieValue, int maxAge) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(maxAge);
		cookie.setPath(path);
		cookie.setDomain(domain);
		res.addCookie(cookie);
	}
	public static void removeCookie(HttpServletResponse res,String cookieName) {
		Cookie cookie = new Cookie(cookieName, "");
		cookie.setMaxAge(0);
		cookie.setDomain(Config.getCookieDomain());
		cookie.setPath(Config.getCookiePath());
		
		res.addCookie(cookie);
	}	
	
}
