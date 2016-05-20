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
	public List<Utilisateur> findResponsablePedagogiqueByName(String nom) {
		Query query = em
				.createQuery(
						"SELECT u.id FROM Utilisateur u LEFT JOIN u.parcours p WHERE u.nom= :nom AND u.role='responsable' AND p.id_parcours IS NULL")
				.setParameter("nom", nom);
		return (List<Utilisateur>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findResponsableByName(String nom) {
		Query query = em
				.createQuery(
						"SELECT u.id FROM Utilisateur u LEFT JOIN u.parcours p WHERE u.nom= :nom AND u.role='responsable'")
				.setParameter("nom", nom);
		return (List<Utilisateur>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findByResponsableParcours(int id) {
		Query query = em
				.createQuery(
						"SELECT u.id FROM Utilisateur u LEFT JOIN u.parcours p WHERE u.role ='responsable' AND p.id_parcours= :id")
				.setParameter("id", id);
		return (List<Utilisateur>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findResponsableByModuleId(int id){
		Query query = em
				.createQuery(
						"SELECT u.id FROM Utilisateur u LEFT JOIN u.modules m  WHERE m.id_module= :id AND u.role='responsable'")
				.setParameter("id", id);
		return (List<Utilisateur>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findAllEtudiants(){
		Query query = em
				.createQuery("SELECT u.id, u.nom, u.prenom, u.promotion FROM Utilisateur u WHERE u.role = 'etudiant'");
		return (List<Utilisateur>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findEtudiantById(int id){
		Query query = em
				.createQuery("SELECT u.id, u.nom, u.prenom, u.login, u.promotion, u.email, u.image, u.cv, u.lm FROM Utilisateur u WHERE u.role = 'etudiant' and u.id = :id")
		.setParameter("id", id);
		return (List<Utilisateur>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
    public List<Utilisateur> findByCriteriaAsLike(Map<String,String> critere) {
		Query query = em.createQuery(
				"SELECT u.id, u.nom, u.prenom, u.promotion from Utilisateur u WHERE u.nom like :nom AND u.prenom LIKE :prenom AND u.promotion LIKE :promotion AND u.role ='etudiant'").setParameter("nom", '%'+critere.get("nom")+'%').setParameter("prenom", '%'+critere.get("prenom")+'%').setParameter("promotion", '%'+critere.get("promotion")+'%');
		return (List<Utilisateur>) query.getResultList();

    }
	
	@SuppressWarnings("unchecked")
	public List<CV> findCVByIdEtudiant(int id){
		Query query = em
				.createQuery("SELECT cv.id_cv FROM CV cv LEFT JOIN cv.utilisateur u WHERE u.id = :id")
		.setParameter("id", id);
		return (List<CV>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<LM> findLMByIdEtudiant(int id){
		Query query = em
				.createQuery("SELECT lm.id_lm FROM LM lm LEFT JOIN lm.utilisateur u WHERE u.id = :id")
		.setParameter("id", id);
		return (List<LM>) query.getResultList();
	}
}
