package com.tetherfi.test;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.tmac.AgentTeamMgmtDetails;
import com.tetherfi.model.tmac.TmacBroadCastMsgDetails;
import com.tetherfi.model.tmac.WaitTimeColorConfigDetails;
import com.tetherfi.model.tmac.WorkCodeListDetails;
import com.tetherfi.model.user.UserDetails;
import com.tetherfi.pages.AdhocOptionEnhancementPage;
import com.tetherfi.pages.AdminCallbackPage;
import com.tetherfi.pages.AgentSkillAssignmentPage;
import com.tetherfi.pages.AgentTeamManagementPage;
import com.tetherfi.pages.AgentTransferPage;
import com.tetherfi.pages.BillingOrgPage;
import com.tetherfi.pages.BranchDetailsPage;

import com.tetherfi.pages.CallBackManagementPage;
import com.tetherfi.pages.CallbackAnnouncementPage;
import com.tetherfi.pages.ChatIntentSkillMappingPage;
import com.tetherfi.pages.ChatMenuDescriptionPage;
import com.tetherfi.pages.ChatPage;
import com.tetherfi.pages.ChatTemplatesPage;
import com.tetherfi.pages.FaxFormsPage;
import com.tetherfi.pages.FaxGroupPage;
import com.tetherfi.pages.FaxLineConfigPage;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.FaxSendersPage;
import com.tetherfi.pages.HolidayListPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrConfigPage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OperatingHoursPage;
import com.tetherfi.pages.TmacBroadCastMsgPage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.pages.UserManagementPage;
import com.tetherfi.pages.WaitTimeColorConfigPage;
import com.tetherfi.pages.WorkCodeListPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class UserManagementE2ETest extends BaseTest {
	Screenshot screenshot=new Screenshot();
	
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
		driver.close();
		try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
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
	public void VerifyAccessOfAdHocOptionEnhancement() throws Exception
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

