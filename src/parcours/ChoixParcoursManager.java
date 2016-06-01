package parcours;

import java.util.List;

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
				throw new Exception("Veuillez au moins sélectionner le choix 1");
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
			//Si l'etudiant revalide le parcours, on supprime les anciens choix
			List<Integer> liste_parcours_status = this.getParcoursStatus(etudiant.getId());
			if (liste_parcours_status != null) {
				for (int id_parcours_status : liste_parcours_status) {
					ParcoursStatus parcours_status_temp = em.find(ParcoursStatus.class, id_parcours_status);
					em.getTransaction().begin();
					em.remove(parcours_status_temp);
					em.getTransaction().commit();
				}
			}
			for (int i = 1; i <= 5; i++) {
				if (request.getParameter("choix" + i) != null) {
					ParcoursStatus parcours_status = new ParcoursStatus();
					parcours_status.setUtilisateur(etudiant);
					parcours_status.setPrioriteChoixParcours(i);
					parcours_status.setStatus(status);
					Integer choix = Integer.parseInt(request.getParameter("choix" + i));
					Parcours parcours = new Parcours();
					parcours = em.find(Parcours.class, choix);
					parcours_status.setParcours(parcours);
					em.getTransaction().begin();
					em.persist(parcours_status);
					em.getTransaction().commit();
				}
			}
		}
		return succes;
	}

	public List<Integer> getParcoursStatus(int id_etudiant) {
		try {
			List<Integer> liste_parcours_status = parcours_status_repository
					.findAllParcoursStatusByIdEtudiant(id_etudiant);
			return liste_parcours_status;
		} catch (Exception e) {
			return null;
		}
	}
}
