import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageAccueil extends PageBandeau {
	
	public PageAccueil(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath = "//tr[@valign='top']//span[text()='Calendrier']")
	public WebElement fil_d_ariane_calendrier;
	
	public PageMachines chooseOptionMenuMachine (WebElement mouseHover, WebElement mouseClick) throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(mouseHover).perform();
		Thread.sleep(2000);
		mouseClick.click();
		return PageFactory.initElements(driver, PageMachines.class);
	}
	
	public PageProfils chooseOptionMenuProfils (WebElement mouseHover, WebElement mouseClick) throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(mouseHover).perform();
		Thread.sleep(2000);
		mouseClick.click();
		return PageFactory.initElements(driver, PageProfils.class);
	}
	

}