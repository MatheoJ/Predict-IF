package metier.modele;


import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mjoseph
 */

@Entity
public class Client extends Personne{
    
  
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateNaissance;
    String addrPostale;
    @Embedded
    ProfilAstral profilAstrale;
    
    public Client() {
    }

    public Client( String nom, String prenom, String numTel, String mail, String motDePasse,Date dateNaissance, String addrPostale) {
        super( nom, prenom, numTel, mail,  motDePasse);
        this.dateNaissance = dateNaissance;
        this.addrPostale = addrPostale;
        this.profilAstrale = null;
    }
    
    @Override
    public String toString() {
        return "Client{" + "dateNaissance=" + dateNaissance + ", addrPostale=" + addrPostale + ", profilAstrale=" + profilAstrale + "} " + super.toString();
    }
    
    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAddrPostale() {
        return addrPostale;
    }

    public void setAddrPostale(String addrPostale) {
        this.addrPostale = addrPostale;
    }

    public ProfilAstral getProfilAstral() {
        return profilAstrale;
    }

    public void setProfilAstrale(ProfilAstral profilAstrale) {
        this.profilAstrale = profilAstrale;
    }
    
}
