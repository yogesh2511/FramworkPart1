package com.test.automation.UIAutomation.helper;

import java.util.LinkedList;
import java.util.List;

import com.test.automation.UIAutomation.utility.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownHelper {
	
	private WebDriver driver;
	
	public DropDownHelper(WebDriver driver) {
		this.driver = driver;
		Logger.debug("DropDownHelper : " + this.driver.hashCode());
	}

	
	public void SelectUsingVisibleValue(WebElement element,String visibleValue) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleValue);
		Logger.info("Locator : " + element + " Value : " + visibleValue);
	}

	public String getSelectedValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		Logger.info("WebELement : " + element + " Value : "+ value);
		return value;
	}
	
	public void SelectUsingIndex(WebElement element,int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		Logger.info("Locator : " + element + " Value : " + index);
	}
	
	public void SelectUsingVisibleText(WebElement element,String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
		Logger.info("Locator : " + element + " Value : " + text);
	}
	
	
	public List<String> getAllDropDownValues(WebElement locator) {
		Select select = new Select(locator);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		
		for (WebElement element : elementList) {
			Logger.info(element.getText());
			valueList.add(element.getText());
		}
		return valueList;
	}
}
