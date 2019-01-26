package com.tetherfi.pages;

import com.tetherfi.model.user.AgentSkillAssignmentDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

public class AgentSkillAssignmentPage extends BasePage {

    public AgentSkillAssignmentPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement AgentSkillAssignment;

    @FindBy(css=".k-tabstrip-items li")
    private List<WebElement> tabList;

    @FindBy(id="addedittab")
    private WebElement multiSkillTab;

    @FindBy(css = ".k-grid-CustomEdit")
    private WebElement editButton;

    @FindBy(id="popup")
    private WebElement popupContainer;

    @FindBy(css="#tabstrip_skilledit ul[class='k-tabstrip-items k-reset'] li")
    private List<WebElement> skillsTab;

    @FindBy(css="#tabstrip_skilledit ul[role='listbox'] li")
    private List<WebElement> skillsName;

    @FindBy(css="#tabstrip_skilledit div[id='tdrillgrid'] div[data-role='draggable']")
    private List<WebElement> skillsSelected;

    @FindBy(css="#tabstrip_multi ul[class='k-tabstrip-items k-reset'] li")
    private List<WebElement> multiskillsTab;

    @FindBy(css="#tabstrip_multi ul[role='listbox'] li")
    private List<WebElement> multiskillsName;

    @FindBy(css="#tabstrip_multi div[id='mdrillgrid'] div[data-role='draggable']")
    private List<WebElement> multiskillsSelected;

    @FindBy(css="div[class='swal-modal'] input")
    private WebElement skillLevel;

    @FindBy(css="div[class='swal-modal'] button")
    private WebElement okButton;

    @FindBy(css="button[onclick='saveSkills()']")
    private WebElement saveBtn;

    @FindBy(css="button[onclick='saveMultiSkills()']")
    private WebElement saveBtn1;

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

    @FindBy(css = "#1001sCloseButton .k-i-close")
    private WebElement searchRemoveFilterBtn;

    @FindBy(css = ".modal-footer .k-button")
    private WebElement searchCloseBtn;

    @FindBy(css = ".modal-footer .button-danger-theme")
    private WebElement searchClearAllBtn;

    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;

    @FindBy(css=".k-grid-content")
    private WebElement gridContent;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css = "#toast-container .toast-error")
    private List<WebElement> errorMsg;

    @FindBy(css="#progress ul[class='k-reset k-list'] li")
    private List<WebElement> agentList;

    @FindBy(css="#progress div[data-role='draggable']")
    private List<WebElement> agentSelected;

    public boolean isAgentSkillAssignmentPageDisplayed() {
        waitForLoad(driver);
        //waitForJqueryLoad(driver);
        return AgentSkillAssignment.isEnabled();
    }
    public void navigateToMultiSkillTab(){
        waitUntilWebElementIsVisible(multiSkillTab);
        waitUntilWebElementIsClickable(multiSkillTab);
        multiSkillTab.click();
        waitForJqueryLoad(driver);
    }
    public void searchAgentListRecord(String username) {
        selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"User Name");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,username);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }

    public void assignSkillToAgent(AgentSkillAssignmentDetails details) {
        searchAgentListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        waitUntilWebElementListIsVisible(skillsSelected);
        selectSkillName(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(saveBtn);
    }
    public void assignSkillToMultipleAgent(AgentSkillAssignmentDetails details) {
        selectAgent(details.getUsername());
        selectmultiSkillTab(details.getSkillType());
        waitUntilWebElementListIsVisible(multiskillsSelected);
        selectmultiSkillName(details.getSkillName(),details.getSkillLevel());
        clickOn(saveBtn1);
    }
    public void selectSkillTab(String skillType){
        for(WebElement ele:skillsTab){
            if(ele.getText().equalsIgnoreCase(skillType)){
                selectWebElement(ele);break;
            }
        }
    }
    public void selectmultiSkillTab(String skillType){
        for(WebElement ele:multiskillsTab){
            if(ele.getText().equalsIgnoreCase(skillType)){
                selectWebElement(ele);break;
            }
        }
    }
    public void selectSkillName(String skillName){
        for(WebElement ele:skillsName){
            if(ele.getText().equalsIgnoreCase(skillName)){
                Actions builder = new Actions(driver);
                Action dragAndDrop = builder.clickAndHold(ele).moveToElement(ele).release(skillsSelected.get(1)).build();
                dragAndDrop.perform();
                break;
            }
        }
    }
    public void selectmultiSkillName(String skillName, String skLevel){
        String[] a=skillName.split(",");
        String[] b=skLevel.split(",");
        for(String skill:a){
        for(WebElement ele:multiskillsName){
            if(ele.getText().equalsIgnoreCase(skill)){
                Actions builder = new Actions(driver);
                Action dragAndDrop = builder.clickAndHold(ele).moveToElement(ele).release(multiskillsSelected.get(1)).build();
                dragAndDrop.perform();
                break;
            }
        }
        enterValueToTxtField(skillLevel,b[Arrays.asList(a).indexOf(skill)]);
        selectWebElement(okButton);
        }
    }
    public void selectAgent(String agent){
        String[] a=agent.split(",");
        for(String agentName:a) {
            for (WebElement ele : agentList) {
                if (ele.getText().contains(agentName)) {
                    Actions builder = new Actions(driver);
                    Action dragAndDrop = builder.clickAndHold(ele).moveToElement(ele).release(agentSelected.get(1)).build();
                    dragAndDrop.perform();
                    break;
                }
            }
        }
    }
    public boolean verifyRecordUpdated(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        waitUntilWebElementIsVisible(successmsg);
        if(successmsg.getText().contains("Skills Assigned Successfully"))
        {return true;}else{return false;}
    }
}
