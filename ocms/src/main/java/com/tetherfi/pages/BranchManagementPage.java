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

import com.tetherfi.model.ivr.BranchManagementDetails;
import com.tetherfi.utility.FileUploader;

public class BranchManagementPage extends BasePage {

	public BranchManagementPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css=".ibox-title h5")
    private WebElement branchManagement;
	
	@FindBy(xpath = "//div[@id='tabstripAgentMakerChecker']/ul/li")
    private List<WebElement> navTabs;
	
	@FindBy(xpath="//button[text()='Make Branch Management Changes']")
	private WebElement makeBranchManageChanges;
	
	@FindBy(css="#create")
	private WebElement addNewBranchManageRecordBtn;
	
	@FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;
	
	@FindBy(css = ".k-grid-edit")
    private WebElement editButton;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
	
	@FindBy(xpath="//i[@class='fa fa-envelope']")
	private WebElement BMImg;
	    
	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;
		
	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;
		 
	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
	@FindBy(css="#checkerGrid th[data-role='columnsorter']")
	private List<WebElement> approvedDataTableHeaders;

	@FindBy(css="#tGrid th[data-role='columnsorter']")
	private List<WebElement> auditTrailTableHeaders;
	
    @FindBy(css="ul[class='k-tabstrip-items k-reset'] li")
    private List<WebElement> BranchManagementTabs;
    
    @FindBy(id="goToAuditTrail")
    private WebElement goBackBtn;
    
    @FindBy(id="makeChanges")
    private WebElement makeBranchManagementChanges;
    
    @FindBy(css="#drillGrid th[data-role='columnsorter']")
    private List<WebElement> makerTableHeaders;
    
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
    
    @FindBy(id="navbarheader")
	private WebElement header;
    
    @FindBy(xpath="//span[@class='k-icon k-i-arrow-60-right k-menu-expand-arrow']")
    private WebElement coloumnarrow;
    
    @FindBy(css=".k-pager-sizes .k-input")
    private List<WebElement> pagerSize;
    
    @FindBy(css="#drillGrid .k-grid-content")
    private WebElement gridContent;
    
    @FindBy(id = "drillGrid")
    private WebElement grid;

    @FindBy(css = "#drillGrid .k-grouping-header")
    private WebElement dragColumnDestination;
    
    @FindBy(id="tGrid")
    private WebElement auditGridContent;
    
    @FindBy(xpath="//a[text()='Location']")
    private List<WebElement> Location;
    
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
    
    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css="#toast-container .toast-error .toast-message")
    private List<WebElement> errorMsg;

    @FindBy(css="#gridDiv2 .search-link")
    private WebElement searchLink;
    
    @FindBy(css="#gridDiv .search-link")
    private WebElement gridsearchLink;
    
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
    
    @FindBy(xpath="//button[text()='Close']")
    private WebElement searchClose;
    
    @FindBy(xpath="//i[@class='fas fa-sync fa-spin']")
    private WebElement clearsearch;
    
    @FindBy(css="#tcheckerGrid .k-grid-content")
    private WebElement approvedgridcontent;
    
    @FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
    
    @FindBy(css="span[aria-owns='MainLines_listbox']")
    private WebElement mainLinesDropDown;
    
    @FindBy(css="ul[id='MainLines_listbox'] li")
    private List<WebElement> mainLinesListbox;
    
    @FindBy(css="span[aria-owns='SubLines_listbox']")
    private WebElement subLinesDropDown;
    
    @FindBy(css="ul[id='SubLines_listbox'] li")
    private List<WebElement> subLinesListbox;
    
    @FindBy(id="Location")
    private WebElement LocationTextbox;
    
    @FindBy(css="span[aria-owns='BranchType_listbox']")
    private WebElement branchTypeDropDown;
    
    @FindBy(css="ul[id='BranchType_listbox'] li")
    private List<WebElement> branchTypeListbox;
    
    @FindBy(xpath="//div[@class='k-button k-upload-button' and @aria-label='Select files...']")
    private List<WebElement> selectFiles;
    
    @FindBy(xpath="//input[@placeholder='Line/Estate Order']")
    private WebElement lineEstateOrder;
    
    @FindBy(css="span[aria-owns='Status_listbox']")
    private WebElement statusDropDown;
    
    @FindBy(css="ul[id='Status_listbox'] li")
    private List<WebElement> statusListbox;
    
    @FindBy(css="span[aria-owns='Language_listbox']")
    private WebElement languageDropDown;
    
    @FindBy(css="ul[id='Language_listbox'] li")
    private List<WebElement> languageListbox;
    
    @FindBy(css=".k-grid-update")
    private WebElement saveBtn;
    
    @FindBy(id="taskComplete")
    private WebElement taskCompleteBtn;

    @FindBy(id="MakerComments")
    private WebElement makerComments;
    
    @FindBy(id="submitMakerComment")
    private WebElement taskCompleteBtnAtMakerCommentsPopUp;
    
    @FindBy(id="Approve")
    private WebElement approveBtn;

    @FindBy(id="Reject")
    private WebElement rejectBtn;

    @FindBy(id="CheckerReason")
    private WebElement checkerReason;

    @FindBy(id="approveButton")
    private WebElement approveYesBtn;
    
    @FindBy(xpath="//tbody/tr/td[7]")
    private WebElement rowdata;
    
    @FindBy(id="checkerGrid")
    private WebElement checkerGrid;
    
    @FindBy(css="#drillGrid tbody tr td")
    private List<WebElement> editrowdata;

    @FindBy(id="ModifyReason")
    private WebElement modifyReasonTextbox;
    
    @FindBy(css=".form-group #ModifyReason1")
    private  WebElement deleteReasonTextBox;
    
    @FindBy(id="yesButton")
    private WebElement yesBtn;

    @FindBy(id="noButton")
    private WebElement noBtn;
    
    @FindBy(css=".k-edit-form-container #tgrid label")
    private List<WebElement> labels;
    
    @FindBy(id="tabstripfaxtemplateMakerChecker")
    private List<WebElement> makerCheckerTab;
    
    @FindBy(id="sendForApproval")
    private WebElement sendForApprovalBtn;
    
    @FindBy(id="undoChanges")
    private WebElement revertBtn;
    
    @FindBy(id="undoChangesMakerComments")
    private WebElement revertMakerComments;
    
    @FindBy(id="submitMakerComment")
    private WebElement submitMakerComments;
    
    @FindBy(id="submitUndoChangesMakerComment")
    private WebElement revertSubmitMakerComments;
    
    @FindBy(css = ".modal-body .form-inline .form-group .k-select")
	private List<WebElement> selectSearchCol;
    
    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
	private List<WebElement> searchCriteriaDropDwn;
    
    @FindBy(id = "1001sTextToSearch")
	private WebElement searchTextBox;
    
    @FindBy(css = ".modal-footer .button-theme")
	private WebElement searchSearchBtn;
    
    @FindBy(css="#gridDiv2 #tGrid")
    private WebElement auditGrid;
    
    @FindBy(id="tdrillgrid")
    private WebElement tgrid;

    
	public boolean isBranchManagementPageDisplayed() {
		waitForLoad(driver);
        waitForJqueryLoad(driver);
		return branchManagement.isEnabled();
	}
	
	public void navigateToTab(String tabname){
        waitUntilWebElementListIsVisible(navTabs);
        waitUntilWebElementListIsClickable(navTabs);
        for(WebElement ele: navTabs){
            if(ele.getText().equalsIgnoreCase(tabname)){selectWebElement(ele);break;}
        }
    }
	
	public void clickOnMakeBranchManageChanges() {
		selectWebElement(makeBranchManageChanges);
	}
	
	public boolean isMakeBranchChangesButtonDisplayed() {
		Boolean status = false;
		try {
    		if(makeBranchManageChanges.isDisplayed() && makeBranchManageChanges.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
	}
	
	public boolean isAddBtnDisplayed() {
    	return addNewBranchManageRecordBtn.isDisplayed() && addNewBranchManageRecordBtn.isEnabled();
    }
    
    public boolean isEditBtnDisplayed() {
    	Boolean status = false;
    	try {
    		if(editButton.isDisplayed() && editButton.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
    }
    
    public boolean isDeleteBtnDisplayed() {
    	Boolean status = false;
    	try {
    		if(deleteButton.isDisplayed() && deleteButton.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
    }
    
    public boolean isExportBtnDisplayed() {
    	return exporttoexcel.isDisplayed() && exporttoexcel.isEnabled();
    }

    public boolean verifylogo() {
		if(isElementExist(BMImg))
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

	public boolean verifyApprovedDataTableHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("MainLines","SubLines","Location","BranchType","Branch Wav File","Address Wav File","Branch Name","Address Text","Line/Estate Order","Status","Language","Last Changed By","Last Changed On"));
        ArrayList Actual = getHeadersfromTable(approvedDataTableHeaders);
        System.out.println(Actual);
        Collections.sort(Expected);
        Collections.sort(Actual);
        return Actual.equals(Expected);
	}

	private ArrayList getHeadersfromTable(List<WebElement> e) {
		ArrayList header=new ArrayList();
        for(int j=0;j<e.size();j++){
            scrollToElement(e.get(j));
            if(!e.get(j).getText().equals("")){header.add(e.get(j).getText());}
        }
        return header;
	}

	public void selectBranchManagementAuditTrailTab() {
		selectWebElement(BranchManagementTabs.get(1));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }		
	}

	public boolean verifyAuditTrailDataTableHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList(" ","Request Id", "Transaction", "Function", "Status", "User Id", "Submission DateTime", "Maker Comments", "Old Values", "New Values", "Reviewed By","Review DateTime", "Checker Comments"));
        ArrayList Actual = getHeadersfromTable(auditTrailTableHeaders);
        System.out.println(Actual);
        Collections.sort(Expected);Collections.sort(Actual);
        return Actual.equals(Expected);
	}

	public void selectMakeBranchManagementChanges() {
		selectWebElement(makeBranchManagementChanges);
		try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
	}

	public boolean verifyAddNewBranchManagementRecordButton() {
		return addNewBranchManageRecordBtn.isEnabled();

	}

	public boolean verifyGoBackButton() {
		return goBackBtn.isEnabled();

	}

	public boolean verifyExportToExcelButton() {
		return exporttoexcel.isEnabled();
	}

	public boolean verifyMakerDataTableHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("MainLines","SubLines","Location","BranchType","Branch Wav File","Address Wav File","Branch Name","Address Text","Line/Estate Order","Status","Language","Last Changed By","Last Changed On"));
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
		    if (f.getName().startsWith("Branch Management")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "Branch Management");
		return Status;
	}
    
    public boolean verifyexportToExcelSheet(List<Map<String, String>> maplist) throws Exception {
		List<Map<String,String>> UI=getdata(); 
		System.out.println(UI);
		System.out.println(maplist);
		if(UI.equals(maplist))
		return true;
		else
		return false;
	}
	
	private List<Map<String,String>> getdata() throws Exception{
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
				scrollToElement(headers.get(j));
				Thread.sleep(1000);
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
	
	public boolean ExporttoExcelWithoutData(BranchManagementDetails details) throws Exception {
		searchBranchManagementRecord(details.getBranchName());
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(exporttoexcel);
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
		return false;
	}

	private void searchBranchManagementRecord(String BranchName) throws Exception {
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Branch Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText,BranchName);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);	
	}
	
	private void searchBranchManagementRecordApprovedData(String branchName) throws Exception {
		selectWebElement(gridsearchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Branch Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText,branchName);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(approvedgridcontent);	
	}
	
	
	public void SortByAscending() {
		selectWebElement(Location.get(1));
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(Location.get(1));
		selectWebElement(Location.get(1));
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

	public boolean verifyDatabase(String query) throws Exception {
		List<Map<String,String>> database=database(query);
		System.out.println(database);
		List<Map<String,String>> UI=gettable(); 
		System.out.println(UI);
		if(UI.equals(database))
			return true;
		else
			return false;
	}
	
	public List<Map<String, String>> gettable() throws Exception {
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
				//waitUntilWebElementIsVisible(headers.get(j));
				scrollToElement(headers.get(j));
				Thread.sleep(1000);
				/*if(headers.get(j).getText().equals("Last Changed On")){
					col=cols.get(j).getText().substring(11);
					}
				else*/
					col=cols.get(j).getText();
				map.put(headers.get(j).getText(),col);
			}
			map.remove("");
			//map.remove("Preview");
			arr.add(map);
		}
		if(k!=pages)
		{
			nextPageIcon.get(0).click();
			waitForJqueryLoad(driver);}
		}
			return arr;
	}
	public boolean clearAll(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(gridsearchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Branch Name");
        Thread.sleep(1000);
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText,branchManagementDetails.getBranchName());
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

	public void searchwithoutextsearch(BranchManagementDetails branchManagementDetails) {
		selectWebElement(gridsearchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Branch Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        selectWebElement(searchBtn);	
        selectWebElement(searchClose);
	}


	public boolean verifyclearsearch() {
		selectWebElement(clearsearch);
		if(approvedgridcontent.isDisplayed())
			return true;
		else
		return false;
	}
	
	public String getSuccessMessage() {
        waitUntilWebElementIsVisible(successmsg);
        return successmsg.getText();
	}
	
	public boolean getErrorMsg() {
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.size()>0)
		return false;
		else
			return true;
	}
	
	public boolean verifyApprovedSectionData(BranchManagementDetails details) throws Exception {
		searchBranchManagementRecordApprovedData(details.getBranchName());
		if(norecords.isDisplayed())
			return true; 
			else
				return false;
	}

	public boolean addCancelButton(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		selectWebElement(makeBranchManagementChanges);
		String actualitems=items.get(2).getText();
		selectWebElement(addNewBranchManageRecordBtn);
		waitForJqueryLoad(driver);
		Thread.sleep(2000);
        selectWebElement(cancelBtn);
        waitForJqueryLoad(driver);
        if(actualitems.equals(items.get(2).getText()))
        	return true;
        else
		return false;
	}

	public void addNewBranchManagementRecord(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		selectWebElement(makeBranchManagementChanges);
		Thread.sleep(1000);
		selectWebElement(addNewBranchManageRecordBtn);
		waitForJqueryLoad(driver);
		Thread.sleep(3000);
		selectWebElement(mainLinesDropDown);
        selectDropdownFromVisibleText(mainLinesListbox, branchManagementDetails.getMainLines());
        selectWebElement(subLinesDropDown);
        selectDropdownFromVisibleText(subLinesListbox, branchManagementDetails.getSubLines());
        enterValueToTxtField(LocationTextbox,branchManagementDetails.getLocation());
        selectWebElement(branchTypeDropDown);
        selectDropdownFromVisibleText(branchTypeListbox, branchManagementDetails.getBranchType());
        selectWebElement(selectFiles.get(0));
        FileUploader fileUploader=new FileUploader();
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getBranchWave());
        Thread.sleep(1000);
        selectWebElement(selectFiles.get(0));
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getAddressWave());
        enterValueToTxtField(lineEstateOrder,branchManagementDetails.getLineEstateOrder());
        selectWebElement(statusDropDown);
        selectDropdownFromVisibleText(statusListbox, branchManagementDetails.getStatus());
        selectWebElement(languageDropDown);
        selectDropdownFromVisibleText(languageListbox, branchManagementDetails.getLanguage());
        selectWebElement(saveBtn);
        try {
        	selectWebElement(cancelBtn);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
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


	public boolean verifyAuditTrail(BranchManagementDetails branchManagementDetails, String Transaction, String Status) {
		boolean stat=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable();
        Map<String,String> popupRowData=getFirstRowDatafromPreviewPopup();


        if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
            if(firstRowData.get("Status").equalsIgnoreCase(Status)){
                if(firstRowData.get("Function Name").equalsIgnoreCase("Branch Management")){
                       if(Transaction.equals("MakerCreate")){
                           Map<String,String> newvalues=new HashMap<>();
                            String[] d=popupRowData.get("New Values").split("\n");
                            for(String e:d){
                                String[]f=e.split(":",2);
                                if(f.length>1){newvalues.put(f[0],f[1]);}
                            }
                            if(verifyNewValues(branchManagementDetails,newvalues)){
                                stat=true;}
                            else 
                            stat=false;
                       }
                       else{System.out.println("Data mismatch");}
                }else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"Branch Management");}
            }else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}
        }else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}
        return stat;
        }
	
	private boolean verifyNewValues(BranchManagementDetails details, Map<String, String> newvalues) {
		Boolean Status=false;
		if(newvalues.get("MainLines").equals(details.getMainLines()))
		{
			if(newvalues.get("SubLines").equals(details.getSubLines()))
			{
				if(newvalues.get("Location").equals(details.getLocation()))
				{
					if(newvalues.get("BranchType").equals(details.getBranchType()))
					{
						if(newvalues.get("BranchWavefile").equals(details.getBranchWave()))
						{
							if(newvalues.get("AddressWavefile").equals(details.getAddressWave()))
							{
								if(newvalues.get("BranchName").equals(details.getBranchName()))
								{
									if(newvalues.get("AddressText").equals(details.getAddress()))
									{
										if(newvalues.get("LineOrder").equals(details.getLineEstateOrder()))
										{
											if(newvalues.get("Status").equals(details.getStatus()))
											{
												if(newvalues.get("Language").equals(details.getLanguage()))
													Status= true;
												else {System.out.println("Language data mismatch");}
											}
											else {System.out.println("Status data mismatch");}
										}									
										else {System.out.println("Line/EstateOrder data mismatch");}
									}
									else {System.out.println("AddressText data mismatch");}
								}
								else {System.out.println("BranchName data mismatch");}
							}
							else {System.out.println("AddressWavfile data mismatch");}
						}
						else {System.out.println("BranchWavfile data mismatch");}
					}
					else {System.out.println("BranchType data mismatch");}
				}
				else {System.out.println("Location data mismatch");}
			}
			else {System.out.println("SubLines data mismatch");	}
		}
		else {System.out.println("MainLines data mismatch");	}
		return Status;
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

	public boolean verifyTaskCompleteEnabled() {
        return taskCompleteBtn.isEnabled();

	}

	public void taskCompleteAction(String comment) throws Exception {
		selectWebElement(makeBranchManagementChanges);
        waitForLoad(driver);
        selectWebElement(taskCompleteBtn);
        enterValueToTxtField(makerComments,comment);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        selectWebElement(taskCompleteBtnAtMakerCommentsPopUp);		
	}

	public boolean verifyTaskCompleteSuccessMessage() {
        return(getSuccessMessage().contains("Record submission for approval success. Your Request ID is :"));

	}

	public boolean verifyStatus(String status) {
		try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String,String> firstRowData=getFirstRowDatafromTable();
        return firstRowData.get("Status").equals(status);
	}

	public void clickonApprove(String comment) throws Exception {
		 selectWebElement(BranchManagementTabs.get(1));
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
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        clickOn(approveYesBtn);		
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

	public boolean verifyApprovedSectionDataafterapproval(BranchManagementDetails branchManagementDetails) throws Exception {
		searchBranchManagementRecordApprovedData(branchManagementDetails.getBranchName());
		if(rowdata.getText().equals(branchManagementDetails.getBranchName()))
			return true;
		else
		return false;
	}

	public void addwithoutMainLines(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		selectWebElement(makeBranchManagementChanges);
		Thread.sleep(1000);
		selectWebElement(addNewBranchManageRecordBtn);
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		enterValueToTxtField(LocationTextbox,branchManagementDetails.getLocation());
        selectWebElement(branchTypeDropDown);
        selectDropdownFromVisibleText(branchTypeListbox, branchManagementDetails.getBranchType());
        selectWebElement(selectFiles.get(0));
        FileUploader fileUploader=new FileUploader();
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getBranchWave());
        Thread.sleep(1000);
        selectWebElement(selectFiles.get(0));
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getAddressWave());
        enterValueToTxtField(lineEstateOrder,branchManagementDetails.getLineEstateOrder());
        selectWebElement(statusDropDown);
        selectDropdownFromVisibleText(statusListbox, branchManagementDetails.getStatus());
        selectWebElement(languageDropDown);
        selectDropdownFromVisibleText(languageListbox, branchManagementDetails.getLanguage());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);		
	}

	public void addwithoutSubLines(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		selectWebElement(makeBranchManagementChanges);
		Thread.sleep(1000);
		selectWebElement(addNewBranchManageRecordBtn);
		waitForJqueryLoad(driver);
		Thread.sleep(2000);
		selectWebElement(mainLinesDropDown);
        selectDropdownFromVisibleText(mainLinesListbox, branchManagementDetails.getMainLines());
        enterValueToTxtField(LocationTextbox,branchManagementDetails.getLocation());
        selectWebElement(branchTypeDropDown);
        selectDropdownFromVisibleText(branchTypeListbox, branchManagementDetails.getBranchType());
        selectWebElement(selectFiles.get(0));
        FileUploader fileUploader=new FileUploader();
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getBranchWave());
        Thread.sleep(1000);
        selectWebElement(selectFiles.get(0));
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getAddressWave());
        enterValueToTxtField(lineEstateOrder,branchManagementDetails.getLineEstateOrder());
        selectWebElement(statusDropDown);
        selectDropdownFromVisibleText(statusListbox, branchManagementDetails.getStatus());
        selectWebElement(languageDropDown);
        selectDropdownFromVisibleText(languageListbox, branchManagementDetails.getLanguage());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);		
	}

	public void addwithoutLocation(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		selectWebElement(makeBranchManagementChanges);
		Thread.sleep(1000);
		selectWebElement(addNewBranchManageRecordBtn);
		waitForJqueryLoad(driver);
		Thread.sleep(2000);
		selectWebElement(mainLinesDropDown);
        selectDropdownFromVisibleText(mainLinesListbox, branchManagementDetails.getMainLines());
        selectWebElement(subLinesDropDown);
        selectDropdownFromVisibleText(subLinesListbox, branchManagementDetails.getSubLines());
        selectWebElement(branchTypeDropDown);
        selectDropdownFromVisibleText(branchTypeListbox, branchManagementDetails.getBranchType());
        selectWebElement(selectFiles.get(0));
        FileUploader fileUploader=new FileUploader();
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getBranchType());
        Thread.sleep(1000);
        selectWebElement(selectFiles.get(0));
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getAddressWave());
        enterValueToTxtField(lineEstateOrder,branchManagementDetails.getLineEstateOrder());
        selectWebElement(statusDropDown);
        selectDropdownFromVisibleText(statusListbox, branchManagementDetails.getStatus());
        selectWebElement(languageDropDown);
        selectDropdownFromVisibleText(languageListbox, branchManagementDetails.getLanguage());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);		
	}

	public void addwithoutBranchType(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		selectWebElement(makeBranchManagementChanges);
		Thread.sleep(1000);
		selectWebElement(addNewBranchManageRecordBtn);
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(mainLinesDropDown);
        selectDropdownFromVisibleText(mainLinesListbox, branchManagementDetails.getMainLines());
        selectWebElement(subLinesDropDown);
        selectDropdownFromVisibleText(subLinesListbox, branchManagementDetails.getSubLines());
        enterValueToTxtField(LocationTextbox,branchManagementDetails.getLocation());
        selectWebElement(selectFiles.get(0));
        FileUploader fileUploader=new FileUploader();
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getBranchWave());
        Thread.sleep(1000);
        selectWebElement(selectFiles.get(0));
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getAddressWave());
        enterValueToTxtField(lineEstateOrder,branchManagementDetails.getLineEstateOrder());
        selectWebElement(statusDropDown);
        selectDropdownFromVisibleText(statusListbox, branchManagementDetails.getStatus());
        selectWebElement(languageDropDown);
        selectDropdownFromVisibleText(languageListbox, branchManagementDetails.getLanguage());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);		
	}

	public void addwithoutBranchName(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		selectWebElement(makeBranchManagementChanges);
		Thread.sleep(1000);
		selectWebElement(addNewBranchManageRecordBtn);
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(mainLinesDropDown);
        selectDropdownFromVisibleText(mainLinesListbox, branchManagementDetails.getMainLines());
        selectWebElement(subLinesDropDown);
        selectDropdownFromVisibleText(subLinesListbox, branchManagementDetails.getSubLines());
        enterValueToTxtField(LocationTextbox,branchManagementDetails.getLocation());
        selectWebElement(branchTypeDropDown);
        selectDropdownFromVisibleText(branchTypeListbox, branchManagementDetails.getBranchType());
        FileUploader fileUploader=new FileUploader();
        selectWebElement(selectFiles.get(1));
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getAddressWave());
        enterValueToTxtField(lineEstateOrder,branchManagementDetails.getLineEstateOrder());
        selectWebElement(statusDropDown);
        selectDropdownFromVisibleText(statusListbox, branchManagementDetails.getStatus());
        selectWebElement(languageDropDown);
        selectDropdownFromVisibleText(languageListbox, branchManagementDetails.getLanguage());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);		
	}

	public void addwithoutAddress(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		selectWebElement(makeBranchManagementChanges);
		Thread.sleep(1000);
		selectWebElement(addNewBranchManageRecordBtn);
		waitForJqueryLoad(driver);
		Thread.sleep(2000);
		selectWebElement(mainLinesDropDown);
        selectDropdownFromVisibleText(mainLinesListbox, branchManagementDetails.getMainLines());
        selectWebElement(subLinesDropDown);
        selectDropdownFromVisibleText(subLinesListbox, branchManagementDetails.getSubLines());
        enterValueToTxtField(LocationTextbox,branchManagementDetails.getLocation());
        selectWebElement(branchTypeDropDown);
        selectDropdownFromVisibleText(branchTypeListbox, branchManagementDetails.getBranchType());
        selectWebElement(selectFiles.get(0));
        FileUploader fileUploader=new FileUploader();
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getBranchWave());
        Thread.sleep(1000);
        enterValueToTxtField(lineEstateOrder,branchManagementDetails.getLineEstateOrder());
        selectWebElement(statusDropDown);
        selectDropdownFromVisibleText(statusListbox, branchManagementDetails.getStatus());
        selectWebElement(languageDropDown);
        selectDropdownFromVisibleText(languageListbox, branchManagementDetails.getLanguage());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);		
	}

	public void addwithoutLineOrder(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		selectWebElement(makeBranchManagementChanges);
		Thread.sleep(1000);
		selectWebElement(addNewBranchManageRecordBtn);
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(mainLinesDropDown);
        selectDropdownFromVisibleText(mainLinesListbox, branchManagementDetails.getMainLines());
        selectWebElement(subLinesDropDown);
        selectDropdownFromVisibleText(subLinesListbox, branchManagementDetails.getSubLines());
        enterValueToTxtField(LocationTextbox,branchManagementDetails.getLocation());
        selectWebElement(branchTypeDropDown);
        selectDropdownFromVisibleText(branchTypeListbox, branchManagementDetails.getBranchType());
        selectWebElement(selectFiles.get(0));
        FileUploader fileUploader=new FileUploader();
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getBranchWave());
        Thread.sleep(1000);
        selectWebElement(selectFiles.get(0));
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getAddressWave());
        selectWebElement(statusDropDown);
        selectDropdownFromVisibleText(statusListbox, branchManagementDetails.getStatus());
        selectWebElement(languageDropDown);
        selectDropdownFromVisibleText(languageListbox, branchManagementDetails.getLanguage());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);
		
	}

	public void addwithoutStatus(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		selectWebElement(makeBranchManagementChanges);
		Thread.sleep(1000);
		selectWebElement(addNewBranchManageRecordBtn);
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(mainLinesDropDown);
        selectDropdownFromVisibleText(mainLinesListbox, branchManagementDetails.getMainLines());


        enterValueToTxtField(LocationTextbox,branchManagementDetails.getLocation());
        selectWebElement(branchTypeDropDown);
        selectDropdownFromVisibleText(branchTypeListbox, branchManagementDetails.getBranchType());
        selectWebElement(selectFiles.get(0));
        FileUploader fileUploader=new FileUploader();
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getBranchWave());
        Thread.sleep(1000);
        selectWebElement(selectFiles.get(0));
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getAddressWave());
        enterValueToTxtField(lineEstateOrder,branchManagementDetails.getLineEstateOrder());
        selectWebElement(languageDropDown);
        selectDropdownFromVisibleText(languageListbox, branchManagementDetails.getLanguage());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);		
	}

	public void addwithoutLanguage(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		selectWebElement(makeBranchManagementChanges);
		Thread.sleep(1000);
		selectWebElement(addNewBranchManageRecordBtn);
		waitForJqueryLoad(driver);
		Thread.sleep(2000);
		selectWebElement(mainLinesDropDown);
        selectDropdownFromVisibleText(mainLinesListbox, branchManagementDetails.getMainLines());
        selectWebElement(subLinesDropDown);
        selectDropdownFromVisibleText(subLinesListbox, branchManagementDetails.getSubLines());
        enterValueToTxtField(LocationTextbox,branchManagementDetails.getLocation());
        selectWebElement(branchTypeDropDown);
        selectDropdownFromVisibleText(branchTypeListbox, branchManagementDetails.getBranchType());
        selectWebElement(selectFiles.get(0));
        FileUploader fileUploader=new FileUploader();
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getBranchWave());
        Thread.sleep(1000);
        selectWebElement(selectFiles.get(0));
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + branchManagementDetails.getAddressWave());
        enterValueToTxtField(lineEstateOrder,branchManagementDetails.getLineEstateOrder());
        selectWebElement(statusDropDown);
        selectDropdownFromVisibleText(statusListbox, branchManagementDetails.getStatus());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);
	}

	public void clickonReject(String comment) throws Exception {
		 selectWebElement(BranchManagementTabs.get(1));
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectRecord();
	        clickOn(rejectBtn);
	        selectWebElement(checkerReason);
	        enterValueToTxtField(checkerReason,comment);
	        try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        clickOn(approveYesBtn);		
	}

	public boolean VerifyMakeBranchManagementChangeButton() {
		return makeBranchManagementChanges.isDisplayed();

	}

	public boolean EditCancel(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makeBranchManagementChanges);
		Thread.sleep(1000);
		searchBranchManagementRecord(branchManagementDetails.getBranchName());
		selectWebElement(editButton);
		waitForJqueryLoad(driver);
		selectWebElement(cancelBtn);
		if(editrowdata.get(2).getText().equals(branchManagementDetails.getSubLines()))
			return true;
		else
		return false;
	}
	
	public void EditRecordWithoutModifyReason(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makeBranchManagementChanges);
		Thread.sleep(1000);
		searchBranchManagementRecord(branchManagementDetails.getBranchName());
		selectWebElement(editButton);
		waitForJqueryLoad(driver);
		selectWebElement(subLinesDropDown);
	    selectDropdownFromVisibleText(subLinesListbox, branchManagementDetails.getUpdatedSubLines());
	    selectWebElement(statusDropDown);
        selectDropdownFromVisibleText(statusListbox, branchManagementDetails.getUpdatedStatus());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);
	}

	public void EditBranchManagementRecord(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makeBranchManagementChanges);
		Thread.sleep(1000);
		searchBranchManagementRecord(branchManagementDetails.getBranchName());
		selectWebElement(editButton);
		waitForJqueryLoad(driver);
		selectWebElement(subLinesDropDown);
	    selectDropdownFromVisibleText(subLinesListbox, branchManagementDetails.getUpdatedSubLines());
	    selectWebElement(statusDropDown);
        selectDropdownFromVisibleText(statusListbox, branchManagementDetails.getUpdatedStatus());
        selectWebElement(modifyReasonTextbox);
        enterValueToTxtFieldWithoutClear(modifyReasonTextbox,branchManagementDetails.getModifyReason());
        selectWebElement(saveBtn);	
	}

	public boolean verifyAuditTrailUpdate(BranchManagementDetails details, String Transaction,String Status) {
		boolean stat=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable();
        Map<String,String> popupRowData=getFirstRowDatafromPreviewPopup();


        if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
            if(firstRowData.get("Status").equalsIgnoreCase(Status)){
                if(firstRowData.get("Function Name").equalsIgnoreCase("Branch Management")){
                       if(Transaction.equals("MakerUpdate")){
                           Map<String,String> newvalues=new HashMap<>();
                            String[] d=popupRowData.get("New Values").split("\n");
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
                }else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"RoleManagement");}
            }else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}
        }else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}
        return stat;
	}

	private boolean verifyUpdatedNewValues(BranchManagementDetails details, Map<String, String> newvalues) {
		Boolean Status=false;
		if(newvalues.get("MainLines").equals(details.getMainLines()))
		{
			if(newvalues.get("SubLines").equals(details.getUpdatedSubLines()))
			{
				if(newvalues.get("Location").equals(details.getLocation()))
				{
					if(newvalues.get("BranchType").equals(details.getBranchType()))
					{
						if(newvalues.get("BranchWavefile").equals(details.getBranchWave()))
						{
							if(newvalues.get("AddressWavefile").equals(details.getAddressWave()))
							{
								if(newvalues.get("BranchName").equals(details.getBranchName()))
								{
									if(newvalues.get("AddressText").equals(details.getAddress()))
									{
										if(newvalues.get("LineOrder").equals(details.getLineEstateOrder()))
										{
											if(newvalues.get("Status").equals(details.getUpdatedStatus()))
											{
												if(newvalues.get("Language").equals(details.getLanguage()))
												{	
													Status=true;
												}
												else System.out.println("Language data mismatch");	
											}
											else System.out.println("Status data mismatch");
										}
										else System.out.println("LineOrder data mismatch");
									}
									else System.out.println("AddressText data mismatch");
								}
								else System.out.println("BranchName data mismatch");
							}
							else System.out.println("AddressWavefile data mismatch");
						}
						else System.out.println("BranchWavefile data mismatch");
					}
					else System.out.println("BranchType data mismatch");
				}
				else System.out.println("Location data mismatch");
			}
			else System.out.println("SubLines data mismatch");
		}
		else System.out.println("MainLines data mismatch");	
	return Status;
	}

	public boolean DeleteCancel(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makeBranchManagementChanges);
		Thread.sleep(1000);
		searchBranchManagementRecord(branchManagementDetails.getBranchName());
		selectWebElement(deleteButton);
		waitForJqueryLoad(driver);
		selectWebElement(noBtn);
		if(editrowdata.get(2).getText().equals(branchManagementDetails.getSubLines()))
			return true;
		else
		return false;
	}
	
	public void DeleteRecordWithoutDeleteReason(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makeBranchManagementChanges);
		Thread.sleep(1000);
		searchBranchManagementRecord(branchManagementDetails.getBranchName());
		Thread.sleep(1000);
		selectWebElement(deleteButton);
		Thread.sleep(1000);
        selectWebElement(yesBtn);	
        selectWebElement(noBtn);
	}

	public void DeleteBranchManagementRecord(BranchManagementDetails branchManagementDetails) throws Exception {
		selectWebElement(BranchManagementTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makeBranchManagementChanges);
		Thread.sleep(1000);
		searchBranchManagementRecord(branchManagementDetails.getBranchName());
		Thread.sleep(1000);
		selectWebElement(deleteButton);
		Thread.sleep(1000);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtFieldWithoutClear(deleteReasonTextBox,branchManagementDetails.getDeleteReason());
        selectWebElement(yesBtn);		
	}

	public boolean verifyAuditTrailDelete(BranchManagementDetails details, String Transaction,String Status) {
		boolean stat=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable();
        if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
            if(firstRowData.get("Status").equalsIgnoreCase(Status)){
                if(firstRowData.get("Function Name").equalsIgnoreCase("Branch Management")){
                       stat=true;
                }else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"RoleManagement");}
            }else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}
        }else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}
        return stat;
	}

	public void selectAddNewBranchManagement() {
		selectWebElement(addNewBranchManageRecordBtn);
	}
	
	 public boolean verifyJsonDataForMakerAndChecker(boolean mkrchk){
	        boolean status=false;
	        if(mkrchk){
	            if(makerCheckerTab.size()==1){status=true;}
	        }else {if(makerCheckerTab.size()!=1){status=true;}}
	    return status;
	    }
	    
	 public boolean verifyJsonDataForgridColumnHidden(Map<String,String> jsonmap){
	        boolean status=false;
	        for(WebElement e: headersText){
	            scrollToElement(e);
	            if(jsonmap.get(e.getText()).equalsIgnoreCase("false")){status=true;}else{
	                System.out.println("Header "+e.getText()+"is hidden in JSON configuration file");status=false;break;}
	        }
	        return status;
	    }
	    	    
	 public void enableAllColumnsHeaders() {
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
	                WebElement checkbox = headersColumns.get(i).findElement(By.tagName("input"));
	                checkbox.click();
	                if (checkbox.isSelected()) {
	                } else {
	                    checkbox.click();
	                }
	            }
	        }
	    }
	    public boolean verifyJsonDataForColumnIncludeGrid(Map<String,String> jsonmap){
	        Map<String,String> map =getDefaultEnabledColumnsHeaders();
	        return map.equals(jsonmap);
	    }
	    public Map<String,String> getDefaultEnabledColumnsHeaders() {
	        Map<String,String> map=new HashMap<>();
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
	                WebElement checkbox = headersColumns.get(i).findElement(By.tagName("input"));
	                if (checkbox.isSelected()) 
	                {map.put(headersColumns.get(i).getText(),"false");}
	                else{
	                	map.put(headersColumns.get(i).getText(),"true");}
	            }
	        }
	        return map;
	    }
	    public boolean verifyJsonDataForMandatoryField(Map<String,String> jsonmap){
	        boolean status=false;
	        jsonmap.remove("Features");
	        ArrayList<String> mand=new ArrayList<>();
	        for(String key:jsonmap.keySet()){
	            if(jsonmap.get(key).equalsIgnoreCase("true")){
	                status=false;
	                for(WebElement e:labels){
	                    if(e.getText().equals(key+"*")){mand.add(key);status=true;break;}
	                }if(!status){System.out.println(key+" label is not having mandatory * symbol");break;}
	            }
	        }
	        return status;
	    }
	    
	    
	    public void selectRecord() {
			Map<String,String> map = new HashMap<>();
			waitUntilWebElementIsVisible(auditGridContent);
			List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
			List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
			selectWebElement(cols.get(0).findElement(By.id("isEnabled")));
	    }
		
		public void sendForAprroval(String comments) throws Exception {
			selectWebElement(sendForApprovalBtn);
			enterValueToTxtField(makerComments, comments);
			selectWebElement(submitMakerComments);		
		}
		
		public void Revert(String comments) throws Exception {
			selectWebElement(revertBtn);
			enterValueToTxtField(revertMakerComments,comments);
			selectWebElement(revertSubmitMakerComments);				
		}
		
		public boolean verifyMessage() {
	        return(getSuccessMessage().contains("Record approved successfully. Request ID :"));

		}
		
		public List<Map<String, String>> gettable1() throws Exception {
			Thread.sleep(3000);
			int item=Integer.valueOf(items.get(2).getText().split("of ")[1].split(" items")[0]);
	        int pagersize=Integer.valueOf(pagerSize.get(2).getText());
	        int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
			List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
			for(int k=0;k<=pages;k++){

			waitUntilWebElementIsVisible(auditGrid);
			List<WebElement> rows=auditGrid.findElements(By.tagName("tr"));
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
				nextPageIcon.get(2).click();
				waitForJqueryLoad(driver);}
			}
				return arr;
		}
		
		public boolean verifySearchIsNotEqualTo(String sublines) throws Exception {
			Boolean Status=false;
			Map<String, String> map=new HashMap<String,String>() ;
			map.put("SubLines", sublines);
			selectWebElement(searchLink);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"SubLines");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
	        enterValueToTxtField(searchTextBox,sublines);		
	        selectWebElement(searchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable1(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(map1.equals(map))
	        	Status= false;
	        	else 
	        		Status= true;
		}
	        return Status;
		
		}
		
		public boolean verifySearchContains(String sublines) throws Exception {
			Boolean Status=false;
			selectWebElement(searchLink);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"SubLines");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
	        enterValueToTxtField(searchTextBox,sublines);		
	        selectWebElement(searchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable1(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(map1.get("SubLines").toUpperCase().contains(sublines.toUpperCase()))
	        	Status= true;
	        	else 
	        		Status= false;
		}
	        return Status;
		}
		public boolean verifySearchDoesNotContains(String sublines) throws Exception {
			Boolean Status=false;
			selectWebElement(searchLink);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"SubLines");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not contain");
	        enterValueToTxtField(searchTextBox,sublines);		
	        selectWebElement(searchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable1(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(!map1.get("SubLines").toLowerCase().contains(sublines.toLowerCase()))
	        	Status= true;
	        	else 
	        		Status= false;
		}
	        return Status;
		}
		
		public boolean verifySearchStartsWith(String sublines) throws Exception {
			Boolean Status=false;
			selectWebElement(searchLink);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"SubLines");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
	        enterValueToTxtField(searchTextBox,sublines);		
	        selectWebElement(searchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable1(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(map1.get("SubLines").toLowerCase().startsWith(sublines.toLowerCase()))
	        	Status= true;
	        	else 
	        		Status= false;
		}
	        return Status;
		}
		
		public boolean verifySearchEndsWith(String sublines) throws Exception {
			Boolean Status=false;
			selectWebElement(searchLink);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"SubLines");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
	        enterValueToTxtField(searchTextBox,sublines);		
	        selectWebElement(searchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable1(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(map1.get("SubLines").toUpperCase().endsWith(sublines.toUpperCase()))
	        	Status= true;
	        	else 
	        		Status= false;
		}
	        return Status;
		}
		

		

		

		

}
