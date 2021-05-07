package com.tetherfi.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.fax.FaxTemplateDetails;
import com.tetherfi.utility.FileUploader;


public class FaxTemplatePage extends BasePage{

	public FaxTemplatePage(WebDriver driver) {
		super(driver);

	}

	@FindBy(css=".ibox-title h5")
	private WebElement faxTemplate;

	@FindBy(xpath="//i[@class='fas fa-fax']")
	private WebElement FTImg;

	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;

	@FindBy(id="navbarheader")
	private WebElement header;

	@FindBy(css="#checkerGrid th[data-role='columnsorter']")
	private List<WebElement> approvedDataTableHeaders;

	@FindBy(css="ul[class='k-tabstrip-items k-reset'] li")
	private List<WebElement> FaxTemplateTabs;

	@FindBy(css="#tGrid th[data-role='columnsorter']")
	private List<WebElement> auditTrailTableHeaders;

	@FindBy(id="makeChanges")
	private WebElement makeFaxTemplateChanges;

	@FindBy(id="create")
	private WebElement addNewRecordBtn;

	@FindBy(css = ".k-grid-edit")
	private WebElement editButton;

	@FindBy(css = ".k-grid-CustomDelete")
	private WebElement deleteButton;

	@FindBy(id="noButton")
	private WebElement nobtn;

	@FindBy(id="yesButton")
	private WebElement yesbtn;

	@FindBy(css=".form-group #ModifyReason1")
	private  WebElement deleteReasonTextBox;

	@FindBy(css=".k-grid-cancel")
	private WebElement cancelBtn;

	@FindBy(css="#drillGrid tbody tr td")
	private List<WebElement> editrowdata;

	@FindBy(id="ModifyReason")
	private WebElement modifyResonTextBox;

	@FindBy(css=".k-grid-update")
	private WebElement saveBtn;

	@FindBy(id="goToAuditTrail")
	private WebElement goBackBtn;

	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;

	@FindBy(css="#drillGrid th[data-role='columnsorter']")
	private List<WebElement> makerTableHeaders;

	@FindBy(css="#drillGrid th a[title='Edit Column Settings']")
	private List<WebElement> headersDropdown;

	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;

	@FindBy(css="#drillGrid th a[class='k-link']")
	private List<WebElement> headersText;

	@FindBy(css="a[aria-label='Go to the next page']")
	private List<WebElement> nextPageIcon;

	@FindBy(css="a[aria-label='Go to the previous page']")
	private List<WebElement> previousPageIcon;

	@FindBy(css=".k-pager-numbers .k-state-selected")
	private List<WebElement> pageNumber;

	@FindBy(css="a[aria-label='Go to the first page']")
	private List<WebElement> firstPageIcon;

	@FindBy(css="a[aria-label='Go to the last page']")
	private List<WebElement> lastPageIcon;

	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private List<WebElement> items;

	@FindBy(css=".k-pager-sizes .k-icon")
	private List<WebElement> pagerDropdown;

	@FindBy(css=".k-animation-container ul li")
	private List<WebElement> pageSizeListBox;

	@FindBy(css="#checkerGrid")
	private WebElement checkerGrid;

	@FindBy(css=".k-pager-sizes .k-input")
	private List<WebElement> pagerSize;

	@FindBy(id = "drillGrid")
	private WebElement grid;

