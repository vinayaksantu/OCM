package com.tetherfi.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RoleBasedAccessManagementPage extends BasePage{

    public RoleBasedAccessManagementPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement roleBasedAccessManagement;

    @FindBy(css=".k-grid-content .k-selectable")
    private WebElement gridContent;

    @FindBy(css=".k-loading-img")
    private WebElement loadingImg;

    @FindBy(css="modal-backdrop")
    private WebElement backdropImg;

    @FindBy(id="create")
    private WebElement addNewRoleBasedAccessManagementRecordBtn;

    @FindBy(css=".k-edit-form-container")
    private WebElement popopContainer;

    @FindBy(css =".k-edit-form-container #RoleName")
    private WebElement roleNameTextBox;

    @FindBy(css=".k-edit-form-container .k-grid-update")
    private WebElement saveBtn;

    @FindBy(css=".k-grid-cancel")
    private WebElement cancelBtn;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css="#toast-container .toast-error")
    private List<WebElement> errorMsg;

    @FindBy(css=".search-link")
    private WebElement searchLink;

    @FindBy(css=".modal-body .form-inline .form-group .k-select")
    private List<WebElement> selectSearchColumn;

    @FindBy(css="ul[id='1001sColumnName_listbox'] li")
    private List<WebElement> columnNameList;

    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchTypeList;

    @FindBy(css=".modal-body .form-inline .form-group .k-textbox")
    private List<WebElement> searchText;

    @FindBy(css=".modal-footer .k-primary")
    private WebElement searchBtn;

    @FindBy(css=".k-grid-edit")
    private WebElement editBtn;

    @FindBy(css=".k-grid-CustomDelete")
    private WebElement deleteBtn;

    @FindBy(css=".k-edit-form-container #ModifyReason")
    private  WebElement modifyReasonTextBox;

    @FindBy(css=".form-group #ModifyReason1")
    private  WebElement deleteReasonTextBox;

    @FindBy(id="yesButton")
    private WebElement yesBtn;

    @FindBy(id="noButton")
    private WebElement noBtn;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;

    public boolean isRoleBasedAccessManagementPageDisplayed() throws InterruptedException {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return roleBasedAccessManagement.isEnabled();
    }

    public void addNewRoleBasedAccessManagementRecord(String roleName) {
        selectWebElement(addNewRoleBasedAccessManagementRecordBtn);
        selectWebElement(roleNameTextBox);
        enterValueToTxtField(roleNameTextBox,roleName);
        selectWebElement(saveBtn);
    }
    public void searchRoleBasedAccessManagementRecord(String roleName) {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Role Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),roleName);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public void editRoleBasedAccessManagementRecord(String oldrolename, String newrolename, String reason) {
        searchRoleBasedAccessManagementRecord(oldrolename);
        selectWebElement(editBtn);
        selectWebElement(roleNameTextBox);
        enterValueToTxtField(roleNameTextBox,newrolename);
        enterValueToTxtField(modifyReasonTextBox,reason);
        selectWebElement(saveBtn);
    }
    public void deleteRoleBasedAccessManagementRecord(String oldrolename, String reason) {
        searchRoleBasedAccessManagementRecord(oldrolename);
        selectWebElement(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtField(deleteReasonTextBox,reason);
        selectWebElement(yesBtn);
    }
    public boolean verifyNewRecordCreated(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Created Successfully"))
        {return true;}else{return false;}
    }

    public boolean verifyRecordDeleted(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Deleted Successfully")){
            return true;
        }else{return false;}
    }

    public boolean verifyRecordUpdated(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Updated Successfully"))
        {return true;}else{return false;}
    }
    
    public boolean isAddBtnDisplayed() {
    	return addNewRoleBasedAccessManagementRecordBtn.isDisplayed() && addNewRoleBasedAccessManagementRecordBtn.isEnabled();
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
