package servlets.message;

import java.io.IOException;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Parcours;
import message.MessageManager;
import parcours.ParcoursRepository;

/**
 * Servlet implementation class AfficherParcours
 */
@WebServlet("/SelectionMessageGroupe")
public class SelectionMessageGroupe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "selection_message_groupe.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectionMessageGroupe() {
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
		ParcoursRepository parcours_repository = new ParcoursRepository(entitymanager);
		List<Parcours> liste_parcours = parcours_repository.findAllParcours();
		request.setAttribute("liste_parcours", liste_parcours);
		request.getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
		EntityManager entitymanager = emfactory.createEntityManager();
		MessageManager message_manager = new MessageManager(entitymanager);
		String email_destinataires = message_manager.envoyerMessageGroupe(request);
		request.getRequestDispatcher("redac_mess.jsp?email_destinataire="+email_destinataires).forward(request, response);
	}

}
