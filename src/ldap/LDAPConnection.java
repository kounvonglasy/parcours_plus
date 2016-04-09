package ldap;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;

class ConnexionForm
{
	private static final String CHAMP_LOGIN = "username";
	private static final String CHAMP_PASS = "password";
	
	public Utilisateur connecterUtilisateur(HttpServletRequest request) {
		LDAPaccess access = new LDAPaccess();
		
		/* R�cup�ration des champs du formulaire */
		String login = getValeurChamp(request, CHAMP_LOGIN);
		String mdp = getValeurChamp(request, CHAMP_PASS);
		
		try {
		LDAPObject connexion = access.LDAPget(login, mdp);
		if (connexion == null)
		{
			System.out.println("login invalide");
			System.exit(1);
		}
		System.exit(0);
		} catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	    //Si la connexion s'est bien pass�,retourne le bean
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setLogin(login);
		return utilisateur;
		
	}
	
	/*
	 * 
	 * M�thode utilitaire qui retourne null si un champ est vide, et son contenu
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