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

public class OCMRoleAccessReportPage extends BasePage  {

	public OCMRoleAccessReportPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//i[@class='fas fa-file-export']")
	private WebElement VEFImg;

	@FindBy(xpath="//a[text()='Session ID']")
	private WebElement sessionid;

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

	@FindBy(css = ".k-pager-last")
	private WebElement goToLastPage;

	@FindBy(css = ".k-state-selected")
	private WebElement pageNumber;

	@FindBy(css=".k-pager-sizes .k-icon")
	private WebElement pagerDropdown;

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

	@FindBy(css = ".fa-search")
	private WebElement searchBtn;

	@FindBy(css = "span[aria-controls='1001sColumnName_listbox']") 
	private WebElement searchColDropdown;

	@FindBy(css="ul[id='1001sColumnName_listbox'] li")
	private List<WebElement> searchColListBox;

	@FindBy(css = "span[aria-owns='1001sCriteria_listbox']")
	private WebElement searchCriteriaDropdown;

	@FindBy(css="ul[id='1001sCriteria_listbox'] li") 
	private List<WebElement> searchCriteriaListbox;

	@FindBy(id = "1001sTextToSearch")
	private WebElement searchTextBox;

	
	@FindBy(xpath="//*[@id=\"searchModel\"]/div/div/div[3]/button[1]")
	private WebElement searchCloseBtn;

	@FindBy(css = ".modal-footer .button-danger-theme")
	private WebElement searchClearAllBtn;

	@FindBy(css = ".modal-footer .button-theme")
	private WebElement searchSearchBtn;

	
	@FindBy(id="autoCompleteTextbox")
	private WebElement searchbyfeatureTextBox;

	@FindBy(css = "ul[id='autoCompleteTextbox_listbox'] li")
	private List<WebElement> searchbyfeaturelistBox;

	@FindBy(xpath="//a[text()='Access']")
	private WebElement Access;

	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupby;

	@FindBy(xpath="//*[@id=\"grid\"]/div[2]/div/a[2]")
	private WebElement closeGroup;

	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
	private WebElement groupbyAccess;

	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;
	
	@FindBy(id="exporttoexcel")
	private WebElement exportToExcel;
	
	private List<String> getColumnDatafromTable(String columnname){
		List<String> list = new ArrayList<>();
		List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
		List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
		int colindex=0;
		for(WebElement e:headers){if(e.getText().equals(columnname)){break;}else{colindex++;}}
		for(int j=1;j<=rows.size()-1;j++){
			List<WebElement> cols=rows.get(j).findElements(By.tagName("td"));
			list.add(cols.get(colindex).getText());
		}
		return list;
	}
	
	public boolean verifySorting() throws Exception {
		boolean status=false;
		int items = Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
		int pagersize = Integer.valueOf(pagerSize.getText());
		int pages=(items%pagersize==0)?items/pagersize-1:items/pagersize;
		for (int i=0; i<= pages; i++) {
			List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			int k=0;
			for(int j=0;j<1;j++){
				if(headers.get(j).getText().equals("")||headers.get(j).getText().equals(" ")){continue;}
				List<String> l1 = getColumnDatafromTable(headers.get(j).getText());
				//System.out.println(l1);
				List<String> temp = l1;
				Collections.sort(temp);
				//System.out.println(temp);
				selectWebElement(headersDropdown.get(k));
				waitForJqueryLoad(driver);
				selectWebElement(sortAscending.get(k-0));
				waitForJqueryLoad(driver);
				List<String> l2 = getColumnDatafromTable(headers.get(j).getText());
				//System.out.println(l2);
				if (l2.equals(temp)) {/*System.out.println("sorting works fine");*/status = true;}else{status=false;}
				if(status){}else{System.out.println("Ascending sorting failed for column name:"+headers.get(j).getText()+"\n"+l2);break;}
				/*descending sort code*/
				status=false;
				temp = l1;
				Collections.sort(temp,Collections.reverseOrder());
				//System.out.println(temp);
				selectWebElement(headersDropdown.get(k));
				waitForJqueryLoad(driver);
				selectWebElement(sortDescending.get(k-0));
				waitForJqueryLoad(driver);
				k++;
				List<String> l3 = getColumnDatafromTable(headers.get(j).getText());
				//System.out.println(l3);
				if (l3.equals(temp)) {
					status = true;}
				if(status){}else{System.out.println("Descending sorting failed for column name:"+headers.get(j).getText()+"\n"+l3);break;}
			}
			if(status) {
			if(i!=pages) {
				nextPageIcon.click();
				waitForJqueryLoad(driver);
			}
			}
			else
				break;
		}
		return status;	
	}
	
	

