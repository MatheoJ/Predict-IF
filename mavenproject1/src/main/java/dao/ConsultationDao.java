package dao;

import metier.modele.Consultation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mjoseph
 */
public class ConsultationDao {
    public void creer(Consultation consultation) {
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }

    public void supprimer(Consultation consultation) {
        JpaUtil.obtenirContextePersistance().remove(consultation);
    }

    public Consultation modifier(Consultation consultation) {
        return JpaUtil.obtenirContextePersistance().merge(consultation);
    }
       
}
