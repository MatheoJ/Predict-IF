package vue;

import metier.modele.Client;
import dao.JpaUtil;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.service.Service;
import static metier.service.Service.authentifierClient;
import static metier.service.Service.inscrireClient;
import static metier.service.Service.listerTousClients;
import static metier.service.Service.trouverClientParId;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mjoseph
 */
public class Main {

    public static void main(String[] args) {
        /* To-Do:
            Dans Service.java, mettre sous forme try catch finally.
        */
        JpaUtil.init();
        
        // Initialisation des employés
        Service.initialiserEmployes();
        
        try {
            // Initialisation des Mediums
            Service.initialiserMedium();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        initialiserClients();
       
        List<Client> clientsAInscrire = new ArrayList<>();
        try {
           
            clientsAInscrire.add(new Client("JOSEPH", "Mathéo", "1365464", "matheo.joseph@insa-lyon.fr", "admin", simpleDateFormat.parse("20/05/2001"), "13 rue cambon"));
            clientsAInscrire.add(new Client("JOSEPH", "Mathéo", "1365464", "matheo.joseph@insa-lyon.fr", "admin", simpleDateFormat.parse("20/05/2001"), "13 rue cambon"));
            clientsAInscrire.add(new Client("NOT", "Anadmin", "1365464", "not.anadmin@insa-lyon.fr", "notandmin", simpleDateFormat.parse("20/05/2001"), "13 rue cambon"));
                    
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Client c : clientsAInscrire) {
            testIncriptionClient(c);
        }
        
        List<Client> clients = Service.listerTousClients();
        for (Client c: clients)
        {
            System.out.println(c);
        }
        System.out.println("-------------");
        List<Medium> mediums = Service.listerTousMedium();
        for (Medium m: mediums)
        {
            System.out.println(m);
        }
        System.out.println("-------------");
        List<Employe> employes = Service.listerTousEmploye();
        for (Employe e: employes)
        {
            System.out.println(e);
        }
        System.out.println("-------------");
        
        // Création d'une consultation
        Client clientConsulte = clients.get(0);
        Medium mediumConsulte = mediums.get(0);
        Consultation cons = new Consultation(clientConsulte, mediumConsulte);
        Consultation consPrise = Service.demanderConsultation(cons);
        System.out.println(consPrise);
        
        // Acceptation d'une consultation
        try {
            consPrise = Service.accepterConsultation(consPrise);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(consPrise);
        
        // Générer prédictions
        try {
            List<String> predictions = Service.obtenirPredictions(clientConsulte.getProfilAstral(), 1, 2, 3);
            for (String p: predictions)
            {
                System.out.println(p);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Terminer la consultation
        try {
            consPrise = Service.finirConsultation(consPrise);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(consPrise);
        
        // Saisir commentaire
        try {
             Service.saisirCommentaire(cons, "Franchement pas terrible, il a repris un peu d'espoir avec la dernière prédiction, puis s'est rendu compte que le signe collaborateur que le signe collaborateur était le chien. Ducoup temps mort pour ce client.");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Obtenir historique des consultations du client
        List<Consultation> consultationsClient = Service.obtenirHistoriqueConsultationClient(clientConsulte);
        for (Consultation c: consultationsClient)
        {
            System.out.println(c);
            //System.out.println(c.getCommentaire());
        }
        
        // Obtenir historique des consultations de l'employé
        List<Consultation> consultationsEmploye = Service.obtenirHistoriqueConsultationEmploye(cons.getEmploye());
        for (Consultation c: consultationsEmploye)
        {
            System.out.println(c);
            //System.out.println(c.getCommentaire());
        }
        
        
//        testRechercheClient(
//                (long) 0);
//        testRechercheClient(
//                (long) 1);
//        testRechercheClient(
//                (long) 2);
//        testRechercheClient(
//                (long) 3);
//
//        testListerTousClients();
//
//        testerAuthentificationClient(
//                "matheo.joseph@insa-lyon.fr", "admin");
    }

    public static void initialiserClients() {
//        EntityManagerFactory emf = null;
//        EntityManager em = null;
//        EntityTransaction tx = null;
//
//        try {
//            emf = Persistence.createEntityManagerFactory("td1_DB");
//            em = emf.createEntityManager();
//            tx = em.getTransaction();
//            tx.begin();
//            Client[] clients = {
//                new Client("JOSEPH", "Mathéo", "matheo.joseph@insa-lyon.fr", "admin"),
//                new Client("NOT", "Anadmin", "not.anadmin@insa-lyon.fr", "notandmin")
//            };
//
//            for (int i = 0; i < clients.length; ++i) {
//                em.persist(clients[i]);
//            }
//            tx.commit();
//        } catch (Exception ex) {
//            if (tx != null && tx.isActive()) {
//                tx.rollback();
//            }
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }

    }
    
//    public static void initaliserEmploye() {
//        EntityManagerFactory emf = null;
//        EntityManager em = null;
//        EntityTransaction tx = null;
//
//        try {
//            emf = Persistence.createEntityManagerFactory("td1_DB");
//            em = emf.createEntityManager();
//            tx = em.getTransaction();
//            tx.begin();
//            Client[] clients = {
//                new Employe("JOSEPH", "Mathéo", "1365464", "matheo.joseph@insa-lyon.fr", "admin", simpleDateFormat.parse("20/05/2001"), "13 rue cambon", "M", True),
//                new Client("NOT", "Anadmin", "not.anadmin@insa-lyon.fr", "notandmin")
//            };
//
//            for (int i = 0; i < clients.length; ++i) {
//                em.persist(clients[i]);
//            }
//            tx.commit();
//        } catch (Exception ex) {
//            if (tx != null && tx.isActive()) {
//                tx.rollback();
//            }
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//
//    }

    public static void testIncriptionClient(Client c) {
        if (inscrireClient(c) != null) {
            System.out.println("Succès inscription");
        } else {
            System.out.println("Echec inscription");
        }

        System.out.println(c);
    }

    public static void testRechercheClient(Long id) {
        Client c;
        if ((c = trouverClientParId(id)) != null) {
            System.out.println("Succès recherche");
            System.out.println(c);
        } else {
            System.out.println("Echec recherche");
        }
    }

    public static void testListerTousClients() {
        List<Client> c;
        if ((c = listerTousClients()) != null) {
            System.out.println("Succès recherche");

            Iterator<Client> cIterator = c.iterator();
            while (cIterator.hasNext()) {
                System.out.println(cIterator.next());
            }

            //System.out.println(c);
        } else {
            System.out.println("Echec recherche");
        }
    }

    public static void testerAuthentificationClient(String mail, String motDePasse) throws Exception {
        Client c;
        if ((c = authentifierClient(mail, motDePasse)) != null) {
            System.out.println("Succès authentification");
            System.out.println(c);
        } else {
            System.out.println("Echec authentification");
        }
    }
}
