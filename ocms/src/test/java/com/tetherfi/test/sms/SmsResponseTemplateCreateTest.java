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
            map= new ExcelReader(filePath,"Login").getTestData().get(1);
        else
            map= new ExcelReader(filePath,"Login").getTestData().get(0);
        try{driver.get("https://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}catch (TimeoutException e){e.printStackTrace();driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}
        LoginPage loginPage=PageFactory.createPageInstance(driver,LoginPage.class);
        loginPage.overrideSecurityConcern();
        if(map.get("LoginType").equals("Custom")){
            Assert.assertTrue(loginPage.isLoginPageDisplayed(),"Login page not loaded");
			loginPage.login(map.get("Username"),map.get("Password"),map.get("EmailId"));
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
	
	@Test(groups= {"Maker"},priority=1)
    public void VerifyAddCancelButton() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        Assert.assertTrue(SmsResponseTemplatePage.addCancelButton(SmsResponseTemplateDetails), "Add cancel button assertion failed");
    }
	
	@Test(groups = { "Maker" },priority=2)
    public void VerifyAddRecordwithoutText() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.addwithoutText(SmsResponseTemplateDetails);
        Assert.assertFalse(SmsResponseTemplatePage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=3)
    public void VerifyAddRecordWithoutIntent() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.addwithoutIntent(SmsResponseTemplateDetails);
        Assert.assertFalse(SmsResponseTemplatePage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=4)
    public void VerifyAddRecordWithoutEnable() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.addwithoutEnable(SmsResponseTemplateDetails);
        Assert.assertFalse(SmsResponseTemplatePage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=5)
    public void VerifyAddRecordWithoutICOMTemplateID() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.addwithoutICOMTemplateID(SmsResponseTemplateDetails);
        Assert.assertFalse(SmsResponseTemplatePage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=6)
    public void AddRevertRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.addNewSmsResponseTemplateRecord(SmsResponseTemplateDetails);
        Assert.assertEquals(SmsResponseTemplatePage.getSuccessMessage(), "Record Created Successfully");
       }
	
	@Test(groups = { "Maker" },priority=7,dependsOnMethods="AddRevertRecord")
    public void VerifyRevertForAddNewRecord() throws Exception {
       	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
       	SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
       	SmsResponseTemplatePage.selectRecord();
       	SmsResponseTemplatePage.Revert("revert");
        Assert.assertTrue(SmsResponseTemplatePage.verifyStatus("Reverted"),"approal status details failed");
    }
	
	@Test(groups= {"Maker"},priority=8,dependsOnMethods="VerifyRevertForAddNewRecord")
	public void VerifyApprovedDataSectionWithoutApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        Assert.assertTrue(SmsResponseTemplatePage.verifyApprovedSectionData(SmsResponseTemplateDetails));
	}
	
	@Test(groups = { "Maker" },priority=9,dependsOnMethods = "VerifyApprovedDataSectionWithoutApproval")
    public void VerifyAuditTrailReportForRevert() throws Exception {
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
        Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateCreate(SmsResponseTemplateDetails, "MakerReverted"),"Audit Trail report assertion failed");
    }
	@Test(groups = { "Maker" },priority=10)
    public void AddRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.addNewSmsResponseTemplateRecord(SmsResponseTemplateDetails);
        Assert.assertEquals(SmsResponseTemplatePage.getSuccessMessage(), "Record Created Successfully");
       }
	
	@Test(groups = { "Maker" },priority=11,dependsOnMethods="AddRejectRecord")
    public void VerifySendForApprovalForAddNewRecord() throws Exception {
       	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
       	SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
       	SmsResponseTemplatePage.selectRecord();
       	SmsResponseTemplatePage.sendForAprroval("sent");
        Assert.assertTrue(SmsResponseTemplatePage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Checker" },priority=12)//,dependsOnMethods="VerifySendForApprovalForAddNewRecord")
    public void RejectforAddNewSmsResponseTemplateRecord() throws Exception{
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.clickonReject("Reject Created");
        Assert.assertFalse(SmsResponseTemplatePage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(SmsResponseTemplatePage.verifyReviewAuditTrail("Rejected","Reject Created"));
    }
    
    @Test(groups = { "Checker" },priority=13,dependsOnMethods = "RejectforAddNewSmsResponseTemplateRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
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
        Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateCreate(SmsResponseTemplateDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Maker"},priority=14,dependsOnMethods="VerifyAuditTrailReportForReject")
    public void VerifyRecordAfterRejection() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        Assert.assertTrue(SmsResponseTemplatePage.verifyApprovedSectionData(SmsResponseTemplateDetails));
    }
	
    @Test(groups = { "Maker" },priority=15)
    public void AddNewSmsResponseTemplateRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.addNewSmsResponseTemplateRecord(SmsResponseTemplateDetails);
        Assert.assertEquals(SmsResponseTemplatePage.getSuccessMessage(), "Record Created Successfully");
    }
	
	@Test(groups = { "Maker" },priority=16,dependsOnMethods = "AddNewSmsResponseTemplateRecord")
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
    
    @Test(groups = { "Maker" },priority=17)//,dependsOnMethods="AddNewSmsResponseTemplateRecord")
    public void VerifyAuditTrailDataForAddNewSmsResponseTemplateRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        Assert.assertTrue(SmsResponseTemplatePage.verifyAuditTrail(SmsResponseTemplateDetails, "MakerCreate", "New"), "Audit trail details failed");
    }
    
    @Test(groups = { "Maker" },priority=18)//,dependsOnMethods="VerifyAuditTrailDataForAddNewSmsResponseTemplateRecord")
    public void VerifySendForApprovalForAddNewSmsResponseTemplateRecord() throws Exception {
       	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
       	SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
       	SmsResponseTemplatePage.selectRecord();
       	SmsResponseTemplatePage.sendForAprroval("sent");
        Assert.assertTrue(SmsResponseTemplatePage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Maker" },priority=19,dependsOnMethods = "VerifySendForApprovalForAddNewSmsResponseTemplateRecord")
    public void VerifyAuditTrailReportForSendForApproval() throws Exception {
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
        Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateCreate(SmsResponseTemplateDetails, "MakerSendToApproval"),"Audit Trail report assertion failed");
    }
    
	@Test(groups = { "Checker" },priority=20,dependsOnMethods="VerifyAuditTrailReportForSendForApproval")
    public void ApproveforAddNewSmsResponseTemplateRecord() throws Exception{
       	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
       	SmsResponseTemplatePage.clickonApprove("Approve Create");
        Assert.assertTrue(SmsResponseTemplatePage.verifyMessage());
        Assert.assertTrue(SmsResponseTemplatePage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    
    @Test(groups = { "Checker" },priority=21,dependsOnMethods = "ApproveforAddNewSmsResponseTemplateRecord")
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
    
    @Test(groups= {"Checker"},priority=22,dependsOnMethods="ApproveforAddNewSmsResponseTemplateRecord")
    public void VerifyRecordApprovedDataSectionafterApproval()throws Exception{
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        Assert.assertTrue(SmsResponseTemplatePage.verifyApprovedSectionDataafterapproval(SmsResponseTemplateDetails));
    }
    
    @Test(groups = { "Maker" },priority=23)//,dependsOnMethods="AddNewSmsResponseTemplateRecord")
    public void AddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.addNewSmsResponseTemplateRecord(SmsResponseTemplateDetails);
        Assert.assertFalse(SmsResponseTemplatePage.getErrorMsg(),"Duplicate assetion failed");
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
