package com.def.springmvc.service;


import com.def.springmvc.entity.identity.User;
import com.def.springmvc.util.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:07:44 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface UserService {

	User getUser(Long userId);
	
	User getUser(String username);
	
	User loginUser(String username, String password);
	
	void createUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(Long userId);
	
	User registerUser(User user);

	User testUser();

}
