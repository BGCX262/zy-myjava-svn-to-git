package com.def.springmvc.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.def.springmvc.entity.identity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.def.springmvc.service.UserService;
import com.def.springmvc.web.AttributeKeys;
import com.def.springmvc.web.session.UserSessionUtils;

/**
 * Expose object to web context
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 18, 2011 - 2:22:31 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class ObjectExposeInterceptor extends HandlerInterceptorAdapter {
	//@Autowired
	//private PostCategoryService postCategoryService;

	//private SiteService siteService;
	@Autowired
	private UserService userService;


	
	public ObjectExposeInterceptor() {
	}
	

	

	

	

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//

		exposeUser(request);

		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//
		exposeUser(request);

	}
	

	
	protected void exposeUser(HttpServletRequest request) {
		/**modify sunhaibo2012-06-18*/
		if(UserSessionUtils.getUserSession().getAttribute(AttributeKeys.USER_ID_KEY)!=null) {
		//if(request.getSession().getAttribute(AttributeKeys.USER_ID_KEY)!=null) {
			
			//Long userId = (Long) request.getSession().getAttribute(AttributeKeys.USER_ID_KEY);
			Long userId=Long.valueOf(UserSessionUtils.getUserSession().getAttribute(AttributeKeys.USER_ID_KEY));
			User user = userService.getUser(userId);
			request.setAttribute(AttributeKeys.USER_KEY, user);
			request.setAttribute(AttributeKeys.USER_KEY_READABLE, user);
		}
		
	}
	

	
	
}
