package Selenium.Figosoft.TripAdvisor;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Selenium.Figosoft.testing.executionClass.HomePageMethods;
import Selenium.Figosoft.testing.executionClass.ReviewPageMethods;
import Selenium.Figosoft.testing.executionClass.ReviewRatingPageMethods;
import Selenium.Figosoft.testing.utility.ReadObject;
import Selenium.Figosoft.testing.utility.ScreenShot;



public class ExecutionEngine {

	WebDriver driver;
	WebDriverWait wait;
	Properties allObjects;
	Logger log;
	ReadObject object = new ReadObject();
	ExtentReports extent;
	ExtentTest test, childTest;
	ExtentHtmlReporter htmlReporter;
	String testId;
	
	/**
	 * Logger
	 * 
	 * @param c
	 * @param path
	 * @return
	 */
	public static Logger getLogger(Class<?> c, String path) {
		PropertyConfigurator.configure(path);
		return Logger.getLogger(c);
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	@BeforeSuite
	private void start() throws IOException {

		// log4j
		allObjects = object.getObjectRepository();
		log = ExecutionEngine.getLogger(ExecutionEngine.class, allObjects.getProperty("logPath"));
		log.setLevel(Level.ALL);
		FileInputStream input = new FileInputStream(allObjects.getProperty("logPath"));
		PropertyConfigurator.configure(input);
		log.info("Property file configured");
		
		// extent reports
		extent = new ExtentReports();
		htmlReporter = new ExtentHtmlReporter(allObjects.getProperty("reportPath"));
		extent.attachReporter(htmlReporter);
		log.info("Extent Reports initialised");
	}
	
	/**
	 * Runs before every Test method
	 * 
	 * @param method
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@BeforeMethod
	public void beforeMethod(Method method) throws IOException, InterruptedException {

		log.info("Entered in beforeMethod method");

		// Creating tests and categories for reports
		String descriptiveTestName = method.getName();
		test = extent.createTest(descriptiveTestName, "This is a " + descriptiveTestName);
		test.assignCategory(descriptiveTestName + "  Scenario");
		test.pass(descriptiveTestName + " Started");
				
		// initialize the driver
		if ("chrome".equals(allObjects.getProperty("browser"))) {
			System.setProperty("webdriver.chrome.driver", allObjects.getProperty("chromePath"));
			driver = new ChromeDriver();
		} else if ("IE".equals(allObjects.getProperty("browser"))) {
			System.setProperty("webdriver.ie.driver", allObjects.getProperty("iePath"));
			driver = new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(allObjects.getProperty("url"));
		wait = new WebDriverWait(driver, 10);

		log.info("Exiting from beforeMethod method");

	}
	
	/**
	 * Test Method
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	@Test(priority = 1)
	public void reviewTest() throws IOException, InterruptedException, AWTException {
		
		testId="ReviewTest";
		log.info("Entered in ReviewTest method.");

		ScreenShot.attachScreenshot(test, testId, driver, "Url navigation");
		log.info("Screenshot taken for URL navigation.");

		// creating child test
		childTest = test.createNode(testId);
		log.info("Child node created");
		
		// calling method to perform actions on respective web elements
		log.info("Calling methods to perform actions on respective web elements");
		HomePageMethods.homePageAllMethods(driver, test, testId,allObjects.getProperty("input"));
		ReviewPageMethods.reviewPageAllMethods(driver, test, testId);
		ReviewRatingPageMethods.reviewRatingPageAllMethods(driver, test, testId,allObjects.getProperty("title"),allObjects.getProperty("text"));
			
	}
	
	/**
	 * After Method: Runs after every test method
	 */
	@AfterMethod
	public void afterMethod() {
		
		log.info("Driver closed");
		driver.quit();
	}
	
	/**
	 * Flush the extentTest
	 */
	@AfterSuite
	public void endResult() {

		log.info("Entered in endResult method which is an AfterSuite");

		// generating report
		extent.flush();
		log.info("Report generated !");
		
	}

}



