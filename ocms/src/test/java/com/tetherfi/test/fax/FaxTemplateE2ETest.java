package com.tetherfi.test.fax;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.fax.FaxTemplateDetails;
import com.tetherfi.model.tmac.TmacUserDetails;
import com.tetherfi.pages.FaxAutoACKConfigurationPage;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.SendFaxPage;
import com.tetherfi.pages.TmacLoginPage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.pages.TmacPopupPage;
import com.tetherfi.pages.WorkCodeListPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class FaxTemplateE2ETest extends BaseTest{
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateToFaxTemplatePage(Method method) throws Exception {
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
                ocmHomePage.navigateToTab("FAX");
                FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
                Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
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
	public void VerifyFaxTemplateInAutoAckConfig() throws Exception {
        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
		faxPage.navigateToFaxAutoACKConfigurationPage();
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.isFaxAutoACKConfigurationPageDisplayed(), "FAX page assertion failed");
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyFaxTemplate(faxTemplateDetails));
	}
	
	@Test(priority=2,groups= {"OCM"})
	public void VerifyFaxTemplateInSendFax() throws Exception {
        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
		faxPage.navigateToSendFaxPage();
		SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.isSendFaxPageDisplayed(), "FAX page assertion failed");
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        Assert.assertTrue(sendFaxPage.verifyFaxTemplate(faxTemplateDetails));
	}
	
	@Test(priority=3,groups= {"OCM"})
	public void VerifyFaxTemplateHTMLInAutoAckConfig() throws Exception {
        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
		faxPage.navigateToFaxAutoACKConfigurationPage();
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.isFaxAutoACKConfigurationPageDisplayed(), "FAX page assertion failed");
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
        FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyFaxTemplate(faxTemplateDetails));
	}
	
	@Test(priority=4,groups= {"OCM"})
	public void VerifyFaxTemplateHTMLInSendFax() throws Exception {
        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
		faxPage.navigateToSendFaxPage();
		SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.isSendFaxPageDisplayed(), "FAX page assertion failed");
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
        FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        Assert.assertTrue(sendFaxPage.verifyFaxTemplate(faxTemplateDetails));
	}
	
	@Test(priority=5,groups= {"TMAC"})
	public void VerfyFaxTemplateInTMAC() throws Exception {
		TmacLoginPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacLoginPage.class);
		tmacPopupPage.changeStatus("Available");
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
        FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        Assert.assertTrue(tmacPopupPage.verifyFaxTemplate(faxTemplateDetails));
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
