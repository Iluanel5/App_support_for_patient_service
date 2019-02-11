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
@Table(name = "[Patient]")
public class Patient {
/*
 *  ID_Patient ID_Institution ID_Person Insurance Comment
 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Patient")
	private int patientID;
	
	@Column(name="Insurance")
	private String insurance;
	
	@Column(name="Comment")
	private String comment;
	
	@ManyToOne
    @JoinColumn(name="ID_Person", nullable=false)
	private Person person;
	
	@ManyToOne
    @JoinColumn(name="ID_Institution", nullable=false)
	private Institution institution;

	@OneToMany(mappedBy="patient", orphanRemoval = true, cascade = CascadeType.ALL )
	private List<Reservation> reservationList = new ArrayList<Reservation>();
	
	

	
	public Patient() {
		super();
	}

	public Patient(
			 String insurance,
			 String comment, 
			Person person,
			Institution institution) {
		super();
		this.insurance = insurance;
		this.comment = comment;
		this.person = person;
		this.institution = institution;
	}

	
	//   Getters and setters   //
	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

}

