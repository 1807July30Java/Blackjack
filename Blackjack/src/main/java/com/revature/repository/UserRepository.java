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
import com.revature.util.HibernateUtil;

@Repository(value="userRepository")
@Transactional
@EnableTransactionManagement 
public class UserRepository {

	@Autowired
	SessionFactory sessionFactory;
	
	
	//private SessionFactory sf = HibernateUtil.getSessionFactory();
	
	@SuppressWarnings("unchecked")
	public User getUserById(int Accountid) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("from User where account = :accountVar");
		q.setParameter("accountVar", Accountid);
		List<User> ul = q.list();
		
		return ul.get(0);
	}


	public void persist(User u) {
		Session s = sessionFactory.getCurrentSession();
		s.persist(u);
	}
}
