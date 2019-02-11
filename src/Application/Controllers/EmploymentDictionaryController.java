package Application.Controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Application.DAOs.EmploymentDictionaryDAO;
import Application.DAOs.SpecializationDictionaryDAO;
import Application.Tables.EmploymentDictionary;

@Path("/EmploymentDictionary")
public class EmploymentDictionaryController {
	
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EmploymentDictionary> getAll() {
		
		return EmploymentDictionaryDAO.getAll();
	}
	
	@Path("/allActive")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EmploymentDictionary> getAllActive() {
		
		return EmploymentDictionaryDAO.getAllActive();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/{recordID}")
	public EmploymentDictionary get (@PathParam("recordID") int recordID) {
		
		return EmploymentDictionaryDAO.get(recordID);
		
		
		//return "<meta http-equiv=\"refresh\" content=\"5; url=..\\..\\index.html\">";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	@Path("/changeActive/{dictionaryID}")
	public void changeActive (@PathParam("dictionaryID") int dictionaryID) {
		

		EmploymentDictionaryDAO.changeActive(EmploymentDictionaryDAO.get(dictionaryID));
		
		
		
	}
}

