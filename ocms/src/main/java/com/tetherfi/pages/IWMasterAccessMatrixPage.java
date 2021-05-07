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

import com.tetherfi.model.tmac.TmacAuxCodesDetails;
import com.tetherfi.model.user.IWMasterAccessMatrixDetails;
import com.tetherfi.model.user.UserRoleMappingDetails;

public class IWMasterAccessMatrixPage extends BasePage{

	public IWMasterAccessMatrixPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".ibox-title h5")
	private WebElement iwMasterAccessMatrix;

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

	@FindBy(css="a[aria-label='Go to the next page']")
	private WebElement nextPageIcon;

	@FindBy(xpath="//a[text()='Role Name']")
	private WebElement gridRoleName;

	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement dropTarget;

	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupBy;

	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
	private WebElement groupByName;

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

	@FindBy(css="#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;

	@FindBy(xpath="//span[@aria-owns='1001sCriteria_listbox']")
	private WebElement condition;

	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearall;

	@FindBy(xpath="//button[text()='Close']")
	private WebElement searchClose;

	@FindBy(className="toast-message")
	private WebElement successmsg;

	@FindBy(xpath="//i[@class='fas fa-sync fa-spin']")
	private WebElement clearsearch;

	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;

	@FindBy(xpath="//button[@id='create']")
	private WebElement addRoleBasedAccessManagementRecord;

	@FindBy(css=".ibox-title h5")
	private WebElement RoleBasedAccessManagement;

	@FindBy(xpath="//*[@id=\"grid\"]/div[4]/table/tbody/tr/td[2]")
	private WebElement FirstRowRecordRoleName;

	@FindBy(xpath="//h3[text()=' IW Master Access Matrix']")
	private WebElement MasterComponentsWindowTitle;

	@FindBy(xpath="//div[@id='DrillReportNameSubLbl']")
	private WebElement RoleDetails;

	@FindBy(xpath="//label[text()='Modify Reason*']")
	private List<WebElement> ModifyReasonLabel;
	
	@FindBy(xpath="//button[@class='k-button k-grid-cancel']")
	private WebElement masterComponenetWindowCancelButton;
	
	@FindBy(xpath="//button[@class='k-button  k-grid-update']")
	private WebElement masterComponentWindowSaveButton;
	
	@FindBy(css="#IntentMaster.k-checkbox")
	private WebElement IntentMasterCheckBox;
	














	public boolean isIWMasterAccessPageDisplayed() {
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		return iwMasterAccessMatrix.isEnabled();
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
			if(f.getName().startsWith("IW Master Access Matrix")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath,"IW Master Access Matrix");
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

	public void searchIWMasterAcessMatrixRecord(String roleName) throws Exception {
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

	public boolean VerifyExportToExcelWithoutData(IWMasterAccessMatrixDetails details) throws Exception {
		searchIWMasterAcessMatrixRecord(details.getRoleName());
		waitForJqueryLoad(driver);
		selectWebElement(exportButton);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
			return false;
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

	public boolean clearAll(IWMasterAccessMatrixDetails details) throws Exception {
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

	public String VerifyMessage() {
		waitForJqueryLoad(driver);
		if(errorMsg.get(0).isDisplayed())
		{return errorMsg.get(0).getText();}
		else
		{return successmsg.getText();}
	}

	public void searchwithoutextsearch(IWMasterAccessMatrixDetails details) throws Exception {
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

	public boolean verifyinvalidsearchwithwrongdata(IWMasterAccessMatrixDetails details) throws Exception {
		searchIWMasterAcessMatrixRecord(details.getRoleName());
		if(norecords.isDisplayed())
			return true; 
		else
			return false;
	}

	public boolean VerifyAddRolebasedAccessManagementRecord() throws InterruptedException {
		waitForJqueryLoad(driver);
		selectWebElement(addRoleBasedAccessManagementRecord);
		waitForJqueryLoad(driver);
		return RoleBasedAccessManagement.isEnabled();
	}

	public boolean VerifyIWMasterComponentsWindowPresence(IWMasterAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		searchIWMasterAcessMatrixRecord(details.getRoleName());
		waitForJqueryLoad(driver);
		selectWebElement(FirstRowRecordRoleName);
		String gridRoleDetails="For Role: "+details.getRoleName()+" & Role Type :"+details.getRoleType();
		String windowRoleDetails=RoleDetails.getText();
		System.out.println(gridRoleDetails);
		System.out.println(windowRoleDetails);
		boolean status=false;
		if(MasterComponentsWindowTitle.isDisplayed()) {
			if(gridRoleDetails.equals(windowRoleDetails)) {
				if(ModifyReasonLabel.get(1).isDisplayed()) {
					if(masterComponenetWindowCancelButton.isEnabled()) {
						if(masterComponentWindowSaveButton.isEnabled()) {
							status=true;
						}
						else {System.out.println("masterComponentWindowSaveButton is Disabled");}
					}
					else {System.out.println("masterComponenetWindowCancelButton is Disabled");}
				}
				else {System.out.println("ModifyReasonLabel is not Displayed");}
			}
			else {System.out.println("RoleDetails data Mismatch");}
		}
		else {System.out.println("MasterComponentsWindowTitle is not Displayed");}
     return status;
	}
	
	public boolean VerifyIWMasterComponentsWindowCancelButton(IWMasterAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		searchIWMasterAcessMatrixRecord(details.getRoleName());
		waitForJqueryLoad(driver);
		selectWebElement(FirstRowRecordRoleName);
		waitUntilWebElementIsVisible(MasterComponentsWindowTitle);
		selectWebElement(masterComponenetWindowCancelButton);
		if(iwMasterAccessMatrix.isDisplayed()) 
			return true;
		else
			return false;
	}
	
	public void VerifyUpdateIWMasterComponentWithOutModifyReason(IWMasterAccessMatrixDetails details) throws Exception {
		waitForJqueryLoad(driver);
		searchIWMasterAcessMatrixRecord(details.getRoleName());
		waitForJqueryLoad(driver);
		selectWebElement(FirstRowRecordRoleName);
		waitUntilWebElementIsVisible(MasterComponentsWindowTitle);
		selectCheckbox(IntentMasterCheckBox);
		selectWebElement(masterComponentWindowSaveButton);
	}

}
