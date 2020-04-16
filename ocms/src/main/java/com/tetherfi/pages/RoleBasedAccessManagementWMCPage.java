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

import com.tetherfi.model.user.CepEventMappingDetails;
import com.tetherfi.model.user.UserDetails;

public class RoleBasedAccessManagementWMCPage extends BasePage{

	public RoleBasedAccessManagementWMCPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//i[@class='fa  fa-users']")
	private WebElement RBAMImg;

	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;

	@FindBy(css="th a[class='k-header-column-menu']")
	private List<WebElement> headersDropdown;

	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;

	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;		

	@FindBy(xpath="//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")
	private List<WebElement> tablerecord;


	@FindBy(id="navbarheader")
	private WebElement header;

	@FindBy(css=".k-grid-cancel")
	private WebElement cancelBtn;

	@FindBy(css="a[aria-label='Go to the next page']")
	private WebElement nextPageIcon;

	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupby;

	@FindBy(css="a[aria-label='Go to the first page']")
	private WebElement firstPageIcon;

	@FindBy(css="a[aria-label='Go to the previous page']")
	private WebElement previousPageIcon;

	@FindBy(css=".k-pager-numbers .k-state-selected")
	private WebElement pageNumber;

	@FindBy(css="a[aria-label='Go to the last page']")
	private WebElement lastPageIcon;

	@FindBy(id="create")
	private WebElement addRoleBasedAccessManagementRecordBtn;

	@FindBy(id="RoleName")
	private WebElement roleNameTextBox;

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

	@FindBy(css="#Role_taglist .k-select")
	private List<WebElement> roleCloseIcons;

	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;

	@FindBy(css=".k-edit-form-container .k-multiselect")
	private WebElement RoleDropDown;

	@FindBy(css="ul[id=Role_listbox] li")
	private List<WebElement> RoleListBox;

	@FindBy(css=".modal-footer .k-grid-save")
	private WebElement saveBtn;

	@FindBy(css=".k-edit-buttons .k-grid-update")
	private WebElement editFormSaveBtn;

	@FindBy(css=".toast-message")
	private WebElement successmsg;

