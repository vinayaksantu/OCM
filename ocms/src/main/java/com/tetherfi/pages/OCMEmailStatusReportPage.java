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
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;

public class OCMEmailStatusReportPage extends BasePage{

	public OCMEmailStatusReportPage(WebDriver driver) {
		super(driver);}

	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;

	@FindBy(css=".k-pager-sizes .k-input")
	private WebElement pagerSize;

	@FindBy(id="grid")
	private WebElement auditGridContent;

	@FindBy(css="a[aria-label='Go to the next page']")
	private WebElement nextPageIcon;

	@FindBy(xpath="//i[@class='fas fa-file-export']")
	private WebElement VEFImg;

	@FindBy(css=".ibox-title h5")
	private WebElement exportSchedulerTitle;

	@FindBy(css="a[href$='/ExportScheduler/Index'] div")
	private WebElement exportschedulerlinkonHomepg;

	@FindBy(xpath="//a[text()='Session ID']")
	private WebElement sessionid;

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

	@FindBy(css=".k-i-more-vertical")
	private List<WebElement> headersDropdown;

	@FindBy(css=".k-sort-asc")
	private List<WebElement> sortAscending;

	@FindBy(css=".k-sort-desc")
	private List<WebElement> sortDescending;

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

	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")		
	private WebElement exporttoexcel;

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

	@FindBy(xpath="//div[@id=\"grid\"]/div[3]/div/table/thead/tr/th")
	private List<WebElement> columnHeader;

	@FindBy(id = "1001sTextToSearch")
	private WebElement searchTextBox;

	@FindBy(id = "1001sMaskedTextToSearch")
	private WebElement searchTextBox1;
	
	@FindBy(id = "1001MaskedTextToSearch")
	private WebElement searchTextBox2;
	
	@FindBy(css = ".modal-footer .k-button")
	private WebElement searchCloseBtn;

	@FindBy(css = ".modal-footer .button-danger-theme")
	private WebElement searchClearAllBtn;

	@FindBy(css = ".modal-footer .button-theme")
	private WebElement searchSearchBtn;

	@FindBy(id = "yesButton")
	private WebElement deleteYesBtn;

	@FindBy(id = "noButton")
	private WebElement deleteNoBtn;

	@FindBy(id = "ModifyReason1")
	private WebElement deleteReasonTextBox;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-CustomDelete']")
	private List<WebElement> deleteReportinInReportDownloadpage;

	@FindBy(css="div[style='display: block; z-index: 10002; opacity: 0.5;']")
	private WebElement deleteContainer;

	@FindBy(css = "ul[id='autoCompleteTextbox_listbox'] li")
	private List<WebElement> searchbyfeaturelistBox;

	@FindBy(id="autoCompleteTextbox")
	private WebElement searchbyfeatureTextBox;

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
	
	@FindBy(id = "1001MaskedTextToSearch")
	private WebElement maskedTextbox;
	
	@FindBy(id="1001AddButton")
	private WebElement searchAddCriteriaBtn;

	@FindBy(xpath="//label[@for='1001RadioAND']")
	private WebElement andradiobtn;

	@FindBy(xpath="//label[@for='1001RadioOR']")
	private WebElement orradiobtn;

	@FindBy(css = ".k-Show")
	private List<WebElement> showReportBtn;

	@FindBy(xpath="//tbody/tr/td[11]")
	private WebElement receivedTime;
	
	@FindBy(xpath="//tbody/tr/td[8]")
	private WebElement senddate;

	@FindBy(xpath="//tbody/tr/td[5]")
	private WebElement rowdata1;

	@FindBy(xpath="//tbody/tr/td[3]")
	private WebElement tolist;

	@FindBy(xpath="//tbody/tr[2]/td[1]")
	private WebElement rowdatatwo;

	@FindBy(id="tGrid")
	private WebElement Grid;

	@FindBy(id = "grid")
	private WebElement gridBoxContent;

	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;

