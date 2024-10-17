package com.test;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Set;

public class BrokenLinks {

    WebDriver driver;
    String originalWindow;
    StringBuilder resultLog;

   
    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.automationexercise.com/");
        originalWindow = driver.getWindowHandle(); 
        resultLog = new StringBuilder(); 
    }

   
    @Test
    public void testBrokenLinks() throws InterruptedException {
        List<WebElement> links = driver.findElements(By.tagName("a"));

        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url == null || url.isEmpty()) {
                continue; 
            }

          
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();
                int responseCode = connection.getResponseCode();

                if (responseCode >= 400) {
                   
                    String message = url + " is broken with response code: " + responseCode;
                    resultLog.append("FAILED: ").append(message).append("\n");
                    openGoogleTabWithMessage(message);
                } else {
                   
                    resultLog.append("PASSED: ").append(url).append("\n");
                }

            } catch (IOException e) {
                String message = "Exception occurred while checking the link: " + url;
                resultLog.append("FAILED: ").append(message).append("\n");
                openGoogleTabWithMessage(message);
            }
        }

       
        writeResultsToWord(resultLog.toString());
    }

    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

   
    public void openGoogleTabWithMessage(String message) throws InterruptedException {
       
        ((JavascriptExecutor) driver).executeScript("window.open('https://www.google.com','_blank');");

     
        Set<String> allWindows = driver.getWindowHandles();
        for (String winHandle : allWindows) {
            if (!winHandle.equals(originalWindow)) {
                driver.switchTo().window(winHandle);
                break;
            }
        }

       
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(message);
        searchBox.submit();

    
        Thread.sleep(5000); 
    }

  
    public void writeResultsToWord(String results) {
        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream out = new FileOutputStream("TestResults.docx")) {

            XWPFParagraph titleParagraph = document.createParagraph();
            titleParagraph.createRun().setText("Broken Links Test Results");
            titleParagraph.createRun().addBreak();

          
            for (String line : results.split("\n")) {
                XWPFParagraph resultParagraph = document.createParagraph();
                resultParagraph.createRun().setText(line);
            }

           
            document.write(out);

        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}
