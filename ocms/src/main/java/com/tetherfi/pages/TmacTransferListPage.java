package com.tetherfi.pages;

import com.tetherfi.model.tmac.TmacTransferListDetails;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TmacTransferListPage extends BasePage {
    public TmacTransferListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".fa-search")
    private WebElement searchBtn;

    @FindBy(css = ".modal-body .form-inline .form-group .k-select")
    private List<WebElement> selectSearchCol;

    @FindBy(css="ul[id='1001sColumnName_listbox'] li")
    private List<WebElement> columnNameList;

    @FindBy(css = "#tgrid .k-grid-edit")
    private WebElement editButton;

    @FindBy(css = "#drillgrid .k-grid-edit")
    private WebElement editButton1;


    @FindBy(css = "#1001sFormDropDown .k-i-arrow-60-down")
    private List<WebElement> selectSearchCriteria;

    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchCriteriaDropDwn;

    @FindBy(id = "1001sTextToSearch")
    private WebElement searchTextBox;

    @FindBy(css = "#1001sAddButton .k-i-add")
    private WebElement searchAddCriteriaBtn;

    //@FindBy(css=".k-tabstrip-items li")
    @FindBy(xpath = "//div[@id='tabstrip']/ul/li")
    private List<WebElement> navTabs;

    @FindBy(id="create")
    private List<WebElement> addNewTmacTransferListBtn;

    @FindBy(id="createone")
    private WebElement addNewTmacBlindTransferListBtn;

    @FindBy(css="#Name")
    private WebElement tmacTransferListNameTextBox;

    @FindBy(css=".col-lg-6 .k-widget .k-state-default .k-formatted-value")
    private WebElement tmacTransferListAgentExtnsnTextBox;

    @FindBy(css="#Value")
    private WebElement tmacTransferListAgentExtnsnTextBox1;

    @FindBy(css=".ibox-title h5")
    private WebElement tmacTansferList;

    @FindBy(css=".k-grid-update")
    private WebElement SaveButton;

    @FindBy(css="span[aria-owns='Type_listbox']")
    private WebElement typeDropdown;

    @FindBy(css="ul[id='Type_listbox'] li")
    private List<WebElement> typeListBox;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css = "#toast-container .toast-error")
    private List<WebElement> errorMsg;

    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;

    @FindBy(css=".k-grid-content")
    private List <WebElement> gridContent;

    @FindBy(id = "ModifyReason")
    private WebElement editModifyReasonTextBox;

    @FindBy(css = "#grid .k-grid-CustomDelete")
    private WebElement deleteButton;

    @FindBy(css = "#drillgrid .k-grid-CustomDelete")
    private WebElement deleteButton1;

    @FindBy(id = "ModifyReason1")
    private WebElement deleteReasonTextBox;

    @FindBy(id = "yesButton")
    private WebElement deleteYesBtn;

    @FindBy(css="#myform .k-formatted-value")
    private WebElement skillIdTextBox;

    @FindBy(css="#SkillId")
    private WebElement skillIdTextBox1;

    @FindBy(css="#SkillName")
    private WebElement skillNameTextBox;
    
    @FindBy(css=".col-lg-7 .k-dropdown .k-dropdown-wrap .k-input")
    private WebElement skillNameListBox;
    
    @FindBy(css="ul[id='SkillName_listbox'] li")
    private List<WebElement> skillNameListElements;
    
   @FindBy(css="#Vdn")
   private WebElement vdnTextBox;

   @FindBy(css=".col-lg-7 .k-numerictextbox .k-numeric-wrap .k-formatted-value")
      private List<WebElement> skillIdVdnList;

    public boolean isTmacTransferListPageDisplayed() {
        waitForLoad(driver);
        //waitForJqueryLoad(driver);
        return tmacTansferList.isEnabled();
    }
    public void navigateToTab(String tabname){
        waitUntilWebElementListIsVisible(navTabs);
        waitUntilWebElementListIsClickable(navTabs);
        for(WebElement ele: navTabs){
            if(ele.getText().equalsIgnoreCase(tabname)){selectWebElement(ele);break;}
        }
    }

