package lilas.ejb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "FACTURATION")
public class Facturation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Set<Prestation> getPrestations() {
		return prestations;
	}

	public void setPrestations(Set<Prestation> prestations) {
		this.prestations = prestations;
	}

	public Facturation() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NUM_FACTURE")
	private Long numFacture;
	@Column(name = "CLIENT", nullable = false, length = 255)
	private String client;
	@Column(name = "ENTREE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date entree;
	@Column(name = "SORTIE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date sortie;
	@Column(name = "CHAMBRE", nullable = false)
	private String numChambre;
	@Column(name = "NBRE_PERS", nullable = false)
	private Integer nbrePers;
	@Column(name = "MONTANT_HT")
	private Double montantHT;
	@Column(name = "MONTANT_TVA")
	private Double montantTVA;
	@Column(name = "MONTANT_TTC")
	private Double montantTTC;
	@Column(name = "TAXE_SEJOUR")
	private Double taxeSejour;
	@Column(name = "TIMBRE")
	private Double timbre;
	@Column(name = "MONTANT_LETTRE", nullable = false, length = 255)
	private String montantLettre;
	@Column(name = "REGLEMENT")
	private String modePaie;
	@Column(name = "TELEPHONE")
	private String telephone;
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	Set<Prestation> prestations;
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Long getNumFacture() {
		return numFacture;
	}

	public void setNumFacture(Long numFacture) {
		this.numFacture = numFacture;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Date getEntree() {
		return entree;
	}

	public void setEntree(Date entree) {
		this.entree = entree;
	}

	public Date getSortie() {
		return sortie;
	}

	public void setSortie(Date sortie) {
		this.sortie = sortie;
	}

	public String getNumChambre() {
		return numChambre;
	}

	public void setNumChambre(String numChambre) {
		this.numChambre = numChambre;
	}

	public Integer getNbrePers() {
		return nbrePers;
	}

	public void setNbrePers(Integer nbrePers) {
		this.nbrePers = nbrePers;
	}

	public Double getMontantHT() {
		return montantHT;
	}

	public void setMontantHT(Double montantHT) {
		this.montantHT = montantHT;
	}

	public Double getMontantTVA() {
		return montantTVA;
	}

	public void setMontantTVA(Double montantTVA) {
		this.montantTVA = montantTVA;
	}

	public Double getMontantTTC() {
		return montantTTC;
	}

	public void setMontantTTC(Double montantTTC) {
		this.montantTTC = montantTTC;
	}

	public Double getTaxeSejour() {
		return taxeSejour;
	}

	public void setTaxeSejour(Double taxeSejour) {
		this.taxeSejour = taxeSejour;
	}

	public Double getTimbre() {
		return timbre;
	}

	public void setTimbre(Double timbre) {
		this.timbre = timbre;
	}

	public String getMontantLettre() {
		return montantLettre;
	}

	public void setMontantLettre(String montantLettre) {
		this.montantLettre = montantLettre;
	}

	public String getModePaie() {
		return modePaie;
	}

	public void setModePaie(String modePaie) {
		this.modePaie = modePaie;
	}

}
