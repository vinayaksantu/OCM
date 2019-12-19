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

import com.tetherfi.model.ivr.HolidayListDetails;
import com.tetherfi.model.ivr.OperatingHoursDetails;

public class HolidayListPage extends BasePage{

	

	public HolidayListPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".ibox-title h5")
    private WebElement holidayList;
	
	@FindBy(xpath="//i[@class='far fa-list-alt']")
	private WebElement hlImg;
	
	@FindBy(css="#create")
	private WebElement addNewHolidayListRecordBtn;
    
    @FindBy(xpath="//a[@class='k-button k-button-icontext k-primary k-grid-update']")
    private WebElement savebtn;
    
    @FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel']")
    private WebElement cancelbtn;
    
	@FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;
	
	@FindBy(css = ".k-grid-edit")
    private WebElement editButton;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
	
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
    
    @FindBy(xpath="//a[text()='Announced Holiday']")
    private WebElement AnnouncedHoliday;
    
    @FindBy(xpath="//a[text()='Start Date']")
    private WebElement StartDate;
    
    @FindBy(id="ModifyReason")
    private WebElement ModifyReasonTxtbox;
    
    @FindBy(xpath="//div[@data-role='droptarget']")
    private WebElement droptarget;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
    private WebElement groupbyAnnouncedHoliday;
    
    @FindBy(css=".k-grid-content")
    private WebElement gridContent;
    
    @FindBy(id="tGrid")
    private WebElement auditGridContent;
    
    @FindBy(id="AnnouncedHoliday")
    private WebElement announcedHolidayTextbox;
    
    @FindBy(id="StartDate")
    private WebElement startDateTextbox;
    
    @FindBy(id="StartTime")
    private WebElement startTimeTextbox;
    
    @FindBy(id="EndDate")
    private WebElement endDateTextbox;
    
    @FindBy(id="EndTime")
    private WebElement endTimeTextbox; 
    
    /*@FindBy(xpath="//input[@placeholder='Enter Value']")
    private WebElement vdnTextbox;*/
    
    @FindBy(id="VDN")
    private WebElement vdnTextbox;
    
    @FindBy(css="#toast-container .toast-error .toast-message")
    private List<WebElement> errorMsg;
    
    @FindBy(css="#toast-container .toast-message")
    private WebElement successmsg;
    
    @FindBy(css = ".fa-search")
    private WebElement searchBtn;

    @FindBy(css = ".modal-body .form-inline .form-group .k-select")
    private List<WebElement> selectSearchCol;

    @FindBy(css="ul[id='1001sColumnName_listbox'] li")
    private List<WebElement> columnNameList;

    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchCriteriaDropDwn;

    @FindBy(id = "1001sMaskedTextToSearch")
    private WebElement searchTextBox;

    @FindBy(css = "#1001sAddButton .k-i-add")
    private WebElement searchAddCriteriaBtn;

    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;
    
    @FindBy(xpath="//tbody/tr/td[4]")
    private WebElement rowdata;
	
    @FindBy(id="ModifyReason1")
    private WebElement deletereasontextbox;
    
    @FindBy(id="yesButton")
    private WebElement yesbtn;
    
    @FindBy(id="noButton")
    private WebElement nobtn;
    
    @FindBy(xpath="//label[@for='1001sRadioAND']")
    private WebElement andradiobtn;
    
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
    
    @FindBy(xpath="//button[text()='Close']")
    private WebElement searchClose;
    
    @FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
    
    @FindBy(xpath="//i[@class='fas fa-sync']")
    private WebElement clearsearch;
    
	public boolean isHolidayListPageDisplayed() {
		waitForLoad(driver);
        waitForJqueryLoad(driver);
		return holidayList.isEnabled();
	}
	public boolean isAddBtnDisplayed() {
    	return addNewHolidayListRecordBtn.isDisplayed() && addNewHolidayListRecordBtn.isEnabled();
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
		if(isElementExist(hlImg))
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
	public boolean groupby() {
		DragandDrop(AnnouncedHoliday,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(groupbyAnnouncedHoliday.getText()))
		{return true;}
		else
			return false;		
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
    
    public boolean verifyExportToExcel(String filepath) {
    	final File folder = new File(filepath);
		for (final File f : folder.listFiles()) {
		    if (f.getName().startsWith("Holiday List")) {
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
		Boolean Status=verifyExportPageFileDownload(filepath, "Holiday List");
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
				scrollToElement(headers.get(j));
				System.out.println(headers.get(j).getText());
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
	
	
	public void SortByAscending() {
		selectWebElement(AnnouncedHoliday);
		
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(StartDate);
		selectWebElement(StartDate);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public boolean addnewHolidayListCancel(HolidayListDetails details) throws Exception {
		String actualitems=items.getText();
		selectWebElement(addNewHolidayListRecordBtn);
		enterValueToTxtField(announcedHolidayTextbox,details.getAnnouncedHoliday());
		enterValueToTxtField(startDateTextbox,details.getStartDate());
		enterValueToTxtField(startTimeTextbox,details.getStartTime());
		enterValueToTxtField(endDateTextbox,details.getEndDate());
		enterValueToTxtField(endTimeTextbox,details.getEndTime());
		enterValueToTxtField(vdnTextbox,details.getVdn());
		selectWebElement(cancelbtn);
		if(actualitems.equals(items.getText()))
			return true;
		else
			return false;
	}
	public void addNewHolidayList(HolidayListDetails details) throws Exception {
		selectWebElement(addNewHolidayListRecordBtn);
		waitForJqueryLoad(driver);
		enterValueToTxtField(announcedHolidayTextbox,details.getAnnouncedHoliday());
		enterValueToTxtField(startDateTextbox,details.getStartDate());
		enterValueToTxtField(startTimeTextbox,details.getStartTime());
		enterValueToTxtField(endDateTextbox,details.getEndDate());
		enterValueToTxtField(endTimeTextbox,details.getEndTime());
		enterValueToTxtField(vdnTextbox,details.getVdn());
		selectWebElement(savebtn);
		
	}
	public boolean verifymessage() {
		if (errorMsg.size()>0)
			return false;
			waitUntilWebElementIsVisible(successmsg);
	    	if(successmsg.getText().contains("Successfully"))
			return true;
			else
			{return false;}
	}
	
	public String getSuccessMessage() {
		//		waitForJqueryLoad(driver);
		if(successmsg.isDisplayed())
			return successmsg.getText();
		else{return errorMsg.get(0).getText();}	
	}
	
	public boolean ExporttoExcelWithoutData(HolidayListDetails details) throws Exception {
		searchHolidayList(details.getStartDate());
		waitForJqueryLoad(driver);
		selectWebElement(exporttoexcel);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
		return false;
	}
	
	private void searchHolidayList(String startDate) throws Exception {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Start Date");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,startDate);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
		
	}
	public boolean editHolidaylistCancelbtn(HolidayListDetails details) throws Exception {
		searchHolidayList(details.getStartDate());
		selectWebElement(editButton);
		enterValueToTxtField(ModifyReasonTxtbox,details.getModifyReason());
		selectWebElement(cancelbtn);
		if(rowdata.getText().equals(details.getStartDate()))
			return true;
		else
		return false;
		
	}
	public void editHolidayListRecord(HolidayListDetails details) throws Exception {
		searchHolidayList(details.getStartDate());
		selectWebElement(editButton);		
		enterValueToTxtField(announcedHolidayTextbox,details.getUpdatedAnnouncedHoliday());
		enterValueToTxtField(ModifyReasonTxtbox,details.getModifyReason());
		selectWebElement(savebtn);
	}
	
	public boolean verifyDatabase(String  query) {
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
				if(headers.get(j).getText().equals("Last Changed On")){
					col=cols.get(j).getText().substring(11);
					}
				else if(headers.get(j).getText().equals("Start Date")||headers.get(j).getText().equals("End Date")){
					String x[]=cols.get(j).getText().split("/");
					String a=converttostring(x);
					String b=a.substring(0,2);
					String c=a.substring(2,4);
					String d=a.substring(4);
					col=d+c+b;
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
	
	
	public boolean verifydeleteNo(HolidayListDetails details) throws Exception {
		searchHolidayList(details.getStartDate());
		selectWebElement(deleteButton);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enterValueToTxtField(deletereasontextbox,details.getDeleteReason());
		selectWebElement(nobtn);
		if(rowdata.getText().equals(details.getUpdatedAnnouncedHoliday()))
				return true;
		else
		return false;
	}
	public void deleteHolidayListRecord(HolidayListDetails details) throws Exception {
		searchHolidayList(details.getStartDate());
		selectWebElement(deleteButton);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enterValueToTxtField(deletereasontextbox,details.getDeleteReason());
		selectWebElement(yesbtn);		
	}
	public void AddEmptyRecord(HolidayListDetails details) {
		selectWebElement(addNewHolidayListRecordBtn);
		selectWebElement(savebtn);
		selectWebElement(cancelbtn);
		
	}
	public void LeavingAnnouncedHolidayBlank(HolidayListDetails details) throws Exception {
		Thread.sleep(2000);
		selectWebElement(addNewHolidayListRecordBtn);
		enterValueToTxtField(startDateTextbox,details.getStartDate());
		enterValueToTxtField(startTimeTextbox,details.getStartTime());
		enterValueToTxtField(endDateTextbox,details.getEndDate());
		enterValueToTxtField(endTimeTextbox,details.getEndTime());
		enterValueToTxtField(vdnTextbox,details.getVdn());
		selectWebElement(savebtn);
		selectWebElement(cancelbtn);		
	}
	public void LeavingStartDateBlank(HolidayListDetails details) throws Exception {
		Thread.sleep(2000);
		selectWebElement(addNewHolidayListRecordBtn);
		enterValueToTxtField(announcedHolidayTextbox,details.getAnnouncedHoliday());
		enterValueToTxtField(startTimeTextbox,details.getStartTime());
		enterValueToTxtField(endDateTextbox,details.getEndDate());
		enterValueToTxtField(endTimeTextbox,details.getEndTime());
		enterValueToTxtField(vdnTextbox,details.getVdn());
		selectWebElement(savebtn);
		selectWebElement(cancelbtn);			
	}
	public void LeavingStartTimeBlank(HolidayListDetails details) throws Exception {
		Thread.sleep(2000);
		selectWebElement(addNewHolidayListRecordBtn);
		enterValueToTxtField(announcedHolidayTextbox,details.getAnnouncedHoliday());
		enterValueToTxtField(startDateTextbox,details.getStartDate());
		enterValueToTxtField(endDateTextbox,details.getEndDate());
		enterValueToTxtField(endTimeTextbox,details.getEndTime());
		enterValueToTxtField(vdnTextbox,details.getVdn());
		selectWebElement(savebtn);
		selectWebElement(cancelbtn);			
	}
	public void LeavingEndDateBlank(HolidayListDetails details) throws Exception {
		Thread.sleep(2000);
		selectWebElement(addNewHolidayListRecordBtn);
		enterValueToTxtField(announcedHolidayTextbox,details.getAnnouncedHoliday());
		enterValueToTxtField(startDateTextbox,details.getStartDate());
		enterValueToTxtField(startTimeTextbox,details.getStartTime());
		enterValueToTxtField(endTimeTextbox,details.getEndTime());
		enterValueToTxtField(vdnTextbox,details.getVdn());
		selectWebElement(savebtn);
		selectWebElement(cancelbtn);			
	}
	
	public void LeavingEndTimeBlank(HolidayListDetails details) throws Exception {
		Thread.sleep(2000);
		selectWebElement(addNewHolidayListRecordBtn);
		enterValueToTxtField(announcedHolidayTextbox,details.getAnnouncedHoliday());
		enterValueToTxtField(startDateTextbox,details.getStartDate());
		enterValueToTxtField(startTimeTextbox,details.getStartTime());
		enterValueToTxtField(endDateTextbox,details.getEndDate());
		waitUntilWebElementIsClickable(vdnTextbox);
		enterValueToTxtField(vdnTextbox,details.getVdn());
		selectWebElement(savebtn);
		selectWebElement(cancelbtn);			
	}
	
	public void LeavingVDNBlank(HolidayListDetails details) throws Exception {
		Thread.sleep(2000);
		selectWebElement(addNewHolidayListRecordBtn);
		enterValueToTxtField(announcedHolidayTextbox,details.getAnnouncedHoliday());
		enterValueToTxtField(startDateTextbox,details.getStartDate());
		enterValueToTxtField(startTimeTextbox,details.getStartTime());
		enterValueToTxtField(endDateTextbox,details.getEndDate());
		enterValueToTxtField(endTimeTextbox,details.getEndTime());
		selectWebElement(savebtn);
		selectWebElement(cancelbtn);			
	}
	public void EditLeavingModifyReasonBlank(HolidayListDetails details) throws Exception {
		searchHolidayList(details.getStartDate());
		selectWebElement(editButton);		
		selectWebElement(savebtn);
		selectWebElement(cancelbtn);
		
	}
	public void addDuplicateRecord(HolidayListDetails details) throws Exception {
		addNewHolidayList(details);
		selectWebElement(cancelbtn);	
	}
	
	public boolean clearAll(HolidayListDetails details) throws Exception {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Start Date");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,details.getStartDate());
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
	public boolean verifyinvalidsearchwithwrongdata(HolidayListDetails details) throws Exception {
		searchHolidayList(details.getStartDate());
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
