package com.test;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Basetest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        
        driver.get("https://www.automationexercise.com/");
    }

    @AfterClass
    public void tearDown() {
        
        if (driver != null) {
            driver.quit();
        }
    }
}
