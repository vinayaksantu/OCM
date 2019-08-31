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

public class AgentSettingsEditTest {
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
    @Test(groups = { "Maker" },dependsOnMethods = "com.tetherfi.test.tmac.AgentSettingsTmacTest.VerifyLogintoTmac")
    public void EditSupervisorRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.editAgentSettingsRecord(agentSettingsDetails);
        Assert.assertTrue(agentSettingsPage.verifyRecordUpdated(), "edit record assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = "EditSupervisorRecord")
    public void VerifyAuditTrailReportForEdit() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Report").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyAuditTrailReportDisplayed("MakerUpdate","AgentSetting"),"Audit Trail report assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods ="EditSupervisorRecord")
    public void VerifyAuditTrailDataForEditSupervisorRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        Assert.assertTrue(agentSettingsPage.verifyAuditTrail(agentSettingsDetails, "MakerUpdate", "New"), "Audit trail details failed");
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteEnabled(), "Task complete button not enabled");
    }
    @Test(groups = { "Maker" },dependsOnMethods="VerifyAuditTrailDataForEditSupervisorRecord")
    public void VerifyTaskCompleteActionForEditSupervisorRecord() throws Exception {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.taskCompleteAction("Task Complete for Edit");
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForEditSupervisorRecord")
    public void ApproveforEditSupervisorRecord() throws Exception{
        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.clickonApprove("Approve Edit");
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Approved","Approve Edit"));
    }
    @Test(groups = { "Maker" },dependsOnMethods = "ApproveforEditSupervisorRecord")
    public void EditAgentSettingsRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.editAgentSettingsRecord(agentSettingsDetails);
        Assert.assertTrue(agentSettingsPage.verifyRecordUpdated(), "edit record assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = "EditAgentSettingsRecord")
    public void VerifyProfileSelectionAgentAtTeamLevel() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.searchAgentSettingsRecord(agentSettingsDetails.getUsername());
        agentSettingsPage.clickonTopmostEditButton();
        Assert.assertTrue(agentSettingsPage.verifyProfileSelection(), "profile selection assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = "EditAgentSettingsRecord")
    public void VerifyProfileSelectionAgentAtCountryDivisionDepartmentLevel() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.searchAgentSettingsRecord(agentSettingsDetails.getUsername());
        agentSettingsPage.clickonTopmostEditButton();
        Assert.assertFalse(agentSettingsPage.verifyProfileSelectionAtCountryDivisionDepartmentLevel(agentSettingsDetails.getTeamName()), "agent profile selection at country, division, department assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = "EditAgentSettingsRecord")
    public void VerifyAuditTrailDataForEditAgentSettingsRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        Assert.assertTrue(agentSettingsPage.verifyAuditTrail(agentSettingsDetails, "MakerUpdate", "New"), "Audit trail details failed");
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteEnabled(), "Task complete button not enabled");
    }
    @Test(groups = { "Maker" },dependsOnMethods="VerifyAuditTrailDataForEditAgentSettingsRecord")
    public void VerifyTaskCompleteActionForEditAgentSettingsRecord() throws Exception {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.taskCompleteAction("Task Complete for Edit");
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForEditAgentSettingsRecord")
    public void RejectforEditAgentRecord() throws Exception{
        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.clickonReject("Reject Edit");
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"All the data has been rejected!","rejected record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Rejected","Reject Edit"));
    }
    @Test(groups = { "Maker" },dependsOnMethods = {"RejectforEditAgentRecord"})
    public void DeleteSupervisorRecordWhenAgentAssigned() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.deleteSupervisorRecordWhenAssignedToAgent(agentSettingsDetails.getUsername());
        Assert.assertTrue(agentSettingsPage.verifyRetagSupervisorPopupDisplayed(), "delete supervisor when agent assigned assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen( "AgentSettingsEditTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}
