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

import beans.Parcours;
import parcours.ParcoursManager;

/**
 * Servlet implementation class AjouterParcours
 */
@WebServlet("/RechercherParcours")
public class RechercherParcours extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_FORM = "form";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechercherParcours() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "parcours_plus" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    ParcoursManager parametrage_parcours = new ParcoursManager(entitymanager);
	    List<Parcours> liste_parcours = parametrage_parcours.rechercherParcours(request);
		request.setAttribute("liste_parcours", liste_parcours);
	 request.getRequestDispatcher("/liste_parcours.jsp").forward(request, response); 
	}

}
