/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Personne;

/**
 *
 * @author mjoseph
 */
public class PersonneDao {

    public void creer(Personne personne) {
        JpaUtil.obtenirContextePersistance().persist(personne);
    }

    public void supprimer(Personne personne) {
        JpaUtil.obtenirContextePersistance().remove(personne);
    }

    public Personne modifier(Personne personne) {
        return JpaUtil.obtenirContextePersistance().merge(personne);
    }

}
