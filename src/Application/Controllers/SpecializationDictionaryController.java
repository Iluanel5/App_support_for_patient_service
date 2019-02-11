package Application.Controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Application.DAOs.PersonDAO;
import Application.DAOs.SpecializationDictionaryDAO;
import Application.Tables.SpecializationDictionary;

@Path("/SpecializationDictionary")
public class SpecializationDictionaryController {
	
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SpecializationDictionary> getAll() {
		
		return SpecializationDictionaryDAO.getAll();
	}
	
	@Path("/allActive")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SpecializationDictionary> getAllActive() {
		
		return SpecializationDictionaryDAO.getAllActive();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/{recordID}")
	public SpecializationDictionary get (@PathParam("recordID") int recordID) {
		
		return SpecializationDictionaryDAO.get(recordID);
		
		
		//return "<meta http-equiv=\"refresh\" content=\"5; url=..\\..\\index.html\">";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	@Path("/changeActive/{dictionaryID}")
	public void changeActive (@PathParam("dictionaryID") int dictionaryID) {
		

		SpecializationDictionaryDAO.changeActive(SpecializationDictionaryDAO.get(dictionaryID));
		
	}
}
