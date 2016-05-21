package servlets.promotion;

import java.io.*;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Promotion;
import promotion.PromotionManager;
import promotion.PromotionRepository;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/EditerPromotion")
public class EditerPromotion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PromotionRepository promotion_repository;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditerPromotion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
		EntityManager entitymanager = emfactory.createEntityManager();
		promotion_repository = new PromotionRepository(entitymanager);
		List<Promotion> liste_promotion = promotion_repository.findAll();
		request.setAttribute("liste_promotion", liste_promotion);
		request.getRequestDispatcher("/editer_promotion.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
		EntityManager entitymanager = emfactory.createEntityManager();
		promotion_repository = new PromotionRepository(entitymanager);
		PromotionManager promotion_manager = new PromotionManager(entitymanager);
		promotion_manager.editerPromotion(request);
		List<Promotion> liste_promotion = promotion_repository.findAll();
		request.setAttribute("liste_promotion", liste_promotion);
		request.getRequestDispatcher("/editer_promotion.jsp").forward(request, response);
	}

}
