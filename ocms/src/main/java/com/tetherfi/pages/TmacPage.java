package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TmacPage extends BasePage {

    public TmacPage(WebDriver driver){super(driver);}

    @FindBy(css="a[href$='/TeamManagement/Index']")
    private WebElement agentTeamManagement;
    @FindBy(css="a[href$='/AgentBroadcastMessage/Index']")
    private WebElement tmacBroadcastMsg;

    @FindBy(css="#navTabs li")
    private List<WebElement> navTabs;

    @FindBy(css="a[href$='/AgentSetting/Index']")
    private WebElement agentSettings;

    @FindBy(css="a[href$='/TmacTransferList/Index']")
    private WebElement tmacTransferList;

    @FindBy(css="a[href$='/WaitTimeColorConfig/Index']")
    private WebElement waitTimeColorConfig;
    
    @FindBy(css="a[href$='/AgentAuxCodes/Index']")
    private WebElement tmacAuxCodes;
    
    @FindBy(css="a[href$='/WorkcodeList/Index']")
    private WebElement workCodeList;
    
    @FindBy(css="a[href$='/SlotScheduler/Index']")
    private WebElement slotScheduler;
    
    @FindBy(css="a[href$='/Attributes/Index']")
    private WebElement attributes;
    
    @FindBy(css="a[href$='/AttributeAssignment/Index']")
    private WebElement attributeAssignment;
    
    @FindBy(css="a[href$='/AgentSkillAssignment/Index']")
    private WebElement agentSkillAssignment;
    
    @FindBy(css="a[href$='/SurveyRuleConfiguration/Index']")
    private WebElement surveyRuleConfiguration;
    
    @FindBy(css="a[href$='/SkillTemplate/Index']")
    private WebElement skillTemplate;
    
	@FindBy(css="a[href$='/SkillConfiguration/Index'] div")
    private WebElement skillConfiguration;

	@FindBy(css="a[href$='/AgentTemplate/Index']")
    private WebElement agentTemplate;
	
	@FindBy(css="a[href$='/AgentSkillTemplateAssignment/Index']")
    private WebElement agentSkillScheduler;


	public void navigateToAgentTemplatePage() {
		 waitUntilWebElementIsClickable(agentTemplate);
	     selectWebElement(agentTemplate);
	}
    public boolean isTMACPageDisplayed() {
        boolean status=false;
        //waitForLoad(driver);
        //waitForJqueryLoad(driver);
        for(WebElement ele:navTabs){
            if(ele.getText().equalsIgnoreCase("TMAC")){
                if(ele.isEnabled()){
                    status=true;
                }
            }
        }
        return status;
    }
    

    
    public void navigateToAgentSettingsPage(){
        selectWebElement(agentSettings);
    }

    public void navigateToAgentTeamManagementPage(){
        waitUntilWebElementIsClickable(agentTeamManagement);
        selectWebElement(agentTeamManagement);
    }
    public void navigateToTmacBroadcastMsgPage(){
        waitUntilWebElementIsClickable(tmacBroadcastMsg);
        selectWebElement(tmacBroadcastMsg);
    }
    public void navigateToTmacTranferListPage(){
        waitUntilWebElementIsClickable(tmacTransferList);
        selectWebElement(tmacTransferList);
    }
    public void navigateToWaitTimeColorConfigPage(){
        waitUntilWebElementIsClickable(waitTimeColorConfig);
        selectWebElement(waitTimeColorConfig);
    }
	public void navigateToWorkCodeListPage() {
		waitUntilWebElementIsClickable(workCodeList);
        selectWebElement(workCodeList);		
	}
	
	public void navigateToTmacAuxCodesPage(){
        waitUntilWebElementIsClickable(tmacAuxCodes);
        selectWebElement(tmacAuxCodes);
    }
	public void navigateToSlotSchedulerPage() {
		 waitUntilWebElementIsClickable(slotScheduler);
	     selectWebElement(slotScheduler);
	}
	
	public void navigateToAttributesPage() {
		 waitUntilWebElementIsClickable(attributes);
	     selectWebElement(attributes);
	}
	
	public void navigateToAttributeAssignmentPage() {
		 waitUntilWebElementIsClickable(attributeAssignment);
	     selectWebElement(attributeAssignment);
	}
	
	public void navigateToAgentSkillAssignmentPage() {
		 waitUntilWebElementIsClickable(agentSkillAssignment);
	     selectWebElement(agentSkillAssignment);
	}
	
	public void navigateToSurveyRuleConfigurationPage() {
		 waitUntilWebElementIsClickable(surveyRuleConfiguration);
	     selectWebElement(surveyRuleConfiguration);
	}
	
	public void navigateToSkillTemplatePage() {
		 waitUntilWebElementIsClickable(skillTemplate);
	     selectWebElement(skillTemplate);
	}
	
	public void navigateToSkillConfigurationPage(){selectWebElement(skillConfiguration);}
	
	public void navigateToAgentSkillSchedulerPage() {
		 waitUntilWebElementIsClickable(agentSkillScheduler);
	     selectWebElement(agentSkillScheduler);
	}
	


}
