package com.d11.project.msd.commonUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.d11.project.msd.config.Config;
import com.d11.project.msd.listeners.TestListners;

public class TestUtils {
	
	/**
	 * Captures screenshot and returns the screenshot path
	 */
	public static String pullScreenshotPath()  {

		String destination = null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpleformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String timeStamp = simpleformat.format(cal.getTime()).replaceAll("/|:| ", "_");
		
		if(Config.SCREENSHOTS_REQUIRED==true) {
			File scrFile = DriverManagerUtil.getWebdriver().getScreenshotAs(OutputType.FILE);
			try {
				if(Config.SCREENSHOTS_PATH.equals("")) {

					destination = System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots\\" + TestListners.getTestcaseName() + "\\" + timeStamp + ".png";
					FileUtils.copyFile(scrFile, new File(destination));
				} else {
					destination = Config.SCREENSHOTS_PATH + "\\" +TestListners.getTestcaseName().replaceAll(" ", "") + timeStamp + ".png";
					FileUtils.copyFile(scrFile, new File(destination));
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	
		return destination;

	}

	/**
	 * Gives a base64 image which is used to append the screenshots in the extent report.
	 * Converting to base64 format avoids screenshots broken image if sent the extent report through email.
	 */
	public static String getBase64Image(String screenshotpath) {
		String base64 = null;
		try {
			InputStream is= new FileInputStream(screenshotpath);
			byte[] imageBytes = IOUtils.toByteArray(is);
			base64 = Base64.getEncoder().encodeToString(imageBytes);
		}
		catch (Exception e) {
			
		}
		return base64;

	}

}
