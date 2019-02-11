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
@Table(name = "[Role]")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Role")
	private int roleID;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Active")
	private int active; // jak 0 to u¿ytkownik jest nieaktywny

	@OneToMany(mappedBy="role",cascade = CascadeType.ALL )
	private List<PersonalRole> roleList = new ArrayList<PersonalRole>();

	
	public Role() {
		super();
	}

	public Role(String name) {
		super();
		this.name = name;
		this.active = 1;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
}
