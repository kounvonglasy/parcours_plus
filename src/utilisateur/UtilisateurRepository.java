package utilisateur;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
				.createQuery("SELECT u.id, u.nom, u.prenom FROM Utilisateur u WHERE u.role = 'etudiant'");
		return (List<Utilisateur>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findEtudiantById(int id){
		Query query = em
				.createQuery("SELECT u.id, u.nom, u.prenom, u.login, u.promotion, u.email, u.image, u.cv, u.lm FROM Utilisateur u WHERE u.role = 'etudiant' and u.id = :id")
		.setParameter("id", id);
		return (List<Utilisateur>) query.getResultList();
	}
	
}
