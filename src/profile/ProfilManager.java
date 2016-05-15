package profile;

import java.io.IOException;
import java.io.InputStream;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import beans.Utilisateur;

public class ProfilManager {
	private EntityManager em;

	public ProfilManager(EntityManager em) {
		this.em = em;
	}

	public Utilisateur editerProfil(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Utilisateur user = em.find(Utilisateur.class, id);

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

		// on upload une cv
		Part cvPart = request.getPart("cv");
		InputStream cvContent = cvPart.getInputStream();
		byte[] cv = IOUtils.toByteArray(cvContent);
		user.setCv(cv);

		// on upload une lm
		Part lmPart = request.getPart("lm");
		InputStream lmContent = lmPart.getInputStream();
		byte[] lm = IOUtils.toByteArray(lmContent);
		user.setLm(lm);

		// ON fait une transaction vers la base
		em.getTransaction().begin();
		em.flush();
		em.getTransaction().commit();

		return user;
	}

}
