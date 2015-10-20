package lilas.ejb.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Acces
 *
 */
@Entity
@Table(name="ACCES")
public class Acces implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NUM_ACCES")
	private Long numacces;
	@Column(name="LIBELLE_ACCES",nullable=false,length=255)
	private String libelleAcces;
	@Column(name="URL_ACCES",nullable=false,length=255)
	private String urlAcces;
		
		private static final long serialVersionUID = 1L;

	public Acces() {
		super();
	}   
	public Long getNumacces() {
		return this.numacces;
	}

	public void setNumacces(Long numacces) {
		this.numacces = numacces;
	}   
	public String getLibelleAcces() {
		return this.libelleAcces;
	}

	public void setLibelleAcces(String libelleAcces) {
		this.libelleAcces = libelleAcces;
	}   
	public String getUrlAcces() {
		return this.urlAcces;
	}

	public void setUrlAcces(String urlAcces) {
		this.urlAcces = urlAcces;
	}
   
}
