package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class ListeResponsables
 */
@WebServlet("/SelectionResponsableParcours")
public class SelectionResponsableParcours extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectionResponsableParcours() {
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
		response.setContentType("text/html");
		JSONObject js = new JSONObject();
		PrintWriter out = response.getWriter();
		boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		String nom = request.getParameter("name");
		//On est censé récuperer l'email à partir de la base de données
		/*     
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
 		EntityManager entitymanager = emfactory.createEntityManager();
 		Query q = entitymanager.createQuery("user.findEmail", Utilisateur.class);
 		 List results = query.getResultList();
 		 email = results.get(0);
 		*/
		try {
			if (ajax) {
				String email = "";
				String type_responsable = "";
				String libelle = "";
				if (nom != null) {
					switch (nom) {
					case "Gates":
						email = "bill.gates@isep.fr ";
						type_responsable = "systeme d'information";
						libelle = "Monsieur Bill Gates";
						break;
					case "Hugo":
						email = "victor.hugo@isep.fr ";
						type_responsable = "système embarquée";
						libelle = "Monsieur Victor Hugo";
						break;
					case "Bulo":
						email = "marie.bulo@isep.fr ";
						type_responsable = "réseau";
						libelle = "Madame Marie Bulo";
						break;
					case "Intelligis":
						email = "anne-laure.inntelligis@isep.fr ";
						type_responsable = "business intelligent";
						libelle = "Madame Anne laure Intelligis";

						break;
					case "Kate":
						email = "greys.kate@isep.fr ";
						type_responsable = "numérique et Santé";
						libelle = "Madame Greys Kate";
						break;
					}
				} else {
					email = "bill.gates@isep.fr ";
					type_responsable = "systeme d'information";
					libelle = "Bill";
				}
				js.put("email", email);
				js.put("type_responsable", type_responsable);
				js.put("libelle", libelle);
				out.print(js.toJSONString());
			}
		} finally {
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
