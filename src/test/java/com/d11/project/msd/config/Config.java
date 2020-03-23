package com.d11.project.msd.config;

/**
 * @author Dwipesh MIshra
 * Class for Declaring Constants
 */

public class Config {
	
	//Application URL
	public static final String AUT_URL = "https://www.seleniumeasy.com/appium-tutorials/connecting-appium-to-selenium-grid-example";
	
	//Screenshot capture
	public static final boolean SCREENSHOTS_REQUIRED = true;
	
	//Grid Config
	public static final String GRID_HUB_IP = "localhost";
	public static final String GRID_HUB_PORT="4445";
	public static final String GRID_URL="http://" + GRID_HUB_IP + ":" + GRID_HUB_PORT;
	public static final String HUB_BAT_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\SeleniumServer\\SeleniumNodeServer.bat";
	public static final String NODE_BAT_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\SeleniumServer\\SeleniumNodeServer.bat";
	public static final String[] LOCAL_NODE_JSONCONFIG = {"ChromeNodeConfig"}; 
	
	
	//Pausing time in Seconds
	public static final int XSMALL_PAUSE = 3;
	public static final int SMALL_PAUSE = 10;
	public static final int MEDIUM_PAUSE = 30;
	public static final int LARGE_PAUSE = 60;
	public static final int XLARGE_PAUSE = 60;
	public static final int POLLING_TIME = 500;
	
	//File Paths
	public static final String TEST_DATA_FILE_PATH = "";
	public static final String RESULT_FILE_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\Result";
	public static final String DEFAULT_DOWNLOAD_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\Downloads";
	public static final String SCREENSHOTS_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots";
	
	//ExtentReportConfig
	public static final String EXTENTCONFIGPATH = System.getProperty("user.dir")+"\\src\\test\\resources\\Config\\extentreport.xml";
	public static final boolean OVERRIDE_RESULTS = true;
	public static final boolean PASSED_STEPS_SCREENSHOTS = true;
	public static final boolean FAILED_STEPS_SCREENSHOTS = true;
	
}
