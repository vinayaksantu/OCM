package com.tetherfi.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.tetherfi.model.email.EmailTemplatesDetails;


public class EmailTemplatesPage extends BasePage {
	public EmailTemplatesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".ibox-title h5")
	private WebElement emailTemplates;

	@FindBy(xpath="//i[@class='fas fa-envelope-open']")
	private WebElement ETImg;

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
	private List<WebElement> EmailTemplatesTabs;

	@FindBy(css="#tGrid th[data-role='columnsorter']")
	private List<WebElement> auditTrailTableHeaders;

	@FindBy(xpath="//button[@id='makeChanges']")
	private WebElement makechangesBtn;

	@FindBy(xpath="//button[@id='create']")
	private WebElement addNewEmailTemplateChangesBtn;

	@FindBy(id="goToAuditTrail")
	private WebElement goBackBtn;

	@FindBy(css=".k-grid-cancel")
	private WebElement cancelBtn;

	@FindBy(css="a[aria-label='Go to the next page']")
	private List<WebElement> nextPageIcon;

	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupby;

	@FindBy(css="a[aria-label='Go to the first page']")
	private List<WebElement> firstPageIcon;

	@FindBy(css="a[aria-label='Go to the previous page']")
	private List<WebElement> previousPageIcon;

	@FindBy(css=".k-pager-numbers .k-state-selected")
	private List<WebElement> pageNumber;

	@FindBy(css="a[aria-label='Go to the last page']")
	private List<WebElement> lastPageIcon;

	@FindBy(css=".k-pager-sizes .k-icon")
	private List<WebElement> pagerDropdown;

	@FindBy(css=".k-animation-container ul li")
	private List<WebElement> pageSizeListBox;

	@FindBy(css="th a[class='k-header-column-menu']")
	private List<WebElement> headersDropdown;

	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;

	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;		

	@FindBy(xpath="//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")
	private List<WebElement> tablerecord;

	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private List<WebElement> items;

	@FindBy(xpath="//span[@class='k-icon k-i-arrow-60-right k-menu-expand-arrow']")
	private WebElement coloumnarrow;

	@FindBy(id="grid")
	private WebElement auditGridContent;

	@FindBy(css=".k-pager-sizes .k-input")
	private List<WebElement> pagerSize;

	@FindBy(id = "drillGrid")
	private WebElement grid;

	@FindBy(css = "#drillGrid .k-grouping-header")
	private WebElement dragColumnDestination;

	@FindBy(css="#gridDiv .search-link")
	private WebElement gridsearchLink;

	@FindBy(xpath="//div[@class='k-button k-upload-button']")
	private WebElement uploadfile;

	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;

	@FindBy(css="#tcheckerGrid .k-grid-content")
	private WebElement approvedgridcontent;

	@FindBy(xpath="//tbody/tr/td[2]")
	private WebElement rowdata;

	@FindBy(css="#drillGrid tbody tr td")
	private List<WebElement> editrowdata;

	@FindBy(id="checkerGrid")
	private WebElement checkerGrid;

	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearall;

	@FindBy(xpath="//button[text()='Close']")
	private WebElement searchClose;

	@FindBy(xpath="//i[@class='fas fa-sync fa-spin']")
	private WebElement clearsearch;

	@FindBy(id="tabstripfaxtemplateMakerChecker")
	private List<WebElement> makerCheckerTab;

	@FindBy(id="sendForApproval")
	private WebElement sendForApprovalBtn;

	@FindBy(id="undoChanges")
	private WebElement revertBtn;

	@FindBy(id="MakerComments")
	private WebElement makerComments;

	@FindBy(id="undoChangesMakerComments")
	private WebElement revertMakerComments;

	@FindBy(id="submitMakerComment")
	private WebElement submitMakerComments;

	@FindBy(id="submitUndoChangesMakerComment")
	private WebElement revertSubmitMakerComments;

	@FindBy(id="Approve")
	private WebElement approveBtn;

	@FindBy(id="Reject")
	private WebElement rejectBtn;

	@FindBy(id="CheckerReason")
	private WebElement checkerReason;

	@FindBy(id="approveButton")
	private WebElement approveYesBtn;

	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;

	@FindBy(css="#drillGrid th[data-role='columnsorter']")
	private List<WebElement> makerTableHeaders;

	@FindBy(css="#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;

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
	private WebElement popupSearchBtn;

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

	@FindBy(css="#drillGrid .k-grid-content")
	private WebElement gridContent;

	@FindBy(xpath="//a[text()='Template Name']")
	private List<WebElement> templateName;

	@FindBy(css=".toast-message")
	private WebElement successmsg;

	@FindBy(id="create")
	private WebElement addEmailTemplatesRecordBtn;

	@FindBy(id="makeChanges")
	private WebElement makeEmailTemplatesChanges;

	@FindBy(css=".k-edit-buttons .k-grid-update")
	private WebElement editFormSaveBtn;

	@FindBy(xpath="//input[@id='TemplateName']")
	private WebElement templatename;

	@FindBy(xpath="//input[@id='Subject']")
	private WebElement subject;

	@FindBy(css = "span[aria-owns='Enabled_listbox']")
	private WebElement enabledTextbox;

	@FindBy(css= "ul[id='Enabled_listbox'] li")
	private List<WebElement> enabledListbox;

	@FindBy(css = "span[aria-owns='Type_listbox']")
	private WebElement typeTextbox;

	@FindBy(css= "ul[id='Type_listbox'] li")
	private List<WebElement> typeListbox;

	@FindBy(xpath="//iframe")
	private WebElement iframe;

	@FindBy(xpath="//body")
	private WebElement body;


	public void selectEmailTemplatesAuditTrailTab() {
		selectWebElement(EmailTemplatesTabs.get(1));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public boolean isEmailTemplatesPageDisplayed() {
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		return emailTemplates.isEnabled();
	}

	public boolean VerifyLogo() {
		waitForLoad(driver);
		if(ETImg.isDisplayed())
			return true;
		else
			return false;	
	}

	public boolean maximizewindow() {
		waitForLoad(driver);
		selectWebElement(maximize);
		waitForJqueryLoad(driver);
		if(fullscreen.isEnabled())
			return true;
		else 
		{return false;}
	}

	public boolean minimizewindow() {
		waitForLoad(driver);
		selectWebElement(minimize);
		waitForJqueryLoad(driver);
		if(header.isDisplayed())
			return true;
		else 
			return false; 
	}
	public boolean verifyApprovedDataTableHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("Template Name","Subject","Enabled","Type","Last Changed By","Last Changed On"));
		ArrayList Actual = getHeadersfromTable(approvedDataTableHeaders);
		System.out.println(Actual);
		Collections.sort(Expected);
		Collections.sort(Actual);
		return Actual.equals(Expected);
	}

	private ArrayList getHeadersfromTable(List<WebElement> e){
		ArrayList header=new ArrayList();
		for(int j=0;j<e.size();j++){
			scrollToElement(e.get(j));
			if(!e.get(j).getText().equals("")){header.add(e.get(j).getText());}
		}
		return header;
	}

	public boolean verifyAuditTrailDataTableHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList(" ","Request Id", "Transaction", "Function", "Status", "User Id", "Submission DateTime", "Maker Comments", "Old Values", "New Values", "Reviewed By","Review DateTime", "Checker Comments"));
		ArrayList Actual = getHeadersfromTable(auditTrailTableHeaders);
		System.out.println(Actual);
		Collections.sort(Expected);Collections.sort(Actual);
		return Actual.equals(Expected);
	}

	public void selectMakeEmailTemplateChanges() {
		waitForLoad(driver);
		selectWebElement(makechangesBtn);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean verifyAddNewEmailTemplateChangesRecordButton() {
		waitForLoad(driver);
		return addNewEmailTemplateChangesBtn.isEnabled();
	}
	public boolean verifyGoBackButton() {
		waitForLoad(driver);
		return goBackBtn.isEnabled();
	}
	public void selectGoBackButton() throws Exception
	{	waitForLoad(driver);
	selectWebElement(goBackBtn);
	Thread.sleep(2000);
	}

	public boolean verifyExportToExcelButton() {
		waitForLoad(driver);
		return exporttoexcel.isEnabled();
	}
	public boolean verifyMakerDataTableHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("Template Name","Subject","Enabled","Type","Last Changed By","Last Changed On"));
		ArrayList Actual = getHeadersfromTable(makerTableHeaders);
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

	public boolean verifycolumnsHeaderDisbaled() {
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
				int totalRows=(gridContent.findElements(By.tagName("tr")).size());
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
	public boolean verifyTotalNumberOfItemsPerPageDetails(int z){
		String item = items.get(z).getText();
		return item.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
	}

	public boolean verifyExportToExcel(String filePath) {
		final File folder = new File(filePath);
		for (final File f : folder.listFiles()) {
			if (f.getName().startsWith("Email Templates")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "Email Templates");
		return Status;
	}    

	public boolean verifyexportToExcelSheet(List<Map<String, String>> maplist) {
		waitForLoad(driver);
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
		waitForLoad(driver);
		return (dragColumnDestination.getText().equals(colname));
	}

	public boolean ExporttoExcelWithoutData(EmailTemplatesDetails details) throws Exception {
		searchEmailTemplateRecord(details.getTemplateName());
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(exporttoexcel);
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
			return false;
	}
	public void searchEmailTemplateRecord(String templatename) throws Exception  {
		waitForLoad(driver);
		selectWebElement(searchLink);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(2000);
		selectWebElement(selectSearchColumn.get(1));	
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		selectWebElement(searchText);
		enterValueToTxtField(searchText,templatename);
		selectWebElement(popupSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
	}
	public void SortByAscending() {
		waitForLoad(driver);
		selectWebElement(templateName.get(1));
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		waitForLoad(driver);
		selectWebElement(templateName.get(1));
		selectWebElement(templateName.get(1));
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void searchwithoutextsearch(EmailTemplatesDetails details) {
		waitForLoad(driver);
		selectWebElement(gridsearchLink);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		selectWebElement(popupSearchBtn);	
		selectWebElement(searchClose);
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
	public boolean verifyclearsearch() {
		waitForLoad(driver);
		selectWebElement(clearsearch);
		if(approvedgridcontent.isDisplayed())
			return true;
		else
			return false;
	}
	public boolean verifyApprovedSectionData(EmailTemplatesDetails details) throws Exception {
		waitForLoad(driver);
		searchEmailTemplateApprovedData(details.getTemplateName());
		if(norecords.isDisplayed())
			return true; 
		else
			return false;
	}
	private void searchEmailTemplateApprovedData(String tempName) throws Exception {
		waitForLoad(driver);
		selectWebElement(gridsearchLink);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		selectWebElement(searchText);
		enterValueToTxtField(searchText,tempName);
		selectWebElement(popupSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(approvedgridcontent);		
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
				nextPageIcon.get(0).click();
				waitForJqueryLoad(driver);}
		}
		return arr;
	}

	public boolean addCancelButton(EmailTemplatesDetails UserDetails) throws Exception {
		waitForLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		selectWebElement(makechangesBtn);
		String actualitems=items.get(2).getText();
		selectWebElement(addEmailTemplatesRecordBtn);
		waitForJqueryLoad(driver);
		selectWebElement(cancelBtn);
		Thread.sleep(2000);
		if(actualitems.equals(items.get(2).getText()))
			return true;
		else
			return false;
	}
	public boolean clearAll(EmailTemplatesDetails details) throws Exception {
		waitForLoad(driver);
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
		waitForLoad(driver);
		selectWebElement(searchClose);
		if(approvedgridcontent.isDisplayed())
			return true;
		else
			return false;
	}
	public void addEmptyRecord(EmailTemplatesDetails details) throws Exception {
		waitForLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		selectWebElement(makechangesBtn);
		selectWebElement(addEmailTemplatesRecordBtn);
		btnClick(editFormSaveBtn);
		selectWebElement(cancelBtn);		
	}

	public void addRecord(EmailTemplatesDetails details) throws Exception {
		waitForLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		selectWebElement(makechangesBtn);
		selectWebElement(addEmailTemplatesRecordBtn);
		selectWebElement(templatename);		
		enterValueToTxtField(templatename,details.getTemplateName());
		selectWebElement(subject);		
		enterValueToTxtField(subject,details.getSubject());
		selectWebElement(enabledTextbox);
		selectDropdownFromVisibleText(enabledListbox,details.getEnabled());
		selectWebElement(typeTextbox);
		selectDropdownFromVisibleText(typeListbox,details.getType());
		driver.switchTo().frame(iframe);
		enterValueToTxtField(body,details.getText());
		driver.switchTo().defaultContent();
		btnClick(editFormSaveBtn);
		//selectWebElement(cancelBtn);		
	}
	public void addRecordwithoutTemplateName(EmailTemplatesDetails details) throws Exception {
		waitForLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		selectWebElement(makechangesBtn);
		selectWebElement(addEmailTemplatesRecordBtn);
		selectWebElement(subject);		
		enterValueToTxtField(subject,details.getSubject());
		selectWebElement(enabledTextbox);
		selectDropdownFromVisibleText(enabledListbox,details.getEnabled());
		selectWebElement(typeTextbox);
		selectDropdownFromVisibleText(typeListbox,details.getType());		
		btnClick(editFormSaveBtn);
		selectWebElement(cancelBtn);		
	}
	public void addRecordwithoutSubject(EmailTemplatesDetails details) throws Exception {
		waitForLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		selectWebElement(makechangesBtn);
		selectWebElement(addEmailTemplatesRecordBtn);
		selectWebElement(templatename);		
		enterValueToTxtField(templatename,details.getTemplateName());
		selectWebElement(enabledTextbox);
		selectDropdownFromVisibleText(enabledListbox,details.getEnabled());
		selectWebElement(typeTextbox);
		selectDropdownFromVisibleText(typeListbox,details.getType());		
		btnClick(editFormSaveBtn);
		selectWebElement(cancelBtn);		
	}

	public void addRecordwithoutEnabledfield(EmailTemplatesDetails details) throws Exception {
		waitForLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		selectWebElement(makechangesBtn);
		selectWebElement(addEmailTemplatesRecordBtn);
		selectWebElement(templatename);		
		enterValueToTxtField(templatename,details.getTemplateName());
		selectWebElement(subject);		
		enterValueToTxtField(subject,details.getSubject());
		selectWebElement(typeTextbox);
		selectDropdownFromVisibleText(typeListbox,details.getType());		
		btnClick(editFormSaveBtn);
		selectWebElement(cancelBtn);		
	}
	public void addRecordwithoutTypefield(EmailTemplatesDetails details) throws Exception {
		waitForLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		selectWebElement(makechangesBtn);
		selectWebElement(addEmailTemplatesRecordBtn);
		selectWebElement(templatename);		
		enterValueToTxtField(templatename,details.getTemplateName());
		selectWebElement(subject);		
		enterValueToTxtField(subject,details.getSubject());
		selectWebElement(enabledTextbox);
		selectDropdownFromVisibleText(enabledListbox,details.getEnabled());	
		btnClick(editFormSaveBtn);
		selectWebElement(cancelBtn);		
	}
	public void addRecordTemplateName(EmailTemplatesDetails details) throws Exception {
		waitForLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		selectWebElement(makechangesBtn);
		selectWebElement(addEmailTemplatesRecordBtn);
		selectWebElement(templatename);		
		enterValueToTxtField(templatename,details.getTemplateName());		
		btnClick(editFormSaveBtn);
		selectWebElement(cancelBtn);		
	}
	public void addRecordwithSubjectOnly(EmailTemplatesDetails details) throws Exception {
		waitForLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		selectWebElement(makechangesBtn);
		selectWebElement(addEmailTemplatesRecordBtn);
		selectWebElement(subject);		
		enterValueToTxtField(subject,details.getSubject());		
		btnClick(editFormSaveBtn);
		selectWebElement(cancelBtn);		
	}
	public void addRecordwithEnableOnly(EmailTemplatesDetails details) throws Exception {
		waitForLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		selectWebElement(makechangesBtn);
		selectWebElement(addEmailTemplatesRecordBtn);
		selectWebElement(enabledTextbox);
		selectDropdownFromVisibleText(enabledListbox,details.getEnabled());		
		btnClick(editFormSaveBtn);
		selectWebElement(cancelBtn);		
	}
	public void addRecordwithTypeOnly(EmailTemplatesDetails details) throws Exception {
		waitForLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		selectWebElement(makechangesBtn);
		selectWebElement(addEmailTemplatesRecordBtn);
		selectWebElement(typeTextbox);
		selectDropdownFromVisibleText(typeListbox,details.getType());
		driver.switchTo().frame(iframe);
		enterValueToTxtField(body,details.getText());
		driver.switchTo().defaultContent();
		btnClick(editFormSaveBtn);
		selectWebElement(cancelBtn);		
	}
	public void addRecordwithTempandSub(EmailTemplatesDetails details) throws Exception {
		waitForLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		selectWebElement(makechangesBtn);
		selectWebElement(addEmailTemplatesRecordBtn);
		selectWebElement(templatename);		
		enterValueToTxtField(templatename,details.getTemplateName());
		selectWebElement(subject);		
		enterValueToTxtField(subject,details.getSubject());
		btnClick(editFormSaveBtn);
		selectWebElement(cancelBtn);		
	}
	public void selectRecord() {
		waitForLoad(driver);
		Map<String,String> map = new HashMap<>();
		waitUntilWebElementIsVisible(auditGridContent);
		List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
		List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
		selectWebElement(cols.get(0).findElement(By.id("isEnabled")));
	}

	public void sendForAprroval(String comments) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(sendForApprovalBtn);
		enterValueToTxtFieldWithoutClear(makerComments, comments);
		selectWebElement(submitMakerComments);		
	}

	public void Revert(String comments) throws Exception {
		waitForLoad(driver);
		selectWebElement(revertBtn);
		enterValueToTxtField(revertMakerComments,comments);
		selectWebElement(revertSubmitMakerComments);				
	}
	public boolean verifyStatus(String status) {
		waitForLoad(driver);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Map<String,String> firstRowData=getFirstRowDatafromTable();
		return firstRowData.get("Status").equals(status);
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
				try{map.put(headers.get(j).getText(), cols.get(j).getText());break;}catch (Exception e){e.printStackTrace();}
			}
		}
		return map;
	}


	public void clickonReject(String comment) throws Exception {
		waitForLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
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
		waitForLoad(driver);
		return(getSuccessMessage().contains("Record approved successfully. Request ID :"));

	}
	public boolean verifyReviewAuditTrail(String status, String comment) {
		waitForLoad(driver);
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
	public boolean verifyAuditTrail(EmailTemplatesDetails details, String Transaction, String Status) {
		boolean stat=false;
		Map<String,String> firstRowData=getFirstRowDatafromTable();
		if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
			if(firstRowData.get("Status").equalsIgnoreCase(Status)){
				if(firstRowData.get("Function Name").equalsIgnoreCase("Email Templates")){
					if(Transaction.equals("MakerCreate")){
						Map<String,String> newvalues=new HashMap<>();
						String[] d=firstRowData.get("New Values").split("\n");
						for(String e:d){
							String[]f=e.split(":",2);
							if(f.length>1){newvalues.put(f[0],f[1]);}
						}
						if(verifyNewValues(details,newvalues)){
							stat=true;}
						else 
							stat=false;
					}
					else{System.out.println("Data mismatch");}
				}else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"Template Name");}
			}else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}
		}else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}
		return stat;
	}
	private boolean verifyNewValues(EmailTemplatesDetails details, Map<String, String> newvalues) {
		Boolean Status=false;
		if(newvalues.get("TemplateName").equals(details.getTemplateName()))
		{
			Status=true;
		}
		else {System.out.println("TemplateName data mismatch");}
		return Status;
	}
	public void clickonApprove(String comment) throws Exception {
		selectWebElement(EmailTemplatesTabs.get(1));
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
	public boolean verifyApprovedSectionDataafterapproval(EmailTemplatesDetails details) throws Exception {
		searchEmailTemplateRecordApprovedData(details.getTemplateName());
		if(rowdata.getText().equals(details.getTemplateName()))
			return true;
		else
			return false;
	}
	private void searchEmailTemplateRecordApprovedData(String tempName) throws Exception {
		waitForLoad(driver);
		selectWebElement(gridsearchLink);
		selectWebElement(selectSearchColumn.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		selectWebElement(selectSearchColumn.get(1));
		selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		enterValueToTxtField(searchText,tempName);
		selectWebElement(popupSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(approvedgridcontent);		
	}
	public boolean EditCancel(EmailTemplatesDetails details) throws Exception {
		waitForLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makechangesBtn);
		Thread.sleep(1000);
		searchEmailTemplateRecord(details.getTemplateName());
		selectWebElement(editBtn);
		waitForJqueryLoad(driver);
		selectWebElement(cancelBtn);
		if(editrowdata.get(1).getText().equals(details.getTemplateName()))
			return true;
		else
			return false;
	}
	public void editEmailTemplatesRecord(EmailTemplatesDetails details) throws Exception {
		waitForLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		selectWebElement(makechangesBtn);
		searchEmailTemplateRecord(details.getTemplateName());
		selectWebElement(editBtn);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(subject);		
		enterValueToTxtField(subject,details.getUpdatedSubject());
		driver.switchTo().frame(iframe);
		enterValueToTxtField(body,details.getUpdatedtext());
		driver.switchTo().defaultContent();
		enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
		btnClick(editFormSaveBtn);
	}

	public void EditRecordWithoutModifyReason(EmailTemplatesDetails details) throws Exception {
		waitForLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		selectWebElement(makechangesBtn);
		searchEmailTemplateRecord(details.getTemplateName());
		selectWebElement(editBtn);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(subject);		
		enterValueToTxtField(subject,details.getUpdatedSubject());
		btnClick(editFormSaveBtn);			
	}
	public boolean verifyAuditTrailUpdate(EmailTemplatesDetails details, String Transaction,String Status) {
		boolean stat=false;
		Map<String,String> firstRowData=getFirstRowDatafromTable();
		if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
			if(firstRowData.get("Status").equalsIgnoreCase(Status)){
				if(firstRowData.get("Function Name").equalsIgnoreCase("Email Templates")){
					if(Transaction.equals("MakerUpdate")){
						Map<String,String> newvalues=new HashMap<>();
						String[] d=firstRowData.get("New Values").split("\n");
						for(String e:d){
							String[]f=e.split(":",2);
							if(f.length>1){newvalues.put(f[0],f[1]);}
						}
						if(verifyUpdatedNewValues(details,newvalues)){
							stat=true;}
						else 
							stat=false;
					}
					else{System.out.println("Data mismatch");}
				}else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"Email Templates");}
			}else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}
		}else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}
		return stat;
	}

	private boolean verifyUpdatedNewValues(EmailTemplatesDetails details, Map<String, String> newvalues) {
		Boolean Status=false;
		if(newvalues.get("Subject").equals(details.getUpdatedSubject()))

		{
			Status=true;
		}
		else {System.out.println("Subject data mismatch");}
		return Status;
	}
	public boolean DeleteCancel(EmailTemplatesDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makechangesBtn);
		Thread.sleep(1000);
		searchEmailTemplateRecord(details.getTemplateName());
		selectWebElement(deleteBtn);
		waitForJqueryLoad(driver);
		selectWebElement(noBtn);
		if(editrowdata.get(1).getText().equals(details.getTemplateName()))
			return true;
		else
			return false;
	}

	public void DeleteEmailTemplatesRecord(EmailTemplatesDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makechangesBtn);
		Thread.sleep(1000);
		searchEmailTemplateRecord(details.getTemplateName());
		Thread.sleep(1000);
		selectWebElement(deleteBtn);
		Thread.sleep(1000);
		selectWebElement(deleteReasonTextBox);
		enterValueToTxtFieldWithoutClear(deleteReasonTextBox,details.getDeleteReason());
		selectWebElement(yesBtn);				
	}

	public boolean verifyAuditTrailDelete(EmailTemplatesDetails Details, String Transaction,String Status) {
		boolean stat=false;
		Map<String,String> firstRowData=getFirstRowDatafromTable();
		if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
			if(firstRowData.get("Status").equalsIgnoreCase(Status)){
				if(firstRowData.get("Function Name").equalsIgnoreCase("Email Templates")){
					stat=true;
				}else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"Email Templates");}
			}else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}
		}else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}
		return stat;
	}

	public void DeleteRecordWithoutModifyReason(EmailTemplatesDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(EmailTemplatesTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makechangesBtn);
		Thread.sleep(1000);
		searchEmailTemplateRecord(details.getTemplateName());
		Thread.sleep(1000);
		selectWebElement(deleteBtn);
		Thread.sleep(1000);
		selectWebElement(deleteReasonTextBox);
		selectWebElement(yesBtn);		
		//selectWebElement(noBtn);
	}
}