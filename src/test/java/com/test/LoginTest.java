package com.test;

import com.pom.Loginpage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

    WebDriver driver;
    Loginpage loginpage;

    @BeforeClass
    public void setUp() {
        // Use WebDriverManager to manage the ChromeDriver binary
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        
        // Navigate to the login page of the website
        driver.get("https://automationexercise.com/login"); // Replace with the actual login page URL

        // Initialize the Loginpage object
        loginpage = new Loginpage(driver);
    }

    @Test
    public void testLogin() {
        // Perform login action with valid credentials
        String email = "gadgeshwetal@gmail.com"; 
        String password = "Sweety12@";  
        
        loginpage.login(email, password);
        
        // Add assertions here to verify successful login
        // Example: Assert.assertTrue(driver.getCurrentUrl().contains("dashboard") || any other expected behavior);
    }

    @Test
    public void testInvalidLogin() {
        // Perform login action with invalid credentials
        String invalidEmail = "invalid@example.com";
        String invalidPassword = "wrongpassword";
        
        loginpage.login(invalidEmail, invalidPassword);
        
        // Add assertions here to verify login failure
        // Example: Assert.assertTrue(driver.getCurrentUrl().contains("login") || verify an error message);
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
