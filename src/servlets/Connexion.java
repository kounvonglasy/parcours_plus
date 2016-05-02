package servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import forms.ConnexionForm;
import ldap.LDAPConnection;

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
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "parcours_plus" );
	    EntityManager entitymanager = emfactory.createEntityManager( );
		/* Préparation de l'objet formulaire */
		ConnexionForm form = new ConnexionForm(entitymanager);
		
		//Utilisable qu'à l'ISEP (ligne 58 à remplacer par la ligne ci-dessous)
		//LDAPConnection ldap = new LDAPConnection();

		/* Traitement de la requête et récupération du bean en résultant */
		Utilisateur utilisateur = form.connecterUtilisateur(request);

		//Utilisable qu'à l'ISEP (ligne 64 à remplacer par la ligne ci-dessous)
		//Utilisateur utilisateur = ldap.connecterUtilisateur(request);

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );
        
		//Si la connexion n'est pas valide, on ne cree pas de session
		if(utilisateur != null){
			/* Récupération de la session depuis la requête */
			HttpSession session = request.getSession(true);		
			session.setAttribute(ATT_SESSION_USER, utilisateur);
		}
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

}
