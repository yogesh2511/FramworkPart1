package com.test.automation.UIAutomation.helper;

import com.test.automation.UIAutomation.utility.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class AlertHelper{
	
	private WebDriver driver;
	
	public AlertHelper(WebDriver driver) {
		this.driver = driver;
		Logger.debug("AlertHelper : " + this.driver.hashCode());
	}
	
	public Alert getAlert() {
		Logger.debug("");
		return driver.switchTo().alert();
	}
	
	public void AcceptAlert() {
		Logger.info("");
		getAlert().accept();
	}

	public void DismissAlert() {
		Logger.info("");
		getAlert().dismiss();
	}

	public String getAlertText() {		
		String text = getAlert().getText();
		Logger.info(text);
		return text;
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			Logger.info("true");
			return true;
		} catch (NoAlertPresentException e) {
			// Ignore
			Logger.info("false");
			return false;
		}
	}

	public void AcceptAlertIfPresent() {
		if (!isAlertPresent())
			return;
		AcceptAlert();
		Logger.info("");
	}

	public void DismissAlertIfPresent() {

		if (!isAlertPresent())
			return;
		DismissAlert();
		Logger.info("");
	}
	
	public void AcceptPrompt(String text) {
		
		if (!isAlertPresent())
			return;
		
		Alert alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
		Logger.info(text);
	}
}
