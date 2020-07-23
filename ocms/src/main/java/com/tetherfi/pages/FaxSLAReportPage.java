package com.tetherfi.pages;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.report.ReportDetails;

public class FaxSLAReportPage extends BasePage  {

	public FaxSLAReportPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//i[@class='fas fa-file-export']")
	private WebElement VEFImg;

	@FindBy(css=".ibox-title h5")
	private WebElement exportSchedulerTitle;

	@FindBy(css="a[href$='/ExportScheduler/Index'] div")
	private WebElement exportschedulerlinkonHomepg;

	@FindBy(xpath="//a[text()='Fax Line']")
	private WebElement FaxLine;

	@FindBy(css=".k-grid-excel")
	private WebElement exportPage;

	@FindBy(xpath="//button[text()=' Scheduled Reports']")
	private WebElement schRptsinAgent;

	@FindBy(xpath="//button[text()=' View Downloaded Reports']")
	private WebElement viewDwnRptinAgntpg;

	@FindBy(css="button[onclick='onSelectExportAll()']")
	private WebElement exportToCSV;

	@FindBy(css=".ibox-content")
	private WebElement gridContent;

	@FindBy(css=".k-pager-info")
	private WebElement pagerInfo;	

	@FindBy(css=".k-pager-sizes .k-input")
	private WebElement pagerSize;

	@FindBy(css=".k-i-more-vertical")
	private List<WebElement> headersDropdown;

	@FindBy(css=".k-sort-asc")
	private List<WebElement> sortAscending;

	@FindBy(css=".k-sort-desc")
	private List<WebElement> sortDescending;

	@FindBy(css="a[aria-label='Go to the next page']")
	private WebElement nextPageIcon;

	@FindBy(xpath="//table/tbody/tr")
	private List<WebElement> MainReportRows;

	@FindBy(css = ".k-pager-last")
	private WebElement goToLastPage;

	@FindBy(css=".k-state-selected")
	private List<WebElement> pageNumber;

	@FindBy(css=".k-pager-sizes .k-icon")
	private List<WebElement> pagerDropdown;

	@FindBy(css=".k-animation-container ul li")
	private List<WebElement> pageSizeListBox;

	@FindBy(css=".k-columns-item")
	private List<WebElement> columns;

	@FindBy(css=".fa-refresh")
	private WebElement refreshBtn;

	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;

	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;

	@FindBy(css=".k-grid-pdf")
	private WebElement exportToPDF;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;

	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;

	@FindBy(id="navbarheader")
	private WebElement header;

	@FindBy(css=".k-grid-norecords-template")
	private List<WebElement> norecords;

	@FindBy(id="grid")
	private WebElement auditGridContent;

	@FindBy(css=".toast-message")
	private WebElement successmsg;

	@FindBy(css="#toast-container .toast-error")
	private List<WebElement> errorMsg;

	@FindBy(css="a[aria-label='Go to the last page']")
	private WebElement lastPageIcon;

	@FindBy(css="a[aria-label='Go to the first page']")
	private WebElement firstPageIcon;


	@FindBy(css="a[aria-label='Go to the previous page']")
	private WebElement previousPageIcon;

	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;

	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")		
	private WebElement exporttoexcel;

	//export to excel in FaxSLAPage
	@FindBy(xpath="//button[@id='exportAllToExcel']")
	private WebElement exportToExcel;

	@FindBy(css = ".fa-search")
	private WebElement searchBtn;

	@FindBy(css = "span[aria-controls='1001sColumnName_listbox']")  //input[aria-owns='1001sColumnName_listbox']
	private WebElement searchColDropdown;

	@FindBy(css="ul[id='1001sColumnName_listbox'] li")
	private List<WebElement> searchColListBox;

	@FindBy(css = "span[aria-owns='1001sCriteria_listbox']")
	private WebElement searchCriteriaDropdown;

	@FindBy(css="ul[id='1001sCriteria_listbox'] li") 
	private List<WebElement> searchCriteriaListbox;

	@FindBy(id = "1001sTextToSearch")
	private WebElement searchTextBox;

	//@FindBy(css = ".modal-footer .k-button")//@FindBy(xpath="//*[@id=\"searchModel\"]/div/div/div[3]/button[1]")
	@FindBy(xpath="//*[@id=\"searchModel\"]/div/div/div[3]/button[1]")
	private WebElement searchCloseBtn;

	@FindBy(css = ".modal-footer .button-danger-theme")
	private WebElement searchClearAllBtn;

	@FindBy(css = ".modal-footer .button-theme")
	private WebElement searchSearchBtn;

	// @FindBy(css = "span[aria-owns='autoCompleteTextbox_listbox']")
	@FindBy(id="autoCompleteTextbox")
	private WebElement searchbyfeatureTextBox;

