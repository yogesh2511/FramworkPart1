package com.test.automation.UIAutomation.testBase;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.automation.UIAutomation.LoginPage.LoginPage;
import com.test.automation.UIAutomation.config.ElementLoad;
import com.test.automation.UIAutomation.config.EmailConfiguration;
import com.test.automation.UIAutomation.config.RequirementConfig;
import com.test.automation.UIAutomation.customListener.Listener;
import com.test.automation.UIAutomation.errorScreenShot.ErrorScreenShot;
import com.test.automation.UIAutomation.excelReader.Excel_Reader;
import com.test.automation.UIAutomation.extendReport.ExtentReportDemo;
import com.test.automation.UIAutomation.helper.AlertHelper;
import com.test.automation.UIAutomation.helper.BrowserHelper;
import com.test.automation.UIAutomation.helper.DropDownHelper;
import com.test.automation.UIAutomation.helper.JavaScriptHelper;
import com.test.automation.UIAutomation.helper.WaitHelper;
import com.test.automation.UIAutomation.utility.ResourceHelper;

//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;

//import TestNgPkg.ExtentReportDemo;

public class TestBase_old {
	public static WebDriver driver = null;

	public static ElementLoad ElementLoaderObj;
	// public static excelReader reader;
	public static Excel_Reader excel;
	// public static String path;
	public static String OS = System.getProperty("os.name").toLowerCase();
	public static Properties Config = new Properties();
	// public static Properties OR = new Properties();
	public static Properties loginPropertyFile = new Properties();
	public static Properties hompagePropertyFile = new Properties();
	public static Properties CandidateDetailsPropertyFile = new Properties();
	public static Properties CandidateListPropertyFile = new Properties();
	public static Properties RequirementDetailsPropertyFile = new Properties();
	public static Properties NewRequirementsDetailsPropertyFile = new Properties();
	public static Properties DashboardFile = new Properties();
	public static FileInputStream fConfig, fhomepage, floginpage, fCandidateDetails, fCandidateList,
			fRequirementDetailsFIS, fNewRequirementsDetails, fDashboard;// FOR
	public static Properties ORr;
	public static FileInputStream file;
	public static ArrayList<String> handles = new ArrayList<String>();

	public static Logger log = Logger.getLogger(TestBase.class.getName());
	public static WebDriverWait wait;

	public static ExtentReports extent = ExtentReportDemo.ExtentDemo("AutomationReport");
	public static ExtentTest test;
	public static Listener lis = new Listener();
	public static EmailConfiguration Emailconfig2 = new EmailConfiguration();
	public static RequirementConfig Requirementconfig = new RequirementConfig();
	public static LoginPage jsp;
	// public static PropertiesfilesLoad pfload;
	// public static ElementLoad elmentload = new ElementLoad();
	public static ErrorScreenShot errorscrenshot = new ErrorScreenShot();

	// Utility class
	public static AlertHelper alerhelper;
	public static BrowserHelper browserhelper;
	public static DropDownHelper dropdownhelper;
	public static JavaScriptHelper javascripthelper;
	public static WaitHelper waithelper;

