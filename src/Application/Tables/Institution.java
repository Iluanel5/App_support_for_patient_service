package Application.Tables;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@Table(name = "[Institution]")
public class Institution {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Institution")
	private int institutionID;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="Postal_Code")
	private String postalCode;
	
	@Column(name="City")
	private String city;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Number_of_Register_Book")
	private String numberOfRegisterBook;

	@Column(name="Active")
	private int active;
	
	@OneToMany(mappedBy="institution",cascade = CascadeType.ALL )
	private List<Employment> employmentList = new ArrayList<Employment>();
	
	@OneToMany(mappedBy="institution",cascade = CascadeType.ALL )
	private List<Patient> patientList = new ArrayList<Patient>();

	public Institution() {
		super();
	}

	public Institution(
			String address,
			String postalCode,
			String city,
			String name,
			String numberOfRegisterBook) {
		super();
		this.address = address;
		this.postalCode = postalCode;
		this.city = city;
		this.name = name;
		this.numberOfRegisterBook = numberOfRegisterBook;
		this.active = 1;
	}

	
	public void setInstitutionID(int institutionID) {
		this.institutionID = institutionID;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getInstitutionID() {
		return institutionID;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumberOfRegisterBook() {
		return numberOfRegisterBook;
	}

	public void setNumberOfRegisterBook(String numberOfRegisterBook) {
		this.numberOfRegisterBook = numberOfRegisterBook;
	}
	
	 
	
	
}
