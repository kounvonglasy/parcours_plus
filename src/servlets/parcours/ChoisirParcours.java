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
import javax.servlet.http.HttpSession;

import beans.Parcours;
import beans.Utilisateur;
import parcours.ChoixParcoursManager;
import parcours.ParcoursRepository;

/**
 * Servlet implementation class AfficherParcours
 */
@WebServlet("/ChoisirParcours")
public class ChoisirParcours extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "choisir_parcours.jsp";
	public static final String ATT_FORM = "form";


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChoisirParcours() {
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
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
		EntityManager entitymanager = emfactory.createEntityManager();
		ParcoursRepository choix_parcours = new ParcoursRepository(entitymanager);
		List<Parcours> liste_parcours = choix_parcours.findAllParcours();
		request.setAttribute("liste_parcours", liste_parcours);
		HttpSession session = request.getSession(true);
		Utilisateur etudiant = (Utilisateur) session.getAttribute("session_utilisateur");
		List<Parcours> liste_parcours_selectionne = choix_parcours.findSelectedParcours(etudiant.getId());
		request.setAttribute("liste_parcours_selectionne",liste_parcours_selectionne);
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
		ParcoursRepository choix_parcours = new ParcoursRepository(entitymanager);
		List<Parcours> liste_parcours = choix_parcours.findAllParcours();
		ChoixParcoursManager choix_parcours_manager = new ChoixParcoursManager(entitymanager);
		Boolean succes = choix_parcours_manager.creerParcoursStatus(request);
		request.setAttribute("liste_parcours", liste_parcours);
		if (succes == false) {
			request.setAttribute(ATT_FORM, choix_parcours_manager);
		} else {
			request.setAttribute("succes_validation", "Parcours enregistré(s) avec succès");
			HttpSession session = request.getSession(true);
			Utilisateur etudiant = (Utilisateur) session.getAttribute("session_utilisateur");
			List<Parcours> liste_parcours_selectionne = choix_parcours.findSelectedParcours(etudiant.getId());
			request.setAttribute("liste_parcours_selectionne",liste_parcours_selectionne);
		}
		request.getRequestDispatcher(VUE).forward(request, response);
	}

}
