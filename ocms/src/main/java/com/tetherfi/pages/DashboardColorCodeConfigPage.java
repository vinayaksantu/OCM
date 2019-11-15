package com.tetherfi.pages;

import com.tetherfi.model.user.DashboardColorCodeConfigDetails;

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

public class DashboardColorCodeConfigPage extends BasePage {

    public DashboardColorCodeConfigPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement dashboardColorConfig;
    
    @FindBy(xpath="//i[@class='fa fa-paint-brush']")
    private WebElement dshCCImg;
    
    @FindBy(xpath="//th[3]/a/span[@class='k-icon k-i-more-vertical']")
    private WebElement headerColumn;
    
    @FindBy(xpath="//span[@class='k-icon k-i-arrow-60-right k-menu-expand-arrow']")
	private WebElement coloumnarrow;
    
   // @FindBy(xpath="//span[@class='k-picker-wrap k-state-default k-state-hover']")
   @FindBy(css=".k-widget .k-picker-wrap .k-selected-color")
    private List<WebElement> colordropdown;
    
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
   
    @FindBy(xpath="//a[text()='Start Range']")
    private WebElement startRange;
    
    @FindBy(xpath="//a[text()='Background Color']")
    private WebElement bgcolor;
    
    @FindBy(xpath="//a[text()='Font Color']")
    private WebElement fontcolor;
    
    @FindBy(xpath="//a[text()='Last Changed By']")
    private WebElement lastChangedBy;
    
    @FindBy(xpath="//a[text()='Last Changed On']")
    private WebElement lastChangedOn;
    
    @FindBy(xpath="//a[text()='End Range']")
    private WebElement endRange;
    
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

	@FindBy(xpath="//button[@id='create']")
    private WebElement addNewDashboardColorConfigRecordBtn;

    @FindBy(css=".k-edit-form-container")
    private WebElement popupContent;

   // @FindBy(xpath="//span[@class='k-dropdown-wrap k-state-default k-state-hover']")
    @FindBy(css = "span[aria-owns='DashboardName_listbox']")
    private WebElement dashboardName;
    
    @FindBy(css= "ul[id='DashboardName_listbox'] li")
    private List<WebElement> DashListbox;
    
   /*@FindBy(xpath="//ul[@id='DashboardName_listbox']")
   private List<WebElement> DashListbox;*/
    
    @FindBy(css = "span[aria-owns='ColumnName_listbox']")
    private WebElement ColumnName;
    
    @FindBy(css= "ul[id='ColumnName_listbox'] li")
    private List<WebElement> columnListbox;
    
    @FindBy(css=".form-group .k-formatted-value")
    private List<WebElement> rangetext;//get(1)for Startarnge get(3)end
    
    @FindBy(id="StartRange")
    private WebElement startRangeTxtbox;
    
    @FindBy(id="EndRange")
    private WebElement endRangeTxtbox;
    
    //@FindBy(id="StartTime")
    //private WebElement startTime;

    //@FindBy(id="EndTime")
    //private WebElement endTime;

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
    
    @FindBy(id="1001sTextToSearch")
    private WebElement textTosearch;

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
    