	@FindBy(css = "ul[id='autoCompleteTextbox_listbox'] li")
	private List<WebElement> searchbyfeaturelistBox;

	@FindBy(xpath="//a[text()='Fax Line']")
	private WebElement FaxLine1;

	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupby;

	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[2]")
	private WebElement groupbyFaxLine;

	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;

	@FindBy(xpath="//table/tbody/tr/td")
	private List<WebElement> rows;

	@FindBy(css = "span[aria-owns='1001ColumnName_listbox']")
	private WebElement searchColDropdownAdvSrchReportPage;

	@FindBy(xpath="//div[@id='searchRadioGroup']")
	private WebElement advancedsearchBtn;

	@FindBy(css = "span[aria-owns='1002ColumnName_listbox']") 
	private WebElement searchColDropdownAdvSrchReportPage1;		

	@FindBy(css="ul[id='1001ColumnName_listbox'] li")
	private List<WebElement> searchColListBoxAdvSrchReportPage;

	@FindBy(css="ul[id='1002ColumnName_listbox'] li")
	private List<WebElement> searchColListBoxAdvSrchReportPage1;

	@FindBy(css = "span[aria-owns='1001Criteria_listbox']")
	private WebElement searchCriteriaDropdownAdvSrch;

	@FindBy(css = "span[aria-owns='1002Criteria_listbox']")
	private WebElement searchCriteriaDropdownAdvSrch1;

	@FindBy(css="ul[id='1001Criteria_listbox'] li") 
	private List<WebElement> searchCriteriaListboxAdvSrch;

	@FindBy(css="ul[id='1002Criteria_listbox'] li") 
	private List<WebElement> searchCriteriaListboxAdvSrch1;

	@FindBy(id = "1001TextToSearch")
	private WebElement searchTextBoxAdvSrch;

	@FindBy(id = "1002TextToSearch")
	private WebElement searchTextBoxAdvSrch1;

	@FindBy(id="1001AddButton")
	private WebElement searchAddCriteriaBtn;

	@FindBy(xpath="//label[@for='1001RadioAND']")
	private WebElement andradiobtn;

	@FindBy(xpath="//label[@for='1001RadioOR']")
	private WebElement orradiobtn;

	@FindBy(css = ".k-Show")
	private List<WebElement> showReportBtn;

	@FindBy(id = "grid")
	private WebElement gridBoxContent;

	@FindBy(id="tGrid")
	private WebElement Grid;

	@FindBy(xpath="//tbody/tr[1]/td[1]")
	private WebElement rowdata;

	@FindBy(xpath="//tbody/tr[1]/td[2]")
	private WebElement rowdatatwo;

	@FindBy(xpath="//button[@id='drillSearchClose']")
	private WebElement DrillClose;


