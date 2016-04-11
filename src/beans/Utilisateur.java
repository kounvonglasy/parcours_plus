package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
 
 @Id
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