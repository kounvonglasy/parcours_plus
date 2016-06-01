package module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import beans.Module;
import beans.Utilisateur;
import parcours.ParcoursManager;

public class ModuleManager extends ParcoursManager {
	protected static final String CHAMP_MODULE = "module";
	protected static final String CHAMP_SUPPRESSION_MODULE = "suppression_module";
	private Module module;
	private Module module_existant;
	private ModuleRepository module_repository;
	private List<Module> liste_module;

	public ModuleManager(EntityManager em) {
		super(em);
		module_repository = new ModuleRepository(em);
	}

	public boolean editerModule(int id_module, String libelle_module, String a_la_carte, String nom_responsable) {
		// On verifie que le libelle n'est pas vide
		try {
			this.validationLibelle(libelle_module);
		} catch (Exception e) {
			setErreur(CHAMP_MODULE, e.getMessage());
		}
		if (erreurs.isEmpty()) {
			// On verifie que l'utilisateur est un responsable
			utilisateur = this.getResponsableByName(nom_responsable);
			if (utilisateur != null) {
				// On verifie que le libelle module n'existe pas deja dans la
				// base
				module = this.getModuleByLibelle(libelle_module);
				utilisateur_existant = this.getResponsableByModuleId(id_module);
				if (module != null) {
					try {
						module_existant = this.getModuleByParcours(module.getParcours().getId());
						if (module_existant != null && module.getId() != id_module
								&& module.getLibelle().equals(libelle_module)) {
							throw new Exception("Le module appartient déjà au parcours");
						} else if (module.getLibelle().equals(libelle_module)) {
							//Si on saisi le meme libellé mais le respo saisi est différent
							// On met à jour les infos de l'utilisateur
							utilisateur_existant.removeModule(module);
							module = em.find(Module.class, id_module);
							module.setLibelle(libelle_module);
							module.setCarte(a_la_carte);
							utilisateur.addModule(module);
							em.getTransaction().begin();
							em.flush();
							em.getTransaction().commit();
						}
					} catch (Exception e) {
						setErreur(CHAMP_MODULE, e.getMessage());
					}
				} else {// Sinon on fait que mettre à jour les informations du
						// module
					module = em.find(Module.class, id_module);
					// Si on change de respo, on supprime le module de l'ancien
					// respo
					if (utilisateur_existant.getNom() != nom_responsable && utilisateur_existant.getId() != id_module) {
						utilisateur_existant.removeModule(module);
					}
					module.setLibelle(libelle_module);
					module.setCarte(a_la_carte);
					utilisateur.addModule(module);
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

	public boolean creerModule(String libelle_module, String libelle_parcours, String nom_responsable,
			String a_la_carte, HttpServletRequest request) {
		// On verifie que le libelle n'est pas vide
		try {
			this.validationLibelle(libelle_module);
		} catch (Exception e) {
			setErreur(CHAMP_MODULE, e.getMessage());
		}
		if (erreurs.isEmpty()) {
			utilisateur = this.getResponsableByName(nom_responsable);
			if (utilisateur != null) {
				// On verifie que le libelle module n'existe pas deja dans la
				// base
				module = this.getModuleByLibelle(libelle_module);
				if (module != null) {
					try {
						module_existant = this.getModuleByParcours(module.getParcours().getId());
						if (module_existant != null && module_existant.getParcours() == module.getParcours()) {
							throw new Exception("Le module appartient déjà à un parcours");
						}
					} catch (Exception e) {
						setErreur(CHAMP_MODULE, e.getMessage());
					}
				} else {// Sinon on fait que mettre à jour les informations du
						// module
					try {
						parcours = this.getParcoursByLibelle(libelle_parcours);
						if (parcours == null) {
							throw new Exception("Le parcours n'existe pas");
						} else {
							em.getTransaction().begin();
							module = new Module();
							module.setLibelle(libelle_module);
							module.setCarte(a_la_carte);
							module.setParcours(parcours);
							em.persist(module);
							utilisateur.addModule(module);
							module.addUtilisateur(utilisateur);
							em.flush();
							em.getTransaction().commit();
						}
					} catch (Exception e) {
						setErreur(CHAMP_PARCOURS, e.getMessage());
					}
				}
			}
		}
		if (!erreurs.isEmpty()) {
			succes = false;
		}
		return succes;

	}

	public void supprimerModule(int id) {
		try {
			module = em.find(Module.class, id);
			em.getTransaction().begin();
			em.remove(module);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {// On verifie que l'id existe
			setErreur(CHAMP_SUPPRESSION_MODULE, "L'id n'existe pas");
		}
	}

	public List<Module> rechercherModule(HttpServletRequest request) {
		Map<String, String> critere = new HashMap<String, String>();
		critere.put("libelle_parcours", request.getParameter("libelle_parcours"));
		critere.put("libelle", request.getParameter("libelleFilter"));
		critere.put("responsable", request.getParameter("responsableFilter"));
		critere.put("a_la_carte", request.getParameter("aLaCarteFilter"));
		liste_module = module_repository.findByCriteriaAsLike(critere);
		return liste_module;
	}

	public Module getModuleByLibelle(String libelle) {
		try {
			liste_module = module_repository.findModuleByLibelle(libelle);
			module = em.find(Module.class, liste_module);
		} catch (Exception e) {
			return null;
		}
		return module;
	}

	public Module getModuleByParcours(int id_parcours) {
		try {
			liste_module = module_repository.findModuleByIdParcours(id_parcours);
			module_existant = em.find(Module.class, liste_module);
		} catch (Exception e) {
			return null;
		}
		return module_existant;
	}

	public Utilisateur getResponsableByModuleId(int id_module) {
		try {
			liste_utilisateurs = utilisateur_repository.findByModuleId(id_module);
			utilisateur_existant = em.find(Utilisateur.class, liste_utilisateurs);
		} catch (Exception e) {
			return null;
		}
		return utilisateur_existant;
	}
}
