package message;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import beans.Message;

public class MessageRepository {
	protected EntityManager em;

	public MessageRepository(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> findUserMessagesById(int id) {
		Query query = em.createQuery(
				"SELECT m.titre, m.status, m.date, m.id_message, u.nom, m.id_expediteur FROM Message m, Utilisateur u WHERE m.id_destinataire = :id AND m.id_expediteur = u.id").setParameter("id", id);
		return (List<Message>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Message> findMessagesNonLues(int id) {
		Query query = em.createQuery(
				"SELECT COUNT(m.id_message) as nombre FROM Message m WHERE m.id_destinataire = :id and m.status = 'Nouvelle'").setParameter("id", id);
		return (List<Message>) query.getResultList();
	}
}
