package com.tetherfi.pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.model.user.AgentSkillAssignmentNewDetails;
import com.tetherfi.model.user.UserOnBoardingDetails;
import com.tetherfi.model.user.UserRoleMappingDetails;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.FileUploader;
import com.tetherfi.utility.Util;

public class UserOnBoardingPage extends BasePage {

	public UserOnBoardingPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".ibox-title h5")
	private WebElement userOnBoarding;

	@FindBy(xpath="//i[@class='far fa-user-plus']")
	private WebElement UOBImg;

	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;

	@FindBy(id="navbarheader")
	private WebElement header;

	@FindBy(css="#checkerGrid th[data-role='columnsorter']")
	private List<WebElement> approvedDataTableHeaders;

	@FindBy(css="ul[class='k-tabstrip-items k-reset'] li")
	private List<WebElement> UserOnBoardingTabs;

	@FindBy(css="#gridDiv #tGrid th[data-role='columnsorter']")
	private List<WebElement> auditTrailTableHeaders;

	@FindBy(id="makeChanges")
	private WebElement makeUserOnBoardingChanges;

	@FindBy(id="create")
	private WebElement addNewUserOnBoardingRecordBtn;

	@FindBy(id="goToAuditTrail")
	private WebElement goBackBtn;

	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;

	@FindBy(css="#drillGrid th[data-role='columnsorter']")
	private List<WebElement> makerTableHeaders;

	@FindBy(css="a[aria-label='Go to the next page']")
	private List<WebElement> nextPageIcon;

	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupby;

	@FindBy(css="a[aria-label='Go to the first page']")
	private List<WebElement> firstPageIcon;

	@FindBy(css="a[aria-label='Go to the previous page']")
	private List<WebElement> previousPageIcon;

	@FindBy(css=".k-pager-numbers .k-state-selected")
	private List<WebElement> pageNumber;

	@FindBy(css="a[aria-label='Go to the last page']")
	private List<WebElement> lastPageIcon;

	@FindBy(css=".k-pager-sizes .k-icon")
	private List<WebElement> pagerDropdown;

	@FindBy(css=".k-animation-container ul li")
	private List<WebElement> pageSizeListBox;

	@FindBy(css="th a[class='k-header-column-menu']")
	private List<WebElement> headersDropdown;

	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;

	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;		

	@FindBy(xpath="//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")
	private List<WebElement> tablerecord;

	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private List<WebElement> items;

	@FindBy(xpath="//span[@class='k-icon k-i-arrow-60-right k-menu-expand-arrow']")
	private WebElement coloumnarrow;

	@FindBy(id="grid")
	private WebElement auditGridContent;

	@FindBy(css=".k-pager-sizes .k-input")
	private List<WebElement> pagerSize;

	@FindBy(css="#drillGrid .k-grid-content")
	private WebElement gridContent;
	
	@FindBy(xpath="//a[@aria-label='Go to the next page']")
	private List<WebElement> featuresGridNexpageButton;

	@FindBy(id = "drillGrid")
	private WebElement grid;

	@FindBy(css="#gridDiv2 .search-link")
	private WebElement searchLink;
	
	@FindBy(css="#gridDiv .search-link")
	private WebElement searchLinkApprovedSec;

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

	@FindBy(css="#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;

	@FindBy(xpath="//a[text()='First Name']")
	private List<WebElement> FirstName;

	@FindBy(css = "#drillGrid .k-grouping-header")
	private WebElement dragColumnDestination;

	@FindBy(css="#gridDiv .search-link")
	private WebElement gridsearchLink;

	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearall;

	@FindBy(xpath="//button[text()='Close']")
	private WebElement searchClose;

	@FindBy(xpath="//i[@class='fas fa-sync fa-spin']")
	private List<WebElement> clearsearch;

	@FindBy(css="#tcheckerGrid .k-grid-content")
	private WebElement approvedgridcontent;

	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;

	@FindBy(css = ".modal-body .form-inline .form-group .k-select")
	private List<WebElement> selectSearchCol;

	@FindBy(css="ul[id='1001sCriteria_listbox'] li")
	private List<WebElement> searchCriteriaDropDwn;

	@FindBy(id = "1001sTextToSearch")
	private WebElement searchTextBox;

	@FindBy(xpath="//button[text()='Search']")
	private WebElement searchSearchBtn;

	@FindBy(css="#gridDiv2 #tGrid")
	private WebElement auditGrid;

	@FindBy(xpath="//button[@id='create']")
	private WebElement addNewUserOnBoardingRecord;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel']")
	private WebElement addNewUserOnBoardingCancelButton;

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

	@FindBy(xpath="//button[text()='Next']")
	private List<WebElement> nextButton;

	@FindBy(css=".k-edit-form-container")
	private WebElement popupContent;

	@FindBy(xpath="//div[@aria-label='Select a new Profile Picture for Upload']//input[@id='profile-picture-upload']")
	private WebElement ProfilePictureUploadBtn;

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

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-edit']")
	private WebElement editBtn;

	@FindBy(css=".k-edit-form-container #ModifyReason")
	private  WebElement modifyReasonTextBox;

	@FindBy(xpath="//a[@title='Role Mapping']")
	private WebElement RoleMappingWindow;

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

	@FindBy(xpath="//button[normalize-space()='Import User Profile Pictures']")
	private WebElement ImportUserProfilePictureButton;

	@FindBy(xpath="//div[text()='Import Profile Pictues']")
	private WebElement ImportProfilePicturesPopUpHeader;

	@FindBy(xpath="//label[normalize-space()='User Attribute to match Pictures:']")
	private WebElement UserAttributetoMatchPicturesdropdownLabel;

	@FindBy(xpath="//span[@aria-owns='User_listbox']/span")
	private WebElement UserAttributetoMatchPicturesdropdownbutton;

	@FindBy(css="ul[id='User_listbox'] li")
	private List<WebElement> UserAttributetoMatchPicturesdropdownValues;

	@FindBy(xpath="//li[normalize-space()='Lan ID']")
	private WebElement LanIDAttribute;

	@FindBy(xpath="//li[normalize-space()='Avaya Login ID']")
	private WebElement AvayaLoginTDAttribute;

	@FindBy(xpath="//input[@id='zipFile']")
	private WebElement selectfile;

	@FindBy(id="uploadZipFile")
	private WebElement uploadBtn;

	@FindBy(xpath="//p[normalize-space()='Drag & Drop Files Here']")
	private WebElement dragAndDropFilesHereLabel;

	@FindBy(xpath="//div[text()='Please provide only the images inside the zip file, do not create the folder inside the zip file, Also Image Size should be less than or equal to 30kb (30000 bytes)']")
	private WebElement FileUploadMessage;

	@FindBy(xpath="//div[text()='Inserted Records: ']/a[1]")
	private WebElement insertedRecordCount;

	@FindBy(xpath="//div[text()='Invalid Records: ']/a[2]")
	private WebElement invalidRecordCount;

	@FindBy(xpath="//button[normalize-space()='Continue']")
	private WebElement continueBtn;

	@FindBy(xpath="//span[@class='k-file-validation-message k-text-error']")
	private WebElement filetypeerrorMsg;

	@FindBy(xpath="//button[normalize-space()='Import Users']")
	private WebElement importUsersButton;

	@FindBy(xpath="//span[text()='Import Users']")
	private WebElement ImportUsersPopUpHeader;

	@FindBy(xpath="//label[text()='Please import 3 files as per template']")
	private WebElement messageLabel;

	@FindBy(xpath="//label[text()='Note: Supervisor should already be present!!']")
	private WebElement messageLabel1;

	@FindBy(xpath="//a[text()='Download User Detail Template']")
	private WebElement userDetailTemplateDownLoadLink;

	@FindBy(xpath="//a[text()='Download User Channel Template']")
	private WebElement userChannelTemplateDownLoadLink;

	@FindBy(xpath="//a[text()='Download User Feature Template']")
	private WebElement userFeatureTemplateDownLoadLink;

	@FindBy(xpath="//span[@id='bulkAgentUploadWindow_wnd_title']/following-sibling::div/a")
	private WebElement ImportUsersPopUpCloseButton;

	@FindBy(xpath="//input[@id='importdetails']")
	private WebElement selectImportFileButton;

	@FindBy(xpath="//div[text()='Duplicate Records: ']/a[1]")
	private WebElement duplicateUserRecordCount;

	@FindBy(xpath="//div[text()=' Inserted Records: ']/a[2]")
	private WebElement InsertedUserRecordCount;

	@FindBy(xpath="//div[text()='Invalid Records: ']/a[3]")
	private WebElement InvalidUserRecordCount;

	@FindBy(xpath="//a[text()='Please upload all the 3 files together.']")
	private WebElement fileUploadMessage;
	
	@FindBy(xpath="//a[text()='Failed to import Agent Details']")
	private WebElement uploadErrormessage;
	
	@FindBy(xpath="//a[@class='k-icon k-i-expand']")
    private List<WebElement> pageArrowDropDown;
    
    @FindBy(xpath="//span[text()='Skill List']")
    private WebElement pageArrowSkillList;
    
    @FindBy(xpath="//li[@class='k-item k-state-default k-first k-tab-on-top k-state-active']")
    private List<WebElement> channelTab;
    
    @FindBy(css="#tcheckerGrid .k-grid-display-block")
    private List<WebElement> arrowGrid;










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

	public boolean verifyApprovedDataTableHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("First Name", "Last Name", "Lan ID", "Avaya Login ID", "Profile", "Supervisor Name", "Org. Unit", "Access Role", "CRM Name", "Text Chat Greeting Template Name", "Role", "Last Changed By", "Last Changed On"));
		ArrayList<String> Actual = getHeadersfromTable(approvedDataTableHeaders);
		System.out.println(Actual);
		System.out.println(Expected);
		Collections.sort(Expected);
		Collections.sort(Actual);
		return Actual.equals(Expected);
	}

	public void selectUserOnBoardingAuditTrailTab() {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean verifyAuditTrailDataTableHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList(" ","Request ID", "Transaction", "Function Name", "Status", "User Id", "Submission DateTime", "Maker Comments", "Reviewed By","Review DateTime", "Checker Comments"));
		ArrayList<String> Actual = getHeadersfromTable(auditTrailTableHeaders);
		System.out.println(Actual);
		System.out.println(Expected);
		Collections.sort(Expected);
		Collections.sort(Actual);
		return Actual.equals(Expected);
	}

	public void selectMakeUserOnBoardingChanges() {
		waitForJqueryLoad(driver);
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean verifyAddNewUserOnBoardingRecordButton() {
		return addNewUserOnBoardingRecordBtn.isEnabled();
	}

	public boolean verifyGoBackButton() {
		return goBackBtn.isEnabled();
	}

	public void selectGoBackButton() throws Exception
	{
		selectWebElement(goBackBtn);
		Thread.sleep(2000);
	}

	public boolean verifyExportToExcelButton() {
		return exporttoexcel.isEnabled();
	}

	public boolean verifyMakerDataTableHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("First Name", "Last Name", "Lan ID", "Avaya Login ID", "Profile", "Supervisor Name", "Org. Unit", "Access Role", "CRM Name", "Text Chat Greeting Template Name", "Role", "Last Changed By", "Last Changed On"));
		ArrayList<String> Actual = getHeadersfromTable(makerTableHeaders);
		System.out.println(Actual);
		Collections.sort(Expected);Collections.sort(Actual);
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

	public boolean verifyExportToExcel(String filePath) {
		final File folder = new File(filePath);
		for (final File f : folder.listFiles()) {
			if (f.getName().startsWith("User Onboarding")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "User Onboarding");
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
			waitUntilWebElementIsVisible(auditGrid);
			List<WebElement> rows=auditGrid.findElements(By.tagName("tr"));
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

	public void searchUserOnBoardingRecord(String lanID) throws Exception  {
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
	
	public void searchUserOnBoardingRecordInApprovedSection(String lanID) throws Exception  {
		selectWebElement(searchLinkApprovedSec);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Lan ID");
		Thread.sleep(1000);
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		enterValueToTxtField(searchTextBox,lanID);
		selectWebElement(popupSearchBtn);
		waitForJqueryLoad(driver);
	}

	public boolean ExporttoExcelWithoutData(UserOnBoardingDetails details) throws Exception {
		searchUserOnBoardingRecord(details.getLanID());
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
			return false;
	}

	public void SortByAscending() {
		selectWebElement(FirstName.get(1));
		waitForJqueryLoad(driver);
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(FirstName.get(1));
		waitForJqueryLoad(driver);
		selectWebElement(FirstName.get(1));
		waitForJqueryLoad(driver);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

	public boolean clearAll(UserOnBoardingDetails details) throws Exception {
		selectWebElement(gridsearchLink);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Lan ID");
		Thread.sleep(1000);
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		enterValueToTxtField(searchTextBox,details.getLanID());
		selectWebElement(clearall);
		if(searchTextBox.isEnabled())
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

	public void searchwithoutextsearch(UserOnBoardingDetails details) throws Exception {
		selectWebElement(gridsearchLink);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Lan ID");
		Thread.sleep(1000);
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		selectWebElement(popupSearchBtn);	
		selectWebElement(searchClose);
	}


	public boolean verifyclearsearch() {
		selectWebElement(clearsearch.get(0));
		if(approvedgridcontent.isDisplayed())
			return true;
		else
			return false;
	}

	public boolean getErrorMsg() {
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.size()>0)
			return false;
		else
			return true;
	}

	public boolean verifyApprovedSectionData(UserOnBoardingDetails details) throws Exception {
		searchUserOnBoardingRecordApprovedData(details.getLanID());
		if(norecords.isDisplayed())
			return true; 
		else
			return false;
	}

	private void searchUserOnBoardingRecordApprovedData(String LanId) throws Exception {
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

	public List<Map<String, String>> gettable1() throws Exception {
		int item=Integer.valueOf(items.get(2).getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.get(2).getText());
		int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){

			waitUntilWebElementIsVisible(auditGrid);
			List<WebElement> rows=auditGrid.findElements(By.tagName("tr"));
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
				Thread.sleep(2000);
				waitForJqueryLoad(driver);}
		}
		return arr;
	}
	public boolean verifySearchIsNotEqualTo(String firstname) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("First Name", firstname);
		selectWebElement(searchLink);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"First Name");
		Thread.sleep(2000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
		enterValueToTxtField(searchTextBox,firstname);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable1(); 
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
		selectWebElement(searchLink);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"First Name");
		Thread.sleep(2000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		enterValueToTxtField(searchTextBox,firstname);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable1(); 
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
		selectWebElement(searchLink);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"First Name");
		Thread.sleep(2000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not contain");
		enterValueToTxtField(searchTextBox,firstname);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable1(); 
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
		selectWebElement(searchLink);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"First Name");
		Thread.sleep(2000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
		enterValueToTxtField(searchTextBox,firstname);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable1(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("First Name").toLowerCase().startsWith(firstname.toLowerCase()))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}

	public String VerifyMessage() {
		waitForJqueryLoad(driver);
		if(successmsg.isDisplayed())
			return successmsg.getText();
		else{return errorMsg.get(0).getText();}
	}

	public boolean verifySearchEndsWith(String firstname) throws Exception {
		Boolean Status=false;
		selectWebElement(searchLink);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"First Name");
		Thread.sleep(2000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
		enterValueToTxtField(searchTextBox,firstname);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable1(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("First Name").toUpperCase().endsWith(firstname.toUpperCase()))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}

	public boolean addCancelButton(UserOnBoardingDetails details) throws Exception {
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		String actualitems=items.get(2).getText();
		Thread.sleep(2000);
		selectWebElement(addNewUserOnBoardingRecord);
		waitForJqueryLoad(driver);
		selectWebElement(addNewUserOnBoardingCancelButton);
		if(actualitems.equals(items.get(2).getText()))
			return true;
		else
			return false;
	}



	public boolean VerifyAddNewUserOnBoardingStepIconsandCreateRecordWithAlltheFieldsBlank() throws Exception {
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		Thread.sleep(2000);
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
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
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
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
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
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
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
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
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
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
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
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
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
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
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
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
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


	public void selectRecord() {
		Map<String,String> map = new HashMap<>();
		waitUntilWebElementIsVisible(auditGridContent);
		List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
		List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
		selectWebElement(cols.get(0).findElement(By.id("isEnabled")));
	}

	public void selectallNonmandatoryFieldsRecord() {
		Map<String,String> map = new HashMap<>();
		waitUntilWebElementIsVisible(auditGridContent);
		for(int i=1;i<=3;i++) {
			List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			selectWebElement(cols.get(0).findElement(By.id("isEnabled")));}
	}

	public void sendForAprroval(String comments) throws Exception {
		selectWebElement(sendForApprovalBtn);
		Thread.sleep(1000);
		enterValueToTxtField(makerComments, comments);
		selectWebElement(submitMakerComments);			
	}

	public void Revert(String comments) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(revertBtn);
		Thread.sleep(2000);
		enterValueToTxtField(revertMakerComments,comments);
		selectWebElement(revertSubmitMakerComments);
		waitForJqueryLoad(driver);
	}

	public boolean verifyStatus(String status){
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Map<String,String> firstRowData=getFirstRowDatafromTable();
		return firstRowData.get("Status").equals(status);
	}

	private Map<String,String> getFirstRowDatafromTable(){
		Map<String,String> map = new HashMap<>();
		waitUntilWebElementIsVisible(auditGridContent);
		List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
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

	public void clickonReject(String comment) throws Exception{
		selectWebElement(UserOnBoardingTabs.get(1));
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

	public boolean verifyMessage() {
		waitForJqueryLoad(driver);
		return(getSuccessMessage().contains("Record approved successfully. Request ID :"));
	}

	public String getSuccessMessage() {
		waitForJqueryLoad(driver);
		if(successmsg.isDisplayed())
			return successmsg.getText();
		else{return errorMsg.get(0).getText();}

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

	public boolean verifyAuditTrail(UserOnBoardingDetails details, String transaction, String status){
		boolean stat=false;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date = simpleDateFormat.format(new Date());
		Map<String,String> firstRowData=getFirstRowDatafromTable();
		if(firstRowData.get("Transaction").equalsIgnoreCase(transaction)){
			if(firstRowData.get("Status").equalsIgnoreCase(status)){
				if(firstRowData.get("Function Name").equalsIgnoreCase("User Onboarding")){
					 if(transaction.equals("MakerCreate")||transaction.equals("MakerImport")){
					/* Map<String,String> newvalues=new HashMap<>();
	                            String[] d=firstRowData.get("New Values").split("\n");
	                            for(String e:d){
	                                String[]f=e.split(":",2);
	                                if(f.length>1){newvalues.put(f[0],f[1]);}
	                            }
	                            if(verifyNewValues(details,newvalues)){
	                                stat=true;}*/stat=true;
					}else{System.out.println("transaction Mismatch");}
				}else{System.out.println("Data mismatch:"+firstRowData.get("Function Name")+"\t"+"User Onboarding");}
			}else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+status);}
		}else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+transaction);}
		return stat;}

	public void clickonApprove(String comment) throws Exception{
		selectWebElement(UserOnBoardingTabs.get(1));
		waitForJqueryLoad(driver);
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
		waitForJqueryLoad(driver);
	}

	public void editUserOnBoardingRecord(UserOnBoardingDetails details) {
		try{selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
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

	public String verifySuccessMessage(){
		waitUntilWebElementIsVisible(successmsg);return successmsg.getText();
	}

	public boolean verifyAuditTrailUpdate(UserOnBoardingDetails details, String Transaction, String Status) {
		boolean stat=false;
		Map<String,String> firstRowData=getFirstRowDatafromTable();
		if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
			if(firstRowData.get("Status").equalsIgnoreCase(Status)){
				if(firstRowData.get("Function Name").equalsIgnoreCase("User Onboarding")){
					if(Transaction.equals("MakerUpdate")){
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
				}else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"User Onboarding");}
			}else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}
		}else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}
		return stat;
	}
	
	public boolean verifyAuditTrailDelete(UserOnBoardingDetails details, String Transaction, String Status) {
		boolean stat=false;
		Map<String,String> firstRowData=getFirstRowDatafromTable();
		if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
			if(firstRowData.get("Status").equalsIgnoreCase(Status)){
				if(firstRowData.get("Function Name").equalsIgnoreCase("User Onboarding")){
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
				}else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"User Onboarding");}
			}else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}
		}else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}
		return stat;
	}

	private boolean verifyUpdatedNewValues(UserOnBoardingDetails details, Map<String, String> newvalues) {
		Boolean Status=false;
		if(newvalues.get("Lan ID").equals(details.getLanID())){
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
		}else{System.out.println("data mismatch"+newvalues.get("Lan ID")+"\t"+details.getLanID());}
		return Status;
	}

	public void EditRecordWithoutModifyReason(UserOnBoardingDetails details) {
		try{selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		searchUserOnBoardingRecord(details.getLanID());
		waitUntilWebElementIsVisible(editBtn);
		Thread.sleep(1000);
		selectWebElement(editBtn);
		selectWebElement(firstnameTextBox);
		selectWebElement(saveButton);}catch (Exception e){e.printStackTrace();}
	}

	public boolean deleteCancel(UserOnBoardingDetails details) throws Exception {
		selectWebElement(UserOnBoardingTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makeUserOnBoardingChanges);
		Thread.sleep(1000);
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
		{selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		searchUserOnBoardingRecord(lanId);
		Thread.sleep(4000);
		btnClick(deleteBtn);
		selectWebElement(deleteReasonTextBox);
		enterValueToTxtFieldWithoutClear(deleteReasonTextBox,reason);
		selectWebElement(yesBtn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean VerifyImportUserProfilePicturePopUp() throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		Thread.sleep(2000);
		selectWebElement(ImportUserProfilePictureButton);
		waitForJqueryLoad(driver);
		boolean status=false;
		if(ImportProfilePicturesPopUpHeader.isDisplayed()) {
			if(UserAttributetoMatchPicturesdropdownLabel.isDisplayed()) {
				selectWebElement(UserAttributetoMatchPicturesdropdownbutton);
				if(LanIDAttribute.isEnabled()) {
					if(AvayaLoginTDAttribute.isEnabled()) {
						if(selectfile.isEnabled()) {
							if(uploadBtn.isEnabled()) {
								if(dragAndDropFilesHereLabel.isDisplayed()) {
									if(FileUploadMessage.isDisplayed()) {
										status=true;
									}
									else {System.out.println("FileUploadMessage is not displayed");}
								}
								else {System.out.println("dragAndDropFilesHereLabel is not dispalyed ");}
							}
							else {System.out.println("uploadBtn is disabled ");}
						}
						else {System.out.println("selectfile is disabled ");}
					}
					else {System.out.println("AvayaLoginTDAttribute is disabled ");}	 
				}
				else {System.out.println("UserAttributetoMatchPicturesdropdownLabel is not displayed ");}
			}
			else {System.out.println("ImportProfilePicturesPopUpHeader is not displayed ");} 
		}
		return status;

	}

	public void VerifyUploadwithoutAttributeandProfilePic() throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		Thread.sleep(2000);
		selectWebElement(ImportUserProfilePictureButton);
		waitForJqueryLoad(driver);
		selectWebElement(uploadBtn);
	}

	public void VerifyUploadwithoutProfilePic(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		Thread.sleep(2000);
		selectWebElement(ImportUserProfilePictureButton);
		selectWebElement(UserAttributetoMatchPicturesdropdownbutton);
		selectDropdownFromVisibleText(UserAttributetoMatchPicturesdropdownValues,details.getattribute());
		waitForJqueryLoad(driver);
		selectWebElement(uploadBtn);
	}

	public void VerifyUploadwithoutAttribute(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(ImportUserProfilePictureButton);
		Thread.sleep(2000);
		clickOnUsingActionClass(selectfile);
		//Auto It script to load zip file
		FileUploader upload= new FileUploader();
		upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getfilename());
		selectWebElement(uploadBtn);
	}

	public boolean VerifyUploadInvalidFormatProfilePicFileWithLanIdAttribute(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(ImportUserProfilePictureButton);
		selectWebElement(UserAttributetoMatchPicturesdropdownbutton);
		selectDropdownFromVisibleText(UserAttributetoMatchPicturesdropdownValues,details.getattribute());
		Thread.sleep(2000);
		clickOnUsingActionClass(selectfile);
		//Auto It script to load zip file
		FileUploader upload= new FileUploader();
		upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getfilename());
		if(filetypeerrorMsg.getText().equals("File type not allowed."))
			return true;
		else 
			return false;
	}

	public void VerifyUploadProfilePicWithAttribute(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(ImportUserProfilePictureButton);
		selectWebElement(UserAttributetoMatchPicturesdropdownbutton);
		selectDropdownFromVisibleText(UserAttributetoMatchPicturesdropdownValues,details.getattribute());
		Thread.sleep(2000);
		clickOnUsingActionClass(selectfile);
		//Auto It script to load zip file
		FileUploader upload= new FileUploader();
		upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getfilename());
		selectWebElement(uploadBtn);
	}



	public boolean VerifyRecordcount(UserOnBoardingDetails details) throws Exception{
		boolean status=false;
		waitForJqueryLoad(driver);
		System.out.println(details.getInsertedRecordCount());
		System.out.println(insertedRecordCount.getText());
		if(insertedRecordCount.getText().equals(details.getInsertedRecordCount())) {
			if(invalidRecordCount.getText().equals(details.getInavlidRecordCount())) {
				status=true;
			}
			else {System.out.println("Invalid Record Count mismatch");}
		}
		else {System.out.println("Inserted Record Count mismatch");}	
		selectWebElement(continueBtn);
		return status;
	}

	public boolean VerifyImportUserPopUp() throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		Thread.sleep(2000);
		selectWebElement(importUsersButton);
		waitForJqueryLoad(driver);
		boolean status=false;
		if(ImportUsersPopUpHeader.isDisplayed()) {
			if(messageLabel.isDisplayed()) {
				if(messageLabel1.isDisplayed()){
					if(userDetailTemplateDownLoadLink.isEnabled()) {
						if(userChannelTemplateDownLoadLink.isEnabled()) {
							if(userFeatureTemplateDownLoadLink.isEnabled()) {
								if(ImportUsersPopUpCloseButton.isEnabled()) {
									status=true;
								}
								else {System.out.println("ImportUsersPopUpCloseButton is disabled");}
							}
							else {System.out.println("userFeatureTemplateDownLoadLink is disabled");}
						}
						else {System.out.println("userChannelTemplateDownLoadLink is disabled");}
					}
					else {System.out.println("userDetailTemplateDownLoadLink is disabled");}
				}
				else {System.out.println("messageLabel1 is not displayed");}
			}
			else {System.out.println("messageLabel is not displayed");}
		}
		else {System.out.println("ImportUsersPopUpHeader is not displayed");}

		return status;


	}

	public boolean VerifyImportUserRecordcount(UserOnBoardingDetails details) throws Exception{
		boolean status=false;
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(duplicateUserRecordCount.getText().equals(details.getDuplicateRecordCount())) {
			if(InsertedUserRecordCount.getText().equals(details.getInsertedRecordCount())) {
				if(InvalidUserRecordCount.getText().equals(details.getInavlidRecordCount()))
					status=true;
				else {System.out.println("Invalid Record Count mismatch");}
			}
			else {System.out.println("Inserted Record Count mismatch");}
		}
		else {System.out.println("Duplicate Record Count mismatch");}
		selectWebElement(continueBtn);
		return status;
	}

	public boolean VerifyImportUserwithLanIDBlank(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsFileNameWithLanIDBlank();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}

	public boolean VerifyImportUserwithFirstNameBlank(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsFileNamewithFirstNameBlank();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}

	public boolean VerifyImportUserwithLastNameBlank(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsFileNamewithLastNameBlank();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}

	public boolean VerifyImportUserwithAvayaLoginIDBlank(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsFileNameAvayaLoginIdBlank();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}

	public boolean VerifyImportUserwithProfileBlank(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsFileNamewithProfileBlank();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}

	public boolean VerifyImportUserwithOrgUnitBlank(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsFileNamewithOrgUnitBlank();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}

	public boolean VerifyImportUserwithSupervisorLanIDBlank(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsFileNamewithSupervisorLanIDBlank();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}

	public boolean VerifyImportUserwithAllFieldsBlank(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		return status;

	}

	public boolean VerifyImportUserwithOnlyOneFileatTime(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(fileUploadMessage.isDisplayed())
			return true;
		else
			return false;
	}

	public boolean VerifyImportUserFiles(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		return status;

	}

	public boolean VerifyImportUserwithAllNonMandatoryFieldsBlank(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsNonMandatoryFieldsBlankFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}

	public boolean VerifyImportUserwithAccessRoleandRoleFieldsBlank(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsAccessRoleandRoleFieldsBlankFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}

	public boolean VerifyImportUserwithCRMandTextChatFieldsBlank(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsCRMandTexChatFieldsBlankFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;
	}

	public boolean VerifyImportCountryLevelSupervisor(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getCountryLevelSupervisorFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}

	public boolean VerifyImportDivisionLevelSupervisor(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getDivisionLevelSupervisorFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}

	public boolean VerifyImportDepartmentlevelSupervisor(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getDepartmentLevelSupervisorFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}

	public boolean VerifyImportTeamLevelSupervisor(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getTeamlevelSupervisorFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		return status;
	}

	public void selectallSupervisorRecord() {
		Map<String,String> map = new HashMap<>();
		waitUntilWebElementIsVisible(auditGridContent);
		for(int i=1;i<=4;i++) {
			List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			selectWebElement(cols.get(0).findElement(By.id("isEnabled")));}
	}
	
	public void selectallImportedRecord() {
		Map<String,String> map = new HashMap<>();
		waitUntilWebElementIsVisible(auditGridContent);
		for(int i=1;i<=2;i++) {
			List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			selectWebElement(cols.get(0).findElement(By.id("isEnabled")));}
	}
	/*********************************************************************************************************************************************/
	/*public void CSVReader(UserOnBoardingDetails details) throws FileNotFoundException {
	    String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
	    System.out.println("+++++++++++++++++++++Code For CSV Parser++++++++++++");
	    Map<String, Map<String, String>> CSV= Util.getCSVData(',',ChannelDetailsFile,1);
	  Iterator<String> keys = CSV.keySet().iterator();
	  while (keys.hasNext()){
	      String primaryKey= keys.next();
	      System.out.print("Emp Id: "+primaryKey);
	      System.out.print(CSV.get(primaryKey).get("Name")+" ");
	      System.out.print(CSV.get(primaryKey).get("Age")+" ");
	      System.out.print(CSV.get(primaryKey).get("City")+" ");
	      System.out.print(CSV.get(primaryKey).get("Salary")+" \n");
	  }
	    System.out.println(CSV.entrySet());
	 }
	
	public void verifyArrowDropDown(UserOnBoardingDetails details) throws Exception {
		List<Map<String, String>> maplist=getAgetSkillData(details);
		System.out.println(maplist);
		
		if(maplist.equals(maplist1))
		return true;
		else
		return false;
	}
	
	public List<Map<String,String>> getAgetSkillData(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		searchUserOnBoardingRecordInApprovedSection(details.getLanID());
		waitUntilWebElementIsVisible(pageArrowDropDown.get(0));
		selectWebElement(pageArrowDropDown.get(0));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		waitUntilWebElementIsVisible(arrowGrid.get(1));
		List<WebElement> rows=arrowGrid.get(1).findElements(By.tagName("tr"));
		List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
		String col=null;
		for(int i=1;i<rows.size();i++) {
			Map<String,String> map = new HashMap<String,String>();
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			for(int j=0;j<headers.size();j++) {
				scrollToElement(headers.get(j));
				System.out.println(headers.get(j).getText());
					col=cols.get(j).getText();
				map.put(headers.get(j).getText(),col);
			}
			arr.add(map);
			if(featuresGridNexpageButton.get(0).isEnabled()) {
				selectWebElement(featuresGridNexpageButton.get(0));
			}
	}
		return arr;
}*/
	/********************************************************************************************************************************************/

	public boolean VerifyImportMultipleUserwithFirstNameBlank(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsFileNamewithFirstNameBlank();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;
	}
	
	public boolean VerifyImportMultipleUserwithAllFieldsBlank(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		return status;

	}
	
	public boolean VerifyImportUserDetailswithInvalidOrgUnit(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailswithInavlidOrgUnitFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}
	
	public boolean VerifyImportUserDetailswithNewColumn(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailswithNewColumnFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}
	
	public boolean VerifyImportUserDetailswithUpdatedColumn(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailswithUpdatedColumnFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(uploadErrormessage.isDisplayed())
			return true;
		else
			return false;

	}
	
	public boolean VerifyImportwithoutDivisionLevelSupervisor(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getDivisionLevelSupervisorFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}

	public boolean VerifyImportwithoutDepartmentlevelSupervisor(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getDepartmentLevelSupervisorFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}

	public boolean VerifyImportwithoutTeamLevelSupervisor(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getTeamlevelSupervisorFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		return status;
	}

	public boolean VerifyImportUserwithInvalidTextChatGreetingFiles(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsCRMandTexChatFieldsBlankFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}
	
	public boolean VerifyImportUserwithInvalidRoleFile(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsAccessRoleandRoleFieldsBlankFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		return status;

	}
	
	
	public boolean VerifyImportUserwithInvalidProfileFile(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsFileNamewithProfileBlank();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}
	
	public boolean VerifyImportUserwithInvalidRoleAccessFile(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsAccessRoleandRoleFieldsBlankFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}
	
	public boolean VerifyImportUserwithAlphabetsasAvayaLoginID(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsFileNameAvayaLoginIdBlank();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		driver.navigate().refresh();
		return status;

	}
	
	public boolean VerifyImportUserwithSpecialCharectorsasAvayaLoginID(UserOnBoardingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
		waitForJqueryLoad(driver);
		selectWebElement(importUsersButton);
		Thread.sleep(2000);
		String UserDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getUserDetailsFileName();
		String ChannelDetailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getChannelDetailsFileName();
		String FeatureDeatailsFile=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\ImportUsers\\"+details.getFeatureDetailsFileName();
		selectImportFileButton.sendKeys(UserDetailsFile+"\n"+ChannelDetailsFile+"\n"+FeatureDeatailsFile);
		boolean status=VerifyImportUserRecordcount(details);
		return status;

	}
	
	public void editImportedUserOnBoardingRecord(UserOnBoardingDetails details) {
		try{selectWebElement(UserOnBoardingTabs.get(1));
		selectWebElement(makeUserOnBoardingChanges);
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
	



}
