package beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@Column(name = "prenom")
	private String prenom;
		
	@ManyToOne
	@JoinColumn(name = "id_promotion")
	private Promotion promotion;
	
	@Column(name = "alternant")
	private String alternant = "Non";
	
	@Column(name = "bureau")
	private String bureau = "Non";
	
	@Lob
	@Column( name = "image" , nullable = true)
	private byte[] image = null;
	 
	@OneToOne(mappedBy = "utilisateur", cascade = CascadeType.REMOVE)
	private CV cv;
	
	@OneToOne(mappedBy = "utilisateur", cascade = CascadeType.REMOVE)
	private LM lm;

	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "utilisateur_parcours", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id_parcours"))
	private List<Parcours> parcours;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "utilisateur_module", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id_module"))
	private List<Module> modules;
	
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
	private List<ParcoursStatus> parcours_status;
	
	@Column(name = "description", nullable = true)
	private String description;

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setImage( byte[] image )
    {
        this.image = image;
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
	
	public void setAlternant(String alternant) {
		this.alternant = alternant;
	}
	
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setBureau(String bureau){
		this.bureau = bureau;
	}

	public int getId() {
		return this.id;
	}
	
	public byte[] getImage()
    {
        return image;
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
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public String getAlternant() {
		return this.alternant;
	}
	public String getBureau() {
		return this.bureau;
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
	
	public CV getCv()
    {
        return cv;
    }
	
	public LM getLm()
    {
        return lm;
    }
	
	public void setPromotion(Promotion promotion){
		this.promotion = promotion;
	}
	
	public Promotion getPromotion(){
		return promotion;
	}
	
	public void addParcoursStatus(ParcoursStatus parcours_status) {
		if (!getParcoursStatus().contains(parcours_status)) {
			getParcoursStatus().add(parcours_status);
		}
	}

	public void removeParcoursStatus(ParcoursStatus parcours_status) {
		getParcoursStatus().remove(parcours_status);
	}

	public List<ParcoursStatus> getParcoursStatus() {
		return this.parcours_status;
	}
	
	public String getDescription(){
		return this.description;
	}
	
}