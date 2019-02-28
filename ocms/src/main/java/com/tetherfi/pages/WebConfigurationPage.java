package com.tetherfi.pages;

import com.tetherfi.model.user.WebConfigurationDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WebConfigurationPage extends BasePage {

    public WebConfigurationPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement webConfiguration;

    @FindBy(css=".k-grid-content")
    private WebElement gridContent;

    @FindBy(css=".k-loading-img")
    private WebElement loadingImg;

    @FindBy(css="modal-backdrop")
    private WebElement backdropImg;

    @FindBy(id="create")
    private WebElement addNewWebConfigurationRecordBtn;

    @FindBy(css=".k-edit-form-container")
    private WebElement popopContainer;

    @FindBy(id="Key")
    private WebElement key;

    @FindBy(id="Value")
    private WebElement value;

    @FindBy(id="Comment")
    private WebElement comment;

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

    public boolean isWebConfigurationPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return webConfiguration.isEnabled();
    }
    public void addNewWebConfigurationRecord(WebConfigurationDetails details) {
        selectWebElement(addNewWebConfigurationRecordBtn);
        selectWebElement(key);
        enterValueToTxtField(key,details.getKey());
        selectWebElement(value);
        enterValueToTxtField(value,details.getValue());
        selectWebElement(comment);
        enterValueToTxtField(comment,details.getComment());
        selectWebElement(saveBtn);
    }
    public void searchWebConfigurationRecord(String key) {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Key");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),key);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public void editWebConfigurationRecord(WebConfigurationDetails details) {
        searchWebConfigurationRecord(details.getKey());
        selectWebElement(editBtn);
        selectWebElement(key);
        enterValueToTxtField(key,details.getUpdatedkey());
        selectWebElement(value);
        enterValueToTxtField(value,details.getValue());
        selectWebElement(comment);
        enterValueToTxtField(comment,details.getComment());
        enterValueToTxtField(modifyReasonTextBox,details.getModifyReason());
        selectWebElement(saveBtn);
    }
    public void deleteWebConfigurationRecord(String key, String reason) {
        searchWebConfigurationRecord(key);
        selectWebElement(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtField(deleteReasonTextBox,reason);
        selectWebElement(yesBtn);
    }
    public boolean verifyNewRecordCreated(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Created successfully"))
        {return true;}else{return false;}
    }

    public boolean verifyRecordDeleted(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Deleted successfully")){
            return true;
        }else{return false;}
    }

    public boolean verifyRecordUpdated(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Updated successfully"))
        {return true;}else{return false;}
    }
    
    public boolean isAddBtnDisplayed() {
    	return addNewWebConfigurationRecordBtn.isDisplayed() && addNewWebConfigurationRecordBtn.isEnabled();
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
