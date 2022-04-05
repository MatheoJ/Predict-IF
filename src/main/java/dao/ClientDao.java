/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.EtatConsultation;

/**
 *
 * @author mjoseph
 */
public class ClientDao extends PersonneDao{
    public List<Client> chercherTous() {
        String s = "select c from Client c order by c.nom asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class);
        return query.getResultList();
    }
    
    public List<Consultation> obtenirConsultation(Client client) {
        String s = "select c from Consultation c where c.client=:unClient and c.etat=:unEtat order by c.dateHeurePriseRDV desc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class);
        query.setParameter("unClient", client);
        query.setParameter("unEtat", EtatConsultation.FINI);
        return query.getResultList();
    }
    
    
    public Consultation obtenirConsultationActuelle(Client client) {
        String s = "select c from Consultation c where c.client=:unClient and c.etat<>:unEtat";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class);
        query.setParameter("unClient", client);
        query.setParameter("unEtat", EtatConsultation.FINI);
        return (Consultation) query.getSingleResult();
    }
    
    public Client authentifier(String mail, String motDePasse){
        // /!\ JPQL sensible à la casse
        String s = "select c from Client c where c.mail='"+mail+"' and c.motDePasse='"+motDePasse+"'";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class);
        return (Client) query.getSingleResult();
    }
    
    public Client chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Client.class, id);
    } 
}