public void addNewTmacConsultTransferList(TmacTransferListDetails tmacTransferListDetails) {
        selectWebElement(addNewTmacTransferListBtn.get(0));
        selectWebElement(tmacTransferListNameTextBox);
        enterValueToTxtField(tmacTransferListNameTextBox,tmacTransferListDetails.getName());
        selectWebElement(tmacTransferListAgentExtnsnTextBox);
        enterValueToTxtField(tmacTransferListAgentExtnsnTextBox1,tmacTransferListDetails.getAgentExtension());
        selectWebElement(typeDropdown);
        selectDropdownFromVisibleText(typeListBox,tmacTransferListDetails.getType());
        selectWebElement(SaveButton);
    }
    public void searchTmacTransferRecord(String Name,String columnNameListValue,int index) {
        selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,columnNameListValue);
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        boolean staleElement = true;
        while(staleElement){
            try{
                enterValueToTxtField(searchTextBox,Name);
                staleElement = false;

            } catch(StaleElementReferenceException e){
                staleElement = true;
            }
        }

        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent.get(index));
    }
    public void editTmacConsultTransferList(TmacTransferListDetails tmacTransferListDetails) {
        searchTmacTransferRecord(tmacTransferListDetails.getName(),"Name",0);
        selectWebElement(editButton);
        selectWebElement(tmacTransferListNameTextBox);
        enterValueToTxtField(tmacTransferListNameTextBox,tmacTransferListDetails.getUpdateName());
        selectWebElement(tmacTransferListAgentExtnsnTextBox);
        enterValueToTxtField(tmacTransferListAgentExtnsnTextBox1,tmacTransferListDetails.getAgentExtension());
        selectWebElement(typeDropdown);
        selectDropdownFromVisibleText(typeListBox,tmacTransferListDetails.getType());
        enterValueToTxtField(editModifyReasonTextBox,tmacTransferListDetails.getModifyReason());
        selectWebElement(SaveButton);
    }
    public void deleteTmacConsultTransferList(TmacTransferListDetails tmacTransferListDetails) {
        searchTmacTransferRecord(tmacTransferListDetails.getName(),"Name",0);
        selectWebElement(deleteButton);
        enterValueToTxtField(deleteReasonTextBox,tmacTransferListDetails.getDeleteReason());
        selectWebElement(deleteYesBtn);
    }

    public void addNewTmacBlindTransferList(TmacTransferListDetails tmacTransferListDetails) {
        selectWebElement(addNewTmacBlindTransferListBtn);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(skillNameListBox);
        selectDropdownFromVisibleText(skillNameListElements,tmacTransferListDetails.getSkillName());
        selectWebElement(skillIdVdnList.get(1));
        enterValueToTxtField(vdnTextBox,tmacTransferListDetails.getVdn());
        selectWebElement(SaveButton);
    }
    public void editTmacBlindTransferList(TmacTransferListDetails tmacTransferListDetails) {
       searchTmacTransferRecord(tmacTransferListDetails.getSkillId(),"Skill Id",1);
      selectWebElement(editButton1);
        selectWebElement(skillNameListBox);
        selectDropdownFromVisibleText(skillNameListElements,tmacTransferListDetails.getSkillNameUpdate());
        selectWebElement(skillIdVdnList.get(1));
        enterValueToTxtField(vdnTextBox,tmacTransferListDetails.getVdn());
        enterValueToTxtField(editModifyReasonTextBox,tmacTransferListDetails.getModifyReason());
        selectWebElement(SaveButton);
    }

    public void deleteTmacBlindTransferList(TmacTransferListDetails tmacTransferListDetails) {
        searchTmacTransferRecord(tmacTransferListDetails.getSkillId(),"Skill Id",1);
        selectWebElement(deleteButton1);
        enterValueToTxtField(deleteReasonTextBox,tmacTransferListDetails.getDeleteReason());
        selectWebElement(deleteYesBtn);
    }

    public boolean verifyNewRecordCreated(){
        //waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Created Successfully"))
        {return true;}else{return false;}
    }
    public boolean verifyRecordUpdated(){
       // waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Updated Successfully"))
        {return true;}else{return false;}
    }

    public boolean verifyRecordDeleted(){
        //waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Deleted Successfully")){
            return true;
        }else{return false;}
    }


}
