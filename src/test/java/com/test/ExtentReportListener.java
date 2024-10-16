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
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportListener {

    public static WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.automationexercise.com/products");

        // Setup ExtentReports and attach the reporter
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Optional: Additional configurations
        htmlReporter.config().setReportName("Automation Exercise Products Test Report");
        htmlReporter.config().setDocumentTitle("Test Report");
    }

    @Test
    public void verifyProductNamesAndPrices() {
        test = extent.createTest("Verify Product Names and Prices", "Test to validate the names and prices of products");

        try {
            List<WebElement> products = driver.findElements(By.xpath("//div[@class='productinfo text-center']"));
            test.log(Status.INFO, "Fetched all products. Total products: " + products.size());

            for (WebElement product : products) {
                String productName = product.findElement(By.tagName("p")).getText();
                String productPrice = product.findElement(By.tagName("h2")).getText();

                test.log(Status.INFO, "Product Name: " + productName);
                test.log(Status.INFO, "Product Price: " + productPrice);

                System.out.println("Product Name: " + productName);
                System.out.println("Product Price: " + productPrice);
                System.out.println("----------------------");
            }
            test.log(Status.PASS, "All product names and prices verified successfully.");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        if (extent != null) {
            extent.flush();
        }
    }
}
