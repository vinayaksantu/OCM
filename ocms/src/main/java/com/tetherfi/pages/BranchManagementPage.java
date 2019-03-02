package com.tetherfi.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BranchManagementPage extends BasePage {

	public BranchManagementPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css=".ibox-title h5")
    private WebElement branchManagement;
	
	@FindBy(xpath = "//div[@id='tabstripAgentMakerChecker']/ul/li")
    private List<WebElement> navTabs;
	
	@FindBy(xpath="//button[text()='Make Branch Management Changes']")
	private WebElement makeBranchManageChanges;
	
	@FindBy(css="#create")
	private WebElement addNewBranchManageRecordBtn;
	
	@FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;
	
	@FindBy(css = ".k-grid-edit")
    private WebElement editButton;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
	
	public boolean isBranchManagementPageDisplayed() {
		waitForLoad(driver);
        waitForJqueryLoad(driver);
		return branchManagement.isEnabled();
	}
	
	public void navigateToTab(String tabname){
        waitUntilWebElementListIsVisible(navTabs);
        waitUntilWebElementListIsClickable(navTabs);
        for(WebElement ele: navTabs){
            if(ele.getText().equalsIgnoreCase(tabname)){selectWebElement(ele);break;}
        }
    }
	
	public void clickOnMakeBranchManageChanges() {
		selectWebElement(makeBranchManageChanges);
	}
	
	public boolean isMakeBranchChangesButtonDisplayed() {
		Boolean status = false;
		try {
    		if(makeBranchManageChanges.isDisplayed() && makeBranchManageChanges.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
	}
	
	public boolean isAddBtnDisplayed() {
    	return addNewBranchManageRecordBtn.isDisplayed() && addNewBranchManageRecordBtn.isEnabled();
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
