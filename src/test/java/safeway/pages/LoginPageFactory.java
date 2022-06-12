package safeway.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPageFactory {
	
WebDriver driver;
	
	public LoginPageFactory(WebDriver Idriver) 
	{
		this.driver=Idriver;
	}
	
	// Signin option from menu
	@FindBy(xpath="//span[@class='button__item menu-nav__profile-button-sign-in-up d-none d-lg-inline-block dst-sign-in-up']") 
	@CacheLookup
	WebElement signInMenu;
	
	// Select singn option
	@FindBy(xpath="//a[@id='sign-in-modal-link']") 
	@CacheLookup
	WebElement signInOption;
	
	// Email id
	@FindBy(how=How.ID, using="label-email") 
	@CacheLookup
	WebElement emailId;
	
	// password
	@FindBy(how= How.ID, using="label-password") 
	@CacheLookup
	WebElement password;
	
	// Signin button
	@FindBy(how = How.ID, using = "btnSignIn")
	@CacheLookup
	WebElement signInButton;

	
	public void loginTest(String email, String pass) throws InterruptedException {
		
		signInMenu.click();
		signInOption.click();
		Thread.sleep(2000);
		emailId.sendKeys(email);
		password.sendKeys(pass);
		signInButton.click();

	}


}
