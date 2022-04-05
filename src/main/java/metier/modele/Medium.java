/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Medium {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    protected String genre;
    protected String denomination;
    protected String presentation;
    protected Integer nbConsultation;

    public Medium(String genre, String denomination, String presentation) {
        this.genre = genre;
        this.denomination = denomination;
        this.presentation = presentation;
        nbConsultation = 0;
    }

    public Medium() {
    }

    public long getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public Integer getNbConsultation() {
        return nbConsultation;
    }

    public void setNbConsultation(Integer nbConsultation) {
        this.nbConsultation = nbConsultation;
    }

    @Override
    public String toString() {
        return "Medium{" + "id=" + id + ", genre=" + genre + ", denomination=" + denomination + ", presentation=" + presentation + ", nbConsultation=" + nbConsultation + '}';
    }
    
    
}
