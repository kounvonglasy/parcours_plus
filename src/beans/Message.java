package beans;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_message")
	private int id_message;

	@Column( name = "id_expediteur" )
	private int id_expediteur = 0;
	 
	@Column(name = "id_destinataire")
	private int id_destinataire = 0;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "titre")
	private String titre;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "status")
	private String status;
	
	public void setIdExpediteur(int id_expediteur) {
		this.id_expediteur = id_expediteur;
	}

	public int getIdExpediteur() {
		return this.id_expediteur;
	}
	
	public void setIdDestinataire(int id_destinataire) {
		this.id_destinataire = id_destinataire;
	}

	public int getIdDestinataire() {
		return this.id_destinataire;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return this.date;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTitre() {
		return this.titre;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
	
	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
	
}