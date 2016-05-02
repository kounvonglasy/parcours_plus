package beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "parcours")
public class Parcours {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_parcours")
	private int id_parcours;

	@Column(name = "libelle")
	private String libelle;

	@ManyToMany(mappedBy = "parcours", cascade = CascadeType.PERSIST)
	private List<Utilisateur> utilisateurs;

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
}