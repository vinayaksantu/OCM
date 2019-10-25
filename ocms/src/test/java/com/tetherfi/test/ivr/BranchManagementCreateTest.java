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


import com.tetherfi.model.ivr.BranchManagementDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.BranchManagementPage;

import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.BranchManagementPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class BranchManagementCreateTest {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateToBranchManagementPage(Method method) throws Exception {
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
        ivrPage.navigateToBranchManagementPage();
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        Assert.assertTrue(branchManagementPage.isBranchManagementPageDisplayed(), "Branch Management page assertion failed");
    }
	
	@Test(groups= {"Maker"},priority=1)
    public void VerifyAddCancelButton() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        Assert.assertTrue(branchManagementPage.addCancelButton(branchManagementDetails), "Add cancel button assertion failed");
    }	
    
    @Test(groups = { "Maker" },priority=2)
    public void VerifyAddRecordwithoutMainLines() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutMainLines(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=3)
    public void VerifyAddRecordWithoutSubLines() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutSubLines(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=4)
    public void VerifyAddRecordWithoutLocation() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutLocation(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=5)
    public void VerifyAddRecordWithoutBranchType() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutBranchType(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=6)
    public void VerifyAddRecordWithoutBranchName() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutBranchName(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=7)
    public void VerifyAddRecordWithoutAddress() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutAddress(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=8)
    public void VerifyAddRecordWithoutLineOrder() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutLineOrder(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=9)
    public void VerifyAddRecordWithoutStatus() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutStatus(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=10)
    public void VerifyAddRecordWithoutLanguage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutLanguage(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    
    @Test(groups = { "Maker" },priority=11)
    public void AddRevertRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementPage.addNewBranchManagementRecord(BranchManagementDetails);
        Assert.assertEquals(BranchManagementPage.getSuccessMessage(), "Record Created Successfully");
       }
	
	@Test(groups = { "Maker" },priority=12,dependsOnMethods="AddRevertRecord")
    public void VerifyRevertForAddNewRecord() throws Exception {
       	BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
       	BranchManagementPage.selectBranchManagementAuditTrailTab();
       	BranchManagementPage.selectRecord();
       	BranchManagementPage.Revert("revert");
        Assert.assertTrue(BranchManagementPage.verifyStatus("Reverted"),"approal status details failed");
    }
	
	@Test(groups= {"Maker"},priority=13,dependsOnMethods="VerifyRevertForAddNewRecord")
	public void VerifyApprovedDataSectionWithoutApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        Assert.assertTrue(BranchManagementPage.verifyApprovedSectionData(BranchManagementDetails));
	}
	
	@Test(groups = { "Maker" },priority=14,dependsOnMethods = "VerifyApprovedDataSectionWithoutApproval")
    public void VerifyAuditTrailReportForRevert() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementCreate(BranchManagementDetails, "MakerReverted"),"Audit Trail report assertion failed");
    }
	@Test(groups = { "Maker" },priority=15)
    public void AddRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementPage.addNewBranchManagementRecord(BranchManagementDetails);
        Assert.assertEquals(BranchManagementPage.getSuccessMessage(), "Record Created Successfully");
       }
	
	@Test(groups = { "Maker" },priority=16,dependsOnMethods="AddRejectRecord")
    public void VerifySendForApprovalForAddNewRecord() throws Exception {
       	BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
       	BranchManagementPage.selectBranchManagementAuditTrailTab();
       	BranchManagementPage.selectRecord();
       	BranchManagementPage.sendForAprroval("sent");
        Assert.assertTrue(BranchManagementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Checker" },priority=17,dependsOnMethods="VerifySendForApprovalForAddNewRecord")
    public void RejectforAddNewBranchManagementRecord() throws Exception{
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementPage.clickonReject("Reject Created");
        Assert.assertFalse(BranchManagementPage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(BranchManagementPage.verifyReviewAuditTrail("Rejected","Reject Created"));
    }
    
    @Test(groups = { "Checker" },priority=18,dependsOnMethods = "RejectforAddNewBranchManagementRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementCreate(BranchManagementDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Maker"},priority=19,dependsOnMethods="VerifyAuditTrailReportForReject")
    public void VerifyRecordAfterRejection() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        Assert.assertTrue(BranchManagementPage.verifyApprovedSectionData(BranchManagementDetails));
    }
	@Test(groups = { "Maker" },priority=20)
    public void AddNewBranchManagementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementPage.addNewBranchManagementRecord(BranchManagementDetails);
        Assert.assertEquals(BranchManagementPage.getSuccessMessage(), "Record Created Successfully");
    }
	
	@Test(groups = { "Maker" },priority=21,dependsOnMethods = "AddNewBranchManagementRecord")
    public void VerifyAuditTrailReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementCreate(BranchManagementDetails, "MakerCreate"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=22,dependsOnMethods="AddNewBranchManagementRecord")
    public void VerifyAuditTrailDataForAddNewBranchManagementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementPage.selectBranchManagementAuditTrailTab();
        Assert.assertTrue(BranchManagementPage.verifyAuditTrail(BranchManagementDetails, "MakerCreate", "New"), "Audit trail details failed");
    }
    
    @Test(groups = { "Maker" },priority=23,dependsOnMethods="VerifyAuditTrailDataForAddNewBranchManagementRecord")
    public void VerifySendForApprovalForAddNewBranchManagementRecord() throws Exception {
       	BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
       	BranchManagementPage.selectBranchManagementAuditTrailTab();
       	BranchManagementPage.selectRecord();
       	BranchManagementPage.sendForAprroval("sent");
        Assert.assertTrue(BranchManagementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Maker" },priority=24,dependsOnMethods = "VerifySendForApprovalForAddNewBranchManagementRecord")
    public void VerifyAuditTrailReportForSendForApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementCreate(BranchManagementDetails, "MakerSendToApproval"),"Audit Trail report assertion failed");
    }
    
	@Test(groups = { "Checker" },priority=25,dependsOnMethods="VerifyAuditTrailReportForSendForApproval")

    public void ApproveforAddNewBranchManagementRecord() throws Exception{
       	BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
       	BranchManagementPage.clickonApprove("Approve Create");
        Assert.assertTrue(BranchManagementPage.verifyMessage());
        Assert.assertTrue(BranchManagementPage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    
    @Test(groups = { "Checker" },priority=26,dependsOnMethods = "ApproveforAddNewBranchManagementRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementCreate(BranchManagementDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Checker"},priority=27,dependsOnMethods="ApproveforAddNewBranchManagementRecord")
    public void VerifyRecordApprovedDataSectionafterApproval()throws Exception{
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        Assert.assertTrue(BranchManagementPage.verifyApprovedSectionDataafterapproval(BranchManagementDetails));
    }
    
    @Test(groups = { "Maker" },priority=28,dependsOnMethods="AddNewBranchManagementRecord")
    public void AddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementPage.addNewBranchManagementRecord(BranchManagementDetails);
        Assert.assertFalse(BranchManagementPage.getErrorMsg(),"Duplicate assetion failed");
    }
    
    @AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("BranchManagementCreateTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}
