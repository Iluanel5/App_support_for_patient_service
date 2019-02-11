package Application.Tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

@XmlRootElement
@Entity
@Table(name = "[Reservation]")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Reservation")
	private int reservationID;
	
	@Column(name="Date_From")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date dateFrom;
	
	@Column(name="Date_To")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date dateTo;
	
	@ManyToOne
    @JoinColumn(name="ID_Patient", nullable=false)
	private Patient patient;
	
	@ManyToOne
    @JoinColumn(name="ID_Employment", nullable=false)
	private Employment personel;

	@OneToMany(mappedBy="reservation", orphanRemoval = true, cascade = CascadeType.ALL )
	private List<Visit> visitList = new ArrayList<Visit>();
	
	
	
	
	
	public Reservation() {
		super();
	}

	public Reservation(Date dateFrom, Date dateTo, Patient patient, Employment personel) {
		super();
		//this.reservationID = reservationID;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.patient = patient;
		this.personel = personel;
	}

	//Getters and setters//
	public int getReservationID() {
		return reservationID;
	}

	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Employment getPersonel() {
		return personel;
	}

	public void setPersonel(Employment personel) {
		this.personel = personel;
	}
	
	
	
}

