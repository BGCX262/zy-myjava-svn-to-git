package com.def.springmvc.test.dao;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.def.springmvc.entity.identity.Role;
import com.def.springmvc.entity.identity.User;
import com.def.springmvc.test.dao.SpringTransactionalTestCase;


import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

/**
 * 测试初始化数据
 *
 * @author HenryYan
 */
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class InitDataTest extends SpringTransactionalTestCase {

	@PersistenceContext
	private EntityManager em;
	
	@Test
	public void testUserData() throws Exception {


		User user = new User();
		user.setEmail("test@hp.com");
		user.setUserId("test");
		user.setFirstName("Dory");
		user.setLastName("Font");
		user.setPassword("000000");
		em.persist(user);
		assertNotNull(user);
		
		user = em.find(User.class, user.getId());
		assertNotNull(user);
		
		Role role = new Role();
		role.setName("read");
		role.setDescription("common read operation");
		user.getRoles().add(role);
		em.persist(user);
		
		role = em.find(Role.class, role.getId());
		assertNotNull(role);

		//user = role.getUsers().get(0);
		//assertNotNull(user);
		
		
		

	}

}
