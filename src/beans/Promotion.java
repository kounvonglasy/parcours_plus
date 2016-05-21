package beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "promotion")
public class Promotion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_promotion")
	private int id_promotion;

	@Column(name = "annee")
	private String annee;
	
	@Column(name = "promotion")
	private String promotion;
	
	@OneToMany(mappedBy = "promotion")
	@JoinColumn(name = "id")
	private List<Utilisateur> utilisateurs;


	public void setAnnee(String annee){
		this.annee = annee;
	}
	
	public void setPromotion(String promotion){
		this.promotion = promotion;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void addUtilisateur(Utilisateur utilisateur) {
		if (!getUtilisateurs().contains(utilisateur)) {
			getUtilisateurs().add(utilisateur);
		}
	}
	
	public String getAnnee(){
		return annee;
	}
	
	public String getPromotion(){
		return promotion;
	}
}