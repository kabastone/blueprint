package lilas.ejb.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Fonctionnalite
 *
 */
@Entity
@Table(name="FONCTIONNALITES")
public class Fonctionnalite implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FCTLTE")
	private Long id;
	@Column(name="NAME_FCTLTE")
	private String name;
	@Column(name="STATUS_FCTLTE")
	private String status;
	
	
	
	
	private static final long serialVersionUID = 1L;

	public Fonctionnalite() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
   
}
