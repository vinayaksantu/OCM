package com.tetherfi.test;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.user.UserDetails;
import com.tetherfi.pages.AgentScriptingPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IWChatPage;
import com.tetherfi.pages.IWIVRPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMDashboardPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.UserManagementPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class UserManagementOtherAppsE2ETest extends BaseTest {
	Screenshot screenshot=new Screenshot();
	
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
        screenshot.captureScreen(driver, "PageBasedUSerAccessDisplayed", "UserManagementOtherAppsE2ETest");
        userManagementPage.navigateToOtherAppsTab();
        userManagementPage.clearOtherAppsAccess();
	}
	
	//@Test
	public void VerifyAgentScriptingPreviewerAccess() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideOtherAppsAccess("Agent Scripting Previewer");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(4);
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
        AgentScriptingPage agentScriptingPage = PageFactory.createPageInstance(driver,AgentScriptingPage.class);
        agentScriptingPage.closeDashboard();
        Assert.assertTrue(agentScriptingPage.checkRole("Previewer"), "Agent Scripting Role Assertion Failed");
        driver.close();
	}
	
	//@Test
	public void VerifyAgentScriptingMakerAccess() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideOtherAppsAccess("Agent Scripting Maker");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(4);
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
        AgentScriptingPage agentScriptingPage = PageFactory.createPageInstance(driver,AgentScriptingPage.class);
        agentScriptingPage.closeDashboard();
        Assert.assertTrue(agentScriptingPage.checkRole("Maker"), "Agent Scripting Role Assertion Failed");
        driver.close();
	}
	
	//@Test
	public void VerifyAgentScriptingCheckerAccess() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideOtherAppsAccess("Agent Scripting Checker");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(4);
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
        AgentScriptingPage agentScriptingPage = PageFactory.createPageInstance(driver,AgentScriptingPage.class);
        agentScriptingPage.closeDashboard();
        Assert.assertTrue(agentScriptingPage.checkRole("Checker"), "Agent Scripting Role Assertion Failed");
        driver.close();
	}
	
	//@Test
	public void VerifyAgentScriptingMakerCheckerAccess() throws Exception
	{            
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.ProvideOtherAppsAccess("Agent Scripting Checker");
        Thread.sleep(2000);
        userManagementPage.ProvideOtherAppsAccess("Agent Scripting Maker");
		Thread.sleep(2000);
        driver.close();
        try {
            PageFactory.reset();
           BrowserFactory browserFactory = new BrowserFactory();
          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(4);
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
        AgentScriptingPage agentScriptingPage = PageFactory.createPageInstance(driver,AgentScriptingPage.class);
        agentScriptingPage.closeDashboard();
        Assert.assertTrue(agentScriptingPage.checkRole("MAKER,CHECKER"), "Agent Scripting Role Assertion Failed");
        driver.close();
	}
	
	//@Test
		public void VerifyIWChatPreviewerAccess() throws Exception
		{            
	        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	        userManagementPage.ProvideOtherAppsAccess("InteractionWorkFlowChatPreviewer");
			Thread.sleep(2000);
	        driver.close();
	        try {
	            PageFactory.reset();
	           BrowserFactory browserFactory = new BrowserFactory();
	          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(5);
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
	        IWChatPage iwChatPage = PageFactory.createPageInstance(driver,IWChatPage.class);
	        iwChatPage.closeDashboard();
	        Assert.assertTrue(iwChatPage.checkRole("Previewer"), "IW Chat Role Assertion Failed");
	        driver.close();
		}
		
		//@Test
		public void VerifyIWChatMakerAccess() throws Exception
		{            
	        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	        userManagementPage.ProvideOtherAppsAccess("InteractionWorkFlowChatMaker");
			Thread.sleep(2000);
	        driver.close();
	        try {
	            PageFactory.reset();
	           BrowserFactory browserFactory = new BrowserFactory();
	          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(5);
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
	        IWChatPage iwChatPage = PageFactory.createPageInstance(driver,IWChatPage.class);
	        iwChatPage.closeDashboard();
	        Assert.assertTrue(iwChatPage.checkRole("Maker"), "IW Chat Role Assertion Failed");
	        driver.close();
		}
		
		//@Test
		public void VerifyIWChatCheckerAccess() throws Exception
		{            
	        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	        userManagementPage.ProvideOtherAppsAccess("InteractionWorkFlowChatChecker");
			Thread.sleep(2000);
	        driver.close();
	        try {
	            PageFactory.reset();
	           BrowserFactory browserFactory = new BrowserFactory();
	          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(5);
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
	        IWChatPage iwChatPage = PageFactory.createPageInstance(driver,IWChatPage.class);
	        iwChatPage.closeDashboard();
	        Assert.assertTrue(iwChatPage.checkRole("Checker"), "IW Chat Role Assertion Failed");
	        driver.close();
		}
		
		//@Test
		public void VerifyIWChatMakerCheckerAccess() throws Exception
		{            
	        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
	        userManagementPage.ProvideOtherAppsAccess("InteractionWorkFlowChatMaker");
	        userManagementPage.ProvideOtherAppsAccess("InteractionWorkFlowChatChecker");
			Thread.sleep(2000);
	        driver.close();
	        try {
	            PageFactory.reset();
	           BrowserFactory browserFactory = new BrowserFactory();
	          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(5);
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
	        IWChatPage iwChatPage = PageFactory.createPageInstance(driver,IWChatPage.class);
	        iwChatPage.closeDashboard();
	        Assert.assertTrue(iwChatPage.checkRole("Maker,Checker"), "IW Chat Role Assertion Failed");
	        driver.close();
		}
		
		//@Test
				public void VerifyIWIVRPreviewerAccess() throws Exception
				{            
			        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
			        userManagementPage.ProvideOtherAppsAccess("InteractionWorkFlowPreviewer");
					Thread.sleep(2000);
			        driver.close();
			        try {
			            PageFactory.reset();
			           BrowserFactory browserFactory = new BrowserFactory();
			          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(6);
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
			        IWIVRPage iwIvrPage = PageFactory.createPageInstance(driver,IWIVRPage.class);
			        iwIvrPage.closeDashboard();
			        Assert.assertTrue(iwIvrPage.checkRole("Previewer"), "IW Chat Role Assertion Failed");
			        driver.close();
				}
				
				//@Test
				public void VerifyIVRMakerAccess() throws Exception
				{            
			        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
			        userManagementPage.ProvideOtherAppsAccess("Interaction WorkFlow Maker");
					Thread.sleep(2000);
			        driver.close();
			        try {
			            PageFactory.reset();
			           BrowserFactory browserFactory = new BrowserFactory();
			          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(6);
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
			        IWIVRPage iwIvrPage = PageFactory.createPageInstance(driver,IWIVRPage.class);
			        iwIvrPage.closeDashboard();
			        Assert.assertTrue(iwIvrPage.checkRole("Maker"), "IW IVR Role Assertion Failed");
			        driver.close();
				}
				
				//@Test
				public void VerifyIWIVRCheckerAccess() throws Exception
				{            
			        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
			        userManagementPage.ProvideOtherAppsAccess("InteractionWorkFlowChecker");
					Thread.sleep(2000);
			        driver.close();
			        try {
			            PageFactory.reset();
			           BrowserFactory browserFactory = new BrowserFactory();
			          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(6);
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
			        IWIVRPage iwIvrPage = PageFactory.createPageInstance(driver,IWIVRPage.class);
			        iwIvrPage.closeDashboard();
			        Assert.assertTrue(iwIvrPage.checkRole("Checker"), "IW IVR Role Assertion Failed");
			        driver.close();
				}
				
				//@Test
				public void VerifyIWIVRMakerCheckerAccess() throws Exception
				{            
			        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
			        userManagementPage.ProvideOtherAppsAccess("Interaction WorkFlow Maker");
			        userManagementPage.ProvideOtherAppsAccess("InteractionWorkFlowChecker");
					Thread.sleep(2000);
			        driver.close();
			        try {
			            PageFactory.reset();
			           BrowserFactory browserFactory = new BrowserFactory();
			          driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
			    		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
			        	Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(6);
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
			        IWIVRPage iwIvrPage = PageFactory.createPageInstance(driver,IWIVRPage.class);
			        iwIvrPage.closeDashboard();
			        Assert.assertTrue(iwIvrPage.checkRole("Maker,Checker"), "IW IVR Role Assertion Failed");
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
	            
	        }catch (Exception e){
	            PageFactory.reset();
	            driver.close();
	            e.printStackTrace();
	        }
	   	 
		
	    }

}

