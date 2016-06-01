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

import beans.Message;


/**
 * Servlet implementation class AfficherParcours
 */
@WebServlet("/VoirMessage")
public class VoirMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "voir_message.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VoirMessage() {
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
        int id = Integer.parseInt(request.getParameter("id"));
		Message message = entitymanager.find(Message.class,id);
		request.setAttribute("message", message);
		request.getRequestDispatcher(VUE).forward(request, response);
	}

}
