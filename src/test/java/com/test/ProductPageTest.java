package com.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pom.ProductPage;

public class ProductPageTest {

    private WebDriver driver;
    private ProductPage productPage;

    @BeforeClass
    public void setUp() {
      
     

      
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); 

       
        driver = new ChromeDriver(options);

       
        driver.get("https://www.automationexercise.com/product_details");

    
        productPage = new ProductPage(driver);
    }

    @Test
    public void testClickEachProductAndPrintPrice() {
       
        productPage.clickEachProductAndPrintPrice();
        
    

   
    }
    
    @AfterClass
    public void tearDown() {
      
        if (driver != null) {
            driver.quit();
        }
    }
}
