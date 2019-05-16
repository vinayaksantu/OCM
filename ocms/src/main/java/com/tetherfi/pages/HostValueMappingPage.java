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

import com.tetherfi.model.fax.FaxTemplateDetails;
import com.tetherfi.model.ivr.HostValueMappingDetails;
import com.tetherfi.utility.FileUploader;

public class HostValueMappingPage extends BasePage {

	public HostValueMappingPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css=".ibox-title h5")
    private WebElement hostValueMapping;
	
	@FindBy(xpath = "//div[@id='tabstripHostMapMakerChecker']/ul/li")
    private List<WebElement> navTabs;
	
	@FindBy(css="#create")
	private WebElement addNewHostValueMappingRecordBtn;
	
	@FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;
	
	@FindBy(css = ".k-grid-edit")
    private WebElement editButton;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
	
	@FindBy(xpath="//i[@class='fas fa-map-signs']")
	private WebElement HVMImg;
	    
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
    private List<WebElement> HostValueMappingTabs;
    
    @FindBy(id="goToAuditTrail")
    private WebElement goBackBtn;
    
    @FindBy(id="makeChanges")
    private WebElement makeHostValueMappingChanges;
    
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
    
    @FindBy(css="#drillGrid th a[title='Column Settings']")
    private List<WebElement> headersDropdown;
    
    @FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
    private List<WebElement> headersColumns;
    
    @FindBy(css="#drillGrid th a[class='k-link']")
    private List<WebElement> headersText;		
    
    @FindBy(xpath="//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")
    private List<WebElement> tablerecord;
   
    @FindBy(xpath="//span[@class='k-pager-info k-label']")
	private List<WebElement> items;
    
    @FindBy(id="navbarheader")
	private WebElement header;
    
    @FindBy(xpath="//span[@class='k-icon k-i-arrow-60-right k-menu-expand-arrow']")
    private WebElement coloumnarrow;
    
    @FindBy(id="grid")
    private WebElement auditGridContent;
    
    @FindBy(css=".k-pager-sizes .k-input")
    private List<WebElement> pagerSize;
    
    @FindBy(css="#drillGrid .k-grid-content")
    private WebElement gridContent;
    
    @FindBy(id = "drillGrid")
    private WebElement grid;

    @FindBy(css = "#drillGrid .k-grouping-header")
    private WebElement dragColumnDestination;
    
    @FindBy(css=".modal-body .form-inline .form-group .k-select")
    private List<WebElement> selectSearchColumn;

    @FindBy(css="ul[id='1001sColumnName_listbox'] li")
    private List<WebElement> columnNameList;

    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchTypeList;

    @FindBy(css=".modal-body .form-inline .form-group .k-textbox")
    private List<WebElement> searchText;

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
    
    @FindBy(xpath="//div[@class='k-button k-upload-button']")
    private WebElement uploadfile;
    
    @FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
    
    @FindBy(css="#tcheckerGrid .k-grid-content")
    private WebElement approvedgridcontent;
    
    @FindBy(xpath="//tbody/tr/td[1]")
    private WebElement rowdata;
    
    @FindBy(css="#drillGrid tbody tr td")
    private List<WebElement> editrowdata;
    
    @FindBy(id = "ModifyReason")
    private WebElement modifyReasonTextBox;
    
    @FindBy(css=".form-group #ModifyReason1")
    private  WebElement deleteReasonTextBox;
    
    @FindBy(id="yesButton")
    private WebElement yesBtn;

    @FindBy(id="noButton")
    private WebElement noBtn;
    
    @FindBy(id="checkerGrid")
    private WebElement checkerGrid;
    
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
    
    @FindBy(xpath="//button[text()='Close']")
    private WebElement searchClose;
    
    @FindBy(xpath="//i[@class='fas fa-sync']")
    private WebElement clearsearch;
    
    @FindBy(id="tabstripfaxtemplateMakerChecker")
    private List<WebElement> makerCheckerTab;
    
    @FindBy(css=".k-grid-update")
    private WebElement saveBtn;
    
    @FindBy(css=".k-edit-form-container #tgrid label")
    private List<WebElement> labels;
    
    @FindBy(xpath="//a[text()='Functionality']")
    private List<WebElement> Functionality;
    
    @FindBy(css="span[aria-owns='Functionality_listbox']")
    private WebElement functionalityDropdown;
    
    @FindBy(css="ul[id='Functionality_listbox'] li")
    private List<WebElement> functionalityListbox;
    
    @FindBy(css="span[aria-owns='Language_listbox']")
    private WebElement languageDropdown;
    
    @FindBy(css="ul[id='Language_listbox'] li")
    private List<WebElement> languageListbox;
    
    @FindBy(id="HostData")
    private WebElement hostDataTextbox;
    
    @FindBy(css="span[aria-owns='Status_listbox']")
    private WebElement statusDropdown;
    
    @FindBy(css="ul[id='Status_listbox'] li")
    private List<WebElement> statusListbox;
    
    @FindBy(id="Description")
    private WebElement descriptionTextbox;
    
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
	
	
	public boolean isHostValueMappingPageDisplayed() {
		waitForLoad(driver);
        waitForJqueryLoad(driver);
		return hostValueMapping.isEnabled();
	}
	
	public void navigateToTab(String tabname){
        waitUntilWebElementListIsVisible(navTabs);
        waitUntilWebElementListIsClickable(navTabs);
        for(WebElement ele: navTabs){
            if(ele.getText().equalsIgnoreCase(tabname)){selectWebElement(ele);break;}
        }
    }
	
	public void clickOnMakeHostValueMappingChanges() {
		selectWebElement(makeHostValueMappingChanges);
	}
	
	public boolean isMakeHostValueMappingChangesButtonDisplayed() {
		Boolean status = false;
		try {
    		if(makeHostValueMappingChanges.isDisplayed() && makeHostValueMappingChanges.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
	}
	
	public boolean isAddBtnDisplayed() {
    	return addNewHostValueMappingRecordBtn.isDisplayed() && addNewHostValueMappingRecordBtn.isEnabled();
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
		if(isElementExist(HVMImg))
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

    private ArrayList getHeadersfromTable(List<WebElement> e){
        ArrayList header=new ArrayList();
        for(int j=0;j<e.size();j++){
            scrollToElement(e.get(j));
            if(!e.get(j).getText().equals("")){header.add(e.get(j).getText());}
        }
        return header;
        }

	public boolean verifyApprovedDataTableHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("Functionality","Language","Host Data","Description","Wave File","Last Changed By","Last Changed On"));
        ArrayList Actual = getHeadersfromTable(approvedDataTableHeaders);
        System.out.println(Actual);
        Collections.sort(Expected);
        Collections.sort(Actual);
        return Actual.equals(Expected);
	}

	public void selectHostValueMappingAuditTrailTab() {
		selectWebElement(HostValueMappingTabs.get(1));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	public void selectHostValueMappingAuditTrailTab1() {
		selectWebElement(HostValueMappingTabs.get(0));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	public boolean verifyAuditTrailDataTableHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("Request Id", "Transaction", "Function", "Status", "User Id", "Submission DateTime", "Maker Comments", "Old Values", "New Values", "Reviewed By","Review DateTime", "Checker Comments"));
        ArrayList Actual = getHeadersfromTable(auditTrailTableHeaders);
        System.out.println(Actual);
        Collections.sort(Expected);Collections.sort(Actual);
        return Actual.equals(Expected);
	}

	public void selectMakeHostValueMappingChanges() {
		selectWebElement(makeHostValueMappingChanges);
		try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	public boolean verifyAddNewHostValueMappingRecordButton() {
		return addNewHostValueMappingRecordBtn.isEnabled();
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
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("Functionality","Language","Host Data","Description","Wave File","Last Changed By","Last Changed On"));
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
		    if (f.getName().startsWith("Host Value Mapping")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "Host Value Mapping");
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

        return (dragColumnDestination.getText().equals(colname));
    }

	public boolean ExporttoExcelWithoutData(HostValueMappingDetails details) throws Exception {
		searchHostValueMappingRecord(details.getHostData());
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(exporttoexcel);
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
		return false;
	}
	
	private void searchHostValueMappingRecord(String hostData) {
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Host Data");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),hostData);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);			
	}

	public boolean clearAll(HostValueMappingDetails details) {
		selectWebElement(gridsearchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Host Data");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),details.getHostData());
	    selectWebElement(clearall);
			if(searchText.get(0).isEnabled())
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

	public void searchwithoutextsearch(HostValueMappingDetails details) {
		selectWebElement(gridsearchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Host Data");
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
		waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return errorMsg.get(0).getText();}
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
	
	public void SortByAscending() {
		selectWebElement(Functionality.get(1));
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(Functionality.get(1));
		selectWebElement(Functionality.get(1));
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean verifyApprovedSectionData(HostValueMappingDetails details) {
		searchHostValueMappingRecordApprovedData(details.getHostData());
		if(norecords.isDisplayed())
			return true; 
			else
				return false;
	}

	private void searchHostValueMappingRecordApprovedData(String hostData) {
		selectWebElement(gridsearchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Host Data");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),hostData);
        selectWebElement(searchBtn);
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

	public boolean addCancelButton(HostValueMappingDetails hostValueMappingDetails) {
		selectWebElement(HostValueMappingTabs.get(1));
		selectWebElement(makeHostValueMappingChanges);
		String actualitems=items.get(2).getText();
		selectWebElement(addNewHostValueMappingRecordBtn);
		waitForJqueryLoad(driver);
        selectWebElement(cancelBtn);
        if(actualitems.equals(items.get(2).getText()))
        	return true;
        else
		return false;
	}

	public void addNewHostValueMappingRecord(HostValueMappingDetails hostValueMappingDetails) {
		selectWebElement(HostValueMappingTabs.get(1));
		selectWebElement(makeHostValueMappingChanges);
		selectWebElement(addNewHostValueMappingRecordBtn);
		waitForJqueryLoad(driver);	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectWebElement(functionalityDropdown);
        selectDropdownFromVisibleText(functionalityListbox, hostValueMappingDetails.getFunctionality());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox, hostValueMappingDetails.getLanguage());
        selectWebElement(hostDataTextbox);
        enterValueToTxtField(hostDataTextbox,hostValueMappingDetails.getHostData());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListbox,hostValueMappingDetails.getStatus());
        enterValueToTxtField(descriptionTextbox,hostValueMappingDetails.getDescription());
        selectWebElement(uploadfile);
        FileUploader fileUploader=new FileUploader();
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + hostValueMappingDetails.getWaveFile());
        selectWebElement(saveBtn);
	}

	public boolean verifyAuditTrail(HostValueMappingDetails hostValueMappingDetails, String Transaction, String Status) {
		boolean stat=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable();
        if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
            if(firstRowData.get("Status").equalsIgnoreCase(Status)){
                if(firstRowData.get("Function").equalsIgnoreCase("IvrHostmap")){
                       if(Transaction.equals("MakerCreate")){
                           Map<String,String> newvalues=new HashMap<>();
                            String[] d=firstRowData.get("New Values").split("\n");
                            for(String e:d){
                                String[]f=e.split(":",2);
                                if(f.length>1){newvalues.put(f[0],f[1]);}
                            }
                            if(verifyNewValues(hostValueMappingDetails,newvalues)){
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

	private boolean verifyNewValues(HostValueMappingDetails details, Map<String, String> newvalues) {
		Boolean Status=false;
		if(newvalues.get("Functionality").equals(details.getFunctionality()))
		{
			if(newvalues.get("Language").equals(details.getLanguage()))
			{
				if(newvalues.get("HostData").equals(details.getHostData()))
				{
					if(newvalues.get("Status").equals(details.getStatus()))
					{
						if(newvalues.get("Description").equals(details.getDescription()))
						{
							if(newvalues.get("WaveFile").equals(details.getWaveFile()))
							{
								Status=true;
							}
							else {System.out.println("WaveFile data mismatch");}
						}
						else {System.out.println("Description data mismatch");}
					}	
					else {System.out.println("Status data mismatch");}
				}
				else {System.out.println("HostData data mismatch");}
			}
			else {System.out.println("Language data mismatch");}
		}
		else {System.out.println("Functionality data mismatch");}
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

	public void taskCompleteAction(String comment) {
		selectWebElement(makeHostValueMappingChanges);
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

	public boolean verifyStatus(String status) {
		try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String,String> firstRowData=getFirstRowDatafromTable();
        return firstRowData.get("Status").equals(status);
	}

	public void clickonApprove(String comment) {
		selectWebElement(HostValueMappingTabs.get(1));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickOn(approveBtn);
        selectWebElement(checkerReason);
        enterValueToTxtField(checkerReason,comment);
        try {
            Thread.sleep(4000);
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

	public boolean verifyApprovedSectionDataafterapproval(HostValueMappingDetails hostValueMappingDetails) {
		searchHostValueMappingRecordApprovedData(hostValueMappingDetails.getHostData());
		if(rowdata.getText().equals(hostValueMappingDetails.getHostData()))
			return true;
		else
		return false;
	}

	public void addwithoutFunctionality(HostValueMappingDetails hostValueMappingDetails) {
		selectWebElement(HostValueMappingTabs.get(1));
		selectWebElement(makeHostValueMappingChanges);
		selectWebElement(addNewHostValueMappingRecordBtn);
		waitForJqueryLoad(driver);		
		selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox, hostValueMappingDetails.getLanguage());
        selectWebElement(hostDataTextbox);
        enterValueToTxtField(hostDataTextbox,hostValueMappingDetails.getHostData());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListbox,hostValueMappingDetails.getStatus());
        enterValueToTxtField(descriptionTextbox,hostValueMappingDetails.getDescription());
        selectWebElement(uploadfile);
        FileUploader fileUploader=new FileUploader();
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + hostValueMappingDetails.getWaveFile());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);
	}

	public void addwithoutLanguage(HostValueMappingDetails hostValueMappingDetails) {
		selectWebElement(HostValueMappingTabs.get(1));
		selectWebElement(makeHostValueMappingChanges);
		selectWebElement(addNewHostValueMappingRecordBtn);
		waitForJqueryLoad(driver);		
		selectWebElement(functionalityDropdown);
        selectDropdownFromVisibleText(languageListbox, hostValueMappingDetails.getLanguage());
        selectWebElement(hostDataTextbox);
        enterValueToTxtField(hostDataTextbox,hostValueMappingDetails.getHostData());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListbox,hostValueMappingDetails.getStatus());
        enterValueToTxtField(descriptionTextbox,hostValueMappingDetails.getDescription());
        selectWebElement(uploadfile);
        FileUploader fileUploader=new FileUploader();
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + hostValueMappingDetails.getWaveFile());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);
	}

	public void addwithoutHostData(HostValueMappingDetails hostValueMappingDetails) {
		selectWebElement(HostValueMappingTabs.get(1));
		selectWebElement(makeHostValueMappingChanges);
		selectWebElement(addNewHostValueMappingRecordBtn);
		waitForJqueryLoad(driver);		
		selectWebElement(functionalityDropdown);
        selectDropdownFromVisibleText(functionalityListbox, hostValueMappingDetails.getFunctionality());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox, hostValueMappingDetails.getLanguage());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListbox,hostValueMappingDetails.getStatus());
        enterValueToTxtField(descriptionTextbox,hostValueMappingDetails.getDescription());
        selectWebElement(uploadfile);
        FileUploader fileUploader=new FileUploader();
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + hostValueMappingDetails.getWaveFile());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);
	}

	public void addwithoutStatus(HostValueMappingDetails hostValueMappingDetails) {
		selectWebElement(HostValueMappingTabs.get(1));
		selectWebElement(makeHostValueMappingChanges);
		selectWebElement(addNewHostValueMappingRecordBtn);
		waitForJqueryLoad(driver);		
		selectWebElement(functionalityDropdown);
        selectDropdownFromVisibleText(functionalityListbox, hostValueMappingDetails.getFunctionality());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox, hostValueMappingDetails.getLanguage());
        selectWebElement(hostDataTextbox);
        enterValueToTxtField(hostDataTextbox,hostValueMappingDetails.getHostData());
        enterValueToTxtField(descriptionTextbox,hostValueMappingDetails.getDescription());
        selectWebElement(uploadfile);
        FileUploader fileUploader=new FileUploader();
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + hostValueMappingDetails.getWaveFile());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);
	}

	public void addwithoutDescription(HostValueMappingDetails hostValueMappingDetails) {
		selectWebElement(HostValueMappingTabs.get(1));
		selectWebElement(makeHostValueMappingChanges);
		selectWebElement(addNewHostValueMappingRecordBtn);
		waitForJqueryLoad(driver);		
		selectWebElement(functionalityDropdown);
        selectDropdownFromVisibleText(functionalityListbox, hostValueMappingDetails.getFunctionality());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox, hostValueMappingDetails.getLanguage());
        selectWebElement(hostDataTextbox);
        enterValueToTxtField(hostDataTextbox,hostValueMappingDetails.getHostData());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListbox,hostValueMappingDetails.getStatus());
        selectWebElement(uploadfile);
        FileUploader fileUploader=new FileUploader();
        fileUploader.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + hostValueMappingDetails.getWaveFile());
        selectWebElement(saveBtn);
        selectWebElement(cancelBtn);
	}

	public void addwithoutWaveFile(HostValueMappingDetails hostValueMappingDetails) {
		selectWebElement(HostValueMappingTabs.get(1));
		selectWebElement(makeHostValueMappingChanges);
		selectWebElement(addNewHostValueMappingRecordBtn);
		waitForJqueryLoad(driver);		
		selectWebElement(functionalityDropdown);
        selectDropdownFromVisibleText(functionalityListbox, hostValueMappingDetails.getFunctionality());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox, hostValueMappingDetails.getLanguage());
        selectWebElement(hostDataTextbox);
        enterValueToTxtField(hostDataTextbox,hostValueMappingDetails.getHostData());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListbox,hostValueMappingDetails.getStatus());
        enterValueToTxtField(descriptionTextbox,hostValueMappingDetails.getDescription());
        selectWebElement(uploadfile);
       selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);
	}

	public void clickonReject(String comment) {
		 selectWebElement(HostValueMappingTabs.get(1));
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        clickOn(rejectBtn);
	        selectWebElement(checkerReason);
	        enterValueToTxtField(checkerReason,comment);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        clickOn(approveYesBtn);				
	}

	public boolean VerifyMakeHostValueMappingChangeButton() {
		return makeHostValueMappingChanges.isDisplayed();

	}

	public boolean EditCancel(HostValueMappingDetails details) throws Exception {
		selectWebElement(HostValueMappingTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makeHostValueMappingChanges);
		Thread.sleep(1000);
		searchHostValueMappingRecord(details.getHostData());
		selectWebElement(editButton);
		waitForJqueryLoad(driver);
		selectWebElement(cancelBtn);
		if(editrowdata.get(3).getText().equals(details.getHostData()))
			return true;
		else
		return false;
	}

	public void EditHostValueMappingRecord(HostValueMappingDetails details) throws Exception {
		selectWebElement(HostValueMappingTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makeHostValueMappingChanges);
		Thread.sleep(1000);
		searchHostValueMappingRecord(details.getHostData());
		selectWebElement(editButton);
		waitForJqueryLoad(driver);		
		Thread.sleep(1000);
        enterValueToTxtField(descriptionTextbox,details.getUpdatedDescription());
        enterValueToTxtField(modifyReasonTextBox,details.getModifyReason());
        selectWebElement(saveBtn);

	}

	public boolean verifyAuditTrailUpdate(HostValueMappingDetails details, String Transaction,String Status) {
		boolean stat=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable();
        if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
            if(firstRowData.get("Status").equalsIgnoreCase(Status)){
                if(firstRowData.get("Function").equalsIgnoreCase("IvrHostMap")){
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
                }else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"RoleManagement");}
            }else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}
        }else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}
        return stat;
	}

	private boolean verifyUpdatedNewValues(HostValueMappingDetails details, Map<String, String> newvalues) {
		Boolean Status=false;
		if(newvalues.get("Functionality").equals(details.getFunctionality())) {
			if(newvalues.get("Language").equals(details.getLanguage())){
				if(newvalues.get("HostData").equals(details.getHostData())){
					if(newvalues.get("Status").equals(details.getStatus())){
						if(newvalues.get("Description").equals(details.getUpdatedDescription())){
							if(newvalues.get("WaveFile").equals(details.getWaveFile())) {	
								Status=true;
        					}
        					else System.out.println("WaveFile data mismatch");
    					}
    					else System.out.println("Description data mismatch");
					}
					else System.out.println("Status data mismatch");
				}
				else System.out.println("HostData data mismatch");
			}
			else System.out.println("Language data mismatch");
		}
		else {System.out.println("Functionality data mismatch");}
	return Status;
	}

	public boolean verifyTaskCompleteSuccessMessage() {
        return(getSuccessMessage().contains("Record submission for approval success. Your Request ID is :"));

	}

	public void EditRecordWithoutModifyReason(HostValueMappingDetails details) throws Exception {
		selectWebElement(HostValueMappingTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makeHostValueMappingChanges);
		Thread.sleep(1000);
		searchHostValueMappingRecord(details.getHostData());
		selectWebElement(editButton);
		waitForJqueryLoad(driver);		
		Thread.sleep(1000);
        enterValueToTxtField(descriptionTextbox,details.getUpdatedDescription());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);
	}

	public boolean DeleteCancel(HostValueMappingDetails details) throws Exception {
		selectWebElement(HostValueMappingTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makeHostValueMappingChanges);
		Thread.sleep(1000);
		searchHostValueMappingRecord(details.getHostData());
		selectWebElement(deleteButton);
		waitForJqueryLoad(driver);
		selectWebElement(noBtn);
		if(editrowdata.get(3).getText().equals(details.getHostData()))
			return true;
		else
		return false;
	}

	public void DeleteHostValueMappingRecord(HostValueMappingDetails details) throws Exception {
		selectWebElement(HostValueMappingTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makeHostValueMappingChanges);
		Thread.sleep(1000);
		searchHostValueMappingRecord(details.getHostData());
		Thread.sleep(1000);
		selectWebElement(deleteButton);
		Thread.sleep(1000);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtField(deleteReasonTextBox,details.getDeleteReason());
        selectWebElement(yesBtn);				
	}

	public boolean verifyAuditTrailDelete(HostValueMappingDetails hostValueMappingDetails, String Transaction,
			String Status) {
		boolean stat=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable();
        if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
            if(firstRowData.get("Status").equalsIgnoreCase(Status)){
                if(firstRowData.get("Function").equalsIgnoreCase("IvrHostMap")){
                       stat=true;
                }else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"RoleManagement");}
            }else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}
        }else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}
        return stat;
	}

	public void DeleteRecordWithoutModifyReason(HostValueMappingDetails details) throws Exception {
		selectWebElement(HostValueMappingTabs.get(1));
		Thread.sleep(1000);
		selectWebElement(makeHostValueMappingChanges);
		Thread.sleep(1000);
		searchHostValueMappingRecord(details.getHostData());
		Thread.sleep(1000);
		selectWebElement(deleteButton);
		Thread.sleep(1000);
        selectWebElement(deleteReasonTextBox);
        selectWebElement(yesBtn);		
        selectWebElement(noBtn);
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

		public void selectAddNewHostValueMapping() {
			selectWebElement(addNewHostValueMappingRecordBtn);
		}

	
}
