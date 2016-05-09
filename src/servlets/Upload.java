package servlets;

import java.io.*;

import javax.tools.JavaFileManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Profile;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request, response);
		
		
		 EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "parcours_plus" );
	     EntityManager entitymanager = emfactory.createEntityManager( );
	     
	    	  Profile profile = new Profile();
	    	  
	    	  /* uploadedFile c'est lors de l'utilisation de EJB....
			String uploadedFile = null;
			@SuppressWarnings("null")
			byte[] file = uploadedFile.getBytes();
	        profile.setImage(file);*/
	    	  
	    	  File file = new File((request.getParameter("pic")));
	  		byte[] fis = null;
	  		fis = new FileInputStream(file);
	    	  profile.setImage(fis);
	        
	    	  entitymanager.getTransaction().begin();
	    	  entitymanager.persist(profile);
	    	  entitymanager.getTransaction().commit();
	    	  
	    	  request.getRequestDispatcher("/JPA_EXAMPLE.jsp").forward(request, response); 
	    	  
	    	 
		
	}


}
