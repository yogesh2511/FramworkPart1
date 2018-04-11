package com.test.automation.UIAutomation.uiActions.RequirementsLifeCycle;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.test.automation.UIAutomation.testBase.TestBase;

public class OfferAccepted extends TestBase {
	public static Logger log = Logger.getLogger(OfferAccepted.class.getName());

	public static WebElement selectOfferStatus(String status) {
		WebElement element = null;
		if (status.equalsIgnoreCase("In progress")) {
			element = waitElement(
					"//span[contains(text(),'"+status+"')]/preceding::input[@name='HiringStatusID' and @value='1'']");
		} else if (status.equalsIgnoreCase("Hired")) {
			element = waitElement("//span[contains(text(),'"+status+"')]/preceding::input[@name='HiringStatusID' and @value='2']");
		} else if (status.equalsIgnoreCase("Not hired")) {
			element = waitElement("//span[contains(text(),'"+status+"')]/preceding::input[@name='HiringStatusID' and @value='3']");
		}
		else {
			log.info("not valid status" + status);
		}
		return element;
	}

	public static WebElement selectContractType(String status) {
		WebElement element = null;
		if (status.equalsIgnoreCase("Contract-To-Hire")) {
			element = waitElement(
					"//span[contains(text(),'"+status+"')]/preceding::input[@id='ContractTypeID'and @value='2']");
		} else if (status.equalsIgnoreCase("Corp-to-Corp")) 
		{
			element = waitElement("//span[contains(text(),'"+status+"')]/preceding::input[@id='ContractTypeID'and @value='2']");
		} else if (status.equalsIgnoreCase("Employment-Agreement")) {
			element = waitElement("//span[contains(text(),'"+status+"')]/preceding::input[@id='ContractTypeID' and @value='1']");
		}
		else if (status.equalsIgnoreCase("Other")) {
			element = waitElement("//span[contains(text(),'"+status+"')]/preceding::input[@id='ContractTypeID' and @value='5']");
		}
		else if (status.equalsIgnoreCase("W2-ExhibitA")) {
			element = waitElement("//span[contains(text(),'"+status+"')]/preceding::input[@id='ContractTypeID' and @value='4']");
		}
		else if (status.equalsIgnoreCase("W2/Corp-To-Corp")) {
			element = waitElement("//span[contains(text(),'"+status+"')]/preceding::input[@id='ContractTypeID' and @value='3']");
		}
		else {
			log.info("not valid status:" + status);
		}
		return element;
	}
	
	public static WebElement OfferedCandidatePayRate() {
		return waitElement("//input[@id='PayRateToCandidate']");
	}
	public static WebElement timely() {
		return waitElement("//select[@id='drpPayRateType']");
	}
	public static WebElement OfferedBillRate() {
		return waitElement("//input[@id='BillRateFromClient']");
	}

	public static WebElement PerDiem() {
		return waitElement("//input[@id='OfferAcceptedPerDiems']");
	}

	public static void startDate(String date) {
		click(waitElement("//input[@id='OfferAcceptedStartDate']"));
		calender(date);
	}

	public static void endDate(String date) {
		click(waitElement("//input[@id='OfferAcceptedEndDate']"));
		calender(date);
	}

	public static WebElement comments() {
		return waitElement("//textarea [@id='OfferAcceptedComment']");
	}

	public static WebElement BrowseFile()
	{
		return waitElement("//input[@id='Doccheckup']");
	}
	public static WebElement save() {
		return waitElement("//input[@id='btnOfferAccepted']");
	}

	public static WebElement back() {
		return waitElement("//a[contains(text(),'Back')]");
	}

	public static void calender(String Day) {

		String year = null;
		int month = 0;
		String expectedDay = null;
		String array1[] = Day.split("/");
		for (int i = 0; i < array1.length; i++) {
			month = Integer.parseInt(array1[0]);
			expectedDay = array1[1];
			year = array1[2];
		}

		year(year);

		month(month);

		days(expectedDay);
	}

	public static void year(String year) {
		WebElement years = waitElement(".//*[@id='ui-datepicker-div']/div/div/select[@class='ui-datepicker-year']");
		while (true) {
			Select(years, year);
			break;
		}
	}

	public static void month(int month) {
		WebElement months = waitElement("//div[@id='ui-datepicker-div']/div/div/select[@class='ui-datepicker-month']");
		String getMonth = setMonthName(month);
		while (true) {
			Select(months, getMonth);
			break;
		}
	}

	private static String setMonthName(int month) {
		String monthString;
		switch (month) {
		case 1:
			monthString = "January";
			break;
		case 2:
			monthString = "February";
			break;
		case 3:
			monthString = "March";
			break;
		case 4:
			monthString = "April";
			break;
		case 5:
			monthString = "May";
			break;
		case 6:
			monthString = "June";
			break;
		case 7:
			monthString = "July";
			break;
		case 8:
			monthString = "August";
			break;
		case 9:
			monthString = "September";
			break;
		case 10:
			monthString = "October";
			break;
		case 11:
			monthString = "November";
			break;
		case 12:
			monthString = "December";
			break;
		default:
			monthString = "Invalid month";
			break;
		}
		return monthString;
	}

	public static void days(String expectedDay) {
		List<WebElement> days = waitElements("//div[@id='ui-datepicker-div']/table/tbody/tr/td/a");
		for (WebElement day : days) {
			if (day.getText().equalsIgnoreCase(expectedDay)) {
				day.click();
				break;
			}
		}

	}
}
