package com.def.springmvc.web.session;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.def.springmvc.web.session.CookieUtils;

public class UserSessionLifecycleFilter implements Filter {
	private static final Logger log = Logger.getLogger(UserSessionLifecycleFilter.class);
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String cookieSessionId = CookieUtils.getSessionIdFromCookie(req);

		StandardSession session = null;
		SessionManager sessionManager = new SessionManager();
		try {
			/** 查询当前session如果没有新建 */
			
			session = sessionManager.findSessionById(cookieSessionId, req.getSession().getId());
			req.setAttribute(UserSessionConstants.USER_SESSION_KEY, (UserSession)(session));
			/** 将session id 写入cookie 以便每次请求构造用户session */
			if (cookieSessionId == null || !cookieSessionId.equals(session.getId())) {
				CookieUtils.addSessionIdToCookie(res, session.getId());
			}
			chain.doFilter(req, res);
		} finally {
			if (session.isValidate()) {
				/** 更新最访问时间 */
				sessionManager.updateSessionLastVisitedTime();
				/** 保存session */
				sessionManager.saveSession();
				if(log.isInfoEnabled()){
				log.info("######              save----------------------session\n");
				log.info("######save after content----------------------"+session.getAttributes().toString());
				}
			}

		}

	}
	public void init(FilterConfig arg0) throws ServletException {
	}

	public void destroy() {
		// ignore
	}

}
