package Application.Controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Application.DAOs.EmploymentDictionaryDAO;
import Application.DAOs.PatientDAO;
import Application.DAOs.PersonDAO;
import Application.DAOs.PersonalRoleDAO;
import Application.Tables.EmploymentDictionary;
import Application.Tables.Person;

@Path("/Person")
public class PersonController {


	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String add(@FormParam("firstName")String name, 
						@FormParam("lastName")String surname,
						@FormParam("pesel")String pesel,
						@FormParam("email")String email,
						@FormParam("secondName")String secondName,
						@FormParam("phoneNumber")String phoneNumber,
						@FormParam("address")String address,
						@FormParam("postalCode")String postalCode,
						@FormParam("city")String city
						) {
		String password=PersonDAO.add(name, surname,pesel, email,
				secondName, phoneNumber, address, postalCode, city);
		return "{ \"password\": \""+password+"\"}";
		 
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("delete/{personID}")
	public void delete(@PathParam("personID") int personID) {
		PersonDAO.delete(personID);
		
	}
	
	@Path("/changePassword/{personID}")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String changePassword(@PathParam("personID")int personID, 
						@FormParam("password")String password,
						@FormParam("newPassword")String newPassword) {

		Person p = PersonDAO.get(personID);
		String pas = PersonDAO.securePassword(password);
		
		if (p.getPassword().equals(pas)) {
			PersonDAO.changePassword(newPassword, p);
			return "true";
		}else {
			return "false";
		}
	}
	

	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String authentication(@FormParam("pesel")String pesel, 
						@FormParam("password")String password) {

		Person p = PersonDAO.authentication(pesel, password);
		if (p!=null) 
		return "{ \"personID\": \""+p.getPersonID()
				+"\", \"active\": \""+p.getActive()
				+"\", \"firstName\": \""+p.getFirstName()
				+"\", \"lastName\": \""+p.getLastName()+"\"}";
		else
			return null;
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	@Path("/changeActive/{personID}")
	public String changeActive (@PathParam("personID") int personID) {
		

			PersonDAO.changeActive(PersonDAO.get(personID));
		
		
		return "Usunieto uzytkownika";
	}
	
	
	
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getAll() {
		
		return PersonDAO.getAll();
	}
	
	@Path("/allActive")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getAllActive() {
		
		return PersonDAO.getAllActive();
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update/{personID}")
	public void update (
			@PathParam("personID") int personID, 
			@FormParam("firstName")String name, 
			@FormParam("lastName")String surname,
			@FormParam("email")String email,
			@FormParam("address")String address,
			@FormParam("postalCode")String postalCode,
			@FormParam("city")String city, 
			@FormParam("pesel")String pesel,
			@FormParam("secondName")String secondName,
			@FormParam("phoneNumber")String phoneNumber) {
		PersonDAO.update(
				PersonDAO.get(personID),
				name, surname, email, address, postalCode, city, pesel, secondName, phoneNumber
				);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/{personID}")
	public Person get(@PathParam("personID") int personID) {
		
		return PersonDAO.get(personID);

	}
	
}

