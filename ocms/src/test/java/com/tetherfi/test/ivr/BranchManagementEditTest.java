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
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class BranchManagementEditTest {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateBranchManagementPage(Method method) throws Exception {
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
	public void EditCancelBranchManagementRecord() throws Exception {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        Assert.assertTrue(branchManagementPage.EditCancel(branchManagementDetails), "Edit Cancel assertion Failed");
	}	
    
    @Test(groups = { "Maker" },priority=2)
    public void EditRecordWithoutModifyReaosn() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.EditRecordWithoutModifyReason(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg(),"Invalid Record Assertion failed");
    }
    
    @Test(groups= {"Maker"},priority=3)
	public void EditRevertBranchManagementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementPage.EditBranchManagementRecord(BranchManagementDetails);
        Assert.assertEquals(BranchManagementPage.getSuccessMessage(), "Record updated successfully");
	}
	
	@Test(groups = { "Maker" },priority=4,dependsOnMethods="EditRevertBranchManagementRecord")
    public void VerifyRevertForEditRecord() throws Exception {
       	BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
       	BranchManagementPage.selectBranchManagementAuditTrailTab();
       	BranchManagementPage.selectRecord();
       	BranchManagementPage.Revert("revert");
        Assert.assertTrue(BranchManagementPage.verifyStatus("Reverted"),"approval status details failed");
    }
	
	@Test(groups= {"Maker"},priority=5,dependsOnMethods="VerifyRevertForEditRecord")
    public void VerifyAuditTrialReportForRevertUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementUpdate(BranchManagementDetails,"MakerReverted"));
    }
	
	@Test(groups= {"Maker"},priority=6)
	public void EditRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementPage.EditBranchManagementRecord(BranchManagementDetails);
        Assert.assertEquals(BranchManagementPage.getSuccessMessage(), "Record updated successfully");
	}
	
	@Test(groups = { "Maker" },priority=7,dependsOnMethods="EditRejectRecord")
    public void VerifySendForApprovalForEditRejectRecord() throws Exception {
       	BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
       	BranchManagementPage.selectBranchManagementAuditTrailTab();
       	BranchManagementPage.selectRecord();
       	BranchManagementPage.sendForAprroval("sent");
        Assert.assertTrue(BranchManagementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }

	@Test(groups = { "Checker" },priority=8)//,dependsOnMethods="VerifySendForApprovalForEditRejectRecord")
    public void RejectforEditBranchManagementRecord() throws Exception{
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementPage.clickonReject("Reject Updated");
        Assert.assertFalse(BranchManagementPage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(BranchManagementPage.verifyReviewAuditTrail("Rejected","Reject Updated"));
    }
    
    @Test(groups = { "Checker" },priority=9,dependsOnMethods = "RejectforEditBranchManagementRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementUpdate(BranchManagementDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
	@Test(groups= {"Maker"},priority=10)
	public void EditBranchManagementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementPage.EditBranchManagementRecord(BranchManagementDetails);
        Assert.assertEquals(BranchManagementPage.getSuccessMessage(), "Record updated successfully");
	}
	
	@Test(groups = { "Maker" },priority=11,dependsOnMethods="EditBranchManagementRecord")
    public void VerifyAuditTrailDataForEditBranchManagementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementPage.selectBranchManagementAuditTrailTab();
        Assert.assertTrue(BranchManagementPage.verifyAuditTrailUpdate(BranchManagementDetails, "MakerUpdate", "New"), "Audit trail details failed");
    }
	

	@Test(groups= {"Maker"},priority=12,dependsOnMethods="EditBranchManagementRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementUpdate(BranchManagementDetails,"MakerUpdate"));
    }
    
	@Test(groups = { "Maker" },priority=13,dependsOnMethods="VerifyAuditTrialReportForUpdate")
    public void VerifySendForApprovalForRejectRecord() throws Exception {
       	BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
       	BranchManagementPage.selectBranchManagementAuditTrailTab();
       	BranchManagementPage.selectRecord();
       	BranchManagementPage.sendForAprroval("sent");
        Assert.assertTrue(BranchManagementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	@Test(groups= {"Maker"},priority=14,dependsOnMethods="VerifySendForApprovalForRejectRecord")
    public void VerifyAuditTrialReportForSendForApprovalUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementUpdate(BranchManagementDetails,"MakerSendToApproval"));
    }
    
    @Test(groups = { "Checker" },priority=15)//,dependsOnMethods="VerifyAuditTrialReportForSendForApprovalUpdate")
    public void ApproveforEditBranchManagementRecord() throws Exception{
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementPage.clickonApprove("Approve Edited");
        Assert.assertTrue(BranchManagementPage.verifyMessage());
        Assert.assertTrue(BranchManagementPage.verifyReviewAuditTrail("Approved","Approve Edited"));
    }
	
	@Test(groups = { "Checker" },priority=16,dependsOnMethods = "ApproveforEditBranchManagementRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);	    
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementUpdate(BranchManagementDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
   
    @AfterMethod
	    public void afterEachMethod(Method method){
	        Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("BranchManagementTest",method.getName());
	        driver.navigate().refresh();
	        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	        homePage.userLogout();
	        driver.close();
	        System.out.println("Completed Executing : "+method.getName());
	    }
}
