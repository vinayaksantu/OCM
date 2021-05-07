package com.tetherfi.test.fax;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.model.fax.SendFaxDetails;
import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.model.tmac.TmacUserDetails;
import com.tetherfi.pages.FaxLineConfigPage;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.FaxTemplatePage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.SendFaxPage;
import com.tetherfi.pages.TmacLoginPage;
import com.tetherfi.pages.TmacPopupPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class FaxLineConfigE2ETest {
	protected WebDriver driver;
	 @BeforeMethod
	    public void NavigateToTmacPopupPage(Method method) throws Exception {
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
	    				loginPage.login(map.get("Username"),map.get("Password"),map.get("EmailId"));
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
	        ocmHomePage.navigateToTab("FAX");
	        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
	        Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
	        faxPage.navigateToFaxLineConfigPage();
	        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
	        Assert.assertTrue(faxLineConfigPage.isFaxLineConfigPageDisplayed(), "FAX page assertion failed");
	        }
	        else {
	        	 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	             map=new ExcelReader(filePath,"Login").getTestData().get(12);
	             try{driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}
	             catch (TimeoutException e)
	             {e.printStackTrace();
	             driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}
	             TmacLoginPage tmacloginPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
	             Assert.assertTrue(tmacloginPage.checkPageLoadStatus(), "login page successful status");
	             map = new ExcelReader(filePath, "TMAC").getTestData().get(0);
	     		 TmacUserDetails tmacUserDetails=new TmacUserDetails(map);
	             tmacloginPage.logintotmac_WQ(tmacUserDetails.getLanID(),tmacUserDetails.getStation());
	             Assert.assertTrue(tmacloginPage.verifyUserLogged(),"Tmac login failed");
	             tmacloginPage.switchToNewWindow();
	             Assert.assertTrue(tmacloginPage.isTmacPopUpDisplayed(),"TMAC popup not displayed");
	        }
	    }
	    
	 @Test(priority=1,groups= {"OCM"})
	    public void AddFaxLineConfigRecord() throws Exception {
	        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
	        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
	        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
	        faxLineConfigPage.addNewFaxLineConfigRecord(faxLineConfigDetails);
	        Assert.assertEquals(faxLineConfigPage.getSuccessMessage(), "Record Created Successfully");
	 }
	 
	 @Test(priority=2,groups= {"TMAC"})
	    public void VerifyFaxLineCreateInTMAC() throws IOException {
		 	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
	        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
            TmacLoginPage tmacloginPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
            Assert.assertTrue(tmacloginPage.VerifyDropdownForCreate(faxLineConfigDetails));
	 }
	 
	 @Test(priority=3,groups= {"OCM"},dependsOnMethods = {"AddFaxLineConfigRecord"})
	 public void EditFaxLineConfigRecord() throws Exception {
	        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
	        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
	        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
	        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
	        faxLineConfigPage.editFaxLineConfig(faxLineConfigDetails);
	        Assert.assertEquals(faxLineConfigPage.getSuccessMessage(),"Record Updated Successfully");
	    }
	 
	 @Test(priority=4,groups= {"OCM"})
	 public void verifyEditSendFaxModule() throws IOException {
		 HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	        homePage.navigateToOCMPage();
	        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
	        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
	        ocmHomePage.navigateToTab("FAX");
	        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
	        Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
	        faxPage.navigateToSendFaxPage();
	        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
	        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
	        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
	        SendFaxPage sendFaxpage=PageFactory.createPageInstance(driver, SendFaxPage.class);
	        Assert.assertTrue(sendFaxpage.VerifyDropDown(faxLineConfigDetails));
	        
	 }
	 
	 @Test(priority=5,groups= {"TMAC"})
	    public void VerifyFaxLineEditInTMAC() throws IOException {
		 	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
	        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
	        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
	        TmacLoginPage tmacloginPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
	        Assert.assertTrue(tmacloginPage.VerifyDropdownForEdit(faxLineConfigDetails));
	 }
	    
	 @Test(priority=6,groups= {"OCM"})//dependsOnMethods = {"DeleteCancelFaxLineConfigRecord"},priority=8)
	    public void DeleteFaxLineConfigRecord() throws Exception {
	        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
	        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
	        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
	        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
	        faxLineConfigPage.deleteFaxLineConfig(faxLineConfigDetails);
	        Assert.assertEquals(faxLineConfigPage.getSuccessMessage(),"Record Deleted Successfully");
	    }
	    
	  @Test(priority=7,groups= {"TMAC"})
	    public void VerifyFaxLineDeleteInTMAC() throws IOException {
		 	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
	        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
	        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
	        TmacLoginPage tmacloginPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
	        Assert.assertTrue(tmacloginPage.VerifyDropdownForEdit(faxLineConfigDetails));
	 }
	   @Test(priority=4,groups= {"OCM"})
		 public void verifyDeleteSendFaxModule() throws IOException {
			 HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		        homePage.navigateToOCMPage();
		        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
		        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
		        ocmHomePage.navigateToTab("FAX");
		        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
		        Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
		        faxPage.navigateToSendFaxPage();
		        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		        SendFaxPage sendFaxpage=PageFactory.createPageInstance(driver, SendFaxPage.class);
		        Assert.assertTrue(sendFaxpage.VerifyDropDown(faxLineConfigDetails));
		        
		 }
	    
	   @Test(priority=4,groups= {"OCM"},dependsOnMethods = {"AddFaxLineConfigRecord"})
		 public void AddFaxLineConfigRecordforSendDisable() throws Exception {
		        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
		        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		        faxLineConfigPage.addNewFaxLineConfigRecord(faxLineConfigDetails);
		        Assert.assertEquals(faxLineConfigPage.getSuccessMessage(),"Record Created Successfully");
				HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		        homePage.navigateToOCMPage();
		        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
		        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
		        ocmHomePage.navigateToTab("FAX");
		        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
		        Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
		        faxPage.navigateToSendFaxPage();
		        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
		        Map<String, String> map1 = new ExcelReader(filePath1,"Create").getTestData().get(4);
		        SendFaxDetails sendFaxDetails=new SendFaxDetails(map1);
		        SendFaxPage sendFaxpage=PageFactory.createPageInstance(driver, SendFaxPage.class);
		        sendFaxpage.addNewSendFaxRecord(sendFaxDetails);
		 }
		 
		 //@Test()
		 public void RecieveFax() {
			 
		 }
		 
		 @AfterMethod
		    public void afterEachMethod(Method method){
		        Screenshot screenshot=new Screenshot(driver);
		        screenshot.captureScreen("FaxTemplateE2ETest",method.getName());
		        driver.navigate().refresh();
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