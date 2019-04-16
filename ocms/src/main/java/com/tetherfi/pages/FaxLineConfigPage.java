package com.tetherfi.pages;

import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.model.ivr.AdhocOptionEnhancementDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaxLineConfigPage extends BasePage{
    public FaxLineConfigPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css=".ibox-title h5")
    private WebElement faxLineConfig;

    @FindBy(id="create")
    private WebElement addNewFaxLineConfigRcrdBtn;

    @FindBy(css=".k-numerictextbox")
    private List<WebElement> faxLineTextFields;

    @FindBy(id="DNIS")
    private WebElement faxLineTextBox;

    @FindBy(id="FaxLineName")
    private WebElement faxLineNameTextBox;

    @FindBy(id="Description")
    private WebElement descriptionTextBox;

    @FindBy(css="span[aria-owns='Enabled_listbox']")
    private WebElement statusDropdown;

    @FindBy(css="ul[id='Enabled_listbox'] li")
    private List<WebElement> statusListBox;

    @FindBy(css="span[aria-owns='SendEnabled_listbox']")
    private WebElement sendStatusDropdown;

    @FindBy(css="ul[id='SendEnabled_listbox'] li")
    private List <WebElement> sendStatusListBox;

    @FindBy(css="span[aria-owns='ReceiveEnabled_listbox']")
    private WebElement receiveStatusDropdown;

    @FindBy(css="ul[id='ReceiveEnabled_listbox'] li")
    private List<WebElement> receiveStatusListBox;

    @FindBy(css=".k-grid-update")
    private WebElement faxLineSaveButton;
    
    @FindBy(css=".k-grid-cancel")
    private WebElement cancelBtn;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css = "#toast-container .toast-error .toast-message")
    private List<WebElement> errorMsg;

    @FindBy(xpath="//tbody/tr/td[2]")
    private WebElement rowdata;
    
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
    
    @FindBy(xpath="//button[text()='Export to Excel']")
    private WebElement exporttoexcel;
    
    @FindBy(xpath="//i[@class='fas fa-cog']")
    private WebElement FLCImg;
    
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
    
    @FindBy(xpath="//a[text()='Description']")
    private WebElement Description;
    
    @FindBy(xpath="//div[@data-role='droptarget']")
    private WebElement droptarget;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
    private WebElement groupbyFaxLine;
    
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
    
    @FindBy(xpath="//button[text()='Close']")
    private WebElement searchClose;
    
    @FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
    
    @FindBy(xpath="//i[@class='fas fa-sync']")
    private WebElement clearsearch;
    
    @FindBy(xpath="//td/a[@class='k-button k-button-icontext k-grid-CustomPreview']")
    private WebElement sendersBtn;
    
    @FindBy(xpath="//td/a[@class='k-button k-button-icontext k-grid-CustomPreview1']")
    private WebElement autoAckBtn;
    
    @FindBy(xpath="//td/a[@class='k-button k-button-icontext k-grid-CustomPreview2']")
    private WebElement routesBtn;
    
    @FindBy(xpath="//tbody[@role='rowgroup']")
    private WebElement rowgroup;
    

    public boolean isFaxLineConfigPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return faxLineConfig.isEnabled();
    }

    public void addNewFaxLineConfigRecord(FaxLineConfigDetails faxLineConfigDetails) {
        selectWebElement(addNewFaxLineConfigRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        enterValueToTxtFieldWithoutClear(faxLineTextBox,faxLineConfigDetails.getFaxLine());
        selectWebElement(faxLineNameTextBox);
        enterValueToTxtField(faxLineNameTextBox,faxLineConfigDetails.getFaxLineName());
        selectWebElement(descriptionTextBox);
        enterValueToTxtField(descriptionTextBox,faxLineConfigDetails.getDescription());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,faxLineConfigDetails.getStatus());
        selectWebElement(sendStatusDropdown);
        selectDropdownFromVisibleText(sendStatusListBox,faxLineConfigDetails.getSendStatus());
        selectWebElement(receiveStatusDropdown);
        selectDropdownFromVisibleText(receiveStatusListBox,faxLineConfigDetails.getReceiveStatus());
        selectWebElement(faxLineSaveButton);
        }
    
    public boolean AddCancelRecord(FaxLineConfigDetails faxLineConfigDetails) {
		String actualitems=items.getText();
		selectWebElement(addNewFaxLineConfigRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        enterValueToTxtFieldWithoutClear(faxLineTextBox,faxLineConfigDetails.getFaxLine());
        selectWebElement(faxLineNameTextBox);
        enterValueToTxtField(faxLineNameTextBox,faxLineConfigDetails.getFaxLineName());
        selectWebElement(descriptionTextBox);
        enterValueToTxtField(descriptionTextBox,faxLineConfigDetails.getDescription());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,faxLineConfigDetails.getStatus());
        selectWebElement(sendStatusDropdown);
        selectDropdownFromVisibleText(sendStatusListBox,faxLineConfigDetails.getSendStatus());
        selectWebElement(receiveStatusDropdown);
        selectDropdownFromVisibleText(receiveStatusListBox,faxLineConfigDetails.getReceiveStatus());
        selectWebElement(cancelBtn);
        if(items.getText().equals(actualitems))
        	return true;
        else
		return false;
	}

    public void editFaxLineConfig(FaxLineConfigDetails faxLineConfigDetails) {
        searchFaxLineConfigRecord(faxLineConfigDetails.getFaxLine());
        selectWebElement(editButton);
        selectWebElement(descriptionTextBox);
        enterValueToTxtField(descriptionTextBox,faxLineConfigDetails.getUpdatedDescription());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,faxLineConfigDetails.getUpdatedStatus());
        selectWebElement(sendStatusDropdown);
        selectDropdownFromVisibleText(sendStatusListBox,faxLineConfigDetails.getUpdatedSendStatus());
        selectWebElement(receiveStatusDropdown);
        selectDropdownFromVisibleText(receiveStatusListBox,faxLineConfigDetails.getUpdatedReceiveStatus());
        selectWebElement(modifyReasonTextBox);
       enterValueToTxtField(modifyReasonTextBox,faxLineConfigDetails.getModifyReason());
       selectWebElement(faxLineSaveButton);
    }
    public boolean editcancel(FaxLineConfigDetails faxLineConfigDetails) {
    	searchFaxLineConfigRecord(faxLineConfigDetails.getFaxLine());
        selectWebElement(editButton);
        selectWebElement(descriptionTextBox);
        enterValueToTxtField(descriptionTextBox,faxLineConfigDetails.getDescription());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,faxLineConfigDetails.getStatus());
        selectWebElement(sendStatusDropdown);
        selectDropdownFromVisibleText(sendStatusListBox,faxLineConfigDetails.getSendStatus());
        selectWebElement(receiveStatusDropdown);
        selectDropdownFromVisibleText(receiveStatusListBox,faxLineConfigDetails.getReceiveStatus());
        selectWebElement(modifyReasonTextBox);
        enterValueToTxtField(modifyReasonTextBox,faxLineConfigDetails.getModifyReason());
        selectWebElement(cancelBtn);
        if(rowdata.getText().equals(faxLineConfigDetails.getFaxLine()))
        	return true;
        else
       return false;
	}
    public void searchFaxLineConfigRecord(String faxLine) {
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

    public void deleteFaxLineConfig(FaxLineConfigDetails faxLineConfigDetails) {
            searchFaxLineConfigRecord(faxLineConfigDetails.getFaxLine());
            selectWebElement(deleteButton);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enterValueToTxtField(deleteReasonTextBox,faxLineConfigDetails.getDeleteReason());
            selectWebElement(deleteYesBtn);
        }
    
    public boolean deletecancelRecord(FaxLineConfigDetails faxLineConfigDetails) {
    	searchFaxLineConfigRecord(faxLineConfigDetails.getFaxLine());
        selectWebElement(deleteButton);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        enterValueToTxtField(deleteReasonTextBox,faxLineConfigDetails.getDeleteReason());
        selectWebElement(deleteNoBtn);
        if(rowdata.getText().equals(faxLineConfigDetails.getFaxLine()))
        	return true;
        else
		return false;
	}

    public String getSuccessMessage(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return errorMsg.get(0).getText();}
        waitUntilWebElementIsVisible(successmsg);
        return successmsg.getText();
    }
    
    public boolean getErrorMessage() {
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.size()>0)
		return false;
		else
			return true;
	}
    
    public boolean isAddBtnDisplayed() {
    	return addNewFaxLineConfigRcrdBtn.isDisplayed() && addNewFaxLineConfigRcrdBtn.isEnabled();
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
		if(isElementExist(FLCImg))
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
		    if (f.getName().startsWith("Fax Line")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "Fax Line Config");
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
				col=cols.get(j).getText().substring(0,10);
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
					col=cols.get(j).getText().substring(11);
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
	public boolean ExporttoExcelWithoutData(FaxLineConfigDetails details) throws Exception {
		searchFaxLineConfigRecord(details.getFaxLine());
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
		selectWebElement(Description);
		selectWebElement(Description);
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

	public void addNewFaxLineConfigRecordwithoutInput(FaxLineConfigDetails faxLineConfigDetails) {
		 selectWebElement(addNewFaxLineConfigRcrdBtn);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(faxLineSaveButton);
	        selectWebElement(cancelBtn);
	        }

	public void addNewFaxLineConfigRecordwithoutFaxline(FaxLineConfigDetails faxLineConfigDetails) throws Exception {
		Thread.sleep(1000);
		selectWebElement(addNewFaxLineConfigRcrdBtn);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(faxLineNameTextBox);
	        enterValueToTxtField(faxLineNameTextBox,faxLineConfigDetails.getFaxLineName());
	        selectWebElement(descriptionTextBox);
	        enterValueToTxtField(descriptionTextBox,faxLineConfigDetails.getDescription());
	        selectWebElement(statusDropdown);
	        selectDropdownFromVisibleText(statusListBox,faxLineConfigDetails.getStatus());
	        selectWebElement(sendStatusDropdown);
	        selectDropdownFromVisibleText(sendStatusListBox,faxLineConfigDetails.getSendStatus());
	        selectWebElement(receiveStatusDropdown);
	        selectDropdownFromVisibleText(receiveStatusListBox,faxLineConfigDetails.getReceiveStatus());
	        selectWebElement(faxLineSaveButton);
	        selectWebElement(cancelBtn);
	        }

	public void addNewFaxLineConfigRecordwithoutFaxLineName(FaxLineConfigDetails faxLineConfigDetails) throws Exception {
		Thread.sleep(1000);
		selectWebElement(addNewFaxLineConfigRcrdBtn);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        enterValueToTxtFieldWithoutClear(faxLineTextBox,faxLineConfigDetails.getFaxLine());
	        selectWebElement(descriptionTextBox);
	        enterValueToTxtField(descriptionTextBox,faxLineConfigDetails.getDescription());
	        selectWebElement(statusDropdown);
	        selectDropdownFromVisibleText(statusListBox,faxLineConfigDetails.getStatus());
	        selectWebElement(sendStatusDropdown);
	        selectDropdownFromVisibleText(sendStatusListBox,faxLineConfigDetails.getSendStatus());
	        selectWebElement(receiveStatusDropdown);
	        selectDropdownFromVisibleText(receiveStatusListBox,faxLineConfigDetails.getReceiveStatus());
	        selectWebElement(faxLineSaveButton);
	        selectWebElement(cancelBtn);
	        }

	public void addNewFaxLineConfigRecordwithoutDescription(FaxLineConfigDetails faxLineConfigDetails) throws Exception {
		Thread.sleep(1000);
		selectWebElement(addNewFaxLineConfigRcrdBtn);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        enterValueToTxtFieldWithoutClear(faxLineTextBox,faxLineConfigDetails.getFaxLine());
	        selectWebElement(faxLineNameTextBox);
	        enterValueToTxtField(faxLineNameTextBox,faxLineConfigDetails.getFaxLineName());
	        selectWebElement(statusDropdown);
	        selectDropdownFromVisibleText(statusListBox,faxLineConfigDetails.getStatus());
	        selectWebElement(sendStatusDropdown);
	        selectDropdownFromVisibleText(sendStatusListBox,faxLineConfigDetails.getSendStatus());
	        selectWebElement(receiveStatusDropdown);
	        selectDropdownFromVisibleText(receiveStatusListBox,faxLineConfigDetails.getReceiveStatus());
	        selectWebElement(faxLineSaveButton);
	        selectWebElement(cancelBtn);
	        }
	
	public void addNewFaxLineConfigRecordwithoutStatus(FaxLineConfigDetails faxLineConfigDetails) throws Exception {
		Thread.sleep(1000);
		 selectWebElement(addNewFaxLineConfigRcrdBtn);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        enterValueToTxtFieldWithoutClear(faxLineTextBox,faxLineConfigDetails.getFaxLine());
	        selectWebElement(faxLineNameTextBox);
	        enterValueToTxtField(faxLineNameTextBox,faxLineConfigDetails.getFaxLineName());
	        selectWebElement(descriptionTextBox);
	        enterValueToTxtField(descriptionTextBox,faxLineConfigDetails.getDescription());
	        selectWebElement(sendStatusDropdown);
	        selectDropdownFromVisibleText(sendStatusListBox,faxLineConfigDetails.getSendStatus());
	        selectWebElement(receiveStatusDropdown);
	        selectDropdownFromVisibleText(receiveStatusListBox,faxLineConfigDetails.getReceiveStatus());
	        selectWebElement(faxLineSaveButton);
	        selectWebElement(cancelBtn);
	        }

	public void addNewFaxLineConfigRecordwithoutSendStatus(FaxLineConfigDetails faxLineConfigDetails) throws Exception {
		Thread.sleep(1000);
		selectWebElement(addNewFaxLineConfigRcrdBtn);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        enterValueToTxtFieldWithoutClear(faxLineTextBox,faxLineConfigDetails.getFaxLine());
	        selectWebElement(faxLineNameTextBox);
	        enterValueToTxtField(faxLineNameTextBox,faxLineConfigDetails.getFaxLineName());
	        selectWebElement(descriptionTextBox);
	        enterValueToTxtField(descriptionTextBox,faxLineConfigDetails.getDescription());
	        selectWebElement(statusDropdown);
	        selectDropdownFromVisibleText(statusListBox,faxLineConfigDetails.getStatus());
	        selectWebElement(receiveStatusDropdown);
	        selectDropdownFromVisibleText(receiveStatusListBox,faxLineConfigDetails.getReceiveStatus());
	        selectWebElement(faxLineSaveButton);
	        selectWebElement(cancelBtn);
	        }
	
	public void addNewFaxLineConfigRecordwithoutReceiveStatus(FaxLineConfigDetails faxLineConfigDetails) throws Exception {
		Thread.sleep(1000);
		selectWebElement(addNewFaxLineConfigRcrdBtn);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        enterValueToTxtFieldWithoutClear(faxLineTextBox,faxLineConfigDetails.getFaxLine());
	        selectWebElement(faxLineNameTextBox);
	        enterValueToTxtField(faxLineNameTextBox,faxLineConfigDetails.getFaxLineName());
	        selectWebElement(descriptionTextBox);
	        enterValueToTxtField(descriptionTextBox,faxLineConfigDetails.getDescription());
	        selectWebElement(statusDropdown);
	        selectDropdownFromVisibleText(statusListBox,faxLineConfigDetails.getStatus());
	        selectWebElement(sendStatusDropdown);
	        selectDropdownFromVisibleText(sendStatusListBox,faxLineConfigDetails.getSendStatus());
	        selectWebElement(faxLineSaveButton);
	        selectWebElement(cancelBtn);
	        }

	public boolean clearAll(FaxLineConfigDetails details) {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Fax Line");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,details.getFaxLine());
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

	public void searchwithoutextsearch(FaxLineConfigDetails faxLineConfigDetails) {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Fax Line");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        selectWebElement(searchSearchBtn);	
        selectWebElement(searchCloseBtn);
	}

	public boolean verifyinvalidsearchwithwrongdata(FaxLineConfigDetails faxLineConfigDetails) {
		searchFaxLineConfigRecord(faxLineConfigDetails.getFaxLine());
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

	public void deleteFaxLineConfigwithoutReason(FaxLineConfigDetails faxLineConfigDetails) {
		searchFaxLineConfigRecord(faxLineConfigDetails.getFaxLine());
        selectWebElement(deleteButton);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(deleteYesBtn);
        selectWebElement(deleteNoBtn);
    }

	public void editInvalidFaxLineConfig(FaxLineConfigDetails faxLineConfigDetails) {
		 searchFaxLineConfigRecord(faxLineConfigDetails.getFaxLine());
	        selectWebElement(editButton);
	        selectWebElement(descriptionTextBox);
	        descriptionTextBox.clear();
	        selectWebElement(statusDropdown);
	        selectDropdownFromVisibleText(statusListBox,faxLineConfigDetails.getUpdatedStatus());
	        selectWebElement(sendStatusDropdown);
	        selectDropdownFromVisibleText(sendStatusListBox,faxLineConfigDetails.getUpdatedSendStatus());
	        selectWebElement(receiveStatusDropdown);
	        selectDropdownFromVisibleText(receiveStatusListBox,faxLineConfigDetails.getUpdatedReceiveStatus());
	       selectWebElement(faxLineSaveButton);
	       selectWebElement(cancelBtn);
		
	}

	public boolean verifySendersLink(FaxLineConfigDetails faxLineConfigDetails) {
		Boolean Status=false;
		searchFaxLineConfigRecord(faxLineConfigDetails.getFaxLine());
		selectWebElement(sendersBtn);
		if(faxLineConfig.getText().equals("Fax Senders")) {
			List<WebElement> rows=rowgroup.findElements(By.tagName("tr"));
			if(rows.size()>0){
				for(WebElement e:rows){
					List<WebElement> cols=e.findElements(By.tagName("td"));
					cols.get(1).getText().equals(faxLineConfigDetails.getFaxLine());
				}	
				Status=true;
			}
			else
			Status=true;
		}
		return Status;
		}

	public boolean verifyAutoAckLink(FaxLineConfigDetails faxLineConfigDetails) {
		Boolean Status=false;
		searchFaxLineConfigRecord(faxLineConfigDetails.getFaxLine());
		selectWebElement(autoAckBtn);
		if(faxLineConfig.getText().equals("Fax Auto ACK Configuration")) {
			List<WebElement> rows=rowgroup.findElements(By.tagName("tr"));
			if(rows.size()>0){
				for(WebElement e:rows){
					List<WebElement> cols=e.findElements(By.tagName("td"));
					cols.get(1).getText().equals(faxLineConfigDetails.getFaxLine());
				}	
				Status=true;
			}
			else
			Status=true;
		}
		return Status;
		}
	
	public boolean verifyRoutesLink(FaxLineConfigDetails faxLineConfigDetails) {
		Boolean Status=false;
		searchFaxLineConfigRecord(faxLineConfigDetails.getFaxLine());
		selectWebElement(routesBtn);
		if(faxLineConfig.getText().equals("Fax Routing Configuration")) {
			List<WebElement> rows=rowgroup.findElements(By.tagName("tr"));
			if(rows.size()>0){
				for(WebElement e:rows){
					List<WebElement> cols=e.findElements(By.tagName("td"));
					cols.get(1).getText().equals(faxLineConfigDetails.getFaxLine());
				}	
				Status=true;
			}
			else
			Status=true;
		}
		return Status;
		}

	public void addFaxLineConfig(FaxLineConfigDetails faxLineConfigDetails) {
		// TODO Auto-generated method stub
		
	}
	
}


