package safeway.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import safeway.testcases.BrowserFactory;
import safeway.utility.ConfigDataProvider;
import safeway.utility.ExcelDataProvider;
import safeway.utility.Helper;

public class BaseClass {
	
	protected WebDriver driver;
	protected ExcelDataProvider excel ;
	protected ConfigDataProvider config;
	protected ExtentReports extent;
	protected ExtentTest reportLogger;
	
	private static Logger logger;
	
	@BeforeSuite
	public void setUpSuite() 
	{
		logger= LogManager.getLogger(BaseClass.class);
		logger.info("Setting up reports and Test is getting ready");
		
		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();
		
		// extent report in Report directory
		 extent = new ExtentReports(); 
		 ExtentSparkReporter spark = new
				  ExtentSparkReporter( "./Reports/SafewayReport_" + Helper.getCurrentDateTime()
				  + ".html"); extent.attachReporter(spark);
		
		logger.info("Setting done - Test can be started ");		  
	}
	
	@BeforeClass
	public void setUp()
	{
		logger.info("Trying to start browser and getting application ready");
		
		//driver= BrowserFactory.startBrowser(driver, "chrome", "https://www.safeway.com/"); // 1. data from BrowserFactory
		driver= BrowserFactory.startBrowser(driver, config.getBrowser(),config.getStagingURL());
	
		logger.info("Browser and application is up and running ");
	
	}
	
	@AfterClass
	public void tearDown() throws InterruptedException 
	{
		Thread.sleep(2000);
		BrowserFactory.quitBrowser(driver);
		
		logger.info("Browser is closed");
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) 
	{
		logger.info("Test is about to end  ");
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			Helper.captureScreenshot(driver);
			reportLogger.fail("Test failed ", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if (result.getStatus()==ITestResult.SUCCESS)
		{
			reportLogger.fail("Test passed ", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		// Generate report
		extent.flush();
		
		logger.info("Test completed ");
	}
}

