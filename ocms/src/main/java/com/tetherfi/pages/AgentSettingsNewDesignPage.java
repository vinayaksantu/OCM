package com.tetherfi.pages;

import com.tetherfi.constants.Constants;
import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.model.user.UserOnBoardingDetails;
import com.tetherfi.utility.DatabaseConnector;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.FileUploader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

public class AgentSettingsNewDesignPage extends BasePage {

	public AgentSettingsNewDesignPage(WebDriver driver){super(driver);}

	@FindBy(css=".ibox-title h5")
	private WebElement agentSettings;

	@FindBy(id="tabstripAgentMakerChecker")
	private List<WebElement> makerCheckerTab;

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

	@FindBy(css="#drillGrid tbody tr td")
	private List<WebElement> editrowdata;

	/*@FindBy(id="export")
    private WebElement exportToExcelBtn;*/

	@FindBy(xpath="//div[@id='export']")
	private WebElement exportToExcelBtn;

	@FindBy(css=".k-edit-form-container")
	private WebElement popupContent;

	@FindBy(css=".k-edit-form-container #tgrid label")
	private List<WebElement> labels;

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

	@FindBy(css="input[title='Total Faxout Tabs Allowed']")
	private List<WebElement> totalFaxoutTabsAllowedTextBox;

	@FindBy(css="input[title='Total FaxInernational Tabs Allowed']")
	private List<WebElement> totalFaxInternationalTabsAllowedTextBox;

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

	@FindBy(id="faxout7")
	private WebElement faxoutCheckbox;

	@FindBy(id="faxinternational8")
	private WebElement faxInternationalCheckbox;

	@FindBy(id="IsVoiceACDAutoACWEnabled")
	private WebElement goToAcwAfterEachAcdCallsCheckbox;

	@FindBy(id="IsVoiceACDAutoAnswerEnabled")
	private WebElement autoAnswerAllAcdCallsCheckbox;  //IsVoiceACDAutoAnswerEnabled, IsVoiceAcdAutoAnswerEnabled

	@FindBy(id="IsVoiceAllAutoACWEnabled")
	private WebElement goToAcwAfterAnyCallsCheckbox;

	@FindBy(id="IsCRMEnabled")
	private WebElement crmEnabledCheckbox;

	@FindBy(id="IsHoldVoiceCallOnChatCall")   
	private WebElement holdVoiceCallOnChatCallCheckbox;

	@FindBy(id="IsSecondTextChatAutoAnswer")
	private WebElement secondTextChatAutoAnswerCheckbox;  

	@FindBy(id="IsTextChatAutoACWEnabled")
	private WebElement textChatAutoACWCheckbox; //IsTextChatAutoACWEnabled

	@FindBy(id="IsTextChatAutoAnswer")
	private WebElement textChatAutoAnswerCheckbox;

	@FindBy(id="createthree")
	private WebElement importUserProfilePicbtn;

	/*@FindBy(css=".k-edit-form-container .k-grid-update")
    private WebElement saveBtn;*/

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-primary k-grid-update']")
	private WebElement saveBtn;

	@FindBy(id="sendForApproval")
	private WebElement sendForApprovalBtn;

	@FindBy(id="undoChanges")
	private WebElement revertBtn;

	@FindBy(id="undoChangesMakerComments")
	private WebElement revertMakerComments;

	@FindBy(id="submitUndoChangesMakerComment")
	private WebElement revertSubmitMakerComments;

	@FindBy(id="submitMakerComment")
	private WebElement submitMakerComments;

	@FindBy(id="MakerComments")
	private WebElement makerComments;

	@FindBy(css=".k-grid-cancel")
	private List<WebElement> cancelBtn;

	@FindBy(css=".toast-message")
	private WebElement successmsg;

	@FindBy(css="#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;
	
	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;

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
	
	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearall;

	@FindBy(css="ul[id='1001sCriteria_listbox'] li")
	private List<WebElement> searchTypeList;

	@FindBy(css=".modal-body .form-inline .form-group .k-textbox")
	private List<WebElement> searchText;

	@FindBy(css="#gridDiv2 .search-link")
	private WebElement searchBtn;
	
	@FindBy(xpath="//button[text()='Search']")
	private WebElement searchSearchBtn;

	@FindBy(css="#drillGrid .k-grid-content")
	private WebElement gridContent;

	@FindBy(css="#drillGrid")
	private WebElement gridContent1;

	@FindBy(css=".k-grid-CustomDelete")
	private WebElement deleteBtn;

	@FindBy(css=".k-grid-CustomDelete")
	private List<WebElement> deleteBtnList;

	@FindBy(css=".form-group #ModifyReason1")
	private  WebElement deleteReasonTextBox;

	/*@FindBy(css=".k-grid-edit")
    private WebElement editBtn;*/

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-edit']")
	private WebElement editBtn;

	@FindBy(css=".k-edit-form-container #ModifyReason")
	private  WebElement modifyReasonTextBox;

	@FindBy(id="yesButton")
	private WebElement yesBtn;

	@FindBy(id="noButton")
	private WebElement noBtn;

	@FindBy(css="#retagnewsupervisorwindow")
	private WebElement retagSupervisorPopup;

	@FindBy(css="#retagnewsupervisorwindow #noButton")
	private WebElement retagSupervisorPopupNoButton;

	@FindBy(css="#tabstripAgtAgent .k-tabstrip-items li")
	private List<WebElement> tabList;

	@FindBy(id="goToAuditTrail")
	private WebElement goBackBtn;

	@FindBy(css="#gridDiv2 #tGrid")
	private WebElement auditGridContent;
	
	@FindBy(id="grid")
	private WebElement auditGrid;

	@FindBy(id="drillGrid")
	private WebElement makerGridContent;

	@FindBy(css="#lanid b")
	private WebElement loggedInUserName;

	@FindBy(id="taskComplete")
	private WebElement taskCompleteBtn;

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
	
	@FindBy(xpath="//button[text()='Close']")
	private WebElement searchClose;
	
	@FindBy(css=".modal-footer .k-primary")
	private WebElement popupSearchBtn;

	@FindBy(xpath="//i[@class='fas fa-sync fa-spin']")
	private List<WebElement> clearsearch;

	@FindBy(css="#tcheckerGrid .k-grid-content")
	private WebElement approvedgridcontent;

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

	@FindBy(id="tdrillgrid")
	private WebElement tgrid;

	@FindBy(css="span[aria-owns='User_listbox']")
	private WebElement UserAttriDropdown;

	@FindBy(css="ul[id='User_listbox'] li")
	private List<WebElement> userListbox;

	@FindBy(xpath="//input[@id='zipFile']")
	private WebElement selectfile;

	@FindBy(id="uploadZipFile")
	private WebElement uploadBtn;

	@FindBy(xpath="//button[normalize-space()='Continue']")
	private WebElement continueBtn;

	@FindBy(xpath="//span[@class='k-file-validation-message k-text-error']")
	private WebElement filetypeerrorMsg;

	@FindBy(xpath="//div[text()='Inserted Records: ']/a[1]")
	private WebElement insertedRecordCount;

	@FindBy(xpath="//div[text()='Invalid Records: ']/a[2]")
	private WebElement invalidRecordCount;
	
	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private List<WebElement> items;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;

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
	public void clickOnSave(){
		selectWebElement(saveBtn);
	}
	public void clickOnCancel(){
		selectWebElement(cancelBtn.get(1));
	}

	public void clickOnCancelAtDelete(){
		//      selectWebElement(retagSupervisorPopupNoButton);
		selectWebElement(noBtn);
	}

	public void clickOnTopmostDeleteButton(){
		selectWebElement(deleteBtnList.get(0));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public boolean verifyCancelButton(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(driver.findElements(By.cssSelector(".k-edit-form-container")).size()>0){return false;}else{return true;}
	}
	public boolean verifyCancelButtonAtDelete(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(retagSupervisorPopupNoButton.isDisplayed()){return false;}else{return true;}
	}						  

	public boolean verifyApprovedDataTableHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("Lan ID","Avaya Login ID","First Name","Last Name","Profile","Supervisor Name","Org. Unit","Access Role","CRM Name","Text Chat Greeting Template Name","Last Changed By","Last Changed On"));
		ArrayList Actual = getHeadersfromTable(approvedDataTableHeaders);
		System.out.println(Actual);
		System.out.println("*******");
		System.out.println(Expected);
		Collections.sort(Expected);
		Collections.sort(Actual);
		return Actual.equals(Expected);
	}


