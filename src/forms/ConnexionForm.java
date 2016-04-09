package forms;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;

public class ConnexionForm {

	private static final String CHAMP_LOGIN = "login";

	public Utilisateur connecterUtilisateur(HttpServletRequest request) {

		/* Récupération des champs du formulaire */

		String login = getValeurChamp(request, CHAMP_LOGIN);

		Utilisateur utilisateur = new Utilisateur();

		utilisateur.setLogin(login);

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
