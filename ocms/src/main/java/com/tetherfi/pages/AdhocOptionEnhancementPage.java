package com.tetherfi.pages;

import com.tetherfi.model.ivr.AdhocOptionEnhancementDetails;
import com.tetherfi.utility.FileUploader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.security.Key;
import java.util.List;

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

    @FindBy(css = "#1001sFormDropDown .k-i-arrow-60-down")
    private List<WebElement> selectSearchCriteria;

    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchCriteriaDropDwn;

    @FindBy(id = "1001sTextToSearch")
    private WebElement searchTextBox;

    @FindBy(css = "#1001sAddButton .k-i-add")
    private WebElement searchAddCriteriaBtn;

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

    @FindBy(css = "#toast-container .toast-error")
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
    
    @FindBy(css = ".fa-expand")
    private WebElement fullScrnBtn;
    
    @FindBy(css = ".fa-compress")
    private WebElement minimiseScrnBtn;
    
    @FindBy(xpath = "//button[text()='Export to Excel']")
    private WebElement exportBtn;

    public boolean isAdhocOptionEnhancementPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return AdhocOptionEnhancement.isEnabled();
    }
    public void addNewAdhocOptionEnhancementRecord(AdhocOptionEnhancementDetails details) {
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

    public void searchAdhocOptionEnhancementRecord(String column, String value) {
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
    public void editAdhocOptionEnhancementRecord(AdhocOptionEnhancementDetails details) {
        searchAdhocOptionEnhancementRecord(details.getSearchColumn(),details.getSearchValue());
        selectWebElement(editButton);
        selectWebElement(promotionalDescription);
        enterValueToTxtField(promotionalDescription,details.getPromotionalDescription());
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
        enterValueToTxtField(ModifyReasonTextBox,details.getModifyReason());
        selectWebElement(saveButton);
    }
    public void deleteAdhocOptionEnhancementRecord(AdhocOptionEnhancementDetails details) {
        searchAdhocOptionEnhancementRecord(details.getSearchColumn(),details.getSearchValue());
        selectWebElement(deleteButton);
        enterValueToTxtField(deleteReasonTextBox,details.getDeleteReason());
        selectWebElement(deleteYesBtn);
    }
    public String verifySuccessMessage(){
        if(errorMsg.size()>0){return errorMsg.get(0).getText();}
        else{waitUntilWebElementIsVisible(successmsg);return successmsg.getText();}
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
    public boolean verifyPageFullScreen() {
        selectWebElement(fullScrnBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(minimiseScrnBtn);
        return (isWebElementDisplayed(minimiseScrnBtn) && isWebElementEnabled(minimiseScrnBtn));
    }
    public void clickMinimiseScrnBtn() {
        if(isElementExist(minimiseScrnBtn)){selectWebElement(minimiseScrnBtn);}
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
}
