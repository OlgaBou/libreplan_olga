import java.util.Properties;

public interface PageInterface {
	
    Properties propertyJDD = SocleTechnique.initScenario();

    String Username=propertyJDD.getProperty("Username");
    String Password=propertyJDD.getProperty("Password");
    String NewNomProfil=propertyJDD.getProperty("NewNomProfil");
    String NomProfil=propertyJDD.getProperty("NomProfil");
    String ID=propertyJDD.getProperty("ID");
    String Nom_utilisateur=propertyJDD.getProperty("Nom_utilisateur");
    String Password_utilisateur=propertyJDD.getProperty("Password_utilisateur");
    String Email=propertyJDD.getProperty("Email");

}
