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

public class AgentSettingsDeleteTest {
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
            loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
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
    @Test(groups = { "Maker" },dependsOnMethods = "com.tetherfi.test.tmac.AgentSettingsTmac1Test.VerifyAutoInDropdownSelected")
    public void DeleteAgentSettingsRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(1);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.deleteAgentSettingsRecord(agentSettingsDetails.getUsername(), agentSettingsDetails.getDeleteReason());
        Assert.assertTrue(agentSettingsPage.verifyRecordDeleted(), "delete record assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = {"DeleteAgentSettingsRecord"})
    public void VerifyAuditTrailDataForDeleteAgentSettingsRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        Assert.assertTrue(agentSettingsPage.verifyAuditTrail(agentSettingsDetails, "MakerDelete", "New"), "Audit trail details failed");
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteEnabled(), "Task complete button not enabled");
    }
    @Test(groups = { "Maker" },dependsOnMethods="VerifyAuditTrailDataForDeleteAgentSettingsRecord")
    public void VerifyTaskCompleteActionForDeleteAgentSettingsRecord() throws Exception {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.taskCompleteAction("Task Complete for Delete");
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForDeleteAgentSettingsRecord")
    public void ApproveforDeleteAgentRecord() throws Exception{
        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.clickonApprove("Approve Delete");
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Approved","Approve Delete"));
    }
    @Test(groups = { "Maker" },dependsOnMethods = {"ApproveforDeleteAgentRecord"})
    public void DeleteSupervisorRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.deleteAgentSettingsRecord(agentSettingsDetails.getUsername(), agentSettingsDetails.getDeleteReason());
        Assert.assertTrue(agentSettingsPage.verifyRecordDeleted(), "delete record assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = "DeleteSupervisorRecord")
    public void VerifyAuditTrailReportForDelete() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Report").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyAuditTrailReportDisplayed("MakerDelete","AgentSetting"),"Audit Trail report assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = {"DeleteSupervisorRecord"})
    public void VerifyAuditTrailDataForDeleteSupervisorRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        Assert.assertTrue(agentSettingsPage.verifyAuditTrail(agentSettingsDetails, "MakerDelete", "New"), "Audit trail details failed");
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteEnabled(), "Task complete button not enabled");
    }
    @Test(groups = { "Maker" },dependsOnMethods="VerifyAuditTrailDataForDeleteSupervisorRecord")
    public void VerifyTaskCompleteActionForDeleteSupervisorRecord() throws Exception {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.taskCompleteAction("Task Complete for Delete");
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForDeleteSupervisorRecord")
    public void ApproveforDeleteSupervisorRecord() throws Exception{
        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.clickonApprove("Approve Delete");
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Approved","Approve Delete"));
    }
    @AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen( "AgentSettingsDeleteTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}
