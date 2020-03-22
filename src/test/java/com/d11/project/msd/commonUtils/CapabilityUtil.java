package com.d11.project.msd.commonUtils;

import java.io.IOException;
import java.util.HashMap;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.d11.project.msd.config.Config;
import com.d11.project.msd.config.TestConfig;

public class CapabilityUtil {

	public static DesiredCapabilities getCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		switch(TestConfig.getEnvironment().toUpperCase()) {
		
			case "GRID":
			{
				if(TestConfig.getBrowserName().equalsIgnoreCase("chrome"))
				{	
					capabilities.setCapability("platform",Platform.ANY);
					capabilities.setCapability("browserName","chrome");
				}
				break;
			}
			
			case "LOCAL":
			default:
			{
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);

			if (TestConfig.getBrowserName().equalsIgnoreCase("chrome")) {
				capabilities.setCapability("platform", Platform.ANY);
				capabilities.setCapability("browserName", "chrome");

				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", Config.DEFAULT_DOWNLOAD_PATH);

				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", chromePrefs);
				options.setExperimentalOption("useAutomationExtension", false);
				options.addArguments("--start-maximized");
				options.addArguments("--test-type");
				options.addArguments("--allow-file-access-from-files");
				//options.addArguments("--expose-internals-for-testing");
				options.addArguments("--disable-popup-blocking");
				options.addArguments("--disable-extensions");
				options.addArguments("--enable-automation");

				capabilities.merge(options);
				
				}
			}
		}
		return capabilities;
		
	}
}
