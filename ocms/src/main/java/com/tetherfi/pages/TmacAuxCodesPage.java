package com.tetherfi.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.tmac.TmacAuxCodesDetails;

public class TmacAuxCodesPage extends BasePage {

	public TmacAuxCodesPage(WebDriver driver) {super(driver);}
	
	@FindBy(css=".ibox-title h5")
	private WebElement tmacAuxCodes;
	
	@FindBy(xpath="//i[@class='fa fa-bar-chart']")
	private List<WebElement> TAUXImg;
	
	@FindBy(xpath="//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")
	private List<WebElement> tablerecord;
	
	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;
	
	@FindBy(xpath="//span[@class='k-input']")
    private WebElement pagerSize;
	
	@FindBy(id="navbarheader")
	private WebElement header;

	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
	@FindBy(id="create")
	private WebElement addNewTmacAuxCodeRecord;
	
	@FindBy(xpath="//input[@class='k-formatted-value k-input' and @placeholder='Enter Value']")
	private WebElement value;
	
    @FindBy(xpath="//span[@aria-label='Increase value']")
    private List<WebElement> data;
	
	@FindBy(id="Value")
	private WebElement valueText;
	
	@FindBy(id="Name")
	private WebElement name;
	
	@FindBy(id="Code")
	private WebElement code;
	
	@FindBy(css="span[aria-owns='Display_listbox']")
	private WebElement statusDropDown;
	
	@FindBy(css="ul[id='Display_listbox'] li")
	private List<WebElement> statusDropDownList;
	
	@FindBy(css=" .k-grid-update")
	private WebElement saveButton;
	
	@FindBy(css=" .k-grid-cancel")
	private WebElement cancelButton;
	
	@FindBy(css="#toast-container .toast-error .toast-message")
    private List<WebElement> errorMsg;
    
    @FindBy(className="toast-message")
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
    
    @FindBy(xpath = "//a[@class='k-button k-button-icontext k-grid-edit']")
    private WebElement editButton;
    
    @FindBy(id="ModifyReason")
    private WebElement modifyReason;
    
    @FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel']")
    private WebElement editCancel;
	
    @FindBy(xpath="//table//tbody/tr/td[3]")
    private WebElement tablerow;
    
    @FindBy(id="export")
    private WebElement exportButton;
    
    @FindBy(id="tGrid")
    private WebElement auditGridContent;
    
    @FindBy(css="a[aria-label='Go to the next page']")
    private WebElement nextPageIcon;
    
    @FindBy(xpath="//a[text()='Name']")
    private WebElement gridName;
    
    @FindBy(xpath="//div[@data-role='droptarget']")
    private WebElement dropTarget;
    
    @FindBy(xpath="//p[@class='k-reset']")
    private WebElement groupBy;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[4]")
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
    
    @FindBy(xpath="//a[@title='Edit Column Settings']")
    private List<WebElement> headersDropdown;
    
    @FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
    private List<WebElement> headersColumns;
    
    @FindBy(css="th a[class='k-link']")
    private List<WebElement> headersText;
    
    @FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-CustomDelete']")
    private WebElement deleteButton;
    
    @FindBy(id="ModifyReasonForMultiToaster")
    private WebElement deleteReason;
    
    @FindBy(id="noButtonforAux")
    private WebElement deleteNoButton;
    
    @FindBy(id="yesButton")
    private List<WebElement> deleteYesButton;
    
    @FindBy(xpath="//span[@aria-controls='1001sColumnName_listbox']")
    private WebElement selectSearchColumn;
    
    @FindBy(xpath="//span[@aria-owns='1001sCriteria_listbox']")
    private WebElement condition;
    
    
    
   
    
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean isTmacAuxCodesPageIsDisplayed() {
		waitForLoad(driver);
        waitForJqueryLoad(driver);
        return tmacAuxCodes.isEnabled();
	}
	
	public boolean verifylogo() 
	{
		if(isElementExist(TAUXImg.get(1))) 
		{return true;}
		else return false;
	}
	
