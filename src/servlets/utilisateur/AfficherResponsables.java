package servlets.utilisateur;

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
import utilisateur.UtilisateurRepository;

/**
 * Servlet implementation class AfficherProfile
 */
@WebServlet("/AfficherResponsables")
public class AfficherResponsables extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "liste_responsables.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherResponsables() {
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
		UtilisateurRepository utilisateur_repository = new UtilisateurRepository(entitymanager);
		List<Utilisateur> liste_responsables = utilisateur_repository.findAllResponsablesParcours();
		request.setAttribute("liste_responsables", liste_responsables);
		request.getRequestDispatcher(VUE).forward(request, response);
	}
	
	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
}
