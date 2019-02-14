package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FaxFormsPage extends BasePage{

	
	public FaxFormsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".ibox-title h5")
    private WebElement faxForms;
	
	@FindBy(css="#create")
	private WebElement addNewFaxFormsRecordBtn;
	
	@FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;
	
	@FindBy(css = ".k-grid-edit")
    private WebElement editButton;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
	
	public boolean isFaxFormsPageDisplayed() {
		waitForLoad(driver);
        waitForJqueryLoad(driver);
		return faxForms.isEnabled();
	}
	public boolean isAddBtnDisplayed() {
    	return addNewFaxFormsRecordBtn.isDisplayed() && addNewFaxFormsRecordBtn.isEnabled();
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
}
