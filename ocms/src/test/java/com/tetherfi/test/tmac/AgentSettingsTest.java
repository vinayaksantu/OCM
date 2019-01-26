package com.tetherfi.test.tmac;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.pages.*;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class AgentSettingsTest {
    protected WebDriver driver;
    Screenshot screenshot=new Screenshot();
    @BeforeMethod
    public void NavigateToAgentSettingsPage(Method method) throws IOException, InterruptedException {
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
        driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
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
    @Test(groups = { "Maker" })
    public void VerifyAgentSettingsModuleDisplay() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        Assert.assertEquals(agentSettingsPage.getHeaderText(), "Agent Settings", "Agent settings Module text assertion failed");
        Assert.assertTrue(agentSettingsPage.verifySearchLink(), "search link assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyClearSearchLink(), "clear search link assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyFullScreenLink(), "full screen link assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyAgentSettingsTabsDisplayed(), "Agent Settings tab assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyAgentSettingsApprovedDataPage() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        Assert.assertTrue(agentSettingsPage.verifyApprovedDataTableHeaders(),"Approved table assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyAgentSettingsAuditTrailDataPage() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        Assert.assertTrue(agentSettingsPage.verifyAuditTrailDataTableHeaders(),"Audit Trail table assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyMakeAgentSettingsChangeButton() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyAddNewAgentSettingsRecordButton(), "add new Agent Settings record button assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyGoBackButton(), "Go back button assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyExportToExcelButton(), "export to excel button assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyMakerDataTableHeaders(), "maker table headers assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyAddNewAgentSettingsButton() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.selectAddNewAgentSettings();
        Assert.assertTrue(agentSettingsPage.verifyAddNewPopupContents(),"Add new pop up content assertion failed");
    }
    @Test(groups = { "Maker" })
    public void AddNewSupervisorRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.addNewAgentSettingsRecord(agentSettingsDetails);
        Assert.assertTrue(agentSettingsPage.verifyNewRecordCreated(), "Add New record assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = "AddNewSupervisorRecord")
    public void VerifyAuditTrailReportForCreate() throws IOException {
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
    public void VerifyDuplicateRecordCreation() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.addNewAgentSettingsRecord(agentSettingsDetails);
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"×\nDuplicate UserName", "Add Duplicate record assertion failed");
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
    public void VerifyTaskCompleteActionForAddNewSupervisorRecord() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.taskCompleteAction("Task Complete for Create");
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForAddNewSupervisorRecord")
    public void ApproveforAddNewSupervisorRecord(){
        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.clickonApprove("Approve Create");
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    @Test(groups = { "Maker" },dependsOnMethods = "ApproveforAddNewSupervisorRecord")
    public void VerifyLogintoTmac() throws IOException {
    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
    Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
    AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

    ((JavascriptExecutor)driver).executeScript("window.open()");
    TmacLoginPage tmacloginPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
    tmacloginPage.switchToWindow(1);
    driver.get("http://172.16.2.16/TMAC_UI/");
    Assert.assertTrue(tmacloginPage.checkPageLoadStatus(), "login page successful status");
    tmacloginPage.loginIntoTmac(agentSettingsDetails.getUsername(),"40022");
    Assert.assertTrue(tmacloginPage.verifyUserLogged(),"Tmac login failed");
    TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
    tmacPopupPage.switchToWindow(2);
    Assert.assertTrue(tmacPopupPage.isTmacPopUpDisplayed(),"TMAC popup not displayed");
    tmacPopupPage.userLogout();
    tmacloginPage.switchToWindow(1);
    driver.close();
    tmacloginPage.switchToWindow(0);
    }
    @Test(groups = { "Maker" },dependsOnMethods = {"ApproveforAddNewSupervisorRecord"})
    public void EditSupervisorRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.editAgentSettingsRecord(agentSettingsDetails);
        Assert.assertTrue(agentSettingsPage.verifyRecordUpdated(), "edit record assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = "EditSupervisorRecord")
    public void VerifyAuditTrailReportForEdit() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Report").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyAuditTrailReportDisplayed("MakerUpdate","AgentSetting"),"Audit Trail report assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = {"EditSupervisorRecord"})
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
    public void VerifyTaskCompleteActionForEditSupervisorRecord() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.taskCompleteAction("Task Complete for Edit");
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForEditSupervisorRecord")
    public void ApproveforEditSupervisorRecord(){
        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.clickonApprove("Approve Edit");
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Approved","Approve Edit"));
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
    public void VerifyAuditTrailReportForDelete() throws IOException {
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
    public void VerifyTaskCompleteActionForDeleteSupervisorRecord() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.taskCompleteAction("Task Complete for Delete");
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForDeleteSupervisorRecord")
    public void ApproveforDeleteSupervisorRecord(){
        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.clickonApprove("Approve Delete");
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Approved","Approve Delete"));
    }
    @Test(groups = { "Maker" },dependsOnMethods = {"ApproveforEditSupervisorRecord"})
    public void AddNewAgentSettingsRecord() throws IOException {
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
    public void VerifyTaskCompleteActionForAddNewAgentSettingsRecord() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.taskCompleteAction("Task Complete for Create");
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForAddNewAgentSettingsRecord")
    public void ApproveforAddNewAgentRecord(){
        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.clickonApprove("Approve Create");
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    @Test(groups = { "Maker" },dependsOnMethods = {"ApproveforAddNewAgentRecord"})
    public void EditAgentSettingsRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.editAgentSettingsRecord(agentSettingsDetails);
        Assert.assertTrue(agentSettingsPage.verifyRecordUpdated(), "edit record assertion failed");
    }
    @Test(groups = { "Maker" },dependsOnMethods = {"EditAgentSettingsRecord"})
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
    public void VerifyTaskCompleteActionForEditAgentSettingsRecord() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.taskCompleteAction("Task Complete for Edit");
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForEditAgentSettingsRecord")
    public void ApproveforEditAgentRecord(){
        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.clickonApprove("Approve Edit");
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Approved","Approve Edit"));
    }
    @Test(groups = { "Maker" },dependsOnMethods = {"ApproveforEditAgentRecord"})
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
    public void VerifyTaskCompleteActionForDeleteAgentSettingsRecord() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.taskCompleteAction("Task Complete for Delete");
        Assert.assertTrue(agentSettingsPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForDeleteAgentSettingsRecord")
    public void ApproveforDeleteAgentRecord(){
        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.clickonApprove("Approve Delete");
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Approved","Approve Delete"));
    }
    @Test(groups = { "Maker" })
    public void VerifySearchRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.searchAgentSettingsRecord(agentSettingsDetails.getUsername());
        Assert.assertTrue(agentSettingsPage.verifySearcedContentsDisplayed(agentSettingsDetails.getUsername()),"Search assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyClearAllAtSearchRecord() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyClearAllFunctionality(),"clear all at Search assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyClearSearchButton() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyClearSearchFunctionality(agentSettingsDetails),"clear Search assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyClearSearchNegative() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyClearSearchNegative(),"clear Search without searching assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyFullScreenButton() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyPageFullScrnd(),"full screen assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyExportToExcel() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyExportToExcel(),"export to excel assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyExportToExcelWithoutData() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.searchAgentSettingsRecord("tmac");
        agentSettingsPage.clickonExportToExcelBtn();
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"×\nThere is no record to export","export to excel assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyDragAndDrop() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.dragColumntoGroup("Lan ID");
        Assert.assertTrue(agentSettingsPage.verifyDragColumntoGroup("Lan ID"),"drag and drop assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyDragAndDropofAlreadyGroupedHeader() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.dragColumntoGroup("Lan ID");
        agentSettingsPage.dragColumntoGroup("Lan ID");
        Assert.assertTrue(agentSettingsPage.verifyDragColumntoGroup("Lan ID"),"drag and drop assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyDropdownForAllTheColumns() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyColumnsHeaderEnable() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyColumnsHeaderDisable() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertFalse(agentSettingsPage.verifycolumnsHeaderDisbaled(),"columns disabled assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyArrowMoveForPreviousAndNextPage() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyArrowMoveForPreviousAndNextPage(2),"arrow move for previous and next page assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyArrowMoveForFirstAndLastPage() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyArrowMoveForFirstAndLastPage(2),"arrow move for first and last page assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyTotalNumberOfItemsPerPageDetails(2),"item per page assertion failed");
    }
    @Test(groups = { "Maker" })
    public void VerifyNumberOfItemsPerPageSelection() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyNumberOfItemsPerPage(2),"item per page assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method){
    	screenshot.captureScreen(driver, "", method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}
