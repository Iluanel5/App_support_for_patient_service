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
@Table(name = "[Person]")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Person")
	private int personID;
	
	@Column(name="First_Name")
	private String firstName;
	
	@Column(name="Last_Name")
	private String lastName;
	
	@Column(name="Pesel")
	private String pesel;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Second_Name")
	private String secondName;
	
	@Column(name="Phone_Number")
	private String phoneNumber;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="Postal_Code")
	private String postalCode;
	
	@Column(name="City")
	private String city;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Active")
	private int active; // jak 0 to u¿ytkownik jest nieaktywny
	
	
	@OneToMany(mappedBy="person", orphanRemoval = true, cascade = CascadeType.ALL )
	private List<Employment> employmentList = new ArrayList<Employment>();
	
	@OneToMany(mappedBy="person", orphanRemoval = true, cascade = CascadeType.ALL )
	private List<Patient> patientList = new ArrayList<Patient>();
	
	@OneToMany(mappedBy="person", orphanRemoval = true, cascade = CascadeType.ALL )
	private List<PersonalRole> personalRoleList = new ArrayList<PersonalRole>();

	public Person() {
		super();
	}
	
	

	

	public Person(String firstName, String lastName, String pesel, String email, String secondName,
			String phoneNumber, String address, String postalCode, String city, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.pesel = pesel;
		this.email = email;
		this.secondName = secondName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.postalCode = postalCode;
		this.city = city;
		this.password = password;
		this.active = 1;
	}




	// Getters and Setters //
	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
}
