package testcomponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.Report;

public class Listeners implements ITestListener{

	ExtentTest test;
	ExtentReports extent = Report.getReport();
	@Override
	public void onTestStart(ITestResult result) {
		
    System.out.println("Test started");
    test =extent.createTest(result.getMethod().getMethodName());
    
	}

	@Override
	public void onTestSuccess(ITestResult result) {

    test.log(Status.PASS, "Teestcase is passed");
    System.out.println(result.getName()+"Testcase is success");

	}

	@Override
	public void onTestFailure(ITestResult result) {

	test.fail(result.getThrowable());
    System.out.println(result.getName()+"Testcase failed");
    baseTest test = (baseTest) result.getInstance();
    
    try {
        test.screenshot(result.getName());
    } catch (IOException e) {
        e.printStackTrace();
    }
	}
	
	public void onFinish(ITestContext context) {
		    extent.flush();
		  }	
}
