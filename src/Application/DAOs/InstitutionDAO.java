package Application.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Application.Tables.Institution;
import Application.Tables.Person;

public class InstitutionDAO {

	public static void add(
			String address, 
			String postalCode, 
			String city, 
			String name,
			String numberOfRegisterBook) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Institution.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
 
		try {
			Institution institution = new Institution(address, postalCode, city, name, numberOfRegisterBook);
			session.save(institution);
			session.getTransaction().commit();
			
		} catch (Exception e) { 
				
		} finally {
			session.close();
			sessionFactory.close();
		}
		
	}

	public static List<Institution> getAll() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Institution.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Institution> list = new ArrayList<Institution>();
		
		try {
			
			list = session.createCriteria(Institution.class).list();
			//for (int i=0; i<=list.size(); i++) {
			//	if(list.get(i).getActive()==0)
			//		list.remove(i);
			//}
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		return list;
	}
	
	public static List<Institution> getAllActive() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Institution.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Institution> list = new ArrayList<Institution>();
		
		try {
			
			list = session.createCriteria(Institution.class).list();
			for (int i=0; i<=list.size(); i++) {
				if(list.get(i).getActive()==0)
					list.remove(i);
			}
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		return list;
	}

	public static Institution get(int institutionID) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Institution.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		Institution institution = new Institution();
			
		try {
			institution = (Institution) session.get(Institution.class, institutionID);
			if (institution.equals(null)||institution.getActive()==0) {
				throw new Exception(" Institution with id: "+institutionID+" not exist");
			}

			
			session.beginTransaction();
			session.getTransaction().commit();
			
		} catch (Exception e) { 
			System.out.println(e);
		} finally {
			session.close();
			sessionFactory.close();
		}
		return institution;
	}

	public static void update(
			Institution institution, 
			String address, 
			String postalCode, 
			String city, 
			String name,
			String numberOfRegisterBook
			) {
	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Institution.class).buildSessionFactory();
	Session session = sessionFactory.openSession();
		
	try {
		institution.setAddress(address);
		institution.setCity(city);
		institution.setName(name);
		institution.setNumberOfRegisterBook(numberOfRegisterBook);
		institution.setPostalCode(postalCode);
		
		session.saveOrUpdate(institution); //jeœli istnieje 
		session.beginTransaction();
		session.getTransaction().commit();
	} catch (Exception e) { 
		
	} finally {
		session.close();
		sessionFactory.close();
	}
	
}
	
	

	public static void changeActive(Institution institution) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Institution.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
			
		try {
			if(institution.getActive()==1)
				institution.setActive(0);
			else 
				institution.setActive(1);
			
			session.saveOrUpdate(institution); //jeœli istnieje 
			session.beginTransaction();
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		
	}
	

}
