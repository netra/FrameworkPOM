package safeway.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {
	
	static WebDriver driver;

	public static WebDriver startBrowser(WebDriver driver, String browserName, String url) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/netra/Downloads/selenium_new/ChromeDriver/chromedriver");
			driver= new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("safari")) {
			driver=new SafariDriver();
			
		}
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}
	public static void quitBrowser(WebDriver driver) {
		
		driver.quit();
	}

}
