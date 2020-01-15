package Selenium.Figosoft.testing.executionClass;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import Selenium.Figosoft.testing.uiStore.ReviewRatingPageXpaths;
import Selenium.Figosoft.testing.utility.HoverOverElements;
import Selenium.Figosoft.testing.utility.ScreenShot;
import Selenium.Figosoft.testing.utility.TabSwitch;

public class ReviewRatingPageMethods extends ReviewRatingPageXpaths{

	public static WebDriver driver;
	static WebDriverWait wait;
	static ExtentTest test;
	static String testId;
	
	/**
	 * Method to Enter Review Title
	 * @param input
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void enterReviewTitle(String input) throws IOException, InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		driver.findElement(title).sendKeys(input);
		ScreenShot.attachScreenshot(test, testId, driver, "Title entered for review");
	}
	
	/**
	 * Method to Enter Review
	 * @param input
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void enterReviewText(String input) throws IOException, InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(text));
		driver.findElement(text).sendKeys(input);
		ScreenShot.attachScreenshot(test, testId, driver, "Review entered");
	}
	
	/**
	 * Method to Click Trip Type
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void clickTripType() throws IOException, InterruptedException {
		
		Actions actions = new Actions(driver);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(trip)));
		actions.moveToElement(driver.findElement(By.xpath(trip))).click().build().perform();

		ScreenShot.attachScreenshot(test, testId, driver, "Trip Type selected");

	}
	
	/**
	 * Method to Select Travel Time
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void selectTravel() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(travel));
		
		Select travelDropDown = new Select(driver.findElement(travel));
		travelDropDown.selectByVisibleText("January 2020");
	
		ScreenShot.attachScreenshot(test, testId, driver, "Travel Time selected");

	}
	
	/**
	 * Method to select checkbox
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void clickCheckBox() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(checkbox));
		driver.findElement(checkbox).click();
		ScreenShot.attachScreenshot(test, testId, driver, "Check box selected");

	}
	
	/**
	 * Submit the review
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void clickSubmitReview() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(submit));
		driver.findElement(submit).click();
		ScreenShot.attachScreenshot(test, testId, driver, "Submit Button Clicked");

	}
	
	/**
	 * Method to be called for performing all above operations 
	 * @param driver1
	 * @param test1
	 * @param testId1
	 * @param title
	 * @param text
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	public  static void reviewRatingPageAllMethods(WebDriver driver1, ExtentTest test1, String testId1,String title,String text) throws IOException, InterruptedException, AWTException {
		
		driver = driver1;
		test = test1;
		testId = testId1;
		wait = new WebDriverWait(driver, 10);
	    
		TabSwitch.tabSwitch(driver);
	
		HoverOverElements.hoverOnRating(driver, driver.findElement(rating), test, testId);
		enterReviewTitle(title);
		enterReviewText(text);
		clickTripType();
		selectTravel();
		clickCheckBox();
		clickSubmitReview();
		
		//Hotel Rating Description is randomly changing	
/*		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(scrollTill)));
        Thread.sleep(100);
		HoverOverElements.hoverOnRating(driver, driver.findElement(service), test, testId);
		HoverOverElements.hoverOnRating(driver, driver.findElement(clean), test, testId);
		HoverOverElements.hoverOnRating(driver, driver.findElement(sleep), test, testId);*/
		
	}
}
