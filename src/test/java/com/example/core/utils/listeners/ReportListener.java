package com.example.core.utils.listeners;

import com.aventstack.extentreports.Status;
import com.example.core.utils.extentreports.ExtentManager;
import com.example.core.utils.extentreports.ExtentTestManager;
import com.example.core.utils.logs.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ReportListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
	}
    
    // @Override
    // public void onStart(ITestContext iTestContext) {
    //     WebDriver driver = BaseTest.getDriver();
    //     Log.info("Start testing " + iTestContext.getName());
    //     iTestContext.setAttribute("WebDriver", driver);
    // }

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {
        Log.error(getTestName(result) + " test is failed.");
        ExtentTestManager.addScreenShot(Status.FAIL, getTestName(result));
        ExtentTestManager.logMessage(Status.FAIL, getTestDescription(result));
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

    // @Override
    // public void onStart(ITestContext iTestContext) {
    //     WebDriver driver = BaseTest.getDriver();
    //     Log.info("Start testing " + iTestContext.getName());
    //     iTestContext.setAttribute("WebDriver", driver);
    // }

    // @Override
    // public void onFinish(ITestContext iTestContext) {
    //     Log.info("End testing " + iTestContext.getName());
    //     //Kết thúc và thực thi Extents Report
    //     ExtentManager.getInstance().flush();
    // }

    // @Override
    // public void onTestStart(ITestResult iTestResult) {
    //     Log.info(getTestName(iTestResult) + " test is starting...");
    //     ExtentTestManager.startTest(iTestResult.getMethod().getMethodName());
    // }

    // @Override
    // public void onTestSuccess(ITestResult iTestResult) {
    //     Log.info(getTestName(iTestResult) + " test is passed.");
    //     //ExtentReports log operation for passed tests.
    //     ExtentTestManager.logMessage(Status.PASS, getTestDescription(iTestResult));
    // }

    // @Override
    // public void onTestFailure(ITestResult iTestResult) {
    //     Log.error(getTestName(iTestResult) + " test is failed.");

    //     ExtentTestManager.addScreenShot(Status.FAIL, getTestName(iTestResult));
    //     ExtentTestManager.logMessage(Status.FAIL, getTestDescription(iTestResult));
    // }

    // @Override
    // public void onTestSkipped(ITestResult iTestResult) {
    //     Log.warn(getTestName(iTestResult) + " test is skipped.");
    //     ExtentTestManager.logMessage(Status.SKIP, getTestName(iTestResult) + " test is skipped.");
    // }

    // @Override
    // public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    //     Log.error("Test failed but it is in defined success ratio " + getTestName(iTestResult));
    //     ExtentTestManager.logMessage(Status.INFO, "Test failed but it is in defined success ratio " + getTestName(iTestResult));
    // }
}
