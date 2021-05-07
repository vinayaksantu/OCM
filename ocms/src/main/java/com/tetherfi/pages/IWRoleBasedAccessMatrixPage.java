package com.tetherfi.pages;



import com.tetherfi.model.user.IWMasterAccessMatrixDetails;
import com.tetherfi.model.user.IWRoleBasedAccessMatrixDetails;
import com.tetherfi.utility.FileUploader;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IWRoleBasedAccessMatrixPage extends BasePage {

	public IWRoleBasedAccessMatrixPage(WebDriver driver){super(driver);}

	@FindBy(css=".ibox-title h5")
	private WebElement iwRoleBasedAccessMatrix;

	@FindBy(xpath="//i[@class='far fa-shield-check']")
	private WebElement IMAImage;

	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullScreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement restore;

	@FindBy(id="navbarheader")
	private WebElement header;

	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;

	@FindBy(xpath="//a[@title='Edit Column Settings']")
	private List<WebElement> headersDropdown;

	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;

	@FindBy(xpath="//button[@id='create']")
	private WebElement addRoleBasedAccessManagementRecord;

	@FindBy(css=".ibox-title h5")
	private WebElement RoleBasedAccessManagement;

	@FindBy(xpath="//a[text()='Role Name']")
	private WebElement gridRoleName;

	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement dropTarget;

	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupBy;

	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
	private WebElement groupByName;

	@FindBy(css="a[aria-label='Go to the next page']")
	private WebElement nextPageIcon;

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

	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;

	@FindBy(xpath="//span[@class='k-pager-sizes k-label']/span/span/span[1]")
	private WebElement pagerSize;

	@FindBy(css=".k-grid-content")
	private WebElement gridContent;

	@FindBy(id="export")
	private WebElement exportButton;

	@FindBy(id="tGrid")
	private WebElement auditGridContent;

	@FindBy(css="#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;

	@FindBy(css = ".fa-search")
	private WebElement searchBtn;


	@FindBy(xpath="//span[@aria-controls='1001sColumnName_listbox']")
	private WebElement selectSearchColumn;

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

	@FindBy(xpath="//span[@aria-owns='1001sCriteria_listbox']")
	private WebElement condition;

	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearall;

	@FindBy(xpath="//button[text()='Close']")
	private WebElement searchClose;

	@FindBy(xpath="//i[@class='fas fa-sync fa-spin']")
	private WebElement clearsearch;

	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;

	@FindBy(className="toast-message")
	private WebElement successmsg;

	@FindBy(xpath="//*[@id=\"grid\"]/div[4]/table/tbody/tr/td[2]")
	private WebElement FirstRowRecordRoleName;

	@FindBy(xpath="//div[@id='DrillReportNameLbl']")
	private WebElement RoleDetails;

	@FindBy(xpath="//a[@id='createone']")
	private WebElement saveChangesButton;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel-changes']")
	private WebElement cancelChangesButton;

	@FindBy(xpath="//input[@placeholder='Search by CallFlow Name..']")
	private WebElement searchByCallflowNamePlaceHolder;

	@FindBy(xpath="//h3[@class='md-card-toolbar-heading-text']")
	private List<WebElement>DashBordTitle;

	@FindBy(xpath="//i[@id='btnDashboardCreatednis']")
	private WebElement addNewIWFlow;

	@FindBy(xpath="//span[text()='Interaction Workflow : DNIS']")
	private WebElement addIWCallFlowDNISPopUP;

	@FindBy(xpath="//input[@id='txtNodeId']")
	private WebElement NodeId;

	@FindBy(xpath="//textarea[@id='txtNodedescription']")
	private WebElement nodeDescription;

	@FindBy(xpath="//input[@placeholder='Enter Maker DNIS']")
	private WebElement makerDNIS;

	@FindBy(xpath="//input[@placeholder='Enter Checker DNIS']")
	private WebElement checkerDNIS;

	@FindBy(xpath="//input[@id='txtcallflowname']")
	private WebElement flowName;

	@FindBy(xpath="//input[@id='chkCantonese']")
	private WebElement CantoneseCheckBox;

	@FindBy(xpath="//input[@id='txtvoiceTalent']")
	private WebElement txtvoiceTalent;

	@FindBy(xpath="//a[@id='btnSaveDnis']")
	private WebElement saveDNISCallFlow;

	@FindBy(xpath="//div[text()='Do you want to save?']")
	private WebElement CallFlowConfirmPopUp;

	@FindBy(xpath="//button[@class='js-modal-confirm md-btn-success md-btn-flat-primary md-btn md-btn-flat']")
	private WebElement CallFlowSaveConfirm;

	@FindBy(css=".uk-notify-message.uk-notify-message-success")
	private WebElement SuccessMessage;

	@FindBy(css=".uk-notify-message.uk-notify-message-danger")
	private WebElement ErrorMessage;

	@FindBy(xpath="//a[text()='Callflow Configuration']")
	private WebElement callFlowConfiguration;

	@FindBy(xpath="//input[@id='chkEnableSkillBasedRouting']")
	private WebElement skillBasedRoutingCHKBox;

	@FindBy(xpath="//input[@id='txtcallflowtoSearch']")
	private WebElement callFlowSearchTxtPlaceHolder;

	@FindBy(xpath="//i[@onclick='SearchDashboardCallflows();']")
	private WebElement searchCallFlowBtn;

	@FindBy(xpath="//h3[@class='md-card-head-text uk-text-center md-color-white uk-text-truncate']")
	private WebElement searchedCallFlow;

	@FindBy(xpath="//input[@id='autoCompleteTextbox']")
	private WebElement searchByCallFlowName;

	@FindBy(xpath="//input[@id='copy']")
	private WebElement CopycheckBox;

	@FindBy(xpath="//input[@id='importCallFlow']")
	private WebElement importCallFlowCheckBox;
	

	@FindBy(xpath="//input[@id='realtimeView']")
	private WebElement realtimeViewCheckBox;

	@FindBy(xpath="//input[@id='build']")
	private WebElement buildCheckBox;

	@FindBy(xpath="//input[@id='versionControl']")
	private WebElement versionControlCheckBox;

	@FindBy(xpath="//input[@id='moduleAccess']")
	private WebElement moduleAccessCheckBox;

	@FindBy(xpath="//input[@id='setupNode']")
	private WebElement setupNodeCheckBox;

	@FindBy(xpath="//input[@id='announcement']")
	private WebElement announcementCheckBox;

	@FindBy(xpath="//input[@id='entryPoint']")
	private WebElement entryPointCheckBox;

	@FindBy(xpath="//input[@id='menu']")
	private WebElement menuCheckBox;

	@FindBy(xpath="//input[@id='assignmemt']")
	private WebElement assignmemtCheckBox;

	@FindBy(xpath="//input[@id='conditional']")
	private WebElement conditionalCheckBox;

	@FindBy(xpath="//input[@id='disconnect']")
	private WebElement disconnectCheckBox;

	@FindBy(xpath="//input[@id='agent']")
	private WebElement agentCheckBox;

	@FindBy(xpath="//input[@id='exitPoint']")
	private WebElement exitPointCheckBox;

	@FindBy(xpath="//input[@id='promptAndCollect']")
	private WebElement promptAndCollectCheckBox;

	@FindBy(xpath="//input[@id='flowconnector']")
	private WebElement flowconnectorCheckBox;

	@FindBy(xpath="//input[@id='returnnode']")
	private WebElement returnnodeCheckBox;


	@FindBy(id="ModifyReasonUser")
	private  WebElement modifyReasonTextBox;

	@FindBy(css="ul[id='autoCompleteTextbox_listbox'] li")
	private List<WebElement> searchCallFlow;

	@FindBy(xpath="//i[text()='zoom_in']")
	private List<WebElement> previewCallFlowBtn;

	@FindBy(xpath="//span[text()='Main']")
	private WebElement MainIcon;

	@FindBy(xpath="//li[@class='iw-menu-button-border-right']")
	private List<WebElement> moreMenusBtn;

	@FindBy(xpath="//a[@id='btnImportCallFlow']")
	private WebElement CopyCallFlowBtn;

	@FindBy(xpath="//a[@id='btnImportPackage']")
	private WebElement ImportCallFlowBtn;

	@FindBy(xpath="//span[text()='Interaction Workflow : Copy Flow']")
	private WebElement CopyCallFlowWindow;

	@FindBy(xpath="//div[text()='Import Callflow Package']")
	private WebElement ImportCallFlowWindow;

	@FindBy(xpath="//input[@id='txtfileimportpackage']")
	private WebElement ChooseCallFlowBtn;

	@FindBy(xpath="//button[@class='js-modal-confirm md-btn-success md-btn-flat-primary md-btn md-btn-flat']")
	private WebElement importBtn;
	
	@FindBy(xpath="//button[@id='btnImportCallflow']")
	private WebElement CallFlowCopyBtninCopyWindow;

	@FindBy(xpath="//div[@class='uk-margin uk-modal-content']")
	private WebElement CopyCallFlowConfirmationPopUp;

	@FindBy(xpath="//button[@class='js-modal-confirm md-btn-success md-btn-flat-primary md-btn md-btn-flat']")
	private WebElement CopyCallFlowConfirmationPopUpYesBtn;

	@FindBy(xpath="//span[@aria-owns='ddlcallFlowListToImport_listbox']")
	private WebElement selectCallFlowToCopy;

	@FindBy(xpath="//div[@onclick='saveChangeYes()']")
	private WebElement editFormSaveBtn;

	@FindBy(css="ul[id='ddlcallFlowListToImport_listbox'] li")
	private List<WebElement> selectCallFlowToCopyDropDown;
	
	@FindBy(xpath="//label[@class='k-checkbox-label']")
	private WebElement OverWriteCallFlow;
	
	@FindBy(xpath="//a[@id='li_module_select']")
	private WebElement IwModuleNode;
	
	@FindBy(css="#txtModuleSearch")
	private WebElement ModuleSearchTab;
	
	@FindBy(xpath="//li[@id='btnAnnouncementNode']")
	private WebElement iwAnnouncementNode;
	
	@FindBy(xpath="//li[@id='btnEntryNode']")
	private WebElement iwEntrynode;
	
	@FindBy(xpath="//li[@id='btnAddNewMenu']")
	private WebElement iwMenunode;
	
	@FindBy(xpath="//li[@id='btnAssignment']")
	private WebElement iwAssignmentNode;
	
	@FindBy(xpath="//li[@id='btnConditionalNodeRule']")
	private WebElement iwConditionalNode;
	
	@FindBy(xpath="//li[@id='btnDisconnectNode']")
	private WebElement iwDisConnectNode;
	
	@FindBy(xpath="//li[@id='btnAgent']")
	private WebElement IwAgentnode;
	
	@FindBy(xpath="//li[@id='btnExitNode']")
	private WebElement iwexitNode;
	
	@FindBy(xpath="//li[@id='btnOrdertaking']")
	private WebElement iwpromtAndCollectNode;
	
	@FindBy(xpath="//li[@id='btnFlowConnectorNode']")
	private WebElement iwFlowConnecterNode;
	
	@FindBy(xpath="//li[@id='btnReturnNode']")
	private WebElement iwRetrunNode;
	
	@FindBy(xpath="//i[contains(normalize-space(),'card_travel')]")
	private WebElement callFlowBuild;





	public boolean isIWRoleBasedAccessMatrixPageDisplayed() {
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		return iwRoleBasedAccessMatrix.isEnabled();
	}

	public boolean verifyLogo() {
		if(isElementExist(IMAImage))
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

	public boolean verifycolumnsHeaderDisabled() {
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

	public boolean VerifyAddRolebasedAccessManagementRecord() throws InterruptedException {
		waitForJqueryLoad(driver);
		selectWebElement(addRoleBasedAccessManagementRecord);
		waitForJqueryLoad(driver);
		return RoleBasedAccessManagement.isEnabled();
	}

	public boolean groupBy() {
		DragandDrop(gridRoleName,dropTarget);
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();	
		}
		if(groupBy.getText().split(": ")[1].equals(groupByName.getText()))
			return true;
		else
			return false;

	}

	public boolean verifyArrowMoveForPreviousAndNextPage(){
		waitForJqueryLoad(driver);
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
		waitForJqueryLoad(driver);
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

	public boolean verifyTotalNumberOfItemsPerPageDetails(){
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

	public boolean ExportToExcelButton(String filePath) {
		final File folder=new File(filePath);
		for(final File f : folder.listFiles()) {
			if(f.getName().startsWith("IW Role Based Access Matrix")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath,"IW Role Based Access Matrix");
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

	private List<Map<String,String>> getData() {
		int item=Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.getText());
		int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++) {
			waitUntilWebElementIsVisible(auditGridContent);
			List<WebElement>rows=auditGridContent.findElements(By.tagName("tr"));
			List<WebElement>headers=rows.get(0).findElements(By.tagName("th"));
			String col=null;
			for(int i=1;i<rows.size();i++) {
				Map<String,String> map=new HashMap<String,String>();
				List<WebElement>cols=rows.get(i).findElements(By.tagName("td"));
				for(int j=0;j<headers.size();j++) {
					scrollToElement(headers.get(j));
					System.out.println(headers.get(j).getText());
					if(headers.get(j).getText().equals("Value"))
					{
						col=cols.get(j).getText()+".0";
						System.out.println(col);
						map.put(headers.get(j).getText(),col);
					}
					else {
						col=cols.get(j).getText();
						map.put(headers.get(j).getText(),col);
					}
				}
				map.remove("");
				arr.add(map);
			}
			if(k!=pages) {
				nextPageIcon.click();
				waitForJqueryLoad(driver);
			}

		}
		return arr;
	}

	public void verifySortByAscending() {
		waitUntilWebElementIsVisible(auditGridContent);
		selectWebElement(gridRoleName);
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
		selectWebElement(gridRoleName);
		selectWebElement(gridRoleName);
		waitForJqueryLoad(driver);
		selectWebElement(exportButton);
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean VerifyExportToExcelWithoutData(IWRoleBasedAccessMatrixDetails details) throws Exception {
		searchIWRoleBasedAcessMatrixRecord(details.getRoleName());
		waitForJqueryLoad(driver);
		selectWebElement(exportButton);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
			return false;
	}

	public void searchIWRoleBasedAcessMatrixRecord(String roleName) throws Exception {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Role Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		enterValueToTxtField(searchTextBox,roleName);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
	}

	public boolean verifySearchIsnotEqualTo(String roleName) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Role Name", roleName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Role Name");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
		enterValueToTxtField(searchTextBox,roleName);		
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

	public boolean verifySearchContains(String roleName) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Role Name", roleName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Role Name");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		enterValueToTxtField(searchTextBox,roleName);		
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

	public boolean verifySearchDoesNotContains(String roleName) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Role Name", roleName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Role Name");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not Contain");
		enterValueToTxtField(searchTextBox,roleName);		
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

	public boolean verifySearchStartsWith(String roleName) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Role Name", roleName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Role Name");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
		enterValueToTxtField(searchTextBox,roleName);		
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

	public boolean verifySearchEndsWith(String roleName) throws Exception {
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Role Name", roleName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Role Name");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
		enterValueToTxtField(searchTextBox,roleName);		
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

	public boolean clearAll(IWRoleBasedAccessMatrixDetails details) throws Exception {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Role Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		enterValueToTxtField(searchTextBox,details.getRoleName());
		selectWebElement(clearall);
		if(searchTextBox.isEnabled())
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

	public void searchwithoutextsearch(IWRoleBasedAccessMatrixDetails details) throws Exception {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Role Name");
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

	public boolean verifyinvalidsearchwithwrongdata(IWRoleBasedAccessMatrixDetails details) throws Exception {
		searchIWRoleBasedAcessMatrixRecord(details.getRoleName());
		if(norecords.isDisplayed())
			return true; 
		else
			return false;
	}

	public String VerifyMessage() {
		waitForJqueryLoad(driver);
		if(errorMsg.get(0).isDisplayed())
		{return errorMsg.get(0).getText();}
		else
		{return successmsg.getText();}
	}

	public boolean VerifyIWComponentsWindowPresence(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		searchIWRoleBasedAcessMatrixRecord(details.getRoleName());
		waitForJqueryLoad(driver);
		selectWebElement(FirstRowRecordRoleName);
		waitForJqueryLoad(driver);
		String gridRoleDetails="IW Role Based Access Matrix For Role: "+details.getRoleName()+" & Role Type :"+details.getRoleType();
		String windowRoleDetails=RoleDetails.getText();
		System.out.println(gridRoleDetails);
		System.out.println(windowRoleDetails);
		boolean status=false;
		if(gridRoleDetails.equals(windowRoleDetails)) {
			if(searchByCallflowNamePlaceHolder.isDisplayed()) {
				if(cancelChangesButton.isEnabled()) {
					if(saveChangesButton.isEnabled()) {
						status=true;
					}
					else {System.out.println("saveChangesButton is Disabled");}
				}
				else {System.out.println("cancelChangesButton is Disabled");}
			}
			else {System.out.println("searchByCallflowNamePlaceHolder is not Displayed");}
		}
		else {System.out.println("RoleDetails data Mismatch");}
		return status;
	}

	public void VerifySaveChnagesButton(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		searchIWRoleBasedAcessMatrixRecord(details.getRoleName());
		waitForJqueryLoad(driver);
		selectWebElement(FirstRowRecordRoleName);
		waitForJqueryLoad(driver);
		selectWebElement(saveChangesButton);

	}


	public boolean VerifyIWPage() {
		waitForJqueryLoad(driver);
		if(DashBordTitle.get(0).isDisplayed())
			return true;
		else
			return false;

	}

	public void switchToWindow(int i){
		ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(Tabs.get(i));
	}

	public void ADDNewIWCallFlow(IWRoleBasedAccessMatrixDetails details) throws Exception {
		switchToWindow(1);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(DashBordTitle.get(0));
		selectWebElement(addNewIWFlow);
		waitUntilWebElementIsVisible(addIWCallFlowDNISPopUP);
		selectWebElement(NodeId);
		enterValueToTxtField(NodeId,details.getNodeID());
		selectWebElement(nodeDescription);
		enterValueToTxtField(nodeDescription,details.getNodeDescription());
		enterValueToTxtFieldWithoutClear(makerDNIS,details.getMakerDNIS());
		makerDNIS.sendKeys(Keys.ENTER); 
		enterValueToTxtFieldWithoutClear(checkerDNIS,details.getCheckerDNIS());
		checkerDNIS.sendKeys(Keys.ENTER); 
		enterValueToTxtField(flowName,details.getCallFlowName());
		selectCheckbox(CantoneseCheckBox);
		enterValueToTxtField(txtvoiceTalent,details.getVoiceTalNet());
		selectWebElement(callFlowConfiguration);
		waitForJqueryLoad(driver);
		selectCheckbox(skillBasedRoutingCHKBox);
		selectWebElement(saveDNISCallFlow);
		waitUntilWebElementIsVisible(CallFlowConfirmPopUp);
		selectWebElement(CallFlowSaveConfirm);
	}

	public String VerifySuccessMessage() {
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(SuccessMessage);
		if(SuccessMessage.isDisplayed()) {
			System.out.println(SuccessMessage.getText());
			{return SuccessMessage.getText();}}
		else
		{return ErrorMessage.getText();}
	}

	public boolean VerifyCopyCheckBoxUnchecked(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		searchIWRoleBasedAcessMatrixRecord(details.getRoleName());
		waitForJqueryLoad(driver);
		selectWebElement(FirstRowRecordRoleName);
		waitForJqueryLoad(driver);
		enterValueToTxtFieldWithoutClear(searchByCallFlowName,details.getCallFlowName());
		selectDropdownFromVisibleText(searchCallFlow,details.getCallFlowName());
		Thread.sleep(3000);
		if(CopycheckBox.isSelected())
			return false;
		else 
			return true;
	}

	public void CopyCallFlowInIW(IWRoleBasedAccessMatrixDetails details) throws Exception {
		switchToWindow(1);
		driver.navigate().refresh();
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(DashBordTitle.get(0));
		enterValueToTxtField(callFlowSearchTxtPlaceHolder,details.getCallFlowName());
		selectWebElement(searchCallFlowBtn);
		System.out.println(searchedCallFlow.getText());
		selectWebElement(previewCallFlowBtn.get(0));
		waitUntilWebElementIsVisible(MainIcon);
		selectWebElement(moreMenusBtn.get(1));
		selectWebElement(CopyCallFlowBtn);
		waitUntilWebElementIsVisible(CopyCallFlowWindow);
		selectWebElement(selectCallFlowToCopy);
		selectCallFlowDropdownFromVisibleText(selectCallFlowToCopyDropDown,details.getCallFlowName());
		enterValueToTxtField(flowName,details.getNewCallFlowName());
		enterValueToTxtFieldWithoutClear(makerDNIS,details.getMakerDNIS());
		makerDNIS.sendKeys(Keys.ENTER); 
		enterValueToTxtFieldWithoutClear(checkerDNIS,details.getCheckerDNIS());
		checkerDNIS.sendKeys(Keys.ENTER);
		selectWebElement(CallFlowCopyBtninCopyWindow);
		waitUntilWebElementIsVisible(CopyCallFlowConfirmationPopUp);
		selectWebElement(CopyCallFlowConfirmationPopUpYesBtn);	 
	}

	public String VerifyErrorMessage() {
		waitForJqueryLoad(driver);
		if(ErrorMessage.isDisplayed())
		{return ErrorMessage.getText();}
		else
		{return SuccessMessage.getText();}
	}

	public void selectCallFlowDropdownFromVisibleText(List<WebElement> webElementList, String text) {
		waitUntilWebElementListIsVisible(webElementList);
		waitUntilWebElementListIsClickable(webElementList);
		for(WebElement ele: webElementList){
			String subText=text.split(" ")[0];
			//System.out.println(subText);
			String subTextEqualIgnoreCase=subText.toUpperCase();
			//System.out.println(subTextEqualIgnoreCase);
			//System.out.println(ele);
			if(ele.getText().contains(subTextEqualIgnoreCase)){ clickOn(ele);break;}
		}
	}

	public boolean VerifyCopyCheckBoxchecked(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		searchIWRoleBasedAcessMatrixRecord(details.getRoleName());
		waitForJqueryLoad(driver);
		selectWebElement(FirstRowRecordRoleName);
		waitForJqueryLoad(driver);
		enterValueToTxtFieldWithoutClear(searchByCallFlowName,details.getCallFlowName());
		selectDropdownFromVisibleText(searchCallFlow,details.getCallFlowName());
		Thread.sleep(3000);
		selectCheckbox(CopycheckBox);
		boolean status=false;
		if(CopycheckBox.isSelected()) {
			selectWebElement(saveChangesButton);
			System.out.println(details.getModifyReason());
			waitUntilWebElementIsVisible(modifyReasonTextBox);
			enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
			selectWebElement(editFormSaveBtn);
			if(successmsg.isDisplayed()) {
				status=true;
			}
			else {System.out.println("SuccessMessage is not Displayed");}
		}
		else {System.out.println("CopycheckBox is not Seleceted");}
		return status;
	}

	public boolean VerifyImportCallFlowCheckBoxUnchecked(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		searchIWRoleBasedAcessMatrixRecord(details.getRoleName());
		waitForJqueryLoad(driver);
		selectWebElement(FirstRowRecordRoleName);
		waitForJqueryLoad(driver);
		enterValueToTxtFieldWithoutClear(searchByCallFlowName,details.getCallFlowName());
		selectDropdownFromVisibleText(searchCallFlow,details.getCallFlowName());
		Thread.sleep(3000);
		selectCheckbox(importCallFlowCheckBox);
		if (importCallFlowCheckBox.isSelected()) {
			selectCheckbox(importCallFlowCheckBox);
		} else {
			System.out.println("ImportCall Flow Check Box Not Selected");
		}
		boolean status=false;
			selectWebElement(saveChangesButton);
			System.out.println(details.getModifyReason());
			waitUntilWebElementIsVisible(modifyReasonTextBox);
			selectWebElement(modifyReasonTextBox);
			enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
			selectWebElement(editFormSaveBtn);
			if(successmsg.isDisplayed()) {
				status=true;
			}
			else {System.out.println("SuccessMessage is not Displayed");}
		return status;
	}

	public void ImportCallFlowInIW(IWRoleBasedAccessMatrixDetails details) throws Exception {
		switchToWindow(1);
		driver.navigate().refresh();
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(DashBordTitle.get(0));
		enterValueToTxtField(callFlowSearchTxtPlaceHolder,details.getCallFlowName());
		selectWebElement(searchCallFlowBtn);
		System.out.println(searchedCallFlow.getText());
		selectWebElement(previewCallFlowBtn.get(0));
		waitUntilWebElementIsVisible(MainIcon);
		selectWebElement(moreMenusBtn.get(1));
		selectWebElement(ImportCallFlowBtn);
		Thread.sleep(3000);
		//waitUntilWebElementIsVisible(ImportCallFlowWindow);
		clickOnUsingActionClass(ChooseCallFlowBtn);
		//ChooseCallFlowBtn.click();
		FileUploader upload= new FileUploader();
		upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getImportCallFlowFile());
		//selectCheckbox(OverWriteCallFlow);
		selectWebElement(importBtn);
			 
	}
	
	public boolean VerifyImportCheckBoxcheckedForImportingCallFlow(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		searchIWRoleBasedAcessMatrixRecord(details.getRoleName());
		waitForJqueryLoad(driver);
		selectWebElement(FirstRowRecordRoleName);
		waitForJqueryLoad(driver);
		enterValueToTxtFieldWithoutClear(searchByCallFlowName,details.getImportingCallFlowName());
		selectDropdownFromVisibleText(searchCallFlow,details.getImportingCallFlowName());
		Thread.sleep(3000);
		selectCheckbox(importCallFlowCheckBox);
		if (importCallFlowCheckBox.isSelected()) {
			System.out.println("ImportCall Flow Check Box Selected");
		} else {
			selectCheckbox(importCallFlowCheckBox);
		}
		boolean status=false;
			selectWebElement(saveChangesButton);
			System.out.println(details.getModifyReason());
			waitUntilWebElementIsVisible(modifyReasonTextBox);
			selectWebElement(modifyReasonTextBox);
			enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
			selectWebElement(editFormSaveBtn);
			if(successmsg.isDisplayed()) {
				status=true;
			}
			else {System.out.println("SuccessMessage is not Displayed");}
		return status;
	}
	
	public boolean VerifyImportCheckBoxcheckedForCurrentCallFlow(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		searchIWRoleBasedAcessMatrixRecord(details.getRoleName());
		waitForJqueryLoad(driver);
		selectWebElement(FirstRowRecordRoleName);
		waitForJqueryLoad(driver);
		enterValueToTxtFieldWithoutClear(searchByCallFlowName,details.getCallFlowName());
		Thread.sleep(1000);
		selectDropdownFromVisibleText(searchCallFlow,details.getCallFlowName());
		Thread.sleep(3000);
		selectCheckbox(importCallFlowCheckBox);
		if (importCallFlowCheckBox.isSelected()) {
			System.out.println("ImportCall Flow Check Box Selected");
		} else {
			selectCheckbox(importCallFlowCheckBox);
		}
		boolean status=false;
			selectWebElement(saveChangesButton);
			System.out.println(details.getModifyReason());
			waitUntilWebElementIsVisible(modifyReasonTextBox);
			selectWebElement(modifyReasonTextBox);
			enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
			selectWebElement(editFormSaveBtn);
			if(successmsg.isDisplayed()) {
				status=true;
			}
			else {System.out.println("SuccessMessage is not Displayed");}
		return status;
	}
	
	private void NaviagateToCallflowAccessWindowforRole(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		searchIWRoleBasedAcessMatrixRecord(details.getRoleName());
		waitForJqueryLoad(driver);
		selectWebElement(FirstRowRecordRoleName);
		waitForJqueryLoad(driver);
		enterValueToTxtFieldWithoutClear(searchByCallFlowName,details.getCallFlowName());
		Thread.sleep(1000);
		selectDropdownFromVisibleText(searchCallFlow,details.getCallFlowName());
		Thread.sleep(3000);
	}
	
	private void UpdateAccessStatusINOCM(IWRoleBasedAccessMatrixDetails details) throws Exception {
		selectWebElement(saveChangesButton);
		System.out.println(details.getModifyReason());
		waitUntilWebElementIsVisible(modifyReasonTextBox);
		selectWebElement(modifyReasonTextBox);
		enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
		selectWebElement(editFormSaveBtn);
	}
	
	private void NavigateToCallFlow(IWRoleBasedAccessMatrixDetails details) throws Exception {
		switchToWindow(1);
		driver.navigate().refresh();
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(DashBordTitle.get(0));
		enterValueToTxtField(callFlowSearchTxtPlaceHolder,details.getCallFlowName());
		selectWebElement(searchCallFlowBtn);
		System.out.println(searchedCallFlow.getText());
		selectWebElement(previewCallFlowBtn.get(0));
		waitUntilWebElementIsVisible(MainIcon);
	}
	
	public boolean VerifyModuleNodeAccess(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		NaviagateToCallflowAccessWindowforRole(details);
		selectCheckbox(moduleAccessCheckBox);
		if (moduleAccessCheckBox.isSelected()) {
			System.out.println("moduleAccessCheckBox  Check Box Selected");
		} else {
			selectCheckbox(moduleAccessCheckBox);
		}
		UpdateAccessStatusINOCM(details);
		NavigateToCallFlow(details);
		selectWebElement(IwModuleNode);
		Thread.sleep(2000);
		if(ModuleSearchTab.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean VerifyAnnouncementNodeAccess(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		NaviagateToCallflowAccessWindowforRole(details);
		selectCheckbox(announcementCheckBox);
		if (announcementCheckBox.isSelected()) {
			System.out.println("announcementCheckBox  Check Box Selected");
		} else {
			selectCheckbox(announcementCheckBox);
		}
		UpdateAccessStatusINOCM(details);
		NavigateToCallFlow(details);
		Thread.sleep(2000);
		if(iwAnnouncementNode.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean VerifyEntryPoinNodeAccess(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		NaviagateToCallflowAccessWindowforRole(details);
		selectCheckbox(entryPointCheckBox);
		if (entryPointCheckBox.isSelected()) {
			System.out.println("entryPointCheckBox  Check Box Selected");
		} else {
			selectCheckbox(entryPointCheckBox);
		}
		UpdateAccessStatusINOCM(details);
		NavigateToCallFlow(details);
		Thread.sleep(2000);
		if(iwEntrynode.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean VerifyMenuNodeAccess(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		NaviagateToCallflowAccessWindowforRole(details);
		selectCheckbox(menuCheckBox);
		if (menuCheckBox.isSelected()) {
			System.out.println("menuCheckBox Selected");
		} else {
			selectCheckbox(menuCheckBox);
		}
		UpdateAccessStatusINOCM(details);
		NavigateToCallFlow(details);
		Thread.sleep(2000);
		if(iwMenunode.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean VerifyAssignmentNodeAccess(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		NaviagateToCallflowAccessWindowforRole(details);
		scrollToElement(assignmemtCheckBox);
		selectCheckbox(assignmemtCheckBox);
		if (assignmemtCheckBox.isSelected()) {
			System.out.println("assignmemtCheckBox Selected");
		} else {
			selectCheckbox(assignmemtCheckBox);
		}
		UpdateAccessStatusINOCM(details);
		NavigateToCallFlow(details);
		Thread.sleep(2000);
		if(iwAssignmentNode.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean VerifyConditionalNodeAccess(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		NaviagateToCallflowAccessWindowforRole(details);
		scrollToElement(conditionalCheckBox);
		selectCheckbox(conditionalCheckBox);
		if (conditionalCheckBox.isSelected()) {
			System.out.println("conditionalCheckBox Selected");
		} else {
			selectCheckbox(conditionalCheckBox);
		}
		UpdateAccessStatusINOCM(details);
		NavigateToCallFlow(details);
		Thread.sleep(2000);
		if(iwConditionalNode.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean VerifyDisconnectNodeAccess(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		NaviagateToCallflowAccessWindowforRole(details);
		scrollToElement(disconnectCheckBox);
		selectCheckbox(disconnectCheckBox);
		if (disconnectCheckBox.isSelected()) {
			System.out.println("disconnectCheckBox Selected");
		} else {
			selectCheckbox(disconnectCheckBox);
		}
		UpdateAccessStatusINOCM(details);
		NavigateToCallFlow(details);
		Thread.sleep(2000);
		if(iwDisConnectNode.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean VerifyAgentNodeAccess(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		NaviagateToCallflowAccessWindowforRole(details);
		scrollToElement(agentCheckBox);
		selectCheckbox(agentCheckBox);
		if (agentCheckBox.isSelected()) {
			System.out.println("agentCheckBox Selected");
		} else {
			selectCheckbox(agentCheckBox);
		}
		UpdateAccessStatusINOCM(details);
		NavigateToCallFlow(details);
		Thread.sleep(2000);
		if(IwAgentnode.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean VerifyExitPointNodeAccess(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		NaviagateToCallflowAccessWindowforRole(details);
		scrollToElement(exitPointCheckBox);
		selectCheckbox(exitPointCheckBox);
		if (exitPointCheckBox.isSelected()) {
			System.out.println("exitPointCheckBox Selected");
		} else {
			selectCheckbox(exitPointCheckBox);
		}
		UpdateAccessStatusINOCM(details);
		NavigateToCallFlow(details);
		Thread.sleep(2000);
		if(iwexitNode.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean VerifyPromptandColletNodeAccess(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		NaviagateToCallflowAccessWindowforRole(details);
		scrollToElement(promptAndCollectCheckBox);
		selectCheckbox(promptAndCollectCheckBox);
		if (promptAndCollectCheckBox.isSelected()) {
			System.out.println("promptAndCollectCheckBox Selected");
		} else {
			selectCheckbox(promptAndCollectCheckBox);
		}
		UpdateAccessStatusINOCM(details);
		NavigateToCallFlow(details);
		Thread.sleep(2000);
		if(iwpromtAndCollectNode.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean VerifyFlowConnecterNodeAccess(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		NaviagateToCallflowAccessWindowforRole(details);
		scrollToElement(flowconnectorCheckBox);
		selectCheckbox(flowconnectorCheckBox);
		if (flowconnectorCheckBox.isSelected()) {
			System.out.println("flowconnectorCheckBox Selected");
		} else {
			selectCheckbox(flowconnectorCheckBox);
		}
		UpdateAccessStatusINOCM(details);
		NavigateToCallFlow(details);
		Thread.sleep(2000);
		if(iwFlowConnecterNode.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean VerifyReturnNodeAccess(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		NaviagateToCallflowAccessWindowforRole(details);
		scrollToElement(returnnodeCheckBox);
		selectCheckbox(returnnodeCheckBox);
		if (returnnodeCheckBox.isSelected()) {
			System.out.println("returnnodeCheckBox Selected");
		} else {
			selectCheckbox(returnnodeCheckBox);
		}
		UpdateAccessStatusINOCM(details);
		NavigateToCallFlow(details);
		Thread.sleep(2000);
		if(iwRetrunNode.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean VerifyBuildCallFlowCheckBoxUnchecked(IWRoleBasedAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		searchIWRoleBasedAcessMatrixRecord(details.getRoleName());
		waitForJqueryLoad(driver);
		selectWebElement(FirstRowRecordRoleName);
		waitForJqueryLoad(driver);
		enterValueToTxtFieldWithoutClear(searchByCallFlowName,details.getCallFlowName());
		selectDropdownFromVisibleText(searchCallFlow,details.getCallFlowName());
		Thread.sleep(3000);
		selectCheckbox(buildCheckBox);
		if (buildCheckBox.isSelected()) {
			selectCheckbox(buildCheckBox);
		} else {
			System.out.println("ImportCall Flow Check Box Not Selected");
		}
		boolean status=false;
			selectWebElement(saveChangesButton);
			System.out.println(details.getModifyReason());
			waitUntilWebElementIsVisible(modifyReasonTextBox);
			selectWebElement(modifyReasonTextBox);
			enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
			selectWebElement(editFormSaveBtn);
			if(successmsg.isDisplayed()) {
				status=true;
			}
			else {System.out.println("SuccessMessage is not Displayed");}
		return status;
	}
	
	public void BuildCallFlowInIW(IWRoleBasedAccessMatrixDetails details) throws Exception {
		switchToWindow(1);
		driver.navigate().refresh();
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(DashBordTitle.get(0));
		enterValueToTxtField(callFlowSearchTxtPlaceHolder,details.getCallFlowName());
		selectWebElement(searchCallFlowBtn);
		System.out.println(searchedCallFlow.getText());
		selectWebElement(callFlowBuild);	 
	}
	
}
