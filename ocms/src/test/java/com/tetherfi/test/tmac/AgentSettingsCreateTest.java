package com.tetherfi.test.tmac;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.pages.*;
import com.tetherfi.utility.BrowserFactory;
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
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AgentSettingsCreateTest {
    protected WebDriver driver;
    @BeforeMethod
    public void NavigateToAgentSettingsPage(Method method) throws Exception {
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
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        Test t = method.getAnnotation(Test.class);
        Map<String, String> map;
        if(t.groups()[0].equalsIgnoreCase("Checker"))
            map= new ExcelReader(filePath,"Login").getTestData().get(1);
        else
            map= new ExcelReader(filePath,"Login").getTestData().get(0);
        try{driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}catch (TimeoutException e){e.printStackTrace();driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}
        if(map.get("LoginType").equals("Custom")){
            LoginPage loginPage=PageFactory.createPageInstance(driver,LoginPage.class);
            Assert.assertTrue(loginPage.isLoginPageDisplayed(),"Login page not loaded");
            loginPage.login(map.get("Username"),map.get("Password"));
            Thread.sleep(5000);
        }
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage = PageFactory.createPageInstance(driver,TmacPage.class);
        Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"TMAC page assertion failed");
        tmacPage.navigateToAgentSettingsPage();
        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        Assert.assertTrue(agentSettingsPage.isAgentSettingsPageDisplayed(),"Agent Settings page assertion failed");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    } 
    
    @Test(groups = { "Maker" },priority=1)
    public void AddRevertNewSupervisorRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.addNewAgentSettingsRecord(agentSettingsDetails);
        Assert.assertTrue(agentSettingsPage.verifyNewRecordCreated(), "Add New record assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=2)//,dependsOnMethods="AddRevertNewSupervisorRecord")
    public void VerifyRevertForAddNewRecord() throws Exception {
    	AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
    	agentSettingsPage.selectAgentSettingsAuditTrailTab();
       	agentSettingsPage.selectRecord();
       	agentSettingsPage.Revert("revert");
        Assert.assertTrue(agentSettingsPage.verifyStatus("Reverted"),"Approval Status Details Failed");
    }

   @Test(groups = { "Maker" },priority=3,dependsOnMethods = "VerifyRevertForAddNewRecord")
    public void VerifyAuditTrailReportForRevert() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyAuditTrailReportDisplayed(agentSettingsDetails, "MakerReverted"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=4)
    public void AddRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.addNewAgentSettingsRecord(agentSettingsDetails);
        Assert.assertEquals(agentSettingsPage.getSuccessMessage(), "Record Created Successfully");
       }   
    
    @Test(groups = { "Maker" },priority=5,dependsOnMethods="AddRejectRecord")
    public void VerifySendForApprovalForAddNewAgentSettingsRecord() throws Exception {
    	AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
       	agentSettingsPage.selectAgentSettingsAuditTrailTab();
       	agentSettingsPage.selectRecord();
       	agentSettingsPage.sendForAprroval("sent");
        Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approval status details failed");
    }
    
    @Test(groups = { "Checker" },priority=6)//,dependsOnMethods="VerifySendForApprovalForAddNewAgentSettingsRecord")
    public void RejectForAddNewAgentSettingsRecord() throws Exception{
    	 AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
    	 agentSettingsPage.clickonReject("Reject Created");
         Assert.assertFalse(agentSettingsPage.verifyMessage(),"Reject record assertion failed");
         Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Rejected","Reject Created"));
     }
     
    @Test(groups = { "Checker" },priority=7,dependsOnMethods = "RejectForAddNewAgentSettingsRecord")
     public void VerifyAuditTrailReportForReject() throws Exception {
         String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
 	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
 	   AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
 	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
 	    homePage.navigateToOCMReportsPage();
 	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
 	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
 	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
 	    ReportDetails reportDetails= new ReportDetails(map1);
 	    ocmReportsPage.showReport(reportDetails);
 	    Assert.assertTrue(ocmReportsPage.verifyAuditTrailReportDisplayed(agentSettingsDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=8)
      public void AddNewSupervisorRecord() throws Exception {
         String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
         Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
         AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
         AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
         agentSettingsPage.addNewAgentSettingsRecord(agentSettingsDetails);
         Assert.assertEquals(agentSettingsPage.getSuccessMessage(), "Record Created Successfully");
     }
               
    @Test(groups = { "Maker" },priority=9,dependsOnMethods = "AddNewSupervisorRecord")
    public void VerifyAuditTrailReportForCreate() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
 	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
 	   AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
 	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
 	    homePage.navigateToOCMReportsPage();
 	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
 	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
 	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
 	    ReportDetails reportDetails= new ReportDetails(map1);
 	    ocmReportsPage.showReport(reportDetails);
 	    Assert.assertTrue(ocmReportsPage.verifyAuditTrailReportDisplayed(agentSettingsDetails, "MakerCreate"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=10)//,dependsOnMethods="AddNewSupervisorRecord")
    public void VerifyAuditTrailDataForAddNewSupervisorRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        Assert.assertTrue(agentSettingsPage.verifyAuditTrail(agentSettingsDetails, "MakerCreate", "New"), "Audit trail details failed");
    }
    
    @Test(groups = { "Maker" },priority=11,dependsOnMethods="VerifyAuditTrailDataForAddNewSupervisorRecord")
    public void VerifySendForApprovalForAddNewAgentSettingsRecord1() throws Exception {
    AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
       	 agentSettingsPage.selectAgentSettingsAuditTrailTab();
       	agentSettingsPage.selectRecord();
       	agentSettingsPage.sendForAprroval("sent");
        Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approval status details failed");
    }
    
    @Test(groups = { "Maker" },priority=12,dependsOnMethods = "VerifySendForApprovalForAddNewAgentSettingsRecord1")
    public void VerifyAuditTrailReportForSendForApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyAuditTrailReportDisplayed(agentSettingsDetails, "MakerSendToApproval"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Checker" },priority=13,dependsOnMethods="VerifyAuditTrailReportForSendForApproval")
    public void ApproveforAddNewAgentSettingRecord() throws Exception{
    	AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
    	agentSettingsPage.clickonApprove("Approve Create");
        Assert.assertTrue(agentSettingsPage.verifyMessage());
        Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    
    
    @Test(groups = { "Checker" },priority=14,dependsOnMethods = "ApproveforAddNewAgentSettingRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyAuditTrailReportDisplayed(agentSettingsDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
   
    @Test(groups = { "Maker" },priority=15)//,dependsOnMethods = "AddNewSupervisorRecord")
    public void VerifyDuplicateLanIDCreation() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.addNewAgentSettingsRecord(agentSettingsDetails);
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"Duplicate Lan ID", "Add Duplicate record assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=16)//,dependsOnMethods = "AddNewSupervisorRecord")
    public void VerifyDuplicateAvayaLoginIDCreation() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(3);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.addNewAgentSettingsRecord(agentSettingsDetails);
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"Duplicate Avaya Login ID", "Add Duplicate record assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=17)//,dependsOnMethods = "ApproveforAddNewSupervisorRecord")
    public void VerifyAddAgentsDetailsInDatabase() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        Assert.assertTrue(agentSettingsPage.verifyDatabaseDetails(agentSettingsDetails),"database insertion failed");
    }
    
    @Test(groups = { "Maker" },priority=18)//,dependsOnMethods = {"ApproveforAddNewAgentSettingRecord"})
    public void AddNewAgentSettingsRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.addNewAgentSettingsRecord(agentSettingsDetails);
        Assert.assertEquals(agentSettingsPage.getSuccessMessage(), "Record Created Successfully");
    }   
    
    @Test(groups = { "Maker" },priority=19,dependsOnMethods="AddNewAgentSettingsRecord")
    public void VerifySendForApprovalRecord() throws Exception {
    	AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
       	agentSettingsPage.selectAgentSettingsAuditTrailTab();
       	agentSettingsPage.selectRecord();
       	agentSettingsPage.sendForAprroval("sent");
        Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approval status details failed");
    }
    
    @Test(groups = { "Checker" },priority=20)
    public void ApproveforAddNewAgentRecord() throws Exception{
        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.clickonApprove("Approve Create");
        Assert.assertTrue(agentSettingsPage.verifyMessage());
        Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
      
   @AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen( "AgentSettingsCreateTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}
