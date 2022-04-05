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
public class Astrologue extends Medium {

    String formation;
    String promotion;

    public Astrologue() {
    }

    public Astrologue(String formation, String promotion, String genre, String denomination, String presentation) {
        super(genre, denomination, presentation);
        this.formation = formation;
        this.promotion = promotion;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "Astrologue{" + "formation=" + formation + ", promotion=" + promotion + "} " + super.toString();
    }
    
    

}
