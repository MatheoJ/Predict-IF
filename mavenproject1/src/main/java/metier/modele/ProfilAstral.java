/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author mjoseph
 */
@Embeddable
public class ProfilAstral implements Serializable {
    String signeZodiaque;
    String signeChinois;
    String couleurBonheur;
    String animalTotem;

    public ProfilAstral() {
    }

    public ProfilAstral(String signeZodiaque, String signeChinois, String couleurBonheur, String animalTotem) {
        this.signeZodiaque = signeZodiaque;
        this.signeChinois = signeChinois;
        this.couleurBonheur = couleurBonheur;
        this.animalTotem = animalTotem;
    }

    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public String getCouleurBonheur() {
        return couleurBonheur;
    }

    public void setCouleurBonheur(String couleurBonheur) {
        this.couleurBonheur = couleurBonheur;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }

    
    
    @Override
    public String toString() {
        return "ProfilAstral{" + "signeZodiaque=" + signeZodiaque + ", signeChinois=" + signeChinois + ", couleurBonheur=" + couleurBonheur + ", animalTotem=" + animalTotem + '}';
    }
}
