package com.revature.repository;

import java.util.List;

import org.hibernate.Query;
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
	
	@SuppressWarnings("unchecked")
	public User getUserById(int Accountid) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("from User where account = :accountVar");
		List<User> ul = q.list();
		
		return ul.get(0);
	}
}
