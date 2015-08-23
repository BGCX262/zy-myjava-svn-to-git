package com.def.springmvc.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.SystemUtils;
import com.def.springmvc.entity.identity.User;
import com.def.springmvc.service.UserService;

import com.def.springmvc.util.CipherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.def.springmvc.web.session.SessionManager;
import com.def.springmvc.web.session.UserSession;
import com.def.springmvc.web.session.UserSessionUtils;
import com.def.springmvc.web.AttributeKeys;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 30, 2011 - 3:11:34 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class RememberMeInterceptor extends HandlerInterceptorAdapter {

	private static final String KEY_FILE = SystemUtils.USER_HOME + "/." + AttributeKeys.KEY_FILE_NAME + "/key";
	
	private UserService userService;
	
	public RememberMeInterceptor() {
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

			resolveRememberMe(request, response);

		return true;
	}
	
	public void resolveRememberMe(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		/**modify sunhaibo 2012-06-18*/
		//if(request.getSession().getAttribute(AttributeKeys.USER_ID_KEY)==null) {
		if(	UserSessionUtils.getUserSession().getAttribute(AttributeKeys.USER_KEY)==null){
			Cookie cookie = WebUtils.getCookie(request, AttributeKeys.REMEMBER_ME_KEY);
			if(cookie!=null && StringUtils.hasText(cookie.getValue())) {
				String username = CipherUtil.decrypt(cookie.getValue(), KEY_FILE);
				User user = userService.getUser(username);
				if(user==null){
					return;
				}
				//request.getSession().setAttribute(AttributeKeys.USER_ID_KEY, user.getId());
				UserSessionUtils.getUserSession().setAttribute(AttributeKeys.USER_KEY, user.getId()+"");
				
				SessionManager sessionManager = new SessionManager();
				UserSession session = SessionManager.getCurrentSession();
				if (session != null && session.isValidate()) {
					/** 更新最访问时间 */
					sessionManager.updateSessionLastVisitedTime();
					/** 保存session */
					sessionManager.saveSession();
				}
				
				String redirectUrl=request.getContextPath()+AttributeKeys.USER_INDEX_URL;
				response.sendRedirect(redirectUrl);
			}
		}else{
			String requestUrl=request.getRequestURL().toString();
			String ingnoreUrl=RememberMeInterceptor.getServerRootHttpPath(request);
			
			if(requestUrl.endsWith(ingnoreUrl) || requestUrl.endsWith(ingnoreUrl+"/")){
				User user = userService.getUser(UserSessionUtils.getUserSession().getAttribute(AttributeKeys.USER_KEY));
				String redirectUrl=request.getContextPath()+AttributeKeys.USER_INDEX_URL;
				response.sendRedirect(redirectUrl);
			}
		}
		

		
	}
	public  static String getServerRootHttpPath(HttpServletRequest request){
		String url =request.getRequestURL().toString();
		String contextPath=request.getContextPath();
		
		if(!StringUtils.hasText(url)){
			return "";
		}
		if(url.indexOf("//")!=-1){
			url=url.substring(url.indexOf("//")+2);
		}
		
		url=url.split("/")[0];
		
		if(StringUtils.hasText(contextPath)){
			url=url+contextPath;
		}
		return url;
	}
	
}
