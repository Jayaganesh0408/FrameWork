package SelfLearning.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import SelfLearning.resources.ExtentReporter;


public class Listeners extends TestBase implements ITestListener {
	ExtentReports extent = ExtentReporter.getReporterObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
	    test= extent.createTest(result.getMethod().getMethodName());
	    extentTest.set(test);
	  }
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test passed"); // logging test success with message
	  }

	@Override
	public void onTestFailure(ITestResult result) {
	    extentTest.get().fail(result.getThrowable());   // explicitly making test to fail and throw error whatever we got from Test
	    
	    
	    //driver from Test
	    
	    try {
		 driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    //Code for taking screenshot
	    String filePath = null;
		try {
			 filePath = takeScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	    
	  }

	@Override
	public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }
	@Override
	public void onFinish(ITestContext context) {
	    extent.flush();
	  }

}
