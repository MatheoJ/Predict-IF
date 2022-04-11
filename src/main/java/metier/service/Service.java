package metier.service;

import dao.ClientDao;
import dao.ConsultationDao;
import dao.EmployeDao;
import metier.modele.Client;
import dao.JpaUtil;
import dao.MediumDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import metier.modele.Astrologue;
import metier.modele.Cartomancien;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.EtatConsultation;
import metier.modele.Medium;
import metier.modele.ProfilAstral;
import metier.modele.Spirite;
import util.AstroNetApi;
import util.Message;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mjoseph
 */
public class Service {

    public static void initialiserMediums() throws Exception {

        // Attention, on rollback tous les employés si
        // un des employés n'est pas bon !
        JpaUtil.creerContextePersistance();

        List<Medium> mediums = new ArrayList<>();

        mediums.add(new Cartomancien("F", "Mme Irma", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides."));
        mediums.add(new Cartomancien("F", "Endora", "Mes cartes répondront à toutes vos questions personnelles."));
        mediums.add(new Spirite("Marc de café, boule de cristal, oreilles de lapin", "M", "Professeur Tran", "Votre avenir est devant vous !"));
        mediums.add(new Astrologue("École Normale Supérieure d'Astrologie (ENS-Astro)", "2006", "F", "Serena", "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé."));

        mediums.add(new Cartomancien("F", "Mme Jeanne", "Découvrez ecettes de cuisines pour l'inconscient."));
        mediums.add(new Spirite("L'eau boueuse", "M", "Dr. Strange", "Votre histoire n'a pas de secrets pour moi"));
        mediums.add(new Astrologue("Institut National des Astres Supérieurs", "2017", "F", "Seren", "Basée à Paris, Serena essaierea de vous révèler votre avenir probable."));

        mediums.add(new Cartomancien("M", "M Ergot", "Je vous révelerai la suite de cartes qui décrit votre passé et votre avenir."));
        mediums.add(new Spirite("Un ou deux crânes de lapin", "F", "Mme Logithèque", "Je peux appeller vos animaux décédés, surtout les lapins !"));
        mediums.add(new Astrologue("Faculté d'Astrologie", "2012", "M", "M Ross", "Adepte des astres."));

        mediums.add(new Cartomancien("F", "Mme Joker", "Je m'ennuie, vous aussi ? Discutons-en !"));
        mediums.add(new Spirite("Le ragoût de lapin", "M", "Grippay", "Votre avenir est devant vous, dans ce ragoût !"));
        mediums.add(new Astrologue("DUT Astrologie", "2020", "M", "Pierre", "Votre avenir coule comme une pierre."));

        mediums.add(new Cartomancien("M", "Routard", "Un coin de repos entre copains."));
        mediums.add(new Spirite("Un étang", "M", "Professeur Cadavérique", "Consultation dans la nature !"));

        MediumDao mediumDao = new MediumDao();
        try {
            JpaUtil.ouvrirTransaction();
            for (Medium m : mediums) {
                mediumDao.creer(m);
                System.out.println("Medium crée: " + m);
            }
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            throw new Exception("Les médiums n'ont pas pu être initalisés, veuillez réessayer plus tard.");
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public static void initialiserEmployes() throws Exception {

        // Attention, on rollback tous les employés si
        // un des employés n'est pas bon !
        JpaUtil.creerContextePersistance();

        List<Employe> employes = new ArrayList<>();

        employes.add(new Employe("JOSEPH", "Mathéo", "0930818759", "matheo.joseph@insa-lyon.fr", "admin", "M", true));
        employes.add(new Employe("JOSEPH", "Pasmathéo", "0583381026", "IUYTR@gmail.com", "admin", "M", true));
        employes.add(new Employe("NOT", "Anadmin", "0295682848", "not.anadmin@insa-lyon.fr", "mpd", "M", true));
        employes.add(new Employe("CHIBAH", "Pierre-Henri", "0189275148", "juan-felix.hiuellon@free.fr", "mpd", "M", true));
        employes.add(new Employe("GAYEL", "Camille", "0428007020", "cgayel5170@free.fr", "mpd", "F", true));
        employes.add(new Employe("THALOT", "Libin", "0663518853", "libin.thalot@free.fr", "mpd", "M", true));
        employes.add(new Employe("GISCHE", "Vincent", "0819372585", "vgische@laposte.net", "mpd", "M", true));
        employes.add(new Employe("IL FATHO", "Anéva", "0286066103", "ailfatho2061@outlook.com", "mpd", "F", true));
        employes.add(new Employe("VIUXEUR", "Sébastien", "0756530923", "sviuxeur9319@gmail.com", "mpd", "M", true));
        employes.add(new Employe("MILREC", "Charlotte", "0704616866", "cmilrec9009@yahoo.com", "mpd", "F", true));
        employes.add(new Employe("SEMONNIT", "Walid", "0287856816", "wsemonnit8079@outlook.com", "mpd", "M", true));
        employes.add(new Employe("BONNOARI", "Clément", "0418488995", "cbonnoari@outlook.com", "mpd", "M", true));
        employes.add(new Employe("JAMBOT", "Jonathan", "0831895356", "jonathan.jambot@outlook.com", "mpd", "M", true));

        EmployeDao employeDao = new EmployeDao();
        try {
            JpaUtil.ouvrirTransaction();
            for (Employe e : employes) {
                employeDao.creer(e);
                System.out.println("Employé crée: " + e);
            }
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            throw new Exception("Les employés n'ont pas pu être initalisés, veuillez réessayer plus tard.");
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public static Client inscrireClient(Client c) {
        JpaUtil.creerContextePersistance();

        Client res = c;

        // Génération du profil astral
        AstroNetApi astroApi = new AstroNetApi();

        ProfilAstral profilAstral;
        try {
            List<String> profil;
            profil = astroApi.getProfil(c.getPrenom(), c.getDateNaissance());
            String signeZodiaque = profil.get(0);
            String signeChinois = profil.get(1);
            String couleurBonheur = profil.get(2);
            String animalTotem = profil.get(3);

            profilAstral = new ProfilAstral(signeZodiaque, signeChinois, couleurBonheur, animalTotem);
            c.setProfilAstrale(profilAstral);
        } catch (IOException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }

        ClientDao clientDao = new ClientDao();
        try {
            JpaUtil.ouvrirTransaction();
            clientDao.creer(c);
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            res = null;
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();

            // Génération du mail de confirmation d'inscription
            StringWriter corps = new StringWriter();
            PrintWriter mailWriter = new PrintWriter(corps);

            mailWriter.println("Bonjour,");
            mailWriter.println();
            mailWriter.println("  Ceci est un mail de confirmation de votre inscription sur Predict'If,");
            mailWriter.println("  le site référence de la prédiction !");
            mailWriter.println("  Cordialement,");
            mailWriter.println();
            mailWriter.println("    L'équipe de Predict'If");
            // Envoi du mail de confirmation d'inscription
            Message.envoyerMail(
                    "support.predictif@gmail.com",
                    c.getMail(),
                    "[PREDICT'IF] Confirmation d'inscription",
                    corps.toString()
            );

        }
        return res;
    }

    public static Client trouverClientParId(Long id) {
        JpaUtil.creerContextePersistance();
        ClientDao clientDao = new ClientDao();
        Client res = clientDao.chercherParId(id);
        JpaUtil.fermerContextePersistance();

        // On enlève le mot de passe des données envoyées
        if (res != null) {
            res.setMotDePasse("N/A");
        }

        return res;
    }

    public static Employe trouverEmployeParId(Long id) {
        JpaUtil.creerContextePersistance();
        EmployeDao employeDao = new EmployeDao();
        Employe res = employeDao.chercherParId(id);
        JpaUtil.fermerContextePersistance();

        // On enlève le mot de passe des données envoyées
        if (res != null) {
            res.setMotDePasse("N/A");
        }

        return res;
    }

    public static Medium trouverMediumParId(Long id) {
        JpaUtil.creerContextePersistance();
        MediumDao mediumDao = new MediumDao();
        Medium res = mediumDao.chercherParId(id);
        JpaUtil.fermerContextePersistance();

        return res;
    }

    public static List<Client> listerTousClients() {
        JpaUtil.creerContextePersistance();
        ClientDao clientDao = new ClientDao();
        List<Client> res = clientDao.chercherTous();
        JpaUtil.fermerContextePersistance();

        return res;
    }

    public static List<Employe> listerTousEmploye() {
        JpaUtil.creerContextePersistance();
        EmployeDao employeDao = new EmployeDao();
        List<Employe> res = employeDao.chercherTous();
        JpaUtil.fermerContextePersistance();

        return res;
    }

    public static List<Medium> listerTousMedium() {
        JpaUtil.creerContextePersistance();
        MediumDao mediumDao = new MediumDao();
        List<Medium> res = mediumDao.chercherTous();
        JpaUtil.fermerContextePersistance();

        return res;
    }

    public static Employe authentifierEmploye(String mail, String motDePasse) {
        Employe res;
        try {
            JpaUtil.creerContextePersistance();
            EmployeDao employeDao = new EmployeDao();
            res = employeDao.authentifier(mail, motDePasse);
        } catch (NoResultException e) {
            return null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }

    public static Client authentifierClient(String mail, String motDePasse) {
        Client res;
        try {
            JpaUtil.creerContextePersistance();
            ClientDao clientDao = new ClientDao();
            res = clientDao.authentifier(mail, motDePasse);
        } catch (NoResultException e) {
            return null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }

    public static Consultation demanderConsultation(Consultation cons) {

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            EmployeDao employeDao = new EmployeDao();
            Employe employe = employeDao.estDisponible(cons.getMedium().getGenre());

            // Si aucun employé ne correspond aux critères et n'est disponible,
            // renvoie null.
            if (employe == null) {
                cons = null;
            } else {

                cons.setEmploye(employe);

                ConsultationDao consultationDao = new ConsultationDao();
                consultationDao.creer(cons);

                // L'employé n'est plus disponible pour d'autres consultations que celle-ci
                employe.setDisponibilite(false);
                // Enregistrement de la modification de l'employé               
                employeDao.modifier(employe);

                JpaUtil.validerTransaction();

                // Envoi d'une notification de consultation prise
                // à l'employé:
                StringWriter message = new StringWriter();
                PrintWriter notificationWriter = new PrintWriter(message);

                notificationWriter.println("Bonjour " + employe.getPrenom() + ". Consultation requise pour " + cons.getClient().getPrenom() + " "
                        + cons.getClient().getNom().toUpperCase() + ". Médium à incarner: " + cons.getMedium().getDenomination());

                Message.envoyerNotification(
                        cons.getEmploye().getNumTel(),
                        message.toString()
                );
            }

        } catch (Exception e) {
            cons = null;
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return cons;
    }

    public static Consultation accepterConsultation(Consultation cons) throws Exception {

        if (cons.getEtat() != EtatConsultation.EN_ATTENTE) {
            throw new Exception("La consultation n'est pas en attente.");
        }

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            // La consultation est en cours
            cons.setEtat(EtatConsultation.EN_COURS);
            ConsultationDao consultationDao = new ConsultationDao();
            consultationDao.modifier(cons);

            JpaUtil.validerTransaction();

            // Envoi d'une notification au client
            // pour lui indiquer que sa consultation a démarré
            StringWriter message = new StringWriter();
            PrintWriter notificationWriter = new PrintWriter(message);
            // La date est sous la forme: 04/04/2022 à 15h32
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/YYYY 'à' HH'h'mm");
            notificationWriter.println("Bonjour " + cons.getClient().getPrenom() + ". J'ai bien reçu votre demande de consultation du " + dateFormatter.format(cons.getDateHeurePriseRDV()) + ".");
            notificationWriter.println("Vous pouvez dès a présent me contacter au " + cons.getEmploye().getNumTel() + ". À tout de suite ! Médiumiquement vôtre, " + cons.getMedium().getDenomination());

            Message.envoyerNotification(
                    cons.getClient().getNumTel(),
                    message.toString()
            );

        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            throw new Exception("La consultation n'a pas pu être acceptée, veuillez réessayer plus tard.");
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return cons;
    }

    public static Consultation finirConsultation(Consultation cons) throws Exception {

        if (cons.getEtat() != EtatConsultation.EN_COURS) {
            throw new Exception("La consultation que l'on cherche à fermer n'est pas en cours !");
        }

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            // La consultation est en cours
            cons.setEtat(EtatConsultation.FINI);
            ConsultationDao consultationDao = new ConsultationDao();
            consultationDao.modifier(cons);

            // On incrémente le nombre de consultations faites par l'employé
            Employe employe = cons.getEmploye();
            employe.setDisponibilite(true);
            employe.setNbConsultation(employe.getNbConsultation() + 1);
            EmployeDao employeDao = new EmployeDao();
            employeDao.modifier(employe);

            // On incrémente le nombre de consultations prises avec le médium
            Medium medium = cons.getMedium();
            medium.setNbConsultation(medium.getNbConsultation() + 1);
            MediumDao mediumDao = new MediumDao();
            mediumDao.modifier(medium);

            JpaUtil.validerTransaction();
        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            throw new Exception("La consultation n'a pas pu être acceptée, veuillez réessayer plus tard.");
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return cons;
    }

    public static void saisirCommentaire(Consultation cons, String comm) throws Exception {
        if (cons.getEtat() != EtatConsultation.FINI) {
            throw new Exception("Veuillez terminer la consultation avant de saisir un commentaire.");
        }

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            // La consultation est en cours
            cons.setCommentaire(comm);
            ConsultationDao consultationDao = new ConsultationDao();
            consultationDao.modifier(cons);

            JpaUtil.validerTransaction();

        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            throw new Exception("Le commentaire n'a pas pu être saisi, veuillez réessayer plus tard.");
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public static List<String> obtenirPredictions(ProfilAstral profilAstral, int amour, int sante, int travail) throws IOException, Exception {
        if (amour > 4 || amour < 0 || sante > 4 || sante < 0 || travail > 4 || travail < 0) {
            throw new Exception("Les notes saisies doivent être comprises entre 0 et 4.");
        }
        AstroNetApi astroApi = new AstroNetApi();

        List<String> predictions = astroApi.getPredictions(profilAstral.getCouleurBonheur(), profilAstral.getAnimalTotem(), amour, sante, travail);

        return predictions;
    }

    public static List<Consultation> obtenirHistoriqueConsultationClient(Client c) {
        JpaUtil.creerContextePersistance();
        ClientDao clientDao = new ClientDao();
        List<Consultation> consultations = clientDao.obtenirConsultation(c);
        // Le client ne doit pas pouvoir accéder aux commentaires qui lui sont associés.
        // Ils doivent donc être supprimés avant de lui envoyer son historique de consultation.
        for (Consultation cons : consultations) {
            cons.setCommentaire("N/A");
        }
        JpaUtil.fermerContextePersistance();

        return consultations;
    }

    public static List<Consultation> obtenirHistoriqueConsultationEmploye(Employe e) {
        JpaUtil.creerContextePersistance();
        EmployeDao employeDao = new EmployeDao();
        List<Consultation> consultations = employeDao.obtenirConsultation(e);
        JpaUtil.fermerContextePersistance();

        return consultations;
    }

    public static List<Medium> obtenirTop5Medium() {
        List<Medium> mediums;
        JpaUtil.creerContextePersistance();

        MediumDao mediumDao = new MediumDao();
        mediums = mediumDao.obtenirTop5();

        JpaUtil.fermerContextePersistance();
        return mediums;
    }

    public static Consultation getConsultationActuelleClient(Client c) {
        Consultation consultation;
        try {
            JpaUtil.creerContextePersistance();
            ClientDao clientDao = new ClientDao();
            consultation = clientDao.obtenirConsultationActuelle(c);
        } catch (NoResultException e) {
            return null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return consultation;
    }

    public static Consultation getConsultationActuelleEmploye(Employe e) {
        Consultation consultation;
        try {
            JpaUtil.creerContextePersistance();
            EmployeDao employeDao = new EmployeDao();
            consultation = employeDao.obtenirConsultationActuelle(e);
        } catch (NoResultException ex) {
            return null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return consultation;
    }
}
