package profile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import beans.Utilisateur;

public class DownloadFileManager {
	private EntityManager em;

	public DownloadFileManager(EntityManager em) {
		this.em = em;
	}

	public HttpServletResponse downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String file = request.getParameter("file");
		int id = Integer.parseInt(request.getParameter("id"));
		Utilisateur etudiant = em.find(Utilisateur.class, id);
		byte[] file_type = null;
		if (request.getParameter("file").equals("CV")) {
			file_type = etudiant.getCv();
		} else if (request.getParameter("file").equals("LM")) {
			file_type = etudiant.getLm();
		}
		response.setHeader("Content-Disposition", "attachment;filename=" + file + ".pdf");
		ByteArrayInputStream bis = new ByteArrayInputStream(file_type);
		IOUtils.copy(bis, response.getOutputStream());
		response.flushBuffer();
		return response;
	}

}
