package CompleteSeleniumFramework.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
//Itestlistener is an interface provided by TestNg
// when a class implements this interface there are a lot of advatages 
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import CompleteSeleniumFramework.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	// Right click ITestListener and click source-> implement/override methods/
	// check all related methods

	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;

	// in case tests are run parallel the test is overriden by another test running
	// in parallel hence
	// creating flaws in the extent Reports created
	// to avoid it we have ThreadLocal that generates unique Thread id for the test

	ThreadLocal<ExtentTest> extentTest = new ThreadLocal();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		// create entry for test report
		// here we will be giving testcase name
		// the result holds the information of the testcase
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);// this generates a map // unique thread id(ErrorValidationTest)->test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		// test.log(Status.PASS, "Test Passed");
		// instead of test you will say
		extentTest.get().log(Status.PASS, "Test Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		// test.log(Status.FAIL, "Test Failed");
		extentTest.get().log(Status.FAIL, "Test Failed");
		// It will show the error message
		// test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());

		/*
		 * try { driver = (WebDriver)
		 * result.getTestClass().getRealClass().getField("driver").get(result.
		 * getInstance()); } catch (Exception e1) { e1.printStackTrace(); }
		 * 
		 * String[] screenshotData = null;
		 */
		/*
		 * try { // even here the driver has no life // result has all the information
		 * and it knows about its driver screenshotData =
		 * getScreenshot(result.getMethod().getMethodName(), driver);
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * // Attach the Screenshot to the report
		 * test.addScreenCaptureFromPath(screenshotData[0],
		 * result.getMethod().getMethodName() + " File Screenshot");
		 * test.addScreenCaptureFromBase64String(screenshotData[1],
		 * result.getMethod().getMethodName() + " Base64 Screenshot");
		 */

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// screenshot
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		extentTest.get().log(Status.SKIP, "Test Skipped");


	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		extent.flush();
	}

}
