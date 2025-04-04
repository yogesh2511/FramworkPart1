package com.test.automation.UIAutomation.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;



import com.test.automation.UIAutomation.testBase.TestBase;

public class AddCandidateReferences extends TestBase {
	Properties or;
	File file;
	FileInputStream input;

	public static String contactName;
	public static String contactNumber;
	public static String email;
	
	public AddCandidateReferences() {
		try {
			or = new Properties();
			file = new File(System.getProperty("user.dir") + "//resources//properties//AddCandidateReferences.properties");
			input = new FileInputStream(file);
			or.load(input);
			AddCandidateReferences.contactName = or.getProperty("contactName");
			System.out.println("contactName:"+contactName);
			AddCandidateReferences.contactNumber = or.getProperty("contactNumber");
			System.out.println("contactNumber:"+contactNumber);
			AddCandidateReferences.email = or.getProperty("email");
			System.out.println("email:"+email);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
