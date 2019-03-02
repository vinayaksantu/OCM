package com.tetherfi.pages;

import com.tetherfi.utility.FileUploader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReportDownloadsPage extends BasePage {

    public ReportDownloadsPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement reportDownloads;
    
    @FindBy(css = ".k-grid-CustomDelete")
	private WebElement deleteButton;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;
    
    public boolean isReportDownloadsPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return reportDownloads.isEnabled();
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
     
}