	@FindBy(xpath="//a[text()='Send Date']")
	private WebElement sendDate;

	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupby;

	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[9]")
	private WebElement groupbysendDate;

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
		System.out.println("Excel"+maplist);
		System.out.println("UI"+UI);
		if(UI.equals(maplist))
			return true;
		else
			return false;
	}

	private List<Map<String,String>> getdata(){
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		waitUntilWebElementIsVisible(auditGridContent);
		List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
		List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
		String col=null;
		for(int i=1;i<rows.size();i++) {
			scrollToElement(rows.get(i));
			Map<String,String> map = new HashMap<String,String>();
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			for(int j=0;j<headers.size();j++) {
				scrollToElement(headers.get(j));
				col=cols.get(j).getText();
				map.put(headers.get(j).getText(),col);
			}
			arr.add(map);
		}
		return arr;
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

	public boolean verifyTotalNumberOfItemsPerPageDetails(){
		String item = items.getText();
		return item.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
	}

	public void SortByAscending() {
		selectWebElement(sessionid);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(sessionid);
		selectWebElement(sessionid);
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
			if (f.getName().startsWith("OCMEmailStatusReport")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "OCMEmailStatusReport");
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
	public boolean verifyAdvanceSearch(ReportDetails reportDetails) throws Exception {
		Boolean Status=false;
		List<Map<String,String>>UI=getDataTable();
		for(Map<String,String> map1:UI)
		{
			System.out.println(map1.get("To List"));
			if(map1.get("To List").equalsIgnoreCase(reportDetails.getSearchStr()))
				Status= true;
			else 
				Status =false;
		}
		return Status;
	}
	private List<Map<String, String>> getDataTable() throws Exception{
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
				nextPageIcon.click();
				waitForJqueryLoad(driver);}
		}
		return arr;
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
	
	public boolean verifySearchEqualIsEqualto(String details) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("To List", details);
		selectWebElement(searchBtn);	
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,"To List");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Is equal to");		   
		waitForJqueryLoad(driver); 
		Thread.sleep(2000);
		System.out.println(details);
		selectWebElement(searchTextBox);
		enterValueToTxtField(searchTextBox,details);
		System.out.println(searchTextBox);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		//waitUntilWebElementIsVisible(gridContent);
		System.out.println(map);

		List<Map<String,String>> UI=getDataTable();
		for (Map<String,String> map1: UI)
		{   System.out.println(map1);	
		if(map1.get("To List").equals(details))			
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
		selectDropdownFromVisibleText(searchColListBox,"BCC List");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Contains");		   
		waitForJqueryLoad(driver);    
		enterValueToTxtField(searchTextBox,details);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		//waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("BCC List").toUpperCase().contains(details.toUpperCase()))
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
		selectDropdownFromVisibleText(searchColListBox,"To List");  
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
			if(!map1.get("To List").toLowerCase().contains(description.toLowerCase()))
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
		selectDropdownFromVisibleText(searchColListBox,"Current Status");  
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
			if(map1.get("Current Status").toUpperCase().endsWith(description.toUpperCase()))
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
		if(auditGridContent.isDisplayed())
			Status= true;
		else
			Status=false;
		return Status;	
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

	public void deleteWithoutDeleteReason(ReportDetails details) throws Exception {

		waitUntilWebElementIsVisible(deleteReportinInReportDownloadpage.get(0));
		selectWebElement(deleteReportinInReportDownloadpage.get(0));
		waitForJqueryLoad(driver);
		selectWebElement(deleteYesBtn);			
	}
	public void deletecancelButton(ReportDetails details) throws Exception{	
		waitUntilWebElementIsVisible(deleteReportinInReportDownloadpage.get(0));
		selectWebElement(deleteReportinInReportDownloadpage.get(0));
		selectWebElement(deleteNoBtn);
	}
	public void deleteRecordAtReportsDownloadsPage(ReportDetails details) throws Exception {

		waitUntilWebElementIsVisible(deleteReportinInReportDownloadpage.get(0));
		selectWebElement(deleteReportinInReportDownloadpage.get(0));
		waitForJqueryLoad(driver);
		enterValueToTxtFieldWithoutClear(deleteReasonTextBox,details.getDeleteReason());
		selectWebElement(deleteYesBtn);				
	}
	public boolean verifyDeleteContainer() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return isElementExist(deleteContainer);
	}
	public boolean verifySearchByTextbox(ReportDetails details) throws Exception{	
		boolean Status=false;		
		selectWebElement(searchbyfeatureTextBox);    
		enterValueToTxtFieldWithoutClear(searchbyfeatureTextBox,details.getSearchStr());
		Thread.sleep(2000);
		selectDropdownFromVisibleText(searchbyfeaturelistBox,details.getSearchStr());	
		waitForJqueryLoad(driver);
		Thread.sleep(2000);
		List<Map<String,String>> UI=getDataTable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("Session ID").equals(details.getSearchStr()))
				Status= true;
			else 
				Status= false;
		}
		return Status;	
	}
	public Boolean advancedSearchANDCriteria(ReportDetails details) throws Exception {
		Boolean Status=false;	
		selectWebElement(advancedsearchBtn);
		selectWebElement(searchColDropdownAdvSrchReportPage);
		Thread.sleep(2000);
		selectDropdownFromVisibleText(searchColListBoxAdvSrchReportPage,"To List");
		Thread.sleep(2000);
		selectWebElement(searchCriteriaDropdownAdvSrch);
		selectDropdownFromVisibleText(searchCriteriaListboxAdvSrch,"Contains");
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
		selectDropdownFromVisibleText(searchColListBoxAdvSrchReportPage1,"Send Date");
		Thread.sleep(2000);
		selectWebElement(searchCriteriaDropdownAdvSrch1);
		selectDropdownFromVisibleText(searchCriteriaListboxAdvSrch1,"Is equal to");
		selectWebElement(maskedTextbox);
		enterValueToTxtFieldWithoutClear(maskedTextbox,details.getSearchStr1());
		selectWebElement(showReportBtn.get(0));
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridBoxContent);
		Thread.sleep(2000);
		if(tolist.getText().contains(details.getSearchStr()) && senddate.getText().equals(details.getSearchStr1())) {
			Status=true;
		}
		return Status;	

	}
	public Boolean advancedSearchORCriteria(ReportDetails details) throws Exception {
		Boolean Status=false;	
		selectWebElement(advancedsearchBtn);
		selectWebElement(searchColDropdownAdvSrchReportPage);
		Thread.sleep(2000);
		selectDropdownFromVisibleText(searchColListBoxAdvSrchReportPage,"To List");
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
		selectDropdownFromVisibleText(searchColListBoxAdvSrchReportPage1,"Send Date");
		Thread.sleep(2000);
		selectWebElement(searchCriteriaDropdownAdvSrch1);
		selectDropdownFromVisibleText(searchCriteriaListboxAdvSrch1,"Is equal to");
		selectWebElement(maskedTextbox);
		enterValueToTxtFieldWithoutClear(maskedTextbox,details.getSearchStr2());
		selectWebElement(showReportBtn.get(0));
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridBoxContent);
		Thread.sleep(3000);
		List<WebElement> rows=Grid.findElements(By.tagName("tr"));	
		for(WebElement e:rows)
		{
			if(tolist.getText().contains(details.getSearchStr())||senddate.getText().equals(details.getSearchStr2()))
				Status=true;
		}
		return Status;	

	}

	public boolean groupby() {
		waitForJqueryLoad(driver);
		DragandDrop(sendDate,droptarget);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(groupbysendDate.getText()))
		{return true;}
		else
			return false;		
	}	


	public boolean verifySearchAfterorEqualTo(String details) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("Send Date", details);
		selectWebElement(searchBtn);	
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,"Send Date");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Is after or equal to");		   
		waitForJqueryLoad(driver); 
		Thread.sleep(2000);
		System.out.println(details);
		selectWebElement(searchTextBox1);
		enterValueToTxtFieldWithoutClear(searchTextBox1,details);
		System.out.println(searchTextBox1);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		//waitUntilWebElementIsVisible(gridContent);
		System.out.println(map);

		List<Map<String,String>> UI=getDataTable();
		for (Map<String,String> map1: UI)
		{   System.out.println(map1);	
		if((senddate.getText().compareTo(details) > 0 ) || (senddate.getText().compareTo(details) == 0 ))		
			Status= true;
		else 
			Status= false;
		}

		return Status;	
	}

	public boolean verifySearchBeforeorEqualTo(String details) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("Received Time", details);
		selectWebElement(searchBtn);	
		selectWebElement(searchColDropdown);  
		selectDropdownFromVisibleText(searchColListBox,"Received Time");  
		waitForJqueryLoad(driver);
		selectWebElement(searchCriteriaDropdown);
		selectDropdownFromVisibleText(searchCriteriaListbox,"Is before or equal to");		   
		waitForJqueryLoad(driver); 
		Thread.sleep(2000);
		System.out.println(details);
		selectWebElement(searchTextBox1);
		enterValueToTxtFieldWithoutClear(searchTextBox1,details);
		System.out.println(searchTextBox1);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		//waitUntilWebElementIsVisible(gridContent);
		System.out.println(map);

		List<Map<String,String>> UI=getDataTable();
		for (Map<String,String> map1: UI)
		{   System.out.println(map1);	
		if((receivedTime.getText().compareTo(details) < 0 ) || (receivedTime.getText().compareTo(details) == 0 ))		
			Status= true;
		else 
			Status= false;
		}

		return Status;	
	}

	public boolean verifyDatabase(String query,ReportDetails details) throws InterruptedException {
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
//		System.out.println("Printing Query" +" "+query);		
//		System.out.println("Printing DB results" +" "+database);
		List<Map<String,String>> UI=getDataTable1(); 
//		System.out.println("Printing UI Results"+" "+UI);	
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
					if(headers.get(j).getText().contains("Body")){
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
				map.remove("Body");
				arr.add(map);
			}
			if(k!=pages)
			{
				nextPageIcon.click();
				waitForJqueryLoad(driver);
			}
		}
		return arr;
	}


}	


