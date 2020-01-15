package Selenium.Figosoft.testing.utility;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class TabSwitch {

	public static void tabSwitch(WebDriver driver) {
		
		String currentWindowHandle = driver.getWindowHandle();
		
		 Set<String> windowHandles = driver.getWindowHandles();

		    for (String window:windowHandles){

		        //if it contains the current window we want to eliminate that from switchTo();
		        if (!currentWindowHandle.equalsIgnoreCase(window)){
		            //Now switchTo new Tab.
		            driver.switchTo().window(window);
		        }
		    }
	
	  }
}
