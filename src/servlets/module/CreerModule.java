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
 * Servlet implementation class AjouterParcours
 */
@WebServlet("/CreerModule")
public class CreerModule extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/creer_module.jsp";
	public static final String ATT_FORM = "form";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreerModule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String libelle_parcours = request.getParameter("libelle_parcours");
		int id_parcours = Integer.parseInt(request.getParameter("id_parcours"));
		request.setAttribute("id_parcours", id_parcours);
		request.setAttribute("libelle_parcours", libelle_parcours);
		 request.getRequestDispatcher("/creer_module.jsp").forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String libelle_parcours = request.getParameter("libelle_parcours");
		String libelle_module = request.getParameter("libelle_module");
		String nom_responsable = request.getParameter("nom_responsable");
		int id_parcours = Integer.parseInt(request.getParameter("id_parcours"));
		String a_la_carte = request.getParameter("a_la_carte");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "parcours_plus" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    ModuleManager parametrage_module = new ModuleManager(entitymanager);
	    Boolean succes = parametrage_module.creerModule(libelle_module,libelle_parcours,nom_responsable, a_la_carte,request);
		if(succes){
		request.getRequestDispatcher("AfficherModule?id_parcours="+id_parcours).forward(request, response);
		} else {
			request.setAttribute("id_parcours", id_parcours);
		    request.setAttribute("libelle_parcours", libelle_parcours);
			request.setAttribute(ATT_FORM, parametrage_module);
			request.getRequestDispatcher(VUE).forward(request, response);
		}
	}

}
