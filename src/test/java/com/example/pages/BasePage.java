package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriver driver;
    
    public WebDriverWait wait;
    public int timeout = 60;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void setSelectElementByValue(WebElement webElement, String value){
        convertWebelementToSelect(webElement).selectByValue(value);
    }
    public Select convertWebelementToSelect(WebElement webElement){
        return new Select(waitVisibility(webElement));        
    }

    public String readTextWebElement(WebElement webElement){
        return waitVisibility(webElement).getText();
    }

    public void writeTextWebelement(WebElement webElement, String text){
        waitVisibility(webElement).sendKeys(text);
    }

    public void clickWebElement(WebElement webElement){
        waitVisibility(webElement).click();
    }
    public WebElement waitVisibility(WebElement webElement){
        return wait.until(ExpectedConditions.elementToBeClickable(webElement))  ;      
    }

}
