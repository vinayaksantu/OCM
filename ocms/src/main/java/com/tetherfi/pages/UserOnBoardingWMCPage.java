package com.tetherfi.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.user.UserOnBoardingDetails;
import com.tetherfi.model.user.UserRoleMappingDetails;
import com.tetherfi.utility.FileUploader;

public class UserOnBoardingWMCPage extends BasePage {

	public UserOnBoardingWMCPage(WebDriver driver) {super(driver);}



	@FindBy(css=".ibox-title h5")
	private WebElement userOnBoarding;

	@FindBy(xpath="//i[@class='fa  fa-cogs']")
	private WebElement UOBImg;

	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;

	@FindBy(id="navbarheader")
	private WebElement header;

	@FindBy(css="#tGrid th[data-role='columnsorter']")
	private List<WebElement> approvedDataTableHeaders;

	@FindBy(css="a[aria-label='Go to the first page']")
    private WebElement firstPageIcon;
    
    @FindBy(css="a[aria-label='Go to the previous page']")
    private WebElement previousPageIcon;
    
    @FindBy(css=".k-pager-numbers .k-state-selected")
    private WebElement pageNumber;
    
    @FindBy(css="a[aria-label='Go to the last page']")
    private WebElement lastPageIcon;
    
    @FindBy(css=".k-pager-sizes .k-icon")
    private WebElement pagerDropdown;

	@FindBy(css=".k-animation-container ul li")
	private List<WebElement> pageSizeListBox;

	@FindBy(css="th a[class='k-header-column-menu']")
	private List<WebElement> headersDropdown;

	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;

	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;

	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;

	@FindBy(css=".k-grid-cancel")
	private WebElement cancelBtn;

	@FindBy(xpath="//button[@id='create']")
	private WebElement addNewUserOnBoardingRecord;

	@FindBy(xpath="//a[@class='k-step-link' and @title='Personal Info']")
	private WebElement PersonalInfoStepIcon;

	@FindBy(xpath="//a[@class='k-step-link' and @title='Profile Picture']")
	private WebElement ProfilePictureStepIcon;

	@FindBy(xpath="//a[@class='k-step-link' and @title='Channel Count']")
	private WebElement ChannelCountStepIcon;

	@FindBy(xpath="//a[@class='k-step-link' and @title='Features']")
	private WebElement FeaturesStepIcon;

	@FindBy(xpath="//a[@class='k-step-link' and @title='Role Mapping']")
	private WebElement RoleMappingStepIcon;

	@FindBy(xpath="//a[normalize-space()='Save']")
	private WebElement saveButton;

	@FindBy(css=".toast-message")
	private WebElement successmsg;

	@FindBy(css=".k-edit-form-container .form-group .k-numerictextbox .k-numeric-wrap .k-formatted-value")
	private List<WebElement> numericTextbox;

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

	@FindBy(css=".k-treeview-lines li div")
	private List<WebElement> teamList;

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

	@FindBy(id="FirstName")
	private WebElement firstnameTextBox;

	@FindBy(id="LastName")
	private WebElement lastnameTextBox;

	@FindBy(css="span[class^='k-widget k-dropdowntree k-dropdowntree-clearable']")
	private WebElement teamnameDropdown;

	@FindBy(css="ul[id='TeamID_listbox'] li")
	private List<WebElement> teamNameListBox;

	@FindBy(css="span[aria-owns='Profile_listbox']")
	private WebElement profileDropdown;

	@FindBy(css="ul[id='Profile_listbox'] li")
	private List<WebElement> profileListBox;
	
	@FindBy(css="a[aria-label='Go to the next page']")
    private WebElement nextPageIcon;

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

	@FindBy(id="AvayaLoginID")
	private WebElement avayaLoginIdTextBox;

	@FindBy(id="UserName")
	private WebElement lanIdTextBox;

	@FindBy(css=".k-edit-form-container")
	private WebElement popupContent;

	@FindBy(xpath="//div[@aria-label='Select a new Profile Picture for Upload']//input[@id='profile-picture-upload']")
	private WebElement ProfilePictureUploadBtn;

	@FindBy(xpath="//button[text()='Next']")
	private List<WebElement> nextButton;

