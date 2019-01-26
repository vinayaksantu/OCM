package com.tetherfi.test.tmac;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.tmac.TmacBroadCastMsgDetails;
import com.tetherfi.model.tmac.TmacUserDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.TmacLoginPage;
import com.tetherfi.pages.TmacBroadCastMsgPage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class TmacBroadCastMsgE2E extends BaseTest {
	Screenshot screenshot=new Screenshot();
	
	 @BeforeMethod
	 public void NavigateToTmacBroadcastMsgPage()  {
	        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	        homePage.navigateToOCMPage();
	        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
	        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
	        ocmHomePage.navigateToTab("TMAC");
	        TmacPage tmacPage = PageFactory.createPageInstance(driver, TmacPage.class);
	        Assert.assertTrue(tmacPage.isTMACPageDisplayed(), "tmac page assertion failed");
	        screenshot.captureScreen(driver, "TMAC Page","TmacBroadCastMsgTest");
	        tmacPage.navigateToTmacBroadcastMsgPage();
	        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
	        Assert.assertTrue(tmacBroadCastMsgPage.isTmacBroadcastMsgPageDisplayed(), "TmacBroadcastmessage page assertion failed");
	        screenshot.captureScreen(driver, "TMACBroadcastMsg Page","TmacBroadCastMsgTest");
	    }
	
	@Test(priority=1)
    public void addTmacBroadcastMessage() throws IOException
    { 
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
    	TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
    	TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
    	tmacBroadCastMsgPage.addTmacBroadcastMsg(tmacBroadCastMsgDetails);
    	Assert.assertTrue(tmacBroadCastMsgPage.verifyNewRecordCreated(),"add record assertion failed");
    	screenshot.captureScreen(driver, "Record Created Successfully","TmacBroadCastMsgTest");
    	driver.close();
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";

    	try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME);
            Map<String, String> map1 = new ExcelReader(filePath1,"Login").getTestData().get(1);
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
        screenshot.captureScreen(driver, "Tmaclogin", "TmacBroadCastMsgE2E");
        loginPage.switchToNewWindow();
        Assert.assertTrue(loginPage.isTmacPopUpDisplayed(),"TMAC popup not displayed");
        TmacLoginPage TmacPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
		TmacPage.broadcastMsg();
		Assert.assertEquals(TmacPage.broadcastMsg(), tmacBroadCastMsgDetails.getMessage());
    }
	@Test(priority=2)
	public void editTmacBroadcastMessage() throws Exception
	{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        tmacBroadCastMsgPage.editTmacBroadcastMsg(tmacBroadCastMsgDetails);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyRecordUpdated(),"Record Updated assertion failed");
        screenshot.captureScreen(driver, "Updated Record","TmacBroadCastMsgTest");
	driver.close();
    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";

	try {
        PageFactory.reset();
        BrowserFactory browserFactory = new BrowserFactory();
        driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME);
        Map<String, String> map1 = new ExcelReader(filePath1,"Login").getTestData().get(1);
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
		screenshot.captureScreen(driver, "Tmaclogin", "TmacBroadCastMsgE2E");
		loginPage.switchToNewWindow();
		Assert.assertTrue(loginPage.isTmacPopUpDisplayed(),"TMAC popup not displayed");
		TmacLoginPage TmacPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
		TmacPage.broadcastMsg();
		Assert.assertEquals(TmacPage.broadcastMsg(), tmacBroadCastMsgDetails.getUpdatedMessage());
	}
	
	@Test(priority=3)
	public void disabledStatus() throws Exception
	{
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
	        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(1);
	        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
	        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
	        tmacBroadCastMsgPage.editTmacBroadcastMsg(tmacBroadCastMsgDetails);
	        Assert.assertTrue(tmacBroadCastMsgPage.verifyRecordUpdated(),"Record Updated assertion failed");
	        screenshot.captureScreen(driver, "Updated Record","TmacBroadCastMsgTest");
	        Thread.sleep(2000);
	        driver.close();
	        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";

	        try {
	        	PageFactory.reset();
	        	BrowserFactory browserFactory = new BrowserFactory();
	        	driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME);
	        	Map<String, String> map1 = new ExcelReader(filePath1,"Login").getTestData().get(1);
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
			screenshot.captureScreen(driver, "Tmaclogin", "TmacBroadCastMsgE2E");
			loginPage.switchToNewWindow();
			Assert.assertTrue(loginPage.isTmacPopUpDisplayed(),"TMAC popup not displayed");
			TmacLoginPage TmacPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
			Assert.assertTrue(TmacPage.Status(),"Disable status assertion failed");
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
