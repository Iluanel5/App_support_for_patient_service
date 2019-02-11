package Application.Controllers;

import java.util.Date;
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

import Application.DAOs.EmploymentDAO;
import Application.DAOs.PatientDAO;
import Application.DAOs.PersonDAO;
import Application.DAOs.ReservationDAO;
import Application.Tables.Reservation;

@Path("/Reservation")
public class ReservationController {
	
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String add(@FormParam("dateFrom")String dateFrom, 
						@FormParam("dateTo")String dateTo,
						@FormParam("patientID")int patientID, 
						@FormParam("employmentID")int employmentID) {
		if(ReservationDAO.add(
				dateFrom,
				dateTo,
				PatientDAO.get(patientID),
				EmploymentDAO.get(employmentID)
				)) {
			return "{\"res\":\"ok\"}";
		} else {
			return "{\"res\":\"nope\"}";
		}
	}
	
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Reservation> getAll() {
		
		return ReservationDAO.getAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/{reservationID}")
	public Reservation get (@PathParam("reservationID") int reservationID) {
		
		return ReservationDAO.get(reservationID);
		
		
		//return "<meta http-equiv=\"refresh\" content=\"5; url=..\\..\\index.html\">";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getByData")
	public List<Reservation> getByData (
			@FormParam("employmentID") int employmentID, 
			@FormParam("dateFrom")String dateFrom, 
			@FormParam("dateTo")String dateTo) {
		return ReservationDAO.get(dateFrom, dateTo, employmentID);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllForEmployee/{employmentID}")
	public List<Reservation> getAllForEmployee(@PathParam("employmentID") int employmentID) {
		
		return ReservationDAO.getAllForEmployee(EmploymentDAO.get(employmentID));
		
		
		//return "<meta http-equiv=\"refresh\" content=\"5; url=..\\..\\index.html\">";
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("delete/{reservationID}")
	public String delete(@PathParam("reservationID") int reservationID) {
		ReservationDAO.delete(reservationID);
		return "{\"res\":\"ok\"}";
	}
}

