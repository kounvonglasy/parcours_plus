package module;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import beans.Module;

public class ModuleRepository {
	protected EntityManager em;

	public ModuleRepository(EntityManager em) {
		this.em = em;
	}

	public List<Module> findAllModule(int id_parcours) {
		Query query = em
				.createQuery(
						"SELECT m.id_module, m.libelle, p.libelle, m.a_la_carte, u.nom FROM Module m LEFT JOIN m.parcours p LEFT JOIN m.utilisateurs u WHERE p.id_parcours= :id_parcours")
				.setParameter("id_parcours", id_parcours);
		return (List<Module>) query.getResultList();
	}

	public List<Module> findModuleByLibelle(String libelle) {
		Query query = em.createQuery("SELECT m.id_module FROM Module m WHERE m.libelle= :libelle")
				.setParameter("libelle", libelle);
		return (List<Module>) query.getResultList();
	}

	public List<Module> findModuleByParcours(int id) {
		Query query = em.createQuery("SELECT m.id_module FROM Module m LEFT JOIN m.parcours p WHERE p.id_parcours= :id")
				.setParameter("id", id);
		return (List<Module>) query.getResultList();
	}
	
    public List<Module> findByCriteriaAsLike(Map<String,String> critere) {
		Query query = em.createQuery(
				"SELECT m.id_module, m.libelle, p.libelle, m.a_la_carte, u.nom FROM Utilisateur u LEFT JOIN u.modules m LEFT JOIN m.parcours p WHERE u.nom like :nom AND m.libelle LIKE :libelle AND m.a_la_carte LIKE :a_la_carte AND u.role='responsable'").setParameter("libelle", '%'+critere.get("libelle")+'%').setParameter("nom", '%'+critere.get("responsable")+'%').setParameter("a_la_carte", '%'+critere.get("a_la_carte")+'%');
		return (List<Module>) query.getResultList();

    }

}
