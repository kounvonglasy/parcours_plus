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
@Table(name = "cv")
public class CV {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cv")
	private int id_cv;

	@Lob
	@Column( name = "cv" )
	private byte[] cv;
	 
	@Column(name = "filename")
	private String filename;
	
	@OneToOne
	@JoinColumn(name = "id")
	private Utilisateur utilisateur;
	
	public void setIdCv(int id_cv) {
		this.id_cv = id_cv;
	}

	public int getIdCv() {
		return this.id_cv;
	}

	public void setCv( byte[] cv )
    {
        this.cv = cv;
    }

	public byte[] getCv()
    {
        return cv;
    }
	
	public void setFileName(String filename){
		this.filename = filename;
	}
	
	public String getFilename(){
		return filename;
	}

	public void setUtilisateur(Utilisateur utilisateur){
		this.utilisateur = utilisateur;
	}
	
	public Utilisateur getUtilisateur(){
		return utilisateur;
	}
}