	@FindBy(css="#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;

	@FindBy(css = ".fa-search")
	private WebElement searchBtn;

	@FindBy(css = ".modal-body .form-inline .form-group .k-select")
	private List<WebElement> selectSearchCol;

	@FindBy(css="ul[id='1001sColumnName_listbox'] li")
	private List<WebElement> columnNameList;

	@FindBy(css = "#1001sFormDropDown .k-i-arrow-60-down")
	private List<WebElement> selectSearchCriteria;

	@FindBy(css="ul[id='1001sCriteria_listbox'] li")
	private List<WebElement> searchCriteriaDropDwn;

	@FindBy(id = "1001sTextToSearch")
	private WebElement searchTextBox;

	@FindBy(css = ".modal-footer .button-theme")
	private WebElement searchSearchBtn;

	@FindBy(css=".k-grid-content")
	private WebElement gridContent;

	@FindBy(css="#drillGrid tbody tr td")
	private List<WebElement> editrowdata;

	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;

	@FindBy(css=".k-pager-sizes .k-icon")
	private WebElement pagerDropdown;

	@FindBy(css=".k-animation-container ul li")
	private List<WebElement> pageSizeListBox;

	@FindBy(xpath="//span[@class='k-input']")
	private WebElement pagerSize;

	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearall;

	@FindBy(xpath="//button[text()='Close']")
	private WebElement searchClose;

	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;

	@FindBy(xpath="//i[@class='fas fa-sync']")
	private WebElement clearsearch;

	@FindBy(id="drillGrid")
	private WebElement auditGridContent;

	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;

	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
	private WebElement groupbyRoleName;

	@FindBy(xpath="//a[text()='Role Name']")
	private WebElement RoleName;
	
	@FindBy(xpath="//table[@class='k-selectable']/tbody/tr/td[2]")
    private WebElement rowdata;
    
    @FindBy(id="DrillReportNameLbl")
    private WebElement PageBasedAccess;
    
    @FindBy(id="checkAll")
    private WebElement checkallAccess;
    
    @FindBy(id="checkAllReport")
    private WebElement checkallReportAccess;
    
    @FindBy(id="checkAllDashboard")
    private WebElement checkallDashboardAccess;
    
    
    @FindBy(id="checkAllAddAccess")
    private List<WebElement> checkalladdaccess;
    
    @FindBy(id="checkAllEditAccess")
    private List<WebElement> checkalleditaccess;
    
    @FindBy(id="checkAllDeleteAccess")
    private List<WebElement> checkalldeleteaccess;
    
    @FindBy(id="checkAllExportAccess")
    private WebElement checkallexportaccess;
    
    @FindBy(id="checkAllReportExportAccess")
    private WebElement checkallReportexportaccess;

    
    @FindBy(id="checkAll")
    private List <WebElement> accessAll;
    
    @FindBy(xpath="//div[@id='DrillReportNameLbl']/h2")
	private WebElement pagebaseduseraccess;
   
    @FindBy(css="#popupDrill .modal-dialog .modal-content .close")
    private WebElement pbuaclose;
    
    @FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel-changes']")
	private List<WebElement> pbuacancel;
	
	@FindBy(id="myWindowUser_wnd_title")
	private WebElement modifypopup;
	
	@FindBy(css=".toast-info .toast-message")
	private WebElement changesMsg;
	
	@FindBy(id="createone")
    private List<WebElement> saveaccess;
    
    @FindBy(xpath="//div[@onclick='saveChangeYes()']")
    private WebElement useryesBtn;
    
    @FindBy(id="ModifyReasonUser")
    private WebElement userModifyReasontxtbox;
    
    @FindBy(xpath="//span[text()='Reports']")
	private WebElement navigatetoreports;
	
	@FindBy(xpath="//span[text()='Dashboards']")
	private WebElement navigatetodashboard;
	
	@FindBy(id="checkAllOtherApplications")
    private WebElement checkallotherapplication;
	
	@FindBy(xpath="//span[text()='Other Applications']")
	private WebElement navigatetootherapplication;

	public boolean verifylogo() {
		if(isElementExist(RBAMImg))
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

	public boolean verifycolumnsHeaderDisabled() {
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

	public boolean addNewCancel(UserDetails UserDetails) {
		String actualitems=items.getText();
		selectWebElement(addRoleBasedAccessManagementRecordBtn);
		waitForJqueryLoad(driver);
		selectWebElement(cancelBtn);
		if(actualitems.equals(items.getText()))
			return true;
		else
			return false;
	}

	public void addRoleBasedAccessManagementRecord(UserDetails details) throws Exception {
		selectWebElement(addRoleBasedAccessManagementRecordBtn);
		selectWebElement(roleNameTextBox);
		enterValueToTxtField(roleNameTextBox,details.getRoleName());
		btnClick(editFormSaveBtn);
	}  

	public String getSuccessMessage() {
		waitForJqueryLoad(driver);
		if(successmsg.isDisplayed())
			return successmsg.getText();
		else{return errorMsg.get(0).getText();}

	}

	public String getErrorMsg() {
		waitUntilWebElementListIsVisible(errorMsg);
		return errorMsg.get(0).getText();
	}

	public void addwithoutRole(UserDetails details) throws Exception {
		selectWebElement(addRoleBasedAccessManagementRecordBtn);
		btnClick(editFormSaveBtn);
		selectWebElement(cancelBtn);		
	}

	public void searchRoleBasedAccessManagementRecord(String RoleName) throws Exception  {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Role Name");
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
		enterValueToTxtField(searchTextBox,RoleName);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
	}
	public void editRoleBasedAccessManagementRecord(UserDetails details) throws Exception {
		searchRoleBasedAccessManagementRecord(details.getRoleName());
		selectWebElement(editBtn);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(roleNameTextBox);
		enterValueToTxtField(roleNameTextBox,details.getUpdateRoleName());
		enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
		btnClick(editFormSaveBtn);
	}

	public boolean EditCancel(UserDetails details) throws Exception {
		searchRoleBasedAccessManagementRecord(details.getRoleName());
		selectWebElement(editBtn);
		waitForJqueryLoad(driver);
		selectWebElement(cancelBtn);
		if(editrowdata.get(1).getText().equals(details.getRoleName()))
			return true;
		else
			return false;
	}

	public void EditRecordWithoutModifyReason(UserDetails details) throws Exception {
		searchRoleBasedAccessManagementRecord(details.getUpdateRoleName());
		selectWebElement(editBtn);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(roleNameTextBox);
		enterValueToTxtField(roleNameTextBox,details.getUpdateRoleName());
		btnClick(editFormSaveBtn);			
	}

	public boolean verifyArrowMoveForPreviousAndNextPage(){
		boolean status=false;
		if(!nextPageIcon.getAttribute("class").contains("k-state-disabled")){
			int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(nextPageIcon);
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(previousPageIcon);
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
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(firstPageIcon);
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

	public boolean clearAll(UserDetails details) throws Exception {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Role Name");
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
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

	public void searchwithoutextsearch(UserDetails details) {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Role Name");
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

	public boolean verifyinvalidsearch(UserDetails details) throws Exception {
		searchRoleBasedAccessManagementRecord(details.getRoleName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(norecords.isDisplayed())
			return true; 
		else
			return false;
	}

	public boolean verifyExportToExcel(String filePath) {
		final File folder = new File(filePath);
		for (final File f : folder.listFiles()) {
			if (f.getName().startsWith("Role Based Access Management")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "Role Based Access Management");
		return Status;
	}
	public boolean verifyexportToExcelSheet(List<Map<String, String>> maplist) {
		List<Map<String,String>> UI=getdata(); 
		System.out.println(UI);
		System.out.println(maplist);
		if(UI.equals(maplist))
			return true;
		else
			return false;
	}

	private List<Map<String,String>> getdata(){
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
				for(int j=1;j<headers.size();j++) {
					scrollToElement(headers.get(j));
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
	public boolean verifyDatabase(String query) {
		List<Map<String,String>> database=database(query);
		System.out.println(database);
		List<Map<String,String>> UI=gettable(); 
		System.out.println(UI);
		if(UI.equals(database))
			return true;
		else
			return false;
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
				nextPageIcon.click();
				waitForJqueryLoad(driver);}
		}
		return arr;
	}

	public boolean groupby() {
		DragandDrop(RoleName,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(groupbyRoleName.getText()))
		{return true;}
		else
			return false;		
	}

	public boolean ExporttoExcelWithoutData(UserDetails details ) throws Exception {
		searchRoleBasedAccessManagementRecord(details.getRoleName());
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(exporttoexcel);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
			return false;
	}

	public void SortByAscending() {
		selectWebElement(RoleName);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(RoleName);
		selectWebElement(RoleName);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean verifySearchIsNotEqualTo(String RoleName) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("Role Name", RoleName);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Role Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
		enterValueToTxtField(searchTextBox,RoleName);		
		selectWebElement(searchSearchBtn);
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
	public boolean verifySearchContains(String RoleName) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Role Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		enterValueToTxtField(searchTextBox,RoleName);		
		selectWebElement(searchSearchBtn);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("Role Name").toUpperCase().contains(RoleName.toUpperCase()))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}
	public boolean verifySearchDoesNotContains(String roleName) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Role Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		Thread.sleep(1000);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not contain");
		enterValueToTxtField(searchTextBox,roleName);		
		selectWebElement(searchSearchBtn);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(!map1.get("Role Name").toLowerCase().contains(roleName.toLowerCase()))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}

	public boolean verifySearchStartsWith(String roleName) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Role Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
		enterValueToTxtField(searchTextBox,roleName);		
		selectWebElement(searchSearchBtn);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("Role Name").toLowerCase().startsWith(roleName.toLowerCase()))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}

	public boolean verifySearchEndsWith(String RoleName) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Role Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
		enterValueToTxtField(searchTextBox,RoleName);		
		selectWebElement(searchSearchBtn);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("Role Name").toUpperCase().endsWith(RoleName.toUpperCase()))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}

	public void DeleteRecordWithoutModifyReason(UserDetails details) throws Exception {
		searchRoleBasedAccessManagementRecord(details.getRoleName());
		Thread.sleep(1000);
		selectWebElement(deleteBtn);
		Thread.sleep(1000);
		selectWebElement(deleteReasonTextBox);
		selectWebElement(yesBtn);		
		selectWebElement(noBtn);
	}

	public boolean DeleteCancel(UserDetails details) throws Exception {
		searchRoleBasedAccessManagementRecord(details.getRoleName());
		selectWebElement(deleteBtn);
		waitForJqueryLoad(driver);
		selectWebElement(noBtn);
		if(editrowdata.get(1).getText().equals(details.getRoleName()))
			return true;
		else
			return false;
	}

	public void DeleteRoleBasedAccessManagementRecord(UserDetails details) throws Exception {
		searchRoleBasedAccessManagementRecord(details.getRoleName());
		Thread.sleep(1000);
		selectWebElement(deleteBtn);
		Thread.sleep(1000);
		selectWebElement(deleteReasonTextBox);
		enterValueToTxtFieldWithoutClear(deleteReasonTextBox,details.getDeleteReason());
		selectWebElement(yesBtn);				
	}
	
	public boolean verifypagebaseduseraccess(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(pagebaseduseraccess.isDisplayed())
			return true;
		else
		return false;
	}
	
	public boolean verifyclosebutton()
	{
		moveToElement(pbuaclose);
		selectWebElement(pbuaclose);
		if(rowdata.isDisplayed())
			return true;
		else
		return false;
	}
	
	public boolean verifyaccesscheckbox(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=false;
		//if(!checkallAccess.isEnabled())
		selectWebElement(checkallAccess);
		if(checkallAccess.isEnabled()) {
			selectWebElement(checkalladdaccess.get(0));
				if(checkalladdaccess.get(0).isEnabled()) {
					selectWebElement(checkalleditaccess.get(0));
					if(checkalleditaccess.get(0).isEnabled())
					{
						selectWebElement(checkalldeleteaccess.get(0));
						if(checkalldeleteaccess.get(0).isEnabled())
						{
							selectWebElement(checkallexportaccess);
							if(checkallexportaccess.isEnabled())
							{
							Status=true;
							}
						}
				
					}
				}
		}
		return Status;
	}
	
	public boolean verifycancelchanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(checkallAccess);
		Thread.sleep(500);
		selectWebElement(pbuacancel.get(0));
		Thread.sleep(1000);
		if(checkallAccess.isSelected())
			return false;
		else
		return true;
	}		
	public void verifysavechanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(checkallAccess);
		selectWebElement(saveaccess.get(0));
		if(modifypopup.isDisplayed())
		{
			selectWebElement(userModifyReasontxtbox);
			Thread.sleep(1000);
			enterValueToTxtFieldWithoutClear(userModifyReasontxtbox,"Modified");
			clickOn(useryesBtn);
		}
	}	
	
	public boolean verifyunsuccessfullcancelchanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());									 
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(pbuacancel.get(0));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}
	
	public boolean verifyunsuccessfullsavechanges(UserDetails userDetails) throws Exception{
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());									 
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(saveaccess.get(0));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}
	
	public boolean verifyreportsaccesscheckbox(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetoreports);
		Thread.sleep(1000);
		Boolean Status=false;
		selectWebElement(checkallReportAccess);
		if(checkallReportAccess.isSelected())
		{
			selectWebElement(checkallReportexportaccess);
			if(checkallReportexportaccess.isSelected())
			{
				Status=true;
			}
		}
		return Status;
	}

	public boolean verifyReportscancelchanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetoreports);
		Thread.sleep(1000);
		selectWebElement(checkallReportAccess);
		selectWebElement(pbuacancel.get(2));
		Thread.sleep(1000);
		if(checkallReportAccess.isSelected())
			return false;
		else
		return true;
	}

	public void verifyReportssavechanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetoreports);
		Thread.sleep(1000);
		selectWebElement(checkallReportAccess);
		selectWebElement(saveaccess.get(2));
		if(modifypopup.isDisplayed())
		{	
			selectWebElement(userModifyReasontxtbox);
			Thread.sleep(1000);
			enterValueToTxtFieldWithoutClear(userModifyReasontxtbox,"Modified");
			Thread.sleep(1000);
			clickOn(useryesBtn);
		}
	}

	public boolean verifyreportsunsuccessfullcancelchanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetoreports);
		selectWebElement(pbuacancel.get(2));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}

	public boolean verifyreportsunsuccessfullsavechanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetoreports);
		selectWebElement(saveaccess.get(2));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}

	public boolean verifydashboardaccesscheckbox(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetodashboard);
		Boolean Status=false;
		selectWebElement(checkallDashboardAccess);
		if(checkallDashboardAccess.isSelected())
				Status=true;
		return Status;
	}


	public boolean verifyDashboardcancelchanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetodashboard);
		Thread.sleep(1000);
		selectWebElement(checkallDashboardAccess);
		selectWebElement(pbuacancel.get(3));
		Thread.sleep(1000);
		if(checkallDashboardAccess.isSelected())
			return false;
		else
		return true;
	}

	public void verifyDashboardsavechanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetodashboard);
		Thread.sleep(1000);
		selectWebElement(checkallDashboardAccess);
		selectWebElement(saveaccess.get(3));
		if(modifypopup.isDisplayed())
		{	
			selectWebElement(userModifyReasontxtbox);
			Thread.sleep(1000);
			enterValueToTxtFieldWithoutClear(userModifyReasontxtbox,"Modified");
			Thread.sleep(1000);
			clickOn(useryesBtn);
		}
	}

	public boolean verifydashboardunsuccessfullcancelchanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetodashboard);
		selectWebElement(pbuacancel.get(3));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}

	public boolean verifydashboardunsuccessfullsavechanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetodashboard);
		selectWebElement(saveaccess.get(3));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}
	
	public boolean verifyotherapplicationaccesscheckbox(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetootherapplication);
		Boolean Status=false;
		selectWebElement(checkallotherapplication);
		if(checkallotherapplication.isSelected())
			{
				Status=true;
			}
		return Status;
	}


	public boolean verifyotherapplicationcancelchanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetootherapplication);
		Thread.sleep(1000);
		selectWebElement(checkallotherapplication);
		selectWebElement(pbuacancel.get(4));
		Thread.sleep(1000);
		if(checkallotherapplication.isSelected())
			return false;
		else
		return true;
	}

	public void verifyotherapplicationsavechanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetootherapplication);
		Thread.sleep(1000);
		selectWebElement(checkallotherapplication);
		selectWebElement(saveaccess.get(4));
		if(modifypopup.isDisplayed())
		{	
			selectWebElement(userModifyReasontxtbox);
			Thread.sleep(1000);
			enterValueToTxtFieldWithoutClear(userModifyReasontxtbox,"Modified");
			Thread.sleep(1000);
			clickOn(useryesBtn);
		}
	}

	public boolean verifyotherapplicationunsuccessfullcancelchanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetootherapplication);
		selectWebElement(pbuacancel.get(4));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}

	public boolean verifyotherapplicationunsuccessfullsavechanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetootherapplication);
		selectWebElement(saveaccess.get(4));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}
}