	static {

		if (driver == null) {

			if (OS.contains("mac os x")) {
				try {
					fConfig = new FileInputStream(
							System.getProperty("user.dir") + "//resources//properties//Config.properties");
					Config.load(fConfig);
					fhomepage = new FileInputStream(
							System.getProperty("user.dir") + "//resources//properties//Requirements.properties");
					hompagePropertyFile.load(fhomepage);
					floginpage = new FileInputStream(
							System.getProperty("user.dir") + "//resources//properties//LoginPage.properties");
					loginPropertyFile.load(floginpage);
					fCandidateDetails = new FileInputStream(
							System.getProperty("user.dir") + "//resources//properties//CandidateDetails.properties");
					CandidateDetailsPropertyFile.load(fCandidateDetails);
					fCandidateList = new FileInputStream(
							System.getProperty("user.dir") + "//resources//properties//CandidateList.properties");
					CandidateListPropertyFile.load(fCandidateList);
					fRequirementDetailsFIS = new FileInputStream(
							System.getProperty("user.dir") + "//resources//properties//RequirementsDetails.properties");
					RequirementDetailsPropertyFile.load(fRequirementDetailsFIS);

					fNewRequirementsDetails = new FileInputStream(System.getProperty("user.dir")
							+ "//resources/properties//NewRequirementsDetails.properties");
					NewRequirementsDetailsPropertyFile.load(fNewRequirementsDetails);

					// log.debug("login property File Loaded sucessfully");
				} catch (Exception e) {

					e.printStackTrace();
				}

			} else {

				try {

					/*
					 * fConfig = new FileInputStream(
					 * System.getProperty("user.dir") +
					 * "/resources/properties/Config.properties");
					 */
					fConfig = new FileInputStream(
							ResourceHelper.getResourcePath("/resources/properties/Config.properties"));
					Config.load(fConfig);
					log.debug("Config File Loaded sucessfully");
					/*
					 * fOR = new FileInputStream(System.getProperty("user.dir")
					 * + "/resources/properties/OR.properties"); OR.load(fOR);
					 */
					log.debug("OR File Loaded sucessfully");
					fhomepage = new FileInputStream(
							ResourceHelper.getResourcePath("/resources/properties/Requirements.properties"));
					// System.getProperty("user.dir") +
					// "/resources/properties/Requirements.properties");
					hompagePropertyFile.load(fhomepage);
					log.debug("homepage File Loaded sucessfully");
					floginpage = new FileInputStream(
							ResourceHelper.getResourcePath("/resources/properties/LoginPage.properties"));
					// System.getProperty("user.dir") +
					// "/resources/properties/LoginPage.properties");
					loginPropertyFile.load(floginpage);
					log.debug("login property File Loaded sucessfully");
					fCandidateDetails = new FileInputStream(
							ResourceHelper.getResourcePath("/resources/properties/CandidateDetails.properties"));
					// System.getProperty("user.dir") +
					// "/resources/properties/CandidateDetails.properties");
					CandidateDetailsPropertyFile.load(fCandidateDetails);
					fCandidateList = new FileInputStream(
							ResourceHelper.getResourcePath("/resources/properties/CandidateList.properties"));
					// System.getProperty("user.dir") +
					// "/resources/properties/CandidateList.properties");
					CandidateListPropertyFile.load(fCandidateList);

					fRequirementDetailsFIS = new FileInputStream(
							ResourceHelper.getResourcePath("/resources/properties/RequirementsDetails.properties"));
					// System.getProperty("user.dir") +
					// "/resources/properties/RequirementsDetails.properties");
					RequirementDetailsPropertyFile.load(fRequirementDetailsFIS);

					fNewRequirementsDetails = new FileInputStream(
							ResourceHelper.getResourcePath("/resources/properties/NewRequirementsDetails.properties"));
					// System.getProperty("user.dir") +
					// "/resources/properties/NewRequirementsDetails.properties");
					NewRequirementsDetailsPropertyFile.load(fNewRequirementsDetails);

					fDashboard = new FileInputStream(
							ResourceHelper.getResourcePath("/resources/properties/Dashboard.properties"));
					// System.getProperty("user.dir") +
					// "/resources/properties/NewRequirementsDetails.properties");
					DashboardFile.load(fDashboard);

				} catch (Exception e) {

					e.printStackTrace();
				}

			}

		}

	}

	@BeforeTest
	public void setUp1() {
		TestBase.initializeTestBaseSetup(Config.getProperty("browser"), Config.getProperty("testsiteurl"));
		log.info("open url succssfully");
		System.out.println(Config.getProperty("testsiteurl"));
		log.info(Config.getProperty("testsiteurl"));
		alerhelper = new AlertHelper(driver);
		browserhelper = new BrowserHelper(driver);
		dropdownhelper = new DropDownHelper(driver);
		javascripthelper = new JavaScriptHelper(driver);
		waithelper = new WaitHelper(driver);
	}