    public boolean isDashboardColorConfigPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return dashboardColorConfig.isEnabled();
    }
    
    public void addNewDashboardColorConfigRecord(DashboardColorCodeConfigDetails details) throws Exception {
        selectWebElement(addNewDashboardColorConfigRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        selectWebElement(dashboardName);
        selectDropdownFromVisibleText(DashListbox,details.getdashboardName());
        selectWebElement(ColumnName);
       selectDropdownFromVisibleText(columnListbox,details.getcolumnName());
        selectWebElement(rangetext.get(0));
        enterValueToTxtFieldWithoutClear(startRangeTxtbox, details.getStartRange());
        selectWebElement(rangetext.get(1));
        enterValueToTxtFieldWithoutClear(endRangeTxtbox, details.getEndRange());
        selectWebElement(colorPicker);
        selectWebElement(colorValue);
        enterValueToTxtField(colorValue,details.getColorcode());
        selectWebElement(applyBtn);
        selectWebElement(saveBtn);
    }

    public void searchDashboardColorConfigRecord(String StartTime) throws Exception  {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Start Range");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        
        //enterValueToTxtField(searchText.get(0),StartTime);//textTosearch range ==3
        enterValueToTxtField(textTosearch,StartTime);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public void editDashboardColorConfigRecord(DashboardColorCodeConfigDetails details) throws Exception {
        searchDashboardColorConfigRecord(details.getStartRange());
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        /*waitUntilWebElementIsVisible(popupContent);
        selectWebElement(dashboardName);
        selectDropdownFromVisibleText(DashListbox,details.getdashboardName());
        selectWebElement(ColumnName);
       selectDropdownFromVisibleText(columnListbox,details.getcolumnName());*/
        selectWebElement(rangetext.get(0));
        enterValueToTxtFieldWithoutClear(startRangeTxtbox, details.getUpdatedStartRange());
        selectWebElement(rangetext.get(1));
        enterValueToTxtFieldWithoutClear(endRangeTxtbox, details.getUpdatedEndRange());                   
        selectWebElement(colorPicker);
        selectWebElement(colorValue);
        enterValueToTxtField(colorValue,details.getUpdatedColorCode());
        selectWebElement(applyBtn);
        enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
        btnClick(saveBtn);
    }
    
    public void deleteDashboardColorConfigRecord(String Starttime, String reason) throws Exception {
        searchDashboardColorConfigRecord(Starttime);
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
		searchDashboardColorConfigRecord(Starttime);
        btnClick(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtField(deleteReasonTextBox,reason);
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
		if(isElementExist(dshCCImg))
		{return true;}
		else
		return false;
	}
	
	/*public boolean verifyStartRangeLabel() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
//		Boolean Status=startdurationcheckbox.isSelected();
		if(Status.equals(startRange.isDisplayed()))
		{return true;}
		else
			return false;
	}*/

	/*public boolean verifyEndRangeLabel() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		Boolean Status=enddurationcheckbox.isSelected();
		if(Status.equals(endRange.isDisplayed()))
		{return true;}
		else
			return false;
	}*/
	
	/*public boolean verifyColorCodeLabel() {
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
	}*/
	
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

public boolean VerifyCancelBtnAddNewRecord(DashboardColorCodeConfigDetails details) throws Exception {
	String actualitems=items.getText();
        selectWebElement(addNewDashboardColorConfigRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        selectWebElement(dashboardName);
        selectDropdownFromVisibleText(DashListbox,details.getdashboardName());
        selectWebElement(ColumnName);
       selectDropdownFromVisibleText(columnListbox,details.getcolumnName());
        selectWebElement(rangetext.get(0));
        enterValueToTxtFieldWithoutClear(startRangeTxtbox, details.getStartRange());
        selectWebElement(rangetext.get(1));
        enterValueToTxtFieldWithoutClear(endRangeTxtbox, details.getEndRange());
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
	
	
	public boolean editcancel(DashboardColorCodeConfigDetails details) throws Exception {
		searchDashboardColorConfigRecord(details.getStartRange());
        selectWebElement(editBtn);
        waitForJqueryLoad(driver); 
        selectWebElement(rangetext.get(0));     
        enterValueToTxtFieldWithoutClear(rangetext.get(0), details.getUpdatedStartRange());
        selectWebElement(rangetext.get(1));
        enterValueToTxtFieldWithoutClear(rangetext.get(1), details.getUpdatedEndRange());    
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
	
	public boolean clearAll(DashboardColorCodeConfigDetails details) throws Exception {
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Start Range");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(textTosearch,details.getStartRange());
        //enterValueToTxtField(searchText.get(0),details.getStartRange());
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
	public boolean verifyinvalidsearch(DashboardColorCodeConfigDetails details) throws Exception {
		searchDashboardColorConfigRecord(details.getStartRange());
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
		    if (f.getName().startsWith("Dashboard")) {
		        f.delete();
		    }
		}
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath, "Dashboard Color Code Config");
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
				if(headers.get(j).getText().equals("Last Changed On")){
					col=cols.get(j).getText().substring(11);
					}
				else if(headers.get(j).getText().equals("Start Range")) {
					String str[]=cols.get(j).getText().split(":");
					for(int x=0;x<str.length;x++)
					{
					col=col+str[x];
				}}
				else if(headers.get(j).getText().equals("End Range")) {
					String str1[]=cols.get(j).getText().split(":");
					col="";
					for(int y=0;y<str1.length;y++)
					{
					col=col+str1[y];
					}}
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
	
	public boolean verifyexportToExcelSheet(List<Map<String, String>> maplist) {
		List<Map<String,String>> UI=getdata(); 
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
		DragandDrop(bgcolor,droptarget);
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
	public void addRecordWithoutStartTime(DashboardColorCodeConfigDetails details) throws Exception {
		selectWebElement(addNewDashboardColorConfigRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        selectWebElement(dashboardName);
        selectDropdownFromVisibleText(DashListbox,details.getdashboardName());
        selectWebElement(ColumnName);
       selectDropdownFromVisibleText(columnListbox,details.getcolumnName());
        selectWebElement(rangetext.get(1));
        enterValueToTxtFieldWithoutClear(endRangeTxtbox, details.getEndRange());     
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
	public void addRecordWithoutEndTime(DashboardColorCodeConfigDetails details) throws Exception {
		selectWebElement(addNewDashboardColorConfigRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        selectWebElement(dashboardName);
        selectDropdownFromVisibleText(DashListbox,details.getdashboardName());
        selectWebElement(ColumnName);
       selectDropdownFromVisibleText(columnListbox,details.getcolumnName());
        selectWebElement(rangetext.get(0));
        enterValueToTxtFieldWithoutClear(startRangeTxtbox, details.getStartRange());               
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
    	return addNewDashboardColorConfigRecordBtn.isDisplayed() && addNewDashboardColorConfigRecordBtn.isEnabled();
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
		selectWebElement(startRange);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void SortByDescending() {
		selectWebElement(endRange);
		selectWebElement(endRange);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	public boolean ExporttoExcelWithoutData(DashboardColorCodeConfigDetails dashboardColorConfigDetails) throws Exception {
		searchDashboardColorConfigRecord(dashboardColorConfigDetails.getStartRange());	
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
