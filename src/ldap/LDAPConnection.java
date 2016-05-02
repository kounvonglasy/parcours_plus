package ldap;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;

public class LDAPConnection {
	private static final String CHAMP_LOGIN = "username";
	private static final String CHAMP_PASS = "password";

	public Utilisateur connecterUtilisateur(HttpServletRequest request) {
		LDAPaccess access = new LDAPaccess();

		/* Récupération des champs du formulaire */
		String login = getValeurChamp(request, CHAMP_LOGIN);
		String mdp = getValeurChamp(request, CHAMP_PASS);

		try {
			LDAPObject connexion = access.LDAPget(login, mdp);
			if (connexion == null) {
				System.out.println("login invalide");
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		// Si la connexion s'est bien passé,retourne le bean
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