package com.example.core.utils.listeners;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.example.core.utils.extentreports.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.example.core.utils.extentreports.ExtentManager.getInstance;;

public class ReportListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    private synchronized String getSimpleClassName(ITestResult result) {
        return result.getMethod().getRealClass().getSimpleName();
    }

    private synchronized void addExtentLabelToTest(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS)
            ExtentTestManager.getTest().pass(MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
        else if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().fail(MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
        } else
            ExtentTestManager.getTest().skip(MarkupHelper.createLabel("Test Skipped", ExtentColor.ORANGE));
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("---------Start testing " + iTestContext.getName() + " ---------");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("---------End testing " + iTestContext.getName() + " ---------");
        getInstance().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("--------- Executing :- " + getTestName(iTestResult) + " ---------");
        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName()); //"Extent Report By TuND20"
        ExtentTestManager.setCategoryName(getSimpleClassName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(getTestName(iTestResult) + " test is passed.");
        ExtentTestManager.getTest().assignCategory(getSimpleClassName(iTestResult));
        addExtentLabelToTest(iTestResult);
        ExtentTestManager.endTest();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println(getTestName(iTestResult) + " test is failed.");
        ExtentTestManager.getTest().assignCategory(getSimpleClassName(iTestResult));
        ExtentTestManager.addScreenShot(Status.FAIL, getTestName(iTestResult));
        ExtentTestManager.getTest().log(Status.FAIL, iTestResult.getName() + " Test is failed" +iTestResult.getThrowable());
        addExtentLabelToTest(iTestResult);
        ExtentTestManager.endTest();

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        ExtentTestManager.getTest().log(Status.SKIP, iTestResult.getName() + " Test is Skipped" +  iTestResult.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        ExtentTestManager.logMessage(Status.INFO, "Test failed but it is in defined success ratio " + iTestResult.getMethod().getMethodName());
    }
}