	@FindBy(css="#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;

	@FindBy(css=".toast-message")
	private WebElement successmsg;

	@FindBy(css="#gridDiv2 .search-link")
	private WebElement searchLink;

	@FindBy(css=".modal-body .form-inline .form-group .k-select")
	private List<WebElement> selectSearchColumn;

	@FindBy(css="ul[id='1001sColumnName_listbox'] li")
	private List<WebElement> columnNameList;

	@FindBy(css="ul[id='1001sCriteria_listbox'] li")
	private List<WebElement> searchTypeList;

	@FindBy(id="1001sTextToSearch")
	private WebElement searchText;

	@FindBy(css=".modal-footer .k-primary")
	private WebElement searchBtn;

	@FindBy(xpath="//a[text()='Template Name']")
	private List<WebElement> TemplateName;

	@FindBy(css = "#drillGrid .k-grouping-header")
	private WebElement dragColumnDestination;

	@FindBy(css="#tGrid #drillGrid")
	private WebElement gridContent;

	@FindBy(css="#gridDiv .search-link")
	private WebElement gridsearchLink;

	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearall;

	@FindBy(xpath="//button[text()='Close']")
	private WebElement searchClose;

	@FindBy(css="#tcheckerGrid .k-grid-content")
	private WebElement approvedgridcontent;

	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;

	@FindBy(xpath="//i[@class='fas fa-sync fa-spin']")
	private WebElement clearsearch;

	@FindBy(css = "#gridDiv2 .fa-search")
	private WebElement searchBtn1;

	@FindBy(css = ".modal-body .form-inline .form-group .k-select")
	private List<WebElement> selectSearchCol;

	@FindBy(css="ul[id='1001sCriteria_listbox'] li")
	private List<WebElement> searchCriteriaDropDwn;

	@FindBy(id = "1001sTextToSearch")
	private WebElement searchTextBox;

	@FindBy(css = ".modal-footer .button-theme")
	private WebElement searchSearchBtn;

	@FindBy(xpath="//span[@class='k-widget k-dropdown' and @aria-owns='TemplateType_listbox' ]")
	private WebElement templateType;

	@FindBy(css="ul[id='TemplateType_listbox'] li")
	private List<WebElement> templateTypeListBox;

	@FindBy(xpath="//iframe[@class='k-content']")
	private WebElement iframe;

	@FindBy(id="TemplateName")
	private WebElement templateName;

	@FindBy(xpath="//html[@lag='en']")
	private WebElement content;

	@FindBy(xpath="//body")
	private List<WebElement>body;

	@FindBy(id="grid")
	private WebElement auditGridContent;

	@FindBy(id="undoChanges")
	private WebElement revertBtn;

	@FindBy(id="sendForApproval")
	private WebElement sendForApprovalBtn;

	@FindBy(id="MakerComments")
	private WebElement makerComments;

	@FindBy(id="undoChangesMakerComments")
	private WebElement revertMakerComments;

	@FindBy(id="submitMakerComment")
	private WebElement submitMakerComments;

	@FindBy(id="submitUndoChangesMakerComment")
	private WebElement revertSubmitMakerComments;

	@FindBy(id="Reject")
	private WebElement rejectBtn;

	@FindBy(id="CheckerReason")
	private WebElement checkerReason;

	@FindBy(id="Approve")
	private WebElement approveBtn;

	@FindBy(id="approveButton")
	private WebElement approveYesBtn;

	@FindBy(xpath="//tbody/tr/td[2]")
	private WebElement rowdata;

	@FindBy(css=".k-upload-button")
	private WebElement selectFile;

	@FindBy(xpath="//span[@title='Format']/span")
	private WebElement formatDropDown;

	@FindBy(xpath="//span[text()='Heading 1']")
	private WebElement formatHeading1;

	@FindBy(xpath="//a[@title='Italic']")
	private WebElement italicFormatText;

	@FindBy(xpath="//a[@title='Insert image']")
	private WebElement InsertImage;

	@FindBy(xpath="//span[text()='Insert image']")
	private WebElement ImgPopup;

	@FindBy(xpath="//div[@class='k-button k-button-icontext k-upload-button']")
	private WebElement ImageUploadBtn;

	@FindBy(xpath="//input[@placeholder='Search']")
	private WebElement searchImagePlaceHolder;

	@FindBy(xpath="//a[@class='k-icon k-i-zoom k-search']")
	private WebElement searchIcon;

	@FindBy(xpath="//img[@alt='London.png']")
	private WebElement Img1;

	@FindBy(xpath="//button[text()='Insert']")
	private WebElement insertImg;
	
	@FindBy(xpath="//strong[text()='Empty Folder']")
	private WebElement emptyFolder;
	
	@FindBy(xpath="//a[@title='Insert unordered list']")
	private WebElement insertUnorderdList;
	
	@FindBy(id="tdrillgrid")
	private WebElement tgrid;

	public boolean isFaxTemplatePageDisplayed() {
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		return faxTemplate.isEnabled();
	}

	public boolean verifylogo() {
		if(isElementExist(FTImg))
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

	private ArrayList getHeadersFromTable(List<WebElement> e) {
		ArrayList header=new ArrayList();
		for(int j=0;j<e.size();j++) {
			scrollToElement(e.get(j));
			if(!e.get(j).getText().equals("")) {header.add(e.get(j).getText());}
		}
		return header;
	}


	public boolean verifyApprovedDataHeader(){
		ArrayList<String>Expected=new ArrayList<String>(Arrays.asList("Template Name","FileName","Last Changed By","Last Changed On"));
		ArrayList Actual=getHeadersFromTable(approvedDataTableHeaders);
		System.out.println(Actual);
		System.out.println(Expected);
		Collections.sort(Expected);
		Collections.sort(Actual);
		return Actual.equals(Expected);
	}

	public void selectFaxTemplateAuditTrailTab() {
		selectWebElement(FaxTemplateTabs.get(1));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean verifyAuditTrailDataTableHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList(" ","Request Id", "Transaction", "Function", "Status", "User Id", "Submission DateTime", "Maker Comments", "Reviewed By","Review DateTime", "Checker Comments"));
		ArrayList Actual = getHeadersFromTable(auditTrailTableHeaders);
		System.out.println(Actual);
		Collections.sort(Expected);Collections.sort(Actual);
		return Actual.equals(Expected);
	}

	public void selectMakeFaxTemplateChanges() {
		selectWebElement(makeFaxTemplateChanges);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean verifyAddNewFaxTemplateRecordButton() {
		return addNewRecordBtn.isEnabled();
	}

	public boolean verifyGoBackButton() {
		return goBackBtn.isEnabled();
	}

	public void selectGoBackButton() throws Exception
	{
		selectWebElement(goBackBtn);
		Thread.sleep(2000);
	}

	public boolean verifyExportToExcelButton() {
		return exporttoexcel.isEnabled();
	}

	public boolean verifyMakerDataTableHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("Template Name","FileName","Last Changed By","Last Changed On"));
		ArrayList Actual = getHeadersFromTable(makerTableHeaders);
		System.out.println(Actual);
		Collections.sort(Expected);Collections.sort(Actual);
		return Actual.equals(Expected);
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

	public boolean verifycolumnsHeaderDisbaled() {
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
			for (int i = 3; i < headersColumns.size(); i++) {
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

	public boolean verifyArrowMoveForPreviousAndNextPage(int i){
		boolean status=false;
		if(!nextPageIcon.get(i).getAttribute("class").contains("k-state-disabled")){
			int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
			selectWebElement(nextPageIcon.get(i));
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
			selectWebElement(previousPageIcon.get(i));
			int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
			if(nextnumber==(pagenumber+1) && pagenumber==previousnumber){status=true;}
		}else{
			System.out.println("previous and next page icon disabled");status=true;
		}
		return status;
	}

	public boolean verifyArrowMoveForFirstAndLastPage(int i){
		boolean status=false;
		if(!lastPageIcon.get(i).getAttribute("class").contains("k-state-disabled")){
			int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
			selectWebElement(lastPageIcon.get(i));
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
			selectWebElement(firstPageIcon.get(i));
			int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
			if(nextnumber>pagenumber && pagenumber==previousnumber){status=true;}
		}else{
			System.out.println("previous and next page icon disabled");status=true;
		}
		return status;
	}

	public boolean verifyTotalNumberOfItemsPerPageDetails(int z){
		String item = items.get(z).getText();
		return item.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
	}

	public boolean verifyNumberOfItemsPerPage(int z) {
		boolean status = false;
		try {
			//  if (norecords.size() <= 0) {
			int item = Integer.valueOf(items.get(z).getText().split("of ")[1].split(" items")[0]);
			selectWebElement(pagerDropdown.get(z));
			Thread.sleep(1500);
			for (int i = 0; i < pageSizeListBox.size(); i++) {
				if(Integer.valueOf(pageSizeListBox.get(i).getText())>item){continue;}
				selectDropdownFromVisibleText(pageSizeListBox, pageSizeListBox.get(i).getText());
				waitForJqueryLoad(driver);
				int totalItems = Integer.valueOf(items.get(z).getText().split("of ")[1].split(" items")[0]);
				int pagersize = Integer.valueOf(pagerSize.get(z).getText());
				int pages = (totalItems % pagersize == 0) ? item / pagersize : item / pagersize+1;
				int totalRows=(gridContent.findElements(By.tagName("tr")).size()-1);
				selectWebElement(lastPageIcon.get(z));
				waitForJqueryLoad(driver);
				int lastPageNumber = Integer.valueOf(pageNumber.get(z).getText());
				if (item == totalItems && pages == lastPageNumber&&totalRows==pagersize) {
					status = true;
				} else {System.out.println(items+":"+totalItems+"\t"+pages+":"+lastPageNumber+"\t"+totalRows+":"+pagersize);
				status = false;
				break;
				}selectWebElement(pagerDropdown.get(z));Thread.sleep(1500);
			}
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} return status;
	}

	public boolean verifyExportToExcel(String filePath) {
		final File folder = new File(filePath);
		for (final File f : folder.listFiles()) {
			if (f.getName().startsWith("Fax Template")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "Fax Template");
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
		int item=Integer.valueOf(items.get(2).getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.get(2).getText());
		int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){
			waitUntilWebElementIsVisible(grid);
			List<WebElement> rows=grid.findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			String col=null;
			for(int i=1;i<rows.size();i++) {
				Map<String,String> map = new HashMap<String,String>();
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				for(int j=1;j<headers.size();j++) {
					/*if(headers.get(j).getText().equals("Last Changed On")){
						col=cols.get(j).getText().substring(0,10);
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
				nextPageIcon.get(2).click();
				waitForJqueryLoad(driver);}
		}
		return arr;
	}

	private void searchFaxTemplateRecord(String templatename) throws Exception {
		selectWebElement(searchLink);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		enterValueToTxtField(searchText,templatename);
		selectWebElement(searchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);			
	}

	public boolean ExporttoExcelWithoutData(FaxTemplateDetails details) throws Exception {
		searchFaxTemplateRecord(details.getTemplateName());
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(exporttoexcel);
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
			return false;
	}

	public void SortByAscending()  {
		selectWebElement(TemplateName.get(1));
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending(){
		selectWebElement(TemplateName.get(1));
		selectWebElement(TemplateName.get(1));
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void dragColumntoGroup(String columnname) {
		List<WebElement> rows = grid.findElements(By.tagName("tr"));
		List<WebElement> columnHeaders = rows.get(0).findElements(By.tagName("th"));
		for (WebElement ele : columnHeaders) {
			if (ele.getText().equals(columnname)) {
				Actions builder = new Actions(driver);
				Action dragAndDrop = builder.clickAndHold(ele).moveToElement(dragColumnDestination).release(dragColumnDestination).build();
				dragAndDrop.perform();
			}
		}
	}

	public boolean verifyDragColumntoGroup(String colname) {

		return (dragColumnDestination.getText().equals(colname));
	}

	public boolean verifyDatabase(String query) {
		List<Map<String,String>> database=database(query);
		System.out.println(database);
		List<Map<String,String>> UI=gettable1(); 
		System.out.println(UI);
		if(UI.equals(database))
			return true;
		else
			return false;
	}

	public List<Map<String, String>> gettable1() {
		int item=Integer.valueOf(items.get(0).getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.get(0).getText());
		int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){

			waitUntilWebElementIsVisible(checkerGrid);
			List<WebElement> rows=checkerGrid.findElements(By.tagName("tr"));
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
				map.remove("Preview");
				arr.add(map);
			}
			if(k!=pages)
			{
				nextPageIcon.get(0).click();
				waitForJqueryLoad(driver);}
		}
		return arr;
	}

	public boolean clearAll(FaxTemplateDetails details) throws Exception {
		selectWebElement(gridsearchLink);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		enterValueToTxtField(searchText,details.getTemplateName());
		selectWebElement(clearall);
		if(searchText.isEnabled())
			return true;
		else
			return false;
	}

	public boolean verifyclose() {
		selectWebElement(searchClose);
		if(approvedgridcontent.isDisplayed())
			return true;
		else
			return false;
	}

	public void searchwithoutextsearch(FaxTemplateDetails details) {
		selectWebElement(gridsearchLink);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		selectWebElement(searchBtn);	
		selectWebElement(searchClose);
	}

	public boolean getErrorMsg() {
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.size()>0)
			return false;
		else
			return true;
	}

	public String VerifyMessage() {
		waitForJqueryLoad(driver);
		if(successmsg.isDisplayed())
			return successmsg.getText();
		else{return errorMsg.get(0).getText();}
	}

	public String getSuccessMessage() {
		waitForJqueryLoad(driver);
		if(successmsg.isDisplayed())
			return successmsg.getText();
		else{return errorMsg.get(0).getText();}

	}

	private void searchFaxTemplateRecordApprovedData(String templateName) throws Exception {
		selectWebElement(gridsearchLink);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		selectWebElement(selectSearchColumn.get(1));
		Thread.sleep(1000);
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		enterValueToTxtField(searchText,templateName);
		selectWebElement(searchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(approvedgridcontent);		
	}

	public boolean verifyApprovedSectionData(FaxTemplateDetails details) throws Exception {
		searchFaxTemplateRecordApprovedData(details.getTemplateName());
		if(norecords.isDisplayed())
			return true; 
		else
			return false;
	}

	public boolean verifyclearsearch() {
		selectWebElement(clearsearch);
		if(approvedgridcontent.isDisplayed())
			return true;
		else
			return false;
	}

	public List<Map<String, String>> gettable() {
		int item=Integer.valueOf(items.get(2).getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.get(2).getText());
		int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){

			waitUntilWebElementIsVisible(gridContent);
			List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			for(int i=1;i<rows.size();i++) {
				Map<String,String> map = new HashMap<String,String>();
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				String col=null;
				for(int j=0;j<headers.size();j++){
					scrollToElement(headers.get(j));
					if(headers.get(j).getText().equals("Last Changed On")){
						col=cols.get(j).getText().substring(11);
					}
					else
						col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				map.remove("");
				map.remove("Preview");
				arr.add(map);
			}
			if(k!=pages)
			{
				nextPageIcon.get(2).click();
				waitForJqueryLoad(driver);}
		}
		return arr;
	}

	public boolean verifySearchIsNotEqualTo(String templatename) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("Template Name", templatename);
		selectWebElement(searchBtn1);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
		enterValueToTxtField(searchTextBox,templatename);		
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

	public boolean verifySearchContains(String templatename) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn1);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		enterValueToTxtField(searchTextBox,templatename);		
		selectWebElement(searchSearchBtn);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("Template Name").toUpperCase().contains(templatename.toUpperCase()))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}
	public boolean verifySearchDoesNotContains(String templatename) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn1);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not contain");
		enterValueToTxtField(searchTextBox,templatename);		
		selectWebElement(searchSearchBtn);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(!map1.get("Template Name").toLowerCase().contains(templatename.toLowerCase()))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}

	public boolean verifySearchStartsWith(String templatename) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn1);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
		enterValueToTxtField(searchTextBox,templatename);		
		selectWebElement(searchSearchBtn);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("Template Name").toLowerCase().startsWith(templatename.toLowerCase()))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}

	public boolean verifySearchEndsWith(String templatename) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn1);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
		enterValueToTxtField(searchTextBox,templatename);		
		selectWebElement(searchSearchBtn);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.get("Template Name").toUpperCase().endsWith(templatename.toUpperCase()))
				Status= true;
			else 
				Status= false;
		}
		return Status;
	}

	public boolean addCancelButton(FaxTemplateDetails FaxTemplateDetails) {
		selectWebElement(FaxTemplateTabs.get(1));
		selectWebElement(makeFaxTemplateChanges);
		String actualitems=items.get(2).getText();
		selectWebElement(addNewRecordBtn);
		waitForJqueryLoad(driver);
		selectWebElement(cancelBtn);
		if(actualitems.equals(items.get(2).getText()))
			return true;
		else
			return false;
	}



	public void addWithoutTemplateName(FaxTemplateDetails FaxTemplateDetails) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		selectWebElement(makeFaxTemplateChanges);
		Thread.sleep(1000);
		selectWebElement(addNewRecordBtn);
		waitForJqueryLoad(driver);
		selectWebElement(templateType);
		selectDropdownFromVisibleText(templateTypeListBox,"Custom Template");
		driver.switchTo().frame(iframe);
		selectWebElement(body.get(0));
		enterValueToTxtFieldWithoutClear(body.get(0),FaxTemplateDetails.getContent());
		driver.switchTo().defaultContent();
		selectWebElement(saveBtn);		
		selectWebElement(cancelBtn);
	}

	public void addWithoutTemplateType(FaxTemplateDetails FaxTemplateDetails) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		selectWebElement(makeFaxTemplateChanges);
		Thread.sleep(1000);
		selectWebElement(addNewRecordBtn);
		waitForJqueryLoad(driver);
		enterValueToTxtField(templateName,FaxTemplateDetails.getTemplateName());
		driver.switchTo().frame(iframe);
		selectWebElement(body.get(0));
		enterValueToTxtFieldWithoutClear(body.get(0),FaxTemplateDetails.getContent());
		driver.switchTo().defaultContent();
		selectWebElement(saveBtn);		
		selectWebElement(cancelBtn);
	}

	public void addWithoutContent(FaxTemplateDetails FaxTemplateDetails) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		selectWebElement(makeFaxTemplateChanges);
		selectWebElement(addNewRecordBtn);
		waitForJqueryLoad(driver);
		enterValueToTxtField(templateName,FaxTemplateDetails.getTemplateName());
		selectWebElement(templateType);
		selectDropdownFromVisibleText(templateTypeListBox,"Custom Template");
		selectWebElement(saveBtn);		
		selectWebElement(cancelBtn);
	}
	

	public void addFaxTemplateRecord(FaxTemplateDetails FaxTemplateDetails) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		selectWebElement(makeFaxTemplateChanges);
		Thread.sleep(1000);
		selectWebElement(addNewRecordBtn);
		Thread.sleep(1000);
		waitForJqueryLoad(driver);
		enterValueToTxtField(templateName,FaxTemplateDetails.getTemplateName());
		selectWebElement(templateType);
		selectDropdownFromVisibleText(templateTypeListBox,"Custom Template");
		selectWebElement(italicFormatText);
		selectWebElement(formatDropDown);
		selectWebElement(formatHeading1);
		driver.switchTo().frame(iframe);
		selectWebElement(body.get(0));
		enterValueToTxtFieldWithoutClear(body.get(0),FaxTemplateDetails.getContent());
		driver.switchTo().defaultContent();
		selectWebElement(InsertImage);
		waitUntilWebElementIsVisible(ImgPopup);
		enterValueToTxtFieldWithoutClear(searchImagePlaceHolder,FaxTemplateDetails.getImage());
		selectWebElement(searchIcon);
		try {
			if(emptyFolder.isDisplayed()) {
				selectWebElement(ImageUploadBtn);
				//Auto It script to load Image 
				FileUploader upload= new FileUploader();
				upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+FaxTemplateDetails.getImage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
		enterValueToTxtField(searchImagePlaceHolder,FaxTemplateDetails.getImage());
		selectWebElement(searchIcon);
		selectWebElement(Img1);
		selectWebElement(insertImg);
		Thread.sleep(3000);
		waitForJqueryLoad(driver);
		selectWebElement(saveBtn);	}		
	}
	
	public void addFaxTemplateforCoverPageTyprRecord(FaxTemplateDetails FaxTemplateDetails) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		selectWebElement(makeFaxTemplateChanges);
		Thread.sleep(1000);
		selectWebElement(addNewRecordBtn);
		Thread.sleep(2000);
		waitForJqueryLoad(driver);
		enterValueToTxtField(templateName,FaxTemplateDetails.getTemplateName());
		selectWebElement(templateType);
		selectDropdownFromVisibleText(templateTypeListBox,"Cover Page");
		selectWebElement(italicFormatText);
		selectWebElement(formatDropDown);
		selectWebElement(formatHeading1);
		driver.switchTo().frame(iframe);
		selectWebElement(body.get(0));
		enterValueToTxtFieldWithoutClear(body.get(0),FaxTemplateDetails.getContent());
		driver.switchTo().defaultContent();
		selectWebElement(InsertImage);
		waitUntilWebElementIsVisible(ImgPopup);
		enterValueToTxtFieldWithoutClear(searchImagePlaceHolder,FaxTemplateDetails.getImage());
		selectWebElement(searchIcon);
		try {
			if(emptyFolder.isDisplayed()) {
				selectWebElement(ImageUploadBtn);
				//Auto It script to load Image 
				FileUploader upload= new FileUploader();
				upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+FaxTemplateDetails.getImage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
		enterValueToTxtField(searchImagePlaceHolder,FaxTemplateDetails.getImage());
		selectWebElement(searchIcon);
		selectWebElement(Img1);
		selectWebElement(insertImg);
		Thread.sleep(3000);
		waitForJqueryLoad(driver);
		selectWebElement(saveBtn);	}		
	}

	public void selectRecord() {
		Map<String,String> map = new HashMap<>();
		waitUntilWebElementIsVisible(auditGridContent);
		List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
		List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
		selectWebElement(cols.get(0).findElement(By.id("isEnabled")));
	}

	public void Revert(String comments) throws Exception {
		selectWebElement(revertBtn);
		enterValueToTxtField(revertMakerComments,comments);
		selectWebElement(revertSubmitMakerComments);				
	}

	private Map<String, String> getFirstRowDatafromTable() {
		Map<String,String> map = new HashMap<>();
		waitUntilWebElementIsVisible(auditGridContent);
		List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
		List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
		List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
		for(int j=0;j<headers.size();j++){
			scrollToElement(headers.get(j));
			for(int i=0;i<3;i++) {													
				try{map.put(headers.get(j).getText(), cols.get(j).getText());break;}
				catch (Exception e){e.printStackTrace();}
			}
		}
		return map;
	}

	public boolean verifyStatus(String status) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Map<String,String> firstRowData=getFirstRowDatafromTable();
		return firstRowData.get("Status").equals(status);
	}

	private void searchSmsResponseTemplateRecordApprovedData(String templatename) throws Exception {
		selectWebElement(gridsearchLink);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		enterValueToTxtField(searchText,templatename);
		selectWebElement(searchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(approvedgridcontent);		
	}

	public void sendForAprroval(String comments) throws Exception {
		selectWebElement(sendForApprovalBtn);
		Thread.sleep(2000);
		enterValueToTxtFieldWithoutClear(makerComments, comments);
		selectWebElement(submitMakerComments);		
	}

	public void clickonReject(String comment) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectRecord();
		clickOn(rejectBtn);
		selectWebElement(checkerReason);
		enterValueToTxtFieldWithoutClear(checkerReason,comment);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clickOn(approveYesBtn);				
	}

	public boolean verifyMessage() {
		return(getSuccessMessage().contains("Record approved successfully. Request ID :"));

	}

	public boolean verifyReviewAuditTrail(String status, String comment) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boolean stat=false;
		Map<String,String> firstRowData=getFirstRowDatafromTable();
		if(firstRowData.get("Status").equals(status)){
			if(firstRowData.get("Checker Comments").equals(comment)){
				stat=true;
			}
			else{System.out.println("Data mismatch:"+firstRowData.get("Review Comments")+"\t"+comment);}
		}else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+status);}
		return stat;
	}

	private boolean verifyNewValues(FaxTemplateDetails details, Map<String, String> newvalues) {
		Boolean Status=false;
		if(newvalues.get("TemplateName").equals(details.getTemplateName()))
		{
			if(newvalues.get("TemplateType").equals(details.getTemplatetype()))
			{
				if(newvalues.get("FileName").equals(details.getFilename()))
					Status= true;
				else {System.out.println("FileName data mismatch");}
			}
			else {System.out.println("TemplateType data mismatch");}
		}
		else {System.out.println("TemplateName data mismatch");}
		return Status;	
	}

	public boolean verifyAuditTrail(FaxTemplateDetails FaxTemplateDetails, String Transaction, String Status) {
		boolean stat=false;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Map<String,String> firstRowData=getFirstRowDatafromTable();
        Map<String,String> popupRowData=getFirstRowDatafromPreviewPopup();


		if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
			if(firstRowData.get("Status").equalsIgnoreCase(Status)){
				if(firstRowData.get("Function Name").equalsIgnoreCase("Fax Template")){
					if(Transaction.equals("MakerCreate")){
						Map<String,String> newvalues=new HashMap<>();
						String[] d=popupRowData.get("New Values").split("\n");
						for(String e:d){
							String[]f=e.split(":",2);
							if(f.length>1){newvalues.put(f[0],f[1]);}
						}
						if(verifyNewValues(FaxTemplateDetails,newvalues)){
							stat=true;}
						else 
							stat=false;
					}
					else{System.out.println("Data mismatch");}
				}else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"Fax Template");}
			}else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}
		}else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}
		return stat;
	}

	private Map<String, String> getFirstRowDatafromPreviewPopup() {
		Map<String,String> map = new HashMap<>();
        waitUntilWebElementIsVisible(auditGridContent);
        List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
        List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
        List<WebElement> preview= cols.get(1).findElements(By.tagName("a"));
        preview.get(0).click();
        waitUntilWebElementIsVisible(tgrid);
        List<WebElement> gridrows=tgrid.findElements(By.tagName("tr"));
        List<WebElement> gridheaders = gridrows.get(0).findElements(By.tagName("th"));
        List<WebElement> gridcols=gridrows.get(1).findElements(By.tagName("td"));     
        for(int j=0;j<gridheaders.size();j++){
            scrollToElement(gridheaders.get(j));
            for(int i=0;i<gridcols.size();i++) {
            	System.out.println(gridheaders.get(j).getText());
                try{
                	map.put(gridheaders.get(j).getText(), gridcols.get(j).getText());
                break;
                }
                catch (Exception e){e.printStackTrace();}
            }
        }
        return map;
	}

	public boolean verifyAuditTrailUpdate(FaxTemplateDetails FaxTemplateDetails, String Transaction, String Status) {
		boolean stat=false;
		Map<String,String> firstRowData=getFirstRowDatafromTable();
        Map<String,String> popupRowData=getFirstRowDatafromPreviewPopup();


		if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
			if(firstRowData.get("Status").equalsIgnoreCase(Status)){
				if(firstRowData.get("Function Name").equalsIgnoreCase("Fax Template")){
					if(Transaction.equals("MakerUpdate")){
						Map<String,String> newvalues=new HashMap<>();
						String[] d=popupRowData.get("New Values").split("\n");
						for(String e:d){
							String[]f=e.split(":",2);
							if(f.length>1){newvalues.put(f[0],f[1]);}
						}
						if(verifyNewValues(FaxTemplateDetails,newvalues)){
							stat=true;}
						else 
							stat=false;
					}
					else{System.out.println("Data mismatch");}
				}else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"Fax Template");}
			}else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}
		}else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}
		return stat;
	}

	public boolean verifyAuditTrailDelete(FaxTemplateDetails FaxTemplateDetails, String Transaction,
			String Status) {
		boolean stat=false;
		Map<String,String> firstRowData=getFirstRowDatafromTable();
		if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
			if(firstRowData.get("Status").equalsIgnoreCase(Status)){
				if(firstRowData.get("Function Name").equalsIgnoreCase("Fax Template")){
					stat=true;
				}else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"RoleManagement");}
			}else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}
		}else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}
		return stat;
	}

	public void clickonApprove(String comment) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectRecord();
		clickOn(approveBtn);
		selectWebElement(checkerReason);
		enterValueToTxtFieldWithoutClear(checkerReason,comment);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clickOn(approveYesBtn);		
	}

	public boolean verifyApprovedSectionDataafterapproval(FaxTemplateDetails FaxTemplateDetails) throws Exception {
		searchFaxTemplateRecordApprovedData(FaxTemplateDetails.getTemplateName());
		if(rowdata.getText().equals(FaxTemplateDetails.getTemplateName()))
			return true;
		else
			return false;
	}

	public boolean EditCancel(FaxTemplateDetails details) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makeFaxTemplateChanges);
		Thread.sleep(1000);
		searchFaxTemplateRecord(details.getTemplateName());
		selectWebElement(editButton);
		waitForJqueryLoad(driver);
		selectWebElement(cancelBtn);
		if(editrowdata.get(1).getText().equals(details.getTemplateName()))
			return true;
		else
			return false;
	}

	public void EditFaxTemplateRecord(FaxTemplateDetails FaxTemplateDetails) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		selectWebElement(makeFaxTemplateChanges);
		Thread.sleep(1000);
		searchFaxTemplateRecord(FaxTemplateDetails.getTemplateName());
		selectWebElement(editButton);
		waitForJqueryLoad(driver);
		selectWebElement(italicFormatText);
		Thread.sleep(1000);
		selectWebElement(insertUnorderdList);
		driver.switchTo().frame(iframe);
		selectWebElement(body.get(0));
		enterValueToTxtField(body.get(0),FaxTemplateDetails.getUpdatedContent());
		driver.switchTo().defaultContent();
		enterValueToTxtFieldWithoutClear(modifyResonTextBox,FaxTemplateDetails.getModifyReason());
		selectWebElement(saveBtn);
	}
	
	public void EditCoverPageTypeFaxTemplateRecord(FaxTemplateDetails FaxTemplateDetails) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		selectWebElement(makeFaxTemplateChanges);
		Thread.sleep(1000);
		searchFaxTemplateRecord(FaxTemplateDetails.getTemplateName());
		selectWebElement(editButton);
		waitForJqueryLoad(driver);
		selectWebElement(italicFormatText);
		Thread.sleep(1000);
		selectWebElement(insertUnorderdList);
		driver.switchTo().frame(iframe);
		selectWebElement(body.get(0));
		enterValueToTxtField(body.get(0),FaxTemplateDetails.getUpdatedContent());
		driver.switchTo().defaultContent();
		enterValueToTxtFieldWithoutClear(modifyResonTextBox,FaxTemplateDetails.getModifyReason());
		selectWebElement(saveBtn);
	}

	public void EditFaxTemplateRecordWithOutModifyReson(FaxTemplateDetails FaxTemplateDetails) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		selectWebElement(makeFaxTemplateChanges);
		searchFaxTemplateRecord(FaxTemplateDetails.getTemplateName());
		selectWebElement(editButton);
		waitForJqueryLoad(driver);
		driver.switchTo().frame(iframe);
		selectWebElement(body.get(0));
		enterValueToTxtField(body.get(0),FaxTemplateDetails.getUpdatedContent());
		driver.switchTo().defaultContent();
		selectWebElement(saveBtn);
	}

	public boolean DeleteCancel(FaxTemplateDetails details) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		selectWebElement(makeFaxTemplateChanges);
		searchFaxTemplateRecord(details.getTemplateName());
		selectWebElement(deleteButton);
		waitForJqueryLoad(driver);
		selectWebElement(nobtn);
		if(editrowdata.get(1).getText().equals(details.getTemplateName()))
			return true;
		else
			return false;
	}

	public void DeleteRecordWithoutDeleteReason(FaxTemplateDetails details) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		selectWebElement(makeFaxTemplateChanges);
		Thread.sleep(1000);
		searchFaxTemplateRecord(details.getTemplateName());
		selectWebElement(deleteButton);
		selectWebElement(deleteReasonTextBox);
		selectWebElement(yesbtn);		
		selectWebElement(nobtn);
	}

	public void DeleteFaxTemplateRecord(FaxTemplateDetails details) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		selectWebElement(makeFaxTemplateChanges);
		Thread.sleep(1000);
		searchFaxTemplateRecord(details.getTemplateName());
		selectWebElement(deleteButton);
		selectWebElement(deleteReasonTextBox);
		enterValueToTxtFieldWithoutClear(deleteReasonTextBox,details.getDeleteReason());
		selectWebElement(yesbtn);				
	}
	
	public void DeleteCoverPageTypeFaxTemplateRecord(FaxTemplateDetails details) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		selectWebElement(makeFaxTemplateChanges);
		Thread.sleep(1000);
		searchFaxTemplateRecord(details.getTemplateName());
		selectWebElement(deleteButton);
		selectWebElement(deleteReasonTextBox);
		enterValueToTxtFieldWithoutClear(deleteReasonTextBox,details.getDeleteReason());
		selectWebElement(yesbtn);				
	}

	public void addAutoACKTypeFaxTemplateRecord(FaxTemplateDetails FaxTemplateDetails) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		selectWebElement(makeFaxTemplateChanges);
		Thread.sleep(1000);
		selectWebElement(addNewRecordBtn);
		Thread.sleep(1000);
		waitForJqueryLoad(driver);
		enterValueToTxtField(templateName,FaxTemplateDetails.getTemplateName());
		selectWebElement(templateType);
		selectDropdownFromVisibleText(templateTypeListBox,"Auto ACK");
		clickOnUsingActionClass(selectFile);
		//Auto It script to load wave file
		FileUploader upload= new FileUploader();
		upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+FaxTemplateDetails.getContent());
		selectWebElement(saveBtn);				    		
	}

	public void EditAutoAckTemplateTypeRecord(FaxTemplateDetails details) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		selectWebElement(makeFaxTemplateChanges);
		Thread.sleep(1000);
		searchFaxTemplateRecord(details.getTemplateName());
		selectWebElement(editButton);
		waitForJqueryLoad(driver);
		clickOnUsingActionClass(selectFile);
		//Auto It script to load wave file
		FileUploader upload= new FileUploader();
		upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getUpdatedContent());
		enterValueToTxtFieldWithoutClear(modifyResonTextBox,details.getModifyReason());
		selectWebElement(saveBtn);
	}

	public void EditAutoAckTemplateTypeRecordWithOutModifyReason(FaxTemplateDetails details) throws Exception {
		selectWebElement(FaxTemplateTabs.get(1));
		selectWebElement(makeFaxTemplateChanges);
		Thread.sleep(1000);
		searchFaxTemplateRecord(details.getTemplateName());
		selectWebElement(editButton);
		waitForJqueryLoad(driver);
		clickOnUsingActionClass(selectFile);
		//Auto It script to load wave file
		FileUploader upload= new FileUploader();
		upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getUpdatedContent());
		selectWebElement(saveBtn);
	}

}










