package Application.DAOs;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Application.Tables.Person;
import Application.Tables.PersonalRole;

public class PersonDAO {
	
	public static String add(
			String firstName, String lastName, String pesel, String email, String secondName,
			String phoneNumber, String address, String postalCode, String city) {
			
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Person.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String password = null;
		
		try {
			password = pesel.substring(0,4)+lastName.substring(0, 2)+firstName.substring(0, 2);
			Person user = new Person(
					firstName, 
					lastName, 
					pesel, 
					email, 
					secondName,
					phoneNumber, 
					address,
					postalCode,
					city, 
					securePassword(password));
			session.save(user);
			session.getTransaction().commit();
			
		} catch (Exception e) { 
				
		} finally {
			session.close();
			sessionFactory.close();
		}
			return password;
	}
		
	
	public static List<Person> getAll() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Person.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Person> users = null;
		
		try {
			
			users = session.createCriteria(Person.class).list();
			
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		return users;
	}
	
	
	
	public static List<Person> getAllActive() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Person.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Person> users = null;
		
		try {
			
			users = session.createCriteria(Person.class).list();
			users = users.stream().filter(x->x.getActive()==1).collect(Collectors.toList());
			
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		return users;
	}
	
		
	
	public static Person get(int id) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Person.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		Person user = null;
			
		try {
			user = (Person) session.get(Person.class, id);
			
			session.beginTransaction();
			session.getTransaction().commit();
			
		} catch (Exception e) { 
			System.out.println(e);
		} finally {
			session.close();
			sessionFactory.close();
		}
		return user;
	}
		
	
	public static Person authentication(String pesel, String password) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Person.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Person user = null;
		Boolean answer = false;
		List<Person> listPerson = null;
		final String sPassword = securePassword(password);
		
		try {
			
			listPerson = session.createCriteria(Person.class).list();
			
			if (listPerson.isEmpty() == true) 
				return user;
			
			user = listPerson.stream().filter(x->
				x.getActive()!=0 
				&& x.getPesel().equals(pesel) 
				&& x.getPassword().equals(sPassword))
					.findFirst().orElse(null);
			
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
			return user;
		}
		

	public static void changeActive(Person user) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Person.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			if(user.getActive()==1)
				user.setActive(0);
			else
				user.setActive(1);
			
			session.saveOrUpdate(user); //jeœli istnieje 
			
			session.getTransaction().commit();
		} catch (Exception e) { 
				
		} finally {
			session.close();
			sessionFactory.close();
		}
			
	}
	

	
	public static void update(
			Person person, String name, String surname,
			String email, String address, String postalCode, String city, String pesel, 
			String secondName, String phoneNumber
			) {
	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Person.class).buildSessionFactory();
	Session session = sessionFactory.openSession();
	session.beginTransaction();
	try {
		person.setAddress(address);
		person.setCity(city);
		person.setEmail(email);
		person.setFirstName(name);
		person.setLastName(surname);
		person.setPesel(pesel);
		person.setPostalCode(postalCode);
		person.setPhoneNumber(phoneNumber);
		person.setSecondName(secondName);
		
		session.saveOrUpdate(person); //jeœli istnieje 
		
		session.getTransaction().commit();
	} catch (Exception e) { 
		
	} finally {
		session.close();
		sessionFactory.close();
	}
	
}

	public static String securePassword(String password) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return generatedPassword;
	}

	public static void changePassword(String password, Person person) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Person.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			person.setPassword(PersonDAO.securePassword(password));
			
			session.saveOrUpdate(person); //jeœli istnieje 
			
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		
	}
	
	public static void delete(int personID) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Person.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			
			Person person = null;
			person = (Person) session.get(Person.class, personID);
			
			session.delete(person);
			
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		
	}
}
