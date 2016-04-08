package parcours_plus.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parcours_plus.beans.Utilisateur;
import parcours_plus.forms.ConnexionForm;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "session_utilisateur";
	public static final String VUE = "/resp_parcours.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Connexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		/* Préparation de l'objet formulaire */
		ConnexionForm form = new ConnexionForm();

		/* Traitement de la requête et récupération du bean en résultant */
		Utilisateur utilisateur = form.connecterUtilisateur(request);

		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession(true);
		
		session.setAttribute(ATT_SESSION_USER, utilisateur);

		/* Stockage du formulaire et du bean dans l'objet request */
		//request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, utilisateur);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

}
