package metier.modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.ProfilAstral;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-04T19:21:47")
@StaticMetamodel(Client.class)
public class Client_ extends Personne_ {

    public static volatile SingularAttribute<Client, Date> dateNaissance;
    public static volatile SingularAttribute<Client, String> addrPostale;
    public static volatile SingularAttribute<Client, ProfilAstral> profilAstrale;

}