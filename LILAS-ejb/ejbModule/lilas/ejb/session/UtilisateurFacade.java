package lilas.ejb.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lilas.ejb.entity.Utilisateur;
@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> {
	@PersistenceContext
	private EntityManager em;

	public UtilisateurFacade() {
		super(Utilisateur.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	public Utilisateur findByLogin(String l) {
		Query query = null;
		Utilisateur u = new Utilisateur();
		try {
			query = em.createQuery("FROM Utilisateur u WHERE u.identifiant =?")
					.setParameter(1, l);
			u = (Utilisateur) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return u;
	}

}
