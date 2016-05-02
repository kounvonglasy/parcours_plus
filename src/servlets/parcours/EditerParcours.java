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
@WebServlet("/EditerParcours")
public class EditerParcours extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/editer_parcours.jsp";
	public static final String ATT_FORM = "form";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditerParcours() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nom_responsable = request.getParameter("nom_responsable");
		request.setAttribute("id", id);
		request.setAttribute("nom_responsable", nom_responsable);
		request.getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String libelle = request.getParameter("libelle_parcours");
		String nom_responsable = request.getParameter("nom_responsable");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
		EntityManager entitymanager = emfactory.createEntityManager();
		ParcoursManager parametrage_parcours = new ParcoursManager(entitymanager);
		Boolean succes = parametrage_parcours.editerParcours(id, libelle, nom_responsable); 
		if (succes) {
			request.getRequestDispatcher("AfficherParcours").forward(request, response);
		} else {
			request.setAttribute( ATT_FORM, parametrage_parcours);
			request.setAttribute("id", id);
			request.getRequestDispatcher(VUE).forward(request, response);
		}
	}

}
