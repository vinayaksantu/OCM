package com.tetherfi.pages;

import com.tetherfi.model.user.UserRoleMappingDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UserRoleMappingPage extends BasePage {

    public UserRoleMappingPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement userRoleMapping;

    @FindBy(css=".k-grid-content .k-selectable")
    private WebElement gridContent;
    
    @FindBy(xpath = "//div[@id='tabstripMappingMakerChecker']/ul/li")
    private List<WebElement> navTabs;

    @FindBy(css=".k-loading-img")
    private WebElement loadingImg;

    @FindBy(css="modal-backdrop")
    private WebElement backdropImg;
    
    @FindBy(css="#makeChanges")
    private WebElement makeChangesBtn;

    @FindBy(css=".k-grid-top .k-button-icontext .k-i-add")
    private WebElement addNewUserRoleMappingRecordBtn;

    @FindBy(id="_TextToSearch")
    private WebElement userToSearchTextBox;

    //@FindBy(css="#_apdFormTextBox .k-button-icontext")
    @FindBy(css="button[onclick='searchUsers()']")
    private WebElement searchBtn;

    @FindBy(css="#gridAvailableUsers .k-grid-content .k-selectable")
    private WebElement availableUsersTable;

    @FindBy(css="span[aria-owns='Team_listbox']")
    private WebElement teamNameDropDown;

    @FindBy(css="span[aria-owns='TeamId_listbox']")
    private WebElement editTeamNameDropDown;

    @FindBy(css="ul[id='Team_listbox'] li")
    private List<WebElement> teamListBox;

    @FindBy(css="ul[id='TeamId_listbox'] li")
    private List<WebElement> teamIDListBox;

    @FindBy(css=".form-group .k-numerictextbox .k-numeric-wrap .k-formatted-value")
    private WebElement avayaLoginIDTextbox;

    @FindBy(css=".k-edit-form-container .k-numerictextbox .k-numeric-wrap .k-formatted-value")
    private WebElement editAvayaLoginIDTextbox;

    @FindBy(css=".modal-body .col-lg-10 label")
    private List<WebElement> radioBtn;

    @FindBy(css="li label")
    private List<WebElement> editradioBtn;

    @FindBy(css="#panelSupervisor .k-dropdown .k-select")
    private WebElement supervisorDropdown;

    @FindBy(css="#panelSupervisorEdit .k-dropdown .k-select")
    private WebElement editsupervisorDropdown;

    @FindBy(css="ul[id='Supervisor_listbox'] li")
    private List<WebElement> supervisorListBox;

    @FindBy(css="span[aria-owns='Profile_listbox']")
    private WebElement profilesDropdown;

    @FindBy(css="ul[id='Profile_listbox'] li")
    private List<WebElement> profilesListBox;

    @FindBy(id="Role")
    private WebElement userSecurityGroupTextbox;

    @FindBy(css=".modal-footer .k-grid-save")
    private WebElement saveBtn;

    @FindBy(css=".k-edit-buttons .k-grid-update")
    private WebElement editFormSaveBtn;

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
    private WebElement popupSearchBtn;

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

    @FindBy(css=".k-grid-norecords-template")
    private List<WebElement> noRecords;

    //@FindBy(css="#popupSearchUsers button[class='close']")
    @FindBy(css="div[style*='display: block'] a[aria-label='Close']")
    private WebElement closeBtn;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;

    public boolean isUserRoleMappingPageDisplayed() throws InterruptedException {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return userRoleMapping.isEnabled();
    }
    
    public void navigateToTab(String tabname){
        waitUntilWebElementListIsVisible(navTabs);
        waitUntilWebElementListIsClickable(navTabs);
        for(WebElement ele: navTabs){
            if(ele.getText().equalsIgnoreCase(tabname)){selectWebElement(ele);break;}
        }
    }
    
    public void clickOnMakeChangesBtn() {
    	selectWebElement(makeChangesBtn);
    }
    
    public void addNewUserRoleMappingRecord(UserRoleMappingDetails details) throws Exception {
        selectWebElement(addNewUserRoleMappingRecordBtn);
        enterValueToTxtField(userToSearchTextBox,details.getBankUserName());
        try {
        selectWebElement(searchBtn);
        Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(noRecords.size()>0){
            System.out.println(noRecords.get(0).getText());
            selectWebElement(closeBtn);
        }else{
        clickonBankUserName(details.getBankUserName());
        waitForJqueryLoad(driver);
       /* selectWebElement(teamNameDropDown);
        selectDropdownFromVisibleText(teamListBox,details.getTeamName());
        enterValueToTxtFieldWithoutClear(avayaLoginIDTextbox,details.getAvayaLoginID());
        selectProfile(details.getProfile(),details.getSupervisor());
        selectWebElement(saveBtn);*/
            selectWebElement(editTeamNameDropDown);
            selectDropdownFromVisibleText(teamIDListBox,details.getTeamName());
            enterValueToTxtFieldWithoutClear(editAvayaLoginIDTextbox,details.getAvayaLoginID());
            editProfile(details.getProfile(),details.getSupervisor());
            btnClick(editFormSaveBtn);}
    }
    public void searchUserRoleMappingRecord(String bankUsername) throws Exception  {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"1Bank User Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),bankUsername);
        selectWebElement(popupSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public void editUserRoleMappingRecord(UserRoleMappingDetails details) throws Exception {
        searchUserRoleMappingRecord(details.getBankUserName());
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(editTeamNameDropDown);
        selectDropdownFromVisibleText(teamIDListBox,details.getTeamName());
        enterValueToTxtFieldWithoutClear(editAvayaLoginIDTextbox,details.getAvayaLoginID());
        editProfile(details.getProfile(),details.getSupervisor());
        enterValueToTxtField(modifyReasonTextBox,details.getModifyReason());
        btnClick(editFormSaveBtn);
    }
    public void selectProfile(String profile, String supervisor){
        if(profile.equalsIgnoreCase("Agent")){
            selectWebElement(radioBtn.get(0));
            selectSupervisor(supervisor);
        }else if(profile.equalsIgnoreCase("Supervisor")){
            selectWebElement(radioBtn.get(1));
        }
    }
    public void selectSupervisor(String supervisor){
    selectWebElement(supervisorDropdown);
    selectDropdownFromVisibleText(supervisorListBox,supervisor);
    }
    public void editProfile(String profile, String supervisor){
        selectWebElement(profilesDropdown);
        if(profile.equalsIgnoreCase("Agent")){
          //selectWebElement(editradioBtn.get(6));
            selectDropdownFromVisibleText(profilesListBox,profile);
          editSupervisor(supervisor);
        }else if(profile.equalsIgnoreCase("Supervisor")){
         //selectWebElement(editradioBtn.get(7));
            selectDropdownFromVisibleText(profilesListBox,profile);
        }
    }
    public void editSupervisor(String supervisor){
        selectWebElement(editsupervisorDropdown);
        for(WebElement ele: supervisorListBox){
            try{if(ele.getText().equalsIgnoreCase(supervisor)){ele.click();break;}}catch(Exception e){
                System.out.println("element not found");
            }
        }
    }
    public void clickonBankUserName(String bankusername){
        waitUntilWebElementIsVisible(availableUsersTable);
        List<WebElement> rows= availableUsersTable.findElements(By.tagName("tr"));
        for(WebElement ele: rows){
            waitUntilWebElementIsVisible(ele);
            List<WebElement> columns=ele.findElements(By.tagName("td"));
            if(columns.get(2).getText().equalsIgnoreCase(bankusername)){columns.get(2).click();}
        }
    }
    public boolean verifyNewRecordCreated(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Created Successfully"))
        {return true;}else{return false;}
    }
    public void deleteUserRoleMappingRecord(String bankusername, String reason) throws Exception {
        searchUserRoleMappingRecord(bankusername);
        btnClick(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtField(deleteReasonTextBox,reason);
        selectWebElement(yesBtn);
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
    	return addNewUserRoleMappingRecordBtn.isDisplayed() && addNewUserRoleMappingRecordBtn.isEnabled();
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

	public boolean isMakeChangesBtnDisplayed() {
		Boolean status = false;
    	try {
    		if(makeChangesBtn.isDisplayed() && makeChangesBtn.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
	}
}
