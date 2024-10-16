package com.test;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBabyhugProducts {

    public static WebDriver driver;

    @BeforeClass
    public void setup() {
     
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.automationexercise.com/brand_products/Babyhug");
    }

    @Test
    public void verifyTotalProducts() {
      
        List<WebElement> allProducts = driver.findElements(By.xpath("//div[@class='productinfo text-center']"));
        System.out.println("Total Babyhug products: " + allProducts.size());
        for (WebElement e : allProducts) {
            System.out.println(e.getText());
        }
    }

    @Test
    public void verifyProductNames() {
        
        List<WebElement> productNames = driver.findElements(By.xpath("//div[@class='productinfo text-center']/p"));
        System.out.println("Total product names: " + productNames.size());
        for (WebElement e : productNames) {
            System.out.println(e.getText());
        }
    }

    @Test
    public void verifyProductPrices() {
       
        List<WebElement> productPrices = driver.findElements(By.xpath("//div[@class='productinfo text-center']/h2"));
        System.out.println("Total prices of products: " + productPrices.size());
        for (WebElement e : productPrices) {
            System.out.println(e.getText());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

