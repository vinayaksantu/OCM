package com.tetherfi.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.fax.FaxTemplateDetails;

import com.tetherfi.utility.FileUploader;

public class FaxTemplateWMCPage extends BasePage{

	public FaxTemplateWMCPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css=".ibox-title h5")
    private WebElement faxTemplate;
	
	@FindBy(xpath="//i[@class='fa fa-fax']")
	private WebElement FTImage;
	
	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;
	
	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullScreen;
	
	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement restore;
	
	@FindBy(id="navbarheader")
	private WebElement header;
	
	@FindBy(css="th a[class='k-header-column-menu']")
	private List<WebElement> headersDropdown;
	
	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;
	
	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;
	
	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;
	
	@FindBy(id="create")
    private WebElement addNewFaxTemplateRecordBtn;
	
	@FindBy(css=".k-grid-update")
	private WebElement saveBtn;
	
	@FindBy(css=".k-grid-cancel")
	private WebElement cancelBtn;
	
    @FindBy(xpath="//span[@class='k-widget k-dropdown' and @aria-owns='TemplateType_listbox' ]")
	private WebElement templateType;
	  
	@FindBy(css="ul[id='TemplateType_listbox'] li")
	private List<WebElement> templateTypeListBox;
	
	@FindBy(css=".k-upload-button")
    private WebElement selectFile;
	
	@FindBy(css="#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;
	
	@FindBy(css=".toast-message")
	private WebElement successmsg;
	
	@FindBy(id="TemplateName")
	private WebElement templateName;
	
	@FindBy(xpath="//iframe[@class='k-content']")
	private WebElement iframe;
	
	@FindBy(xpath="//body")
	private List<WebElement>body;
	
	@FindBy(css="#gridDiv2 .search-link")
	private WebElement searchLink;
	 
	@FindBy(css=".modal-body .form-inline .form-group .k-select")
	private List<WebElement> selectSearchColumn;
	 
	@FindBy(css="ul[id='1001sColumnName_listbox'] li")
	private List<WebElement> columnNameList;

	@FindBy(css="ul[id='1001sCriteria_listbox'] li")
	private List<WebElement> searchTypeList;

	@FindBy(css=".modal-body .form-inline .form-group .k-textbox")
	private List<WebElement> searchText;

	@FindBy(css=".modal-footer .k-primary")
	private WebElement searchBtn;
	
	@FindBy(css="#tGrid #drillGrid")
	private WebElement gridContent;
	
	@FindBy(id="ModifyReason")
	private WebElement modifyResonTextBox;
	
	@FindBy(css = ".k-grid-edit")
	private WebElement editButton;
	
	@FindBy(css="#drillGrid tbody tr td")
	private List<WebElement> editrowdata;
	
	@FindBy(css = ".k-grid-CustomDelete")
	private WebElement deleteButton;
	
	@FindBy(id="noButton")
	private WebElement nobtn;
	
	@FindBy(id="yesButton")
	private WebElement yesbtn;
	 
	@FindBy(css=".form-group #ModifyReason1")
	private  WebElement deleteReasonTextBox;
	
	@FindBy(css=".k-i-image")
	private WebElement selectImg;
	
	@FindBy(xpath="//span[text()='Insert image']")
	private WebElement ImgPopup;
	
	@FindBy(xpath="//img[@alt='Image.png']")
	private WebElement Img;
	
	@FindBy(xpath="//img[@alt='London.png']")
	private WebElement Img1;
	
	@FindBy(xpath="//button[text()='Insert']")
	private WebElement insertImg;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
	
	@FindBy(css=".k-pager-sizes .k-input")
    private WebElement pagerSize;
    
    @FindBy(id = "drillGrid")
    private WebElement grid;
    
    @FindBy(css="a[aria-label='Go to the next page']")
    private WebElement nextPageIcon;
    
    @FindBy(xpath="//a[text()='Template Name']")
	private WebElement TemplateName;
    
    @FindBy(css = "#drillGrid .k-grouping-header")
    private WebElement dragColumnDestination;
	
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
    
    @FindBy(xpath="//button[text()='Close']")
    private WebElement searchClose;
    
    @FindBy(xpath="//i[@class='fas fa-sync']")
    private WebElement clearsearch;
    
    @FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
    
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
    
    @FindBy(css="#tGrid")
    private WebElement auditGrid;
    
    @FindBy(xpath="//span[@aria-controls='1001sColumnName_listbox']")
    private WebElement SelectSearchColumn;
    
    @FindBy(xpath="//span[@aria-owns='1001sCriteria_listbox']")
    private WebElement condition;
    
    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchCriteriaDropDwn;
    
    @FindBy(id = "1001sTextToSearch")
    private WebElement searchTextBox;
    
    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;
	
	
	
	
	public boolean isFaxTemplatePageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return faxTemplate.isEnabled();
    }
	
	public boolean verifyLogo() {
		if(isElementExist(FTImage))
		 {return true;}
		 else
		 {return false;}
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
	
public boolean addCancelButton(FaxTemplateDetails details) {
		String actualitems=items.getText();
		selectWebElement(addNewFaxTemplateRecordBtn);
		waitForJqueryLoad(driver);
        selectWebElement(cancelBtn);
        if(actualitems.equals(items.getText()))
        	return true;
        else
		return false;
	}

public boolean getErrorMsg() {
	waitUntilWebElementListIsVisible(errorMsg);
	if(errorMsg.size()>0)
	return false;
	else
		return true;
}

public String VerifyMessage() {
	waitForJqueryLoad(driver);
	if(successmsg.isDisplayed())
		return successmsg.getText();
	else{return errorMsg.get(0).getText();}
}

public void addWithoutTemplateName(FaxTemplateDetails details) throws Exception {
	selectWebElement(addNewFaxTemplateRecordBtn);
	Thread.sleep(1000);
	selectWebElement(templateType);
	selectDropdownFromVisibleText(templateTypeListBox,"Auto ACK");
	clickOnUsingActionClass(selectFile);
    //Auto It script to load wave file
    FileUploader upload= new FileUploader();
    upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getContent());
	selectWebElement(saveBtn);		
    selectWebElement(cancelBtn);
}

