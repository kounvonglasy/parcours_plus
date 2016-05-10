package servlets.module;

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

import beans.Module;
import module.ModuleManager;

/**
 * Servlet implementation class AjouterParcours
 */
@WebServlet("/RechercherModule")
public class RechercherModule extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_FORM = "form";
	public static final String VUE = "liste_module.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechercherModule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "parcours_plus" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    ModuleManager parametrage_module = new ModuleManager(entitymanager);
	    int id_parcours = Integer.parseInt(request.getParameter("id_parcours"));
	    String libelle_parcours = request.getParameter("libelle_parcours");
	    List<Module> liste_module = parametrage_module.rechercherModule(request);
	    request.setAttribute("id_parcours", id_parcours);
		request.setAttribute("liste_module", liste_module);
		request.setAttribute("libelle_parcours", libelle_parcours);
	 request.getRequestDispatcher(VUE).forward(request, response); 
	}

}
