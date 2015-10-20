package lilas.ejb.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Utilisateur
 * 
 */
@Entity
@Table(name = "UTILISATEURS")
public class Utilisateur implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NUM_UTILISATEUR")
	private Long numUtilisateur;
	@Column(name = "NOM_UTILISATEUR", nullable = false, length = 255)
	private String nomutilisateur;
	@Column(name = "PRENOM_UTILISATEUR", nullable = false, length = 255)
	private String prenomUtilisateur;
	@Column(name = "IDENTIFIANT_UTILISATEUR", nullable = false, length = 255)
	private String identifiant;
	@Column(name = "PASSWORD_UTILISATEUR", nullable = false, length = 255)
	private String password;
	@Column(name = "MAIL_UTILISATEUR", nullable = false, length = 255)
	private String mail;
	@ManyToMany(fetch = FetchType.EAGER)
	Set<Fonction> fonctions;

	public Set<Fonction> getFonctions() {
		return fonctions;
	}

	public void setFonctions(Set<Fonction> fonctions) {
		this.fonctions = fonctions;
	}

	private static final long serialVersionUID = 1L;

	public Utilisateur() {
		super();
	}

	public Long getNumUtilisateur() {
		return this.numUtilisateur;
	}

	public void setNumUtilisateur(Long numUtilisateur) {
		this.numUtilisateur = numUtilisateur;
	}

	public String getNomutilisateur() {
		return this.nomutilisateur;
	}

	public void setNomutilisateur(String nomutilisateur) {
		this.nomutilisateur = nomutilisateur;
	}

	public String getPrenomUtilisateur() {
		return this.prenomUtilisateur;
	}

	public void setPrenomUtilisateur(String prenomUtilisateur) {
		this.prenomUtilisateur = prenomUtilisateur;
	}

	public String getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
	

