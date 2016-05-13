package servlets.profile;

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
import beans.Utilisateur;
import parcours.ParcoursRepository;
import profile.ProfileRepository;

/**
 * Servlet implementation class AfficherProfile
 */
@WebServlet("/AfficherProfile")
public class AfficherProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "profile_voir.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
		EntityManager entitymanager = emfactory.createEntityManager();
		ProfileRepository parametrage_profile = new ProfileRepository(entitymanager);
		List<Profile> liste_profile = parametrage_profile.findAllProfile();
		request.setAttribute("liste_parcours", liste_parcours);
		request.getRequestDispatcher(VUE).forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
