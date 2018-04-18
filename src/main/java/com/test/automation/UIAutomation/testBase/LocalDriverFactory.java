package com.test.automation.UIAutomation.testBase;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.test.automation.UIAutomation.utility.ResourceHelper;

public class LocalDriverFactory extends TestBase
{
	public static Logger log = Logger.getLogger(LocalDriverFactory.class.getName());
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
		}
		else {
			System.out.println("driver is already assigned..");
		}
		return driver;
	}

	public static void setWebDriver(WebDriver driver) {
		webDriver.set(driver);
	}

	public static WebDriver createInstance(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = initChromeDriver();
			log.info("chrome browser");
		}
		if (browserName.equalsIgnoreCase("firefox")) {
			driver = initFirefoxDriver();
			log.info("firefox browser");
		}
		if (browserName.equalsIgnoreCase("ie")) {
			driver = initIEDriver();
			log.info("IE browser");
		}
		if (browserName.equalsIgnoreCase("phantom")) {
			driver = initPhantom();
			log.info("Phantom browser");
		} else
			log.info("browser is invalid, browser of choice..");
			System.out.println("invalid browser type");
		
		return driver;
	}
	
	private static WebDriver initPhantom() {
		System.setProperty("phantomjs.binary.path",
				ResourceHelper.getResourcePath("\\resources\\drivers\\phantomjs.exe"));
		return driver = new PhantomJSDriver();
	}

	private static WebDriver initChromeDriver() {

		try {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
			driver = new RemoteWebDriver(new URL(Config.getProperty("hub")), dc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}

	private static WebDriver initFirefoxDriver() {

		try {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
			driver = new RemoteWebDriver(new URL(Config.getProperty("hub")), dc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	private static WebDriver initIEDriver() {
		try {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
			dc.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL(Config.getProperty("hub")), dc);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}

	
}
