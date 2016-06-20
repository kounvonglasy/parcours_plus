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
import beans.Promotion;
import beans.Utilisateur;
import promotion.PromotionRepository;
import utilisateur.UtilisateurRepository;

@MultipartConfig
public class ProfilManager {
	private EntityManager em;
	UtilisateurRepository utilisateur_repository;
	PromotionRepository promotion_repository;

	public ProfilManager(EntityManager em) {
		this.em = em;
		utilisateur_repository = new UtilisateurRepository(em);
		promotion_repository = new PromotionRepository(em);
	}

	public Utilisateur editerProfil(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Utilisateur user = em.find(Utilisateur.class, id);

		// On récupère les champs à saisir dans la base
		user.setLogin(request.getParameter("userlogin"));
		user.setNom(request.getParameter("username"));
		user.setPrenom(request.getParameter("userfname"));
		user.setEmail(request.getParameter("useremail"));
		user.setDescription(request.getParameter("userdescription"));
		user.setBureau(request.getParameter("userbureau"));

		// on upload une image
		Part filePart = request.getPart("pic");
		InputStream fileContent = filePart.getInputStream();
		byte[] image = IOUtils.toByteArray(fileContent);
		user.setImage(image);

		if (!user.getRole().equals("prof") && !user.getRole().equals("administration")) {
			//Alternant ou non
			user.setAlternant(request.getParameter("useralternant"));
			// On met à jour la promotion
			Promotion promotion = new Promotion();
			try {
				List<Promotion> liste_promotion = promotion_repository
						.findPromotionByAnnee(request.getParameter("userannee"));
				promotion = em.find(Promotion.class, liste_promotion);
			} catch (Exception e) {
				promotion.setAnnee(request.getParameter("userannee"));
				promotion.setPromotion("ancien");
				em.getTransaction().begin();
				em.persist(promotion);
				em.getTransaction().commit();
			}
			user.setPromotion(promotion);
			// on upload une cv
			Part cvPart = request.getPart("cv");
			InputStream cvContent = cvPart.getInputStream();
			String cvName = getFileName(cvPart);
			byte[] cv = IOUtils.toByteArray(cvContent);
			try {
				List<CV> liste_user_cv_existant = utilisateur_repository.findCVById(user.getId());
				CV user_cv_existant = em.find(CV.class, liste_user_cv_existant);
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
				em.getEntityManagerFactory().getCache().evictAll();
				em.getTransaction().commit();
			}

			// on upload une lm
			Part lmPart = request.getPart("lm");
			InputStream lmContent = lmPart.getInputStream();
			String lmName = getFileName(lmPart);
			byte[] lm = IOUtils.toByteArray(lmContent);
			try {
				List<LM> liste_user_lm_existant = utilisateur_repository.findLMById(user.getId());
				LM user_lm_existant = em.find(LM.class, liste_user_lm_existant);
				if (user_lm_existant != null) {
					user_lm_existant.setUtilisateur(user);
					user_lm_existant.setLm(lm);
					user_lm_existant.setFileName(lmName);
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
				em.getEntityManagerFactory().getCache().evictAll();
				em.getTransaction().commit();
			}
		} else {
			em.getTransaction().begin();
			em.flush();
			em.getTransaction().commit();
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
