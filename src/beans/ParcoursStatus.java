package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parcours_status")
public class ParcoursStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_parcours_status")
	private int id_parcours_status;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_utilisateur")
	private Utilisateur utilisateur;

	@OneToOne
	@JoinColumn(name = "id_parcours")
	private Parcours parcours;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_status")
	private Status status;

	@Column(name = "priorite_choix_parcours")
	private int priorite_choix_parcours;

	public void setId(int id_parcours_status) {
		this.id_parcours_status = id_parcours_status;
	}

	public int getId() {
		return this.id_parcours_status;
	}

	public void setPrioriteChoixParcours(int priorite_choix_parcours) {
		this.priorite_choix_parcours = priorite_choix_parcours;
	}

	public int getPrioriteChoixParcours() {
		return this.priorite_choix_parcours;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return this.status;
	}
	
	public void setParcours(Parcours parcours) {
		this.parcours = parcours;
	}

	public Parcours getParcours() {
		return this.parcours;
	}

}