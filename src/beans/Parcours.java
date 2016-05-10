package beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
@Table(name = "parcours")
public class Parcours {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_parcours")
	private int id_parcours;

	@Column(name = "libelle")
	private String libelle;

	@OneToMany(mappedBy = "parcours", orphanRemoval = true)
	private List<Module> modules;

	@ManyToMany(mappedBy = "parcours", cascade = CascadeType.PERSIST)
	private List<Utilisateur> utilisateurs;

	@OneToOne(mappedBy = "parcours", cascade = CascadeType.REMOVE)
	private ParcoursStatus parcours_status;

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setId(int id_parcours) {
		this.id_parcours = id_parcours;
	}

	public int getId() {
		return this.id_parcours;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void addUtilisateur(Utilisateur utilisateur) {
		if (!getUtilisateurs().contains(utilisateur)) {
			getUtilisateurs().add(utilisateur);
		}
	}

	public void addModule(Module module) {
		if (!getModules().contains(module)) {
			getModules().add(module);
		}
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<Module> getModules() {
		// TODO Auto-generated method stub
		return modules;
	}

}