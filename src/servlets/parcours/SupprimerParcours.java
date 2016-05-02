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
 * Servlet implementation class SupprimerParcours
 */
@WebServlet("/SupprimerParcours")
public class SupprimerParcours extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String ATT_FORM = "form";
	public static final String VUE = "AfficherParcours";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerParcours() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "parcours_plus" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    ParcoursManager parametrage_parcours = new ParcoursManager(entitymanager);
		parametrage_parcours.supprimerParcours(id);
        request.setAttribute( ATT_FORM, parametrage_parcours);
		request.getRequestDispatcher(VUE).forward(request, response);
		
	}

}
