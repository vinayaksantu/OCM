package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AgentTransferPage extends BasePage{

	public AgentTransferPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(css=".ibox-title h5")
    private WebElement agentTransfer;
	
	@FindBy(css="#create")
	private WebElement addNewAgentTransferBtn;
	
	@FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;
	
	@FindBy(css = ".k-grid-edit")
    private WebElement editButton;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;

	public boolean isAgentTransferPageDisplayed() {
		waitForLoad(driver);
        waitForJqueryLoad(driver);
		return agentTransfer.isEnabled();
	}

	public boolean isAddBtnDisplayed() {
    	return addNewAgentTransferBtn.isDisplayed() && addNewAgentTransferBtn.isEnabled();
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
