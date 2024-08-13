package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProductPage {

	 private WebDriver driver;

	    @FindBy(xpath ="//h2[contains(text(),'All Products')]")
	    private WebElement allProductsSection;

	    @FindBy(xpath ="//div[@class='overlay-content']//p")
	    private List<WebElement> productName;

	    @FindBy(xpath ="//div[@class='overlay-content']//h2")
	    private List<WebElement> productPrices;

	    public ProductPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

		public void printProductDetails1() {
			// TODO Auto-generated method stub
			
		}

	    public void printProductDetails() {
	    	if (productName.size() == productPrices.size() && !productName.isEmpty()) {
	            for (int i = 0; i < productName.size(); i++) {
	                String name = productName.get(i).getText();
	                String price = productPrices.get(i).getText();
	                System.out.println("Product: " + name + ", Price: " + price);
	            }
	        } else if (productName.isEmpty() || productPrices.isEmpty()) {
	            System.out.println("Product names or prices are not loaded.");
	        } else {
	            System.out.println("Mismatch between product names and prices count.");
	        }
	    }
}