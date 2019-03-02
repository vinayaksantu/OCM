package com.tetherfi.pages;

import com.tetherfi.model.chat.ChatTemplateDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ChatTemplatesPage extends BasePage {

    public ChatTemplatesPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement chatTemplates;

    @FindBy(css=".k-tabstrip-items li")
    private List<WebElement> tabList;

    @FindBy(css="#tgrid .k-grid-content")
    private WebElement gridContent;

    @FindBy(css="#tdrillgrid .k-grid-content")
    private WebElement departmentsGridContent;

    @FindBy(css="#tdrillgrid2 .k-grid-content")
    private WebElement groupsGridContent;

    @FindBy(id="createtwo")
    private WebElement addNewChatTemplatesRecordBtn;

    @FindBy(css="#tdrillgrid #create")
    private WebElement addNewDepartmentRecordBtn;

    @FindBy(css="#tdrillgrid2 #create")
    private WebElement addNewGroupRecordBtn;

    @FindBy(css=".k-edit-form-container")
    private WebElement popupContent;

    @FindBy(css="span[aria-owns='DepartmentName_listbox']")
    private WebElement departmentNameDropdown;

    @FindBy(css="ul[id='DepartmentName_listbox'] li")
    private List<WebElement> departmentNameListbox;

    @FindBy(css="span[aria-owns='GroupName_listbox']")
    private WebElement groupNameDropdown;

    @FindBy(css="ul[id='GroupName_listbox'] li")
    private List<WebElement> groupNameListbox;

    @FindBy(id="Name")
    private WebElement nameTextbox;

    @FindBy(css="span[aria-owns='Enabled_listbox']")
    private WebElement enableDropdown;

    @FindBy(css="ul[id='Enabled_listbox'] li")
    private List<WebElement> enableListbox;

    @FindBy(id="Text")
    private WebElement textTextbox;

    @FindBy(id="Intent")
    private WebElement intentTextbox;

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
	private List<WebElement> exporttoexcel;

    public boolean isChatTemplatePageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return chatTemplates.isEnabled();
    }
    public boolean isDepartmentTabDisplayed(){
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(departmentsGridContent);
        return departmentsGridContent.isDisplayed();
    }
    public boolean isGroupTabDisplayed(){
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(groupsGridContent);
        return groupsGridContent.isDisplayed();
    }
    public void addNewChatTemplatesRecord(ChatTemplateDetails details) {
        selectWebElement(addNewChatTemplatesRecordBtn);
        waitUntilWebElementIsVisible(popupContent);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(departmentNameDropdown);
        selectDropdownFromVisibleText(departmentNameListbox,details.getDepartmentName());
        selectWebElement(groupNameDropdown);
        selectDropdownFromVisibleText(groupNameListbox,details.getGroupName());
        selectWebElement(nameTextbox);
        enterValueToTxtField(nameTextbox,details.getName());
        selectWebElement(enableDropdown);
        selectDropdownFromVisibleText(enableListbox,details.getEnabled());
        selectWebElement(textTextbox);
        enterValueToTxtField(textTextbox,details.getText());
        selectWebElement(intentTextbox);
        enterValueToTxtField(intentTextbox,details.getIntent());
        selectWebElement(saveBtn);
    }
    public void searchChatTemplatesRecord(String name)  {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),name);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public void editChatTemplatesRecord(ChatTemplateDetails details) {
        searchChatTemplatesRecord(details.getName());
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(groupNameDropdown);
        selectDropdownFromVisibleText(groupNameListbox,details.getGroupName());
        selectWebElement(nameTextbox);
        enterValueToTxtField(nameTextbox,details.getUpdatedname());
        selectWebElement(enableDropdown);
        selectDropdownFromVisibleText(enableListbox,details.getEnabled());
        selectWebElement(textTextbox);
        enterValueToTxtField(textTextbox,details.getText());
        selectWebElement(intentTextbox);
        enterValueToTxtField(intentTextbox,details.getIntent());
        enterValueToTxtField(modifyReasonTextBox,details.getModifyReason());
        selectWebElement(saveBtn);
    }
    public void deleteChatTemplatesRecord(String name,String reason) {
        searchChatTemplatesRecord(name);
        btnClick(deleteBtn);
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
    public void navigateToTab(String tabname){
        waitUntilWebElementListIsVisible(tabList);
        waitUntilWebElementListIsClickable(tabList);
        for(WebElement ele:tabList){
            if(ele.getText().equalsIgnoreCase(tabname)){ele.click();break;}
        }
    }
    public void addNewDepartmentRecord(ChatTemplateDetails details) {
        selectWebElement(addNewDepartmentRecordBtn);
        selectWebElement(nameTextbox);
        enterValueToTxtField(nameTextbox,details.getDepartmentName());
        selectWebElement(enableDropdown);
        selectDropdownFromVisibleText(enableListbox,details.getDeptEnabled());
        selectWebElement(saveBtn);
    }
    public void searchDepartmentRecord(String name)  {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),name);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(departmentsGridContent);
    }
    public void editDepartmentRecord(ChatTemplateDetails details) {
        searchDepartmentRecord(details.getDepartmentName());
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(enableDropdown);
        selectDropdownFromVisibleText(enableListbox,details.getDeptEnabled());
        enterValueToTxtField(modifyReasonTextBox,details.getModifyReason());
        selectWebElement(saveBtn);
    }
    public void deleteDepartmentRecord(String name,String reason) {
        searchDepartmentRecord(name);
        btnClick(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtField(deleteReasonTextBox,reason);
        selectWebElement(yesBtn);
    }
    public void addNewGroupRecord(ChatTemplateDetails details) {
        selectWebElement(addNewGroupRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(departmentNameDropdown);
        selectDropdownFromVisibleText(departmentNameListbox,details.getDepartmentName());
        selectWebElement(nameTextbox);
        enterValueToTxtField(nameTextbox,details.getGroupName());
        selectWebElement(enableDropdown);
        selectDropdownFromVisibleText(enableListbox,details.getGroupEnabled());
        selectWebElement(saveBtn);
    }
    public void searchGroupRecord(String name)  {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),name);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(groupsGridContent);
    }
    public void editGroupRecord(ChatTemplateDetails details) {
        searchGroupRecord(details.getGroupName());
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(enableDropdown);
        selectDropdownFromVisibleText(enableListbox,details.getGroupEnabled());
        enterValueToTxtField(modifyReasonTextBox,details.getModifyReason());
        selectWebElement(saveBtn);
    }
    public void deleteGroupRecord(String name,String reason) {
        searchGroupRecord(name);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        btnClick(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtField(deleteReasonTextBox,reason);
        selectWebElement(yesBtn);
    }
    public boolean isAddBtnDisplayed() {
    	return addNewChatTemplatesRecordBtn.isDisplayed() && addNewChatTemplatesRecordBtn.isEnabled();
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
    	waitForJqueryLoad(driver);
    	return exporttoexcel.get(2).isDisplayed() && exporttoexcel.get(2).isEnabled();
    }
}
