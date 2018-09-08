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

@Repository(value="loginRepository")
@Transactional
@EnableTransactionManagement 
public class LoginRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@SuppressWarnings("unchecked")
	public boolean authentication(Account a) {
		List<Account> al = null;
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("from Account where username = :userVar and password = :passVar");
		q.setParameter("userVar", a.getUsername());
		q.setParameter("passVar", a.getPassword());
		al = q.list();
		
		if(!al.isEmpty())
			return true;
		
		return false;
	}

}