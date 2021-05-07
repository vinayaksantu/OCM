package com.tetherfi.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.tmac.AttributeAssignmentDetails;
import com.tetherfi.model.tmac.AttributesDetails;
import com.tetherfi.model.tmac.SkillTemplateDetails;
import com.tetherfi.model.user.AgentSkillAssignmentNewDetails;
import com.tetherfi.utility.ExcelReader;

public class SkillTemplatePage extends BasePage {

	public SkillTemplatePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".ibox-title h5")
	private WebElement skillTemplate;

	@FindBy(xpath="//i[@class='fas fa-street-view']")
	private WebElement SkillTemplateLogo;

	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullScreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement restore;

	@FindBy(id="navbarheader")
	private WebElement header;

	@FindBy(xpath="//th[@class='k-header k-with-icon']")
	private List<WebElement> skillTemplatePageHeader;

	@FindBy(css="th a[class='k-header-column-menu']")
	private List<WebElement> headersDropdown;

	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;

	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;

	@FindBy(id="export")
	private WebElement exportButton;

	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;

	@FindBy(xpath="//span[@class='k-input']")
	private WebElement pagerSize;

	@FindBy(css="a[aria-label='Go to the next page']")
	private WebElement nextPageIcon;

	@FindBy(id="tgrid")
	private WebElement auditGridContent;

	@FindBy(css="#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;

	@FindBy(css=".toast-message")
	private WebElement successmsg;

	@FindBy(css = ".fa-search")
	private WebElement searchBtn;

	@FindBy(css = ".modal-body .form-inline .form-group .k-select")
	private List<WebElement> selectSearchCol;

	@FindBy(css="ul[id='1001sColumnName_listbox'] li")
	private List<WebElement> columnNameList;

	@FindBy(css="ul[id='1001sCriteria_listbox'] li")
	private List<WebElement> searchCriteriaDropDwn;

	@FindBy(id = "1001sTextToSearch")
	private WebElement searchTextBox;

	@FindBy(css = ".modal-footer .button-theme")
	private WebElement searchSearchBtn;

	@FindBy(css=".k-grid-content")
	private WebElement gridContent;

	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;

	@FindBy(xpath="//a[text()='Template Name']")
	private WebElement gridTemplateName;

	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement dropTarget;

	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupBy;

	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[4]")
	private WebElement groupByTemplateName;

	@FindBy(xpath="//a[@aria-label='Go to the last page']")
	private WebElement lastPageIcon;

	@FindBy(css=".k-pager-numbers .k-state-selected")
	private WebElement pageNumber;

	@FindBy(css="a[aria-label='Go to the first page']")
	private WebElement firstPageIcon;

	@FindBy(css="a[aria-label='Go to the previous page']")
	private WebElement previousPageIcon;

	@FindBy(css=".k-pager-sizes .k-icon")
	private WebElement pagerDropdown;

	@FindBy(css=".k-animation-container ul li")
	private List<WebElement> pageSizeListBox;

	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearall;

	@FindBy(xpath="//span[@class='k-widget k-textbox k-state-disabled']")
	private List<WebElement> searchText;

	@FindBy(xpath="//button[text()='Close']")
	private WebElement searchClose;

	@FindBy(xpath="//span[@aria-controls='1001sColumnName_listbox']")
	private WebElement selectSearchColumn;

	@FindBy(xpath="//span[@aria-owns='1001sCriteria_listbox']")
	private WebElement condition;

	@FindBy(xpath="//i[@class='fas fa-sync fa-spin']")
	private WebElement clearsearch;

	@FindBy(xpath="//button[@id='create']")
	private WebElement addNewSkillTemplateRecord;

	@FindBy(xpath="//h3[normalize-space()='Skill Template']")
	private WebElement addSkillTemplateWindowTitleprsence;

	@FindBy(xpath="//label[normalize-space()='Template Name*']")
	private WebElement templateNameLabel;

	@FindBy(xpath="//label[normalize-space()='Org. Unit*']")
	private WebElement orgUnitLabel;

	@FindBy(xpath="//span[normalize-space()='Voice Skills']")
	private WebElement voiceSkillsTab;

	@FindBy(xpath="//span[normalize-space()='Text Chat Skills']")
	private WebElement textChatSkillsTab;

	@FindBy(xpath="//span[normalize-space()='Audio Chat Skills']")
	private WebElement audioChatSkillsTab;

	@FindBy(xpath="//span[normalize-space()='Video Chat Skills']")
	private WebElement videoChatSkillsTab;

	@FindBy(xpath="//span[normalize-space()='Fax Skills']")
	private WebElement faxSkillsTab;

	@FindBy(xpath="//span[normalize-space()='Email Skills']")
	private WebElement emailSkillsTab;

	@FindBy(xpath="//span[normalize-space()='SMS Skills']")
	private WebElement smsSkillsTab;

	@FindBy(xpath="//span[normalize-space()='Email Channel Skills']")
	private WebElement emailChannelSkillsTab;

	@FindBy(xpath="//input[@id='voiceSearchBox']")
	private WebElement voiceSkillSearchTextBox;

	@FindBy(xpath="//a[@aria-label='Transfer To']")
	private List<WebElement> transferToButton;

	@FindBy(xpath="//a[@aria-label='Transfer From']")
	private List<WebElement> transferFrombutton;

	@FindBy(xpath="//button[normalize-space()='Save']")	
	private WebElement saveButton;

	@FindBy(xpath="//button[normalize-space()='Reset']")
	private WebElement resetButton;

	@FindBy(xpath="//span[contains(text(),'Select a Team')]")
	private WebElement selectTeamDropDown;

	@FindBy(css="ul[id='TeamID_listbox'] li")
	private List<WebElement> listofTeams;

	@FindBy(xpath="//input[@id='TemplateName']")
	private WebElement templatenameTextBox;

	@FindBy(xpath="//div[@id='popupDrill']//span[@aria-hidden='true'][normalize-space()='×']")
	private WebElement addSkillTemplateWindowCloseButton;

	@FindBy(css="ul[id='tabstrip_skilledit_ul'] li")
	private List<WebElement> skillTabs;

	@FindBy(css="#tabstrip_skilledit ul[role='listbox'] li")
	private List<WebElement> multipleSkills;

	@FindBy(xpath="//a[@class='k-button k-button-icon']")
	private List<WebElement> transferToSelectedList;

	@FindBy(css="div[class='swal-modal'] input")
	private WebElement skillLevel;

	@FindBy(css="div[class='swal-modal'] button")
	private WebElement okButton;

	@FindBy(xpath = "//a[@class='k-button k-button-icontext k-grid-CustomEdit']")
	private WebElement editButton;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-CustomDelete']")
	private WebElement deleteButton;

	@FindBy(xpath="//input[@id='ModifyReason1']")
	private WebElement deleteReason;

	@FindBy(css="#myWindow #noButton")
	private WebElement deleteNoButton;

	@FindBy(id="yesButton")
	private List<WebElement> deleteYesButton;

	@FindBy(xpath="//table//tbody/tr/td[2]")
	private WebElement tablerow;

	@FindBy(xpath="//input[@id='voiceSearchBox']")
	private WebElement searchVoiceSkills;

	@FindBy(xpath="//input[@id='textchatSearchBox']")
	private WebElement searchTextChatSkills;

	@FindBy(xpath="//input[@id='audiochatSearchBox']")
	private WebElement searchAudioChatSkills;

	@FindBy(xpath="//input[@id='videochatSearchBox']")
	private WebElement searchVideochatSkills;

	@FindBy(xpath="//input[@id='faxSearchBox']")
	private WebElement searchfaxSkills;

	@FindBy(xpath="//input[@id='emailSearchBox']")
	private WebElement searchemailSkills;

	@FindBy(xpath="//input[@id='smsSearchBox']")
	private WebElement searchSmsSkills;

	@FindBy(xpath="//input[@id='emailchannelSearchBox']")
	private WebElement searchemailChannelSkills;

	@FindBy(css="#tabstrip_skilledit ul[role='listbox'] li")
	private List<WebElement> SearchMultiSkillTabData;









	public boolean isSkillTemplatePageIsDisplayed() {
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		return skillTemplate.isEnabled();
	}

	public boolean verifyLogoButtonPresence() {
		boolean status=false;
		if(isElementExist(SkillTemplateLogo)){
			if(addNewSkillTemplateRecord.isDisplayed()) {
				if(exportButton.isDisplayed()) {
					status=true;
				}
				else {System.out.println("exportButton is not Displayed");}
			}
			else {System.out.println("addNewSkillTemplateRecord is not Displayed");}
		}
		else {System.out.println("SkillTemplateLogo is not Displayed");}
		return status;
	}

	public boolean maximizeWindow() {
		selectWebElement(maximize);
		waitForJqueryLoad(driver);
		if(fullScreen.isEnabled())
		{return true;}
		else
		{return false;}
	}

	public boolean minimizeWindow() {
		selectWebElement(restore);
		waitForJqueryLoad(driver);
		if(header.isDisplayed())
		{return true;}
		else
		{return false;}	
	}

	public boolean VerifySkillTemplatePageHeader() {
		waitForJqueryLoad(driver);
		List<String>ActualPageHeadrs = new ArrayList<>();
		for(int i=0;i<skillTemplatePageHeader.size();i++) {
			if(skillTemplatePageHeader.get(i).isDisplayed()) {
				String HeaderName=skillTemplatePageHeader.get(i).getText();
				ActualPageHeadrs.add(HeaderName);
			}
			else {System.out.println("surveyRuleConfigurationPageHeader is not Displayed at Index"+i);}
		}
		List<String>ExpectedPageHeadrs = new ArrayList<>();
		ExpectedPageHeadrs.add("Template Name");
		ExpectedPageHeadrs.add("Team Name");
		ExpectedPageHeadrs.add("Last Changed By");
		ExpectedPageHeadrs.add("Last Changed On");
		System.out.println(ActualPageHeadrs);
		System.out.println(ExpectedPageHeadrs);
		if(ActualPageHeadrs.equals(ExpectedPageHeadrs))
			return true;
		else
			return false;

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

	public boolean VerifyColumnsHeadersEnable() {
		boolean status=false;
		WebElement ele=headersDropdown.get(0);
		if(ele.isDisplayed()) {
			try {
				selectWebElement(ele);
				Thread.sleep(1000);
				selectWebElement(headersColumns.get(2));
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			for(int i=3; i<headersColumns.size(); i++) {
				WebElement checkbox=headersColumns.get(i).findElement(By.tagName("input"));
				checkbox.click();
				if(checkbox.isSelected()) { 
				}
				else {
					checkbox.click();
				}
				for(WebElement ele1 : headersText) {
					if(ele1.getText().equals(headersColumns.get(i).getText())) {
						status=true;
						break;
					}
				}
				if(status) {
				}
				else {
					break;
				}
			}

		}


		return status;
	}

	public boolean verifycolumnsHeaderDisabled() {
		boolean status = false;
		WebElement ele = headersDropdown.get(1);
		if (ele.isDisplayed()) {
			try {
				selectWebElement(ele);
				Thread.sleep(1000);
				selectWebElement(headersColumns.get(2));
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i =3; i < headersColumns.size(); i++) {
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
		return status;
	}

	public boolean ExportToExcelButton(String filePath) {
		final File folder=new File(filePath);
		for(final File f : folder.listFiles()) {
			if(f.getName().startsWith("Skill Template")) {
				f.delete();
			}
		}
		selectWebElement(exportButton);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath,"Skill Template");
		return Status;
	}

	public boolean VerifyExportToExcelSheet(List<Map<String,String>>maplist){
		List<Map<String,String>>UI=getData();
		System.out.println(UI);
		System.out.println(maplist);
		if(UI.equals(maplist))
			return true;
		else
			return false;
	}

	private List<Map<String,String>> getData(){
		int item=Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.getText());
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
				for(int j=2;j<headers.size();j++) {
					col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				map.remove("");
				arr.add(map);
			}
			if(k!=pages)
			{
				nextPageIcon.click();
				waitForJqueryLoad(driver);}
		}
		return arr;
	}

	public void searchSkillTemplate(String templateName) throws Exception {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		enterValueToTxtField(searchTextBox,templateName);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
	}

	public boolean VerifyExportToExcelWithoutData(SkillTemplateDetails details) throws Exception {
		searchSkillTemplate(details.getTemplatename());
		waitForJqueryLoad(driver);
		selectWebElement(exportButton);
		waitForJqueryLoad(driver);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
			return false;
	}

	public void verifySortByAscending() {
		waitUntilWebElementIsVisible(auditGridContent);
		selectWebElement(gridTemplateName);
		waitForJqueryLoad(driver);
		selectWebElement(exportButton);
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void verifySortByDescending() {
		waitUntilWebElementIsVisible(auditGridContent);
		selectWebElement(gridTemplateName);
		selectWebElement(gridTemplateName);
		waitForJqueryLoad(driver);
		selectWebElement(exportButton);
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean groupBy() {
		DragandDrop(gridTemplateName,dropTarget);
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();	
		}
		if(groupBy.getText().split(": ")[1].equals(groupByTemplateName.getText()))
			return true;
		else
			return false;

	}

	public boolean verifyArrowMoveForPreviousAndNextPage() throws Exception{
		waitForJqueryLoad(driver);
		boolean status=false;
		if(!nextPageIcon.getAttribute("class").contains("k-state-disabled")){
			int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(nextPageIcon);
			Thread.sleep(1000);
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(previousPageIcon);
			Thread.sleep(1000);
			int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			if(nextnumber==(pagenumber+1) && pagenumber==previousnumber){status=true;}
		}else{
			System.out.println("previous and next page icon disabled");status=true;
		}
		return status;
	}
	public boolean verifyArrowMoveForFirstAndLastPage() throws Exception{
		waitForJqueryLoad(driver);
		boolean status=false;
		if(!lastPageIcon.getAttribute("class").contains("k-state-disabled")){
			int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(lastPageIcon);
			Thread.sleep(1000);
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(firstPageIcon);
			Thread.sleep(1000);
			int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			if(nextnumber>pagenumber && pagenumber==previousnumber){status=true;}
		}else{
			System.out.println("previous and next page icon disabled");status=true;
		}
		return status;
	}

	public boolean verifyTotalNumberOfItemsPerPageDetails(){
		waitForJqueryLoad(driver);
		String item = items.getText();
		return item.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
	}

	public boolean verifyNumberOfItemsPerPage() {
		waitForJqueryLoad(driver);
		boolean status = false;
		try {
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

		} catch (Exception e) {
			e.printStackTrace();
		} return status;
	}

	public boolean clearAll(SkillTemplateDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
		enterValueToTxtField(searchTextBox,details.getTemplatename());
		selectWebElement(clearall);
		Thread.sleep(2000);
		if(searchText.get(3).isDisplayed())
			return true;
		else
			return false;
	}
	public boolean verifyclose() {
		selectWebElement(searchClose);
		if(gridContent.isDisplayed())
			return true;
		else
			return false;
	}

	public void searchwithoutextsearch(SkillTemplateDetails details) throws Exception {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
		selectWebElement(searchSearchBtn);	
		selectWebElement(searchClose);
	}

	public boolean verifyclearsearch() {
		selectWebElement(clearsearch);
		if(gridContent.isDisplayed())
			return true;
		else
			return false;
	}

	public boolean verifyinvalidsearchwithwrongdata(SkillTemplateDetails details) throws Exception {
		searchSkillTemplate(details.getTemplatename());
		if(norecords.isDisplayed())
			return true; 
		else
			return false;
	}

	public String VerifyMessage() {
		waitForJqueryLoad(driver);
		if(successmsg.isDisplayed())
			return successmsg.getText();
		else{return errorMsg.get(0).getText();}
	}

	public boolean verifySearchIsnotEqualTo(String templateName) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Template Name", templateName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
		enterValueToTxtField(searchTextBox,templateName);		
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

	public boolean verifySearchContains(String templateName) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Template Name", templateName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		enterValueToTxtField(searchTextBox,templateName);		
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

	public boolean verifySearchDoesNotContains(String templateName) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Template Name", templateName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not Contain");
		enterValueToTxtField(searchTextBox,templateName);		
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

	public boolean verifySearchStartsWith(String templateName) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Template Name", templateName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
		enterValueToTxtField(searchTextBox,templateName);		
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

	public boolean verifySearchEndsWith(String templateName) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Template Name", templateName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
		enterValueToTxtField(searchTextBox,templateName);		
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
	public List<Map<String, String>> gettable() {
		int item=Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.getText());
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
					/*if(headers.get(j).getText().equals("Last Changed On")){
						col=cols.get(j).getText().substring(11);
						}
					else*/
					col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				map.remove("");
				arr.add(map);
			}
			if(k!=pages)
			{
				nextPageIcon.click();
				waitForJqueryLoad(driver);}
		}
		return arr;
	}

	public boolean VerifyPresenceOfAddNewSkillTemplateWindow() {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSkillTemplateRecord);
		waitForJqueryLoad(driver);
		boolean status=false;
		if(addSkillTemplateWindowTitleprsence.isDisplayed()) {
			if(templateNameLabel.isDisplayed()) {
				if(orgUnitLabel.isDisplayed()) {
					if(voiceSkillsTab.isEnabled()){
						if(textChatSkillsTab.isEnabled()) {
							if(audioChatSkillsTab.isEnabled()) {
								if(videoChatSkillsTab.isEnabled()) {
									if(faxSkillsTab.isEnabled()) {
										if(emailSkillsTab.isEnabled()) {
											if(smsSkillsTab.isEnabled()) {
												if(emailChannelSkillsTab.isEnabled()) {
													if(voiceSkillSearchTextBox.isDisplayed()) {
														if(transferToButton.get(0).isDisplayed()) {
															if(transferFrombutton.get(0).isDisplayed()) {
																if(saveButton.isEnabled()) {
																	if(resetButton.isEnabled()) {
																		status=true;
																	}
																	System.out.println("resetButton is Disabled");
																}
																System.out.println("saveButton is Disabled");
															}
															System.out.println("transferFrombutton is Displayed");
														}
														System.out.println("transferToButton is not Displayed");
													}
													System.out.println("voiceSkillSearchTextBox is Disabled");
												}
												System.out.println("emailChannelSkillsTab is Disabled");	
											}
											System.out.println("smsSkillsTab is Disabled");
										}
										System.out.println("emailSkillsTab is Disabled");
									}
									System.out.println("faxSkillsTab is  Disabled");
								}
								System.out.println("videoChatSkillsTab is Disabled");
							}
							System.out.println("audioChatSkillsTab is Disabled");
						}
						System.out.println("textChatSkillsTab is Disabled");
					}
					System.out.println("voiceSkillsTab is Disabled");
				}
				System.out.println("orgUnitLabel is not Displayed");
			}
			System.out.println("templateNameLabel is not Dislpaled");
		}
		System.out.println("addSkillTemplateWindowTitleprsence");
		return status;
	}

	public boolean verifyTeamNameswithDataBase(String query) {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSkillTemplateRecord);
		waitForJqueryLoad(driver);
		selectWebElement(selectTeamDropDown);
		List<String>database=database1(query);
		System.out.println(query);
		System.out.println(database);
		List<String>UI=getTeamNames();
		System.out.println(UI);
		if(UI.equals(database))
			return true;
		else
			return false;

	}

	public List<String> getTeamNames() {
		List<String>arr = new ArrayList<>();
		for(int i=0;i<listofTeams.size();i++) {
			String teamName=listofTeams.get(i).getText();
			arr.add(teamName);
		}
		Collections.sort(arr, String.CASE_INSENSITIVE_ORDER);
		return arr;
	}

	public void addNewSkillTemplateWithoutTemplateName(SkillTemplateDetails details) {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSkillTemplateRecord);
		waitForJqueryLoad(driver);
		selectWebElement(selectTeamDropDown);
		selectDropdownFromVisibleText(listofTeams,details.getOrgUnit());
		selectWebElement(saveButton);
	}

	public void addNewSkillTemplateWithoutOrgUnit(SkillTemplateDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSkillTemplateRecord);
		waitForJqueryLoad(driver);
		selectWebElement(templatenameTextBox);
		enterValueToTxtField(templatenameTextBox,details.getTemplatename());
		selectWebElement(saveButton);
	}

	public void addNewSkillTemplateWithoutSkill(SkillTemplateDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSkillTemplateRecord);
		waitForJqueryLoad(driver);
		selectWebElement(templatenameTextBox);
		enterValueToTxtField(templatenameTextBox,details.getTemplatename());
		selectWebElement(selectTeamDropDown);
		selectDropdownFromVisibleText(listofTeams,details.getOrgUnit());
		selectWebElement(saveButton);
	}

	public boolean VerifyAddSkillTemplateWindowCloseButton() {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSkillTemplateRecord);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(addSkillTemplateWindowTitleprsence);
		selectWebElement(addSkillTemplateWindowCloseButton);
		boolean status=false;
		try {
			if(addSkillTemplateWindowTitleprsence.isDisplayed())
				status=false;
			else {
				System.out.println("Add SkillTemplate Window Closed");
				status=true;
			}
		}

		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}	

	public void selectSkillTab(String skillType){
		for(WebElement ele:skillTabs){
			scrollToElement(ele);
			if(ele.getText().equalsIgnoreCase(skillType)){
				selectWebElement(ele);break;
			}
		}
	}

	public void selectmultipleSkills(String skillName, String skillsLevel) throws Exception{
		String[] a=skillName.split(",");
		String[] b=skillsLevel.split(",");
		for(String attribute:a){
			for(WebElement ele:multipleSkills){
				if(ele.getText().equalsIgnoreCase(attribute)){
					selectWebElement(ele);
					waitForJqueryLoad(driver);
					selectWebElement(transferToSelectedList.get(0));
					break;
				}
			}
			enterValueToTxtField(skillLevel,b[Arrays.asList(a).indexOf(attribute)]);
			selectWebElement(okButton);
		}
	}

	public void verifyAddnewSkillTemplate(SkillTemplateDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSkillTemplateRecord);
		waitForJqueryLoad(driver);
		selectWebElement(templatenameTextBox);
		enterValueToTxtField(templatenameTextBox,details.getTemplatename());
		selectWebElement(selectTeamDropDown);
		selectDropdownFromVisibleText(listofTeams,details.getOrgUnit());
		selectSkillTab(details.getSkillType());
		System.out.println(details.getSkills());
		selectmultipleSkills(details.getSkills(),details.getSkillLevels());
		scrollToElement(saveButton);
		selectWebElement(saveButton); 
	}

	public void EditSkillTemplate(SkillTemplateDetails details) throws Exception {
		searchSkillTemplate(details.getTemplatename());
		selectWebElement(editButton);
		selectSkillTab(details.getSkillType());
		selectmultipleSkills(details.getSkills(),details.getSkillLevels());
		scrollToElement(saveButton);
		selectWebElement(saveButton);
	}

	public boolean DeleteCancel(SkillTemplateDetails details) throws Exception {
		searchSkillTemplate(details.getTemplatename());
		selectWebElement(deleteButton);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enterValueToTxtFieldWithoutClear(deleteReason,details.getDeleteReason());
		selectWebElement(deleteNoButton);
		waitForJqueryLoad(driver);
		if(tablerow.getText().equals(details.getTemplatename()))
		{return true;}
		else
			return false;

	}

	public void DeleteWithoutReason(SkillTemplateDetails details) throws Exception {
		searchSkillTemplate(details.getTemplatename());
		selectWebElement(deleteButton);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(deleteYesButton.get(0));
		waitForJqueryLoad(driver);
		selectWebElement(deleteNoButton);

	}

	public void DeleteRecord(SkillTemplateDetails details) throws Exception {
		searchSkillTemplate(details.getTemplatename());
		selectWebElement(deleteButton);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enterValueToTxtFieldWithoutClear(deleteReason,details.getDeleteReason());
		selectWebElement(deleteYesButton.get(0));

	}

	public void SearchVoiceSkillsToAssign(SkillTemplateDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSkillTemplateRecord);
		waitForJqueryLoad(driver);
		selectSkillTab(details.getSkillType());
		enterValueToTxtField(searchVoiceSkills,details.getSkills());

	}
	
	public void SearchTextChatSkillsToAssign(SkillTemplateDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSkillTemplateRecord);
		waitForJqueryLoad(driver);
		selectSkillTab(details.getSkillType());
		enterValueToTxtField(searchTextChatSkills,details.getSkills());

	}
	
	public void SearchAudioChatSkillsToAssign(SkillTemplateDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSkillTemplateRecord);
		waitForJqueryLoad(driver);
		selectSkillTab(details.getSkillType());
		enterValueToTxtField(searchAudioChatSkills,details.getSkills());

	}
	
	public void SearchVideoChatSkillsToAssign(SkillTemplateDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSkillTemplateRecord);
		waitForJqueryLoad(driver);
		selectSkillTab(details.getSkillType());
		enterValueToTxtField(searchVideochatSkills,details.getSkills());

	}
	
	public void SearchFaxSkillsToAssign(SkillTemplateDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSkillTemplateRecord);
		waitForJqueryLoad(driver);
		selectSkillTab(details.getSkillType());
		enterValueToTxtField(searchfaxSkills,details.getSkills());

	}
	
	public void SearchSMSSkillsToAssign(SkillTemplateDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSkillTemplateRecord);
		waitForJqueryLoad(driver);
		selectSkillTab(details.getSkillType());
		enterValueToTxtField(searchSmsSkills,details.getSkills());

	}
	
	public void SearchEmailSkillsToAssign(SkillTemplateDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSkillTemplateRecord);
		waitForJqueryLoad(driver);
		selectSkillTab(details.getSkillType());
		enterValueToTxtField(searchemailSkills,details.getSkills());

	}
	
	public void SearchEmailChannelSkillsToAssign(SkillTemplateDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSkillTemplateRecord);
		waitForJqueryLoad(driver);
		selectSkillTab(details.getSkillType());
		enterValueToTxtField(searchemailChannelSkills,details.getSkills());

	}

	public boolean verifySearchedMultiTabSkillData(SkillTemplateDetails details){
		waitForJqueryLoad(driver);
		selectSkillTab(details.getSkillType());
		boolean status=false;
		for(WebElement ele:SearchMultiSkillTabData) {
			System.out.println(ele.getText());
			if(ele.getText().equalsIgnoreCase(details.getSkills()))
				status= true;
		}
		return status;

	}
	
}
