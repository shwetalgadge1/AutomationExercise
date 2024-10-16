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

	public class TestProductDetails {

	    public static WebDriver driver;

	    @BeforeClass
	    public void setUp() {
	        
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://www.automationexercise.com/category_products/3");
	    }

	    @Test
	    public void testTshirtDetails() {
	        
	        List<WebElement> allTshirts = driver.findElements(By.xpath("//a[contains(@href,'/brand_products/Polo')]"));
	        System.out.println("Total T-shirts found: " + allTshirts.size());
	        for (WebElement e : allTshirts) {
	            System.out.println("T-shirt: " + e.getText());
	        }

	      
	        List<WebElement> allTshirtsNameType = driver.findElements(By.xpath("//b[normalize-space()='Brand:']"));
	        System.out.println("Total type of T-shirt names: " + allTshirtsNameType.size());
	        for (WebElement e : allTshirtsNameType) {
	            System.out.println("Brand: " + e.getText());
	        }

	        
	        List<WebElement> tshirtPrices = driver.findElements(By.cssSelector("div[class='product-information'] span span"));
	        System.out.println("Total T-shirt prices found: " + tshirtPrices.size());
	        for (WebElement e : tshirtPrices) {
	            System.out.println("Price: " + e.getText());
	        }
	    }

	    @AfterClass
	    public void tearDown() {
	        
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}



