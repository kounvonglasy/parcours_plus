package beans;

public class Utilisateur {
	
	private String nom;
	
	private String login;
	
	private String email;
	
	private String mdp;
	
    public void setNom( String nom ) {
        this.nom = nom;
    }
    
    public void setLogin( String login ) {
        this.login = login;
    }

    public void setEmail( String email ) {
        this.email = email;
    }
    
    public void setMdp( String mdp ) {
        this.mdp = mdp;
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
	
	public String getMdp() {
		return this.mdp;
	}

}
