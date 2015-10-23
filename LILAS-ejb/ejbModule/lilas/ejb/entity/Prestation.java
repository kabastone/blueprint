package lilas.ejb.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="PRESTATIONS")
public class Prestation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NUM_PRESTATION")
	private Long numPrestation;
	@Column(name="LIBELLE", nullable=false, length=255)
	private String Libelle;
	@Column(name="MONTANT_PRSTATION",nullable=false)
	private Double montantPres;
    
	@ManyToOne(cascade=CascadeType.ALL)
	private Remise remise;

	public Prestation() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Long getNumPrestation() {
		return numPrestation;
	}

	public void setNumPrestation(Long numPrestation) {
		this.numPrestation = numPrestation;
	}

	public String getLibelle() {
		return Libelle;
	}

	public void setLibelle(String libelle) {
		Libelle = libelle;
	}

	public Double getMontantPres() {
		return montantPres;
	}

	public void setMontantPres(Double montantPres) {
		this.montantPres = montantPres;
	}

	public Remise getRemise() {
		return remise;
	}

	public void setRemise(Remise remise) {
		this.remise = remise;
	}

}
