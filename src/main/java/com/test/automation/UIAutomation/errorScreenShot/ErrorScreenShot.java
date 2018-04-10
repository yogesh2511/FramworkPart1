package com.test.automation.UIAutomation.errorScreenShot;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import com.test.automation.UIAutomation.testBase.TestBase;
import com.test.automation.UIAutomation.utility.DateTimeHelper;

public class ErrorScreenShot extends TestBase
{
	public static String getScreenShot(String methodname, String imageExtension,String folderName)
	{
		String dest = null;
		File destFile = null ;
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/resources/ScreenShots/"+folderName;
			System.out.println("image path:"+(String) reportDirectory +"_"+methodname + "_" + DateTimeHelper.getCurrentDateTime()+ imageExtension);
			log.info((String) reportDirectory +"_"+methodname + "_" +DateTimeHelper.getCurrentDateTime() + imageExtension);
			 dest = (String) reportDirectory +"_"+methodname + "_" + DateTimeHelper.getCurrentDateTime() + imageExtension;
			log.info("Dest="+dest);
			 destFile = new File((String) reportDirectory +"_"+methodname + "_" + DateTimeHelper.getCurrentDateTime() + imageExtension);
			System.out.println("reportDirectory:"+reportDirectory);
			FileUtils.copyFile(scrFile, destFile);
			log.info("image path : "+destFile.getAbsolutePath());
			log.info("Take screenshot completed");
			return dest;
		} 
		catch (Exception e) {
			log.error(e.getMessage());
			System.out.println("ErrorScreenShot(class)->getScreenShot(method) error:"+e.getMessage());
		}
		
		return dest;
		
		
	}

}
