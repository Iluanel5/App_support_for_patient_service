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
@Table(name = "[Specialization_Dictionary]")
public class SpecializationDictionary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_S_Dictionary")
	private int dictionaryID;
	
	@Column(name="Name")
    //@Length(max=120, message="The Name can have up to 120 characters")
	private String name;

	@Column(name="Active")
	private int active; // jak 0 to u¿ytkownik jest nieaktywny

	@OneToMany(mappedBy="specialization",cascade = CascadeType.ALL )
	private List<Employment> employmentList = new ArrayList<>();

	
	public SpecializationDictionary() {
		super();
	}

	public SpecializationDictionary(int dictionaryID,
			 String name) {
		super();
		this.dictionaryID = dictionaryID;
		this.name = name;
		this.active = 1;
	}

	
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
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
	
	
	
}