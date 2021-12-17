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
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.report.ReportDetails;

public class OCMViolationBugReportPage extends BasePage{

public OCMViolationBugReportPage(WebDriver driver) {
		super(driver);
	}

@FindBy(xpath="//i[@class='fas fa-expand']")
private WebElement maximize;

@FindBy(xpath="//i[@class='fas fa-compress']")
private WebElement minimize;

@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
private WebElement fullscreen;

@FindBy(id="navbarheader")
private WebElement header;

@FindBy(css=".k-i-more-vertical")
private List<WebElement> headersDropdown;

@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
private List<WebElement> headersColumns;

@FindBy(css="th a[class='k-link']")
private List<WebElement> headersText;

@FindBy(css="a[aria-label='Go to the next page']")
private WebElement nextPageIcon;

@FindBy(css="a[aria-label='Go to the previous page']")
private WebElement previousPageIcon;

@FindBy(css="a[aria-label='Go to the last page']")
private WebElement lastPageIcon;

@FindBy(css="a[aria-label='Go to the first page']")
private WebElement firstPageIcon;

@FindBy(css = ".k-state-selected")
private WebElement pageNumber;

@FindBy(xpath="//a[text()='Agent ID']")
private WebElement Agentid;

@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")		
private WebElement exporttoexcel;

@FindBy(xpath="//span[@class='k-pager-info k-label']")
private WebElement items;

@FindBy(css=".k-animation-container ul li")
private List<WebElement> pageSizeListBox;

@FindBy(css=".k-pager-info")
private WebElement pagerInfo;

@FindBy(css=".k-grid-norecords-template")
private List<WebElement> norecords;

@FindBy(css=".k-pager-sizes .k-icon")
private WebElement pagerDropdown;

@FindBy(css=".k-pager-sizes .k-input")
private WebElement pagerSize;

@FindBy(css=".ibox-content")
private WebElement gridContent;

@FindBy(css = ".k-pager-last")
private WebElement goToLastPage;

@FindBy(id="grid")
private WebElement auditGridContent;

@FindBy(xpath="//button[normalize-space()='Scheduled Reports']")
private WebElement schRptsinAgent;

@FindBy(css=".ibox-title h5")
private WebElement exportSchedulerTitle;

@FindBy(css=".toast-message")
private WebElement successmsg;

//export to excel in AgentRptPage
	@FindBy(xpath="//button[@id='exportAllToExcel']")
	private WebElement exportToExcel;
	
	@FindBy(xpath="//button[normalize-space()='View Downloaded Reports']")
	private WebElement viewDwnRptinAgntpg;
	
	@FindBy(id="autoCompleteTextbox")
	private WebElement searchbyfeatureTextBox;
	
	@FindBy(css = "ul[id='autoCompleteTextbox_listbox'] li")
	private List<WebElement> searchbyfeaturelistBox;
	
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
	
	@FindBy(css = ".modal-footer .button-theme")
	private WebElement searchSearchBtn;
	
	@FindBy(xpath="//button[@onclick='closeSearch()']")
	private WebElement searchCloseBtn;
	
	@FindBy(css="#toast-container .toast-error")
	private List<WebElement> errorMsg;
	
	@FindBy(css = ".modal-footer .button-danger-theme")
	private WebElement searchClearAllBtn;
	
	@FindBy(xpath="//a[text()='Agent Id']")
	private WebElement agentId;
	
	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;
	
	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[2]")
	private WebElement groupbyAgentid;
	
	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupby;
	
	@FindBy(xpath="//div[@id='searchRadioGroup']")
	private WebElement advancedsearchBtn;
	
	@FindBy(css = "span[aria-owns='1001ColumnName_listbox']")
	private WebElement searchColDropdownAdvSrchReportPage;
	
	@FindBy(css="ul[id='1001ColumnName_listbox'] li")
	private List<WebElement> searchColListBoxAdvSrchReportPage;
	
	@FindBy(css = "span[aria-owns='1001Criteria_listbox']")
	private WebElement searchCriteriaDropdownAdvSrch;
	
