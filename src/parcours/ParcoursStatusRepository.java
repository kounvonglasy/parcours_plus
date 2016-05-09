package parcours;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import beans.ParcoursStatus;
import beans.Status;

public class ParcoursStatusRepository {
	protected EntityManager em;

	public ParcoursStatusRepository(EntityManager em) {
		this.em = em;
	}
	
	public List<Status> findAllStatus() {
		Query query = em.createQuery(
				"SELECT s.libelle FROM Status s");
		return (List<Status>) query.getResultList();
	}
	
	public List<ParcoursStatus> findAllParcoursStatus() {
		Query query = em.createQuery(
				"SELECT u.id, p.libelle, st.libelle, u.nom, ps.priorite_choix_parcours FROM ParcoursStatus ps LEFT JOIN ps.utilisateur u LEFT JOIN ps.parcours p LEFT JOIN ps.status st WHERE u.role<>'responsable' and st.libelle NOT IN ('Accepté','Réfusé') and u.id NOT IN (SELECT uu.id FROM ParcoursStatus pss  LEFT JOIN pss.utilisateur uu WHERE pss.status.id_status IN (2))");
		return (List<ParcoursStatus>) query.getResultList();
	}
	
	public List<ParcoursStatus> findParcoursStatusByIdAndParcours(int id_utilisateur, String libelle_parcours) {
		Query query = em.createQuery(
				"SELECT ps.id_parcours_status FROM ParcoursStatus ps LEFT JOIN ps.utilisateur u LEFT JOIN ps.parcours p WHERE u.id=:id_utilisateur and p.libelle=:libelle")
				.setParameter("id_utilisateur", id_utilisateur).setParameter("libelle", libelle_parcours);
		return (List<ParcoursStatus>) query.getResultList();
	}
	
	public List<Status> findStatusByLibelle(String libelle_status) {
		Query query = em.createQuery(
				"SELECT s.id_status FROM Status s WHERE s.libelle =:libelle_status")
				.setParameter("libelle_status", libelle_status);
		return (List<Status>) query.getResultList();
	}


}
