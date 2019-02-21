package com.tetherfi.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FaxTemplatePage extends BasePage {

	public FaxTemplatePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css=".ibox-title h5")
    private WebElement faxTemplate;
	
	@FindBy(xpath = "//div[@id='tabstripfaxtemplateMakerChecker']/ul/li")
    private List<WebElement> navTabs;
	
	@FindBy(xpath="//button[text()='Make Fax Template Changes']")
	private WebElement makeFaxTemplateChanges;
	
	@FindBy(css="#create")
	private WebElement addNewFaxTemplateRecordBtn;
	
	@FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;
	
	@FindBy(css = ".k-grid-edit")
    private WebElement editButton;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
	
	public boolean isFaxTemplatePageDisplayed() {
		waitForLoad(driver);
        waitForJqueryLoad(driver);
		return faxTemplate.isEnabled();
	}
	
	public void navigateToTab(String tabname){
        waitUntilWebElementListIsVisible(navTabs);
        waitUntilWebElementListIsClickable(navTabs);
        for(WebElement ele: navTabs){
            if(ele.getText().equalsIgnoreCase(tabname)){selectWebElement(ele);break;}
        }
    }
	
	public void clickOnMakeFaxTemplateChanges() {
		selectWebElement(makeFaxTemplateChanges);
	}
	
	public boolean isMakeFaxTemplateChangesButtonDisplayed() {
		Boolean status = false;
		try {
    		if(makeFaxTemplateChanges.isDisplayed() && makeFaxTemplateChanges.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
	}
	
	public boolean isAddBtnDisplayed() {
    	return addNewFaxTemplateRecordBtn.isDisplayed() && addNewFaxTemplateRecordBtn.isEnabled();
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
