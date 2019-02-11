package Application.DAOs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Application.Tables.Employment;
import Application.Tables.EmploymentDictionary;
import Application.Tables.Institution;
import Application.Tables.Person;
import Application.Tables.SpecializationDictionary;

public class EmploymentDAO {

	public static void add(
			String dateFrom, 
			String dateTo, 
			String pwzNumber,
			Person person, 
			Institution institution,
			EmploymentDictionary employmentDictionary,
			SpecializationDictionary specialization) {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employment.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
 
		try {
			//DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			//TimeZone tz = TimeZone.getTimeZone("UTC");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			//df.setTimeZone(tz);
			//Date d = df.parse(dateFrom);
			Employment personel = new Employment(
					df.parse(dateFrom), 
					df.parse(dateTo),
					pwzNumber,
					person, 
					institution,
					employmentDictionary,
					specialization);
			session.save(personel);
			session.getTransaction().commit();
			
		}catch (ParseException pe) {
			System.out.println("Z³y format daty");
		}catch (Exception e) { 
				
		} finally {
			session.close();
			sessionFactory.close();
		}
		
	}

	public static List<Employment> getAll() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employment.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Employment> personel = null;
		
		try {
			
			personel = session.createCriteria(Employment.class).list();
			
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		return personel;
	}
	
	public static List<Employment> getAllActive() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employment.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Employment> list = null;
		Date today = new Date();
		
		try {
			
			list = session.createCriteria(Employment.class).list();
			for (int i=0; i<=list.size(); i++) {
				if(list.get(i).getDateTo().after(today) && list.get(i).getPerson().getActive()==0)
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

	public static Employment get(int employmentID) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employment.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		Employment personel = new Employment();
			
		try {
			personel = (Employment) session.get(Employment.class, employmentID);
			if (personel.equals(null)/*||personel.getActive()==0*/) {
				throw new Exception(" Employment with id: "+employmentID+" not exist");
			}

			
			session.beginTransaction();
			session.getTransaction().commit();
			
		} catch (Exception e) { 
			System.out.println(e);
		} finally {
			session.close();
			sessionFactory.close();
		}
		return personel;
	}

	public static void update(
			Employment employment, 
			String dateFrom, 
			String dateTo, 
			String pwzNumber,
			Person person, 
			Institution institution,
			EmploymentDictionary employmentDictionary,
			SpecializationDictionary specialization) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employment.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateto, datefrom;
			dateto = (Date) format.parse(dateTo);
			employment.setDateTo(dateto);
			datefrom = (Date) format.parse(dateFrom);
			employment.setDateFrom(datefrom);
			employment.setPerson(person);
			employment.setInstitution(institution);
			employment.setEmploymentDictionary(employmentDictionary);
			employment.setSpecialization(specialization);
			employment.setPwzNumber(pwzNumber);
			
			session.saveOrUpdate(employment); //jeœli istnieje 
			session.beginTransaction();
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

}

