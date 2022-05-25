package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StagingCarPricePage extends BasePage{

    public StagingCarPricePage(WebDriver driver) {
        super(driver);
    }
    
            
    public void searchForAVehicle(String year, String make, String model, String zipcode){
        setSelectElementByValue(ddlYear, year);
        setSelectElementByValue(ddlMake, make);
        setSelectElementByValue(ddlModel, model);
        writeTextWebelement(txtZipcode, zipcode);
        clickWebElement(btnGetReviews);
    }

    public WebElement ddlYear = driver.findElement(By.cssSelector("select[placeholder*='Year']"));
    public WebElement ddlMake = driver.findElement(By.cssSelector("select[placeholder*='Make']"));
    public WebElement ddlModel = driver.findElement(By.cssSelector("select[placeholder*='Model']"));
    public WebElement txtZipcode = driver.findElement(By.cssSelector("input[class*='zipcode']"));
    public WebElement btnGetReviews = driver.findElement(By.cssSelector("button[class*='WrappedButton']"));


}
