package lilas.ejb.entity;

import java.io.Serializable;
import java.lang.Long;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Remise
 *
 */
@Entity
public class Remise implements Serializable {

	@Id
	@Column(name="POURCENTAGE_REMISE")
	private int pourcentage;
	private static final long serialVersionUID = 1L;

	public Remise() {
		super();
	}   
	public int getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}
   
}
