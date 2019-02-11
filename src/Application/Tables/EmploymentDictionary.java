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
@Table(name = "[Employment_Dictionary]")
public class EmploymentDictionary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_E_Dictionary")
	private int dictionaryID;
	
	@Column(name="Name")
	private String name;

	@Column(name="Active")
	private int active; // jak 0 to u¿ytkownik jest nieaktywny

	@OneToMany(mappedBy="dictionary",cascade = CascadeType.ALL )
	private List<Employment> employmentList = new ArrayList<>();

	
	public EmploymentDictionary() {
		super();
	}

	public EmploymentDictionary(int dictionaryID,
			String name) {
		super();
		this.dictionaryID = dictionaryID;
		this.name = name;
		this.active = 1;
	}

	
	


	public void setDictionaryID(int dictionaryID) {
		this.dictionaryID = dictionaryID;
	}

	public int getDictionaryID() {
		return dictionaryID;
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

