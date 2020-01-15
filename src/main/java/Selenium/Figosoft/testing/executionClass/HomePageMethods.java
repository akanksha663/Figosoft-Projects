package Selenium.Figosoft.testing.executionClass;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import Selenium.Figosoft.testing.uiStore.HomePageXpaths;
import Selenium.Figosoft.testing.utility.ScreenShot;

public class HomePageMethods extends HomePageXpaths{

	public static WebDriver driver;
	static WebDriverWait wait;
	static ExtentTest test;
	static String testId;
	
	/**
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void clickSearchField() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchField)));
		driver.findElement(By.xpath(searchField)).click();
		ScreenShot.attachScreenshot(test, testId, driver, "Search Field clicked");
	}
	
	/**
	 * 
	 * @param input
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void enterIntoSearchField(String input) throws IOException, InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(mainSearch));
		driver.findElement(mainSearch).sendKeys(input);
		ScreenShot.attachScreenshot(test, testId, driver, "Value entered for search");
	}
	
	/**
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void clickSearchButton() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
		driver.findElement(searchButton).click();
		ScreenShot.attachScreenshot(test, testId, driver, "Search Button Clicked");

	}
	
	/**
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void clickFirstMatch() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectFirstMatch)));
		driver.findElements(By.xpath(selectFirstMatch)).get(0).click();
		ScreenShot.attachScreenshot(test, testId, driver, "First Match Clicked");

	}
	
	/**
	 * Method to be called for performing all above operations 
	 * @param driver1
	 * @param test1
	 * @param testId1
	 * @param input
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public  static void homePageAllMethods(WebDriver driver1, ExtentTest test1, String testId1,String input) throws IOException, InterruptedException {
		
		driver = driver1;
		test = test1;
		testId = testId1;
		wait = new WebDriverWait(driver, 10);

		clickSearchField();
		enterIntoSearchField(input);
		clickSearchButton();
		clickFirstMatch();
	}
}
