package Application.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Application.DAOs.ICD_10_DAO;
import Application.Tables.ICD_10;

@Path("/ICD_10")
public class ICD_10_Controller {
	
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ICD_10> getAll() {
		
		return ICD_10_DAO.getAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/{icd_10ID}")
	public ICD_10 get (@PathParam("icd_10ID") int icd_10ID) {
		
		return ICD_10_DAO.get(icd_10ID);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/changeActive/{icd_10ID}")
	public void changeActive (
			@PathParam("icd_10ID") int icd_10ID) {
		ICD_10_DAO.changeActive(ICD_10_DAO.get(icd_10ID));
	}
	
	@Path("/xml")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void uploadXML() {
		//ICD_10_DAO.uploadXmlFile("C:\\inzynierka\\ICD10_PL_en.xml");
	}
	
}
