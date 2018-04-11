package com.test.automation.UIAutomation.testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.SessionId;

import com.test.automation.UIAutomation.utility.ResourceHelper;

public class LocalDriverFactory {
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	private static WebDriver driver = null;

	private LocalDriverFactory() {
	}

	public static void initilize(String browser) {
		WebDriver dr = LocalDriverFactory.createInstance(browser);
		setWebDriver(dr);

	}

	public static WebDriver getDriver() {
		if (driver == null) {
			driver = webDriver.get();
		} else {
			System.out.println("driver is already assigned..");
		}
		return driver;
	}

	public static void setWebDriver(WebDriver driver) {
		webDriver.set(driver);
	}

	public static WebDriver createInstance(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					ResourceHelper.getResourcePath("\\resources\\drivers\\chromedriver.exe"));
			SessionId session = ((ChromeDriver) driver).getSessionId();
			System.out.println("Session id: " + session.toString());
			driver = new ChromeDriver();
		}
		if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					ResourceHelper.getResourcePath("\\resources\\drivers\\geckodriver.exe"));
			driver = new FirefoxDriver();
			return driver;
		}
		if (browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver",
					ResourceHelper.getResourcePath("\\resources\\drivers\\IEDriverServer.exe"));
			// System.setProperty("webdriver.ie.driver", driverPath +
			// "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		return driver;
	}
}