	@FindBy(css="#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel']")
	private WebElement addNewUserOnBoardingCancelButton;

	@FindBy(css="#gridDiv2 .search-link")
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

	@FindBy(css="#drillGrid .k-grid-content")
	private WebElement gridContent;

	@FindBy(id = "1001sTextToSearch")
	private WebElement searchTextBox;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-edit']")
	private WebElement editBtn;

	@FindBy(xpath="//a[@title='Role Mapping']")
	private WebElement RoleMappingWindow;

	@FindBy(css=".k-edit-form-container #ModifyReason")
	private  WebElement modifyReasonTextBox;
	
	@FindBy(css="#drillGrid tbody tr td")
    private List<WebElement> editrowdata;
	
	@FindBy(id="noButton")
    private WebElement noBtn;
    
    @FindBy(css=".k-grid-CustomDelete")
    private WebElement deleteBtn;
    
    @FindBy(id="yesButton")
    private WebElement yesBtn;
    
    @FindBy(css=".form-group #ModifyReason1")
    private  WebElement deleteReasonTextBox;
    
    @FindBy(css=".k-pager-sizes .k-input")
    private WebElement pagerSize;































	public boolean verifylogo() {
		if(isElementExist(UOBImg))
			return true;
		else
			return false;
	}

	public boolean maximizewindow() {
		selectWebElement(maximize);
		waitForJqueryLoad(driver);
		if(fullscreen.isEnabled())
			return true;
		else 
		{return false;}
	}

	public boolean minimizewindow() {
		selectWebElement(minimize);
		waitForJqueryLoad(driver);
		if(header.isDisplayed())
		{
			return true;
		}
		else 
			return false; 
	}

	public boolean isUserOnBoardingPageDisplayed() throws InterruptedException {
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		return userOnBoarding.isEnabled();
	}

	private ArrayList<String> getHeadersfromTable(List<WebElement> e){
		ArrayList<String> header=new ArrayList<String>();
		for(int j=0;j<e.size();j++){
			scrollToElement(e.get(j));
			if(e.get(j).isDisplayed())
			{
				header.add(e.get(j).getText());
			}
			else {System.out.println(e.get(j).getText()+"is Disabled at index"+j);}
		}
		return header;
	}

