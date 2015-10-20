package lilas.ejb.session;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lilas.ejb.entity.Facturation;
@Stateless
public class FactureFacade extends AbstractFacade<Facturation> {
@PersistenceContext
private EntityManager em;
	public FactureFacade() {
		super(Facturation.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	public Collection<? extends Facturation> listeFactureImpaye() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object supprimer(Facturation ft) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<? extends Facturation> listeFacturePaye() {
		// TODO Auto-generated method stub
		return null;
	}

}
