package Application.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Application.Tables.Patient;
import Application.Tables.Person;
import Application.Tables.SpecializationDictionary;

public class SpecializationDictionaryDAO {
	
	public static List<SpecializationDictionary> getAll() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(SpecializationDictionary.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<SpecializationDictionary> list = null;
		
		try {
			
			list = session.createCriteria(SpecializationDictionary.class).list();
			
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		return list;
	}
	
	public static List<SpecializationDictionary> getAllActive() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(SpecializationDictionary.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<SpecializationDictionary> list = null;
		
		try {
			
			list = session.createCriteria(SpecializationDictionary.class).list();
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

	public static SpecializationDictionary get(int id) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(SpecializationDictionary.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		SpecializationDictionary dictionary = null;
			
		try {
			dictionary = (SpecializationDictionary) session.get(SpecializationDictionary.class, id);
			if (dictionary.equals(null)/*||personel.getActive()==0*/) {
				throw new Exception(" Employment with id: "+id+" not exist");
			}

			
			session.beginTransaction();
			session.getTransaction().commit();
			
		} catch (Exception e) { 
			System.out.println(e);
		} finally {
			session.close();
			sessionFactory.close();
		}
		return dictionary;
	}
	
	public static void changeActive(SpecializationDictionary dictionary) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(SpecializationDictionary.class).buildSessionFactory();
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
