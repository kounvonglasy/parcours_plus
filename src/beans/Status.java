package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_status")
	private int id_status;

	@Column(name = "libelle")
	private String libelle;

	public void setId(int id_status) {
		this.id_status = id_status;
	}

	public int getId() {
		return this.id_status;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return this.libelle;
	}

}