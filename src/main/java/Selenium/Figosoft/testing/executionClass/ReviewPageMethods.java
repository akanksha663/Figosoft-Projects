package Selenium.Figosoft.testing.executionClass;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import Selenium.Figosoft.testing.uiStore.ReviewPageXpaths;
import Selenium.Figosoft.testing.utility.ScreenShot;
import Selenium.Figosoft.testing.utility.TabSwitch;

public class ReviewPageMethods extends ReviewPageXpaths{

	public static WebDriver driver;
	static WebDriverWait wait;
	static ExtentTest test;
	static String testId;
	static String review;
	
	private static void clickWriteReviewButton() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(writeReview)));
		driver.findElement(By.xpath(writeReview)).click();
		ScreenShot.attachScreenshot(test, testId, driver, "Write a Review Button clicked");
	}
	
	public  static void reviewPageAllMethods(WebDriver driver1, ExtentTest test1, String testId1) throws IOException, InterruptedException {
		
		driver = driver1;
		test = test1;
		testId = testId1;
		wait = new WebDriverWait(driver, 10);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		TabSwitch.tabSwitch(driver);
		
		//This will scroll the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(scrollTill)));
		clickWriteReviewButton();
	}
}
