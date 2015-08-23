package com.def.springmvc.service.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;

import com.def.springmvc.entity.identity.Role;
import com.def.springmvc.entity.identity.User;
import com.def.springmvc.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import com.def.springmvc.dao.identity.UserDao;
import com.def.springmvc.service.UserService;
/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:15:39 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@PersistenceContext
	private EntityManager entityManager;
	
	public UserServiceImpl() {
	}
	

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	


	
	public User getUser(Long userId) {
		return userDao.get(userId);
	}
	
	@Transactional(readOnly=true)
	public User loginUser(String userId, String password) {
		User user = userDao.findUserByID(userId);
		if(user!=null && StringUtils.equals(password, user.getPassword())) {
			//user.setLastLogin(new Date());
			//userDao.update(user);
			return user;
		}
		return  null;
	}
	
	public void createUser(User user) {
		updateUser(user);
	}

	public void updateUser(User user) {
		if(user.getId()==null) {
			userDao.save(user);
		} else {
			userDao.update(user);
		}
	}

	public void deleteUser(Long userId) {
		userDao.delete(userId);
	}
	
	public User getUser(String username) {
		return userDao.findUserByID(username);
	}
	
	
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public User registerUser(User user) {
		Assert.notNull(user, "Parameter user can not be null!");


		Date now = new Date();
		// create project
		String uniqueId = cleanEmail(user.getEmail());
		//add by zhongyi 12-7-27 am
		//System.out.println("now, i am  the UserServiceImpl, catch me? uniquedId="+ uniqueId);

	

		// create user

		user.setEmail(user.getEmail());
		//user.setEntered(now);
		//user.setLastLogin(now);
		//user.setEnabled(true);
		//user.setToken(UUID.randomUUID().toString());
		//user.setProject(project);
		userDao.save(user);
		//目前新注册的用户就归入这个角色

		
		return user;
	}
	
	protected String cleanEmail(String email) {
		email = StringUtils.lowerCase(email);
		String uniqueId = StringUtils.substringBefore(email, "@");
		StringBuffer buffer = new StringBuffer();
		for(char c : uniqueId.toCharArray()) {
			if((c>='0' && c<='9') || (c>='a' && c<='z') || c=='_' || c=='-') {
				buffer.append(c);
			} else {
				buffer.append('-');
			}
		}
		return buffer.toString();
	}


	public User testUser()
	{
		User user = new User();
		user.setEmail("test@hp.com");
		user.setUserId("test");
		user.setFirstName("Dory");
		user.setLastName("Font");
		user.setPassword("000000");
		entityManager.persist(user);

		user = entityManager.find(User.class, user.getId());

		
		Role role = new Role();
		role.setName("read");
		role.setDescription("common read operation");
		user.getRoles().add(role);
		
		entityManager.persist(user);
		entityManager.flush();
		
		
		//role = entityManager.find(Role.class, role.getId());
		entityManager.refresh(role);

		user = role.getUsers().get(0);
	
		
		return user;
	}
	
}
