package Application.Controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Application.DAOs.InstitutionDAO;
import Application.DAOs.PersonDAO;
import Application.Tables.Institution;

@Path("/Institution")
public class InstitutionController {

	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void add(@FormParam("address")String address, 
						@FormParam("postalCode")String postalCode,
						@FormParam("city")String city, 
						@FormParam("name")String name,
						@FormParam("numberOfRegisterBook")String numberOfRegisterBook) {
		InstitutionDAO.add(address,postalCode,city,name,numberOfRegisterBook);
	}
	
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Institution> getAll() {
		
		return InstitutionDAO.getAll();
	}
	
	@Path("/allActive")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Institution> getAllActive() {
		
		return InstitutionDAO.getAllActive();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/{recordID}")
	public Institution get (@PathParam("recordID") int recordID) {
		
		return InstitutionDAO.get(recordID);
		
		
		//return "<meta http-equiv=\"refresh\" content=\"5; url=..\\..\\index.html\">";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update/{institutionID}")
	public void update (
			@PathParam("institutionID") int institutionID, 
			@FormParam("address")String address, 
			@FormParam("postalCode")String postalCode,
			@FormParam("city")String city, 
			@FormParam("name")String name,
			@FormParam("numberOfRegisterBook")String numberOfRegisterBook
					) {
		InstitutionDAO.update(
				InstitutionDAO.get(institutionID),
				address,postalCode,city,name,numberOfRegisterBook
				);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/changeActive/{institutionID}")
	public void changeActive (
			@PathParam("institutionID") int institutionID) {
		InstitutionDAO.changeActive(InstitutionDAO.get(institutionID));
	}
	
}

