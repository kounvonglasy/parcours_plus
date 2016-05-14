package servlets.parcours;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parcours.ParcoursManager;

/**
 * Servlet implementation class AjouterParcours
 */
@WebServlet("/CreerParcours")
public class CreerParcours extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/creer_parcours.jsp";
	public static final String ATT_FORM = "form";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreerParcours() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
		String libelle = request.getParameter("libelle_parcours");
		String nom_responsable = request.getParameter("nom_responsable");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
		EntityManager entitymanager = emfactory.createEntityManager();
		ParcoursManager parametrage_parcours = new ParcoursManager(entitymanager);
		Boolean succes = parametrage_parcours.creerParcours(libelle, nom_responsable, request);
		if (succes) {
			request.getRequestDispatcher("AfficherParcours").forward(request, response);
		} else {
			request.setAttribute(ATT_FORM, parametrage_parcours);
			request.getRequestDispatcher(VUE).forward(request, response);
		}
	}

}
