package metier.modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.EtatConsultation;
import metier.modele.Medium;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-04T19:21:47")
@StaticMetamodel(Consultation.class)
public class Consultation_ { 

    public static volatile SingularAttribute<Consultation, Date> dateHeurePriseRDV;
    public static volatile SingularAttribute<Consultation, Employe> employe;
    public static volatile SingularAttribute<Consultation, Client> client;
    public static volatile SingularAttribute<Consultation, Long> id;
    public static volatile SingularAttribute<Consultation, Medium> medium;
    public static volatile SingularAttribute<Consultation, String> commentaire;
    public static volatile SingularAttribute<Consultation, EtatConsultation> etat;

}