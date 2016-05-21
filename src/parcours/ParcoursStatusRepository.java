package parcours;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import beans.ParcoursStatus;
import beans.Status;

public class ParcoursStatusRepository {
	protected EntityManager em;

	public ParcoursStatusRepository(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<Status> findAllStatus() {
		Query query = em.createQuery("SELECT s.libelle FROM Status s");
		return (List<Status>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Status> findStatusByLibelle(String libelle_status) {
		Query query = em.createQuery("SELECT s.id_status FROM Status s WHERE s.libelle =:libelle_status")
				.setParameter("libelle_status", libelle_status);
		return (List<Status>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ParcoursStatus> findAllParcoursStatusByIdUser(int id_utilisateur) {
		Query query = em
				.createQuery(
						"SELECT u.id, p.libelle, st.libelle, u.nom, ps.priorite_choix_parcours FROM ParcoursStatus ps LEFT JOIN ps.utilisateur u LEFT JOIN ps.parcours p LEFT JOIN ps.status st WHERE u.role<>'responsable' and p.id_parcours IN (SELECT rp.id_parcours FROM Utilisateur responsable LEFT JOIN responsable.parcours rp WHERE responsable.id=:id_utilisateur) and st.libelle NOT IN ('Accepté','Réfusé') and u.id NOT IN (SELECT uu.id FROM ParcoursStatus pss  LEFT JOIN pss.utilisateur uu WHERE pss.status.id_status IN (2)) ORDER BY ps.priorite_choix_parcours")
				.setParameter("id_utilisateur", id_utilisateur);
		return (List<ParcoursStatus>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> findAllParcoursStatusByIdEtudiant(int id_utilisateur) {
		Query query = em
				.createQuery(
						"SELECT ps.id_parcours_status FROM ParcoursStatus ps LEFT JOIN ps.utilisateur u WHERE u.id = :id_utilisateur")
				.setParameter("id_utilisateur", id_utilisateur);
		return (List<Integer>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ParcoursStatus> findParcoursStatusByIdAndLibelleParcours(int id_utilisateur, String libelle_parcours) {
		Query query = em
				.createQuery(
						"SELECT ps.id_parcours_status FROM ParcoursStatus ps LEFT JOIN ps.utilisateur u LEFT JOIN ps.parcours p WHERE u.id=:id_utilisateur and p.libelle=:libelle")
				.setParameter("id_utilisateur", id_utilisateur).setParameter("libelle", libelle_parcours);
		return (List<ParcoursStatus>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ParcoursStatus> findParcoursStatusByIdAndIdParcours(int id_utilisateur, int id_parcours) {
		Query query = em
				.createQuery(
						"SELECT ps.id_parcours_status FROM ParcoursStatus ps LEFT JOIN ps.utilisateur u LEFT JOIN ps.parcours p WHERE u.id=:id_utilisateur and p.id_parcours=:id_parcours")
				.setParameter("id_utilisateur", id_utilisateur).setParameter("id_parcours", id_parcours);
		return (List<ParcoursStatus>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
    public List<ParcoursStatus> findByCriteriaAsLike(Map<String,String> critere, int id_responsable) {
		Query query = em.createQuery(
				"SELECT u.id, p.libelle, st.libelle, u.nom, ps.priorite_choix_parcours FROM ParcoursStatus ps LEFT JOIN ps.utilisateur u LEFT JOIN ps.parcours p LEFT JOIN ps.status st WHERE u.role<>'responsable' and p.id_parcours IN (SELECT rp.id_parcours FROM Utilisateur responsable LEFT JOIN responsable.parcours rp WHERE responsable.id = :id_responsable) and st.libelle NOT IN ('Accepté','Réfusé') and u.id NOT IN (SELECT uu.id FROM ParcoursStatus pss  LEFT JOIN pss.utilisateur uu WHERE pss.status.id_status IN (2)) AND u.nom LIKE :nom_etudiant AND CAST(ps.priorite_choix_parcours as CHAR) LIKE :priorite")
				.setParameter("id_responsable", id_responsable).setParameter("nom_etudiant", '%'+critere.get("nom_etudiant")+'%').setParameter("priorite", '%'+critere.get("priorite")+'%');
			return (List<ParcoursStatus>) query.getResultList();

    }
	
}
