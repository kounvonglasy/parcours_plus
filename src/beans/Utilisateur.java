package beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "nom")
	private String nom;

	@Column(name = "login")
	private String login;

	@Column(name = "email")
	private String email;

	@Column(name = "role")
	private String role;

	@Column(name = "mdp")
	private String mdp;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "utilisateur_parcours", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id_parcours"))
	private List<Parcours> parcours;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "utilisateur_module", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id_module"))
	private List<Module> modules;

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public int getId() {
		return this.id;
	}

	public String getNom() {
		return this.nom;
	}

	public String getLogin() {
		return this.login;
	}

	public String getEmail() {
		return this.email;
	}

	public String getRole() {
		return this.role;
	}

	public String getMdp() {
		return this.mdp;
	}

	public void addParcours(Parcours parcours) {
		if (!getParcours().contains(parcours)) {
			getParcours().add(parcours);
		}
	}

	public void removeParcours(Parcours parcours) {
		getParcours().remove(parcours);
	}

	public List<Parcours> getParcours() {
		return this.parcours;
	}

	public void addModule(Module module) {
		if (!getModules().contains(module)) {
			getModules().add(module);
		}
	}

	public void removeModule(Module module) {
		getModules().remove(module);
	}

	public List<Module> getModules() {
		return this.modules;
	}

}