	private String getProperHeadersInGrid(String cname){
		List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
		List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
		for(WebElement e:headers){if(cname.contains(e.getText())){return e.getText();}}
		return "";
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

	public void enableColumns(){
		selectWebElement(headersDropdown.get(1));
		selectWebElement(columns.get(0));
		selectWebElement(columns.get(0).findElements(By.tagName("input")).get(0));
		selectWebElement(refreshBtn);
	}


	public void dragandDropColumns(String col1, String col2){
		List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
		List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
		WebElement column1=null,column2=null;
		for(WebElement e:headers){
			if(e.getText().equals(col1)){column1=e;}
			if(e.getText().equals(col2)){column2=e;}
		}
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(column1).moveToElement(column2).release(column2).build();
		dragAndDrop.perform();
	}
	
	public void exportToPDF(){
		emptyDownloadsDirectory(System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		selectWebElement(exportToPDF);
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public boolean verifySortingForAllDataDisplayed(){
		boolean status=false;
		List<Map<String,String>> table=getAllDatafromTable();
		List<String> colDataFromTable=new ArrayList<>();
		List<String> sort=new ArrayList<>();
		List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
		List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
		int k=0;
		for(int j=0;j<headers.size();j++){
			String key=headers.get(j).getText();
			for(Map<String,String> m:table){
				colDataFromTable.add(m.get(key));
			}
			selectWebElement(refreshBtn);
			if(key.equals("")||key.equals(" ")){continue;}
			selectWebElement(headersDropdown.get(k));
			waitForJqueryLoad(driver);
			selectWebElement(sortAscending.get(k));
			waitForJqueryLoad(driver);
			Collections.sort(colDataFromTable);
			int items=Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
			int pagersize=Integer.valueOf(pagerSize.getText());
			int pages=(items%pagersize==0)?items/pagersize-1:items/pagersize;
			for(int i=0;i<=pages;i++){
				List<String> sortList=getColumnDatafromTable(key);
				sort.addAll(sortList);
				selectWebElement(nextPageIcon);
				waitForJqueryLoad(driver);
			}
			if(sort.equals(colDataFromTable)){status=true;}else{System.out.println("Ascending Sort failed for column "+key);System.out.println(sort+"\n"+colDataFromTable);status=false;break;}
			selectWebElement(refreshBtn);sort.clear();
			waitForJqueryLoad(driver);
			//Descending sort starts from here
			selectWebElement(headersDropdown.get(k));
			waitForJqueryLoad(driver);
			selectWebElement(sortDescending.get(k));
			waitForJqueryLoad(driver);
			k++;
			Collections.sort(colDataFromTable,Collections.reverseOrder());
			for(int i=0;i<=pages;i++){
				List<String> sortList=getColumnDatafromTable(key);
				sort.addAll(sortList);
				selectWebElement(nextPageIcon);
				waitForJqueryLoad(driver);
			}
			if(sort.equals(colDataFromTable)){status=true;}else{System.out.println("Descending Sort failed for column "+key);System.out.println(sort+"\n"+colDataFromTable);status=false;break;}
			selectWebElement(refreshBtn);colDataFromTable.clear();sort.clear();
			waitForJqueryLoad(driver);
		}
		return status;
	}

	private List<Map<String, String>> getAllDatafromTable(){
		int items=Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.getText());
		int pages=(items%pagersize==0)?items/pagersize-1:items/pagersize;
		List<Map<String, String>> maplist = new ArrayList<Map<String, String>>();
		for(int i=0;i<=pages;i++){
			List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			for(int j=1;j<=rows.size()-1;j++){
				List<WebElement> cols=rows.get(j).findElements(By.tagName("td"));
				Map<String,String> map=new HashMap<>();
				for(int k=0; k<cols.size();k++){
					map.put(headers.get(k).getText(),cols.get(k).getText());
				}
				maplist.add(map);
			}
			if(i<pages){    nextPageIcon.click();
			waitForJqueryLoad(driver);}
		}
		return maplist;
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
			/*if(k!=pages)
			{
				nextPageIcon.click();
				waitForJqueryLoad(driver);}*/
		}
		return arr;
	}

	public boolean verifyArrowMoveForPreviousAndNextPage() throws Exception{
		boolean status=false;
		if(!nextPageIcon.getAttribute("class").contains("k-state-disabled")){
			Thread.sleep(3000);
			int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(nextPageIcon);
			waitForJqueryLoad(driver);
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(previousPageIcon);
			waitForJqueryLoad(driver);
			int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			if(nextnumber==(pagenumber+1) && pagenumber==previousnumber)
			{status=true;}
		}else{
			System.out.println("previous and next page icon disabled");status=true;
		}
		return status;
	}

	public boolean verifyArrowMoveForFirstAndLastPage() throws Exception{
		boolean status=false;
		if(!lastPageIcon.getAttribute("class").contains("k-state-disabled")){
			Thread.sleep(3000);
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

	public boolean verifyExportToPDF(String filePath) {
		final File folder = new File(filePath);
		for (final File f : folder.listFiles()) {
			if (f.getName().startsWith("OCMRoleAccessReport")) {
				f.delete();
			}
		}
		selectWebElement(exportToPDF);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath, "OCMRoleAccessReport");
		return Status;
	}
	public boolean verifyExportPageFileDownloaded(String reportname){
		return verifyExportPageFileDownload(System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles",reportname);
	}

	public String verifySuccessMessage() {

		if(errorMsg.size()>0)	
		{return errorMsg.get(0).getText();}
		else{waitUntilWebElementIsVisible(successmsg);return successmsg.getText();}
	}
	/* public void scheduleReport(ReportDetails details) throws Exception{
    chooseReport(details);
    selectWebElement(schRptsinAgent);
}*/
	public boolean VerifyLogo() {
		if(VEFImg.isDisplayed())
			return true;
		else
			return false;

	}
	
	public boolean verifySearchIsEqualTo(String details) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		selectWebElement(searchBtn);	
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,"Role Name");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Is equal to");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,details);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("Role Name").equals(details))
				Status= true;
			else 
				Status= false;
		}
		return Status;	
	}
	
