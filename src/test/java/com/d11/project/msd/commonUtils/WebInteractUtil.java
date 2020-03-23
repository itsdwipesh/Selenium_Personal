package com.d11.project.msd.commonUtils;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.d11.project.msd.config.Config;

public class WebInteractUtil {
	
	/**
	 * Loads the Web Page in the current browser window 
	 * @param url {@link String} - URL to be launched
	 */
	public static void launchWebApp(String url) {
		
		try {
			DriverManagerUtil.getWebdriver().get(url);
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to Launch the URL => "+url);
		}
		
		LoggerUtil.logMessage("URL Launched => " + url);
	}
	
	/**
	 * Waits for Element and Clicks on it
	 * @param webelement {@link WebElement}
	 * @return status
	 */
	
	public static boolean click(WebElement webelement) {
		boolean status = false;
		try {
			scrollIntoView(webelement);
			webelement.click();
			status = true;
		}catch(StaleElementReferenceException e1) {
			for(int i=0;i<=10;i++) {
				try {
					waitForElementToBeVisible(webelement, Config.MEDIUM_PAUSE);
					webelement.click();
					status = true;
					break;
				}catch(Exception e) {
					continue;
				}
			}
		}catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to Click Element => "+ webElementName(webelement));
		}
		
		return status;	
	}
	
	/**
	 * Waits for Element and set text in it
	 * @param webelement {@link WebElement}
	 * @return status
	 */
	
	public static boolean sendKeys(WebElement webelement, String text) {
		boolean status = false;
		try {
			scrollIntoView(webelement);
			webelement.clear();
			webelement.sendKeys(text);
			status = true;
		}catch(StaleElementReferenceException e1) {
			for(int i=0;i<=10;i++) {
				try {
					waitForElementToBeVisible(webelement, Config.MEDIUM_PAUSE);
					webelement.clear();
					webelement.sendKeys(text);
					status = true;
					break;
				}catch(Exception e) {
					continue;
				}
			}
		}catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to set text in Element => "+ webElementName(webelement));
		}
		
		return status;	
	}
	
	/**
	 * Verifies Element is Present
	 * @param webelement {@link WebElement}
	 * @param timeOut {@link int}
	 * @return status
	 */
	public static boolean isPresent(WebElement webelement,int timeOut) {
		boolean status = false;
		waitForElementToBeVisible(webelement, timeOut);
		try {
			status=webelement.isDisplayed();
		}catch (Exception e) {
			LoggerUtil.logErrorMessage("Element is not present => "+ webElementName(webelement));
		}
		
		LoggerUtil.logMessage("Element is present => " + webElementName(webelement));
		return status;
	}
	
	/**
	 * Verifies Element is Enabled
	 * @param webelement {@link WebElement}
	 * @param timeOut {@link int}
	 * @return status
	 */
	public static boolean isEnabled(WebElement webelement,int timeOut) {
		boolean status = false;
		waitForElementToBeVisible(webelement, timeOut);
		try {
			status=webelement.isEnabled();
		}catch (Exception e) {
			LoggerUtil.logErrorMessage("Element is disabled => "+ webElementName(webelement));
		}
		
		LoggerUtil.logMessage("Element is enabled => " + webElementName(webelement));
		return status;
	}
	/**
	 * Scroll to element
	 * @param webelement {@link WebElement}
	 * @return
	 */
	
	public static boolean scrollIntoView(WebElement webelement) {
		boolean status = false;
		waitForElementToBeVisible(webelement, Config.MEDIUM_PAUSE);
		try {
			DriverManagerUtil.getWebdriver().executeScript("arguments[0].scrollIntoView({inline: \"center\"});", webelement);
			status = true;
		}catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to scroll to element => "+ webElementName(webelement));
		}
		return status;
	}
	
	/**
	 * Waits for web element visibility
	 * @param webelement {@link WebElement}
	 * @param timeOut {@link int}
	 * @return status
	 */
	
	public static boolean waitForElementToBeVisible(WebElement webelement,int timeOut) {
		
		boolean status =  false;
		
		try {
			WebDriverWait wait=new WebDriverWait(DriverManagerUtil.getWebdriver(), timeOut, Config.POLLING_TIME);
			wait.until(ExpectedConditions.visibilityOf(webelement));
			status = true;
		}catch (Exception e) {
			LoggerUtil.logErrorMessage("Element not Visible => "+ webElementName(webelement));
		}
		
		LoggerUtil.logMessage("Element is Visible => "+ webElementName(webelement));
		return status;
	}
	
	/**
	 * Waits for web element invisibility
	 * @param webelement {@link WebElement}
	 * @param timeOut {@link int}
	 * @return status
	 */
	
	public static boolean waitForInvisibilityOfElement(WebElement webelement,int timeOut) {
		
		boolean status =  false;
		
		try {
			WebDriverWait wait=new WebDriverWait(DriverManagerUtil.getWebdriver(), timeOut, Config.POLLING_TIME);
			wait.until(ExpectedConditions.invisibilityOf(webelement));
			status = true;
		}catch (Exception e) {
			LoggerUtil.logErrorMessage("Element is Visible => "+ webElementName(webelement));
		}
		
		LoggerUtil.logMessage("Element not Visible => "+ webElementName(webelement));
		return status;
	}

	
	private static String webElementName(WebElement webelement) {
		try {
			return webelement.toString().split("->")[1].trim();
		} catch (Exception e) {
			return "";
		}
	}
}
