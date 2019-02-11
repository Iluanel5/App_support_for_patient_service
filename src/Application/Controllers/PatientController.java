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
import Application.DAOs.PatientDAO;
import Application.DAOs.PersonDAO;
import Application.DAOs.PersonalRoleDAO;
import Application.DAOs.RoleDAO;
import Application.Tables.Patient;

@Path("/Patient")
public class PatientController {

	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void add(@FormParam("insurance")String insurance, 
						@FormParam("comment")String comment,
						@FormParam("personID")int personID, 
						@FormParam("institutionID")int institutionID) {
		PatientDAO.add(
				insurance,
				comment,
				PersonDAO.get(personID),
				InstitutionDAO.get(institutionID)
				);
		PersonalRoleDAO.add(PersonDAO.get(personID), RoleDAO.get("Patient"));
	}
	
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Patient> getAll() {
		
		return PatientDAO.getAll();
	}
	
	@Path("/allActive")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Patient> getAllActive() {
		
		return PatientDAO.getAllActive();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/{patientID}")
	public Patient get (@PathParam("patientID") int patientID) {
		
		return PatientDAO.get(patientID);
		
		
		//return "<meta http-equiv=\"refresh\" content=\"5; url=..\\..\\index.html\">";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getForPerson/{personID}")
	public Patient getForPerson(@PathParam("personID") int personID) {
		
		List<Patient> list = PatientDAO.getAllActive();
		
		return PatientDAO.getForPerson(personID, list);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update/{patientID}")
	public void updateRecord (
			@PathParam("patientID") int patientID, 
			@FormParam("insurance")String insurance, 
			@FormParam("comment")String comment,
			@FormParam("personID")int personID, 
			@FormParam("institutionID")int institutionID) {
		PatientDAO.update(
				PatientDAO.get(patientID),
				insurance, 
				comment,
				PersonDAO.get(personID), 
				InstitutionDAO.get(institutionID)
				);
		
	}
	
}

