package com.tetherfi.test.chat;

import com.tetherfi.model.chat.ChatTemplateDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.TmacUserDetails;
import com.tetherfi.pages.*;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ChatAPI;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ChatTemplatesTest {
	protected WebDriver driver;
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToChatTemplatesPage(Method method) throws InterruptedException, Exception {
    	try {
    		PageFactory.reset();
    		BrowserFactory browserFactory = new BrowserFactory();
	        driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	    }
    	catch (Exception e){
    		PageFactory.reset();
	        driver.close();
	        e.printStackTrace();
    	}
    	System.out.println("Started Executing : "+method.getName());
	    Test t = method.getAnnotation(Test.class);
	    Map<String, String> map;
	    if(t.groups()[0].equalsIgnoreCase("OCM")){	
	    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	        map= new ExcelReader(filePath,"Login").getTestData().get(0);
	        try{
	        	driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	        }
	        catch (TimeoutException e){
	        	e.printStackTrace();
	            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	            }
	        if(map.get("LoginType").equals("Custom")){
	        LoginPage loginPage=PageFactory.createPageInstance(driver,LoginPage.class);
	        Assert.assertTrue(loginPage.isLoginPageDisplayed(),"Login page not loaded");
	        loginPage.login(map.get("Username"),map.get("Password"));
	        try {
	        	Thread.sleep(5000);
	        } catch (InterruptedException e) {
	        	e.printStackTrace();
				}
	        }
	        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
	        homePage.navigateToOCMPage();
	        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
	        ocmHomePage.navigateToTab("Chat");
	        ChatPage chatPage = PageFactory.createPageInstance(driver,ChatPage.class);
	        Assert.assertTrue(chatPage.isChatPageDisplayed(),"chat page assertion failed");
	        chatPage.navigateToChatTemplatesPage();
	        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
	        Assert.assertTrue(chatTemplatesPage.isChatTemplatePageDisplayed(),"Chat Template page assertion failed");
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
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
      }
    
    /*@Test(priority=1,groups= {"OCM"})
	public void ChatTemplatePage() {
        ChatTemplatesPage ChatTemplatePage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(ChatTemplatePage.VerifyLogo(),"Logo assertion failed");
        Assert.assertTrue(ChatTemplatePage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("ChatTemplateTest","Maximize");
    	Assert.assertTrue(ChatTemplatePage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("ChatTemplateTest","Minimize");	
    }
	
    @Test(priority=2,groups= {"OCM"})
    public void VerifyDepartmentsDropdownForAllTheColumns() throws Exception {
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Departments");
        Thread.sleep(1000);
        Assert.assertTrue(ChatTemplatesPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3,groups= {"OCM"})
    public void VerifyDepartmentsColumnsHeaderEnable() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Thread.sleep(1000);
        Assert.assertTrue(chatTemplatesPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4,groups= {"OCM"})
    public void VerifyDepartmentsColumnsHeaderDisable() throws Exception {
    	 ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
         chatTemplatesPage.navigateToTab("Departments");
         Thread.sleep(1000);
         Assert.assertFalse(chatTemplatesPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=5,groups= {"OCM"})
    public void VerifyGroupsDropdownForAllTheColumns() throws Exception {
    	ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Thread.sleep(2000);
        Assert.assertTrue(chatTemplatesPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=6,groups= {"OCM"})
    public void VerifyGroupsColumnsHeaderEnable() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Thread.sleep(2000);
        Assert.assertTrue(chatTemplatesPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=7,groups= {"OCM"})
    public void VerifyGroupsColumnsHeaderDisable() throws Exception {
    	 ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
    	 chatTemplatesPage.navigateToTab("Groups");
         Thread.sleep(2000);
         Assert.assertFalse(chatTemplatesPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=8,groups= {"OCM"})
    public void VerifyMainsDropdownForAllTheColumns() throws Exception {
    	ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=9,groups= {"OCM"})
    public void VerifyMainsColumnsHeaderEnable() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=10,groups= {"OCM"})
    public void VerifyMainsColumnsHeaderDisable() throws Exception {
    	 ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
         Assert.assertFalse(chatTemplatesPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=11,groups= {"OCM"})
    public void AddNewDepartmentRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.isDepartmentTabDisplayed());
        chatTemplatesPage.addNewDepartmentRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    
    @Test(priority=12,groups= {"OCM"})//,dependsOnMethods = "AddNewDepartmentRecord")
    public void AddDuplicateDepartmentRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Departments");
        ChatTemplatesPage.addNewDepartmentRecord(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nDuplicate Name");
    }
    
    @Test(priority=13,groups= {"OCM"})//,dependsOnMethods ="AddNewDepartmentRecord")
    public void VerifyAuditTrialReportForCreateDepartment() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyChatTemplatesDepartmentCreate(ChatTemplatesDetails,"Create"));
    }
    
    @Test(priority=14,groups= {"OCM"})
    public void AddEmptyDepartmentRecord() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Departments");
        ChatTemplatesPage.addNewEmptyRecord(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Name, Enabled, Channel", "Add invalid record assertion failed");
    }
    
    @Test(priority=15,groups= {"OCM"})
    public void AddRecordWithoutDepartment() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Departments");
        ChatTemplatesPage.addRecordWithoutDepartment(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Name", "Add invalid record assertion failed");
    }
    
    @Test(priority=16,groups= {"OCM"})
    public void AddRecordWithoutEnabled() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Departments");
        ChatTemplatesPage.addRecordWithoutEnabled(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Enabled", "Add invalid record assertion failed");
    }
    
    @Test(priority=17,groups= {"OCM"})
    public void AddRecordWithoutChannel() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Departments");
        ChatTemplatesPage.addRecordWithoutChannel(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Channel", "Add invalid record assertion failed");
    }
    @Test(priority=18,groups= {"OCM"})
    public void VerifyCancelBtnAtAddDepartmentRecord(){
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Departments");
        ChatTemplatesPage.clickOnAddDepartmentRecord();
        ChatTemplatesPage.clickOnCancelBtn();
        Assert.assertFalse(ChatTemplatesPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
	}
    
    @Test(priority=19,groups= {"OCM"})//,dependsOnMethods = "AddNewDepartmentRecord")
    public void AddNewGroupsRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.isGroupTabDisplayed());
        chatTemplatesPage.addNewGroupRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    
    @Test(priority=20,groups= {"OCM"})//,dependsOnMethods = "AddNewGroupsRecord")
    public void AddDuplicateGroupsRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Groups");
        ChatTemplatesPage.addNewGroupRecord(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nDuplicate Name");
    }
    
    @Test(priority=21,groups= {"OCM"})//,dependsOnMethods ="AddNewGroupsRecord")
    public void VerifyAuditTrialReportForCreateGroup() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyChatTemplatesGroupCreate(ChatTemplatesDetails,"Create"));
    }
    
    @Test(priority=22,groups= {"OCM"})
    public void AddEmptyGroupRecord() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Groups");
        ChatTemplatesPage.addNewEmptyGroupRecord(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Department Name, Name, Enabled", "Add invalid record assertion failed");
    }
    
    @Test(priority=23,groups= {"OCM"})
    public void AddRecordWithoutDepartmentName() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Groups");
        ChatTemplatesPage.addRecordWithoutDepartmentName(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Department Name", "Add invalid record assertion failed");
    }
    
    @Test(priority=24,groups= {"OCM"})
    public void AddRecordWithoutName() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Groups");
        ChatTemplatesPage.addRecordWithoutName(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Name", "Add invalid record assertion failed");
    }
    @Test(priority=25,groups= {"OCM"})
    public void AddRecordWithoutGroupEnabled() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Groups");
        ChatTemplatesPage.addRecordWithoutGroupEnabled(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Enabled", "Add invalid record assertion failed");
    }
    
    @Test(priority=26,groups= {"OCM"})
    public void VerifyCancelBtnAtAddGroupsRecord(){
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Groups");
        ChatTemplatesPage.clickOnAddGroupRecord();
        ChatTemplatesPage.clickOnCancelBtn();
        Assert.assertFalse(ChatTemplatesPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
	}
    
    @Test(priority=27,groups= {"OCM"})//,dependsOnMethods = "AddNewGroupsRecord")
    public void AddNewChatTemplatesRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.addNewChatTemplatesRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    
    @Test(priority=28,groups= {"OCM"})//,dependsOnMethods = "AddNewChatTemplatesRecord")
    public void AddDuplicateChatTemplatesRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addNewChatTemplatesRecord(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nDuplicate record. Department Name, Group Name,Name combination should be unique.");
    }
    
    @Test(priority=29,groups= {"OCM"})//,dependsOnMethods ="AddNewChatTemplatesRecord")
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyChatTemplatesCreate(ChatTemplatesDetails,"Create"));
    }
    
    @Test(priority=30,groups= {"OCM"})
    public void AddEmptyChatTemplateRecord() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addNewEmptyChatTemplateRecord(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Department Name, Group Name, Name, Enabled, Text, Start Time, End Time", "Add invalid record assertion failed");
    }
    
    @Test(priority=31,groups= {"OCM"})
    public void AddRecordWithoutChatDepartmentName() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addRecordWithoutChatDepartmentName(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Department Name, Group Name", "Add invalid record assertion failed");
    }
    
    @Test(priority=32,groups= {"OCM"})
    public void AddRecordWithoutGroupsName() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addRecordWithoutGroupName(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Group Name", "Add invalid record assertion failed");
    }
    
    @Test(priority=33,groups= {"OCM"})
    public void AddRecordWithoutChatName() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addRecordWithoutChatName(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Name", "Add invalid record assertion failed");
    }
    @Test(priority=34,groups= {"OCM"})
    public void AddRecordWithoutText() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addRecordWithoutText(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Text", "Add invalid record assertion failed");
    }
    
    @Test(priority=35,groups= {"OCM"})
    public void AddRecordWithoutStartTime() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addRecordWithoutStartTime(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Start Time", "Add invalid record assertion failed");
    }
    
    @Test(priority=36,groups= {"OCM"})
    public void AddRecordWithoutEndTime() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addRecordWithoutEndTime(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide End Time", "Add invalid record assertion failed");
    }
    
    @Test(priority=37,groups= {"OCM"})
    public void AddRecordWithoutChatEnabled() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addRecordWithoutChatEnabled(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Enabled", "Add invalid record assertion failed");
    }
    
    @Test(priority=38,groups= {"OCM"})
    public void VerifyCancelBtnAtAddChatTemplateRecord(){
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.clickOnAddRecord();
        ChatTemplatesPage.clickOnCancelBtn();
        Assert.assertFalse(ChatTemplatesPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
	}
    
    @Test(priority=39,groups= {"OCM"})
    public void DepartmentExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.verifyDepartmentExportToExcel(filePath));
    }
    
    @Test(priority=40,groups= {"OCM"})
    public void DepartmentExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\ChatTemplatesDepartment.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelDepartmentSheet(maplist));	
    } 
    
    @Test(priority=41,groups= {"OCM"})
    public void DepatmentSortingByAscending() throws IOException {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        chatTemplatesPage.SortByDepartmentAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\ChatTemplatesDepartment (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelDepartmentSheet(maplist));
    }
    
    @Test(priority=42,groups= {"OCM"})
    public void DepartmentSortingByDescending() throws IOException {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        chatTemplatesPage.SortByDepartmentDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\ChatTemplatesDepartment (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelDepartmentSheet(maplist));
    }
    
    @Test(priority=43,groups= {"OCM"})
    public void GroupsExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.verifyGroupsExportToExcel(filePath));
    }
    
    @Test(priority=44,groups= {"OCM"})
    public void GroupsExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\ChatTemplateGroup.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelGroupsSheet(maplist));	
    } 
    
    @Test(priority=45,groups= {"OCM"})
    public void GroupsSortingByAscending() throws IOException {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        chatTemplatesPage.SortByGroupAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\ChatTemplateGroup (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelGroupsSheet(maplist));
    }
    
    @Test(priority=46,groups= {"OCM"})
    public void GroupsSortingByDescending() throws IOException {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        chatTemplatesPage.SortByGroupDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\ChatTemplateGroup (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelGroupsSheet(maplist));
    }
    
    @Test(priority=47,groups= {"OCM"})
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=48,groups= {"OCM"})
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Chat Templates.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelSheet(maplist));	
    } 
    
    @Test(priority=49,groups= {"OCM"})
    public void SortingByAscending() throws IOException {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Chat Templates (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=50,groups= {"OCM"})
    public void SortingByDescending() throws IOException {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Chat Templates (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=51,groups= {"OCM"})
    public void VerifyDepartmentArrowMoveForPreviousAndNextPage() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.verifyArrowMoveForPreviousAndNextPage(0),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=52,groups= {"OCM"})
    public void VerifyDepartmentArrowMoveForFirstAndLastPage() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.verifyArrowMoveForFirstAndLastPage(0),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=53,groups= {"OCM"})
    public void VerifyDepartmentTotalNumberOfItemsPerPageDetails() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.verifyTotalNumberOfItemsPerPageDetails(0),"item per page assertion failed");
    }
    
    @Test(priority=54,groups= {"OCM"})
    public void VerifyDepartmentNumberOfItemsPerPageSelection() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.verifyNumberOfItemsPerPage(0),"item per page assertion failed");
    }
    
    @Test(priority=55,groups= {"OCM"})
    public void VerifyGroupsArrowMoveForPreviousAndNextPage() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.verifyArrowMoveForPreviousAndNextPage(1),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=56,groups= {"OCM"})
    public void VerifyGroupsArrowMoveForFirstAndLastPage() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.verifyArrowMoveForFirstAndLastPage(1),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=57,groups= {"OCM"})
    public void VerifyGroupsTotalNumberOfItemsPerPageDetails() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.verifyTotalNumberOfItemsPerPageDetails(1),"item per page assertion failed");
    }
    
    @Test(priority=58,groups= {"OCM"})
    public void VerifyGroupsNumberOfItemsPerPageSelection() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.verifyNumberOfItemsPerPage(1),"item per page assertion failed");
    }
    
    @Test(priority=59,groups= {"OCM"})
    public void VerifyArrowMoveForPreviousAndNextPage() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.verifyArrowMoveForPreviousAndNextPage(2),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=60,groups= {"OCM"})
    public void VerifyArrowMoveForFirstAndLastPage() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.verifyArrowMoveForFirstAndLastPage(2),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=61,groups= {"OCM"})
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.verifyTotalNumberOfItemsPerPageDetails(2),"item per page assertion failed");
    }
    
    @Test(priority=62,groups= {"OCM"})
    public void VerifyNumberOfItemsPerPageSelection() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.verifyNumberOfItemsPerPage(2),"item per page assertion failed");
    }
    
    @Test(priority=63,groups= {"OCM"})
    public void VerifyDepartmentDragAndDrop() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Thread.sleep(2000);
        chatTemplatesPage.dragDepartmentColumntoGroup("Enabled");
        Assert.assertTrue(chatTemplatesPage.verifyDragColumntoGroup("Enabled", 0),"drag and drop assertion failed");
    }
    @Test(priority=64,groups= {"OCM"})
    public void VerifyDepartmentDragAndDropofAlreadyGroupedHeader() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Thread.sleep(2000);
        chatTemplatesPage.dragDepartmentColumntoGroup("Enabled");
        chatTemplatesPage.dragDepartmentColumntoGroup("Enabled");
        Assert.assertTrue(chatTemplatesPage.verifyDragColumntoGroup("Enabled",0),"drag and drop assertion failed");
    }
    
   @Test(priority=65,groups= {"OCM"})
    public void VerifyGroupsDragAndDrop() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Thread.sleep(2000);
        chatTemplatesPage.dragGroupsColumntoGroup("Enabled");
        Assert.assertTrue(chatTemplatesPage.verifyDragColumntoGroup("Enabled",1),"drag and drop assertion failed");
    }
    @Test(priority=66,groups= {"OCM"})
    public void VerifyGroupsDragAndDropofAlreadyGroupedHeader() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Thread.sleep(2000);
        chatTemplatesPage.dragGroupsColumntoGroup("Enabled");
        chatTemplatesPage.dragGroupsColumntoGroup("Enabled");
        Assert.assertTrue(chatTemplatesPage.verifyDragColumntoGroup("Enabled",1),"drag and drop assertion failed");
    }
    
    @Test(priority=67,groups= {"OCM"})
    public void VerifyDragAndDrop() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Thread.sleep(2000);
        chatTemplatesPage.dragColumntoGroup("Enabled");
        Assert.assertTrue(chatTemplatesPage.verifyDragColumntoGroup("Enabled",2),"drag and drop assertion failed");
    }
    @Test(priority=68,groups= {"OCM"})
    public void VerifyDragAndDropofAlreadyGroupedHeader() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Thread.sleep(2000);
        chatTemplatesPage.dragColumntoGroup("Enabled");
        chatTemplatesPage.dragColumntoGroup("Enabled");
        Assert.assertTrue(chatTemplatesPage.verifyDragColumntoGroup("Enabled",2),"drag and drop assertion failed");
    }
    
    //@Test(groups= {"TMAC"},priority=69)
    public void VerifyChatTemplateInTMAC() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
    	TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.changeStatus("Available");
        ChatAPI chatapi=new ChatAPI(driver);
        chatapi.open("https://172.16.2.16:15012/api/testapp.jsp");
        chatapi.initiateChat();
        chatapi.sendChatMessage("Testing chat msg");
        tmacPopupPage.receivechat();
        tmacPopupPage.clickOnChatTemplate();
        Assert.assertTrue(tmacPopupPage.ChatTemplate(ChatTemplatesDetails));
        tmacPopupPage.disconnectchat();
	}
	
    
    @Test(priority=70,groups= {"OCM"})//,dependsOnMethods = "AddNewChatTemplatesRecord")
    public void EditChatTemplatesRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.editChatTemplatesRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    
    @Test(priority=71,groups= {"OCM"},dependsOnMethods="EditChatTemplatesRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyChatTemplateUpdate(chatTemplateDetails,"Update"));
    }
    
    @Test(priority=72,groups= {"OCM"})
    public void EditWithoutModifyReasonRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatePage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatePage.editChatTemplateWithoutModifyReason(chatTemplateDetails);
        Assert.assertEquals(chatTemplatePage.getMessage(),"×\nPlease enter the modify reason", "empty modify reason record assertion failed");
    }
    
    @Test(priority=73,groups= {"OCM"})
    public void VerifyCancelBtnAtEditRecord() throws Exception{
        ChatTemplatesPage chatTemplatePage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatePage.searchChatTemplatesRecord("PalakChatTemplate");
        Thread.sleep(1000);
        chatTemplatePage .clickOnEditButton();
        chatTemplatePage .clickOnCancelBtn();
        Assert.assertFalse(chatTemplatePage .verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    
    @Test(priority=74,groups= {"OCM"})//,dependsOnMethods = "AddNewChatTemplatesRecord")
    public void EditGroupRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditGroup").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.isGroupTabDisplayed());
        chatTemplatesPage.editGroupRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    
    @Test(priority=75,groups= {"OCM"})//,dependsOnMethods="EditGroupRecord")
    public void VerifyAuditTrialReportForGroupUpdate() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditGroup").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyChatTemplateGroupUpdate(chatTemplateDetails,"Update"));
    }
    
    @Test(priority=76,groups= {"OCM"})
    public void EditWithoutModifyReasonGroupRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditGroup").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatePage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatePage.navigateToTab("Groups");
        chatTemplatePage.editChatTemplateGroupWithoutModifyReason(chatTemplateDetails);
        Assert.assertEquals(chatTemplatePage.getMessage(),"×\nPlease enter the modify reason", "empty modify reason record assertion failed");
    }
    
    @Test(priority=77,groups= {"OCM"})
    public void VerifyCancelBtnAtEditGroupRecord() throws Exception{
        ChatTemplatesPage chatTemplatePage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatePage.navigateToTab("Groups");
        chatTemplatePage.searchGroupRecord("GrpPalak");
        Thread.sleep(1000);
        chatTemplatePage .clickOnEditButton();
        chatTemplatePage .clickOnCancelBtn();
        Assert.assertFalse(chatTemplatePage .verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    
    @Test(priority=78,groups= {"OCM"})
    public void VerifyUpdateGroupRecordInMain() throws Exception {
  	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"EditGroup").getTestData().get(0);
         ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
         ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
         chatTemplatesPage.clickOnAddRecord();
         Assert.assertFalse(chatTemplatesPage.VerifyGroupDropdown(chatTemplateDetails));
     }
    
    @Test(priority=80,groups= {"OCM"})
    public void EditDepartmentRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditDepartment").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.isDepartmentTabDisplayed());
        chatTemplatesPage.editDepartmentRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    
    @Test(priority=81,groups= {"OCM"})//,dependsOnMethods="EditDepartmentRecord")
    public void VerifyAuditTrialReportForDepartmentUpdate() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditDepartment").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyChatTemplateDepartmentUpdate(chatTemplateDetails,"Update"));
    }
       
    @Test(priority=82,groups= {"OCM"})
    public void EditWithoutModifyReasonDepartmentRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditDepartment").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatePage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatePage.navigateToTab("Departments");
        chatTemplatePage.editChatDepartmentWithoutModifyReason(chatTemplateDetails);
        Assert.assertEquals(chatTemplatePage.getMessage(),"×\nPlease enter the modify reason", "empty modify reason record assertion failed");
    }
    
    @Test(priority=83,groups= {"OCM"})
    public void VerifyCancelBtnAtEditDepartmentRecord() throws Exception{
        ChatTemplatesPage chatTemplatePage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatePage.navigateToTab("Departments");
        chatTemplatePage.searchDepartmentRecord("DeptPalak");
        Thread.sleep(1000);
        chatTemplatePage .clickOnEditButton();
        chatTemplatePage .clickOnCancelBtn();
        Assert.assertFalse(chatTemplatePage .verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
   
   @Test(priority=84,groups= {"OCM"})
   public void VerifyUpdateDepartmentRecordInGroup() throws IOException {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"EditDepartment").getTestData().get(0);
       ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
       ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
       chatTemplatesPage.navigateToTab("Groups");
       chatTemplatesPage.clickOnAddGroupRecord();
       Assert.assertFalse(chatTemplatesPage.VerifyDepartmentDropdown(chatTemplateDetails));
   }
   
   @Test(priority=85,groups= {"OCM"})
   public void VerifyUpdateDepartmentRecordInMain() throws IOException {
 	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditDepartment").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.clickOnAddRecord();
        Assert.assertFalse(chatTemplatesPage.VerifyDepartmentDropdown(chatTemplateDetails));
    }
       
    @Test(priority=86,groups= {"OCM"})
    public void searchDepartmentPage() throws Exception {
  	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertFalse(chatTemplatesPage.departmentclearAll(chatTemplateDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("ChatTemplatesTest", "clearall");
        Assert.assertTrue(chatTemplatesPage.verifyDepartmentclose());
    }
    
    @Test(priority=87,groups= {"OCM"})
    public void searchDepartmentwithoutSearchTextbox() throws IOException {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        chatTemplatesPage.searchwithoutextsearch();
        Assert.assertEquals(chatTemplatesPage.getMessage(),"×\nPlease enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
    
    @Test(priority=88,groups= {"OCM"})
    public void searchGroupsPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertFalse(chatTemplatesPage.GroupclearAll(chatTemplateDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("ChatTemplatesTest", "clearall");
        Assert.assertTrue(chatTemplatesPage.verifyGroupclose());
    }
    
    @Test(priority=89,groups= {"OCM"})
    public void searchGroupwithoutSearchTextbox() throws IOException {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groupss");
        chatTemplatesPage.searchwithoutextsearch();
        Assert.assertEquals(chatTemplatesPage.getMessage(),"×\nPlease enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
    
    @Test(priority=90,groups= {"OCM"})
    public void searchPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        Assert.assertFalse(chatTemplatesPage.clearAll(chatTemplateDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("ChatTemplateTest", "clearall");
        Assert.assertTrue(chatTemplatesPage.verifyclose());
    }
    
    @Test(priority=91,groups= {"OCM"})
    public void searchwithoutSearchTextbox() throws IOException {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.searchwithoutextsearch();
        Assert.assertEquals(chatTemplatesPage.getMessage(),"×\nPlease enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
   
    @Test(priority=92,groups= {"OCM"})
    public void DeleteWithoutDeleteReasonRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.deleteChatTempalateWithoutDeleteReasonRecord(chatTemplateDetails);
        Assert.assertEquals(chatTemplatesPage.getMessage(),"×\nPlease enter the delete reason","empty delete reason record assertion failed");
    }
    @Test(priority=93,groups= {"OCM"})
    public void VerifyCancelBtnAtDeleteChatTemplateRecord() throws Exception{
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.searchChatTemplatesRecord("PalakChatTemplate");
        Thread.sleep(1000);
        chatTemplatesPage.clickOnDeleteButton();
        chatTemplatesPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(chatTemplatesPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    @Test(priority=94,groups= {"OCM"})//dependsOnMethods = "EditChatTemplatesRecord")
    public void DeleteChatTemplatesRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.deleteChatTemplatesRecord(chatTemplateDetails.getName(),chatTemplateDetails.getDeleteReason());
        Assert.assertTrue(chatTemplatesPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    
    @Test(priority=95,groups= {"OCM"})//,dependsOnMethods= {"DeleteChatTemplatesRecord"})
    public void VerifyAuditTrialReportForDelete() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyChatTemplatedelete(chatTemplateDetails,"Delete"));
    }
    
    @Test(priority=96,groups= {"OCM"})
    public void DeleteGroupWithoutDeleteReasonRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        chatTemplatesPage.deleteGroupsWithoutDeleteReasonRecord(chatTemplateDetails);
        Assert.assertEquals(chatTemplatesPage.getMessage(),"×\nPlease enter the delete reason","empty delete reason record assertion failed");
    }
    @Test(priority=97,groups= {"OCM"})
    public void VerifyCancelBtnAtDeleteGroupRecord() throws Exception{
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        chatTemplatesPage.searchGroupRecord("GrpPalak");
        Thread.sleep(1000);
        chatTemplatesPage.clickOnDeleteButton();
        chatTemplatesPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(chatTemplatesPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    
    @Test(priority=98,groups= {"OCM"})//dependsOnMethods = {"EditGroupRecord","DeleteChatTemplatesRecord"})
    public void DeleteGroupRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.isGroupTabDisplayed());
        chatTemplatesPage.deleteGroupRecord(chatTemplateDetails.getGroupName(),chatTemplateDetails.getDeleteReason());
        Assert.assertTrue(chatTemplatesPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
     
    @Test(priority=99,groups= {"OCM"})//,dependsOnMethods= {"DeleteGroupRecord"})
    public void VerifyAuditTrialReportForGroupDelete() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyGroupdelete(chatTemplateDetails,"Delete"));
    }
    
    @Test(priority=100,groups= {"OCM"})
    public void VerifyDeleteGroupRecordInMain() throws Exception {
  	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
         ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
         ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
         chatTemplatesPage.clickOnAddRecord();
         Assert.assertFalse(chatTemplatesPage.VerifyGroupDropdown(chatTemplateDetails));
     }
    
   @Test(priority=101,groups= {"OCM"})
    public void DeleteDepartmentWithoutDeleteReasonRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        chatTemplatesPage.deleteDepartmentWithoutDeleteReasonRecord(chatTemplateDetails);
        Assert.assertEquals(chatTemplatesPage.getMessage(),"×\nPlease enter the delete reason","empty delete reason record assertion failed");
    }
    @Test(priority=102,groups= {"OCM"})
    public void VerifyCancelBtnAtDeleteDepartmentRecord() throws Exception{
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        chatTemplatesPage.searchDepartmentRecord("DeptPalak");
        Thread.sleep(1000);
        chatTemplatesPage.clickOnDeleteButton();
        chatTemplatesPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(chatTemplatesPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    
   @Test(priority=103,groups= {"OCM"})//dependsOnMethods = {"EditDepartmentRecord","DeleteGroupRecord","DeleteChatTemplatesRecord"})
    public void DeleteDepartmentRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.isDepartmentTabDisplayed());
        chatTemplatesPage.deleteDepartmentRecord(chatTemplateDetails.getDepartmentName(),chatTemplateDetails.getDeleteReason());
        Assert.assertTrue(chatTemplatesPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
   @Test(priority=104,groups= {"OCM"},dependsOnMethods= {"DeleteDepartmentRecord"})
   public void VerifyAuditTrialReportForDepartmentDelete() throws Exception {
   	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
       ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
       HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
       homePage.navigateToOCMReportsPage();
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
       String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
       Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map1);
       ocmReportsPage.showReport(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDepartmentdelete(chatTemplateDetails,"Delete"));
   }
   
   @Test(priority=105,groups= {"OCM"})
   public void VerifyDeleteDepartmentRecordInGroup() throws IOException {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
       ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
       ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
       chatTemplatesPage.navigateToTab("Groups");
       chatTemplatesPage.clickOnAddGroupRecord();
       Assert.assertFalse(chatTemplatesPage.VerifyDepartmentDropdown(chatTemplateDetails));
   }
   
   @Test(priority=106,groups= {"OCM"})
   public void VerifyDeleteDepartmentRecordInMain() throws IOException {
 	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.clickOnAddRecord();
        Assert.assertFalse(chatTemplatesPage.VerifyDepartmentDropdown(chatTemplateDetails));
    }
   
   @Test(priority=107,groups= {"OCM"})
   public void SearchDepartmentClearSearch() throws Exception
   {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
       ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
       ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
       chatTemplatesPage.navigateToTab("Departments");
       Assert.assertTrue(chatTemplatesPage.verifyinvalidDepartmentsearchwithwrongdata(chatTemplateDetails),"invalidsearchwithwrongdata");
       screenshot.captureScreen("ChatTemplateTest","Invalid Search with wrong data");
       Assert.assertTrue(chatTemplatesPage.verifyDepartmentclearsearch(), "Clear All Assertion Failed");
   }
   
   @Test(priority=108,groups= {"OCM"})
   public void SearchGroupClearSearch() throws Exception
   {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
       ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
       ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
       chatTemplatesPage.navigateToTab("Groups");
       Assert.assertTrue(chatTemplatesPage.verifyinvalidGroupsearchwithwrongdata(chatTemplateDetails),"invalidsearchwithwrongdata");
       screenshot.captureScreen("ChatTemplateTest","Invalid Search with wrong data");
       Assert.assertTrue(chatTemplatesPage.verifyGroupclearsearch(), "Clear All Assertion Failed");
   }
   
   @Test(priority=109,groups= {"OCM"})
   public void SearchClearSearch() throws Exception
   {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
       ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
       ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
       Assert.assertTrue(chatTemplatesPage.verifyinvalidsearchwithwrongdata(chatTemplateDetails),"invalidsearchwithwrongdata");
       screenshot.captureScreen("ChatTemplateTest","Invalid Search with wrong data");
       Assert.assertTrue(chatTemplatesPage.verifyclearsearch(), "Clear All Assertion Failed");
   }*/
   
   @Test(priority=110,groups= {"OCM"})
   public void DepartmentDatabase() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
       ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
       ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
       chatTemplatesPage.navigateToTab("Departments");
       Assert.assertTrue(chatTemplatesPage.verifyDepartmentDatabase(chatTemplateDetails.getQuery()));
   }
   
   @Test(priority=111,groups= {"OCM"})
   public void GroupDatabase() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(1);
       ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
       ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
       chatTemplatesPage.navigateToTab("Groups");
       Assert.assertTrue(chatTemplatesPage.verifyGroupDatabase(chatTemplateDetails.getQuery()));
   }
   
   @Test(priority=112,groups= {"OCM"})
   public void Database() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(2);
       ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
       ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
       Assert.assertTrue(chatTemplatesPage.verifyDatabase(chatTemplateDetails.getQuery()));
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
	        	 try{
	                 HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	                 homePage.userLogout();
	                 driver.close();
	             }catch(Exception e)
	               {
	                 driver.close();
	               e.printStackTrace();
	               }
	    }
}
