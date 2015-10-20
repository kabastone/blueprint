package lilas.ejb.session;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lilas.ejb.entity.Prestation;
@Stateless
public class PrestationFacade extends AbstractFacade<Prestation> {
@PersistenceContext
private EntityManager em;
	public PrestationFacade() {
		super(Prestation.class);
		
	}

	@Override
	protected EntityManager getEntityManager() {
				return em;
	}

	public Collection<? extends Prestation> findPrestation() {
		// TODO Auto-generated method stub
		return null;
	}

}
