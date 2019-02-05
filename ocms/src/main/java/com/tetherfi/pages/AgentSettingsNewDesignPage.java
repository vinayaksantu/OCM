package com.tetherfi.pages;

import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.model.user.UserRoleMappingDetails;
import com.tetherfi.utility.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AgentSettingsNewDesignPage extends BasePage {

    public AgentSettingsNewDesignPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement agentSettings;

    @FindBy(css="#checkerGrid th[data-role='columnsorter']")
    private List<WebElement> approvedDataTableHeaders;

    @FindBy(css="#tGrid th[data-role='columnsorter']")
    private List<WebElement> auditTrailTableHeaders;

    @FindBy(css="#drillGrid th[data-role='columnsorter']")
    private List<WebElement> makerTableHeaders;

    @FindBy(id="makeChanges")
    private WebElement makeAgentSettingsChanges;

    @FindBy(id="create")
    private WebElement addNewAgentSettingsRecordBtn;

    @FindBy(id="export")
    private WebElement exportToExcelBtn;

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

    @FindBy(css="span[class^='k-widget k-dropdowntree k-dropdowntree-clearable']")
    private WebElement teamnameDropdown;

    @FindBy(css=".k-treeview-lines li div")
    private List<WebElement> teamList;

    @FindBy(css="ul[id='TeamID_listbox'] li")
    private List<WebElement> teamNameListBox;

    @FindBy(css="span[aria-owns='Profile_listbox']")
    private WebElement profileDropdown;

    @FindBy(css="ul[id='Profile_listbox'] li")
    private List<WebElement> profileListBox;

    @FindBy(css="span[aria-owns='SupervisorID_listbox']")
    private WebElement supervisorDropdown;

    @FindBy(css="ul[id='SupervisorID_listbox'] li")
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

    @FindBy(css="input[title='Total Audio Chat Tabs Allowed']")
    private List<WebElement> totalAudioChatTabsAllowedTextBox;

    @FindBy(css="input[title='Total Video Chat Tabs Allowed']")
    private List<WebElement> totalVideoChatTabsAllowedTextBox;

    @FindBy(css="input[title='Total Voice Tabs Allowed']")
    private List<WebElement> totalVoiceTabsAllowedTextBox;

    @FindBy(css="input[title='Total Text Chat Tabs Allowed']")
    private List<WebElement> totalChatTabsAllowedTextBox;

    @FindBy(css="input[title='Total Email Tabs Allowed']")
    private List<WebElement> totalEmailTabsAllowedTextBox;

    @FindBy(css="input[title='Total Fax Tabs Allowed']")
    private List<WebElement> totalFaxTabsAllowedTextBox;

    @FindBy(css="input[title='Total SMS Tabs Allowed']")
    private List<WebElement> totalSMSTabsAllowedTextBox;

    @FindBy(id="voice0")
    private WebElement voiceCheckbox;

    @FindBy(id="textchat1")
    private WebElement textChatCheckbox;

    @FindBy(id="audiochat2")
    private WebElement audiochatCheckbox;

    @FindBy(id="videochat3")
    private WebElement videoChatCheckbox;

    @FindBy(id="fax4")
    private WebElement faxCheckbox;

    @FindBy(id="email5")
    private WebElement mailCheckbox;

    @FindBy(id="sms6")
    private WebElement smsCheckbox;

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

    @FindBy(css="#gridDiv2 .search-link")
    private WebElement searchLink;

    @FindBy(css="#gridDiv .search-link")
    private WebElement gridsearchLink;

    @FindBy(css=".modal-footer .button-danger-theme")
    private WebElement searchClearAllBtn;

    @FindBy(css=".clearSearch-link")
    private List<WebElement> clearSearchLink;

    @FindBy(id="gridDivClose1")
    private WebElement fullScreenLink;

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

    @FindBy(css="#drillGrid .k-grid-content")
    private WebElement gridContent;

    @FindBy(css="#drillGrid")
    private WebElement gridContent1;

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

    @FindBy(css="#tabstripAgtAgent .k-tabstrip-items li")
    private List<WebElement> tabList;

    @FindBy(id="goToAuditTrail")
    private WebElement goBackBtn;

    @FindBy(id="tgrid")
    private WebElement auditGridContent;

    @FindBy(id="drillGrid")
    private WebElement makerGridContent;

    @FindBy(css="#lanid b")
    private WebElement loggedInUserName;

    @FindBy(id="taskComplete")
    private WebElement taskCompleteBtn;

    @FindBy(id="MakerComments")
    private WebElement makerComments;

    @FindBy(id="submitMakerComment")
    private WebElement taskCompleteBtnAtMakerCommentsPopUp;

    @FindBy(css="ul[class='k-tabstrip-items k-reset'] li")
    private List<WebElement> agentSettingsTabs;

    @FindBy(id="Approve")
    private WebElement approveBtn;

    @FindBy(id="Reject")
    private WebElement rejectBtn;

    @FindBy(id="CheckerReason")
    private WebElement checkerReason;

    @FindBy(id="approveButton")
    private WebElement approveYesBtn;

    @FindBy(id="rejectButton")
    private WebElement RejectnoBtn;

    @FindBy(css=".k-pager-info")
    private List<WebElement> pagerInfo;

    @FindBy(css=".k-pager-sizes .k-input")
    private List<WebElement> pagerSize;

    @FindBy(css="a[aria-label='Go to the first page']")
    private List<WebElement> firstPageIcon;
    @FindBy(css="a[aria-label='Go to the previous page']")
    private List<WebElement> previousPageIcon;
    @FindBy(css=".k-pager-numbers .k-state-selected")
    private List<WebElement> pageNumber;
    @FindBy(css="a[aria-label='Go to the next page']")
    private List<WebElement> nextPageIcon;
    @FindBy(css="a[aria-label='Go to the last page']")
    private List<WebElement> lastPageIcon;
    @FindBy(css=".k-pager-sizes .k-icon")
    private List<WebElement> pagerDropdown;
    @FindBy(css=".k-animation-container ul li")
    private List<WebElement> pageSizeListBox;

    @FindBy(css = ".k-input[aria-owns='1001sColumnName_listbox']")
    private WebElement searchSelectColDropdwn;

    @FindBy(css = "span[aria-owns='1001sCriteria_listbox']")
    private WebElement searchCriteriaColDropdwn;

    @FindBy(id = "1001sTextToSearch")
    private WebElement searchTextBox;

    @FindBy(css = ".fa-expand")
    private List<WebElement> fullScrnBtn;

    @FindBy(css = ".fa-compress")
    private WebElement minimiseScrnBtn;

    @FindBy(css = ".k-grid-excel")
    private WebElement exportBtn;

    @FindBy(css="#drillGrid .k-i-expand")
    private List<WebElement> expandRecord;

    @FindBy(css="div[id^='tabStrip_5'] .k-tabstrip-items li")
    private List<WebElement> expandTabs;

    @FindBy(css="div[id^='grid_5']")
    private WebElement channelsDetails;

    @FindBy(css=".agentfeature ul li")
    private List<WebElement> features;

    @FindBy(id = "drillGrid")
    private WebElement grid;

    @FindBy(css = "#drillGrid .k-grouping-header")
    private WebElement dragColumnDestination;

    @FindBy(css="#drillGrid th a[class='k-header-column-menu']")
    private List<WebElement> headersDropdown;

    @FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
    private List<WebElement> headersColumns;

    @FindBy(css="#drillGrid th a[class='k-link']")
    private List<WebElement> headersText;

    public boolean isAgentSettingsPageDisplayed() throws InterruptedException {
        waitForLoad(driver);
        //waitForJqueryLoad(driver);
        return agentSettings.isEnabled();
    }
    public String getHeaderText(){
        return agentSettings.getText();
    }
    public boolean verifySearchLink(){
        return (isWebElementDisplayed(gridsearchLink)&& isWebElementEnabled(gridsearchLink));
    }
    public boolean verifyClearSearchLink(){
        return (isWebElementDisplayed(clearSearchLink.get(0))&& isWebElementEnabled(clearSearchLink.get(0)));
    }
    public boolean verifyFullScreenLink(){
        return (isWebElementDisplayed(fullScreenLink)&& isWebElementEnabled(fullScreenLink));
    }
    private ArrayList getHeadersfromTable(List<WebElement> e){
        ArrayList header=new ArrayList();
        for(int j=0;j<e.size();j++){
            scrollToElement(e.get(j));
            if(!e.get(j).getText().equals("")){header.add(e.get(j).getText());}
        }
        return header;
    }
    public boolean verifyApprovedDataTableHeaders() {
        ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("Lan ID","Avaya Login ID","First Name","Last Name","Profile","Supervisor Name","Team Name","Access Role","CRM Name","Text Chat Greeting Template Name","Last Changed On","Last Changed By"));
        ArrayList Actual = getHeadersfromTable(approvedDataTableHeaders);
        Collections.sort(Expected);Collections.sort(Actual);
        return Actual.equals(Expected);
    }
    public boolean verifyAgentSettingsTabsDisplayed(){
        return agentSettingsTabs.get(0).isDisplayed()&&agentSettingsTabs.get(1).isDisplayed();
    }
    public boolean verifyAuditTrailDataTableHeaders() {
        ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("Request Id", "Transaction", "Function", "Status", "User Id", "Submission DateTime", "Maker Comments", "Old Values", "New Values", "Review DateTime", "Checker Comments"));
        ArrayList Actual = getHeadersfromTable(auditTrailTableHeaders);
        Collections.sort(Expected);Collections.sort(Actual);
        return Actual.equals(Expected);
    }
    public boolean verifyAddNewAgentSettingsRecordButton(){
        return addNewAgentSettingsRecordBtn.isEnabled();
    }
    public boolean verifyGoBackButton(){
        return goBackBtn.isEnabled();
    }
    public boolean verifyExportToExcelButton(){
        return exportToExcelBtn.isEnabled();
    }
    public boolean verifyMakerDataTableHeaders() {
        ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("Lan ID","Avaya Login ID","First Name","Last Name","Profile","Supervisor Name","Team Name","Access Role","CRM Name","Text Chat Greeting Template Name","Last Changed On","Last Changed By"));
        ArrayList Actual = getHeadersfromTable(makerTableHeaders);
        Collections.sort(Expected);Collections.sort(Actual);
        return Actual.equals(Expected);
    }
    public void selectAddNewAgentSettings(){
    selectWebElement(addNewAgentSettingsRecordBtn);
    }
    public boolean verifyAddNewPopupContents(){
        return popupContent.isDisplayed()&&saveBtn.isEnabled()&&cancelBtn.isEnabled();
    }
    public void navigateToTab(String tabname){
        waitUntilWebElementListIsVisible(tabList);
        waitUntilWebElementListIsClickable(tabList);
        for(WebElement ele:tabList){
            if(ele.getText().equalsIgnoreCase(tabname)){ele.click();break;}
        }
    }
    public void closePopUpIfOpen(){
        if(popupContent.isEnabled()){clickOn(cancelBtn);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void selectAgentSettingsAuditTrailTab(){
        selectWebElement(agentSettingsTabs.get(1));
        //waitForJqueryLoad(driver);
    }
    public void selectMakeAgentSettingsChanges(){
        selectWebElement(makeAgentSettingsChanges);
        //waitForJqueryLoad(driver);
    }
    public void addNewAgentSettingsRecord(AgentSettingsDetails details) {
        selectWebElement(agentSettingsTabs.get(1));
        selectWebElement(makeAgentSettingsChanges);
        //waitForJqueryLoad(driver);
        try {Thread.sleep(5000);
        selectWebElement(addNewAgentSettingsRecordBtn);
        waitUntilWebElementIsVisible(popupContent);

            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        navigateToTab("Info");
        selectWebElement(usernameTextBox);
        enterValueToTxtFieldWithoutClear(usernameTextBox,details.getUsername());
        selectWebElement(numericTextbox.get(0));
        enterValueToTxtFieldWithoutClear(avayaLoginIdTextBox,details.getAvayaLoginID());
        selectWebElement(firstnameTextBox);
        enterValueToTxtFieldWithoutClear(firstnameTextBox,details.getFirstname());
        selectWebElement(lastnameTextBox);
        enterValueToTxtFieldWithoutClear(lastnameTextBox,details.getLastname());
        selectWebElement(teamnameDropdown);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //selectDropdownFromVisibleText(teamNameListBox,details.getTeamName());
        ChooseTeamHeirarchy(details.getTeamName());
        selectProfile(details.getProfile(),details.getSupervisor());
        selectWebElement(accessroleDropdown);
        selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());
        selectWebElement(crmnameDropdown);
        selectDropdownFromVisibleText(crmnameListBox,details.getCrmName());
        // selectWebElement(texttemplatenameDropdown);
        // selectDropdownFromVisibleText(texttemplatenameListBox,details.getTextTemplateName());
        navigateToTab("Settings");
        selectFeaturesToBeSelected(details.getFeaturestobeSeleted());
        selectWebElement(numericTextbox.get(1));
        enterValueToTxtField(totalVoiceTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVoiceTabs()));
        selectWebElement(numericTextbox.get(2));
        enterValueToTxtField(totalChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalChatTabs()));
        selectWebElement(numericTextbox.get(3));
        enterValueToTxtField(totalAudioChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalAudioChatTabs()));
        selectWebElement(numericTextbox.get(4));
        enterValueToTxtField(totalVideoChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVideoChatTabs()));
        selectWebElement(numericTextbox.get(5));
        enterValueToTxtField(totalFaxTabsAllowedTextBox.get(1),String.valueOf(details.getTotalFaxTabs()));
        selectWebElement(numericTextbox.get(6));
        enterValueToTxtField(totalEmailTabsAllowedTextBox.get(1),String.valueOf(details.getTotalEmailTabs()));
        selectWebElement(numericTextbox.get(7));
        enterValueToTxtField(totalSMSTabsAllowedTextBox.get(1),String.valueOf(details.getTotalSMSTabs()));
        selectWebElement(featuresDropdown);
        selectDropdownFromVisibleText(featuresListBox,details.getFeatures());
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
    private void ChooseTeamHeirarchy(String team){
        String[] hrcy=team.split(">");
        for(int i=0;i<hrcy.length;i++){
            for(WebElement e: teamList){
                if(e.getText().equals(hrcy[i]))
                    if(e.findElements(By.className("k-icon")).size()>0)
                    {selectWebElement(e.findElement(By.className("k-icon")));break;}
                    else
                    {selectWebElement(e.findElement(By.className("k-in")));break;}   }}
    }
    public boolean verifyNewRecordCreated(){
        //waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        return(waitUntilTextToBePresentInWebElement(successmsg,"Record Created Successfully"));
    }
    public void selectFeaturesToBeSelected(String[] features){
        for(String feature:features){
            if(feature.equalsIgnoreCase("Voice")&&!voiceCheckbox.isSelected()){selectCheckbox(voiceCheckbox);}
            else if(feature.equalsIgnoreCase("Email")&&!mailCheckbox.isSelected()){selectCheckbox(mailCheckbox);}
            else if(feature.equalsIgnoreCase("SMS")&&!smsCheckbox.isSelected()){selectCheckbox(smsCheckbox);}
            else if(feature.equalsIgnoreCase("TextChat")&&!textChatCheckbox.isSelected()){selectCheckbox(textChatCheckbox);}
            else if(feature.equalsIgnoreCase("VideoChat")&&!videoChatCheckbox.isSelected()){selectCheckbox(videoChatCheckbox);}
            else if(feature.equalsIgnoreCase("AudioChat")&&!audiochatCheckbox.isSelected()){selectCheckbox(audiochatCheckbox);}
            else if(feature.equalsIgnoreCase("Fax")&&!faxCheckbox.isSelected()){selectCheckbox(faxCheckbox);}
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
            selectDropdownFromVisibleText(supervisorListBox,supervisor);
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
        selectDropdownFromVisibleText(columnNameList,"Lan ID");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),name);
        selectWebElement(searchBtn);
        waitUntilLoadingImageDisapper(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public void editAgentSettingsRecord(AgentSettingsDetails details) {
        try{selectWebElement(agentSettingsTabs.get(1));
        Thread.sleep(3000);
        selectWebElement(makeAgentSettingsChanges);
        Thread.sleep(3000);
        searchAgentSettingsRecord(details.getUsername());
        Thread.sleep(3000);
        selectWebElement(editBtn);
        selectWebElement(firstnameTextBox);
        enterValueToTxtField(firstnameTextBox,details.getFirstname());
        selectWebElement(lastnameTextBox);
        enterValueToTxtField(lastnameTextBox,details.getLastname());
        selectProfile(details.getProfile(),details.getSupervisor());
        /*selectWebElement(teamnameDropdown);
        try{Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ChooseTeamHeirarchy(details.getTeamName());*/
        //selectDropdownFromVisibleText(teamNameListBox,details.getTeamName());
        selectWebElement(accessroleDropdown);
        selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());
        selectWebElement(crmnameDropdown);
        selectDropdownFromVisibleText(crmnameListBox,details.getCrmName());
        // selectWebElement(texttemplatenameDropdown);
        // selectDropdownFromVisibleText(texttemplatenameListBox,details.getTextTemplateName());
        navigateToTab("Settings");
        selectFeaturesToBeSelected(details.getFeaturestobeSeleted());
        selectWebElement(numericTextbox.get(1));
        enterValueToTxtField(totalVoiceTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVoiceTabs()));
        selectWebElement(numericTextbox.get(2));
        enterValueToTxtField(totalChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalChatTabs()));
        selectWebElement(numericTextbox.get(3));
        enterValueToTxtField(totalAudioChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalAudioChatTabs()));
        selectWebElement(numericTextbox.get(4));
        enterValueToTxtField(totalVideoChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVideoChatTabs()));
        selectWebElement(numericTextbox.get(5));
        enterValueToTxtField(totalFaxTabsAllowedTextBox.get(1),String.valueOf(details.getTotalFaxTabs()));
        selectWebElement(numericTextbox.get(6));
        enterValueToTxtField(totalEmailTabsAllowedTextBox.get(1),String.valueOf(details.getTotalEmailTabs()));
        selectWebElement(numericTextbox.get(7));
        enterValueToTxtField(totalSMSTabsAllowedTextBox.get(1),String.valueOf(details.getTotalSMSTabs()));
        selectWebElement(featuresDropdown);
        selectDropdownFromVisibleText(featuresListBox,details.getFeatures());
        selectCheckBox(goToAcwAfterEachAcdCallsCheckbox,details.isGotoACWaftereachACDcalls());
        selectCheckBox(autoAnswerAllAcdCallsCheckbox,details.isAutoanswerallACDcalls());
        selectCheckBox(goToAcwAfterAnyCallsCheckbox,details.isGotoACWafteranycalls());
        selectCheckBox(crmEnabledCheckbox,details.iscRMEnabled());
        selectCheckBox(holdVoiceCallOnChatCallCheckbox,details.isHoldVoiceCallOnChatCall());
        selectCheckBox(secondTextChatAutoAnswerCheckbox,details.isSecondTextChatAutoAnswer());
        selectCheckBox(textChatAutoACWCheckbox,details.isTextChatAutoACWEnabled());
        selectCheckBox(textChatAutoAnswerCheckbox,details.isTextChatAutoAnswer());
        enterValueToTxtField(modifyReasonTextBox,details.getModifyReason());
        selectWebElement(saveBtn);}catch (Exception e){e.printStackTrace();}
    }
    public void deleteAgentSettingsRecord(String username,String reason) {
        selectWebElement(agentSettingsTabs.get(1));
        selectWebElement(makeAgentSettingsChanges);
        searchAgentSettingsRecord(username);
        btnClick(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtField(deleteReasonTextBox,reason);
        selectWebElement(yesBtn);
    }
    public boolean verifyRecordDeleted(){
        //waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        return(waitUntilTextToBePresentInWebElement(successmsg,"Record Deleted Successfully"));
    }
    public boolean verifyRecordUpdated(){
        //waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        return(waitUntilTextToBePresentInWebElement(successmsg,"Record Updated Successfully"));
    }
    public void clickOnGoBackButton(){
        selectWebElement(goBackBtn);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private Map<String,String> getFirstRowDatafromTable(){
        Map<String,String> map = new HashMap<>();
        waitUntilWebElementIsVisible(auditGridContent);
        List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
        List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
        List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
        for(int j=0;j<headers.size();j++){
            scrollToElement(headers.get(j));
            waitForWebElementIgnoringStaleException(cols.get(j));
            waitForWebElementIgnoringStaleException(headers.get(j));
            map.put(headers.get(j).getText(),cols.get(j).getText());
        }
        return map;
    }
    public boolean verifyAuditTrail(AgentSettingsDetails details, String transaction, String status){
        boolean stat=false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(new Date());
        Map<String,String> firstRowData=getFirstRowDatafromTable();
        if(firstRowData.get("Transaction").equalsIgnoreCase(transaction)){
            if(firstRowData.get("Status").equalsIgnoreCase(status)){
                if(firstRowData.get("Function").equalsIgnoreCase("AgentSetting")){
                    if(firstRowData.get("Submission DateTime").contains(date)){
                       // if(transaction.equals("MakerCreate")||transaction.equals("MakerUpdate")){
                           /* Map<String,String> newvalues=new HashMap<>();
                            String[] d=firstRowData.get("New Values").split("\n");
                            for(String e:d){
                                String[]f=e.split(":",2);
                                if(f.length>1){newvalues.put(f[0],f[1]);}
                            }
                            if(verifyNewValues(details,newvalues)){
                                stat=true;}*/
                            stat=true;//}//else{stat=true;}
                    }else{System.out.println("Data mismatch:"+firstRowData.get("Submission DateTime")+"\t"+date);}
                }else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"RoleManagement");}
            }else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+status);}
        }else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+transaction);}
        return stat;}
    public boolean verifyNewValues(AgentSettingsDetails details, Map<String,String> newvalues){
        boolean stat=false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(new Date());
        if(newvalues.get("UserName").equals(details.getUsername())){
            if(newvalues.get("AvayaLoginID").equals(details.getAvayaLoginID())){
               // if(newvalues.get("AccessRole").equals(details.getAccessRole())){
                    if(newvalues.get("FirstName").equals(details.getFirstname())){
                        if(newvalues.get("LastName").equals(details.getLastname())){
                            if(newvalues.get("Profile").equals(details.getProfile())){
                              //  if(newvalues.get("TeamName").equals(details.getTeamName().split(">")[details.getTeamName().split(">").length-1])){
                                //    if(newvalues.get("SupervisorName").equals(details.getSupervisor())){
                                       // if(verifyFeatures(details,newvalues)) {
                                           // if (verifyTabsAllowed(details, newvalues)) {
                                                if (verifyAgentSettingsOtherdetails(details, newvalues)) {
                                                    // if(newvalues.get("LastChangedOn").contains(date)){
                                                    if (newvalues.get("LastChangedBy").equals(loggedInUserName.getText())) {
                                                        stat = true;
                                                    } else {
                                                        System.out.println("data mismatch" + newvalues.get("LastChangedBy") + "\t" + loggedInUserName.getText());
                                                    }
                                                    // }else{System.out.println("data mismatch"+newvalues.get("LastChangedOn")+"\t"+date);}
                                                }
                                           // }
                                        //}
                                  //  }else{System.out.println("data mismatch"+newvalues.get("SupervisorName")+"\t"+details.getSupervisor());}
                              //  }else{System.out.println("data mismatch"+newvalues.get("TeamName")+"\t"+details.getTeamName());}
                            }else{System.out.println("data mismatch"+newvalues.get("Profile")+"\t"+details.getProfile());}
                        }else{System.out.println("data mismatch"+newvalues.get("LastName")+"\t"+details.getLastname());}
                    }else{System.out.println("data mismatch"+newvalues.get("FirstName")+"\t"+details.getFirstname());}
                //}else{System.out.println("data mismatch"+newvalues.get("AccessRole")+"\t"+details.getAccessRole());}
            }else{System.out.println("data mismatch"+newvalues.get("AvayaLoginID")+"\t"+details.getAvayaLoginID());}
        }else{System.out.println("data mismatch"+newvalues.get("UserName")+"\t"+details.getUsername());}
        return stat;
    }
    public boolean verifyFeatures(AgentSettingsDetails details, Map<String,String> newvalues){
        boolean status=false;
        if(newvalues.get("IsVoice").equals("True")&&details.getFeaturestobeSeleted()[0].equals("Voice")){
        if(newvalues.get("IsEmail").equals("True")&&details.getFeaturestobeSeleted()[1].equals("Email")){
        if(newvalues.get("IsSMS").equals("True")&&details.getFeaturestobeSeleted()[2].equals("SMS")){
        if(newvalues.get("IsTextChat").equals("True")&&details.getFeaturestobeSeleted()[3].equals("TextChat")){
        if(newvalues.get("IsVideoChat").equals("True")&&details.getFeaturestobeSeleted()[4].equals("VideoChat")){
            status=true;
        }else{System.out.println("data mismatch"+newvalues.get("IsVideoChat")+"\t"+details.getFeaturestobeSeleted()[4]);}
        }else{System.out.println("data mismatch"+newvalues.get("IsTextChat")+"\t"+details.getFeaturestobeSeleted()[3]);}
        }else{System.out.println("data mismatch"+newvalues.get("IsSMS")+"\t"+details.getFeaturestobeSeleted()[2]);}
        }else{System.out.println("data mismatch"+newvalues.get("IsEmail")+"\t"+details.getFeaturestobeSeleted()[1]);}
        }else{System.out.println("data mismatch"+newvalues.get("IsVoice")+"\t"+details.getFeaturestobeSeleted()[0]);}
return status;
    }
    public boolean verifyTabsAllowed(AgentSettingsDetails details, Map<String,String> newvalues){
        boolean status=false;
        if(newvalues.get("TotalTabsAllowed").equals(details.getTotalTabsAllowed())){
            if(newvalues.get("TotalChatTabsAllowed").equals(details.getTotalChatTabs())){
                if(newvalues.get("TotalVoiceTabsAllowed").equals(details.getTotalVoiceTabs())){
                    if(newvalues.get("TotalEmailTabsAllowed").equals(details.getTotalEmailTabs())){
                            status=true;
                    }else{System.out.println("data mismatch"+newvalues.get("TotalEmailTabsAllowed")+"\t"+details.getTotalEmailTabs());}
                }else{System.out.println("data mismatch"+newvalues.get("TotalVoiceTabsAllowed")+"\t"+details.getTotalVoiceTabs());}
            }else{System.out.println("data mismatch"+newvalues.get("TotalChatTabsAllowed")+"\t"+details.getTotalChatTabs());}
        }else{System.out.println("data mismatch"+newvalues.get("TotalTabsAllowed")+"\t"+details.getTotalTabsAllowed());}
        return status;
    }
    public boolean verifyAgentSettingsOtherdetails(AgentSettingsDetails details, Map<String,String> newvalues){
        boolean status=false;
        if(newvalues.get("IsCrmEnabled").equals("True")&&details.iscRMEnabled()){
            if(newvalues.get("IsVoiceAcdAutoAnswerEnabled").equals("True")&&details.isAutoanswerallACDcalls()){
                if(newvalues.get("IsVoiceAcdAutoAcwEnabled").equals("True")&&details.isGotoACWaftereachACDcalls()){
                    if(newvalues.get("IsVoiceAllAutoAcwEnabled").equals("True")&&details.isGotoACWafteranycalls()){
                        if(newvalues.get("IsTextChatAutoAcwEnabled").equals("True")&&details.isTextChatAutoACWEnabled()){
                            if(newvalues.get("CrmName").equals(details.getCrmName())){
                                if(newvalues.get("IsSecondTextChatAutoAnswer").equals("True")&&details.isSecondTextChatAutoAnswer()){
                                    if(newvalues.get("IsHoldVoiceCallOnChatCall").equals("True")&&details.isHoldVoiceCallOnChatCall()){
                                        if(newvalues.get("IsTextChatAutoAnswer").equals("True")&&details.isTextChatAutoAnswer()){
                                        status=true;
                                        }else{System.out.println("data mismatch"+newvalues.get("IsTextChatAutoAnswer")+"\t"+details.isTextChatAutoAnswer());}
                                    }else{System.out.println("data mismatch"+newvalues.get("IsHoldVoiceCallOnChatCall")+"\t"+details.isHoldVoiceCallOnChatCall());}
                                }else{System.out.println("data mismatch"+newvalues.get("IsSecondTextChatAutoAnswer")+"\t"+details.isSecondTextChatAutoAnswer());}
                            }else{System.out.println("data mismatch"+newvalues.get("CrmName")+"\t"+details.getCrmName());}
                        }else{System.out.println("data mismatch"+newvalues.get("IsTextChatAutoAcwEnabled")+"\t"+details.isTextChatAutoACWEnabled());}
                    }else{System.out.println("data mismatch"+newvalues.get("IsVoiceAllAutoAcwEnabled")+"\t"+details.isGotoACWafteranycalls());}
                }else{System.out.println("data mismatch"+newvalues.get("IsVoiceAcdAutoAcwEnabled")+"\t"+details.isGotoACWaftereachACDcalls());}
            }else{System.out.println("data mismatch"+newvalues.get("IsVoiceAcdAutoAnswerEnabled")+"\t"+details.isGotoACWaftereachACDcalls());}
        }else{System.out.println("data mismatch"+newvalues.get("IsCrmEnabled")+"\t"+details.iscRMEnabled());}
        return status;
    }
    public void taskCompleteAction(String comment){
        selectWebElement(makeAgentSettingsChanges);
        waitForLoad(driver);
        selectWebElement(taskCompleteBtn);
        enterValueToTxtField(makerComments,comment);
        selectWebElement(taskCompleteBtnAtMakerCommentsPopUp);
    }
    public boolean verifyTaskCompleteSuccessMessage(){
        return(verifySuccessMessage().contains("Record submission for approval success. Your Request ID is :"));
    }
    public String verifySuccessMessage(){
        if(errorMsg.size()>0){return errorMsg.get(0).getText();}
        else { waitUntilWebElementIsVisible(successmsg);return successmsg.getText();}
    }
    public boolean verifyStatus(String status){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String,String> firstRowData=getFirstRowDatafromTable();
        return firstRowData.get("Status").equals(status);
    }
    public void clickonApprove(String comment){
        selectWebElement(agentSettingsTabs.get(1));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickOn(approveBtn);
        selectWebElement(checkerReason);
        enterValueToTxtField(checkerReason,comment);
        clickOn(approveYesBtn);
    }
    public boolean verifyReviewAuditTrail(String status,String comment){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean stat=false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(new Date());
        Map<String,String> firstRowData=getFirstRowDatafromTable();
        if(firstRowData.get("Status").equals(status)){
            if(firstRowData.get("Checker Comments").equals(comment)){
                if (firstRowData.get("Review DateTime").contains(date)){
                    stat=true;
                }else{System.out.println("Data mismatch:"+firstRowData.get("Review DateTime")+"\t"+date);}
            }else{System.out.println("Data mismatch:"+firstRowData.get("Review Comments")+"\t"+comment);}
        }else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+status);}
        return stat;
    }
    public boolean verifyTaskCompleteEnabled(){
        return taskCompleteBtn.isEnabled();
    }
    private List<Map<String, String>> getAllDatafromTable(int uitable){
        int items=Integer.valueOf(pagerInfo.get(uitable).getText().split("of ")[1].split(" items")[0]);
        int pagersize=Integer.valueOf(pagerSize.get(uitable).getText());
        int pages=(items%pagersize==0)?items/pagersize-1:items/pagersize;
        List<Map<String, String>> maplist = new ArrayList<Map<String, String>>();
        for(int i=0;i<=pages;i++){
            List<WebElement> rows=gridContent1.findElements(By.tagName("tr"));
            waitForWebElementIgnoringStaleException(rows.get(0));
            List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
            for(int j=1;j<=rows.size()-1;j++){
                List<WebElement> cols=rows.get(j).findElements(By.tagName("td"));
                Map<String,String> map=new HashMap<>();
                for(int k=0; k<cols.size();k++){
                    map.put(headers.get(k).getText(),cols.get(k).getText());
                }
                maplist.add(map);
            }
            if(i<pages){nextPageIcon.get(uitable).click();
            waitForJqueryLoad(driver);}
        }
        return maplist;
    }
    public boolean verifySearcedContentsDisplayed(String val){
    boolean status=false;
    List<Map<String, String>> record=getAllDatafromTable(2);
    for(Map<String, String> map:record){
    for(String e:map.keySet()){
        if(e.equals("Lan ID")&&map.get(e).equals(val)){status=true;break;}
    }if(!status){break;}}
    return status;
    }
    public boolean verifyClearAllFunctionality(){
        boolean clear=false;
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Lan ID");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),"Testing");
        selectWebElement(searchClearAllBtn);
        waitForJqueryLoad(driver);
        if ((searchSelectColDropdwn.getAttribute("value")).equals("") &&
                (searchCriteriaColDropdwn.getText()).equals("Is equal to") &&
                (searchTextBox.getAttribute("value")).equals("")) {
            clear = true;
        }
        return clear;
    }
    public boolean verifyClearSearchFunctionality(AgentSettingsDetails details){
        int items=Integer.valueOf(pagerInfo.get(2).getText().split("of ")[1].split(" items")[0]);
        try{searchAgentSettingsRecord(details.getUsername());
        selectWebElement(clearSearchLink.get(1));
        Thread.sleep(5000);
        }catch(Exception e){e.printStackTrace();}
        int items1=Integer.valueOf(pagerInfo.get(2).getText().split("of ")[1].split(" items")[0]);
        return items==items1;
    }
    public boolean verifyClearSearchNegative(){
        waitUntilLoadingImageDisapper(driver);
        int items=Integer.valueOf(pagerInfo.get(2).getText().split("of ")[1].split(" items")[0]);
        selectWebElement(clearSearchLink.get(1));
        int items1=Integer.valueOf(pagerInfo.get(2).getText().split("of ")[1].split(" items")[0]);
        return items==items1;
    }
    public boolean verifyPageFullScrnd() {
        selectWebElement(fullScrnBtn.get(1));
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(minimiseScrnBtn);
        return (isWebElementDisplayed(minimiseScrnBtn) && isWebElementEnabled(minimiseScrnBtn));
    }
    public void clickonExportToExcelBtn() {
        emptyDownloadsDirectory(System.getProperty("user.dir") + "\\src\\test\\resources\\DownloadedFiles");
        selectWebElement(exportBtn);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private Map<String,String> getFirstRowDatafromTable1(){
        Map<String,String> map = new HashMap<>();
        waitUntilWebElementIsVisible(makerGridContent);
        List<WebElement> rows=makerGridContent.findElements(By.tagName("tr"));
        List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
        List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
        for(int j=0;j<headers.size();j++){
            scrollToElement(headers.get(j));
            map.put(headers.get(j).getText(),cols.get(j).getText());
        }
        return map;
    }
    public boolean verifyExportToExcel() {
        boolean status=false;
        try{clickonExportToExcelBtn();
        if(verifyFilePresentInFolder(System.getProperty("user.dir") + "\\src\\test\\resources\\DownloadedFiles", "Agent Settings")){

        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\DownloadedFiles\\Agent Settings.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Sheet1").getTestData().get(0);
        Map<String, String> map1= new ExcelReader(filePath, "Sheet1").getAgentSettingsRowData(3,9);
        map.putAll(map1);
        map.remove(null);
        Map<String,String> uidata=getFirstRowDatafromTable1();
        selectWebElement(expandRecord.get(0));
        List<WebElement> rows=channelsDetails.findElements(By.tagName("tr"));
        for(int i=1;i<rows.size();i++) {
        List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
        uidata.put(cols.get(0).getText(),cols.get(1).getText()+":"+cols.get(2).getText()+":"+cols.get(3).getText());
        }
        selectWebElement(expandTabs.get(1));
        Thread.sleep(3000);
        for(WebElement s: features){
            String a[] = s.getText().split(":");
            uidata.put(a[0],a[1]);
        }uidata.remove("");uidata.remove(" ");
        if(map.size()==(uidata.size())){status=true;}
        }}catch(Exception e){e.printStackTrace();}
        return status;
    }
    public void dragColumntoGroup(String columnname) {
        List<WebElement> rows = grid.findElements(By.tagName("tr"));
        List<WebElement> columnHeaders = rows.get(0).findElements(By.tagName("th"));
        for (WebElement ele : columnHeaders) {
            if (ele.getText().equals(columnname)) {
                Actions builder = new Actions(driver);
                Action dragAndDrop = builder.clickAndHold(ele).moveToElement(dragColumnDestination).release(dragColumnDestination).build();
                dragAndDrop.perform();
            }
        }
    }

    public boolean verifyDragColumntoGroup(String colname) {

        return (dragColumnDestination.getText().equals(colname));
    }
    public boolean verifyDropDownOfAllHeaders() {
        boolean status = false;
        try {for (WebElement ele : headersDropdown) {
            scrollToElement(ele);
            status = false;
            if (!ele.isDisplayed()) {
                continue;
            } else {
                selectWebElement(ele);
                    Thread.sleep(1000);
                if (headersColumns.get(0).getText().equals("Sort Ascending")) {
                    if (headersColumns.get(1).getText().equals("Sort Descending")) {
                        if (headersColumns.get(2).getText().equals("Columns")) {
                            status = true;selectWebElement(ele);
                        }
                    }
                }
                if (status) {
                } else {
                    break;
                }
            }
        }} catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    public boolean verifycolumnsHeaderEnabled(){
        boolean status=false;
        WebElement ele= headersDropdown.get(0);
            if(ele.isDisplayed()){
                try {
                    selectWebElement(ele);
                    Thread.sleep(1000);
                    selectWebElement(headersColumns.get(2));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 3; i <headersColumns.size(); i++) {
                    WebElement checkbox = headersColumns.get(i).findElement(By.tagName("input"));
                    checkbox.click();
                    if (checkbox.isSelected()) {
                    } else {
                        checkbox.click();
                    }
                    for (WebElement ele1 : headersText) {
                        if (ele1.getText().equals(headersColumns.get(i).getText())) {
                            status = true;
                            break;
                        }
                    }
                    if (status) {
                    } else {
                        break;
                    }
                }
            }
        return status;
    }
    public boolean verifycolumnsHeaderDisbaled() {
        boolean status = false;
        WebElement ele = headersDropdown.get(0);
            if (ele.isDisplayed()) {
                try {
                    selectWebElement(ele);
                    Thread.sleep(1000);
                    selectWebElement(headersColumns.get(2));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 3; i < headersColumns.size(); i++) {
                    // System.out.println(headersColumns.get(i).getText());
                    WebElement checkbox = headersColumns.get(i).findElement(By.tagName("input"));
                    if (checkbox.isSelected()) {
                        checkbox.click();
                    } else {
                    }
                    for (WebElement ele1 : headersText) {
                        if (ele1.getText().equals(headersColumns.get(i).getText())) {
                            status = true;
                            break;
                        }
                    }
                    if (!status) {
                        checkbox.click();
                    } else {
                        break;
                    }
                }

            }
        return status;
    }
    public boolean verifyArrowMoveForPreviousAndNextPage(int i){
        boolean status=false;
        if(!nextPageIcon.get(i).getAttribute("class").contains("k-state-disabled")){
        int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
        selectWebElement(nextPageIcon.get(i));
        int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
        selectWebElement(previousPageIcon.get(i));
        int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
        if(nextnumber==(pagenumber+1) && pagenumber==previousnumber){status=true;}
        }else{
            System.out.println("previous and next page icon disabled");status=true;
        }
        return status;
    }
    public boolean verifyArrowMoveForFirstAndLastPage(int i){
        boolean status=false;
        if(!lastPageIcon.get(i).getAttribute("class").contains("k-state-disabled")){
            int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
            selectWebElement(lastPageIcon.get(i));
            int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
            selectWebElement(firstPageIcon.get(i));
            int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
            if(nextnumber>pagenumber && pagenumber==previousnumber){status=true;}
        }else{
            System.out.println("previous and next page icon disabled");status=true;
        }
        return status;
    }
    public boolean verifyNumberOfItemsPerPage(int z) {
        boolean status = false;
        try {
          //  if (norecords.size() <= 0) {
                int items = Integer.valueOf(pagerInfo.get(z).getText().split("of ")[1].split(" items")[0]);
                selectWebElement(pagerDropdown.get(z));
                Thread.sleep(1500);
                for (int i = 0; i < pageSizeListBox.size(); i++) {
                    if(Integer.valueOf(pageSizeListBox.get(i).getText())>items){continue;}
                    selectDropdownFromVisibleText(pageSizeListBox, pageSizeListBox.get(i).getText());
                    waitForJqueryLoad(driver);
                    int totalItems = Integer.valueOf(pagerInfo.get(z).getText().split("of ")[1].split(" items")[0]);
                    int pagersize = Integer.valueOf(pagerSize.get(z).getText());
                    int pages = (totalItems % pagersize == 0) ? items / pagersize : items / pagersize+1;
                    int totalRows=(gridContent.findElements(By.tagName("tr")).size());
                    selectWebElement(lastPageIcon.get(z));
                    waitForJqueryLoad(driver);
                    int lastPageNumber = Integer.valueOf(pageNumber.get(z).getText());
                    if (items == totalItems && pages == lastPageNumber&&totalRows==pagersize) {
                        status = true;
                    } else {System.out.println(items+":"+totalItems+"\t"+pages+":"+lastPageNumber+"\t"+totalRows+":"+pagersize);
                        status = false;
                        break;
                    }selectWebElement(pagerDropdown.get(z));Thread.sleep(1500);
                }
           // }
        } catch (Exception e) {
            e.printStackTrace();
        } return status;
    }
    public boolean verifyTotalNumberOfItemsPerPageDetails(int z){
        String items = pagerInfo.get(z).getText();
        return items.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
    }
}
