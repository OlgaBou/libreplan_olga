import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageBandeau {
	
public WebDriver driver;
	
	public PageBandeau(WebDriver driver) {
		this.driver=driver;
	}
	
	// onglet principal du Bandeau 
	
	@FindBy (xpath="//button[contains(text(),\"Calendrier\")]")
	WebElement Onglet_Calendrier;
	
	@FindBy (xpath="//button[contains(text(),\"Ressources\")]")
	WebElement Onglet_Ressources;
	
	@FindBy (xpath="//button[contains(text(),\"Coût\")]")
	WebElement Onglet_Coût;
	
	@FindBy (xpath="//button[contains(text(), 'Configuration')]")
	WebElement Onglet_Configuration;
	
	@FindBy (xpath="//button[contains(text(),\"Communication\")]")
	WebElement Onglet_Communications;
	
	@FindBy (xpath="//button[contains(text(),\"Rapport\")]")
	WebElement Onglet_Rapports;
	
	@FindBy (xpath="//button[contains(text(),\"Zone\")]")
	WebElement Onglet_ZonePersonnelle;
	
	@FindBy (xpath="//a[@href='/libreplan/resources/machine/machines.zul']")
	WebElement Machines;
	
	@FindBy (xpath="//a[@href='/libreplan/profiles/profiles.zul']")
	WebElement Profils;
	
	
	public boolean verifPage(WebElement nomWebEl) {
		boolean verif;
		if (nomWebEl.isDisplayed()) {
			verif = true;
		} else {
			verif = false;
		}
		return verif;
	}

	public String namePage(WebElement nomPage) {
		String namePage = "Page"+ nomPage;
		return namePage;
	}
	
	

}
