package com.tetherfi.pages;

import com.tetherfi.model.chat.SOI_IntentMappingDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SOI_IntentMappingPage extends BasePage {

    public SOI_IntentMappingPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement soiIntentMapping;

    @FindBy(id="create")
    private WebElement addNewSoiIntentMappingRecordBtn;

    @FindBy(css=".k-edit-form-container")
    private WebElement popupContent;

    @FindBy(id="Segment")
    private WebElement segment;

    @FindBy(id="SubSegment")
    private WebElement subSegmnent;

    @FindBy(id="Intent")
    private WebElement intent;

    @FindBy(id="Language")
    private WebElement language;

    @FindBy(id="RequestType")
    private WebElement requestType;

    @FindBy(css=".k-edit-form-container .form-group .k-numerictextbox .k-numeric-wrap .k-formatted-value")
    private List<WebElement> textbox;

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


    public boolean isSoiIntentMappingPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return soiIntentMapping.isEnabled();
    }

    public void addNewSoiIntentMappingRecord(SOI_IntentMappingDetails details){
        selectWebElement(addNewSoiIntentMappingRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        selectWebElement(segment);
        enterValueToTxtField(segment,details.getSegment());
        selectWebElement(subSegmnent);
        enterValueToTxtField(subSegmnent,details.getSubSegment());
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(language);
        enterValueToTxtField(language,details.getLanguage());
        selectWebElement(requestType);
        enterValueToTxtField(requestType,details.getRequestType());
        selectWebElement(textbox.get(0));
        enterValueToTxtField(chatVDN,details.getChatVDN());
        selectWebElement(textbox.get(1));
        enterValueToTxtField(cBAVDN,details.getCBAVDN());
        selectWebElement(custEntType);
        enterValueToTxtField(custEntType,details.getCustEntType());
        btnClick(saveBtn);
    }
    public void searchSoiIntentMappingRecord(String segment)  {
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
    public void editSoiIntentMappingRecord(SOI_IntentMappingDetails details) {
        searchSoiIntentMappingRecord(details.getSegment());
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        selectWebElement(subSegmnent);
        enterValueToTxtField(subSegmnent,details.getSubSegment());
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(language);
        enterValueToTxtField(language,details.getLanguage());
        selectWebElement(requestType);
        enterValueToTxtField(requestType,details.getRequestType());
        selectWebElement(textbox.get(0));
        enterValueToTxtField(chatVDN,details.getChatVDN());
        selectWebElement(textbox.get(1));
        enterValueToTxtField(cBAVDN,details.getCBAVDN());
        selectWebElement(custEntType);
        enterValueToTxtField(custEntType,details.getCustEntType());
        enterValueToTxtField(modifyReasonTextBox,details.getModifyReason());
        btnClick(saveBtn);
    }
    public void deleteWaitTimeColorConfigRecord(String segment, String reason) {
        searchSoiIntentMappingRecord(segment);
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
}
