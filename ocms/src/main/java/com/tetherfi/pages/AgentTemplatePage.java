package com.tetherfi.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.tmac.AgentTemplateDetails;

public class AgentTemplatePage extends BasePage {

	public AgentTemplatePage(WebDriver driver) {
		super(driver);
	}


	@FindBy(css=".ibox-title h5")
	private WebElement agentTemplate;

	@FindBy(xpath="//i[@class='fal fa-portrait']")
	private WebElement agentTemplateLogo;

	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullScreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement restore;

	@FindBy(id="navbarheader")
	private WebElement header;

	@FindBy(xpath="//th[@class='k-header k-with-icon']")
	private List<WebElement> agentTemplatePageHeader;

	@FindBy(css="th a[class='k-header-column-menu']")
	private List<WebElement> headersDropdown;

	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;

	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;

	@FindBy(id="export")
	private List<WebElement> exportButton;

	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private List<WebElement> items;

	@FindBy(xpath="//span[@class='k-input']")
	private List<WebElement> pagerSize;

	@FindBy(css="a[aria-label='Go to the next page']")
	private List<WebElement> nextPageIcon;

	@FindBy(xpath="//div[@id='tabstripslot']/div")
	private List<WebElement> auditGridContent;

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
	private List<WebElement> gridContent;

	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;

	@FindBy(xpath="//a[text()='Template Name']")
	private List<WebElement> gridTemplateName;

	@FindBy(xpath="//a[normalize-space()='Screen Name']")
	private WebElement gridScreenName;

	@FindBy(xpath="//div[@data-role='droptarget']")
	private List<WebElement> dropTarget;

	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupBy;

	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[5]")
	private WebElement groupByTemplateNameInTemplateTab;

	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
	private WebElement groupByTemplateNameInTemplateScreenTab;

	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[5]")
	private WebElement groupByTemplateNameInTemplateWidgetScreenTab;

	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[4]")
	private WebElement groupByTemplateNameInTemplateAuxCodesTab;

	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
	private WebElement groupByTemplateNameInTemplateOpHoursTab;

	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[4]")
	private WebElement groupByTemplateNameInTemplateFeaturesTab;

	@FindBy(xpath="//a[@aria-label='Go to the last page']")
	private List<WebElement> lastPageIcon;

	@FindBy(css=".k-pager-numbers .k-state-selected")
	private List<WebElement> pageNumber;

	@FindBy(css="a[aria-label='Go to the first page']")
	private List<WebElement> firstPageIcon;

	@FindBy(css="a[aria-label='Go to the previous page']")
	private List<WebElement> previousPageIcon;

	@FindBy(css=".k-pager-sizes .k-icon")
	private List<WebElement> pagerDropdown;

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

	@FindBy(xpath="//span[normalize-space()='Template Screen']")
	private WebElement templateScreenTab;

	@FindBy(xpath="//button[normalize-space()='Add New Template Screen Record']")
	private WebElement addNewTemplateScreenRecord;

	@FindBy(xpath="//span[normalize-space()='Template Screen Widgets']")
	private WebElement templateScreenWidgetsTab;

	@FindBy(xpath="//span[normalize-space()='Template Auxcodes']")
	private WebElement templateAuxCodesTab; 

	@FindBy(xpath="//button[normalize-space()='Add New Template Aux codes Record']")
	private WebElement addNewTemplateAuxCodesRecord;

	@FindBy(xpath="//span[normalize-space()='Template OpHours']")
	private WebElement templateOpHoursTab;

	@FindBy(xpath="//span[normalize-space()='Template Features']")
	private WebElement templateFeatures;			
	
	@FindBy(xpath="//button[normalize-space()='Add New Template Screen Widget Record']")
	private WebElement addNewTemplateScreenWidgetRecord;


	@FindBy(id="create")
	private List<WebElement> addButton;

	@FindBy(xpath="//button[text()='Add New Templates Record']")
	private WebElement addNewTemplateRecordBtn;

	@FindBy(xpath="//span[text()='Templates']")
	private WebElement AgntTmpPopupLbl;
	
	@FindBy(xpath="//div[@class='k-edit-form-container']")
	private WebElement editPopUp;

	@FindBy(xpath="//div[@id='toast-container']")
	private WebElement toastid;

	@FindBy(xpath="//div[@class='toast toast-success']")
	private WebElement toastSuccess;

	@FindBy(xpath="//div[text()='Record inserted Successfully']")
	private WebElement successmsg1;

	@FindBy(css="#gridDetails")
	private WebElement drillgridContent;


	@FindBy(css="a[aria-label='Close']")
	private List<WebElement> closeicon;


	@FindBy(xpath="//div/ul/li[2]/span[2]")
	private List<WebElement> TemplateScreenTab;

	@FindBy(xpath=" //div[@class='ibox-tools']")
	private WebElement header1;

	@FindBy(xpath=" //button[normalize-space()='Add New Template Screen Widget Record']")
	private WebElement addNewWigetRecordBtn;


	@FindBy(xpath="//span[@class='k-input k-readonly']")
	private WebElement OrgunitDropdown;

	@FindBy(css=".k-treeview-lines li div")
	private List<WebElement> teamList;

	@FindBy(xpath="//input[@id='name']")
	private WebElement Templatename;

	@FindBy(xpath="//span[contains(text(),'Select the Theme')]")
	private WebElement themeoptions;

	@FindBy(xpath="//span[@aria-owns='theme_listbox']")
	private WebElement updatedtheme; 

	@FindBy(css="ul[id='theme_listbox'] li")
	private List<WebElement> themeDropDwn;
	
	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel']")
	private WebElement CancelButton;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-primary k-grid-update']")
	private WebElement saveBtn;

	//@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-edit k-state-border-down']")
	// private WebElement editBtn;

	//@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-edit']")
	// private List<WebElement> editBtn;

	@FindBy(css="#tgrid .k-grid-edit")
	private WebElement EditBtnTempTab;


	@FindBy(css="#tdrillGrid .k-grid-edit")
	private WebElement EditBtnScreenTab;

	@FindBy(css="#tdrillgrid .k-grid-edit")
	private WebElement EditBtnAuxTab;

	@FindBy(css="#tdrillgrid2 .k-grid-edit")
	private WebElement EditBtnWidgetTab;
	
	@FindBy(css="#tdrillgrid3 .k-grid-edit")
	private WebElement EditFeaturesTab;

	@FindBy(xpath="//input[@id='ModifyReason']")
	private WebElement ModifyReasontext;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-primary k-grid-update']")
	private WebElement editSavebtn;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel']")
	private WebElement editcancel;

	@FindBy(css=".k-edit-form-container")
	private List<WebElement> editFormContainer;

	//@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-CustomDelete']")
	//private List<WebElement> deleteBtn;

	@FindBy(css="#tgrid .k-grid-CustomDelete")
	private WebElement deleteBtnTempTab;

	@FindBy(css="#tdrillGrid .k-grid-CustomDelete")
	private WebElement deleteBtnScreentab;

	@FindBy(css="#tdrillgrid .k-grid-CustomDelete")
	private WebElement deleteBtnAuxtab;

	@FindBy(css="#tdrillgrid2 .k-grid-CustomDelete")
	private WebElement deleteBtnWidgettab;

	@FindBy(css="#gridDetails .k-grid-CustomDelete")
	private WebElement deleteBtnOpHrstab;
	
	@FindBy(css="#tdrillgrid3 .k-grid-CustomDelete")
	private WebElement deleteBtnFeaturesTab;


	@FindBy(xpath="//div[@id='myScreenTemplate']//div//div[@id='yesButton']")
	private WebElement deleteYesScreentab;

	@FindBy(xpath="//div[@id='myWidgetTemplate']//div//div[@id='yesButton']")
	private WebElement deleteYesWidgetab;
	
	@FindBy(xpath="//div[@id='myFeatureTemplate']//div//div[@id='yesButton']")
	private WebElement deleteYesFeaturesTab;


	@FindBy(xpath="//input[@id='CustomTemplateModifyReason']")
	private WebElement deleteReasonTxtBox;

	@FindBy(xpath="//input[@id='CustomScreenModifyReason']")
	private WebElement deleteReasonTxtBoxScreenTab;

	@FindBy(xpath="//input[@id='CustomAuxModifyReason']")
	private WebElement deleteReasonTxtBoxAuxTab;

	@FindBy(xpath="//input[@id='CustomWidgetModifyReason']")
	private WebElement deleteReasonTxtBoxWidgetTab;

	@FindBy(xpath="//input[@id='CustomOphourModifyReason']")
	private WebElement deleteReasonTxtBoxOphrs;
	
	@FindBy(xpath="//input[@id='CustomFeatureModifyReason']")
	private WebElement deleteReasonTxtFeaturesTab;


	@FindBy(xpath="//div[@id='myTemplate1']//div//div[@id='yesButton']")
	private WebElement deleteYesBtn;

	@FindBy(xpath="//div[@id='myScreenTemplate']//div//div[@id='yesButton']")
	private WebElement deleteYesBtnSreentab;

	@FindBy(xpath="//div[@id='myAuxTemplate']//div//div[@id='yesButton']")
	private WebElement deleteYesBtnAuxtab;

	@FindBy(xpath="//div[@id='myOPhourTemplate']//div//div[@id='yesButton']")
	private WebElement deleteYesBtnOPtab;

	@FindBy(xpath="//div[@id='myTemplate1']//div//div[@id='noButton']")
	private WebElement deleteNoBtn;

	@FindBy(xpath="//div[@id='myScreenTemplate']//div//div[@id='noButton']")
	private WebElement deleteNoBtnScreentb;

