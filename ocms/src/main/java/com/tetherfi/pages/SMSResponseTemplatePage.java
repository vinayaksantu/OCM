package com.tetherfi.pages;

import com.tetherfi.model.ivr.IntroMessageAnnouncementDetails;
import com.tetherfi.utility.FileUploader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SMSResponseTemplatePage extends BasePage {

    public SMSResponseTemplatePage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement smsResponseTemplate;
    
    @FindBy(css="#create")
    private WebElement addNewRecordBtn;
    
    @FindBy(css = ".k-grid-edit")
    private WebElement editButton;
    
    @FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
    
    @FindBy(xpath = "//div[@id='tabstripsmstemplateMakerChecker']/ul/li")
    private List<WebElement> navTabs;
    
    @FindBy(css="#makeChanges")
    private WebElement makeChangesButton;
    
    public boolean isSMSResponseTemplatePageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return smsResponseTemplate.isEnabled();
    }
    
    public void navigateToTab(String tabname){
        waitUntilWebElementListIsVisible(navTabs);
        waitUntilWebElementListIsClickable(navTabs);
        for(WebElement ele: navTabs){
            if(ele.getText().equalsIgnoreCase(tabname)){selectWebElement(ele);break;}
        }
    }
    
    public boolean isMakeChangesBtnDisplayed() {
    	Boolean status = false;
    	try {
    		if(makeChangesButton.isDisplayed() && makeChangesButton.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
    }
    
	public boolean isAddBtnDisplayed() {
    	return addNewRecordBtn.isDisplayed() && addNewRecordBtn.isEnabled();
    }
	
    public boolean isEditBtnDisplayed() {
    	Boolean status = false;
    	try {
    		if(editButton.isDisplayed() && editButton.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
    }
    
    public boolean isDeleteBtnDisplayed() {
    	Boolean status = false;
    	try {
    		if(deleteButton.isDisplayed() && deleteButton.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
    }
    
    public boolean isExportBtnDisplayed() {
    	return exporttoexcel.isDisplayed() && exporttoexcel.isEnabled();
    }

	public void selectMakeChangesBtn() {
		selectWebElement(makeChangesButton);		
	}
}