public void addWithoutTemplateType(FaxTemplateDetails details) throws Exception {
	selectWebElement(addNewFaxTemplateRecordBtn);
	Thread.sleep(1000);
	enterValueToTxtField(templateName,details.getTemplateName());
	driver.switchTo().frame(iframe);
	selectWebElement(body.get(0));
	enterValueToTxtFieldWithoutClear(body.get(0),details.getContent());
	driver.switchTo().defaultContent();
	selectWebElement(saveBtn);		
    selectWebElement(cancelBtn);
}

public void addWithoutContent(FaxTemplateDetails details) throws Exception {
	selectWebElement(addNewFaxTemplateRecordBtn);
	Thread.sleep(1000);
	enterValueToTxtField(templateName,details.getTemplateName());
	selectWebElement(templateType);
	selectDropdownFromVisibleText(templateTypeListBox,"Auto ACK");
	selectWebElement(saveBtn);		
    selectWebElement(cancelBtn);
}

public void addNewAutoAcKTemplateTypeRecord(FaxTemplateDetails details) throws Exception {
	selectWebElement(addNewFaxTemplateRecordBtn);
	Thread.sleep(1000);
	enterValueToTxtField(templateName,details.getTemplateName());
	selectWebElement(templateType);
	selectDropdownFromVisibleText(templateTypeListBox,"Auto ACK");
	clickOnUsingActionClass(selectFile);
    //Auto It script to load wave file
    FileUploader upload= new FileUploader();
    upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getContent());
	selectWebElement(saveBtn);		
    selectWebElement(cancelBtn);
}

private void searchFaxTemplateRecord(String templatename) throws Exception {
	selectWebElement(searchLink);
    selectWebElement(selectSearchColumn.get(0));
    selectDropdownFromVisibleText(columnNameList,"Template Name");
    Thread.sleep(1000);
    selectWebElement(selectSearchColumn.get(1));
    selectDropdownFromVisibleText(searchTypeList,"Is equal to");
    enterValueToTxtField(searchText.get(0),templatename);
    selectWebElement(searchBtn);
    waitForJqueryLoad(driver);
    waitUntilWebElementIsVisible(gridContent);			
}

public boolean EditCancel(FaxTemplateDetails details) throws Exception {
	searchFaxTemplateRecord(details.getTemplateName());
	selectWebElement(editButton);
	waitForJqueryLoad(driver);
	selectWebElement(cancelBtn);
	if(editrowdata.get(1).getText().equals(details.getTemplateName()))
		return true;
	else
	    return false;
}

