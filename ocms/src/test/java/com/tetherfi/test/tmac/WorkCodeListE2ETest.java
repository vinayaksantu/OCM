package com.tetherfi.test.tmac;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.tmac.TmacUserDetails;
import com.tetherfi.model.tmac.WorkCodeListDetails;
import com.tetherfi.pages.ChatApiPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.TmacLoginPage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.pages.WorkCodeListPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class WorkCodeListE2ETest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
    public void NavigateToWorkCodeListPage()  {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage = PageFactory.createPageInstance(driver, TmacPage.class);
        Assert.assertTrue(tmacPage.isTMACPageDisplayed(), "tmac page assertion failed");
        screenshot.captureScreen(driver, "TMAC Page","WorkCodeListTest");
        tmacPage.navigateToWorkCodeListPage();
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.isWorkCodeListPageDisplayed(), "WorkCodeList page assertion failed");
        screenshot.captureScreen(driver, "WorkCodeList Page","WorkCodeListE2ETest");
	}
	
	@Test
	public void AddnewWorkcodeList() throws Exception {
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(3);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        workCodeListPage.addNewWorkGroup(workcodeListDetails);
        Assert.assertTrue(workCodeListPage.verifymessage(), "Workgroup Record creation assertion failed" );
        screenshot.captureScreen(driver, "Workgroup Record Created","WorkCodeListE2ETest");
        driver.close();
        String filePath1 = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\LoginData.xlsx";
        try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME);
            Map<String, String> map1 = new ExcelReader(filePath1,"Login").getTestData().get(7);
            driver.get(map1.get("Application URL"));
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
        }
        ChatApiPage chatapiPage  = PageFactory.createPageInstance(driver, ChatApiPage.class);
        chatapiPage.StartChat();
        driver.close();
        String filePath2 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
    	try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME);
            Map<String, String> map1 = new ExcelReader(filePath2,"Login").getTestData().get(3);
            driver.get("http://"+map1.get("Username")+":"+map1.get("Password")+"@"+map1.get("Application URL").split("//")[1]);
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
	}
		TmacLoginPage loginPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
		Map<String, String> map2 = new ExcelReader(filePath1,"TMAC").getTestData().get(0);
		TmacUserDetails tmacUserDetails=new TmacUserDetails(map2);
        loginPage.logintotmac(tmacUserDetails.getLanID(),tmacUserDetails.getStation());
        Assert.assertTrue(loginPage.verifyUserLogged(),"Tmac login failed");
        screenshot.captureScreen(driver, "Tmaclogin", "WorkCodeListE2ETest");
        loginPage.switchToNewWindow();
        Assert.assertTrue(loginPage.isTmacPopUpDisplayed(),"TMAC popup not displayed");
        TmacLoginPage TmacPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
        TmacPage.changeStatus("Available");
        Assert.assertTrue(TmacPage.workcodelist(workcodeListDetails.getName()),"add workcodelist Assertion failed");
        screenshot.captureScreen(driver, "Add Workcode", "WorkCodeListE2ETest");

	}
	@Test
	public void EditNewWorkcodeList() throws Exception {
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(2);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        workCodeListPage.editworkcodeListRecord(workcodeListDetails);
        Assert.assertTrue(workCodeListPage.verifymessage(),"Record updation assertion failed");
        screenshot.captureScreen(driver, "Workgroup Record Created","WorkCodeListE2ETest");
        driver.close();
        String filePath1 = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\LoginData.xlsx";
        try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME);
            Map<String, String> map1 = new ExcelReader(filePath1,"Login").getTestData().get(7);
            driver.get(map1.get("Application URL"));
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
        }
        ChatApiPage chatapiPage  = PageFactory.createPageInstance(driver, ChatApiPage.class);
        chatapiPage.StartChat();
        driver.close();
        String filePath2 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
    	try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME);
            Map<String, String> map1 = new ExcelReader(filePath2,"Login").getTestData().get(1);
            driver.get("http://"+map1.get("Username")+":"+map1.get("Password")+"@"+map1.get("Application URL").split("//")[1]);
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
	}
		TmacLoginPage loginPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
		Map<String, String> map2 = new ExcelReader(filePath1,"TMAC").getTestData().get(0);
		TmacUserDetails tmacUserDetails=new TmacUserDetails(map2);
        loginPage.logintotmac(tmacUserDetails.getLanID(),tmacUserDetails.getStation());
        Assert.assertTrue(loginPage.verifyUserLogged(),"Tmac login failed");
        screenshot.captureScreen(driver, "Tmaclogin", "WorkCodeListE2ETest");
        loginPage.switchToNewWindow();
        Assert.assertTrue(loginPage.isTmacPopUpDisplayed(),"TMAC popup not displayed");
        TmacLoginPage TmacPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
        TmacPage.changeStatus("Available");
        Assert.assertTrue(TmacPage.workcodelist(workcodeListDetails.getUpdatedName()),"add workcodelist Assertion failed");
        screenshot.captureScreen(driver, "Edit Workcode", "WorkCodeListE2ETest");

	}
	
	@Test
	public void VerifyWorkCodeList() throws Exception
	{
		WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
		List<Map<String, String>> arr=new ArrayList<Map<String, String>>();
        arr=workCodeListPage.capturelist();
        screenshot.captureScreen(driver, "Workgroup Record Created","WorkCodeListE2ETest");
        driver.close();
        String filePath1 = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\LoginData.xlsx";
        try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME);
            Map<String, String> map1 = new ExcelReader(filePath1,"Login").getTestData().get(7);
            driver.get(map1.get("Application URL"));
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
        }
        ChatApiPage chatapiPage  = PageFactory.createPageInstance(driver, ChatApiPage.class);
        chatapiPage.StartChat();
        driver.close();
        String filePath2 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
    	try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME);
            Map<String, String> map1 = new ExcelReader(filePath2,"Login").getTestData().get(1);
            driver.get("http://"+map1.get("Username")+":"+map1.get("Password")+"@"+map1.get("Application URL").split("//")[1]);
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
	}
		TmacLoginPage loginPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
		Map<String, String> map2 = new ExcelReader(filePath1,"TMAC").getTestData().get(0);
		TmacUserDetails tmacUserDetails=new TmacUserDetails(map2);
        loginPage.logintotmac(tmacUserDetails.getLanID(),tmacUserDetails.getStation());
        Assert.assertTrue(loginPage.verifyUserLogged(),"Tmac login failed");
        screenshot.captureScreen(driver, "Tmaclogin", "WorkCodeListE2ETest");
        loginPage.switchToNewWindow();
        Assert.assertTrue(loginPage.isTmacPopUpDisplayed(),"TMAC popup not displayed");
        TmacLoginPage TmacPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
        Assert.assertTrue(TmacPage.verifyworkcodelist(arr));
        screenshot.captureScreen(driver, "VerifyList", "WorkCodeListE2ETest");	
	}
	@AfterMethod
	public void aftereachmethod()
	{
			TmacLoginPage TmacloginPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
			 TmacloginPage.userLogout();
	          driver.close();
	      
		try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
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
