package promotion;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import beans.Promotion;

public class PromotionRepository {
	protected EntityManager em;

	public PromotionRepository(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Promotion> findAll(){
		Query query = em
				.createQuery("SELECT p FROM Promotion p");
		return (List<Promotion>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Promotion> findPromotionByAnnee(String annee){
		Query query = em
				.createQuery("SELECT p.id_promotion FROM Promotion p WHERE p.annee = :annee")
		.setParameter("annee", annee);
		return (List<Promotion>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Promotion> findPromotionByPromotion(String promotion){
		Query query = em
				.createQuery("SELECT p.id_promotion FROM Promotion p WHERE p.promotion = :promotion")
		.setParameter("promotion", promotion);
		return (List<Promotion>) query.getResultList();
	}
	
}
