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
 * Servlet implementation class SupprimerParcours
 */
@WebServlet("/SupprimerMessage")
public class SupprimerMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String ATT_FORM = "form";
    public static final String VUE = "AfficherMessages";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
		EntityManager entitymanager = emfactory.createEntityManager();
		MessageManager message_manager = new MessageManager(entitymanager);
		message_manager.supprimerMessage(id);
		request.setAttribute(ATT_FORM, message_manager);
		request.getRequestDispatcher(VUE).forward(request, response);
	}

}
