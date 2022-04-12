import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PageBandeau {

	public WebDriver driver;

	public PageBandeau(WebDriver driver) {
		this.driver = driver;
	}

	// onglet principal du Bandeau

	@FindBy(xpath = "//button[contains(text(),\"Calendrier\")]")
	WebElement Onglet_Calendrier;

	@FindBy(xpath = "//button[contains(text(),\"Ressources\")]")
	WebElement Onglet_Ressources;

	@FindBy(xpath = "//button[contains(text(),\"Coût\")]")
	WebElement Onglet_Coût;

	@FindBy(xpath = "//button[contains(text(), 'Configuration')]")
	WebElement Onglet_Configuration;

	@FindBy(xpath = "//button[contains(text(),\"Communication\")]")
	WebElement Onglet_Communications;

	@FindBy(xpath = "//button[contains(text(),\"Rapport\")]")
	WebElement Onglet_Rapports;

	@FindBy(xpath = "//button[contains(text(),\"Zone\")]")
	WebElement Onglet_ZonePersonnelle;

	@FindBy(xpath = "//a[@href='/libreplan/resources/machine/machines.zul']")
	WebElement Machines;

	@FindBy(xpath = "//a[@href='/libreplan/profiles/profiles.zul']")
	WebElement Profils;

	@FindBy(xpath = "//div[@class='message_INFO']/span")
	public WebElement messageBandeau;

	public boolean verifPage(WebElement nomWebEl) {
		boolean verif;
		if (nomWebEl.isDisplayed()) {
			verif = true;
		} else {
			verif = false;
		}
		return verif;
	}

	public static String getNameFile() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		String dateNow = formatter.format(date);
		String fileName = dateNow.toString().replace(":", "_").replace(" ", "_") + ".png";

		return fileName;
	}

	public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File DestFile = new File(fileWithPath);
		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
	}

	public void mouseHover(WebElement mouseHover, WebElement mouseClick) {
		Actions action = new Actions(driver);
		action.moveToElement(mouseHover).perform();
	}

}
