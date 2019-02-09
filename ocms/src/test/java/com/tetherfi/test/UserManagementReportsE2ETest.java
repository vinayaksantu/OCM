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
import com.tetherfi.pages.AgentTeamManagementPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.TmacBroadCastMsgPage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.pages.UserManagementPage;
import com.tetherfi.pages.WaitTimeColorConfigPage;
import com.tetherfi.pages.WorkCodeListPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class UserManagementReportsE2ETest extends BaseTest {
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
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementReportAccessData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        userManagementPage.searchUserManagementRecord(userDetails.getUserId());
        Assert.assertTrue(userManagementPage.isPageBasedUserAccessPageDisplayed());
        screenshot.captureScreen(driver, "PageBasedUSerAccessDisplayed", "UserManagementE2ETest");
        userManagementPage.clearAccess();
	}
	@Test
	public void VerifyAccessOfAgentTeamMgmt() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideAccess("Agent Team Management");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
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
        Assert.assertTrue(agentTeamManagementPage.VerifyAccesss());
        screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");  
        homePage.userLogout();
        driver.close();
	}
	/*@Test(dependsOnMethods = {"VerifyAccessOfAgentTeamMgmt"})
	public void VerifyAddAccessOfAgentTeamMgmt()throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAddAccess("Agent Team Management");
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
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath, "Create").getTestData().get(4);
        AgentTeamMgmtDetails agentTeamMgmtDetails1=new AgentTeamMgmtDetails(map1);
        agentTeamManagementPage.addnewCountry(agentTeamMgmtDetails1);
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(), "Country Assertion Failed");
		Assert.assertTrue(agentTeamManagementPage.verifyAddAccesss());
		screenshot.captureScreen(driver, "VerifyAddAccess", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	@Test(dependsOnMethods = {"VerifyAddAccessOfAgentTeamMgmt"})
	public void VerifyEditAccessOfAgentTeamMgmt() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideEditAccess("Agent Team Management");
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
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        agentTeamManagementPage.editAgentTeamManagementRecord(agentTeamMgmtDetails.getTeamName(),agentTeamMgmtDetails.getUpdateTeamName(),agentTeamMgmtDetails.getModifyReason());
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(),"Edit record assertion failed");
		Assert.assertTrue(agentTeamManagementPage.verifyEditAccesss());
		screenshot.captureScreen(driver, "VerifyEditAccess", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	@Test(dependsOnMethods = {"VerifyEditAccessOfAgentTeamMgmt"})
	public void VerifyDeleteAccessOfAgentTeamMgmt() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideDeleteAccess("Agent Team Management");
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
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(1);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        agentTeamManagementPage.deleteAgentTeamManagementRecord(agentTeamMgmtDetails.getUpdateTeamName(),agentTeamMgmtDetails.getDeleteReason());
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(),"delete record assertion failed");
		Assert.assertTrue(agentTeamManagementPage.verifyDeleteAccesss());
		screenshot.captureScreen(driver, "VerifyDeleteAccess", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	@Test(dependsOnMethods = {"VerifyDeleteAccessOfAgentTeamMgmt"})
	public void VerifyExportAccess() throws Exception {
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideExportAccess("Agent Team Management");
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
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\UserManagemnetExcelFiles";
        Assert.assertTrue(agentTeamManagementPage.verifyExportToExcel(filePath));
        Assert.assertTrue(agentTeamManagementPage.verifyExportAccesss());
		screenshot.captureScreen(driver, "VerifyExportAccess", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	@Test
	public void VerifyAccessOfTMACBroadcastMsg() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideAccess("TMAC Broadcast Message");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
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
        Assert.assertTrue(tmacBroadCastMsgPage.VerifyAccesss());
        screenshot.captureScreen(driver, "VerifyAccessOfTMACBroadcastMsg", "UserManagementE2ETest");  
        homePage.userLogout();
        driver.close();
	}
	@Test(dependsOnMethods = {"VerifyAccessOfTMACBroadcastMsg"})
	public void VerifyAddAccessOfTMACBroadcastMsg()throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAddAccess("TMAC Broadcast Message");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
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
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        tmacBroadCastMsgPage.addTmacBroadcastMsg(tmacBroadCastMsgDetails);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyNewRecordCreated(),"add record assertion failed");
		Assert.assertTrue(tmacBroadCastMsgPage.verifyAddAccesss());
		screenshot.captureScreen(driver, "VerifyAddAccessOfTMACBroadcastMsg", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	@Test(dependsOnMethods = {"VerifyAddAccessOfTMACBroadcastMsg"})
	public void VerifyEditAccessOfTMACBroadcastMsg() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideEditAccess("TMAC Broadcast Message");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
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
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(3);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        tmacBroadCastMsgPage.editTmacBroadcastMsg(tmacBroadCastMsgDetails);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyRecordUpdated(),"Record Updated assertion failed");
		Assert.assertTrue(tmacBroadCastMsgPage.verifyEditAccesss());
		screenshot.captureScreen(driver, "VerifyEditAccessOfTMACBroadcastMsg", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	@Test(dependsOnMethods= {"VerifyEditAccessOfTMACBroadcastMsg"})
		public void VerifyExportAccessOfTMACBroadcastMsg() throws Exception {
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideExportAccess("TMAC Broadcast Message");
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
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\UserManagemnetExcelFiles";
        Assert.assertTrue(tmacBroadCastMsgPage.verifyExportToExcel(filePath));
        Assert.assertTrue(tmacBroadCastMsgPage.verifyExportAccesss());
		screenshot.captureScreen(driver, "VerifyExportAccessOfTMACBroadcastMsg", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	@Test
	public void VerifyAccessOfWaitTimeColorConfig() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideAccess("Wait Time Color Config");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
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
        Assert.assertTrue(waitTimeColorConfigPage.VerifyAccesss());
        screenshot.captureScreen(driver, "VerifyAccessOfWaitTimeColorConfig", "UserManagementE2ETest");  
        homePage.userLogout();
        driver.close();
	}
	@Test(dependsOnMethods = {"VerifyAccessOfWaitTimeColorConfig"})
	public void VerifyAddAccessOfWaitTimeColorConfig()throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAddAccess("Wait Time Color Config");
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
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
        waitTimeColorConfigPage.addNewWaitTimeColorConfigRecord(waitTimeColorConfigDetails);
        Assert.assertEquals(waitTimeColorConfigPage.getMessage(),"Record Created Successfully","Add New record assertion failed");
		screenshot.captureScreen(driver, "VerifyAddAccessOfWaitTimeColorConfig()", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	@Test(dependsOnMethods = {"VerifyAddAccessOfWaitTimeColorConfig"})
	public void VerifyEditAccessOfWaitTimeColorConfig() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideEditAccess("Wait Time Color Config");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
				LoginPage loginPage=new LoginPage(driver);
				loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
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
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(1);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
        waitTimeColorConfigPage.editWaitTimeColorConfigRecord(waitTimeColorConfigDetails);
        Assert.assertEquals(waitTimeColorConfigPage.getMessage(),"Record updated successfully","Edit record assertion failed");
		Assert.assertTrue(waitTimeColorConfigPage.verifyEditAccesss());
		screenshot.captureScreen(driver, "VerifyEditAccessOfWaitTimeColorConfig", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	@Test(dependsOnMethods = {"VerifyEditAccessOfWaitTimeColorConfig"})
	public void VerifyDeleteAccessOfWaitTimeColorConfig() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideDeleteAccess("Wait Time Color Config");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
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
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(1);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
        waitTimeColorConfigPage.deleteWaitTimeColorConfigRecord(waitTimeColorConfigDetails.getStartTime(),waitTimeColorConfigDetails.getDeleteReason());
        Assert.assertEquals(waitTimeColorConfigPage.getMessage(),"Record deleted successfully","Delete record assertion failed");
        screenshot.captureScreen(driver, "Deleted Successfully","WaitTimeColorConfigTest");
        Assert.assertTrue(waitTimeColorConfigPage.verifyDeleteAccesss());
		screenshot.captureScreen(driver, "VerifyDeleteAccessOfWaitTimeColorConfig", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	@Test(dependsOnMethods = {"VerifyDeleteAccessOfWaitTimeColorConfig"})
	public void VerifyExportAccessOfWaitTimeColorConfig() throws Exception {
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideExportAccess("Wait Time Color Config");
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
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\UserManagemnetExcelFiles";
        Assert.assertTrue(waitTimeColorConfigPage.verifyExportToExcel(filePath));
        Assert.assertTrue(waitTimeColorConfigPage.verifyExportAccesss());
		screenshot.captureScreen(driver, "VerifyExportAccessOfWaitTimeColorConfig", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	@Test
	public void VerifyAccessOfWorkcodeList() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideAccess("Workcode List");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
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
        Assert.assertTrue(workCodeListPage.VerifyAccesss());
        screenshot.captureScreen(driver, "VerifyAccessOfWorkcodeList", "UserManagementE2ETest");  
        homePage.userLogout();
        driver.close();
	}
	@Test(dependsOnMethods = {"VerifyAccessOfWorkcodeList"})
	public void VerifyAddAccessOfWorkcodeList()throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideAddAccess("Workcode List");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
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
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        workCodeListPage.addNewWorkGroup(workcodeListDetails);
        Assert.assertTrue(workCodeListPage.verifymessage(), "Workgroup Record creation assertion failed" );
        Assert.assertTrue(workCodeListPage.verifyAddAccesss());
		screenshot.captureScreen(driver, "VerifyAddAccessOfWorkcodeList", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	@Test(dependsOnMethods = {"VerifyAddAccessOfWorkcodeList"})
	public void VerifyEditAccessOfWorkcodeList() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideEditAccess("Workcode List");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
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
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);	
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);	
        workCodeListPage.editworkcodeListRecord(workcodeListDetails);
        Assert.assertTrue(workCodeListPage.verifymessage(),"Record updation assertion failed");
		Assert.assertTrue(workCodeListPage.verifyEditAccesss());
		screenshot.captureScreen(driver, "VerifyEditAccessOfWorkcodeList", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	@Test(dependsOnMethods = {"VerifyEditAccessOfWorkcodeList"})
	public void VerifyDeleteAccessOfWorkcodeList() throws Exception
	{
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideDeleteAccess("Workcode List");
		Thread.sleep(2000);
		driver.close();
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
			driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
			if(map.get("LoginType").equals("Custom")){
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
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        workCodeListPage.deleteWorkCodeListRecord(workcodeListDetails);
        Assert.assertTrue(workCodeListPage.verifymessage(),"delete record assertion failed");
        Assert.assertTrue(workCodeListPage.verifyDeleteAccesss());
		screenshot.captureScreen(driver, "VerifyDeleteAccessOfWorkcodeList", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}
	
	@Test(dependsOnMethods = {"VerifyDeleteAccessOfWorkcodeList"})
	public void VerifyExportAccessOfWorkcodeList() throws Exception {
		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
		userManagementPage.ProvideExportAccess("Workcode List");
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
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\UserManagemnetExcelFiles";
        Assert.assertTrue(workCodeListPage.verifyExportToExcel(filePath));
        Assert.assertTrue(workCodeListPage.verifyExportAccesss());
		screenshot.captureScreen(driver, "VerifyExportAccessOfWorkcodeList()", "UserManagementE2ETest");  
		homePage.userLogout();
		driver.close();
	}*/
	
	@AfterMethod
	 public void afterEachMethod(ITestResult result){
	   	 if(ITestResult.FAILURE==result.getStatus()){
			 try{
				 screenshot.captureScreen(driver, result.getName(),"UserManagementE2ETest");
			 }
			catch (Exception e){
			 System.out.println("Exception while taking screenshot "+e.getMessage());
			 } 
			 driver.navigate().refresh();
			 }
	   	 else
	   	 {
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

}

