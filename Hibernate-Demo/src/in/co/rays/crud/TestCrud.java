package in.co.rays.crud;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class TestCrud {
	public static void main(String[] args) {

		// testAdd();
		// testUpdate();
		// testGet();
		// testList();
		// testList1();
		// testCriteria(); //Restriction
		//testcriteria1(); // projection
		testCriteria2(); // simple
	}

	public static void testAdd() {

		User user = new User();
		user.setFname("Ram");
		user.setLname("Sharma");
		user.setUsername("ram.Sharma");
		user.setPassword("pass123");

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();
		System.out.println("inserted");

	}

	public static void testGet() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		User user = (User) session.get(User.class, 4);
		System.out.println(user.getId());
		System.out.println(user.getFname());
		System.out.println(user.getLname());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());

		session.close();
	}

	public static void testList() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Query q = session.createQuery("from User");
		List list = q.list();
		Iterator<User> it = list.iterator();
		while (it.hasNext()) {
			User user = it.next();
			System.out.println(user.getId());
			System.out.println("\t" + user.getFname());
			System.out.println("\t" + user.getLname());
			System.out.println("\t" + user.getUsername());
			System.out.println("\t" + user.getPassword());
		}
	}

	public static void testList1() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		// Query q = session.createQuery("select u.id from User u");
		Query q = session.createQuery("select u.id , u.Fname from User u");
		List list = q.list();
		Iterator it = list.iterator();

		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			System.out.println(obj[0]);
			System.out.println(obj[1]);

		}
	}

	public static void testCriteria() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		ProjectionList p = Projections.projectionList();

		criteria.add(Restrictions.like("Lname", "sharma"));
		criteria.add(Restrictions.eq("id", 1));

		List<User> list = criteria.list();
		Iterator<User> it = list.iterator();
		while (it.hasNext()) {

			// User user = it.next();
			// System.out.println(user);

			User user = it.next();
			System.out.println(user.getId());
			System.out.println("\t" + user.getFname());
			System.out.println("\t" + user.getLname());
			System.out.println("\t" + user.getUsername());
			System.out.println("\t" + user.getPassword());

		}

	}

	public static void testcriteria1() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		ProjectionList p = Projections.projectionList();
		p.add(Projections.property("Fname"));
		criteria.setProjection(p);

		List<User> list = criteria.list();
		Iterator<User> it = list.iterator();

		while (it.hasNext()) {

			User user = it.next();
			System.out.println(user.getFname());

		}
	}

	public static void testCriteria2() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		List<User> list = criteria.list();
		Iterator<User> it = list.iterator();
		while (it.hasNext()) {
			User user = it.next();
			System.out.println(user.getId());
			System.out.println("\t" + user.getFname());
			System.out.println("\t" + user.getLname());
			System.out.println("\t" + user.getUsername());
			System.out.println("\t" + user.getPassword());
	}
}
}
