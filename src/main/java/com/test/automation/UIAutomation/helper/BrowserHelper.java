package com.test.automation.UIAutomation.helper;

import java.util.LinkedList;
import java.util.Set;

import com.test.automation.UIAutomation.utility.Logger;
import org.openqa.selenium.WebDriver;

public class BrowserHelper{

	private WebDriver driver;
	
	public BrowserHelper(WebDriver driver) {
		this.driver = driver;
		Logger.debug("BrowserHelper : " + this.driver.hashCode());
	}

	public void goBack() {
		driver.navigate().back();
		Logger.info("");
	}

	public void goForward() {
		driver.navigate().forward();
		Logger.info("");
	}

	public void refresh() {
		driver.navigate().refresh();
		Logger.info("");
	}

	public Set<String> getWindowHandlens() {
		Logger.info("");
		return driver.getWindowHandles();
	}

	public void SwitchToWindow(int index) {

		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());

		if (index < 0 || index > windowsId.size()){
			throw new IllegalArgumentException("Invalid Index : " + index);
		}
		driver.switchTo().window(windowsId.get(index));
		//Logger.info(index);
	}

	public void switchToParentWindow() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());
		driver.switchTo().window(windowsId.get(0));
		Logger.info("");
	}

	public void switchToParentWithChildClose() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());

		for (int i = 1; i < windowsId.size(); i++) {
			Logger.info(windowsId.get(i));
			driver.switchTo().window(windowsId.get(i));
			driver.close();
		}

		switchToParentWindow();
	}
	

	
	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
		Logger.info(nameOrId);
	}

}
