package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pom.ActionClass;

import java.time.Duration;

public class ActionClassTest {
    WebDriver driver;
    ActionClass actionClass;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.automationexercise.com/");
        actionClass = new ActionClass(driver);
    }

    @Test
    public void testActions() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
           
            By productsLinkLocator = By.cssSelector("a[href='/products']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(productsLinkLocator));
            actionClass.hoverOverElement(productsLinkLocator);

            By viewProductLocator = By.linkText("View Product");
            wait.until(ExpectedConditions.visibilityOfElementLocated(viewProductLocator));
            actionClass.clickElement(viewProductLocator);

            
            By searchInputLocator = By.id("search_product");
            System.out.println("Waiting for search input field...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputLocator));
            System.out.println("Search input field is visible.");

    
            actionClass.sendKeysToElement(searchInputLocator, "Dress");

       
            By dropdownLocator = By.id("category");
            wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
            actionClass.clickElement(dropdownLocator);

           
            By menOptionLocator = By.xpath("//option[text()='Men']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(menOptionLocator));
            actionClass.selectDropdownOption(dropdownLocator, "Men");

           
            By menSectionLocator = By.cssSelector("body > section:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > h4:nth-child(1) > a:nth-child(1)']"); // Replace with the correct locator for the Men section
            wait.until(ExpectedConditions.elementToBeClickable(menSectionLocator)).click();

           
        } catch (TimeoutException e) {
            System.out.println("Timeout occurred: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
