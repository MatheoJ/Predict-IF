/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Medium;

/**
 *
 * @author mjoseph
 */
public class MediumDao {

    public void creer(Medium medium) {
        JpaUtil.obtenirContextePersistance().persist(medium);
    }

    public void supprimer(Medium medium) {
        JpaUtil.obtenirContextePersistance().remove(medium);
    }

    public Medium modifier(Medium medium) {
        return JpaUtil.obtenirContextePersistance().merge(medium);
    }
    
    public List<Medium> chercherTous() {
        String s = "select m from Medium m order by m.denomination asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        return query.getResultList();
    }
    
    public Medium chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Medium.class, id);
    }

    public List<Medium> obtenirTop5() {
        String s = "select m from Medium m order by m.nbConsultation desc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        return query.setMaxResults(5).getResultList();
    }
    
}
