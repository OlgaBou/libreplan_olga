import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestClassGestionProfil {
	WebDriver driver;
	WebDriverWait wait;

	@Before

	public void setup() throws Exception {
		

		//Initialisation du driver
		System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/geckodriver.exe");
		driver = new FirefoxDriver();
		
		//initialiser le fichier.properties
		Properties propertyJDD = new Properties();
	    propertyJDD.load(new FileInputStream("src/main/resources/JDD.properties/JDD.properties"));
	    
	    //aller sur le site du challenge
	    driver.get(propertyJDD.getProperty("URL"));
	    driver.manage().window().maximize();

	}

	@After

	public void teardown() {
		driver.quit();
	}

	
	@Test //creation Machine
	public void test() throws Exception {
		wait = new WebDriverWait(driver, 15); // Explicit wait
		
		  //vérifier que la page est correcte
	    assertEquals("LibrePlan: accès utilisateur", driver.getTitle());
	    
	    //initialiser la PageConnexion
	    PageConnexion page_connexion = PageFactory.initElements(driver, PageConnexion.class);
	    
	    //se logger
	    PageAccueil page_accueil = page_connexion.logIn(driver, "admin", "admin");
	    
	    WebElement fil_d_ariane_calendrier = wait.until(ExpectedConditions.visibilityOf(page_accueil.fil_d_ariane_calendrier)); // Explicit wait
	    
	    //vérifier que la page est correcte
		assertTrue(page_accueil.verifPage(fil_d_ariane_calendrier));
		
		PageProfils page_profils = page_accueil.chooseOptionMenuProfils(page_accueil.Onglet_Configuration, page_accueil.Profils);
		
		//vérifier que la page est correcte
		WebElement titreProfilsListe = wait.until(ExpectedConditions.visibilityOf(page_profils.titreProfilsListe)); // Explicit wait
		assertTrue(page_profils.verifPage(titreProfilsListe));
		assertTrue(page_profils.verifPage(page_profils.nomProfilColonne));
		assertTrue(page_profils.verifPage(page_profils.actionsColonne));
		assertTrue(page_profils.verifPage(page_profils.iconeModifier));
		assertTrue(page_profils.verifPage(page_profils.iconeSupprimer));
		assertTrue(page_profils.verifPage(page_profils.buttonCreer));
		
		//créer un profil
		page_profils.creationProfil();
		
		//vérifier que la page est correcte
		WebElement titreCreerProfil = wait.until(ExpectedConditions.visibilityOf(page_profils.titreCreerProfil));
		assertTrue(page_profils.verifPage(titreCreerProfil));
		
		//vérifier la page "Créer Profil" contenant un onglet "Données de profil"
		assertTrue(page_profils.verifPage(page_profils.ongletDonnesProfil));
		
		//vérifier que le champ de saisie "Nom" non renseigné
		assertTrue(page_profils.nomProfilChamp.getAttribute("value").trim().isEmpty());
		
		//vérifier q'un bloc "Association avec les rôles" contient : une liste déroulante sans valeur affichée par défaut, un bouton [Ajouter un rôle],un tableau avec les colonnes "Nom du rôle" et "Actions"
		assertTrue(page_profils.verifPage(page_profils.champAssociationRoles));
		assertTrue(page_profils.verifPage(page_profils.menuDeroulantRoleDefault));
		assertTrue(page_profils.verifPage(page_profils.ajouterRoleButton));
		assertTrue(page_profils.verifPage(page_profils.nomRoleColonne));
		assertTrue(page_profils.verifPage(page_profils.nomAction));
		
		//vérifier l'affichage des boutons [Enregistrer], [Sauver et continuer] et [Annuler]
		
		assertTrue(page_profils.verifPage(page_profils.enregistrerButton));
		assertTrue(page_profils.verifPage(page_profils.saverEtContinuerButton));
		assertTrue(page_profils.verifPage(page_profils.annulerButton));
		
		//créer un profil
		
		page_profils.ajouterProfil(driver, "profil 1");
		
		//Dans la colonne "Actions" associé à ce rôle, présence d'une icône représentant une poubelle.
		assertTrue(page_profils.verifPage(page_profils.roleAjouteTableau));
		assertTrue(page_profils.verifPage(page_profils.iconePoubelle));
		
		//Une infobulle est affichée et contient le message "Supprimer".
		
		 assertEquals("Supprimer", page_profils.iconePoubelle.getAttribute("title"));
	}
	

}
