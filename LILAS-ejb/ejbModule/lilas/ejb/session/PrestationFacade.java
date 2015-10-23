package lilas.ejb.session;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	public List<Prestation> findPrestation() {
		Query query = null;
		 query = em.createQuery("From Prestation p");
		return query.getResultList();
	}

}
