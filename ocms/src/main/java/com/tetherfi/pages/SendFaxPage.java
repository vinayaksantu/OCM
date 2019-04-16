package com.tetherfi.pages;

import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.model.fax.FaxRoutingConfigurationDetails;
import com.tetherfi.model.fax.SendFaxDetails;
import com.tetherfi.utility.FileUploader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SendFaxPage extends BasePage {

    public SendFaxPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement sendFax;
    
    @FindBy(css="#create")
    private WebElement addNewRecdBtn;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;
    
    @FindBy(id = "ModifyReason")
    private WebElement modifyReasonTextBox;

    @FindBy(id = "ModifyReason1")
    private WebElement deleteReasonTextBox;

    @FindBy(id = "yesButton")
    private WebElement deleteYesBtn;
    
    @FindBy(id = "noButton")
    private WebElement deleteNoBtn;
    
    @FindBy(css=".k-grid-content")
    private WebElement gridContent;
    
    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css = "#toast-container .toast-error .toast-message")
    private List<WebElement> errorMsg;
    
    @FindBy(css =".toast-info .toast-message")
    private WebElement infoMsg;

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

    @FindBy(id="1001sAddButton")
    private WebElement searchAddCriteriaBtn;

    @FindBy(css = "#1001sCloseButton .k-i-close")
    private WebElement searchRemoveFilterBtn;
    
    @FindBy(xpath="//label[@for='1001sRadioAND']")
    private WebElement andradiobtn;
    
    @FindBy(xpath="//label[@for='1001sRadioOR']")
    private WebElement ORradiobtn;
    
    @FindBy(xpath="//tbody/tr/td[2]")
    private WebElement rowdata;
    
    @FindBy(xpath="//tbody/tr/td[5]")
    private WebElement faxlinedata;

    @FindBy(css = ".modal-footer .k-button")
    private WebElement searchCloseBtn;

    @FindBy(css = ".modal-footer .button-danger-theme")
    private WebElement searchClearAllBtn;

    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;
    
    @FindBy(css="ul[id='1002sColumnName_listbox'] li")
    private List<WebElement> columnNameListtwo;
    
    @FindBy(css="ul[id='1002sCriteria_listbox'] li")
    private List<WebElement> searchCriteriaDropDwntwo;
    
    @FindBy(id="1002sTextToSearch")
    private WebElement searchTextBoxtwo;
    
    @FindBy(xpath="//i[@class='fas fa-paper-plane']")
    private WebElement SFImg;
    
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
    
    @FindBy(xpath="//a[text()='GUID']")
    private WebElement GUID;
    
    @FindBy(xpath="//a[text()='File Name']")
    private WebElement FileName;
    
    @FindBy(xpath="//div[@data-role='droptarget']")
    private WebElement droptarget;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
    private WebElement groupbyFaxLine;
    
    @FindBy(css="span[aria-owns='DNIS_listbox']")
    private WebElement faxLineDropdown;

    @FindBy(css="ul[id='DNIS_listbox'] li")
    private List<WebElement> faxLineListBox;
    
    @FindBy(css="span[aria-owns='Type_listbox']")
    private WebElement senderTypeDropdown;
    
    @FindBy(css="ul[id='Type_listbox'] li")
    private List<WebElement> senderTypeListBox;
    
    @FindBy(id="Intent")
    private WebElement Intent;
    
    @FindBy(css="span[aria-owns='RouteType_listbox']")
    private WebElement RouteTypeDropdown;
    
    @FindBy(css="ul[id='RouteType_listbox'] li")
    private List<WebElement> routeTypeListBox;
    
    @FindBy(id="RouteData")
    private WebElement routeData;
    
    @FindBy(css=".k-grid-update")
    private WebElement SaveButton;
    
    @FindBy(css=".k-grid-cancel")
    private WebElement cancelBtn;
    
    @FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
    
    @FindBy(xpath="//i[@class='fas fa-sync']")
    private WebElement clearsearch;
    
    @FindBy(css="span[aria-owns='FaxLine_listbox']")
    private WebElement faxlineDropdown;
    
    @FindBy(xpath="//input[@class='select2-search__field']")
    private WebElement recipientnumber;
    
    @FindBy(css="ul[id='FaxLine_listbox'] li")
    private List<WebElement> faxlinelistbox;
    
    @FindBy(xpath="//label[@class='k-checkbox-label' and @for='sendNow']")
    private WebElement sendimmediatelycheckbox;
    
    @FindBy(xpath="//label[@class='k-checkbox-label' and @for='enableTemplate']")
    private WebElement enableTemplate;
    
    @FindBy(css="span[aria-owns='Template_listbox']")
    private WebElement templateDropdown;
    
    @FindBy(css="ul[id='Template_listbox'] li")
    private List<WebElement> templatelistbox;
    
    @FindBy(xpath="//div[@class='k-button k-upload-button']")
    private WebElement uploadFile;
    
    @FindBy(xpath="//input[@alt='Preview']")
    private WebElement preview;
    
    public boolean isSendFaxPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return sendFax.isEnabled();
    }
    
    public boolean isAddBtnDisplayed() {
    	return addNewRecdBtn.isDisplayed() && addNewRecdBtn.isEnabled();
    }
    
    public boolean isExportBtnDisplayed() {
    	return exporttoexcel.isDisplayed() && exporttoexcel.isEnabled();
    }
    
    public boolean verifylogo() {
		if(isElementExist(SFImg))
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
    
    public boolean clearAll(SendFaxDetails sendFaxDetails) {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Number");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,sendFaxDetails.getRecipientNumber());
	        selectWebElement(searchClearAllBtn);
			if(searchTextBox.isEnabled())
	        	return true;
	        else
			return false;
	}

	public boolean verifyclose() {
		selectWebElement(searchCloseBtn);
		if(gridContent.isDisplayed())
			return true;
		else
		return false;
	}

	public void searchwithoutextsearch() {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Fax Line");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        selectWebElement(searchSearchBtn);	
        selectWebElement(searchCloseBtn);
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
	
	public String getInfoMsg() {
		waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return errorMsg.get(0).getText();}
        waitUntilWebElementIsVisible(infoMsg);
        return infoMsg.getText();
	}
	
	public boolean verifyExportToExcel(String filePath) {
		final File folder = new File(filePath);
		for (final File f : folder.listFiles()) {
		    if (f.getName().startsWith("Send Fax")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "Send Fax");
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
			for(int j=0;j<headers.size();j++) {
				if(headers.get(j).getText().equals("Last Changed On")){
				col=cols.get(j).getText().substring(0,10);
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
			arr.add(map);
		}
		if(k!=pages)
		{
			nextPageIcon.click();
			waitForJqueryLoad(driver);}
		}
			return arr;
	}
	public boolean ExporttoExcelWithoutData(SendFaxDetails details) throws Exception {
		searchSendFaxRecord(details.getFaxLine());
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(exporttoexcel);
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
		return false;
	}

	public void searchSendFaxRecord(String faxLine) {
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
	public boolean verifyvalidsearchdata(SendFaxDetails details) {
		searchSendFaxRecord(details.getFaxLine());
		if(faxlinedata.getText().equals(details.getFaxLine()))
			return true; 
			else
				return false;
	}
	
	
	public boolean verifyinvalidsearchwithwrongdata(SendFaxDetails details) {
		searchSendFaxRecord(details.getFaxLine());
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
	public void SortByAscending() {
		selectWebElement(GUID);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(FileName);
		selectWebElement(FileName);
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

	public boolean AddCancelRecord(SendFaxDetails sendFaxDetails) throws Exception {
		String actualitems=items.getText();
		selectWebElement(addNewRecdBtn);
		waitForJqueryLoad(driver);
	    Thread.sleep(3000);
        enterValueToTxtFieldWithoutClear(recipientnumber,sendFaxDetails.getRecipientNumber());
        recipientnumber.sendKeys(Keys.ENTER);
	    selectWebElement(faxlineDropdown);
	    selectDropdownFromVisibleText(faxlinelistbox,sendFaxDetails.getFaxLine());
	    selectWebElement(sendimmediatelycheckbox);
	    selectWebElement(enableTemplate);
	    selectWebElement(templateDropdown);
	    selectDropdownFromVisibleText(templatelistbox,sendFaxDetails.getTemplate());
	    selectWebElement(cancelBtn);
	    if(actualitems.equals(items.getText()))
	    	return true;
	    else
		return false;
	}

	public void addNewSendFaxRecord(SendFaxDetails sendFaxDetails) throws Exception {
		selectWebElement(addNewRecdBtn);
		waitForJqueryLoad(driver);
	    Thread.sleep(2000);
        enterValueToTxtFieldWithoutClear(recipientnumber,sendFaxDetails.getRecipientNumber());
        recipientnumber.sendKeys(Keys.ENTER);
	    selectWebElement(faxlineDropdown);
	    selectDropdownFromVisibleText(faxlinelistbox,sendFaxDetails.getFaxLine());
	    selectWebElement(sendimmediatelycheckbox);
	    selectWebElement(enableTemplate);
	    selectWebElement(templateDropdown);
	    selectDropdownFromVisibleText(templatelistbox,sendFaxDetails.getTemplate());
	    selectWebElement(SaveButton);
		
	}

	public void addRecordWithoutRecipientNumber(SendFaxDetails sendFaxDetails) throws Exception {
		selectWebElement(addNewRecdBtn);
		waitForJqueryLoad(driver);
	    Thread.sleep(2000);
	    selectWebElement(faxlineDropdown);
	    selectDropdownFromVisibleText(faxlinelistbox,sendFaxDetails.getFaxLine());
	    selectWebElement(sendimmediatelycheckbox);
	    selectWebElement(enableTemplate);
	    selectWebElement(templateDropdown);
	    selectDropdownFromVisibleText(templatelistbox,sendFaxDetails.getTemplate());
	    selectWebElement(SaveButton);
	    selectWebElement(cancelBtn);
		
	}

	public void addRecordWithoutFaxLine(SendFaxDetails sendFaxDetails) throws Exception {
		selectWebElement(addNewRecdBtn);
		waitForJqueryLoad(driver);
	    Thread.sleep(2000);
        enterValueToTxtFieldWithoutClear(recipientnumber,sendFaxDetails.getRecipientNumber());
        recipientnumber.sendKeys(Keys.ENTER);
	    selectWebElement(sendimmediatelycheckbox);
	    selectWebElement(enableTemplate);
	    selectWebElement(templateDropdown);
	    selectDropdownFromVisibleText(templatelistbox,sendFaxDetails.getTemplate());
	    selectWebElement(SaveButton);
	    selectWebElement(cancelBtn);
		
	}

	public void addNewInvalidRecordWithoutDateTime(SendFaxDetails sendFaxDetails) throws Exception {
		selectWebElement(addNewRecdBtn);
		waitForJqueryLoad(driver);
	    Thread.sleep(2000);
        enterValueToTxtFieldWithoutClear(recipientnumber,sendFaxDetails.getRecipientNumber());
        recipientnumber.sendKeys(Keys.ENTER);
	    selectWebElement(faxlineDropdown);
	    selectDropdownFromVisibleText(faxlinelistbox,sendFaxDetails.getFaxLine());
	    selectWebElement(enableTemplate);
	    selectWebElement(templateDropdown);
	    selectDropdownFromVisibleText(templatelistbox,sendFaxDetails.getTemplate());
	    selectWebElement(SaveButton);
	    selectWebElement(cancelBtn);
	}

	public void addRecordWithoutTemplate(SendFaxDetails sendFaxDetails) throws Exception {
		selectWebElement(addNewRecdBtn);
		waitForJqueryLoad(driver);
	    Thread.sleep(2000);
        enterValueToTxtFieldWithoutClear(recipientnumber,sendFaxDetails.getRecipientNumber());
        recipientnumber.sendKeys(Keys.ENTER);
	    selectWebElement(faxlineDropdown);
	    selectDropdownFromVisibleText(faxlinelistbox,sendFaxDetails.getFaxLine());
	    selectWebElement(sendimmediatelycheckbox);
	    selectWebElement(SaveButton);
	    selectWebElement(cancelBtn);
		}

	public void addNewSendFaxRecordFileUpload(SendFaxDetails sendFaxDetails) throws Exception {
		selectWebElement(addNewRecdBtn);
		waitForJqueryLoad(driver);
	    Thread.sleep(2000);
        enterValueToTxtFieldWithoutClear(recipientnumber,sendFaxDetails.getRecipientNumber());
        recipientnumber.sendKeys(Keys.ENTER);
	    selectWebElement(faxlineDropdown);
	    selectDropdownFromVisibleText(faxlinelistbox,sendFaxDetails.getFaxLine());
	    selectWebElement(sendimmediatelycheckbox);
	    selectWebElement(uploadFile);
        FileUploader upload = new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + sendFaxDetails.getFileupload());
	    selectWebElement(SaveButton);
		
	}

	public void addNewInvalidRecordWithoutTemplate(SendFaxDetails sendFaxDetails) throws Exception {
		selectWebElement(addNewRecdBtn);
		waitForJqueryLoad(driver);
	    Thread.sleep(2000);
        enterValueToTxtFieldWithoutClear(recipientnumber,sendFaxDetails.getRecipientNumber());
        recipientnumber.sendKeys(Keys.ENTER);
	    selectWebElement(faxlineDropdown);
	    selectDropdownFromVisibleText(faxlinelistbox,sendFaxDetails.getFaxLine());
	    selectWebElement(sendimmediatelycheckbox);
	    selectWebElement(enableTemplate);
	    selectWebElement(SaveButton);
	    selectWebElement(cancelBtn);
		
	}

	public void addNewInvalidRecordUploadingWrongFile(SendFaxDetails sendFaxDetails) throws InterruptedException {
		selectWebElement(addNewRecdBtn);
		waitForJqueryLoad(driver);
	    Thread.sleep(2000);
        enterValueToTxtFieldWithoutClear(recipientnumber,sendFaxDetails.getRecipientNumber());
        recipientnumber.sendKeys(Keys.ENTER);
	    selectWebElement(faxlineDropdown);
	    selectDropdownFromVisibleText(faxlinelistbox,sendFaxDetails.getFaxLine());
	    selectWebElement(sendimmediatelycheckbox);
	    selectWebElement(uploadFile);
        FileUploader upload = new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + sendFaxDetails.getFileupload());
	    selectWebElement(SaveButton);
	    selectWebElement(cancelBtn);

		
	}

	public void addButtonPreviewwithoutuploadingfile(SendFaxDetails sendFaxDetails) throws Exception {
		selectWebElement(addNewRecdBtn);
		waitForJqueryLoad(driver);
	    Thread.sleep(2000);
        enterValueToTxtFieldWithoutClear(recipientnumber,sendFaxDetails.getRecipientNumber());
        recipientnumber.sendKeys(Keys.ENTER);
	    selectWebElement(faxlineDropdown);
	    selectDropdownFromVisibleText(faxlinelistbox,sendFaxDetails.getFaxLine());
	    selectWebElement(sendimmediatelycheckbox);
	    selectWebElement(preview);
	    selectWebElement(cancelBtn);		
	}

	public void addButtonPreviewuploadWrongfile(SendFaxDetails sendFaxDetails) throws Exception {
		selectWebElement(addNewRecdBtn);
		waitForJqueryLoad(driver);
	    Thread.sleep(2000);
        enterValueToTxtFieldWithoutClear(recipientnumber,sendFaxDetails.getRecipientNumber());
        recipientnumber.sendKeys(Keys.ENTER);
	    selectWebElement(faxlineDropdown);
	    selectDropdownFromVisibleText(faxlinelistbox,sendFaxDetails.getFaxLine());
	    selectWebElement(sendimmediatelycheckbox);
	    selectWebElement(uploadFile);
        FileUploader upload = new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + sendFaxDetails.getFileupload());
	    selectWebElement(preview);
	    selectWebElement(cancelBtn);
		
	}

	public boolean VerifyDropDown(FaxLineConfigDetails faxLineConfigDetails) {
		Boolean Status=true;
		selectWebElement(addNewRecdBtn);
		selectWebElement(faxlineDropdown);
		for(int i=0;i<faxLineListBox.size();i++) {
			String value=faxLineListBox.get(i).getText();
			if(value.equals(faxLineConfigDetails.getFaxLine())) {
				Status= false;
			break;}
		}
		return Status;
	}

     
}
