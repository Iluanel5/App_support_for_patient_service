package Application.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Application.Tables.EmploymentDictionary;
import Application.Tables.SpecializationDictionary;

public class EmploymentDictionaryDAO {

	public static List<EmploymentDictionary> getAll() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(EmploymentDictionary.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<EmploymentDictionary> list = null;
		
		try {
			
			list = session.createCriteria(EmploymentDictionary.class).list();
			
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		return list;
	}
	
	public static List<EmploymentDictionary> getAllActive() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(EmploymentDictionary.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<EmploymentDictionary> list = null;
		
		try {
			
			list = session.createCriteria(EmploymentDictionary.class).list();
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
	
	public static EmploymentDictionary get(int recordID) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(EmploymentDictionary.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		EmploymentDictionary record = null;
			
		try {
			record = (EmploymentDictionary) session.get(EmploymentDictionary.class, recordID);
			if (record.equals(null)) {
				throw new Exception(" EmploymentDictionary with id: "+recordID+" not exist");
			}

			
			session.beginTransaction();
			session.getTransaction().commit();
			
		} catch (Exception e) { 
			System.out.println(e);
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		return record;
	}
	
	public static void changeActive(EmploymentDictionary dictionary) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(EmploymentDictionary.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
			
		try {
			if(dictionary.getActive()==1)
				dictionary.setActive(0);
			else
				dictionary.setActive(1);
			
			session.saveOrUpdate(dictionary); //jeœli istnieje 
			session.beginTransaction();
			session.getTransaction().commit();
		} catch (Exception e) { 
				
		} finally {
			session.close();
			sessionFactory.close();
		}
			
	}

}

