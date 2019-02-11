package Application.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Application.Tables.ICD_10;
import Application.Tables.Patient;
import Application.Tables.Reservation;
import Application.Tables.Visit;

public class VisitDAO {

	public static void add(String comment, Reservation reservation, ICD_10 icd_10) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Visit.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
 
		try {
			Visit visit = new Visit(comment, reservation, icd_10);
			session.save(visit);
			session.getTransaction().commit();
			
		} catch (Exception e) { 
				
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	public static List<Visit> getAll() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Visit.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Visit> list = null;
		
		try {
			
			list = session.createCriteria(Visit.class).list();
			
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		return list;
	}

	public static Visit get(int visitID) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Visit.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		Visit visit = new Visit();
			
		try {
			visit = (Visit) session.get(Visit.class, visitID);
			if (visit.equals(null)) {
				throw new Exception(" Visit with id: "+visitID+" not exist");
			}

			
			session.beginTransaction();
			session.getTransaction().commit();
			
		} catch (Exception e) { 
			System.out.println(e);
		} finally {
			session.close();
			sessionFactory.close();
		}
		return visit;
	}

}
