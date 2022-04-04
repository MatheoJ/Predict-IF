/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author mjoseph
 */
@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Personne implements Serializable {
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected long id;
    
    protected String nom;
    protected String prenom;
    protected String numTel;
   
   
    
    @Column(unique=true)
    protected String mail;
    protected String motDePasse;

    public Personne() {  
    }
    
    public Personne( String nom, String prenom, String numTel, String mail, String motDePasse) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
        this.mail = mail;
        this.motDePasse = motDePasse;
    }
    
    
    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", numTel=" + numTel + ", mail=" + mail + ", motDePasse=" + motDePasse + '}';
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    } 
    
}
