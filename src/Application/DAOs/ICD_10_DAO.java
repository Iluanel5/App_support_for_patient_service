package Application.DAOs;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import Application.Tables.ICD_10;


public class ICD_10_DAO {

	public static List<ICD_10> getAll() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(ICD_10.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<ICD_10> list = new ArrayList<ICD_10>();
		
		try {
			
			list = session.createCriteria(ICD_10.class).list();
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

	public static ICD_10 get(int icd_10ID) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(ICD_10.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		ICD_10 record = null;
			
		try {
			record = (ICD_10) session.get(ICD_10.class, icd_10ID);
			if (record.equals(null)||record.getActive()==0) {
				throw new Exception("ICD-10 with id: "+icd_10ID+" not exist");
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

	public static void changeActive(ICD_10 icd_10) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(ICD_10.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
			
		try {
			if(icd_10.getActive()==1)
				icd_10.setActive(0);
			else 
				icd_10.setActive(1);
			
			session.saveOrUpdate(icd_10); //jeœli istnieje 
			session.beginTransaction();
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
		
	}
	
	private static void add(String mark, String name) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(ICD_10.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
			
		try {
			ICD_10 icd_10 = new ICD_10(mark, name);
			
			session.save(icd_10);
			session.getTransaction().commit();
		} catch (Exception e) { 
			
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	private static String getFileNameExtension(String input) {
		int dotIndex = input.lastIndexOf('.');
	    return (dotIndex == -1) ? "" : input.substring(dotIndex + 1);
	}

	private static NodeList createNodeList (String pathToFile, String expression) throws XPathExpressionException, FileNotFoundException {
		FileReader fileReader = new FileReader(pathToFile);
		InputSource inputSource = new InputSource(fileReader);
		XPath xpath = XPathFactory.newInstance().newXPath();
		return (NodeList) xpath.evaluate(expression, inputSource, XPathConstants.NODESET);
	}
	
	public static void uploadXmlFile(String pathToFile) {
		System.out.println(getFileNameExtension(pathToFile));
		//List <ICD_10> list = new ArrayList<>();
		
		try {
			if(!getFileNameExtension(pathToFile).contains("xml")) 
				throw new Exception("z³y format pliku");
			
			
			String expressionParent = "/hcd/nodes/node/nodes/node/nodes/node/nodes/node/nodes/node";
			//String expression = "/hcd/nodes/node/nodes/node/nodes/node/nodes/node/nodes/node/nodes/node/@code";
			//String expression2 = "/hcd/nodes/node/nodes/node/nodes/node/nodes/node/nodes/node/nodes/node/name";
			
			NodeList parentNode = createNodeList(pathToFile, expressionParent);
			//NodeList codes = createNodeList(pathToFile, expression);
			//NodeList names = createNodeList(pathToFile, expression2);
			for (int i = 0; i < parentNode.getLength(); i++) {
				add(
						parentNode.item(i).getAttributes().item(0).getNodeValue(),
						parentNode.item(i).getChildNodes().item(1).getTextContent()
						
						);
				if (parentNode.item(i).getChildNodes().getLength()>5) {
				for (int j = 1; j < (parentNode.item(i).getChildNodes().item(5).getChildNodes().getLength()); j+=2) {
					add(
							parentNode.item(i).getChildNodes().item(5).getChildNodes().item(j).getAttributes().item(0).getNodeValue(),
							parentNode.item(i).getChildNodes().item(5).getChildNodes().item(j).getChildNodes().item(1).getTextContent()
							
							);
					
				
				}
				}
				
				//System.out.println(parentNode.item(i).getAttributes().item(0).getNodeValue());//G³ówne kody A00
				//System.out.println(parentNode.item(i).getChildNodes().item(1).getTextContent());//opis g³ównego gówna
				//System.out.println(parentNode.item(i).getChildNodes().item(5).getChildNodes().item(1).getAttributes().item(0).getNodeValue());//to kolejny kod gówna A00.1
				//System.out.println(parentNode.item(i).getChildNodes().item(5).getChildNodes().item(1).getChildNodes().item(1).getTextContent());
				//System.out.println(parentNode.item(i).getChildNodes().item(5).getChildNodes().item(3).getAttributes().item(0).getNodeValue());
				//list.add(new ICD_10(codes.item(i).getNodeValue(),names.item(i).getTextContent()));
			  
			}
			
			
		} catch (SAXException | IOException e) {
			
			e.printStackTrace();
		}catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
 
		//return list;
        
        
		

	}
	
	
}
