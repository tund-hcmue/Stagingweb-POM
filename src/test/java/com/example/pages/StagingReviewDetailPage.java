package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StagingReviewDetailPage extends BasePage{

    public StagingReviewDetailPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyReviewDetailPageCorrect(String year, String make, String model){
        if(isTextTileContentCorrect(year, make, model) == true && isButtonBuldAndPriceEnable() == true && isButtonCarForSaleEnable() == true){
            return true;
        }else{
            return false;
        }
    }
    public boolean isTextTileContentCorrect(String year, String make, String model){
        
        String expectedContent = year + " " + make + " " + model;

        if(readTextWebElement(lblCarReview).contains(expectedContent)){
            return true;
        }else{
            return false;
        }
    }
    public boolean isButtonBuldAndPriceEnable(){
        return btnBuildAndPrice.isEnabled();
    }
    public boolean isButtonCarForSaleEnable(){
        return btnCarForSale.isEnabled();
    }

    public WebElement lblCarReview = driver.findElement(By.cssSelector("h1[class*='eisth832']"));
    public WebElement btnBuildAndPrice = driver.findElement(By.cssSelector("span[class*='css-95wgy9-primaryButton']"));
    public WebElement btnCarForSale = driver.findElement(By.cssSelector("span[class*='css-tt9syz-default']"));


    
}
