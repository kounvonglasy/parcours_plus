package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lm")
public class LM {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_lm")
	private int id_lm;

	@Lob
	@Column(name = "lm")
	private byte[] lm;

	@Column(name = "filename")
	private String filename;

	@OneToOne
	@JoinColumn(name = "id")
	private Utilisateur utilisateur;

	public void setIdLm(int id_lm) {
		this.id_lm = id_lm;
	}

	public int getIdLm() {
		return this.id_lm;
	}

	public void setLm(byte[] lm) {
		this.lm = lm;
	}

	public byte[] getLm() {
		return lm;
	}

	public void setFileName(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

}