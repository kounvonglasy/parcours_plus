package parcours;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Parcours;
import beans.ParcoursStatus;
import beans.Status;
import beans.Utilisateur;

public class ChoixParcoursManager extends ValidationParcoursManager {
	private static final String CHAMP_CHOIX_PARCOURS = "choix_parcours";

	public ChoixParcoursManager(EntityManager em) {
		super(em);
	}

	public Boolean creerParcoursStatus(HttpServletRequest request) {
		try {
			if (request.getParameter("choix1") == null) {
				throw new Exception("Veuillez choisir au moins un parcours");
			}
		} catch (Exception e) {
			setErreur(CHAMP_CHOIX_PARCOURS, e.getMessage());
		}
		if (!erreurs.isEmpty()) {
			succes = false;
		} else {
			HttpSession session = request.getSession(true);
			Utilisateur etudiant = (Utilisateur) session.getAttribute("session_utilisateur");
			status = em.find(Status.class, 1);
			// Pour chaque resultat trouvé
			for (int i = 1; i <= 5; i++) {
				if (request.getParameter("choix" + i) != null) {
					ParcoursStatus parcours_status = new ParcoursStatus();
					parcours_status.setUtilisateur(etudiant);
					parcours_status.setPrioriteChoixParcours(i);
					parcours_status.setStatus(status);
					Integer choix = Integer.parseInt(request.getParameter("choix" + i));
					Parcours parcours = new Parcours();
					parcours = em.find(Parcours.class, choix);
					parcours_status.setIdParcours(parcours);
					em.getTransaction().begin();
					em.persist(parcours_status);
					em.getTransaction().commit();
				}
			}
		}
		return succes;

	}
}
