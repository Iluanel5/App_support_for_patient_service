package Application.Tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "[Personal_Role]")
public class PersonalRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Personal_Role")
	private int personalRoleID;
	
	@ManyToOne
    @JoinColumn(name="ID_Person", nullable=false)
	private Person person;
	
	@ManyToOne
    @JoinColumn(name="ID_Role", nullable=false)
	private Role role;

	
	
	public PersonalRole() {
		super();
	}

	public PersonalRole(Person person, Role role) {
		super();
		this.person = person;
		this.role = role;
	}

	public int getPersonalRoleID() {
		return personalRoleID;
	}

	public void setPersonalRoleID(int personalRoleID) {
		this.personalRoleID = personalRoleID;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
