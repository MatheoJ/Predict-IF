/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;

/**
 *
 * @author mjoseph
 */
@Entity
public class Consultation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
            
    String commentaire;
    EtatConsultation etat;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date dateHeurePriseRDV;
    @PrePersist void onPrePersist() {dateHeurePriseRDV = new java.util.Date();}
    
     
    @ManyToOne
    private Employe employe;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Medium medium;
    

    public Consultation(Client client, Medium medium) {
        etat = EtatConsultation.EN_ATTENTE;
        commentaire = "";
        this.client = client;
        this.medium = medium;
    }
        
    public Consultation(){
    }
        
    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public long getId() {
        return id;
    }

    public EtatConsultation getEtat() {
        return etat;
    }

    public void setEtat(EtatConsultation etat) {
        this.etat = etat;
    }

    public Date getDateHeurePriseRDV() {
        return dateHeurePriseRDV;
    }

    public void setDateHeurePriseRDV(Date dateHeurePriseRDV) {
        this.dateHeurePriseRDV = dateHeurePriseRDV;
    }
    
    

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", commentaire=" + commentaire + ", etat=" + etat + ", dateHeurePriseRDV=" + dateHeurePriseRDV + ", employe=" + employe + ", client=" + client + ", medium=" + medium + '}';
    }
    
    
    
}
