package profile;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import beans.Utilisateur;


public class ProfileRepository {
	
	protected EntityManager em;

	public ProfileRepository(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<Utilisateur> findAllProfile() {
		Query query = em.createQuery(
				"SELECT u.nom, u.prenom, u.login, p.id_parcours FROM Utilisateur u LEFT JOIN u.parcours p WHERE u.role ='responsable' AND p.id_parcours IS NOT NULL");
		return (List<Utilisateur>) query.getResultList();
	}

}
