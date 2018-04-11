package com.test.automation.UIAutomation.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.test.automation.UIAutomation.LandingPage.HomePage;
import com.test.automation.UIAutomation.LandingPage.Pages;
import com.test.automation.UIAutomation.LoginPage.LoginPage;
import com.test.automation.UIAutomation.config.ConfigConfiguration;
import com.test.automation.UIAutomation.config.RequirementConfig;
import com.test.automation.UIAutomation.testBase.TestBase;
import com.test.automation.UIAutomation.uiActions.RequirementsLifeCycle.RequirmentLifeCycle;

public class CandidateCycle extends TestBase {

	public static Logger log = Logger.getLogger(CandidateCycle.class.getName());
	public static String requirementList = Requirementconfig.Requirement;
	public static String candidateName = Requirementconfig.CandidateName;

	@DataProvider(name = "SingleLoginTestData")
	public String[][] getTestData() {
		
		String[][] testRecords = getData(Config.getProperty("exelsheetname"),
				Config.getProperty("SingleLoginTestData"));
		return testRecords;
	}



	@Test(dataProvider = "SingleLoginTestData")
	public static void login(String emailAddress, String password, String runMode) throws InterruptedException {

		if (runMode.equalsIgnoreCase("n")) {
			log.info("verifiy Login With Different Records method skipped");
			// test = extent.startTest("verifiy Login With Different Records");
			test.log(LogStatus.INFO, "verifiy Login With Different Records method skipped");
			test.log(LogStatus.SKIP, "Login and logout skipped");
			throw new SkipException("Skipping this exception");

		} else {

			log.info("starting login test");
			test = extent.startTest("Candidate Life Cycle SourceProc US User Login");
			try {
				Pages.loginpage();
				LoginPage.loginToApplication(emailAddress, password);

				test.log(LogStatus.INFO,
						"Login successfully: username:" + emailAddress + "\t" + " password" + password + "\t");
				log.info("Enter username and password");
				test.log(LogStatus.INFO, "Login method successfully:");

			} catch (Exception e) {

				log.info(e.getMessage());
			}
		}
	}

