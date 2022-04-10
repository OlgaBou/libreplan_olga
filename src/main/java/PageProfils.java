import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PageProfils extends PageBandeau{
	
	public PageProfils(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath = "//div[text()='Profils Liste']")
	public WebElement titreProfilsListe;
	
	@FindBy(xpath = "//div[@class='z-column-cnt' and text()='Nom de profil']")
	public WebElement nomProfilColonne;
	
	@FindBy(xpath = "//div[@class='clickable-rows z-grid']//div[@class='z-column-cnt' and text()='Actions']")
	public WebElement actionsColonne;
	
	@FindBy(xpath = "//tr[@class='clickable-rows z-row'][1]//span[@class='icono z-button' and @title='Modifier']")
	public WebElement iconeModifier;
	@FindBy(xpath = "//tr[@class='clickable-rows z-row'][1]//span[@class='icono z-button' and @title='Supprimer']")
	public WebElement iconeSupprimer;
	
	@FindBy(xpath = "//div[@class='z-window-embedded'][1]//td[@class='z-button-cm' and text()='Créer']")
	public WebElement buttonCreer;
	
	@FindBy(xpath = "//td[text()='Créer Profil']")
	public WebElement titreCreerProfil;
	
	@FindBy(xpath = "//li[@class='z-tab z-tab-seld']//span[text()='Données de profil']")
	public WebElement ongletDonnesProfil;
	
	@FindBy(css = "tr.z-row input.focus-element.z-textbox.z-textbox")
	public WebElement nomProfilChamp;
	
	@FindBy(xpath = "//legend[@class='z-caption z-caption-readonly']//span[text()='Association avec les rôles']")
	public WebElement champAssociationRoles;

	@FindBy(xpath = "//legend[@class='z-caption z-caption-readonly']//following-sibling::div//input[@class='z-combobox-inp' and @autocomplete='off']")
	public WebElement menuDeroulantRoleDefault;
	
	@FindBy(xpath = "//input[@class='z-combobox-inp']")
	public WebElement menuDeroulantRole;
	
	@FindBy(xpath = "//td[contains(text(),'importer')]")
	public WebElement importerDesProjetsRole;
	
	@FindBy(xpath = "//legend[@class='z-caption z-caption-readonly']/following-sibling::div//div[@class='z-grid-body']//span[@class='z-label']")
	public WebElement roleAjouteTableau;
	
	
	@FindBy(xpath = "//td[@class='z-button-cm' and text()='Ajouter un rôle']")
	public WebElement ajouterRoleButton;
	
	@FindBy(xpath = "//div[@class='z-column-cnt' and text()='Nom du rôle']")
	public WebElement nomRoleColonne;
	
	@FindBy(xpath = "//th[@class='z-column'][2]/div[@class='z-column-cnt' and text()='Actions']")
	public WebElement nomAction;
	
	@FindBy(xpath = "//span[@class='save-button global-action z-button']//td[@class='z-button-cm' and text()='Enregistrer']")
	public WebElement enregistrerButton;
	
	@FindBy(xpath = "//span[@class='saveandcontinue-button global-action z-button']//td[@class='z-button-cm' and text()='Sauver et continuer']")
	public WebElement saverEtContinuerButton;
	
	@FindBy(xpath = "//span[@class='cancel-button global-action z-button']//td[@class='z-button-cm' and text()='Annuler']")
	public WebElement annulerButton;
	
	@FindBy(xpath = "//legend[@class='z-caption z-caption-readonly']/following-sibling::div//span[@class='icono z-button']")
	public WebElement iconePoubelle;


	public void creationProfil() {
		buttonCreer.click();
		
	}
	
	public void ajouterProfil(WebDriver driver, String nomProfil) throws Exception{
		nomProfilChamp.clear();
		nomProfilChamp.sendKeys(nomProfil);
		menuDeroulantRole.click();
		importerDesProjetsRole.click();
		ajouterRoleButton.click();

	}
	
}
