package com.tetherfi.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.tmac.AttributesDetails;

public class AttributesPage extends BasePage{

	public AttributesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".ibox-title h5")
	private WebElement attributes;

	@FindBy(xpath="//i[@class='far fa-abacus']")
	private WebElement AttributesLogo;

	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullScreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement restore;

	@FindBy(id="navbarheader")
	private WebElement header;

	@FindBy(xpath="//th[@class='k-header k-with-icon']")
	private List<WebElement> attributesPageHeader;

	@FindBy(css="th a[class='k-header-column-menu']")
	private List<WebElement> headersDropdown;

	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;

	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;

	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;

	@FindBy(xpath="//button[@id='create']")
	private WebElement addNewAttributesRecordButton;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel']")
	private WebElement addNewRecordCancelButton;

	@FindBy(xpath="//span[text()='Attributes']")
	private WebElement addNewAttributesWindowTitle;

	@FindBy(xpath="//input[@id='Name']")
	private WebElement NameTextBox;

	@FindBy(xpath="//span[@aria-owns='Category_listbox']")
	private WebElement CategoryListBox;

	@FindBy(xpath="//span[@aria-owns='Prioritylevel_listbox']")
	private WebElement priorityListBox;

	@FindBy(xpath="//input[@id='IsEnabled']")
	private WebElement isEnabledCheckBox;

	@FindBy(css="ul[id='Category_listbox'] li")
	private List<WebElement> categoryListBoxDropDown;

	@FindBy(css="ul[id='Prioritylevel_listbox'] li")
	private List<WebElement> priorityListBoxDropDown;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-primary k-grid-update']")
	private WebElement AddNewRecordSaveButton;

	@FindBy(css="#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;

	@FindBy(css=".toast-message")
	private WebElement successmsg;

	@FindBy(css = ".fa-search")
	private WebElement searchBtn;

	@FindBy(css = ".modal-body .form-inline .form-group .k-select")
	private List<WebElement> selectSearchCol;

	@FindBy(css="ul[id='1001sColumnName_listbox'] li")
	private List<WebElement> columnNameList;

	@FindBy(css="ul[id='1001sCriteria_listbox'] li")
	private List<WebElement> searchCriteriaDropDwn;

	@FindBy(id = "1001sTextToSearch")
	private WebElement searchTextBox;

	@FindBy(css = ".modal-footer .button-theme")
	private WebElement searchSearchBtn;

	@FindBy(css=".k-grid-content")
	private WebElement gridContent;
	
	@FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
	
	@FindBy(xpath="//i[@class='fas fa-sync fa-spin']")
    private WebElement clearsearch;

	@FindBy(xpath = "//a[@class='k-button k-button-icontext k-grid-edit']")
	private WebElement editButton;

	@FindBy(id="ModifyReason")
	private WebElement modifyReason;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel']")
	private WebElement editCancel;

	@FindBy(xpath="//table//tbody/tr/td[2]")
	private WebElement tablerow;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-primary k-grid-update']")
	private WebElement UpdateRecordSaveButton;

	@FindBy(id="export")
	private WebElement exportButton;

	@FindBy(xpath="//span[@class='k-input']")
	private WebElement pagerSize;

	@FindBy(css="a[aria-label='Go to the next page']")
	private WebElement nextPageIcon;

	@FindBy(id="tGrid")
	private WebElement auditGridContent;

	@FindBy(xpath="//a[text()='Name']")
	private WebElement gridName;
	 
    @FindBy(xpath="//div[@data-role='droptarget']")
    private WebElement dropTarget;
    
    @FindBy(xpath="//p[@class='k-reset']")
    private WebElement groupBy;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
    private WebElement groupByName;
    
    @FindBy(xpath="//a[@aria-label='Go to the last page']")
    private WebElement lastPageIcon;
    
    @FindBy(css=".k-pager-numbers .k-state-selected")
    private WebElement pageNumber;
    
    @FindBy(css="a[aria-label='Go to the first page']")
    private WebElement firstPageIcon;
    
    @FindBy(css="a[aria-label='Go to the previous page']")
    private WebElement previousPageIcon;
    
    @FindBy(css=".k-pager-sizes .k-icon")
    private WebElement pagerDropdown;
    
    @FindBy(css=".k-animation-container ul li")
    private List<WebElement> pageSizeListBox;
    
    @FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-CustomDelete']")
    private WebElement deleteButton;
    
    @FindBy(id="ModifyReason1")
    private WebElement deleteReason;
    
    @FindBy(id="noButton")
    private WebElement deleteNoButton;
    
    @FindBy(id="yesButton")
    private List<WebElement> deleteYesButton;
    
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
    
    @FindBy(xpath="//span[@class='k-widget k-textbox k-state-disabled']")
    private List<WebElement> searchText;
    
    @FindBy(xpath="//button[text()='Close']")
    private WebElement searchClose;
    
    @FindBy(xpath="//span[@aria-controls='1001sColumnName_listbox']")
    private WebElement selectSearchColumn;
    
    @FindBy(xpath="//span[@aria-owns='1001sCriteria_listbox']")
    private WebElement condition;
    
  
    


	public boolean isAttributesPageIsDisplayed() {
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		return attributes.isEnabled();
	}

	public boolean verifyLogoAndButtonPresence() {
		boolean status=false;
		if(isElementExist(AttributesLogo)){
			if(addNewAttributesRecordButton.isDisplayed()) {
				if(exportButton.isDisplayed()) {
					status=true;
				}
				else {System.out.println("exportButton is not Displayed");}
			}
			else {System.out.println("addNewAttributesRecordButton is not Displayed");}	
		}
		else {System.out.println("AttributesLogo is not Displayed");}
		return status;
	}

	public boolean maximizeWindow() {
		selectWebElement(maximize);
		waitForJqueryLoad(driver);
		if(fullScreen.isEnabled())
		{return true;}
		else
		{return false;}
	}

	public boolean minimizeWindow() {
		selectWebElement(restore);
		waitForJqueryLoad(driver);
		if(header.isDisplayed())
		{return true;}
		else
		{return false;}	
	}

	public boolean VerifyAttributesPageHeader() {
		waitForJqueryLoad(driver);
		List<String>ActualAttributePageHeadrs = new ArrayList<>();
		for(int i=0;i<attributesPageHeader.size();i++) {
			if(attributesPageHeader.get(i).isDisplayed()) {
				String HeaderName=attributesPageHeader.get(i).getText();
				ActualAttributePageHeadrs.add(HeaderName);
			}
			else {System.out.println("attributesPageHeader is not Displayed at Index"+i);}
		}
		List<String>ExpectedAttributePageHeadrs = new ArrayList<>();
		ExpectedAttributePageHeadrs.add("Name");
		ExpectedAttributePageHeadrs.add("Category");
		ExpectedAttributePageHeadrs.add("Priority");
		ExpectedAttributePageHeadrs.add("IsEnabled");
		ExpectedAttributePageHeadrs.add("Last Changed By");
		ExpectedAttributePageHeadrs.add("Last Changed On");
		System.out.println(ActualAttributePageHeadrs);
		System.out.println(ExpectedAttributePageHeadrs);
		if(ActualAttributePageHeadrs.equals(ExpectedAttributePageHeadrs))
			return true;
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

	public boolean VerifyColumnsHeadersEnable() {
		boolean status=false;
		WebElement ele=headersDropdown.get(0);
		if(ele.isDisplayed()) {
			try {
				selectWebElement(ele);
				Thread.sleep(1000);
				selectWebElement(headersColumns.get(2));
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			for(int i=3; i<headersColumns.size(); i++) {
				WebElement checkbox=headersColumns.get(i).findElement(By.tagName("input"));
				checkbox.click();
				if(checkbox.isSelected()) { 
				}
				else {
					checkbox.click();
				}
				for(WebElement ele1 : headersText) {
					if(ele1.getText().equals(headersColumns.get(i).getText())) {
						status=true;
						break;
					}
				}
				if(status) {
				}
				else {
					break;
				}
			}

		}


		return status;
	}

	public boolean verifycolumnsHeaderDisabled() {
		boolean status = false;
		WebElement ele = headersDropdown.get(1);
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

	public boolean addCancelButton(AttributesDetails details) throws Exception {
		String actualitems=items.getText();
		selectWebElement(addNewAttributesRecordButton);
		waitForJqueryLoad(driver);
		waitForJqueryLoad(driver);
		selectWebElement(NameTextBox);
		enterValueToTxtField(NameTextBox,details.getName());
		waitForJqueryLoad(driver);
		selectWebElement(CategoryListBox);
		selectDropdownFromVisibleText(categoryListBoxDropDown,details.getCategory());
		selectWebElement(priorityListBox);
		selectDropdownFromVisibleText(priorityListBoxDropDown,details.getCategory());
		selectCheckbox(isEnabledCheckBox);
		selectWebElement(addNewRecordCancelButton);
		if(actualitems.equals(items.getText()))
			return true;
		else
			return false;
	}

	public void AddNewAttributesRecordwithAllthefieldsBlank(AttributesDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewAttributesRecordButton);
		waitUntilWebElementIsVisible(addNewAttributesWindowTitle);
		selectWebElement(AddNewRecordSaveButton);
	}

	public void AddNewAttributesRecordwithOutName(AttributesDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewAttributesRecordButton);
		waitUntilWebElementIsVisible(addNewAttributesWindowTitle);
		waitForJqueryLoad(driver);
		selectWebElement(CategoryListBox);
		selectDropdownFromVisibleText(categoryListBoxDropDown,details.getCategory());
		selectWebElement(priorityListBox);
		selectDropdownFromVisibleText(priorityListBoxDropDown,details.getPriorityLevel());
		selectCheckbox(isEnabledCheckBox);
		selectWebElement(AddNewRecordSaveButton);
	}

	public void AddNewAttributesRecordwithOutCategoryListBox(AttributesDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewAttributesRecordButton);
		waitUntilWebElementIsVisible(addNewAttributesWindowTitle);
		selectWebElement(NameTextBox);
		enterValueToTxtField(NameTextBox,details.getName());
		selectWebElement(priorityListBox);
		selectDropdownFromVisibleText(priorityListBoxDropDown,details.getPriorityLevel());
		selectCheckbox(isEnabledCheckBox);
		selectWebElement(AddNewRecordSaveButton);
	}

	public void AddNewAttributesRecordwithOutPriorityListBox(AttributesDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewAttributesRecordButton);
		waitUntilWebElementIsVisible(addNewAttributesWindowTitle);
		selectWebElement(NameTextBox);
		enterValueToTxtField(NameTextBox,details.getName());
		waitForJqueryLoad(driver);
		selectWebElement(CategoryListBox);
		selectDropdownFromVisibleText(categoryListBoxDropDown,details.getCategory());
		selectCheckbox(isEnabledCheckBox);
		selectWebElement(AddNewRecordSaveButton);
	}

	public void VerifyAddNewRecordwithAllValidFields(AttributesDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewAttributesRecordButton);
		waitForJqueryLoad(driver);
		waitForJqueryLoad(driver);
		selectWebElement(NameTextBox);
		enterValueToTxtField(NameTextBox,details.getName());
		waitForJqueryLoad(driver);
		selectWebElement(CategoryListBox);
		selectDropdownFromVisibleText(categoryListBoxDropDown,details.getCategory());
		selectWebElement(priorityListBox);
		selectDropdownFromVisibleText(priorityListBoxDropDown,details.getPriorityLevel());
		selectCheckbox(isEnabledCheckBox);
		selectWebElement(AddNewRecordSaveButton);
	}

	public String VerifyMessage() {
		waitForJqueryLoad(driver);
		if(successmsg.isDisplayed())
			return successmsg.getText();
		else{return errorMsg.get(0).getText();}
	}

	public void searchAttributes(String name) throws Exception {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		enterValueToTxtField(searchTextBox,name);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
	}

	public boolean EditCancel(AttributesDetails details) throws Exception {
		searchAttributes(details.getName());
		selectWebElement(editButton);
		waitForJqueryLoad(driver);
		selectWebElement(priorityListBox);
		selectDropdownFromVisibleText(priorityListBoxDropDown,details.getUpdatedPriorityLevel());
		enterValueToTxtFieldWithoutClear(modifyReason,details.getModifyReason());
		selectWebElement(editCancel);
		waitForJqueryLoad(driver);
		if(tablerow.getText().equals(details.getName()))
		{return true;}
		else
			return false;

	}

	public void EditRecordWithoutModifyReason(AttributesDetails details) throws Exception {
		searchAttributes(details.getName());
		selectWebElement(editButton);
		waitForJqueryLoad(driver);
		selectWebElement(priorityListBox);
		selectDropdownFromVisibleText(priorityListBoxDropDown,details.getUpdatedPriorityLevel());
		selectWebElement(UpdateRecordSaveButton);
		selectWebElement(editCancel);
	}

	public void EditRecord(AttributesDetails details) throws Exception {
		searchAttributes(details.getName());
		selectWebElement(editButton);
		selectWebElement(priorityListBox);
		selectDropdownFromVisibleText(priorityListBoxDropDown,details.getUpdatedPriorityLevel());
		enterValueToTxtFieldWithoutClear(modifyReason,details.getModifyReason());
		selectWebElement(UpdateRecordSaveButton);
	}

	public boolean ExportToExcelButton(String filePath) {
		final File folder=new File(filePath);
		for(final File f : folder.listFiles()) {
			if(f.getName().startsWith("Attributes")) {
				f.delete();
			}
		}
		selectWebElement(exportButton);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath,"Attributes");
		return Status;
	}

	public boolean VerifyExportToExcelSheet(List<Map<String,String>>maplist){
		List<Map<String,String>>UI=getData();
		System.out.println(UI);
		System.out.println(maplist);
		if(UI.equals(maplist))
			return true;
		else
			return false;
	}

	private List<Map<String,String>> getData(){
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

	public boolean VerifyExportToExcelWithoutData(AttributesDetails details) throws Exception {
		searchAttributes(details.getName());
		waitForJqueryLoad(driver);
		selectWebElement(exportButton);
		waitForJqueryLoad(driver);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
			return false;
	}

	public void verifySortByAscending() {
		waitUntilWebElementIsVisible(auditGridContent);
		selectWebElement(gridName);
		waitForJqueryLoad(driver);
		selectWebElement(exportButton);
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void verifySortByDescending() {
		waitUntilWebElementIsVisible(auditGridContent);
		selectWebElement(gridName);
		selectWebElement(gridName);
		waitForJqueryLoad(driver);
		selectWebElement(exportButton);
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean groupBy() {
    	DragandDrop(gridName,dropTarget);
    	try {
    		Thread.sleep(1000);
    	}catch(InterruptedException e) {
    		e.printStackTrace();	
    	}
    	if(groupBy.getText().split(": ")[1].equals(groupByName.getText()))
    		return true;
    	else
    		return false;
    	
    }
    
    public boolean verifyArrowMoveForPreviousAndNextPage() throws Exception{
    	waitForJqueryLoad(driver);
        boolean status=false;
        if(!nextPageIcon.getAttribute("class").contains("k-state-disabled")){
        int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
        selectWebElement(nextPageIcon);
        Thread.sleep(1000);
        int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
        selectWebElement(previousPageIcon);
        Thread.sleep(1000);
        int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
        if(nextnumber==(pagenumber+1) && pagenumber==previousnumber){status=true;}
        }else{
            System.out.println("previous and next page icon disabled");status=true;
        }
        return status;
	}
	public boolean verifyArrowMoveForFirstAndLastPage() throws Exception{
		waitForJqueryLoad(driver);
        boolean status=false;
        if(!lastPageIcon.getAttribute("class").contains("k-state-disabled")){
            int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
            selectWebElement(lastPageIcon);
            Thread.sleep(1000);
            int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
            selectWebElement(firstPageIcon);
            Thread.sleep(1000);
            int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
            if(nextnumber>pagenumber && pagenumber==previousnumber){status=true;}
        }else{
            System.out.println("previous and next page icon disabled");status=true;
        }
        return status;
    }
	
	public boolean DeleteCancel(AttributesDetails details) throws Exception {
		searchAttributes(details.getName());
    	selectWebElement(deleteButton);
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	enterValueToTxtFieldWithoutClear(deleteReason,details.getDeleteReason());
    	selectWebElement(deleteNoButton);
    	waitForJqueryLoad(driver);
    	if(tablerow.getText().equals(details.getName()))
    			{return true;}
    	else
    		    return false;
    		
    }
 
 public void DeleteWithoutReason(AttributesDetails details) throws Exception {
	 searchAttributes(details.getName());
    	selectWebElement(deleteButton);
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	selectWebElement(deleteYesButton.get(0));
    	waitForJqueryLoad(driver);
    	selectWebElement(deleteNoButton);
    		
    }
 
 public void DeleteRecord(AttributesDetails details) throws Exception {
	 searchAttributes(details.getName());
    	selectWebElement(deleteButton);
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	enterValueToTxtFieldWithoutClear(deleteReason,details.getDeleteReason());
    	selectWebElement(deleteYesButton.get(0));
    		
    }
	public boolean verifyTotalNumberOfItemsPerPageDetails(){
		waitForJqueryLoad(driver);
        String item = items.getText();
        return item.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
    }
	
	public boolean verifyNumberOfItemsPerPage() {
		waitForJqueryLoad(driver);
        boolean status = false;
        try {
                int item = Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
                selectWebElement(pagerDropdown);
                Thread.sleep(1500);
                for (int i = 0; i < pageSizeListBox.size(); i++) {
                    if(Integer.valueOf(pageSizeListBox.get(i).getText())>item){continue;}
                    selectDropdownFromVisibleText(pageSizeListBox, pageSizeListBox.get(i).getText());
                    waitForJqueryLoad(driver);
                    int totalItems = Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
                    int pagersize = Integer.valueOf(pagerSize.getText());
                    int pages = (totalItems % pagersize == 0) ? item / pagersize : item / pagersize+1;
                    int totalRows=(gridContent.findElements(By.tagName("tr")).size());
                    selectWebElement(lastPageIcon);
                    waitForJqueryLoad(driver);
                    int lastPageNumber = Integer.valueOf(pageNumber.getText());
                    if (item == totalItems && pages == lastPageNumber&&totalRows==pagersize) {
                        status = true;
                    } else {System.out.println(items+":"+totalItems+"\t"+pages+":"+lastPageNumber+"\t"+totalRows+":"+pagersize);
                        status = false;
                        break;
                    }selectWebElement(pagerDropdown);Thread.sleep(1500);
                }
           
        } catch (Exception e) {
            e.printStackTrace();
        } return status;
    }

	public boolean clearAll(AttributesDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
		enterValueToTxtField(searchTextBox,details.getName());
	    selectWebElement(clearall);
	    Thread.sleep(2000);
			if(searchText.get(3).isDisplayed())
	        	return true;
	        else
			return false;
		}
	public boolean verifyclose() {
		selectWebElement(searchClose);
		if(gridContent.isDisplayed())
			return true;
		else
		return false;
	}
	
	public void searchwithoutextsearch(AttributesDetails details) throws Exception {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        selectWebElement(searchSearchBtn);	
        selectWebElement(searchClose);
	}
	
	public boolean verifyclearsearch() {
		selectWebElement(clearsearch);
		if(gridContent.isDisplayed())
			return true;
		else
		return false;
	}

	public boolean verifyinvalidsearchwithwrongdata(AttributesDetails details) throws Exception {
		searchAttributes(details.getName());
		if(norecords.isDisplayed())
			return true; 
			else
				return false;
	}
	
	 public boolean verifySearchIsnotEqualTo(String name) throws Exception {
		 boolean Status=false;
		 Map<String,String>map=new HashMap<String,String>();
		 map.put("Name", name);
		 selectWebElement(searchBtn);
		 selectWebElement(selectSearchColumn);
		 selectDropdownFromVisibleText(columnNameList,"Name");
		 Thread.sleep(2000);
		 selectWebElement(condition);
		 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
		 enterValueToTxtField(searchTextBox,name);		
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
	 
	 public boolean verifySearchContains(String name) throws Exception {
		 boolean Status=false;
		 Map<String,String>map=new HashMap<String,String>();
		 map.put("Name", name);
		 selectWebElement(searchBtn);
		 selectWebElement(selectSearchColumn);
		 selectDropdownFromVisibleText(columnNameList,"Name");
		 Thread.sleep(2000);
		 selectWebElement(condition);
		 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		 enterValueToTxtField(searchTextBox,name);		
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
	 
	 public boolean verifySearchDoesNotContains(String name) throws Exception {
		 boolean Status=false;
		 Map<String,String>map=new HashMap<String,String>();
		 map.put("Name", name);
		 selectWebElement(searchBtn);
		 selectWebElement(selectSearchColumn);
		 selectDropdownFromVisibleText(columnNameList,"Name");
		 Thread.sleep(2000);
		 selectWebElement(condition);
		 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not Contain");
		 enterValueToTxtField(searchTextBox,name);		
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
	 
	 public boolean verifySearchStartsWith(String name) throws Exception {
		 boolean Status=false;
		 Map<String,String>map=new HashMap<String,String>();
		 map.put("Name", name);
		 selectWebElement(searchBtn);
		 selectWebElement(selectSearchColumn);
		 selectDropdownFromVisibleText(columnNameList,"Name");
		 Thread.sleep(2000);
		 selectWebElement(condition);
		 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
		 enterValueToTxtField(searchTextBox,name);		
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
	 
	 public boolean verifySearchEndsWith(String name) throws Exception {
		 boolean Status=false;
		 Map<String,String>map=new HashMap<String,String>();
		 map.put("Name", name);
		 selectWebElement(searchBtn);
		 selectWebElement(selectSearchColumn);
		 selectDropdownFromVisibleText(columnNameList,"Name");
		 Thread.sleep(2000);
		 selectWebElement(condition);
		 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
		 enterValueToTxtField(searchTextBox,name);		
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
	 public List<Map<String, String>> gettable() throws Exception {
		 Thread.sleep(4000);
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
				for(int j=1;j<headers.size();j++){
					scrollToElement(headers.get(j));
					/*if(headers.get(j).getText().equals("Last Changed On")){
						col=cols.get(j).getText().substring(11);
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
				nextPageIcon.click();
				waitForJqueryLoad(driver);}
			}
				return arr;
		}
	 
	 public boolean verifyDataBase(String query) throws Exception {
		 List<Map<String,String>>database=database(query);
		 System.out.println(query);
		 System.out.println(database);
		 List<Map<String,String>>UI=gettable();
		 System.out.println(UI);
		 if(UI.equals(database))
			 return true;
		 else
			 return false;
		 
	 }


}

