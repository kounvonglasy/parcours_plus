package parcours;

import java.util.List;
import javax.persistence.EntityManager;

import beans.ParcoursStatus;
import beans.Status;


public class ValidationParcoursManager {
	private EntityManager em;
	private ParcoursStatus parcours_status;
	private Status status;
	private ParcoursStatusRepository parcours_status_repository;

	public ValidationParcoursManager(EntityManager em) {
		this.em = em;
		parcours_status_repository = new ParcoursStatusRepository(em);
		parcours_status = new ParcoursStatus();
		status = new Status();
	}

	public void validerParcours(int id, String libelle_parcours, String choix_responsable) {
		em.getTransaction().begin();
		List<ParcoursStatus> liste_parcours_status = parcours_status_repository.findParcoursStatusByIdAndParcours(id,
				libelle_parcours);
		parcours_status = em.find(ParcoursStatus.class, liste_parcours_status);
		List<Status> liste_status = parcours_status_repository.findStatusByLibelle(choix_responsable);
		status = em.find(Status.class, liste_status);
		status.setLibelle(choix_responsable);
		parcours_status.setStatus(status);
		em.flush();
		em.getTransaction().commit();

	}

}
