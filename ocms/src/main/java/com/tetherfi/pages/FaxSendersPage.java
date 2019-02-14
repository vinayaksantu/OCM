package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.fax.FaxSendersDetails;

import java.util.List;

public class FaxSendersPage extends BasePage{
    public FaxSendersPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css=".ibox-title h5")
    private WebElement faxSenders;

    @FindBy(id="create")
    private WebElement addNewFaxSendersRcrdBtn;

    @FindBy(css=".k-numerictextbox")
    private List<WebElement> faxLineTextFields;

    @FindBy(css="span[aria-owns='DNIS_listbox']")
    private WebElement faxLineDropdown;

    @FindBy(css="ul[id='DNIS_listbox'] li")
    private List<WebElement> faxLineListBox;

    @FindBy(id="Name")
    private WebElement nameTextBox ;

    @FindBy(id="faxNumber")
    private WebElement faxNumberTextBox;

    @FindBy(css="span[aria-owns=Type_listbox")
    private WebElement senderTypeDropdown;

    @FindBy(css="ul[id='Type_listbox'] li")
    private List<WebElement> sendersTypeListBox;

    @FindBy(css=".k-grid-update")
    private WebElement faxSendersSaveButton;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css = "#toast-container .toast-error")
    private List<WebElement> errorMsg;

    @FindBy(css = ".fa-search")
    private WebElement searchBtn;

    @FindBy(css = ".modal-body .form-inline .form-group .k-select")
    private List<WebElement> selectSearchCol;

    @FindBy(css="ul[id='1001sColumnName_listbox'] li")
    private List<WebElement> columnNameList;

    @FindBy(css = "#1001sFormDropDown .k-i-arrow-60-down")
    private List<WebElement> selectSearchCriteria;

    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchCriteriaDropDwn;

    @FindBy(id = "1001sTextToSearch")
    private WebElement searchTextBox;

    @FindBy(css = "#1001sAddButton .k-i-add")
    private WebElement searchAddCriteriaBtn;

    @FindBy(css = "#1001sCloseButton .k-i-close")
    private WebElement searchRemoveFilterBtn;

    @FindBy(css = ".modal-footer .k-button")
    private WebElement searchCloseBtn;

    @FindBy(css = ".modal-footer .button-danger-theme")
    private WebElement searchClearAllBtn;

    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;

    @FindBy(css=".k-grid-content")
    private WebElement gridContent;

    @FindBy(css = ".k-grid-edit")
    private WebElement editButton;

    @FindBy(id = "ModifyReason")
    private WebElement modifyReasonTextBox;

    @FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;

    @FindBy(id = "ModifyReason1")
    private WebElement deleteReasonTextBox;

    @FindBy(id = "yesButton")
    private WebElement deleteYesBtn;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;


    public boolean isFaxSendersPageDisplayed() {
           waitForLoad(driver);
            waitForJqueryLoad(driver);
            return faxSenders.isEnabled();
        }

    public void addNewFaxSendersRecord(FaxSendersDetails faxSendersDetails) {
        selectWebElement(addNewFaxSendersRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(faxLineDropdown);
        selectDropdownFromVisibleText(faxLineListBox,faxSendersDetails.getFaxLine());
        selectWebElement(nameTextBox);
        enterValueToTxtFieldWithoutClear(nameTextBox,faxSendersDetails.getName());
        selectWebElement(faxLineTextFields.get(1));
        enterValueToTxtField(faxNumberTextBox,faxSendersDetails.getFaxNumber());
        selectWebElement(senderTypeDropdown);
        selectDropdownFromVisibleText(sendersTypeListBox,faxSendersDetails.getSenderType());
        selectWebElement(faxSendersSaveButton);
    }
    public void searchFaxSendersRecord(String faxLine) {
        selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Fax Line");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,faxLine);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public void editFaxSendersRecord(FaxSendersDetails faxSendersDetails) {
        searchFaxSendersRecord(faxSendersDetails.getFaxLine());
        selectWebElement(editButton);
        selectWebElement(nameTextBox);
        enterValueToTxtField(nameTextBox,faxSendersDetails.getName());
        selectWebElement(senderTypeDropdown);
        selectDropdownFromVisibleText(sendersTypeListBox,faxSendersDetails.getSenderType());
        selectWebElement(modifyReasonTextBox);
        enterValueToTxtField(modifyReasonTextBox,faxSendersDetails.getModifyReason());
        selectWebElement(faxSendersSaveButton);
    }
    public void deleteFaxSendersRecord(FaxSendersDetails faxSendersDetails) {
        searchFaxSendersRecord(faxSendersDetails.getFaxLine());
        selectWebElement(deleteButton);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        enterValueToTxtField(deleteReasonTextBox,faxSendersDetails.getDeleteReason());
        selectWebElement(deleteYesBtn);
    }
    public String getSuccessMessage(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return errorMsg.get(0).getText();}
        waitUntilWebElementIsVisible(successmsg);
        return successmsg.getText();
    }
    public boolean isAddBtnDisplayed() {
    	return addNewFaxSendersRcrdBtn.isDisplayed() && addNewFaxSendersRcrdBtn.isEnabled();
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

