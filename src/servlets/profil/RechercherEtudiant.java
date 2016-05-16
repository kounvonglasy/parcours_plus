package servlets.profil;

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
import profile.ProfilManager;

/**
 * Servlet implementation class AjouterParcours
 */
@WebServlet("/RechercherEtudiant")
public class RechercherEtudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RechercherEtudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
		EntityManager entitymanager = emfactory.createEntityManager();
		ProfilManager profil_manager = new ProfilManager(entitymanager);
		List<Utilisateur> liste_etudiants = profil_manager.rechercherEtudiant(request);
		request.setAttribute("liste_etudiants", liste_etudiants);
		request.getRequestDispatcher("/liste_etudiants.jsp").forward(request, response);
	}

}