public void EditAutoAckTemplateTypeRecordWithOutModifyReason(FaxTemplateDetails details) throws Exception {
	searchFaxTemplateRecord(details.getTemplateName());
	selectWebElement(editButton);
	waitForJqueryLoad(driver);
	clickOnUsingActionClass(selectFile);
    //Auto It script to load wave file
    FileUploader upload= new FileUploader();
    upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getUpdatedContent());
	selectWebElement(saveBtn);
}

public void EditAutoAckTemplateTypeRecord(FaxTemplateDetails details) throws Exception {
	searchFaxTemplateRecord(details.getTemplateName());
	selectWebElement(editButton);
	waitForJqueryLoad(driver);
	clickOnUsingActionClass(selectFile);
    //Auto It script to load wave file
    FileUploader upload= new FileUploader();
    upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getUpdatedContent());
	enterValueToTxtFieldWithoutClear(modifyResonTextBox,details.getModifyReason());
	selectWebElement(saveBtn);
}

public boolean DeleteCancel(FaxTemplateDetails details) throws Exception {
	searchFaxTemplateRecord(details.getTemplateName());
	selectWebElement(deleteButton);
	waitForJqueryLoad(driver);
	selectWebElement(nobtn);
	if(editrowdata.get(1).getText().equals(details.getTemplateName()))
		return true;
	else
	return false;
}

public void DeleteFaxTemplateRecordWithoutDeleteReason(FaxTemplateDetails details) throws Exception {
	searchFaxTemplateRecord(details.getTemplateName());
	selectWebElement(deleteButton);
    selectWebElement(deleteReasonTextBox);
    selectWebElement(yesbtn);		
    selectWebElement(nobtn);
}

public void DeleteAutoAckTemplateTypeRecord(FaxTemplateDetails details) throws Exception {
	searchFaxTemplateRecord(details.getTemplateName());
	selectWebElement(deleteButton);
    selectWebElement(deleteReasonTextBox);
    enterValueToTxtFieldWithoutClear(deleteReasonTextBox,details.getDeleteReason());
    selectWebElement(yesbtn);				
}

public void addNewCoverPageTemplateTypeRecord(FaxTemplateDetails details) throws Exception {
	selectWebElement(addNewFaxTemplateRecordBtn);
	Thread.sleep(1000);
	enterValueToTxtField(templateName,details.getTemplateName());
	selectWebElement(templateType);
	selectDropdownFromVisibleText(templateTypeListBox,"Cover Page");
	driver.switchTo().frame(iframe);
	selectWebElement(body.get(0));
	enterValueToTxtFieldWithoutClear(body.get(0),details.getContent());
	driver.switchTo().defaultContent();
	selectWebElement(selectImg);
	waitUntilWebElementIsVisible(ImgPopup);
	selectWebElement(Img);
	selectWebElement(insertImg);
	waitForJqueryLoad(driver);
	selectWebElement(saveBtn);
}

public void EditCoverPageTemplateTypeRecordWithOutModifyReason(FaxTemplateDetails details) throws Exception {
	searchFaxTemplateRecord(details.getTemplateName());
	selectWebElement(editButton);
	waitForJqueryLoad(driver);
	driver.switchTo().frame(iframe);
	selectWebElement(body.get(0));
	enterValueToTxtField(body.get(0),details.getUpdatedContent());
	driver.switchTo().defaultContent();
	selectWebElement(saveBtn);
}

public void EditCoverPageTemplateTypeRecord(FaxTemplateDetails details) throws Exception {
	searchFaxTemplateRecord(details.getTemplateName());
	selectWebElement(editButton);
	waitForJqueryLoad(driver);
	driver.switchTo().frame(iframe);
	selectWebElement(body.get(0));
	enterValueToTxtField(body.get(0),details.getUpdatedContent());
	driver.switchTo().defaultContent();
	enterValueToTxtFieldWithoutClear(modifyResonTextBox,details.getModifyReason());
	selectWebElement(saveBtn);
}
	
public void DeleteCoverPageTemplateTypeRecord(FaxTemplateDetails details) throws Exception {
	searchFaxTemplateRecord(details.getTemplateName());
	selectWebElement(deleteButton);
    selectWebElement(deleteReasonTextBox);
    enterValueToTxtFieldWithoutClear(deleteReasonTextBox,details.getDeleteReason());
    selectWebElement(yesbtn);				
}

