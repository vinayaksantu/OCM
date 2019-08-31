package com.tetherfi.test;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.user.UserDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMDashboardPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.UserManagementPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class UserManagementDashboardE2ETest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
	
	@BeforeMethod
	public void OCMPage() throws Exception {
		HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        ocmHomePage.navigateToUserManagementPage();
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementDashAccessData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"DashAccess").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        userManagementPage.searchUserManagementRecord(userDetails.getUserId());
        Assert.assertTrue(userManagementPage.isPageBasedUserAccessPageDisplayed());
        screenshot.captureScreen(driver, "PageBasedUSerAccessDisplayed", "UserManagementDashboardE2ETest");
        userManagementPage.navigateToDashboardTab();
        userManagementPage.clearDashboardAccess();
	}
	
	@Test (priority=0)
	public void VerifyDashboardBtnNotAvailable() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"));
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
        Assert.assertTrue(homePage.noDashboardAccess(), "Dashboard assertion failed");
        homePage.userLogout();
        driver.close();
	}
	
	@Test (priority=1)
	public void VerifyAccessOfChatCallback() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideDashboardAccess("Chat Callback Dashboard");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"));
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
        homePage.navigateToDashBoard();
        OCMDashboardPage ocmDashBoardPage = PageFactory.createPageInstance(driver,OCMDashboardPage.class);
        Assert.assertTrue(ocmDashBoardPage.checkDashboardElementExists("perm_phone_msg"), "Dashboard Item assertion failed");
        homePage.userLogout();
        driver.close();
	}
	
	@Test (priority=2)
	public void VerifyAccessOfChat() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideDashboardAccess("Chat Dashboard");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"));
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
        
        homePage.navigateToDashBoard();
        OCMDashboardPage ocmDashBoardPage = PageFactory.createPageInstance(driver,OCMDashboardPage.class);
        Assert.assertTrue(ocmDashBoardPage.checkDashboardElementExists("chat"), "Dashboard Item assertion failed");
        homePage.userLogout();
        driver.close();
	}
	
	@Test (priority=3)
	public void VerifyAccessOfChatRealTime() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideDashboardAccess("Chat Real Time Dashboard");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"));
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
        
        homePage.navigateToDashBoard();
        OCMDashboardPage ocmDashBoardPage = PageFactory.createPageInstance(driver,OCMDashboardPage.class);
        Assert.assertTrue(ocmDashBoardPage.checkDashboardElementExists("chat sync"), "Dashboard Item assertion failed");
        homePage.userLogout();
        driver.close();
	}
	
	@Test (priority=4)
	public void VerifyAccessOfChatSelfService() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideDashboardAccess("Chat Self-Service Dashboard");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"));
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
        
        homePage.navigateToDashBoard();
        OCMDashboardPage ocmDashBoardPage = PageFactory.createPageInstance(driver,OCMDashboardPage.class);
        Assert.assertTrue(ocmDashBoardPage.checkDashboardElementExists(""), "Dashboard Item assertion failed");
        homePage.userLogout();
        driver.close();
	}
	
	@Test (priority=5)
	public void VerifyAccessOfCustomer() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideDashboardAccess("Customer Dashboard");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"));
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
        
        homePage.navigateToDashBoard();
        OCMDashboardPage ocmDashBoardPage = PageFactory.createPageInstance(driver,OCMDashboardPage.class);
        Assert.assertTrue(ocmDashBoardPage.checkDashboardElementExists("person"), "Dashboard Item assertion failed");
        homePage.userLogout();
        driver.close();
	}
	
	@Test (priority=6)
	public void VerifyAccessOfEmail() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideDashboardAccess("Email Dashboard");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"));
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
        
        homePage.navigateToDashBoard();
        OCMDashboardPage ocmDashBoardPage = PageFactory.createPageInstance(driver,OCMDashboardPage.class);
        Assert.assertTrue(ocmDashBoardPage.checkDashboardElementExists("email"), "Dashboard Item assertion failed");
        homePage.userLogout();
        driver.close();
	}
	
	@Test (priority=7)
	public void VerifyAccessOfEmailRealTime() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideDashboardAccess("Email Real Time Dashboard");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"));
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
        
        homePage.navigateToDashBoard();
        OCMDashboardPage ocmDashBoardPage = PageFactory.createPageInstance(driver,OCMDashboardPage.class);
        Assert.assertTrue(ocmDashBoardPage.checkDashboardElementExists("email sync"), "Dashboard Item assertion failed");
        homePage.userLogout();
        driver.close();
	}
	
	@Test (priority=8)
	public void VerifyAccessOfFax() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideDashboardAccess("Fax Dashboard");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"));
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
        
        homePage.navigateToDashBoard();
        OCMDashboardPage ocmDashBoardPage = PageFactory.createPageInstance(driver,OCMDashboardPage.class);
        Assert.assertTrue(ocmDashBoardPage.checkDashboardElementExists("voicemail"), "Dashboard Item assertion failed");
        homePage.userLogout();
        driver.close();
	}
	
	@Test (priority=9)
	public void VerifyAccessOfSMS() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideDashboardAccess("SMS Dashboard");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"));
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
        
        homePage.navigateToDashBoard();
        OCMDashboardPage ocmDashBoardPage = PageFactory.createPageInstance(driver,OCMDashboardPage.class);
        Assert.assertTrue(ocmDashBoardPage.checkDashboardElementExists("sms"), "Dashboard Item assertion failed");
        homePage.userLogout();
        driver.close();
	}
	
	@Test (priority=10)
	public void VerifyAccessOfVoiceCallback() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideDashboardAccess("Voice Callback Dashboard");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"));
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
        
        homePage.navigateToDashBoard();
        OCMDashboardPage ocmDashBoardPage = PageFactory.createPageInstance(driver,OCMDashboardPage.class);
        Assert.assertTrue(ocmDashBoardPage.checkDashboardElementExists("phone_missed"), "Dashboard Item assertion failed");
        homePage.userLogout();
        driver.close();
	}
	
	@Test (priority=11)
	public void VerifyAccessOfVoiceRealTime() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideDashboardAccess("Voice Real Time Dashboard");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"));
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
        
        homePage.navigateToDashBoard();
        OCMDashboardPage ocmDashBoardPage = PageFactory.createPageInstance(driver,OCMDashboardPage.class);
        Assert.assertTrue(ocmDashBoardPage.checkDashboardElementExists("phone sync"), "Dashboard Item assertion failed");
        homePage.userLogout();
        driver.close();
	}
	
	@Test (priority=12)
	public void VerifyAccessOfVoiceSelfService() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideDashboardAccess("Voice/Self-Service Dashboard");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=new LoginPage(driver);
                loginPage.login(map.get("Username"),map.get("Password"));
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
        
        homePage.navigateToDashBoard();
        OCMDashboardPage ocmDashBoardPage = PageFactory.createPageInstance(driver,OCMDashboardPage.class);
        Assert.assertTrue(ocmDashBoardPage.checkDashboardElementExists("phone"), "Dashboard Item assertion failed");
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
	                loginPage.login(map.get("Username"),map.get("Password"));
	                Thread.sleep(5000);
	            }
	            HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	            
	        }catch (Exception e){
	            PageFactory.reset();
	            driver.close();
	            e.printStackTrace();
	        }
	   	 
		
	    }

}

