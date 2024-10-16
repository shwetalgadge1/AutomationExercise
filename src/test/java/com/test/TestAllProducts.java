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

public class TestAllProducts {

    public static WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.automationexercise.com/products");
    }

    @Test
    public void verifyProductNamesAndPrices() {
       
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='productinfo text-center']"));
        System.out.println("Total products: " + products.size());
        
        for (WebElement product : products) {
           
            String productName = product.findElement(By.tagName("p")).getText();
           
            String productPrice = product.findElement(By.tagName("h2")).getText();
            
            System.out.println("Product Name: " + productName);
            System.out.println("Product Price: " + productPrice);
            System.out.println("----------------------");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
