package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class IWIVRPage extends BasePage {

    public IWIVRPage(WebDriver driver){super(driver);}

    @FindBy(xpath="//span[text()='clear']")
    private WebElement closeDashboard;
    
    @FindBy(css="#spnCurrentFlowRole")
    private WebElement role;

    public void closeDashboard() {
    	waitForJqueryLoad(driver);
    	selectWebElement(closeDashboard);
    }
    
    public boolean checkRole (String logInRole) {
    	if(role.getText().equalsIgnoreCase(logInRole))
    		return true;
    	else
    		return false;
    }
}
