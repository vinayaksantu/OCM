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
    
    @FindBy(css="a[href$='/IvrBranchManagement/Index'] div")
    private WebElement branchManagement;
    
    @FindBy(css="a[href$='/CallbackEnableDisable/Index'] div")
    private WebElement callbackEnableDisable;
    
    @FindBy(css="a[href$='/IvrCallBackAnnouncement/Index'] div")
    private WebElement callBackAnnouncement;
    
    @FindBy(css="a[href$='/IvrCallbackManagement/Index'] div")
    private WebElement callbackManagement;
    
    @FindBy(css="a[href$='/IvrFaxFormName/Index'] div")
    private WebElement faxForms;
    
    @FindBy(css="a[href$='/IvrFaxGroup/Index'] div")
    private WebElement faxGroup;
    
    @FindBy(css="a[href$='/IvrHostmap/Index'] div")
    private WebElement hostValueMapping;
    
    @FindBy(css="a[href$='/EmailCallback/Index'] div")
    private WebElement emailCallback;
    
    @FindBy(css="a[href$='/IVRFeeWaiver/Index'] div")
    private WebElement feeWaiver;
    
    @FindBy(css="a[href$='/IvrHolidayList/Index'] div")
    private WebElement holidayList;
    
    @FindBy(css="a[href$='/FaxApplicationForm/Index'] div")
    private WebElement faxApplicationForm;
    
    @FindBy(css="a[href$='/VIPListManagement/Index'] div")
    private WebElement vipListManagement;
    
    @FindBy(css="a[href$='/VbEnrollmentFlag/Index'] div")
    private WebElement vbEnrollmentFlag;
    
    @FindBy(css="a[href$='/IvrProductPromotions/Index'] div")
    private WebElement productPromotions;
    
    @FindBy(css="a[href$='/IvrIntentMapping/Index'] div")
    private WebElement intentMapping;
    
    @FindBy(css="a[href$='/IvrIntentMapping/Index'] div")
    private WebElement speechModule;
    
    @FindBy(css="a[href$='/CsoSurvey/Index'] div")
    private WebElement csoSurveyQA;
    
    @FindBy(css="a[href$='/OrderTakeConfiguration/Index'] div")
    private WebElement orderTakeConfiguration;
    
    @FindBy(css="a[href$='/IVRSurvey/Index'] div")
    private WebElement ivrSurveyQA;
    
    @FindBy(css="a[href$='/IvrMenuDescription/Index'] div")
    private WebElement menuDescMapping;
    
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
	public void navigateToFaxFormsPage() {
		waitUntilWebElementIsClickable(faxForms);
		selectWebElement(faxForms);
		
	}
	public void navigateToFaxGroupPage() {
		waitUntilWebElementIsClickable(faxGroup);
		selectWebElement(faxGroup);
		
	}
	public void navigateToHolidayListPage() {
		waitUntilWebElementIsClickable(holidayList);
		selectWebElement(holidayList);		
	}
	public void navigateToVipListManagementPage() {
		scrollToElement(vipListManagement);
		waitUntilWebElementIsClickable(vipListManagement);
		selectWebElement(vipListManagement);		
	}
	public void navigateToVbEnrollmentFlagPage() {
		scrollToElement(vbEnrollmentFlag);
		waitUntilWebElementIsClickable(vbEnrollmentFlag);
		selectWebElement(vbEnrollmentFlag);			
	}
	public void navigateToProductPromotionsPage() {
		waitUntilWebElementIsClickable(productPromotions);
		selectWebElement(productPromotions);			
	}
	public void navigateToIntentMappingPage() {
		waitUntilWebElementIsClickable(intentMapping);
		selectWebElement(intentMapping);		
	}
	public void navigateToBranchManagementPage() {
		waitUntilWebElementIsClickable(branchManagement);
		selectWebElement(branchManagement);		
	}
	public void navigateToCallBackEnableDisable() {
		waitUntilWebElementIsClickable(callbackEnableDisable);
		selectWebElement(callbackEnableDisable);
	}
	public void navigateToCallbackManagementPage() {
		waitUntilWebElementIsClickable(callbackManagement);
		selectWebElement(callbackManagement);
		
	}
	public void navigateToEmailCallbackPage() {
		waitUntilWebElementIsClickable(emailCallback);
		selectWebElement(emailCallback);		
	}
	public void navigateToFaxApplicationFormPage() {
		waitUntilWebElementIsClickable(faxApplicationForm);
		selectWebElement(faxApplicationForm);		
	}
	public void navigateToFeeWaiverPage() {
		waitUntilWebElementIsClickable(feeWaiver);
		selectWebElement(feeWaiver);
	}
	public void navigateToHostValueMappingPage() {
		waitUntilWebElementIsClickable(hostValueMapping);
		selectWebElement(hostValueMapping);
	}
	public void navigateToSpeechModulePage() {
		waitUntilWebElementIsClickable(hostValueMapping);
		selectWebElement(speechModule);
	}
	public void navigateToCSOSurveyQAPage() {
		waitUntilWebElementIsClickable(csoSurveyQA);
		selectWebElement(csoSurveyQA);
	}
	public void navigateToOrderTakeConfigurationPage() {
		waitUntilWebElementIsClickable(orderTakeConfiguration);
		selectWebElement(orderTakeConfiguration);
	}
	public void navigateToIVRSurveyQAPage() {
		waitUntilWebElementIsClickable(ivrSurveyQA);
		selectWebElement(ivrSurveyQA);
	}
	public void navigateToMenuDescriptionMappingPage() {
		waitUntilWebElementIsClickable(menuDescMapping);
		selectWebElement(menuDescMapping);
	}
}
