package com.revature.main;

import org.hibernate.SessionFactory;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		sf.openSession();
		
		
//		AccountDao a = new AccountDaoImpl();
//		System.out.println(a.getAccounts());
		Account a = new Account("Grandmaster", "Tep");
		User u = new User("firstname","lastname", a);
		System.out.println(u);
		UserDao ud = new UserDaoImpl();
		ud.addUser(u);
	}
	

}
