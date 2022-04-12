import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PageProfils extends PageBandeau {

	public PageProfils(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//div[text()='Profils Liste']")
	public WebElement titreProfilsListe;

	@FindBy(xpath = "//td[text()='Créer Profil']")
	public WebElement titreCreerProfil;

	@FindBy(xpath = "//td[contains(text(),'Modifier Profil')]")
	public WebElement titreModifierProfil;

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

	@FindBy(xpath = "//li[@class='z-tab z-tab-seld']//span[text()='Données de profil']")
	public WebElement ongletDonnesProfil;

	@FindBy(css = "tr.z-row input.focus-element.z-textbox.z-textbox")
	public WebElement nomProfilChamp;

	@FindBy(xpath = "//legend[@class='z-caption z-caption-readonly']//span[text()='Association avec les rôles']")
	public WebElement champAssociationRoles;

	@FindBy(xpath = "//legend[@class='z-caption z-caption-readonly']//following-sibling::div//input[@class='z-combobox-inp' and @autocomplete='off']")
	public WebElement menuDeroulantRoleDefault;

	@FindBy(xpath = "//i[@class='z-combobox-btn']")
	public WebElement menuDeroulantRole;

	@FindBy(xpath = "//td[contains(text(),'importer')]")
	public WebElement importerDesProjetsRole;

	@FindBy(xpath = "//td[contains(text(),'Catégories')]")
	public WebElement categorieCoutRole;

	@FindBy(xpath = "//td[contains(text(),'Libellés')]")
	public WebElement libellesRole;

	@FindBy(xpath = "//legend[@class='z-caption z-caption-readonly']/following-sibling::div//div[@class='z-grid-body']//span[@class='z-label' and text()='importer des projets']")
	public WebElement importerDesProjetsAjouteTableau;

	@FindBy(xpath = "//legend[@class='z-caption z-caption-readonly']/following-sibling::div//div[@class='z-grid-body']//span[@class='z-label' and text()='Libellés']")
	public WebElement libellesAjouteTableau;

	@FindBy(xpath = "//legend[@class='z-caption z-caption-readonly']/following-sibling::div//div[@class='z-grid-body']//span[@class='z-label' and text()='Catégories de coût']")
	public WebElement categorieCoutRoleAjouteTableau;

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

	@FindBy(xpath = "//legend[@class='z-caption z-caption-readonly']/following-sibling::div//tr[@class='z-row'][1]/td[@class='z-row-inner']//span[@class='icono z-button']")
	public WebElement iconePoubelle;

	@FindBy(xpath = "//tr[@class='z-row'][1]/td[@class='z-row-inner']//img")
	public WebElement iconePoubelle2;

	@FindBy(xpath = "//tr[@class='z-row z-row-over']/td[@class='z-row-inner']//img")
	public WebElement iconePoubelle3;

	@FindBy(xpath = "//tr[@class='clickable-rows z-row'][1]//span[@title='Supprimer']")
	public WebElement supprimerProfilButton;

	public void creationProfil() {
		buttonCreer.click();

	}

	public void ajouterProfil(WebDriver driver, String nomProfil) throws Exception {
		nomProfilChamp.clear();
		nomProfilChamp.sendKeys(nomProfil);

	}

	public void ajouterRole(WebElement roleChoix) throws Exception {
		menuDeroulantRoleDefault.clear();
		menuDeroulantRole.click();
		Thread.sleep(2000);
		roleChoix.click();
		ajouterRoleButton.click();
	}

	public void supprimerRole() throws Exception {
		WebElement iconePoubelle = driver
				.findElement(By.xpath("//tr[@class='z-row'][1]/td[@class='z-row-inner']//img"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", iconePoubelle);
		Actions act = new Actions(driver);
		act.moveToElement(iconePoubelle).click().perform();
		saverEtContinuerButton.click();
	}

	/*
	 * public void supprimerRole3() { WebDriverWait wait = new WebDriverWait(driver,
	 * 15); wait.until(ExpectedConditions.visibilityOf(iconePoubelle2));
	 * wait.until(ExpectedConditions.elementToBeClickable(iconePoubelle2));
	 * List<WebElement> elements = driver.findElements(By.
	 * xpath("//legend[@class='z-caption z-caption-readonly']/following-sibling::div//tr[@class='z-row']/td[@class='z-row-inner']//span[@class='icono z-button']"
	 * )); System.out.println(elements.size()); while (elements.size()+1 != 0) {
	 * Actions action = new Actions(driver);
	 * action.moveToElement(iconePoubelle2).click().build().perform(); } }
	 */

	public void supprimerRole2() throws Exception {
		WebElement iconePoubelle = driver
				.findElement(By.xpath("//tr[@class='z-row z-row-over']/td[@class='z-row-inner']//img"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", iconePoubelle);
		Actions act = new Actions(driver);
		act.moveToElement(iconePoubelle).click().perform();
		saverEtContinuerButton.click();
	}

	public void enregistrerProfil() {
		enregistrerButton.click();
	}

	public void modifierProfil() {
		iconeModifier.click();

	}

	public void supprimerProfil() throws Exception {
		supprimerProfilButton.click();
		WebElement modal = driver.findElement(By.xpath("//div[@class='z-window-modal z-window-modal-shadow']"));
		Thread.sleep(2000);
		WebElement okButton = modal.findElement(By.xpath("//td[@class='z-button-cm' and text()='OK']"));
		okButton.click();

	}

}
