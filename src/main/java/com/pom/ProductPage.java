package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    
    @FindBy(xpath = "//a[normalize-space()='Women']")
    private WebElement womenSection;

  
    @FindBy(xpath = "//div[@id='Women']//a[contains(text(),'Dress')]")
    private List<WebElement> productLinks;

    @FindBy(xpath = "//span[contains(@class,'price')]")
    private WebElement productPrice;
    
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
    }

    public void clickEachProductAndPrintPrice() {
        try {
            
            wait.until(ExpectedConditions.elementToBeClickable(womenSection)).click();
            
           
            List<WebElement> links = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='Women']//a[contains(text(),'Dress')]")));
            
            if (!links.isEmpty()) {
                for (int i = 0; i < links.size(); i++) {
                    WebElement productLink = links.get(i);

                  
                    wait.until(ExpectedConditions.elementToBeClickable(productLink)).click();
                   
                    wait.until(ExpectedConditions.visibilityOf(productPrice));

                   
                    String productName = productLink.getText();

                    // Get the product price
                    String price = productPrice.getText();
                    System.out.println("Product: " + productName + ", Price: " + price);

                  
                    driver.navigate().back();
                    
                    // Wait for the product list to reload and reinitialize the product links
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Women']")));
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='Women']//a[contains(text(),'Dress')]")));
                    links = driver.findElements(By.xpath("//div[@id='Women']//a[contains(text(),'Dress')]"));
                }
            } else {
                System.out.println("No products found in the Women section.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while clicking on products and printing prices: " + e.getMessage());
            List<WebElement> DressesPrices = driver.findElements(By.cssSelector("div[class='product-information'] span span"));
            for(WebElement e1:DressesPrices ) {
           	 System.out.println(e1.getText());
        }
    }

    }}
