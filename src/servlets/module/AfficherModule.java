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
import module.ModuleRepository;

/**
 * Servlet implementation class AfficherModule
 */
@WebServlet("/AfficherModule")
public class AfficherModule extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "liste_module.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficherModule() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
		int id_parcours = Integer.parseInt(request.getParameter("id_parcours"));
		String libelle_parcours = request.getParameter("libelle_parcours");
		EntityManager entitymanager = emfactory.createEntityManager();
		ModuleRepository parametrage_module = new ModuleRepository(entitymanager);
		List<Module> liste_module = parametrage_module.findAllModule(id_parcours);
		request.setAttribute("libelle_parcours", libelle_parcours);
		request.setAttribute("id_parcours", id_parcours);
		request.setAttribute("liste_module", liste_module);
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
