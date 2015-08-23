package com.def.springmvc.dao.identity;

import com.def.springmvc.entity.identity.User;
import com.def.springmvc.dao.base.BaseDao;
import com.def.springmvc.util.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 10:07:53 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface UserDao extends BaseDao<User> {

	/**
	 * Find user by username
	 * @param username
	 * @return
	 */
	User findUserByID(String userId);


	
	void deleteUser(Long userID);
	
	


	
}
