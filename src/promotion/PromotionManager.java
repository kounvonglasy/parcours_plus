package promotion;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import beans.Promotion;
import error.ErrorManager;

public class PromotionManager extends ErrorManager {
	protected EntityManager em;
	private PromotionRepository promotion_repository;
	List<Promotion> liste_promotion;
	Promotion promotion;

	public PromotionManager(EntityManager em) {
		this.em = em;
		promotion_repository = new PromotionRepository(em);
	}

	public void editerPromotion(HttpServletRequest request) {
		for (int i = 1; i <= 3; i++) {
			String annee = request.getParameter("A" + i);
			liste_promotion = promotion_repository.findPromotionByPromotion("A" + i);
			promotion = em.find(Promotion.class, liste_promotion);
			promotion.setAnnee(annee);
			em.getTransaction().begin();
			em.flush();
			em.getTransaction().commit();
		}
	}

}
