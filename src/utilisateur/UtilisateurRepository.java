package utilisateur;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import beans.CV;
import beans.LM;
import beans.Utilisateur;

public class UtilisateurRepository {
	protected EntityManager em;

	public UtilisateurRepository(EntityManager em) {
		this.em = em;
	}
	

	@SuppressWarnings("unchecked")
	public List<Utilisateur> findByLogin(String login) {
		Query q = em.createQuery("SELECT u.id FROM Utilisateur u WHERE u.login = :login").setParameter("login", login);
		return (List<Utilisateur>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findResponsableByName(String nom) {
		Query query = em
				.createQuery(
						"SELECT u.id FROM Utilisateur u LEFT JOIN u.parcours p WHERE u.nom= :nom AND (u.role='prof' OR u.role='administration')")
				.setParameter("nom", nom);
		return (List<Utilisateur>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findResponsableParcours(int id) {
		Query query = em
				.createQuery(
						"SELECT u.id FROM Utilisateur u LEFT JOIN u.parcours p WHERE u.role ='prof' AND p.id_parcours= :id")
				.setParameter("id", id);
		return (List<Utilisateur>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findByModuleId(int id){
		Query query = em
				.createQuery(
						"SELECT u.id FROM Utilisateur u LEFT JOIN u.modules m  WHERE m.id_module= :id")
				.setParameter("id", id);
		return (List<Utilisateur>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findAllEtudiants(){
		Query query = em
				.createQuery("SELECT u.id, u.nom, u.prenom, u.promotion FROM Utilisateur u WHERE u.role = 'eleve'");
		return (List<Utilisateur>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findAllResponsablesParcours(){
		Query query = em
				.createQuery("SELECT u FROM Utilisateur u WHERE u.role = 'prof'");
		return (List<Utilisateur>) query.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
    public List<Utilisateur> findByCriteriaAsLike(Map<String,String> critere) {
		Query query = em.createQuery(
				"SELECT u.id, u.nom, u.prenom, u.promotion from Utilisateur u LEFT JOIN u.promotion p WHERE u.nom like :nom AND u.prenom LIKE :prenom AND p.promotion LIKE :promotion AND u.role ='eleve'").setParameter("nom", '%'+critere.get("nom")+'%').setParameter("prenom", '%'+critere.get("prenom")+'%').setParameter("promotion", '%'+critere.get("promotion")+'%');
		return (List<Utilisateur>) query.getResultList();

    }
	
	@SuppressWarnings("unchecked")
	public List<CV> findCVById(int id){
		Query query = em
				.createQuery("SELECT cv.id_cv FROM CV cv LEFT JOIN cv.utilisateur u WHERE u.id = :id")
		.setParameter("id", id);
		return (List<CV>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<LM> findLMById(int id){
		Query query = em
				.createQuery("SELECT lm.id_lm FROM LM lm LEFT JOIN lm.utilisateur u WHERE u.id = :id")
		.setParameter("id", id);
		return (List<LM>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
    public List<Utilisateur> findIdByEmail(String email) {
		Query query = em.createQuery(
				"SELECT u.id from Utilisateur u WHERE u.email = :email").setParameter("email", email);
		return (List<Utilisateur>) query.getResultList();

    }
}
