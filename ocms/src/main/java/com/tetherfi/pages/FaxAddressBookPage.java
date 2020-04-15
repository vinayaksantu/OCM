package com.tetherfi.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.fax.FaxAddressBookDetails;
import com.tetherfi.model.fax.FaxSendersDetails;

public class FaxAddressBookPage extends BasePage {

	public FaxAddressBookPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css=".ibox-title h5")
    private WebElement faxAddressBook;
	
	@FindBy(css="ul[class='k-tabstrip-items k-reset'] li")
	private List<WebElement> faxAddressBookTab; 
	
	@FindBy(css="#createone")
	private WebElement addNewRecipientRecordBtn;
	
	@FindBy(css="#create")
	private WebElement addNewFaxAddressRecordBtn;
	
	@FindBy(id="FirstName")
	private WebElement firstNameTxtbox;
	
	@FindBy(id="LastName")
	private WebElement lastNameTxtbox;
	
	@FindBy(id="FaxNumber")
	private WebElement FaxNumberTxtbox;
	
    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css="#toast-container .toast-error .toast-message")
    private List<WebElement> errorMsg;
	
	@FindBy(css=".k-grid-update")
	private WebElement saveBtn;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private List<WebElement> exporttoexcel;
    
    @FindBy(xpath="//i[@class='far fa-address-book']")
    private WebElement FABImg;
    
    @FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;
	
	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;
	 
	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
	@FindBy(xpath="//span[@class='k-input']")
    private List<WebElement> pagerSize;
    
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
    
    @FindBy(id="tdrillgrid")
    private WebElement auditGridContent;
    
    @FindBy(id="tgrid")
    private WebElement auditGridAddressContent;
    
    @FindBy(xpath="//a[text()='First Name']")
    private WebElement FirstName;
    
    @FindBy(xpath="//a[text()='Last Name']")
    private WebElement LastName;
    
    @FindBy(xpath="//a[text()='Name']")
    private WebElement Name;
    
    @FindBy(xpath="//div[@data-role='droptarget']")
    private List<WebElement> droptarget;
    
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
    
    @FindBy(xpath="//button[text()='Close']")
    private WebElement searchClose;
    
    @FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
    
    @FindBy(xpath="//i[@class='fas fa-sync']")
    private WebElement clearsearch;
    
    @FindBy(css = ".k-grid-edit")
    private WebElement editButton;
      
    @FindBy(xpath="//*[@id=\"drillgrid\"]/div[4]/table/tbody/tr/td[1]/a[1]")
    private WebElement editButton1; 
    
    @FindBy(id = "ModifyReason")
    private WebElement ModifyReasonTextBox;
    
    @FindBy(css=".k-grid-cancel")
    private WebElement cancelBtn;
    
    @FindBy(css=".k-edit-form-container")
    private WebElement editFormContainer;
    
    @FindBy(css="div[style='display: block; z-index: 10002; opacity: 0.5;']")
    private WebElement deleteContainer;

    @FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;

    @FindBy(id = "ModifyReason1")
    private WebElement deleteReasonTextBox;

    @FindBy(id = "yesButton")
    private WebElement deleteYesBtn;

    @FindBy(id = "noButton")
    private WebElement deleteNoBtn;
    
    @FindBy(css=".search-link")
    private WebElement searchLink;

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
    
    @FindBy(css="#tdrillgrid .k-grid-content")
    private WebElement gridcontent;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[4]")
    private WebElement groupbyFirstName;
    
    @FindBy(css="span[aria-owns='FaxLine_listbox']")
    private WebElement FaxLineDropdown;
    
    @FindBy(css="ul[id='FaxLine_listbox'] li")
    private List<WebElement> FaxLineListbox;
    
    @FindBy(id="Name")
    private WebElement NameTextbox;
    
    @FindBy(css="input[aria-owns='RecipientIds_taglist RecipientIds_listbox']")
    private WebElement RecipientDropdown;
    
    @FindBy(css="ul[id='RecipientIds_listbox'] li")
    private List<WebElement> RecipientListbox;
    
    
    
    
	
	public boolean isFaxAddressBookPageDisplayed() {
		waitForLoad(driver);
        waitForJqueryLoad(driver);
		return faxAddressBook.isEnabled();
	}
	
	public void NavigateToRecipientTab() {
		selectWebElement(faxAddressBookTab.get(1));
	}

	public void addNewRecipientRecord(FaxAddressBookDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewRecipientRecordBtn);
		waitForJqueryLoad(driver);
		enterValueToTxtFieldWithoutClear(firstNameTxtbox,details.getfirstName());
		enterValueToTxtFieldWithoutClear(lastNameTxtbox,details.getlastName());
		enterValueToTxtFieldWithoutClear(FaxNumberTxtbox,details.getNumber());
		selectWebElement(saveBtn);
		try {
			selectWebElement(cancelBtn);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public String getSuccessMessage() {
		if(successmsg.isDisplayed())
			return successmsg.getText();
		else
			return errorMsg.get(0).getText();
	}
	
	public boolean verifylogo() {
		if(isElementExist(FABImg))
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

	public boolean verifyExportToExcel(String filePath) {
		final File folder = new File(filePath);
		for (final File f : folder.listFiles()) {
		    if (f.getName().startsWith("Recipient")) {
		        f.delete();
		    }
		}
		selectWebElement(exporttoexcel.get(1));
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath, "Recipient");
		return Status;
	}
	
	public boolean verifyAddressBookExportToExcel(String filePath) {
		final File folder = new File(filePath);
		for (final File f : folder.listFiles()) {
		    if (f.getName().startsWith("AddressBook")) {
		        f.delete();
		    }
		}
		selectWebElement(exporttoexcel.get(0));
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath, "AddressBook");
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
	
	public boolean verifyAddressBookexportToExcelSheet(List<Map<String, String>> maplist) {
		List<Map<String,String>> UI=getAddressBookdata(); 
		System.out.println(UI);
		System.out.println(maplist);
		if(UI.equals(maplist))
		return true;
		else
		return false;
	}
	
	private List<Map<String, String>> getAddressBookdata() {
		int item=Integer.valueOf(items.get(0).getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.get(0).getText());
        int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){
		waitUntilWebElementIsVisible(auditGridAddressContent);
		List<WebElement> rows=auditGridAddressContent.findElements(By.tagName("tr"));
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
			nextPageIcon.get(0).click();
			waitForJqueryLoad(driver);}
		}
			return arr;
	}

	private List<Map<String,String>> getdata(){
		int item=Integer.valueOf(items.get(1).getText().split("of ")[1].split(" items")[0]);
        int pagersize=Integer.valueOf(pagerSize.get(1).getText());
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
			nextPageIcon.get(1).click();
			waitForJqueryLoad(driver);}
		}
			return arr;
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
		int item=Integer.valueOf(items.get(1).getText().split("of ")[1].split(" items")[0]);
        int pagersize=Integer.valueOf(pagerSize.get(1).getText());
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
			nextPageIcon.get(1).click();
			waitForJqueryLoad(driver);}
		}
			return arr;
	}
	public boolean ExporttoExcelWithoutData(FaxSendersDetails details) throws Exception {
		searchFaxAddressBookRecord(details.getFaxLine());
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(exporttoexcel.get(1));
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
		return false;
	}

	private void searchFaxAddressBookRecord(String faxLine2) {
		// TODO Auto-generated method stub
		
	}

	public void SortByAscending() {
		selectWebElement(FirstName);
		selectWebElement(exporttoexcel.get(1));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(LastName);
		selectWebElement(LastName);
		selectWebElement(exporttoexcel.get(1));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void AddressBookSortByAscending() {
		selectWebElement(Name);
		selectWebElement(exporttoexcel.get(0));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void AddressBookSortByDescending() {
		selectWebElement(Name);
		selectWebElement(Name);
		selectWebElement(exporttoexcel.get(0));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
	                    int totalRows=(gridcontent.findElements(By.tagName("tr")).size());
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
        	 break;
	        }
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
        return status;
    }
    public boolean verifycolumnsHeaderEnabled(){
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
    
    public void dragRecipientColumntoGroup(String columnname) {
        List<WebElement> rows = auditGridContent.findElements(By.tagName("tr"));
        List<WebElement> columnHeaders = rows.get(0).findElements(By.tagName("th"));
        for (WebElement ele : columnHeaders) {
            if (ele.getText().equals(columnname)) {
                Actions builder = new Actions(driver);
                Action dragAndDrop = builder.clickAndHold(ele).moveToElement(droptarget.get(1)).release(droptarget.get(1)).build();
                dragAndDrop.perform();
            }
        }
    }

    public boolean verifyDragColumntoGroup(String colname, int i) {

        return (droptarget.get(i).getText().equals(colname));
    }
    
    public void dragAddressBookColumntoGroup(String columnname) {
        List<WebElement> rows = auditGridAddressContent.findElements(By.tagName("tr"));
        List<WebElement> columnHeaders = rows.get(0).findElements(By.tagName("th"));
        for (WebElement ele : columnHeaders) {
            if (ele.getText().equals(columnname)) {
                Actions builder = new Actions(driver);
                Action dragAndDrop = builder.clickAndHold(ele).moveToElement(droptarget.get(0)).release(droptarget.get(0)).build();
                dragAndDrop.perform();
            }
        }
    }

	public void clickonAddNewRecipientRecord() {
		selectWebElement(addNewRecipientRecordBtn);
	}

	public void clickOnCancelBtn(){
        if(isElementExist(cancelBtn)){selectWebElement(cancelBtn);}
    }
    public boolean verifyEditFormContainer(){
        return isElementExist(editFormContainer);
    }
    public void clickOnEditButton(){
        selectWebElement(editButton);
    }
    public void clickOnDeleteButton(){
        selectWebElement(deleteButton);
    }
    public void clickOnDeleteCancelBtn(){
        selectWebElement(deleteNoBtn);
    }
    public boolean verifyDeleteContainer(){
        return isElementExist(deleteContainer);
    }

	public void addNewEmptyRecipientRecord(FaxAddressBookDetails faxAddressBoookDetails) {
		selectWebElement(addNewRecipientRecordBtn);
		selectWebElement(saveBtn);
	}

	public String getMessage() {
		if(errorMsg.size()>0)
			return errorMsg.get(0).getText();
		else {return successmsg.getText();}
	}

	public void addNewRecipientWithoutFirstNameRecord(FaxAddressBookDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewRecipientRecordBtn);
		enterValueToTxtField(lastNameTxtbox,details.getlastName());
		enterValueToTxtField(FaxNumberTxtbox,details.getNumber());
		selectWebElement(saveBtn);
		selectWebElement(cancelBtn);

	}
	
	public void addNewRecipientWithoutLastNameRecord(FaxAddressBookDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewRecipientRecordBtn);
		enterValueToTxtField(firstNameTxtbox,details.getfirstName());
		enterValueToTxtField(FaxNumberTxtbox,details.getNumber());
		selectWebElement(saveBtn);	
		selectWebElement(cancelBtn);
	}

	public void addNewRecipientWithoutNumberRecord(FaxAddressBookDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewRecipientRecordBtn);
		enterValueToTxtField(firstNameTxtbox,details.getfirstName());
		enterValueToTxtField(lastNameTxtbox,details.getlastName());
		selectWebElement(saveBtn);	
		selectWebElement(cancelBtn);		
	}

	public void editRecipientRecord(FaxAddressBookDetails details) throws Exception {
		searchRecipientRecord(details);
		waitForJqueryLoad(driver);
		selectWebElement(editButton1);
		waitForJqueryLoad(driver);
		enterValueToTxtField(firstNameTxtbox,details.getUpdatedFirstName());
		enterValueToTxtFieldWithoutClear(ModifyReasonTextBox,details.getModifyReason());
		selectWebElement(saveBtn);	
	}

	private void searchRecipientRecord(FaxAddressBookDetails details) throws Exception {
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Fax Number");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtFieldWithoutClear(searchText.get(0),details.getNumber());
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridcontent);		
	}

	public void editRecipientWithoutModifyReason(FaxAddressBookDetails details) throws Exception {
		searchRecipientRecord(details);
		waitUntilWebElementIsVisible(editButton);
		selectWebElement(editButton);
		waitForJqueryLoad(driver);
		enterValueToTxtField(firstNameTxtbox,details.getUpdatedFirstName());
		selectWebElement(saveBtn);		
		selectWebElement(cancelBtn);
	}

	public void deleteRecipientWithoutDeleteReasonRecord(FaxAddressBookDetails details) throws Exception {
		searchRecipientRecord(details);
		waitUntilWebElementIsVisible(deleteButton);
		selectWebElement(deleteButton);
		waitForJqueryLoad(driver);
		selectWebElement(deleteYesBtn);		
		selectWebElement(deleteNoBtn);
		
	}

	public void deleteRecipientRecord(FaxAddressBookDetails details) throws Exception {
		searchRecipientRecord(details);
		waitUntilWebElementIsVisible(deleteButton);
		selectWebElement(deleteButton);
		waitForJqueryLoad(driver);
		enterValueToTxtFieldWithoutClear(deleteReasonTextBox,details.getDeleteReason());
		selectWebElement(deleteYesBtn);		
		
	}

	public void searchwithoutextsearch() {
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Fax Number");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        selectWebElement(searchBtn);		
	}
	
	public void searchAddressBookwithoutextsearch() {
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        selectWebElement(searchBtn);		
	}

	public boolean RecipientclearAll(FaxAddressBookDetails details) throws Exception {
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Fax Number");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtFieldWithoutClear(searchText.get(0),details.getNumber());
        selectWebElement(clearall);
		if(searchText.get(0).isEnabled())
        	return true;
        else
		return false;
	}

	public boolean verifyRecipientSearchclose() {
		selectWebElement(searchClose);
		if(gridcontent.isDisplayed())
			return true;
		else
		return false;
	}

	public boolean verifyinvalidRecipientsearchwithwrongdata(FaxAddressBookDetails faxAddressBoookDetails) throws Exception {
		searchRecipientRecord(faxAddressBoookDetails);
		Thread.sleep(1000);
		if(norecords.isDisplayed())
			return true; 
			else
				return false;
	}

	public boolean verifyRecipientclearsearch() {
		selectWebElement(clearsearch);
		if(auditGridContent.isDisplayed())
			return true;
		else
		return false;
	}

	public boolean AddressBookclearAll(FaxAddressBookDetails details) throws Exception {
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtFieldWithoutClear(searchText.get(0),details.getName());
        selectWebElement(clearall);
		if(searchText.get(0).isEnabled())
        	return true;
        else
		return false;
	}

	public boolean verifyAddressBookSearchclose() {
		selectWebElement(searchClose);
		if(auditGridAddressContent.isDisplayed())
			return true;
		else
		return false;
	}

	public boolean verifyinvalidAddresssearchwithwrongdata(FaxAddressBookDetails faxAddressBoookDetails) throws Exception {
		searchAddressRecord(faxAddressBoookDetails);
		Thread.sleep(1000);
		if(norecords.isDisplayed())
			return true; 
			else
				return false;
	}

	private void searchAddressRecord(FaxAddressBookDetails details) throws Exception {
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtFieldWithoutClear(searchText.get(0),details.getName());
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(auditGridAddressContent);		
	}

	public boolean verifyAddressclearsearch() {
		selectWebElement(clearsearch);
		if(auditGridAddressContent.isDisplayed())
			return true;
		else
		return false;
	}

	public void addNewAddressBookRecord(FaxAddressBookDetails details) throws Exception {
		selectWebElement(addNewFaxAddressRecordBtn);
		waitForJqueryLoad(driver);
		selectWebElement(FaxLineDropdown);
		selectDropdownFromVisibleText(FaxLineListbox,details.getFaxLine());
		enterValueToTxtField(NameTextbox,details.getName());
		selectWebElement(RecipientDropdown);
		selectMultipleDropdownFromVisibleText(RecipientListbox,details.getRecipient());
		selectWebElement(RecipientDropdown);
		selectMultipleDropdownFromVisibleText(RecipientListbox,details.getRecipient1());
		selectWebElement(saveBtn);
		try {
			selectWebElement(cancelBtn);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}

	public void addNewEmptyRecord(FaxAddressBookDetails faxAddressBoookDetails) {
		selectWebElement(addNewFaxAddressRecordBtn);
		waitForJqueryLoad(driver);
		selectWebElement(saveBtn);
		try {
			selectWebElement(cancelBtn);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}				
	}

	public void addNewAddressBookRecordWithoutFaxline(FaxAddressBookDetails details) throws Exception {
		selectWebElement(addNewFaxAddressRecordBtn);
		waitForJqueryLoad(driver);
		enterValueToTxtField(NameTextbox,details.getName());
		selectWebElement(RecipientDropdown);
		selectMultipleDropdownFromVisibleText(RecipientListbox,details.getRecipient());
		selectWebElement(RecipientDropdown);
		selectMultipleDropdownFromVisibleText(RecipientListbox,details.getRecipient1());
		selectWebElement(saveBtn);
		try {
			selectWebElement(cancelBtn);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}				
	}

	public void addNewAddressBookRecordWithoutName(FaxAddressBookDetails details) {
		selectWebElement(addNewFaxAddressRecordBtn);
		waitForJqueryLoad(driver);
		selectWebElement(FaxLineDropdown);
		selectDropdownFromVisibleText(FaxLineListbox,details.getFaxLine());
		selectWebElement(RecipientDropdown);
		selectMultipleDropdownFromVisibleText(RecipientListbox,details.getRecipient());
		selectWebElement(RecipientDropdown);
		selectMultipleDropdownFromVisibleText(RecipientListbox,details.getRecipient1());
		selectWebElement(saveBtn);
		try {
			selectWebElement(cancelBtn);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}				
	}

	public void addNewAddressBookRecordWithoutRecipients(FaxAddressBookDetails details) throws Exception {
		selectWebElement(addNewFaxAddressRecordBtn);
		waitForJqueryLoad(driver);
		selectWebElement(FaxLineDropdown);
		selectDropdownFromVisibleText(FaxLineListbox,details.getFaxLine());
		enterValueToTxtField(NameTextbox,details.getName());
		selectWebElement(saveBtn);
		try {
			selectWebElement(cancelBtn);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}				
	}

	public void editAddressBookRecord(FaxAddressBookDetails details) throws Exception {
		searchAddressRecord(details);
		waitUntilWebElementIsVisible(editButton);
		selectWebElement(editButton);
		waitForJqueryLoad(driver);
		enterValueToTxtField(NameTextbox,details.getUpdatedName());
		enterValueToTxtFieldWithoutClear(ModifyReasonTextBox,details.getModifyReason());
		selectWebElement(saveBtn);			
	}

	public void editAddressBookWithoutModifyReason(FaxAddressBookDetails details) throws Exception {
		searchAddressRecord(details);
		waitUntilWebElementIsVisible(editButton);
		selectWebElement(editButton);
		waitForJqueryLoad(driver);
		enterValueToTxtField(NameTextbox,details.getUpdatedName());
		selectWebElement(saveBtn);		
		selectWebElement(cancelBtn);
	}

	public void deleteWithoutDeleteReason(FaxAddressBookDetails details) throws Exception {
		searchAddressRecord(details);
		waitUntilWebElementIsVisible(deleteButton);
		selectWebElement(deleteButton);
		waitForJqueryLoad(driver);
		selectWebElement(deleteYesBtn);			
	}

	public void deleteRecord(FaxAddressBookDetails details) throws Exception {
		searchAddressRecord(details);
		waitUntilWebElementIsVisible(deleteButton);
		selectWebElement(deleteButton);
		waitForJqueryLoad(driver);
		enterValueToTxtFieldWithoutClear(deleteReasonTextBox,details.getDeleteReason());
		selectWebElement(deleteYesBtn);				
	}
}
