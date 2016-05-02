package error;

import java.util.HashMap;
import java.util.Map;


public class ErrorManager {
	
	protected boolean succes = true;
	
	protected Map<String, String> erreurs = new HashMap<String, String>();

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	public void setErreur(String champ, String message) {
		erreurs.put(champ, message);

	}

}
