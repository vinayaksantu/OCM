package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OCMHomePage extends BasePage {

    public OCMHomePage(WebDriver driver){super(driver);}

    @FindBy(id="OCMli")
    private WebElement ocmTab;

    @FindBy(css="a[href$='/RoleManagement/Index'] div")
    private WebElement roleBasedAccessManagement;
    
    @FindBy(xpath="//h5[text()='Opaque Data']")
    private WebElement opaqueData;

    @FindBy(css="a[href$='/UserManagement/Index'] div")
    private WebElement userManagement;

    @FindBy(css="a[href$='/ReportDownload/Index'] div")
    private WebElement reportDownloads;

    @FindBy(css="a[href$='/UserRoleMapping/Index'] div")
    private WebElement userRoleMapping;

    @FindBy(css="a[href$='/WebConfiguration/Index'] div")
    private WebElement webConfiguration;

    @FindBy(css="a[href$='/LogfileData/Index'] div")
    private WebElement logfileDownloader;

    @FindBy(id="adminLoadingAccess")
    private WebElement loadingModulesImg;

    @FindBy(css=".spiner-example")
    private WebElement loadingImg;

    @FindBy(css="#Home div")
    private List<WebElement> ocmHomePageList;

    @FindBy(css="#navTabs li")
    private List<WebElement> navTabs;

    @FindBy(css="a[href$='/CMDataLink/Index'] div")
    private WebElement cmDataSync;

    @FindBy(css="a[href$='/AgentSkillAssignment/Index'] div")
    private WebElement agentSkillAssignment;
	
	@FindBy(css="a[href$='/SkillConfiguration/Index'] div")
    private WebElement skillConfiguration;

    @FindBy(css="a[href$='/QueryBrowser/Index'] div")
    private WebElement queryBrowser;

    @FindBy(css="a[href$='/SMSResponseTemplate/Index'] div")
    private WebElement smsResponseTemplate;

    @FindBy(css="a[href$='/ApplicationAccessControl/Index'] div")
    private WebElement applicationAccessControl;
    
    @FindBy(css="a[href$='/CepCodeMapping/Index'] div")
    private WebElement cepEventMapping;
    
    @FindBy(css="a[href$='/ExportScheduler/Index'] div")
    private WebElement exportScheduler;
    
    @FindBy(css="#adminInvalidAccess h2")
    private WebElement noAccess;
    
    // @FindBy(xpath="//a[@onclick='setLastTabClickedName(Ivr)']")
    // private WebElement IVR;

    public boolean isOCMHomePageIsDisplayed() {
        waitForLoad(driver);
        if(isElementExist(loadingModulesImg)){
            System.out.println("loading page displayed");
			waitForJqueryLoad(driver);
        }else{
            System.out.println("loading page not displayed");
        }
        waitUntilWebElementListIsVisible(navTabs);
        return ocmTab.isEnabled();
    }

    public void navigateToRoleBasedAccessManagementPage(){
        selectWebElement(roleBasedAccessManagement);
    }
    public void navigateToUserRoleMappingPage(){
       selectWebElement(userRoleMapping);
    }
    public void navigateToUserManagementPage(){
        selectWebElement(userManagement);
    }
    public void navigateToWebConfigurationPage(){selectWebElement(webConfiguration);}
    public void navigateToLogfileDownloader(){selectWebElement(logfileDownloader);}
    public void navigateToCmDataSyncPage(){ selectWebElement(cmDataSync); }
    public void navigateToAgentSkillAssignmentPage(){selectWebElement(agentSkillAssignment);}
	public void navigateToSkillConfigurationPage(){selectWebElement(skillConfiguration);}
    public void navigateToQueryBrowserPage(){selectWebElement(queryBrowser);}
    public void navigateToSMSResponseTemplatePage(){selectWebElement(smsResponseTemplate);}
    public void navigateToApplicationAccessControlPage(){selectWebElement(applicationAccessControl);}
    public void navigateToTab(String tabname){
        waitUntilWebElementListIsVisible(navTabs);
        waitUntilWebElementListIsClickable(navTabs);
        for(WebElement ele: navTabs){
            if(ele.getText().equalsIgnoreCase(tabname)){selectWebElement(ele);break;}
        }
    }

	public void navigateToOpaqueDataPage() 
	{
		selectWebElement(opaqueData);
	}

	public boolean VerifyNoModulesAvailable() {
		if(noAccess.getText().equals("You do not have access to any of the resources"))
			return true;
		else
			return false;
	}

	public void navigateToCepEventMappingPage() {
		selectWebElement(cepEventMapping);
	}

	public void navigateToExportSchedulerPage() {
		selectWebElement(exportScheduler);
	}


}
