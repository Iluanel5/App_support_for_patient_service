package Application.DAOs;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Application.Tables.PersonalRole;
import Application.Tables.Role;



public class RoleDAO {
	public static Role get(int roleID) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Role.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		Role role = null;
			
		try {
			role = (Role) session.get(Role.class, roleID);
			

			
			session.beginTransaction();
			session.getTransaction().commit();
			
		} catch (Exception e) { 
			System.out.println(e);
		} finally {
			session.close();
			sessionFactory.close();
		}
		return role;
	}
	
	public static List<Role> getAll() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Role.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Role> list = null;
		
		try {
			
			list = session.createCriteria(Role.class).list();
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
	
	public static Role get(String name) {
		Role pr = null;
		List<Role> list= getAll();
		
		for(int i = 0; i<list.size(); i++) {
			if(list.get(i).getName().equals(name)) {
				pr=list.get(i);
				break;
			}
		}
		
		return pr;
	}
}
