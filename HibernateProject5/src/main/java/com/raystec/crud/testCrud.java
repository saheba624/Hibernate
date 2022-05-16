package com.raystec.crud;

import java.util.Iterator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.sql.RowSet;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;


public class testCrud {
	public static void main(String[] args) throws Exception {
		 testAdd();
		//testDelete();
		//testGet();
		//testUpdate();
	}

	
	public static void testAdd() {
		User u = new User();
		u.setFname("jay");
		u.setLname("Sharma");
		u.setUserName("Jay.Sharma");
		u.setPwd("Jay");

		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();

		session.save(u);

		tx.commit();

		session.close();

		HibernateUtil.shutdown();
	}

	public static void testDelete() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = new User();
			user.setId(2);
			session.delete(user);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
			HibernateUtil.shutdown();
		}

	}
	
	public static void testGet() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		User u =(User) session.get(User.class, 2);
		System.out.println(u.getId());
		System.out.println(u.getFname());
		System.out.println(u.getLname());
		System.out.println(u.getUserName());
		System.out.println(u.getPwd());
		session.close();
		HibernateUtil.shutdown();
}
	
	public static void testUpdate() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
		tx = session.beginTransaction();
		User user = new User();
		user.setId(2);
		user.setFname("Sameer");
	user.setLname("Ansari");
		session.update(user);
		tx.commit();
		} catch (HibernateException e) {
		tx.rollback();
		} finally {
		session.close();
		HibernateUtil.shutdown();
		}
}
}