	@FindBy(xpath="//div[@id='myAuxTemplate']//div//div[@id='noButton']")
	private WebElement deleteNoBtnAuxtab;

	@FindBy(xpath="//div[@id='myWidgetTemplate']//div//div[@id='noButton']")
	private WebElement deleteNoBtnWidgettab;

	@FindBy(xpath="//div[@id='myOPhourTemplate']//div//div[@id='noButton']")
	private WebElement deleteNoBtnOptab;
	
	@FindBy(xpath="//div[@id='myFeatureTemplate']//div//div[@id='noButton']")
	private WebElement deleteNoBtnFeaturesTab;


	@FindBy(xpath="//div[@class='k-widget k-window']")
	private List<WebElement> deletecontainer;

	@FindBy(xpath="//button[normalize-space()='Add New Template Screen Record']")
	private WebElement addnewscreenRecordBtn;
	

	@FindBy(xpath="//a[@class='k-button k-flat k-button-icon k-window-action']")
	private List<WebElement> screenpopupCloseBtn;

	@FindBy(xpath="//div[@class='k-widget k-window k-state-focused']//span[@class='k-window-title'][normalize-space()='Template Screen']")
	private WebElement screenpopUpTitle;

	@FindBy(xpath="//div[@class='k-widget k-window k-state-focused']//span[@class='k-window-title'][normalize-space()='Template Auxcodes']")  
	private WebElement screenpopUpTitleAux;

	@FindBy(xpath="//div[@class='k-widget k-window k-state-focused']//span[@class='k-window-title'][normalize-space()='Template Screen Widgets']")
	private WebElement widgetpopupTitle;
	
	@FindBy(xpath="//div[@class='k-widget k-window k-state-focused']//span[@class='k-window-title'][normalize-space()='Template Features']")
	private WebElement templateFeaturesPopupTitle;


	@FindBy(xpath="//span[contains(text(),'Select a Template')]")
	private WebElement tempnameDropdown;

	//@FindBy(xpath="//select[@id='ScreenList']")
	//private WebElement screenListbox;

	//@FindBy(xpath="/html/body/div[43]/div[1]/div/a")
	//private WebElement screenpopclose;

	@FindBy(css="ul[id='AuxCode'] li")
	private List<WebElement> auxcode;

	//SelectedScreentList

	@FindBy(css="ul[id='TemplateID_listbox'] li")
	private List<WebElement> tempnameListbox;
	
	@FindBy(css="ul[id='TemplateFeatureRecord_listbox'] li")
	private List<WebElement> templateFeaturesTemplateNameListBox;

	@FindBy(css="ul[id='ScreenList'] li")
	private List<WebElement> sceenListbox1; 

	@FindBy(css="ul[id='SelectedScreentList'] li")
	private List<WebElement> selectedListbox1;

	@FindBy(xpath="//a[@title='Transfer To']")
	private WebElement transferToicon;
	
	@FindBy(xpath="//a[@title='Transfer From']")
	private WebElement transferFromIcon;

	@FindBy(xpath="//a[@title='Transfer All To']")
	private WebElement transferAlltoicon;

	@FindBy(xpath="//a[@title='Transfer All From']")
	private WebElement transferAllfromIcon;

	@FindBy(xpath=" //a[@title='Transfer From']")
	private WebElement transferfromIcon;

	//@FindBy(xpath="/html/body/div[43]/div[2]/div/div[2]/div[3]/div[1]/div[2]/ul")
	@FindBy(xpath="//*[@id='ScreenList']")
	private WebElement Screenlist_listbox;


	@FindBy(xpath="/html/body/div[43]/div[2]/div/div[2]/div[3]/div[2]/div/ul")
	private WebElement Selectedscreen_listbox;

	@FindBy(css="ul[class='k-reset k-list'] li")
	private List<WebElement> screenListbox;

	@FindBy(css="ul[class='k-reset k-list'] li")
	private List<WebElement> auxListbox;

	@FindBy(css="ul[class='k-reset k-list'] li")
	private List<WebElement> WidgetListbox;

	@FindBy(css="ul[id='AuxCode'] li")
	private List<WebElement> auxListbox2;

	@FindBy(css="ul[id='Widget'] li")
	private List<WebElement> widgetList;

	@FindBy(css="ul[id='SelectedWidget'] li")
	private List<WebElement> selectedWidgetList;

	@FindBy(css="ul[id='SelectedAuxCode'] li")
	private List<WebElement> selectedAuxList;

	@FindBy(xpath="//*[@id='AuxCode']")
	private WebElement axuList1;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-primary k-grid-update']")
	private WebElement auxtabSavebtn;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-primary k-grid-update']")
	private WebElement widgettabaddSavebtn;

	@FindBy(css="ul[id='TemplateAuxID_listbox'] li")
	private List<WebElement> tempnamelistAuxtab;

	@FindBy(css="ul[id='TemplateWidgetID_listbox'] li")
	private List<WebElement> tempnameListWidgetTab;

	@FindBy(xpath="//span[contains(text(),'Select a Screen')]")
	private WebElement screennameDropdowninWidget;

	@FindBy(css="ul[id='ScreenName_listbox'] li")
	private List<WebElement> screennameListWidgetTab;

	@FindBy(xpath="//td[@data-field='name']")
	private List<WebElement> TemplatenameColumnOphoursTab;

	@FindBy(xpath="//button[normalize-space()='Add New Row']")
	private WebElement AddnewrowBtn;

	@FindBy(xpath="//span[contains(text(),'Select a WeekDay')]")
	private WebElement selectdropdownWeekday;

	@FindBy(css="ul[id='weekDay_listbox'] li")
	private List<WebElement> weekdayList;

	@FindBy(xpath="//td[@data-field='fromTime']")
	private WebElement fromtimedataField;

	@FindBy(xpath="//td[@class='editableFiled']")
	private WebElement toTimedataField;

	@FindBy(xpath="//span[@aria-label='select']//span[@class='k-icon k-i-clock']")
	private WebElement clockicon;

	@FindBy(css="ul[id='fromTime_timeview'] li")
	private List<WebElement> fromTimeList;

	@FindBy(css="ul[id='toTime_timeview'] li")
	private List<WebElement> toTimeList;

	@FindBy(xpath="//a[@id='createone']")
	private WebElement SavebtnOphrsTab;

	@FindBy(xpath="//a[normalize-space()='Cancel changes']")
	private WebElement CancelbtnOphrsTab;

	@FindBy(xpath="//input[@id='ModifyReasonUser']")
	private WebElement modifyReasonOphrsTab;

	@FindBy(xpath=" //div[@id='myWindowUser']//div//div[@id='yesButton']")
	private WebElement modifyReasonYesBtnOphrsTab;

	@FindBy(xpath="//div[@id='tabstripslot']/div")
	private WebElement auditGridContent1;

	@FindBy(css="a[aria-label='Go to the next page']")
	private WebElement nextPageIcon1;

	@FindBy(css=".k-i-more-vertical")
	private List<WebElement> headersDropdown1;

	@FindBy(css=".k-sort-asc")
	private List<WebElement> sortAscending;

	@FindBy(xpath="//a[@href='/UI/AgentTemplate/GetTemplateGridData?usergrid-sort=name-asc']")
	private WebElement Sorting;

	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items1;

	@FindBy(xpath="//span[@class='k-input']")
	private WebElement pagerSize1;
	
	@FindBy(xpath="//*[@id=\"grid\"]/div[4]/table/tbody/tr/td[4]")
	private WebElement templateTabTableRow;
	
	@FindBy(xpath="//*[@id=\"drillGrid\"]/div[4]/table/tbody/tr/td[2]")
	private WebElement screenTabTableRow;
	
	@FindBy(xpath="//*[@id=\"drillgrid\"]/div[4]/table/tbody/tr/td[3]")
	private WebElement auxcodesTableRow;
	
	@FindBy(xpath="//*[@id=\"drillgrid3\"]/div[4]/table/tbody/tr[1]/td[3]")
	private WebElement featuresTableRow;
	
	@FindBy(xpath="//a[text()='Template Name']")
	private List<WebElement> templateNameColumn;
	
	@FindBy(xpath="//button[normalize-space()='Add New Template Feature Record']")
	private WebElement addNewTemplateFeaturesRecordButton;
	
	@FindBy(id="IsScreenCaptureEnabled")
	private WebElement isScreenCaptureEnabled;
	
	@FindBy(id="IsTRSCameraPictureEnabled")
	private WebElement isTRSCameraPictureEnabled ;
	
	@FindBy(id="IsLocationEnabled")
	private WebElement isLocationEnabled;
	
	@FindBy(id="IsFaceAuthenticationEnabled")
	private WebElement isFaceAuthenticationEnabled;
	
	@FindBy(id="TRS_AUTO_UPDATE_INTERVAL")
	private WebElement isTRS_AUTO_UPDATE_INTERVAL;
	
	@FindBy(id="TRS_LOG_UPLOAD_URL")
	private WebElement isTRS_LOG_UPLOAD_URL;

	public boolean isAgentemplatePageIsDisplayed() {
		//waitForLoad(driver);
		waitForJqueryLoad(driver);
		return agentTemplate.isEnabled();
	}



