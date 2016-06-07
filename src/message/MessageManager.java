package message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import beans.Message;
import beans.Parcours;
import beans.Utilisateur;
import error.ErrorManager;
import parcours.ParcoursRepository;
import utilisateur.UtilisateurRepository;

public class MessageManager extends ErrorManager {
	private EntityManager em;
	UtilisateurRepository utilisateur_repository;
	private String operator;
	protected static final String CHAMP_SUPPRESSION_MESSAGE = "suppression_message";

	public MessageManager(EntityManager em) {
		this.em = em;
		utilisateur_repository = new UtilisateurRepository(em);
	}

	public void envoyerMessage(HttpServletRequest request) {
		String email_expediteur = request.getParameter("email_expediteur");
		List<Utilisateur> list_expediteur = utilisateur_repository.findIdByEmail(email_expediteur);
		Utilisateur expediteur = em.find(Utilisateur.class, list_expediteur);
		int id_expediteur = expediteur.getId();
		String message = request.getParameter("message");
		String titre = request.getParameter("titre");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// get current date time with Date()
		Date getCurrentDate = new Date();
		String date = dateFormat.format(getCurrentDate);
		String status = "Nouvelle";
		// Preparation de l'envoi de message
		String email_destinataire = request.getParameter("email_destinataire");
		String[] split = email_destinataire.split(";");
		for (String s : split) {
			List<Utilisateur> list_destinataire = utilisateur_repository.findIdByEmail(s);
			Utilisateur destinataire = em.find(Utilisateur.class, list_destinataire);
			int id_destinataire = destinataire.getId();
			Message message_to_persist = new Message();
			message_to_persist.setIdDestinataire(id_destinataire);
			message_to_persist.setIdExpediteur(id_expediteur);
			message_to_persist.setMessage(message);
			message_to_persist.setDate(date);
			message_to_persist.setStatus(status);
			message_to_persist.setTitre(titre);
			em.getTransaction().begin();
			em.persist(message_to_persist);
			em.getTransaction().commit();
		}

	}

	@SuppressWarnings("rawtypes")
	public String envoyerMessageGroupe(HttpServletRequest request) {
		// Créer liste pour l'envoi des mails
		List<String> liste_emails = new ArrayList<String>();

		for (int i = 1; i <= 3; i++) {
			// Recupere liste eleves A2
			List<Utilisateur> liste_eleves = this.getListeEleves(request, "A" + i);
			Iterator itr = liste_eleves.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				liste_emails.add((String) obj[1]);
			}
		}
		String email_destinataire = "";
		for (int i = 0; i < liste_emails.size(); ++i) {
			email_destinataire += liste_emails.get(i);
			if (i != liste_emails.size() - 1) {
				email_destinataire += ";";
			}
		}
		return email_destinataire;
	}

	public List<Utilisateur> getListeEleves(HttpServletRequest request, String promotion) {
		Map<String, String> critere = new HashMap<String, String>();
		List<Utilisateur> liste_eleves;
		// Promotion A2
		if (request.getParameter(promotion) != null && promotion.equals("A1")) {
			// Gestion des A1
			liste_eleves = utilisateur_repository.findEleveA1ForMessageGroupe(promotion);
		} else {
			if (request.getParameter(promotion + "_alternant") != null && request.getParameter(promotion) != null) {
				critere = this.getParcours(request, promotion);
				critere.put("alternant", "%%");
			} else if (request.getParameter(promotion) != null) {
				critere = this.getParcours(request, promotion);
				critere.put("alternant", "Non");
			} else if (request.getParameter(promotion + "_alternant") != null) {
				critere = this.getParcours(request, promotion);
				critere.put("alternant", "Oui");
			} else {
				operator = "LIKE";
				critere.put("parcours", " \"\"");
				critere.put("alternant", "%%");
			}
			critere.put("promotion", promotion);
			liste_eleves = utilisateur_repository.findForMessageGroupe(critere, operator);
		}
		return liste_eleves;

	}

	@SuppressWarnings("rawtypes")
	public Map<String, String> getParcours(HttpServletRequest request, String promotion) {
		Map<String, String> critere = new HashMap<String, String>();
		ParcoursRepository parcours_repository = new ParcoursRepository(em);
		List<Parcours> liste_parcours = parcours_repository.findAllParcours();
		List<String> liste_parcours_trouve = new ArrayList<String>();
		Iterator itr = liste_parcours.iterator();
		while (itr.hasNext()) {
			Object[] obj = (Object[]) itr.next();
			if (request.getParameterMap().containsKey(promotion + "_" + (String) obj[1])) {
				liste_parcours_trouve.add((String) obj[1]);
			}
		}
		operator = "IN";
		String critere_parcours;
		if (!liste_parcours_trouve.isEmpty()) {
			critere_parcours = " (";
			for (int i = 0; i < liste_parcours_trouve.size(); ++i) {
				critere_parcours += "\"" + liste_parcours_trouve.get(i) + "\"";
				if (i != liste_parcours_trouve.size() - 1) {
					critere_parcours += ",";
				}
			}
			critere_parcours += ")";
		} else { // On choisit tous les parcours
			operator = "LIKE";
			critere_parcours = " \"%%\"";
		}
		critere.put("parcours", critere_parcours);
		return critere;
	}

	public void changerStatusMessage(Message message) {
		if (!message.getStatus().equals("Lue")) {
			message.setStatus("Lue");
			em.getTransaction().begin();
			em.flush();
			em.getTransaction().commit();
		}
	}

	public void supprimerMessage(int id) {
		try {
			Message message = em.find(Message.class, id);
			em.getTransaction().begin();
			em.remove(message);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {// On verifie que l'id existe
			setErreur(CHAMP_SUPPRESSION_MESSAGE, "L'id n'existe pas");
		}
	}

}
