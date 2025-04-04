package com.test.automation.UIAutomation.tests;

import com.test.automation.UIAutomation.utility.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.test.automation.UIAutomation.LoginPage.LoginPage;
import com.test.automation.UIAutomation.testBase.TestBase;

public class VerifiyLoginWithDifferentRecords extends TestBase {
	SoftAssert s_assert = new SoftAssert();

	@DataProvider(name = "loginData")
	public String[][] getTestData() {
		// String[][] testRecords = getData("TestData.xlsx", "LoginTestData");
		String[][] testRecords = getData(Config.getProperty("exelsheetname"), Config.getProperty("LoginsheetName"));
		return testRecords;
	}

	/*
	 * @BeforeTest public void setUp1() {
	 * 
	 * TestBase.initializeTestBaseSetup(Config.getProperty("browser"),
	 * Config.getProperty("testsiteurl")); Logger.info("open url succssfully");
	 * System.out.println(Config.getProperty("testsiteurl"));
	 * Logger.info(Config.getProperty("testsiteurl"));
	 * 
	 * }
	 */

	@Test(dataProvider = "loginData", timeOut = 10000)
	public void verifiyLoginWithDifferentRecords(String emailAddress, String password, String runMode)
			throws InterruptedException {
		

		if (runMode.equalsIgnoreCase("n")) {
			Logger.info("verifiyLoginWithDifferentRecords method skipped");
			test = extent.startTest("verifiyLoginWithDifferentRecords");
			test.log(LogStatus.INFO, "verifiyLoginWithDifferentRecords method skipped");
			test.log(LogStatus.SKIP, "Login and logout skipped");
			throw new SkipException("Skipping this exception");

		}

		else {
			try {
				jsp = new LoginPage(driver);
				// jsp.beforelogin();
				driver.navigate().refresh();
				Logger.info("**********starting test**********");

				test = extent.startTest("verifiyLoginWithDifferentRecords");

				Logger.info("try to login with username:" + emailAddress + " and password" + password);
				test.log(LogStatus.INFO, "Email:" + emailAddress + " and password : " + password);

				boolean LoginSuccessStatus = LoginPage.loginToApplication(emailAddress, password);
				Logger.info("LoginSuccessStatus:" + LoginSuccessStatus);
				Assert.assertEquals(true, LoginSuccessStatus);
				logout();
				boolean LogOutSuccessStatus = jsp.verifyLogOutSuccess();
				s_assert.assertEquals(LogOutSuccessStatus, true);
				test.log(LogStatus.INFO, "Logout successfully:" + LoginSuccessStatus);
				s_assert.assertAll();
				Logger.info("Login functionality working fine");
				Logger.info("**********finish test************");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
