package message;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import beans.Message;
import beans.Parcours;

public class MessageRepository {
	
	protected EntityManager em;
	
	public MessageRepository(EntityManager em) {
		this.em = em;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Message> findAllMessage() {
		Query query = em.createQuery(
				"SELECT m.titre, m.status  FROM Message m");
		return (List<Message>) query.getResultList();
	}
}
