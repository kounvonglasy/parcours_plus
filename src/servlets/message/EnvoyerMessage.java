package servlets.message;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.MessageManager;



/**
 * Servlet implementation class AfficherParcours
 */
@WebServlet("/EnvoyerMessage")
public class EnvoyerMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "index.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnvoyerMessage() {
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
		MessageManager message_manager = new MessageManager(entitymanager);
		message_manager.envoyerMessage(request);
		request.getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
