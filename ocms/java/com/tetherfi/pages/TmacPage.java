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

    
    @FindBy(css="a[href$='/WorkcodeList/Index']")
    private WebElement workCodeList;

    public boolean isTMACPageDisplayed() {
        boolean status=false;
        waitForLoad(driver);
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
}
