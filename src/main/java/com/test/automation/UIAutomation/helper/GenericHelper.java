package com.test.automation.UIAutomation.helper;

import com.test.automation.UIAutomation.utility.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class GenericHelper{
	
	
	public String readValueFromElement(WebElement element) {

		if (null == element){
			Logger.info("weblement is null");
			return null;
		}

		boolean displayed = false;
		try {
			displayed = isDisplayed(element);
		} catch (Exception e) {
			Logger.error(e.getMessage());
			Reporter.log(e.fillInStackTrace().toString());
			return null;
		}

		if (!displayed){
			return null;
		}
		String text = element.getText();
		Logger.info("weblement valus is.."+text);
		return text;
	}
	

	public String readValueFromInput(WebElement element) {
		if (null == element){
			return null;
		}
		if (!isDisplayed(element)){
			return null;
		}
		String value = element.getAttribute("value");
		Logger.info("weblement valus is.."+value);
		return value;
	}
	
	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			Logger.info("element is displayed.."+element);
			return true;
		} catch (Exception e) {
			Logger.info(e.getMessage());
			Reporter.log(e.fillInStackTrace().toString());
			return false;
		}
	}
	
	protected boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			Logger.info("element is displayed.."+element);
			return false;
		} catch (Exception e) {
			Logger.error(e.getMessage());
			Reporter.log(e.fillInStackTrace().toString());
			return true;
		}
	}
	
	protected String getDisplayText(WebElement element) {
		if (null == element){
			return null;
		}
		if (!isDisplayed(element)){
			return null;
		}
		return element.getText();
	}
	

	public static synchronized String getElementText( WebElement element) {
		if (null == element) {
			Logger.info("weblement is null");
			return null;
		}
		String elementText = null;
		try {
			elementText = element.getText();
		} catch (Exception ex) {
			Logger.info("Element not found " + ex);
			Reporter.log(ex.fillInStackTrace().toString());
		}
		return elementText;
	}

}
