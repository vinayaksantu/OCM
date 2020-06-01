package com.tetherfi.test.tmac;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.tmac.TmacUserDetails;
import com.tetherfi.model.tmac.WorkCodeListDetails;

import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.TmacLoginPage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.pages.TmacPopupPage;
import com.tetherfi.pages.WorkCodeListPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ChatAPI;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class WorkCodeListE2ETest {
	protected WebDriver driver;
	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
    public void NavigateToWorkCodeListPage(Method method) throws Exception {
		 try {
	            PageFactory.reset();
	            BrowserFactory browserFactory = new BrowserFactory();
	            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	        }catch (Exception e){
	            PageFactory.reset();
	            driver.close();
	            e.printStackTrace();
	        }
	        System.out.println("Started Executing : "+method.getName());
	        Test t = method.getAnnotation(Test.class);
	        Map<String, String> map;
	        if(t.groups()[0].equalsIgnoreCase("OCM"))
	        {	
	        	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	            map= new ExcelReader(filePath,"Login").getTestData().get(0);
	            try{driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	            }catch (TimeoutException e)
	            {e.printStackTrace();
	            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}
	            	if(map.get("LoginType").equals("Custom")){
	            		LoginPage loginPage=PageFactory.createPageInstance(driver,LoginPage.class);
	            		Assert.assertTrue(loginPage.isLoginPageDisplayed(),"Login page not loaded");
	            		loginPage.login(map.get("Username"),map.get("Password"));
	            		try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            	}
	            	 HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	                 homePage.navigateToOCMPage();
	                 OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
	                 Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
	                 ocmHomePage.navigateToTab("TMAC");
	                 TmacPage tmacPage = PageFactory.createPageInstance(driver, TmacPage.class);
	                 Assert.assertTrue(tmacPage.isTMACPageDisplayed(), "tmac page assertion failed");
	                 screenshot.captureScreen("WorkCodeListTest", "TMAC Page");
	                 tmacPage.navigateToWorkCodeListPage();
	                 WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
	                 Assert.assertTrue(workCodeListPage.isWorkCodeListPageDisplayed(), "WorkCodeList page assertion failed");
	                 screenshot.captureScreen("WorkCodeListTest", "WorkCodeList Page");
	                 }
	        else {
	        	 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             map=new ExcelReader(filePath,"Login").getTestData().get(3);
	             try{driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}
	             catch (TimeoutException e)
	             {e.printStackTrace();
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}
	             TmacLoginPage tmacloginPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
	             Assert.assertTrue(tmacloginPage.checkPageLoadStatus(), "login page successful status");
	             map = new ExcelReader(filePath, "TMAC").getTestData().get(0);
	     		 TmacUserDetails tmacUserDetails=new TmacUserDetails(map);
	             tmacloginPage.logintotmac(tmacUserDetails.getLanID(),tmacUserDetails.getStation());
	             Assert.assertTrue(tmacloginPage.verifyUserLogged(),"Tmac login failed");
	             tmacloginPage.switchToNewWindow();
	             Assert.assertTrue(tmacloginPage.isTmacPopUpDisplayed(),"TMAC popup not displayed");
	        }
	}
	
	/*@Test(groups= {"OCM"},priority=1)
	public void AddnewWorkcodeList() throws Exception {
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(3);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        workCodeListPage.addNewWorkGroup(workcodeListDetails);
        Assert.assertTrue(workCodeListPage.verifymessage(), "Workgroup Record creation assertion failed" );
        screenshot.captureScreen(driver, "Workgroup Record Created","WorkCodeListE2ETest");
	}
	
	@Test(groups= {"TMAC"},priority=2)
	public void VerifyAddWorkCodeintoTMAC() throws Exception
	{	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(3);
    	WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.changeStatus("Available");
        ChatAPI chatapi=new ChatAPI(driver);
        chatapi.open("https://172.16.2.16:15012/api/testapp.jsp");
        chatapi.initiateChat();
        chatapi.sendChatMessage("Testing chat msg");
        tmacPopupPage.receivechat();
        tmacPopupPage.disconnectchat();
        ((JavascriptExecutor)

        		driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
        
        tmacPopupPage.WorkCodeList(workcodeListDetails.getName());
	}
	@Test(groups= {"OCM"},priority=3)
	public void EditNewWorkcodeList() throws Exception {
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(2);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        workCodeListPage.editworkcodeListRecord(workcodeListDetails);
        Assert.assertTrue(workCodeListPage.verifymessage(),"Record updation assertion failed");
        screenshot.captureScreen(driver, "Workgroup Record Created","WorkCodeListE2ETest");
	}
	
	@Test(groups= {"TMAC"},priority=4)
	public void VerifyEditWorkCodeintoTmac() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(2);
		WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
		TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
		tmacPopupPage.changeStatus("Available");
		ChatAPI chatapi=new ChatAPI(driver);
		chatapi.open("https://172.16.2.16:15012/api/testapp.jsp");
		chatapi.initiateChat();
		chatapi.sendChatMessage("Testing chat msg");
		tmacPopupPage.receivechat();
		tmacPopupPage.disconnectchat();
		((JavascriptExecutor)

    		driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
    
    	tmacPopupPage.WorkCodeList(workcodeListDetails.getUpdatedName());
	}
	
	@Test(groups= {"OCM"},priority=5)
	public void DeleteWorkCodeList() throws Exception {
		WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(1);
		WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
		workCodeListPage.deleteWorkCodeListRecord(workcodeListDetails);
		Assert.assertTrue(workCodeListPage.verifymessage(),"delete record assertion failed");
		screenshot.captureScreen(driver, "Verify Record Deleted", "WorkCodeListTest");
	}*/
	
	@Test(groups= {"TMAC"},priority=6)
	public void VerifyDropdownWorkCodeintoTmac() throws Exception {
		WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(3);
		WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
		List<String> lst=workCodeListPage.captureDatabase(workcodeListDetails.getQuery());
		TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
		tmacPopupPage.changeStatus("Available");
		ChatAPI chatapi=new ChatAPI(driver);
		chatapi.open("https://172.16.2.16:15012/api/testapp.jsp");
		chatapi.initiateChat();
		chatapi.sendChatMessage("Testing chat msg");
		tmacPopupPage.receivechat();
		tmacPopupPage.disconnectchat();
		((JavascriptExecutor)

    		driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
		Assert.assertTrue(tmacPopupPage.verifyDropdown(lst));
	}
	
	
	 @AfterMethod
	 public void close(Method method){
		 	Test t = method.getAnnotation(Test.class);
	        if(t.groups()[0].equalsIgnoreCase("TMAC"))
	        {	
	        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
	        tmacPopupPage.userLogout();
	        driver.close();
	        }
	        else 
	        	driver.close();
	    }
	

}
