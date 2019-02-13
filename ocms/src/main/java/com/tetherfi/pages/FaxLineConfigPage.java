package com.tetherfi.pages;

import com.tetherfi.model.fax.FaxLineConfigDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class FaxLineConfigPage extends BasePage{
    public FaxLineConfigPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css=".ibox-title h5")
    private WebElement faxLineConfig;

    @FindBy(id="create")
    private WebElement addNewFaxLineConfigRcrdBtn;

    @FindBy(css=".k-numerictextbox")
    private List<WebElement> faxLineTextFields;

    @FindBy(id="dnis")
    private WebElement faxLineTextBox;

    @FindBy(id="prefix")
    private WebElement prefixTextBox;

    @FindBy(id="FaxLineName")
    private WebElement faxLineNameTextBox;

    @FindBy(id="Description")
    private WebElement descriptionTextBox;

    @FindBy(css="span[aria-owns='Enabled_listbox']")
    private WebElement statusDropdown;

    @FindBy(css="ul[id='Enabled_listbox'] li")
    private List<WebElement> statusListBox;

    @FindBy(css="span[aria-owns='SendEnabled_listbox']")
    private WebElement sendStatusDropdown;

    @FindBy(css="ul[id='SendEnabled_listbox'] li")
    private List <WebElement> sendStatusListBox;

    @FindBy(css="span[aria-owns='ReceiveEnabled_listbox']")
    private WebElement receiveStatusDropdown;

    @FindBy(css="ul[id='ReceiveEnabled_listbox'] li")
    private List<WebElement> receiveStatusListBox;

    @FindBy(css=".k-grid-update")
    private WebElement faxLineSaveButton;

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
    
    @FindBy(xpath="//button[text()='Export to Excel']")
    private WebElement exporttoexcel;

    public boolean isFaxLineConfigPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return faxLineConfig.isEnabled();
    }

    public void addNewFaxLineConfigRecord(FaxLineConfigDetails faxLineConfigDetails) {
        selectWebElement(addNewFaxLineConfigRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(faxLineTextFields.get(1));
        enterValueToTxtFieldWithoutClear(faxLineTextBox,faxLineConfigDetails.getFaxLine());
        selectWebElement(faxLineTextFields.get(2));
        enterValueToTxtField(prefixTextBox,faxLineConfigDetails.getPrefix());
        selectWebElement(faxLineNameTextBox);
        enterValueToTxtField(faxLineNameTextBox,faxLineConfigDetails.getFaxLineName());
        selectWebElement(descriptionTextBox);
        enterValueToTxtField(descriptionTextBox,faxLineConfigDetails.getDescription());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,faxLineConfigDetails.getStatus());
        selectWebElement(sendStatusDropdown);
        selectDropdownFromVisibleText(sendStatusListBox,faxLineConfigDetails.getSendStatus());
        selectWebElement(receiveStatusDropdown);
        selectDropdownFromVisibleText(receiveStatusListBox,faxLineConfigDetails.getReceiveStatus());
        selectWebElement(faxLineSaveButton);
        }

    public void editFaxLineConfig(FaxLineConfigDetails faxLineConfigDetails) {
        searchFaxLineConfigRecord(faxLineConfigDetails.getFaxLine());
        selectWebElement(editButton);
        selectWebElement(faxLineTextFields.get(2));
        enterValueToTxtField(prefixTextBox,faxLineConfigDetails.getPrefix());
        selectWebElement(descriptionTextBox);
        enterValueToTxtField(descriptionTextBox,faxLineConfigDetails.getDescription());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,faxLineConfigDetails.getStatus());
        selectWebElement(sendStatusDropdown);
        selectDropdownFromVisibleText(sendStatusListBox,faxLineConfigDetails.getSendStatus());
        selectWebElement(receiveStatusDropdown);
        selectDropdownFromVisibleText(receiveStatusListBox,faxLineConfigDetails.getReceiveStatus());
        selectWebElement(modifyReasonTextBox);
       enterValueToTxtField(modifyReasonTextBox,faxLineConfigDetails.getModifyReason());
       selectWebElement(faxLineSaveButton);
    }

    public void searchFaxLineConfigRecord(String faxLine) {
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

    public void deleteFaxLineConfig(FaxLineConfigDetails faxLineConfigDetails) {
            searchFaxLineConfigRecord(faxLineConfigDetails.getFaxLine());
            selectWebElement(deleteButton);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enterValueToTxtField(deleteReasonTextBox,faxLineConfigDetails.getDeleteReason());
            selectWebElement(deleteYesBtn);
        }

    public String getSuccessMessage(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return errorMsg.get(0).getText();}
        waitUntilWebElementIsVisible(successmsg);
        return successmsg.getText();
    }
    
    public boolean isAddBtnDisplayed() {
    	return addNewFaxLineConfigRcrdBtn.isDisplayed() && addNewFaxLineConfigRcrdBtn.isEnabled();
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


