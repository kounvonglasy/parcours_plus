package forms;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;

public class ConnexionForm {

	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_PASS = "password";

	public Utilisateur connecterUtilisateur(HttpServletRequest request) {

		/* Récupération des champs du formulaire */

		String login = getValeurChamp(request, CHAMP_LOGIN);

		String mdp = getValeurChamp(request, CHAMP_PASS);

		Utilisateur utilisateur = new Utilisateur();

		utilisateur.setLogin(login);

		utilisateur.setMdp(mdp);

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
