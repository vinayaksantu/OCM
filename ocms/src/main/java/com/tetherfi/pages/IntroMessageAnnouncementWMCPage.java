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

import com.tetherfi.model.ivr.IntroMessageAnnouncementDetails;
import com.tetherfi.utility.FileUploader;

public class IntroMessageAnnouncementWMCPage extends BasePage {

	public IntroMessageAnnouncementWMCPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".ibox-tools")
	private WebElement introMessageAnnouncement;
	
	@FindBy(xpath="//i[@class='fa fa-envelope']")
	private WebElement IMAImage;
	
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
	
	@FindBy(css="#drillGrid #create")
	private WebElement addNewIntroMessageAnnouncementRcrdBtn;
	
	@FindBy(css="span[aria-owns='Functionality_listbox']")
    private WebElement funtionalityDropdown;
	
	@FindBy(css="ul[id='Functionality_listbox'] li")
	private List<WebElement> functionalityListBox;
	
	@FindBy(id="Hotline")
	private WebElement HotlineTextbox;

	@FindBy(css="span[aria-owns='Language_listbox']")
	private WebElement languageDropdown;

	@FindBy(css="ul[id='Language_listbox'] li")
	private List<WebElement> languageListBox;

	@FindBy(css="span[aria-owns='Status_listbox']")
    private WebElement statusDropdown;

    @FindBy(css="ul[id='Status_listbox'] li")
    private List<WebElement> statusListBox;

    @FindBy(css="span[aria-owns='Interrupt_listbox']")
    private WebElement interruptDropdown;

    @FindBy(css="ul[id='Interrupt_listbox'] li")
    private List<WebElement> interruptListBox;

    @FindBy(id="StartDateTime")
    private WebElement startDateTime;

    @FindBy(id="EndDateTime")
    private WebElement endDateTime;

    @FindBy(css=".k-upload-button")
    private WebElement selectFile;
    
    @FindBy(css=".k-grid-cancel")
    private WebElement cancelBtn;

    @FindBy(css=".k-grid-update")
    private WebElement saveBtn; 
    
    @FindBy(xpath="//span[@class='k-pager-info k-label']")
    private WebElement items;
    
    @FindBy(css="#toast-container .toast-message")
	private WebElement successmsg;
    
    @FindBy(css=".toast toast-success")
    private WebElement deletemsg;
    
	@FindBy(css = "#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;
    
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
	
	@FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;
	
	@FindBy(css=".k-grid-content")
	private WebElement gridContent;
	
	@FindBy(css=" .k-grid-edit")
	private WebElement editBtn;
	
	@FindBy(id="ModifyReason")
	private WebElement mopdifyReason;
	
	@FindBy(xpath="//tbody/tr/td[2]")
	private WebElement rowdata;
	
	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearAll;
	
	@FindBy(xpath="//button[text()='Close']")
	private WebElement searchClose;
	
	@FindBy(css=".k-grid-CustomDelete")
	private WebElement deleteBtn;
	
	@FindBy(id="yesButton")
	private WebElement deleteyesBtn;
	
	@FindBy(id="noButton")
	private WebElement deletenoBtn;
	
	@FindBy(id="ModifyReason1")
	private WebElement deleteResonText;
	
	@FindBy(id="export")
	private WebElement exportToExcel;
	
	@FindBy(xpath="//span[@class='k-input']")
	private WebElement pagerSize;
	
	@FindBy(id="tGrid")
	private WebElement auditGridContent;
	
	@FindBy(css="a[aria-label='Go to the next page']")
	private WebElement nextPageIcon;
	
	@FindBy(id = "drillGrid")
    private WebElement grid;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
	
	@FindBy(xpath="//a[text()='Functionality']")
	private WebElement Functionality;
	
	
	
	
	
	

	
	
	
	public boolean isIntroMessageAnnouncementPageDisplayed() {
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		return introMessageAnnouncement.isEnabled();
		
	}
	
	public boolean verifyLogo() {
		if(isElementExist(IMAImage))
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
	
	public boolean addNewcancel(IntroMessageAnnouncementDetails details) throws Exception {
		String actualitems=items.getText();
		selectWebElement(addNewIntroMessageAnnouncementRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(funtionalityDropdown);
        selectDropdownFromVisibleText(functionalityListBox,details.getFunctionality());
        selectWebElement(HotlineTextbox);
        enterValueToTxtField(HotlineTextbox,details.getHotLine());
        selectWebElement(startDateTime);
        enterValueToTxtField(startDateTime,details.getStartDateTime());
        selectWebElement(endDateTime);
        enterValueToTxtField(endDateTime,details.getEndDateTime());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,details.getStatus());
        selectWebElement(interruptDropdown);
        selectDropdownFromVisibleText(interruptListBox,details.getInterrupt());
        clickOnUsingActionClass(selectFile);
        //Auto It script to load wave file
        FileUploader upload= new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getWavFile());
        selectWebElement(cancelBtn);
		if(actualitems.equals(items.getText()))
			return true;
		else
			return false;
	}
	
	public void addNewIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails details) throws Exception {
		selectWebElement(addNewIntroMessageAnnouncementRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(funtionalityDropdown);
        selectDropdownFromVisibleText(functionalityListBox,details.getFunctionality());
        selectWebElement(HotlineTextbox);
        enterValueToTxtField(HotlineTextbox,details.getHotLine());
        selectWebElement(startDateTime);
        enterValueToTxtField(startDateTime,details.getStartDateTime());
        selectWebElement(endDateTime);
        enterValueToTxtField(endDateTime,details.getEndDateTime());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,details.getStatus());
        selectWebElement(interruptDropdown);
        selectDropdownFromVisibleText(interruptListBox,details.getInterrupt());
        clickOnUsingActionClass(selectFile);
        //Auto It script to load wave file
        FileUploader upload= new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getWavFile());
        selectWebElement(saveBtn);
		
	}
	public String verifySuccessMessage(){
        waitUntilWebElementIsVisible(successmsg);return successmsg.getText();
    }
	
	public String verifyDeleteSuccessMessage() {
		waitUntilWebElementIsVisible(deletemsg);return deletemsg.getText();
	}
	public Boolean verifyErrorMessage() {
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.size()>0){
			return true;}
			else 
				return false;
	}
	
	public void AddIntroMessageAnnouncementRecordWithoutFunctionality(IntroMessageAnnouncementDetails details) throws Exception {
		selectWebElement(addNewIntroMessageAnnouncementRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(HotlineTextbox);
        enterValueToTxtField(HotlineTextbox,details.getHotLine());
        selectWebElement(startDateTime);
        enterValueToTxtField(startDateTime,details.getStartDateTime());
        selectWebElement(endDateTime);
        enterValueToTxtField(endDateTime,details.getEndDateTime());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,details.getStatus());
        selectWebElement(interruptDropdown);
        selectDropdownFromVisibleText(interruptListBox,details.getInterrupt());
        clickOnUsingActionClass(selectFile);
        //Auto It script to load wave file
        FileUploader upload= new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getWavFile());
        selectWebElement(saveBtn);
		
	}
	
	public void AddNewIntroMessageAnnouncementRecordWithoutHotLine(IntroMessageAnnouncementDetails details) throws Exception {
		selectWebElement(addNewIntroMessageAnnouncementRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(funtionalityDropdown);
        selectDropdownFromVisibleText(functionalityListBox,details.getFunctionality());
        selectWebElement(startDateTime);
        enterValueToTxtField(startDateTime,details.getStartDateTime());
        selectWebElement(endDateTime);
        enterValueToTxtField(endDateTime,details.getEndDateTime());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,details.getStatus());
        selectWebElement(interruptDropdown);
        selectDropdownFromVisibleText(interruptListBox,details.getInterrupt());
        clickOnUsingActionClass(selectFile);
        //Auto It script to load wave file
        FileUploader upload= new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getWavFile());
        selectWebElement(saveBtn);
		
	}
	
	public void AddIntroMessageAnnouncementWithoutStartDate(IntroMessageAnnouncementDetails details) throws Exception {
		
		selectWebElement(addNewIntroMessageAnnouncementRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(funtionalityDropdown);
        selectDropdownFromVisibleText(functionalityListBox,details.getFunctionality());
        selectWebElement(HotlineTextbox);
        enterValueToTxtField(HotlineTextbox,details.getHotLine());
        selectWebElement(endDateTime);
        enterValueToTxtField(endDateTime,details.getEndDateTime());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,details.getStatus());
        selectWebElement(interruptDropdown);
        selectDropdownFromVisibleText(interruptListBox,details.getInterrupt());
        clickOnUsingActionClass(selectFile);
        //Auto It script to load wave file
        FileUploader upload= new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getWavFile());
        selectWebElement(saveBtn);
		
	}
	 public void AddNewIntroMessageAnnouncementWithoutEndDateTime(IntroMessageAnnouncementDetails details) throws Exception {
		 selectWebElement(addNewIntroMessageAnnouncementRcrdBtn);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(funtionalityDropdown);
	        selectDropdownFromVisibleText(functionalityListBox,details.getFunctionality());
	        selectWebElement(HotlineTextbox);
	        enterValueToTxtField(HotlineTextbox,details.getHotLine());
	        selectWebElement(startDateTime);
	        enterValueToTxtField(startDateTime,details.getStartDateTime());
	        selectWebElement(languageDropdown);
	        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
	        selectWebElement(statusDropdown);
	        selectDropdownFromVisibleText(statusListBox,details.getStatus());
	        selectWebElement(interruptDropdown);
	        selectDropdownFromVisibleText(interruptListBox,details.getInterrupt());
	        clickOnUsingActionClass(selectFile);
	        //Auto It script to load wave file
	        FileUploader upload= new FileUploader();
	        upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getWavFile());
	        selectWebElement(saveBtn);
	 }
	 
	 public void AddIntroMessageAnnouncementWithoutLanguage(IntroMessageAnnouncementDetails details) throws Exception {
		 selectWebElement(addNewIntroMessageAnnouncementRcrdBtn);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(funtionalityDropdown);
	        selectDropdownFromVisibleText(functionalityListBox,details.getFunctionality());
	        selectWebElement(HotlineTextbox);
	        enterValueToTxtField(HotlineTextbox,details.getHotLine());
	        selectWebElement(startDateTime);
	        enterValueToTxtField(startDateTime,details.getStartDateTime());
	        selectWebElement(endDateTime);
	        enterValueToTxtField(endDateTime,details.getEndDateTime());
	        selectWebElement(statusDropdown);
	        selectDropdownFromVisibleText(statusListBox,details.getStatus());
	        selectWebElement(interruptDropdown);
	        selectDropdownFromVisibleText(interruptListBox,details.getInterrupt());
	        clickOnUsingActionClass(selectFile);
	        //Auto It script to load wave file
	        FileUploader upload= new FileUploader();
	        upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getWavFile());
	        selectWebElement(saveBtn);
	 }
	 
	 public void AddIntroMessgeAnnouncementWithoutStatus(IntroMessageAnnouncementDetails details) throws Exception {
		 selectWebElement(addNewIntroMessageAnnouncementRcrdBtn);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(funtionalityDropdown);
	        selectDropdownFromVisibleText(functionalityListBox,details.getFunctionality());
	        selectWebElement(HotlineTextbox);
	        enterValueToTxtField(HotlineTextbox,details.getHotLine());
	        selectWebElement(startDateTime);
	        enterValueToTxtField(startDateTime,details.getStartDateTime());
	        selectWebElement(endDateTime);
	        enterValueToTxtField(endDateTime,details.getEndDateTime());
	        selectWebElement(languageDropdown);
	        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
	        selectWebElement(interruptDropdown);
	        selectDropdownFromVisibleText(interruptListBox,details.getInterrupt());
	        clickOnUsingActionClass(selectFile);
	        //Auto It script to load wave file
	        FileUploader upload= new FileUploader();
	        upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getWavFile());
	        selectWebElement(saveBtn);
	 }
	
	 public void AddIntroMessageannouncementWithoutInturupt(IntroMessageAnnouncementDetails details) throws Exception {
		 selectWebElement(addNewIntroMessageAnnouncementRcrdBtn);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(funtionalityDropdown);
	        selectDropdownFromVisibleText(functionalityListBox,details.getFunctionality());
	        selectWebElement(HotlineTextbox);
	        enterValueToTxtField(HotlineTextbox,details.getHotLine());
	        selectWebElement(startDateTime);
	        enterValueToTxtField(startDateTime,details.getStartDateTime());
	        selectWebElement(endDateTime);
	        enterValueToTxtField(endDateTime,details.getEndDateTime());
	        selectWebElement(languageDropdown);
	        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
	        selectWebElement(statusDropdown);
	        selectDropdownFromVisibleText(statusListBox,details.getStatus());
	        clickOnUsingActionClass(selectFile);
	        //Auto It script to load wave file
	        FileUploader upload= new FileUploader();
	        upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\"+details.getWavFile());
	        selectWebElement(saveBtn);
	 }
	
	 public void AddIntroMessageAnnouncementWithoutWaveFile(IntroMessageAnnouncementDetails details) throws Exception {
		 
		 selectWebElement(addNewIntroMessageAnnouncementRcrdBtn);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(funtionalityDropdown);
	        selectDropdownFromVisibleText(functionalityListBox,details.getFunctionality());
	        selectWebElement(HotlineTextbox);
	        enterValueToTxtField(HotlineTextbox,details.getHotLine());
	        selectWebElement(startDateTime);
	        enterValueToTxtField(startDateTime,details.getStartDateTime());
	        selectWebElement(endDateTime);
	        enterValueToTxtField(endDateTime,details.getEndDateTime());
	        selectWebElement(languageDropdown);
	        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
	        selectWebElement(statusDropdown);
	        selectDropdownFromVisibleText(statusListBox,details.getStatus());
	        selectWebElement(interruptDropdown);
	        selectDropdownFromVisibleText(interruptListBox,details.getInterrupt());
	        selectWebElement(saveBtn);
		 
	 }
	 
	 private void SearchIntroMessageAnnouncementRecord(String hotline) throws Exception {
		    selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Hotline");
	        Thread.sleep(1000);
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
	        enterValueToTxtBox1(searchTextBox,hotline);
	        selectWebElement(searchSearchBtn);
	        waitForJqueryLoad(driver);
	        waitUntilWebElementIsVisible(gridContent);	
	 }
	 
	 public void editIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails details) throws Exception {
		 SearchIntroMessageAnnouncementRecord(details.getHotLine());
		 selectWebElement(editBtn);
		 Thread.sleep(2000);
		 selectWebElement(interruptDropdown);
		 selectDropdownFromVisibleText(interruptListBox,details.getUpdatedInterrupt());
		 selectWebElement(mopdifyReason);
		 enterValueToTxtFieldWithoutClear(mopdifyReason,details.getModifyReason());
		 selectWebElement(saveBtn);
		 
		 
	 }
	 
	 public void editIntroMessageAnnouncementRecordWithoutModifyReson(IntroMessageAnnouncementDetails details) throws Exception {
		 SearchIntroMessageAnnouncementRecord(details.getHotLine());
		 selectWebElement(editBtn);
		 Thread.sleep(2000);
		 selectWebElement(interruptDropdown);
		 selectDropdownFromVisibleText(interruptListBox,details.getUpdatedInterrupt());
		 selectWebElement(saveBtn);
	 }
	 
	 public boolean editCancel(IntroMessageAnnouncementDetails details) throws Exception {
		 SearchIntroMessageAnnouncementRecord(details.getHotLine());
		 selectWebElement(editBtn);
		 Thread.sleep(2000);
		 selectWebElement(interruptDropdown);
		 selectDropdownFromVisibleText(interruptListBox,details.getUpdatedInterrupt());
		 selectWebElement(mopdifyReason);
		 enterValueToTxtFieldWithoutClear(mopdifyReason,details.getModifyReason());
		 selectWebElement(cancelBtn);
		 System.out.println(rowdata);
		 if(rowdata.getText().equals(details.getFunctionality()))
			 return true;
		 else
			 return false;
		 
	 }
	 
	 public boolean clearAll(IntroMessageAnnouncementDetails details) {
		    selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Hotline");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
	        enterValueToTxtBox1(searchTextBox,details.getHotLine());
	        selectWebElement(clearAll);
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
	 
	 public void searchWithoutSearchText(IntroMessageAnnouncementDetails details) {
		    selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Hotline");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
	        selectWebElement(searchSearchBtn);
	 }
	 
	 public void deleteWithoutDeleteReason(IntroMessageAnnouncementDetails details) throws Exception {
		 SearchIntroMessageAnnouncementRecord(details.getHotLine());
		 selectWebElement(deleteBtn);
		 selectWebElement(deleteyesBtn);
		 selectWebElement(deletenoBtn);
			 
	 }
	 
	 public boolean deleteCancel(IntroMessageAnnouncementDetails details) throws Exception {
		 SearchIntroMessageAnnouncementRecord(details.getHotLine());
		 selectWebElement(deleteBtn);
		 enterValueToTxtField(deleteResonText,details.getDeleteReason());
		 selectWebElement(deletenoBtn);
		 if(rowdata.getText().equals(details.getFunctionality()))
			 return true;
		 else
			 return false;
		 
	 }
	 
	 public void deleteIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails details) throws Exception {
		 SearchIntroMessageAnnouncementRecord(details.getHotLine());
		 selectWebElement(deleteBtn);
		 Thread.sleep(2000);
		 enterValueToTxtFieldWithoutClear(deleteResonText,details.getDeleteReason());
		 selectWebElement(deleteyesBtn);
		 
	 }
	 
	 public boolean VerifyExportToExcel(String filePath) {
		 final File folder=new File(filePath);
		 for(final File f:folder.listFiles()) {
			 if(f.getName().startsWith("IntroMessage")) {
				 f.delete();
			 }
		 }
		 selectWebElement(exportToExcel);
		 waitForJqueryLoad(driver);
		 try {
			 Thread.sleep(2000);
		 }catch(InterruptedException e) {
			 e.printStackTrace();
		 }
		 Boolean Status=verifyExportPageFileDownload(filePath,"IntroMessage Announcement");
		 return Status;
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
	 
		
	 public boolean verifySearchIsNotEqualTo(String hotline) throws Exception {
			Boolean Status=false;
			Map<String, String> map=new HashMap<String,String>() ;
			map.put("Hotline", hotline);
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Hotline");
	        Thread.sleep(1000);
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
	        enterValueToTxtField(searchTextBox,hotline);		
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
		public boolean verifySearchContains(String hotline) throws Exception {
			Boolean Status=false;
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Hotline");
	        Thread.sleep(1000);
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
	        enterValueToTxtField(searchTextBox,hotline);		
	        selectWebElement(searchSearchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(map1.get("Hotline").toUpperCase().contains(hotline.toUpperCase()))
	        	Status= true;
	        	else 
	        		Status= false;
		}
	        return Status;
		}
		public boolean verifySearchDoesNotContains(String hotline) throws Exception {
			Boolean Status=false;
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Hotline");
	        Thread.sleep(1000);
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not contain");
	        enterValueToTxtField(searchTextBox,hotline);		
	        selectWebElement(searchSearchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(!map1.get("Hotline").toLowerCase().contains(hotline.toLowerCase()))
	        	Status= true;
	        	else 
	        		Status= false;
		}
	        return Status;
		}
		
		public boolean verifySearchStartsWith(String hotline) throws Exception {
			Boolean Status=false;
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Hotline");
	        Thread.sleep(1000);
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
	        enterValueToTxtField(searchTextBox,hotline);		
	        selectWebElement(searchSearchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(map1.get("Hotline").toLowerCase().startsWith(hotline.toLowerCase()))
	        	Status= true;
	        	else 
	        		Status= false;
		}
	        return Status;
		}
		
		public boolean verifySearchEndsWith(String hotline) throws Exception {
			Boolean Status=false;
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Hotline");
	        Thread.sleep(1000);
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
	        enterValueToTxtField(searchTextBox,hotline);		
	        selectWebElement(searchSearchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(map1.get("Hotline").toUpperCase().endsWith(hotline.toUpperCase()))
	        	Status= true;
	        	else 
	        		Status= false;
		}
	        return Status;
		}
		
		public boolean verifyExportToExcel(String filePath) {
			final File folder = new File(filePath);
			for (final File f : folder.listFiles()) {
			    if (f.getName().startsWith("Intro Message Announcement")) {
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
			Boolean Status=verifyExportPageFileDownload(filePath, "Intro Message Announcement");
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
		
		public boolean ExporttoExcelWithoutData(IntroMessageAnnouncementDetails details) throws Exception {
			SearchIntroMessageAnnouncementRecord(details.getHotLine());
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
			selectWebElement(Functionality);
			selectWebElement(exporttoexcel);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void SortByDescending() {
			selectWebElement(Functionality);
			selectWebElement(Functionality);
			selectWebElement(exporttoexcel);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	
}
