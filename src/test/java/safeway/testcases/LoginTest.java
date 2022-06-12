package safeway.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import safeway.pages.LoginPageFactory;
import safeway.utility.ExcelDataProvider;
import safeway.pages.BaseClass;

public class LoginTest extends BaseClass{
	
	
	
	@Test(priority=1)
	public void loginWithValidData() throws InterruptedException 
	{
		reportLogger = extent.createTest("Log in to Safeway");
		
		LoginPageFactory loginPageFactory = PageFactory.initElements(driver, LoginPageFactory.class);
		
		//loginPageFactory.loginTest("netra.motgi@googlemail.com", "netravati11"); // 1. data from loginFactory
		loginPageFactory.loginTest(excel.getStringData("login", 0, 0), excel.getStringData("login", 0, 1));// 2. data from excel file	
		
	}
	@Test(priority=2)
	public void loginWithINValidData() throws InterruptedException 
	{
		reportLogger = extent.createTest("Log in to Safeway");
		
		LoginPageFactory loginPageFactory = PageFactory.initElements(driver, LoginPageFactory.class);
		
		//loginPageFactory.loginTest("netra.motgi@googlemail.com", "netravati11"); // 1. data from loginFactory
		loginPageFactory.loginTest(excel.getStringData("login", 1, 0), excel.getStringData("login", 1, 1));// 2. data from excel file	
		
	}
	


}
