package profile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

import beans.CV;
import beans.LM;
import beans.Utilisateur;
import utilisateur.UtilisateurRepository;

@MultipartConfig
public class ProfilManager {
	private EntityManager em;
	UtilisateurRepository utilisateur_repository;

	public ProfilManager(EntityManager em) {
		this.em = em;
		utilisateur_repository = new UtilisateurRepository(em);
	}

	public Utilisateur editerProfil(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException, ServletException {
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
		if (!user.getRole().equals("responsable")) {
			Part cvPart = request.getPart("cv");
			InputStream cvContent = cvPart.getInputStream();
			String cvName = getFileName(cvPart);
			byte[] cv = IOUtils.toByteArray(cvContent);
			try {
				List<CV> list_user_cv_existant = utilisateur_repository.findCVByIdEtudiant(user.getId());
				CV user_cv_existant = em.find(CV.class, list_user_cv_existant);
				if (user_cv_existant != null) {
					user_cv_existant.setUtilisateur(user);
					user_cv_existant.setCv(cv);
					user_cv_existant.setFileName(cvName);
					em.getTransaction().begin();
					em.flush();
					em.getTransaction().commit();
				}
			} catch (Exception e) {
				CV user_cv = new CV();
				user_cv.setUtilisateur(user);
				user_cv.setCv(cv);
				user_cv.setFileName(cvName);
				em.getTransaction().begin();
				em.persist(user_cv);
				em.getTransaction().commit();
			}

			// on upload une lm
			Part lmPart = request.getPart("lm");
			InputStream lmContent = lmPart.getInputStream();
			String lmName = getFileName(lmPart);
			byte[] lm = IOUtils.toByteArray(lmContent);
			try {
				List<LM> list_user_lm_existant = utilisateur_repository.findLMByIdEtudiant(user.getId());
				LM user_lm_existant = em.find(LM.class, list_user_lm_existant);
				if (user_lm_existant != null) {
					user_lm_existant.setUtilisateur(user);
					user_lm_existant.setLm(lm);
					user_lm_existant.setFileName(cvName);
					em.getTransaction().begin();
					em.flush();
					em.getTransaction().commit();
				}
			} catch (Exception e) {
				LM user_lm = new LM();
				user_lm.setUtilisateur(user);
				user_lm.setLm(lm);
				user_lm.setFileName(lmName);
				em.getTransaction().begin();
				em.persist(user_lm);
				em.getTransaction().commit();
			}
		}

		return user;

	}

	public List<Utilisateur> rechercherEtudiant(HttpServletRequest request) {
		Map<String, String> critere = new HashMap<String, String>();
		critere.put("nom", request.getParameter("nomFilter"));
		critere.put("prenom", request.getParameter("prenomFilter"));
		critere.put("promotion", request.getParameter("promotionFilter"));
		List<Utilisateur> liste_etudiants = utilisateur_repository.findByCriteriaAsLike(critere);
		return liste_etudiants;
	}

	public static String getFileName(Part filePart) {
		String header = filePart.getHeader("content-disposition");
		for (String headerPart : header.split(";")) {
			if (headerPart.trim().startsWith("filename")) {
				return headerPart.substring(headerPart.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
