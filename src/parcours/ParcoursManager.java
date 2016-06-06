package parcours;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import beans.Parcours;
import beans.Utilisateur;
import error.ErrorManager;
import parcours.ParcoursRepository;
import utilisateur.UtilisateurRepository;

public class ParcoursManager extends ErrorManager {
	protected EntityManager em;
	private List<Parcours> liste_parcours;
	private ParcoursRepository parcours_repository;
	private static final String CHAMP_NOM_RESPONSABLE = "nom_responsable";
	private static final String CHAMP_SUPPRESSION_PARCOURS = "suppression_parcours";
	protected static final String CHAMP_PARCOURS = "parcours";
	protected Utilisateur utilisateur;
	protected Utilisateur utilisateur_existant;
	protected Parcours parcours;
	protected List<Utilisateur> liste_utilisateurs;
	protected UtilisateurRepository utilisateur_repository;

	public ParcoursManager(EntityManager em) {
		this.em = em;
		utilisateur = new Utilisateur();
		utilisateur_existant = new Utilisateur();
		parcours = new Parcours();
		parcours_repository = new ParcoursRepository(em);
		utilisateur_repository = new UtilisateurRepository(em);
	}

	public boolean editerParcours(int id, String libelle_parcours, String nom_responsable) {
		// On verifie que le libelle n'est pas vide
		try {
			this.validationLibelle(libelle_parcours);
		} catch (Exception e) {
			setErreur(CHAMP_PARCOURS, e.getMessage());
		}
		if (erreurs.isEmpty()) {
			// On verifie que l'utilisateur est un responsable
			utilisateur = this.getResponsableByName(nom_responsable);
			if (utilisateur != null) {
				// On verifie que le libelle n'existe pas deja dans la base
				parcours = this.getParcoursByLibelle(libelle_parcours);
				if (parcours != null) {
					try {
						utilisateur_existant = this.getResponsableByParcours(parcours.getId());
						// On throw une erreur si un respo dispose deja du
						// parcours
						if (utilisateur_existant != null && utilisateur_existant.getNom().equals(nom_responsable)
								&& parcours.getId() != id) {
							throw new Exception("Le parcours appartient déjà à quelqu'un");
						} else if (!utilisateur_existant.getNom().equals(nom_responsable)) {
							utilisateur_existant.removeParcours(parcours);
							utilisateur.addParcours(parcours);
							em.getTransaction().begin();
							em.flush();
							em.getTransaction().commit();
						}
					} catch (Exception e) {
						setErreur(CHAMP_PARCOURS, e.getMessage());
					}

				} else {// Le libelle n'existe pas dans la base
					utilisateur_existant = this.getResponsableByParcours(id);
					parcours = em.find(Parcours.class, id);
					// Si on change de respo, on supprime le parcours de
					// l'ancien respo
					if (utilisateur_existant.getNom() != nom_responsable && utilisateur_existant.getId() != id) {
						utilisateur_existant.removeParcours(parcours);
					}
					parcours.setLibelle(libelle_parcours);
					utilisateur.addParcours(parcours);
					em.getTransaction().begin();
					em.flush();
					em.getTransaction().commit();
				}
			}
		}
		if (!erreurs.isEmpty()) {
			succes = false;
		}
		return succes;

	}

	public boolean creerParcours(String libelle_parcours, String nom_responsable, HttpServletRequest request) {
		try {
			this.validationLibelle(libelle_parcours);
		} catch (Exception e) {
			setErreur(CHAMP_PARCOURS, e.getMessage());
		}
		if (erreurs.isEmpty()) {
			// On verifie que le parcours n'existe pas deja
			utilisateur = this.getResponsableByName(nom_responsable);
			if (utilisateur != null) {
				parcours = this.getParcoursByLibelle(libelle_parcours);
				if (parcours != null) {
					try {
						utilisateur_existant = this.getResponsableByParcours(parcours.getId());
						// si un respo dispose deja du parcours, on throw une
						// exception
						if (utilisateur_existant != null) {
							throw new Exception("Le parcours appartient déjà à quelqu'un");
						}
					} catch (Exception e) {
						setErreur(CHAMP_PARCOURS, e.getMessage());
					}
				} else {// Le libelle n'existe pas dans la base
					em.getTransaction().begin();
					parcours = new Parcours();
					parcours.setLibelle(libelle_parcours);
					em.persist(parcours);
					utilisateur.addParcours(parcours);
					parcours.addUtilisateur(utilisateur);
					em.flush();
					em.getTransaction().commit();
				}
			}
		}
		if (!erreurs.isEmpty()) {
			succes = false;
		}
		return succes;
	}

	public void supprimerParcours(int id) {
		try {
			em.getTransaction().begin();
			em.getEntityManagerFactory().getCache().evictAll();
			parcours = em.find(Parcours.class, id);
			parcours.getModules().clear();
			em.flush();
			em.remove(parcours);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {// On verifie que l'id existe
			setErreur(CHAMP_SUPPRESSION_PARCOURS, "L'id n'existe pas");
		}
	}

	public List<Parcours> rechercherParcours(HttpServletRequest request) {
		Map<String, String> critere = new HashMap<String, String>();
		critere.put("libelle", request.getParameter("libelleFilter"));
		critere.put("responsable", request.getParameter("responsableFilter"));
		List<Parcours> liste_parcours = parcours_repository.findByCriteriaAsLike(critere);
		return liste_parcours;
	}

	public Utilisateur getResponsableByName(String nom_responsable) {
		try {
			// Liste des responsables
			liste_utilisateurs = utilisateur_repository.findResponsableByName(nom_responsable);
			utilisateur = em.find(Utilisateur.class, liste_utilisateurs);
		} catch (Exception e) {
			try {
				throw new Exception("L'utilisateur n'est pas un responsable");
			} catch (Exception e1) {
				setErreur(CHAMP_NOM_RESPONSABLE, e1.getMessage());
				return null;
			}
		}
		return utilisateur;
	}

	public Parcours getParcoursByLibelle(String libelle) {
		try {
			liste_parcours = parcours_repository.findParcoursByLibelle(libelle);
			parcours = em.find(Parcours.class, liste_parcours);
		} catch (Exception e) {
			return null;
		}
		return parcours;
	}

	public Utilisateur getResponsableByParcours(int id_parcours) {
		try {
			liste_utilisateurs = utilisateur_repository.findResponsableParcours(id_parcours);
			utilisateur_existant = em.find(Utilisateur.class, liste_utilisateurs);
		} catch (Exception e) {
			return null;
		}
		return utilisateur_existant;
	}

	public void validationLibelle(String libelle) throws Exception {
		if (libelle.isEmpty()) {
			throw new Exception("Veuillez saisir le libelle");
		}
	}

}
