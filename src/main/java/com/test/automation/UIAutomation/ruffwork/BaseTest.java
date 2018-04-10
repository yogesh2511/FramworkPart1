package com.test.automation.UIAutomation.ruffwork;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.test.automation.UIAutomation.extendReport.ExtentUtil;

public class BaseTest {

	static WebDriver driver=null;
	public ExtentReports extent=ExtentUtil.createReporter("D:\\Java_Project\\SourceProsUSA\\test-output\\New folder\\extentreport.html");
	public ExtentTest test=null;
	@BeforeTest
	public static WebDriver setUp()
	{
		System.setProperty("webdriver.chrome.driver",
				"D:\\Java_Project\\UIAutomation\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}
/*	@BeforeSuite
	public static void setUp2() {
		if (ExtentUtil.getExtentReports() == null) {
			ExtentUtil.createReporter("extentreport.html");
		}
	}

	@AfterClass
	public static void tearDown() {
		ExtentUtil.saveReporter();
	}*/
	public static File getScreenShot() {
		File dest = null;
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			dest = new File("D:\\Java_Project\\SourceProsUSA\\test-output\\New folder\\test.png");
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dest;
	}
}
