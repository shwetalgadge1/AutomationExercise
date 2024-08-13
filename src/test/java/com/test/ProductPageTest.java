package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pom.ProductPage;

public class ProductPageTest {
	  private WebDriver driver;
	    private ProductPage productPage;

	    @BeforeMethod
	    public void setUp() {
	        // Initialize ChromeDriver
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://www.automationexercise.com/products");
	        productPage = new ProductPage(driver);
	    }

	    @Test
	    public void testPrintProductDetails() {
	    	try {
	            productPage.printProductDetails();
	            // Add assertions here if needed
	        } catch (Exception e) {
	            Assert.fail("Test failed due to exception: " + e.getMessage());
	        }
	    }
	    

	    @AfterMethod
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }

}