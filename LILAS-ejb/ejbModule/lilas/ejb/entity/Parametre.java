package lilas.ejb.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Paremetre
 *
 */
@Entity

public class Parametre implements Serializable {

	   
	@Id
	private int id;
	private String logo;
	private String telephone;
	private String adresse,rc,ninea;
	private static final long serialVersionUID = 1L;

	public Parametre() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}   
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}   
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getRc() {
		return rc;
	}
	public void setRc(String rc) {
		this.rc = rc;
	}
	public String getNinea() {
		return ninea;
	}
	public void setNinea(String ninea) {
		this.ninea = ninea;
	}
   
}
