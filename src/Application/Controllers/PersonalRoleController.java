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

import Application.DAOs.PersonDAO;
import Application.DAOs.PersonalRoleDAO;
import Application.DAOs.ReservationDAO;
import Application.DAOs.RoleDAO;
import Application.Tables.PersonalRole;

@Path("/PersonalRole")
public class PersonalRoleController {
	
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void add(
			@FormParam("personID") int personID, 
			@FormParam("roleID") int roleID) {

		PersonalRoleDAO.add(
				PersonDAO.get(personID),
				RoleDAO.get(roleID)
				);
		
	}
	
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonalRole> getAll() {
		
		return PersonalRoleDAO.getAll();
	}
	
	@Path("/getForPerson/{personID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonalRole> getForPerson(@PathParam("personID") int personID) {
		
		return PersonalRoleDAO.getForPerson(personID);
	}
	
	@Path("/getRoleForPerson/{personID}")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public PersonalRole getRoleForPerson(@PathParam("personID") int personID, 
			@FormParam("roleID") int roleID) {
		PersonalRole pr= null;
		List<PersonalRole> list = PersonalRoleDAO.getForPerson(personID);
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getRole().getRoleID()==roleID) {
				return pr;
			}
		}
		
		return pr;
	} 	
	
	@Path("/get/{attributeID}")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public PersonalRole get(@PathParam("attributeID") int attributeID) {
		
		return PersonalRoleDAO.get(attributeID);
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("delete/{personalRoleID}")
	public void delete(@PathParam("personalRoleID") int personalRoleID) {
		PersonalRoleDAO.delete(personalRoleID);
		//return "Reservation "+reservationID+" deleted";
	}
}
