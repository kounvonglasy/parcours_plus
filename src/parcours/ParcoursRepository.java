package parcours;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import beans.Parcours;

public class ParcoursRepository {
	protected EntityManager em;

	public ParcoursRepository(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Parcours> findAllParcours() {
		Query query = em.createQuery(
				"SELECT p.id_parcours, p.libelle, u.nom FROM Utilisateur u LEFT JOIN u.parcours p WHERE u.role ='responsable' AND p.id_parcours IS NOT NULL");
		return (List<Parcours>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Parcours> findParcoursByLibelle(String libelle) {
		Query query = em.createQuery("SELECT p.id_parcours FROM Parcours p WHERE p.libelle= :libelle")
				.setParameter("libelle", libelle);
		return (List<Parcours>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
    public List<Parcours> findByCriteriaAsLike(Map<String,String> critere) {
		Query query = em.createQuery(
				"SELECT p.id_parcours, p.libelle, u.nom FROM Utilisateur u LEFT JOIN u.parcours p WHERE u.nom like :nom AND p.libelle LIKE :libelle AND u.role='responsable'").setParameter("libelle", '%'+critere.get("libelle")+'%').setParameter("nom", '%'+critere.get("responsable")+'%');
		return (List<Parcours>) query.getResultList();

    }

}
