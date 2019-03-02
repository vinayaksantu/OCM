package com.tetherfi.test;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.model.user.UserDetails;
import com.tetherfi.pages.AdhocOptionEnhancementPage;
import com.tetherfi.pages.AdminCallbackPage;
import com.tetherfi.pages.AgentSettingsNewDesignPage;
import com.tetherfi.pages.AgentSettingsPage;
import com.tetherfi.pages.AgentSkillAssignmentPage;
import com.tetherfi.pages.AgentTeamManagementPage;
import com.tetherfi.pages.AgentTransferPage;
import com.tetherfi.pages.ApplicationAccessControlPage;
import com.tetherfi.pages.BillingOrgPage;
import com.tetherfi.pages.BranchDetailsPage;
import com.tetherfi.pages.BranchManagementPage;
import com.tetherfi.pages.BulkUploadModulePage;
import com.tetherfi.pages.CMDataSyncPage;
import com.tetherfi.pages.CSOSurveyQAPage;
import com.tetherfi.pages.CallBackEnableDisablePage;
import com.tetherfi.pages.CallBackManagementPage;
import com.tetherfi.pages.CallbackAnnouncementPage;
import com.tetherfi.pages.CepEventMappingPage;
import com.tetherfi.pages.ChatConfigurationsPage;
import com.tetherfi.pages.ChatIntentSkillMappingPage;
import com.tetherfi.pages.ChatMenuDescriptionPage;
import com.tetherfi.pages.ChatPage;
import com.tetherfi.pages.ChatTemplatesPage;
import com.tetherfi.pages.DynamicReportDesignerPage;
import com.tetherfi.pages.EmailCallbackPage;
import com.tetherfi.pages.ExportSchedulerPage;
import com.tetherfi.pages.FaxApplicationFormPage;
import com.tetherfi.pages.FaxAutoACKConfigurationPage;
import com.tetherfi.pages.FaxFormsPage;
import com.tetherfi.pages.FaxGroupPage;
import com.tetherfi.pages.FaxLineConfigPage;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.FaxRoutingConfigurationPage;
import com.tetherfi.pages.FaxSendersPage;
import com.tetherfi.pages.FaxTemplatePage;
import com.tetherfi.pages.FeeWaiverPage;
import com.tetherfi.pages.HolidayListPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.HostValueMappingPage;
import com.tetherfi.pages.IVRSurveyQAPage;
import com.tetherfi.pages.IWRoleBasedAccessMatrixPage;
import com.tetherfi.pages.IntentMappingPage;
import com.tetherfi.pages.IntroMessageAnnouncementPage;
import com.tetherfi.pages.IvrCallbackManagementPage;
import com.tetherfi.pages.IvrConfigPage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.LogfileDownloaderPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.MenuDescriptionMappingPage;
import com.tetherfi.pages.ModuleExitNodeMappingPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OpaqueDataPage;
import com.tetherfi.pages.OperatingHoursPage;
import com.tetherfi.pages.OrderTakeConfigurationPage;
import com.tetherfi.pages.ProductPromotionsPage;
import com.tetherfi.pages.ReportDesignerPage;
import com.tetherfi.pages.ReportDownloadsPage;
import com.tetherfi.pages.ReportSchedulerPage;
import com.tetherfi.pages.RoleBasedAccessManagementPage;
import com.tetherfi.pages.SMSAlertPage;
import com.tetherfi.pages.SMSResponseTemplatePage;
import com.tetherfi.pages.SendFaxPage;
import com.tetherfi.pages.SkillConfigurationPage;
import com.tetherfi.pages.SmsPage;
import com.tetherfi.pages.SpeechModulePage;
import com.tetherfi.pages.SplitFaxPage;
import com.tetherfi.pages.TPRedemptionPage;
import com.tetherfi.pages.TextSynonymsPage;
import com.tetherfi.pages.TmacBroadCastMsgPage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.pages.TmacTransferListPage;
import com.tetherfi.pages.UserManagementPage;
import com.tetherfi.pages.UserRoleMappingPage;
import com.tetherfi.pages.VbEnrollmentFlagPage;
import com.tetherfi.pages.VipListManagementPage;
import com.tetherfi.pages.WaitTimeColorConfigPage;
import com.tetherfi.pages.WebConfigurationPage;
import com.tetherfi.pages.WorkCodeListPage;
import com.tetherfi.pages.WorkFlowDesignerPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class UserManagementE2ETest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
	
	@BeforeMethod
	public void OCMPage() throws Exception {
		HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToUserManagementPage();
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.isUserManagementPageDisplayed(),"User management assertion failed");
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        userManagementPage.searchUserManagementRecord(userDetails.getUserId());
        Assert.assertTrue(userManagementPage.isPageBasedUserAccessPageDisplayed());
        screenshot.captureScreen(driver, "PageBasedUSerAccessDisplayed", "UserManagementE2ETest");
        userManagementPage.clearAccess();
	}
	
	//@Test
	public void VerifyNoModulesAvailable() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		Assert.assertTrue(userManagementPage.isPageBasedUserAccessPageDisplayed());
		userManagementPage.navigateToOtherAppsTab();
		userManagementPage.clearOtherAppsAccess();
		driver.close();
		try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
                Thread.sleep(5000);
            }
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
        }
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
       Assert.assertTrue(ocmHomePage.VerifyNoModulesAvailable(), "No Modules available assertion failed");
       screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");  
       homePage.userLogout();
       driver.close();
	}
	
	//@Test
	public void VerifyViewAccessOfAgentTeamManagement() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideAccess("Agent Team Management","View");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
                Thread.sleep(5000);
            }
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
        }
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
        tmacPage.navigateToAgentTeamManagementPage();
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.isAgentTeamManagementPageDisplayed(),"Agent Team Management Page assertion failed");
        Assert.assertFalse(agentTeamManagementPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(agentTeamManagementPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(agentTeamManagementPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertFalse(agentTeamManagementPage.isExportBtnDisplayed(), "Export button assertion failed");
        screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");  
        homePage.userLogout();
        driver.close();
	}
	
	//@Test
	public void VerifyAddAccessOfAgentTeamMgmt()throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Agent Team Management", "Add");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
			}
		}catch (Exception e){
        PageFactory.reset();
        driver.close();
        e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		tmacPage.navigateToAgentTeamManagementPage();
		AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
		Assert.assertTrue(agentTeamManagementPage.isAgentTeamManagementPageDisplayed(),"Agent Team Management Page assertion failed");
		Assert.assertTrue(agentTeamManagementPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(agentTeamManagementPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(agentTeamManagementPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertFalse(agentTeamManagementPage.isExportBtnDisplayed(), "Export button assertion failed");
		screenshot.captureScreen(driver, "VerifyAddAccess", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	//@Test
	public void VerifyEditAccessOfAgentTeamManagement() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Agent Team Management", "Edit");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
			}
		}catch (Exception e){
        PageFactory.reset();
        driver.close();
        e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		tmacPage.navigateToAgentTeamManagementPage();
		AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
		Assert.assertTrue(agentTeamManagementPage.isAgentTeamManagementPageDisplayed(),"Agent Team Management Page assertion failed");
		Assert.assertFalse(agentTeamManagementPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertTrue(agentTeamManagementPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(agentTeamManagementPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertFalse(agentTeamManagementPage.isExportBtnDisplayed(), "Export button assertion failed");
		screenshot.captureScreen(driver, "VerifyEditAccess", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	//@Test
	public void VerifyDeleteAccessOfAgentTeamManagement() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Agent Team Management", "Delete");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
			}
		}catch (Exception e){
        PageFactory.reset();
        driver.close();
        e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		tmacPage.navigateToAgentTeamManagementPage();
		AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
		Assert.assertTrue(agentTeamManagementPage.isAgentTeamManagementPageDisplayed(),"Agent Team Management Page assertion failed");
		Assert.assertFalse(agentTeamManagementPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(agentTeamManagementPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertTrue(agentTeamManagementPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertFalse(agentTeamManagementPage.isExportBtnDisplayed(), "Export button assertion failed");
		screenshot.captureScreen(driver, "VerifyDeleteAccess", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	//@Test
	public void VerifyExportAccessOfagentTeamManagement() throws Exception {
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Agent Team Management", "Export");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\UserManagemnetExcelFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
			}
		}catch (Exception e){
        PageFactory.reset();
        driver.close();
        e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		tmacPage.navigateToAgentTeamManagementPage();
		AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
		Assert.assertTrue(agentTeamManagementPage.isAgentTeamManagementPageDisplayed(),"Agent Team Management Page assertion failed");
		Assert.assertFalse(agentTeamManagementPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(agentTeamManagementPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(agentTeamManagementPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertTrue(agentTeamManagementPage.isExportBtnDisplayed(), "Export button assertion failed");
		screenshot.captureScreen(driver, "VerifyExportAccess", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	//@Test
	public void VerifyAccessOfTMACBroadcastMsg() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideAccess("TMAC Broadcast Message", "View");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
                Thread.sleep(5000);
            }
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
        }
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
        tmacPage.navigateToTmacBroadcastMsgPage();
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
       	Assert.assertTrue(tmacBroadCastMsgPage.isTmacBroadcastMsgPageDisplayed(), "TMACBroadcastMsg page assertion failed");
       	Assert.assertFalse(tmacBroadCastMsgPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(tmacBroadCastMsgPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(tmacBroadCastMsgPage.isExportBtnDisplayed(), "Export button assertion failed");
        screenshot.captureScreen(driver, "VerifyAccessOfTMACBroadcastMsg", "UserManagementE2ETest");  
        homePage.userLogout();
        driver.close();
	}
	
	//@Test
	public void VerifyAddAccessOfTMACBroadcastMsg()throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("TMAC Broadcast Message", "Add");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
			}
		}catch (Exception e){
        PageFactory.reset();
        driver.close();
        e.printStackTrace();
		}
		Thread.sleep(2000);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		tmacPage.navigateToTmacBroadcastMsgPage();
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
       	Assert.assertTrue(tmacBroadCastMsgPage.isTmacBroadcastMsgPageDisplayed(), "TMACBroadcastMsg page assertion failed");
       	Assert.assertTrue(tmacBroadCastMsgPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(tmacBroadCastMsgPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(tmacBroadCastMsgPage.isExportBtnDisplayed(), "Export button assertion failed");
		screenshot.captureScreen(driver, "VerifyAddAccessOfTMACBroadcastMsg", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	//@Test
	public void VerifyEditAccessOfTMACBroadcastMsg() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("TMAC Broadcast Message", "Edit");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
			}
		}catch (Exception e){
        PageFactory.reset();
        driver.close();
        e.printStackTrace();
		}
		Thread.sleep(2000);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		tmacPage.navigateToTmacBroadcastMsgPage();
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
       	Assert.assertTrue(tmacBroadCastMsgPage.isTmacBroadcastMsgPageDisplayed(), "TMACBroadcastMsg page assertion failed");
       	Assert.assertFalse(tmacBroadCastMsgPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertTrue(tmacBroadCastMsgPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(tmacBroadCastMsgPage.isExportBtnDisplayed(), "Export button assertion failed");
		screenshot.captureScreen(driver, "VerifyEditAccessOfTMACBroadcastMsg", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	//@Test
		public void VerifyExportAccessOfTMACBroadcastMsg() throws Exception {
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("TMAC Broadcast Message", "Export");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\UserManagemnetExcelFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
			}
		}catch (Exception e){
        PageFactory.reset();
        driver.close();
        e.printStackTrace();
		}
		Thread.sleep(2000);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		tmacPage.navigateToTmacBroadcastMsgPage();
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
       	Assert.assertTrue(tmacBroadCastMsgPage.isTmacBroadcastMsgPageDisplayed(), "TMACBroadcastMsg page assertion failed");
       	Assert.assertFalse(tmacBroadCastMsgPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(tmacBroadCastMsgPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertTrue(tmacBroadCastMsgPage.isExportBtnDisplayed(), "Export button assertion failed");
		screenshot.captureScreen(driver, "VerifyExportAccessOfTMACBroadcastMsg", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	//@Test
	public void VerifyAccessOfWaitTimeColorConfig() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideAccess("Wait Time Color Config", "View");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
                Thread.sleep(5000);
            }
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
        }
		Thread.sleep(2000);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
        tmacPage.navigateToWaitTimeColorConfigPage();
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        Assert.assertTrue(waitTimeColorConfigPage.isWaitTimeColorConfigPageDisplayed(),"Wait time color config page assertion failed");
        Assert.assertFalse(waitTimeColorConfigPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(waitTimeColorConfigPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(waitTimeColorConfigPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertFalse(waitTimeColorConfigPage.isExportBtnDisplayed(), "Export button assertion failed");
        screenshot.captureScreen(driver, "VerifyAccessOfWaitTimeColorConfig", "UserManagementE2ETest");  
        homePage.userLogout();
        driver.close();
	}
	//@Test(dependsOnMethods = {"VerifyAccessOfWaitTimeColorConfig"})
	public void VerifyAddAccessOfWaitTimeColorConfig()throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Wait Time Color Config", "Add");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
			}
		}catch (Exception e){
        PageFactory.reset();
        driver.close();
        e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		tmacPage.navigateToWaitTimeColorConfigPage();
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        Assert.assertTrue(waitTimeColorConfigPage.isWaitTimeColorConfigPageDisplayed(),"Wait time color config page assertion failed");
        Assert.assertTrue(waitTimeColorConfigPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(waitTimeColorConfigPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(waitTimeColorConfigPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertFalse(waitTimeColorConfigPage.isExportBtnDisplayed(), "Export button assertion failed");
		screenshot.captureScreen(driver, "VerifyAddAccessOfWaitTimeColorConfig()", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	//@Test(dependsOnMethods = {"VerifyAddAccessOfWaitTimeColorConfig"})
	public void VerifyEditAccessOfWaitTimeColorConfig() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Wait Time Color Config", "Edit");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
			}
		}catch (Exception e){
        PageFactory.reset();
        driver.close();
        e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		tmacPage.navigateToWaitTimeColorConfigPage();
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        Assert.assertTrue(waitTimeColorConfigPage.isWaitTimeColorConfigPageDisplayed(),"Wait time color config page assertion failed");
        Assert.assertFalse(waitTimeColorConfigPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertTrue(waitTimeColorConfigPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(waitTimeColorConfigPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertFalse(waitTimeColorConfigPage.isExportBtnDisplayed(), "Export button assertion failed");
		screenshot.captureScreen(driver, "VerifyEditAccessOfWaitTimeColorConfig", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	//@Test(dependsOnMethods = {"VerifyEditAccessOfWaitTimeColorConfig"})
	public void VerifyDeleteAccessOfWaitTimeColorConfig() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Wait Time Color Config", "Delete");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
			}
		}catch (Exception e){
        PageFactory.reset();
        driver.close();
        e.printStackTrace();
		}
		Thread.sleep(2000);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		tmacPage.navigateToWaitTimeColorConfigPage();
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        Assert.assertTrue(waitTimeColorConfigPage.isWaitTimeColorConfigPageDisplayed(),"Wait time color config page assertion failed");
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Assert.assertFalse(waitTimeColorConfigPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(waitTimeColorConfigPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertTrue(waitTimeColorConfigPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertFalse(waitTimeColorConfigPage.isExportBtnDisplayed(), "Export button assertion failed");
		screenshot.captureScreen(driver, "VerifyDeleteAccessOfWaitTimeColorConfig", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	//@Test(dependsOnMethods = {"VerifyDeleteAccessOfWaitTimeColorConfig"})
	public void VerifyExportAccessOfWaitTimeColorConfig() throws Exception {
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Wait Time Color Config", "Export");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\UserManagemnetExcelFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
			}
		}catch (Exception e){
        PageFactory.reset();
        driver.close();
        e.printStackTrace();
		}
		Thread.sleep(2000);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		tmacPage.navigateToWaitTimeColorConfigPage();
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        Assert.assertFalse(waitTimeColorConfigPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(waitTimeColorConfigPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(waitTimeColorConfigPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertTrue(waitTimeColorConfigPage.isExportBtnDisplayed(), "Export button assertion failed");
		screenshot.captureScreen(driver, "VerifyExportAccessOfWaitTimeColorConfig", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	//@Test
	public void VerifyAccessOfWorkcodeList() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideAccess("Workcode List", "View");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
                Thread.sleep(5000);
            }
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
        }
		Thread.sleep(2000);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
        tmacPage.navigateToWorkCodeListPage();
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.isWorkCodeListPageDisplayed(), "WorkCodeList page assertion failed");
        Assert.assertFalse(workCodeListPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(workCodeListPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(workCodeListPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertFalse(workCodeListPage.isExportBtnDisplayed(), "Export button assertion failed");
        screenshot.captureScreen(driver, "VerifyAccessOfWorkcodeList", "UserManagementE2ETest");  
        homePage.userLogout();
        driver.close();
	}
	//@Test(dependsOnMethods = {"VerifyAccessOfWorkcodeList"})
	public void VerifyAddAccessOfWorkcodeList()throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Workcode List", "Add");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
			}
		}catch (Exception e){
        PageFactory.reset();
        driver.close();
        e.printStackTrace();
		}
		Thread.sleep(2000);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		tmacPage.navigateToWorkCodeListPage();
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.isWorkCodeListPageDisplayed(), "WorkCodeList page assertion failed");
        Assert.assertTrue(workCodeListPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(workCodeListPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(workCodeListPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertFalse(workCodeListPage.isExportBtnDisplayed(), "Export button assertion failed");
		screenshot.captureScreen(driver, "VerifyAddAccessOfWorkcodeList", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	//@Test(dependsOnMethods = {"VerifyAddAccessOfWorkcodeList"})
	public void VerifyEditAccessOfWorkcodeList() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Workcode List", "Edit");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
			}
		}catch (Exception e){
        PageFactory.reset();
        driver.close();
        e.printStackTrace();
		}
		Thread.sleep(2000);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		tmacPage.navigateToWorkCodeListPage();
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.isWorkCodeListPageDisplayed(), "WorkCodeList page assertion failed");
        Assert.assertFalse(workCodeListPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertTrue(workCodeListPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(workCodeListPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertFalse(workCodeListPage.isExportBtnDisplayed(), "Export button assertion failed");
		screenshot.captureScreen(driver, "VerifyEditAccessOfWorkcodeList", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	//@Test(dependsOnMethods = {"VerifyEditAccessOfWorkcodeList"})
	public void VerifyDeleteAccessOfWorkcodeList() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Workcode List", "Delete");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
			}
		}catch (Exception e){
        PageFactory.reset();
        driver.close();
        e.printStackTrace();
		}
		Thread.sleep(2000);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		tmacPage.navigateToWorkCodeListPage();
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.isWorkCodeListPageDisplayed(), "WorkCodeList page assertion failed");
        Assert.assertFalse(workCodeListPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(workCodeListPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertTrue(workCodeListPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertFalse(workCodeListPage.isExportBtnDisplayed(), "Export button assertion failed");
		screenshot.captureScreen(driver, "VerifyDeleteAccessOfWorkcodeList", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	//@Test
	public void VerifyExportAccessOfWorkcodeList() throws Exception {
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Workcode List", "Export");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\UserManagemnetExcelFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
			}
		}catch (Exception e){
        PageFactory.reset();
        driver.close();
        e.printStackTrace();
		}
		Thread.sleep(2000);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		tmacPage.navigateToWorkCodeListPage();
		WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
	    Assert.assertTrue(workCodeListPage.isWorkCodeListPageDisplayed(), "WorkCodeList page assertion failed");
	    Assert.assertFalse(workCodeListPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(workCodeListPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(workCodeListPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertTrue(workCodeListPage.isExportBtnDisplayed(), "Export button assertion failed");
		screenshot.captureScreen(driver, "VerifyExportAccessOfWorkcodeList()", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	//@Test
	public void VerifyViewAccessOfAdHocOptionEnhancement() throws Exception
	{
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideAccess("Adhoc Option Enhancement", "View");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
                Thread.sleep(5000);
            }
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
        }
		Thread.sleep(2000);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
        ivrPage.navigateToAdhocOptionEnhancementPage();
        AdhocOptionEnhancementPage adhocOptEnhPage=PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptEnhPage.isAdhocOptionEnhancementPageDisplayed(),"Adhoc Option Enhancement Page assertion failed");
        Assert.assertFalse(adhocOptEnhPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(adhocOptEnhPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(adhocOptEnhPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertFalse(adhocOptEnhPage.isExportBtnDisplayed(), "Export button assertion failed");
        screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");  
        homePage.userLogout();
        driver.close();
	}
	
	//@Test
	public void VerifyAddAccessOfAdHocOptionEnhancement() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideAccess("Adhoc Option Enhancement", "Add");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
                Thread.sleep(5000);
            }
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
        }
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
       	ivrPage.navigateToAdhocOptionEnhancementPage();
        AdhocOptionEnhancementPage adhocOptEnhPage=PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptEnhPage.isAdhocOptionEnhancementPageDisplayed(),"Adhoc Option Enhancement Page assertion failed");
        Assert.assertTrue(adhocOptEnhPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertFalse(adhocOptEnhPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(adhocOptEnhPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertFalse(adhocOptEnhPage.isExportBtnDisplayed(), "Export button assertion failed");
        screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");  
        homePage.userLogout();
        driver.close();
	}
	
	//@Test
	public void VerifyEditAccessOfAdHocOptionEnhancement() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideAccess("Adhoc Option Enhancement", "Edit");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
                Thread.sleep(5000);
            }
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
        }
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
        ivrPage.navigateToAdhocOptionEnhancementPage();
        AdhocOptionEnhancementPage adhocOptEnhPage=PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptEnhPage.isAdhocOptionEnhancementPageDisplayed(),"Adhoc Option Enhancement Page assertion failed");
        Assert.assertFalse(adhocOptEnhPage.isAddBtnDisplayed(), "Add button assertion failed");
        Assert.assertTrue(adhocOptEnhPage.isEditBtnDisplayed(), "Edit button assertion failed");
        Assert.assertFalse(adhocOptEnhPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
        Assert.assertFalse(adhocOptEnhPage.isExportBtnDisplayed(), "Export button assertion failed");
        screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");  
        homePage.userLogout();
        driver.close();
	}
	
	//@Test
	public void VerifyDeleteAccessOfAdHocOptionEnhancement() throws Exception
	{            
	   UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	        userManagementPage.ProvideAccess("Adhoc Option Enhancement", "Delete");
			Thread.sleep(2000);
	        driver.close();
	        try {
	            PageFactory.reset();
	            BrowserFactory browserFactory = new BrowserFactory();
	            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	            if(map.get("LoginType").equals("Custom")){
	                LoginPage loginPage=new LoginPage(driver);
	                loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                Thread.sleep(5000);
	            }
	        }catch (Exception e){
	            PageFactory.reset();
	            driver.close();
	            e.printStackTrace();
	        }
	        try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	        Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	        homePage.navigateToOCMPage();
	        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	        ocmHomePage.navigateToTab("IVR");
	        IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	        ivrPage.navigateToAdhocOptionEnhancementPage();
	        AdhocOptionEnhancementPage adhocOptEnhPage=PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
	        Assert.assertTrue(adhocOptEnhPage.isAdhocOptionEnhancementPageDisplayed(),"Adhoc Option Enhancement Page assertion failed");
	        Assert.assertFalse(adhocOptEnhPage.isAddBtnDisplayed(), "Add button assertion failed");
	        Assert.assertFalse(adhocOptEnhPage.isEditBtnDisplayed(), "Edit button assertion failed");
	        Assert.assertTrue(adhocOptEnhPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
	        Assert.assertFalse(adhocOptEnhPage.isExportBtnDisplayed(), "Export button assertion failed");
	        screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");  
	        homePage.userLogout();
	        driver.close();
		}
	//@Test
		public void VerifyExportAccessOfAdHocOptionEnhancement() throws Exception
		{            
			UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
			userManagementPage.ProvideAccess("Adhoc Option Enhancement", "Export");
			Thread.sleep(2000);
			driver.close();
			try {
			     PageFactory.reset();
			     BrowserFactory browserFactory = new BrowserFactory();
			     driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			     String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			     Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			     driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			     if(map.get("LoginType").equals("Custom")){
			         LoginPage loginPage=new LoginPage(driver);
			         loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
			         Thread.sleep(5000);
			      	}
			}catch (Exception e){
				PageFactory.reset();
			    driver.close();
			    e.printStackTrace();
			}
			Thread.sleep(2000);
			HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
			Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
			homePage.navigateToOCMPage();
			OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
			ocmHomePage.navigateToTab("IVR");
			IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
			ivrPage.navigateToAdhocOptionEnhancementPage();
			AdhocOptionEnhancementPage adhocOptEnhPage=PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
			Assert.assertTrue(adhocOptEnhPage.isAdhocOptionEnhancementPageDisplayed(),"Adhoc Option Enhancement Page assertion failed");
			Assert.assertFalse(adhocOptEnhPage.isAddBtnDisplayed(), "Add button assertion failed");
			Assert.assertFalse(adhocOptEnhPage.isEditBtnDisplayed(), "Edit button assertion failed");
			Assert.assertFalse(adhocOptEnhPage.isDeleteBtnDisplayed(), "Delete button assertion failed");
			Assert.assertTrue(adhocOptEnhPage.isExportBtnDisplayed(), "Export button assertion failed");
			screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");  
			homePage.userLogout();
			driver.close();
		}
		//@Test
		public void VerifyViewAccessOfAdminCallback() throws Exception
		{
			UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
			userManagementPage.ProvideAccess("Admin Callback", "View");
			Thread.sleep(2000);
			driver.close();
			try {
				PageFactory.reset();
				BrowserFactory browserFactory = new BrowserFactory();
				driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
				String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
				Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
				driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
				if(map.get("LoginType").equals("Custom")){
					LoginPage loginPage=new LoginPage(driver);
				    loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				    Thread.sleep(5000);
				}
			}catch (Exception e){
				PageFactory.reset();
				driver.close();
				e.printStackTrace();
			}
			Thread.sleep(2000);
			HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
			Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
			homePage.navigateToOCMPage();
			OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
			ocmHomePage.navigateToTab("IVR");
			IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
			ivrPage.navigateToAdminCallbackPage();
			AdminCallbackPage modPageInst=PageFactory.createPageInstance(driver,AdminCallbackPage.class);
			Assert.assertTrue(modPageInst.isAdminCallbackPageDisplayed(), "Admin Callback Page assertion failed");
			Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
			screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
			homePage.userLogout();
			driver.close();
		}

		//@Test
		public void VerifyExportAccessOfAdminCallback() throws Exception
		{
			UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
			userManagementPage.ProvideAccess("Admin Callback", "Export");
			Thread.sleep(2000);
			driver.close();
			try {
				PageFactory.reset();
				BrowserFactory browserFactory = new BrowserFactory();
				driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
				String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
				Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
				driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
				if(map.get("LoginType").equals("Custom")){
					LoginPage loginPage=new LoginPage(driver);
				    loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				    Thread.sleep(5000);
				}
			}catch (Exception e){
				PageFactory.reset();
				driver.close();
				e.printStackTrace();
			}
			Thread.sleep(2000);
			HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
			Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
			homePage.navigateToOCMPage();
			OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
			ocmHomePage.navigateToTab("IVR");
			IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
			ivrPage.navigateToAdminCallbackPage();
			AdminCallbackPage modPageInst=PageFactory.createPageInstance(driver,AdminCallbackPage.class);
			Assert.assertTrue(modPageInst.isAdminCallbackPageDisplayed(), "Admin Callback Page assertion failed");
			Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
			screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
			homePage.userLogout();
			driver.close();
		}
				
		//@Test
		public void VerifyViewAccessOfAgentSkillAssignment() throws Exception
		{
			UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
			userManagementPage.ProvideAccess("Agent Skill Assignment", "View");
			Thread.sleep(2000);
			driver.close();
			try {
				 PageFactory.reset();
				 BrowserFactory browserFactory = new BrowserFactory();
				 driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
				 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
				 Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
				 driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
				 if(map.get("LoginType").equals("Custom")){
					 LoginPage loginPage=new LoginPage(driver);
					 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
					 Thread.sleep(5000);
				 }
			}catch (Exception e){
				PageFactory.reset();
				driver.close();
				e.printStackTrace();
			}
			Thread.sleep(2000);
			HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
			Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
			homePage.navigateToOCMPage();
			OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
			ocmHomePage.navigateToTab("HOME");
			ocmHomePage.navigateToAgentSkillAssignmentPage();
			AgentSkillAssignmentPage modPageInst=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
			Assert.assertTrue(modPageInst.isAgentSkillAssignmentPageDisplayed(), "Agent Skill Assignment Page assertion failed");
			Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
			Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
			Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
			screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
			homePage.userLogout();
			driver.close();
		}
			    
		//@Test
		public void VerifyAddAccessOfAgentSkillAssignment() throws Exception
		{
			UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
			userManagementPage.ProvideAccess("Agent Skill Assignment", "Add");
			Thread.sleep(2000);
			driver.close();
			try {
				 PageFactory.reset();
				 BrowserFactory browserFactory = new BrowserFactory();
				 driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
				 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
				 Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
				 driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
				 if(map.get("LoginType").equals("Custom")){
				     LoginPage loginPage=new LoginPage(driver);
				     loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				     Thread.sleep(5000);
				  }
			}catch (Exception e){
				 PageFactory.reset();
				 driver.close();
				 e.printStackTrace();
			}
			Thread.sleep(2000);
			HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
			Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
			homePage.navigateToOCMPage();
			OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
			ocmHomePage.navigateToTab("HOME");
			ocmHomePage.navigateToAgentSkillAssignmentPage();
			AgentSkillAssignmentPage modPageInst=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
			Assert.assertTrue(modPageInst.isAgentSkillAssignmentPageDisplayed(), "Agent Skill Assignment Page assertion failed");
			Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
			Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
			Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
			homePage.userLogout();
			driver.close();
		}
				
				//@Test
				public void VerifyEditAccessOfAgentSkillAssignment() throws Exception
				{
				     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
				     userManagementPage.ProvideAccess("Agent Skill Assignment", "Edit");
				     Thread.sleep(2000);
				     driver.close();
				     try {
				             PageFactory.reset();
				             BrowserFactory browserFactory = new BrowserFactory();
				             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
				             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
				             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
				             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
				             if(map.get("LoginType").equals("Custom")){
				                 LoginPage loginPage=new LoginPage(driver);
				                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				                 Thread.sleep(5000);
				              }
				      }catch (Exception e){
				         PageFactory.reset();
				         driver.close();
				         e.printStackTrace();
				      }
				      try {
				             Thread.sleep(2000);
				          }catch(InterruptedException e) {
				             e.printStackTrace();
				          }
				      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
				      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
				      homePage.navigateToOCMPage();
				      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
				      ocmHomePage.navigateToTab("HOME");
				      ocmHomePage.navigateToAgentSkillAssignmentPage();
				      AgentSkillAssignmentPage modPageInst=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
				      Assert.assertTrue(modPageInst.isAgentSkillAssignmentPageDisplayed(), "Agent Skill Assignment Page assertion failed");
				      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
				      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
				      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
				      screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
				      homePage.userLogout();
				      driver.close();
				}
				
				//@Test
				public void VerifyExportAccessOfAgentSkillAssignment() throws Exception
				{
				     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
				     userManagementPage.ProvideAccess("Agent Skill Assignment", "Export");
				     Thread.sleep(2000);
				     driver.close();
				     try {
				             PageFactory.reset();
				             BrowserFactory browserFactory = new BrowserFactory();
				             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
				             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
				             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
				             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
				             if(map.get("LoginType").equals("Custom")){
				                 LoginPage loginPage=new LoginPage(driver);
				                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				                 Thread.sleep(5000);
				              }
				      }catch (Exception e){
				         PageFactory.reset();
				         driver.close();
				         e.printStackTrace();
				      }
				      try {
				             Thread.sleep(2000);
				          }catch(InterruptedException e) {
				             e.printStackTrace();
				          }
				      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
				      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
				      homePage.navigateToOCMPage();
				      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
				      ocmHomePage.navigateToTab("HOME");
				      ocmHomePage.navigateToAgentSkillAssignmentPage();
				      AgentSkillAssignmentPage modPageInst=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
				      Assert.assertTrue(modPageInst.isAgentSkillAssignmentPageDisplayed(), "Agent Skill Assignment Page assertion failed");
				      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
				      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
				      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
				      screenshot.captureScreen(driver, "VerifyAccess2", "UserManagementE2ETest");
				      homePage.userLogout();
				      driver.close();
				}
				
				//@Test
				public void VerifyViewAccessOfFaxLineConfig() throws Exception
				{
				     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
				     userManagementPage.ProvideAccess("Fax Line Config", "View");
				     Thread.sleep(2000);
				     driver.close();
				     try {
				             PageFactory.reset();
				             BrowserFactory browserFactory = new BrowserFactory();
				             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
				             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
				             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
				             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
				             if(map.get("LoginType").equals("Custom")){
				                 LoginPage loginPage=new LoginPage(driver);
				                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				                 Thread.sleep(5000);
				              }
				      }catch (Exception e){
				         PageFactory.reset();
				         driver.close();
				         e.printStackTrace();
				      }
				      try {
				             Thread.sleep(2000);
				          }catch(InterruptedException e) {
				             e.printStackTrace();
				          }
				      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
				      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
				      homePage.navigateToOCMPage();
				      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
				      ocmHomePage.navigateToTab("FAX");
				      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
				      faxPage.navigateToFaxLineConfigPage();
				      FaxLineConfigPage modPageInst=PageFactory.createPageInstance(driver,FaxLineConfigPage.class);
				      Assert.assertTrue(modPageInst.isFaxLineConfigPageDisplayed(), "Fax Line Config Page assertion failed");
				      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
				      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
				      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
				      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
				      homePage.userLogout();
				      driver.close();
				}
				
				//@Test
				public void VerifyAddAccessOfFaxLineConfig() throws Exception
				{
				     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
				     userManagementPage.ProvideAccess("Fax Line Config", "Add");
				     Thread.sleep(2000);
				     driver.close();
				     try {
				             PageFactory.reset();
				             BrowserFactory browserFactory = new BrowserFactory();
				             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
				             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
				             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
				             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
				             if(map.get("LoginType").equals("Custom")){
				                 LoginPage loginPage=new LoginPage(driver);
				                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				                 Thread.sleep(5000);
				              }
				      }catch (Exception e){
				         PageFactory.reset();
				         driver.close();
				         e.printStackTrace();
				      }
				      try {
				             Thread.sleep(2000);
				          }catch(InterruptedException e) {
				             e.printStackTrace();
				          }
				      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
				      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
				      homePage.navigateToOCMPage();
				      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
				      ocmHomePage.navigateToTab("FAX");
				      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
				      faxPage.navigateToFaxLineConfigPage();
				      FaxLineConfigPage modPageInst=PageFactory.createPageInstance(driver,FaxLineConfigPage.class);
				      Assert.assertTrue(modPageInst.isFaxLineConfigPageDisplayed(), "Fax Line Config Page assertion failed");
				      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
				      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
				      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
				      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
				      homePage.userLogout();
				      driver.close();
				}

				//@Test
				public void VerifyEditAccessOfFaxLineConfig() throws Exception
				{
				     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
				     userManagementPage.ProvideAccess("Fax Line Config", "Edit");
				     Thread.sleep(2000);
				     driver.close();
				     try {
				             PageFactory.reset();
				             BrowserFactory browserFactory = new BrowserFactory();
				             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
				             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
				             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
				             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
				             if(map.get("LoginType").equals("Custom")){
				                 LoginPage loginPage=new LoginPage(driver);
				                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				                 Thread.sleep(5000);
				              }
				      }catch (Exception e){
				         PageFactory.reset();
				         driver.close();
				         e.printStackTrace();
				      }
				      try {
				             Thread.sleep(2000);
				          }catch(InterruptedException e) {
				             e.printStackTrace();
				          }
				      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
				      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
				      homePage.navigateToOCMPage();
				      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
				      ocmHomePage.navigateToTab("FAX");
				      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
				      faxPage.navigateToFaxLineConfigPage();
				      FaxLineConfigPage modPageInst=PageFactory.createPageInstance(driver,FaxLineConfigPage.class);
				      Assert.assertTrue(modPageInst.isFaxLineConfigPageDisplayed(), "Fax Line Config Page assertion failed");
				      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
				      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
				      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
				      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
				      homePage.userLogout();
				      driver.close();
				}

				//@Test
				public void VerifyDeleteAccessOfFaxLineConfig() throws Exception
				{
				     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
				     userManagementPage.ProvideAccess("Fax Line Config", "Delete");
				     Thread.sleep(2000);
				     driver.close();
				     try {
				             PageFactory.reset();
				             BrowserFactory browserFactory = new BrowserFactory();
				             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
				             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
				             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
				             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
				             if(map.get("LoginType").equals("Custom")){
				                 LoginPage loginPage=new LoginPage(driver);
				                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				                 Thread.sleep(5000);
				              }
				      }catch (Exception e){
				         PageFactory.reset();
				         driver.close();
				         e.printStackTrace();
				      }
				      try {
				             Thread.sleep(2000);
				          }catch(InterruptedException e) {
				             e.printStackTrace();
				          }
				      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
				      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
				      homePage.navigateToOCMPage();
				      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
				      ocmHomePage.navigateToTab("FAX");
				      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
				      faxPage.navigateToFaxLineConfigPage();
				      FaxLineConfigPage modPageInst=PageFactory.createPageInstance(driver,FaxLineConfigPage.class);
				      Assert.assertTrue(modPageInst.isFaxLineConfigPageDisplayed(), "Fax Line Config Page assertion failed");
				      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
				      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
				      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
				      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
				      homePage.userLogout();
				      driver.close();
				}

				//@Test
				public void VerifyExportAccessOfFaxLineConfig() throws Exception
				{
				     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
				     userManagementPage.ProvideAccess("Fax Line Config", "Export");
				     Thread.sleep(2000);
				     driver.close();
				     try {
				             PageFactory.reset();
				             BrowserFactory browserFactory = new BrowserFactory();
				             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
				             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
				             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
				             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
				             if(map.get("LoginType").equals("Custom")){
				                 LoginPage loginPage=new LoginPage(driver);
				                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				                 Thread.sleep(5000);
				              }
				      }catch (Exception e){
				         PageFactory.reset();
				         driver.close();
				         e.printStackTrace();
				      }
				      try {
				             Thread.sleep(2000);
				          }catch(InterruptedException e) {
				             e.printStackTrace();
				          }
				      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
				      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
				      homePage.navigateToOCMPage();
				      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
				      ocmHomePage.navigateToTab("FAX");
				      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
				      faxPage.navigateToFaxLineConfigPage();
				      FaxLineConfigPage modPageInst=PageFactory.createPageInstance(driver,FaxLineConfigPage.class);
				      Assert.assertTrue(modPageInst.isFaxLineConfigPageDisplayed(), "Fax Line Config Page assertion failed");
				      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
				      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
				      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
				      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
				      homePage.userLogout();
				      driver.close();
				}

	//@Test
	public void VerifyViewAccessOfChatTemplates() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Chat Templates", "View");
		Thread.sleep(2000);
		driver.close();
		try {
				PageFactory.reset();
				BrowserFactory browserFactory = new BrowserFactory();
				driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
				String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
				Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
				driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
				if(map.get("LoginType").equals("Custom")){
					LoginPage loginPage=new LoginPage(driver);
					loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
					Thread.sleep(5000);
					}
		}catch (Exception e){
			PageFactory.reset();
			driver.close();
			e.printStackTrace();
		}
		Thread.sleep(2000);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("CHAT");
		ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
		chatPage.navigateToChatTemplatesPage();
		ChatTemplatesPage modPageInst=PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
		Assert.assertTrue(modPageInst.isChatTemplatePageDisplayed(), "Chat Templates Page assertion failed");
		Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		homePage.userLogout();
		driver.close();
	}
		
	//@Test
	public void VerifyAddAccessOfChatTemplates() throws Exception {
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Chat Templates", "Add");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
			LoginPage loginPage=new LoginPage(driver);
			loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
			Thread.sleep(5000);
			}
		}catch (Exception e){
			PageFactory.reset();
			driver.close();
			e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("CHAT");
		ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
		chatPage.navigateToChatTemplatesPage();
		ChatTemplatesPage modPageInst=PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
		Assert.assertTrue(modPageInst.isChatTemplatePageDisplayed(), "Chat Templates Page assertion failed");
		Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		homePage.userLogout();
		driver.close();
	}

	//@Test
	public void VerifyEditAccessOfChatTemplates() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Chat Templates", "Edit");
		Thread.sleep(2000);
		driver.close();
		try {
		PageFactory.reset();
		BrowserFactory browserFactory = new BrowserFactory();
		driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		if(map.get("LoginType").equals("Custom")){
			LoginPage loginPage=new LoginPage(driver);
			loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
			Thread.sleep(5000);
		}
		}catch (Exception e){
			PageFactory.reset();
			driver.close();
			e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		ocmHomePage.navigateToTab("CHAT");
		ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
		chatPage.navigateToChatTemplatesPage();
		ChatTemplatesPage modPageInst=PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
		Assert.assertTrue(modPageInst.isChatTemplatePageDisplayed(), "Chat Templates Page assertion failed");
		Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		homePage.userLogout();
		driver.close();
	}
	
	//@Test
	public void VerifyDeleteAccessOfChatTemplates() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Chat Templates", "Delete");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
				}
			}catch (Exception e){
				PageFactory.reset();
				driver.close();
				e.printStackTrace();
			}
			Thread.sleep(2000);
			HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
			Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
			homePage.navigateToOCMPage();
			OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
			ocmHomePage.navigateToTab("CHAT");
			ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
			chatPage.navigateToChatTemplatesPage();
			ChatTemplatesPage modPageInst=PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
			Assert.assertTrue(modPageInst.isChatTemplatePageDisplayed(), "Chat Templates Page assertion failed");
			Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
			Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
			Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
			Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
			homePage.userLogout();
			driver.close();
	}

	//@Test
	public void VerifyExportAccessOfChatTemplates() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAccess("Chat Templates", "Export");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
				Thread.sleep(5000);
				}
			}catch (Exception e){
				PageFactory.reset();
				driver.close();
				e.printStackTrace();
			}
			Thread.sleep(2000);
			HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
			Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
			homePage.navigateToOCMPage();
			OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
			ocmHomePage.navigateToTab("CHAT");
			ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
			chatPage.navigateToChatTemplatesPage();
			ChatTemplatesPage modPageInst=PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
			Assert.assertTrue(modPageInst.isChatTemplatePageDisplayed(), "Chat Templates Page assertion failed");
			Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
			Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
			Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
			Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
			homePage.userLogout();
			driver.close();
		}

	//@Test
	public void VerifyViewAccessOfChatIntentSkillMapping() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Chat Intent Skill Mapping", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("CHAT");
	      ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
	      chatPage.navigateToChatIntentSkillMappingPage();
	      ChatIntentSkillMappingPage modPageInst=PageFactory.createPageInstance(driver,ChatIntentSkillMappingPage.class);
	      Assert.assertTrue(modPageInst.isChatIntentSkillMappingPageDisplayed(), "Chat Intent Skill Mapping Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfChatIntentSkillMapping() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Chat Intent Skill Mapping", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("CHAT");
	      ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
	      chatPage.navigateToChatIntentSkillMappingPage();
	      ChatIntentSkillMappingPage modPageInst=PageFactory.createPageInstance(driver,ChatIntentSkillMappingPage.class);
	      Assert.assertTrue(modPageInst.isChatIntentSkillMappingPageDisplayed(), "Chat Intent Skill Mapping Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfChatIntentSkillMapping() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Chat Intent Skill Mapping", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("CHAT");
	      ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
	      chatPage.navigateToChatIntentSkillMappingPage();
	      ChatIntentSkillMappingPage modPageInst=PageFactory.createPageInstance(driver,ChatIntentSkillMappingPage.class);
	      Assert.assertTrue(modPageInst.isChatIntentSkillMappingPageDisplayed(), "Chat Intent Skill Mapping Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfChatIntentSkillMapping() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Chat Intent Skill Mapping", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("CHAT");
	      ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
	      chatPage.navigateToChatIntentSkillMappingPage();
	      ChatIntentSkillMappingPage modPageInst=PageFactory.createPageInstance(driver,ChatIntentSkillMappingPage.class);
	      Assert.assertTrue(modPageInst.isChatIntentSkillMappingPageDisplayed(), "Chat Intent Skill Mapping Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfChatIntentSkillMapping() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Chat Intent Skill Mapping", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("CHAT");
	      ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
	      chatPage.navigateToChatIntentSkillMappingPage();
	      ChatIntentSkillMappingPage modPageInst=PageFactory.createPageInstance(driver,ChatIntentSkillMappingPage.class);
	      Assert.assertTrue(modPageInst.isChatIntentSkillMappingPageDisplayed(), "Chat Intent Skill Mapping Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfFaxSenders() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Fax Senders", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("FAX");
	      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
	      faxPage.navigateToFaxSendersPage();
	      FaxSendersPage modPageInst=PageFactory.createPageInstance(driver,FaxSendersPage.class);
	      Assert.assertTrue(modPageInst.isFaxSendersPageDisplayed(), "Fax Senders Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfFaxSenders() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Fax Senders", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("FAX");
	      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
	      faxPage.navigateToFaxSendersPage();
	      FaxSendersPage modPageInst=PageFactory.createPageInstance(driver,FaxSendersPage.class);
	      Assert.assertTrue(modPageInst.isFaxSendersPageDisplayed(), "Fax Senders Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfFaxSenders() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Fax Senders", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("FAX");
	      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
	      faxPage.navigateToFaxSendersPage();
	      FaxSendersPage modPageInst=PageFactory.createPageInstance(driver,FaxSendersPage.class);
	      Assert.assertTrue(modPageInst.isFaxSendersPageDisplayed(), "Fax Senders Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfFaxSenders() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Fax Senders", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("FAX");
	      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
	      faxPage.navigateToFaxSendersPage();
	      FaxSendersPage modPageInst=PageFactory.createPageInstance(driver,FaxSendersPage.class);
	      Assert.assertTrue(modPageInst.isFaxSendersPageDisplayed(), "Fax Senders Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfFaxSenders() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Fax Senders", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("FAX");
	      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
	      faxPage.navigateToFaxSendersPage();
	      FaxSendersPage modPageInst=PageFactory.createPageInstance(driver,FaxSendersPage.class);
	      Assert.assertTrue(modPageInst.isFaxSendersPageDisplayed(), "Fax Senders Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfIvrConfig() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("IVR Config", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToIvrConfigPage();
	      IvrConfigPage modPageInst=PageFactory.createPageInstance(driver,IvrConfigPage.class);
	      Assert.assertTrue(modPageInst.isIvrConfigPageDisplayed(), "IVR Config Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfIvrConfig() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("IVR Config", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToIvrConfigPage();
	      IvrConfigPage modPageInst=PageFactory.createPageInstance(driver,IvrConfigPage.class);
	      Assert.assertTrue(modPageInst.isIvrConfigPageDisplayed(), "IVR Config Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfIvrConfig() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("IVR Config", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToIvrConfigPage();
	      IvrConfigPage modPageInst=PageFactory.createPageInstance(driver,IvrConfigPage.class);
	      Assert.assertTrue(modPageInst.isIvrConfigPageDisplayed(), "IVR Config Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfIvrConfig() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("IVR Config", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToIvrConfigPage();
	      IvrConfigPage modPageInst=PageFactory.createPageInstance(driver,IvrConfigPage.class);
	      Assert.assertTrue(modPageInst.isIvrConfigPageDisplayed(), "IVR Config Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfIvrConfig() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("IVR Config", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToIvrConfigPage();
	      IvrConfigPage modPageInst=PageFactory.createPageInstance(driver,IvrConfigPage.class);
	      Assert.assertTrue(modPageInst.isIvrConfigPageDisplayed(), "IVR Config Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfOperatingHours() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Operating Hours", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToOperatingHoursPage();
	      OperatingHoursPage modPageInst=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
	      Assert.assertTrue(modPageInst.isOperatingHoursPageDisplayed(), "Operating Hours Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfOperatingHours() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Operating Hours", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToOperatingHoursPage();
	      OperatingHoursPage modPageInst=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
	      Assert.assertTrue(modPageInst.isOperatingHoursPageDisplayed(), "Operating Hours Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfOperatingHours() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Operating Hours", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToOperatingHoursPage();
	      OperatingHoursPage modPageInst=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
	      Assert.assertTrue(modPageInst.isOperatingHoursPageDisplayed(), "Operating Hours Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfOperatingHours() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Operating Hours", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToOperatingHoursPage();
	      OperatingHoursPage modPageInst=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
	      Assert.assertTrue(modPageInst.isOperatingHoursPageDisplayed(), "Operating Hours Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfOperatingHours() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Operating Hours", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToOperatingHoursPage();
	      OperatingHoursPage modPageInst=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
	      Assert.assertTrue(modPageInst.isOperatingHoursPageDisplayed(), "Operating Hours Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfChatMenuDescription() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Chat Menu Description", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("CHAT");
	      ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
	      chatPage.navigateToChatMenuDescriptionPage();
	      ChatMenuDescriptionPage modPageInst=PageFactory.createPageInstance(driver,ChatMenuDescriptionPage.class);
	      Assert.assertTrue(modPageInst.isChatMenuDescriptionPageDisplayed(), "Chat Menu Description Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfChatMenuDescription() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Chat Menu Description", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("CHAT");
	      ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
	      chatPage.navigateToChatMenuDescriptionPage();
	      ChatMenuDescriptionPage modPageInst=PageFactory.createPageInstance(driver,ChatMenuDescriptionPage.class);
	      Assert.assertTrue(modPageInst.isChatMenuDescriptionPageDisplayed(), "Chat Menu Description Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfChatMenuDescription() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Chat Menu Description", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("CHAT");
	      ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
	      chatPage.navigateToChatMenuDescriptionPage();
	      ChatMenuDescriptionPage modPageInst=PageFactory.createPageInstance(driver,ChatMenuDescriptionPage.class);
	      Assert.assertTrue(modPageInst.isChatMenuDescriptionPageDisplayed(), "Chat Menu Description Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfChatMenuDescription() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Chat Menu Description", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("CHAT");
	      ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
	      chatPage.navigateToChatMenuDescriptionPage();
	      ChatMenuDescriptionPage modPageInst=PageFactory.createPageInstance(driver,ChatMenuDescriptionPage.class);
	      Assert.assertTrue(modPageInst.isChatMenuDescriptionPageDisplayed(), "Chat Menu Description Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfChatMenuDescription() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Chat Menu Description", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("CHAT");
	      ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
	      chatPage.navigateToChatMenuDescriptionPage();
	      ChatMenuDescriptionPage modPageInst=PageFactory.createPageInstance(driver,ChatMenuDescriptionPage.class);
	      Assert.assertTrue(modPageInst.isChatMenuDescriptionPageDisplayed(), "Chat Menu Description Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfAgentTransfer() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Agent Transfer", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToAgentTransferPage();
	      AgentTransferPage modPageInst=PageFactory.createPageInstance(driver,AgentTransferPage.class);
	      Assert.assertTrue(modPageInst.isAgentTransferPageDisplayed(), "Agent Transfer Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfAgentTransfer() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Agent Transfer", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToAgentTransferPage();
	      AgentTransferPage modPageInst=PageFactory.createPageInstance(driver,AgentTransferPage.class);
	      Assert.assertTrue(modPageInst.isAgentTransferPageDisplayed(), "Agent Transfer Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfAgentTransfer() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Agent Transfer", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToAgentTransferPage();
	      AgentTransferPage modPageInst=PageFactory.createPageInstance(driver,AgentTransferPage.class);
	      Assert.assertTrue(modPageInst.isAgentTransferPageDisplayed(), "Agent Transfer Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfAgentTransfer() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Agent Transfer", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToAgentTransferPage();
	      AgentTransferPage modPageInst=PageFactory.createPageInstance(driver,AgentTransferPage.class);
	      Assert.assertTrue(modPageInst.isAgentTransferPageDisplayed(), "Agent Transfer Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfAgentTransfer() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Agent Transfer", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToAgentTransferPage();
	      AgentTransferPage modPageInst=PageFactory.createPageInstance(driver,AgentTransferPage.class);
	      Assert.assertTrue(modPageInst.isAgentTransferPageDisplayed(), "Agent Transfer Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfBillingOrg() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Billing Org", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToBillingOrgPage();
	      BillingOrgPage modPageInst=PageFactory.createPageInstance(driver,BillingOrgPage.class);
	      Assert.assertTrue(modPageInst.isBillingOrgPageDisplayed(), "Billing Org Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfBillingOrg() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Billing Org", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToBillingOrgPage();
	      BillingOrgPage modPageInst=PageFactory.createPageInstance(driver,BillingOrgPage.class);
	      Assert.assertTrue(modPageInst.isBillingOrgPageDisplayed(), "Billing Org Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfBillingOrg() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Billing Org", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToBillingOrgPage();
	      BillingOrgPage modPageInst=PageFactory.createPageInstance(driver,BillingOrgPage.class);
	      Assert.assertTrue(modPageInst.isBillingOrgPageDisplayed(), "Billing Org Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfBillingOrg() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Billing Org", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToBillingOrgPage();
	      BillingOrgPage modPageInst=PageFactory.createPageInstance(driver,BillingOrgPage.class);
	      Assert.assertTrue(modPageInst.isBillingOrgPageDisplayed(), "Billing Org Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfBillingOrg() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Billing Org", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToBillingOrgPage();
	      BillingOrgPage modPageInst=PageFactory.createPageInstance(driver,BillingOrgPage.class);
	      Assert.assertTrue(modPageInst.isBillingOrgPageDisplayed(), "Billing Org Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfBranchDetails() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Branch Details", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToBranchDetailsPage();
	      BranchDetailsPage modPageInst=PageFactory.createPageInstance(driver,BranchDetailsPage.class);
	      Assert.assertTrue(modPageInst.isBranchDetailsPageDisplayed(), "Branch Details Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfBranchDetails() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Branch Details", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToBranchDetailsPage();
	      BranchDetailsPage modPageInst=PageFactory.createPageInstance(driver,BranchDetailsPage.class);
	      Assert.assertTrue(modPageInst.isBranchDetailsPageDisplayed(), "Branch Details Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfBranchDetails() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Branch Details", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToBranchDetailsPage();
	      BranchDetailsPage modPageInst=PageFactory.createPageInstance(driver,BranchDetailsPage.class);
	      Assert.assertTrue(modPageInst.isBranchDetailsPageDisplayed(), "Branch Details Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfBranchDetails() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Branch Details", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToBranchDetailsPage();
	      BranchDetailsPage modPageInst=PageFactory.createPageInstance(driver,BranchDetailsPage.class);
	      Assert.assertTrue(modPageInst.isBranchDetailsPageDisplayed(), "Branch Details Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfBranchDetails() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Branch Details", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToBranchDetailsPage();
	      BranchDetailsPage modPageInst=PageFactory.createPageInstance(driver,BranchDetailsPage.class);
	      Assert.assertTrue(modPageInst.isBranchDetailsPageDisplayed(), "Branch Details Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfCallbackAnnouncement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Callback Announcement", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToCallbackAnnouncementPage();
	      CallbackAnnouncementPage modPageInst=PageFactory.createPageInstance(driver,CallbackAnnouncementPage.class);
	      Assert.assertTrue(modPageInst.isCallbackAnnouncementPageDisplayed(), "Callback Announcement Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfCallbackAnnouncement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Callback Announcement", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToCallbackAnnouncementPage();
	      CallbackAnnouncementPage modPageInst=PageFactory.createPageInstance(driver,CallbackAnnouncementPage.class);
	      Assert.assertTrue(modPageInst.isCallbackAnnouncementPageDisplayed(), "Callback Announcement Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfCallbackAnnouncement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Callback Announcement", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToCallbackAnnouncementPage();
	      CallbackAnnouncementPage modPageInst=PageFactory.createPageInstance(driver,CallbackAnnouncementPage.class);
	      Assert.assertTrue(modPageInst.isCallbackAnnouncementPageDisplayed(), "Callback Announcement Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfCallbackAnnouncement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Callback Announcement", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToCallbackAnnouncementPage();
	      CallbackAnnouncementPage modPageInst=PageFactory.createPageInstance(driver,CallbackAnnouncementPage.class);
	      Assert.assertTrue(modPageInst.isCallbackAnnouncementPageDisplayed(), "Callback Announcement Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfCallbackAnnouncement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Callback Announcement", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToCallbackAnnouncementPage();
	      CallbackAnnouncementPage modPageInst=PageFactory.createPageInstance(driver,CallbackAnnouncementPage.class);
	      Assert.assertTrue(modPageInst.isCallbackAnnouncementPageDisplayed(), "Callback Announcement Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfFaxForms() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Fax Forms", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToFaxFormsPage();
	      FaxFormsPage modPageInst=PageFactory.createPageInstance(driver,FaxFormsPage.class);
	      Assert.assertTrue(modPageInst.isFaxFormsPageDisplayed(), "Fax Forms Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfFaxForms() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Fax Forms", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToFaxFormsPage();
	      FaxFormsPage modPageInst=PageFactory.createPageInstance(driver,FaxFormsPage.class);
	      Assert.assertTrue(modPageInst.isFaxFormsPageDisplayed(), "Fax Forms Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfFaxForms() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Fax Forms", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToFaxFormsPage();
	      FaxFormsPage modPageInst=PageFactory.createPageInstance(driver,FaxFormsPage.class);
	      Assert.assertTrue(modPageInst.isFaxFormsPageDisplayed(), "Fax Forms Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfFaxForms() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Fax Forms", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToFaxFormsPage();
	      FaxFormsPage modPageInst=PageFactory.createPageInstance(driver,FaxFormsPage.class);
	      Assert.assertTrue(modPageInst.isFaxFormsPageDisplayed(), "Fax Forms Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfFaxForms() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Fax Forms", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToFaxFormsPage();
	      FaxFormsPage modPageInst=PageFactory.createPageInstance(driver,FaxFormsPage.class);
	      Assert.assertTrue(modPageInst.isFaxFormsPageDisplayed(), "Fax Forms Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfFaxGroup() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Fax Group", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToFaxGroupPage();
	      FaxGroupPage modPageInst=PageFactory.createPageInstance(driver,FaxGroupPage.class);
	      Assert.assertTrue(modPageInst.isFaxGroupPageDisplayed(), "Fax Group Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfFaxGroup() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Fax Group", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToFaxGroupPage();
	      FaxGroupPage modPageInst=PageFactory.createPageInstance(driver,FaxGroupPage.class);
	      Assert.assertTrue(modPageInst.isFaxGroupPageDisplayed(), "Fax Group Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfFaxGroup() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Fax Group", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToFaxGroupPage();
	      FaxGroupPage modPageInst=PageFactory.createPageInstance(driver,FaxGroupPage.class);
	      Assert.assertTrue(modPageInst.isFaxGroupPageDisplayed(), "Fax Group Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfFaxGroup() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Fax Group", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToFaxGroupPage();
	      FaxGroupPage modPageInst=PageFactory.createPageInstance(driver,FaxGroupPage.class);
	      Assert.assertTrue(modPageInst.isFaxGroupPageDisplayed(), "Fax Group Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfFaxGroup() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Fax Group", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToFaxGroupPage();
	      FaxGroupPage modPageInst=PageFactory.createPageInstance(driver,FaxGroupPage.class);
	      Assert.assertTrue(modPageInst.isFaxGroupPageDisplayed(), "Fax Group Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfHolidayList() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Holiday List", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToHolidayListPage();
	      HolidayListPage modPageInst=PageFactory.createPageInstance(driver,HolidayListPage.class);
	      Assert.assertTrue(modPageInst.isHolidayListPageDisplayed(), "Holiday List Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfHolidayList() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Holiday List", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToHolidayListPage();
	      HolidayListPage modPageInst=PageFactory.createPageInstance(driver,HolidayListPage.class);
	      Assert.assertTrue(modPageInst.isHolidayListPageDisplayed(), "Holiday List Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfHolidayList() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Holiday List", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToHolidayListPage();
	      HolidayListPage modPageInst=PageFactory.createPageInstance(driver,HolidayListPage.class);
	      Assert.assertTrue(modPageInst.isHolidayListPageDisplayed(), "Holiday List Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfHolidayList() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Holiday List", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToHolidayListPage();
	      HolidayListPage modPageInst=PageFactory.createPageInstance(driver,HolidayListPage.class);
	      Assert.assertTrue(modPageInst.isHolidayListPageDisplayed(), "Holiday List Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfHolidayList() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Holiday List", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToHolidayListPage();
	      HolidayListPage modPageInst=PageFactory.createPageInstance(driver,HolidayListPage.class);
	      Assert.assertTrue(modPageInst.isHolidayListPageDisplayed(), "Holiday List Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfVipListManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("VIP List Management", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToVipListManagementPage();
	      VipListManagementPage modPageInst=PageFactory.createPageInstance(driver,VipListManagementPage.class);
	      Assert.assertTrue(modPageInst.isVipListManagementPageDisplayed(), "VIP List Management Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfVipListManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("VIP List Management", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToVipListManagementPage();
	      VipListManagementPage modPageInst=PageFactory.createPageInstance(driver,VipListManagementPage.class);
	      Assert.assertTrue(modPageInst.isVipListManagementPageDisplayed(), "VIP List Management Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfVipListManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("VIP List Management", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToVipListManagementPage();
	      VipListManagementPage modPageInst=PageFactory.createPageInstance(driver,VipListManagementPage.class);
	      Assert.assertTrue(modPageInst.isVipListManagementPageDisplayed(), "VIP List Management Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfVipListManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("VIP List Management", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToVipListManagementPage();
	      VipListManagementPage modPageInst=PageFactory.createPageInstance(driver,VipListManagementPage.class);
	      Assert.assertTrue(modPageInst.isVipListManagementPageDisplayed(), "VIP List Management Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfVipListManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("VIP List Management", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToVipListManagementPage();
	      VipListManagementPage modPageInst=PageFactory.createPageInstance(driver,VipListManagementPage.class);
	      Assert.assertTrue(modPageInst.isVipListManagementPageDisplayed(), "VIP List Management Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfVbEnrollmentFlag() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("VB Enrollment Flag", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToVbEnrollmentFlagPage();
	      VbEnrollmentFlagPage modPageInst=PageFactory.createPageInstance(driver,VbEnrollmentFlagPage.class);
	      Assert.assertTrue(modPageInst.isVbEnrollmentFlagPageDisplayed(), "VB Enrollment Flag Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfVbEnrollmentFlag() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("VB Enrollment Flag", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToVbEnrollmentFlagPage();
	      VbEnrollmentFlagPage modPageInst=PageFactory.createPageInstance(driver,VbEnrollmentFlagPage.class);
	      Assert.assertTrue(modPageInst.isVbEnrollmentFlagPageDisplayed(), "VB Enrollment Flag Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfVbEnrollmentFlag() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("VB Enrollment Flag", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToVbEnrollmentFlagPage();
	      VbEnrollmentFlagPage modPageInst=PageFactory.createPageInstance(driver,VbEnrollmentFlagPage.class);
	      Assert.assertTrue(modPageInst.isVbEnrollmentFlagPageDisplayed(), "VB Enrollment Flag Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfVbEnrollmentFlag() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("VB Enrollment Flag", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToVbEnrollmentFlagPage();
	      VbEnrollmentFlagPage modPageInst=PageFactory.createPageInstance(driver,VbEnrollmentFlagPage.class);
	      Assert.assertTrue(modPageInst.isVbEnrollmentFlagPageDisplayed(), "VB Enrollment Flag Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfVbEnrollmentFlag() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("VB Enrollment Flag", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToVbEnrollmentFlagPage();
	      VbEnrollmentFlagPage modPageInst=PageFactory.createPageInstance(driver,VbEnrollmentFlagPage.class);
	      Assert.assertTrue(modPageInst.isVbEnrollmentFlagPageDisplayed(), "VB Enrollment Flag Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfProductPromotions() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Product Promotions", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToProductPromotionsPage();
	      ProductPromotionsPage modPageInst=PageFactory.createPageInstance(driver,ProductPromotionsPage.class);
	      Assert.assertTrue(modPageInst.isProductPromotionsPageDisplayed(), "Product Promotions Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfProductPromotions() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Product Promotions", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToProductPromotionsPage();
	      ProductPromotionsPage modPageInst=PageFactory.createPageInstance(driver,ProductPromotionsPage.class);
	      Assert.assertTrue(modPageInst.isProductPromotionsPageDisplayed(), "Product Promotions Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfProductPromotions() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Product Promotions", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToProductPromotionsPage();
	      ProductPromotionsPage modPageInst=PageFactory.createPageInstance(driver,ProductPromotionsPage.class);
	      Assert.assertTrue(modPageInst.isProductPromotionsPageDisplayed(), "Product Promotions Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfProductPromotions() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Product Promotions", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToProductPromotionsPage();
	      ProductPromotionsPage modPageInst=PageFactory.createPageInstance(driver,ProductPromotionsPage.class);
	      Assert.assertTrue(modPageInst.isProductPromotionsPageDisplayed(), "Product Promotions Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfProductPromotions() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Product Promotions", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToProductPromotionsPage();
	      ProductPromotionsPage modPageInst=PageFactory.createPageInstance(driver,ProductPromotionsPage.class);
	      Assert.assertTrue(modPageInst.isProductPromotionsPageDisplayed(), "Product Promotions Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfIntentMapping() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Intent Mapping", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToIntentMappingPage();
	      IntentMappingPage modPageInst=PageFactory.createPageInstance(driver,IntentMappingPage.class);
	      Assert.assertTrue(modPageInst.isIntentMappingPageDisplayed(), "Intent Mapping Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfIntentMapping() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Intent Mapping", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToIntentMappingPage();
	      IntentMappingPage modPageInst=PageFactory.createPageInstance(driver,IntentMappingPage.class);
	      Assert.assertTrue(modPageInst.isIntentMappingPageDisplayed(), "Intent Mapping Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfIntentMapping() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Intent Mapping", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToIntentMappingPage();
	      IntentMappingPage modPageInst=PageFactory.createPageInstance(driver,IntentMappingPage.class);
	      Assert.assertTrue(modPageInst.isIntentMappingPageDisplayed(), "Intent Mapping Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfIntentMapping() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Intent Mapping", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToIntentMappingPage();
	      IntentMappingPage modPageInst=PageFactory.createPageInstance(driver,IntentMappingPage.class);
	      Assert.assertTrue(modPageInst.isIntentMappingPageDisplayed(), "Intent Mapping Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfIntentMapping() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Intent Mapping", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToIntentMappingPage();
	      IntentMappingPage modPageInst=PageFactory.createPageInstance(driver,IntentMappingPage.class);
	      Assert.assertTrue(modPageInst.isIntentMappingPageDisplayed(), "Intent Mapping Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfCepEventMapping() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("CEP Event Mapping", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToCepEventMappingPage();
	      CepEventMappingPage modPageInst=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
	      Assert.assertTrue(modPageInst.isCepEventMappingPageDisplayed(), "Cep Event Mapping Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfCepEventMapping() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("CEP Event Mapping", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToCepEventMappingPage();
	      CepEventMappingPage modPageInst=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
	      Assert.assertTrue(modPageInst.isCepEventMappingPageDisplayed(), "Cep Event Mapping Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfCepEventMapping() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("CEP Event Mapping", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToCepEventMappingPage();
	      CepEventMappingPage modPageInst=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
	      Assert.assertTrue(modPageInst.isCepEventMappingPageDisplayed(), "Cep Event Mapping Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfCepEventMapping() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("CEP Event Mapping", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToCepEventMappingPage();
	      CepEventMappingPage modPageInst=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
	      Assert.assertTrue(modPageInst.isCepEventMappingPageDisplayed(), "Cep Event Mapping Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfCepEventMapping() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("CEP Event Mapping", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToCepEventMappingPage();
	      CepEventMappingPage modPageInst=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
	      Assert.assertTrue(modPageInst.isCepEventMappingPageDisplayed(), "Cep Event Mapping Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfExportScheduler() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Export Scheduler", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToExportSchedulerPage();
	      ExportSchedulerPage modPageInst=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
	      Assert.assertTrue(modPageInst.isExportSchedulerPageDisplayed(), "Export Scheduler Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfExportScheduler() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Export Scheduler", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToExportSchedulerPage();
	      ExportSchedulerPage modPageInst=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
	      Assert.assertTrue(modPageInst.isExportSchedulerPageDisplayed(), "Export Scheduler Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfExportScheduler() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Export Scheduler", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToExportSchedulerPage();
	      ExportSchedulerPage modPageInst=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
	      Assert.assertTrue(modPageInst.isExportSchedulerPageDisplayed(), "Export Scheduler Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfExportScheduler() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Export Scheduler", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToExportSchedulerPage();
	      ExportSchedulerPage modPageInst=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
	      Assert.assertTrue(modPageInst.isExportSchedulerPageDisplayed(), "Export Scheduler Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfExportScheduler() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Export Scheduler", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToExportSchedulerPage();
	      ExportSchedulerPage modPageInst=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
	      Assert.assertTrue(modPageInst.isExportSchedulerPageDisplayed(), "Export Scheduler Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfOpaqueData() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Opaque Data", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToOpaqueDataPage();
	      OpaqueDataPage modPageInst=PageFactory.createPageInstance(driver,OpaqueDataPage.class);
	      Assert.assertTrue(modPageInst.isOpaqueDataPageDisplayed(), "Opaque Data Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfOpaqueData() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Opaque Data", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToOpaqueDataPage();
	      OpaqueDataPage modPageInst=PageFactory.createPageInstance(driver,OpaqueDataPage.class);
	      Assert.assertTrue(modPageInst.isOpaqueDataPageDisplayed(), "Opaque Data Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfOpaqueData() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Opaque Data", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToOpaqueDataPage();
	      OpaqueDataPage modPageInst=PageFactory.createPageInstance(driver,OpaqueDataPage.class);
	      Assert.assertTrue(modPageInst.isOpaqueDataPageDisplayed(), "Opaque Data Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfOpaqueData() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Opaque Data", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToOpaqueDataPage();
	      OpaqueDataPage modPageInst=PageFactory.createPageInstance(driver,OpaqueDataPage.class);
	      Assert.assertTrue(modPageInst.isOpaqueDataPageDisplayed(), "Opaque Data Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfOpaqueData() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Opaque Data", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToOpaqueDataPage();
	      OpaqueDataPage modPageInst=PageFactory.createPageInstance(driver,OpaqueDataPage.class);
	      Assert.assertTrue(modPageInst.isOpaqueDataPageDisplayed(), "Opaque Data Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfRoleBasedAccessManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Role Based Access Management", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToRoleBasedAccessManagementPage();
	      RoleBasedAccessManagementPage modPageInst=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
	      Assert.assertTrue(modPageInst.isRoleBasedAccessManagementPageDisplayed(), "Role Based Access Management Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfRoleBasedAccessManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Role Based Access Management", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToRoleBasedAccessManagementPage();
	      RoleBasedAccessManagementPage modPageInst=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
	      Assert.assertTrue(modPageInst.isRoleBasedAccessManagementPageDisplayed(), "Role Based Access Management Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfRoleBasedAccessManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Role Based Access Management", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToRoleBasedAccessManagementPage();
	      RoleBasedAccessManagementPage modPageInst=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
	      Assert.assertTrue(modPageInst.isRoleBasedAccessManagementPageDisplayed(), "Role Based Access Management Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfRoleBasedAccessManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Role Based Access Management", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToRoleBasedAccessManagementPage();
	      RoleBasedAccessManagementPage modPageInst=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
	      Assert.assertTrue(modPageInst.isRoleBasedAccessManagementPageDisplayed(), "Role Based Access Management Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfRoleBasedAccessManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Role Based Access Management", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToRoleBasedAccessManagementPage();
	      RoleBasedAccessManagementPage modPageInst=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
	      Assert.assertTrue(modPageInst.isRoleBasedAccessManagementPageDisplayed(), "Role Based Access Management Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfUserManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("User Management", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToUserManagementPage();
	      UserManagementPage modPageInst=PageFactory.createPageInstance(driver,UserManagementPage.class);
	      Assert.assertTrue(modPageInst.isUserManagementPageDisplayed(), "User Management Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfUserManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("User Management", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToUserManagementPage();
	      UserManagementPage modPageInst=PageFactory.createPageInstance(driver,UserManagementPage.class);
	      Assert.assertTrue(modPageInst.isUserManagementPageDisplayed(), "User Management Page assertion failed");
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfUserManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("User Management", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToUserManagementPage();
	      UserManagementPage modPageInst=PageFactory.createPageInstance(driver,UserManagementPage.class);
	      Assert.assertTrue(modPageInst.isUserManagementPageDisplayed(), "User Management Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfUserManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("User Management", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToUserManagementPage();
	      UserManagementPage modPageInst=PageFactory.createPageInstance(driver,UserManagementPage.class);
	      Assert.assertTrue(modPageInst.isUserManagementPageDisplayed(), "User Management Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfUserManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("User Management", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("HOME");
	      ocmHomePage.navigateToUserManagementPage();
	      UserManagementPage modPageInst=PageFactory.createPageInstance(driver,UserManagementPage.class);
	      Assert.assertTrue(modPageInst.isUserManagementPageDisplayed(), "User Management Page assertion failed");
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyViewAccessOfBranchManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Branch Management", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToBranchManagementPage();
	      BranchManagementPage modPageInst=PageFactory.createPageInstance(driver,BranchManagementPage.class);
	      Assert.assertTrue(modPageInst.isBranchManagementPageDisplayed(), "Branch Management Page assertion failed");
	      modPageInst.navigateToTab("Branch Management Audit Trail");;
	      Assert.assertFalse(modPageInst.isMakeBranchChangesButtonDisplayed(), "View Assertion Failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfBranchManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Branch Management", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToBranchManagementPage();
	      BranchManagementPage modPageInst=PageFactory.createPageInstance(driver,BranchManagementPage.class);
	      Assert.assertTrue(modPageInst.isBranchManagementPageDisplayed(), "Branch Management Page assertion failed");
	      modPageInst.navigateToTab("Branch Management Audit Trail");
	      modPageInst.clickOnMakeBranchManageChanges();
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfBranchManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Branch Management", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToBranchManagementPage();
	      BranchManagementPage modPageInst=PageFactory.createPageInstance(driver,BranchManagementPage.class);
	      Assert.assertTrue(modPageInst.isBranchManagementPageDisplayed(), "Branch Management Page assertion failed");
	      modPageInst.navigateToTab("Branch Management Audit Trail");
	      modPageInst.clickOnMakeBranchManageChanges();
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfBranchManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Branch Management", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToBranchManagementPage();
	      BranchManagementPage modPageInst=PageFactory.createPageInstance(driver,BranchManagementPage.class);
	      Assert.assertTrue(modPageInst.isBranchManagementPageDisplayed(), "Branch Management Page assertion failed");
	      modPageInst.navigateToTab("Branch Management Audit Trail");;
	      modPageInst.clickOnMakeBranchManageChanges();
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfBranchManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Branch Management", "Add");
	     userManagementPage.ProvideAccess("Branch Management", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("IVR");
	      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
	      ivrPage.navigateToBranchManagementPage();
	      BranchManagementPage modPageInst=PageFactory.createPageInstance(driver,BranchManagementPage.class);
	      Assert.assertTrue(modPageInst.isBranchManagementPageDisplayed(), "Branch Management Page assertion failed");
	      modPageInst.navigateToTab("Branch Management Audit Trail");;
	      modPageInst.clickOnMakeBranchManageChanges();
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}
	
	//@Test
	public void VerifyViewAccessOfAgentSettings() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Agent Settings", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("TMAC");
	      TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
	      tmacPage.navigateToAgentSettingsPage();
	      AgentSettingsNewDesignPage modPageInst=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
	      Assert.assertTrue(modPageInst.isAgentSettingsPageDisplayed(), "Agent Settings Page assertion failed");
	      modPageInst.selectAgentSettingsAuditTrailTab();
	      Assert.assertFalse(modPageInst.isMakeAgentSettingsChangesButtonDisplayed(), "View Assertion Failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyAddAccessOfAgentSettings() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Agent Settings", "Add");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("TMAC");
	      TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
	      tmacPage.navigateToAgentSettingsPage();
	      AgentSettingsNewDesignPage modPageInst=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
	      Assert.assertTrue(modPageInst.isAgentSettingsPageDisplayed(), "Agent Settings Page assertion failed");
	      modPageInst.selectAgentSettingsAuditTrailTab();
	      modPageInst.selectMakeAgentSettingsChanges();
	      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyEditAccessOfAgentSettings() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Agent Settings", "Edit");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("TMAC");
	      TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
	      tmacPage.navigateToAgentSettingsPage();
	      AgentSettingsNewDesignPage modPageInst=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
	      Assert.assertTrue(modPageInst.isAgentSettingsPageDisplayed(), "Agent Settings Page assertion failed");
	      modPageInst.selectAgentSettingsAuditTrailTab();
	      modPageInst.selectMakeAgentSettingsChanges();
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyDeleteAccessOfAgentSettings() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Agent Settings", "Delete");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("TMAC");
	      TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
	      tmacPage.navigateToAgentSettingsPage();
	      AgentSettingsNewDesignPage modPageInst=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
	      Assert.assertTrue(modPageInst.isAgentSettingsPageDisplayed(), "Agent Settings Page assertion failed");
	      modPageInst.selectAgentSettingsAuditTrailTab();
	      modPageInst.selectMakeAgentSettingsChanges();
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfAgentSettings() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Agent Settings", "Edit");
	     userManagementPage.ProvideAccess("Agent Settings", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("TMAC");
	      TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
	      tmacPage.navigateToAgentSettingsPage();
	      AgentSettingsNewDesignPage modPageInst=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
	      Assert.assertTrue(modPageInst.isAgentSettingsPageDisplayed(), "Agent Settings Page assertion failed");
	      modPageInst.selectAgentSettingsAuditTrailTab();
	      modPageInst.selectMakeAgentSettingsChanges();
	      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
	      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
	      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}
	
	//@Test
	public void VerifyAgentSettingsCheckerAccess() throws Exception 
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	    userManagementPage.ProvideAccess("Agent Setting Sync Checker", "View");
	    Thread.sleep(2000);
	    HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
	    OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	    ocmHomePage.navigateToTab("TMAC");
	    TmacPage tmacPage = PageFactory.createPageInstance(driver,TmacPage.class);
	    Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"TMAC page assertion failed");
        tmacPage.navigateToAgentSettingsPage();
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(4);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.addRecdOnlyInfo(agentSettingsDetails);
        Assert.assertTrue(agentSettingsPage.verifyNewRecordCreated(), "Add New record assertion failed");
        Thread.sleep(2000);
        agentSettingsPage.selectTaskComplete();
        agentSettingsPage.enterTaskCompleteText("Complete");
        agentSettingsPage.saveTaskCompletePopUp();
        driver.close();
        try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
            String filePath2 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
            Map<String, String> map2 = new ExcelReader(filePath2,"Login").getTestData().get(3);
            driver.get("http://"+map2.get("Username")+":"+map2.get("Password")+"@"+map2.get("Application URL").split("//")[1]);
            if(map2.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map2.get("Username"),map2.get("Password"),map2.get("DomainName"));
                Thread.sleep(5000);
             }
     }catch (Exception e){
        PageFactory.reset();
        driver.close();
        e.printStackTrace();
     }
     try {
            Thread.sleep(2000);
         }catch(InterruptedException e) {
            e.printStackTrace();
         }
     	HomePage homePage2 = PageFactory.createPageInstance(driver, HomePage.class);
	     Assert.assertTrue(homePage2.checkPageLoadStatus(), "user login successful status");
	     homePage2.navigateToOCMPage();
	     OCMHomePage ocmHomePage2 = PageFactory.createPageInstance(driver,OCMHomePage.class);
	     ocmHomePage2.navigateToTab("TMAC");
	     TmacPage tmacPage2 = PageFactory.createPageInstance(driver,TmacPage.class);
	     tmacPage2.navigateToAgentSettingsPage();
	     AgentSettingsNewDesignPage modPageInst=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
	     Assert.assertTrue(modPageInst.isAgentSettingsPageDisplayed(), "Agent Settings Page assertion failed");
	     modPageInst.selectAgentSettingsAuditTrailTab();
	     Assert.assertTrue(modPageInst.isApproveButtonDisplayed(), "Approve button assertion failed");
	     Assert.assertTrue(modPageInst.isApproveButtonDisplayed(), "Reject button assertion failed");
	     modPageInst.rejectChanges();
	}
	
	//@Test
	public void VerifyViewAccessOfCallBackManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Call Back Management", "View");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("CHAT");
	      ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
	      chatPage.navigateToCallBackMgmtPage();
	      CallBackManagementPage modPageInst=PageFactory.createPageInstance(driver,CallBackManagementPage.class);
	      Assert.assertTrue(modPageInst.isCallBackManagementPageDisplayed(), "Call Back Management Page assertion failed");
	      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}

	//@Test
	public void VerifyExportAccessOfCallBackManagement() throws Exception
	{
	     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	     userManagementPage.ProvideAccess("Call Back Management", "Export");
	     Thread.sleep(2000);
	     driver.close();
	     try {
	             PageFactory.reset();
	             BrowserFactory browserFactory = new BrowserFactory();
	             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	             if(map.get("LoginType").equals("Custom")){
	                 LoginPage loginPage=new LoginPage(driver);
	                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                 Thread.sleep(5000);
	              }
	      }catch (Exception e){
	         PageFactory.reset();
	         driver.close();
	         e.printStackTrace();
	      }
	      try {
	             Thread.sleep(2000);
	          }catch(InterruptedException e) {
	             e.printStackTrace();
	          }
	      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	      homePage.navigateToOCMPage();
	      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	      ocmHomePage.navigateToTab("CHAT");
	      ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
	      chatPage.navigateToCallBackMgmtPage();
	      CallBackManagementPage modPageInst=PageFactory.createPageInstance(driver,CallBackManagementPage.class);
	      Assert.assertTrue(modPageInst.isCallBackManagementPageDisplayed(), "Call Back Management Page assertion failed");
	      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
	      homePage.userLogout();
	      driver.close();
	}
	
	//@Test
		public void VerifyViewAccessOfCallBackEnableDisable() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Callback Enable/Disable", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToCallBackEnableDisable();
		      CallBackEnableDisablePage modPageInst=PageFactory.createPageInstance(driver,CallBackEnableDisablePage.class);
		      Assert.assertTrue(modPageInst.isCallBackEnableDisablePageDisplayed(), "Call Back Enable/Disable Page assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfApplicationAccessControl() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Application Access Control", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToApplicationAccessControlPage();
		      ApplicationAccessControlPage modPageInst=PageFactory.createPageInstance(driver,ApplicationAccessControlPage.class);
		      Assert.assertTrue(modPageInst.isApplicationAccessControlPageDisplayed(), "Application Access Control Page assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyExportAccessOfApplicationAccessControl() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Application Access Control", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToApplicationAccessControlPage();
		      ApplicationAccessControlPage modPageInst=PageFactory.createPageInstance(driver,ApplicationAccessControlPage.class);
		      Assert.assertTrue(modPageInst.isApplicationAccessControlPageDisplayed(), "Application Access Control Page assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfBulkUploadModule() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Bulk Upload Module", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToBulkUploadModulePage();
		      BulkUploadModulePage modPageInst=PageFactory.createPageInstance(driver,BulkUploadModulePage.class);
		      Assert.assertTrue(modPageInst.isBulkUploadModulePageDisplayed(), "Bulk Upload Module Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfBulkUploadModule() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Bulk Upload Module", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToBulkUploadModulePage();
		      BulkUploadModulePage modPageInst=PageFactory.createPageInstance(driver,BulkUploadModulePage.class);
		      Assert.assertTrue(modPageInst.isBulkUploadModulePageDisplayed(), "Bulk Upload Module Page assertion failed");
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfCallbackManagement() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Callback Management", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToCallbackManagementPage();
		      IvrCallbackManagementPage modPageInst=PageFactory.createPageInstance(driver,IvrCallbackManagementPage.class);
		      Assert.assertTrue(modPageInst.isCallbackManagementPageDisplayed(), "Callback Management Page assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfCallbackManagement() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Callback Management", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToCallbackManagementPage();
		      IvrCallbackManagementPage modPageInst=PageFactory.createPageInstance(driver,IvrCallbackManagementPage.class);
		      Assert.assertTrue(modPageInst.isCallbackManagementPageDisplayed(), "Callback Management Page assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfCMDataSync() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("CM Data Sync", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToCMDataSyncPage();
		      CMDataSyncPage modPageInst=PageFactory.createPageInstance(driver,CMDataSyncPage.class);
		      Assert.assertTrue(modPageInst.isCMDataSyncPageDisplayed(), "CM Data Sync Page assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfDynamicReportDesigner() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Dynamic Report Designer", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToDynamicReportDesignerPage();
		      DynamicReportDesignerPage modPageInst=PageFactory.createPageInstance(driver,DynamicReportDesignerPage.class);
		      Assert.assertTrue(modPageInst.isDynamicReportDesignerPageDisplayed(), "Dynamic Report Designer Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfDynamicReportDesigner() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Dynamic Report Designer", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToDynamicReportDesignerPage();
		      DynamicReportDesignerPage modPageInst=PageFactory.createPageInstance(driver,DynamicReportDesignerPage.class);
		      Assert.assertTrue(modPageInst.isDynamicReportDesignerPageDisplayed(), "Dynamic Report Designer Page assertion failed");
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfDynamicReportDesigner() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Dynamic Report Designer", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToDynamicReportDesignerPage();
		      DynamicReportDesignerPage modPageInst=PageFactory.createPageInstance(driver,DynamicReportDesignerPage.class);
		      Assert.assertTrue(modPageInst.isDynamicReportDesignerPageDisplayed(), "Dynamic Report Designer Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfDynamicReportDesigner() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Dynamic Report Designer", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToDynamicReportDesignerPage();
		      DynamicReportDesignerPage modPageInst=PageFactory.createPageInstance(driver,DynamicReportDesignerPage.class);
		      Assert.assertTrue(modPageInst.isDynamicReportDesignerPageDisplayed(), "Dynamic Report Designer Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfDynamicReportDesigner() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Dynamic Report Designer", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToDynamicReportDesignerPage();
		      DynamicReportDesignerPage modPageInst=PageFactory.createPageInstance(driver,DynamicReportDesignerPage.class);
		      Assert.assertTrue(modPageInst.isDynamicReportDesignerPageDisplayed(), "Dynamic Report Designer Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfEmailCallback() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Email Callback", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToEmailCallbackPage();
		      EmailCallbackPage modPageInst=PageFactory.createPageInstance(driver,EmailCallbackPage.class);
		      Assert.assertTrue(modPageInst.isEmailCallbackPageDisplayed(), "Email Callback Page assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyExportAccessOfEmailCallback() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Email Callback", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToEmailCallbackPage();
		      EmailCallbackPage modPageInst=PageFactory.createPageInstance(driver,EmailCallbackPage.class);
		      Assert.assertTrue(modPageInst.isEmailCallbackPageDisplayed(), "Email Callback Page assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfFaxApplicationForm() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Application Form", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToFaxApplicationFormPage();
		      FaxApplicationFormPage modPageInst=PageFactory.createPageInstance(driver,FaxApplicationFormPage.class);
		      Assert.assertTrue(modPageInst.isFaxApplicationFormPageDisplayed(), "Fax Application Form Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfFaxApplicationForm() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Application Form", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToFaxApplicationFormPage();
		      FaxApplicationFormPage modPageInst=PageFactory.createPageInstance(driver,FaxApplicationFormPage.class);
		      Assert.assertTrue(modPageInst.isFaxApplicationFormPageDisplayed(), "Fax Application Form Page assertion failed");
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfFaxApplicationForm() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Application Form", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToFaxApplicationFormPage();
		      FaxApplicationFormPage modPageInst=PageFactory.createPageInstance(driver,FaxApplicationFormPage.class);
		      Assert.assertTrue(modPageInst.isFaxApplicationFormPageDisplayed(), "Fax Application Form Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfFaxApplicationForm() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Application Form", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToFaxApplicationFormPage();
		      FaxApplicationFormPage modPageInst=PageFactory.createPageInstance(driver,FaxApplicationFormPage.class);
		      Assert.assertTrue(modPageInst.isFaxApplicationFormPageDisplayed(), "Fax Application Form Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfFaxApplicationForm() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Application Form", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToFaxApplicationFormPage();
		      FaxApplicationFormPage modPageInst=PageFactory.createPageInstance(driver,FaxApplicationFormPage.class);
		      Assert.assertTrue(modPageInst.isFaxApplicationFormPageDisplayed(), "Fax Application Form Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfFaxAutoACKConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Auto ACK Configuration", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToFaxAutoACKConfigurationPage();
		      FaxAutoACKConfigurationPage modPageInst=PageFactory.createPageInstance(driver,FaxAutoACKConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isFaxAutoACKConfigurationPageDisplayed(), "Fax Auto ACK Configuration Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfFaxAutoACKConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Auto ACK Configuration", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToFaxAutoACKConfigurationPage();
		      FaxAutoACKConfigurationPage modPageInst=PageFactory.createPageInstance(driver,FaxAutoACKConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isFaxAutoACKConfigurationPageDisplayed(), "Fax Auto ACK Configuration Page assertion failed");
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfFaxAutoACKConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Auto ACK Configuration", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToFaxAutoACKConfigurationPage();
		      FaxAutoACKConfigurationPage modPageInst=PageFactory.createPageInstance(driver,FaxAutoACKConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isFaxAutoACKConfigurationPageDisplayed(), "Fax Auto ACK Configuration Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfFaxAutoACKConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Auto ACK Configuration", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToFaxAutoACKConfigurationPage();
		      FaxAutoACKConfigurationPage modPageInst=PageFactory.createPageInstance(driver,FaxAutoACKConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isFaxAutoACKConfigurationPageDisplayed(), "Fax Auto ACK Configuration Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfFaxAutoACKConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Auto ACK Configuration", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToFaxAutoACKConfigurationPage();
		      FaxAutoACKConfigurationPage modPageInst=PageFactory.createPageInstance(driver,FaxAutoACKConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isFaxAutoACKConfigurationPageDisplayed(), "Fax Auto ACK Configuration Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfFaxRoutingConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Routing Configuration", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToFaxRoutingConfigurationPage();
		      FaxRoutingConfigurationPage modPageInst=PageFactory.createPageInstance(driver,FaxRoutingConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isFaxRoutingConfigurationPageDisplayed(), "Fax Routing Configuration Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfFaxRoutingConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Routing Configuration", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToFaxRoutingConfigurationPage();
		      FaxRoutingConfigurationPage modPageInst=PageFactory.createPageInstance(driver,FaxRoutingConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isFaxRoutingConfigurationPageDisplayed(), "Fax Routing Configuration Page assertion failed");
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfFaxRoutingConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Routing Configuration", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToFaxRoutingConfigurationPage();
		      FaxRoutingConfigurationPage modPageInst=PageFactory.createPageInstance(driver,FaxRoutingConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isFaxRoutingConfigurationPageDisplayed(), "Fax Routing Configuration Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfFaxRoutingConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Routing Configuration", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToFaxRoutingConfigurationPage();
		      FaxRoutingConfigurationPage modPageInst=PageFactory.createPageInstance(driver,FaxRoutingConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isFaxRoutingConfigurationPageDisplayed(), "Fax Routing Configuration Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfFaxRoutingConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Routing Configuration", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToFaxRoutingConfigurationPage();
		      FaxRoutingConfigurationPage modPageInst=PageFactory.createPageInstance(driver,FaxRoutingConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isFaxRoutingConfigurationPageDisplayed(), "Fax Routing Configuration Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfFaxTemplate() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Template", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToFaxTemplatePage();
		      FaxTemplatePage modPageInst=PageFactory.createPageInstance(driver,FaxTemplatePage.class);
		      Assert.assertTrue(modPageInst.isFaxTemplatePageDisplayed(), "Fax Template Page assertion failed");
		      modPageInst.navigateToTab("Fax Template Audit Trail");
		      Assert.assertFalse(modPageInst.isMakeFaxTemplateChangesButtonDisplayed(), "View assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfFaxTemplate() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Template", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToFaxTemplatePage();
		      FaxTemplatePage modPageInst=PageFactory.createPageInstance(driver,FaxTemplatePage.class);
		      Assert.assertTrue(modPageInst.isFaxTemplatePageDisplayed(), "Fax Template Page assertion failed");
		      modPageInst.navigateToTab("Fax Template Audit Trail");
		      modPageInst.clickOnMakeFaxTemplateChanges();
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfFaxTemplate() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Template", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToFaxTemplatePage();
		      FaxTemplatePage modPageInst=PageFactory.createPageInstance(driver,FaxTemplatePage.class);
		      Assert.assertTrue(modPageInst.isFaxTemplatePageDisplayed(), "Fax Template Page assertion failed");
		      modPageInst.navigateToTab("Fax Template Audit Trail");
		      modPageInst.clickOnMakeFaxTemplateChanges();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfFaxTemplate() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Template", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToFaxTemplatePage();
		      FaxTemplatePage modPageInst=PageFactory.createPageInstance(driver,FaxTemplatePage.class);
		      Assert.assertTrue(modPageInst.isFaxTemplatePageDisplayed(), "Fax Template Page assertion failed");
		      modPageInst.navigateToTab("Fax Template Audit Trail");
		      modPageInst.clickOnMakeFaxTemplateChanges();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfFaxTemplate() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Fax Template", "Delete");
		     userManagementPage.ProvideAccess("Fax Template", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToFaxTemplatePage();
		      FaxTemplatePage modPageInst=PageFactory.createPageInstance(driver,FaxTemplatePage.class);
		      Assert.assertTrue(modPageInst.isFaxTemplatePageDisplayed(), "Fax Template Page assertion failed");
		      modPageInst.navigateToTab("Fax Template Audit Trail");
		      modPageInst.clickOnMakeFaxTemplateChanges();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfFeeWaiver() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("FeeWaiver", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToFeeWaiverPage();
		      FeeWaiverPage modPageInst=PageFactory.createPageInstance(driver,FeeWaiverPage.class);
		      Assert.assertTrue(modPageInst.isFeeWaiverPageDisplayed(), "Fee Waiver Page assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyExportAccessOfFeeWaiver() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("FeeWaiver", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToFeeWaiverPage();
		      FeeWaiverPage modPageInst=PageFactory.createPageInstance(driver,FeeWaiverPage.class);
		      Assert.assertTrue(modPageInst.isFeeWaiverPageDisplayed(), "Fee Waiver Page assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfHostValueMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Host Value Mapping", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToHostValueMappingPage();
		      HostValueMappingPage modPageInst=PageFactory.createPageInstance(driver,HostValueMappingPage.class);
		      Assert.assertTrue(modPageInst.isHostValueMappingPageDisplayed(), "Host Value Mapping Page assertion failed");
		      modPageInst.navigateToTab("Host Value Mapping Audit Trail");
		      Assert.assertFalse(modPageInst.isMakeHostValueMappingChangesButtonDisplayed(), "View assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfHostValueMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Host Value Mapping", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToHostValueMappingPage();
		      HostValueMappingPage modPageInst=PageFactory.createPageInstance(driver,HostValueMappingPage.class);
		      Assert.assertTrue(modPageInst.isHostValueMappingPageDisplayed(), "Host Value Mapping Page assertion failed");
		      modPageInst.navigateToTab("Host Value Mapping Audit Trail");
		      modPageInst.clickOnMakeHostValueMappingChanges();
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfHostValueMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Host Value Mapping", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToHostValueMappingPage();
		      HostValueMappingPage modPageInst=PageFactory.createPageInstance(driver,HostValueMappingPage.class);
		      Assert.assertTrue(modPageInst.isHostValueMappingPageDisplayed(), "Host Value Mapping Page assertion failed");
		      modPageInst.navigateToTab("Host Value Mapping Audit Trail");
		      modPageInst.clickOnMakeHostValueMappingChanges();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfHostValueMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Host Value Mapping", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToHostValueMappingPage();
		      HostValueMappingPage modPageInst=PageFactory.createPageInstance(driver,HostValueMappingPage.class);
		      Assert.assertTrue(modPageInst.isHostValueMappingPageDisplayed(), "Host Value Mapping Page assertion failed");
		      modPageInst.navigateToTab("Host Value Mapping Audit Trail");
		      modPageInst.clickOnMakeHostValueMappingChanges();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfHostValueMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Host Value Mapping", "Add");
		     userManagementPage.ProvideAccess("Host Value Mapping", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToHostValueMappingPage();
		      HostValueMappingPage modPageInst=PageFactory.createPageInstance(driver,HostValueMappingPage.class);
		      Assert.assertTrue(modPageInst.isHostValueMappingPageDisplayed(), "Host Value Mapping Page assertion failed");
		      modPageInst.navigateToTab("Host Value Mapping Audit Trail");
		      modPageInst.clickOnMakeHostValueMappingChanges();
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfIntroMessageAnnouncement() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Intro Message Announcement", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToIntroMessageAnnouncementPage();
		      IntroMessageAnnouncementPage modPageInst=PageFactory.createPageInstance(driver,IntroMessageAnnouncementPage.class);
		      Assert.assertTrue(modPageInst.isIntroMessageAnnouncementPageDisplayed(), "Intro Message Announcement Page assertion failed");
		      modPageInst.navigateToTab("Intro Message Announcement Audit Trail");
		      Assert.assertFalse(modPageInst.isMakeIntroMessageAnnouncementChangesButtonDisplayed(), "View assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfIntroMessageAnnouncement() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Intro Message Announcement", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToIntroMessageAnnouncementPage();
		      IntroMessageAnnouncementPage modPageInst=PageFactory.createPageInstance(driver,IntroMessageAnnouncementPage.class);
		      Assert.assertTrue(modPageInst.isIntroMessageAnnouncementPageDisplayed(), "Intro Message Announcement Page assertion failed");
		      modPageInst.navigateToTab("Intro Message Announcement Audit Trail");
		      modPageInst.clickOnIntroMsgannouncementRecordBtn();
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfIntroMessageAnnouncement() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Intro Message Announcement", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToIntroMessageAnnouncementPage();
		      IntroMessageAnnouncementPage modPageInst=PageFactory.createPageInstance(driver,IntroMessageAnnouncementPage.class);
		      Assert.assertTrue(modPageInst.isIntroMessageAnnouncementPageDisplayed(), "Intro Message Announcement Page assertion failed");
		      modPageInst.navigateToTab("Intro Message Announcement Audit Trail");
		      modPageInst.clickOnIntroMsgannouncementRecordBtn();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfIntroMessageAnnouncement() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Intro Message Announcement", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToIntroMessageAnnouncementPage();
		      IntroMessageAnnouncementPage modPageInst=PageFactory.createPageInstance(driver,IntroMessageAnnouncementPage.class);
		      Assert.assertTrue(modPageInst.isIntroMessageAnnouncementPageDisplayed(), "Intro Message Announcement Page assertion failed");
		      modPageInst.navigateToTab("Intro Message Announcement Audit Trail");
		      modPageInst.clickOnIntroMsgannouncementRecordBtn();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfIntroMessageAnnouncement() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Intro Message Announcement", "Edit");
		     userManagementPage.ProvideAccess("Intro Message Announcement", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToIntroMessageAnnouncementPage();
		      IntroMessageAnnouncementPage modPageInst=PageFactory.createPageInstance(driver,IntroMessageAnnouncementPage.class);
		      Assert.assertTrue(modPageInst.isIntroMessageAnnouncementPageDisplayed(), "Intro Message Announcement Page assertion failed");
		      modPageInst.navigateToTab("Intro Message Announcement Audit Trail");
		      modPageInst.clickOnIntroMsgannouncementRecordBtn();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfIWRoleBasedAccessMatrix() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("IW Role Based Access Matrix", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToIWRoleBasedAccessMatrixPage();
		      IWRoleBasedAccessMatrixPage modPageInst=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		      Assert.assertTrue(modPageInst.isIWRoleBasedAccessMatrixPageDisplayed(), "IW Role Based Access Matrix Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddRecdButtonDisplayed(), "View assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfIWRoleBasedAccessMatrix() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("IW Role Based Access Matrix", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToIWRoleBasedAccessMatrixPage();
		      IWRoleBasedAccessMatrixPage modPageInst=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		      Assert.assertTrue(modPageInst.isIWRoleBasedAccessMatrixPageDisplayed(), "IW Role Based Access Matrix Page assertion failed");
		      modPageInst.clickOnAddRecdBtn();
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfIWRoleBasedAccessMatrix() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("IW Role Based Access Matrix", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToIWRoleBasedAccessMatrixPage();
		      IWRoleBasedAccessMatrixPage modPageInst=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		      Assert.assertTrue(modPageInst.isIWRoleBasedAccessMatrixPageDisplayed(), "IW Role Based Access Matrix Page assertion failed");
		      modPageInst.clickOnAddRecdBtn();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfIWRoleBasedAccessMatrix() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("IW Role Based Access Matrix", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToIWRoleBasedAccessMatrixPage();
		      IWRoleBasedAccessMatrixPage modPageInst=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		      Assert.assertTrue(modPageInst.isIWRoleBasedAccessMatrixPageDisplayed(), "IW Role Based Access Matrix Page assertion failed");
		      modPageInst.clickOnAddRecdBtn();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfIWRoleBasedAccessMatrix() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("IW Role Based Access Matrix", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToIWRoleBasedAccessMatrixPage();
		      IWRoleBasedAccessMatrixPage modPageInst=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		      Assert.assertTrue(modPageInst.isIWRoleBasedAccessMatrixPageDisplayed(), "IW Role Based Access Matrix Page assertion failed");
		      modPageInst.clickOnAddRecdBtn();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfModuleExitNodeMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Module Exit Node Mapping", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToModuleExitNodeMappingPage();
		      ModuleExitNodeMappingPage modPageInst=PageFactory.createPageInstance(driver,ModuleExitNodeMappingPage.class);
		      Assert.assertTrue(modPageInst.isModuleExitNodeMappingPageDisplayed(), "Module Exit Node Mapping Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfModuleExitNodeMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Module Exit Node Mapping", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToModuleExitNodeMappingPage();
		      ModuleExitNodeMappingPage modPageInst=PageFactory.createPageInstance(driver,ModuleExitNodeMappingPage.class);
		      Assert.assertTrue(modPageInst.isModuleExitNodeMappingPageDisplayed(), "Module Exit Node Mapping Page assertion failed");
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfModuleExitNodeMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Module Exit Node Mapping", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToModuleExitNodeMappingPage();
		      ModuleExitNodeMappingPage modPageInst=PageFactory.createPageInstance(driver,ModuleExitNodeMappingPage.class);
		      Assert.assertTrue(modPageInst.isModuleExitNodeMappingPageDisplayed(), "Module Exit Node Mapping Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfModuleExitNodeMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Module Exit Node Mapping", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToModuleExitNodeMappingPage();
		      ModuleExitNodeMappingPage modPageInst=PageFactory.createPageInstance(driver,ModuleExitNodeMappingPage.class);
		      Assert.assertTrue(modPageInst.isModuleExitNodeMappingPageDisplayed(), "Module Exit Node Mapping Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfModuleExitNodeMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Module Exit Node Mapping", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToModuleExitNodeMappingPage();
		      ModuleExitNodeMappingPage modPageInst=PageFactory.createPageInstance(driver,ModuleExitNodeMappingPage.class);
		      Assert.assertTrue(modPageInst.isModuleExitNodeMappingPageDisplayed(), "Module Exit Node Mapping Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyViewAccessOfReportDesigner() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Report Designer", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToReportDesignerPage();
		      ReportDesignerPage modPageInst=PageFactory.createPageInstance(driver,ReportDesignerPage.class);
		      Assert.assertTrue(modPageInst.isReportDesignerPageDisplayed(), "Report Designer Page assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfReportDownloads() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Report Downloads", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToReportDownloadsPage();
		      ReportDownloadsPage modPageInst=PageFactory.createPageInstance(driver,ReportDownloadsPage.class);
		      Assert.assertTrue(modPageInst.isReportDownloadsPageDisplayed(), "Report Downloads Page assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfReportDownloads() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Report Downloads", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToReportDownloadsPage();
		      ReportDownloadsPage modPageInst=PageFactory.createPageInstance(driver,ReportDownloadsPage.class);
		      Assert.assertTrue(modPageInst.isReportDownloadsPageDisplayed(), "Report Downloads Page assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfReportDownloads() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Report Downloads", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToReportDownloadsPage();
		      ReportDownloadsPage modPageInst=PageFactory.createPageInstance(driver,ReportDownloadsPage.class);
		      Assert.assertTrue(modPageInst.isReportDownloadsPageDisplayed(), "Report Downloads Page assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfReportScheduler() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Report Scheduler", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToReportSchedulerPage();
		      ReportSchedulerPage modPageInst=PageFactory.createPageInstance(driver,ReportSchedulerPage.class);
		      Assert.assertTrue(modPageInst.isReportSchedulerPageDisplayed(), "Report Scheduler Page assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfSendFax() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Send Fax", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToSendFaxPage();
		      SendFaxPage modPageInst=PageFactory.createPageInstance(driver,SendFaxPage.class);
		      Assert.assertTrue(modPageInst.isSendFaxPageDisplayed(), "Send Fax Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfSendFax() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Send Fax", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToSendFaxPage();
		      SendFaxPage modPageInst=PageFactory.createPageInstance(driver,SendFaxPage.class);
		      Assert.assertTrue(modPageInst.isSendFaxPageDisplayed(), "Send Fax Page assertion failed");
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfSendFax() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Send Fax", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToSendFaxPage();
		      SendFaxPage modPageInst=PageFactory.createPageInstance(driver,SendFaxPage.class);
		      Assert.assertTrue(modPageInst.isSendFaxPageDisplayed(), "Send Fax Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfSkillConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("SkillConfiguration", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToSkillConfigurationPage();
		      SkillConfigurationPage modPageInst=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isSkillConfigurationPageDisplayed(), "SkillConfiguration Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfSkillConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("SkillConfiguration", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToSkillConfigurationPage();
		      SkillConfigurationPage modPageInst=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isSkillConfigurationPageDisplayed(), "SkillConfiguration Page assertion failed");
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfSkillConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("SkillConfiguration", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToSkillConfigurationPage();
		      SkillConfigurationPage modPageInst=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isSkillConfigurationPageDisplayed(), "SkillConfiguration Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfSkillConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("SkillConfiguration", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToSkillConfigurationPage();
		      SkillConfigurationPage modPageInst=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isSkillConfigurationPageDisplayed(), "SkillConfiguration Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfSkillConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("SkillConfiguration", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToSkillConfigurationPage();
		      SkillConfigurationPage modPageInst=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isSkillConfigurationPageDisplayed(), "SkillConfiguration Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfSMSResponseTemplate() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("SMS Response Template", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("SMS");
		      SmsPage smsPage=PageFactory.createPageInstance(driver,SmsPage.class);
		      smsPage.navigateToSMSResponseTemplatePage();
		      SMSResponseTemplatePage modPageInst=PageFactory.createPageInstance(driver,SMSResponseTemplatePage.class);
		      Assert.assertTrue(modPageInst.isSMSResponseTemplatePageDisplayed(), "SMS Response Template Page assertion failed");
		      modPageInst.navigateToTab("SMS Response Template Audit Trail");
		      Assert.assertFalse(modPageInst.isMakeChangesBtnDisplayed(), "View assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfSMSResponseTemplate() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("SMS Response Template", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("SMS");
		      SmsPage smsPage=PageFactory.createPageInstance(driver,SmsPage.class);
		      smsPage.navigateToSMSResponseTemplatePage();
		      SMSResponseTemplatePage modPageInst=PageFactory.createPageInstance(driver,SMSResponseTemplatePage.class);
		      Assert.assertTrue(modPageInst.isSMSResponseTemplatePageDisplayed(), "SMS Response Template Page assertion failed");
		      modPageInst.navigateToTab("SMS Response Template Audit Trail");
		      Assert.assertFalse(modPageInst.isMakeChangesBtnDisplayed(), "View assertion failed");
		      modPageInst.selectMakeChangesBtn();
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfSMSResponseTemplate() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("SMS Response Template", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("SMS");
		      SmsPage smsPage=PageFactory.createPageInstance(driver,SmsPage.class);
		      smsPage.navigateToSMSResponseTemplatePage();
		      SMSResponseTemplatePage modPageInst=PageFactory.createPageInstance(driver,SMSResponseTemplatePage.class);
		      Assert.assertTrue(modPageInst.isSMSResponseTemplatePageDisplayed(), "SMS Response Template Page assertion failed");
		      modPageInst.navigateToTab("SMS Response Template Audit Trail");
		      Assert.assertFalse(modPageInst.isMakeChangesBtnDisplayed(), "View assertion failed");
		      modPageInst.selectMakeChangesBtn();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfSMSResponseTemplate() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("SMS Response Template", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("SMS");
		      SmsPage smsPage=PageFactory.createPageInstance(driver,SmsPage.class);
		      smsPage.navigateToSMSResponseTemplatePage();
		      SMSResponseTemplatePage modPageInst=PageFactory.createPageInstance(driver,SMSResponseTemplatePage.class);
		      Assert.assertTrue(modPageInst.isSMSResponseTemplatePageDisplayed(), "SMS Response Template Page assertion failed");
		      modPageInst.navigateToTab("SMS Response Template Audit Trail");
		      Assert.assertFalse(modPageInst.isMakeChangesBtnDisplayed(), "View assertion failed");
		      modPageInst.selectMakeChangesBtn();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfSMSResponseTemplate() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("SMS Response Template", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("SMS");
		      SmsPage smsPage=PageFactory.createPageInstance(driver,SmsPage.class);
		      smsPage.navigateToSMSResponseTemplatePage();
		      SMSResponseTemplatePage modPageInst=PageFactory.createPageInstance(driver,SMSResponseTemplatePage.class);
		      Assert.assertTrue(modPageInst.isSMSResponseTemplatePageDisplayed(), "SMS Response Template Page assertion failed");
		      modPageInst.navigateToTab("SMS Response Template Audit Trail");
		      Assert.assertFalse(modPageInst.isMakeChangesBtnDisplayed(), "View assertion failed");
		      modPageInst.selectMakeChangesBtn();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfSpeechModule() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Speech Module", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToSpeechModulePage();
		      SpeechModulePage modPageInst=PageFactory.createPageInstance(driver,SpeechModulePage.class);
		      Assert.assertTrue(modPageInst.isSpeechModulePageDisplayed(), "Speech Module Page assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyExportAccessOfSpeechModule() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Speech Module", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToSpeechModulePage();
		      SpeechModulePage modPageInst=PageFactory.createPageInstance(driver,SpeechModulePage.class);
		      Assert.assertTrue(modPageInst.isSpeechModulePageDisplayed(), "Speech Module Page assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfTextSynonyms() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Text Synonyms", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("SMS");
		      SmsPage smsPage=PageFactory.createPageInstance(driver,SmsPage.class);
		      smsPage.navigateToTextSynonymsPage();
		      TextSynonymsPage modPageInst=PageFactory.createPageInstance(driver,TextSynonymsPage.class);
		      Assert.assertTrue(modPageInst.isTextSynonymsPageDisplayed(), "Text Synonyms Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfTextSynonyms() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Text Synonyms", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("SMS");
		      SmsPage smsPage=PageFactory.createPageInstance(driver,SmsPage.class);
		      smsPage.navigateToTextSynonymsPage();
		      TextSynonymsPage modPageInst=PageFactory.createPageInstance(driver,TextSynonymsPage.class);
		      Assert.assertTrue(modPageInst.isTextSynonymsPageDisplayed(), "Text Synonyms Page assertion failed");
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfTextSynonyms() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Text Synonyms", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("SMS");
		      SmsPage smsPage=PageFactory.createPageInstance(driver,SmsPage.class);
		      smsPage.navigateToTextSynonymsPage();
		      TextSynonymsPage modPageInst=PageFactory.createPageInstance(driver,TextSynonymsPage.class);
		      Assert.assertTrue(modPageInst.isTextSynonymsPageDisplayed(), "Text Synonyms Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfTextSynonyms() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Text Synonyms", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("SMS");
		      SmsPage smsPage=PageFactory.createPageInstance(driver,SmsPage.class);
		      smsPage.navigateToTextSynonymsPage();
		      TextSynonymsPage modPageInst=PageFactory.createPageInstance(driver,TextSynonymsPage.class);
		      Assert.assertTrue(modPageInst.isTextSynonymsPageDisplayed(), "Text Synonyms Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfTextSynonyms() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Text Synonyms", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("SMS");
		      SmsPage smsPage=PageFactory.createPageInstance(driver,SmsPage.class);
		      smsPage.navigateToTextSynonymsPage();
		      TextSynonymsPage modPageInst=PageFactory.createPageInstance(driver,TextSynonymsPage.class);
		      Assert.assertTrue(modPageInst.isTextSynonymsPageDisplayed(), "Text Synonyms Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfTMACTransferList() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("TMAC Transfer List", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("TMAC");
		      TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		      tmacPage.navigateToTmacTranferListPage();
		      TmacTransferListPage modPageInst=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
		      Assert.assertTrue(modPageInst.isTmacTransferListPageDisplayed(), "TMAC Transfer List Page assertion failed");
		      modPageInst.navigateToTab("TMAC Consult Transfer");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfTMACTransferList() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("TMAC Transfer List", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("TMAC");
		      TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		      tmacPage.navigateToTmacTranferListPage();
		      TmacTransferListPage modPageInst=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
		      Assert.assertTrue(modPageInst.isTmacTransferListPageDisplayed(), "TMAC Transfer List Page assertion failed");
		      modPageInst.navigateToTab("TMAC Consult Transfer");
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfTMACTransferList() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("TMAC Transfer List", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("TMAC");
		      TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		      tmacPage.navigateToTmacTranferListPage();
		      TmacTransferListPage modPageInst=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
		      Assert.assertTrue(modPageInst.isTmacTransferListPageDisplayed(), "TMAC Transfer List Page assertion failed");
		      modPageInst.navigateToTab("TMAC Consult Transfer");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfTMACTransferList() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("TMAC Transfer List", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("TMAC");
		      TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		      tmacPage.navigateToTmacTranferListPage();
		      TmacTransferListPage modPageInst=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
		      Assert.assertTrue(modPageInst.isTmacTransferListPageDisplayed(), "TMAC Transfer List Page assertion failed");
		      modPageInst.navigateToTab("TMAC Consult Transfer");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfTMACTransferList() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("TMAC Transfer List", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("TMAC");
		      TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
		      tmacPage.navigateToTmacTranferListPage();
		      TmacTransferListPage modPageInst=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
		      Assert.assertTrue(modPageInst.isTmacTransferListPageDisplayed(), "TMAC Transfer List Page assertion failed");
		      modPageInst.navigateToTab("TMAC Consult Transfer");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfTPRedemption() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("TP Redemption", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToTPRedemptionPage();
		      TPRedemptionPage modPageInst=PageFactory.createPageInstance(driver,TPRedemptionPage.class);
		      Assert.assertTrue(modPageInst.isTPRedemptionPageDisplayed(), "TP Redemption Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfTPRedemption() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("TP Redemption", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToTPRedemptionPage();
		      TPRedemptionPage modPageInst=PageFactory.createPageInstance(driver,TPRedemptionPage.class);
		      Assert.assertTrue(modPageInst.isTPRedemptionPageDisplayed(), "TP Redemption Page assertion failed");
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfTPRedemption() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("TP Redemption", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToTPRedemptionPage();
		      TPRedemptionPage modPageInst=PageFactory.createPageInstance(driver,TPRedemptionPage.class);
		      Assert.assertTrue(modPageInst.isTPRedemptionPageDisplayed(), "TP Redemption Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfTPRedemption() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("TP Redemption", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToTPRedemptionPage();
		      TPRedemptionPage modPageInst=PageFactory.createPageInstance(driver,TPRedemptionPage.class);
		      Assert.assertTrue(modPageInst.isTPRedemptionPageDisplayed(), "TP Redemption Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfTPRedemption() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("TP Redemption", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToTPRedemptionPage();
		      TPRedemptionPage modPageInst=PageFactory.createPageInstance(driver,TPRedemptionPage.class);
		      Assert.assertTrue(modPageInst.isTPRedemptionPageDisplayed(), "TP Redemption Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfUserRoleMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("User Role Mapping", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToUserRoleMappingPage();
		      UserRoleMappingPage modPageInst=PageFactory.createPageInstance(driver,UserRoleMappingPage.class);
		      Assert.assertTrue(modPageInst.isUserRoleMappingPageDisplayed(), "User Role Mapping Page assertion failed");
		      modPageInst.navigateToTab("User Role Mapping Audit Trail");
		      Assert.assertFalse(modPageInst.isMakeChangesBtnDisplayed(), "View assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfUserRoleMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("User Role Mapping", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToUserRoleMappingPage();
		      UserRoleMappingPage modPageInst=PageFactory.createPageInstance(driver,UserRoleMappingPage.class);
		      Assert.assertTrue(modPageInst.isUserRoleMappingPageDisplayed(), "User Role Mapping Page assertion failed");
		      modPageInst.navigateToTab("User Role Mapping Audit Trail");
		      modPageInst.clickOnMakeChangesBtn();
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfUserRoleMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("User Role Mapping", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToUserRoleMappingPage();
		      UserRoleMappingPage modPageInst=PageFactory.createPageInstance(driver,UserRoleMappingPage.class);
		      Assert.assertTrue(modPageInst.isUserRoleMappingPageDisplayed(), "User Role Mapping Page assertion failed");
		      modPageInst.navigateToTab("User Role Mapping Audit Trail");
		      modPageInst.clickOnMakeChangesBtn();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfUserRoleMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("User Role Mapping", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToUserRoleMappingPage();
		      UserRoleMappingPage modPageInst=PageFactory.createPageInstance(driver,UserRoleMappingPage.class);
		      Assert.assertTrue(modPageInst.isUserRoleMappingPageDisplayed(), "User Role Mapping Page assertion failed");
		      modPageInst.navigateToTab("User Role Mapping Audit Trail");
		      modPageInst.clickOnMakeChangesBtn();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfUserRoleMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("User Role Mapping", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToUserRoleMappingPage();
		      UserRoleMappingPage modPageInst=PageFactory.createPageInstance(driver,UserRoleMappingPage.class);
		      Assert.assertTrue(modPageInst.isUserRoleMappingPageDisplayed(), "User Role Mapping Page assertion failed");
		      modPageInst.navigateToTab("User Role Mapping Audit Trail");
		      modPageInst.clickOnMakeChangesBtn();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfWebConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Web Configuration", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToWebConfigurationPage();
		      WebConfigurationPage modPageInst=PageFactory.createPageInstance(driver,WebConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isWebConfigurationPageDisplayed(), "Web Configuration Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfWebConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Web Configuration", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToWebConfigurationPage();
		      WebConfigurationPage modPageInst=PageFactory.createPageInstance(driver,WebConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isWebConfigurationPageDisplayed(), "Web Configuration Page assertion failed");
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfWebConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Web Configuration", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToWebConfigurationPage();
		      WebConfigurationPage modPageInst=PageFactory.createPageInstance(driver,WebConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isWebConfigurationPageDisplayed(), "Web Configuration Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfWebConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Web Configuration", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToWebConfigurationPage();
		      WebConfigurationPage modPageInst=PageFactory.createPageInstance(driver,WebConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isWebConfigurationPageDisplayed(), "Web Configuration Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfWebConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Web Configuration", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToWebConfigurationPage();
		      WebConfigurationPage modPageInst=PageFactory.createPageInstance(driver,WebConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isWebConfigurationPageDisplayed(), "Web Configuration Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfWorkFlowDesigner() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("WorkFlow Designer", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("HOME");
		      ocmHomePage.navigateToWorkFlowDesignerPage();
		      WorkFlowDesignerPage modPageInst=PageFactory.createPageInstance(driver,WorkFlowDesignerPage.class);
		      Assert.assertTrue(modPageInst.isWorkFlowDesignerPageDisplayed(), "WorkFlow Designer Page assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfChatConfigurations() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Chat Configurations", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("CHAT");
		      ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
		      chatPage.navigateToChatConfigurationsPage();
		      ChatConfigurationsPage modPageInst=PageFactory.createPageInstance(driver,ChatConfigurationsPage.class);
		      Assert.assertTrue(modPageInst.isChatConfigurationsPageDisplayed(), "Chat Configurations Page assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfSplitFax() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Split Fax", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToSplitFaxPage();
		      SplitFaxPage modPageInst=PageFactory.createPageInstance(driver,SplitFaxPage.class);
		      Assert.assertTrue(modPageInst.isSplitFaxPageDisplayed(), "Split Fax Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfSplitFax() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Split Fax", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToSplitFaxPage();
		      SplitFaxPage modPageInst=PageFactory.createPageInstance(driver,SplitFaxPage.class);
		      Assert.assertTrue(modPageInst.isSplitFaxPageDisplayed(), "Split Fax Page assertion failed");
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfSplitFax() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Split Fax", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("FAX");
		      FaxPage faxPage=PageFactory.createPageInstance(driver,FaxPage.class);
		      faxPage.navigateToSplitFaxPage();
		      SplitFaxPage modPageInst=PageFactory.createPageInstance(driver,SplitFaxPage.class);
		      Assert.assertTrue(modPageInst.isSplitFaxPageDisplayed(), "Split Fax Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfSMSAlert() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("SMS Alert", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("SMS");
		      SmsPage smsPage=PageFactory.createPageInstance(driver,SmsPage.class);
		      smsPage.navigateToSMSAlertPage();
		      SMSAlertPage modPageInst=PageFactory.createPageInstance(driver,SMSAlertPage.class);
		      Assert.assertTrue(modPageInst.isSMSAlertPageDisplayed(), "SMS Alert Page assertion failed");
		      modPageInst.navigateToTab("SMS Alert Audit Trail");
		      Assert.assertFalse(modPageInst.isMakeChangesBtnDisplayed(), "View button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfSMSAlert() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("SMS Alert", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("SMS");
		      SmsPage smsPage=PageFactory.createPageInstance(driver,SmsPage.class);
		      smsPage.navigateToSMSAlertPage();
		      SMSAlertPage modPageInst=PageFactory.createPageInstance(driver,SMSAlertPage.class);
		      Assert.assertTrue(modPageInst.isSMSAlertPageDisplayed(), "SMS Alert Page assertion failed");
		      modPageInst.navigateToTab("SMS Alert Audit Trail");
		      modPageInst.clickOnMakeChangesBtn();
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfSMSAlert() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("SMS Alert", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("SMS");
		      SmsPage smsPage=PageFactory.createPageInstance(driver,SmsPage.class);
		      smsPage.navigateToSMSAlertPage();
		      SMSAlertPage modPageInst=PageFactory.createPageInstance(driver,SMSAlertPage.class);
		      Assert.assertTrue(modPageInst.isSMSAlertPageDisplayed(), "SMS Alert Page assertion failed");
		      modPageInst.navigateToTab("SMS Alert Audit Trail");
		      modPageInst.clickOnMakeChangesBtn();
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfCSOSurveyQA() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("CSO Survey QA", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToCSOSurveyQAPage();
		      CSOSurveyQAPage modPageInst=PageFactory.createPageInstance(driver,CSOSurveyQAPage.class);
		      Assert.assertTrue(modPageInst.isCSOSurveyQAPageDisplayed(), "CSO Survey QA Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfCSOSurveyQA() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("CSO Survey QA", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToCSOSurveyQAPage();
		      CSOSurveyQAPage modPageInst=PageFactory.createPageInstance(driver,CSOSurveyQAPage.class);
		      Assert.assertTrue(modPageInst.isCSOSurveyQAPageDisplayed(), "CSO Survey QA Page assertion failed");
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfCSOSurveyQA() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("CSO Survey QA", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToCSOSurveyQAPage();
		      CSOSurveyQAPage modPageInst=PageFactory.createPageInstance(driver,CSOSurveyQAPage.class);
		      Assert.assertTrue(modPageInst.isCSOSurveyQAPageDisplayed(), "CSO Survey QA Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfCSOSurveyQA() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("CSO Survey QA", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToCSOSurveyQAPage();
		      CSOSurveyQAPage modPageInst=PageFactory.createPageInstance(driver,CSOSurveyQAPage.class);
		      Assert.assertTrue(modPageInst.isCSOSurveyQAPageDisplayed(), "CSO Survey QA Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfCSOSurveyQA() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("CSO Survey QA", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToCSOSurveyQAPage();
		      CSOSurveyQAPage modPageInst=PageFactory.createPageInstance(driver,CSOSurveyQAPage.class);
		      Assert.assertTrue(modPageInst.isCSOSurveyQAPageDisplayed(), "CSO Survey QA Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfOrderTakeConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Order Take Configuration", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToOrderTakeConfigurationPage();
		      OrderTakeConfigurationPage modPageInst=PageFactory.createPageInstance(driver,OrderTakeConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isOrderTakeConfigurationPageDisplayed(), "Order Take Configuration Page assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyExportAccessOfOrderTakeConfiguration() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Order Take Configuration", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToOrderTakeConfigurationPage();
		      OrderTakeConfigurationPage modPageInst=PageFactory.createPageInstance(driver,OrderTakeConfigurationPage.class);
		      Assert.assertTrue(modPageInst.isOrderTakeConfigurationPageDisplayed(), "Order Take Configuration Page assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfIVRSurveyQA() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("IVR Survey QA", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToIVRSurveyQAPage();
		      IVRSurveyQAPage modPageInst=PageFactory.createPageInstance(driver,IVRSurveyQAPage.class);
		      Assert.assertTrue(modPageInst.isIVRSurveyQAPageDisplayed(), "IVR Survey QA Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfIVRSurveyQA() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("IVR Survey QA", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToIVRSurveyQAPage();
		      IVRSurveyQAPage modPageInst=PageFactory.createPageInstance(driver,IVRSurveyQAPage.class);
		      Assert.assertTrue(modPageInst.isIVRSurveyQAPageDisplayed(), "IVR Survey QA Page assertion failed");
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfIVRSurveyQA() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("IVR Survey QA", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToIVRSurveyQAPage();
		      IVRSurveyQAPage modPageInst=PageFactory.createPageInstance(driver,IVRSurveyQAPage.class);
		      Assert.assertTrue(modPageInst.isIVRSurveyQAPageDisplayed(), "IVR Survey QA Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfLogfileDownloader() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Logfile Downloader", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("Home");
		      ocmHomePage.navigateToLogfileDownloader();
		      LogfileDownloaderPage modPageInst=PageFactory.createPageInstance(driver,LogfileDownloaderPage.class);
		      Assert.assertTrue(modPageInst.isLogFileDownloaderPageDisplayed(), "Logfile Downloader Page assertion failed");
		      homePage.userLogout();
		      driver.close();
		}
		
		//@Test
		public void VerifyViewAccessOfMenuDescriptionMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Menu Description Mapping", "View");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToMenuDescriptionMappingPage();
		      MenuDescriptionMappingPage modPageInst=PageFactory.createPageInstance(driver,MenuDescriptionMappingPage.class);
		      Assert.assertTrue(modPageInst.isMenuDescriptionMappingPageDisplayed(), "Menu Description Mapping Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyAddAccessOfMenuDescriptionMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Menu Description Mapping", "Add");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToMenuDescriptionMappingPage();
		      MenuDescriptionMappingPage modPageInst=PageFactory.createPageInstance(driver,MenuDescriptionMappingPage.class);
		      Assert.assertTrue(modPageInst.isMenuDescriptionMappingPageDisplayed(), "Menu Description Mapping Page assertion failed");
		      Assert.assertTrue(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyEditAccessOfMenuDescriptionMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Menu Description Mapping", "Edit");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToMenuDescriptionMappingPage();
		      MenuDescriptionMappingPage modPageInst=PageFactory.createPageInstance(driver,MenuDescriptionMappingPage.class);
		      Assert.assertTrue(modPageInst.isMenuDescriptionMappingPageDisplayed(), "Menu Description Mapping Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertTrue(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyDeleteAccessOfMenuDescriptionMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Menu Description Mapping", "Delete");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToMenuDescriptionMappingPage();
		      MenuDescriptionMappingPage modPageInst=PageFactory.createPageInstance(driver,MenuDescriptionMappingPage.class);
		      Assert.assertTrue(modPageInst.isMenuDescriptionMappingPageDisplayed(), "Menu Description Mapping Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertTrue(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertFalse(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

		//@Test
		public void VerifyExportAccessOfMenuDescriptionMapping() throws Exception
		{
		     UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		     userManagementPage.ProvideAccess("Menu Description Mapping", "Export");
		     Thread.sleep(2000);
		     driver.close();
		     try {
		             PageFactory.reset();
		             BrowserFactory browserFactory = new BrowserFactory();
		             driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		             String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		             Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
		             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
		             if(map.get("LoginType").equals("Custom")){
		                 LoginPage loginPage=new LoginPage(driver);
		                 loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
		                 Thread.sleep(5000);
		              }
		      }catch (Exception e){
		         PageFactory.reset();
		         driver.close();
		         e.printStackTrace();
		      }
		      try {
		             Thread.sleep(2000);
		          }catch(InterruptedException e) {
		             e.printStackTrace();
		          }
		      HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		      Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		      homePage.navigateToOCMPage();
		      OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		      ocmHomePage.navigateToTab("IVR");
		      IvrPage ivrPage=PageFactory.createPageInstance(driver,IvrPage.class);
		      ivrPage.navigateToMenuDescriptionMappingPage();
		      MenuDescriptionMappingPage modPageInst=PageFactory.createPageInstance(driver,MenuDescriptionMappingPage.class);
		      Assert.assertTrue(modPageInst.isMenuDescriptionMappingPageDisplayed(), "Menu Description Mapping Page assertion failed");
		      Assert.assertFalse(modPageInst.isAddBtnDisplayed(), "Add button assertion failed");
		      Assert.assertFalse(modPageInst.isEditBtnDisplayed(), "Edit button assertion failed");
		      Assert.assertFalse(modPageInst.isDeleteBtnDisplayed(), "Delete button assertion failed");
		      Assert.assertTrue(modPageInst.isExportBtnDisplayed(), "Export button assertion failed");
		      homePage.userLogout();
		      driver.close();
		}

	@AfterMethod
	 public void afterEachMethod(ITestResult result){
	   	 if(ITestResult.FAILURE==result.getStatus()){
			 try{
				 screenshot.captureScreen(driver, result.getName(),"UserManagementE2ETest");
			 }
			catch (Exception e){
			 System.out.println("Exception while taking screenshot "+e.getMessage());
			 } 
			 HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
			 homePage.userLogout();
		     driver.close();
			 }
	   	 
	        try {
	            PageFactory.reset();
	            BrowserFactory browserFactory = new BrowserFactory();
	            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\UserManagemnetExcelFiles");
	            String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	            Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(0);
	            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	            if(map.get("LoginType").equals("Custom")){
	                LoginPage loginPage=PageFactory.createPageInstance(driver,LoginPage.class);
	                loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                Thread.sleep(5000);
	            }
	            HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	            Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	        }catch (Exception e){
	            PageFactory.reset();
	            driver.close();
	            e.printStackTrace();
	        }
	   	 
		
	    }
	
}

