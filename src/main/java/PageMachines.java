
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PageMachines extends PageBandeau {
	
	public PageMachines(WebDriver driver) {
		super(driver);
		
	}
	
	

	@FindBy(xpath = "//div[text()='Machines Liste']")
	public WebElement titreMachinesListe;
	
	@FindBy(xpath = "//td[text()='Modifier Machine: MACHINETEST1']")
	public WebElement titreMachinesModifier;
	
	@FindBy(xpath = "//div[@class='z-window-embedded'][1]//td[@class='z-button-cm' and text()='Créer']")
	public WebElement buttonCreer;
	
	@FindBy(xpath = "//li[@class='z-tab z-tab-seld']//span[text()='Donnée de la machine']")
	public WebElement ongletDonneMachine;
	
	@FindBy(css = "tr.z-row input.z-textbox-text-disd")
	public WebElement code;
	
	@FindBy(css = "tr.z-row.z-grid-odd input.focus-element.z-textbox")
	public WebElement nom;
	
	@FindBy(css = "div.z-tabpanels tbody.z-rows tr.z-row:nth-child(3) td:nth-child(2) div.z-row-cnt.z-overflow-hidden > input.z-textbox")
	public WebElement description;
	
	@FindBy(css =  "div.z-row-cnt.z-overflow-hidden > select")
	public WebElement type;
	
	@FindBy(css =  "div.z-row-cnt.z-overflow-hidden > select > option[selected]")
	public WebElement typeSelected;
	
	@FindBy(xpath = "//div[@class='z-tabpanels']//tr[@class='z-row']//input[@type='checkbox']")
	public WebElement checkbox;
	
	@FindBy(xpath = "//span[@class='save-button global-action z-button']//td[@class='z-button-cm' and text()='Enregistrer']")
	public WebElement enregistrerButton;
	
	@FindBy(xpath = "//span[@class='save-button global-action z-button']//td[@class='z-button-cm' and text()='Sauver et continuer']")
	public WebElement saverEtContinuerButton;
	
	//@FindBy(xpath = "//div[2]//div[2]//div[2]/div[3]/div[1]/div[2]/div[3]/div/table/tbody/tr/td/table/tbody/tr[3]/td/div/div[2]/div[3]/table/tbody/tr/td/table/tbody/tr/td[3]/span/table/tbody/tr[2]/td[2]")
	//public WebElement annulerButton;
	
	@FindBy(css = "td[id*='chdextr'] td.z-button-cm img[src='/libreplan/common/img/ico_borrar1.png']")
	public WebElement supprimerButton;
	
	@FindBy(xpath = "//td[@class='z-button-cm' and text()='OK']")
	public WebElement okButton;
	
	@FindBy(xpath = "//tr[@class='z-row']//tr[@valign=\"top\"]//input[@type='checkbox']")
	public WebElement checkBox;
	
	@FindBy(css = "div.z-tabpanels tbody.z-rows tr.z-row:nth-child(1) td:nth-child(2) div.z-row-cnt.z-overflow-hidden > input.z-textbox")
	public WebElement codeFieldActive;
	
	
	public void creationMachine() {
		buttonCreer.click();
		
	}
	
	public void ajouterMachine(WebDriver driver, String nomMachine, String discriptionMachine, String typeOfMachine) throws Exception{
		Select typeMachine = new Select(type);
		nom.clear();
		nom.sendKeys(nomMachine);
		description.clear();
		description.sendKeys(discriptionMachine);
		typeMachine.selectByVisibleText(typeOfMachine);
		saverEtContinuerButton.click();
	}
	
	
	public void annulerAction() {
		WebElement annulerButton = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div[3]/span[3]/table/tbody/tr[2]/td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", annulerButton);
		Actions act=new Actions(driver);
		act.moveToElement(annulerButton).click().perform();
		
		
	}
	
	public void supprimerMachine() throws Exception{
		supprimerButton.click();
		WebElement modal = driver.findElement(By.xpath("//div[@class='z-window-modal z-window-modal-shadow']"));
		Thread.sleep(2000);
		WebElement okButton = modal.findElement(By.xpath("//td[@class='z-button-cm' and text()='OK']"));
		okButton.click();
	
	}
	
	/*public void tableauElements() throws Exception {
		
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='clickable-rows z-grid']/div[@class='z-grid-body']//table[@style='table-layout:fixed;']"));

        for (WebElement webElement : elements) {
            String name = webElement.getText();
            System.out.println(name);
	    }
	}
*/
	public String[] getInfoTable() {
		
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='clickable-rows z-grid']/div[@class='z-grid-body']//table[@style='table-layout:fixed;']"));
		List<String> strings = new ArrayList<>();
		elements.stream().map(WebElement::getText).forEach(strings::add);
		StringBuilder strbul=new StringBuilder();
		for(String str : strings)
        {
            strbul.append(str);
            //for adding comma between elements
            strbul.append(",");
        }
        //just for removing last comma
        strbul.setLength(strbul.length()-1);
        String str=strbul.toString();
		//System.out.println("list" +strings.get(0));
		String[] words = str.split("\n");
		//System.out.println("this is my list" + Arrays.toString(words));
		return words;
	}
	
	
}
	
