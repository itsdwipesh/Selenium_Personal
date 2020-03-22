package com.d11.project.msd.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import com.d11.project.msd.commonUtils.ExtentManagerUtil;
import com.d11.project.msd.commonUtils.TestUtils;

/** 
 * @author Dwipesh Mishra
 * Class for defining Listners
 */
public class TestListners implements ITestListener, IAnnotationTransformer, ISuiteListener{
	
	private static String TestcaseName;
	private static String TestClassName;

	public static String getTestClassName() {
		return TestClassName;
	}

	public static void setTestClassName(String testClassName) {
		TestClassName = testClassName;
	}

	public static String getTestcaseName() {
		return TestcaseName;
	}

	public static void setTestcaseName(String testcaseName) {
		TestcaseName = testcaseName;
	}

	public void onTestStart(ITestResult result) {
		String testcaseName =result.getMethod().getMethodName();
		setTestcaseName(testcaseName);
		setTestClassName(result.getMethod().getRealClass().getSimpleName());
		ExtentManagerUtil.setExtentTest(ExtentManagerUtil.report.startTest(TestClassName+" - "+TestcaseName));
		ExtentManagerUtil.logPass(TestClassName+" - "+TestcaseName+ " is started successfully");
		
	}

	public void onTestSuccess(ITestResult result) {
		ExtentManagerUtil.logPass(result.getMethod().getMethodName()+ "--PASSED!");
		ExtentManagerUtil.report.endTest(ExtentManagerUtil.getExtTest());
		
	}

	public void onTestFailure(ITestResult result) {
		ExtentManagerUtil.logFail(result.getMethod().getMethodName()+ "--Failed! Error => " + result.getThrowable().toString());
		ExtentManagerUtil.logFail("Failed",TestUtils.pullScreenshotPath());
		ExtentManagerUtil.report.endTest(ExtentManagerUtil.getExtTest());
		
	}

	public void onTestSkipped(ITestResult result) {
		ExtentManagerUtil.logSkip(result.getMethod().getMethodName()+ "--SKIPPED!");
		ExtentManagerUtil.report.endTest(ExtentManagerUtil.getExtTest());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ExtentManagerUtil.report.endTest(ExtentManagerUtil.getExtTest());
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		ExtentManagerUtil.report.endTest(ExtentManagerUtil.getExtTest());
		
	}

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}


}
