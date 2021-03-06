package servlets.utilisateur;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

import beans.Parcours;
import beans.Utilisateur;
import utilisateur.UtilisateurRepository;

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
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		JSONObject js = new JSONObject();
		PrintWriter out = response.getWriter();
		boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		String nom = request.getParameter("name");
		try {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
			EntityManager entitymanager = emfactory.createEntityManager();
			UtilisateurRepository utilisateur_repository = new UtilisateurRepository(entitymanager);
			List<Utilisateur> liste_responsable = utilisateur_repository.findResponsableByName(nom);
			Utilisateur responsable = entitymanager.find(Utilisateur.class, liste_responsable);
			String email, libelle, type_responsable,description,bureau;
			email = "";
			libelle = "";
			type_responsable ="";
			description ="";
			bureau ="";
			Boolean img_existante = true;
			if (ajax) {
				if (nom != null) {
					email = responsable.getEmail();
					List<Parcours> liste_parcours = responsable.getParcours();
					ArrayList<String> list = new ArrayList<String>();
					for (int i = 0; i < liste_parcours.size(); i++) {
						Parcours parcours = liste_parcours.get(i);
						list.add(parcours.getLibelle());
					}
					String listString = "";
					for (int i = 0; i < list.size(); ++i) {
						listString += list.get(i);
						if (i != list.size() - 1) {
							listString += " / ";
						}
					}
					type_responsable = listString;
					libelle = responsable.getNom() + " " + responsable.getPrenom();
					if (responsable.getImage().length == 0) {
						img_existante = false;
					}
					description = responsable.getDescription();
					bureau = responsable.getBureau();
				}
				js.put("email", email);
				js.put("type_responsable", type_responsable);
				js.put("libelle", libelle);
				js.put("description", description);
				js.put("bureau", bureau);
				js.put("img", responsable.getId());
				js.put("img_existante", img_existante);
				out.print(js);
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