	public boolean verifyLogo() {
		if(isElementExist(agentTemplateLogo))
		{return true;}
		else
		{return false;}
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

	public boolean VerifyAgnetTemplatePageTemplateTabHeader() {
		waitForJqueryLoad(driver);
		List<String>ActualPageHeadrs = new ArrayList<>();
		for(int i=0;i<agentTemplatePageHeader.size();i++) {
			if(agentTemplatePageHeader.get(i).isDisplayed()) {
				String HeaderName=agentTemplatePageHeader.get(i).getText();
				ActualPageHeadrs.add(HeaderName);
			}
			else {System.out.println("surveyRuleConfigurationPageHeader is not Displayed at Index"+i);}
		}
		List<String>ExpectedPageHeadrs = new ArrayList<>();
		ExpectedPageHeadrs.add("Template Name");
		ExpectedPageHeadrs.add("Theme Options");
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

	public boolean ExportToExcelButton(String filePath,int i) {
		final File folder=new File(filePath);
		for(final File f : folder.listFiles()) {
			if(f.getName().startsWith("Agent Template")) {
				f.delete();
			}
		}
		selectWebElement(exportButton.get(i));
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath,"Agent Template");
		return Status;
	}

	public boolean VerifyExportToExcelSheet(List<Map<String,String>>maplist,int x){
		waitForJqueryLoad(driver);
		System.out.println(x);
		List<Map<String,String>>UI=getData(x);
		System.out.println(UI);
		System.out.println(maplist);
		if(UI.equals(maplist))
			return true;
		else
			return false;
	}

	private List<Map<String,String>> getData(int x){
		int item=Integer.valueOf(items.get(x).getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.get(x).getText());
		int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){
			waitUntilWebElementIsVisible(auditGridContent.get(x));
			List<WebElement> rows=auditGridContent.get(x).findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			String col=null;
			for(int i=1;i<rows.size();i++) {
				Map<String,String> map = new HashMap<String,String>();
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				for(int j=0;j<headers.size();j++) {
					col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				map.remove(" ");
				map.remove("");
				arr.add(map);
			}
			if(k!=pages)
			{
				nextPageIcon.get(x).click();
				waitForJqueryLoad(driver);}
		}
		return arr;
	}

	public void searchAgentTemplate(String templateName,int x) throws Exception {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		enterValueToTxtField(searchTextBox,templateName);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent.get(x));
	}

