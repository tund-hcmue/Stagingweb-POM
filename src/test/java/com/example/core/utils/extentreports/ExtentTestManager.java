package com.example.core.utils.extentreports;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.example.tests.BaseTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	static ExtentReports extent = ExtentManager.getInstance();

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		extent.flush();
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}

    // public static void addScreenShot(String message) {
    //     String base64Image = "data:image/png;base64,"
    //             + ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BASE64);
    //     getTest().log(Status.INFO, message,
    //             getTest().addScreenCaptureFromBase64String(base64Image).getModel().getMedia().get(0));
    // }

    public static void addScreenShot(Status status, String message) {

        String base64Image = "data:image/png;base64,"
                + ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BASE64);
        getTest().log(status, message,
                getTest().addScreenCaptureFromBase64String(base64Image).getModel().getMedia().get(0));
    }

    // public static void logMessage(String message) {
    //     getTest().log(Status.INFO, message);
    // }

    public static void logMessage(Status status, String message) {
        getTest().log(status, message);
    }

}