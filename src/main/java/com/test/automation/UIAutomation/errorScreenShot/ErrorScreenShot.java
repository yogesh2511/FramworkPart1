package com.test.automation.UIAutomation.errorScreenShot;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import com.test.automation.UIAutomation.testBase.TestBase;
import com.test.automation.UIAutomation.utility.DateTimeHelper;
import com.test.automation.UIAutomation.utility.Logger;

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
			Logger.info((String) reportDirectory +"_"+methodname + "_" +DateTimeHelper.getCurrentDateTime() + imageExtension);
			 dest = (String) reportDirectory +"_"+methodname + "_" + DateTimeHelper.getCurrentDateTime() + imageExtension;
			Logger.info("Dest="+dest);
			 destFile = new File((String) reportDirectory +"_"+methodname + "_" + DateTimeHelper.getCurrentDateTime() + imageExtension);
			System.out.println("reportDirectory:"+reportDirectory);
			FileUtils.copyFile(scrFile, destFile);
			Logger.info("image path : "+destFile.getAbsolutePath());
			Logger.info("Take screenshot completed");
			return dest;
		} 
		catch (Exception e) {
			Logger.error(e.getMessage());
			System.out.println("ErrorScreenShot(class)->getScreenShot(method) error:"+e.getMessage());
		}
		
		return dest;
		
		
	}

}
