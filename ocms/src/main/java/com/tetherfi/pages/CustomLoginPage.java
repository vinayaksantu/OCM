package com.tetherfi.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.By;

public class CustomLoginPage extends BasePage {

	public CustomLoginPage(WebDriver driver){super(driver);}
	
	@FindBy(id="UserName")
    private WebElement userName;

    @FindBy(id="Password")
    private WebElement password;

    @FindBy(id="ocmLoginButton")
    private WebElement loginBtn;
 
	public void loginIntoOCM(String username, String Pass) throws Exception{
        selectWebElement(userName);
        enterValueToTxtField(userName,username);
        selectWebElement(password);
        enterValueToTxtField(password,Pass);
        selectWebElement(loginBtn);
    }

}
