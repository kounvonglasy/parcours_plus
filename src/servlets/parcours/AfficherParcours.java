package servlets.parcours;

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
import parcours.ParcoursRepository;

/**
 * Servlet implementation class AfficherParcours
 */
@WebServlet("/AfficherParcours")
public class AfficherParcours extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "liste_parcours.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficherParcours() {
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
		ParcoursRepository parametrage_parcours = new ParcoursRepository(entitymanager);
		List<Parcours> liste_parcours = parametrage_parcours.findAllParcours();
		request.setAttribute("liste_parcours", liste_parcours);
		request.getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
