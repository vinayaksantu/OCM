
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

public class OCMIvrCallTraceReportPage extends BasePage  {

	public OCMIvrCallTraceReportPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//i[@class='fas fa-file-export']")
	private WebElement VEFImg;

	@FindBy(css=".ibox-title h5")
	private WebElement exportSchedulerTitle;

	@FindBy(css="a[href$='/ExportScheduler/Inde	x'] div")
	private WebElement exportschedulerlinkonHomepg;

	@FindBy(xpath="//a[text()='Caller ID']")
	private WebElement callerId;

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
	
	@FindBy(css=".k-pager-sizes .k-input")
	private WebElement pagerSize;
	
	@FindBy(css=".k-pager-info")
    private WebElement pagerInfo;

	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")		
	private WebElement exporttoexcel;

	//export to excel in IVRCallTrace
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

	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupby;

	//@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[9]/div")
	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[9]")
	private WebElement groupbyCallerId;

	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;

	@FindBy(xpath="//div[@id='searchRadioGroup']")
	private WebElement advancedsearchBtn;

	@FindBy(css = "span[aria-owns='1001ColumnName_listbox']")
	private WebElement searchColDropdownAdvSrchReportPage;

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

	@FindBy(xpath="//tbody/tr/td[2]")
	private WebElement rowdata;

	@FindBy(xpath="//tbody/tr[2]/td[1]")
	private WebElement rowdatatwo;

	@FindBy(id="tGrid")
	private WebElement Grid;

	@FindBy(id = "grid")
	private WebElement gridBoxContent;

	@FindBy(xpath="//div[@id='dGrid']//a[@class='k-link k-header k-state-selected']")
	private WebElement menuTraversaldropdownarrow;

	@FindBy(xpath="#dGrid .k-icon.k-i-arrow-60-up.k-panelbar-collapse")
	private WebElement menutraversalUparrow;

	//@FindBy(xpath="//div[@id='panelbar-1']")
	//@FindBy(css="#panelbar-1")
	@FindBy(xpath="//a[text()='Menu Traversal']")
	private WebElement menugridId;

	@FindBy(xpath="//div[@id='MenuTraversalGrid']")
	private WebElement menuTraversalGrid;

	@FindBy(xpath="//div[@id='flowCanvas']")
	private WebElement callFlowDiagramgrid;

	@FindBy(xpath="//a[text()='Call Flow Diagram']")
	private WebElement callflowdiagramlink;

	@FindBy(xpath="//a[text()='Call Flow Grid']")
	private WebElement callflowGridlink;

	@FindBy(xpath="//div[@id='CallFlowGrid']")
	private WebElement callFlowGrid;

	@FindBy(id="MenuTraversalGrid")
	private WebElement DrillGridOneTable;
	
	@FindBy(id="panel-1")
	private WebElement MenuTraversalPanel;
	
	@FindBy(id="MenuTraversalGrid")
	private WebElement MenuTraversalTable;
	
	@FindBy(xpath="//*[@id=\"popupcallflow\"]/div/div/div[1]/button/span[1]")
	private WebElement CloseDrillGridOne;
	
	@FindBy(xpath="//div[@id='gridDrillOne']//span[@class='k-icon k-i-arrow-60-right']")
	private WebElement nextPageIconDrillOne;

	@FindBy(xpath="//table/tbody/tr/td")
	private List<WebElement> rows;