	private static WebDriver setDriver(String browserType, String appURL) {

		LocalDriverFactory.initilize(browserType);
		driver=LocalDriverFactory.getDriver();
		driver.manage().deleteAllCookies();
		driver.get(appURL);
		driver.manage().window().maximize();
		/*switch (browserType.toLowerCase()) {
		case "chrome":
			driver = initChromeDriver(appURL);
			log.info("chrome browser");
			// test=extent.startTest("Chrome browser");
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			log.info("firefox browser");
			// test=extent.startTest("firefox browser");
			break;
		case "ie":
			driver = initIEDriver(appURL);
			log.info("IE browser");
			// test=extent.startTest("html browser");
			break;
		default:
			log.info("browser :" + browserType + " is invalid, browser of choice..");
			System.out.println("browser : " + browserType + " is invalid,  browser of choice..");
			// driver = initFirefoxDriver(appURL);
		}*/
		return driver;
	}

	/*private static WebDriver initChromeDriver(String appURL) {

		// System.out.println("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver",
				ResourceHelper.getResourcePath("\\resources\\drivers\\chromedriver.exe"));

		driver = new ChromeDriver();
		SessionId session = ((ChromeDriver) driver).getSessionId();
		System.out.println("Session id: " + session.toString());
		
		 * EventFiringWebDriver driver = new EventFiringWebDriver(driver1);
		 * WebEventListener eventListener= new WebEventListener();
		 * driver.register(eventListener);
		 

		// driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.navigate().to(appURL);

		// test=extent.startTest("Redirected to URL");
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		// System.out.println("Launching firefox with new profile..");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);

		System.setProperty("webdriver.gecko.driver",
				ResourceHelper.getResourcePath("\\resources\\drivers\\geckodriver.exe"));
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.navigate().to(appURL);
		// test=extent.startTest("Redirected to URL");
		return driver;
	}

	private static WebDriver initIEDriver(String appURL) {
		System.setProperty("webdriver.gecko.driver",
				ResourceHelper.getResourcePath("\\resources\\drivers\\IEDriverServer.exe"));
		// System.setProperty("webdriver.ie.driver", driverPath +
		// "IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.navigate().to(appURL);
		// test=extent.startTest("Redirected to URL");
		return driver;
	}
*/
	// new HtmlUnitDriver()
	public static void initializeTestBaseSetup(String browserType, String appURL) {
		try {
			PropertyConfigurator.configure(ResourceHelper.getResourcePath("\\resources\\logs\\log4j.properties"));
			setDriver(browserType, appURL);
			log.info("creating object of " + browserType + "and URL of: " + appURL);
		} catch (Exception e) {
			System.out.println("BrowserType Error....." + e.getStackTrace());
		}
	}

	public static void waitForVisitibilty(WebElement element) {
		wait = new WebDriverWait(driver, 60);
		// wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void click(WebElement element) {
		waitForVisitibilty(element);
		element.click();
	}

	public static void clear(WebElement element) {
		waitForVisitibilty(element);
		element.clear();
	}

	public static void sendKeys(WebElement element, String keyword) {
		waitForVisitibilty(element);
		element.clear();
		element.sendKeys(keyword);
	}

	public static boolean isDisplayed(WebElement element) {
		boolean present = false;
		waitForVisitibilty(element);
		try {
			present = element.isDisplayed();
			log.info(element.getText() + " is dispalyed");
			// return element.isDisplayed();
		} catch (Exception e) {
			log.error("Element not found " + e);
			// log.info(e.getMessage());
		}
		return present;
	}

	public static boolean isElementPresent(WebElement element) {

		waitForVisitibilty(element);
		log.info("element is present:" + element.toString());
		return element.isDisplayed();

	}

	public static String getText(WebElement element) {
		waitForVisitibilty(element);
		return element.getText();
	}

	public static WebElement linkText(WebElement element, String Keyword) {
		waitForVisitibilty(element);
		return element = driver.findElement(By.partialLinkText(Keyword));
	}

	public static String getAttributeVolume(WebElement element, String attribute) {
		waitForVisitibilty(element);
		return element.getAttribute(attribute);
	}

