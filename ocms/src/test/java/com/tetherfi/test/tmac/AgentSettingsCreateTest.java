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
    }
    @Test(groups = { "Maker" })
    public void AddNewSupervisorRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.addNewAgentSettingsRecord(agentSettingsDetails);
        Assert.assertTrue(agentSettingsPage.verifyNewRecordCreated(), "Add New record assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = "AddNewSupervisorRecord")
    public void VerifySearchRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.searchAgentSettingsRecord(agentSettingsDetails.getUsername());
        Assert.assertTrue(agentSettingsPage.verifySearcedContentsDisplayed(agentSettingsDetails.getUsername()),"Search assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = "AddNewSupervisorRecord")
    public void VerifyClearSearchButton() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyClearSearchFunctionality(agentSettingsDetails),"clear Search assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = "AddNewSupervisorRecord")
    public void VerifyAuditTrailReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Report").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyAuditTrailReportDisplayed("MakerCreate","AgentSetting"),"Audit Trail report assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = "AddNewSupervisorRecord")
    public void VerifyDuplicateLanIDCreation() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.addNewAgentSettingsRecord(agentSettingsDetails);
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"×\nDuplicate Lan ID", "Add Duplicate record assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = "AddNewSupervisorRecord")
    public void VerifyDuplicateAvayaLoginIDCreation() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(3);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.addNewAgentSettingsRecord(agentSettingsDetails);
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"×\nDuplicate Avaya Login ID", "Add Duplicate record assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods="AddNewSupervisorRecord")
    public void VerifyAuditTrailDataForAddNewSupervisorRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        Assert.assertTrue(agentSettingsPage.verifyAuditTrail(agentSettingsDetails, "MakerCreate", "New"), "Audit trail details failed");
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteEnabled(), "Task complete button not enabled");
    }
    @Test(groups = { "Maker" },dependsOnMethods="VerifyAuditTrailDataForAddNewSupervisorRecord")
    public void VerifyTaskCompleteActionForAddNewSupervisorRecord() throws Exception {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.taskCompleteAction("Task Complete for Create");
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForAddNewSupervisorRecord")
    public void ApproveforAddNewSupervisorRecord() throws Exception{
        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.clickonApprove("Approve Create");
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    @Test(groups = { "Maker" },dependsOnMethods = "ApproveforAddNewSupervisorRecord")
    public void VerifyAddAgentsDetailsInDatabase() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        Assert.assertTrue(agentSettingsPage.verifyDatabaseDetails(agentSettingsDetails),"database insertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = {"ApproveforAddNewSupervisorRecord"})
    public void VerifySupervisorDisplayedForAgent() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.selectAddNewAgentSettings();
        Assert.assertTrue(agentSettingsPage.verifySupervisorDisplayed(agentSettingsDetails.getTeamName(),agentSettingsDetails.getSupervisor()),"supervisor displayed assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = {"ApproveforAddNewSupervisorRecord"})
    public void AddNewAgentSettingsRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.addNewAgentSettingsRecord(agentSettingsDetails);
        Assert.assertTrue(agentSettingsPage.verifyNewRecordCreated(), "Add New record assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = {"AddNewAgentSettingsRecord"})
    public void VerifyAuditTrailDataForAddNewAgentSettingsRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        Assert.assertTrue(agentSettingsPage.verifyAuditTrail(agentSettingsDetails, "MakerCreate", "New"), "Audit trail details failed");
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteEnabled(), "Task complete button not enabled");
    }
    @Test(groups = { "Maker" },dependsOnMethods="VerifyAuditTrailDataForAddNewAgentSettingsRecord")
    public void VerifyTaskCompleteActionForAddNewAgentSettingsRecord() throws Exception {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.taskCompleteAction("Task Complete for Create");
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForAddNewAgentSettingsRecord")
    public void ApproveforAddNewAgentRecord() throws Exception{
        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.clickonApprove("Approve Create");
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"All the data has been approved successfully!","approve record assertion failed");
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
