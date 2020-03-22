package com.d11.project.msd.commonUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.d11.project.msd.config.Config;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentManagerUtil {
	
	public static ExtentReports report = null;
	public static String extentreportpath = "";
	public static ThreadLocal<ExtentTest> exTest = new ThreadLocal<ExtentTest>();

	//To avoid external initialization
	private ExtentManagerUtil() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy_ hh_mm_ss");
		Date date = new Date();
		String currentDate = formatter.format(date);
		
		if(Config.OVERRIDE_RESULTS==true) 
		{
			if(Config.RESULT_FILE_PATH.equals("")||Config.RESULT_FILE_PATH==null) 
			{
				extentreportpath=System.getProperty("user.dir") + "\\src\\test\\resources\\Result\\Test Report.html";
				
			}
			else {
				extentreportpath = Config.RESULT_FILE_PATH + "\\Test Report.html";
			}
		}
		else 
		{
			if(Config.RESULT_FILE_PATH.equals("")||Config.RESULT_FILE_PATH==null) 
			{
				extentreportpath=System.getProperty("user.dir") + "\\src\\test\\resources\\Result\\Test Report_"+currentDate+".html";
			}
			
			else
			{
				extentreportpath=Config.RESULT_FILE_PATH + "\\Test Report_"+currentDate+".html";
			}

		}
		report=new ExtentReports(extentreportpath);
		report.loadConfig(new File(Config.EXTENTCONFIGPATH));
	}

	public static void initialize()
	{
		ExtentManagerUtil report=new ExtentManagerUtil();
	}
	
	public static ExtentTest getExtTest() {
		return exTest.get();
	}

	public static void setExtentTest(ExtentTest test) {
		exTest.set(test);
	}
	
	public static void logPass(String message)
	{
		getExtTest().log(com.relevantcodes.extentreports.LogStatus.PASS, message);
		
	}

	public static void logFail(String message)
	{
		getExtTest().log(com.relevantcodes.extentreports.LogStatus.FAIL, message);
	}

	public static void logFail(Exception message)
	{
		getExtTest().log(com.relevantcodes.extentreports.LogStatus.FAIL, message);
	}

	public static void logFail(AssertionError a)
	{
		getExtTest().log(com.relevantcodes.extentreports.LogStatus.FAIL, a);
	}

	public static void logInfo(String message)
	{
		getExtTest().log(com.relevantcodes.extentreports.LogStatus.INFO, message);
	}

	public static void logError(String message)
	{
		getExtTest().log(com.relevantcodes.extentreports.LogStatus.ERROR, message);
	}

	public static void logFatal(String message)
	{
		getExtTest().log(com.relevantcodes.extentreports.LogStatus.FATAL, message);
	}

	public static void logSkip(String message)
	{
		getExtTest().log(com.relevantcodes.extentreports.LogStatus.SKIP, message);
	}

	public static void logUnknown(String message)
	{
		getExtTest().log(com.relevantcodes.extentreports.LogStatus.UNKNOWN, message);
	}

	public static void logWarning(String message)
	{
		getExtTest().log(com.relevantcodes.extentreports.LogStatus.WARNING, message);
	}
	public static void logPass(String string, String addScreenCapture) {

		if(Config.PASSED_STEPS_SCREENSHOTS==true) {
			getExtTest().log(com.relevantcodes.extentreports.LogStatus.PASS, string,getExtTest().addBase64ScreenShot("data:image/png;base64,"+TestUtils.getBase64Image(addScreenCapture)));
		}
	}

	public static void logFail(String string, String addScreenCapture)
	{

		if(Config.FAILED_STEPS_SCREENSHOTS==true) {
			getExtTest().log(com.relevantcodes.extentreports.LogStatus.FAIL, string,getExtTest().addBase64ScreenShot("data:image/png;base64,"+TestUtils.getBase64Image(addScreenCapture)));
		}

	}

}
