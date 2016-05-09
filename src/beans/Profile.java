package beans;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "profile")
public class Profile {


	@Id
	@Column(name = "id_profile")
	private int id_profile;

	/*
	@Column(name = "image")
	private String image;
	*/
	 @Lob
	 @Column( name = "image" )
	 private byte[] image;

	@Column(name = "nom")
	private String nom;

	@Column(name = "parcours")
	private String parcours;

	@Column(name = "prenom")
	private String prenom;

	@Column(name = "promotion")
	private String promotion;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "utilisateur_profile", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id_profile"))
	private List<Profile> profile;
	
	public void setId_profile(int id_profile) {
		this.id_profile = id_profile;
		}

/*
	public void setImage(String image) {
		this.image = image;
	}*/

	public void setImage( byte[] image )
    {
        this.image = image;
    }
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setParcours(String parcours) {
		this.parcours = parcours;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public int getId_profile() {
		return this.id_profile;
	}

/*
	public String getImage() {
		return this.image;
	}*/

	public byte[] getImage()
    {
        return image;
    }
	
	
	public String getNom() {
		return this.nom;
	}

	public String getParcours() {
		return this.parcours;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public String getPromotion() {
		return this.promotion;
	}
	
}