	public void searchAgentTemplateScreenName(String screenName,int x) throws Exception {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Screen Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		enterValueToTxtField(searchTextBox,screenName);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent.get(x));
	}

	public boolean VerifyExportToExcelWithoutData(AgentTemplateDetails details,int i) throws Exception {
		searchAgentTemplate(details.getTemplatename(),i);
		waitForJqueryLoad(driver);
		selectWebElement(exportButton.get(i));
		waitForJqueryLoad(driver);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
			return false;
	}



	public void verifySortByAscending(int i) {
		waitUntilWebElementIsVisible(auditGridContent.get(i));
		selectWebElement(gridTemplateName.get(i));
		waitForJqueryLoad(driver);
		selectWebElement(exportButton.get(i));
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void verifySortByDescending(int i) {
		waitUntilWebElementIsVisible(auditGridContent.get(i));
		selectWebElement(gridTemplateName.get(i));
		selectWebElement(gridTemplateName.get(i));
		waitForJqueryLoad(driver);
		selectWebElement(exportButton.get(i));
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean groupBy(int x) {
		DragandDrop(gridTemplateName.get(x),dropTarget.get(x));
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();	
		}
		if(groupBy.getText().split(": ")[1].equals(groupByTemplateNameInTemplateTab.getText()))
			return true;
		else
			return false;

	}

	public boolean groupByInTemplateScreenTab(int x) {
		DragandDrop(gridTemplateName.get(x),dropTarget.get(x));
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();	
		}
		if(groupBy.getText().split(": ")[1].equals(groupByTemplateNameInTemplateScreenTab.getText()))
			return true;
		else
			return false;

	}

	public boolean groupByInTemplateAuxCodesTab(int x) {
		DragandDrop(gridTemplateName.get(x),dropTarget.get(x));
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();	
		}
		if(groupBy.getText().split(": ")[1].equals(groupByTemplateNameInTemplateAuxCodesTab.getText()))
			return true;
		else
			return false;

	}

	public boolean groupByInTemplateOpHoursTab(int x) {
		DragandDrop(gridTemplateName.get(x),dropTarget.get(x));
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();	
		}
		if(groupBy.getText().split(": ")[1].equals(groupByTemplateNameInTemplateOpHoursTab.getText()))
			return true;
		else
			return false;

	}

	public boolean groupByInTemplateFeaturesTab(int x) {
		DragandDrop(gridTemplateName.get(x),dropTarget.get(x));
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();	
		}
		if(groupBy.getText().split(": ")[1].equals(groupByTemplateNameInTemplateFeaturesTab.getText()))
			return true;
		else
			return false;

	}

	public boolean verifyArrowMoveForPreviousAndNextPage(int x) throws Exception{
		waitForJqueryLoad(driver);
		boolean status=false;
		if(!nextPageIcon.get(x).getAttribute("class").contains("k-state-disabled")){
			int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(x)));
			selectWebElement(nextPageIcon.get(x));
			Thread.sleep(1000);
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(x)));
			selectWebElement(previousPageIcon.get(x));
			Thread.sleep(1000);
			int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(x)));
			if(nextnumber==(pagenumber+1) && pagenumber==previousnumber){status=true;}
		}else{
			System.out.println("previous and next page icon disabled");status=true;
		}
		return status;
	}
	public boolean verifyArrowMoveForFirstAndLastPage(int x) throws Exception{
		waitForJqueryLoad(driver);
		boolean status=false;
		if(!lastPageIcon.get(x).getAttribute("class").contains("k-state-disabled")){
			int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(x)));
			selectWebElement(lastPageIcon.get(x));
			Thread.sleep(1000);
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(x)));
			selectWebElement(firstPageIcon.get(x));
			Thread.sleep(1000);
			int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(x)));
			if(nextnumber>pagenumber && pagenumber==previousnumber){status=true;}
		}else{
			System.out.println("previous and next page icon disabled");status=true;
		}
		return status;
	}

	public boolean verifyTotalNumberOfItemsPerPageDetails(int x){
		waitForJqueryLoad(driver);
		String item = items.get(x).getText();
		return item.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
	}

	public boolean verifyNumberOfItemsPerPage(int x) {
		waitForJqueryLoad(driver);
		boolean status = false;
		try {
			int item = Integer.valueOf(items.get(x).getText().split("of ")[1].split(" items")[0]);
			selectWebElement(pagerDropdown.get(x));
			Thread.sleep(1500);
			for (int i = 0; i < pageSizeListBox.size(); i++) {
				if(Integer.valueOf(pageSizeListBox.get(i).getText())>item){continue;}
				selectDropdownFromVisibleText(pageSizeListBox, pageSizeListBox.get(i).getText());
				waitForJqueryLoad(driver);
				int totalItems = Integer.valueOf(items.get(x).getText().split("of ")[1].split(" items")[0]);
				int pagersize = Integer.valueOf(pagerSize.get(x).getText());
				int pages = (totalItems % pagersize == 0) ? item / pagersize : item / pagersize+1;
				int totalRows=(gridContent.get(x).findElements(By.tagName("tr")).size());
				selectWebElement(lastPageIcon.get(x));
				waitForJqueryLoad(driver);
				int lastPageNumber = Integer.valueOf(pageNumber.get(x).getText());
				if (item == totalItems && pages == lastPageNumber&&totalRows==pagersize) {
					status = true;
				} else {System.out.println(items.get(x)+":"+totalItems+"\t"+pages+":"+lastPageNumber+"\t"+totalRows+":"+pagersize);
				status = false;
				break;
				}selectWebElement(pagerDropdown.get(x));Thread.sleep(1500);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} return status;
	}

	public boolean clearAll(AgentTemplateDetails details,int x) throws Exception {
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
	public boolean verifyclose(int x) {
		selectWebElement(searchClose);
		if(gridContent.get(x).isDisplayed())
			return true;
		else
			return false;
	}

	public void searchwithoutextsearch(AgentTemplateDetails details) throws Exception {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
		selectWebElement(searchSearchBtn);	
		selectWebElement(searchClose);
	}

	public void searchwithoutextsearchInTemplateScreenWidgetsTab(AgentTemplateDetails details) throws Exception {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Screen Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
		selectWebElement(searchSearchBtn);	
		selectWebElement(searchClose);
	}

	public boolean verifyclearsearch(int x) {
		selectWebElement(clearsearch);
		if(gridContent.get(x).isDisplayed())
			return true;
		else
			return false;
	}

	public boolean verifyinvalidsearchwithwrongdata(AgentTemplateDetails details,int x) throws Exception {
		searchAgentTemplate(details.getTemplatename(),x);
		if(norecords.isDisplayed())
			return true; 
		else
			return false;
	}

	public boolean verifyinvalidsearchwithwrongdataInTemplateScreenWidgetsTab(AgentTemplateDetails details,int x) throws Exception {
		searchAgentTemplateScreenName(details.getScreenname(),x);
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

	public boolean verifySearchIsnotEqualTo(String templateName,int x) throws Exception {
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
		waitUntilWebElementIsVisible(gridContent.get(x));
		List<Map<String,String>> UI=gettable(x); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;

	}

	public boolean verifySearchContains(String templateName,int x) throws Exception {
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
		waitUntilWebElementIsVisible(gridContent.get(x));
		List<Map<String,String>> UI=gettable(x); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;

	}

	public boolean verifySearchDoesNotContains(String templateName,int x) throws Exception {
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
		waitUntilWebElementIsVisible(gridContent.get(x));
		List<Map<String,String>> UI=gettable(x); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;

	}

	public boolean verifySearchStartsWith(String templateName,int x) throws Exception {
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
		waitUntilWebElementIsVisible(gridContent.get(x));
		List<Map<String,String>> UI=gettable(x); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;

	}

	public boolean verifySearchEndsWith(String templateName,int x) throws Exception {
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
		waitUntilWebElementIsVisible(gridContent.get(x));
		List<Map<String,String>> UI=gettable(x); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;

	}
	public List<Map<String, String>> gettable(int x) {
		int item=Integer.valueOf(items.get(x).getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.get(x).getText());
		int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){
			waitUntilWebElementIsVisible(auditGridContent.get(x));
			List<WebElement> rows=auditGridContent.get(x).findElements(By.tagName("tr"));
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
				nextPageIcon.get(x).click();
				waitForJqueryLoad(driver);}
		}
		return arr;
	}

	public void NavigateToTemplateScreenTab() {
		//waitForJqueryLoad(driver);
		selectWebElement(templateScreenTab);
		//waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(addNewTemplateScreenRecord);
	}

	public void NavigateToTemplateScreenWidgetsTab() {
		waitForJqueryLoad(driver);	
		selectWebElement(templateScreenWidgetsTab);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(addNewTemplateScreenWidgetRecord);
	}

	public void NavigateToTemplateAuxCodesTab() {
		//waitForJqueryLoad(driver);
		selectWebElement(templateAuxCodesTab);
		//waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(addNewTemplateAuxCodesRecord);
	}

	public void NavigateToTemplateOpHoursTab() {
		//waitForJqueryLoad(driver);
		selectWebElement(templateOpHoursTab);								  
		waitForJqueryLoad(driver);
	}

	public void NavigateToTemplateFeaturesTab() {
		waitForJqueryLoad(driver);
		selectWebElement(templateFeatures);
		waitForJqueryLoad(driver);
	}

	public boolean VerifyExportToExcelWithoutDataInTemplateWidgetScreenTab(AgentTemplateDetails details,int i) throws Exception {
		searchAgentTemplateScreenName(details.getScreenname(),i);
		waitForJqueryLoad(driver);
		selectWebElement(exportButton.get(i));
		waitForJqueryLoad(driver);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
			return false;
	}

	public void verifySortByAscendingInTemplateWidgetsScreenTab(int i) {
		waitUntilWebElementIsVisible(auditGridContent.get(i));
		selectWebElement(gridScreenName);
		waitForJqueryLoad(driver);
		selectWebElement(exportButton.get(i));
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void verifySortByDescendingInTemplateWidgetsScreenTab(int i) {
		waitUntilWebElementIsVisible(auditGridContent.get(i));
		selectWebElement(gridScreenName);
		selectWebElement(gridScreenName);
		waitForJqueryLoad(driver);
		selectWebElement(exportButton.get(i));
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean groupByInTemplateWidgetScreenTab(int x) {
		DragandDrop(gridScreenName,dropTarget.get(x));
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();	
		}
		if(groupBy.getText().split(": ")[1].equals(groupByTemplateNameInTemplateWidgetScreenTab.getText()))
			return true;
		else
			return false;

	}

	public boolean clearAllInScreenWidgetScreentab(AgentTemplateDetails details,int x) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Screen Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
		enterValueToTxtField(searchTextBox,details.getScreenname());
		selectWebElement(clearall);
		Thread.sleep(2000);
		if(searchText.get(3).isDisplayed())
			return true;
		else
			return false;
	}

	public boolean verifySearchIsnotEqualToInTemplateWidgetsScreenTab(String sreenName,int x) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Screen Name", sreenName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Screen Name");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
		enterValueToTxtField(searchTextBox,sreenName);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent.get(x));
		List<Map<String,String>> UI=gettable(x); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;

	}

	public boolean verifySearchContainsInTemplateWidgetsScreenTab(String sreenName,int x) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Screen Name", sreenName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Screen Name");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		enterValueToTxtField(searchTextBox,sreenName);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent.get(x));
		List<Map<String,String>> UI=gettable(x); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;

	}

	public boolean verifySearchDoesNotContainsInTemplateWidgetsScreenTab(String sreenName,int x) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Screen Name", sreenName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Screen Name");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not Contain");
		enterValueToTxtField(searchTextBox,sreenName);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent.get(x));
		List<Map<String,String>> UI=gettable(x); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;

	}

	public boolean verifySearchStartsWithInTemplateWidgetsScreenTab(String sreenName,int x) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Screen Name", sreenName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Screen Name");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
		enterValueToTxtField(searchTextBox,sreenName);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent.get(x));
		List<Map<String,String>> UI=gettable(x); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;

	}

	public boolean verifySearchEndsWithInTemplateWidgetsScreenTab(String sreenName,int x) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Screen Name", sreenName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Screen Name");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
		enterValueToTxtField(searchTextBox,sreenName);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent.get(x));
		List<Map<String,String>> UI=gettable(x); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;

	}

	public String VerifyMessageid() {
		waitForJqueryLoad(driver);
		System.out.println(toastid);
		if(toastid.isDisplayed())
			return toastid.getText();
		else{return errorMsg.get(0).getText();}
	}

	public String VerifyMessage1() {
		waitForJqueryLoad(driver);
		System.out.println(successmsg1);
		if(successmsg1.isDisplayed())
			return successmsg1.getText();
		else{return errorMsg.get(0).getText();}
	}

	public boolean verifyAddButton() throws Exception {
		boolean status=false;
		waitForJqueryLoad(driver);
		selectWebElement(addNewTemplateRecordBtn);	
		System.out.println("label="+AgntTmpPopupLbl);
		if(AgntTmpPopupLbl.isDisplayed())
			status=true;
		return status;
	}

	public void addnewTemplateRecord(AgentTemplateDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewTemplateRecordBtn);	
		waitForJqueryLoad(driver);
		selectWebElement(OrgunitDropdown);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//selectDropdownFromVisibleText(teamNameListBox,details.getTeamName());
		ChooseTeamHeirarchy(details.getOrgUnit());
		enterValueToTxtField(Templatename,details.getTemplatename());
		selectWebElement(themeoptions);
		selectDropdownFromVisibleText(themeDropDwn,details.getThemeoptions());
		selectWebElement(saveBtn);

	}	
	public void addRecwithoutOrgUnit(AgentTemplateDetails details) throws Exception {
		// TODO Auto-generated method stub
		waitForJqueryLoad(driver);
		selectWebElement(addNewTemplateRecordBtn);	
		enterValueToTxtField(Templatename,details.getTemplatename());
		selectWebElement(themeoptions);
		selectDropdownFromVisibleText(themeDropDwn,details.getThemeoptions());
		selectWebElement(saveBtn);    
	}


	public void addRecwithoutTemplateName(AgentTemplateDetails details) throws Exception {
		// TODO Auto-generated method stub
		waitForJqueryLoad(driver);
		selectWebElement(addNewTemplateRecordBtn);	
		selectWebElement(OrgunitDropdown);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//selectDropdownFromVisibleText(teamNameListBox,details.getTeamName());
		ChooseTeamHeirarchy(details.getOrgUnit());
		selectWebElement(themeoptions);
		selectDropdownFromVisibleText(themeDropDwn,details.getThemeoptions());
		selectWebElement(saveBtn);

	}	

	public void addRecwithoutTheme(AgentTemplateDetails details) throws Exception {
		// TODO Auto-generated method stub
		waitForJqueryLoad(driver);
		selectWebElement(addNewTemplateRecordBtn);	
		selectWebElement(OrgunitDropdown);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//selectDropdownFromVisibleText(teamNameListBox,details.getTeamName());
		ChooseTeamHeirarchy(details.getOrgUnit());
		enterValueToTxtField(Templatename,details.getTemplatename());
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
	public String getErrorMsg() {
		waitUntilWebElementListIsVisible(errorMsg);
		return errorMsg.get(0).getText();
	}

	public void editRecord(AgentTemplateDetails details,int x) throws Exception {
		verifySearchIsEqualTo(details.getTemplatename(),x);
		waitForJqueryLoad(driver);
		//Thread.sleep(3000);
		//waitUntilWebElementIsVisible(editBtn);
		selectWebElement(EditBtnTempTab);
		waitForJqueryLoad(driver);
		//Thread.sleep(3000);
		selectWebElement(updatedtheme);
		selectDropdownFromVisibleText(themeDropDwn,details.getupdatedTheme());
		enterValueToTxtFieldWithoutClear(ModifyReasontext,details.getmodifyReason());
		selectWebElement(editSavebtn);
	}
	public void editRecordWithoutModifyreason(AgentTemplateDetails details,int x) throws Exception {
		verifySearchIsEqualTo(details.getTemplatename(),x);
		waitForJqueryLoad(driver);
		selectWebElement(EditBtnTempTab);
		waitForJqueryLoad(driver);
		//Thread.sleep(3000);
		selectWebElement(updatedtheme);
		selectDropdownFromVisibleText(themeDropDwn,details.getupdatedTheme());
		//enterValueToTxtFieldWithoutClear(ModifyReasontext,details.getmodifyReason());
		selectWebElement(editSavebtn);
	}



	public void editCancel(AgentTemplateDetails details,int x) throws Exception {
		waitForJqueryLoad(driver);
		verifySearchIsEqualTo(details.getTemplatename(),x);
		waitForJqueryLoad(driver);
		selectWebElement(EditBtnTempTab);
		waitForJqueryLoad(driver);		
		selectWebElement(editcancel);
		waitForJqueryLoad(driver);
	}
	public boolean verifyEditFormContainer() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return isElementExist(editPopUp);
	}
	public void deleteRecord(AgentTemplateDetails details,int x) throws Exception {
		verifySearchIsEqualTo(details.getTemplatename(),x);
		waitForJqueryLoad(driver);
		selectWebElement(deleteBtnTempTab);
		waitForJqueryLoad(driver);
		enterValueToTxtFieldWithoutClear(deleteReasonTxtBox,details.getdeleteReason());
		selectWebElement(deleteYesBtn);
	}
	public void deleteRecordWithoutDeletereason(AgentTemplateDetails details,int x) throws Exception {
		verifySearchIsEqualTo(details.getTemplatename(),x);
		waitForJqueryLoad(driver);
		selectWebElement(deleteBtnTempTab);
		waitForJqueryLoad(driver);
		//Thread.sleep(3000);
		selectWebElement(deleteYesBtn);
	}

	public boolean DeleteCancelTemplateTab(AgentTemplateDetails details,int x) throws Exception {
		boolean status=false;
		verifySearchIsEqualTo(details.getTemplatename(),x);
		waitForJqueryLoad(driver);
		selectWebElement(deleteBtnTempTab);
		waitForJqueryLoad(driver);		
		selectWebElement(deleteNoBtn);
		if(templateTabTableRow.getText().equals(details.getTemplatename()))
			status=true;
		else
			status=false;
		return status;
	}

	public boolean verifySearchIsEqualTo(String templateName,int x) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Template Name", templateName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
		enterValueToTxtField(searchTextBox,templateName);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent.get(x));
		List<Map<String,String>> UI=gettable(x); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= true;
			else 
				Status= false;
		}
		return Status;

	}
	public boolean verifySearchIsEqualToWidget(String widgets,int x) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Widgets", widgets);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Widgets");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
		enterValueToTxtField(searchTextBox,widgets);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent.get(x));
		List<Map<String,String>> UI=gettable(x); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= true;
			else 
				Status= false;
		}
		return Status;

	}
	/*SCreen tab*/
	public boolean verifyElementsInScreenTab() {
		waitForJqueryLoad(driver);
		selectWebElement(templateScreenTab);
		waitForJqueryLoad(driver);
		boolean status=false;

		/*if(addSkillTemplateWindowTitleprsence.isDisplayed()) {
		 			if(templateNameLabel.isDisplayed()) {
		 				if(orgUnitLabel.isDisplayed()) {*/
		return status;
	}
	public void addNewScreenTemplateWithoutScreen(AgentTemplateDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(templateScreenTab);
		selectWebElement(addnewscreenRecordBtn);
		waitForJqueryLoad(driver);
		selectWebElement(tempnameDropdown);
		waitForJqueryLoad(driver);
		selectDropdownFromVisibleText(tempnameListbox,details.getTemplatename());
		selectWebElement(saveBtn);
	}

	public boolean VerifyAddScreenTemplateWindowCancelButton(int x) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(templateScreenTab);
		waitUntilWebElementIsVisible(items.get(x));
		String actualitems=items.get(x).getText();
		System.out.println(actualitems);
		selectWebElement(addnewscreenRecordBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(screenpopUpTitle);
		selectWebElement(CancelButton);
		System.out.println(items.get(x).getText());
		if(actualitems.equals(items.get(x).getText()))
			return true;
		else
			return false;
	}

	public void verifyAddnewScreenTemplate(AgentTemplateDetails details) throws Exception {
		templateScreenTab.click();
		selectWebElement(addnewscreenRecordBtn);
		selectWebElement(tempnameDropdown);
		selectDropdownFromVisibleText(tempnameListbox,details.getTemplatename());
		selectDropdownFromVisibleText(screenListbox,details.getScreenList());
		scrollToElement(transferToicon);
		selectWebElement(transferToicon);
		scrollToElement(saveBtn);
		selectWebElement(saveBtn); 
	}
	public void verifyAddScreenTemplateWithoutTemplate(AgentTemplateDetails details) throws Exception {		
		templateScreenTab.click();
		selectWebElement(addnewscreenRecordBtn);
		waitForJqueryLoad(driver);
		selectDropdownFromVisibleText(screenListbox,details.getScreenList());
		scrollToElement(transferToicon);
		selectWebElement(transferToicon);
		scrollToElement(saveBtn);
		selectWebElement(saveBtn); 
	}
	public boolean verifyTransferAllToBtn(AgentTemplateDetails details) throws Exception {
		boolean status=false;
		templateScreenTab.click();
		selectWebElement(addnewscreenRecordBtn);
		selectWebElement(tempnameDropdown);
		selectDropdownFromVisibleText(tempnameListbox,details.getTemplatename());
		scrollToElement(transferAlltoicon);
		selectWebElement(transferAlltoicon);
		selectWebElement(saveBtn);
		System.out.println(sceenListbox1.size());
		if(sceenListbox1.size()==0)
			status=true;
		return status;
	}

	public void verifyTransferAllFromBtn(AgentTemplateDetails details,int x) throws Exception {
		selectWebElement(templateScreenTab);
		verifySearchIsEqualTo(details.getTemplatename(),x);
		selectWebElement(EditBtnScreenTab);
		scrollToElement(transferAllfromIcon);
		selectWebElement(transferAllfromIcon);
		enterValueToTxtFieldWithoutClear(ModifyReasontext,details.getmodifyReason());
		scrollToElement(editSavebtn);
		selectWebElement(editSavebtn); 
	}

	public void editScreenRecord(AgentTemplateDetails details,int x) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(templateScreenTab);
		verifySearchIsEqualTo(details.getTemplatename(),x);
		selectWebElement(EditBtnScreenTab);
		selectDropdownFromVisibleText(screenListbox,details.getUpdatedScreenList());
		scrollToElement(transferFromIcon);
		selectWebElement(transferFromIcon);
		enterValueToTxtFieldWithoutClear(ModifyReasontext,details.getmodifyReason());
		selectWebElement(editSavebtn);
	}
	public void editCancelScreenTab(AgentTemplateDetails details,int x) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(templateScreenTab);
		verifySearchIsEqualTo(details.getTemplatename(),x);
		selectWebElement(EditBtnScreenTab);
		selectWebElement(editcancel);
		waitForJqueryLoad(driver);
	}

	public void editScreenRecordWithoutModifyreason(AgentTemplateDetails details,int x) throws Exception {
		waitForJqueryLoad(driver);
		templateScreenTab.click();
		verifySearchIsEqualTo(details.getTemplatename(),x);
		selectWebElement(EditBtnScreenTab);	
		waitForJqueryLoad(driver);
		selectDropdownFromVisibleText(screenListbox,details.getUpdatedScreenList());
		scrollToElement(transferFromIcon);
		selectWebElement(transferFromIcon);
		//enterValueToTxtFieldWithoutClear(ModifyReasontext,details.getmodifyReason());
		selectWebElement(editSavebtn);
	}
	public void deleteWithoutDeletereasonScreentab(AgentTemplateDetails details,int x) throws Exception {
		waitForJqueryLoad(driver);
		//selectWebElement(templateScreenTab);
		templateScreenTab.click();
		verifySearchIsEqualTo(details.getTemplatename(),x);
		waitForJqueryLoad(driver);
		selectWebElement(deleteBtnScreentab);
		//waitForJqueryLoad(driver);
		//Thread.sleep(3000);
		selectWebElement(deleteYesScreentab);
	}

	public boolean DeleteCancelScreentab(AgentTemplateDetails details,int x) throws Exception {
		boolean status=false;
		waitForJqueryLoad(driver);
		templateScreenTab.click();
		verifySearchIsEqualTo(details.getTemplatename(),x);
		//waitForJqueryLoad(driver);
		selectWebElement(deleteBtnScreentab);
		//waitForJqueryLoad(driver);		
		selectWebElement(deleteNoBtnScreentb);	
		if(screenTabTableRow.getText().equals(details.getTemplatename()))
			status=true;
		else
			status=false;
		return status;
		
	}

	public void deleteScreenRecord(AgentTemplateDetails details,int x) throws Exception {
		templateScreenTab.click();
		verifySearchIsEqualTo(details.getTemplatename(),x);
		waitForJqueryLoad(driver);
		selectWebElement(deleteBtnScreentab);
		//waitForJqueryLoad(driver);
		enterValueToTxtFieldWithoutClear(deleteReasonTxtBoxScreenTab,details.getdeleteReason());
		deleteYesBtnSreentab.click();
	}
	/*Aux tab methods*/
	public void addNewAuxTemplateWithoutAuxcode(AgentTemplateDetails details) throws Exception {
		templateAuxCodesTab.click();;
		selectWebElement(addNewTemplateAuxCodesRecord);
		selectWebElement(tempnameDropdown);
		waitForJqueryLoad(driver);	
		selectDropdownFromVisibleText(tempnamelistAuxtab,details.getTemplatename());
		waitForJqueryLoad(driver);			
		selectWebElement(saveBtn);
	}
	
	
	public boolean VerifyAddAuxTemplateWindowCancelButton(int x) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(templateAuxCodesTab);
		waitUntilWebElementIsVisible(items.get(x));
		String actualitems=items.get(x).getText();
		System.out.println(actualitems);
		selectWebElement(addNewTemplateAuxCodesRecord);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(screenpopUpTitleAux);
		selectWebElement(CancelButton);
		System.out.println(items.get(x).getText());
		if(actualitems.equals(items.get(x).getText()))
			return true;
		else
			return false;
	}
	
	
	public boolean verifyAddnewAuxTemplate(AgentTemplateDetails details) throws Exception {
		boolean status=false;
		templateAuxCodesTab.click();
		selectWebElement(addNewTemplateAuxCodesRecord);
		selectWebElement(tempnameDropdown);
		selectDropdownFromVisibleText(tempnamelistAuxtab,details.getTemplatename());	
		//selectDropdownFromVisibleText(auxListbox,details.getAuxCode());
		selectmultipleAux(details.getAuxCode());	
		waitUntilWebElementIsVisible(successmsg);
		if(successmsg.getText().equals("Record inserted Successfully"))
			status=true;
		return status;
		/*	waitUntilWebElementIsVisible(gridContent.get(arg0));
		 		Thread.sleep(2000);
		 		if(rowdata.getText().equals(details.getSearchStr()) && rowdata.getText().contains(details.getSearchStr1())) {
		 			Status=true;
		 		}*/

	}

	public void verifyAddTmpwithoutTemplatename(AgentTemplateDetails details) throws Exception {
		templateAuxCodesTab.click();
		selectWebElement(addNewTemplateAuxCodesRecord);
		waitForJqueryLoad(driver);
		selectDropdownFromVisibleText(auxListbox,details.getUpdatedAuxcode());
		scrollToElement(transferToicon);
		selectWebElement(transferToicon);
		scrollToElement(saveBtn);
		selectWebElement(saveBtn); 
	}

	public boolean verifyTransferAllToBtnAuxTab(AgentTemplateDetails details,int x) throws Exception {
		boolean status=false;		
		templateAuxCodesTab.click();
		verifySearchIsEqualTo(details.getTemplatename(),x);
		selectWebElement(EditBtnAuxTab);
		waitForJqueryLoad(driver);
		scrollToElement(transferAlltoicon);
		selectWebElement(transferAlltoicon);
		enterValueToTxtFieldWithoutClear(ModifyReasontext,details.getmodifyReason());
		selectWebElement(saveBtn);
		if(auxListbox2.size()==0)
			status=true;
		return status;
	}

	public void verifyTransferAllFromBtnAuxTab(AgentTemplateDetails details,int x) throws Exception {
		templateAuxCodesTab.click();
		verifySearchIsEqualTo(details.getTemplatename(),x);
		selectWebElement(EditBtnAuxTab);		
		scrollToElement(transferAllfromIcon);
		selectWebElement(transferAllfromIcon);
		enterValueToTxtFieldWithoutClear(ModifyReasontext,details.getmodifyReason());
		scrollToElement(editSavebtn);
		selectWebElement(editSavebtn); 
	}
	public void verifyTransferFromBtn(AgentTemplateDetails details,int x) throws Exception {	
		templateAuxCodesTab.click();
		verifySearchIsEqualTo(details.getTemplatename(),x);
		selectWebElement(EditBtnAuxTab);
		waitForJqueryLoad(driver);
		selectDropdownFromVisibleText(auxListbox,details.getUpdatedAuxcode());
		scrollToElement(transferfromIcon);
		selectWebElement(transferfromIcon);
		enterValueToTxtFieldWithoutClear(ModifyReasontext,details.getmodifyReason());
		scrollToElement(editSavebtn);
		selectWebElement(editSavebtn); 
	}
	public void editAuxRecord(AgentTemplateDetails details,int x) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(templateAuxCodesTab);
		verifySearchIsEqualTo(details.getTemplatename(),x);
		selectWebElement(EditBtnAuxTab);
		waitForJqueryLoad(driver);
		selectDropdownFromVisibleText(auxListbox,details.getUpdatedAuxcode());
		scrollToElement(transferToicon);
		selectWebElement(transferToicon);
		enterValueToTxtFieldWithoutClear(ModifyReasontext,details.getmodifyReason());
		selectWebElement(editSavebtn);
	}
	public void editCancelAuxTab(AgentTemplateDetails details,int x) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(templateAuxCodesTab);
		verifySearchIsEqualTo(details.getTemplatename(),x);
		selectWebElement(EditBtnAuxTab);
		selectWebElement(editcancel);
		waitForJqueryLoad(driver);
	}

	public void editAuxRecordWithoutModifyreason(AgentTemplateDetails details,int x) throws Exception {
		waitForJqueryLoad(driver);
		templateAuxCodesTab.click();
		verifySearchIsEqualTo(details.getTemplatename(),x);
		selectWebElement(EditBtnAuxTab);	
		waitForJqueryLoad(driver);
		selectDropdownFromVisibleText(auxListbox,details.getUpdatedAuxcode());
		scrollToElement(transferToicon);
		selectWebElement(transferToicon);
		//enterValueToTxtFieldWithoutClear(ModifyReasontext,details.getmodifyReason());
		selectWebElement(editSavebtn);
	}
	public void deleteWithoutDeletereasonAuxtab(AgentTemplateDetails details,int x) throws Exception {

		//selectWebElement(templateScreenTab);
		templateAuxCodesTab.click();
		verifySearchIsEqualTo(details.getTemplatename(),x);

		selectWebElement(deleteBtnAuxtab);
		//waitForJqueryLoad(driver);
		//Thread.sleep(3000);
		selectWebElement(deleteYesBtnAuxtab);
	}

	public boolean DeleteCancelAuxtab(AgentTemplateDetails details,int x) throws Exception {
		boolean status=false;
		templateAuxCodesTab.click();
		verifySearchIsEqualTo(details.getTemplatename(),x);
		waitForJqueryLoad(driver);
		selectWebElement(deleteBtnAuxtab);
		//waitForJqueryLoad(driver);		
		selectWebElement(deleteNoBtnAuxtab);	
		if(auxcodesTableRow.getText().equals(details.getTemplatename()))
			status=true;
		else
			status=false;
		return status;
	}

	public void deleteAuxRecord(AgentTemplateDetails details,int x) throws Exception {
		templateAuxCodesTab.click();
		verifySearchIsEqualTo(details.getTemplatename(),x);
		waitForJqueryLoad(driver);
		selectWebElement(deleteBtnAuxtab);
		waitForJqueryLoad(driver);
		enterValueToTxtFieldWithoutClear(deleteReasonTxtBoxAuxTab,details.getdeleteReason());
		deleteYesBtnAuxtab.click();
	}

	public void selectmultipleAux(String auxcode) throws Exception{
		String[] a=auxcode.split(",");		
		for(String attribute:a){
			for(WebElement ele:auxListbox){
				if(ele.getText().equalsIgnoreCase(attribute)){
					selectWebElement(ele);
					waitForJqueryLoad(driver);
					selectWebElement(transferToicon);

				}
			}					
		}
		selectWebElement(auxtabSavebtn);
	}

	public boolean addTemplateWithoutWidget(AgentTemplateDetails details) throws Exception {
		boolean status=false;
		templateScreenWidgetsTab.click();
		selectWebElement(addNewWigetRecordBtn);
		waitForJqueryLoad(driver);
		selectWebElement(tempnameDropdown);
		waitForJqueryLoad(driver);	
		selectDropdownFromVisibleText(tempnameListWidgetTab,details.getTemplatename());
		//waitForJqueryLoad(driver);	
		selectWebElement(screennameDropdowninWidget);	
		//waitForJqueryLoad(driver);
		selectDropdownFromVisibleText(screennameListWidgetTab,details.getScreenList());
		waitForJqueryLoad(driver);
		selectWebElement(saveBtn);
		waitUntilWebElementIsVisible(errorMsg.get(0));
		if(errorMsg.get(0).getText().equals("At least one widget is required"))
			status=true;
		return status;
	}

	
	public boolean VerifyAddWidgetTemplateWindowCancelButton(int x) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(templateScreenWidgetsTab);
		waitUntilWebElementIsVisible(items.get(x));
		String actualitems=items.get(x).getText();
		System.out.println(actualitems);
		selectWebElement(addNewWigetRecordBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(widgetpopupTitle);
		selectWebElement(CancelButton);
		System.out.println(items.get(x).getText());
		if(actualitems.equals(items.get(x).getText()))
			return true;
		else
			return false;
	}

	public boolean verifyAddnewWidgetTemplate(AgentTemplateDetails details,int x) throws Exception {
		boolean status=false;
		templateScreenWidgetsTab.click();
		selectWebElement(addNewWigetRecordBtn);
		selectWebElement(tempnameDropdown);
		selectDropdownFromVisibleText(tempnameListWidgetTab,details.getTemplatename());	
		selectWebElement(screennameDropdowninWidget);	
		selectDropdownFromVisibleText(screennameListWidgetTab,details.getScreenList());
		//selectDropdownFromVisibleText(WidgetListbox,details.getWidgetCode());
		selectmultipleWidget(details.getWidgetList());	
		waitUntilWebElementIsVisible(successmsg);
		if(successmsg.getText().equals("Record inserted Successfully"))
		{	verifySearchIsEqualToWidget(details.getWidgetList(),x);
		status=true;
		}
		return status;
	}
	public void selectmultipleWidget(String Widgetcode) throws Exception{
		String[] a=Widgetcode.split(", ");		
		for(String attribute:a){
			for(WebElement ele:WidgetListbox){
				if(ele.getText().equalsIgnoreCase(attribute)){
					selectWebElement(ele);
					waitForJqueryLoad(driver);
					selectWebElement(transferToicon);

				}
			}					
		}
		selectWebElement(widgettabaddSavebtn);
	}
	public void verifyAddwidgetTmpwithoutTemplatename(AgentTemplateDetails details) throws Exception {
		templateScreenWidgetsTab.click();
		selectWebElement(addNewWigetRecordBtn);
		scrollToElement(saveBtn);
		selectWebElement(saveBtn); 
	}

	public void verifyAddwidgetTmpwithoutScreen(AgentTemplateDetails details) throws Exception {
		templateScreenWidgetsTab.click();
		selectWebElement(addNewWigetRecordBtn);
		selectWebElement(tempnameDropdown);
		selectDropdownFromVisibleText(tempnameListWidgetTab,details.getTemplatename());
		scrollToElement(saveBtn);
		selectWebElement(saveBtn); 
	}
	public boolean verifyTransferAllToBtnWidgetTab(AgentTemplateDetails details,int x) throws Exception {
		boolean status=false;		
		templateScreenWidgetsTab.click();
		verifySearchIsEqualToWidget(details.getWidgetList(),x);
		selectWebElement(EditBtnWidgetTab);
		scrollToElement(transferAlltoicon);
		selectWebElement(transferAlltoicon);
		enterValueToTxtFieldWithoutClear(ModifyReasontext,details.getmodifyReason());
		selectWebElement(saveBtn);
		if(widgetList.size()==0)
			status=true;
		return status;
	}

	public void verifyTransferAllFromBtnWidgetTab(AgentTemplateDetails details,int x) throws Exception {
		templateScreenWidgetsTab.click();
		verifySearchIsEqualToWidget(details.getWidgetList(),x);
		selectWebElement(EditBtnWidgetTab);		
		scrollToElement(transferAllfromIcon);
		selectWebElement(transferAllfromIcon);
		enterValueToTxtFieldWithoutClear(ModifyReasontext,details.getmodifyReason());
		scrollToElement(editSavebtn);
		selectWebElement(editSavebtn); 
	}
	public void verifyTransferFromBtnWidgetTab(AgentTemplateDetails details,int x) throws Exception {	
		templateScreenWidgetsTab.click();
		verifySearchIsEqualToWidget(details.getWidgetList(),x);
		selectWebElement(EditBtnWidgetTab);
		selectDropdownFromVisibleText(WidgetListbox,details.getupdatedWidgetList());
		scrollToElement(transferfromIcon);
		selectWebElement(transferfromIcon);
		enterValueToTxtFieldWithoutClear(ModifyReasontext,details.getmodifyReason());
		scrollToElement(editSavebtn);
		selectWebElement(editSavebtn); 
	}
	public void editWidgetRecord(AgentTemplateDetails details,int x) throws Exception {		
		templateScreenWidgetsTab.click();
		verifySearchIsEqualToWidget(details.getWidgetList(),x);
		selectWebElement(EditBtnWidgetTab);		
		selectDropdownFromVisibleText(WidgetListbox,details.getupdatedWidgetList());
		scrollToElement(transferToicon);
		selectWebElement(transferToicon);
		enterValueToTxtFieldWithoutClear(ModifyReasontext,details.getmodifyReason());
		selectWebElement(editSavebtn);
	}
	public boolean editCancelWidgetTab(AgentTemplateDetails details,int x) throws Exception {
		boolean status=false;
		templateScreenWidgetsTab.click();
		verifySearchIsEqualToWidget(details.getWidgetList(),x);
		selectWebElement(EditBtnWidgetTab);
		waitForJqueryLoad(driver);
		selectWebElement(editcancel);
		if(gridContent.get(x).isDisplayed())
			status=true;
		else
			status=false;
		return status;
	}

	public void editWidgetRecordWithoutModifyreason(AgentTemplateDetails details,int x) throws Exception {
		templateScreenWidgetsTab.click();
		verifySearchIsEqualToWidget(details.getWidgetList(),x);
		selectWebElement(EditBtnWidgetTab);	
		waitForJqueryLoad(driver);
		selectDropdownFromVisibleText(WidgetListbox,details.getupdatedWidgetList());
		scrollToElement(transferToicon);
		selectWebElement(transferToicon);
		//enterValueToTxtFieldWithoutClear(ModifyReasontext,details.getmodifyReason());
		selectWebElement(editSavebtn);
	}
	public void deleteWithoutDeletereasonWidgettab(AgentTemplateDetails details,int x) throws Exception {

		templateScreenWidgetsTab.click();
		verifySearchIsEqualToWidget(details.getWidgetList()+", "+details.getupdatedWidgetList(),x);	
		selectWebElement(deleteBtnWidgettab);
		//waitForJqueryLoad(driver);
		//Thread.sleep(3000);
		selectWebElement(deleteYesWidgetab);
	}

	public boolean DeleteCancelWidgettab(AgentTemplateDetails details,int x) throws Exception {
		boolean status=false;
		templateScreenWidgetsTab.click();
		verifySearchIsEqualToWidget(details.getWidgetList()+", "+details.getupdatedWidgetList(),x);
		//waitForJqueryLoad(driver);
		selectWebElement(deleteBtnWidgettab);
		//waitForJqueryLoad(driver);		
		selectWebElement(deleteNoBtnWidgettab);	
		if(gridContent.get(x).isDisplayed())
			status=true;
		else
			status=false;
		return status;
	}

	public void deleteWidgetRecord(AgentTemplateDetails details,int x) throws Exception {
		templateScreenWidgetsTab.click();
		verifySearchIsEqualToWidget(details.getWidgetList()+", "+details.getupdatedWidgetList(),x);
		waitForJqueryLoad(driver);
		selectWebElement(deleteBtnWidgettab);
		waitForJqueryLoad(driver);
		enterValueToTxtFieldWithoutClear(deleteReasonTxtBoxWidgetTab,details.getdeleteReason());
		deleteYesWidgetab.click();
	}
	//op hours tab
	public void verifyDrillDownForrecord(AgentTemplateDetails details) throws Exception {
		templateOpHoursTab.click();
		waitForJqueryLoad(driver);
		TemplatenameColumnOphoursTab.get(0).click();
		waitForJqueryLoad(driver);
		AddnewrowBtn.click();
		waitForJqueryLoad(driver);
		selectWebElement(selectdropdownWeekday);
		selectDropdownFromVisibleText(weekdayList,details.getweekday());
		fromtimedataField.click();
		clockicon.click();
		selectDropdownFromVisibleText(fromTimeList,details.getfromTime());
		toTimedataField.click();
		waitForJqueryLoad(driver);
		clockicon.click();
		waitForJqueryLoad(driver);
		selectDropdownFromVisibleText(toTimeList,details.gettoTime());
		waitForJqueryLoad(driver);
		selectWebElement(SavebtnOphrsTab);
		enterValueToTxtFieldWithoutClear(modifyReasonOphrsTab,details.getmodifyReason());
		selectWebElement(modifyReasonYesBtnOphrsTab);
	}


	public boolean verifyDeleteforDrillDown(AgentTemplateDetails details) throws Exception {
		boolean status=false;
		templateOpHoursTab.click();
		waitForJqueryLoad(driver);
		TemplatenameColumnOphoursTab.get(0).click();
		waitForJqueryLoad(driver);
		deleteBtnOpHrstab.click();
		waitForJqueryLoad(driver);
		enterValueToTxtFieldWithoutClear(deleteReasonTxtBoxOphrs,details.getdeleteReason());
		deleteYesBtnOPtab.click();
		waitUntilWebElementIsVisible(successmsg);
		if(successmsg.getText().equals("Record Deleted Successfully"))
			status=true;
		return status;

	}
	public boolean DeleteCancelOPtab(AgentTemplateDetails details,int x) throws Exception {
		boolean status=false;
		templateOpHoursTab.click();
		waitForJqueryLoad(driver);
		TemplatenameColumnOphoursTab.get(0).click();
		waitForJqueryLoad(driver);
		deleteBtnOpHrstab.click();
		waitForJqueryLoad(driver);		
		selectWebElement(deleteNoBtnOptab);	
		waitForJqueryLoad(driver);		
		if(gridContent.get(x).isDisplayed())
			status=true;
		else
			status=false;
		return status;
	}


	public boolean verifyaddRowwithoutFromTime(AgentTemplateDetails details) throws Exception {
		boolean status = false;
		templateOpHoursTab.click();
		waitForJqueryLoad(driver);
		TemplatenameColumnOphoursTab.get(0).click();
		waitForJqueryLoad(driver);
		AddnewrowBtn.click();
		waitForJqueryLoad(driver);
		selectWebElement(selectdropdownWeekday);
		selectDropdownFromVisibleText(weekdayList,details.getweekday());
		selectWebElement(SavebtnOphrsTab);
		waitUntilWebElementIsVisible(errorMsg.get(0));
		if(errorMsg.get(0).getText().equals("Enter a FromTime"))
			status=true;
		return status;
	}

	public boolean verifyaddRowwithoutToTime(AgentTemplateDetails details) throws Exception {
		boolean status = false;
		templateOpHoursTab.click();
		waitForJqueryLoad(driver);
		TemplatenameColumnOphoursTab.get(0).click();
		waitForJqueryLoad(driver);
		AddnewrowBtn.click();
		waitForJqueryLoad(driver);
		selectWebElement(selectdropdownWeekday);
		selectDropdownFromVisibleText(weekdayList,details.getweekday());
		fromtimedataField.click();
		clockicon.click();
		selectDropdownFromVisibleText(fromTimeList,details.getfromTime());
		waitForJqueryLoad(driver);
		selectWebElement(SavebtnOphrsTab);
		waitUntilWebElementIsVisible(errorMsg.get(0));
		if(errorMsg.get(0).getText().equals("Enter a ToTime"))
			status=true;
		return status;

	}

	public boolean verifyaddRowwithoutWeekDay(AgentTemplateDetails details) throws Exception {
		boolean status = false;
		templateOpHoursTab.click();
		waitForJqueryLoad(driver);
		TemplatenameColumnOphoursTab.get(0).click();
		waitForJqueryLoad(driver);
		AddnewrowBtn.click();
		waitForJqueryLoad(driver);	
		selectWebElement(SavebtnOphrsTab);
		waitUntilWebElementIsVisible(errorMsg.get(0));
		if(errorMsg.get(0).getText().equals("Please Select WeekDay"))
			status=true;
		return status;

	}
	public boolean verifyDatabase(String query,int x) {
		List<Map<String,String>> database=database(query);
		System.out.println(database);
		List<Map<String,String>> UI=gettable(x); 
		System.out.println(UI);
		if(UI.equals(database))
			return true;
		else
			return false;
	}

	public boolean verifyDatabase1(String query, int x) throws Exception {
		List<Map<String,String>> database=database(query);
		System.out.println("Printing query"+query);
		System.out.println("printing DB values"+database);
		Thread.sleep(2000);
		List<Map<String,String>> UI=getDatatable(x); 
		System.out.println("Printing UI Values"+UI);
		if(UI.equals(database))
			return true;
		else
			return false;
	}

	public boolean verifyTemplateDatabase(String query) throws Exception {
		List<Map<String,String>> database=database(query);
		System.out.println("Printing query"+query);
		System.out.println("printing DB values"+database);
		Thread.sleep(2000);
		List<Map<String,String>> UI=getTemplateData(); 
		System.out.println("Printing UI Values"+UI);
		if(UI.equals(database))
			return true;
		else
			return false;
	}

	public void sortTemplateNameByAsc() {		
		selectWebElement(headersDropdown1.get(1));
		waitForJqueryLoad(driver);
		selectWebElement(headersDropdown1.get(1));
		waitForJqueryLoad(driver);
		selectWebElement(sortAscending.get(0));
	}

	public void sorting() throws Exception {
		Thread.sleep(3000);
		selectWebElement(templateNameColumn.get(4));
	}

	public List<Map<String, String>> getTemplateData() throws Exception {
		int item=Integer.valueOf(items.get(0).getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize1.getText());
		int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){
			waitUntilWebElementIsVisible(auditGridContent1);
			List<WebElement> rows=auditGridContent1.findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			for(int i=1;i<rows.size();i++) {
				Map<String,String> map = new HashMap<String,String>();
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				String col=null;
				for(int j=1;j<headers.size();j++){
					scrollToElement(headers.get(j));
					col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				map.remove(" ");
				map.remove("");
				arr.add(map);
			}
			if(k!=pages)
			{
				Thread.sleep(5000);
				nextPageIcon1.click();
				waitForJqueryLoad(driver);
			}
		}
		return arr;
	}

	public boolean verifyDatabaseForTemplateData(String query) {
		List<Map<String,String>> database=database(query);
		System.out.println("Printing QUery"+query);
		System.out.println("Printing DB values"+database);
		List<Map<String,String>> UI=getDatatable(); 
		System.out.println("Printing UI Values"+UI);
		if(UI.equals(database))
			return true;
		else
			return false;
	}


	private List<Map<String,String>> getDatatable(){
		int item=Integer.valueOf(items1.getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize1.getText());
		int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){
			waitUntilWebElementIsVisible(auditGridContent1);
			List<WebElement> rows=auditGridContent1.findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			for(int i=1;i<rows.size();i++) {
				Map<String,String> map = new HashMap<String,String>();
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				String col=null;
				for(int j=1;j<headers.size();j++){
					/*if(headers.get(j).getText().equals("Last Changed On")){
					col=cols.get(j).getText().substring(10);
					}
				else*/
					if(headers.get(j).getText().equals("Last Changed On"))
						col=cols.get(j).getText().replaceAll("/", "/");
					else
						col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				while(map.values().remove(""));
				arr.add(map);
			}
			if(k!=pages) {
				nextPageIcon1.click();
				waitForJqueryLoad(driver);}
		}
		return arr;
	}

	public List<Map<String, String>> getDatatable(int x) throws Exception {
		int item=Integer.valueOf(items.get(x).getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.get(x).getText());
		int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){
			waitUntilWebElementIsVisible(auditGridContent.get(x));
			List<WebElement> rows=auditGridContent.get(x).findElements(By.tagName("tr"));
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
				Thread.sleep(5000);
				nextPageIcon.get(x).click();
				waitForJqueryLoad(driver);
			}
		}
		return arr;
	}
	
	public boolean VerifyAddNewTemplateFeaturesandPopUpCancelButton(int x) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(templateFeatures);
		waitUntilWebElementIsVisible(items.get(x));
		String actualitems=items.get(x).getText();
		System.out.println(actualitems);
		selectWebElement(addNewTemplateFeaturesRecordButton);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(templateFeaturesPopupTitle);
		selectWebElement(CancelButton);
		System.out.println(items.get(x).getText());
		if(actualitems.equals(items.get(x).getText()))
			return true;
		else
			return false;
	}
	
	public void selectCheckBox(WebElement ele, boolean value){
		if(value){
			if(ele.isSelected()){}else{selectCheckbox(ele);}
		}
		else{
			if(ele.isSelected()){selectCheckbox(ele);}
		}
	}
	
	public void VerifyAddNewTemplateFeaturesWithoutTemplate(AgentTemplateDetails details) {
		waitForJqueryLoad(driver);
		selectWebElement(templateFeatures);
		selectWebElement(addNewTemplateFeaturesRecordButton);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(templateFeaturesPopupTitle);
		selectCheckBox(isScreenCaptureEnabled,details.IsScreenCaptureEnabled());
		selectCheckBox(isTRSCameraPictureEnabled,details.IsTRSCameraPictureEnabled());
		selectCheckBox(isLocationEnabled,details.IsLocationEnabled());
		selectCheckBox(isFaceAuthenticationEnabled,details.IsFaceAuthenticationEnabled());
		scrollToElement(saveBtn);
		selectWebElement(saveBtn);	
	}
	
	public void VerifyAddNewTemplateFeaturesWithoutTemplateFeatures(AgentTemplateDetails details) {
		waitForJqueryLoad(driver);
		selectWebElement(templateFeatures);
		selectWebElement(addNewTemplateFeaturesRecordButton);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(templateFeaturesPopupTitle);
		selectWebElement(tempnameDropdown);
		selectDropdownFromVisibleText(templateFeaturesTemplateNameListBox,details.getTemplatename());
		scrollToElement(saveBtn);
		selectWebElement(saveBtn);	
	}
	
	public void VerifyAddNewValidTemplateFeaturesRecord(AgentTemplateDetails details) {
		waitForJqueryLoad(driver);
		selectWebElement(templateFeatures);
		selectWebElement(addNewTemplateFeaturesRecordButton);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(templateFeaturesPopupTitle);
		selectWebElement(tempnameDropdown);
		selectDropdownFromVisibleText(templateFeaturesTemplateNameListBox,details.getTemplatename());
		selectCheckBox(isScreenCaptureEnabled,details.IsScreenCaptureEnabled());
		selectCheckBox(isTRSCameraPictureEnabled,details.IsTRSCameraPictureEnabled());
		selectCheckBox(isLocationEnabled,details.IsLocationEnabled());
		selectCheckBox(isFaceAuthenticationEnabled,details.IsFaceAuthenticationEnabled());
		scrollToElement(saveBtn);
		selectWebElement(saveBtn);	
	}
	
	public void editFeaturesRecordWithoutModifyreason(AgentTemplateDetails details,int x) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(templateFeatures);
		verifySearchIsEqualTo(details.getTemplatename(),x);
		selectWebElement(EditFeaturesTab);	
		waitForJqueryLoad(driver);
		selectCheckBox(isTRS_AUTO_UPDATE_INTERVAL,details.IsTRS_AUTO_UPDATE_INTERVAL());
		selectCheckBox(isTRS_LOG_UPLOAD_URL,details.IsTRS_LOG_UPLOAD_URL());
		scrollToElement(editSavebtn);
		selectWebElement(editSavebtn);
	}
	
	public void editCancelFeaturesTab(AgentTemplateDetails details,int x) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(templateFeatures);
		verifySearchIsEqualTo(details.getTemplatename(),x);
		selectWebElement(EditFeaturesTab);
		waitForJqueryLoad(driver);
		selectWebElement(editcancel);
		waitForJqueryLoad(driver);
	}
	
	public void editFeaturesRecord(AgentTemplateDetails details,int x) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(templateFeatures);
		verifySearchIsEqualTo(details.getTemplatename(),x);
		selectWebElement(EditFeaturesTab);	
		waitForJqueryLoad(driver);
		scrollToElement(isTRS_AUTO_UPDATE_INTERVAL);
		selectCheckBox(isTRS_AUTO_UPDATE_INTERVAL,details.IsTRS_AUTO_UPDATE_INTERVAL());
		selectCheckBox(isTRS_LOG_UPLOAD_URL,details.IsTRS_LOG_UPLOAD_URL());
		enterValueToTxtFieldWithoutClear(ModifyReasontext,details.getmodifyReason());
		scrollToElement(editSavebtn);
		selectWebElement(editSavebtn);
	}
	
	public void deleteWithoutDeletereasonFeaturestab(AgentTemplateDetails details,int x) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(templateFeatures);
		verifySearchIsEqualTo(details.getTemplatename(),x);
		waitForJqueryLoad(driver);
		selectWebElement(deleteBtnFeaturesTab);
		waitForJqueryLoad(driver);
		selectWebElement(deleteYesFeaturesTab);
	}

	public boolean DeleteCancelFeaturestab(AgentTemplateDetails details,int x) throws Exception {
		boolean status=false;
		waitForJqueryLoad(driver);
		selectWebElement(templateFeatures);
		verifySearchIsEqualTo(details.getTemplatename(),x);
		waitForJqueryLoad(driver);
		selectWebElement(deleteBtnFeaturesTab);
		waitForJqueryLoad(driver);		
		selectWebElement(deleteNoBtnFeaturesTab);	
		if(featuresTableRow.getText().equals(details.getTemplatename()))
			status=true;
		else
			status=false;
		return status;
		
	}

	public void deleteFeaturesRecord(AgentTemplateDetails details,int x) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(templateFeatures);
		verifySearchIsEqualTo(details.getTemplatename(),x);
		waitForJqueryLoad(driver);
		selectWebElement(deleteBtnFeaturesTab);
		enterValueToTxtFieldWithoutClear(deleteReasonTxtFeaturesTab,details.getdeleteReason());
		selectWebElement(deleteYesFeaturesTab);
	}

}

