package com.d11.project.msd.commonUtils;

import java.net.URL;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.d11.project.msd.config.Config;
import com.d11.project.msd.config.TestConfig;

public class DriverManagerUtil {
	
	private static RemoteWebDriver driver= null;
	private static ThreadLocal<RemoteWebDriver> webdriver = new ThreadLocal<>();
	
	/**
	 * Gets the RemoteWebDriver instance for the running session 
	 * @return driver {@link RemoteWebDriver} - The instance of WebDriver
	 */
	public static RemoteWebDriver getWebdriver() {
		return webdriver.get();
	} 
	
	/**
	 * Sets the RemoteWebDriver instance for the running session
	 * @param remoteWebdriver
	 */
	public static void setWebdriver(RemoteWebDriver remoteWebdriver) {
		webdriver.set(remoteWebdriver);
	}
	
	/**
	 * Initialize the Driver instance for the running session 
	 */
	public static void initiateWebDriver() {

		if (getWebdriver() == null) {

			try {
				String url = null;
				
				switch (TestConfig.getEnvironment().toUpperCase()) {
				
				case "GRID":{
					url = Config.GRID_URL + "/wd/hub";
					driver = new RemoteWebDriver(new URL(url), CapabilityUtil.getCapabilities());
					break;
					}
				
				case "LOCAL": 
				default:{

					if (TestConfig.getBrowserName().equalsIgnoreCase("chrome")) {
						System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\SeleniumServer\\chromedriver.exe");
						System.setProperty("webdriver.chrome.silentOutput","true");
						driver = new ChromeDriver(CapabilityUtil.getCapabilities());
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			setWebdriver(driver);
		}
	}
	
	/**
	 * Stops WebDriver instance of Running Session
	 */
	
	public static void stopWebDriver() {
		
		try {
			getWebdriver().close();
			getWebdriver().quit();
			DriverManagerUtil.setWebdriver(null);
		} catch (Exception e) {
			e.printStackTrace();
			// log Driver session not closed with error
		}
		
		//log closed Webdriver session
	}
	
	/**
	 * Start selenium grid server
	 */
	private static void startSeleniumGrid() {
		
		String hubBatCommand = "start " + System.getProperty("user.dir") + "\\src\\test\\resources\\SeleniumServer\\SeleniumHubServer.bat" + " " + Config.GRID_HUB_PORT;
		try {
			Runtime.getRuntime().exec("cmd /c " + hubBatCommand);
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Start Local Node Server
	 * @param - Name of JSON Config file under ConfigJson
	 */
	private static void startNode(String jsonConfigName) {
		
		String nodeBatCommand = "start " + Config.NODE_BAT_PATH + " " + Config.GRID_URL + "/grid/register " + System.getProperty("user.dir") + "\\src\\test\\resources\\ConfigJSON\\";
		try {
			Runtime.getRuntime().exec("cmd /c " + nodeBatCommand + jsonConfigName + ".json");
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Stops the Selenium grid Server
	 */
	
	public static void stopSeleniumGrid() {
		
	}
	
	/**
	 * Initialize selenium grid hub and node server
	 * Takes the Node server config json from Config.LOCAL_NODE_JSONCONFIG 
	 */

	public static void initializeGrid() {
		
		String url=null;
		if(!TestConfig.getEnvironment().toUpperCase().equalsIgnoreCase("LOCAL")) {
			startSeleniumGrid();
			
			for(String jsonConfigName:Config.LOCAL_NODE_JSONCONFIG)
				if(!jsonConfigName.equals(""))
					startNode(jsonConfigName);
		}
		
	}

	
}
