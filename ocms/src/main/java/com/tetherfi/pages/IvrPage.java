package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class IvrPage extends BasePage {
    public IvrPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="Ivr")
    private WebElement ivrContents;

    @FindBy(css="a[href$='/IvrOperatingHours/Index']")
    private WebElement operatingHours;

    @FindBy(css="a[href$='/IvrIntroductoryMessageAnnouncement/Index'] div")
    private WebElement IntroMessageAnnouncement;

    @FindBy(css="a[href$='/IvrConfig/Index'] div")
    private WebElement ivrConfig;

    @FindBy(css="a[href$='/AdhocOptionEnhancement/Index'] div")
    private WebElement adhocOptionEnhancement;
    
    @FindBy(css="a[href$='/AdminCallback/Index'] div")
    private WebElement adminCallBack;
    
    @FindBy(css="a[href$='/AgentTransfer/Index'] div")
    private WebElement agentTransfer;
    
    @FindBy(css="a[href$='/BillingOrg/Index'] div")
    private WebElement billingOrg;
    
    @FindBy(css="a[href$='/IvrBranchDetails/Index'] div")
    private WebElement branchDetails;
    
    @FindBy(css="a[href$='/IvrCallBackAnnouncement/Index'] div")
    private WebElement callBackAnnouncement;
    
    @FindBy(css="#navTabs li")
    private List<WebElement> navTabs;

    public boolean isIVRPageDisplayed() {
        boolean status=false;
        waitForLoad(driver);
        waitUntilWebElementIsVisible(ivrConfig);
        for(WebElement ele:navTabs){if(ele.getText().equals("IVR")){if(ele.isEnabled()){status=true;break;}}}
        return status;
    }
    public void navigateToOperatingHoursPage(){
        waitUntilWebElementIsClickable(operatingHours);
        selectWebElement(operatingHours);
    }
    public void navigateToIntroMessageAnnouncementPage(){
        selectWebElement(IntroMessageAnnouncement);
    }

    public void navigateToIvrConfigPage(){
        selectWebElement(ivrConfig);
    }

    public void navigateToAdhocOptionEnhancementPage(){
    	selectWebElement(adhocOptionEnhancement);
    }
	public void navigateToAdminCallbackPage() {
		selectWebElement(adminCallBack);
		
	}
	public void navigateToAgentTransferPage() {
        waitUntilWebElementIsClickable(agentTransfer);
		selectWebElement(agentTransfer);
	}
	public void navigateToBillingOrgPage() {
		waitUntilWebElementIsClickable(billingOrg);
		selectWebElement(billingOrg);
		
	}
	public void navigateToBranchDetailsPage() {
		waitUntilWebElementIsClickable(branchDetails);
		selectWebElement(branchDetails);
		
	}
	public void navigateToCallbackAnnouncementPage() {
		waitUntilWebElementIsClickable(callBackAnnouncement);
		selectWebElement(callBackAnnouncement);
		
	}
}
