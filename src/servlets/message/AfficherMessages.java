package servlets.message;

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
import javax.servlet.http.HttpSession;

import beans.Message;
import beans.Utilisateur;
import message.MessageRepository;


/**
 * Servlet implementation class AfficherParcours
 */
@WebServlet("/AfficherMessages")
public class AfficherMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "liste_messages.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficherMessages() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
		EntityManager entitymanager = emfactory.createEntityManager();
		HttpSession session = request.getSession(true);
		Utilisateur user = (Utilisateur) session.getAttribute("session_utilisateur");
		MessageRepository message_manager = new MessageRepository(entitymanager);
		List<Message> liste_messages = message_manager.findUserMessagesById(user.getId());
		request.setAttribute("liste_messages", liste_messages);
		request.getRequestDispatcher(VUE).forward(request, response);
	}

}
