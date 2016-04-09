package beans;

public class Utilisateur {
	
	private String nom;
	
	private String login;
	
	private String email;
	
	private String role;
	
    public void setNom( String nom ) {
        this.nom = nom;
    }
    
    public void setLogin( String login ) {
        this.login = login;
    }

    public void setEmail( String email ) {
        this.email = email;
    }
    
    public void setRole( String role ) {
        this.role = role;
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

}
