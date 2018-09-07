package com.revature.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Account;
import com.revature.beans.User;

@Repository(value="userRepository")
@Transactional
@EnableTransactionManagement 
public class UserRepository {

	@Autowired
	SessionFactory sessionFactory;
	
	public User getUserById(int id) {
		Session s = sessionFactory.getCurrentSession();
		User u = (User) s.get(User.class, id);
		
		return u;
	}
}
