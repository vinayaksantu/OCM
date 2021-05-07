package com.tetherfi.test.tmac;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.tmac.TmacBroadCastMsgDetails;
import com.tetherfi.model.tmac.TmacTransferListDetails;
import com.tetherfi.model.tmac.TmacUserDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.TmacLoginPage;
import com.tetherfi.pages.TmacBroadCastMsgPage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.pages.TmacPopupPage;
import com.tetherfi.pages.TmacTransferListPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import com.tetherfi.utility.VoiceLibrary;

public class TmacTransferListE2E extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
	
	 @BeforeMethod
	 public void NavigateToTmacTransferListPage()  {
	        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	        homePage.navigateToOCMPage();
	        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
	        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
	        ocmHomePage.navigateToTab("TMAC");
	        TmacPage tmacPage = PageFactory.createPageInstance(driver, TmacPage.class);
	        Assert.assertTrue(tmacPage.isTMACPageDisplayed(), "tmac page assertion failed");
	        screenshot.captureScreen("TmacTransferListTest", "TMAC Page");
	        tmacPage.navigateToTmacTranferListPage();
	        TmacTransferListPage tmacTransferListPage  = PageFactory.createPageInstance(driver, TmacTransferListPage.class);
	        Assert.assertTrue(tmacTransferListPage.isTmacTransferListPageDisplayed(), "TmacTransferList page assertion failed");
	        screenshot.captureScreen("TmacTransferListTest", "TMACTransferList Page");
	    }
	
	@Test(priority=1)
    public void verifyBlindSkillListOnTmac() throws Exception
    { 
		TmacTransferListPage tmacTransferListPage  = PageFactory.createPageInstance(driver, TmacTransferListPage.class);
		tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "ConsulCreate").getTestData().get(8);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
    	tmacTransferListPage.addNewTmacConsultTransferList(tmacTransferListDetails);
    	/*Assert.assertTrue(tmacTransferListPage.verifyNewRecordCreated(),"Add New record assertion failed");
    	map = new ExcelReader(filePath, "ConsulCreate").getTestData().get(9);
        tmacTransferListDetails=new TmacTransferListDetails(map);
    	tmacTransferListPage.addNewTmacConsultTransferList(tmacTransferListDetails);
    	Assert.assertTrue(tmacTransferListPage.verifyNewRecordCreated(),"Add New record assertion failed");
    	map = new ExcelReader(filePath, "ConsulCreate").getTestData().get(10);
        tmacTransferListDetails=new TmacTransferListDetails(map);
    	tmacTransferListPage.addNewTmacConsultTransferList(tmacTransferListDetails);
    	Assert.assertTrue(tmacTransferListPage.verifyNewRecordCreated(),"Add New record assertion failed");
    	*/driver.close();
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";

    	try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME);
            Map<String, String> map1 = new ExcelReader(filePath1,"Login").getTestData().get(3);
            driver.get("http://"+map1.get("Username")+":"+map1.get("Password")+"@"+map1.get("Application URL").split("//")[1]);
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
	}
		TmacLoginPage loginPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
		Map<String, String> map2 = new ExcelReader(filePath1,"TMAC").getTestData().get(3);
		TmacUserDetails tmacUserDetails=new TmacUserDetails(map2);
        loginPage.logintotmac(tmacUserDetails.getLanID(),tmacUserDetails.getStation());
        Assert.assertTrue(loginPage.verifyUserLogged(),"Tmac login failed");
        screenshot.captureScreen("TmacTransferListTest", "Tmaclogin");
        loginPage.switchToNewWindow();
        Assert.assertTrue(loginPage.isTmacPopUpDisplayed(),"TMAC popup not displayed");
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.changeStatus("Available");
        VoiceLibrary cl=new VoiceLibrary();
        cl.open();
        cl.loginAES("AVAYA#CMSIM#CSTA#AESSIM","Tetherfi","Tetherfi_01");
        cl.loginCallingStation("50034","40027");
        cl.initiateCall("40027");
        Assert.assertTrue(tmacPopupPage.verifyCallReceived(),"TMAC call received");
        tmacPopupPage.receiveCall(1);
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
				loginPage.login(map.get("Username"),map.get("Password"),map.get("EmailId"));
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