	@Test(dependsOnMethods = { "login" }, alwaysRun = true)
	public static void selectMatches() {
		test = extent.startTest("Select Matches");
		try {
			Pages.homapage();
			javascripthelper.clickElementByJS(HomePage.selectOption(requirementList, "Matches"));
			test.log(LogStatus.INFO, "Select Matches");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "selectMatches" }, alwaysRun = true)
	public static void AddCandidateReferences() {
		test = extent.startTest("Add Candidate References");
		try {
			switchWindow("Candidate Matches");
			Thread.sleep(3000);
			javascripthelper.clickElementByJS(RequirmentLifeCycle.selectCandidate(candidateName));
			test.log(LogStatus.INFO, "Selected Candidate");
			switchWindow("Candidate Detail");
			test.log(LogStatus.INFO, "Switch to Candidate Detail");
			javascripthelper.clickElementByJS(Pages.CandidateDetailPage().SelenctTabInfo("Additional Info"));
			test.log(LogStatus.INFO, "Selected Additional info");
			for (int i = 1; i <= 2; i++) {
				Thread.sleep(3000);
				System.out.println("loop:" + i);
				javascripthelper.clickElementByJS(Pages.CandidateDetailPage().addReference());
				test.log(LogStatus.INFO, "Selected Add reference");
				javascripthelper.javaScriptSendText(Pages.CandidateDetailPage().refContactName(), "Testing");
				test.log(LogStatus.INFO, "Contact name Entered");
				javascripthelper.javaScriptSendText(Pages.CandidateDetailPage().refContactNo(), "123-456-7890");
				test.log(LogStatus.INFO, "Contact number Entered");
				javascripthelper.javaScriptSendText(Pages.CandidateDetailPage().refEmail(), "test@test.com");
				test.log(LogStatus.INFO, "Email entered");
				javascripthelper.clickElementByJS(Pages.CandidateDetailPage().refSave());
				test.log(LogStatus.INFO, "Reference Save button clicked");
			}
			javascripthelper.clickElementByJS(Pages.CandidateDetailPage().bottomSavebtn());
			test.log(LogStatus.INFO, "Save button clicked");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "AddCandidateReferences" }, alwaysRun = true)
	public static void assessment() {
		test = extent.startTest("Assessment To Prospective");
		try {
			Thread.sleep(3000);
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matche window");		
			Thread.sleep(3000);
			javascripthelper.clickElementByJS(RequirmentLifeCycle.assessmentCall(candidateName));
			test.log(LogStatus.INFO, "Select Call");
			switchWindow("Conversation");
			test.log(LogStatus.INFO, "Switch to conversation window"); //
			javascripthelper.clickElementByJS(Pages.Conversation().phoneNumber());
			Pages.Conversation().phoneNumber().sendKeys("7698950001");
			test.log(LogStatus.INFO, "Enter phone number");
			javascripthelper.clickElementByJS(Pages.Conversation().conversion("Confirmed for this requirement"));
			test.log(LogStatus.INFO, "Select click on confirmation");
			sendKeys(Pages.Conversation().summary(), "summary");
			test.log(LogStatus.INFO, "Entered summary");
			sendKeys(Pages.Conversation().timeToJoin(), "yes...........");
			test.log(LogStatus.INFO, "Enterd time to join");
			Pages.Conversation().isConfirmationReceived("yes");
			test.log(LogStatus.INFO, "confiremation Received");
			Pages.Conversation().save();
			test.log(LogStatus.INFO, "click on save button");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3 seconds...");
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matches window");
			Select(Pages.RequirmentLifeCycle().assessmentAction(candidateName), "Prospective");
			test.log(LogStatus.INFO, "Select propspective");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "waiting for 3seconds...");
			sendKeys(Pages.Prospective().payRateAmount(), "1000");
			test.log(LogStatus.INFO, "Entered pay Rate amount");
			Select(Pages.Prospective().contractType(), "W2-ExhibitA");
			test.log(LogStatus.INFO, "Select contrect type");
			sendKeys(Pages.Prospective().distance(), "100");
			test.log(LogStatus.INFO, "Entered distance");
			sendKeys(Pages.Prospective().uploadBackgroundCheck(),
					"C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg");
			test.log(LogStatus.INFO, "provide path to upload file");
			click(Pages.Prospective().termAndCondition());
			test.log(LogStatus.INFO, "Click on term and condition");
			Pages.Prospective().save();
			test.log(LogStatus.INFO, "Click on save");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "assessment" }, alwaysRun = true)
	// @Test(dependsOnMethods = { "selectMatches" }, alwaysRun = true) public
	public static void prospective() {
		test = extent.startTest("Prospective To Submission");
		try {
			// click(Pages.RequirmentLifeCycle().selectLifeCycle("Prospective"));
			// test.log(LogStatus.INFO, "Select prospective");
			Thread.sleep(3000);
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matche window");
			Thread.sleep(3000);
			javascripthelper.clickElementByJS(Pages.RequirmentLifeCycle().prospectiveSMS(candidateName));
			test.log(LogStatus.INFO, "Select SMS option in Prospective page");
			switchWindow("SMS Send To Candidate");
			test.log(LogStatus.INFO, "Switch to SMS window");
			sendKeys(Pages.SMSSendToCandidate().textboxPhoneNo(), "7698950001");
			test.log(LogStatus.INFO, "Entered phone number");
			javascripthelper.clickElementByJS(Pages.SMSSendToCandidate().sendMessage());
			test.log(LogStatus.INFO, "Click on send");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "wait for 3seconds...");
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matches window...");
			javascripthelper.clickElementByJS(RequirmentLifeCycle.prospectiveCall(candidateName));
			test.log(LogStatus.INFO, "click on Call option in prospective page");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "wait for 3seconds...");
			switchWindow("Conversation");
			test.log(LogStatus.INFO, "Switch to conversation window");
			Pages.Conversation().phoneNumber().sendKeys("7698950001");
			test.log(LogStatus.INFO, "Enter phone number");
			javascripthelper.clickElementByJS(Pages.Conversation().conversion("Confirmed for this requirement"));
			test.log(LogStatus.INFO, "Click on conversion option");
			sendKeys(Pages.Conversation().summary(), "summary");
			test.log(LogStatus.INFO, "Entered summary");
			sendKeys(Pages.Conversation().timeToJoin(), "yes...........");
			test.log(LogStatus.INFO, "Enter time to join");
			Pages.Conversation().isConfirmationReceived("yes");
			test.log(LogStatus.INFO, "Select yes for isconfirmation radiobox");
			Pages.Conversation().save();
			test.log(LogStatus.INFO, "Click on Save");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Wait for 3seconds...");
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to candidate matches window");
			javascripthelper.clickElementByJS(Pages.RequirmentLifeCycle().prospectiveEditView(candidateName));
			test.log(LogStatus.INFO, "Click on Edit View in prospective window");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds...");
			sendKeys(Pages.Prospective().distance(), "100");
			test.log(LogStatus.INFO, "Entered distance");
			Select(Pages.Prospective().contractType(), "W2-ExhibitA");
			test.log(LogStatus.INFO, "Entered contract type");
			sendKeys(Pages.Prospective().uploadBackgroundCheck(),
					"C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg");
			test.log(LogStatus.INFO, "Upload the back ground check file");
			javascripthelper.clickElementByJS(Pages.Prospective().termAndCondition());
			test.log(LogStatus.INFO, "Select term and condition");
			Pages.Prospective().save();
			test.log(LogStatus.INFO, "Click on Save");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds...");
			Select(Pages.RequirmentLifeCycle().prospectiveAction(candidateName), "Submission");
			test.log(LogStatus.INFO, "Entered Bill Rate");
			sendKeys(Pages.Submission().SubmittedBillRate(), "100");
			test.log(LogStatus.INFO, "Select Action in prospective page");
			Pages.Submission().uploadFile().sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg");
			test.log(LogStatus.INFO, "upload file");
			Select(Pages.Submission().submissionStatus(), "Submitted");
			test.log(LogStatus.INFO, "Select submit");
			javascripthelper.clickElementByJS(Pages.Submission().termsCondtion());
			test.log(LogStatus.INFO, "Select term and condition");
			javascripthelper.clickElementByJS(Pages.Submission().save());
			test.log(LogStatus.INFO, "Select Save");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "prospective" }, alwaysRun = true)
	public static void submission() {

		test = extent.startTest("Submission To Shortlisted");
		try {
			// click(Pages.RequirmentLifeCycle().selectLifeCycle("Submission"));
			Thread.sleep(3000);
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matche window");
			Thread.sleep(3000);
			javascripthelper.clickElementByJS(RequirmentLifeCycle.submissionCall(candidateName));
			test.log(LogStatus.INFO, "Select Call");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds...");
			switchWindow("Conversation");
			test.log(LogStatus.INFO, "Swithch to Conversation window");
			Pages.Conversation().phoneNumber().sendKeys("7698950001");
			test.log(LogStatus.INFO, "Entered phone number");
			javascripthelper.clickElementByJS(Pages.Conversation().conversion("Confirmed for this requirement"));
			test.log(LogStatus.INFO, "Select Confirm");
			sendKeys(Pages.Conversation().summary(), "summary");
			test.log(LogStatus.INFO, "Entered summary");
			sendKeys(Pages.Conversation().timeToJoin(), "yes...........");
			test.log(LogStatus.INFO, "Entered time to join...");
			Pages.Conversation().isConfirmationReceived("yes");
			test.log(LogStatus.INFO, "selected for confiramation");
			Pages.Conversation().save();
			test.log(LogStatus.INFO, "Select Save");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds...");
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to candidate matches window");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds...");
			javascripthelper.clickElementByJS(Pages.RequirmentLifeCycle().submissionEditView(candidateName));
			test.log(LogStatus.INFO, "Select Edit view");
			sendKeys(Pages.Submission().uploadFile(),
					"C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg");
			test.log(LogStatus.INFO, "Upload file");
			javascripthelper.clickElementByJS(Pages.Submission().termsCondtion());
			test.log(LogStatus.INFO, "Select term and condition");
			javascripthelper.clickElementByJS(Pages.Submission().save());
			test.log(LogStatus.INFO, "Click Save");
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate matches");
			javascripthelper.clickElementByJS(Pages.RequirmentLifeCycle().submissionDetails(candidateName));
			test.log(LogStatus.INFO, "Click on submission Details");
			switchWindow("Submission Details");
			test.log(LogStatus.INFO, "Switch to submission window");
			Select(Pages.SubmissionDetailsPage().selectRCTLAM(), "Hetal");
			test.log(LogStatus.INFO, "Select TL");
			javascripthelper.clickElementByJS(Pages.SubmissionDetailsPage().selectAccept());
			test.log(LogStatus.INFO, "Select Accept");
			Select(Pages.SubmissionDetailsPage().selectQualityOfResume(), "Excellent Resume, Good work done.");
			test.log(LogStatus.INFO, "Select QualityOfResume");
			javascripthelper.clickElementByJS(Pages.SubmissionDetailsPage().save());
			test.log(LogStatus.INFO, "Select Save");
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matches");
			Select(Pages.RequirmentLifeCycle().submissionAction(candidateName), "Shortlisted");
			test.log(LogStatus.INFO, "Select Action");
			switchWindow("Shortlisted");
			test.log(LogStatus.INFO, "Swithch to Shortlisted window");
			javascripthelper.clickElementByJS(Pages.Shortlisted().interviewReceived());
			test.log(LogStatus.INFO, "Select intervied Received");
			sendKeys(Pages.Shortlisted().comments(), "testing");
			test.log(LogStatus.INFO, "Entered comments");
			javascripthelper.clickElementByJS(Pages.Shortlisted().save());
			test.log(LogStatus.INFO, "Select save");
			switchWindow("Email");
			test.log(LogStatus.INFO, "Switch to Email window");
			javascripthelper.clickElementByJS(Pages.Email().send());
			test.log(LogStatus.INFO, "Select send");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "submission" }, alwaysRun = true)
	public static void shortlisted() {
		test = extent.startTest("Shortlisted To Interview");
		try {
			Thread.sleep(3000);
			switchWindow("Candidate Matches");
			javascripthelper.clickElementByJS(Pages.RequirmentLifeCycle().selectLifeCycle("Shortlisted"));
			
			Thread.sleep(3000);
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matche window");
			Thread.sleep(3000);
			javascripthelper.clickElementByJS(Pages.RequirmentLifeCycle().shortlistedSMS(candidateName));
			test.log(LogStatus.INFO, "Select SMS for submission");
			switchWindow("SMS Send To Candidate");
			test.log(LogStatus.INFO, "Switch to SMS");
			sendKeys(Pages.SMSSendToCandidate().textboxPhoneNo(), "7698950001");
			test.log(LogStatus.INFO, "Entered phone number");
			javascripthelper.clickElementByJS(Pages.SMSSendToCandidate().sendMessage());
			test.log(LogStatus.INFO, "Select send message");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds...");
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matche window");
			javascripthelper.clickElementByJS(RequirmentLifeCycle.shortlistedCall(candidateName));
			test.log(LogStatus.INFO, "Select shortlisted Call");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds...");
			switchWindow("Conversation");
			test.log(LogStatus.INFO, "Switch to conversation window");
			Pages.Conversation().phoneNumber().sendKeys("7698950001");
			test.log(LogStatus.INFO, "Enterd phone number");
			javascripthelper.clickElementByJS(Pages.Conversation().conversion("Confirmed for this requirement"));
			test.log(LogStatus.INFO, "Select Conversion");
			sendKeys(Pages.Conversation().summary(), "summary");
			test.log(LogStatus.INFO, "Entered Summary");
			sendKeys(Pages.Conversation().timeToJoin(), "yes...........");
			test.log(LogStatus.INFO, "Entered time to join");
			Pages.Conversation().isConfirmationReceived("yes");
			test.log(LogStatus.INFO, "click on confirmation revceid");
			Pages.Conversation().save();
			test.log(LogStatus.INFO, "Select Save");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds...");
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matche window");
			javascripthelper.clickElementByJS(Pages.RequirmentLifeCycle().shortlistedEditView(candidateName));
			test.log(LogStatus.INFO, "Click on Edit view");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds");
			switchWindow("Shortlisted");
			test.log(LogStatus.INFO, "Switch to shortlisted window");
			javascripthelper.clickElementByJS(Pages.Shortlisted().interviewReceived());
			test.log(LogStatus.INFO, "click on interview received");
			sendKeys(Pages.Shortlisted().comments(), "C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg");
			test.log(LogStatus.INFO, "Entered data in comments");
			javascripthelper.clickElementByJS(Pages.Shortlisted().save());
			test.log(LogStatus.INFO, "Click on Save");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds");
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matches window");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds");
			javascripthelper.clickElementByJS(Pages.RequirmentLifeCycle().shortlistedDetails(candidateName));
			test.log(LogStatus.INFO, "Click on shortlisted detaisl");
			switchWindow("Submission Details");
			test.log(LogStatus.INFO, "Swithch to submission window");
			Select(Pages.SubmissionDetailsPage().selectRCTLAM(), "Hetal");
			test.log(LogStatus.INFO, "Select TL");
			javascripthelper.clickElementByJS(Pages.SubmissionDetailsPage().selectAccept());
			test.log(LogStatus.INFO, "Click on Accept");
			Select(Pages.SubmissionDetailsPage().selectQualityOfResume(), "Excellent Resume, Good work done.");
			test.log(LogStatus.INFO, "Select quality of resume");
			javascripthelper.clickElementByJS(Pages.SubmissionDetailsPage().save());
			test.log(LogStatus.INFO, "Select Save");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds.");
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matche window");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds.");
			Select(Pages.RequirmentLifeCycle().shortlistedAction(candidateName), "Interview");
			test.log(LogStatus.INFO, "Select Shorlisted Action");
			switchWindow("Interview");
			test.log(LogStatus.INFO, "Switch to Interview window");
			Select(Pages.Interview().dropdownInterviewAppeal(), "Accepted");
			test.log(LogStatus.INFO, "Select interview appeal");
			sendKeys(Pages.Interview().interviewDateTime(), "03/22/2018 13:00" + Keys.ENTER);
			test.log(LogStatus.INFO, "Enter interview date and time");
			Select(Pages.Interview().dropdownInterviewType(), "In Person");
			test.log(LogStatus.INFO, "Select interview type");
			Select(Pages.Interview().dropdownInterviewResult(), "Completed");
			test.log(LogStatus.INFO, "Select Interview Result");
			sendKeys(Pages.Interview().referenceCheckDocument(),
					"C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg");
			test.log(LogStatus.INFO, "Enter reference check ducument");
			javascripthelper.clickElementByJS(Pages.Interview().save());
			test.log(LogStatus.INFO, "Select Save");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "shortlisted" }, alwaysRun = true)
	public static void interview() {
		test = extent.startTest("Interview To Offer Received");
		try {
			//click(Pages.RequirmentLifeCycle().selectLifeCycle("Interview"));
			Thread.sleep(3000);
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matche window");
			Thread.sleep(3000);
			javascripthelper.clickElementByJS(Pages.RequirmentLifeCycle().interviewSMS(candidateName));
			test.log(LogStatus.INFO, "Select SMS ");
			switchWindow("SMS Send To Candidate");
			test.log(LogStatus.INFO, "Switch to SMS");
			sendKeys(Pages.SMSSendToCandidate().textboxPhoneNo(), "7698950001");
			test.log(LogStatus.INFO, "Entered phone number");
			javascripthelper.clickElementByJS(Pages.SMSSendToCandidate().sendMessage());
			test.log(LogStatus.INFO, "Select send message");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds...");
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matche window");
			javascripthelper.clickElementByJS(RequirmentLifeCycle.interviewCall(candidateName));
			test.log(LogStatus.INFO, "Select shortlisted Call");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds...");
			switchWindow("Conversation");
			test.log(LogStatus.INFO, "Switch to conversation window");
			Pages.Conversation().phoneNumber().sendKeys("7698950001");
			test.log(LogStatus.INFO, "Enterd phone number");
			javascripthelper.clickElementByJS(Pages.Conversation().conversion("Confirmed for this requirement"));
			test.log(LogStatus.INFO, "Select Conversion");
			sendKeys(Pages.Conversation().summary(), "summary");
			test.log(LogStatus.INFO, "Entered Summary");
			sendKeys(Pages.Conversation().timeToJoin(), "yes...........");
			test.log(LogStatus.INFO, "Entered time to join");
			Pages.Conversation().isConfirmationReceived("yes");
			test.log(LogStatus.INFO, "click on confirmation revceid");
			Pages.Conversation().save();
			test.log(LogStatus.INFO, "Select Save");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds...");
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matche window");
			javascripthelper.clickElementByJS(Pages.RequirmentLifeCycle().interviewEditView(candidateName));
			test.log(LogStatus.INFO, "Click on Edit view");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds");
			switchWindow("Interview");
			test.log(LogStatus.INFO, "Switch to Interview window");
			Select(Pages.Interview().dropdownInterviewAppeal(), "Accepted");
			test.log(LogStatus.INFO, "Select interview appeal");
			sendKeys(Pages.Interview().interviewDateTime(), "03/22/2018 13:00" + Keys.ENTER);
			test.log(LogStatus.INFO, "Enter interview date and time");
			Select(Pages.Interview().dropdownInterviewType(), "In Person");
			test.log(LogStatus.INFO, "Select interview type");
			Select(Pages.Interview().dropdownInterviewResult(), "Completed");
			test.log(LogStatus.INFO, "Select Interview Result");
			sendKeys(Pages.Interview().referenceCheckDocument(),
					"C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg");
			test.log(LogStatus.INFO, "Enter reference check ducument");
			javascripthelper.clickElementByJS(Pages.Interview().save());
			test.log(LogStatus.INFO, "Select Save");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds");
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matche window");
			javascripthelper.clickElementByJS(Pages.RequirmentLifeCycle().interviewDetails(candidateName));
			test.log(LogStatus.INFO, "Click on interview Details");
			switchWindow("Submission Details");
			test.log(LogStatus.INFO, "Swithch to submission window");
			Select(Pages.SubmissionDetailsPage().selectRCTLAM(), "Hetal");
			test.log(LogStatus.INFO, "Select TL");
			javascripthelper.clickElementByJS(Pages.SubmissionDetailsPage().selectAccept());
			test.log(LogStatus.INFO, "Click on Accept");
			Select(Pages.SubmissionDetailsPage().selectQualityOfResume(), "Excellent Resume, Good work done.");
			test.log(LogStatus.INFO, "Select quality of resume");
			javascripthelper.clickElementByJS(Pages.SubmissionDetailsPage().save());
			test.log(LogStatus.INFO, "Select Save");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds.");
			switchWindow("Candidate Matches");
			Select(Pages.RequirmentLifeCycle().interviewAction(candidateName), "Offer Received");
			test.log(LogStatus.INFO, "Click on Action");
			switchWindow("OfferReceived");
			test.log(LogStatus.INFO, "switch to offer received");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds.");
			javascripthelper.clickElementByJS(Pages.OfferReceived().selectOfferStatus("Offer Received"));
			test.log(LogStatus.INFO, "select offer status");
			Select(Pages.OfferReceived().selectPayRateType(), "Monthly");
			test.log(LogStatus.INFO, "select payrate type");
			Pages.OfferReceived().startDate("03/14/2018");
			test.log(LogStatus.INFO, "Entered start date");
			javascripthelper.clickElementByJS(Pages.OfferReceived().save());
			test.log(LogStatus.INFO, "Select save");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "interview" }, alwaysRun = true)
	public static void offerRecived() {
		test = extent.startTest("OfferRecived to OfferAccepted");
		try {
			// click(Pages.RequirmentLifeCycle().selectLifeCycle("Offer
			// Received"));
			Thread.sleep(3000);
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Match window");
			Thread.sleep(3000);
			javascripthelper.clickElementByJS(RequirmentLifeCycle.interviewCall(candidateName));
			test.log(LogStatus.INFO, "Select shortlisted Call");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds...");
			switchWindow("Conversation");
			test.log(LogStatus.INFO, "Switch to conversation window");
			Pages.Conversation().phoneNumber().sendKeys("7698950001");
			test.log(LogStatus.INFO, "Enterd phone number");
			javascripthelper.clickElementByJS(Pages.Conversation().conversion("Confirmed for this requirement"));
			test.log(LogStatus.INFO, "Select Conversion");
			sendKeys(Pages.Conversation().summary(), "summary");
			test.log(LogStatus.INFO, "Entered Summary");
			sendKeys(Pages.Conversation().timeToJoin(), "yes...........");
			test.log(LogStatus.INFO, "Entered time to join");
			Pages.Conversation().isConfirmationReceived("yes");
			test.log(LogStatus.INFO, "click on confirmation revceid");
			Pages.Conversation().save();
			test.log(LogStatus.INFO, "Select Save");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds...");
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matche window");
			Thread.sleep(3000);
			javascripthelper.clickElementByJS(Pages.RequirmentLifeCycle().offerReceivedView(candidateName));
			test.log(LogStatus.INFO, "Click on Edit view");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds");
			switchWindow("OfferReceived");
			test.log(LogStatus.INFO, "switch to offer received");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds.");
			javascripthelper.clickElementByJS(Pages.OfferReceived().selectOfferStatus("Offer Received"));
			test.log(LogStatus.INFO, "select offer status");
			Select(Pages.OfferReceived().selectPayRateType(), "Monthly");
			test.log(LogStatus.INFO, "select payrate type");
			Pages.OfferReceived().startDate("03/14/2018");
			test.log(LogStatus.INFO, "Entered start date");
			javascripthelper.clickElementByJS(Pages.OfferReceived().save());
			test.log(LogStatus.INFO, "Select save");
			Thread.sleep(3000);
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matche window");
			javascripthelper.clickElementByJS(Pages.RequirmentLifeCycle().interviewDetails(candidateName));
			test.log(LogStatus.INFO, "Click on interview Details");
			switchWindow("Submission Details");
			test.log(LogStatus.INFO, "Swithch to submission window");
			Select(Pages.SubmissionDetailsPage().selectRCTLAM(), "Hetal");
			test.log(LogStatus.INFO, "Select TL");
			javascripthelper.clickElementByJS(Pages.SubmissionDetailsPage().selectAccept());
			test.log(LogStatus.INFO, "Click on Accept");
			Select(Pages.SubmissionDetailsPage().selectQualityOfResume(), "Excellent Resume, Good work done.");
			test.log(LogStatus.INFO, "Select quality of resume");
			javascripthelper.clickElementByJS(Pages.SubmissionDetailsPage().save());
			test.log(LogStatus.INFO, "Select Save");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds.");
			switchWindow("Candidate Matches");
			Select(Pages.RequirmentLifeCycle().interviewAction(candidateName), "Offer Accepted");
			test.log(LogStatus.INFO, "Click on Action");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds.");
			switchWindow("OfferAccepted");
			test.log(LogStatus.INFO, "Switch to OfferAccepted window");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds.");
			javascripthelper.clickElementByJS(Pages.OfferAccepted().selectOfferStatus("Hired"));
			test.log(LogStatus.INFO, "Selected Hired.");
			javascripthelper.clickElementByJS(Pages.OfferAccepted().selectContractType("Employment-Agreement"));
			test.log(LogStatus.INFO, "Selected Employment-Agreement");
			sendKeys(Pages.OfferAccepted().comments(), "testing");
			test.log(LogStatus.INFO, "Entered comments");
			javascripthelper.clickElementByJS(Pages.OfferAccepted().save());
			test.log(LogStatus.INFO, "Click on Save button");
			javascripthelper.clickElementByJS(Pages.OfferAccepted().back());
			test.log(LogStatus.INFO, "Click on Back button");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Waiting for 3seconds.");
			switchWindow("Candidate Matches");
			test.log(LogStatus.INFO, "Switch to Candidate Matches window");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
