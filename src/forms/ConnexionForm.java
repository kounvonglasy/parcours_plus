package forms;

import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;
import error.ErrorManager;
import utilisateur.UtilisateurRepository;

public class ConnexionForm extends ErrorManager {

	private static final String CHAMP_LOGIN = "username";
	private static final String CHAMP_PASSWORD = "password";

	protected EntityManager em;
	private UtilisateurRepository utilisateur_repository;
	private Utilisateur utilisateur;

	public ConnexionForm(EntityManager em) {
		this.em = em;
		utilisateur_repository = new UtilisateurRepository(em);
		utilisateur = new Utilisateur();
	}

	public Utilisateur connecterUtilisateur(HttpServletRequest request) {
		/* Récupération des champs du formulaire */
		String login = getValeurChamp(request, CHAMP_LOGIN);
		String password = getValeurChamp(request, CHAMP_PASSWORD);
		try {
			validationLogin(login);
		} catch (Exception e) {
			setErreur(CHAMP_LOGIN, e.getMessage());
		}

		// Vérifie que l'user est bien dans la base de données
		utilisateur = this.getUserByLogin(login);
		if (utilisateur != null) {
			try {// Vérifie que l'user a bien saisie son mdp
				validationMdp(password, utilisateur.getMdp());
			} catch (Exception e) {
				setErreur(CHAMP_PASSWORD, e.getMessage());
			}

		}
		if (erreurs.isEmpty() && utilisateur != null) {
			return utilisateur;
		} else
			return null;

	}

	private void validationLogin(String login) throws Exception {
		if (login == null) {
			throw new Exception("Login incorrect, merci de les saisir à nouveau.");
		}
	}

	private void validationMdp(String password_form, String password) throws Exception {
		if (password_form != null) {
			if (!password_form.equals(password)) {
				throw new Exception("Le mot de passe saisi est incorrect, merci de les saisir à nouveau.");
			} else if (password_form.length() < 1) {
				throw new Exception("Les mots de passe doivent contenir au moins 1 caractère.");
			}
		} else {
			throw new Exception("Merci de saisir votre mot de passe.");
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

	private Utilisateur getUserByLogin(String login) {
		try {
			List<Utilisateur> user = utilisateur_repository.findByLogin(login);
			utilisateur = em.find(Utilisateur.class, user);
		} catch (Exception e) {
			try {
				throw new Exception("L'utilisateur n'existe pas");
			} catch (Exception e1) {
				setErreur(CHAMP_LOGIN, e1.getMessage());
				return null;
			}
		}
		return utilisateur;
	}
}
