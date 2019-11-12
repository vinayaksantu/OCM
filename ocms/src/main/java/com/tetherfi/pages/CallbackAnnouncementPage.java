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
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.ivr.AdhocOptionEnhancementDetails;
import com.tetherfi.model.ivr.CallbackAnnouncementDetails;
import com.tetherfi.utility.FileUploader;

public class CallbackAnnouncementPage extends BasePage{

	public CallbackAnnouncementPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".ibox-title h5")
    private WebElement callBackAnnouncement;
	
	@FindBy(css="#create")
	private WebElement addNewCallBackAnnouncementRecordBtn;
	
	@FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;
	
	@FindBy(css = ".k-grid-edit")
    private WebElement editButton;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
	
	@FindBy(css="#toast-container .toast-message")
	private WebElement successmsg;
	
	@FindBy(xpath="//i[@class='fas fa-headset']")
	private WebElement CBAImg;
	
	@FindBy(css = "#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;
	
	@FindBy(id = "ModifyReason")
    private WebElement ModifyReasonTextBox;
    
    @FindBy(css=".k-grid-cancel")
    private WebElement cancelBtn;
    
    @FindBy(css=".k-edit-form-container")
    private WebElement editFormContainer;
    
    @FindBy(css="div[style='display: block; z-index: 10002; opacity: 0.5;']")
    private WebElement deleteContainer;
    
    @FindBy(css=".k-grid-content")
    private WebElement gridContent;
	    
    @FindBy(xpath="//i[@class='fas fa-expand']")
    private WebElement maximize;
		
	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;
		 
	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
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
    
    @FindBy(id = "1001sMaskedTextToSearch")
    private WebElement searchTextBox;

    @FindBy(id = "1001sTextToSearch")
    private WebElement searchTextBox1;

    @FindBy(id="1001sAddButton")
    private WebElement searchAddCriteriaBtn;
    
    @FindBy(xpath="//label[@for='1001sRadioAND']")
    private WebElement andradiobtn;
    
    @FindBy(xpath="//label[@for='1001sRadioOR']")
    private WebElement ORradiobtn;
    
    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;
    
    @FindBy(css=".k-upload-button")
    private List<WebElement> selectFiles;
    
    @FindBy(id="StartTime")
    private WebElement startTimeTextbox;
    
    @FindBy(id="EndTime")
    private WebElement endTimeTextbox;
    
    @FindBy(css=".k-grid-update")
    private WebElement saveButton;
    
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
	
	@FindBy(id = "ModifyReason1")
    private WebElement deleteReasonTextBox;

    @FindBy(id = "yesButton")
    private WebElement deleteYesBtn;

    @FindBy(id = "noButton")
    private WebElement deleteNoBtn;
	    
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
	    
	@FindBy(xpath="//tbody/tr/td[2]")
	private WebElement rowdata;
	    
	@FindBy(xpath="//span[@class='k-icon k-i-arrow-60-right k-menu-expand-arrow']")
	private WebElement coloumnarrow;
	    
	@FindBy(id="tGrid")
	private WebElement auditGridContent;
	    
	@FindBy(xpath="//a[text()='Wav File']")
	private WebElement WavFile;
	    
	@FindBy(xpath="//a[text()='Language']")
	private WebElement Language;
	    
	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;
	    
	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[6]")
	private WebElement groupbyLanguage;
	    
	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearall;
	    
	@FindBy(xpath="//button[text()='Close']")
	private WebElement searchClose;
	    
	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;
	    
	@FindBy(xpath="//i[@class='fas fa-sync']")
	private WebElement clearsearch;
	
	@FindBy(css="span[aria-owns='Language_listbox']")
    private WebElement languageDropdown;

    @FindBy(css="ul[id='Language_listbox'] li")
    private List<WebElement> languageListbox;
	    
	
	public boolean isCallbackAnnouncementPageDisplayed() {
		waitForLoad(driver);
        waitForJqueryLoad(driver);
		return callBackAnnouncement.isEnabled();
	}
	public boolean isAddBtnDisplayed() {
    	return addNewCallBackAnnouncementRecordBtn.isDisplayed() && addNewCallBackAnnouncementRecordBtn.isEnabled();
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
		if(isElementExist(CBAImg))
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
		    if (f.getName().startsWith("Callback")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "Callback Announcement");
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
	public boolean ExporttoExcelWithoutData(CallbackAnnouncementDetails details) throws Exception {
		searchCallbackAnnouncementRecord(details.getStartTime());
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(exporttoexcel);
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
		return false;
	}

	private void searchCallbackAnnouncementRecord(String startTime) throws Exception {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Start Time");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtBox1(searchTextBox,startTime);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);		
	}
	
	public void SortByAscending() {
		selectWebElement(WavFile);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(WavFile);
		selectWebElement(WavFile);
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
		DragandDrop(Language,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(groupbyLanguage.getText()))
		{return true;}
		else
			return false;		
	}
    
    public String verifySuccessMessage(){
        waitUntilWebElementIsVisible(successmsg);return successmsg.getText();
    }
    
	public boolean addnewCancel(CallbackAnnouncementDetails details) throws Exception {
		String actualitems=items.getText();
    	selectWebElement(addNewCallBackAnnouncementRecordBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(selectFiles.get(0));
        //Auto It script to load wave file
        FileUploader upload = new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getWavFile());
        selectWebElement(startTimeTextbox);
        enterValueToTxtField(startTimeTextbox,details.getStartTime());
        selectWebElement(endTimeTextbox);
        enterValueToTxtField(endTimeTextbox,details.getEndTime());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox,details.getLanguage());
        selectWebElement(cancelBtn);
        if(actualitems.equals(items.getText()))
			return true;
		else
		return false;
	}
	public void addNewCallbackAnnouncementRecord(CallbackAnnouncementDetails details) throws Exception {
		selectWebElement(addNewCallBackAnnouncementRecordBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(selectFiles.get(0));
        //Auto It script to load wave file
        FileUploader upload = new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getWavFile());
        selectWebElement(startTimeTextbox);
        enterValueToTxtField(startTimeTextbox,details.getStartTime());
        selectWebElement(endTimeTextbox);
        enterValueToTxtField(endTimeTextbox,details.getEndTime());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox,details.getLanguage());
        selectWebElement(saveButton);		
	}
	
	public Boolean verifyErrorMessage() {
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.size()>0){
			return true;}
			else 
				return false;
	}
	public void addNewCallbackAnnouncementWithoutWavFileRecord(CallbackAnnouncementDetails details) throws Exception {
		selectWebElement(addNewCallBackAnnouncementRecordBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(startTimeTextbox);
        enterValueToTxtField(startTimeTextbox,details.getStartTime());
        selectWebElement(endTimeTextbox);
        enterValueToTxtField(endTimeTextbox,details.getEndTime());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox,details.getLanguage());
        selectWebElement(saveButton);	
        selectWebElement(cancelBtn);
		
	}
	public void addNewCallbackAnnouncementWithoutStartTimeRecord(CallbackAnnouncementDetails details) throws Exception {
		selectWebElement(addNewCallBackAnnouncementRecordBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(selectFiles.get(0));
        //Auto It script to load wave file
        FileUploader upload = new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getWavFile());
        selectWebElement(endTimeTextbox);
        enterValueToTxtField(endTimeTextbox,details.getEndTime());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox,details.getLanguage());
        selectWebElement(saveButton);
        selectWebElement(cancelBtn);
	}
	public void addNewCallbackAnnouncementWithoutEndTimeRecord(CallbackAnnouncementDetails details) throws Exception {
		selectWebElement(addNewCallBackAnnouncementRecordBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(selectFiles.get(0));
        //Auto It script to load wave file
        FileUploader upload = new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getWavFile());
        selectWebElement(startTimeTextbox);
        enterValueToTxtField(startTimeTextbox,details.getStartTime());
       selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox,details.getLanguage());
        selectWebElement(saveButton);
        selectWebElement(cancelBtn);
	}
	public void addNewCallbackAnnouncementWithoutLanguageRecord(CallbackAnnouncementDetails details) throws Exception {
		selectWebElement(addNewCallBackAnnouncementRecordBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(selectFiles.get(0));
        //Auto It script to load wave file
        FileUploader upload = new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getWavFile());
        selectWebElement(startTimeTextbox);
        enterValueToTxtField(startTimeTextbox,details.getStartTime());
        selectWebElement(endTimeTextbox);
        enterValueToTxtField(endTimeTextbox,details.getEndTime());
       selectWebElement(saveButton);	
        selectWebElement(cancelBtn);
	}
	
	public boolean verifySearchIsNotEqualTo(String language) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("Language", language);
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Language");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
        enterValueToTxtField(searchTextBox1,language);		
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
	public boolean verifySearchContains(String language) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Language");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
        enterValueToTxtField(searchTextBox1,language);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("Language").toUpperCase().contains(language.toUpperCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	public boolean verifySearchDoesNotContains(String language) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Language");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not contain");
        enterValueToTxtField(searchTextBox1,language);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(!map1.get("Language").toLowerCase().contains(language.toLowerCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	public boolean verifySearchStartsWith(String language) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Language");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
        enterValueToTxtField(searchTextBox1,language);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("Language").toLowerCase().startsWith(language.toLowerCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	public boolean verifySearchEndsWith(String language) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Language");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
        enterValueToTxtField(searchTextBox1,language);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("Language").toUpperCase().endsWith(language.toUpperCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	public void editCallbackAnnouncementRecord(CallbackAnnouncementDetails details) throws Exception {
		 searchCallbackAnnouncementRecord(details.getStartTime());
	        selectWebElement(editButton);
	        Thread.sleep(2000);
	        selectWebElement(startTimeTextbox);
	        enterValueToTxtField(startTimeTextbox,details.getUpdatedStartTime());
	        selectWebElement(endTimeTextbox);
	        enterValueToTxtField(endTimeTextbox,details.getUpdatedEndTime());
	        selectWebElement(ModifyReasonTextBox);
	        enterValueToTxtFieldWithoutClear(ModifyReasonTextBox,details.getModifyReason());
	        selectWebElement(saveButton);
	}
	public void editwithoutModifyreasonRecord(CallbackAnnouncementDetails details) throws Exception {
		searchCallbackAnnouncementRecord(details.getUpdatedStartTime());
        selectWebElement(editButton);
        Thread.sleep(2000);
        selectWebElement(startTimeTextbox);
        enterValueToTxtField(startTimeTextbox,details.getUpdatedStartTime());
        selectWebElement(endTimeTextbox);
        enterValueToTxtField(endTimeTextbox,details.getUpdatedEndTime());
        selectWebElement(saveButton);		
        selectWebElement(cancelBtn);
	}
	public Boolean editcancel(CallbackAnnouncementDetails details) throws Exception {
		 searchCallbackAnnouncementRecord(details.getUpdatedStartTime());
	        selectWebElement(editButton);
	        Thread.sleep(2000);
	        selectWebElement(startTimeTextbox);
	        enterValueToTxtField(startTimeTextbox,details.getUpdatedStartTime());
	        selectWebElement(endTimeTextbox);
	        enterValueToTxtField(endTimeTextbox,details.getUpdatedEndTime());
	        selectWebElement(ModifyReasonTextBox);
	        enterValueToTxtField(ModifyReasonTextBox,details.getModifyReason());
	        selectWebElement(cancelBtn);
	        if(rowdata.getText().equals(details.getWavFile()))
	        	return true;
	        else 
	        	return false;	
	}
	public boolean clearAll(CallbackAnnouncementDetails details) throws Exception {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Start Time");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,details.getStartTime());
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
	public boolean verifyinvalidsearchwithwrongdata(CallbackAnnouncementDetails details) throws Exception {
		searchCallbackAnnouncementRecord(details.getStartTime());
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
	public void searchwithoutextsearch(CallbackAnnouncementDetails callbackAnnouncementDetails) {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Start Time");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        selectWebElement(searchSearchBtn);		
	}
	public void deleteWithoutModifyReasonRecord(CallbackAnnouncementDetails details) throws Exception {
		searchCallbackAnnouncementRecord(details.getStartTime());
        selectWebElement(deleteButton);
        selectWebElement(deleteYesBtn);		
        selectWebElement(deleteNoBtn);

	}
	public boolean Canceldelete(CallbackAnnouncementDetails details) throws Exception {
		searchCallbackAnnouncementRecord(details.getStartTime());
        selectWebElement(deleteButton);
        Thread.sleep(2000);
        enterValueToTxtField(deleteReasonTextBox,details.getDeleteReason());
        selectWebElement(deleteNoBtn);
        if(rowdata.getText().equals(details.getWavFile()))
        		return true;
        else
        	return false;
	}
	public void deleteCallbackAnnouncementRecord(CallbackAnnouncementDetails details) throws Exception {
		searchCallbackAnnouncementRecord(details.getStartTime());
        selectWebElement(deleteButton);
        Thread.sleep(2000);
        enterValueToTxtFieldWithoutClear(deleteReasonTextBox,details.getDeleteReason());
        selectWebElement(deleteYesBtn);
        try {
        	selectWebElement(deleteNoBtn);
        }
        catch(Exception e){
        	e.printStackTrace();
        }		
	}
}
