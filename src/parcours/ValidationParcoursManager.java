package parcours;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import beans.ParcoursStatus;
import beans.Status;
import error.ErrorManager;


public class ValidationParcoursManager extends ErrorManager {
	protected EntityManager em;
	protected ParcoursStatus parcours_status;
	protected Status status;
	protected ParcoursStatusRepository parcours_status_repository;

	public ValidationParcoursManager(EntityManager em) {
		this.em = em;
		parcours_status_repository = new ParcoursStatusRepository(em);
		parcours_status = new ParcoursStatus();
		status = new Status();
	}

	public void validerParcours(int id, String libelle_parcours, String choix_responsable) {
		em.getTransaction().begin();
		List<ParcoursStatus> liste_parcours_status = parcours_status_repository.findParcoursStatusByIdAndLibelleParcours(id,
				libelle_parcours);
		parcours_status = em.find(ParcoursStatus.class, liste_parcours_status);
		List<Status> liste_status = parcours_status_repository.findStatusByLibelle(choix_responsable);
		status = em.find(Status.class, liste_status);
		status.setLibelle(choix_responsable);
		parcours_status.setStatus(status);
		if(status.getId() == 2){//Si le responsable valide le parcours, on supprime les autres parcours
			List<ParcoursStatus> liste_parcours_status_existant = parcours_status_repository.findNotAcceptedParcoursStatusByIdEtudiant(id);
			for (int i = 0; i < liste_parcours_status_existant.size(); i++) {
				ParcoursStatus parcours_status_existant = liste_parcours_status_existant.get(i);
				em.remove(parcours_status_existant);
			}
		}
		em.flush();
		em.getTransaction().commit();

	}
	
	public List<ParcoursStatus> rechercherParcoursStatus(HttpServletRequest request,int id_utilisateur) {
		Map<String, String> critere = new HashMap<String, String>();
		critere.put("nom_etudiant", request.getParameter("etudiantFilter"));
		critere.put("priorite", request.getParameter("prioriteFilter"));
		List<ParcoursStatus> liste_parcours = parcours_status_repository.findByCriteriaAsLike(critere, id_utilisateur);
		return liste_parcours;
	}

}
