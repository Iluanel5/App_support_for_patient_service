package Application.DAOs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Application.Tables.Employment;
import Application.Tables.Patient;
import Application.Tables.Reservation;

public class ReservationDAO {

	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public static Boolean add(
			String dateFrom, 
			String dateTo, 
			Patient patient, 
			Employment personel) {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Reservation.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Boolean res = true;
		try {
			//DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			//TimeZone tz = TimeZone.getTimeZone("UTC");
			
			//df.setTimeZone(tz);
			//Date d = df.parse(dateFrom);
			Reservation reservation = new Reservation(
					df.parse(dateFrom), 
					df.parse(dateTo),
					patient, 
					personel);
			session.save(reservation);
			session.getTransaction().commit();
			
		}catch (ParseException pe) {
			System.out.println("Z³y format daty");
		}catch (Exception e) { 
			res = false;
		} finally {
			session.close();
			sessionFactory.close();
		}
		
		return res;
	}
	
	public static List<Reservation> getAll() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Reservation.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Reservation> list = null;
		
		try {
			
			list = session.createCriteria(Reservation.class).list();
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

	public static Reservation get(int reservationID) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Reservation.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		Reservation reservation = null;
			
		try {
			reservation = (Reservation) session.get(Reservation.class, reservationID);
			

			
			session.beginTransaction();
			session.getTransaction().commit();
			
		} catch (Exception e) { 
			System.out.println(e);
		} finally {
			session.close();
			sessionFactory.close();
		}
		return reservation;
	}
	
	public static List<Reservation> get(String dateFrom, String dateTo, int employeeID) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Reservation.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		List<Reservation> reservation = null;
		
		
		
		
		try {
			
			System.out.println("------------------------ "+"from Reservation r where r.ID_Employment='"
					+employeeID+"' AND Date_From >= '"+df.format(df.parse(dateFrom))
					+"' AND Date_To <= '"+df.format(df.parse(dateTo))+"'"+"---------------");
			
			
				reservation =  session.createQuery("from Reservation r where r.ID_Employment='"
						+employeeID+"' AND Date_From >= '"+dateFrom
						+"' AND Date_To <= '"+dateTo+"'").getResultList();//.getSingleResult();
				
				
			
			session.beginTransaction();
			session.getTransaction().commit();
			
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		return reservation;
	}
	
	
	
	public static List<Reservation> getAllForEmployee(Employment employee) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Reservation.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		List<Reservation> list = new ArrayList<>();
		Date today = new Date();
		
		
		
		try {
			String str="from Reservation r where r.ID_Employment='"
					+employee.getEmploymentID()+"' AND Date_From < '"+today+"'";
			//list = session.createCriteria(Reservation.class).list();
			if (employee.getDateTo().before(today) && employee.getPerson().getActive()!=0 )
			list = session.createQuery(str).getResultList();

			
			session.beginTransaction();
			session.getTransaction().commit();
			
		} catch (Exception e) { 
			System.out.println(e);
		} finally {
			session.close();
			sessionFactory.close();
		}
		return list;
	}

	public static void delete(int reservationID) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Reservation.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		
		try {
			
			Reservation reservation = null;
			reservation = (Reservation) session.get(Reservation.class, reservationID);
			
			session.delete(reservation);
			session.beginTransaction();
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		
	}

	
}
