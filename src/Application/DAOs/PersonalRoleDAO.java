package Application.DAOs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Application.Tables.Institution;
import Application.Tables.Person;
import Application.Tables.PersonalRole;
import Application.Tables.Role;


public class PersonalRoleDAO {
	
	public static void add(Person person, Role role) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(PersonalRole.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
 
		try {
			PersonalRole personalRight = new PersonalRole(
					person, 
					role);
			session.save(personalRight);
			session.getTransaction().commit();
			
		
		}catch (Exception e) { 
				
		} finally {
			session.close();
			sessionFactory.close();
		}
		
	}
	
	
	
	public static List<PersonalRole> getAll() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(PersonalRole.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<PersonalRole> list = null;
		
		try {
			
			list = session.createCriteria(PersonalRole.class).list();
			for (int i=0; i<=list.size(); i++) {
				if(list.get(i).getRole().getActive()==0)
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

	public static List<PersonalRole> getForPerson(int personID) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(PersonalRole.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<PersonalRole> list = null;
		List<PersonalRole> list2 = null;
		try {
			
			list = session.createCriteria(PersonalRole.class).list();
			/*int size = list.size();
			for (int i=size; i>=0; i--) {
				if(list.get(i).getRole().getActive()==0 
						|| list.get(i).getPerson().getPersonID() != personID) {
					//System.out.println(size+" TU JESTEM!!!! "+list.get(i).getRole().getActive()+" "+list.get(i).getPerson().getPersonID());
					list.remove(i);
					
				}
			}*/
			list = list.stream().filter(x->x.getRole().getActive() == 1 && x.getPerson().getPersonID() == personID).collect(Collectors.toList());
			
			session.getTransaction().commit();
			
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		return list;
	}

	public static PersonalRole get(int personalRoleID) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(PersonalRole.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		PersonalRole list = null;
		
		try {
			
			list = (PersonalRole) session.get(PersonalRole.class, personalRoleID);
			//for (int i=0; i<=list.size(); i++) {
			//	if(!(list.get(i).getPerson().equals(person)))
			//		list.remove(i);
			//}
			session.getTransaction().commit();
			
		} catch (Exception e) { 
			System.out.println(e.getMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
		return list;
	}

	public static void delete(int personalRightsID) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(PersonalRole.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		
		try {
			
			PersonalRole right = new PersonalRole();
			right = (PersonalRole) session.get(PersonalRole.class, personalRightsID);
			
			session.delete(right);
			session.beginTransaction();
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		
	}
}
