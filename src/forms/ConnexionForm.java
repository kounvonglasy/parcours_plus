package forms;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;

public class ConnexionForm {

	private static final String CHAMP_LOGIN = "username";
	private static final String CHAMP_PASSWORD = "password";

	public Utilisateur connecterUtilisateur(HttpServletRequest request) {

		/* Récupération des champs du formulaire */
		String login = getValeurChamp(request, CHAMP_LOGIN);
		String password = getValeurChamp(request, CHAMP_PASSWORD);
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
		EntityManager entitymanager = emfactory.createEntityManager();
		try {
			Query q = entitymanager.createQuery("SELECT u.mdp FROM Utilisateur u WHERE u.login = :login")
					.setParameter("login", login);
			List userPassword = q.getResultList();
			Utilisateur utilisateur = new Utilisateur();
			// Vérifie que l'user est bien celle dans la base de données
			if (password.equals(userPassword.get(0))) {
				utilisateur.setLogin(login);
				utilisateur.setMdp(password);
				return utilisateur;
			} else
				return null;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
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
