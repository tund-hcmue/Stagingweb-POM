package com.example.tests;

import com.example.core.datadriven.CarSearch;
import com.example.core.utils.listeners.ReportListener;
import com.example.pages.StagingCarPricePage;
import com.example.pages.StagingReviewDetailPage;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ReportListener.class)
public class StagingTest extends BaseTest{
    @Test(dataProvider = "dataForSeachCars", dataProviderClass = CarSearch.class)
    public void toReviewDetailPage(String year, String make, String model, String zipcpde){
        StagingCarPricePage stagingCarPricePage = new StagingCarPricePage(driver);
        stagingCarPricePage.searchForAVehicle(year, make, model, zipcpde);
        StagingReviewDetailPage stagingReviewTitlePage = new StagingReviewDetailPage(driver);
        Assert.assertTrue(stagingReviewTitlePage.verifyReviewDetailPageCorrect(year, make, model));
    }
}
//div[class*='fsrModalBackdrop']
// Ads: div[id*='fsrInvite']
// button: button[id*='fsrFocusFirst']
