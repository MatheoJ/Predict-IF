/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author mjoseph
 */
@Entity
public class Employe extends Personne {

    String genre;
    Boolean disponibilite;
    Integer nbConsultations;

    public Employe() {
    }

    public Employe(String nom, String prenom, String numTel, String mail, String motDePasse, String genre, boolean disponibilite) {
        super(nom, prenom, numTel, mail, motDePasse);
        this.genre = genre;
        this.disponibilite = disponibilite;
        nbConsultations = 0;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public Integer getNbConsultation() {
        return nbConsultations;
    }

    public void setNbConsultation(Integer nbConsultations) {
        this.nbConsultations = nbConsultations;
    }
    
    

    @Override
    public String toString() {
        return "Employe{" + "genre=" + genre + ", disponibilite=" + disponibilite + "} " + super.toString();
    }
}
