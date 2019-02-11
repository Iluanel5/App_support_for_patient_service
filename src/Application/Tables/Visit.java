package Application.Tables;

import java.util.ArrayList;
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


@XmlRootElement
@Entity
@Table(name = "[Visit]")
public class Visit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Visit")
	private int visitID;
	
	@Column(name="Comment")
	private String comment;
	
	@ManyToOne
    @JoinColumn(name="ID_Reservation", nullable=false)
	private Reservation reservation;
	
	@ManyToOne
    @JoinColumn(name="ID_ICD", nullable=false)
	private ICD_10 icd;

	

	 
	 
	
	
	public Visit() {
		super();
	}

	public Visit(String comment,
			Reservation reservation, ICD_10 icd) {
		super();
		this.comment = comment;
		this.reservation = reservation;
		this.icd = icd;
	}

	public int getVisitID() {
		return visitID;
	}

	public void setVisitID(int visitID) {
		this.visitID = visitID;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public ICD_10 getIcd() {
		return icd;
	}

	public void setIcd(ICD_10 icd) {
		this.icd = icd;
	}
	
	
}
