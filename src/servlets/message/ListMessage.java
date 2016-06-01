package servlets.message;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import beans.Message;
import beans.Parcours;
import message.MessageRepository;
import parcours.ParcoursRepository;


/**
 * Servlet implementation class ListMessage
 */
@WebServlet("/ListMessage")
public class ListMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "voir_message.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
		EntityManager entitymanager = emfactory.createEntityManager();
		MessageRepository recup_message = new MessageRepository(entitymanager);
		List<Message> liste_message = recup_message.findAllMessage();
		request.setAttribute("liste_message", liste_message);
		request.getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