public void addNewCustomTemplateTypeRecord(FaxTemplateDetails details) throws Exception {
	selectWebElement(addNewFaxTemplateRecordBtn);
	Thread.sleep(1000);
	enterValueToTxtField(templateName,details.getTemplateName());
	selectWebElement(templateType);
	selectDropdownFromVisibleText(templateTypeListBox,"Custom Template");
	driver.switchTo().frame(iframe);
	selectWebElement(body.get(0));
	enterValueToTxtFieldWithoutClear(body.get(0),details.getContent());
	driver.switchTo().defaultContent();
	selectWebElement(selectImg);
	waitUntilWebElementIsVisible(ImgPopup);
	selectWebElement(Img1);
	selectWebElement(insertImg);
	waitForJqueryLoad(driver);
	selectWebElement(saveBtn);
}

public void EditCustomTemplateTypeRecordWithOutModifyReason(FaxTemplateDetails details) throws Exception {
	searchFaxTemplateRecord(details.getTemplateName());
	selectWebElement(editButton);
	waitForJqueryLoad(driver);
	driver.switchTo().frame(iframe);
	selectWebElement(body.get(0));
	enterValueToTxtField(body.get(0),details.getUpdatedContent());
	driver.switchTo().defaultContent();
	selectWebElement(saveBtn);
}

public void EditCustomTemplateTypeRecord(FaxTemplateDetails details) throws Exception {
	searchFaxTemplateRecord(details.getTemplateName());
	selectWebElement(editButton);
	waitForJqueryLoad(driver);
	driver.switchTo().frame(iframe);
	selectWebElement(body.get(0));
	enterValueToTxtField(body.get(0),details.getUpdatedContent());
	driver.switchTo().defaultContent();
	enterValueToTxtFieldWithoutClear(modifyResonTextBox,details.getModifyReason());
	selectWebElement(saveBtn);
}

public void DeleteCustomTemplateTypeRecord(FaxTemplateDetails details) throws Exception {
	searchFaxTemplateRecord(details.getTemplateName());
	selectWebElement(deleteButton);
    selectWebElement(deleteReasonTextBox);
    enterValueToTxtFieldWithoutClear(deleteReasonTextBox,details.getDeleteReason());
    selectWebElement(yesbtn);				
}