	public boolean verifySearchIsNotEqualTo(String details) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("Role Name", details);
		selectWebElement(searchBtn);	
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,"Role Name");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Is not equal to");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,details);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
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
	
	
	private List<Map<String, String>> getDataTable() throws Exception {
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
					col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				map.remove("");
				arr.add(map);
			}
			if(k!=pages)
			{
				Thread.sleep(3000);
				nextPageIcon.click();
				waitForJqueryLoad(driver);}
		}
		return arr;
	}


	public boolean verifySearchContains(String description) throws Exception {
		Boolean Status=false;		
		selectWebElement(searchBtn);
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,"Role Name");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Contains");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,description);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("Role Name").toUpperCase().contains(description.toUpperCase()))
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
		selectDropdownFromVisibleText(searchColListBox,"Role Name");  
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
			if(!map1.get("Role Name").toLowerCase().contains(description.toLowerCase()))
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
		selectDropdownFromVisibleText(searchColListBox,"Role Name");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Starts with");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,description);        
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("Role Name").toLowerCase().startsWith(description.toLowerCase()))
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
		selectDropdownFromVisibleText(searchColListBox,"Role Name");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Ends with");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,description);        
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("Role Name").toUpperCase().endsWith(description.toUpperCase()))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}
	public boolean verifySearchClear(ReportDetails details) {
		boolean Status=false;
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

	public boolean groupby() {
		DragandDrop(Access,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(groupbyAccess.getText()))
		{return true;}
		else
			return false;		
	}
	
	public void closeGroupBy() {
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(closeGroup);
		selectWebElement(closeGroup);		
	}

	public void exportToExcel() {
		selectWebElement(exportToExcel);
	}

	public boolean verifyReportExportedToExcel() throws InterruptedException{
		Thread.sleep(3000);
		if(waitUntilTextToBePresentInWebElement(successmsg,"Report successfully Exported"))
		{return true;}else{return false;}
	}
	
	public void selectPageSize() throws Exception {
		Thread.sleep(5000);
		selectWebElement(pagerSize);
	}
	
}



