package Application.Controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Application.DAOs.ICD_10_DAO;
import Application.DAOs.ReservationDAO;
import Application.DAOs.VisitDAO;
import Application.Tables.Visit;


@Path("/Visit")
public class VisitController {

	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void add(
			@FormParam("comment")String comment, 
			@FormParam("reservationID")int reservationID, 
			@FormParam("icdID")int icdID) {
		VisitDAO.add(
				comment,
				ReservationDAO.get(reservationID),
				ICD_10_DAO.get(icdID)
				);
		
	}
	
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Visit> getAll() {
		
		return VisitDAO.getAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/{visitID}")
	public Visit get (@PathParam("visitID") int visitID) {
		
		return VisitDAO.get(visitID);
		
		
		//return "<meta http-equiv=\"refresh\" content=\"5; url=..\\..\\index.html\">";
	}
	
}
