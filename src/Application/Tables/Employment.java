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
@Table(name = "[Employment]")
public class Employment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Employment")
	private int employmentID;
	
	@Column(name="Date_From")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dateFrom;
	
	@Column(name="PWZ_Number")
	private String pwzNumber;
	
	@Column(name="Date_To")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dateTo;

	@ManyToOne
    @JoinColumn(name="ID_Person", nullable=false)
	private Person person;
	
	@ManyToOne
    @JoinColumn(name="ID_Institution", nullable=false)
	private Institution institution;
	
	@ManyToOne
    @JoinColumn(name="ID_E_Dictionary", nullable=false)
	private EmploymentDictionary dictionary;
	
	@ManyToOne
    @JoinColumn(name="ID_S_Dictionary", nullable=false)
	private SpecializationDictionary specialization;

	@OneToMany(mappedBy="personel", orphanRemoval = true, cascade = CascadeType.ALL )
	private List<Reservation> reservationList = new ArrayList<Reservation>();
	
	public Employment() {
		super();
	}

	public Employment(
			Date dateFrom, 
			Date dateTo, 
			String pwzNumber,
			Person person, 
			Institution institution,
			EmploymentDictionary employmentDictionary,
			SpecializationDictionary specialization) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.pwzNumber=pwzNumber;
		this.person = person;
		this.institution = institution;
		this.dictionary = employmentDictionary;
		this.specialization = specialization;
	}



	public void setEmploymentID(int employmentID) {
		this.employmentID = employmentID;
	}

	
	
	
	public String getPwzNumber() {
		return pwzNumber;
	}

	public void setPwzNumber(String pwzNumber) {
		this.pwzNumber = pwzNumber;
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

	public EmploymentDictionary getEmploymentDictionary() {
		return this.dictionary;
	}

	public void setSpecialization(SpecializationDictionary specialization) {
		this.specialization = specialization;
	}
	
	public SpecializationDictionary getSpecialization() {
		return this.specialization;
	}

	public void setEmploymentDictionary(EmploymentDictionary employmentDictionary) {
		this.dictionary = employmentDictionary;
	}

	public int getEmploymentID() {
		return employmentID;
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
}

