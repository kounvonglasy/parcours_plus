package ldap;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;
import error.ErrorManager;
import utilisateur.UtilisateurRepository;

public class LDAPConnection extends ErrorManager {
	private EntityManager em;
	private static final String CHAMP_LOGIN = "username";
	private static final String CHAMP_PASS = "password";
	private UtilisateurRepository utilisateur_repository;
	private String nom;
	private String prenom;
	private String type;
	private String email;
	private String password;

	public LDAPConnection(EntityManager em) {
		this.em = em;
		utilisateur_repository = new UtilisateurRepository(em);

	}

	public Utilisateur connecterUtilisateur(HttpServletRequest request) {
		LDAPaccess access = new LDAPaccess();

		/* Récupération des champs du formulaire */
		String login = getValeurChamp(request, CHAMP_LOGIN);
		String mdp = getValeurChamp(request, CHAMP_PASS);

		try {
			LDAPObject connexion = access.LDAPget(login, mdp);
			if (connexion == null) {
				throw new Exception("Login invalide");
			}
			type = connexion.getType();
			nom = connexion.getNomFamille();
			prenom = connexion.getPrenom();
			password = connexion.getPassword();		
			email = connexion.getMail();

		} catch (Exception e) {
			setErreur(CHAMP_LOGIN, e.getMessage());
			return null;
		}
		// Si la connexion s'est bien passé,retourne le bean
		Utilisateur utilisateur;
		try{//l'user existe dans la base
		List<Utilisateur> liste_utilisateur = utilisateur_repository.findByLogin(login);
		utilisateur = em.find(Utilisateur.class, liste_utilisateur);
		}catch (Exception e){//l'utilisateur existe dans l'annuaire LDAP mais n'est pas dans la base
			utilisateur = new Utilisateur();
			utilisateur.setLogin(login);
			utilisateur.setMdp(mdp);
			utilisateur.setPrenom(prenom);
			utilisateur.setNom(nom);
			utilisateur.setMdp(password);
			utilisateur.setEmail(email);
			utilisateur.setRole(type);
			em.getTransaction().begin();
			em.persist(utilisateur);
			em.getTransaction().commit();
		}
		return utilisateur;

	}

	/*
	 * 
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * 
	 * sinon.
	 * 
	 */
	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}
}