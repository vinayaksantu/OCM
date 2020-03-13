package com.tetherfi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.model.fax.FaxSendersDetails;
import com.tetherfi.utility.FileUploader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaxSendersPage extends BasePage{
    public FaxSendersPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css=".ibox-title h5")
    private WebElement faxSenders;

    @FindBy(id="create")
    private WebElement addNewFaxSendersRcrdBtn;

    @FindBy(css=".k-numerictextbox")
    private List<WebElement> faxLineTextFields;

    @FindBy(css="span[aria-owns='DNIS_listbox']")
    private WebElement faxLineDropdown;

    @FindBy(css="ul[id='DNIS_listbox'] li")
    private List<WebElement> faxLineListBox;

    @FindBy(id="Name")
    private WebElement nameTextBox ;

    @FindBy(id="FaxNumber")
    private WebElement faxNumberTextBox;

    @FindBy(css="span[aria-owns=Type_listbox")
    private WebElement senderTypeDropdown;

    @FindBy(css="ul[id='Type_listbox'] li")
    private List<WebElement> sendersTypeListBox;

    @FindBy(css=".k-grid-update")
    private WebElement faxSendersSaveButton;
    
    @FindBy(css=".k-grid-cancel")
    private WebElement cancelBtn;
    
    @FindBy(xpath="//tbody/tr/td[4]")
    private WebElement rowdata;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css = "#toast-container .toast-error .toast-message")
    private List<WebElement> errorMsg;

    @FindBy(css = ".fa-search")
    private WebElement searchBtn;

    @FindBy(css = ".modal-body .form-inline .form-group .k-select")
    private List<WebElement> selectSearchCol;

    @FindBy(css="ul[id='1001sColumnName_listbox'] li")
    private List<WebElement> columnNameList;

    @FindBy(css = "#1001sFormDropDown .k-i-arrow-60-down")
    private List<WebElement> selectSearchCriteria;

    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchCriteriaDropDwn;

    @FindBy(id = "1001sTextToSearch")
    private WebElement searchTextBox;

    @FindBy(css = "#1001sAddButton .k-i-add")
    private WebElement searchAddCriteriaBtn;

    @FindBy(css = "#1001sCloseButton .k-i-close")
    private WebElement searchRemoveFilterBtn;

    @FindBy(css = ".modal-footer .k-button")
    private WebElement searchCloseBtn;

    @FindBy(css = ".modal-footer .button-danger-theme")
    private WebElement searchClearAllBtn;

    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;

    @FindBy(css=".k-grid-content")
    private WebElement gridContent;

    @FindBy(css = ".k-grid-edit")
    private WebElement editButton;

    @FindBy(id = "ModifyReason")
    private WebElement modifyReasonTextBox;

    @FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;

    @FindBy(id = "ModifyReason1")
    private WebElement deleteReasonTextBox;

    @FindBy(id = "yesButton")
    private WebElement deleteYesBtn;
    
    @FindBy(id = "noButton")
    private WebElement deleteNoBtn;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
    
    @FindBy(xpath="//i[@class='fas fa-users']")
    private WebElement FSImg;
    
    @FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;
	
	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;
	 
	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
	@FindBy(xpath="//span[@class='k-input']")
    private WebElement pagerSize;
    
    @FindBy(css="a[aria-label='Go to the next page']")
    private WebElement nextPageIcon;
    
    @FindBy(xpath="//p[@class='k-reset']")
    private WebElement groupby;
    
    @FindBy(css="a[aria-label='Go to the first page']")
    private WebElement firstPageIcon;
    
    @FindBy(css="a[aria-label='Go to the previous page']")
    private WebElement previousPageIcon;
    
    @FindBy(css=".k-pager-numbers .k-state-selected")
    private WebElement pageNumber;
    
    @FindBy(css="a[aria-label='Go to the last page']")
    private WebElement lastPageIcon;
    
    @FindBy(css=".k-pager-sizes .k-icon")
    private WebElement pagerDropdown;
    
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
	private WebElement items;
    
    @FindBy(id="navbarheader")
	private WebElement header;
    
    @FindBy(xpath="//span[@class='k-icon k-i-arrow-60-right k-menu-expand-arrow']")
    private WebElement coloumnarrow;
    
    @FindBy(id="grid")
    private WebElement auditGridContent;
    
    @FindBy(xpath="//a[text()='Fax Line']")
    private WebElement FaxLine;
    
    @FindBy(xpath="//a[text()='Name']")
    private WebElement Name;
    
    @FindBy(xpath="//a[text()='Type']")
    private WebElement Type;
    
    @FindBy(xpath="//div[@data-role='droptarget']")
    private WebElement droptarget;
    
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
    
    @FindBy(xpath="//button[text()='Close']")
    private WebElement searchClose;
    
    @FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
    
    @FindBy(xpath="//i[@class='fas fa-sync']")
    private WebElement clearsearch;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
    private WebElement groupbyFaxLine;
    
    @FindBy(xpath="//div[@class='k-button k-upload-button']")
    private WebElement selectcsvfile;

    @FindBy(id="createtwo")
    private WebElement bulkupload;
    
    @FindBy(xpath="//span[@class='k-file-invalid-extension-wrapper']")
    private WebElement errormsgcolor;

    public boolean isFaxSendersPageDisplayed() {
           waitForLoad(driver);
            waitForJqueryLoad(driver);
            return faxSenders.isEnabled();
        }

    public void addNewFaxSendersRecord(FaxSendersDetails faxSendersDetails) throws Exception {
        selectWebElement(addNewFaxSendersRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(faxLineDropdown);
        selectDropdownFromVisibleText(faxLineListBox,faxSendersDetails.getFaxLine());
        selectWebElement(nameTextBox);
        enterValueToTxtFieldWithoutClear(nameTextBox,faxSendersDetails.getName());
        selectWebElement(faxNumberTextBox);
        enterValueToTxtField(faxNumberTextBox,faxSendersDetails.getFaxNumber());
        selectWebElement(senderTypeDropdown);
        selectDropdownFromVisibleText(sendersTypeListBox,faxSendersDetails.getSenderType());
        selectWebElement(faxSendersSaveButton);
    }
    
    public boolean AddCancelRecord(FaxSendersDetails faxSendersDetails) throws Exception {
		String actualitems=items.getText();
		selectWebElement(addNewFaxSendersRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(faxLineDropdown);
        selectDropdownFromVisibleText(faxLineListBox,faxSendersDetails.getFaxLine());
        selectWebElement(nameTextBox);
        enterValueToTxtFieldWithoutClear(nameTextBox,faxSendersDetails.getName());
        selectWebElement(faxNumberTextBox);
        enterValueToTxtField(faxNumberTextBox,faxSendersDetails.getFaxNumber());
        selectWebElement(senderTypeDropdown);
        selectDropdownFromVisibleText(sendersTypeListBox,faxSendersDetails.getSenderType());
        selectWebElement(cancelBtn);
        if(items.getText().equals(actualitems))
        	return true;
        else
		return false;
	}
    public void searchFaxSendersRecord(String faxLine) throws Exception {
        selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Fax Line");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,faxLine);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public void editFaxSendersRecord(FaxSendersDetails faxSendersDetails) throws Exception {
        searchFaxSendersRecord(faxSendersDetails.getFaxLine());
        selectWebElement(editButton);
        selectWebElement(nameTextBox);
        enterValueToTxtField(nameTextBox,faxSendersDetails.getUpdatedName());
        selectWebElement(senderTypeDropdown);
        selectDropdownFromVisibleText(sendersTypeListBox,faxSendersDetails.getUpdatedSenderType());
        selectWebElement(modifyReasonTextBox);
        enterValueToTxtFieldWithoutClear(modifyReasonTextBox,faxSendersDetails.getModifyReason());
        selectWebElement(faxSendersSaveButton);
    }
    
    public boolean EditCancelRecord(FaxSendersDetails faxSendersDetails) throws Exception {
    	searchFaxSendersRecord(faxSendersDetails.getFaxLine());
        selectWebElement(editButton);
        selectWebElement(nameTextBox);
        enterValueToTxtField(nameTextBox,faxSendersDetails.getUpdatedName());
        selectWebElement(senderTypeDropdown);
        selectDropdownFromVisibleText(sendersTypeListBox,faxSendersDetails.getUpdatedSenderType());
        selectWebElement(modifyReasonTextBox);
        enterValueToTxtFieldWithoutClear(modifyReasonTextBox,faxSendersDetails.getModifyReason());
        selectWebElement(cancelBtn);
        if(rowdata.getText().equals(faxSendersDetails.getFaxNumber()))
        		return true;
        else
		return false;
	}
    
    public void editFaxSendersInvalidRecord(FaxSendersDetails faxSendersDetails) throws Exception {
    	searchFaxSendersRecord(faxSendersDetails.getFaxLine());
        selectWebElement(editButton);
        selectWebElement(nameTextBox);
        nameTextBox.clear();
        selectWebElement(senderTypeDropdown);
        selectDropdownFromVisibleText(sendersTypeListBox,faxSendersDetails.getUpdatedSenderType());
        selectWebElement(faxSendersSaveButton);
        selectWebElement(cancelBtn);		
	}

    public void deleteFaxSendersRecord(FaxSendersDetails faxSendersDetails) throws Exception {
        searchFaxSendersRecord(faxSendersDetails.getFaxLine());
        selectWebElement(deleteButton);
        enterValueToTxtFieldWithoutClear(deleteReasonTextBox,faxSendersDetails.getDeleteReason());
        selectWebElement(deleteYesBtn);
    }
    
    public boolean deleteFaxSendersCancelRecord(FaxSendersDetails faxSendersDetails) throws Exception {
    	searchFaxSendersRecord(faxSendersDetails.getFaxLine());
        selectWebElement(deleteButton);
        enterValueToTxtFieldWithoutClear(deleteReasonTextBox,faxSendersDetails.getDeleteReason());
        selectWebElement(deleteNoBtn);
        if(rowdata.getText().equals(faxSendersDetails.getFaxNumber()))
        	return true;
        else
        	return false;
	}

	public void deleteFaxSendersInvalidRecord(FaxSendersDetails faxSendersDetails) throws Exception {
		searchFaxSendersRecord(faxSendersDetails.getFaxLine());
        selectWebElement(deleteButton);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(deleteYesBtn);
        selectWebElement(deleteNoBtn);
		
	}
    public String getSuccessMessage(){
        waitForJqueryLoad(driver);
        if(successmsg.isDisplayed())
        {
        return successmsg.getText();
        }
        else
        return errorMsg.get(0).getText();
    }
    
    public Boolean getErrorMessage() {
    	waitUntilWebElementListIsVisible(errorMsg);
    	if(errorMsg.size()>0)
    	return false;
    	else
    		return true;
	}
    
    public boolean isAddBtnDisplayed() {
    	return addNewFaxSendersRcrdBtn.isDisplayed() && addNewFaxSendersRcrdBtn.isEnabled();
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
		if(isElementExist(FSImg))
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
		    if (f.getName().startsWith("Fax Senders")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "Fax Senders");
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
				if(headers.get(j).getText().equals("Last Changed On")){
				col=cols.get(j).getText().substring(0);
				}
				else
					col=cols.get(j).getText();
				map.put(headers.get(j).getText(),col);
			}
			map.remove("");
			map.remove("Senders");
			map.remove("AutoAck");
			map.remove("Routes");
			arr.add(map);
		}
		if(k!=pages)
		{
			nextPageIcon.click();
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
				if(headers.get(j).getText().equals("Last Changed On")){
					String value=cols.get(j).getText().substring(11, 13);
					int time = Integer.parseInt(value);
					if(time>12) {
					      time=time-12;
					}
					String col1=Integer.toString(time);
					col=col1+cols.get(j).getText().substring(13);
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
			nextPageIcon.click();
			waitForJqueryLoad(driver);}
		}
			return arr;
	}
	public boolean ExporttoExcelWithoutData(FaxSendersDetails details) throws Exception {
		searchFaxSendersRecord(details.getFaxLine());
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(exporttoexcel);
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
		return false;
	}

	public void SortByAscending() {
		selectWebElement(Name);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(Type);
		selectWebElement(Type);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
	public boolean verifyNumberOfItemsPerPage() {
        boolean status = false;
        try {
          //  if (norecords.size() <= 0) {
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
           // }
        } catch (Exception e) {
            e.printStackTrace();
        } return status;
    }
    public boolean verifyTotalNumberOfItemsPerPageDetails(){
        String item = items.getText();
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
    
    public boolean groupby() {
		DragandDrop(FaxLine,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(groupbyFaxLine.getText()))
		{return true;}
		else
			return false;		
	}

	public void addNewFaxSendersRecordwithoutinput(FaxSendersDetails faxSendersDetails) {
		 selectWebElement(addNewFaxSendersRcrdBtn);
	        selectWebElement(faxSendersSaveButton);
	        selectWebElement(cancelBtn);
	}

	public void addNewFaxSendersRecordwithoutfaxline(FaxSendersDetails faxSendersDetails) throws Exception {
		Thread.sleep(1000);
		 selectWebElement(addNewFaxSendersRcrdBtn);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(nameTextBox);
	        enterValueToTxtFieldWithoutClear(nameTextBox,faxSendersDetails.getName());
	        selectWebElement(faxNumberTextBox);
	        enterValueToTxtField(faxNumberTextBox,faxSendersDetails.getFaxNumber());
	        selectWebElement(senderTypeDropdown);
	        selectDropdownFromVisibleText(sendersTypeListBox,faxSendersDetails.getSenderType());
	        selectWebElement(faxSendersSaveButton);
	        selectWebElement(cancelBtn);
		
	}

	public void addNewFaxSendersRecordwithoutName(FaxSendersDetails faxSendersDetails) throws Exception {
		Thread.sleep(1000);
		selectWebElement(addNewFaxSendersRcrdBtn);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(faxLineDropdown);
	        selectDropdownFromVisibleText(faxLineListBox,faxSendersDetails.getFaxLine());
	        selectWebElement(faxNumberTextBox);
	        enterValueToTxtField(faxNumberTextBox,faxSendersDetails.getFaxNumber());
	        selectWebElement(senderTypeDropdown);
	        selectDropdownFromVisibleText(sendersTypeListBox,faxSendersDetails.getSenderType());
	        selectWebElement(faxSendersSaveButton);
	        selectWebElement(cancelBtn);
		
	}

	public void addNewFaxSendersRecordwithoutFaxNumber(FaxSendersDetails faxSendersDetails) throws Exception {
		Thread.sleep(1000);
		 selectWebElement(addNewFaxSendersRcrdBtn);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(faxLineDropdown);
	        selectDropdownFromVisibleText(faxLineListBox,faxSendersDetails.getFaxLine());
	        selectWebElement(nameTextBox);
	        enterValueToTxtFieldWithoutClear(nameTextBox,faxSendersDetails.getName());
	        selectWebElement(senderTypeDropdown);
	        selectDropdownFromVisibleText(sendersTypeListBox,faxSendersDetails.getSenderType());
	        selectWebElement(faxSendersSaveButton);
	        selectWebElement(cancelBtn);
		
	}

	public void addNewFaxSendersRecordwithoutType(FaxSendersDetails faxSendersDetails) throws Exception {
		Thread.sleep(1000);
		selectWebElement(addNewFaxSendersRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(faxLineDropdown);
        selectDropdownFromVisibleText(faxLineListBox,faxSendersDetails.getFaxLine());
        selectWebElement(nameTextBox);
        enterValueToTxtFieldWithoutClear(nameTextBox,faxSendersDetails.getName());
        selectWebElement(faxNumberTextBox);
        enterValueToTxtField(faxNumberTextBox,faxSendersDetails.getFaxNumber());
        selectWebElement(faxSendersSaveButton);
        selectWebElement(cancelBtn);
	}

	public void VerifyBulkUpload(String string) {
		waitForLoad(driver);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectWebElement(selectcsvfile);
		FileUploader upload = new FileUploader();
	    upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + string);
	    selectWebElement(bulkupload);
	}
	
	public Boolean verifycolor(){
		System.out.println(errormsgcolor.getCssValue("color"));
		if(errormsgcolor.getCssValue("color").equals("rgba(255, 52, 72, 1)"))
		return true;
		else
			return false;
		
	}

	public boolean clearAll(FaxSendersDetails faxSendersDetails) throws Exception {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Fax Line");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,faxSendersDetails.getFaxLine());
	        selectWebElement(clearall);
			if(searchTextBox.isEnabled())
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

	public void searchwithoutextsearch(FaxSendersDetails faxSendersDetails) {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Fax Line");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        selectWebElement(searchSearchBtn);	
        selectWebElement(searchCloseBtn);
		
	}

	public boolean verifyinvalidsearchwithwrongdata(FaxSendersDetails faxSendersDetails) throws Exception  {
		searchFaxSendersRecord(faxSendersDetails.getFaxLine());
		if(norecords.isDisplayed())
			return true; 
			else
				return false;
	}

	public boolean verifyclearsearch() {
		selectWebElement(clearsearch);
		if(gridContent.isDisplayed())
			return true;
		else
		return false;
	}

	

	
	

	

	
}

