package com.tetherfi.test.fax;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.fax.FaxTemplateDetails;
import com.tetherfi.model.fax.SendFaxDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.pages.AgentSettingsNewDesignPage;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.FaxTemplatePage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class FaxTemplateCreateTest {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateToFaxTemplatePage(Method method) throws IOException, InterruptedException {
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
            map= new ExcelReader(filePath,"Login").getTestData().get(2);
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
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("FAX");
        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
        Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
        faxPage.navigateToFaxTemplatePage();
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        Assert.assertTrue(faxTemplatePage.isFaxTemplatePageDisplayed(), "FAX page assertion failed");
    }
	
	@Test()
    public void VerifyAddCancelButton() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        Assert.assertTrue(faxTemplatePage.addCancelButton(faxTemplateDetails), "Add cancel button assertion failed");
    }
	
	@Test(groups = { "Maker" })
    public void AddNewFaxTemplateRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.addNewFaxTemplateRecord(faxTemplateDetails);
        Assert.assertEquals(faxTemplatePage.getSuccessMessage(), "Record Created Successfully");
    }
	
	@Test(groups = { "Maker" },dependsOnMethods = "AddNewFaxTemplateRecord")
    public void VerifyAuditTrailReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxTemplateCreate(faxTemplateDetails, "MakerCreate"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="AddNewFaxTemplateRecord")
    public void VerifyAuditTrailDataForAddNewFaxTemplateRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        Assert.assertTrue(faxTemplatePage.verifyAuditTrail(faxTemplateDetails, "MakerCreate", "New"), "Audit trail details failed");
        faxTemplatePage.selectMakeFaxTemplateChanges();
        Assert.assertTrue(faxTemplatePage.verifyTaskCompleteEnabled(), "Task complete button not enabled");
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="VerifyAuditTrailDataForAddNewFaxTemplateRecord")
    public void VerifyTaskCompleteActionForAddNewFaxTemplateRecord() {
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.taskCompleteAction("Task Complete for Create");
        Assert.assertTrue(faxTemplatePage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(faxTemplatePage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForAddNewFaxTemplateRecord")
    public void ApproveforAddNewFaxTemplateRecord(){
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.clickonApprove("Approve Create");
        Assert.assertEquals(faxTemplatePage.getSuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(faxTemplatePage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    
    @Test(groups = { "Checker" },dependsOnMethods = "ApproveforAddNewFaxTemplateRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxTemplateCreate(faxTemplateDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" },dependsOnMethods = "VerifyAuditTrailReportForApprove")
    public void AddNewFaxTemplateUploadLogoRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.addNewFaxTemplateUploadRecord(faxTemplateDetails);
        Assert.assertEquals(faxTemplatePage.getSuccessMessage(), "Record Created Successfully");
    }
    
    @Test(groups = { "Maker" },dependsOnMethods = "AddNewFaxTemplateUploadLogoRecord")
    public void VerifyAuditTrailReportForUploadLogoCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxTemplateCreate(faxTemplateDetails, "MakerCreate"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="AddNewFaxTemplateUploadLogoRecord")
    public void VerifyAuditTrailDataForAddNewFaxTemplateUploadLogoRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        Assert.assertTrue(faxTemplatePage.verifyAuditTrail(faxTemplateDetails, "MakerCreate", "New"), "Audit trail details failed");
        faxTemplatePage.taskCompleteAction("Task Complete for Create");
        Assert.assertTrue(faxTemplatePage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(faxTemplatePage.verifyStatus("Approval Pending"),"approal status details failed");
        }
    
    @Test(groups = { "Checker" },dependsOnMethods="VerifyAuditTrailDataForAddNewFaxTemplateUploadLogoRecord")
    public void ApproveforAddNewFaxTemplateUploadLogoRecord(){
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.clickonApprove("Approve Create");
        Assert.assertEquals(faxTemplatePage.getSuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(faxTemplatePage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    
    @Test(groups = { "Checker" },dependsOnMethods = "ApproveforAddNewFaxTemplateUploadLogoRecord")
    public void VerifyAuditTrailReportForUploadLogoApprove() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxTemplateCreate(faxTemplateDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="VerifyAuditTrailReportForUploadLogoApprove")
    public void AddNewFaxTemplateUploadHtmlRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
        FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.addNewFaxTemplateUploadHtmlRecord(faxTemplateDetails);
        Assert.assertEquals(faxTemplatePage.getSuccessMessage(), "Record Created Successfully");
    }
    
    @Test(groups = { "Maker" },dependsOnMethods = "AddNewFaxTemplateUploadHtmlRecord")
    public void VerifyAuditTrailReportForUploadHtmlCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxTemplateCreate(faxTemplateDetails, "MakerCreate"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="AddNewFaxTemplateUploadHtmlRecord")
    public void VerifyAuditTrailDataForAddNewFaxTemplateUploadHtmlRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        Assert.assertTrue(faxTemplatePage.verifyAuditTrail(faxTemplateDetails, "MakerCreate", "New"), "Audit trail details failed");
        faxTemplatePage.taskCompleteAction("Task Complete for Create");
        Assert.assertTrue(faxTemplatePage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(faxTemplatePage.verifyStatus("Approval Pending"),"approal status details failed");
        }
    
    @Test(groups= {"Maker"},dependsOnMethods="AddNewFaxTemplateUploadHtmlRecord")
    public void VerifyCreatedRecordApprovedDataSectionwithoutAprroval()throws Exception{
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        Assert.assertTrue(faxTemplatePage.verifyApprovedSectionData(faxTemplateDetails));
    }
    
    @Test(groups = { "Checker" },dependsOnMethods="VerifyAuditTrailDataForAddNewFaxTemplateUploadHtmlRecord")
    public void ApproveforAddNewFaxTemplateUploadHtmlRecord(){
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.clickonApprove("Approve Create");
        Assert.assertEquals(faxTemplatePage.getSuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(faxTemplatePage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    
    @Test(groups = { "Checker" },dependsOnMethods = "ApproveforAddNewFaxTemplateUploadHtmlRecord")
    public void VerifyAuditTrailReportForUploadHtmlApprove() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxTemplateCreate(faxTemplateDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Checker"},dependsOnMethods="ApproveforAddNewFaxTemplateUploadHtmlRecord")
    public void VerifyRecordApprovedDataSectionafterApproval()throws Exception{
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        Assert.assertTrue(faxTemplatePage.verifyApprovedSectionDataafterapproval(faxTemplateDetails));
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="AddNewFaxTemplateUploadHtmlRecord")
    public void AddDuplicateRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
        FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.addNewFaxTemplateUploadHtmlRecord(faxTemplateDetails);
        Assert.assertFalse(faxTemplatePage.getErrorMsg(),"Duplicate assetion failed");
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWhenTemplateNameBlank() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.addwithoutTemplateName(faxTemplateDetails);
        Assert.assertFalse(faxTemplatePage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWhenBodyBlank() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.addwithoutBody(faxTemplateDetails);
        Assert.assertFalse(faxTemplatePage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWhenUploadBlank() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.addwithoutUploadingFile(faxTemplateDetails);
        Assert.assertFalse(faxTemplatePage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWhenUploadWrongFile() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.addwithoutUploadingWrongFile(faxTemplateDetails);
        Assert.assertFalse(faxTemplatePage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="VerifyAddRecordWhenUploadWrongFile")
    public void AddRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(3);
        FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.addNewFaxTemplateRecord(faxTemplateDetails);
        Assert.assertEquals(faxTemplatePage.getSuccessMessage(), "Record Created Successfully");
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="AddRecord")
    public void VerifyMakeFaxTemplateButtonafterTaskComplete() throws Exception {
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.taskCompleteAction("Task Complete for Create");
        Assert.assertFalse(faxTemplatePage.VerifyMakefFaxTemplateChangeButton());
    }
    
    @Test(groups = { "Checker" },dependsOnMethods="VerifyMakeFaxTemplateButtonafterTaskComplete")
    public void RejectforAddNewFaxTemplateRecord(){
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.clickonReject("Reject Created");
        Assert.assertFalse(faxTemplatePage.getErrorMsg(),"Reject record assertion failed");
        Assert.assertTrue(faxTemplatePage.verifyReviewAuditTrail("Rejected","Reject Created"));
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="RejectforAddNewFaxTemplateRecord")
    public void VerifyMakeFaxTemplateButtonafterRejection() {
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        Assert.assertTrue(faxTemplatePage.VerifyMakefFaxTemplateChangeButton());
    }
    
    @Test(groups = { "Checker" },dependsOnMethods = "VerifyMakeFaxTemplateButtonafterRejection")
    public void VerifyAuditTrailReportForReject() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxTemplateCreate(faxTemplateDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Maker"},dependsOnMethods="VerifyAuditTrailReportForReject")
    public void VerifyRecordAfterRejection() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(3);
    	FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
    	Assert.assertTrue(faxTemplatePage.verifyApprovedSectionData(faxTemplateDetails));
    }
    
    @AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("FaxTemplateTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}