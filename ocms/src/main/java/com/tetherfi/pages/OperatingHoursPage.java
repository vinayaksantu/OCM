package com.tetherfi.pages;


import com.tetherfi.model.ivr.OperatingHoursDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OperatingHoursPage extends BasePage {

    public OperatingHoursPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".fa-search")
    private WebElement searchBtn;

    @FindBy(css = ".modal-body .form-inline .form-group .k-select")
    private List<WebElement> selectSearchCol;

    @FindBy(css="ul[id='1001sColumnName_listbox'] li")
    private List<WebElement> columnNameList;
    
    @FindBy(css="ul[id='1002sColumnName_listbox'] li")
    private List<WebElement> columnNameListtwo;

    @FindBy(css = "#1001sFormDropDown .k-i-arrow-60-down")
    private List<WebElement> selectSearchCriteria;

    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchCriteriaDropDwn;
    
    @FindBy(css="ul[id='1002sCriteria_listbox'] li")
    private List<WebElement> searchCriteriaDropDwntwo;
    
    @FindBy(id = "1001sTextToSearch")
    private WebElement searchTextBox;
    
    @FindBy(id="1002sTextToSearch")
    private WebElement searchTextBoxtwo;

    @FindBy(css=".toast-message")
    private WebElement successmsg;
    

    @FindBy(css=".ibox-title h5")
    private WebElement operatingHours;

    @FindBy(css="#create")
    private WebElement addNewOperatingHoursRcrdBtn;

    @FindBy(css=".k-grid-content")
    private WebElement gridContent;

    @FindBy(css=".k-edit-form-container .k-numerictextbox .k-numeric-wrap .k-formatted-value")
    private WebElement addVdnTextBox;

    @FindBy(id="VDN")
    private WebElement addVdnTextBox1;

    @FindBy(css=".k-grid-update")
    private WebElement SaveButton;
    
    @FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel']")
    private WebElement CancelButton;

    @FindBy(css=".form-group .k-multiselect .k-floatwrap")
    private WebElement addWeekDayDropdown;
    
    @FindBy(xpath="//span[@aria-owns='BypassPublicHoliday_listbox']")
    private WebElement addBypassPublicHolidayDropdown;

    @FindBy(css="ul[id='WeekDay_listbox'] li")
    private List<WebElement> addWeekDayListBox;
    
    @FindBy(css="ul[id='BypassPublicHoliday_listbox'] li")
    private List<WebElement> addBypassPublicHolidayListBox;

    @FindBy(css="#StartTime")
    private WebElement StartTimeTextBox;

    @FindBy(css="#EndTime")
    private WebElement EndTimeTextBox;

    @FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;

    @FindBy(id = "ModifyReason1")
    private WebElement deleteReasonTextBox;

    @FindBy(id = "yesButton")
    private WebElement deleteYesBtn;
    
    @FindBy(id="noButton")
    private WebElement nobtn;

    /*@FindBy(css = "#toast-container .toast-error")
    private List<WebElement> errorMsg;*/

    @FindBy(css="#toast-container .toast-error .toast-message")
    private List<WebElement> errorMsg;
    
    @FindBy(id="SelAllBut")
    private WebElement selectallbtn;
    
    @FindBy(xpath="//button[text()='Export to Excel']")
    private WebElement exporttoexcel;
    
    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;
    
    @FindBy(css = ".k-grid-edit")
    private WebElement editButton;
    
    @FindBy(id = "ModifyReason")
    private WebElement ModifyReasonTextBox;
    
    @FindBy(xpath="//i[@class='fas fa-business-time']")
    private WebElement OhImg;
    
    @FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;
	
	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;
	 
	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
	@FindBy(id="footer")
	private WebElement FooterButton;
	
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
    
    /*@FindBy(css="th a[class='k-header-column-menu']")
    private List<WebElement> headersDropdown;*/
    
    @FindBy(xpath="//a[@title='Edit Column Settings']")
    private List<WebElement> headersDropdown;
    
    /*@FindBy(css="#drillGrid th a[title='Edit Column Settings']")
    private List<WebElement> headersDropdown;*/
    
    @FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
    private List<WebElement> headersColumns;
     
    @FindBy(css="th a[class='k-link']")
    private List<WebElement> headersText;
    
    /*@FindBy(css="#drillGrid th a[class='k-link']")
    private List<WebElement> headersText;*/
    
    @FindBy(xpath="//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")
    private List<WebElement> tablerecord;
   
    @FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;
    
    @FindBy(id="navbarheader")
	private WebElement header;
    
    @FindBy(xpath="//span[@class='k-icon k-i-arrow-60-right k-menu-expand-arrow']")
    private WebElement coloumnarrow;
    
    @FindBy(id="tdrillGrid")
    private WebElement auditGridContent;
    
    @FindBy(xpath="//a[text()='Skill/VDN']")
    private WebElement vdn;
    
    @FindBy(xpath="//a[text()='Week Day']")
    private WebElement weekDay;
    
    @FindBy(xpath="//a[text()='Start Time']")
    private WebElement StartTime;
    
    @FindBy(xpath="//div[@data-role='droptarget']")
    private WebElement droptarget;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
    private WebElement groupbyVdn;
    
    @FindBy(xpath="//tbody/tr/td[2]")
    private WebElement rowdata;
    
    @FindBy(xpath="//a[@class='k-button k-bare k-button-icon k-window-action']")
    private List<WebElement> closebtn;
    
    @FindBy(id="1001sAddButton")
    private WebElement plusbtn;
    
    @FindBy(xpath="//label[@for='1001sRadioAND']")
    private WebElement andradiobtn;
    
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
    
    @FindBy(xpath="//button[text()='Close']")
    private WebElement searchClose;
    
    @FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
    
    @FindBy(xpath="//i[@class='fas fa-sync fa-spin']")
    private WebElement clearsearch;
    


    
    public boolean isOperatingHoursPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return operatingHours.isEnabled();
    }
    public void addNewOperatingHoursRecord(OperatingHoursDetails details) throws Exception {
        selectWebElement(addNewOperatingHoursRcrdBtn);
        Thread.sleep(2000);
        selectWebElement(addVdnTextBox);
        enterValueToTxtField(addVdnTextBox1,details.getVdnName());
        selectWebElement(addWeekDayDropdown);
        selectDropdownFromVisibleText(addWeekDayListBox,details.getWeekDay());
        Thread.sleep(2000);
        selectWebElement(StartTimeTextBox);
        enterValueToTxtField(StartTimeTextBox,details.getStartTime());
        selectWebElement(EndTimeTextBox);
        enterValueToTxtField(EndTimeTextBox,details.getEndTime());
        selectWebElement(addBypassPublicHolidayDropdown);
        selectDropdownFromVisibleText(addBypassPublicHolidayListBox,details.getBypassPublicHoliday());
        selectWebElement(SaveButton);

    }
    
    public void addNewAllDaysOperatingHoursRecord(OperatingHoursDetails details) throws Exception {
    	selectWebElement(addNewOperatingHoursRcrdBtn);
        Thread.sleep(2000);
        selectWebElement(addVdnTextBox);
        enterValueToTxtField(addVdnTextBox1,details.getVdnName());
        waitForJqueryLoad(driver);
        selectWebElement(selectallbtn);
        selectWebElement(StartTimeTextBox);
        enterValueToTxtField(StartTimeTextBox,details.getStartTime());
        selectWebElement(EndTimeTextBox);
        enterValueToTxtField(EndTimeTextBox,details.getEndTime());
        selectWebElement(addBypassPublicHolidayDropdown);
        selectDropdownFromVisibleText(addBypassPublicHolidayListBox,details.getBypassPublicHoliday());
        selectWebElement(SaveButton);
    }
    
    public boolean addnewOperatingHoursCancel(OperatingHoursDetails details) throws Exception {
    	String actualitems=items.getText();
    	selectWebElement(addNewOperatingHoursRcrdBtn);
        Thread.sleep(2000);
        selectWebElement(addVdnTextBox);
        enterValueToTxtField(addVdnTextBox1,details.getVdnName());
        waitForJqueryLoad(driver);
        selectWebElement(selectallbtn);
        selectWebElement(StartTimeTextBox);
        enterValueToTxtField(StartTimeTextBox,details.getStartTime());
        selectWebElement(EndTimeTextBox);
        enterValueToTxtField(EndTimeTextBox,details.getEndTime());
        selectWebElement(addBypassPublicHolidayDropdown);
        selectDropdownFromVisibleText(addBypassPublicHolidayListBox,details.getBypassPublicHoliday());
        selectWebElement(CancelButton);
        Thread.sleep(2000);
        if(actualitems.equals(items.getText()))
			return true;
		else
			return false;
	}

    public void searchOperatingHoursRecord(String vdn) throws Exception {
        selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Skill/VDN");
        Thread.sleep(2000);
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,vdn);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    
    public boolean verifySearchIsNotEqualTo(String weekday) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("Week Day", weekday);
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Week Day");
        Thread.sleep(2000);
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
        enterValueToTxtField(searchTextBox,weekday);		
        selectWebElement(searchSearchBtn);
        Thread.sleep(5000);
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
    
    public boolean verifySearchContains(String weekday) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Week Day");
        Thread.sleep(2000);
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
        enterValueToTxtField(searchTextBox,weekday);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        Thread.sleep(5000);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("Week Day").toUpperCase().contains(weekday.toUpperCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
    
    public boolean verifySearchDoesNotContains(String weekday) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Week Day");
        Thread.sleep(2000);
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not contain");
        enterValueToTxtField(searchTextBox,weekday);		
        selectWebElement(searchSearchBtn);
        Thread.sleep(5000);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(!map1.get("Week Day").toLowerCase().contains(weekday.toLowerCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	
	public boolean verifySearchStartsWith(String weekday) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Week Day");
        Thread.sleep(2000);
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
        enterValueToTxtField(searchTextBox,weekday);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        Thread.sleep(5000);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("Week Day").toLowerCase().startsWith(weekday.toLowerCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	
	public boolean verifySearchEndsWith(String weekday) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Week Day");
        Thread.sleep(2000);
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
        enterValueToTxtField(searchTextBox,weekday);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        Thread.sleep(5000);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("Week Day").toUpperCase().endsWith(weekday.toUpperCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
    
    public void BooleansearchOperatingHoursRecord(String vdn, String weekday) throws Exception {
        selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Skill/VDN");
        Thread.sleep(2000);
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,vdn);
        selectWebElement(plusbtn);
        moveToElement(andradiobtn);
        selectWebElement(andradiobtn);
        Thread.sleep(2000);
        selectWebElement(selectSearchCol.get(2));
        selectDropdownFromVisibleTextContains(columnNameListtwo,"Week");
        Thread.sleep(2000);
        selectWebElement(selectSearchCol.get(3));
        selectDropdownFromVisibleText(searchCriteriaDropDwntwo,"Is equal to");
        enterValueToTxtField(searchTextBoxtwo,weekday);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }

    public void editOperatingHoursRecord(OperatingHoursDetails operatingHoursDetails) throws Exception {
        BooleansearchOperatingHoursRecord(operatingHoursDetails.getVdnName(),operatingHoursDetails.getWeekDay());
        selectWebElement(editButton);
        selectWebElement(StartTimeTextBox);
        enterValueToTxtField(StartTimeTextBox,operatingHoursDetails.getStartTime());
        selectWebElement(EndTimeTextBox);
        enterValueToTxtField(EndTimeTextBox,operatingHoursDetails.getEndTime());
        selectWebElement(ModifyReasonTextBox);
        waitForJqueryLoad(driver);
        enterValueToTxtFieldWithoutClear(ModifyReasonTextBox,operatingHoursDetails.getModifyReason());
        selectWebElement(SaveButton);
    }
    
    public boolean editOperatingHoursCancelbtn(OperatingHoursDetails operatingHoursDetails) throws Exception {
    	BooleansearchOperatingHoursRecord(operatingHoursDetails.getVdnName(),operatingHoursDetails.getWeekDay());
        selectWebElement(editButton);
        selectWebElement(StartTimeTextBox);
        enterValueToTxtField(StartTimeTextBox,operatingHoursDetails.getStartTime());
        selectWebElement(EndTimeTextBox);
        enterValueToTxtField(EndTimeTextBox,operatingHoursDetails.getEndTime());
        selectWebElement(ModifyReasonTextBox);
        enterValueToTxtField(ModifyReasonTextBox,operatingHoursDetails.getModifyReason());
        selectWebElement(CancelButton);
        if(rowdata.getText().equals(operatingHoursDetails.getVdnName()))
			return true;
        else
    	return false;	
    }
    
    public void editInvalidRecord(OperatingHoursDetails operatingHoursDetails) throws Exception {
    	editOperatingHoursRecord(operatingHoursDetails);
        selectWebElement(CancelButton);	
	}

    public void editRecordWithoutReason(OperatingHoursDetails operatingHoursDetails) throws Exception {
    	BooleansearchOperatingHoursRecord(operatingHoursDetails.getVdnName(),operatingHoursDetails.getWeekDay());
        selectWebElement(editButton);
        selectWebElement(StartTimeTextBox);
        enterValueToTxtField(StartTimeTextBox,operatingHoursDetails.getStartTime());
        selectWebElement(EndTimeTextBox);
        enterValueToTxtField(EndTimeTextBox,operatingHoursDetails.getEndTime());
        selectWebElement(SaveButton);
        selectWebElement(CancelButton);
		
	}
    public void deleteOperatingHoursRecord(OperatingHoursDetails operatingHoursDetails) throws Exception {
        searchOperatingHoursRecord(operatingHoursDetails.getVdnName());
        Thread.sleep(2000);
        selectWebElement(deleteButton);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        enterValueToTxtFieldWithoutClear(deleteReasonTextBox,operatingHoursDetails.getDeleteReason());
        selectWebElement(deleteYesBtn);
    }

    public String getSuccessMessage() {
		waitForJqueryLoad(driver);
		if(successmsg.isDisplayed())
			return successmsg.getText();
		else{return errorMsg.get(0).getText();}	
	}
    
    public String getErrorMessage() {
		waitForJqueryLoad(driver);
		if(errorMsg.get(0).isDisplayed())
			return errorMsg.get(0).getText();
		else{return successmsg.getText();}	
	}
       
    public boolean verifyNewRecordCreated(){
       waitForJqueryLoad(driver);
        //if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Created Successfully"))
        {return true;}else{return false;}
    }
    
    public boolean verifyErrorMsg() {
    	waitForJqueryLoad(driver);
    	if(errorMsg.size()>0){return false;}
    	else 
    		return true;
    }
    
    public boolean verifyRecordUpdated(){
        waitForJqueryLoad(driver);
        //if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Updated Successfully"))
        {return true;}else{return false;}
    }
    
    public boolean verifyRecordDeleted(){
          waitForJqueryLoad(driver);
       // if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Deleted Successfully"))
        {return true;}else{return false;}
    }

    public boolean isAddBtnDisplayed() {
    	return addNewOperatingHoursRcrdBtn.isDisplayed() && addNewOperatingHoursRcrdBtn.isEnabled();
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
		if(isElementExist(OhImg))
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
	
	public boolean VerifyFooterInfo() {
		selectWebElement(FooterButton);
		waitForJqueryLoad(driver);
		if(FooterButton.isDisplayed())
		{
			return true;
		}
		else 
			return false; 
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
		    if (f.getName().startsWith("Operating Hours")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "Operating Hours");
		return Status;
	}
	public boolean verifyexportToExcelSheet(List<Map<String, String>> maplist) {
		List<Map<String,String>> UI=getdata(); 
		System.out.println(maplist);
		System.out.println(UI);
		if(UI.equals(maplist))
		return true;
		else
		return false;
	}
	
	/*private List<Map<String,String>> getdata(){
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
	}*/
	
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
				if(headers.get(j).getText().equals("Insert Date Time")){
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
			nextPageIcon.click();
			waitForJqueryLoad(driver);}
		}
			return arr;
	}
	
	
	public boolean verifyDatabase(String query) {
		List<Map<String,String>> database=database(query);
		selectWebElement(StartTime);
		waitForJqueryLoad(driver);
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
	
	public boolean ExporttoExcelWithoutData(OperatingHoursDetails details) throws Exception {
		searchOperatingHoursRecord(details.getVdnName());
		waitForJqueryLoad(driver);
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
		return false;
	}
	
	public void SortByAscending() {
		selectWebElement(weekDay);
		waitForJqueryLoad(driver);
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(weekDay);
		waitForJqueryLoad(driver);
		selectWebElement(weekDay);
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
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
        waitForJqueryLoad(driver);
        int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
        selectWebElement(previousPageIcon);
        waitForJqueryLoad(driver);
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
            waitForJqueryLoad(driver);
            int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
            selectWebElement(firstPageIcon);
            waitForJqueryLoad(driver);
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
                    Thread.sleep(2000);
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
       
    /*public boolean verifycolumnsHeaderDisabled() {
        boolean status = false;
        WebElement ele = headersDropdown.get(0);
            if (ele.isDisplayed()) {
                try {
                    selectWebElement(ele);
                    Thread.sleep(2000);
                    selectWebElement(headersColumns.get(2));
                    Thread.sleep(2000);
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
    }*/
    
    public boolean verifycolumnsHeaderDisabled() {
        boolean status = false;
        WebElement ele = headersDropdown.get(0);
        scrollToElement(ele);
            if (ele.isDisplayed()) {
                try {
                    selectWebElement(ele);
                    Thread.sleep(2000);
                    selectWebElement(headersColumns.get(2));
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 3; i < headersColumns.size(); i++) {
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
                    Thread.sleep(2000);
                    selectWebElement(headersColumns.get(2));
                    Thread.sleep(2000);
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
		DragandDrop(vdn,droptarget);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(groupbyVdn.getText()))
		{return true;}
		else
			return false;		
	}
    
    public boolean verifydeleteNo(OperatingHoursDetails details) throws Exception {
    	searchOperatingHoursRecord(details.getVdnName());	
    	Thread.sleep(2000);
    	selectWebElement(deleteButton);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enterValueToTxtField(deleteReasonTextBox,details.getDeleteReason());
		selectWebElement(nobtn);
		if(rowdata.getText().equals(details.getVdnName()))
				return true;
		else
		return false;
	}
	public boolean deleteclose(OperatingHoursDetails details) throws Exception {
		searchOperatingHoursRecord(details.getVdnName());		
    	selectWebElement(deleteButton);		
    	Thread.sleep(2000);
    	enterValueToTxtField(deleteReasonTextBox,details.getDeleteReason());
    	moveToElement(closebtn.get(2));
		selectWebElement(closebtn.get(2));
		if(rowdata.getText().equals(details.getVdnName()))
				return true;
		else
		return false;
	}
	public boolean clearAll(OperatingHoursDetails details) throws Exception {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Skill/VDN");
        selectWebElement(selectSearchCol.get(1));
        Thread.sleep(2000);
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,details.getVdnName());
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
	public boolean verifyinvalidsearchwithwrongdata(OperatingHoursDetails operatingHoursDetails) throws Exception {
		searchOperatingHoursRecord(operatingHoursDetails.getVdnName());		
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
	public void LeavingAllFieldsBlank(OperatingHoursDetails details) {
		selectWebElement(addNewOperatingHoursRcrdBtn);
		waitForJqueryLoad(driver);
        selectWebElement(SaveButton);	
        selectWebElement(CancelButton);
	}
	public void LeavingVDNBlank(OperatingHoursDetails details) throws Exception {
		Thread.sleep(2000);
		selectWebElement(addNewOperatingHoursRcrdBtn);
        Thread.sleep(2000);
        selectWebElement(addWeekDayDropdown);
        selectDropdownFromVisibleText(addWeekDayListBox,details.getWeekDay());
        Thread.sleep(2000);
        selectWebElement(StartTimeTextBox);
        enterValueToTxtField(StartTimeTextBox,details.getStartTime());
        selectWebElement(EndTimeTextBox);
        enterValueToTxtField(EndTimeTextBox,details.getEndTime());
        selectWebElement(addBypassPublicHolidayDropdown);
        selectDropdownFromVisibleText(addBypassPublicHolidayListBox,details.getBypassPublicHoliday());
        selectWebElement(SaveButton);
        selectWebElement(CancelButton);
		
	}
	public void LeavingWeekDayBlank(OperatingHoursDetails details) throws Exception {
		Thread.sleep(2000);
		selectWebElement(addNewOperatingHoursRcrdBtn);
        Thread.sleep(2000);
        selectWebElement(addVdnTextBox);
        enterValueToTxtField(addVdnTextBox1,details.getVdnName());
        selectWebElement(StartTimeTextBox);
        enterValueToTxtField(StartTimeTextBox,details.getStartTime());
        selectWebElement(EndTimeTextBox);
        enterValueToTxtField(EndTimeTextBox,details.getEndTime());
        selectWebElement(addBypassPublicHolidayDropdown);
        selectDropdownFromVisibleText(addBypassPublicHolidayListBox,details.getBypassPublicHoliday());
        selectWebElement(SaveButton);
        selectWebElement(CancelButton);
	}
	public void LeavingStartTimeBlank(OperatingHoursDetails details) throws Exception {
		Thread.sleep(2000);
		selectWebElement(addNewOperatingHoursRcrdBtn);
        Thread.sleep(2000);
        selectWebElement(addVdnTextBox);
        enterValueToTxtField(addVdnTextBox1,details.getVdnName());
        selectWebElement(addWeekDayDropdown);
        selectDropdownFromVisibleText(addWeekDayListBox,details.getWeekDay());
        Thread.sleep(2000);
        selectWebElement(EndTimeTextBox);
        enterValueToTxtField(EndTimeTextBox,details.getEndTime());
        selectWebElement(addBypassPublicHolidayDropdown);
        selectDropdownFromVisibleText(addBypassPublicHolidayListBox,details.getBypassPublicHoliday());
        selectWebElement(SaveButton);
        selectWebElement(CancelButton);
		
	}
	public void LeavingEndTimeBlank(OperatingHoursDetails details) throws Exception {
		Thread.sleep(2000);
		selectWebElement(addNewOperatingHoursRcrdBtn);
        Thread.sleep(2000);
        selectWebElement(addVdnTextBox);
        enterValueToTxtField(addVdnTextBox1,details.getVdnName());
        selectWebElement(addWeekDayDropdown);
        selectDropdownFromVisibleText(addWeekDayListBox,details.getWeekDay());
        Thread.sleep(2000);
        selectWebElement(StartTimeTextBox);
        enterValueToTxtField(StartTimeTextBox,details.getStartTime());
        selectWebElement(addBypassPublicHolidayDropdown);
        selectDropdownFromVisibleText(addBypassPublicHolidayListBox,details.getBypassPublicHoliday());
        selectWebElement(SaveButton);
        selectWebElement(CancelButton);
		
	}
	public void LeavingBypassPublicHolidayBlank(OperatingHoursDetails details) throws Exception {
		Thread.sleep(2000);
		selectWebElement(addNewOperatingHoursRcrdBtn);
        Thread.sleep(2000);
        selectWebElement(addVdnTextBox);
        enterValueToTxtField(addVdnTextBox1,details.getVdnName());
        selectWebElement(addWeekDayDropdown);
        selectDropdownFromVisibleText(addWeekDayListBox,details.getWeekDay());
        Thread.sleep(2000);
        selectWebElement(StartTimeTextBox);
        enterValueToTxtField(StartTimeTextBox,details.getStartTime());
        selectWebElement(EndTimeTextBox);
        enterValueToTxtField(EndTimeTextBox,details.getEndTime());
        selectWebElement(SaveButton);
	}
	public void addVDNvaluelessthanfour(OperatingHoursDetails details) throws Exception {
		selectWebElement(addNewOperatingHoursRcrdBtn);
        Thread.sleep(2000);
        selectWebElement(addVdnTextBox);
        enterValueToTxtField(addVdnTextBox1,details.getVdnName());
        selectWebElement(addWeekDayDropdown);
        selectDropdownFromVisibleText(addWeekDayListBox,details.getWeekDay());
        Thread.sleep(2000);
        selectWebElement(StartTimeTextBox);
        enterValueToTxtField(StartTimeTextBox,details.getStartTime());
        selectWebElement(EndTimeTextBox);
        enterValueToTxtField(EndTimeTextBox,details.getEndTime());
        selectWebElement(addBypassPublicHolidayDropdown);
        selectDropdownFromVisibleText(addBypassPublicHolidayListBox,details.getBypassPublicHoliday());
        selectWebElement(SaveButton);
		
	}
	public void addStarttimegreaterthanEndtime(OperatingHoursDetails details) throws Exception {
		Thread.sleep(2000);
		selectWebElement(addNewOperatingHoursRcrdBtn);
        Thread.sleep(2000);
        selectWebElement(addVdnTextBox);
        enterValueToTxtField(addVdnTextBox1,details.getVdnName());
        selectWebElement(addWeekDayDropdown);
        selectDropdownFromVisibleText(addWeekDayListBox,details.getWeekDay());
        Thread.sleep(2000);
        selectWebElement(StartTimeTextBox);
        enterValueToTxtField(StartTimeTextBox,details.getStartTime());
        selectWebElement(EndTimeTextBox);
        enterValueToTxtField(EndTimeTextBox,details.getEndTime());
        selectWebElement(addBypassPublicHolidayDropdown);
        selectDropdownFromVisibleText(addBypassPublicHolidayListBox,details.getBypassPublicHoliday());
        selectWebElement(SaveButton);
		
	}
	public void addStarttimesameasEndtime(OperatingHoursDetails details) throws Exception {
		Thread.sleep(2000);
		selectWebElement(addNewOperatingHoursRcrdBtn);
        Thread.sleep(2000);
        selectWebElement(addVdnTextBox);
        enterValueToTxtField(addVdnTextBox1,details.getVdnName());
        selectWebElement(addWeekDayDropdown);
        selectDropdownFromVisibleText(addWeekDayListBox,details.getWeekDay());
        Thread.sleep(2000);
        selectWebElement(StartTimeTextBox);
        enterValueToTxtField(StartTimeTextBox,details.getStartTime());
        selectWebElement(EndTimeTextBox);
        enterValueToTxtField(EndTimeTextBox,details.getEndTime());
        selectWebElement(addBypassPublicHolidayDropdown);
        selectDropdownFromVisibleText(addBypassPublicHolidayListBox,details.getBypassPublicHoliday());
        selectWebElement(SaveButton);
	}	
}