	public boolean verifyGridColumnHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("First Name", "Last Name", "Lan ID", "Avaya Login ID", "Profile", "Supervisor Name", "Org. Unit", "Access Role", "CRM Name", "Text Chat Greeting Template Name", "Role", "Last Changed By", "Last Changed On"));
		ArrayList<String> Actual = getHeadersfromTable(approvedDataTableHeaders);
		System.out.println(Actual);
		System.out.println(Expected);
		Collections.sort(Expected);
		Collections.sort(Actual);
		return Actual.equals(Expected);
	}

	public boolean verifyDropDownOfAllHeaders() {
		boolean status = false;
		try {for (WebElement ele : headersDropdown) {
			scrollToElement(ele);
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
	public boolean verifycolumnsHeaderEnabled() throws InterruptedException{
		boolean status=false;
		try{
			for(WebElement ele:headersDropdown) {
				scrollToElement(ele);
				if (!ele.isDisplayed()) {
					continue;
				}
				else {
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
				break;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
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
			break;
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}



	public String VerifyMessage() {
		waitForJqueryLoad(driver);
		if(successmsg.isDisplayed())
			return successmsg.getText();
		else{return errorMsg.get(0).getText();}
	}



	public boolean addCancelButton(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		String actualitems=items.getText();
		System.out.println(actualitems);
		Thread.sleep(2000);
		selectWebElement(addNewUserOnBoardingRecord);
		selectWebElement(addNewUserOnBoardingCancelButton);
		if(actualitems.equals(items.getText()))
			return true;
		else
			return false;
	}



	public boolean VerifyAddNewUserOnBoardingStepIconsandCreateRecordWithAlltheFieldsBlank() throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewUserOnBoardingRecord);
		boolean status=false;
		if(PersonalInfoStepIcon.isDisplayed()) {
			if(ProfilePictureStepIcon.isDisplayed()) {
				if(ChannelCountStepIcon.isDisplayed()) {
					if(FeaturesStepIcon.isDisplayed()) {
						if(RoleMappingStepIcon.isDisplayed()) {
							selectWebElement(saveButton);
							status=true;
						}
						else {System.out.println("RoleMappingStepIcon is not Displayed");}
					}
					else {System.out.println("FeaturesStepIcon is not Dispalyed");}
				}
				else {System.out.println("ChannelCountStepIcon is not Displayed");}
			}
			else {System.out.println("ProfilePictureStepIcon is not Displayed");}
		}
		else {System.out.println("PersonalInfoStepIcon is not Displayed");}
		return status;
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

	public void selectCheckBox(WebElement ele, boolean value){
		if(value){
			if(ele.isSelected()){}else{selectCheckbox(ele);}
		}
		else{
			if(ele.isSelected()){selectCheckbox(ele);}
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

	private void addProfilePicture_ChannelCount_FeaturesData(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		clickOnUsingActionClass(ProfilePictureUploadBtn);
		//Auto It script to load wave file
		FileUploader upload= new FileUploader();
		upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getProfilePicture());
		selectWebElement(nextButton.get(1));
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
		selectWebElement(numericTextbox.get(6));
		enterValueToTxtFieldWithoutClear(totalEmailTabsAllowedTextBox.get(1),String.valueOf(details.getTotalEmailTabs()));
		selectWebElement(numericTextbox.get(7));
		enterValueToTxtFieldWithoutClear(totalSMSTabsAllowedTextBox.get(1),String.valueOf(details.getTotalSMSTabs()));																								
		selectWebElement(nextButton.get(2));
		selectCheckBox(autoAnswerAllAcdCallsCheckbox,details.isAutoanswerallACDcalls());
		selectCheckBox(goToAcwAfterEachAcdCallsCheckbox,details.isGotoACWaftereachACDcalls());
		selectCheckBox(goToAcwAfterAnyCallsCheckbox,details.isGotoACWafteranycalls());
		selectCheckBox(holdVoiceCallOnChatCallCheckbox,details.isHoldVoiceCallOnChatCall());
	}

	public void addNewUserOnBoardingRecordWithoutFirstName(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		try {Thread.sleep(5000);
		selectWebElement(addNewUserOnBoardingRecord);
		waitUntilWebElementIsVisible(popupContent);

		Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(lastnameTextBox);
		enterValueToTxtFieldWithoutClear(lastnameTextBox,details.getLastname());
		selectWebElement(lanIdTextBox);
		enterValueToTxtFieldWithoutClear(lanIdTextBox,details.getLanID());
		selectWebElement(numericTextbox.get(0));
		enterValueToTxtFieldWithoutClear(avayaLoginIdTextBox,details.getAvayaLoginID());
		selectWebElement(teamnameDropdown);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ChooseTeamHeirarchy(details.getTeamName());
		selectProfile(details.getProfile(),details.getSupervisor());
		Thread.sleep(1000);
		selectWebElement(nextButton.get(0));
		addProfilePicture_ChannelCount_FeaturesData(details);
		selectWebElement(nextButton.get(3));
		selectWebElement(accessroleDropdown);
		selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());	
		selectWebElement(saveButton);
	}

	public void addNewUserOnBoardingRecordWithoutLastName(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		try {Thread.sleep(5000);
		selectWebElement(addNewUserOnBoardingRecord);
		waitUntilWebElementIsVisible(popupContent);

		Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(firstnameTextBox);
		enterValueToTxtFieldWithoutClear(firstnameTextBox,details.getFirstname());
		//selectWebElement(lastnameTextBox);
		//enterValueToTxtFieldWithoutClear(lastnameTextBox,details.getLastname());
		selectWebElement(lanIdTextBox);
		enterValueToTxtFieldWithoutClear(lanIdTextBox,details.getLanID());
		selectWebElement(numericTextbox.get(0));
		enterValueToTxtFieldWithoutClear(avayaLoginIdTextBox,details.getAvayaLoginID());
		selectWebElement(teamnameDropdown);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ChooseTeamHeirarchy(details.getTeamName());
		selectProfile(details.getProfile(),details.getSupervisor());
		Thread.sleep(1000);
		selectWebElement(nextButton.get(0));
		addProfilePicture_ChannelCount_FeaturesData(details);
		selectWebElement(nextButton.get(3));
		selectWebElement(accessroleDropdown);
		selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());	
		selectWebElement(saveButton);
	}

	public void addNewUserOnBoardingRecordWithoutLanID(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		try {Thread.sleep(5000);
		selectWebElement(addNewUserOnBoardingRecord);
		waitUntilWebElementIsVisible(popupContent);

		Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(firstnameTextBox);
		enterValueToTxtFieldWithoutClear(firstnameTextBox,details.getFirstname());
		selectWebElement(lastnameTextBox);
		enterValueToTxtFieldWithoutClear(lastnameTextBox,details.getLastname());
		selectWebElement(numericTextbox.get(0));
		enterValueToTxtFieldWithoutClear(avayaLoginIdTextBox,details.getAvayaLoginID());
		selectWebElement(teamnameDropdown);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ChooseTeamHeirarchy(details.getTeamName());
		Thread.sleep(1000);
		selectProfile(details.getProfile(),details.getSupervisor());
		Thread.sleep(1000);
		selectWebElement(nextButton.get(0));
		addProfilePicture_ChannelCount_FeaturesData(details);
		selectWebElement(nextButton.get(3));
		selectWebElement(accessroleDropdown);
		selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());	
		selectWebElement(saveButton);
	}

	public void addNewUserOnBoardingRecordWithoutAvayaLoginID(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		try {Thread.sleep(5000);
		selectWebElement(addNewUserOnBoardingRecord);
		waitUntilWebElementIsVisible(popupContent);

		Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(firstnameTextBox);
		enterValueToTxtFieldWithoutClear(firstnameTextBox,details.getFirstname());
		selectWebElement(lastnameTextBox);
		enterValueToTxtFieldWithoutClear(lastnameTextBox,details.getLastname());
		selectWebElement(lanIdTextBox);
		enterValueToTxtFieldWithoutClear(lanIdTextBox,details.getLanID());
		//selectWebElement(numericTextbox.get(0));
		//enterValueToTxtFieldWithoutClear(avayaLoginIdTextBox,details.getAvayaLoginID());
		selectWebElement(teamnameDropdown);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ChooseTeamHeirarchy(details.getTeamName());
		selectProfile(details.getProfile(),details.getSupervisor());
		Thread.sleep(1000);
		selectWebElement(nextButton.get(0));
		addProfilePicture_ChannelCount_FeaturesData(details);
		selectWebElement(nextButton.get(3));
		selectWebElement(accessroleDropdown);
		selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());	
		selectWebElement(saveButton);
	}

	public void addNewUserOnBoardingRecordWithoutOrgUnit(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		try {Thread.sleep(5000);
		selectWebElement(addNewUserOnBoardingRecord);
		waitUntilWebElementIsVisible(popupContent);

		Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(firstnameTextBox);
		enterValueToTxtFieldWithoutClear(firstnameTextBox,details.getFirstname());
		selectWebElement(lastnameTextBox);
		enterValueToTxtFieldWithoutClear(lastnameTextBox,details.getLastname());
		selectWebElement(lanIdTextBox);
		enterValueToTxtFieldWithoutClear(lanIdTextBox,details.getLanID());
		selectWebElement(numericTextbox.get(0));
		enterValueToTxtFieldWithoutClear(avayaLoginIdTextBox,details.getAvayaLoginID());
		selectProfile(details.getProfile(),"NA");
		Thread.sleep(1000);
		selectWebElement(nextButton.get(0));
		addProfilePicture_ChannelCount_FeaturesData(details);
		selectWebElement(nextButton.get(3));
		selectWebElement(accessroleDropdown);
		selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());	
		selectWebElement(saveButton);
	}

	public void addNewUserOnBoardingRecordWithoutProfile(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		try {Thread.sleep(5000);
		selectWebElement(addNewUserOnBoardingRecord);
		waitUntilWebElementIsVisible(popupContent);

		Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(firstnameTextBox);
		enterValueToTxtFieldWithoutClear(firstnameTextBox,details.getFirstname());
		selectWebElement(lastnameTextBox);
		enterValueToTxtFieldWithoutClear(lastnameTextBox,details.getLastname());
		selectWebElement(lanIdTextBox);
		enterValueToTxtFieldWithoutClear(lanIdTextBox,details.getLanID());
		selectWebElement(numericTextbox.get(0));
		enterValueToTxtFieldWithoutClear(avayaLoginIdTextBox,details.getAvayaLoginID());
		selectWebElement(teamnameDropdown);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ChooseTeamHeirarchy(details.getTeamName());
		Thread.sleep(1000);
		selectWebElement(nextButton.get(0));
		addProfilePicture_ChannelCount_FeaturesData(details);
		selectWebElement(nextButton.get(3));
		selectWebElement(accessroleDropdown);
		selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());	
		selectWebElement(saveButton);
	}

	public void addNewUserOnBoardingRecordWithoutAccessRole(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		try {Thread.sleep(5000);
		selectWebElement(addNewUserOnBoardingRecord);
		waitUntilWebElementIsVisible(popupContent);

		Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(firstnameTextBox);
		enterValueToTxtFieldWithoutClear(firstnameTextBox,details.getFirstname());
		selectWebElement(lastnameTextBox);
		enterValueToTxtFieldWithoutClear(lastnameTextBox,details.getLastname());
		selectWebElement(lanIdTextBox);
		enterValueToTxtFieldWithoutClear(lanIdTextBox,details.getLanID());
		selectWebElement(numericTextbox.get(0));
		enterValueToTxtFieldWithoutClear(avayaLoginIdTextBox,details.getAvayaLoginID());
		selectWebElement(teamnameDropdown);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ChooseTeamHeirarchy(details.getTeamName());
		selectProfile(details.getProfile(),details.getSupervisor());
		Thread.sleep(1000);
		selectWebElement(nextButton.get(0));
		addProfilePicture_ChannelCount_FeaturesData(details);
		selectWebElement(nextButton.get(3));	
		selectWebElement(saveButton);
	}

	public void addNewUserOnBoardingRecordWithAllValidData(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		try {Thread.sleep(5000);
		selectWebElement(addNewUserOnBoardingRecord);
		waitUntilWebElementIsVisible(popupContent);
		waitForJqueryLoad(driver);
		Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(firstnameTextBox);
		enterValueToTxtFieldWithoutClear(firstnameTextBox,details.getFirstname());
		selectWebElement(lastnameTextBox);
		enterValueToTxtFieldWithoutClear(lastnameTextBox,details.getLastname());
		selectWebElement(lanIdTextBox);
		enterValueToTxtFieldWithoutClear(lanIdTextBox,details.getLanID());
		selectWebElement(numericTextbox.get(0));
		enterValueToTxtFieldWithoutClear(avayaLoginIdTextBox,details.getAvayaLoginID());
		selectWebElement(teamnameDropdown);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ChooseTeamHeirarchy(details.getTeamName());
		selectProfile(details.getProfile(),details.getSupervisor());
		Thread.sleep(1000);
		selectWebElement(nextButton.get(0));
		addProfilePicture_ChannelCount_FeaturesData(details);
		selectWebElement(nextButton.get(3));
		selectWebElement(accessroleDropdown);
		selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());	
		selectWebElement(saveButton);
		waitForJqueryLoad(driver);
	}

	public void searchUserOnBoardingRecord(String lanID) throws Exception  {
		waitForJqueryLoad(driver);
		selectWebElement(searchLink);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Lan ID");
		Thread.sleep(1000);
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		enterValueToTxtField(searchTextBox,lanID);
		selectWebElement(popupSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
	}

	public void EditRecordWithoutModifyReason(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		searchUserOnBoardingRecord(details.getLanID());
		waitUntilWebElementIsVisible(editBtn);
		Thread.sleep(1000);
		selectWebElement(editBtn);
		selectWebElement(firstnameTextBox);
		selectWebElement(saveButton);
	}

	public boolean getErrorMsg() {
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.size()>0)
			return false;
		else
			return true;
	}

	public void editUserOnBoardingRecord(UserOnBoardingDetails details) {
		try{waitForJqueryLoad(driver);
			searchUserOnBoardingRecord(details.getLanID());
			Thread.sleep(1000);
			selectWebElement(editBtn);
			selectWebElement(firstnameTextBox);
			enterValueToTxtField(firstnameTextBox,details.getUpdatedFirstname()); 
			selectWebElement(RoleMappingWindow);
			waitUntilWebElementIsVisible(modifyReasonTextBox);
			enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
			selectWebElement(saveButton);}catch (Exception e){e.printStackTrace();}
	}
	
	 public String getSuccessMessage() {
			waitForJqueryLoad(driver);
			if(successmsg.isDisplayed())
				return successmsg.getText();
			else{return errorMsg.get(0).getText();}
	        
		}
	
	public boolean deleteCancel(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		searchUserOnBoardingRecord(details.getLanID());
		Thread.sleep(2000);
		selectWebElement(deleteBtn);
		waitForJqueryLoad(driver);
		selectWebElement(noBtn);
		if(editrowdata.get(4).getText().equals(details.getFirstname()))
			return true;
		else
		return false;
	}
 
 public void deleteUserOnBoardingRecord(String lanId,String reason) {
    	try
        {searchUserOnBoardingRecord(lanId);
        Thread.sleep(4000);
        btnClick(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtFieldWithoutClear(deleteReasonTextBox,reason);
        selectWebElement(yesBtn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
 public boolean verifyArrowMoveForPreviousAndNextPage(){
     boolean status=false;
     if(!nextPageIcon.getAttribute("class").contains("k-state-disabled")){
     int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
     selectWebElement(nextPageIcon);
     waitForJqueryLoad(driver);
     int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
     selectWebElement(previousPageIcon);
     waitForJqueryLoad(driver);
     int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
     if(nextnumber==(pagenumber+1) && pagenumber==previousnumber){status=true;}
     }else{
         System.out.println("previous and next page icon disabled");status=true;
     }
     return status;
	}
	public boolean verifyArrowMoveForFirstAndLastPage(){
     boolean status=false;
     if(!lastPageIcon.getAttribute("class").contains("k-state-disabled")){
         int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
         selectWebElement(lastPageIcon);
         waitForJqueryLoad(driver);
         int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
         selectWebElement(firstPageIcon);
         waitForJqueryLoad(driver);
         int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
         if(nextnumber>pagenumber && pagenumber==previousnumber){status=true;}
     }else{
         System.out.println("previous and next page icon disabled");status=true;
     }
     return status;
 }
	public boolean verifyNumberOfItemsPerPage() {
     boolean status = false;
     try {
       //  if (norecords.size() <= 0) {
             int item = Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
             selectWebElement(pagerDropdown);
             Thread.sleep(1500);
             for (int i = 0; i < pageSizeListBox.size(); i++) {
                 if(Integer.valueOf(pageSizeListBox.get(i).getText())>item){continue;}
                 selectDropdownFromVisibleText(pageSizeListBox, pageSizeListBox.get(i).getText());
                 waitForJqueryLoad(driver);
                 int totalItems = Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
                 int pagersize = Integer.valueOf(pagerSize.getText());
                 int pages = (totalItems % pagersize == 0) ? item / pagersize : item / pagersize+1;
                 int totalRows=(gridContent.findElements(By.tagName("tr")).size());
                 selectWebElement(lastPageIcon);
                 waitForJqueryLoad(driver);
                 int lastPageNumber = Integer.valueOf(pageNumber.getText());
                 if (item == totalItems && pages == lastPageNumber&&totalRows==pagersize) {
                     status = true;
                 } else {System.out.println(items+":"+totalItems+"\t"+pages+":"+lastPageNumber+"\t"+totalRows+":"+pagersize);
                     status = false;
                     break;
                 }selectWebElement(pagerDropdown);Thread.sleep(1500);
             }
        // }
     } catch (Exception e) {
         e.printStackTrace();
     } return status;
 }
 public boolean verifyTotalNumberOfItemsPerPageDetails(){
     String item = items.getText();
     return item.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
 }

}