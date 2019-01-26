package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UserManagementPage extends BasePage {

    public UserManagementPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement userManagement;

    @FindBy(id="create")
    private WebElement addNewUserManagementRecordBtn;

    @FindBy(css =".k-edit-form-container #UserId")
    private WebElement userIdTextBox;

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

    @FindBy(css = ".k-grid-edit")
    private WebElement editButton;

    @FindBy(css=".k-grid-CustomDelete")
    private WebElement deleteBtn;

    @FindBy(id = "ModifyReason")
    private WebElement editModifyReasonTextBox;

    @FindBy(css=".form-group #ModifyReason1")
    private  WebElement deleteReasonTextBox;

    @FindBy(id="yesButton")
    private WebElement yesBtn;

    @FindBy(id="noButton")
    private WebElement noBtn;

    public boolean isUserManagementPageDisplayed() throws InterruptedException {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return userManagement.isEnabled();
    }
    public void addNewUserManagementRecord(String userID) {
        selectWebElement(addNewUserManagementRecordBtn);
        selectWebElement(userIdTextBox);
        enterValueToTxtField(userIdTextBox,userID);
        selectWebElement(saveBtn);
    }
    public void editUserManagementRecord(String UserID, String reason) {
        searchUserManagementRecord(UserID);
        selectWebElement(editButton);
        enterValueToTxtField(userIdTextBox,UserID);
        enterValueToTxtField(editModifyReasonTextBox,reason);
        selectWebElement(saveBtn);
    }
    public void searchUserManagementRecord(String UserID) {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"User Id");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),UserID);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
    }
    public void deleteUserManagementRecord(String username, String reason) {
        searchUserManagementRecord(username);
        selectWebElement(deleteBtn);
        enterValueToTxtField(deleteReasonTextBox,reason);
        selectWebElement(yesBtn);
    }
    public boolean verifyNewRecordCreated(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Created Successfully"))
        {return true;}else{return false;}
    }

    public boolean verifyRecordUpdated(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Updated Successfully"))
        {return true;}else{return false;}
    }

    public boolean verifyRecordDeleted(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Deleted Successfully")){
            return true;
        }else{return false;}
    }
}
