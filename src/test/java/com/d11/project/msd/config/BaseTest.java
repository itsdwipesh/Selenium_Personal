package com.d11.project.msd.config;

import java.lang.reflect.Method;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import com.d11.project.msd.commonUtils.DriverManagerUtil;
import com.d11.project.msd.commonUtils.ExtentManagerUtil;
import com.d11.project.msd.listeners.TestListners;

/** 
 * @author Dwipesh Mishra
 * BaseTest Class For SetUp
 */

public class BaseTest {

	@BeforeSuite
	public void suiteSetUp(ITestContext context) {
		ExtentManagerUtil.initialize();
	}
	
	@BeforeTest
	public void testSetUp(ITestContext context) {
		TestConfig.setEnvironment(context.getCurrentXmlTest().getParameter("environment"));
		TestConfig.setPlatformName(context.getCurrentXmlTest().getParameter("platformName"));
		TestConfig.setBrowserName(context.getCurrentXmlTest().getParameter("browserName"));
		DriverManagerUtil.initializeGrid();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void methodSetUp() {
		DriverManagerUtil.initiateWebDriver();
	}
	
	@DataProvider
	public Object[][] getTestData(Method method){
		return null;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void methodTearDown(){
		DriverManagerUtil.stopWebDriver();
	}
	
	@AfterSuite
	public void suiteTearDown() {
		ExtentManagerUtil.report.flush();
	}
	
	
	
	
	
}