	@FindBy(xpath="//input[@id='checkFax']")
	private List<WebElement> checkboxBeforePreview;


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
					continue;
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
						System.out.println(ele1.getText());
						System.out.println(headersColumns.get(i).getText());
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
	public boolean verifyArrowMoveForPreviousAndNextPage(){
		boolean status=false;
		if(!nextPageIcon.getAttribute("class").contains("k-state-disabled")){
			int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(0)));
			System.out.println(pagenumber);
			selectWebElement(nextPageIcon);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(0)));
			System.out.println(nextnumber);
			selectWebElement(previousPageIcon);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(0)));
			System.out.println(previousnumber);
			if(nextnumber==(pagenumber+1) && pagenumber==previousnumber)
			{
				status=true;
			}
		}
		else{
			System.out.println("previous and next page icon disabled");status=true;
		}
		return status;
	}
	public boolean verifyTotalNumberOfItemsPerPageDetails(){
		String item = items.getText();
		return item.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
	}
	public boolean verifyNumberOfItemsPerPage() {
		boolean status = false;
		try {
			if (norecords.size() <= 0) {
				int items = Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
				selectWebElement(pagerDropdown.get(0));
				Thread.sleep(1500);
				for (int i = 0; i < pageSizeListBox.size(); i++) {
					if(Integer.valueOf(pageSizeListBox.get(i).getText())>items){continue;}
					selectDropdownFromVisibleText(pageSizeListBox, pageSizeListBox.get(i).getText());
					waitForJqueryLoad(driver);
					int totalItems = Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
					int pagersize = Integer.valueOf(pagerSize.getText());
					int pages = (totalItems % pagersize == 0) ? items / pagersize : items / pagersize+1;
					int totalRows=(gridContent.findElements(By.tagName("tr")).size())-1;
					selectWebElement(goToLastPage);
					waitForJqueryLoad(driver);
					int lastPageNumber = Integer.valueOf(pageNumber.get(0).getText());
					if (items == totalItems && pages == lastPageNumber&&totalRows==pagersize) {
						status = true;
					} else {System.out.println(items+":"+totalItems+"\t"+pages+":"+lastPageNumber+"\t"+totalRows+":"+pagersize);
					status = false;
					break;
					}selectWebElement(pagerDropdown.get(0));Thread.sleep(1500);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} return status;
	}

	public boolean verifyExportToExcel(String filePath) {
		final File folder = new File(filePath);
		for (final File f : folder.listFiles()) {
			if (f.getName().startsWith("FaxSlaReport")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "FaxSlaReport");
		return Status;
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
				for(int j=0;j<headers.size();j++) {
					scrollToElement(headers.get(j));
					System.out.println(headers.get(j).getText());
					if(headers.get(j).getText().equals("Last Changed On")){
						col=cols.get(j).getText().substring(0,10);
					}
					else
						col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				map.remove("");
				arr.add(map);
			}
		}
		return arr;
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
	public void SortByAscending() {
		selectWebElement(FaxLine);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void SortByDescending() {
		selectWebElement(FaxLine);
		selectWebElement(FaxLine);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public boolean isExportSchedulerPageDisplayed() {
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		return exportSchedulerTitle.isEnabled();
	}
	public void navigateToExportSchedulerPage() {

		waitUntilWebElementIsClickable(schRptsinAgent);
		selectWebElement(schRptsinAgent);			
	}
	public void exportToExcel() {
		selectWebElement(exportToExcel);
	}
	public boolean verifyReportExported(){
		if(waitUntilTextToBePresentInWebElement(successmsg,"Report export is initiated... Notification will be sent once completed"))
		{return true;}else{return false;}
	}
	public void viewDownloadedReportInReportsDownloadsPage() {
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		selectWebElement(viewDwnRptinAgntpg);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		waitForLoad(driver);
		waitForJqueryLoad(driver);
	}
	/*private List<Map<String, String>> getDataTable() {
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
				for(int j=0;j<headers.size();j++){
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
	}*/


	public boolean verifySearchByTextbox(ReportDetails details) throws Exception{
		boolean Status=false;
		selectWebElement(searchbyfeatureTextBox);
		enterValueToTxtFieldWithoutClear(searchbyfeatureTextBox,details.getSearchStr());
		Thread.sleep(2000);
		selectDropdownFromVisibleText(searchbyfeaturelistBox,details.getSearchStr());	
		Thread.sleep(2000);
		waitForJqueryLoad(driver);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{  		
			if(map1.get("Fax Line").equals(details.getSearchStr()))
				Status= true;
			else 
				Status= false;
		}
		return Status;	
	}
	public boolean verifySearchIsEqualTo(String details) throws Exception {
		Boolean Status=false;
		//map.put("Agent Name", details);
		selectWebElement(searchBtn);	
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,"Fax Line");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Is equal to");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,details);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		//waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("Fax Line").equals(details))
				Status= true;
			else 
				Status= false;
		}
		return Status;	
	}
	public boolean verifySearchIsNotEqualTo(String details) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("Fax Line", details);
		selectWebElement(searchBtn);	
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,"Fax line");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Is not equal to");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,details);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		//waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;	
	}
	public void searchwithoutextsearch(ReportDetails details) {
		selectWebElement(searchBtn);		
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,details.getColname());  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,details.getColtype());		   
		waitForJqueryLoad(driver);    
		selectWebElement(searchSearchBtn);	
		selectWebElement(searchCloseBtn);		
	}

	public String getSuccessMessage() {
		if(successmsg.isDisplayed()){
			return successmsg.getText();}
		else {
			return errorMsg.get(0).getText();}
	}
	public boolean verifySearchContains(String description) throws Exception {
		Boolean Status=false;		
		selectWebElement(searchBtn);
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,"Agent ID");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Contains");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,description);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		//waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("Agent ID").contains(description))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}

	public boolean verifySearchDoesNotContains(String description) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,"Agent ID");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Does not contain");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,description);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);		
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(!map1.get("Agent ID").contains(description))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}

	public boolean verifySearchStartsWith(String description) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
		selectWebElement(searchColDropdown); 
		selectDropdownFromVisibleText(searchColListBox,"Agent ID");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Starts with");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,description);        
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		//waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("Agent ID").startsWith(description))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}
	public boolean verifySearchEndsWith(String description) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
		selectWebElement(searchColDropdown); 
		selectDropdownFromVisibleText(searchColListBox,"Agent ID");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Ends with");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,description);        
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		// waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("Agent ID").endsWith(description))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}
	public boolean verifySearchClear(ReportDetails details) {
		Boolean Status= false;
		selectWebElement(searchBtn);		
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,details.getColname());  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,details.getColtype());		   
		waitForJqueryLoad(driver);    
		selectWebElement(searchClearAllBtn);	
		selectWebElement(searchCloseBtn);	
		if(auditGridContent.isDisplayed())
			Status= true;
		else
			Status=false;
		return Status;	
	}
	public boolean verifyAdvanceSearch(ReportDetails reportDetails) throws Exception {
		Boolean Status=false;	
		selectWebElement(advancedsearchBtn);
		selectWebElement(searchColDropdownAdvSrchReportPage);
		Thread.sleep(2000);
		selectDropdownFromVisibleText(searchColListBoxAdvSrchReportPage,"Fax Line");
		Thread.sleep(2000);
		selectWebElement(searchCriteriaDropdownAdvSrch);
		selectDropdownFromVisibleText(searchCriteriaListboxAdvSrch,"Is equal to");
		enterValueToTxtField(searchTextBoxAdvSrch,reportDetails.getSearchStr());	
		waitForJqueryLoad(driver);
		selectWebElement(showReportBtn.get(0));
		waitForJqueryLoad(driver);
		List<Map<String,String>>UI=getDataTable();
		for(Map<String,String> map1:UI)
		{
			System.out.println(map1.get("Fax Line"));
			if(map1.get("Fax Line").equals(reportDetails.getSearchStr()))
				Status= true;
			else 
				Status =false;
		}
		return Status;
	}

	public Boolean advancedSearchORCriteria(ReportDetails details) throws Exception {
		Boolean Status=false;	
		selectWebElement(advancedsearchBtn);
		selectWebElement(searchColDropdownAdvSrchReportPage);
		Thread.sleep(2000);
		selectDropdownFromVisibleText(searchColListBoxAdvSrchReportPage,"Fax Line");
		Thread.sleep(2000);
		selectWebElement(searchCriteriaDropdownAdvSrch);
		selectDropdownFromVisibleText(searchCriteriaListboxAdvSrch,"Is equal to");
		enterValueToTxtField(searchTextBoxAdvSrch,details.getSearchStr());
		selectWebElement(searchAddCriteriaBtn);
		moveToElement(orradiobtn);
		selectWebElement(orradiobtn);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(searchColDropdownAdvSrchReportPage1);
		Thread.sleep(2000);
		selectDropdownFromVisibleText(searchColListBoxAdvSrchReportPage1,"Skill");
		Thread.sleep(2000);
		selectWebElement(searchCriteriaDropdownAdvSrch1);
		selectDropdownFromVisibleText(searchCriteriaListboxAdvSrch1,"Starts with");
		enterValueToTxtField(searchTextBoxAdvSrch1,details.getSearchStr2());
		selectWebElement(showReportBtn.get(0));
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridBoxContent);
		Thread.sleep(3000);
		List<WebElement> rows=Grid.findElements(By.tagName("tr"));	
		for(WebElement e:rows)
		{
			if(rowdata.getText().equals(details.getSearchStr())||rowdatatwo.getText().startsWith(details.getSearchStr2()))
				Status=true;
		}
		return Status;	

	}
	public boolean groupby() {
		DragandDrop(FaxLine1,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(groupbyFaxLine.getText()))
		{return true;}
		else
			return false;		
	}


	private void searchReport(ReportDetails reportDetails) throws Exception {
		selectWebElement(searchBtn);	
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,"Fax Line");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Is equal to");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,reportDetails.getSearchStr());
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
	}

	public boolean verifyDatabase(String query,ReportDetails details, String OrgUnitID) throws InterruptedException {
		//get dates from xl - step 2
		String reportbeforedate = details.getStartDate();
		String reportafterdate=details.getEndDate();
		//change date formats - step 3
		reportbeforedate=reportbeforedate.substring(6,10)+reportbeforedate.substring(3, 5)+reportbeforedate.substring(0, 2)+reportbeforedate.substring(11, 13)+reportbeforedate.substring(14, 16)+reportbeforedate.substring(17, 19);
		reportafterdate	=reportafterdate.substring(6,10)+reportafterdate.substring(3, 5)+reportafterdate.substring(0, 2)+reportafterdate.substring(11, 13)+reportafterdate.substring(14, 16)+reportafterdate.substring(17, 19);
		//Replace identifiers in query to formatted date - step 5
		query=query.replaceAll("ReportBeforeDate",reportbeforedate );
		query=query.replaceAll("ReportAfterDate",reportafterdate );
		query=query.replaceAll("OrgUnitID", OrgUnitID);
		List<Map<String,String>> database=database(query);
		System.out.println("Printing Query" +" "+query);		
		System.out.println("Printing DB results" +" "+database);
		List<Map<String,String>> UI=getDataTable(); 
		System.out.println("Printing UI Results"+" "+UI);	
		if(UI.equals(database))
			return true;
		else
			return false;
	}
	
	private List<Map<String, String>> getDataTable() {
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
				for(int j=0;j<headers.size();j++){
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

	
	
	
}
