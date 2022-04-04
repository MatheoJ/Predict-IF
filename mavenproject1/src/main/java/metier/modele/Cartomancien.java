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
public class Cartomancien extends Medium {

    public Cartomancien() {
    }

    public Cartomancien(String genre, String denomination, String presentation) {
        super(genre, denomination, presentation);
    }

    @Override
    public String toString() {
        return "Cartomancien{" + "} " + super.toString();
    }
    
}
