package com.tetherfi.pages;

import com.tetherfi.model.tmac.WaitTimeColorConfigDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
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

    @FindBy(css="#toast-container .toast-error")
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
    
    @FindBy(xpath="//p[@class='k-reset']")
    private WebElement groupby;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[5]/div")
	private WebElement groupbycolor;
    
    @FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;
    
    

    public boolean isWaitTimeColorConfigPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return waitTimeColorConfig.isEnabled();
    }
    public void addNewWaitTimeColorConfigRecord(WaitTimeColorConfigDetails details) {
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
        selectWebElement(saveBtn);
    }
    public void searchWaitTimeColorConfigRecord(String StartTime)  {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Start Duration");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),StartTime);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public void editWaitTimeColorConfigRecord(WaitTimeColorConfigDetails details) {
        searchWaitTimeColorConfigRecord(details.getStartTime());
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        selectWebElement(startTime);
        enterValueToTxtField(startTime, details.getStartTime());
        selectWebElement(endTime);
        enterValueToTxtField(endTime, details.getEndTime());
        selectWebElement(colorPicker);
        selectWebElement(colorValue);
        enterValueToTxtField(colorValue,details.getUpdatedColorCode());
        selectWebElement(applyBtn);
        enterValueToTxtField(modifyReasonTextBox,details.getModifyReason());
        btnClick(saveBtn);
    }
    public void deleteWaitTimeColorConfigRecord(String colorcode, String reason) {
        searchWaitTimeColorConfigRecord(colorcode);
        btnClick(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtField(deleteReasonTextBox,reason);
        selectWebElement(yesBtn);
    }

    public String getMessage(){
        //waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return errorMsg.get(0).getText();}
        else {return successmsg.getText();}
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
	public boolean verifyColordisable() {
		// TODO Auto-generated method stub
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
	public boolean addNewCancel(WaitTimeColorConfigDetails details) {
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
	
	public boolean editcancel(WaitTimeColorConfigDetails details) {
		searchWaitTimeColorConfigRecord(details.getStartTime());
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        selectWebElement(startTime);
        enterValueToTxtField(startTime, details.getStartTime());
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
	public boolean verifyinvalidsearch(WaitTimeColorConfigDetails details) {
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
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		Boolean Status=verifyExportPageFileDownload(filePath, "Wait Time Color Config");
		return Status;
	}
	
	private List<Map<String,String>> getdata(){
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
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
			return arr;
	}
	
	private List<Map<String,String>> gettable(){
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		waitUntilWebElementIsVisible(auditGridContent);
		List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
		List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
		for(int i=1;i<rows.size();i++) {
			Map<String,String> map = new HashMap<String,String>();
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			String col=null;
			for(int j=1;j<headers.size();j++){
				if(headers.get(j).getText().equals("Last Changed On")){
					col=cols.get(j).getText().substring(10);
					}
				else
					col=cols.get(j).getText();
				map.put(headers.get(j).getText(),col);
			}
			map.remove("");
			arr.add(map);
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
		List<Map<String,String>> UI=gettable(); 
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
}
