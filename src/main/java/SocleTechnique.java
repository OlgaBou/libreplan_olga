import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;


public class SocleTechnique {

	static WebDriver driver;

	public static Properties propertyJDD;

	public static Properties initScenario() {
		propertyJDD = new Properties();

		try {
			InputStream in = new FileInputStream("src/main/resources/JDD.properties/JDD.properties");
			propertyJDD.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return propertyJDD;
	}


}