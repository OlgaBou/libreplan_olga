import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestClassGestionProfil implements PageInterface {
	WebDriver driver;
	WebDriverWait wait;
	Logger logger = Logger.getLogger(TestClassGestionProfil.class.getName());

	@Before

	public void setup() throws Exception {

		// Initialisation du driver
		System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/geckodriver.exe");
		driver = new FirefoxDriver();

		// initialiser le fichier.properties
		Properties propertyJDD = new Properties();
		propertyJDD.load(new FileInputStream("src/main/resources/JDD.properties/JDD.properties"));

		// aller sur le site du challenge
		driver.get(propertyJDD.getProperty("URL"));
		driver.manage().window().maximize();

	}

	@After

	public void teardown() {

		driver.quit();
	}

	@Test // creation Machine
	public void test() throws Exception {
		wait = new WebDriverWait(driver, 15); // Explicit wait

		// Instanciation du FileHandler qui va écrire dans le fichier app.log.
		// Each new message will be appended at the at of the log file.
		FileHandler fileHandler = new FileHandler("TestClassGestionProfil.log", true);
		logger.addHandler(fileHandler);

		/*
		 * if (logger.isLoggable(Level.INFO)) { logger.info("Information message"); }
		 * 
		 * if (logger.isLoggable(Level.WARNING)) { logger.warning("Warning message"); }
		 */

		try {

			logger.info("\ndeuxième test".toUpperCase());
			// vérifier que la page est correcte
			assertEquals("LibrePlan: accès utilisateur", driver.getTitle());

			// initialiser la PageConnexion
			logger.info("initialiser la PageConnexion".toUpperCase());
			PageConnexion page_connexion = PageFactory.initElements(driver, PageConnexion.class);

			// se logger
			logger.info("se logger".toUpperCase());
			PageAccueil page_accueil = page_connexion.logIn(driver, Username, Password);

			WebElement fil_d_ariane_calendrier = wait
					.until(ExpectedConditions.visibilityOf(page_accueil.fil_d_ariane_calendrier)); // Explicit wait

			// vérifier que la page est correcte
			assertTrue(page_accueil.verifPage(fil_d_ariane_calendrier));

			// ouvrir la page Profils
			logger.info("ouvrir la page Profils".toUpperCase());
			PageProfils page_profils = page_accueil.chooseOptionMenuProfils(page_accueil.Onglet_Configuration,
					page_accueil.Profils);

			// vérifier que la page est correcte
			WebElement titreProfilsListe = wait.until(ExpectedConditions.visibilityOf(page_profils.titreProfilsListe)); // Explicit
																														// //
																														// wait
			assertTrue(page_profils.verifPage(titreProfilsListe));
			assertTrue(page_profils.verifPage(page_profils.nomProfilColonne));
			assertTrue(page_profils.verifPage(page_profils.actionsColonne));
			assertTrue(page_profils.verifPage(page_profils.iconeModifier));
			assertTrue(page_profils.verifPage(page_profils.iconeSupprimer));
			assertTrue(page_profils.verifPage(page_profils.buttonCreer));

			// créer un profil
			logger.info("créer un profil".toUpperCase());
			page_profils.creationProfil();

			// vérifier que la page est correcte
			WebElement titreCreerProfil = wait.until(ExpectedConditions.visibilityOf(page_profils.titreCreerProfil));
			assertTrue(page_profils.verifPage(titreCreerProfil));

			// vérifier la page "Créer Profil" contenant un onglet "Données de profil"
			assertTrue(page_profils.verifPage(page_profils.ongletDonnesProfil));

			// vérifier que le champ de saisie "Nom" non renseigné
			assertTrue(page_profils.nomProfilChamp.getAttribute("value").trim().isEmpty());

			// vérifier q'un bloc "Association avec les rôles" contient : une liste
			// déroulante sans valeur affichée par défaut, un bouton [Ajouter un rôle],un
			// tableau avec les colonnes "Nom du rôle" et "Actions"
			assertTrue(page_profils.verifPage(page_profils.champAssociationRoles));
			assertTrue(page_profils.verifPage(page_profils.menuDeroulantRoleDefault));
			assertTrue(page_profils.verifPage(page_profils.ajouterRoleButton));
			assertTrue(page_profils.verifPage(page_profils.nomRoleColonne));
			assertTrue(page_profils.verifPage(page_profils.nomAction));

			// vérifier l'affichage des boutons [Enregistrer], [Sauver et continuer] et
			// [Annuler]

			assertTrue(page_profils.verifPage(page_profils.enregistrerButton));
			assertTrue(page_profils.verifPage(page_profils.saverEtContinuerButton));
			assertTrue(page_profils.verifPage(page_profils.annulerButton));

			// ajouter un profil
			logger.info("ajouter un profil".toUpperCase());
			page_profils.ajouterProfil(driver, NomProfil);

			// ajouter un rôle
			logger.info("ajouter un rôle".toUpperCase());
			page_profils.ajouterRole(page_profils.importerDesProjetsRole);

			// Dans la colonne "Actions" associé à ce rôle, présence d'une icône
			// représentant une poubelle.
			assertTrue(page_profils.verifPage(page_profils.importerDesProjetsAjouteTableau));
			assertTrue(page_profils.verifPage(page_profils.iconePoubelle));

			// Une infobulle est affichée et contient le message "Supprimer".

			assertEquals("Supprimer", page_profils.iconePoubelle.getAttribute("title"));

			// ajouter 2e et 3e rôles
			logger.info("ajouter 2e et 3e rôles".toUpperCase());
			page_profils.ajouterRole(page_profils.categorieCoutRole);
			page_profils.ajouterRole(page_profils.libellesRole);

			assertTrue(page_profils.verifPage(page_profils.importerDesProjetsAjouteTableau));
			assertTrue(page_profils.verifPage(page_profils.libellesAjouteTableau));
			assertTrue(page_profils.verifPage(page_profils.categorieCoutRoleAjouteTableau));

			// sélectionner un rôle déjà ajouté dans la liste déroulante
			logger.info("un rôle déjà ajouté dans la liste déroulante".toUpperCase());
			page_profils.ajouterRole(page_profils.importerDesProjetsRole);

			// vérifier qu'aucune action n'est effectuée
			assertTrue(page_profils.verifPage(page_profils.importerDesProjetsAjouteTableau));

			// supprimer le rôle ajouté
			logger.info("supprimer le rôle ajouté".toUpperCase());
			page_profils.supprimerRole();
			page_profils.supprimerRole();
			Thread.sleep(1000);
			page_profils.supprimerRole2();
			// page_profils.iconePoubelle3.click();
			Thread.sleep(1000);

			// tous les rôles sont supprimés.
			// wait.until(ExpectedConditions.invisibilityOf(page_profils.importerDesProjetsAjouteTableau));
			// assertTrue(page_profils.verifPage(page_profils.importerDesProjetsAjouteTableau));
			// assertFalse(page_profils.verifPage(page_profils.libellesAjouteTableau));
			// assertFalse(page_profils.verifPage(page_profils.categorieCoutRoleAjouteTableau));

			// enregistrer le profil
			logger.info("enregistrer le profil".toUpperCase());
			page_profils.enregistrerProfil();

			// retour sur la page "Profils Liste"
			WebElement messageBandeau = wait.until(ExpectedConditions.visibilityOf(page_profils.messageBandeau));
			assertEquals("Profil " + "\"1 profil \"" + " enregistré", messageBandeau.getText());
			wait.until(ExpectedConditions.visibilityOf(page_profils.titreProfilsListe)); // Explicit wait
			assertTrue(page_profils.verifPage(titreProfilsListe));

			// modifier le profil
			logger.info("modifier le profil".toUpperCase());
			page_profils.modifierProfil();
			WebElement titreModifierProfil = wait
					.until(ExpectedConditions.visibilityOf(page_profils.titreModifierProfil)); // Explicit wait
			assertTrue(page_profils.verifPage(titreModifierProfil));

			// modifier un profil - Ajout de plusieurs rôles
			logger.info("modifier un profil - Ajout de plusieurs rôles".toUpperCase());
			page_profils.ajouterRole(page_profils.importerDesProjetsRole);
			page_profils.ajouterRole(page_profils.categorieCoutRole);
			page_profils.ajouterRole(page_profils.libellesRole);

			assertTrue(page_profils.verifPage(page_profils.importerDesProjetsAjouteTableau));
			assertTrue(page_profils.verifPage(page_profils.libellesAjouteTableau));
			assertTrue(page_profils.verifPage(page_profils.categorieCoutRoleAjouteTableau));

			// changer le nom du profil
			logger.info("changer le nom du profil".toUpperCase());
			page_profils.ajouterProfil(driver, NewNomProfil);
			page_profils.enregistrerProfil();

			wait.until(ExpectedConditions.visibilityOf(page_profils.messageBandeau));
			assertEquals("Profil " + "\"2 profil \"" + " enregistré", messageBandeau.getText());

			// supprimer le profil
			logger.info("supprimer le profil".toUpperCase());
			page_profils.supprimerProfil();
			wait.until(ExpectedConditions.visibilityOf(page_profils.messageBandeau));
			assertEquals("Profil " + "\"2 profil \"" + " supprimé", messageBandeau.getText());
		} catch (Exception e) {
			PageProfils.takeSnapShot(driver, PageProfils.getNameFile());

			logger.severe("Exception while taking screenshot ".toUpperCase() + e.getMessage());
			throw e;
		}
	}

}
