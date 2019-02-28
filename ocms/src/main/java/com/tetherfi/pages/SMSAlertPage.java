package com.tetherfi.pages;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import  java.util.List;

import com.tetherfi.model.chat.CallBackManagementDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SMSAlertPage extends BasePage {
    public SMSAlertPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".ibox-title h5")
    private WebElement smsAlert;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;
    
    @FindBy(css = "#makeChanges")
    private WebElement makeChangesBtn;
    
    @FindBy(css = "#create")
    private WebElement addSmsAlertRecdBtn;
    
    @FindBy(xpath = "//div[@id='tabstripsmsalertMakerChecker']/ul/li")
    private List<WebElement> navTabs;
    
    public boolean isSMSAlertPageDisplayed() throws InterruptedException {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return smsAlert.isEnabled();
    }
    
    public boolean isMakeChangesBtnDisplayed() {
    	Boolean status = false;
    	try {
    		if(makeChangesBtn.isDisplayed() && makeChangesBtn.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
    }
    
    public void navigateToTab(String tabname){
        waitUntilWebElementListIsVisible(navTabs);
        waitUntilWebElementListIsClickable(navTabs);
        for(WebElement ele: navTabs){
            if(ele.getText().equalsIgnoreCase(tabname)){selectWebElement(ele);break;}
        }
    }
    
    public void clickOnMakeChangesBtn() {
    	selectWebElement(makeChangesBtn);
    }
    
    public boolean isAddBtnDisplayed() {
    	return addSmsAlertRecdBtn.isDisplayed() && addSmsAlertRecdBtn.isEnabled();
    }
    
    public boolean isExportBtnDisplayed() {
    	return exporttoexcel.isDisplayed() && exporttoexcel.isEnabled();
    }
    
}