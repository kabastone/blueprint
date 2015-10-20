package lilas.ejb.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Fonction
 *
 */
@Entity
@Table(name="FONCTION")
public class Fonction implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NUM_FONCTION")
	private Long numFonction;
	@Column(name = "LIBELLE_FONCTION",nullable=false,length=255)
	private String libelleFonction;
	
	private static final long serialVersionUID = 1L;
	@ManyToMany(fetch=FetchType.EAGER)
	private Set <Role> roles;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set <Fonctionnalite> fonctionnalites;
	public Set<Role> getRoles() {
		return roles;
	}
	public Set<Fonctionnalite> getFonctionnalites() {
		return fonctionnalites;
	}
	public void setFonctionnalites(Set<Fonctionnalite> fonctionnalites) {
		this.fonctionnalites = fonctionnalites;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	

	public Fonction() {
		super();
	}   
	public Long getNumFonction() {
		return this.numFonction;
	}

	public void setNumFonction(Long numFonction) {
		this.numFonction = numFonction;
	}   
	public String getLibelleFonction() {
		return this.libelleFonction;
	}

	public void setLibelleFonction(String libelleFonction) {
		this.libelleFonction = libelleFonction;
	}
   
}
