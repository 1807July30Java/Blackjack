package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.beans.Room;
import com.revature.beans.User;
import com.revature.util.HibernateUtil;

public class RoomDaoImpl implements RoomDao {

	private SessionFactory sf = HibernateUtil.getSessionFactory();
	
	@Override
	public List<Room> getAllRooms() {
		Session s = sf.openSession();
		List<Room> ul = null;
		
		try {
			ul = s.createQuery("from Room").list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		
		return ul;
	}

	@Override
	public Room getRoomById(int id) {
		Session s = sf.openSession();
		Room r = null;
		
		try {
			r = (Room) s.get(Room.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		return r;
	}

	@Override
	public int addAccount(Room r) {
		Session s = sf.openSession();
		int id = 0;
		Transaction tx = s.beginTransaction();
		try {
			id = (int) s.save(r);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		s.close();
		
		return id;
	}

	@Override
	public void updateAccount(Room r) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.merge(r);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		s.close();

	}

	@Override
	public void deleteAccount(Room r) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.delete(r);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		s.close();

	}

}
