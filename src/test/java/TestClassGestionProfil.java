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
		
		  //v�rifier que la page est correcte
	    assertEquals("LibrePlan: acc�s utilisateur", driver.getTitle());
	    
	    //initialiser la PageConnexion
	    PageConnexion page_connexion = PageFactory.initElements(driver, PageConnexion.class);
	    
	    //se logger
	    PageAccueil page_accueil = page_connexion.logIn(driver, "admin", "admin");
	    
	    WebElement fil_d_ariane_calendrier = wait.until(ExpectedConditions.visibilityOf(page_accueil.fil_d_ariane_calendrier)); // Explicit wait
	    
	    //v�rifier que la page est correcte
		assertTrue(page_accueil.verifPage(fil_d_ariane_calendrier));
		
		PageProfils page_profils = page_accueil.chooseOptionMenuProfils(page_accueil.Onglet_Configuration, page_accueil.Profils);
		
		//v�rifier que la page est correcte
		WebElement titreProfilsListe = wait.until(ExpectedConditions.visibilityOf(page_profils.titreProfilsListe)); // Explicit wait
		assertTrue(page_profils.verifPage(titreProfilsListe));
		assertTrue(page_profils.verifPage(page_profils.nomProfilColonne));
		assertTrue(page_profils.verifPage(page_profils.actionsColonne));
		assertTrue(page_profils.verifPage(page_profils.iconeModifier));
		assertTrue(page_profils.verifPage(page_profils.iconeSupprimer));
		assertTrue(page_profils.verifPage(page_profils.buttonCreer));
		
		//cr�er un profil
		page_profils.creationProfil();
		
		//v�rifier que la page est correcte
		WebElement titreCreerProfil = wait.until(ExpectedConditions.visibilityOf(page_profils.titreCreerProfil));
		assertTrue(page_profils.verifPage(titreCreerProfil));
		
		//v�rifier la page "Cr�er Profil" contenant un onglet "Donn�es de profil"
		assertTrue(page_profils.verifPage(page_profils.ongletDonnesProfil));
		
		//v�rifier que le champ de saisie "Nom" non renseign�
		assertTrue(page_profils.nomProfilChamp.getAttribute("value").trim().isEmpty());
		
		//v�rifier q'un bloc "Association avec les r�les" contient : une liste d�roulante sans valeur affich�e par d�faut, un bouton [Ajouter un r�le],un tableau avec les colonnes "Nom du r�le" et "Actions"
		assertTrue(page_profils.verifPage(page_profils.champAssociationRoles));
		assertTrue(page_profils.verifPage(page_profils.menuDeroulantRoleDefault));
		assertTrue(page_profils.verifPage(page_profils.ajouterRoleButton));
		assertTrue(page_profils.verifPage(page_profils.nomRoleColonne));
		assertTrue(page_profils.verifPage(page_profils.nomAction));
		
		//v�rifier l'affichage des boutons [Enregistrer], [Sauver et continuer] et [Annuler]
		
		assertTrue(page_profils.verifPage(page_profils.enregistrerButton));
		assertTrue(page_profils.verifPage(page_profils.saverEtContinuerButton));
		assertTrue(page_profils.verifPage(page_profils.annulerButton));
		
		//cr�er un profil
		
		page_profils.ajouterProfil(driver, "profil 1");
		
		//Dans la colonne "Actions" associ� � ce r�le, pr�sence d'une ic�ne repr�sentant une poubelle.
		assertTrue(page_profils.verifPage(page_profils.roleAjouteTableau));
		assertTrue(page_profils.verifPage(page_profils.iconePoubelle));
		
		//Une infobulle est affich�e et contient le message "Supprimer".
		
		 assertEquals("Supprimer", page_profils.iconePoubelle.getAttribute("title"));
	}
	

}
