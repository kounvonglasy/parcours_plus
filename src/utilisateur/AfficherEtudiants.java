package utilisateur;

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

import beans.Utilisateur;

/**
 * Servlet implementation class AfficherParcours
 */
@WebServlet("/AfficherEtudiants")
public class AfficherEtudiants extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "liste_etudiants.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficherEtudiants() {
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
		UtilisateurRepository utilisateur_respository = new UtilisateurRepository(entitymanager);
		List<Utilisateur> liste_etudiants = utilisateur_respository.findAllEtudiants();
		request.setAttribute("liste_etudiants", liste_etudiants);
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
