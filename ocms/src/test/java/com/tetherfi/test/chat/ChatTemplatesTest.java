package com.tetherfi.test.chat;

import com.tetherfi.model.chat.ChatTemplateDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.TmacUserDetails;
import com.tetherfi.pages.*;
import com.tetherfi.test.BaseTest;
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
	        loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
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
      }
    
    /*@Test(priority=1)
	public void ChatTemplatePage() {
        ChatTemplatesPage ChatTemplatePage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(ChatTemplatePage.VerifyLogo(),"Logo assertion failed");
        Assert.assertTrue(ChatTemplatePage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("ChatTemplateTest","Maximize");
    	Assert.assertTrue(ChatTemplatePage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("ChatTemplateTest","Minimize");	
    }
	
    @Test(priority=2)
    public void VerifyDepartmentsDropdownForAllTheColumns() throws Exception {
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Departments");
        Thread.sleep(1000);
        Assert.assertTrue(ChatTemplatesPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyDepartmentsColumnsHeaderEnable() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Thread.sleep(1000);
        Assert.assertTrue(chatTemplatesPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyDepartmentsColumnsHeaderDisable() throws Exception {
    	 ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
         chatTemplatesPage.navigateToTab("Departments");
         Thread.sleep(1000);
         Assert.assertFalse(chatTemplatesPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=5)
    public void VerifyGroupsDropdownForAllTheColumns() throws Exception {
    	ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Thread.sleep(2000);
        Assert.assertTrue(chatTemplatesPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=6)
    public void VerifyGroupsColumnsHeaderEnable() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Thread.sleep(2000);
        Assert.assertTrue(chatTemplatesPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=7)
    public void VerifyGroupsColumnsHeaderDisable() throws Exception {
    	 ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
    	 chatTemplatesPage.navigateToTab("Groups");
         Thread.sleep(2000);
         Assert.assertFalse(chatTemplatesPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=8)
    public void VerifyMainsDropdownForAllTheColumns() throws Exception {
    	ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=9)
    public void VerifyMainsColumnsHeaderEnable() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=10)
    public void VerifyMainsColumnsHeaderDisable() throws Exception {
    	 ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
         Assert.assertFalse(chatTemplatesPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=11)
    public void AddNewDepartmentRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.isDepartmentTabDisplayed());
        chatTemplatesPage.addNewDepartmentRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    
    @Test(priority=12,dependsOnMethods = "AddNewDepartmentRecord")
    public void AddDuplicateDepartmentRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Departments");
        ChatTemplatesPage.addNewDepartmentRecord(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nDuplicate Name");
    }
    
    @Test(priority=13)//,dependsOnMethods ="AddNewDepartmentRecord")
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
    
    @Test(priority=14)
    public void AddEmptyDepartmentRecord() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Departments");
        ChatTemplatesPage.addNewEmptyRecord(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Name, Enabled", "Add invalid record assertion failed");
    }
    
    @Test(priority=15)
    public void AddRecordWithoutDepartment() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Departments");
        ChatTemplatesPage.addRecordWithoutDepartment(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Name", "Add invalid record assertion failed");
    }
    
    @Test(priority=16)
    public void AddRecordWithoutEnabled() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Departments");
        ChatTemplatesPage.addRecordWithoutEnabled(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Enabled", "Add invalid record assertion failed");
    }
    
    @Test(priority=17)
    public void VerifyCancelBtnAtAddDepartmentRecord(){
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Departments");
        ChatTemplatesPage.clickOnAddDepartmentRecord();
        ChatTemplatesPage.clickOnCancelBtn();
        Assert.assertFalse(ChatTemplatesPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
	}
    
    @Test(priority=18)//,dependsOnMethods = "AddNewDepartmentRecord")
    public void AddNewGroupsRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.isGroupTabDisplayed());
        chatTemplatesPage.addNewGroupRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    
    @Test(priority=19,dependsOnMethods = "AddNewGroupsRecord")
    public void AddDuplicateGroupsRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Groups");
        ChatTemplatesPage.addNewGroupRecord(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nDuplicate Name");
    }
    
    @Test(priority=20,dependsOnMethods ="AddNewGroupsRecord")
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
    
    @Test(priority=21)
    public void AddEmptyGroupRecord() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Groups");
        ChatTemplatesPage.addNewEmptyGroupRecord(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Department Name, Name, Enabled", "Add invalid record assertion failed");
    }
    
    @Test(priority=22)
    public void AddRecordWithoutDepartmentName() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Groups");
        ChatTemplatesPage.addRecordWithoutDepartmentName(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Department Name", "Add invalid record assertion failed");
    }
    
    @Test(priority=23)
    public void AddRecordWithoutName() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Groups");
        ChatTemplatesPage.addRecordWithoutName(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Name", "Add invalid record assertion failed");
    }
    @Test(priority=24)
    public void AddRecordWithoutGroupEnabled() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Groups");
        ChatTemplatesPage.addRecordWithoutGroupEnabled(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Enabled", "Add invalid record assertion failed");
    }
    
    @Test(priority=25)
    public void VerifyCancelBtnAtAddGroupsRecord(){
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.navigateToTab("Groups");
        ChatTemplatesPage.clickOnAddGroupRecord();
        ChatTemplatesPage.clickOnCancelBtn();
        Assert.assertFalse(ChatTemplatesPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
	}
    
    @Test(priority=26,dependsOnMethods = "AddNewGroupsRecord")
    public void AddNewChatTemplatesRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.addNewChatTemplatesRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    
    @Test(priority=27,dependsOnMethods = "AddNewChatTemplatesRecord")
    public void AddDuplicateChatTemplatesRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addNewChatTemplatesRecord(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nDuplicate Name");
    }
    
    @Test(priority=28)//,dependsOnMethods ="AddNewChatTemplatesRecord")
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
    
    @Test(priority=29)
    public void AddEmptyChatTemplateRecord() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addNewEmptyChatTemplateRecord(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Department Name, Group Name, Name, Enabled, Text, Start Time, End Time", "Add invalid record assertion failed");
    }
    
    @Test(priority=30)
    public void AddRecordWithoutChatDepartmentName() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addRecordWithoutChatDepartmentName(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Department Name, Group Name", "Add invalid record assertion failed");
    }
    
    @Test(priority=31)
    public void AddRecordWithoutGroupsName() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addRecordWithoutGroupName(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Group Name", "Add invalid record assertion failed");
    }
    
    @Test(priority=32)
    public void AddRecordWithoutChatName() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addRecordWithoutChatName(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Name", "Add invalid record assertion failed");
    }
    @Test(priority=33)
    public void AddRecordWithoutText() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addRecordWithoutText(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Text", "Add invalid record assertion failed");
    }
    
    @Test(priority=34)
    public void AddRecordWithoutStartTime() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addRecordWithoutStartTime(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Start Time", "Add invalid record assertion failed");
    }
    
    @Test(priority=35)
    public void AddRecordWithoutEndTime() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addRecordWithoutEndTime(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide End Time", "Add invalid record assertion failed");
    }
    
    @Test(priority=36)
    public void AddRecordWithoutChatEnabled() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails ChatTemplatesDetails = new ChatTemplateDetails(map);
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.addRecordWithoutChatEnabled(ChatTemplatesDetails);
        Assert.assertEquals(ChatTemplatesPage.getMessage(),"×\nPlease Provide Enabled", "Add invalid record assertion failed");
    }
    
    @Test(priority=37)
    public void VerifyCancelBtnAtAddChatTemplateRecord(){
        ChatTemplatesPage ChatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        ChatTemplatesPage.clickOnAddRecord();
        ChatTemplatesPage.clickOnCancelBtn();
        Assert.assertFalse(ChatTemplatesPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
	}
    
    @Test(priority=38)
    public void DepartmentExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.verifyDepartmentExportToExcel(filePath));
    }
    
    @Test(priority=39)
    public void DepartmentExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\ChatTemplatesDepartment.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelDepartmentSheet(maplist));	
    } 
    
    @Test(priority=40)
    public void DepatmentSortingByAscending() throws IOException {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        chatTemplatesPage.SortByDepartmentAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\ChatTemplatesDepartment (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelDepartmentSheet(maplist));
    }
    
    @Test(priority=41)
    public void DepartmentSortingByDescending() throws IOException {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        chatTemplatesPage.SortByDepartmentDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\ChatTemplatesDepartment (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelDepartmentSheet(maplist));
    }
    
    @Test(priority=42)
    public void GroupsExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.verifyGroupsExportToExcel(filePath));
    }
    
    @Test(priority=43)
    public void GroupsExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\ChatTemplateGroup.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelGroupsSheet(maplist));	
    } 
    
    @Test(priority=44)
    public void GroupsSortingByAscending() throws IOException {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        chatTemplatesPage.SortByGroupAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\ChatTemplateGroups (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelGroupsSheet(maplist));
    }
    
    @Test(priority=45)
    public void GroupsSortingByDescending() throws IOException {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        chatTemplatesPage.SortByGroupDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\ChatTemplateGroups (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelGroupsSheet(maplist));
    }
    
    @Test(priority=46)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=47)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Chat Templates.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelSheet(maplist));	
    } 
    
    @Test(priority=48)
    public void SortingByAscending() throws IOException {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Chat Templates (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=49)
    public void SortingByDescending() throws IOException {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Chat Templates (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(chatTemplatesPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=50)
    public void VerifyDepartmentArrowMoveForPreviousAndNextPage() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.verifyArrowMoveForPreviousAndNextPage(0),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=51)
    public void VerifyDepartmentArrowMoveForFirstAndLastPage() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.verifyArrowMoveForFirstAndLastPage(0),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=52)
    public void VerifyDepartmentTotalNumberOfItemsPerPageDetails() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.verifyTotalNumberOfItemsPerPageDetails(0),"item per page assertion failed");
    }
    
    @Test(priority=53)
    public void VerifyDepartmentNumberOfItemsPerPageSelection() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.verifyNumberOfItemsPerPage(0),"item per page assertion failed");
    }
    
    @Test(priority=54)
    public void VerifyGroupsArrowMoveForPreviousAndNextPage() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.verifyArrowMoveForPreviousAndNextPage(1),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=55)
    public void VerifyGroupsArrowMoveForFirstAndLastPage() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.verifyArrowMoveForFirstAndLastPage(1),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=56)
    public void VerifyGroupsTotalNumberOfItemsPerPageDetails() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.verifyTotalNumberOfItemsPerPageDetails(1),"item per page assertion failed");
    }
    
    @Test(priority=57)
    public void VerifyGroupsNumberOfItemsPerPageSelection() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.verifyNumberOfItemsPerPage(1),"item per page assertion failed");
    }
    
    @Test(priority=58)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.verifyArrowMoveForPreviousAndNextPage(2),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=59)
    public void VerifyArrowMoveForFirstAndLastPage() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.verifyArrowMoveForFirstAndLastPage(2),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=60)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.verifyTotalNumberOfItemsPerPageDetails(2),"item per page assertion failed");
    }
    
    @Test(priority=61)
    public void VerifyNumberOfItemsPerPageSelection() {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.verifyNumberOfItemsPerPage(2),"item per page assertion failed");
    }
    
    @Test(priority=62)
    public void VerifyDepartmentDragAndDrop() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Thread.sleep(2000);
        chatTemplatesPage.dragDepartmentColumntoGroup("Enabled");
        Assert.assertTrue(chatTemplatesPage.verifyDragColumntoGroup("Enabled", 0),"drag and drop assertion failed");
    }
    @Test(priority=63)
    public void VerifyDepartmentDragAndDropofAlreadyGroupedHeader() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Thread.sleep(2000);
        chatTemplatesPage.dragDepartmentColumntoGroup("Enabled");
        chatTemplatesPage.dragDepartmentColumntoGroup("Enabled");
        Assert.assertTrue(chatTemplatesPage.verifyDragColumntoGroup("Enabled",0),"drag and drop assertion failed");
    }
    
   @Test(priority=64)
    public void VerifyGroupsDragAndDrop() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Thread.sleep(2000);
        chatTemplatesPage.dragGroupsColumntoGroup("Enabled");
        Assert.assertTrue(chatTemplatesPage.verifyDragColumntoGroup("Enabled",1),"drag and drop assertion failed");
    }
    @Test(priority=65)
    public void VerifyGroupsDragAndDropofAlreadyGroupedHeader() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Thread.sleep(2000);
        chatTemplatesPage.dragGroupsColumntoGroup("Enabled");
        chatTemplatesPage.dragGroupsColumntoGroup("Enabled");
        Assert.assertTrue(chatTemplatesPage.verifyDragColumntoGroup("Enabled",1),"drag and drop assertion failed");
    }
    
    @Test(priority=66)
    public void VerifyDragAndDrop() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Thread.sleep(2000);
        chatTemplatesPage.dragColumntoGroup("Enabled");
        Assert.assertTrue(chatTemplatesPage.verifyDragColumntoGroup("Enabled",2),"drag and drop assertion failed");
    }
    @Test(priority=67)
    public void VerifyDragAndDropofAlreadyGroupedHeader() throws Exception {
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver, ChatTemplatesPage.class);
        Thread.sleep(2000);
        chatTemplatesPage.dragColumntoGroup("Enabled");
        chatTemplatesPage.dragColumntoGroup("Enabled");
        Assert.assertTrue(chatTemplatesPage.verifyDragColumntoGroup("Enabled",2),"drag and drop assertion failed");
    }
    
    @Test(groups= {"TMAC"},priority=68)
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

    }*/
    
    @Test(priority=38)
    public void EditDepartmentRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditDepartment").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.isDepartmentTabDisplayed());
        chatTemplatesPage.editDepartmentRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
   
   @Test(priority=39)
   public void VerifyUpdateDepartmentRecordInGroup() throws IOException {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"EditDepartment").getTestData().get(0);
       ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
       ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
       chatTemplatesPage.navigateToTab("Groups");
       chatTemplatesPage.clickOnAddGroupRecord();
       Assert.assertTrue(chatTemplatesPage.VerifyDepartmentDropdown());
   }

    /*@Test(dependsOnMethods = {"EditDepartmentRecord","DeleteGroupRecord","DeleteChatTemplatesRecord"})
    public void DeleteDepartmentRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"DeleteDepartment").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.isDepartmentTabDisplayed());
        chatTemplatesPage.deleteDepartmentRecord(chatTemplateDetails.getDepartmentName(),chatTemplateDetails.getDeleteReason());
        Assert.assertTrue(chatTemplatesPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    
    @Test(dependsOnMethods = {"AddNewGroupsRecord","DeleteChatTemplatesRecord"})
    public void EditGroupRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditGroup").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);

        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.isGroupTabDisplayed());
        chatTemplatesPage.editGroupRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    @Test(dependsOnMethods = {"EditGroupRecord","DeleteChatTemplatesRecord"})
    public void DeleteGroupRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"DeleteGroup").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);

        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.isGroupTabDisplayed());
        chatTemplatesPage.deleteGroupRecord(chatTemplateDetails.getGroupName(),chatTemplateDetails.getDeleteReason());
        Assert.assertTrue(chatTemplatesPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    
    @Test(dependsOnMethods = "AddNewChatTemplatesRecord")
    public void EditChatTemplatesRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);

        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.editChatTemplatesRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    @Test(dependsOnMethods = "EditChatTemplatesRecord")
    public void DeleteChatTemplatesRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);

        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.deleteChatTemplatesRecord(chatTemplateDetails.getName(),chatTemplateDetails.getDeleteReason());
        Assert.assertTrue(chatTemplatesPage.verifyRecordDeleted(),"Delete record assertion failed");
    }*/
   
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
