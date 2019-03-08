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

public class FaxAutoACKConfigurationPage extends BasePage {
    public FaxAutoACKConfigurationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".ibox-title h5")
    private WebElement faxAutoAckConfig;
    
    @FindBy(css = "#create")
    private WebElement addFaxAutoAckConfigBtn;
    
    @FindBy(css = ".k-grid-edit")
    private WebElement editBtn;
    
    @FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteBtn;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;

    public boolean isFaxAutoACKConfigurationPageDisplayed() throws InterruptedException {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return faxAutoAckConfig.isEnabled();
    }
    
    public boolean isAddBtnDisplayed() {
    	return addFaxAutoAckConfigBtn.isDisplayed() && addFaxAutoAckConfigBtn.isEnabled();
    }
    
    public boolean isEditBtnDisplayed() {
    	Boolean status = false;
    	try {
    		if(editBtn.isDisplayed() && editBtn.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
    }
    
    public boolean isDeleteBtnDisplayed() {
    	Boolean status = false;
    	try {
    		if(deleteBtn.isDisplayed() && deleteBtn.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
    }
    
    public boolean isExportBtnDisplayed() {
    	return exporttoexcel.isDisplayed() && exporttoexcel.isEnabled();
    }

}