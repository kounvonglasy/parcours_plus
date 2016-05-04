package parcours;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import beans.Module;
import beans.Parcours;
import beans.Utilisateur;
import error.ErrorManager;
import parcours.ParcoursRepository;
import utilisateur.UtilisateurRepository;

public class ParcoursManager extends ErrorManager {
	protected EntityManager em;
	protected static final String CHAMP_NOM_RESPONSABLE = "nom_responsable";
	protected static final String CHAMP_PARCOURS = "parcours";
	protected static final String CHAMP_SUPPRESSION_PARCOURS = "suppression_parcours";
	protected Utilisateur utilisateur;
	protected Utilisateur utilisateur_existant;
	protected Parcours parcours;
	protected List<Utilisateur> liste_utilisateurs;
	protected List<Parcours> liste_parcours;
	protected ParcoursRepository parcours_repository;
	protected UtilisateurRepository utilisateur_repository;
	protected List<Utilisateur> utilisateurs_liste;
	protected List<Parcours> parcours_liste;

	public ParcoursManager(EntityManager em) {
		this.em = em;
		utilisateur = new Utilisateur();
		utilisateur_existant = new Utilisateur();
		parcours = new Parcours();
		parcours_repository = new ParcoursRepository(em);
		utilisateur_repository = new UtilisateurRepository(em);
		utilisateurs_liste = new ArrayList<Utilisateur>();
		parcours_liste = new ArrayList<Parcours>();
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
					parcours = em.find(Parcours.class, id);
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
						} else { // le parcours existe mais n'a pas de respo
							utilisateur.addParcours(parcours);
							em.getTransaction().begin();
							em.flush();
							em.getTransaction().commit();
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
			parcours = em.find(Parcours.class, id);
			liste_utilisateurs = utilisateur_repository.findByResponsableParcours(parcours.getId());
			utilisateur = em.find(Utilisateur.class, liste_utilisateurs);
			utilisateur.removeParcours(parcours);
			em.getTransaction().begin();
			em.remove(parcours);
			em.flush();
			em.clear();
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
			liste_utilisateurs = utilisateur_repository.findByResponsableParcours(id_parcours);
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
