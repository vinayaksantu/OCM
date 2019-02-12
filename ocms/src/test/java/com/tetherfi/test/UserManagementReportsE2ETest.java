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
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.UserManagementPage;
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
        ocmHomePage.navigateToUserManagementPage();
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementReportAccessData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ReportsAccess").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        userManagementPage.searchUserManagementRecord(userDetails.getUserId());
        Assert.assertTrue(userManagementPage.isPageBasedUserAccessPageDisplayed());
        screenshot.captureScreen(driver, "PageBasedUSerAccessDisplayed", "UserManagementReportsE2ETest");
        Thread.sleep(2000);
        userManagementPage.navigateToReportsTab();
        userManagementPage.clearReportAccess();
	}
	
		//@Test (priority=0)
		public void VerifyNoReportsAvailable() throws Exception
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
	        homePage.navigateToOCMReportsPage();
	        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	        ocmReportsPage.chooseReportChannel("Agent");
	        Assert.assertTrue(ocmReportsPage.isReportListDisplayed(), "Agent Reports unavailability assertion failed");
	        ocmReportsPage.chooseReportChannel("CallBack");
	        Assert.assertTrue(ocmReportsPage.isReportListDisplayed(), "CallBack Reports unavailability assertion failed");
	        ocmReportsPage.chooseReportChannel("Chat");
	        Assert.assertTrue(ocmReportsPage.isReportListDisplayed(), "Chat Reports unavailability assertion failed");
	        ocmReportsPage.chooseReportChannel("Common");
	        Assert.assertTrue(ocmReportsPage.isReportListDisplayed(), "Common Reports unavailability assertion failed");
	        ocmReportsPage.chooseReportChannel("Email");
	        Assert.assertTrue(ocmReportsPage.isReportListDisplayed(), "Email Reports unavailability assertion failed");
	        ocmReportsPage.chooseReportChannel("Fax");
	        Assert.assertTrue(ocmReportsPage.isReportListDisplayed(), "Fax Reports unavailability assertion failed");
	        ocmReportsPage.chooseReportChannel("IVR");
	        Assert.assertTrue(ocmReportsPage.isReportListDisplayed(), "IVR Reports unavailability assertion failed");
	        ocmReportsPage.chooseReportChannel("SMS");
	        Assert.assertTrue(ocmReportsPage.isReportListDisplayed(), "SMS Reports unavailability assertion failed");
	        ocmReportsPage.chooseReportChannel("Social Media");
	        Assert.assertTrue(ocmReportsPage.isReportListDisplayed(), "Social Media Reports unavailability assertion failed");
	        screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");  
	        homePage.userLogout();
	        driver.close();
		}
		
	     @Test
	     public void VerifyAccessOfAgentHistoricalReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Agent Historical Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("Agent Historical Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     @Test
	     public void VerifyAccessOfAgentHistoricalReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Agent Historical Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("Agent Historical Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfAgentInteractionReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Agent Interaction Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("Agent Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfAgentInteractionReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Agent Interaction Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("Agent Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfAgentLoginLogoutReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Agent Login Logout Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("Agent Login Logout Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfAgentLoginLogoutReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Agent Login Logout Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("Agent Login Logout Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfAgentSummaryReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Agent Summary Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("Agent Summary Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfAgentSummaryReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Agent Summary Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("Agent Summary Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfAgentVDNReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Agent VDN Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("Agent VDN Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfAgentVDNReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Agent VDN Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("Agent VDN Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfAnalysisCountGraphReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Analysis Count Graph Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("Analysis Count Graph Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfAnalysisCountGraphReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Analysis Count Graph Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("Analysis Count Graph Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfAnalysisCountReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Analysis Count Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("Analysis Count Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfAnalysisCountReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Analysis Count Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("Analysis Count Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfAuditTrailReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Audit Trail Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("Audit Trail Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfAuditTrailReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Audit Trail Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("Audit Trail Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfCallQueueReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Call Queue Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("Call Queue Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfCallQueueReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Call Queue Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("Call Queue Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfCallWorkReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Call Work Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("Call Work Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfCallWorkReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Call Work Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("Call Work Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfCallBackReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("CallBack Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("CallBack");
	         ocmReportsPage.chooseReportName("CallBack Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfCallBackReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("CallBack Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("CallBack");
	         ocmReportsPage.chooseReportName("CallBack Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfCallBackSLAReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("CallBack SLA Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("CallBack");
	         ocmReportsPage.chooseReportName("CallBack SLA Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfCallBackSLAReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("CallBack SLA Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("CallBack");
	         ocmReportsPage.chooseReportName("CallBack SLA Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfChatAgentPerformanceReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Chat Agent Performance Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("Chat Agent Performance Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfChatAgentPerformanceReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Chat Agent Performance Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("Chat Agent Performance Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfChatDetailsReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Chat Details Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("Chat Details Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfChatDetailsReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Chat Details Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("Chat Details Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfChatInteractionReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Chat Interaction Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("Chat Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfChatInteractionReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Chat Interaction Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("Chat Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfEmailAgentPerformanceReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Email Agent Performance Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Email");
	         ocmReportsPage.chooseReportName("Email Agent Performance Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfEmailAgentPerformanceReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Email Agent Performance Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Email");
	         ocmReportsPage.chooseReportName("Email Agent Performance Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfEmailDetailReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Email Detail Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Email");
	         ocmReportsPage.chooseReportName("Email Detail Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfEmailDetailReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Email Detail Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Email");
	         ocmReportsPage.chooseReportName("Email Detail Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfEmailIntervalReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Email Interval Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Email");
	         ocmReportsPage.chooseReportName("Email Interval Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfEmailIntervalReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Email Interval Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Email");
	         ocmReportsPage.chooseReportName("Email Interval Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfEmailOutboundSummaryViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Email Outbound Summary", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Email");
	         ocmReportsPage.chooseReportName("Email Outbound Summary");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfEmailOutboundSummaryWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Email Outbound Summary", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Email");
	         ocmReportsPage.chooseReportName("Email Outbound Summary");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfEmailSkillDailyReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Email Skill Daily Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Email");
	         ocmReportsPage.chooseReportName("Email Skill Daily Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfEmailSkillDailyReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Email Skill Daily Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Email");
	         ocmReportsPage.chooseReportName("Email Skill Daily Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfEmailSLHistoricalReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Email SL Historical Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Email");
	         ocmReportsPage.chooseReportName("Email SL Historical Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfEmailSLHistoricalReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Email SL Historical Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Email");
	         ocmReportsPage.chooseReportName("Email SL Historical Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfFacebookInteractionReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Facebook Interaction Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Social Media");
	         ocmReportsPage.chooseReportName("Facebook Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfFacebookInteractionReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Facebook Interaction Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Social Media");
	         ocmReportsPage.chooseReportName("Facebook Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfFaxDetailReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Fax Detail Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Fax");
	         ocmReportsPage.chooseReportName("Fax Detail Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfFaxDetailReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Fax Detail Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Fax");
	         ocmReportsPage.chooseReportName("Fax Detail Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfFaxReceivedDetailsReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Fax Received Details Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Fax");
	         ocmReportsPage.chooseReportName("Fax Received Details Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfFaxReceivedDetailsReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Fax Received Details Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Fax");
	         ocmReportsPage.chooseReportName("Fax Received Details Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfFaxSentDetailsReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Fax Sent Details Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Fax");
	         ocmReportsPage.chooseReportName("Fax Sent Details Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfFaxSentDetailsReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Fax Sent Details Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Fax");
	         ocmReportsPage.chooseReportName("Fax Sent Details Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIntentDetailReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Intent Detail Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("Intent Detail Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIntentDetailReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Intent Detail Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("Intent Detail Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfInteractionActionsReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Interaction Actions Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("Interaction Actions Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfInteractionActionsReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Interaction Actions Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("Interaction Actions Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRCallTraceReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Call Trace Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Call Trace Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRCallTraceReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Call Trace Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Call Trace Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRCallTransferReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Call Transfer Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Call Transfer Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRCallTransferReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Call Transfer Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Call Transfer Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRCallerIdentificationReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Caller Identification Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Caller Identification Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRCallerIdentificationReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Caller Identification Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Caller Identification Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRCustomerJourneyReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Customer Journey Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Customer Journey Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRCustomerJourneyReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Customer Journey Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Customer Journey Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRDailyReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Daily Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Daily Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRDailyReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Daily Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Daily Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRDropRequestDetailReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Drop Request Detail Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Drop Request Detail Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRDropRequestDetailReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Drop Request Detail Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Drop Request Detail Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRHostTransactionViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Host Transaction", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Host Transaction");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRHostTransactionWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Host Transaction", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Host Transaction");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVROrderTakingReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Order Taking Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Order Taking Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVROrderTakingReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Order Taking Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Order Taking Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRSkillDetailsReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Skill Details Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Skill Details Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRSkillDetailsReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Skill Details Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Skill Details Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRSurveyofCSOReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Survey of CSO Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Survey of CSO Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfIVRSurveyofCSOReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("IVR Survey of CSO Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("IVR Survey of CSO Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfNiceIntegrationReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Nice Integration Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("Nice Integration Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfNiceIntegrationReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Nice Integration Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("Nice Integration Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMAgentAuxReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Agent Aux Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Agent Aux Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMAgentAuxReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Agent Aux Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Agent Aux Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMAgentHistoricalReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Agent Historical Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Agent Historical Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMAgentHistoricalReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Agent Historical Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Agent Historical Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMAgentInteractionReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Agent Interaction Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Agent Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMAgentInteractionReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Agent Interaction Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Agent Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMAgentLoginLogoutReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Agent Login Logout Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Agent Login Logout Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMAgentLoginLogoutReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Agent Login Logout Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Agent Login Logout Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMAgentScriptingReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Agent Scripting Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Agent Scripting Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMAgentScriptingReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Agent Scripting Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Agent Scripting Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMAgentSummaryReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Agent Summary Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Agent Summary Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMAgentSummaryReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Agent Summary Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Agent Summary Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMAnalysisCountReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Analysis Count Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("OCM Analysis Count Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMAnalysisCountReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Analysis Count Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("OCM Analysis Count Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMAudioVideoPlaybackReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Audio Video Playback Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("OCM Audio Video Playback Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMAudioVideoPlaybackReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Audio Video Playback Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("OCM Audio Video Playback Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMCallTransferReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Call Transfer Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("");
	         ocmReportsPage.chooseReportName("OCM Call Transfer Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMCallTransferReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Call Transfer Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("");
	         ocmReportsPage.chooseReportName("OCM Call Transfer Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMChatAgentPerformanceReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Chat Agent Performance Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("OCM Chat Agent Performance Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMChatAgentPerformanceReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Chat Agent Performance Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("OCM Chat Agent Performance Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMChatAnalysisCountReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Chat Analysis Count Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("OCM Chat Analysis Count Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMChatAnalysisCountReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Chat Analysis Count Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("OCM Chat Analysis Count Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMChatHostTransactionReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Chat Host Transaction Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("OCM Chat Host Transaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMChatHostTransactionReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Chat Host Transaction Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("OCM Chat Host Transaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMChatInteractionChatBotReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Chat Interaction ChatBot Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("OCM Chat Interaction ChatBot Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMChatInteractionChatBotReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Chat Interaction ChatBot Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("OCM Chat Interaction ChatBot Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMChatInteractionReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Chat Interaction Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("OCM Chat Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMChatInteractionReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Chat Interaction Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("OCM Chat Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMChatTransferReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Chat Transfer Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("OCM Chat Transfer Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMChatTransferReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Chat Transfer Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("OCM Chat Transfer Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMChatbotInteractionReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Chatbot Interaction Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("OCM Chatbot Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMChatbotInteractionReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Chatbot Interaction Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Chat");
	         ocmReportsPage.chooseReportName("OCM Chatbot Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMDirectBankingWebChatMISReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Direct Banking Web Chat MIS Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Direct Banking Web Chat MIS Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMDirectBankingWebChatMISReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Direct Banking Web Chat MIS Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Direct Banking Web Chat MIS Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMDirectBankingWebChatMISReportAgentViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Direct Banking Web Chat MIS Report - Agent", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Direct Banking Web Chat MIS Report - Agent");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMDirectBankingWebChatMISReportAgentWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Direct Banking Web Chat MIS Report - Agent", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Direct Banking Web Chat MIS Report - Agent");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMDirectBankingWebChatMISReportByTimeProductivityViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Direct Banking Web Chat MIS Report - By Time Productivity", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Direct Banking Web Chat MIS Report - By Time Productivity");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMDirectBankingWebChatMISReportByTimeProductivityWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Direct Banking Web Chat MIS Report - By Time Productivity", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Direct Banking Web Chat MIS Report - By Time Productivity");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMDirectBankingWebChatMISReportServiceQualityViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Direct Banking Web Chat MIS Report - Service Quality", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Direct Banking Web Chat MIS Report - Service Quality");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMDirectBankingWebChatMISReportServiceQualityWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Direct Banking Web Chat MIS Report - Service Quality", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Direct Banking Web Chat MIS Report - Service Quality");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMDirectBankingWebChatMISReportServiceQualityChatNatureViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Direct Banking Web Chat MIS Report - Service Quality (Chat Nature)", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Direct Banking Web Chat MIS Report - Service Quality (Chat Nature)");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMDirectBankingWebChatMISReportServiceQualityChatNatureWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Direct Banking Web Chat MIS Report - Service Quality (Chat Nature)", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Direct Banking Web Chat MIS Report - Service Quality (Chat Nature)");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMDirectBankingWebChatMISReportServiceQualityQuestionsViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Direct Banking WebChat MIS Report - Service Quality Questions", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Direct Banking WebChat MIS Report - Service Quality Questions");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMDirectBankingWebChatMISReportServiceQualityQuestionsWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Direct Banking WebChat MIS Report - Service Quality Questions", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Direct Banking WebChat MIS Report - Service Quality Questions");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMFaxReceivedDetailsReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Fax Received Details Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Fax");
	         ocmReportsPage.chooseReportName("OCM Fax Received Details Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMFaxReceivedDetailsReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Fax Received Details Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Fax");
	         ocmReportsPage.chooseReportName("OCM Fax Received Details Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMFaxSentDetailsReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Fax Sent Details Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Fax");
	         ocmReportsPage.chooseReportName("OCM Fax Sent Details Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMFaxSentDetailsReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Fax Sent Details Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Fax");
	         ocmReportsPage.chooseReportName("OCM Fax Sent Details Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMInteractionActionsReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Interaction Actions Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Interaction Actions Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMInteractionActionsReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Interaction Actions Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Interaction Actions Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMIVRCallTraceReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM IVR Call Trace Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("OCM IVR Call Trace Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMIVRCallTraceReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM IVR Call Trace Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("OCM IVR Call Trace Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMIVRHostTransactionViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM IVR Host Transaction", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("OCM IVR Host Transaction");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMIVRHostTransactionWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM IVR Host Transaction", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("OCM IVR Host Transaction");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMIVROrderTakingReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM IVR Order Taking Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("OCM IVR Order Taking Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMIVROrderTakingReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM IVR Order Taking Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("IVR");
	         ocmReportsPage.chooseReportName("OCM IVR Order Taking Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMNotCalledListReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Not Called List Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Not Called List Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMNotCalledListReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Not Called List Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Not Called List Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMRoleAccessReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Role Access Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("OCM Role Access Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMRoleAccessReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Role Access Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("OCM Role Access Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMSkillHistoricalReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Skill Historical Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Skill Historical Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMSkillHistoricalReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Skill Historical Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Skill Historical Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMSkillProfileReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Skill Profile Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Skill Profile Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMSkillProfileReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM Skill Profile Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("OCM Skill Profile Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMSMSHostTransactionReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM SMS Host Transaction Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("OCM SMS Host Transaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMSMSHostTransactionReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM SMS Host Transaction Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("OCM SMS Host Transaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMSMSInteractionReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM SMS Interaction Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("OCM SMS Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMSMSInteractionReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM SMS Interaction Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("OCM SMS Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMSMSR2WReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM SMS R2W Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("OCM SMS R2W Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMSMSR2WReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM SMS R2W Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("OCM SMS R2W Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMSMSReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM SMS Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("OCM SMS Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMSMSReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM SMS Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("OCM SMS Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMUserListingReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM User Listing Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("OCM User Listing Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMUserListingReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM User Listing Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("OCM User Listing Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMVBCallbackDetailReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM VB Callback Detail Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("OCM VB Callback Detail Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMVBCallbackDetailReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM VB Callback Detail Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("OCM VB Callback Detail Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMWhatsAppInteractionReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM WhatsApp Interaction Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Social Media");
	         ocmReportsPage.chooseReportName("OCM WhatsApp Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOCMWhatsAppInteractionReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("OCM WhatsApp Interaction Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Social Media");
	         ocmReportsPage.chooseReportName("OCM WhatsApp Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOutboundCallsReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Outbound Calls Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("Outbound Calls Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfOutboundCallsReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Outbound Calls Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("Outbound Calls Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfPendingEmailAgeingReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Pending Email Ageing Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Email");
	         ocmReportsPage.chooseReportName("Pending Email Ageing Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfPendingEmailAgeingReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Pending Email Ageing Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Email");
	         ocmReportsPage.chooseReportName("Pending Email Ageing Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfQueueSummaryReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Queue Summary Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("Queue Summary Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfQueueSummaryReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Queue Summary Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("Queue Summary Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfSkillHistoricalReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Skill Historical Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("Skill Historical Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfSkillHistoricalReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Skill Historical Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Agent");
	         ocmReportsPage.chooseReportName("Skill Historical Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfSMSAlertReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("SMS Alert Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("SMS Alert Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfSMSAlertReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("SMS Alert Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("SMS Alert Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfSMSAnalysisCountReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("SMS Analysis Count Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("SMS Analysis Count Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfSMSAnalysisCountReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("SMS Analysis Count Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("SMS Analysis Count Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfSMSDetailReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("SMS Detail Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("SMS Detail Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfSMSDetailReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("SMS Detail Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("SMS Detail Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfSMSHostTransactionReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("SMS Host Transaction Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("SMS Host Transaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfSMSHostTransactionReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("SMS Host Transaction Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("SMS Host Transaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfSMSInteractionReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("SMS Interaction Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("SMS Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfSMSInteractionReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("SMS Interaction Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("SMS Interaction Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfSMSReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("SMS Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("SMS Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfSMSReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("SMS Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("SMS Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfSMSSurveyReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("SMS Survey Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("SMS Survey Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfSMSSurveyReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("SMS Survey Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("SMS");
	         ocmReportsPage.chooseReportName("SMS Survey Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfVBCallbackDetailsReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("VB Callback Details Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("VB Callback Details Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfVBCallbackDetailsReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("VB Callback Details Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("VB Callback Details Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfWakeUpCallReportViewOnly() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Wake Up Call Report", "View");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("Wake Up Call Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertFalse(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
	         homePage.userLogout();
	         driver.close();
	     }

	     //@Test
	     public void VerifyAccessOfWakeUpCallReportWithExport() throws Exception
	     {
	         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	         userManagementPage.ProvideReportAccess("Wake Up Call Report", "Export");
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
	         homePage.navigateToOCMReportsPage();
	         OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver,OCMReportsPage.class);
	         ocmReportsPage.chooseReportChannel("Common");
	         ocmReportsPage.chooseReportName("Wake Up Call Report");
	         Assert.assertTrue(ocmReportsPage.isShowButtonsDisplayed(), "Show Buttons assertion failed");
	         Assert.assertTrue(ocmReportsPage.isExportBtnDisplayed(), "Export button assertion failed");
	         screenshot.captureScreen(driver, "VerifyAccess", "UserManagementE2ETest");
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