	@FindBy(css="ul[id='1001Criteria_listbox'] li") 
	private List<WebElement> searchCriteriaListboxAdvSrch;
	
	@FindBy(id = "1001TextToSearch")
	private WebElement searchTextBoxAdvSrch;

	@FindBy(id="1001AddButton")
	private WebElement searchAddCriteriaBtn;

	@FindBy(xpath="//label[@for='1001RadioAND']")
	private WebElement andradiobtn;
	
	@FindBy(css = "span[aria-owns='1002ColumnName_listbox']")
	private WebElement searchColDropdownAdvSrchReportPage1;	

	@FindBy(css="ul[id='1002ColumnName_listbox'] li")
	private List<WebElement> searchColListBoxAdvSrchReportPage1;

	@FindBy(css = "span[aria-owns='1002Criteria_listbox']")
	private WebElement searchCriteriaDropdownAdvSrch1;

	@FindBy(css="ul[id='1002Criteria_listbox'] li") 
	private List<WebElement> searchCriteriaListboxAdvSrch1;

	@FindBy(id = "1002TextToSearch")
	private WebElement searchTextBoxAdvSrch1;

	@FindBy(css = ".k-Show")
	private List<WebElement> showReportBtn;

	@FindBy(id = "grid")
	private WebElement gridBoxContent;
	
	@FindBy(xpath="//label[@for='1001RadioOR']")
	private WebElement orradiobtn;
	
	@FindBy(id="tGrid")
	private WebElement Grid;
	
	@FindBy(xpath="//table/tbody/tr/td")
	private List<WebElement> rows;
	
	@FindBy(xpath="//div[@id='gridDrillOne']//span[@class='k-icon k-i-arrow-60-right']")
	private WebElement nextPageIconDrillOne;
	
	@FindBy(xpath="//div[@id='gridDrillOne']//span[@class='k-state-selected']")
	private WebElement pageNumberDrillOne;
	
	@FindBy(xpath="//div[@id='gridDrillOne']//a[@aria-label='Go to the previous page']")
	private WebElement previousPageIconDrillOne;

