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
				"SELECT m.titre, m.status, m.date, m.id_message FROM Message m WHERE m.id_destinataire = :id").setParameter("id", id);
		return (List<Message>) query.getResultList();
	}

}
