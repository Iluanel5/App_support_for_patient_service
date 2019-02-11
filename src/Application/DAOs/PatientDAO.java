package Application.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Application.Tables.Institution;
import Application.Tables.Patient;
import Application.Tables.Person;
import Application.Tables.PersonalRole;

public class PatientDAO {

	public static void add(
			String insurance, 
			String comment, 
			Person person, 
			Institution institution) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Patient.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
 
		try {
			Patient patient = new Patient(insurance,comment,person,institution);
			session.save(patient);
			session.getTransaction().commit();
			
		} catch (Exception e) { 
				
		} finally {
			session.close();
			sessionFactory.close();
		}
		
	}
	
	
	
	public static void update(
			Patient patient,
			String insurance, 
			String comment, 
			Person person, 
			Institution institution) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Patient.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
 
		try {
			patient.setComment(comment);
			patient.setInstitution(institution);
			patient.setInsurance(insurance);
			patient.setPerson(person);
			
			session.saveOrUpdate(patient);
			session.beginTransaction();
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		
	}

	public static List<Patient> getAll() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Patient.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Patient> list = null;
		
		try {
			
			list = session.createCriteria(Patient.class).list();
			
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		return list;
	}
	
	public static List<Patient> getAllActive() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Patient.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Patient> list = null;
		
		try {
			
			list = session.createCriteria(Patient.class).list();
			for (int i=0; i<=list.size(); i++) {
				if(list.get(i).getPerson().getActive()==0)
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

	public static Patient get(int patientID) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Patient.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		Patient patient = null;
			
		try {
			patient = (Patient) session.get(Patient.class, patientID);
			if (patient.equals(null)||patient.getPerson().getActive()==0) {
				throw new Exception("Patient with id: "+patientID+" not exist");
			}

			
			session.beginTransaction();
			session.getTransaction().commit();
			
		} catch (Exception e) { 
			System.out.println(e);
		} finally {
			session.close();
			sessionFactory.close();
		}
		return patient;
	}
	
	public static Patient getForPerson(int personID, List<Patient> list) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Patient.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		Patient patient = null;
		//List<Patient> list = null;
		
		try {
			
			//list = session.createCriteria(Patient.class).list();
			for (int i=0; i<=list.size(); i++) {
				if(list.get(i).getPerson().getActive()==0 
						|| list.get(i).getPerson().getPersonID()!=personID)
					list.remove(i);
			}
			
			patient = list.get(0);
			
			session.getTransaction().commit();
			
			session.beginTransaction();
			session.getTransaction().commit();
			
		} catch (Exception e) { 
			System.out.println(e);
		} finally {
			session.close();
			sessionFactory.close();
		}
		return patient;
	}

	

}
