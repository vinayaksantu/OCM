package com.tetherfi.pages;

import com.tetherfi.model.ivr.IvrConfigDetails;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class IvrConfigPage extends BasePage {

    public IvrConfigPage(WebDriver driver) {
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
    private WebElement ivrConfig;

    @FindBy(css="#create")
    private WebElement addNewIVRConfigRcrdBtn;

    @FindBy(css=".k-grid-content")
    private WebElement gridContent;

    @FindBy(css="span[aria-owns='Parameter_listbox']")
    private WebElement parameterDropdown;

    @FindBy(css="#Parameter-list .k-list-optionlabel")
    private List<WebElement> selectParameter;

    @FindBy(css="ul[id='Parameter_listbox'] li")
    private List<WebElement> parameterListBox;

    @FindBy(css="span[aria-owns='Value_listbox']")
    private WebElement valueDropdown;

    @FindBy(css="#Value-list .k-list-optionlabel")
    private List<WebElement> selectValue;

    @FindBy(css="ul[id='Value_listbox'] li")
    private List<WebElement> valueListBox;

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

    @FindBy(css = ".fa-expand")
    private WebElement fullScrnBtn;

    @FindBy(css = ".fa-compress")
    private WebElement minimiseScrnBtn;

    @FindBy(css=".k-grid-cancel")
    private WebElement cancelBtn;

    @FindBy(css=".k-edit-form-container")
    private WebElement editFormContainer;

    @FindBy(css="div[style='display: block; z-index: 10002; opacity: 0.5;']")
    private WebElement deleteContainer;

    public boolean isIvrConfigPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return ivrConfig.isEnabled();
    }
    public void addNewIvrConfigRecord(IvrConfigDetails details) {
        selectWebElement(addNewIVRConfigRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(parameterDropdown);
        selectDropdownFromVisibleText(parameterListBox,details.getParameter());
        parameterDropdown.sendKeys(Keys.TAB);
        selectWebElement(valueDropdown);
        selectDropdownFromVisibleText(valueListBox,details.getValue());
        valueDropdown.sendKeys(Keys.TAB);
        selectWebElement(saveButton);
    }
    public void searchIvrConfigRecord(String column,String value) {
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
    public void editIvrConfigRecord(IvrConfigDetails details) {
        searchIvrConfigRecord(details.getSearchColumn(),details.getSearchValue());
        selectWebElement(editButton);
        selectWebElement(parameterDropdown);
        if(details.getParameter().equals("Select Parameter"))
        {selectDropdownFromVisibleText(selectParameter,details.getParameter());}
        else{selectDropdownFromVisibleText(parameterListBox,details.getParameter());}
        parameterDropdown.sendKeys(Keys.TAB);
        selectWebElement(valueDropdown);
        if(details.getValue().equals("Select Value"))
        {selectDropdownFromVisibleText(selectValue,details.getValue());}
        else{selectDropdownFromVisibleText(valueListBox,details.getValue());}
        valueDropdown.sendKeys(Keys.TAB);
        selectWebElement(ModifyReasonTextBox);
        enterValueToTxtField(ModifyReasonTextBox,details.getModifyReason());
        selectWebElement(saveButton);
    }
    public void deleteIvrConfigRecord(IvrConfigDetails details) {
        searchIvrConfigRecord(details.getSearchColumn(),details.getSearchValue());
        selectWebElement(deleteButton);
        enterValueToTxtField(deleteReasonTextBox,details.getDeleteReason());
        selectWebElement(deleteYesBtn);
    }
    public String verifySuccessMessage(){
        if(errorMsg.size()>0){return errorMsg.get(0).getText();}
       else{waitUntilWebElementIsVisible(successmsg);return successmsg.getText();}
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
    public void clickOnCancelBtn(){
        if(isElementExist(cancelBtn)){selectWebElement(cancelBtn);}
    }
    public void clickOnAddRecord(){
        selectWebElement(addNewIVRConfigRcrdBtn);
    }
    public boolean verifyEditFormContainer(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isElementExist(editFormContainer);
    }
    public boolean verifyDeleteContainer(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isElementExist(deleteContainer);
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
}
