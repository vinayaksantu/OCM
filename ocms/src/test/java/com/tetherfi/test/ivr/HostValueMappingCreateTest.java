package com.tetherfi.test.ivr;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.ivr.HostValueMappingDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.HostValueMappingPage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class HostValueMappingCreateTest {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateToHostValueMappingPage(Method method) throws Exception {
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
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "IVR page assertion failed");
        ivrPage.navigateToHostValueMappingPage();
        HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertTrue(hostValueMappingPage.isHostValueMappingPageDisplayed(), "Branch Management page assertion failed");
    }
	/*
	@Test(groups= {"Maker"},priority=1)
    public void VerifyAddCancelButton() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails hostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertTrue(hostValueMappingPage.addCancelButton(hostValueMappingDetails), "Add cancel button assertion failed");
    }

	@Test(groups = { "Maker" },priority=2)
    public void VerifyAddRecordwithoutFunctionality() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addwithoutFunctionality(HostValueMappingDetails);
        Assert.assertEquals(HostValueMappingPage.getErrorMsg(),HostValueMappingDetails.getErrorMsg1());
    }
    
    @Test(groups = { "Maker" },priority=3)
    public void VerifyAddRecordWithoutLanguage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addwithoutLanguage(HostValueMappingDetails);
        Assert.assertEquals(HostValueMappingPage.getErrorMsg(),HostValueMappingDetails.getErrorMsg2());
    }
    
    @Test(groups = { "Maker" },priority=4)
    public void VerifyAddRecordWithoutHostData() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addwithoutHostData(HostValueMappingDetails);
        Assert.assertEquals(HostValueMappingPage.getErrorMsg(),HostValueMappingDetails.getErrorMsg3());
    }
    
    @Test(groups = { "Maker" },priority=5)
    public void VerifyAddRecordWithoutStatus() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addwithoutStatus(HostValueMappingDetails);
        Assert.assertEquals(HostValueMappingPage.getErrorMsg(),HostValueMappingDetails.getErrorMsg4());
    }
    
    @Test(groups = { "Maker" },priority=6)
    public void VerifyAddRecordWithoutDescription() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addwithoutDescription(HostValueMappingDetails);
        Assert.assertEquals(HostValueMappingPage.getErrorMsg(),HostValueMappingDetails.getErrorMsg5());
    }
    
    @Test(groups = { "Maker" },priority=7)
    public void VerifyAddRecordWithoutWaveFile() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addwithoutWaveFile(HostValueMappingDetails);
        Assert.assertEquals(HostValueMappingPage.getErrorMsg(),HostValueMappingDetails.getErrorMsg6());
    }
    
    @Test(groups = { "Maker" },priority=8)
    public void AddRevertRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addNewHostValueMappingRecord(HostValueMappingDetails);
        Assert.assertEquals(HostValueMappingPage.getSuccessMessage(), "Record created successfully");
       }
	
	@Test(groups = { "Maker" },priority=9,dependsOnMethods="AddRevertRecord")
    public void VerifyRevertForAddNewRecord() throws Exception {
       	HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
       	HostValueMappingPage.selectHostValueMappingAuditTrailTab();
       	HostValueMappingPage.selectRecord();
       	HostValueMappingPage.Revert("revert");
        Assert.assertTrue(HostValueMappingPage.verifyStatus("Reverted"),"approal status details failed");
    }
	
	@Test(groups= {"Maker"},priority=10,dependsOnMethods="VerifyRevertForAddNewRecord")
	public void VerifyApprovedDataSectionWithoutApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertTrue(HostValueMappingPage.verifyApprovedSectionData(HostValueMappingDetails));
	}
	
	@Test(groups = { "Maker" },priority=11,dependsOnMethods = "VerifyApprovedDataSectionWithoutApproval")
    public void VerifyAuditTrailReportForRevert() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyHostValueMappingCreate(HostValueMappingDetails, "MakerReverted"),"Audit Trail report assertion failed");
    }
	@Test(groups = { "Maker" },priority=12)
    public void AddRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addNewHostValueMappingRecord(HostValueMappingDetails);
        Assert.assertEquals(HostValueMappingPage.getSuccessMessage(), "Record created successfully");
       }*/
	
	@Test(groups = { "Maker" },priority=13,dependsOnMethods="AddRejectRecord")
    public void VerifySendForApprovalForAddNewRecord() throws Exception {
       	HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
       	HostValueMappingPage.selectHostValueMappingAuditTrailTab();
       	HostValueMappingPage.selectRecord();
       	HostValueMappingPage.sendForAprroval("sent");
        Assert.assertTrue(HostValueMappingPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Checker" },priority=14,dependsOnMethods="VerifySendForApprovalForAddNewRecord")
    public void RejectforAddNewHostValueMappingRecord() throws Exception{
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.clickonReject("Reject Created");
        Assert.assertFalse(HostValueMappingPage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(HostValueMappingPage.verifyReviewAuditTrail("Rejected","Reject Created"));
    }
    
    @Test(groups = { "Checker" },priority=15,dependsOnMethods = "RejectforAddNewHostValueMappingRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyHostValueMappingCreate(HostValueMappingDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Maker"},priority=16)//,dependsOnMethods="VerifyAuditTrailReportForReject")
    public void VerifyRecordAfterRejection() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertTrue(HostValueMappingPage.verifyApprovedSectionData(HostValueMappingDetails));
    }
	@Test(groups = { "Maker" },priority=17)
    public void AddNewHostValueMappingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addNewHostValueMappingRecord(HostValueMappingDetails);
        Assert.assertEquals(HostValueMappingPage.getSuccessMessage(), "Record created successfully");
    }
	
	@Test(groups = { "Maker" },priority=18,dependsOnMethods = "AddNewHostValueMappingRecord")
    public void VerifyAuditTrailReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyHostValueMappingCreate(HostValueMappingDetails, "MakerCreate"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=19,dependsOnMethods="AddNewHostValueMappingRecord")
    public void VerifyAuditTrailDataForAddNewHostValueMappingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.selectHostValueMappingAuditTrailTab();
        Assert.assertTrue(HostValueMappingPage.verifyAuditTrail(HostValueMappingDetails, "MakerCreate", "New"), "Audit trail details failed");
    }
    
    @Test(groups = { "Maker" },priority=20,dependsOnMethods="VerifyAuditTrailDataForAddNewHostValueMappingRecord")
    public void VerifySendForApprovalForAddNewHostValueMappingRecord() throws Exception {
       	HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
       	HostValueMappingPage.selectHostValueMappingAuditTrailTab();
       	HostValueMappingPage.selectRecord();
       	HostValueMappingPage.sendForAprroval("sent");
        Assert.assertTrue(HostValueMappingPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Maker" },priority=21,dependsOnMethods = "VerifySendForApprovalForAddNewHostValueMappingRecord")
    public void VerifyAuditTrailReportForSendForApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyHostValueMappingCreate(HostValueMappingDetails, "MakerSendToApproval"),"Audit Trail report assertion failed");
    }
    
	@Test(groups = { "Checker" },priority=22,dependsOnMethods="VerifyAuditTrailReportForSendForApproval")
    public void ApproveforAddNewHostValueMappingRecord() throws Exception{
       	HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
       	HostValueMappingPage.clickonApprove("Approve Create");
        Assert.assertTrue(HostValueMappingPage.verifyMessage());
        Assert.assertTrue(HostValueMappingPage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    
    @Test(groups = { "Checker" },priority=23,dependsOnMethods = "ApproveforAddNewHostValueMappingRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyHostValueMappingCreate(HostValueMappingDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Checker"},priority=24,dependsOnMethods="ApproveforAddNewHostValueMappingRecord")
    public void VerifyRecordApprovedDataSectionafterApproval()throws Exception{
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertTrue(HostValueMappingPage.verifyApprovedSectionDataafterapproval(HostValueMappingDetails));
    }
    
    @Test(groups = { "Maker" },priority=25,dependsOnMethods="AddNewHostValueMappingRecord")
    public void AddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addNewHostValueMappingRecord(HostValueMappingDetails);
        Assert.assertEquals(HostValueMappingPage.getErrorMsg(),HostValueMappingDetails.getErrorMsg7(),HostValueMappingDetails.getErrorMsg6());
    }
    
    @AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("HostValueMappingCreateTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}
