package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver){super(driver);}

    @FindBy(css=".navbar-header span")
    private WebElement ocmIconImg;

    @FindBy(id="profiledropdown")
    private WebElement profileDropDown;

    @FindBy(css=".btn-primary")
    private List<WebElement> profileDropdownbtns;

    @FindBy(css=".middle-box H2")
    private WebElement welcomeMsg;

    @FindBy(id="OCMli")
    private WebElement ocmTab;

    @FindBy(id="OCMReportsli")
    private WebElement ocmreportsTab;

    @FindBy(css="button[onclick='continueDashboard()']")
    private WebElement continueToDashboardBtn;

    public void userLogout(){
        waitUntilWebElementIsClickable(profileDropDown);
        selectWebElement(profileDropDown);
        waitUntilWebElementIsClickable(profileDropdownbtns.get(1));
        selectWebElement(profileDropdownbtns.get(1));
    }

    public boolean verifyHomePageTitle(){
        if(getPageTitle().equalsIgnoreCase("OCM | Dashboard")){return true;}else{return false;}
    }

    public boolean verifyWelcomeMsg(){
        waitUntilWebElementIsVisible(welcomeMsg);
        if(getTextFromWebElement(welcomeMsg).contains("Welcome to Tetherfi OCM")){return true;}else{return false;}
    }

    public void navigateToOCMPage(){
        selectWebElement(ocmTab);
		waitForLoad(driver);
    }

    public void navigateToOCMReportsPage(){
        selectWebElement(ocmreportsTab);
    }

    public void navigateToOcmIconImg(){selectWebElement(ocmIconImg);}

    public void navigateToDashBoard(){selectWebElement(continueToDashboardBtn);}
}