public boolean verifyExportToExcel(String filePath) {
	final File folder = new File(filePath);
	for (final File f : folder.listFiles()) {
	    if (f.getName().startsWith("Fax Template")) {
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
	Boolean Status=verifyExportPageFileDownload(filePath, "Fax Template");
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
	waitUntilWebElementIsVisible(grid);
	List<WebElement> rows=grid.findElements(By.tagName("tr"));
	List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
	String col=null;
	for(int i=1;i<rows.size();i++) {
		Map<String,String> map = new HashMap<String,String>();
		List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
		for(int j=1;j<headers.size();j++) {
			/*if(headers.get(j).getText().equals("Last Changed On")){
				col=cols.get(j).getText().substring(0,10);
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

public boolean ExporttoExcelWithoutData(FaxTemplateDetails details) throws Exception {
	searchFaxTemplateRecord(details.getTemplateName());
	waitForJqueryLoad(driver);
	Thread.sleep(1000);
	selectWebElement(exporttoexcel);
	waitUntilWebElementListIsVisible(errorMsg);
	if(errorMsg.get(0).getText().equals("There is no record to export"))
		return true;
	else
	return false;
}

public void SortByAscending()  {
	selectWebElement(TemplateName);
	selectWebElement(exporttoexcel);
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}

public void SortByDescending(){
	selectWebElement(TemplateName);
	selectWebElement(TemplateName);
	selectWebElement(exporttoexcel);
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}
	
public void dragColumntoGroup(String columnname) {
    List<WebElement> rows = grid.findElements(By.tagName("tr"));
    List<WebElement> columnHeaders = rows.get(0).findElements(By.tagName("th"));
    for (WebElement ele : columnHeaders) {
        if (ele.getText().equals(columnname)) {
            Actions builder = new Actions(driver);
            Action dragAndDrop = builder.clickAndHold(ele).moveToElement(dragColumnDestination).release(dragColumnDestination).build();
            dragAndDrop.perform();
        }
    }
}

public boolean verifyDragColumntoGroup(String colname) {
    return (dragColumnDestination.getText().equals(colname));
}
	
public boolean clearAll(FaxTemplateDetails details) throws Exception {
	selectWebElement(searchLink);
    selectWebElement(selectSearchColumn.get(0));
    selectDropdownFromVisibleText(columnNameList,"Template Name");
    selectWebElement(selectSearchColumn.get(1));
    selectDropdownFromVisibleText(searchTypeList,"Is equal to");
    enterValueToTxtField(searchText.get(0),details.getTemplateName());
    selectWebElement(clearall);
		if(searchText.get(0).isEnabled())
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

public void searchwithoutextsearch(FaxTemplateDetails details) {
	selectWebElement(searchLink);
    selectWebElement(selectSearchColumn.get(0));
    selectDropdownFromVisibleText(columnNameList,"Template Name");
    selectWebElement(selectSearchColumn.get(1));
    selectDropdownFromVisibleText(searchTypeList,"Is equal to");
    selectWebElement(searchBtn);	
    selectWebElement(searchClose);
}

public boolean verifyclearsearch() {
	selectWebElement(clearsearch);
	if(gridContent.isDisplayed())
		return true;
	else
	return false;
}

public boolean verifyinvalidsearchwithwrongdata(FaxTemplateDetails details) throws Exception {
	searchFaxTemplateRecord(details.getTemplateName());
	if(norecords.isDisplayed())
		return true; 
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
                int totalRows=(gridContent.findElements(By.tagName("tr")).size()-1);
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
	
public List<Map<String, String>> gettable() {
	int item=Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
    int pagersize=Integer.valueOf(pagerSize.getText());
    int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
	List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
	for(int k=0;k<=pages;k++){

	waitUntilWebElementIsVisible(auditGrid);
	List<WebElement> rows=auditGrid.findElements(By.tagName("tr"));
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

public boolean verifySearchIsnotEqualTo(String templatename) throws Exception {
	 boolean Status=false;
	 Map<String,String>map=new HashMap<String,String>();
	 map.put("Template Name", templatename);
	 selectWebElement(searchLink);
	 selectWebElement(SelectSearchColumn);
	 selectDropdownFromVisibleText(columnNameList,"Template Name");
	 Thread.sleep(1000);
	 selectWebElement(condition);
	 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
	 enterValueToTxtField(searchTextBox,templatename);		
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

public boolean verifySearchContains(String templatename) throws Exception {
	 boolean Status=false;
	 Map<String,String>map=new HashMap<String,String>();
	 map.put("Template Name", templatename);
	 selectWebElement(searchLink);
	 selectWebElement(SelectSearchColumn);
	 selectDropdownFromVisibleText(columnNameList,"Template Name");
	 Thread.sleep(1000);
	 selectWebElement(condition);
	 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
	 enterValueToTxtField(searchTextBox,templatename);		
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

public boolean verifySearchDoesNotContains(String templatename) throws Exception {
	 boolean Status=false;
	 Map<String,String>map=new HashMap<String,String>();
	 map.put("Template Name", templatename);
	 selectWebElement(searchLink);
	 selectWebElement(SelectSearchColumn);
	 selectDropdownFromVisibleText(columnNameList,"Template Name");
	 Thread.sleep(1000);
	 selectWebElement(condition);
	 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not Contain");
	 enterValueToTxtField(searchTextBox,templatename);		
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

public boolean verifySearchStartsWith(String templatename) throws Exception {
	 boolean Status=false;
	 Map<String,String>map=new HashMap<String,String>();
	 map.put("Template Name", templatename);
	 selectWebElement(searchLink);
	 selectWebElement(SelectSearchColumn);
	 selectDropdownFromVisibleText(columnNameList,"Template Name");
	 Thread.sleep(1000);
	 selectWebElement(condition);
	 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
	 enterValueToTxtField(searchTextBox,templatename);		
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

public boolean verifySearchEndsWith(String templatename) throws Exception {
	 boolean Status=false;
	 Map<String,String>map=new HashMap<String,String>();
	 map.put("Template Name", templatename);
	 selectWebElement(searchLink);
	 selectWebElement(SelectSearchColumn);
	 selectDropdownFromVisibleText(columnNameList,"Template Name");
	 Thread.sleep(1000);
	 selectWebElement(condition);
	 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
	 enterValueToTxtField(searchTextBox,templatename);		
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

public List<Map<String, String>> gettable1() {
	int item=Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
    int pagersize=Integer.valueOf(pagerSize.getText());
    int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
	List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
	for(int k=0;k<=pages;k++){

	waitUntilWebElementIsVisible(auditGrid);
	List<WebElement> rows=auditGrid.findElements(By.tagName("tr"));
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
		//map.remove("Preview");
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
