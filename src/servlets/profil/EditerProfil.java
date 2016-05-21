package servlets.profil;

import java.io.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Utilisateur;
import profile.ProfilManager;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/EditerProfil")
@MultipartConfig
public class EditerProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditerProfil() {
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
		HttpSession session = request.getSession(true);
		Utilisateur user = (Utilisateur) session.getAttribute("session_utilisateur");
		user = entitymanager.find(Utilisateur.class, user.getId());
		request.setAttribute("user", user);
		request.getRequestDispatcher("/editer_profil.jsp").forward(request, response);
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
		Utilisateur user = profil_manager.editerProfil(request, response);
		if(user.getRole().equals("eleve")){
			request.getRequestDispatcher("AfficherProfil?id=" + user.getId()).forward(request, response);
		} else if (!user.getRole().equals("eleve")){
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}

}