	public static void SelectByText(String tagname, String text) {
		log.info("Text=" + text + "\t" + "tagname=" + tagname);
		driver.findElement(By.xpath("//'" + tagname + "'[contains(text(),'" + text + "')]"));
	}

	public static ElementLoad ElementLoad() {

		try {
			ElementLoaderObj = new ElementLoad();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return ElementLoaderObj;
	}

	public static String[][] getData(String excelname, String sheetName) {
		String[][] data = null;

		try {
			String excelpath = ResourceHelper.getBaseResourcePath() + "\\resources\\excel\\" + excelname;
			log.info("excel path:" + excelpath);
			// System.out.println("excel path:" + excelpath);
			excel = new Excel_Reader(excelpath);
			data = excel.getDataFromSheet(sheetName);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}

	public static String getCellData(String excelname, String sheetName, String colName, int rowNum) {
		String data = null;

		try {
			String excelpath = ResourceHelper.getBaseResourcePath() + "\\resources\\excel\\" + excelname;
			log.info("excel path:" + excelpath);
			// System.out.println("excel path:" + excelpath);
			excel = new Excel_Reader(excelpath);
			data = excel.getCellData(sheetName, colName, rowNum);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}

	public static void Select(WebElement element, String visibletext) {
		waitForVisitibilty(element);
		Select oSelect = new Select(element);
		List<WebElement> oSize = oSelect.getOptions();
		int iListSize = oSize.size();
		for (int i = 0; i < iListSize; i++) {
			// Storing the value of the option
			String sValue = oSelect.getOptions().get(i).getText();
			// Printing the stored value
			System.out.println(sValue);
			// Putting a check on each option that if any of the option is equal
			// to 'Africa" then select it
			if (sValue.equals(visibletext)) {
				oSelect.selectByIndex(i);
				break;
			}
		}
	}

	public static WebElement waitElement(String element) {
		String s = element;
		By t1 = By.xpath(s);
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(t1));
		return ele;
	}

	public static List<WebElement> waitElements(String element) {
		String s = element;
		By t1 = By.xpath(s);
		List<WebElement> t = driver.findElements(t1);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		List<WebElement> foo = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(t1));
		// .presenceOfAllElementsLocatedBy(t1));
		System.out.println("size=" + foo.size());
		return foo;
	}

	public static void JavaExecute(WebElement element) {
		waitForVisitibilty(element);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static int switchFrame(WebDriver driver, By by) {
		int i;
		int framcount = driver.findElements(By.xpath("iframe")).size();
		for (i = 0; i < framcount; i++) {
			driver.switchTo().frame(i);
			int count = driver.findElements(by).size();
			if (count > 0) {
				driver.findElement(by).click();
				break;
			} else {
				System.out.println("count frame" + i);
			}

		}
		driver.switchTo().defaultContent();
		return i;

	}

	public static boolean switchWindow(String title) {
		String currentWindow = driver.getWindowHandle();
		Set<String> availableWindows = driver.getWindowHandles();
		System.out.println("availableWindows size:" + availableWindows.size());
		try {
			if (!availableWindows.isEmpty()) {
				System.out.println("if is not isEmpty");
				for (String windowId : availableWindows) {
					System.out.println("title of window:" + driver.switchTo().window(windowId).getTitle());
					try {
						if (driver.switchTo().window(windowId).getTitle().equals(title)) {
							System.out.println("if inside if condition");
							// driver.switchTo().window(windowId);
							return true;
						} else {
							driver.switchTo().window(currentWindow);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("not...valid");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public static void logout() {
		log.info("**********Logout SourcePros**********");

		test = extent.startTest("Logout SourcePros");
		test.log(LogStatus.INFO, "Logout SourcePros");
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
	}

	@AfterSuite(alwaysRun = true)
	public static void close() {
		log.info("**********AfterSuite Close Browser**********");

		test = extent.startTest("AfterSuite Close Browser");
		test.log(LogStatus.INFO, "Close close successfully:");
		test.log(LogStatus.INFO, "Driver quite successfully:");
		driver.close();
		driver.quit();
		driver = null;
	}

}