	public void exportPage(){
		emptyDownloadsDirectory(System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		selectWebElement(exportPage);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void exportToCSV(){
		emptyDownloadsDirectory(System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		selectWebElement(exportToCSV);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

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
	public boolean verifySorting() {
		boolean status=false;
		int items = Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
		int pagersize = Integer.valueOf(pagerSize.getText());
		int pages=(items%pagersize==0)?items/pagersize-1:items/pagersize;
		for (int i = 0; i <= pages; i++) {
			List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			int k=0;
			for(int j=0;j<2;j++){
				if(headers.get(j).getText().equals("")||headers.get(j).getText().equals(" ")){continue;}
				List<String> l1 = getColumnDatafromTable(headers.get(j).getText());
				//System.out.println(l1);
				List<String> temp = l1;
				Collections.sort(temp);
				//System.out.println(temp);
				selectWebElement(headersDropdown.get(k));
				waitForJqueryLoad(driver);
				selectWebElement(sortAscending.get(k));
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
				selectWebElement(sortDescending.get(k));
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


	public void dragandDropColumns(String col1, String col2)
	{
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
				for(int j=0;j<headers.size();j++) {
					scrollToElement(headers.get(j));
					System.out.println(headers.get(j).getText());

					col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				map.remove("");
				arr.add(map);
			}

		}
		return arr;
	}

	public boolean verifyArrowMoveForPreviousAndNextPage() throws Exception{
		boolean status=false;
		if(!nextPageIcon.getAttribute("class").contains("k-state-disabled")){
			Thread.sleep(5000);
			int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(nextPageIcon);
			Thread.sleep(5000);
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(previousPageIcon);
			Thread.sleep(5000);
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
			Thread.sleep(5000);
			int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(lastPageIcon);
			Thread.sleep(5000);
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(firstPageIcon);
			Thread.sleep(5000);
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
		selectWebElement(callerId);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(callerId);
		selectWebElement(callerId);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean verifyExportToExcel(String filePath) {
		final File folder = new File(filePath);
		for (final File f : folder.listFiles()) {
			if (f.getName().startsWith("OCMIvrCallTraceReport")) {
				f.delete();
			}
		}
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath, "OCMIvrCallTraceReport");
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

	public boolean verifySearchIsEqualTo(String details) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>();
		selectWebElement(searchBtn);	
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,"Caller ID");  
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
			System.out.println("Parameter used for search is"+":"+details);
			System.out.println("Values returned after applying search on UI is"+":"+map1.get("Caller ID"));
			if(map1.get("Caller ID").equals(details))
				Status= true;
			else 
				Status= false;
		}
		return Status;	
	}
	
	public boolean verifySearchIsNotEqualTo(String details) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("UID", details);
		selectWebElement(searchBtn);	
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,"UID");  
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
			System.out.println(map1);
			System.out.println(map);
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;	
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

	public boolean verifySearchByTextbox(ReportDetails details) throws Exception{	
		boolean Status=false;		
		selectWebElement(searchbyfeatureTextBox);    		
		enterValueToTxtFieldWithoutClear(searchbyfeatureTextBox,details.getSearchStr());
		Thread.sleep(5000);
		selectDropdownFromVisibleText(searchbyfeaturelistBox,details.getSearchStr());	
		waitForJqueryLoad(driver);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{ 
			System.out.println(map1.get("Caller ID"));
			if(map1.get("Caller ID").equals(details.getSearchStr()))
				Status= true;
			else 
				Status= false;
		}
		return Status;	
	}

	public boolean verifySearchContains(String details) throws Exception {
		Boolean Status=false;		
		selectWebElement(searchBtn);
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,"UCID");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Contains");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,details);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("UCID").contains(details))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}

	public boolean verifySearchDoesNotContains(String details) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,"Hotline Number");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Does not contain");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,details);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		//waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(!map1.get("Hotline Number").contains(details))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}

	public boolean verifySearchStartsWith(String details) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
		selectWebElement(searchColDropdown); 
		selectDropdownFromVisibleText(searchColListBox,"Caller ID");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Starts with");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,details);        
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		//waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("Caller ID").startsWith(details))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}
	
	public boolean verifySearchEndsWith(String details) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
		selectWebElement(searchColDropdown); 
		selectDropdownFromVisibleText(searchColListBox,"UID");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Ends with");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,details);        
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		// waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{   	
			System.out.println(details.toLowerCase());
			System.out.println(map1.get("UID").toLowerCase());
			if(map1.get("UID").endsWith(details))
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
	
	public boolean verifyAdvanceSearchIsEqualTo(ReportDetails details) throws Exception {
		boolean Status=false;
		waitForJqueryLoad(driver);
		List<Map<String,String>>UI=getDataTable();
		for(Map<String,String> map1:UI)
		{
			System.out.println(map1.get("UCID"));
			if(map1.get("UCID").equals(details.getSearchStr()))
				Status= true;
			else 
				Status =false;
		}
		return Status;
	}
	
	public boolean verifyAdvanceSearchIsNotEqualTo(ReportDetails details) throws Exception {
		boolean Status=false;
		waitForJqueryLoad(driver);
		List<Map<String,String>>UI=getDataTable();
		for(Map<String,String> map1:UI){
			System.out.println(map1.get("Caller ID"));
			if(map1.get("Caller ID").equals(details.getSearchStr()))
				Status= false;
			else 
				Status =true;
		}
		return Status;
	}
	
	public boolean verifyAdvanceSearchContains(ReportDetails details) throws Exception {
		boolean Status=false;
		waitForJqueryLoad(driver);
		List<Map<String,String>>UI=getDataTable();
		for(Map<String,String> map1:UI){
			if(map1.get("Caller Status").contains(details.getSearchStr()))				
				Status= true;
			else 
				Status =false;
		}
		return Status;
	}
	
	public boolean verifyAdvanceSearchDoesNotContains(ReportDetails details) throws Exception {
		boolean Status=false;
		waitForJqueryLoad(driver);
		List<Map<String,String>>UI=getDataTable();
		for(Map<String,String> map1:UI){
			System.out.println(map1.get("Caller ID"));
			if(!map1.get("Caller ID").contains(details.getSearchStr()))				
				Status= true;
			else 
				Status =false;
		}
		return Status;
	}
	
	public boolean verifyAdvanceSearchStartsWith(ReportDetails details) throws Exception {
		boolean Status=false;
		waitForJqueryLoad(driver);
		List<Map<String,String>>UI=getDataTable();
		for(Map<String,String> map1:UI)
		{
			System.out.println(map1.get("UID"));
			if(map1.get("UID").toLowerCase().startsWith(details.getSearchStr().toLowerCase()))				
				Status= true;
			else 
				Status =false;
		}
		return Status;
	}
	
	public boolean verifyAdvanceSearchEndsWith(ReportDetails details) throws Exception {
		boolean Status=false;
		waitForJqueryLoad(driver);
		List<Map<String,String>>UI=getDataTable();
		for(Map<String,String> map1:UI)
		{
			System.out.println(map1.get("Hotline Number"));
			if(map1.get("Hotline Number").endsWith(details.getSearchStr()))				
				Status= true;
			else 
				Status =false;
		}
		return Status;
	}

	public Boolean advancedSearchANDCriteria(ReportDetails details) throws Exception {
		Boolean Status=false;	
		selectWebElement(advancedsearchBtn);
		selectWebElement(searchColDropdownAdvSrchReportPage);
		Thread.sleep(2000);
		selectDropdownFromVisibleText(searchColListBoxAdvSrchReportPage,"UCID");
		Thread.sleep(2000);
		selectWebElement(searchCriteriaDropdownAdvSrch);
		selectDropdownFromVisibleText(searchCriteriaListboxAdvSrch,"Is equal to");
		enterValueToTxtField(searchTextBoxAdvSrch,details.getSearchStr());
		selectWebElement(searchAddCriteriaBtn);
		moveToElement(andradiobtn);
		selectWebElement(andradiobtn);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(searchColDropdownAdvSrchReportPage1);
		Thread.sleep(2000);
		selectDropdownFromVisibleText(searchColListBoxAdvSrchReportPage1,"UCID");
		Thread.sleep(2000);
		selectWebElement(searchCriteriaDropdownAdvSrch1);
		selectDropdownFromVisibleText(searchCriteriaListboxAdvSrch1,"Contains");
		enterValueToTxtField(searchTextBoxAdvSrch1,details.getSearchStr1());
		selectWebElement(showReportBtn.get(0));
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridBoxContent);
		Thread.sleep(2000);
		List<Map<String,String>>UI=getDataTable();
		for(Map<String,String> map1:UI)
		if(map1.get("UCID").equals(details.getSearchStr()) && map1.get("UCID").contains(details.getSearchStr1())) {
			Status=true;
		}
		return Status;	
	}
	
	public Boolean advancedSearchORCriteria(ReportDetails details) throws Exception {
		Boolean Status=false;	
		selectWebElement(advancedsearchBtn);
		selectWebElement(searchColDropdownAdvSrchReportPage);
		Thread.sleep(2000);
		selectDropdownFromVisibleText(searchColListBoxAdvSrchReportPage,"Caller ID");
		Thread.sleep(2000);
		selectWebElement(searchCriteriaDropdownAdvSrch);
		selectDropdownFromVisibleText(searchCriteriaListboxAdvSrch,"Contains");
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
		selectDropdownFromVisibleText(searchColListBoxAdvSrchReportPage1,"Duration (in seconds)");
		Thread.sleep(2000);
		selectWebElement(searchCriteriaDropdownAdvSrch1);
		selectDropdownFromVisibleText(searchCriteriaListboxAdvSrch1,"Is equal to");
		enterValueToTxtField(searchTextBoxAdvSrch1,details.getSearchStr1());
		selectWebElement(showReportBtn.get(0));
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridBoxContent);
		Thread.sleep(3000);
		List<Map<String,String>>UI=getDataTable();
		for(Map<String,String> map1:UI)
			if(map1.get("Caller ID").contains(details.getSearchStr()) || map1.get("Duration (in seconds)").equals(details.getSearchStr1())) {
				Status=true;
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
	
	public boolean groupby() {
		DragandDrop(callerId,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(groupbyCallerId.getText()))
		{return true;}
		else
			return false;		
	}
	
	public boolean verifyJsonDataForgridColumnHidden(Map<String,String> jsonmap){
		System.out.println(jsonmap);
		boolean status=false;
		for(WebElement e: headersText){
			scrollToElement(e);
			if(jsonmap.get(e.getText()).equalsIgnoreCase("false")){status=true;}else{
				System.out.println("Header "+e.getText()+"is hidden in JSON configuration file");status=false;break;}
		}
		return status;
	}
	public boolean verifyMenuTraversalButton(ReportDetails reportDetails) throws Exception {		
		boolean status=false;
		selectWebElement(rows.get(0));
		waitForJqueryLoad(driver);
		selectWebElement(menugridId);
		waitForJqueryLoad(driver);
		if(menuTraversalGrid.isDisplayed())
		{
			selectWebElement(menugridId);
			status=true;}
		else
			status=false;
		return status;
	}
	public boolean verifyCallflowDiagram(ReportDetails reportDetails) throws Exception {		
		boolean status=false;
		selectWebElement(rows.get(0));
		waitForJqueryLoad(driver);
		selectWebElement(callflowdiagramlink);
		waitForJqueryLoad(driver);
		if(callFlowDiagramgrid.isDisplayed())
		{
			selectWebElement(callflowdiagramlink);
			status=true;}
		else
			status=false;
		return status;
	}
	public boolean verifyCallflowGrid(ReportDetails reportDetails) throws Exception {		
		boolean status=false;
		selectWebElement(rows.get(0));
		waitForJqueryLoad(driver);
		selectWebElement(callflowGridlink);
		waitForJqueryLoad(driver);
		if(callFlowGrid.isDisplayed())
		{
			selectWebElement(callflowGridlink);
			status=true;}
		else
			status=false;
		return status;
	}


	public boolean verifyDatabase(String query,ReportDetails details) throws Exception {
		//get dates from xl - step 2
		String reportbeforedate = details.getStartDate();
		String reportafterdate=details.getEndDate();
		//change date formats - step 3
		reportbeforedate=reportbeforedate.substring(6,10)+"-"+reportbeforedate.substring(3, 5)+"-"+reportbeforedate.substring(0, 2)+" "+reportbeforedate.substring(11, 13)+":"+reportbeforedate.substring(14, 16)+":"+reportbeforedate.substring(17, 19);
		reportafterdate=reportafterdate.substring(6,10)+"-"+reportafterdate.substring(3, 5)+"-"+reportafterdate.substring(0, 2)+" "+reportafterdate.substring(11, 13)+":"+reportafterdate.substring(14, 16)+":"+reportafterdate.substring(17, 19);
		//Replace identifiers in query to formatted date - step 5
		query=query.replaceAll("ReportBeforeDate",reportbeforedate );
		query=query.replaceAll("ReportAfterDate",reportafterdate );
		List<Map<String,String>> database=database(query);
		System.out.println("Printing Query" +" "+query);		
		System.out.println("Printing DB results" +" "+database);
		Thread.sleep(3000);
		List<Map<String,String>> UI=getDataTable1(); 
		System.out.println("Printing UI Results"+" "+UI);	
		if(UI.equals(database))
			return true;
		else
			return false;
	}
	
	private List<Map<String, String>> getDataTable1() {
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
	

	public List<String> getUCID() throws InterruptedException {
		int item=Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
        int pagersize=Integer.valueOf(pagerSize.getText());
        int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<String> UCID = new ArrayList<>();
		for(int k=0;k<=pages;k++){
		waitUntilWebElementIsVisible(auditGridContent);
		List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
		List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
		for(int i=1;i<rows.size();i++) {
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			String col=null;
			for(int j=0;j<headers.size();j++){
				scrollToElement(headers.get(j));
				if(headers.get(j).getText().equals("UCID")){
					col=cols.get(j).getText();
					UCID.add(col);
					}
			}
		}
		if(k!=pages)
		{
			Thread.sleep(1000);
			nextPageIcon.click();
			waitForJqueryLoad(driver);
		}
		}
		return UCID;
	}

	public void goToNextPage() {
		nextPageIcon.click();
		waitForLoad(driver);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(DrillGridOneTable);
	}
	
	
	public void clickOnUCIDRowOnMainReport(int rowNo) throws InterruptedException {
		Thread.sleep(3000);
		MainReportRows.get(rowNo).click();
		waitForLoad(driver);
        waitForJqueryLoad(driver);
//        selectWebElement(MenuTraversalPanel);
//        waitUntilWebElementIsVisible(MenuTraversalTable);  
	}

	public boolean verifyDatabaseDrillGridOne(String queryDrillGridOne,ReportDetails details, String UCID) {
		//get dates from xl - step 2
		String reportbeforedate = details.getStartDate();
		String reportafterdate=details.getEndDate();
		//change date formats - step 3
		reportbeforedate=reportbeforedate.substring(6,10)+"-"+reportbeforedate.substring(3, 5)+"-"+reportbeforedate.substring(0, 2)+" "+reportbeforedate.substring(11, 13)+":"+reportbeforedate.substring(14, 16)+":"+reportbeforedate.substring(17, 19);
		reportafterdate=reportafterdate.substring(6,10)+"-"+reportafterdate.substring(3, 5)+"-"+reportafterdate.substring(0, 2)+" "+reportafterdate.substring(11, 13)+":"+reportafterdate.substring(14, 16)+":"+reportafterdate.substring(17, 19);
		//Replace identifiers in query to formatted date - step 5
		queryDrillGridOne=queryDrillGridOne.replaceAll("ReportBeforeDate",reportbeforedate);
		queryDrillGridOne=queryDrillGridOne.replaceAll("ReportAfterDate",reportafterdate );
		queryDrillGridOne=queryDrillGridOne.replaceAll("UCIDCapturedFromUI", UCID);
		List<Map<String,String>> database=database(queryDrillGridOne);
		System.out.println("Printing Query" +" "+queryDrillGridOne);		
		System.out.println("Printing DB results" +" "+database);
		List<Map<String,String>> UI=getDataTableDrillGridOne(); 
		System.out.println("Printing UI Results"+" "+UI);	
		if(UI.equals(database))
			return true;
		else
			return false;
	}
	
	private List<Map<String, String>> getDataTableDrillGridOne() {
		scrollvertically();
//		waitUntilWebElementIsClickable(drillGridOneItems);
//		waitUntilWebElementIsVisible(drillGridOneItems);
		selectWebElement(MenuTraversalPanel);
		waitUntilWebElementIsVisible(MenuTraversalTable);  
	 	int item=Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
        int pagersize=Integer.valueOf(pagerSize.getText());
        int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
	 	List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
	 	for(int k=0;k<=pages;k++){
	 	waitUntilWebElementIsVisible(DrillGridOneTable);
		List<WebElement> rows=DrillGridOneTable.findElements(By.tagName("tr"));
		List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
		for(int i=1;i<rows.size();i++) {
			Map<String,String> map = new HashMap<String,String>();
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			String col=null;
			for(int j=0;j<headers.size();j++){
				scrollToElement(headers.get(j)); 
				if(headers.get(j).getText().equals("")){					
					col=cols.get(j).getText();
					if(col.contains("."))
						col=col;
					else
						col=col+".00";
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
			nextPageIconDrillOne.click();
			waitForJqueryLoad(driver);
			waitUntilWebElementIsVisible(DrillGridOneTable);
		}
		}
	 	selectWebElement(MenuTraversalPanel);
			CloseDrillGridOne.click();
			return arr;
	}
	
	
}



