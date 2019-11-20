package com.tetherfi.pages;

import com.tetherfi.model.tmac.WaitTimeColorConfigDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WaitTimeColorConfigPage extends BasePage {

    public WaitTimeColorConfigPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement waitTimeColorConfig;
    
    @FindBy(xpath="//i[@class='fas fa-clock']")
    private WebElement wTCCImg;
    
    @FindBy(xpath="//th[3]/a/span[@class='k-icon k-i-more-vertical']")
    private WebElement headerColumn;
    
    @FindBy(xpath="//span[@class='k-icon k-i-arrow-60-right k-menu-expand-arrow']")
	private WebElement coloumnarrow;
    
    @FindBy(xpath="//input[@data-field='StartTime']")
    private WebElement startdurationcheckbox;
    
    @FindBy(xpath="//input[@data-field='EndTime']")
    private WebElement enddurationcheckbox;
    
    @FindBy(xpath="//input[@data-field='Color']")
    private WebElement colorcheckbox;
    
    @FindBy(xpath="//input[@data-field='ColorCode']")
    private WebElement colorcodecheckbox;
    
    @FindBy(xpath="//input[@data-field='LastChangedBy']")
    private WebElement lastChangedBycheckbox;
    
    @FindBy(xpath="//input[@data-field='LastChangedOn']")
    private WebElement lastChangedOncheckbox;
    
    @FindBy(xpath="//a[text()='Start Duration']")
    private WebElement startDuration;
    
    @FindBy(xpath="//a[text()='Color']")
    private WebElement color;
    
    @FindBy(xpath="//a[text()='Color Code']")
    private WebElement colorCode;
    
    @FindBy(xpath="//a[text()='Last Changed By']")
    private WebElement lastChangedBy;
    
    @FindBy(xpath="//a[text()='Last Changed On']")
    private WebElement lastChangedOn;
    
    @FindBy(xpath="//a[text()='End Duration']")
    private WebElement endDuration;
    
    @FindBy(xpath="//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")
	private List<WebElement> tablerecord;
    
    @FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;

	@FindBy(id="navbarheader")
	private WebElement header;

	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen'")
	private WebElement fullscreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;

	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;

    @FindBy(id="create")
    private WebElement addNewWaitTimeColorConfigRecordBtn;

    @FindBy(css=".k-edit-form-container")
    private WebElement popupContent;

    @FindBy(id="StartTime")
    private WebElement startTime;

    @FindBy(id="EndTime")
    private WebElement endTime;

    @FindBy(css=".k-colorpicker .k-select")
    private WebElement colorPicker;

    @FindBy(css=".k-color-value")
    private WebElement colorValue;

    @FindBy(css=".apply")
    private WebElement applyBtn;

    @FindBy(css=".k-edit-form-container .k-grid-update")
    private WebElement saveBtn;

    @FindBy(css=".k-grid-cancel")
    private WebElement cancelBtn;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css="#toast-container .toast-error .toast-message")
    private List<WebElement> errorMsg;

    @FindBy(css=".search-link")
    private WebElement searchLink;

    @FindBy(css=".modal-body .form-inline .form-group .k-select")
    private List<WebElement> selectSearchColumn;

    @FindBy(css="ul[id='1001sColumnName_listbox'] li")
    private List<WebElement> columnNameList;

    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchTypeList;

    @FindBy(id="1001sMaskedTextToSearch")
    private List<WebElement> searchText;

    @FindBy(css=".modal-footer .k-primary")
    private WebElement searchBtn;

    @FindBy(css="#tGrid .k-grid-content")
    private WebElement gridContent;

    @FindBy(css=".k-grid-CustomDelete")
    private WebElement deleteBtn;

    @FindBy(css=".form-group #ModifyReason1")
    private  WebElement deleteReasonTextBox;

    @FindBy(css=".k-grid-edit")
    private WebElement editBtn;

    @FindBy(css=".k-edit-form-container #ModifyReason")
    private  WebElement modifyReasonTextBox;

    @FindBy(id="yesButton")
    private WebElement yesBtn;

    @FindBy(id="noButton")
    private WebElement noBtn;
    
    @FindBy(xpath="//tbody/tr/td[5]")
	private WebElement tabledata;
    
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
    
    @FindBy(xpath="//button[text()='Close']")
    private WebElement closesearch;
    
    @FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
    
    @FindBy(xpath="//i[@class='fas fa-sync']")
    private WebElement clearsearch;
    
    @FindBy(id="tGrid")
    private WebElement auditGridContent;
    
    @FindBy(xpath="//tbody/tr/td[2]")
    private WebElement rowdata;
    
    @FindBy(xpath="//p[@class='k-reset']")
    private WebElement groupby;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[5]/div")
	private WebElement groupbycolor;
    
    @FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;
    
    @FindBy(css="a[aria-label='Go to the first page']")
    private WebElement firstPageIcon;
    
    @FindBy(css="a[aria-label='Go to the previous page']")
    private WebElement previousPageIcon;
    
    @FindBy(css=".k-pager-numbers .k-state-selected")
    private WebElement pageNumber;
    @FindBy(css="a[aria-label='Go to the next page']")
    private WebElement nextPageIcon;
    
    @FindBy(css="a[aria-label='Go to the last page']")
    private WebElement lastPageIcon;
    
    @FindBy(css=".k-pager-sizes .k-icon")
    private WebElement pagerDropdown;
    
    @FindBy(css=".k-animation-container ul li")
    private List<WebElement> pageSizeListBox;
    
    @FindBy(xpath="//span[@class='k-input']")
    private WebElement pagerSize;
    
    @FindBy(css="th a[class='k-header-column-menu']")
    private List<WebElement> headersDropdown;
    
    @FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
    private List<WebElement> headersColumns;
    
    @FindBy(css="th a[class='k-link']")
    private List<WebElement> headersText;
    
    @FindBy(css = ".modal-body .form-inline .form-group .k-select")
	private List<WebElement> selectSearchCol;
	
	@FindBy(css="ul[id='1001sCriteria_listbox'] li")
	private List<WebElement> searchCriteriaDropDwn;
	
	@FindBy(id = "1001sTextToSearch")
	private WebElement searchTextBox;
    
    @FindBy(css = ".modal-footer .button-theme")
	private WebElement searchSearchBtn;
    

    public boolean isWaitTimeColorConfigPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return waitTimeColorConfig.isEnabled();
    }
    public void addNewWaitTimeColorConfigRecord(WaitTimeColorConfigDetails details) throws Exception {
        selectWebElement(addNewWaitTimeColorConfigRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        Thread.sleep(1000);
        selectWebElement(startTime);
        enterValueToTxtFieldWithoutClear(startTime, details.getStartTime());
        selectWebElement(endTime);
        enterValueToTxtFieldWithoutClear(endTime, details.getEndTime());
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(colorPicker);
        selectWebElement(colorValue);
        enterValueToTxtField(colorValue,details.getColorcode());
        selectWebElement(applyBtn);
        selectWebElement(saveBtn);
    }
    
    public boolean verifySearchIsNotEqualTo(String colorcode) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("Color Code", colorcode);
		selectWebElement(searchLink);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Color Code");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
        enterValueToTxtField(searchTextBox,colorcode);		
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
    
    public boolean verifySearchContains(String colorcode) throws Exception {
		Boolean Status=false;
		selectWebElement(searchLink);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Color Code");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
        enterValueToTxtField(searchTextBox,colorcode);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("Color Code").toUpperCase().contains(colorcode.toUpperCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	public boolean verifySearchDoesNotContains(String colorcode) throws Exception {
		Boolean Status=false;
		selectWebElement(searchLink);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Color Code");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not contain");
        enterValueToTxtField(searchTextBox,colorcode);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(!map1.get("Color Code").toLowerCase().contains(colorcode.toLowerCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	
	public boolean verifySearchStartsWith(String colorcode) throws Exception {
		Boolean Status=false;
		selectWebElement(searchLink);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Color Code");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
        enterValueToTxtField(searchTextBox,colorcode);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("Color Code").toLowerCase().startsWith(colorcode.toLowerCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	
	public boolean verifySearchEndsWith(String colorcode) throws Exception {
		Boolean Status=false;
		selectWebElement(searchLink);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Color Code");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
        enterValueToTxtField(searchTextBox,colorcode);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("Color Code").toUpperCase().endsWith(colorcode.toUpperCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
    public void searchWaitTimeColorConfigRecord(String StartTime) throws Exception  {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Start Duration");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtBox1(searchText.get(0),StartTime);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public void editWaitTimeColorConfigRecord(WaitTimeColorConfigDetails details) throws Exception {
        searchWaitTimeColorConfigRecord(details.getStartTime());
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        selectWebElement(startTime);
        enterValueToTxtBox1(startTime, details.getUpdatedStartTime());
        selectWebElement(endTime);
        enterValueToTxtBox1(endTime, details.getEndTime());
        selectWebElement(colorPicker);
        selectWebElement(colorValue);
        enterValueToTxtField(colorValue,details.getUpdatedColorCode());
        selectWebElement(applyBtn);
        enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
        btnClick(saveBtn);
    }
    
    public void deleteWaitTimeColorConfigRecord(String Starttime, String reason) throws Exception {
        searchWaitTimeColorConfigRecord(Starttime);
        btnClick(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        enterValueToTxtFieldWithoutClear(deleteReasonTextBox,reason);
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(yesBtn);
    }
    
    public boolean deleteNo(String Starttime, String reason) throws Exception {
		searchWaitTimeColorConfigRecord(Starttime);
        btnClick(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtFieldWithoutClear(deleteReasonTextBox,reason);
        selectWebElement(noBtn);
        if(rowdata.getText().equals(Starttime))
        {return true;}
        else
        	return false;
		
	}

    public String getMessage(){
        if(errorMsg.size()>0){return errorMsg.get(0).getText();}
        else {
         return successmsg.getText();}
    }
	public boolean verifylogo() {
		if(isElementExist(wTCCImg))
		{return true;}
		else
		return false;
	}
	
	public boolean verifyStartDurationLabel() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		Boolean Status=startdurationcheckbox.isSelected();
		if(Status.equals(startDuration.isDisplayed()))
		{return true;}
		else
			return false;
	}

	public boolean verifyEndDurationLabel() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		Boolean Status=enddurationcheckbox.isSelected();
		if(Status.equals(endDuration.isDisplayed()))
		{return true;}
		else
			return false;
	}
	public boolean verifyColorCodeLabel() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		Boolean Status=colorcodecheckbox.isSelected();
		if(Status.equals(colorCode.isDisplayed()))
		{return true;}
		else
			return false;
	}
	public boolean verifyColorLabel() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		Boolean Status=colorcheckbox.isSelected();
		if(Status.equals(color.isDisplayed()))
		{return true;}
		else
			return false;
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
			return false;
	}
	public boolean verifyLastChangedByLabel() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		Boolean Status=lastChangedBycheckbox.isSelected();
		if(Status.equals(lastChangedBy.isDisplayed()))
		{return true;}
		else
			return false;
	}
	public boolean verifyLastChangedOnLabel() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		Boolean Status=lastChangedOncheckbox.isSelected();
		if(Status.equals(lastChangedOn.isDisplayed()))
		{return true;}
		else
			return false;
	}
	public boolean maximizewindow() {
		selectWebElement(maximize);
		waitForJqueryLoad(driver);
		if(fullscreen!=null)
		{System.out.println("maximized successfully");
		return true;}
		else 
		{return false;}
	}
	public boolean minimizewindow() {
		selectWebElement(minimize);
		waitForJqueryLoad(driver);
		if(header!=null)
		{System.out.println("minimized successfully");
		return true;}
		else 
			return false;
	}
	public boolean addNewCancel(WaitTimeColorConfigDetails details) throws Exception {
		String actualitems=items.getText();
		selectWebElement(addNewWaitTimeColorConfigRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        selectWebElement(startTime);
        enterValueToTxtField(startTime, details.getStartTime());
        selectWebElement(endTime);
        enterValueToTxtField(endTime, details.getEndTime());
        selectWebElement(colorPicker);
        selectWebElement(colorValue);
        enterValueToTxtField(colorValue,details.getColorcode());
        selectWebElement(applyBtn);
        selectWebElement(cancelBtn);
        if(actualitems.equals(items.getText()))
        	return true;
        else
		return false;
	}
	
	public boolean editcancel(WaitTimeColorConfigDetails details) throws Exception {
		searchWaitTimeColorConfigRecord(details.getStartTime());
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        selectWebElement(startTime);
        enterValueToTxtField(startTime, details.getUpdatedStartTime());
        selectWebElement(endTime);
        enterValueToTxtField(endTime, details.getEndTime());
        selectWebElement(colorPicker);
        selectWebElement(colorValue);
        enterValueToTxtField(colorValue,details.getUpdatedColorCode());
        selectWebElement(applyBtn);
        enterValueToTxtField(modifyReasonTextBox,details.getModifyReason());
        selectWebElement(cancelBtn);
        if(tabledata.getText().equals(details.getColorcode()))
        {return true;}
        else
        	return false;
	}
	public boolean clearAll(WaitTimeColorConfigDetails details) throws Exception {
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Start Duration");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),details.getStartTime());
        selectWebElement(clearall);
        Thread.sleep(1000);
		if(searchText.get(0).isEnabled())
			return true;
		else
			return false;
	}
	public boolean verifyclose() {
		selectWebElement(closesearch);
		if(gridContent.isDisplayed())
			return true;
		else
			return false;
	}
	public boolean verifyinvalidsearch(WaitTimeColorConfigDetails details) throws Exception {
		searchWaitTimeColorConfigRecord(details.getStartTime());
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
	
	public boolean verifyExportToExcel(String filePath) {
		final File folder = new File(filePath);
		for (final File f : folder.listFiles()) {
		    if (f.getName().startsWith("Wait Time")) {
		        f.delete();
		    }
		}
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath, "Wait Time Color Config");
		return Status;
	}
	
	private List<Map<String,String>> getdata(){
		int item=Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
        int pagersize=Integer.valueOf(pagerSize.getText());
        int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		waitUntilWebElementIsVisible(auditGridContent);
		for(int k=0;k<=pages;k++){
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
	
	private List<Map<String,String>> gettable(){
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
			String col="";
			for(int j=1;j<headers.size();j++){
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
	
	public boolean verifyexportToExcelSheet(List<Map<String, String>> maplist) {
		List<Map<String,String>> UI=getdata(); 
		System.out.println(UI);
		System.out.println(maplist);
		if(UI.equals(maplist))
		return true;
		else
		return false;
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
	public boolean groupby() {
		DragandDrop(color,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(groupbycolor.getText()))
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
                if(item<=5)
                	return true;
                else {
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
	public void addRecordWithoutStartTime(WaitTimeColorConfigDetails details) throws Exception {
		selectWebElement(addNewWaitTimeColorConfigRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        selectWebElement(endTime);
        enterValueToTxtField(endTime, details.getEndTime());
        selectWebElement(colorPicker);
        selectWebElement(colorValue);
        enterValueToTxtField(colorValue,details.getColorcode());
        selectWebElement(applyBtn);
        selectWebElement(saveBtn);
        selectWebElement(cancelBtn);
	}
	public boolean verifymessage() {
		waitUntilWebElementListIsVisible(errorMsg);									 
		if(errorMsg.size()>0)
		return false;
		else 
			return true;
	}
	public void addRecordWithoutEndTime(WaitTimeColorConfigDetails details) throws Exception {
		selectWebElement(addNewWaitTimeColorConfigRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        selectWebElement(startTime);
        enterValueToTxtField(startTime, details.getStartTime());
        selectWebElement(colorPicker);
        selectWebElement(colorValue);
        enterValueToTxtField(colorValue,details.getColorcode());
        selectWebElement(applyBtn);
        selectWebElement(saveBtn);
        selectWebElement(cancelBtn);	
	}
	public boolean verifyDropDownOfAllHeaders() {
        boolean status = false;
        try {for (WebElement ele : headersDropdown) {
            scrollToElement(ele);
            status = false;
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
		
	private void waitforElementIsClickable(WebElement ele){
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(ele));	
	}

	public boolean isAddBtnDisplayed() {
    	return addNewWaitTimeColorConfigRecordBtn.isDisplayed() && addNewWaitTimeColorConfigRecordBtn.isEnabled();
    }
    
    public boolean isEditBtnDisplayed() {
    	Boolean status = false;
    	try {
    		if(editBtn.isDisplayed() && editBtn.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
    }
    
    public boolean isDeleteBtnDisplayed() {
    	Boolean status = false;
    	try {
    		if(deleteBtn.isDisplayed() && deleteBtn.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
    }
    
    public boolean isExportBtnDisplayed() {
    	return exporttoexcel.isDisplayed() && exporttoexcel.isEnabled();
    }
	
	public void SortByAscending() {
		selectWebElement(startDuration);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void SortByDescending() {
		selectWebElement(endDuration);
		selectWebElement(endDuration);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	public boolean ExporttoExcelWithoutData(WaitTimeColorConfigDetails waitTimeColorConfigDetails) throws Exception {
		searchWaitTimeColorConfigRecord(waitTimeColorConfigDetails.getStartTime());	
		waitForJqueryLoad(driver);
		selectWebElement(exporttoexcel);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
		return false;
	}
	public String getSuccessMessage() {
		if(successmsg.isDisplayed()){
			return successmsg.getText();}
        else {
         return errorMsg.get(0).getText();}
	}	
}
