package Selenium.Figosoft.testing.utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;

public class HoverOverElements {

	/**
	 * Method to hover over ratings and click on last
	 * 
	 * @param driver
	 * @param p
	 * @param test
	 * @param testId
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	public static void hoverOnRating(WebDriver driver, WebElement element, ExtentTest test, String testId)
			throws IOException, InterruptedException, AWTException {

		// Instantiate Action Class
		Actions actions = new Actions(driver);
		
//		Point coordinates = p;
//		Robot robot = new Robot();
		for (int i = 25; i <= 200; i += 35) {
			Thread.sleep(800);
			ScreenShot.attachScreenshot(test, testId, driver, "Hover in Progress");
			//robot.mouseMove(coordinates.getX() + i, coordinates.getY() + 120);
			
			// Move mouse to x offset 25 i.e. in horizontal direction
			actions.moveToElement(element,i , 0).click().build().perform();
		}
//		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click
//		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click
	}
}
