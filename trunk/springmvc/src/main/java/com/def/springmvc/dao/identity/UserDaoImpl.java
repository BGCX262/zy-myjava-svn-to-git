package com.def.springmvc.dao.identity;

import org.apache.commons.lang.StringUtils;
import com.def.springmvc.dao.identity.UserDao;
import com.def.springmvc.entity.identity.User;

import com.def.springmvc.dao.base.AbstractDao;
import com.def.springmvc.util.Page;
import org.springframework.stereotype.Repository;




/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 10:09:37 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

	protected UserDaoImpl() {
		super(User.class);
	}

	static final String JPQL0 = "FROM User AS u WHERE u.userId = ?1";
	public User findUserByID(String userId) {
		return findOne(JPQL0, userId);
	}

	static final String JPQL1 = "FROM User AS u %s ORDER BY u.entered DESC";
	public Page<User> findUserPage(Page<User> page, Long siteId) {
		if(siteId!=null) {
			return findPage(page, String.format(JPQL1, "WHERE u.project.category.site.id = ?1"), siteId);
		}
		return findPage(page, String.format(JPQL1, ""));
	}
	

	static final String JPQL4 = "DELETE User AS u WHERE u.id = ?1)";
	public void deleteUser(Long userID) {
		// TODO Auto-generated method stub
		 delete(userID);
	}



}
