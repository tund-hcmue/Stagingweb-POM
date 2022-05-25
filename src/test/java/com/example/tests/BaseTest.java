package com.example.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public static WebDriver driver;
    public static WebDriver getDriver(){
        if (driver == null){
          driver = new ChromeDriver();
          return driver;
        }else{
          return driver;
        }
      }
    public int timeout = 60;
    public String path = "src/test/java/com/example/core/driver/chromedriver"; //src\\test\\java\\com\\example\\core\\driver\\chromedriver.exe
    public String baseUrl = "https://staging.kbb.com/car-prices/";

    @BeforeMethod
    public void setUp(){
        System.setProperty(
            "webdriver.chrome.driver",
            path);
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    
}