package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class ProductDetailsTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.automationexercise.com/");
        
        // Print the current working directory
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
    }

    @Test
    public void captureProductDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Increased wait time

        try {
            // Wait for and navigate to the Products section
            By productsLink = By.cssSelector("a[href='/products']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(productsLink)).click();

            // Debug information
            System.out.println("Navigated to Products section.");

            // Wait for visibility of a product item instead of the entire product list
            By productItemLocator = By.cssSelector(".product-info");
            wait.until(ExpectedConditions.visibilityOfElementLocated(productItemLocator));

            // Create a Word document to save the product details
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("Product Details:\n");

            // Locate all product items
            List<WebElement> products = driver.findElements(By.cssSelector(".product-info"));

            // Check if products are found
            if (products.isEmpty()) {
                run.addBreak();
                run.setText("No products found.");
            } else {
                // Iterate through the products to get names, prices, and images
                for (WebElement product : products) {
                    String name = product.findElement(By.cssSelector("h4.product-name")).getText();
                    String price = product.findElement(By.cssSelector(".price-container .price")).getText();
                    String imageUrl = product.findElement(By.cssSelector(".product-image img")).getAttribute("src");

                    // Append details to the document
                    run.addBreak();
                    run.setText("Name: " + name);
                    run.addBreak();
                    run.setText("Price: " + price);
                    run.addBreak();
                    run.setText("Image URL: " + imageUrl);
                    run.addBreak();
                }
            }

            // Ensure the output directory exists
            File outputDir = new File("testoutput");
            if (!outputDir.exists()) {
                outputDir.mkdir(); // Create the directory if it doesn't exist
            }

            // Write the document to a file in the testoutput directory
            String outputPath = outputDir.getAbsolutePath() + File.separator + "ProductDetails.docx";
            try (FileOutputStream out = new FileOutputStream(outputPath)) {
                document.write(out);
                System.out.println("Product details written to: " + outputPath);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