	@FindBy(css=".k-sort-asc")
	private List<WebElement> sortAscending;
	
	
	@FindBy(css=".k-sort-desc")
	private List<WebElement> sortDescending;

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

public boolean verifycolumnsHeaderEnabled(){
	boolean status=false;
	WebElement ele= headersDropdown.get(1);
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

public boolean verifyArrowMoveForPreviousAndNextPage() throws Exception{
	boolean status=false;
	if(!nextPageIcon.getAttribute("class").contains("k-state-disabled")){
		int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
		Thread.sleep(3000);
		selectWebElement(nextPageIcon);
		waitForJqueryLoad(driver);
		int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
		Thread.sleep(3000);
		selectWebElement(previousPageIcon);
		waitForJqueryLoad(driver);
		int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
		if(nextnumber==(pagenumber+1) && pagenumber==previousnumber){status=true;}
	}else{
		System.out.println("previous and next page icon disabled");status=true;
	}
	return status;
}

public boolean verifyArrowMoveForFirstAndLastPage() throws Exception{
	boolean status=false;
	if(!lastPageIcon.getAttribute("class").contains("k-state-disabled")){
		int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
		Thread.sleep(3000);
		selectWebElement(lastPageIcon);
		Thread.sleep(3000);
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

public void SortByAscending() {
	selectWebElement(Agentid);
	waitForJqueryLoad(driver);
	selectWebElement(exporttoexcel);
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}

public boolean verifyNumberOfItemsPerPage() {
	boolean status = false;
	try {
		if (norecords.size() <= 0) {
			int items = Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
			selectWebElement(pagerDropdown);
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
				int lastPageNumber = Integer.valueOf(pageNumber.getText());
				if (items == totalItems && pages == lastPageNumber&&totalRows==pagersize) {
					status = true;
				} else {System.out.println(items+":"+totalItems+"\t"+pages+":"+lastPageNumber+"\t"+totalRows+":"+pagersize);
				status = false;
				break;
				}selectWebElement(pagerDropdown);Thread.sleep(1500);
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	} return status;
}

public boolean verifyExportToExcel(String filePath) {
	final File folder = new File(filePath);
	for (final File f : folder.listFiles()) {
		if (f.getName().startsWith("OCMTRSEventTraceReport")) {
			f.delete();
		}
	}
	//waitForJqueryLoad(driver); 
	selectWebElement(exporttoexcel);
	waitForJqueryLoad(driver);
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	Boolean Status=verifyExportPageFileDownload(filePath, "OCMTRSReportBugReport");
	return Status;
}
public boolean verifyExportPageFileDownloaded(String reportname){
	return verifyExportPageFileDownload(System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles",reportname);
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
	//for(int k=0;k<=pages;k++){
	waitUntilWebElementIsVisible(auditGridContent);
	List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
	List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
	String col=null;
	for(int i=1;i<rows.size();i++) {
		Map<String,String> map = new HashMap<String,String>();
		List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
		for(int j=0;j<headers.size();j++) {
			scrollToElement(headers.get(j));
			/*System.out.println(headers.get(j).getText());
					if(headers.get(j).getText().equals("Last Changed On")){
					col=cols.get(j).getText().substring(0,10);
					}
					else*/
			col=cols.get(j).getText();
			map.put(headers.get(j).getText(),col);
		}
		map.remove("");
		arr.add(map);
	}
	/*if(k!=pages)
			{
				nextPageIcon.click();
				waitForJqueryLoad(driver);}
			}*/
	return arr;

}

public void navigateToExportSchedulerPage() {			
	waitUntilWebElementIsClickable(schRptsinAgent);
	selectWebElement(schRptsinAgent);			
}

public boolean isExportSchedulerPageDisplayed() {
	waitForLoad(driver);
	waitForJqueryLoad(driver);
	return exportSchedulerTitle.isEnabled();
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

public boolean verifySearchByTextbox(ReportDetails details) throws Exception{	
	boolean Status=false;
	selectWebElement(searchbyfeatureTextBox);
	enterValueToTxtFieldWithoutClear(searchbyfeatureTextBox,details.getSearchStr());
	Thread.sleep(3000);
	selectDropdownFromVisibleText(searchbyfeaturelistBox,details.getSearchStr());	
	Thread.sleep(2000);
	waitUntilWebElementIsVisible(gridContent);
	waitForJqueryLoad(driver);
	List<Map<String,String>> UI=getDataTable(); 
	for (Map<String,String> map1: UI)
	{   	
		if(map1.get("Event Name").equals(details.getSearchStr()))
			Status= true;
		else 
			Status= false;
	}
	return Status;	
}

private List<Map<String, String>> getDataTable() throws InterruptedException {
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
				if(headers.get(j).getText().equals("")){
					col=cols.get(j).getText().substring(0);
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
			Thread.sleep(10000);
			nextPageIcon.click();
			waitForJqueryLoad(driver);}
	}
	return arr;
}

public boolean verifySearchIsEqualTo(String details) throws Exception {
	Boolean Status=false;
	Map<String, String> map=new HashMap<String,String>() ;
	//map.put("Agent Name", details);
	selectWebElement(searchBtn);	
	selectWebElement(searchColDropdown);  
	selectDropdownFromVisibleText(searchColListBox,"Event Name");  
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
		if(map1.get("Event Name").equals(details))
			Status= true;
		else 
			Status= false;
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

public boolean verifySearchIsNotEqualTo(String details) throws Exception {
	Boolean Status=false;
	Map<String, String> map=new HashMap<String,String>() ;
	map.put("Agent Name", details);
	selectWebElement(searchBtn);	
	selectWebElement(searchColDropdown);  
	selectDropdownFromVisibleText(searchColListBox,"Agent Name");  
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

public boolean verifySearchContains(String description) throws Exception {
	Boolean Status=false;		
	selectWebElement(searchBtn);
	selectWebElement(searchColDropdown);  
	selectDropdownFromVisibleText(searchColListBox,"Agent Name");  
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
		if(map1.get("Agent Name").toUpperCase().contains(description.toUpperCase()))
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
	selectDropdownFromVisibleText(searchColListBox,"Agent Name");  
	waitForJqueryLoad(driver);
	selectWebElement(searchCriteriaDropdown);
	selectDropdownFromVisibleText(searchCriteriaListbox,"Does not contain");		   
	waitForJqueryLoad(driver);    
	enterValueToTxtField(searchTextBox,description);
	selectWebElement(searchSearchBtn);
	waitForJqueryLoad(driver);
	//waitUntilWebElementIsVisible(gridContent);
	List<Map<String,String>> UI=getDataTable(); 
	for (Map<String,String> map1: UI)
	{   	
		if(!map1.get("Agent Name").toLowerCase().contains(description.toLowerCase()))
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
	selectDropdownFromVisibleText(searchColListBox,"Agent Name");  
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
		if(map1.get("Agent Name").toLowerCase().startsWith(description.toLowerCase()))
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
	selectDropdownFromVisibleText(searchColListBox,"Agent Name");  
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
		if(map1.get("Agent Name").toUpperCase().endsWith(description.toUpperCase()))
			Status= true;
		else 
			Status= false;
	}
	return Status;
}

public void verifySearchClear(ReportDetails details) {
	selectWebElement(searchBtn);		
	selectWebElement(searchColDropdown);  
	selectDropdownFromVisibleText(searchColListBox,details.getColname());  
	waitForJqueryLoad(driver);
	selectWebElement(searchCriteriaDropdown);
	selectDropdownFromVisibleText(searchCriteriaListbox,details.getColtype());		   
	waitForJqueryLoad(driver);    
	selectWebElement(searchClearAllBtn);	
	//selectWebElement(searchCloseBtn);		
}

public boolean verifyAdvanceSearch(ReportDetails reportDetails) throws Exception {
	Boolean Status=false;
	List<Map<String,String>>UI=getDataTable();
	for(Map<String,String> map1:UI)
	{
		System.out.println(map1.get("Agent Id"));
		if(map1.get("Agent Id").equalsIgnoreCase(reportDetails.getSearchStr()))
			Status= true;
		else 
			Status =false;
	}
	return Status;
}

public boolean groupby() {
	DragandDrop(agentId,droptarget);
	waitForJqueryLoad(driver);
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	System.out.println(groupby.getText()+groupbyAgentid.getText());
	if(groupby.getText().split(": ")[1].equals(groupbyAgentid.getText()))
	{return true;}
	else
		return false;		
}

public boolean verifyAdvanceSearchNotEqualsTo(ReportDetails reportDetails) throws Exception {
	Boolean Status=false;
	waitForJqueryLoad(driver);
	List<Map<String,String>>UI=getDataTable();
	for(Map<String,String> map1:UI)
	{
		System.out.println(map1.get("Agent Name"));
		if(map1.get("Agent Name").equalsIgnoreCase(reportDetails.getSearchStr()))
			Status= false;
		else 
			Status =true;
	}
	return Status;
}

public boolean verifyAdvanceSearchContains(ReportDetails reportDetails) throws Exception {
	Boolean Status=false;
	waitForJqueryLoad(driver);
	List<Map<String,String>>UI=getDataTable();
	for(Map<String,String> map1:UI)
	{
		System.out.println(map1.get("Agent Name"));
		if(map1.get("Agent Name").toLowerCase().contains(reportDetails.getSearchStr().toLowerCase()))
			Status= true;
		else 
			Status =false;
	}
	return Status;
}

public boolean verifyAdvanceSearchDoesNotContains(ReportDetails reportDetails) throws Exception {
	Boolean Status=false;
	waitForJqueryLoad(driver);
	List<Map<String,String>>UI=getDataTable();
	for(Map<String,String> map1:UI)
	{
		System.out.println(map1.get("Agent Name"));
		if(!map1.get("Agent Name").toLowerCase().contains(reportDetails.getSearchStr().toLowerCase()))
			Status= true;
		else 
			Status =false;
	}
	return Status;
}

public boolean verifyAdvanceSearchStartsWith(ReportDetails reportDetails) throws InterruptedException {
	Boolean Status=false;
	waitForJqueryLoad(driver);
	List<Map<String,String>>UI=getDataTable();
	for(Map<String,String> map1:UI)
	{
		System.out.println(map1.get("Agent Name"));
		if(map1.get("Agent Name").toLowerCase().startsWith(reportDetails.getSearchStr().toLowerCase()))
			Status= true;
		else 
			Status =false;
	}
	return Status;
}

public boolean verifyAdvanceSearchEndsWith(ReportDetails reportDetails) throws Exception {
	Boolean Status=false;
	waitForJqueryLoad(driver);
	List<Map<String,String>>UI=getDataTable();
	for(Map<String,String> map1:UI)
	{
		System.out.println(map1.get("Agent Name"));
		if(map1.get("Agent Name").toLowerCase().endsWith(reportDetails.getSearchStr().toLowerCase()))
			Status= true;
		else 
			Status =false;
	}
	return Status;
}

public boolean verifyArrowMoveForPreviousAndNextPageForDrillDownOne(ReportDetails reportDetails) throws Exception {
	selectWebElement(rows.get(0));
	Thread.sleep(2000);
	boolean status=false;
	Thread.sleep(2000);
	if(nextPageIconDrillOne.isEnabled()){
		int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumberDrillOne));
		System.out.println(pagenumber);
		selectWebElement(nextPageIconDrillOne);
		Thread.sleep(1000);
		int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumberDrillOne));
		System.out.println(nextnumber);
		selectWebElement(previousPageIconDrillOne);
		Thread.sleep(1000);
		int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumberDrillOne));
		System.out.println(previousnumber);
		if(nextnumber==(pagenumber+1) && pagenumber==previousnumber){status=true;}
	}else{
		System.out.println("previous and next page icon disabled");status=true;
	}
	return status;
}

public boolean verifySorting() {
	boolean status=false;
	int items = Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
	int pagersize = Integer.valueOf(pagerSize.getText());
	int pages=(items%pagersize==0)?items/pagersize-1:items/pagersize;
	for (int i = 0; i <= pages; i++) {
		List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
		List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
		int k=1;
		for(int j=0;j<2;j++){
			if(headers.get(j).getText().equals("")||headers.get(j).getText().equals(" ")){continue;}
			List<String> l1 = getColumnDatafromTable(headers.get(j).getText());
			//System.out.println(l1);
			List<String> temp = l1;
			Collections.sort(l1);
			System.out.println(temp+"oooooooooooooooooooooooo");
			selectWebElement(headersDropdown.get(k));
			waitForJqueryLoad(driver);
			selectWebElement(sortAscending.get(k-1));
			waitForJqueryLoad(driver);
			List<String> l2 = getColumnDatafromTable(headers.get(j).getText());
			//System.out.println(l2);
			if (l2.equals(temp)) {/*System.out.println("sorting works fine");*/status = true;}else{status=false;}
			if(status){}else{System.out.println("Ascending sorting failed for column name:"+headers.get(j).getText()+"\n"+l2);break;}
			/*descending sort code*/
			status=false;
			Collections.sort(temp,Collections.reverseOrder());
			System.out.println(temp);
			selectWebElement(headersDropdown.get(k));
			waitForJqueryLoad(driver);
			selectWebElement(sortDescending.get(k-1));
			waitForJqueryLoad(driver);
			k++;
			List<String> l3 = getColumnDatafromTable(headers.get(j).getText());
			//System.out.println(l3);
			if (l3.equals(temp)) {/*System.out.println("sorting works fine");*/status = true;}
			if(status){}else{System.out.println("Descending sorting failed for column name:"+headers.get(j).getText()+"\n"+l3);break;}
		}
		if(status){}else{break;}
		nextPageIcon.click();
		waitForJqueryLoad(driver);
	}
	return status;
}

private List<String> getColumnDatafromTable(String columnname){
	List<String> list = new ArrayList<>();
	List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
	List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
	int colindex=1;
	for(WebElement e:headers){if(e.getText().equals(columnname)){break;}else{colindex++;}}
	for(int j=1;j<=rows.size()-1;j++){
		List<WebElement> cols=rows.get(j).findElements(By.tagName("td"));
		list.add(cols.get(colindex).getText());
	}
	return list;
}

}
