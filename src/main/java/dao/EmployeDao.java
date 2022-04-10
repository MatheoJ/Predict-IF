/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.EtatConsultation;

/**
 *
 * @author mjoseph
 */
public class EmployeDao extends PersonneDao{
    public List<Employe> chercherTous() {
        String s = "select c from Employe c order by c.nom asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
        return query.getResultList();
    }
    
    public Employe authentifier(String mail, String motDePasse){
        // /!\ JPQL sensible à la casse
        String s = "select c from Employe c where c.mail='"+mail+"' and c.motDePasse='"+motDePasse+"'";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
        return (Employe) query.getResultList().stream().findFirst().orElse(null);
    }
    
    public Employe chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Employe.class, id);
    }
    
    public Employe estDisponible(String genre) {
        // À chaque demande de consultation,
        // On cherche un employé du même genre que le medium
        // qui est disponible.
        // L'employé qui a le moins  de consultations est priviliégié.
        String s = "select c from Employe c order by c.nom asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
        // On obtient le premier employé de la liste
        System.out.println(query.getResultList());
        List<Employe> res = query.getResultList();
        if (res.isEmpty())
        {
            return null;
        }
        return res.get(0);
    }
    
    public List<Consultation> obtenirConsultation(Employe employe) {
        String s = "select c from Consultation c where c.employe=:unEmploye and c.etat=:unEtat order by c.dateHeurePriseRDV ";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
        query.setParameter("unEmploye", employe);
        query.setParameter("unEtat", EtatConsultation.FINI);
        return query.getResultList();
    }
    
    public Consultation obtenirConsultationActuelle(Employe employe) {
        String s = "select c from Consultation c where c.employe=:unEmploye and c.etat<>:unEtat";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
        query.setParameter("unEmploye", employe);
        query.setParameter("unEtat", EtatConsultation.FINI);
        return (Consultation) query.getSingleResult();
    }
    
}
