package com.example.core.datadriven;

import com.example.core.helper.JsonHelper;

import org.testng.annotations.DataProvider;

public class CarSearch {
    @DataProvider(name = "dataForSeachCars")    
    public static Object dataLoginAccount(){
        return JsonHelper.ReadJsonFromFile("src/test/java/com/example/core/resources/carsearch.json");
    };
}
