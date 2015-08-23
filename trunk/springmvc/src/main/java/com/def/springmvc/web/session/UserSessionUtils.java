package com.def.springmvc.web.session;

import com.def.springmvc.web.session.SessionManager;
import com.def.springmvc.web.session.UserSession;
import com.def.springmvc.web.session.UserSessionConstants;
import com.def.springmvc.util.StringUtils;
/**
 * 
 * @author sunhaibo
 *
 */
public abstract class UserSessionUtils {
	/**
	 * 检测用户是否登录 如果已经登录返回true
	 * */
	public final static boolean isLogin() {
		UserSession userSession=getUserSession();
		if (userSession == null) {
			return false;
		}
		String userInfo = getUserInfo();
		if (StringUtils.hasText(userInfo)) {
			return true;
		}
		return false;
	}

	public final static String getUserInfo() {
		UserSession userSession=getUserSession();
		if (userSession == null) {
			return null;
		}
		String userInfo = userSession.getAttribute(UserSessionConstants.USER_INFO_KEY);
		return userInfo;
	}
    
	public final static UserSession getUserSession(){
//		UserSession session=new StandardSession();
//		session.setAttribute("_org.osforce.connect.entity.system.User_ID", "4");
//		return session;
		
		return SessionManager.getCurrentSession();
	}
}
