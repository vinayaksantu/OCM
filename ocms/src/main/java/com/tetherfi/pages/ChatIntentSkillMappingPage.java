package com.tetherfi.pages;

import com.tetherfi.model.chat.ChatIntentSkillMappingDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ChatIntentSkillMappingPage extends BasePage {

    public ChatIntentSkillMappingPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement intentSkillMapping;

    @FindBy(id="create")
    private WebElement addNewIntentSkillMappingRecordBtn;

    @FindBy(css=".k-edit-form-container")
    private WebElement popupContent;

    @FindBy(css="span[aria-owns='ChatVdn_listbox']")
    private WebElement skillDropdown;

    @FindBy(css="ul[id='ChatVdn_listbox'] li")
    private List<WebElement> skillListBox;

    @FindBy(id="Segment")
    private WebElement segment;

    @FindBy(id="SubSegment")
    private WebElement subSegmnent;

    @FindBy(id="Intent")
    private WebElement intent;

    @FindBy(css="span[aria-owns='Language_listbox']")
    private WebElement languageDropdown;

    @FindBy(css="ul[id='Language_listbox'] li")
    private List<WebElement> languageListBox;

    @FindBy(id="RequestType")
    private WebElement requestType;

    @FindBy(css=".k-edit-form-container .form-group .k-numerictextbox .k-numeric-wrap .k-formatted-value")
    private List<WebElement> textbox;

    @FindBy(css="span[aria-owns='RequestType_listbox']")
    private WebElement channelDropdown;

    @FindBy(css="ul[id='RequestType_listbox'] li")
    private List<WebElement> channelListbox;

    @FindBy(id="ChatVdn")
    private WebElement chatVDN;

    @FindBy(id="CbaVdn")
    private WebElement cBAVDN;

    @FindBy(id="CustEntType")
    private WebElement custEntType;

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

    @FindBy(css=".modal-body .form-inline .form-group .k-textbox")
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

    @FindBy(id="SkillTimeOut")
    private WebElement skillTimeOut;

    @FindBy(id="SLA")
    private WebElement sla;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;

    public boolean isChatIntentSkillMappingPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return intentSkillMapping.isEnabled();
    }

    public void addNewIntentSkillMappingRecord(ChatIntentSkillMappingDetails details){
        selectWebElement(addNewIntentSkillMappingRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        selectWebElement(skillDropdown);
        selectDropdownFromVisibleText(skillListBox,details.getSkill());
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
        selectWebElement(segment);
        enterValueToTxtField(segment,details.getSegment());
        selectWebElement(subSegmnent);
        enterValueToTxtField(subSegmnent,details.getSubSegment());
        selectWebElement(channelDropdown);
        selectDropdownFromVisibleText(channelListbox,details.getChannel());
        selectWebElement(custEntType);
        enterValueToTxtField(custEntType,details.getCustEntType());
        selectWebElement(skillTimeOut);
        enterValueToTxtField(skillTimeOut,details.getSkillTimeOut());
        selectWebElement(sla);
        enterValueToTxtField(sla,details.getSla());
        btnClick(saveBtn);
    }
    public void searchIntentSkillMappingRecord(String segment)  {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Segment");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),segment);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public void editIntentSkillMappingRecord(ChatIntentSkillMappingDetails details) {
        searchIntentSkillMappingRecord(details.getSegment());
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
     //   selectWebElement(skillDropdown);
     //   selectDropdownFromVisibleText(skillListBox,details.getSkill());
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
        selectWebElement(segment);
        enterValueToTxtField(segment,details.getSegment());
        selectWebElement(subSegmnent);
        enterValueToTxtField(subSegmnent,details.getSubSegment());
       // selectWebElement(channelDropdown);
       // selectDropdownFromVisibleText(channelListbox,details.getChannel());
        selectWebElement(custEntType);
        enterValueToTxtField(custEntType,details.getCustEntType());
        selectWebElement(skillTimeOut);
        enterValueToTxtField(skillTimeOut,details.getSkillTimeOut());
        selectWebElement(sla);
        enterValueToTxtField(sla,details.getSla());
        enterValueToTxtField(modifyReasonTextBox,details.getModifyReason());
        btnClick(saveBtn);
    }
    public void deleteIntentSkillMappingRecord(String segment, String reason) {
        searchIntentSkillMappingRecord(segment);
        btnClick(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtField(deleteReasonTextBox,reason);
        selectWebElement(yesBtn);
    }

    public String getMessage(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return errorMsg.get(0).getText();}
        else {return successmsg.getText();}
    }
    public boolean isAddBtnDisplayed() {
    	return addNewIntentSkillMappingRecordBtn.isDisplayed() && addNewIntentSkillMappingRecordBtn.isEnabled();
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
}
