package lilas.ejb.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lilas.ejb.entity.Parametre;


@Stateless
public class ParametreFacade extends AbstractFacade<Parametre>{
@PersistenceContext
private EntityManager em;
	public ParametreFacade() {
		super(Parametre.class);
		
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

}
