package servlets;
import beans.Utilisateur;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;


@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(
       HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
	 //  request.getRequestDispatcher("/user_list.jsp").forward(request, response);
           
   }


   @Override
   protected void doPost(
       HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       // Obtain a database connection:
   /*   EntityManagerFactory emf =
          (EntityManagerFactory)getServletContext().getAttribute("emf");
       EntityManager em = emf.createEntityManager();*/
	   
	   
	     EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "parcours_plus" );
	     EntityManager entitymanager = emfactory.createEntityManager( );
	     
	    	  Utilisateur user = new Utilisateur();
	    	  user.setLogin(request.getParameter("userlogin"));
	    	  user.setNom(request.getParameter("username"));
	    	  user.setEmail(request.getParameter("useremail"));
	    	  user.setRole(request.getParameter("userrole"));
	    	  entitymanager.getTransaction().begin();
	    	  entitymanager.persist(user);
	    	  entitymanager.getTransaction().commit();
	    	 
	  
           request.getRequestDispatcher("/JPA_EXAMPLE.jsp").forward(request, response); 
   }
}