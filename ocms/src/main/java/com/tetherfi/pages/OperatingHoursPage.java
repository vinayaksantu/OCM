package com.tetherfi.pages;

import com.tetherfi.model.ivr.OperatingHoursDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;


public class OperatingHoursPage extends BasePage {

    public OperatingHoursPage(WebDriver driver) {
        super(driver);
    }

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

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css=".ibox-title h5")
    private WebElement operatingHours;

    @FindBy(css="#create")
    private WebElement addNewOperatingHoursRcrdBtn;

    @FindBy(css=".k-grid-content")
    private WebElement gridContent;

    @FindBy(css=".k-edit-form-container .k-numerictextbox .k-numeric-wrap .k-formatted-value")
    private WebElement addVdnTextBox;

    @FindBy(id="VDN")
    private WebElement addVdnTextBox1;

    @FindBy(css=".k-i-check")
    private WebElement addVdnNameSaveButton;

    @FindBy(css="#editWeekday .k-floatwrap")
    private WebElement addWeekDayDropdown;

    @FindBy(css="ul[id='Day_listbox'] li")
    private List<WebElement> addWeekDayListBox;

    @FindBy(css="#StartTime")
    private WebElement StartTimeTextBox;

    @FindBy(css="#EndTime")
    private WebElement EndTimeTextBox;

    @FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;

    @FindBy(id = "ModifyReason1")
    private WebElement deleteReasonTextBox;

    @FindBy(id = "yesButton")
    private WebElement deleteYesBtn;

    @FindBy(css = "#toast-container .toast-error")
    private List<WebElement> errorMsg;

    @FindBy(xpath="//button[text()='Export to Excel']")
    private WebElement exporttoexcel;
    
    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;
    @FindBy(css = ".k-grid-edit")
    private WebElement editButton;
    @FindBy(id = "ModifyReason")
    private WebElement ModifyReasonTextBox;

    public boolean isOperatingHoursPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return operatingHours.isEnabled();
    }
    public void addNewOperatingHoursRecord(OperatingHoursDetails details) {
        selectWebElement(addNewOperatingHoursRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(addVdnTextBox);
        enterValueToTxtField(addVdnTextBox1,details.getVdnName());
        selectWebElement(addWeekDayDropdown);
        selectDropdownFromVisibleText(addWeekDayListBox,details.getWeekDay());
        selectWebElement(StartTimeTextBox);
        enterValueToTxtField(StartTimeTextBox,details.getStartTime());
        selectWebElement(EndTimeTextBox);
        enterValueToTxtField(EndTimeTextBox,details.getEndTime());
        selectWebElement(addVdnNameSaveButton);

    }

    public void searchOperatingHoursRecord(String vdn) {
        selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"vdn");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,vdn);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }

    public void editOperatingHoursRecord(OperatingHoursDetails operatingHoursDetails) {
        searchOperatingHoursRecord(operatingHoursDetails.getVdnName());
        selectWebElement(editButton);
        selectWebElement(StartTimeTextBox);
        enterValueToTxtField(StartTimeTextBox,operatingHoursDetails.getStartTime());
        selectWebElement(EndTimeTextBox);
        enterValueToTxtField(EndTimeTextBox,operatingHoursDetails.getEndTime());
        selectWebElement(ModifyReasonTextBox);
        enterValueToTxtField(ModifyReasonTextBox,operatingHoursDetails.getModifyReason());
        selectWebElement(addVdnNameSaveButton);

    }
    public void deleteOperatingHoursRecord(OperatingHoursDetails operatingHoursDetails) {
        searchOperatingHoursRecord(operatingHoursDetails.getVdnName());
        selectWebElement(deleteButton);
        enterValueToTxtField(deleteReasonTextBox,operatingHoursDetails.getDeleteReason());
        selectWebElement(deleteYesBtn);
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
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Deleted Successfully"))
        {return true;}else{return false;}
    }
    
    public boolean isAddBtnDisplayed() {
    	return addNewOperatingHoursRcrdBtn.isDisplayed() && addNewOperatingHoursRcrdBtn.isEnabled();
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
