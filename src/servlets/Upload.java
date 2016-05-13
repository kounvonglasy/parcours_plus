package servlets;

import java.io.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import beans.Profile;
import beans.Utilisateur;

import beans.Utilisateur;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
@MultipartConfig
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Upload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("parcours_plus");
		EntityManager entitymanager = emfactory.createEntityManager();
		
	//	Profile profile = entitymanager.find(Profile.class, 1);
		
	     
  	  Utilisateur user = new Utilisateur();
  	  
  	  // On récupère les champs à saisir dans la base 
  	  user.setLogin(request.getParameter("userlogin"));
  	  user.setNom(request.getParameter("username"));
  	 user.setPrenom(request.getParameter("userfname"));
  	 user.setPromotion(request.getParameter("userpromotion"));
  	  user.setEmail(request.getParameter("useremail"));
  	  user.setRole(request.getParameter("userrole"));
  	  user.setMdp(request.getParameter("userpwd"));
  	  
  	  // on upload une image 
		Part filePart = request.getPart("pic");
		InputStream fileContent = filePart.getInputStream();
		byte[] image = IOUtils.toByteArray(fileContent);
		user.setImage(image);
		
		// ON fait une transaction vers la base 
		entitymanager.getTransaction().begin();
		entitymanager.persist(user);
		entitymanager.getTransaction().commit();

		request.getRequestDispatcher("/JPA_EXAMPLE.jsp").forward(request, response);

	}

}