	public boolean verifyAgentSettingsTabsDisplayed(){
		return agentSettingsTabs.get(0).isDisplayed()&&agentSettingsTabs.get(1).isDisplayed();
	}

	public boolean verifyAuditTrailDataTableHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("IsEnabled","Request ID", "Transaction", "Function Name", "Status", "User Id", "Submission DateTime", "Maker Comments","Reviewed By","Review DateTime", "Checker Comments"));
		ArrayList Actual = getHeadersfromTable(auditTrailTableHeaders);
		Collections.sort(Expected);
		Collections.sort(Actual);
		System.out.println(Actual);
		System.out.println(Expected);
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
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("Lan ID","Avaya Login ID","First Name","Last Name","Profile","Supervisor Name","Org. Unit","Access Role","CRM Name","Text Chat Greeting Template Name","Last Changed By","Last Changed On"));
		ArrayList Actual = getHeadersfromTable(makerTableHeaders);
		Collections.sort(Expected);Collections.sort(Actual);
		return Actual.equals(Expected);
	}
	public void selectAddNewAgentSettings(){
		selectWebElement(addNewAgentSettingsRecordBtn);
	}
	public boolean verifyAddNewPopupContents(){
		return popupContent.isDisplayed()&&saveBtn.isEnabled()&&cancelBtn.get(1).isEnabled();
	}
	public void navigateToTab(String tabname){
		waitUntilWebElementListIsVisible(tabList);
		waitUntilWebElementListIsClickable(tabList);
		for(WebElement ele:tabList){
			if(ele.getText().equalsIgnoreCase(tabname)){ele.click();break;}
		}
	}
	public void closePopUpIfOpen(){
		if(popupContent.isEnabled()){clickOn(cancelBtn.get(1));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	public void selectAgentSettingsAuditTrailTab(){
		selectWebElement(agentSettingsTabs.get(1));
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void selectMakeAgentSettingsChanges(){
		selectWebElement(makeAgentSettingsChanges);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//    ----Add from here---
	public void addNewAgentSettingsRecord(AgentSettingsDetails details) throws Exception {
		selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		waitForJqueryLoad(driver);
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
		waitForJqueryLoad(driver);
		selectProfile(details.getProfile(),details.getSupervisor());
		Thread.sleep(1000);
		selectWebElement(accessroleDropdown);
		selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());
		//selectWebElement(crmnameDropdown);
		//selectDropdownFromVisibleText(crmnameListBox,details.getCrmName());
		//        selectWebElement(texttemplatenameDropdown);
		//        selectDropdownFromVisibleText(texttemplatenameListBox,details.getTextTemplateName());
		navigateToTab("Channel Count");
		selectFeaturesToBeSelected(details.getFeaturestobeSeleted());
		selectWebElement(numericTextbox.get(1));
		enterValueToTxtFieldWithoutClear(totalVoiceTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVoiceTabs()));
		selectWebElement(numericTextbox.get(2));
		enterValueToTxtFieldWithoutClear(totalChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalChatTabs()));
		selectWebElement(numericTextbox.get(3));
		enterValueToTxtFieldWithoutClear(totalAudioChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalAudioChatTabs()));
		selectWebElement(numericTextbox.get(4));
		enterValueToTxtFieldWithoutClear(totalVideoChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVideoChatTabs()));
		selectWebElement(numericTextbox.get(5));
		enterValueToTxtFieldWithoutClear(totalFaxTabsAllowedTextBox.get(1),String.valueOf(details.getTotalFaxTabs()));
		/*selectWebElement(numericTextbox.get(6));
		enterValueToTxtFieldWithoutClear(totalEmailTabsAllowedTextBox.get(1),String.valueOf(details.getTotalEmailTabs()));
		selectWebElement(numericTextbox.get(7));
		enterValueToTxtFieldWithoutClear(totalSMSTabsAllowedTextBox.get(1),String.valueOf(details.getTotalSMSTabs()));	*/																							
		navigateToTab("Features");
		//selectDropdownFromVisibleText(featuresListBox,details.getFeatures());
		selectCheckBox(autoAnswerAllAcdCallsCheckbox,details.isAutoanswerallACDcalls());
		selectCheckBox(goToAcwAfterEachAcdCallsCheckbox,details.isGotoACWaftereachACDcalls());
		selectCheckBox(goToAcwAfterAnyCallsCheckbox,details.isGotoACWafteranycalls());
		//selectCheckBox(crmEnabledCheckbox,details.iscRMEnabled());
		selectCheckBox(holdVoiceCallOnChatCallCheckbox,details.isHoldVoiceCallOnChatCall());
		//selectCheckBox(secondTextChatAutoAnswerCheckbox,details.isSecondTextChatAutoAnswer());
		//selectCheckBox(textChatAutoACWCheckbox,details.isTextChatAutoACWEnabled());
		//selectCheckBox(textChatAutoAnswerCheckbox,details.isTextChatAutoAnswer());
		//        selectCheckBox(manualInCheckbox,details.isManualIn());
		//        selectCheckBox(smsOutCheckbox,details.isSMSOut());
		selectWebElement(saveBtn);
	}
	/*private void ChooseTeamHeirarchy(String team){
		String[] hrcy=team.split(">");
		for(int i=0;i<hrcy.length;i++){
			for(WebElement e: teamList){
				if(e.getText().equals(hrcy[i]))
					if(e.findElements(By.className("k-icon")).size()>0)
					{selectWebElement(e.findElement(By.className("k-icon")));break;}
					else
					{selectWebElement(e.findElement(By.className("k-in")));break;}   }}
	}*/
	
	private void ChooseTeamHeirarchy(String team){
		String[] hrcy=team.split(">");
		for(int i=0;i<hrcy.length;i++){
			for(WebElement e: teamList){
				if(e.getText().equals(hrcy[i])) {
					if(e.getText().equals(hrcy[hrcy.length-1]))
					{selectWebElement(e.findElement(By.className("k-in")));break;}
					else if(e.findElements(By.className("k-icon")).size()>0)
					{selectWebElement(e.findElement(By.className("k-icon")));break;}
				}}}

	}
	
	public boolean verifyNewRecordCreated(){
		//waitForJqueryLoad(driver);
		//if(errorMsg.size()>0){return false;}
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
		//   ----- Remove----     

	}
	public void selectCheckBox(WebElement ele, boolean value){
		if(value){
			if(ele.isSelected()){}else{selectCheckbox(ele);}
		}
		else{
			if(ele.isSelected()){selectCheckbox(ele);}
		}
	}
	public void searchAgentSettingsRecord(String name) throws Exception  {
		selectWebElement(searchLink);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Lan ID");
		selectWebElement(selectSearchColumn.get(1));
		Thread.sleep(1000);
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		enterValueToTxtField(searchTextBox,name);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
	}
	public void clickonTopmostEditButton(){
		try {
			Thread.sleep(5000);
			selectWebElement(editBtn);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void editAgentSettingsRecord(AgentSettingsDetails details) {
		try{selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		searchAgentSettingsRecord(details.getUsername());
		Thread.sleep(1000);
		selectWebElement(editBtn);
		selectWebElement(firstnameTextBox);
		enterValueToTxtField(firstnameTextBox,details.getUpdatedFirstname());      
		enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
		selectWebElement(saveBtn);}catch (Exception e){e.printStackTrace();}
	}
	public void deleteAgentSettingsRecord(String username,String reason) {
		try
		{selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		searchAgentSettingsRecord(username);
		Thread.sleep(4000);
		btnClick(deleteBtn);
		selectWebElement(deleteReasonTextBox);
		enterValueToTxtFieldWithoutClear(deleteReasonTextBox,reason);
		selectWebElement(yesBtn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteSupervisorRecordWhenAssignedToAgent(String username) throws Exception {
		selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		searchAgentSettingsRecord(username);
		selectWebElement(deleteBtn);
	}
	public boolean verifyRecordDeleted(){
		//waitForJqueryLoad(driver);
		return(waitUntilTextToBePresentInWebElement(successmsg,"Record Deleted Successfully"));
	}
	public boolean verifyRecordUpdated(){
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
		waitUntilWebElementIsVisible(auditGrid);
		List<WebElement> rows=auditGrid.findElements(By.tagName("tr"));
		List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
		List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
		for(int j=0;j<headers.size();j++){
			scrollToElement(headers.get(j));
			for(int i=0;i<3;i++) {

				try{map.put(headers.get(j).getText(), cols.get(j).getText());break;}catch (Exception e){e.printStackTrace();}
			}
		}

		return map;
	}
		
	public boolean verifyAuditTrail(AgentSettingsDetails details,String transaction, String status) throws Exception{
		boolean stat=false;
		Map<String,String> firstRowData=getFirstRowDatafromTable();
		Map<String,String> popupRowData=getFirstRowDatafromPreviewPopup();
        System.out.println(firstRowData);
        System.out.println(popupRowData);
		if(firstRowData.get("Transaction").equalsIgnoreCase(transaction)){
			if(firstRowData.get("Status").equalsIgnoreCase(status)){
				if(firstRowData.get("Function Name").equalsIgnoreCase("Agent Settings")){
					if(transaction.equals("MakerCreate")||transaction.equals("MakerImport")){
						Map<String,String> newvalues=new HashMap<>();
						String[] d=popupRowData.get("New Values").split("\n");
						for(String e:d){
							String[]f=e.split(":",2);
							if(f.length>1){newvalues.put(f[0],f[1]);}
						}
						if(verifyNewValues(details,newvalues)){
							stat=true;}
						else 
							stat=false;
					}
					else{System.out.println("Data mismatch");}
				}else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"RoleManagement");}
			}else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+status);}
		}else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+transaction);}
		return stat;
	}
	
	public boolean verifyNewValues(AgentSettingsDetails details, Map<String,String> newvalues){
		boolean stat=false;
		if(newvalues.get("Lan ID").equals(details.getUsername())){
			if(newvalues.get("First Name").equals(details.getFirstname())){
				if(newvalues.get("Last Name").equals(details.getLastname())){
					if(newvalues.get("Avaya Login ID").equals(details.getAvayaLoginID())){
						if(newvalues.get("Profile").equals(details.getProfile())){
							stat=true;
						}else{System.out.println("data mismatch"+newvalues.get("Profile")+"\t"+details.getProfile());}
					}else{System.out.println("data mismatch"+newvalues.get("Avaya Login ID")+"\t"+details.getAvayaLoginID());}
				}else{System.out.println("data mismatch"+newvalues.get("Last Name")+"\t"+details.getLastname());}
			}else{System.out.println("data mismatch"+newvalues.get("First Name")+"\t"+details.getFirstname());}
		}else{System.out.println("data mismatch"+newvalues.get("Lan ID")+"\t"+details.getUsername());}
		return stat;
	}
	
	public boolean verifyAuditTrailDelete(AgentSettingsDetails details, String Transaction, String Status) {
		boolean stat=false;
		Map<String,String> firstRowData=getFirstRowDatafromTable();
		if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
			if(firstRowData.get("Status").equalsIgnoreCase(Status)){
				if(firstRowData.get("Function Name").equalsIgnoreCase("Agent Settings")){
					if(Transaction.equals("MakerDelete")){
						/*Map<String,String> newvalues=new HashMap<>();
						String[] d=firstRowData.get("New Values").split("\n");
						for(String e:d){
							String[]f=e.split(":",2);
							if(f.length>1){newvalues.put(f[0],f[1]);}
						}
						if(verifyUpdatedNewValues(details,newvalues)){
							stat=true;}
						else 
							stat=false;*/
						stat=true;
					}
					else{System.out.println("Data mismatch");}
				}else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"Agent Settings");}
			}else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}
		}else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}
		return stat;
	}
	/*public boolean verifyNewValues(AgentSettingsDetails details, Map<String,String> newvalues){
		boolean stat=false;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date = simpleDateFormat.format(new Date());
		if(newvalues.get("Lan ID").equals(details.getUsername())){
			if(newvalues.get("Avaya Login ID").equals(details.getAvayaLoginID())){
				// if(newvalues.get("AccessRole").equals(details.getAccessRole())){
				if(newvalues.get("First Name").equals(details.getFirstname())){
					if(newvalues.get("Last Name").equals(details.getLastname())){
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
	}*/
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
	/*public boolean verifyTabsAllowed(AgentSettingsDetails details, Map<String,String> newvalues){
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
    }*/
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

	public String verifySuccessMessage(){
		//if(errorMsg.size()>0){return errorMsg.get(0).getText();}
		// else { 
		waitUntilWebElementIsVisible(successmsg);return successmsg.getText();
	}

	public boolean verifyStatus(String status){
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Map<String,String> firstRowData=getFirstRowDatafromTable();
		return firstRowData.get("Status").equals(status);
	}
	
	public void clickonApprove(String comment) throws Exception{
		selectWebElement(agentSettingsTabs.get(1));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectRecord();
		clickOn(approveBtn);
		selectWebElement(checkerReason);
		enterValueToTxtField(checkerReason,comment);
		clickOn(approveYesBtn);
	}
	public void clickonReject(String comment) throws Exception{
		selectWebElement(agentSettingsTabs.get(1));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectRecord();
		clickOn(rejectBtn);
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
					for(int z=0;z<3;z++){
						try{map.put(headers.get(k).getText(),cols.get(k).getText());break;}catch (Exception e){e.printStackTrace();}
					}
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
	public boolean clearAll(AgentSettingsDetails details) throws Exception {
		selectWebElement(gridsearchLink);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Lan ID");
		Thread.sleep(1000);
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		enterValueToTxtField(searchTextBox,details.getUsername());
		selectWebElement(clearall);
		if(searchTextBox.isEnabled())
			return true;
		else
			return false;
	}
	
	public boolean verifyclearsearch() {
		selectWebElement(clearsearch.get(0));
		if(approvedgridcontent.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean verifyclose() {
		selectWebElement(searchClose);
		if(approvedgridcontent.isDisplayed())
			return true;
		else
			return false;
	}
	
	public void searchwithoutextsearch(AgentSettingsDetails details) throws Exception {
		selectWebElement(gridsearchLink);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Lan ID");
		Thread.sleep(1000);
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		selectWebElement(popupSearchBtn);	
		selectWebElement(searchClose);
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
	public boolean verifyExportToExcel(String filePath) {
		final File folder = new File(filePath);
		for (final File f : folder.listFiles()) {
			if (f.getName().startsWith("Agent Settings")) {
				f.delete();
			}
		}
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath, "Agent Settings");
		return Status;
	}
	
	public boolean verifyexportToExcelSheet(List<Map<String, String>> maplist) throws Exception {
		waitForJqueryLoad(driver);
		List<Map<String,String>> UI=getdata();
		waitForJqueryLoad(driver);
		System.out.println("This is UI"+UI);
		System.out.println(maplist);
		System.out.println(UI);
		if(UI.equals(maplist))
			return true;
		else
			return false;
	}
	
	private List<Map<String,String>> getdata() throws Exception{
		waitForJqueryLoad(driver);
		int item=Integer.valueOf(items.get(2).getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.get(2).getText());
		int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){
			waitUntilWebElementIsVisible(auditGridContent);
			List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			String col=null;
			for(int i=1;i<rows.size();i++) {
				Map<String,String> map = new HashMap<String,String>();
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				for(int j=3;j<headers.size();j++) {
					scrollToElement(headers.get(j));

					/*if(headers.get(j).getText().equals("Last Changed On")){
						col=cols.get(j).getText().substring(0,10);
					}*/
					col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);

				}
				map.remove("");
				arr.add(map);
			}
			if(k!=pages)
			{
				nextPageIcon.get(2).click();
				Thread.sleep(2000);
				waitForJqueryLoad(driver);}
		}
		return arr;
	}
	
	public boolean ExporttoExcelWithoutData(AgentSettingsDetails details) throws Exception {
		verifySearchAgentSettingsRecord(details.getUsername());
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
			return false;
	}
	
	public boolean verifyApprovedSectionData(AgentSettingsDetails details) throws Exception {
		searchAgentSettingsRecordApprovedData(details.getUsername());
		if(norecords.isDisplayed())
			return true; 
		else
			return false;
	}
	
	private void searchAgentSettingsRecordApprovedData(String LanId) throws Exception {
		selectWebElement(gridsearchLink);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Lan ID");
		Thread.sleep(1000);
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		enterValueToTxtField(searchTextBox,LanId);
		selectWebElement(popupSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(approvedgridcontent);		
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
		try {for(WebElement ele : headersDropdown) {
			scrollToElement(ele);
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
					 System.out.println(headersColumns.get(i).getText());
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
			break;
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean verifyArrowMoveForPreviousAndNextPage(int i){
		boolean status=false;
		if(!nextPageIcon.get(i).getAttribute("class").contains("k-state-disabled")){
			int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
			selectWebElement(nextPageIcon.get(i));
			waitForJqueryLoad(driver);
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
			selectWebElement(previousPageIcon.get(i));
			waitForJqueryLoad(driver);
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
			waitForJqueryLoad(driver);
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
			selectWebElement(firstPageIcon.get(i));
			waitForJqueryLoad(driver);
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
			int item = Integer.valueOf(items.get(z).getText().split("of ")[1].split(" items")[0]);
			selectWebElement(pagerDropdown.get(z));
			Thread.sleep(1500);
			for (int i = 0; i < pageSizeListBox.size(); i++) {
				if(Integer.valueOf(pageSizeListBox.get(i).getText())>item){continue;}
				selectDropdownFromVisibleText(pageSizeListBox, pageSizeListBox.get(i).getText());
				waitForJqueryLoad(driver);
				int totalItems = Integer.valueOf(items.get(z).getText().split("of ")[1].split(" items")[0]);
				int pagersize = Integer.valueOf(pagerSize.get(z).getText());
				int pages = (totalItems % pagersize == 0) ? item / pagersize : item / pagersize+1;
				int totalRows=(gridContent.findElements(By.tagName("tr")).size());
				selectWebElement(lastPageIcon.get(z));
				waitForJqueryLoad(driver);
				int lastPageNumber = Integer.valueOf(pageNumber.get(z).getText());
				if (item == totalItems && pages == lastPageNumber&&totalRows==pagersize) {
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
		String item = items.get(z).getText();
		return item.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
	}
	
	public boolean verifyJsonDataForMakerAndChecker(boolean mkrchk){
		boolean status=false;
		if(mkrchk){
			if(makerCheckerTab.size()==1){status=true;}
		}else {if(makerCheckerTab.size()!=1){status=true;}}
		return status;
	}
	public boolean verifyJsonDataForgridColumnHidden(Map<String,String> jsonmap){
		boolean status=false;
		for(WebElement e: headersText){
			scrollToElement(e);
			if(jsonmap.get(e.getText()).equalsIgnoreCase("false")){status=true;}else{
				System.out.println("Header "+e.getText()+"is hidden in JSON configuration file");status=false;break;}
		}
		return status;
	}
	public void enableAllColumnsHeaders() {
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
				WebElement checkbox = headersColumns.get(i).findElement(By.tagName("input"));
				checkbox.click();
				if (checkbox.isSelected()) {
				} else {
					checkbox.click();
				}
			}
		}
	}
	public boolean verifyJsonDataForColumnIncludeGrid(Map<String,String> jsonmap){
		Map<String,String> map =getDefaultEnabledColumnsHeaders();
		return map.equals(jsonmap);
	}
	public Map<String,String> getDefaultEnabledColumnsHeaders() {
		Map<String,String> map=new HashMap<>();
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
				WebElement checkbox = headersColumns.get(i).findElement(By.tagName("input"));
				if (checkbox.isSelected()) {map.put(headersColumns.get(i).getText(),"false");}else{{map.put(headersColumns.get(i).getText(),"true");}}
			}
		}
		return map;
	}
	public boolean verifyJsonDataForMandatoryField(Map<String,String> jsonmap){
		boolean status=false;
		jsonmap.remove("Features");
		ArrayList<String> mand=new ArrayList<>();
		for(String key:jsonmap.keySet()){
			if(jsonmap.get(key).equalsIgnoreCase("true")){
				status=false;
				for(WebElement e:labels){
					if(e.getText().equals(key+"*")){mand.add(key);status=true;break;}
				}if(!status){System.out.println(key+" label is not having mandatory * symbol");break;}
			}
		}
		return status;
	}
	public boolean verifyDatabaseDetails(AgentSettingsDetails details){
		boolean status=false;
		DatabaseConnector db = new DatabaseConnector();
		db.connectToDataBase(Constants.db_name);
		ResultSet rs =db.executeQuery("select AGT_Agent.AvayaLoginID,AGT_Agent.UserName,AGT_Agent.FirstName,AGT_Agent.LastName,AGT_Agent.Profile, AGT_Teams.DisplayName from AGT_Agent Inner Join AGT_Teams on AGT_Agent.TeamID=AGT_Teams.TeamID where AGT_Agent.AvayaLoginID='"+details.getAvayaLoginID()+"'");
		List<Map<String, String>> maplist=db.getResultSetInMap(rs);
		if(maplist.get(0).get("AvayaLoginID").equals(details.getAvayaLoginID())){
			if(maplist.get(0).get("UserName").equals(details.getUsername())){
				if(maplist.get(0).get("FirstName").equals(details.getFirstname())){
					if(maplist.get(0).get("LastName").equals(details.getLastname())){
						if(maplist.get(0).get("Profile").equals(details.getProfile().substring(0,1))){
							if(maplist.get(0).get("DisplayName").equals(details.getTeamName())){
								status=true;
							}
						}
					}
				}
			}
		}
		return status;
	}
	public boolean verifyRetagSupervisorPopupDisplayed(){
		return retagSupervisorPopup.isDisplayed();
	}
	public boolean verifyProfileSelection(){
		boolean status=false;
		selectWebElement(profileDropdown);
		for(WebElement e:profileListBox){
			if(e.getText().equals("Agent")){status= true;break;}
		}
		return status;
	}
	public boolean verifyProfileSelectionAtCountryDivisionDepartmentLevel(String team){
		boolean status=false;
		String[] hrcy=team.split(">");
		for(int i=0;i<hrcy.length-1;i++) {
			try{selectWebElement(teamnameDropdown);
			Thread.sleep(3000);
			for(WebElement e: teamList){
				if(e.getText().equals(hrcy[i])){selectWebElement(e.findElement(By.className("k-in")));break;}
				if(e.findElements(By.className("k-i-expand")).size()>0)
				{selectWebElement(e.findElement(By.className("k-icon")));} }
			selectWebElement(profileDropdown);}catch (Exception e){e.printStackTrace();}
			for (WebElement e : profileListBox) {
				if (e.getText().equals("Agent")) {status = true;break;}
			}
			if(status){break;}
		}
		return status;
	}
	public boolean verifySupervisorDisplayed(String team, String supervisor){
		boolean status=false;
		try{
			selectWebElement(teamnameDropdown);
			Thread.sleep(3000);
			ChooseTeamHeirarchy(team);
			selectWebElement(profileDropdown);
			selectDropdownFromVisibleText(profileListBox,"Agent");
			Thread.sleep(3000);
			selectWebElement(supervisorDropdown);}catch (Exception e){e.printStackTrace();}
		for(WebElement e :supervisorListBox){
			if(e.getText().equals(supervisor)){status=true;break;}
		}
		return status;
	}

	public boolean isAddBtnDisplayed() {
		return addNewAgentSettingsRecordBtn.isDisplayed() && addNewAgentSettingsRecordBtn.isEnabled();
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
		return exportToExcelBtn.isDisplayed() && exportToExcelBtn.isEnabled();
	}
	public boolean isMakeAgentSettingsChangesButtonDisplayed() {
		Boolean status = false;
		try {
			if(makeAgentSettingsChanges.isDisplayed() && makeAgentSettingsChanges.isEnabled())
				status = true;
		}catch(Exception e) {
			status = false;
		}
		return status;
	}

	public boolean isApproveButtonDisplayed() {
		Boolean status = false;
		try {
			if(approveBtn.isDisplayed() && approveBtn.isEnabled())
				status = true;
		}catch(Exception e) {
			status = false;
		}
		return status;
	}

	public boolean isRejectButtonDisplayed() {
		Boolean status = false;
		try {
			if(rejectBtn.isDisplayed() && rejectBtn.isEnabled())
				status = true;
		}catch(Exception e) {
			status = false;
		}
		return status;
	}

	public void rejectChanges() throws Exception {
		selectWebElement(rejectBtn);
		selectWebElement(checkerReason);
		enterValueToTxtField(checkerReason, "Rejected");
		selectWebElement(approveYesBtn);
	}

	public void addRecdOnlyInfo(AgentSettingsDetails details) throws Exception {
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
		selectWebElement(saveBtn);
	}

	public void selectRecord() throws Exception {
		Map<String,String> map = new HashMap<>();
		waitUntilWebElementIsVisible(auditGrid);
		List<WebElement> rows=auditGrid.findElements(By.tagName("tr"));
		List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
		selectWebElement(cols.get(0).findElement(By.id("isEnabled")));	
	}
	public void sendForAprroval(String comments) throws Exception {
		selectWebElement(sendForApprovalBtn);
		Thread.sleep(1000);
		enterValueToTxtField(makerComments, comments);
		selectWebElement(submitMakerComments);			
	}

	public void Revert(String comments) throws Exception {
		selectWebElement(revertBtn);
		Thread.sleep(1000);
		enterValueToTxtField(revertMakerComments,comments);
		selectWebElement(revertSubmitMakerComments);				
	}

	public String getSuccessMessage() {
		waitForJqueryLoad(driver);
		if(successmsg.isDisplayed())
			return successmsg.getText();
		else{return errorMsg.get(0).getText();}
	}

	public boolean verifyMessage() {
		return(getSuccessMessage().contains("Record approved successfully. Request ID :"));
	}

	public boolean getErrorMsg() {
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.size()>0)
			return false;
		else
			return true;
	}
	public boolean EditCancel(AgentSettingsDetails details) throws Exception {
		selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		searchAgentSettingsRecord(details.getUsername());
		Thread.sleep(1000);
		selectWebElement(editBtn);
		waitForJqueryLoad(driver);
		selectWebElement(cancelBtn.get(1));
		waitUntilWebElementIsVisible(editrowdata.get(4));
		if(editrowdata.get(4).getText().equals(details.getFirstname()))
			return true;
		else
			return false;	
	}
	public boolean deleteCancel(AgentSettingsDetails details) throws Exception {
		selectWebElement(agentSettingsTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makeAgentSettingsChanges);
		Thread.sleep(1000);
		searchAgentSettingsRecord(details.getUsername());
		Thread.sleep(2000);
		selectWebElement(deleteBtn);
		waitForJqueryLoad(driver);
		selectWebElement(noBtn);
		if(editrowdata.get(4).getText().equals(details.getFirstname()))
			return true;
		else
			return false;
	}

	public boolean verifyAuditTrailUpdate(AgentSettingsDetails details, String Transaction, String Status) {
		boolean stat=false;
		Map<String,String> firstRowData=getFirstRowDatafromTable();
		Map<String,String> popupRowData=getFirstRowDatafromPreviewPopup();
		if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
			if(firstRowData.get("Status").equalsIgnoreCase(Status)){
				if(firstRowData.get("Function Name").equalsIgnoreCase("Agent Settings")){
					if(Transaction.equals("MakerUpdate")){
						Map<String,String> newvalues=new HashMap<>();
						String[] d=popupRowData.get("New Values").split("\n");
						for(String e:d){
							String[]f=e.split(":",2);
							if(f.length>1){newvalues.put(f[0],f[1]);}
						}
						if(verifyUpdatedNewValues(details,newvalues)){
							stat=true;}
						else 
							stat=false;
					}
					else{System.out.println("Data mismatch");}
				}else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"Agent Settings");}
			}else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}
		}else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}
		return stat;
	}
	private boolean verifyUpdatedNewValues(AgentSettingsDetails details, Map<String, String> newvalues) {
		Boolean Status=false;
		if(newvalues.get("Lan ID").equals(details.getUsername())){
			if(newvalues.get("Avaya Login ID").equals(details.getAvayaLoginID())){
				if(newvalues.get("First Name").equals(details.getUpdatedFirstname())){
					if(newvalues.get("Last Name").equals(details.getLastname())){
						if(newvalues.get("Profile").equals(details.getProfile())) {
							if(newvalues.get("OrgUnit").equals(details.getTeamName().split(">")[details.getTeamName().split(">").length-1])){
								if(newvalues.get("Supervisor Name").equals(details.getSupervisor())){
									if(newvalues.get("ModifyReason").equals(details.getModifyReason())) {
										Status=true;
									}else {System.out.println("Modify reason data mismatch");}
								}else{System.out.println("data mismatch"+newvalues.get("Supervisor Name")+"\t"+details.getSupervisor());}
							}else{System.out.println("data mismatch"+newvalues.get("OrgUnit")+"\t"+details.getTeamName());}
						}else{System.out.println("data mismatch"+newvalues.get("Profile")+"\t"+details.getProfile());}
					}else{System.out.println("data mismatch"+newvalues.get("Last Name")+"\t"+details.getLastname());}
				}else{System.out.println("data mismatch"+newvalues.get("First Name")+"\t"+details.getFirstname());}
			}else{System.out.println("data mismatch"+newvalues.get("Avaya Login ID")+"\t"+details.getAvayaLoginID());}
		}else{System.out.println("data mismatch"+newvalues.get("Lan ID")+"\t"+details.getUsername());}
		return Status;
	}

	public void EditRecordWithoutModifyReason(AgentSettingsDetails details) {
		try{selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		searchAgentSettingsRecord(details.getUsername());
		waitUntilWebElementIsVisible(editBtn);
		Thread.sleep(1000);
		selectWebElement(editBtn);
		selectWebElement(firstnameTextBox);
		selectWebElement(saveBtn);}catch (Exception e){e.printStackTrace();}
	}

	public void addNewAgentSettingsRecord1(AgentSettingsDetails details) throws Exception {
		selectWebElement(addNewAgentSettingsRecordBtn);
		waitUntilWebElementIsVisible(popupContent);
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
		/*try {
		            Thread.sleep(3000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }*/
		//selectDropdownFromVisibleText(teamNameListBox,details.getTeamName());
		ChooseTeamHeirarchy(details.getTeamName());
		selectProfile(details.getProfile(),details.getSupervisor());
		Thread.sleep(1000);
		selectWebElement(accessroleDropdown);
		selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());
		selectWebElement(saveBtn);
	}
	private Map<String, String> getFirstRowDatafromPreviewPopup() {
		Map<String,String> map = new HashMap<>();
		waitUntilWebElementIsVisible(auditGrid);
		List<WebElement> rows=auditGrid.findElements(By.tagName("tr"));
		List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
		List<WebElement> preview= cols.get(1).findElements(By.tagName("a"));
		preview.get(0).click();
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(tgrid);
		List<WebElement> gridrows=tgrid.findElements(By.tagName("tr"));
		List<WebElement> gridheaders = gridrows.get(0).findElements(By.tagName("th"));
		List<WebElement> gridcols=gridrows.get(1).findElements(By.tagName("td"));     
		for(int j=0;j<gridheaders.size();j++){
			scrollToElement(gridheaders.get(j));
			for(int i=0;i<gridcols.size();i++) {
				System.out.println(gridheaders.get(j).getText());
				try{
					map.put(gridheaders.get(j).getText(), gridcols.get(j).getText());
					break;
				}
				catch (Exception e){e.printStackTrace();}
			}
		}
		return map;
	}

	public void VerifyImportPic(AgentSettingsDetails details) throws Exception {		
		selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUserProfilePicbtn); 
		waitForJqueryLoad(driver);
		selectWebElement(UserAttriDropdown);
		selectDropdownFromVisibleText(userListbox,details.getattribute());
		clickOnUsingActionClass(selectfile);
		//Auto It script to load zip file
		FileUploader upload= new FileUploader();
		upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getfilename());
		selectWebElement(uploadBtn);
		////waitForJqueryLoad(driver);
		////selectWebElement(continueBtn);
		//selectWebElement(cancelBtn);

	}
	public void VerifyPicNdContinue(AgentSettingsDetails details) throws Exception {		
		selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUserProfilePicbtn); 
		waitForJqueryLoad(driver);
		selectWebElement(UserAttriDropdown);
		selectDropdownFromVisibleText(userListbox,details.getattribute());
		clickOnUsingActionClass(selectfile);
		//Auto It script to load zip file
		FileUploader upload= new FileUploader();
		upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getfilename());
		selectWebElement(uploadBtn);
		//waitForJqueryLoad(driver);
		//selectWebElement(continueBtn);
		//selectWebElement(cancelBtn);

	}

	public void UploadWithoutAttribute(AgentSettingsDetails details) throws Exception {		
		selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUserProfilePicbtn); 
		waitForJqueryLoad(driver);
		clickOnUsingActionClass(selectfile);
		//Auto It script to load zip file
		FileUploader upload= new FileUploader();
		upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getfilename());
		selectWebElement(uploadBtn);		
	}
	public void UploadWithoutAttrndPic(AgentSettingsDetails details) throws Exception {		
		selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUserProfilePicbtn); 
		waitForJqueryLoad(driver);
		selectWebElement(uploadBtn);		
	}
	public String VerifyToastMessage() {
		waitForJqueryLoad(driver);
		if(successmsg.isDisplayed())
			return successmsg.getText();
		else{return errorMsg.get(0).getText();}
	}
	public void UplodWithoutPic(AgentSettingsDetails details) throws Exception {		
		selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUserProfilePicbtn); 
		waitForJqueryLoad(driver);
		selectWebElement(UserAttriDropdown);
		selectDropdownFromVisibleText(userListbox,details.getattribute());
		selectWebElement(uploadBtn);		
		//selectWebElement(cancelBtn);
	}
	public boolean VerifyFileTypeErrorMsg(AgentSettingsDetails details) throws Exception {					
		selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUserProfilePicbtn); 
		waitForJqueryLoad(driver);
		selectWebElement(UserAttriDropdown);
		selectDropdownFromVisibleText(userListbox,details.getattribute());
		clickOnUsingActionClass(selectfile);
		//Auto It script to load zip file
		FileUploader upload= new FileUploader();
		upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getfilename());
		if(filetypeerrorMsg.getText().equals("File type not allowed."))
			return true;
		else 
			return false;
	}
	public boolean VerifyInsertedRecordcount(AgentSettingsDetails details) throws Exception{
		boolean status=false;
		waitForJqueryLoad(driver);
		if(insertedRecordCount.getText().equals("1"))
			status=true;
		else
			status=false;
		selectWebElement(continueBtn);
		return status;
	}

	public boolean VerifyInvalidRecordcount(AgentSettingsDetails details) throws Exception{
		boolean status=false;
		waitForJqueryLoad(driver);
		if(invalidRecordCount.getText().equals("1"))
			status=true;
		else
			status=false;
		selectWebElement(continueBtn);
	return status;
	}
	
	public String verifyErrorMessage(){
		return errorMsg.get(0).getText();
}
	
	
	public void verifySearchAgentSettingsRecord(String lanId) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("Lan ID", lanId);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn.get(0));
		Thread.sleep(2000);
		selectDropdownFromVisibleText(columnNameList,"Lan ID");
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		enterValueToTxtField(searchTextBox,lanId);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
	}
	
	public boolean verifySearchIsNotEqualTo(String firstname) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("First Name", firstname);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn.get(0));
		Thread.sleep(2000);
		selectDropdownFromVisibleText(columnNameList,"First Name");
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Is not equal to");
		enterValueToTxtField(searchTextBox,firstname);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;

	}

	public boolean verifySearchContains(String firstname) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"First Name");
		Thread.sleep(2000);
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Contains");
		enterValueToTxtField(searchTextBox,firstname);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("First Name").toUpperCase().contains(firstname.toUpperCase()))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}
	public boolean verifySearchDoesNotContains(String firstname) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"First Name");
		Thread.sleep(2000);
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Does not contain");
		enterValueToTxtField(searchTextBox,firstname);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(!map1.get("First Name").toLowerCase().contains(firstname.toLowerCase()))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}

	public boolean verifySearchStartsWith(String firstname) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"First Name");
		Thread.sleep(2000);
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Starts with");
		enterValueToTxtField(searchTextBox,firstname);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("First Name").toLowerCase().startsWith(firstname.toLowerCase()))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}

	public boolean verifySearchEndsWith(String firstname) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"First Name");
		Thread.sleep(2000);
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Ends with");
		enterValueToTxtField(searchTextBox,firstname);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("First Name").toUpperCase().endsWith(firstname.toUpperCase()))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}
	
	public List<Map<String, String>> gettable() throws Exception {
		waitForJqueryLoad(driver);
		int item=Integer.valueOf(items.get(2).getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.get(2).getText());
		int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){
			waitUntilWebElementIsVisible(auditGridContent);
			List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			for(int i=1;i<rows.size();i++) {
				Map<String,String> map = new HashMap<String,String>();
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				String col=null;
				for(int j=1;j<headers.size();j++){
					scrollToElement(headers.get(j));
					if(headers.get(j).getText().equals("Last Changed On")){
						col=cols.get(j).getText().substring(11);
					}
					else
						col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				map.remove("");
				arr.add(map);
			}
			if(k!=pages)
			{
				nextPageIcon.get(2).click();
				waitForJqueryLoad(driver);}
		}
		return arr;
	}
	
	public String VerifyMessage() {
		if(successmsg.isDisplayed())
			return successmsg.getText();
		else{return errorMsg.get(0).getText();}
	}
	
	public void addNewAgentSettingsRecordWithOutLanID(AgentSettingsDetails details) throws Exception {
		selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		waitForJqueryLoad(driver);
		try {Thread.sleep(5000);
		selectWebElement(addNewAgentSettingsRecordBtn);
		waitUntilWebElementIsVisible(popupContent);
		Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		navigateToTab("Info");
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
		waitForJqueryLoad(driver);
		selectProfile(details.getProfile(),details.getSupervisor());
		Thread.sleep(1000);
		selectWebElement(accessroleDropdown);
		selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());
		//selectWebElement(crmnameDropdown);
		//selectDropdownFromVisibleText(crmnameListBox,details.getCrmName());
		//        selectWebElement(texttemplatenameDropdown);
		//        selectDropdownFromVisibleText(texttemplatenameListBox,details.getTextTemplateName());
		navigateToTab("Channel Count");
		selectFeaturesToBeSelected(details.getFeaturestobeSeleted());
		selectWebElement(numericTextbox.get(1));
		enterValueToTxtFieldWithoutClear(totalVoiceTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVoiceTabs()));
		selectWebElement(numericTextbox.get(2));
		enterValueToTxtFieldWithoutClear(totalChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalChatTabs()));
		selectWebElement(numericTextbox.get(3));
		enterValueToTxtFieldWithoutClear(totalAudioChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalAudioChatTabs()));
		selectWebElement(numericTextbox.get(4));
		enterValueToTxtFieldWithoutClear(totalVideoChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVideoChatTabs()));
		selectWebElement(numericTextbox.get(5));
		enterValueToTxtFieldWithoutClear(totalFaxTabsAllowedTextBox.get(1),String.valueOf(details.getTotalFaxTabs()));
		/*selectWebElement(numericTextbox.get(6));
		enterValueToTxtFieldWithoutClear(totalEmailTabsAllowedTextBox.get(1),String.valueOf(details.getTotalEmailTabs()));
		selectWebElement(numericTextbox.get(7));
		enterValueToTxtFieldWithoutClear(totalSMSTabsAllowedTextBox.get(1),String.valueOf(details.getTotalSMSTabs()));	*/																							
		navigateToTab("Features");
		//selectDropdownFromVisibleText(featuresListBox,details.getFeatures());
		selectCheckBox(autoAnswerAllAcdCallsCheckbox,details.isAutoanswerallACDcalls());
		selectCheckBox(goToAcwAfterEachAcdCallsCheckbox,details.isGotoACWaftereachACDcalls());
		selectCheckBox(goToAcwAfterAnyCallsCheckbox,details.isGotoACWafteranycalls());
		//selectCheckBox(crmEnabledCheckbox,details.iscRMEnabled());
		selectCheckBox(holdVoiceCallOnChatCallCheckbox,details.isHoldVoiceCallOnChatCall());
		//selectCheckBox(secondTextChatAutoAnswerCheckbox,details.isSecondTextChatAutoAnswer());
		//selectCheckBox(textChatAutoACWCheckbox,details.isTextChatAutoACWEnabled());
		//selectCheckBox(textChatAutoAnswerCheckbox,details.isTextChatAutoAnswer());
		//        selectCheckBox(manualInCheckbox,details.isManualIn());
		//        selectCheckBox(smsOutCheckbox,details.isSMSOut());
		selectWebElement(saveBtn);
	}
	
	public void addNewAgentSettingsRecordWithOutAvayaLoginID(AgentSettingsDetails details) throws Exception {
		selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		waitForJqueryLoad(driver);
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
		waitForJqueryLoad(driver);
		selectProfile(details.getProfile(),details.getSupervisor());
		Thread.sleep(1000);
		selectWebElement(accessroleDropdown);
		selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());
		//selectWebElement(crmnameDropdown);
		//selectDropdownFromVisibleText(crmnameListBox,details.getCrmName());
		//        selectWebElement(texttemplatenameDropdown);
		//        selectDropdownFromVisibleText(texttemplatenameListBox,details.getTextTemplateName());
		navigateToTab("Channel Count");
		selectFeaturesToBeSelected(details.getFeaturestobeSeleted());
		selectWebElement(numericTextbox.get(1));
		enterValueToTxtFieldWithoutClear(totalVoiceTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVoiceTabs()));
		selectWebElement(numericTextbox.get(2));
		enterValueToTxtFieldWithoutClear(totalChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalChatTabs()));
		selectWebElement(numericTextbox.get(3));
		enterValueToTxtFieldWithoutClear(totalAudioChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalAudioChatTabs()));
		selectWebElement(numericTextbox.get(4));
		enterValueToTxtFieldWithoutClear(totalVideoChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVideoChatTabs()));
		selectWebElement(numericTextbox.get(5));
		enterValueToTxtFieldWithoutClear(totalFaxTabsAllowedTextBox.get(1),String.valueOf(details.getTotalFaxTabs()));
		/*selectWebElement(numericTextbox.get(6));
		enterValueToTxtFieldWithoutClear(totalEmailTabsAllowedTextBox.get(1),String.valueOf(details.getTotalEmailTabs()));
		selectWebElement(numericTextbox.get(7));
		enterValueToTxtFieldWithoutClear(totalSMSTabsAllowedTextBox.get(1),String.valueOf(details.getTotalSMSTabs()));	*/																							
		navigateToTab("Features");
		//selectDropdownFromVisibleText(featuresListBox,details.getFeatures());
		selectCheckBox(autoAnswerAllAcdCallsCheckbox,details.isAutoanswerallACDcalls());
		selectCheckBox(goToAcwAfterEachAcdCallsCheckbox,details.isGotoACWaftereachACDcalls());
		selectCheckBox(goToAcwAfterAnyCallsCheckbox,details.isGotoACWafteranycalls());
		//selectCheckBox(crmEnabledCheckbox,details.iscRMEnabled());
		selectCheckBox(holdVoiceCallOnChatCallCheckbox,details.isHoldVoiceCallOnChatCall());
		//selectCheckBox(secondTextChatAutoAnswerCheckbox,details.isSecondTextChatAutoAnswer());
		//selectCheckBox(textChatAutoACWCheckbox,details.isTextChatAutoACWEnabled());
		//selectCheckBox(textChatAutoAnswerCheckbox,details.isTextChatAutoAnswer());
		//        selectCheckBox(manualInCheckbox,details.isManualIn());
		//        selectCheckBox(smsOutCheckbox,details.isSMSOut());
		selectWebElement(saveBtn);
	}
	
	public void addNewAgentSettingsRecordWithoutFirstName(AgentSettingsDetails details) throws Exception {
		selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		waitForJqueryLoad(driver);
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
		waitForJqueryLoad(driver);
		selectProfile(details.getProfile(),details.getSupervisor());
		Thread.sleep(1000);
		selectWebElement(accessroleDropdown);
		selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());
		//selectWebElement(crmnameDropdown);
		//selectDropdownFromVisibleText(crmnameListBox,details.getCrmName());
		//        selectWebElement(texttemplatenameDropdown);
		//        selectDropdownFromVisibleText(texttemplatenameListBox,details.getTextTemplateName());
		navigateToTab("Channel Count");
		selectFeaturesToBeSelected(details.getFeaturestobeSeleted());
		selectWebElement(numericTextbox.get(1));
		enterValueToTxtFieldWithoutClear(totalVoiceTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVoiceTabs()));
		selectWebElement(numericTextbox.get(2));
		enterValueToTxtFieldWithoutClear(totalChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalChatTabs()));
		selectWebElement(numericTextbox.get(3));
		enterValueToTxtFieldWithoutClear(totalAudioChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalAudioChatTabs()));
		selectWebElement(numericTextbox.get(4));
		enterValueToTxtFieldWithoutClear(totalVideoChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVideoChatTabs()));
		selectWebElement(numericTextbox.get(5));
		enterValueToTxtFieldWithoutClear(totalFaxTabsAllowedTextBox.get(1),String.valueOf(details.getTotalFaxTabs()));
		/*selectWebElement(numericTextbox.get(6));
		enterValueToTxtFieldWithoutClear(totalEmailTabsAllowedTextBox.get(1),String.valueOf(details.getTotalEmailTabs()));
		selectWebElement(numericTextbox.get(7));
		enterValueToTxtFieldWithoutClear(totalSMSTabsAllowedTextBox.get(1),String.valueOf(details.getTotalSMSTabs()));	*/																							
		navigateToTab("Features");
		//selectDropdownFromVisibleText(featuresListBox,details.getFeatures());
		selectCheckBox(autoAnswerAllAcdCallsCheckbox,details.isAutoanswerallACDcalls());
		selectCheckBox(goToAcwAfterEachAcdCallsCheckbox,details.isGotoACWaftereachACDcalls());
		selectCheckBox(goToAcwAfterAnyCallsCheckbox,details.isGotoACWafteranycalls());
		//selectCheckBox(crmEnabledCheckbox,details.iscRMEnabled());
		selectCheckBox(holdVoiceCallOnChatCallCheckbox,details.isHoldVoiceCallOnChatCall());
		//selectCheckBox(secondTextChatAutoAnswerCheckbox,details.isSecondTextChatAutoAnswer());
		//selectCheckBox(textChatAutoACWCheckbox,details.isTextChatAutoACWEnabled());
		//selectCheckBox(textChatAutoAnswerCheckbox,details.isTextChatAutoAnswer());
		//        selectCheckBox(manualInCheckbox,details.isManualIn());
		//        selectCheckBox(smsOutCheckbox,details.isSMSOut());
		selectWebElement(saveBtn);
	}
	
	public void addNewAgentSettingsRecordWithoutLastName(AgentSettingsDetails details) throws Exception {
		selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		waitForJqueryLoad(driver);
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
		selectWebElement(teamnameDropdown);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//selectDropdownFromVisibleText(teamNameListBox,details.getTeamName());
		ChooseTeamHeirarchy(details.getTeamName());
		waitForJqueryLoad(driver);
		selectProfile(details.getProfile(),details.getSupervisor());
		Thread.sleep(1000);
		selectWebElement(accessroleDropdown);
		selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());
		//selectWebElement(crmnameDropdown);
		//selectDropdownFromVisibleText(crmnameListBox,details.getCrmName());
		//        selectWebElement(texttemplatenameDropdown);
		//        selectDropdownFromVisibleText(texttemplatenameListBox,details.getTextTemplateName());
		navigateToTab("Channel Count");
		selectFeaturesToBeSelected(details.getFeaturestobeSeleted());
		selectWebElement(numericTextbox.get(1));
		enterValueToTxtFieldWithoutClear(totalVoiceTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVoiceTabs()));
		selectWebElement(numericTextbox.get(2));
		enterValueToTxtFieldWithoutClear(totalChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalChatTabs()));
		selectWebElement(numericTextbox.get(3));
		enterValueToTxtFieldWithoutClear(totalAudioChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalAudioChatTabs()));
		selectWebElement(numericTextbox.get(4));
		enterValueToTxtFieldWithoutClear(totalVideoChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVideoChatTabs()));
		selectWebElement(numericTextbox.get(5));
		enterValueToTxtFieldWithoutClear(totalFaxTabsAllowedTextBox.get(1),String.valueOf(details.getTotalFaxTabs()));
		/*selectWebElement(numericTextbox.get(6));
		enterValueToTxtFieldWithoutClear(totalEmailTabsAllowedTextBox.get(1),String.valueOf(details.getTotalEmailTabs()));
		selectWebElement(numericTextbox.get(7));
		enterValueToTxtFieldWithoutClear(totalSMSTabsAllowedTextBox.get(1),String.valueOf(details.getTotalSMSTabs()));	*/																							
		navigateToTab("Features");
		//selectDropdownFromVisibleText(featuresListBox,details.getFeatures());
		selectCheckBox(autoAnswerAllAcdCallsCheckbox,details.isAutoanswerallACDcalls());
		selectCheckBox(goToAcwAfterEachAcdCallsCheckbox,details.isGotoACWaftereachACDcalls());
		selectCheckBox(goToAcwAfterAnyCallsCheckbox,details.isGotoACWafteranycalls());
		//selectCheckBox(crmEnabledCheckbox,details.iscRMEnabled());
		selectCheckBox(holdVoiceCallOnChatCallCheckbox,details.isHoldVoiceCallOnChatCall());
		//selectCheckBox(secondTextChatAutoAnswerCheckbox,details.isSecondTextChatAutoAnswer());
		//selectCheckBox(textChatAutoACWCheckbox,details.isTextChatAutoACWEnabled());
		//selectCheckBox(textChatAutoAnswerCheckbox,details.isTextChatAutoAnswer());
		//        selectCheckBox(manualInCheckbox,details.isManualIn());
		//        selectCheckBox(smsOutCheckbox,details.isSMSOut());
		selectWebElement(saveBtn);
	}
	
	public void addNewAgentSettingsRecordWithoutOrgUnitProfileAndSupervisor(AgentSettingsDetails details) throws Exception {
		selectWebElement(agentSettingsTabs.get(1));
		selectWebElement(makeAgentSettingsChanges);
		waitForJqueryLoad(driver);
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
		selectWebElement(accessroleDropdown);
		selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());
		//selectWebElement(crmnameDropdown);
		//selectDropdownFromVisibleText(crmnameListBox,details.getCrmName());
		//        selectWebElement(texttemplatenameDropdown);
		//        selectDropdownFromVisibleText(texttemplatenameListBox,details.getTextTemplateName());
		navigateToTab("Channel Count");
		selectFeaturesToBeSelected(details.getFeaturestobeSeleted());
		selectWebElement(numericTextbox.get(1));
		enterValueToTxtFieldWithoutClear(totalVoiceTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVoiceTabs()));
		selectWebElement(numericTextbox.get(2));
		enterValueToTxtFieldWithoutClear(totalChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalChatTabs()));
		selectWebElement(numericTextbox.get(3));
		enterValueToTxtFieldWithoutClear(totalAudioChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalAudioChatTabs()));
		selectWebElement(numericTextbox.get(4));
		enterValueToTxtFieldWithoutClear(totalVideoChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVideoChatTabs()));
		selectWebElement(numericTextbox.get(5));
		enterValueToTxtFieldWithoutClear(totalFaxTabsAllowedTextBox.get(1),String.valueOf(details.getTotalFaxTabs()));
		/*selectWebElement(numericTextbox.get(6));
		enterValueToTxtFieldWithoutClear(totalEmailTabsAllowedTextBox.get(1),String.valueOf(details.getTotalEmailTabs()));
		selectWebElement(numericTextbox.get(7));
		enterValueToTxtFieldWithoutClear(totalSMSTabsAllowedTextBox.get(1),String.valueOf(details.getTotalSMSTabs()));	*/																							
		navigateToTab("Features");
		//selectDropdownFromVisibleText(featuresListBox,details.getFeatures());
		selectCheckBox(autoAnswerAllAcdCallsCheckbox,details.isAutoanswerallACDcalls());
		selectCheckBox(goToAcwAfterEachAcdCallsCheckbox,details.isGotoACWaftereachACDcalls());
		selectCheckBox(goToAcwAfterAnyCallsCheckbox,details.isGotoACWafteranycalls());
		//selectCheckBox(crmEnabledCheckbox,details.iscRMEnabled());
		selectCheckBox(holdVoiceCallOnChatCallCheckbox,details.isHoldVoiceCallOnChatCall());
		//selectCheckBox(secondTextChatAutoAnswerCheckbox,details.isSecondTextChatAutoAnswer());
		//selectCheckBox(textChatAutoACWCheckbox,details.isTextChatAutoACWEnabled());
		//selectCheckBox(textChatAutoAnswerCheckbox,details.isTextChatAutoAnswer());
		//        selectCheckBox(manualInCheckbox,details.isManualIn());
		//        selectCheckBox(smsOutCheckbox,details.isSMSOut());
		selectWebElement(saveBtn);
	}
	


}



