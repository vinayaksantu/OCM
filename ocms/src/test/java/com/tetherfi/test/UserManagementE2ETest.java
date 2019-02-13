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
import com.tetherfi.pages.ChatPage;
import com.tetherfi.pages.ChatTemplatesPage;
import com.tetherfi.pages.FaxLineConfigPage;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.HomePage;
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

