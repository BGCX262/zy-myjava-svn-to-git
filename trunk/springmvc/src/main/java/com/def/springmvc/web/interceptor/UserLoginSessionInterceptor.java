package com.def.springmvc.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.def.springmvc.web.AttributeKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.def.springmvc.web.session.SessionManager;
import com.def.springmvc.web.session.StandardSession;
import com.def.springmvc.web.session.UserSession;
import com.def.springmvc.web.session.CookieUtils;

public class UserLoginSessionInterceptor extends HandlerInterceptorAdapter {
	private static Logger log = LoggerFactory.getLogger(UserLoginSessionInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		SessionManager sessionManager = new SessionManager();
		UserSession session = SessionManager.getCurrentSession();
		if (session != null && session.isValidate()) {
			/** 更新最访问时间 */
			sessionManager.updateSessionLastVisitedTime();
			/** 保存session */
			sessionManager.saveSession();

			if (log.isInfoEnabled()) {
				log.info("######save after content----------------------"
						+ session.getAttribute(AttributeKeys.USER_ID_KEY));
			}
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String cookieSessionId = CookieUtils.getSessionIdFromCookie(request);
		SessionManager sessionManager = new SessionManager();
		StandardSession session = sessionManager.findSessionById(
				cookieSessionId, request.getSession().getId());
//		request.setAttribute(UserSessionConstants.USER_SESSION_KEY,
//				(UserSession) (session));
		/** 将session id 写入cookie 以便每次请求构造用户session */
		if (cookieSessionId == null || !cookieSessionId.equals(session.getId())) {
			CookieUtils.addSessionIdToCookie(response, session.getId());
		}
		

		return true;
	}

}
