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

import Application.DAOs.EmploymentDAO;
import Application.DAOs.EmploymentDictionaryDAO;
import Application.DAOs.InstitutionDAO;
import Application.DAOs.PersonDAO;
import Application.DAOs.PersonalRoleDAO;
import Application.DAOs.RoleDAO;
import Application.DAOs.SpecializationDictionaryDAO;
import Application.Tables.Employment;

@Path("/Employment")
public class EmploymentController {
	
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void add(@FormParam("dateFrom")String dateFrom, 
						@FormParam("dateTo")String dateTo,
						@FormParam("pwzNumber") String pwzNumber,
						@FormParam("personID")int personID, 
						@FormParam("institutionID")int institutionID,
						@FormParam("dictionaryID")int dictionaryID,
						@FormParam("specializationDictionaryID")int specializationID) {
		
		EmploymentDAO.add(
				dateFrom,
				dateTo,
				pwzNumber,
				PersonDAO.get(personID),
				InstitutionDAO.get(institutionID),
				EmploymentDictionaryDAO.get(dictionaryID),
				SpecializationDictionaryDAO.get(specializationID)
				);
		
		PersonalRoleDAO.add(
				PersonDAO.get(personID), 
				RoleDAO.get(
						EmploymentDictionaryDAO.get(dictionaryID).getName().toString()
						)
				);
	}
	
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employment> getAll() {
		
		return EmploymentDAO.getAll();
	}
	
	@Path("/allActive")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employment> getAllActive() {
		
		return EmploymentDAO.getAllActive();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/{recordID}")
	public Employment get(@PathParam("recordID") int recordID) {
		
		return EmploymentDAO.get(recordID);
		
		
		//return "<meta http-equiv=\"refresh\" content=\"5; url=..\\..\\index.html\">";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update/{employmentID}")
	public String update (
			@PathParam("employmentID") int employmentID, 
			@FormParam("dateFrom")String dateFrom, 
			@FormParam("dateTo")String dateTo,
			@FormParam("pwzNumber") String pwzNumber,
			@FormParam("personID")int personID, 
			@FormParam("institutionID")int institutionID,
			@FormParam("dictionaryID")int dictionaryID,
			@FormParam("specializationDictionaryID")int specializationID) {
		EmploymentDAO.update(
				EmploymentDAO.get(employmentID),
				dateFrom,
				dateTo,
				pwzNumber,
				PersonDAO.get(personID),
				InstitutionDAO.get(institutionID),
				EmploymentDictionaryDAO.get(dictionaryID),
				SpecializationDictionaryDAO.get(specializationID)
				);
		return "Record for personel "+employmentID+" updated";
	}
	
	
}

