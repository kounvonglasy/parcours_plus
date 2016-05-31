package message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import beans.Message;
import beans.Utilisateur;
import utilisateur.UtilisateurRepository;

public class MessageManager {
	private EntityManager em;
	UtilisateurRepository utilisateur_repository;
	public MessageManager(EntityManager em) {
		this.em = em;
		utilisateur_repository = new UtilisateurRepository(em);
	}

	public void envoyerMessage(HttpServletRequest request) {
		/*String email_destinataire = request.getParameter("email_destinataire");
		String email_expediteur = request.getParameter("email_expediteur");
		List<Utilisateur> list_destinataire = utilisateur_repository.findIdByEmail(email_destinataire);
		List<Utilisateur> list_expediteur = utilisateur_repository.findIdByEmail(email_expediteur);
		Utilisateur destinataire = em.find(Utilisateur.class, list_destinataire);
		Utilisateur expediteur = em.find(Utilisateur.class, list_expediteur);
		int id_destinataire = destinataire.getId();
		int id_expediteur = expediteur.getId();*/
		String message = request.getParameter("message");
		String titre = request.getParameter("titre");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// get current date time with Date()
		Date getCurrentDate = new Date();
		String date = dateFormat.format(getCurrentDate);
		String status = "Nouvelle";
		Message message_to_persist = new Message();
		/*message_to_persist.setIdDestinataire(id_destinataire);
	    message_to_persist.setIdExpediteur(id_expediteur);*/
		message_to_persist.setMessage(message);
		message_to_persist.setDate(date);
		message_to_persist.setStatus(status);
		message_to_persist.setTitre(titre);
		em.getTransaction().begin();
		em.persist(message_to_persist);
		em.getTransaction().commit();
	}
}
