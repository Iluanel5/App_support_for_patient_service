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
@Table(name = "[ICD-10]")
public class ICD_10 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_ICD")
	private int icdID;
	
	@Column(name="Mark")
	private String mark;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Active")
	private int active;
	
	@OneToMany(mappedBy="icd",cascade = CascadeType.ALL )
	private List<Visit> visitList = new ArrayList<Visit>();
	
	
	
	public ICD_10() {
		super();
	}

	public ICD_10(String mark,
			String description) {
		super();
		this.mark = mark;
		this.description = description;
		this.active = 1;
	}


	public int getIcdID() {
		return icdID;
	}

	public void setIcdID(int icdID) {
		this.icdID = icdID;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	
}
