package com.d11.project.msd.config;

import java.util.Map;

/**
 * @author Dwipesh Mishra
 * Class for Getting & Setting Constants
 */
public class TestConfig {
	
	private static String environment = null;
	private static String platformName = null;
	private static String browserName = null;
	
	public static void setEnvironment(String testEnvironment) {
		environment=testEnvironment;
	}
	
	public static void setPlatformName(String testPlatformName) {
		platformName=testPlatformName;
	}
	
	public static void setBrowserName(String testBrowserName) {
		browserName=testBrowserName;
	}
	
	public static String getEnvironment() {
		return environment;
	}
	
	public static String getPlatformName() {
		return platformName;
	}
	
	public static String getBrowserName() {
		return browserName;
	}
	
}
