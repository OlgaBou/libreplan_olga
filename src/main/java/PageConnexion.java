import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageConnexion extends PageBandeau{
	
	public PageConnexion(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath = "//input[@id='textfield']")
	public WebElement username_field;
	@FindBy(xpath = "//input[@id='textfield2']")
	public WebElement password_field;
	@FindBy(xpath = "//input[@id='button']")
	public WebElement se_connecter_button;

	public PageAccueil logIn(WebDriver driver, String username, String password) {
		username_field.clear();
		username_field.sendKeys(username);
		password_field.clear();
		password_field.sendKeys(password);
		se_connecter_button.click();
		return PageFactory.initElements(driver, PageAccueil.class);
	
	
}
}