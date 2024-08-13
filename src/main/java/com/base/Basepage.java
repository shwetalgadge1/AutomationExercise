package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Basepage {
	  protected WebDriver driver;

	    @FindBy(xpath = "//i[contains(@class,'fa fa-lock')]") // Replace with the actual locator
	    protected WebElement signUpLink;

//	    @FindBy(id = "signing-link") // Replace with the actual locator
//	    protected WebElement loginLink;

	    public Basepage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    public void goToSignupPage() {}

//	    public void goToLoginPage() {
//	        loginLink.click();
//	    }

}
