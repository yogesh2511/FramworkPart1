package com.test.automation.UIAutomation.helper;

import com.test.automation.UIAutomation.utility.Logger;
import org.openqa.selenium.WebElement;

public class VerificationHelper{

	

	public static synchronized boolean verifyElementPresent( WebElement element) {
		boolean isDispalyed = false;
		try {
			 isDispalyed= element.isDisplayed();
			 Logger.info(element.getText()+" is dispalyed");
		}
		catch(Exception ex) {
			Logger.error("Element not found " + ex);
		}
		
		return isDispalyed;
	}
	
	public static synchronized boolean verifyElementNotPresent( WebElement element) {
		boolean isDispalyed = false;
		try {
			 element.isDisplayed();
			 Logger.info(element.getText()+" is dispalyed");
		}
		catch(Exception ex) {
			Logger.error("Element not found " + ex);
			isDispalyed = true;
		}
		
		return isDispalyed;
	}
	
	public static synchronized boolean verifyTextEquals( WebElement element,String expectedText) {
		boolean flag = false;
		try {
			String actualText=element.getText();
			if(actualText.equals(expectedText)) {
				Logger.info("actualText is :"+actualText+" expected text is: "+expectedText);
				return flag=true;
			}
			else {
				Logger.error("actualText is :"+actualText+" expected text is: "+expectedText);
				return flag;
			}
		}
		catch(Exception ex) {
			Logger.error("actualText is :"+element.getText()+" expected text is: "+expectedText);
			Logger.info("text not matching" + ex);
			return flag;
		}
	}
}
