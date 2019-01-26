package com.tetherfi.pages;

import com.tetherfi.model.tmac.AgentSettingsDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class AgentSettingsPage extends BasePage {

    public AgentSettingsPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement agentSettings;

    @FindBy(id="create")
    private WebElement addNewAgentSettingsRecordBtn;

    @FindBy(css=".k-edit-form-container")
    private WebElement popupContent;

    @FindBy(id="AvayaLoginID")
    private WebElement avayaLoginIdTextBox;

    @FindBy(css=".k-edit-form-container .form-group .k-numerictextbox .k-numeric-wrap .k-formatted-value")
    private List<WebElement> numericTextbox;

    @FindBy(id="UserName")
    private WebElement usernameTextBox;

    @FindBy(id="FirstName")
    private WebElement firstnameTextBox;

    @FindBy(id="LastName")
    private WebElement lastnameTextBox;

    @FindBy(css="span[aria-owns='TeamName_listbox']")
    private WebElement teamnameDropdown;

    @FindBy(css="ul[id='TeamName_listbox'] li")
    private List<WebElement> teamNameListBox;

    @FindBy(css="span[aria-owns='Profile_listbox']")
    private WebElement profileDropdown;

    @FindBy(css="ul[id='Profile_listbox'] li")
    private List<WebElement> profileListBox;

    @FindBy(css="span[aria-owns='SupervisorName_listbox']")
    private WebElement supervisorDropdown;

    @FindBy(css="ul[id='SupervisorName_listbox'] li")
    private List<WebElement> supervisorListBox;

    @FindBy(css="span[aria-owns='AccessRole_listbox']")
    private WebElement accessroleDropdown;

    @FindBy(css="ul[id='AccessRole_listbox'] li")
    private List<WebElement> accessroleListBox;

    @FindBy(css="span[aria-owns='IsManualInEnabled_listbox']")
    private WebElement featuresDropdown;

    @FindBy(css="ul[id='IsManualInEnabled_listbox'] li")
    private List<WebElement> featuresListBox;

    @FindBy(css="span[aria-owns='CrmName_listbox']")
    private WebElement crmnameDropdown;

    @FindBy(css="ul[id='CrmName_listbox'] li")
    private List<WebElement> crmnameListBox;

    @FindBy(css="span[aria-owns='TextChatGreetingTemplateID_listbox']")
    private WebElement texttemplatenameDropdown;

    @FindBy(css="ul[id='TextChatGreetingTemplateID_listbox'] li")
    private List<WebElement> texttemplatenameListBox;

    @FindBy(id="TotalTabsAllowed")
    private WebElement totalTabsAllowedTextBox;

    @FindBy(id="TotalVoiceTabsAllowed")
    private WebElement totalVoiceTabsAllowedTextBox;

    @FindBy(id="TotalChatTabsAllowed")
    private WebElement totalChatTabsAllowedTextBox;

    @FindBy(id="TotalEmailTabsAllowed")
    private WebElement totalEmailTabsAllowedTextBox;

    @FindBy(id="IsVoice")
    private WebElement voiceCheckbox;

    @FindBy(id="IsEmail")
    private WebElement mailCheckbox;

    @FindBy(id="IsSMS")
    private WebElement smsCheckbox;

    @FindBy(id="IsTextChat")
    private WebElement textChatCheckbox;

    @FindBy(id="IsVideoChat")
    private WebElement videoChatCheckbox;

    @FindBy(id="IsVoiceAcdAutoAcwEnabled")
    private WebElement goToAcwAfterEachAcdCallsCheckbox;

    @FindBy(id="IsVoiceAcdAutoAnswerEnabled")
    private WebElement autoAnswerAllAcdCallsCheckbox;

    @FindBy(id="IsVoiceAllAutoAcwEnabled")
    private WebElement goToAcwAfterAnyCallsCheckbox;

    @FindBy(id="IsCrmEnabled")
    private WebElement crmEnabledCheckbox;

    @FindBy(id="IsHoldVoiceCallOnChatCall")
    private WebElement holdVoiceCallOnChatCallCheckbox;

    @FindBy(id="IsSecondTextChatAutoAnswer")
    private WebElement secondTextChatAutoAnswerCheckbox;

    @FindBy(id="IsTextChatAutoAcwEnabled")
    private WebElement textChatAutoACWCheckbox;

    @FindBy(id="IsTextChatAutoAnswer")
    private WebElement textChatAutoAnswerCheckbox;

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

    @FindBy(css="#tGrid .k-grid-content")
    private WebElement gridContent;

    @FindBy(css=".k-grid-CustomDelete")
    private WebElement deleteBtn;

    @FindBy(css=".form-group #ModifyReason1")
    private  WebElement deleteReasonTextBox;

    @FindBy(css=".k-grid-edit")
    private WebElement editBtn;

    @FindBy(css=".k-edit-form-container #ModifyReason")
    private  WebElement modifyReasonTextBox;

    @FindBy(id="yesButton")
    private WebElement yesBtn;

    @FindBy(id="noButton")
    private WebElement noBtn;

    public boolean isAgentSettingsPageDisplayed() throws InterruptedException {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return agentSettings.isEnabled();
    }

    public void addNewAgentSettingsRecord(AgentSettingsDetails details) {
        selectWebElement(addNewAgentSettingsRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(numericTextbox.get(0));
        enterValueToTxtFieldWithoutClear(avayaLoginIdTextBox,details.getAvayaLoginID());
        selectWebElement(usernameTextBox);
        enterValueToTxtFieldWithoutClear(usernameTextBox,details.getUsername());
        selectWebElement(firstnameTextBox);
        enterValueToTxtFieldWithoutClear(firstnameTextBox,details.getFirstname());
        selectWebElement(lastnameTextBox);
        enterValueToTxtFieldWithoutClear(lastnameTextBox,details.getLastname());
        selectWebElement(teamnameDropdown);
        selectDropdownFromVisibleText(teamNameListBox,details.getTeamName());
        selectProfile(details.getProfile(),details.getSupervisor());
        selectWebElement(accessroleDropdown);
        selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());
        selectWebElement(featuresDropdown);
        selectDropdownFromVisibleText(featuresListBox,details.getFeatures());
        selectWebElement(crmnameDropdown);
        selectDropdownFromVisibleText(crmnameListBox,details.getCrmName());
       // selectWebElement(texttemplatenameDropdown);
       // selectDropdownFromVisibleText(texttemplatenameListBox,details.getTextTemplateName());
        selectWebElement(numericTextbox.get(1));
        enterValueToTxtFieldWithoutClear(totalTabsAllowedTextBox,String.valueOf(details.getTotalTabsAllowed()));
        selectWebElement(numericTextbox.get(2));
        enterValueToTxtFieldWithoutClear(totalVoiceTabsAllowedTextBox,String.valueOf(details.getTotalVoiceTabs()));
        selectWebElement(numericTextbox.get(3));
        enterValueToTxtFieldWithoutClear(totalChatTabsAllowedTextBox,String.valueOf(details.getTotalChatTabs()));
        selectWebElement(numericTextbox.get(4));
        enterValueToTxtFieldWithoutClear(totalEmailTabsAllowedTextBox,String.valueOf(details.getTotalEmailTabs()));
        selectFeaturesToBeSelected(details.getFeaturestobeSeleted());
        selectCheckBox(goToAcwAfterEachAcdCallsCheckbox,details.isGotoACWaftereachACDcalls());
        selectCheckBox(autoAnswerAllAcdCallsCheckbox,details.isAutoanswerallACDcalls());
        selectCheckBox(goToAcwAfterAnyCallsCheckbox,details.isGotoACWafteranycalls());
        selectCheckBox(crmEnabledCheckbox,details.iscRMEnabled());
        selectCheckBox(holdVoiceCallOnChatCallCheckbox,details.isHoldVoiceCallOnChatCall());
        selectCheckBox(secondTextChatAutoAnswerCheckbox,details.isSecondTextChatAutoAnswer());
        selectCheckBox(textChatAutoACWCheckbox,details.isTextChatAutoACWEnabled());
        selectCheckBox(textChatAutoAnswerCheckbox,details.isTextChatAutoAnswer());
        selectWebElement(saveBtn);
    }

    public boolean verifyNewRecordCreated(){
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Created Successfully"))
        {return true;}else{return false;}
    }
    public void selectFeaturesToBeSelected(String[] features){
    for(String feature:features){
        if(feature.equalsIgnoreCase("Voice")){selectCheckbox(voiceCheckbox);}
        else if(feature.equalsIgnoreCase("Email")){selectCheckbox(mailCheckbox);}
        else if(feature.equalsIgnoreCase("SMS")){selectCheckbox(smsCheckbox);}
        else if(feature.equalsIgnoreCase("TextChat")){selectCheckbox(textChatCheckbox);}
        else if(feature.equalsIgnoreCase("VideoChat")){selectCheckbox(videoChatCheckbox);}
    }
    }
    public void selectProfile(String profile, String supervisor){
        try {  if(profile.equalsIgnoreCase("Agent")){
            selectWebElement(profileDropdown);
            selectDropdownFromVisibleText(profileListBox,profile);
            Thread.sleep(3000);
            selectWebElement(supervisorDropdown);
            selectDropdownFromVisibleText(supervisorListBox,supervisor);
        }else if(profile.equalsIgnoreCase("Supervisor")){
            selectWebElement(profileDropdown);
            selectDropdownFromVisibleText(profileListBox,profile);
            Thread.sleep(3000);
            selectWebElement(supervisorDropdown);
            selectDropdownFromVisibleText(supervisorListBox,"NA");
        }} catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void selectCheckBox(WebElement ele, boolean value){
        if(value&&!ele.isSelected()){selectCheckbox(ele);}
    }
    public void searchAgentSettingsRecord(String name)  {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"User Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),name);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public void editAgentSettingsRecord(AgentSettingsDetails details) {
        searchAgentSettingsRecord(details.getUsername());
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        selectWebElement(firstnameTextBox);
        enterValueToTxtField(firstnameTextBox,details.getFirstname());
        selectWebElement(lastnameTextBox);
        enterValueToTxtField(lastnameTextBox,details.getLastname());
        selectWebElement(teamnameDropdown);
        selectDropdownFromVisibleText(teamNameListBox,details.getTeamName());
        //selectProfile(details.getProfile(),details.getSupervisor());
        selectWebElement(accessroleDropdown);
        selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());
        selectWebElement(featuresDropdown);
        selectDropdownFromVisibleText(featuresListBox,details.getFeatures());
        selectWebElement(crmnameDropdown);
        selectDropdownFromVisibleText(crmnameListBox,details.getCrmName());
        // selectWebElement(texttemplatenameDropdown);
        // selectDropdownFromVisibleText(texttemplatenameListBox,details.getTextTemplateName());
        selectWebElement(numericTextbox.get(1));
        enterValueToTxtField(totalTabsAllowedTextBox,String.valueOf(details.getTotalTabsAllowed()));
        selectWebElement(numericTextbox.get(2));
        enterValueToTxtField(totalVoiceTabsAllowedTextBox,String.valueOf(details.getTotalVoiceTabs()));
        selectWebElement(numericTextbox.get(3));
        enterValueToTxtField(totalChatTabsAllowedTextBox,String.valueOf(details.getTotalChatTabs()));
        selectWebElement(numericTextbox.get(4));
        enterValueToTxtField(totalEmailTabsAllowedTextBox,String.valueOf(details.getTotalEmailTabs()));
        selectFeaturesToBeSelected(details.getFeaturestobeSeleted());
        selectCheckBox(goToAcwAfterEachAcdCallsCheckbox,details.isGotoACWaftereachACDcalls());
        selectCheckBox(autoAnswerAllAcdCallsCheckbox,details.isAutoanswerallACDcalls());
        selectCheckBox(goToAcwAfterAnyCallsCheckbox,details.isGotoACWafteranycalls());
        selectCheckBox(crmEnabledCheckbox,details.iscRMEnabled());
        selectCheckBox(holdVoiceCallOnChatCallCheckbox,details.isHoldVoiceCallOnChatCall());
        selectCheckBox(secondTextChatAutoAnswerCheckbox,details.isSecondTextChatAutoAnswer());
        selectCheckBox(textChatAutoACWCheckbox,details.isTextChatAutoACWEnabled());
        selectCheckBox(textChatAutoAnswerCheckbox,details.isTextChatAutoAnswer());
        enterValueToTxtField(modifyReasonTextBox,details.getModifyReason());
        selectWebElement(saveBtn);
    }
    public void deleteAgentSettingsRecord(String username,String reason) {
        searchAgentSettingsRecord(username);
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
}
