package com.tetherfi.pages;

import com.tetherfi.model.ivr.AdhocOptionEnhancementDetails;
import com.tetherfi.utility.FileUploader;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdhocOptionEnhancementPage extends BasePage {

    public AdhocOptionEnhancementPage(WebDriver driver) {
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

    @FindBy(id="1001sAddButton")
    private WebElement searchAddCriteriaBtn;
    
    @FindBy(xpath="//label[@for='1001sRadioAND']")
    private WebElement andradiobtn;
    
    @FindBy(xpath="//label[@for='1001sRadioOR']")
    private WebElement ORradiobtn;
    
    @FindBy(xpath="//tbody/tr/td[2]")
    private WebElement rowdata;
    
    @FindBy(xpath="//tbody/tr/td[4]")
    private WebElement rowdatatwo;

    @FindBy(css="#toast-container .toast-message")
    private WebElement successmsg;

    @FindBy(css=".ibox-title h5")
    private WebElement AdhocOptionEnhancement;

    @FindBy(css="#create")
    private WebElement addNewAdhocOptionEnhancementRcrdBtn;

    @FindBy(css=".k-grid-content")
    private WebElement gridContent;

    @FindBy(css=".k-edit-form-container .k-numeric-wrap")
    private WebElement numericTextbox;

    @FindBy(id="PromotionNumber")
    private WebElement promotionalNumber;

    @FindBy(id="PromotionDescription")
    private WebElement promotionalDescription;

    @FindBy(css="span[aria-owns='Language_listbox']")
    private WebElement languageDropdown;

    @FindBy(css="ul[id='Language_listbox'] li")
    private List<WebElement> languageListbox;

    @FindBy(css="span[aria-owns='DirectTransferEnabled_listbox']")
    private WebElement directTransferEnabledDropdown;

    @FindBy(css="ul[id='DirectTransferEnabled_listbox'] li")
    private List<WebElement> directTransferEnabledListbox;

    @FindBy(css=".k-upload-button")
    private List<WebElement> selectfiles;

    @FindBy(id="Intent")
    private WebElement intent;

    @FindBy(css="span[aria-owns='Status_listbox']")
    private WebElement statusDropdown;

    @FindBy(css="ul[id='Status_listbox'] li")
    private List<WebElement> statusListbox;

    @FindBy(css=".k-grid-update")
    private WebElement saveButton;

    @FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;

    @FindBy(id = "ModifyReason1")
    private WebElement deleteReasonTextBox;

    @FindBy(id = "yesButton")
    private WebElement deleteYesBtn;

    @FindBy(id = "noButton")
    private WebElement deleteNoBtn;

    @FindBy(css = "#toast-container .toast-error .toast-message")
    private List<WebElement> errorMsg;

    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;
    
    @FindBy(css = ".k-grid-edit")
    private WebElement editButton;
    
    @FindBy(id = "ModifyReason")
    private WebElement ModifyReasonTextBox;
    
    @FindBy(css=".k-grid-cancel")
    private WebElement cancelBtn;
    
    @FindBy(css=".k-edit-form-container")
    private WebElement editFormContainer;
    
    @FindBy(css="div[style='display: block; z-index: 10002; opacity: 0.5;']")
    private WebElement deleteContainer;

    @FindBy(xpath = "//button[text()='Export to Excel']")
    private WebElement exportBtn;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
    
    @FindBy(xpath="//i[@class='fas fa-th-list']")
    private WebElement AOEImg;
    
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
    
    @FindBy(xpath="//a[text()='Promotion Number']")
    private WebElement PromotionNumber;
    
    @FindBy(xpath="//a[text()='Intent']")
    private WebElement Intent;
    
    @FindBy(xpath="//a[text()='Promotion Description']")
    private WebElement PromotionDescription;
    
    @FindBy(xpath="//div[@data-role='droptarget']")
    private WebElement droptarget;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
    private WebElement groupbyPromotionNumber;
    
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
    
    @FindBy(xpath="//button[text()='Close']")
    private WebElement searchClose;
    
    @FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
    
    @FindBy(xpath="//i[@class='fas fa-sync']")
    private WebElement clearsearch;
    

    public boolean isAdhocOptionEnhancementPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return AdhocOptionEnhancement.isEnabled();
    }
    public void addNewAdhocOptionEnhancementRecord(AdhocOptionEnhancementDetails details) throws Exception {
        selectWebElement(addNewAdhocOptionEnhancementRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(numericTextbox);
        enterValueToTxtField(promotionalNumber,details.getPromotionalNumber());
        selectWebElement(promotionalDescription);
        enterValueToTxtField(promotionalDescription,details.getPromotionalDescription());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox,details.getLanguage());
        languageDropdown.sendKeys(Keys.TAB);
        selectWebElement(directTransferEnabledDropdown);
        selectDropdownFromVisibleText(directTransferEnabledListbox,details.getDirectTransferEnabled());
        if(!details.getPromotionNameWavFile().equals("")) {
            selectWebElement(selectfiles.get(0));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionNameWavFile());
        }
        if(!details.getPromotionDetailsWavFile().equals("")) {
            selectWebElement(selectfiles.get(1));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionDetailsWavFile());
        }
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListbox,details.getStatus());
        selectWebElement(saveButton);
    }
    
    public boolean addnewAdhocOptionEnhancementCancel(AdhocOptionEnhancementDetails details) throws Exception {
    	String actualitems=items.getText();
    	selectWebElement(addNewAdhocOptionEnhancementRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(numericTextbox);
        enterValueToTxtField(promotionalNumber,details.getPromotionalNumber());
        selectWebElement(promotionalDescription);
        enterValueToTxtField(promotionalDescription,details.getPromotionalDescription());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox,details.getLanguage());
        languageDropdown.sendKeys(Keys.TAB);
        selectWebElement(directTransferEnabledDropdown);
        selectDropdownFromVisibleText(directTransferEnabledListbox,details.getDirectTransferEnabled());
        if(!details.getPromotionNameWavFile().equals("")) {
            selectWebElement(selectfiles.get(0));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionNameWavFile());
        }
        if(!details.getPromotionDetailsWavFile().equals("")) {
            selectWebElement(selectfiles.get(1));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionDetailsWavFile());
        }
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListbox,details.getStatus());
        selectWebElement(cancelBtn);
        if(actualitems.equals(items.getText()))
			return true;
		else
		return false;
	}

    public void searchAdhocOptionEnhancementRecord(String column, String value) throws Exception {
        selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,column);
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,value);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    
    public void searchwithoutextsearch(AdhocOptionEnhancementDetails details) {
    	selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Promotion Number");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        selectWebElement(searchSearchBtn);
    }
    
    public Boolean validAndBooleanSearch(AdhocOptionEnhancementDetails details) throws Exception {
    	Boolean Status=false;
    	selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Promotion Number");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,details.getPromotionalNumber());
        selectWebElement(searchAddCriteriaBtn);
        moveToElement(andradiobtn);
        selectWebElement(andradiobtn);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(selectSearchCol.get(2));
        selectDropdownFromVisibleTextContains(columnNameListtwo,"Language");
        selectWebElement(selectSearchCol.get(3));
        selectDropdownFromVisibleText(searchCriteriaDropDwntwo,"Is equal to");
        enterValueToTxtField(searchTextBoxtwo,details.getLanguage());
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
		if(rowdata.getText().equals(details.getPromotionalNumber())) {
			if(rowdatatwo.getText().equals(details.getLanguage()))
			Status=true;
		}
		return Status;	
    }
    
    public boolean validORBooleanSearch(AdhocOptionEnhancementDetails details) throws Exception {
		Boolean Status=false;
    	selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Promotion Number");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,details.getPromotionalNumber());
        selectWebElement(searchAddCriteriaBtn);
        moveToElement(ORradiobtn);
        selectWebElement(ORradiobtn);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(selectSearchCol.get(2));
        selectDropdownFromVisibleTextContains(columnNameListtwo,"Language");
        selectWebElement(selectSearchCol.get(3));
        selectDropdownFromVisibleText(searchCriteriaDropDwntwo,"Is equal to");
        enterValueToTxtField(searchTextBoxtwo,details.getLanguage());
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
        List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));	
        for(WebElement e:rows)
        {
        	if(rowdata.getText().equals(details.getPromotionalNumber())||rowdatatwo.getText().equals(details.getLanguage()))
        		Status=true;
        }
        return Status;
	}
    
    public void editAdhocOptionEnhancementRecord(AdhocOptionEnhancementDetails details) throws Exception {
        searchAdhocOptionEnhancementRecord(details.getSearchColumn(),details.getSearchValue());
        Thread.sleep(1000);
        selectWebElement(editButton);
        selectWebElement(promotionalDescription);
        enterValueToTxtField(promotionalDescription,details.getUpdatedPromotiondescription());
        selectWebElement(directTransferEnabledDropdown);
        selectDropdownFromVisibleText(directTransferEnabledListbox,details.getDirectTransferEnabled());
        if(!details.getPromotionNameWavFile().equals("")) {
        selectWebElement(selectfiles.get(0));
        //Auto It script to load wave file
        FileUploader upload= new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\" + details.getPromotionNameWavFile());}
        if(!details.getPromotionDetailsWavFile().equals("")) {
        selectWebElement(selectfiles.get(1));
        //Auto It script to load wave file
        FileUploader upload= new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getPromotionDetailsWavFile());}
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListbox,details.getStatus());
        selectWebElement(ModifyReasonTextBox);
        enterValueToTxtFieldWithoutClear(ModifyReasonTextBox,details.getModifyReason());
        selectWebElement(saveButton);
        try {
        	selectWebElement(cancelBtn);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
    }
    public void deleteAdhocOptionEnhancementRecord(AdhocOptionEnhancementDetails details) throws Exception {
        searchAdhocOptionEnhancementRecord(details.getSearchColumn(),details.getSearchValue());
        waitUntilWebElementIsClickable(deleteButton);
        selectWebElement(deleteButton);
        enterValueToTxtFieldWithoutClear(deleteReasonTextBox,details.getDeleteReason());
        selectWebElement(deleteYesBtn);
        try {
        	selectWebElement(deleteNoBtn);
        }
        catch(Exception e){
        	e.printStackTrace();
        }
    }
    public String verifySuccessMessage(){
        waitUntilWebElementIsVisible(successmsg);return successmsg.getText();
    }
    
    public void clickonAddNewRecord(){
        selectWebElement(addNewAdhocOptionEnhancementRcrdBtn);
    }
    public void clickOnCancelBtn(){
        if(isElementExist(cancelBtn)){selectWebElement(cancelBtn);}
    }
    public boolean verifyEditFormContainer(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isElementExist(editFormContainer);
    }
    public void clickOnEditButton(){
        selectWebElement(editButton);
    }
    public void clickOnDeleteButton(){
        selectWebElement(deleteButton);
    }
    public void clickOnDeleteCancelBtn(){
        selectWebElement(deleteNoBtn);
    }
    public boolean verifyDeleteContainer(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isElementExist(deleteContainer);
    }
    
	
	public boolean isAddBtnDisplayed() {
    	return addNewAdhocOptionEnhancementRcrdBtn.isDisplayed() && addNewAdhocOptionEnhancementRcrdBtn.isEnabled();
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
    	return exportBtn.isDisplayed() && exportBtn.isEnabled();
    }
    
    public boolean verifylogo() {
		if(isElementExist(AOEImg))
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
		    if (f.getName().startsWith("Adhoc")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "Adhoc Option Enhancement");
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
	public boolean ExporttoExcelWithoutData(AdhocOptionEnhancementDetails details) throws Exception {
		searchAdhocOptionEnhancementRecord(details.getSearchColumn(),details.getSearchValue());
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
		selectWebElement(PromotionDescription);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(Intent);
		selectWebElement(Intent);
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
		DragandDrop(PromotionNumber,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(groupbyPromotionNumber.getText()))
		{return true;}
		else
			return false;		
	}
	public void addNewAdhocOptionEnhancementInvalidRecord(AdhocOptionEnhancementDetails adhocOptionEnhancementDetails) {
		 selectWebElement(addNewAdhocOptionEnhancementRcrdBtn);
	        selectWebElement(saveButton);
	        try {
	        	selectWebElement(cancelBtn);
	        }
	        catch(Exception e) {
	        	e.printStackTrace();
	        }
		
	}
	public String verifyErrorMessage() {
		waitUntilWebElementListIsVisible(errorMsg);
			return errorMsg.get(0).getText();
	}
		
	public void addNewRecordwithoutPromotionNumber(AdhocOptionEnhancementDetails details) throws Exception {
		Thread.sleep(500);
		selectWebElement(addNewAdhocOptionEnhancementRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(promotionalDescription);
        enterValueToTxtField(promotionalDescription,details.getPromotionalDescription());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox,details.getLanguage());
        languageDropdown.sendKeys(Keys.TAB);
        selectWebElement(directTransferEnabledDropdown);
        selectDropdownFromVisibleText(directTransferEnabledListbox,details.getDirectTransferEnabled());
        if(!details.getPromotionNameWavFile().equals("")) {
            selectWebElement(selectfiles.get(0));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionNameWavFile());
        }
        if(!details.getPromotionDetailsWavFile().equals("")) {
            selectWebElement(selectfiles.get(1));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionDetailsWavFile());
        }
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListbox,details.getStatus());
        selectWebElement(saveButton);
        try {
        	selectWebElement(cancelBtn);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		
	}
	public void addNewRecordwithoutPromotiondescription(AdhocOptionEnhancementDetails details) throws Exception {
		Thread.sleep(500);
		selectWebElement(addNewAdhocOptionEnhancementRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(numericTextbox);
        enterValueToTxtField(promotionalNumber,details.getPromotionalNumber());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox,details.getLanguage());
        languageDropdown.sendKeys(Keys.TAB);
        selectWebElement(directTransferEnabledDropdown);
        selectDropdownFromVisibleText(directTransferEnabledListbox,details.getDirectTransferEnabled());
        if(!details.getPromotionNameWavFile().equals("")) {
            selectWebElement(selectfiles.get(0));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionNameWavFile());
        }
        if(!details.getPromotionDetailsWavFile().equals("")) {
            selectWebElement(selectfiles.get(1));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionDetailsWavFile());
        }
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListbox,details.getStatus());
        selectWebElement(saveButton);
        try {
        	selectWebElement(cancelBtn);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		
	}
	public void addNewRecordwithoutLanguage(AdhocOptionEnhancementDetails details) throws Exception {
		Thread.sleep(500);
		selectWebElement(addNewAdhocOptionEnhancementRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(numericTextbox);
        enterValueToTxtField(promotionalNumber,details.getPromotionalNumber());
        selectWebElement(promotionalDescription);
        enterValueToTxtField(promotionalDescription,details.getPromotionalDescription());
        selectWebElement(directTransferEnabledDropdown);
        selectDropdownFromVisibleText(directTransferEnabledListbox,details.getDirectTransferEnabled());
        if(!details.getPromotionNameWavFile().equals("")) {
            selectWebElement(selectfiles.get(0));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionNameWavFile());
        }
        if(!details.getPromotionDetailsWavFile().equals("")) {
            selectWebElement(selectfiles.get(1));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionDetailsWavFile());
        }
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListbox,details.getStatus());
        selectWebElement(saveButton);
        try {
        	selectWebElement(cancelBtn);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		
	}
	public void addNewRecordwithoutDirectTransferEnabled(AdhocOptionEnhancementDetails details) throws Exception {
		Thread.sleep(500);
		selectWebElement(addNewAdhocOptionEnhancementRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(numericTextbox);
        enterValueToTxtField(promotionalNumber,details.getPromotionalNumber());
        selectWebElement(promotionalDescription);
        enterValueToTxtField(promotionalDescription,details.getPromotionalDescription());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox,details.getLanguage());
        languageDropdown.sendKeys(Keys.TAB);
        if(!details.getPromotionNameWavFile().equals("")) {
            selectWebElement(selectfiles.get(0));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionNameWavFile());
        }
        if(!details.getPromotionDetailsWavFile().equals("")) {
            selectWebElement(selectfiles.get(1));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionDetailsWavFile());
        }
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListbox,details.getStatus());
        selectWebElement(saveButton);
        try {
        	selectWebElement(cancelBtn);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		
	}
	public void addNewRecordwithoutpromotionnamewavfile(AdhocOptionEnhancementDetails details) throws Exception {
		Thread.sleep(500);
		selectWebElement(addNewAdhocOptionEnhancementRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(numericTextbox);
        enterValueToTxtField(promotionalNumber,details.getPromotionalNumber());
        selectWebElement(promotionalDescription);
        enterValueToTxtField(promotionalDescription,details.getPromotionalDescription());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox,details.getLanguage());
        languageDropdown.sendKeys(Keys.TAB);
        selectWebElement(directTransferEnabledDropdown);
        selectDropdownFromVisibleText(directTransferEnabledListbox,details.getDirectTransferEnabled());
        if(!details.getPromotionDetailsWavFile().equals("")) {
            selectWebElement(selectfiles.get(1));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionDetailsWavFile());
        }
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListbox,details.getStatus());
        selectWebElement(saveButton);
        
		
	}
	public void addNewRecordwithoutpromotiondetailswavfile(AdhocOptionEnhancementDetails details) throws Exception {
		Thread.sleep(500);
		selectWebElement(addNewAdhocOptionEnhancementRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(numericTextbox);
        enterValueToTxtField(promotionalNumber,details.getPromotionalNumber());
        selectWebElement(promotionalDescription);
        enterValueToTxtField(promotionalDescription,details.getPromotionalDescription());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox,details.getLanguage());
        languageDropdown.sendKeys(Keys.TAB);
        selectWebElement(directTransferEnabledDropdown);
        selectDropdownFromVisibleText(directTransferEnabledListbox,details.getDirectTransferEnabled());
        if(!details.getPromotionNameWavFile().equals("")) {
            selectWebElement(selectfiles.get(0));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionNameWavFile());
        }
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListbox,details.getStatus());
        selectWebElement(saveButton);
	}
	public void addNewRecordwithoutIntent(AdhocOptionEnhancementDetails details) throws Exception {
		Thread.sleep(500);
		selectWebElement(addNewAdhocOptionEnhancementRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(numericTextbox);
        enterValueToTxtField(promotionalNumber,details.getPromotionalNumber());
        selectWebElement(promotionalDescription);
        enterValueToTxtField(promotionalDescription,details.getPromotionalDescription());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox,details.getLanguage());
        languageDropdown.sendKeys(Keys.TAB);
        selectWebElement(directTransferEnabledDropdown);
        selectDropdownFromVisibleText(directTransferEnabledListbox,details.getDirectTransferEnabled());
        if(!details.getPromotionNameWavFile().equals("")) {
            selectWebElement(selectfiles.get(0));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionNameWavFile());
        }
        if(!details.getPromotionDetailsWavFile().equals("")) {
            selectWebElement(selectfiles.get(1));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionDetailsWavFile());
        }
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListbox,details.getStatus());
        selectWebElement(saveButton);
        try {
        	selectWebElement(cancelBtn);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		
	}
	public void addNewRecordwithoutStatus(AdhocOptionEnhancementDetails details) throws Exception {
		Thread.sleep(500);
		selectWebElement(addNewAdhocOptionEnhancementRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(numericTextbox);
        enterValueToTxtField(promotionalNumber,details.getPromotionalNumber());
        selectWebElement(promotionalDescription);
        enterValueToTxtField(promotionalDescription,details.getPromotionalDescription());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListbox,details.getLanguage());
        languageDropdown.sendKeys(Keys.TAB);
        selectWebElement(directTransferEnabledDropdown);
        selectDropdownFromVisibleText(directTransferEnabledListbox,details.getDirectTransferEnabled());
        if(!details.getPromotionNameWavFile().equals("")) {
            selectWebElement(selectfiles.get(0));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionNameWavFile());
        }
        if(!details.getPromotionDetailsWavFile().equals("")) {
            selectWebElement(selectfiles.get(1));
            //Auto It script to load wave file
            FileUploader upload = new FileUploader();
            upload.uploadFile(System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload\\" + details.getPromotionDetailsWavFile());
        }
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(saveButton);
        try {
        	selectWebElement(cancelBtn);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		
	}
	public boolean clearAll(AdhocOptionEnhancementDetails details) throws Exception {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Promotion Number");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,details.getPromotionalNumber());
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
	public boolean verifyinvalidsearchwithwrongdata(AdhocOptionEnhancementDetails details) throws Exception {
		searchAdhocOptionEnhancementRecord("Promotion Number",details.getPromotionalNumber());
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
	public void InvalidBooleanSearchwithoutSearchTextbox(AdhocOptionEnhancementDetails details) throws Exception {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Promotion Number");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,details.getPromotionalNumber());
        selectWebElement(searchAddCriteriaBtn);
        moveToElement(andradiobtn);
        selectWebElement(andradiobtn);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(selectSearchCol.get(2));
        selectDropdownFromVisibleTextContains(columnNameListtwo,"Language");
        selectWebElement(selectSearchCol.get(3));
        selectDropdownFromVisibleText(searchCriteriaDropDwntwo,"Is equal to");
        selectWebElement(searchSearchBtn);
        selectWebElement(searchClose);
	}
	public void InvalidBooleanSearchwithoutSearchTextbox1(AdhocOptionEnhancementDetails details) throws Exception {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Promotion Number");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,details.getPromotionalNumber());
        selectWebElement(searchAddCriteriaBtn);
        moveToElement(andradiobtn);
        selectWebElement(andradiobtn);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(selectSearchCol.get(2));
        selectDropdownFromVisibleTextContains(columnNameListtwo,"Language");
        selectWebElement(selectSearchCol.get(3));
        selectDropdownFromVisibleText(searchCriteriaDropDwntwo,"Is equal to");
        enterValueToTxtField(searchTextBoxtwo,details.getLanguage());
        searchTextBox.clear();
        selectWebElement(searchSearchBtn);
        selectWebElement(searchClose);
		
	}
	public void editRecordWithoutModifyReason(AdhocOptionEnhancementDetails details) throws Exception {
		searchAdhocOptionEnhancementRecord(details.getSearchColumn(),details.getSearchValue());
        waitUntilWebElementIsClickable(editButton);
		selectWebElement(editButton);
        selectWebElement(saveButton);
        try {
        	selectWebElement(cancelBtn);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }		
	}

	
}
