package servlets.module;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.ModuleManager;

/**
 * Servlet implementation class SupprimerParcours
 */
@WebServlet("/SupprimerModule")
public class SupprimerModule extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String ATT_FORM = "form";
    public static final String VUE = "AfficherModule";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerModule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String libelle_parcours = request.getParameter("libelle_parcours");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "parcours_plus" );
	    EntityManager entitymanager = emfactory.createEntityManager();
		ModuleManager parametrage_module = new ModuleManager(entitymanager);
		parametrage_module.supprimerModule(id);
        request.setAttribute( ATT_FORM, parametrage_module);
        request.setAttribute("libelle_parcours", libelle_parcours);
		request.getRequestDispatcher(VUE+"?"+libelle_parcours).forward(request, response);
	}

}
