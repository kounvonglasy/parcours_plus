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

import beans.ParcoursStatus;
import beans.Status;
import parcours.ValidationParcoursManager;
import parcours.ParcoursStatusRepository;

/**
 * Servlet implementation class AfficherParcours
 */
@WebServlet("/ValiderParcours")
public class ValiderParcours extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "valider_parcours.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValiderParcours() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//HttpSession session = request.getSession(true);
		//Utilisateur responsable = (Utilisateur) session.getAttribute("session_utilisateur");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
		EntityManager entitymanager = emfactory.createEntityManager();
		ParcoursStatusRepository validation_parcours = new ParcoursStatusRepository(entitymanager);
		List<ParcoursStatus> liste_parcours_status = validation_parcours.findAllParcoursStatus();
		List<Status> liste_status = validation_parcours.findAllStatus();
		request.setAttribute("liste_parcours_status", liste_parcours_status);
		request.setAttribute("liste_status", liste_status);
		if (request.getParameter("id") != null && request.getParameter("libelle_parcours") != null
				&& request.getParameter("edit") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			String libelle_parcours = request.getParameter("libelle_parcours");
			String edit = request.getParameter("edit");
			request.setAttribute("id", id);
			request.setAttribute("libelle_parcours", libelle_parcours);
			request.setAttribute("edit", edit);
		}
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
		ParcoursStatusRepository validation_parcours = new ParcoursStatusRepository(entitymanager);
		List<ParcoursStatus> liste_parcours_status = null;
		if(request.getParameter("rechercheParcours")==null){
		List<Status> liste_status = validation_parcours.findAllStatus();
		request.setAttribute("liste_status", liste_status);
		if (request.getParameter("id") != null && request.getParameter("libelle_parcours") != null
				&& request.getParameter("status") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			String libelle_parcours = request.getParameter("libelle_parcours");
			String status = request.getParameter("status");
			ValidationParcoursManager valider_parcours = new ValidationParcoursManager(entitymanager);
			valider_parcours.validerParcours(id, libelle_parcours, status);
		}
		liste_parcours_status = validation_parcours.findAllParcoursStatus();
		}

		request.setAttribute("liste_parcours_status", liste_parcours_status);
		request.getRequestDispatcher(VUE).forward(request, response);
	}

}
