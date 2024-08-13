package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {

	 WebDriver driver;

	    @FindBy(xpath = "//a[@href='/login']")
	    WebElement signInButton;

	    @FindBy(xpath = "//a[@href='/signup']")
	    WebElement signUpButton;

	    public Homepage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    public void clickSignIn() {
	        signInButton.click();
	    }

	    public void clickSignUp() {
	        signUpButton.click();
	    }
}