	public boolean verifygridcontent() {
		int size=tablerecord.size();
		System.out.println(+size);
		String item[]=(items.getText()).split("\\s+");
		int itemno=Integer.parseInt(item[2]);
		System.out.println(itemno);
		if(itemno==size)
			return true;
		else 
			return false;}
	
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
		return true;
		else 
			return false;}
	
	public boolean addCancelButton(TmacAuxCodesDetails details) throws Exception {
		String actualItems=items.getText();
		selectWebElement(addNewTmacAuxCodeRecord);
		Thread.sleep(1000);
		selectWebElement(value);
		enterValueToTxtBox1(valueText,details.getValue());
		enterValueToTxtField(name,details.getName());
		enterValueToTxtField(code,details.getCode());
		selectWebElement(statusDropDown);
		selectDropdownFromVisibleText(statusDropDownList,details.getStatus());
		selectWebElement(cancelButton);
		if(actualItems.equals(items.getText()))
		  return true;
		else
		  return false;	
	}
	
	public void addRecordWithoutValue(TmacAuxCodesDetails details) throws Exception {
		selectWebElement(addNewTmacAuxCodeRecord);
		Thread.sleep(1000);
		enterValueToTxtField(name,details.getName());
		enterValueToTxtField(code,details.getCode());
		selectWebElement(statusDropDown);
		selectDropdownFromVisibleText(statusDropDownList,details.getStatus());
		selectWebElement(saveButton);
		selectWebElement(cancelButton);
	}
	
	public void addRecordWithoutName(TmacAuxCodesDetails details) throws Exception {
		selectWebElement(addNewTmacAuxCodeRecord);
		Thread.sleep(1000);
		selectWebElement(value);
		enterValueToTxtBox1(valueText,details.getValue());
		enterValueToTxtField(code,details.getCode());
		selectWebElement(statusDropDown);
		selectDropdownFromVisibleText(statusDropDownList,details.getStatus());
		selectWebElement(saveButton);
		selectWebElement(cancelButton);
	}
	
	public void addRecordWithoutCode(TmacAuxCodesDetails details) throws Exception {
		selectWebElement(addNewTmacAuxCodeRecord);
		Thread.sleep(1000);
		selectWebElement(value);
		enterValueToTxtBox1(valueText,details.getValue());
		enterValueToTxtField(name,details.getName());
		selectWebElement(statusDropDown);
		selectDropdownFromVisibleText(statusDropDownList,details.getStatus());
		selectWebElement(saveButton);
		selectWebElement(cancelButton);
	}
	
	public void addRecordWithoutStatus(TmacAuxCodesDetails details) throws Exception {
		selectWebElement(addNewTmacAuxCodeRecord);
		Thread.sleep(1000);
		selectWebElement(value);
		enterValueToTxtField(valueText,details.getValue());
		enterValueToTxtField(name,details.getName());
		enterValueToTxtField(code,details.getCode());
		selectWebElement(saveButton);
		selectWebElement(cancelButton);
	}
	
	public void addNewTmacAuxCodesRecord(TmacAuxCodesDetails details) throws Exception {
		selectWebElement(addNewTmacAuxCodeRecord);
		Thread.sleep(2000);
		selectWebElement(value);
		enterValueToTxtBox1(valueText,details.getValue());
		enterValueToTxtField(name,details.getName());
		enterValueToTxtField(code,details.getCode());
		selectWebElement(statusDropDown);
		selectDropdownFromVisibleText(statusDropDownList,details.getStatus());
		selectWebElement(saveButton);
	}
	
	
	public boolean verifymessage() {
		waitUntilWebElementIsVisible(successmsg);
    	if(successmsg.getText().contains("Successfully"))
		return true;
		else
		{return false;}
    }
	
    public boolean errormessage() {
	waitUntilWebElementListIsVisible(errorMsg);
	if(errorMsg.size()>0)
		return false;
	else 
		return true;
    }
    
    public void searchTmacAuxCodes(String name) throws Exception {
        selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchCol.get(1));
        Thread.sleep(4000);
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
        enterValueToTxtField(searchTextBox,name);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    
    public boolean EditCancel(TmacAuxCodesDetails details) throws Exception {
    	searchTmacAuxCodes(details.getName());
    	selectWebElement(editButton);
    	waitForJqueryLoad(driver);
    	enterValueToTxtField(name,details.getUpdatedName());
    	selectWebElement(statusDropDown);
    	selectDropdownFromVisibleText(statusDropDownList,details.getUpdatedStatus());
    	enterValueToTxtFieldWithoutClear(modifyReason,details.getModifyReason());
    	selectWebElement(editCancel);
    	waitForJqueryLoad(driver);
    	if(tablerow.getText().equals(details.getName()))
    			{return true;}
    	else
    		    return false;
    		
    }
    
    public void EditRecordWithoutModifyReason(TmacAuxCodesDetails details) throws Exception {
    	searchTmacAuxCodes(details.getName());
    	selectWebElement(editButton);
    	waitForJqueryLoad(driver);
    	enterValueToTxtField(name,details.getUpdatedName());
    	selectWebElement(statusDropDown);
    	selectDropdownFromVisibleText(statusDropDownList,details.getUpdatedStatus());
    	selectWebElement(saveButton);
    	selectWebElement(editCancel);
    }
    
    public void EditRecord(TmacAuxCodesDetails details) throws Exception {
    	searchTmacAuxCodes(details.getName());
    	selectWebElement(editButton);
    	waitForJqueryLoad(driver);
    	enterValueToTxtBox1(name,details.getUpdatedName());
    	selectWebElement(statusDropDown);
    	selectDropdownFromVisibleText(statusDropDownList,details.getUpdatedStatus());
    	enterValueToTxtFieldWithoutClear(modifyReason,details.getModifyReason());
    	selectWebElement(saveButton);
    }
    
    public boolean ExportToExcelButton(String filePath) {
    	final File folder=new File(filePath);
    	for(final File f : folder.listFiles()) {
    		if(f.getName().startsWith("Agent Aux Codes")) {
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
    	Boolean Status=verifyExportPageFileDownload(filePath,"Agent Aux Codes");
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
	
    private List<Map<String,String>> getData() {
    	int item=Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
    	int pagersize=Integer.valueOf(pagerSize.getText());
    	int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
    	List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
    	for(int k=0;k<=pages;k++) {
    		waitUntilWebElementIsVisible(auditGridContent);
    		List<WebElement>rows=auditGridContent.findElements(By.tagName("tr"));
    		List<WebElement>headers=rows.get(0).findElements(By.tagName("th"));
    		String col=null;
    		for(int i=1;i<rows.size();i++) {
    			Map<String,String> map=new HashMap<String,String>();
    			  List<WebElement>cols=rows.get(i).findElements(By.tagName("td"));
    			  for(int j=1;j<headers.size();j++) {
    				  scrollToElement(headers.get(j));
    				  System.out.println(headers.get(j).getText());
    				  col=cols.get(j).getText();
    				  map.put(headers.get(j).getText(),col);
    				  }
    			  map.remove("");
    			  arr.add(map);
    		}
    		if(k!=pages) {
    			nextPageIcon.click();
    			waitForJqueryLoad(driver);
    		}
    		
    	}
    	return arr;
    }
    
    public boolean VerifyExportToExcelWithoutData(TmacAuxCodesDetails details) throws Exception {
    	searchTmacAuxCodes(details.getName());
    	waitForJqueryLoad(driver);
    	selectWebElement(exportButton);
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
    	if(groupBy.getText().split(":")[1].equals(groupByName.getText()))
    		return true;
    	else
    		return false;
    	
    }
    
    public boolean verifyArrowMoveForPreviousAndNextPage(){
        boolean status=false;
        if(!nextPageIcon.getAttribute("class").contains("k-state-disabled")){
        int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
        selectWebElement(nextPageIcon);
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
        selectWebElement(previousPageIcon);
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
            try {
    			Thread.sleep(3000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
            int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
            selectWebElement(firstPageIcon);
            try {
    			Thread.sleep(3000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
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
	
	public boolean verifyNumberOfItemsPerPage() {
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
    
	 public boolean DeleteCancel(TmacAuxCodesDetails details) throws Exception {
	    	searchTmacAuxCodes(details.getName());
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
	 
	 public void DeleteWithoutReason(TmacAuxCodesDetails details) throws Exception {
	    	searchTmacAuxCodes(details.getName());
	    	selectWebElement(deleteButton);
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	selectWebElement(deleteYesButton.get(1));
	    	waitForJqueryLoad(driver);
	    	selectWebElement(deleteNoButton);
	    		
	    }
	 
	 public void DeleteRecord(TmacAuxCodesDetails details) throws Exception {
	    	searchTmacAuxCodes(details.getName());
	    	selectWebElement(deleteButton);
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	enterValueToTxtFieldWithoutClear(deleteReason,details.getDeleteReason());
	    	selectWebElement(deleteYesButton.get(1));
	    		
	    }
	 
	 public boolean verifySearchIsnotEqualTo(String code) throws Exception {
		 boolean Status=false;
		 Map<String,String>map=new HashMap<String,String>();
		 map.put("Code", code);
		 selectWebElement(searchBtn);
		 selectWebElement(selectSearchColumn);
		 selectDropdownFromVisibleText(columnNameList,"Code");
		 selectWebElement(condition);
		 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
		 enterValueToTxtField(searchTextBox,code);		
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
	 
	 public boolean verifySearchContains(String code) throws Exception {
		 boolean Status=false;
		 Map<String,String>map=new HashMap<String,String>();
		 map.put("Code", code);
		 selectWebElement(searchBtn);
		 selectWebElement(selectSearchColumn);
		 selectDropdownFromVisibleText(columnNameList,"Code");
		 selectWebElement(condition);
		 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		 enterValueToTxtField(searchTextBox,code);		
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
	 
	 public boolean verifySearchDoesNotContains(String code) throws Exception {
		 boolean Status=false;
		 Map<String,String>map=new HashMap<String,String>();
		 map.put("Code", code);
		 selectWebElement(searchBtn);
		 selectWebElement(selectSearchColumn);
		 selectDropdownFromVisibleText(columnNameList,"Code");
		 selectWebElement(condition);
		 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not Contain");
		 enterValueToTxtField(searchTextBox,code);		
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
	 
	 public boolean verifySearchStartsWith(String code) throws Exception {
		 boolean Status=false;
		 Map<String,String>map=new HashMap<String,String>();
		 map.put("Code", code);
		 selectWebElement(searchBtn);
		 selectWebElement(selectSearchColumn);
		 selectDropdownFromVisibleText(columnNameList,"Code");
		 selectWebElement(condition);
		 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
		 enterValueToTxtField(searchTextBox,code);		
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
	 
	 public boolean verifySearchEndsWith(String code) throws Exception {
		 boolean Status=false;
		 Map<String,String>map=new HashMap<String,String>();
		 map.put("Code", code);
		 selectWebElement(searchBtn);
		 selectWebElement(selectSearchColumn);
		 selectDropdownFromVisibleText(columnNameList,"Code");
		 selectWebElement(condition);
		 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
		 enterValueToTxtField(searchTextBox,code);		
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
	 
	 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
