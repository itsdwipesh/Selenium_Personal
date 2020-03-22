package com.d11.project.msd.commonUtils;

import java.net.URL;
import org.openqa.selenium.chrome.ChromeDriver;
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
	 * @param capabilities {@link DesiredCapabilities} - The desired capabilities
	 */
	public static void initiateWebDriver() {
		
		if (getWebdriver() == null) {

			try {
				String url = Config.GRID_URL + "/wd/hub";
				switch (TestConfig.getEnvironment().toUpperCase()) {
				case "LOCAL": {
					if (TestConfig.getBrowserName().equalsIgnoreCase("chrome")) {
						System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\SeleniumServer\\chromedriver.exe");
						}
					}
				}
				if (TestConfig.getEnvironment().equalsIgnoreCase("LOCAL")) {
					if (TestConfig.getBrowserName().equalsIgnoreCase("chrome"))
						driver = new ChromeDriver(CapabilityUtil.getCapabilities());

				} else
					driver = new RemoteWebDriver(new URL(url), CapabilityUtil.getCapabilities());
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
	public static void startSeleniumGrid() {
		
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
	public static void startNode(String jsonConfigName) {
		
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
