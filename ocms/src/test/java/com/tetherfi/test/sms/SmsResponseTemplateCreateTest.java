package com.tetherfi.test.sms;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.sms.SmsResponseTemplateDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.SmsResponseTemplatePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.SmsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class SmsResponseTemplateCreateTest {
	
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateToSmsResponseTemplatePage(Method method) throws Exception {
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
            loginPage.login(map.get("Username"),map.get("Password"));
            Thread.sleep(5000);
        }
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("SMS");
        SmsPage smsPage = PageFactory.createPageInstance(driver, SmsPage.class);
        Assert.assertTrue(smsPage.isSMSPageDisplayed(), "SMS page assertion failed");
        smsPage.navigateToSMSResponseTemplatePage();
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        Assert.assertTrue(SmsResponseTemplatePage.isSMSResponseTemplatePageDisplayed(), "SMS Response Template page assertion failed");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test(groups= {"Maker"})
    public void VerifyAddCancelButton() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        Assert.assertTrue(SmsResponseTemplatePage.addCancelButton(SmsResponseTemplateDetails), "Add cancel button assertion failed");
    }
	
	@Test(groups = { "Maker" })
    public void AddNewSmsResponseTemplateRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.addNewSmsResponseTemplateRecord(SmsResponseTemplateDetails);
        Assert.assertEquals(SmsResponseTemplatePage.getSuccessMessage(), "Record Created Successfully");
    }
	
	@Test(groups = { "Maker" },dependsOnMethods = "AddNewSmsResponseTemplateRecord")
    public void VerifyAuditTrailReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateCreate(SmsResponseTemplateDetails, "MakerCreate"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="AddNewSmsResponseTemplateRecord")
    public void VerifyAuditTrailDataForAddNewSmsResponseTemplateRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        Assert.assertTrue(SmsResponseTemplatePage.verifyAuditTrail(SmsResponseTemplateDetails, "MakerCreate", "New"), "Audit trail details failed");
        SmsResponseTemplatePage.selectMakeSmsResponseTemplateChanges();
        Assert.assertTrue(SmsResponseTemplatePage.verifyTaskCompleteEnabled(), "Task complete button not enabled");
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="VerifyAuditTrailDataForAddNewSmsResponseTemplateRecord")
    public void VerifyTaskCompleteActionForAddNewSmsResponseTemplateRecord() throws Exception {
       	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
       	SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
       	SmsResponseTemplatePage.taskCompleteAction("Task Complete for Create");
        Assert.assertTrue(SmsResponseTemplatePage.verifyTaskCompleteEnabled(),"Task Complete record assertion failed");
        Assert.assertTrue(SmsResponseTemplatePage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForAddNewSmsResponseTemplateRecord")
    public void ApproveforAddNewSmsResponseTemplateRecord() throws Exception{
       	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
       	SmsResponseTemplatePage.clickonApprove("Approve Create");
        Assert.assertEquals(SmsResponseTemplatePage.getSuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(SmsResponseTemplatePage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    
    @Test(groups = { "Checker" },dependsOnMethods = "ApproveforAddNewSmsResponseTemplateRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateCreate(SmsResponseTemplateDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Checker"},dependsOnMethods="ApproveforAddNewSmsResponseTemplateRecord")
    public void VerifyRecordApprovedDataSectionafterApproval()throws Exception{
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        Assert.assertTrue(SmsResponseTemplatePage.verifyApprovedSectionDataafterapproval(SmsResponseTemplateDetails));
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="AddNewSmsResponseTemplateRecord")
    public void AddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.addNewSmsResponseTemplateRecord(SmsResponseTemplateDetails);
        Assert.assertFalse(SmsResponseTemplatePage.getErrorMsg(),"Duplicate assetion failed");
    }
    
   @Test(groups = { "Maker" })
    public void VerifyAddRecordwithoutText() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.addwithoutText(SmsResponseTemplateDetails);
        Assert.assertFalse(SmsResponseTemplatePage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutIntent() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.addwithoutIntent(SmsResponseTemplateDetails);
        Assert.assertFalse(SmsResponseTemplatePage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutEnable() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.addwithoutEnable(SmsResponseTemplateDetails);
        Assert.assertFalse(SmsResponseTemplatePage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutICOMTemplateID() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.addwithoutICOMTemplateID(SmsResponseTemplateDetails);
        Assert.assertFalse(SmsResponseTemplatePage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="VerifyRecordApprovedDataSectionafterApproval")
    public void AddRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.addNewSmsResponseTemplateRecord(SmsResponseTemplateDetails);
        Assert.assertEquals(SmsResponseTemplatePage.getSuccessMessage(), "Record created successfully");
       }
	
	@Test(groups = { "Maker" },dependsOnMethods="AddRecord")
    public void VerifyTaskCompleteActionForAddNewRecord() throws Exception {
       	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
       	SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
       	SmsResponseTemplatePage.taskCompleteAction("Task Complete for Create");
        Assert.assertTrue(SmsResponseTemplatePage.verifyTaskCompleteEnabled(),"Task Complete record assertion failed");
        Assert.assertTrue(SmsResponseTemplatePage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	@Test(groups= {"Maker"}, dependsOnMethods="AddRecord")
	public void VerifyApprovedDataSectionWithoutApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        Assert.assertTrue(SmsResponseTemplatePage.verifyApprovedSectionData(SmsResponseTemplateDetails));
	}
    
    @Test(groups = { "Checker" },dependsOnMethods="VerifyApprovedDataSectionWithoutApproval")
    public void RejectforAddNewSmsResponseTemplateRecord() throws Exception{
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.clickonReject("Reject Created");
        Assert.assertFalse(SmsResponseTemplatePage.getErrorMsg(),"Reject record assertion failed");
        Assert.assertTrue(SmsResponseTemplatePage.verifyReviewAuditTrail("Rejected","Reject Created"));
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="RejectforAddNewSmsResponseTemplateRecord")
    public void VerifyMakeSmsResponseTemplateButtonafterRejection() {
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        Assert.assertTrue(SmsResponseTemplatePage.VerifyMakeSmsResponseTemplateChangeButton());
    }
    
    @Test(groups = { "Checker" },dependsOnMethods = "VerifyMakeSmsResponseTemplateButtonafterRejection")
    public void VerifyAuditTrailReportForReject() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateCreate(SmsResponseTemplateDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Maker"},dependsOnMethods="VerifyAuditTrailReportForReject")
    public void VerifyRecordAfterRejection() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        Assert.assertTrue(SmsResponseTemplatePage.verifyApprovedSectionData(SmsResponseTemplateDetails));
    }
    
    @AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("SmsResponseTemplateCreateTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }

}
