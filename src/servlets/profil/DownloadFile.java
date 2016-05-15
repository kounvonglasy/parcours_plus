package servlets.profil;

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
import org.apache.commons.io.IOUtils;
import profile.DownloadFileManager;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/DownloadFile")
@MultipartConfig
public class DownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadFile() {
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
		DownloadFileManager download_file_manager = new DownloadFileManager(entitymanager);
		String file = request.getParameter("file");
		byte[] file_type = download_file_manager.downloadFile(request, response);
		response.setHeader("Content-Disposition", "attachment;filename=" + file + ".pdf");
		ByteArrayInputStream bis = new ByteArrayInputStream(file_type);
		IOUtils.copy(bis, response.getOutputStream());
		response.flushBuffer();
		request.getRequestDispatcher("/voir_profil.jsp").forward(request, response);
	}

}