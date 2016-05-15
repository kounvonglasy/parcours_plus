package profile;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Utilisateur;

public class DownloadFileManager {
	private EntityManager em;

	public DownloadFileManager(EntityManager em) {
		this.em = em;
	}

	public byte[] downloadFile(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Utilisateur etudiant = em.find(Utilisateur.class, id);
		byte[] file_type = null;
		if (request.getParameter("file").equals("CV")) {
			file_type = etudiant.getCv();
		} else if (request.getParameter("file").equals("LM")) {
			file_type = etudiant.getLm();
		}
		return file_type;
	}

}
