package beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "module")
public class Module {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_module")
	private int id_module;

	@Column(name = "libelle")
	private String libelle;

	@Column(name = "a_la_carte")
	private String a_la_carte;

	@ManyToOne
	@JoinColumn(name = "id_parcours")
	private Parcours parcours;

	@ManyToMany(mappedBy = "modules", cascade = CascadeType.PERSIST)
	private List<Utilisateur> utilisateurs;

	public void setId(int id_module) {
		this.id_module = id_module;
	}

	public int getId() {
		return this.id_module;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setCarte(String a_la_carte) {
		this.a_la_carte = a_la_carte;
	}

	public String getCarte() {
		return this.a_la_carte;
	}

	public void setParcours(Parcours parcours) {
		this.parcours = parcours;
	}

	public Parcours getParcours() {
		return this.parcours;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void addUtilisateur(Utilisateur utilisateur) {
		if (!getUtilisateurs().contains(utilisateur)) {
			getUtilisateurs().add(utilisateur);
		}
	}
}