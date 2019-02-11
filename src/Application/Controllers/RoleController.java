package Application.Controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Application.DAOs.PatientDAO;
import Application.DAOs.RoleDAO;
import Application.Tables.Patient;
import Application.Tables.Role;

@Path("/Role")
public class RoleController {

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Role> getAll() {
		
		return RoleDAO.getAll();
	}
	
	
}
