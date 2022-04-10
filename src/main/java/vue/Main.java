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
import static metier.service.Service.authentifierEmploye;
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

    public static void main(String[] args) throws ParseException {

        // Initialisation de la persistance
        JpaUtil.init();

        // SERVICES -----------------------------------------------

        System.out.println("--------------------------------");
        System.out.println("Test: Initialisation et listerTous des mediums");
        try {
            // Initialisation des Mediums
            Service.initialiserMediums();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Medium> mediums = Service.listerTousMedium();
        for (Medium m : mediums) {
            System.out.println(m);
        }
        System.out.println("--------------------------------");
        
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        
        System.out.println("--------------------------------");
        System.out.println("Test: Inscription des clients");
       List<Client> clientsAInscrire = new ArrayList<>();
        try {
            clientsAInscrire.add(new Client("JOSEPH", "Mathéo", "1365464", "matheo.joseph@insa-lyon.fr", "admin", simpleDateFormat.parse("20/05/2001"), "13 rue cambon"));
            clientsAInscrire.add(new Client("NOT", "Anadmin", "1365464", "not.anadmin@insa-lyon.fr", "notandmin", simpleDateFormat.parse("20/05/2001"), "13 rue cambon"));
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Client c : clientsAInscrire) {
            if (inscrireClient(c) == null)
            {
                System.out.println("ERREUR: Le client n'a pas pu être inscrit: " + c);
            }
        }
        List<Client> clients = Service.listerTousClients();
        for (Client c : clients) {
            System.out.println(c);
        }
        
        System.out.println("--------------------------------");
        System.out.println("Test: Initialisation d'un client non unique");
        Client nonUniqueClient = new Client("JOSEPH", "Mathéo", "1365464", "matheo.joseph@insa-lyon.fr", "admin", simpleDateFormat.parse("20/05/2001"), "13 rue cambon");
        if (inscrireClient(nonUniqueClient) == null)
        {
            System.out.println("ERREUR: Le client n'a pas pu être inscrit: " + nonUniqueClient);
        }
        clients = Service.listerTousClients();
        for (Client c : clients) {
            System.out.println(c);
        }
        System.out.println("--------------------------------");

        System.out.println("--------------------------------");
        System.out.println("Test: Création d'une consultation sans employés disponible");
        // Création d'une consultation
        Client clientConsulte = clients.get(0);
        Medium mediumConsulte = mediums.get(0);
        Consultation cons = new Consultation(clientConsulte, mediumConsulte);
        Consultation consPrise = Service.demanderConsultation(cons);
        System.out.println(consPrise);
        System.out.println("--------------------------------");
        
        System.out.println("--------------------------------");
        System.out.println("Test: Initialisation des employés");
        try {
            // Initialisation des employés
            Service.initialiserEmployes();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Employe> employes = Service.listerTousEmploye();
        for (Employe e : employes) {
            System.out.println(e);
        }
        System.out.println("--------------------------------");
        
        System.out.println("--------------------------------");
        System.out.println("Test: Obtenir la consultation actuelle\r\n"
                + "d'un client qui n'a pas de consultation actuelle");
        // Obtenir la consultation actuelle du client
        Consultation consActuelleClient = Service.getConsultationActuelleClient(clients.get(0));
        System.out.println(consActuelleClient);
        System.out.println("--------------------------------");
        
        System.out.println("--------------------------------");
        System.out.println("Test: Obtenir la consultation actuelle\r\n"
                + "d'un employé qui n'a pas de consultation actuelle");
        // Obtenir la consultation actuelle de l'employé
        Consultation consActuelleEmploye = Service.getConsultationActuelleEmploye(employes.get(0));
        System.out.println(consActuelleEmploye);
        System.out.println("--------------------------------");
        
        System.out.println("--------------------------------");
        System.out.println("Test: Création d'une consultation avec employés disponible");
        // Création d'une consultation
        clientConsulte = clients.get(0);
        mediumConsulte = mediums.get(0);
        cons = new Consultation(clientConsulte, mediumConsulte);
        consPrise = Service.demanderConsultation(cons);
        System.out.println(consPrise);
        System.out.println("--------------------------------");
        

        System.out.println("--------------------------------");
        System.out.println("Test: Obtenir la consultation actuelle du client");
        // Obtenir la consultation actuelle du client
        consActuelleClient = Service.getConsultationActuelleClient(clientConsulte);
        System.out.println(consActuelleClient);
        System.out.println("--------------------------------");

        System.out.println("--------------------------------");
        System.out.println("Test: Obtenir la consultation actuelle de l'employé");
        // Obtenir la consultation actuelle de l'employé
        consActuelleEmploye = Service.getConsultationActuelleEmploye(consPrise.getEmploye());
        System.out.println(consActuelleEmploye);
        System.out.println("--------------------------------");

        System.out.println("--------------------------------");
        System.out.println("Test: Acceptation d'une consultation qui n'existe pas");
        // Acceptation d'une consultation
        try {
            consPrise = Service.accepterConsultation(null);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(consPrise);
        System.out.println("--------------------------------");
        
        System.out.println("--------------------------------");
        System.out.println("Test: Acceptation d'une consultation");
        // Acceptation d'une consultation
        try {
            consPrise = Service.accepterConsultation(consPrise);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(consPrise);
        System.out.println("--------------------------------");

        System.out.println("--------------------------------");
        System.out.println("Test: Générer prédictions");
        // Générer prédictions
        try {
            List<String> predictions = Service.obtenirPredictions(clientConsulte.getProfilAstral(), 1, 2, 3);
            for (String p : predictions) {
                System.out.println(p);
            }
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("--------------------------------");
        
        System.out.println("--------------------------------");
        System.out.println("Test: Générer prédictions avec des notes\r\n"
                + "qui ne sont pas entres 0 et 4");
        // Générer prédictions
        try {
            List<String> predictions = Service.obtenirPredictions(clientConsulte.getProfilAstral(), -1, 2, 3);
            for (String p : predictions) {
                System.out.println(p);
            }
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("--------------------------------");
        
        System.out.println("--------------------------------");
        System.out.println("Test: Générer prédictions pour un client inexistant");
        // Générer prédictions
        try {
            List<String> predictions = Service.obtenirPredictions(null, 1, 2, 3);
            for (String p : predictions) {
                System.out.println(p);
            }
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("--------------------------------");

        System.out.println("--------------------------------");
        System.out.println("Test: Terminer la consultation");
        // Terminer la consultation
        try {
            consPrise = Service.finirConsultation(consPrise);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(consPrise);
        System.out.println("--------------------------------");

        System.out.println("--------------------------------");
        System.out.println("Test: Saisir commentaire");
        // Saisir commentaire
        try {
            Service.saisirCommentaire(cons, "Franchement pas terrible, il a repris un peu d'espoir avec la dernière prédiction, puis s'est rendu compte que le signe collaborateur que le signe collaborateur était le chien. Ducoup temps mort pour ce client.");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("--------------------------------");

        System.out.println("--------------------------------");
        System.out.println("Test: Obtenir historique des consultations du client");
        // Obtenir historique des consultations du client
        List<Consultation> consultationsClient = Service.obtenirHistoriqueConsultationClient(clientConsulte);
        for (Consultation c : consultationsClient) {
            System.out.println(c);
            //System.out.println(c.getCommentaire());
        }
        System.out.println("--------------------------------");

        System.out.println("--------------------------------");
        System.out.println("Test: Obtenir historique des consultations de l'employé");
        // Obtenir historique des consultations de l'employé
        List<Consultation> consultationsEmploye = Service.obtenirHistoriqueConsultationEmploye(cons.getEmploye());
        for (Consultation c : consultationsEmploye) {
            System.out.println(c);
            //System.out.println(c.getCommentaire());
        }
        System.out.println("--------------------------------");

//        System.out.println("--------------------------------");
//        System.out.println("Test: Obtenir le Top 5 des médiums");
//        // Obtenir le Top 5 des médiums
//        List<Medium> top5medium = Service.obtenirTop5Medium();
//        for (Medium m : top5medium) {
//            System.out.println(m.getDenomination());
//        }
//        System.out.println("--------------------------------");


        System.out.println("--------------------------------");
        System.out.println("Test: Authentification d'un client existant");
        // Authentification d'un client existant
        System.out.println(authentifierClient("matheo.joseph@insa-lyon.fr", "admin"));
        System.out.println("--------------------------------");
        
        System.out.println("--------------------------------");
        System.out.println("Test: Authentification d'un client non existant (mauvais identifiants)");
        // Authentification d'un client non existant (mauvais identifiants)
        System.out.println(authentifierClient("matheo.joseph@insa-lyon.fr", "notanadmin"));
        System.out.println("--------------------------------");
        
        System.out.println("--------------------------------");
        System.out.println("Test: Authentification d'un employe existant");
        // Authentification d'un client existant
        System.out.println(authentifierEmploye("libin.thalot@free.fr", "mpd"));
        System.out.println("--------------------------------");
        
        System.out.println("--------------------------------");
        System.out.println("Test: Authentification d'un employe non existant (mauvais identifiants)");
        // Authentification d'un client existant
        System.out.println(authentifierEmploye("libin.thalot@free.fr", "not_mpd"));
        System.out.println("--------------------------------");
        
        // SERVICES -----------------------------------------------
    }

//    public static void initialiserClients() {
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
//    }
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
}