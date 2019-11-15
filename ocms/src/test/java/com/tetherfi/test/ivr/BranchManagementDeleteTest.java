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

public class BranchManagementDeleteTest {
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
	public void DeleteCancelBranchManagementRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
	     Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
	     BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
	     Assert.assertTrue(branchManagementPage.DeleteCancel(branchManagementDetails), "Delete Cancel assertion Failed");
	}
	
	@Test(groups = { "Maker" },priority=2)
    public void DeleteRecordWithoutDeleteReason() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
	     BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
	     branchManagementPage.DeleteRecordWithoutDeleteReason(branchManagementDetails);
	     Assert.assertFalse(branchManagementPage.getErrorMsg(),"Invalid Record Assertion failed");
    }
	
	
    @Test(groups = { "Maker" },priority=3)
    public void DeleteRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
	     BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
	     BranchManagementPage.DeleteBranchManagementRecord(BranchManagementDetails);
        Assert.assertEquals(BranchManagementPage.getSuccessMessage(), "Record Deleted Successfully");
     }
        
	@Test(groups = { "Maker" },priority=4,dependsOnMethods="DeleteRecord")
    public void VerifyRevertForDeleteRecord() throws Exception {
       	BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
       	BranchManagementPage.selectBranchManagementAuditTrailTab();
       	BranchManagementPage.selectRecord();
       	BranchManagementPage.Revert("revert");
        Assert.assertTrue(BranchManagementPage.verifyStatus("Reverted"),"approval status details failed");
    }
    
	@Test(groups= {"Maker"},priority=5,dependsOnMethods="VerifyRevertForDeleteRecord")
    public void VerifyAuditTrialReportForRevertDelete() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementDelete(BranchManagementDetails,"MakerReverted"));
    }
	
	@Test(groups = { "Maker" },priority=6)
    public void RejectDeleteRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
	     BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
	     BranchManagementPage.DeleteBranchManagementRecord(BranchManagementDetails);
        Assert.assertEquals(BranchManagementPage.getSuccessMessage(), "Record Deleted Successfully");
     }
	
	@Test(groups = { "Maker" },priority=7,dependsOnMethods="RejectDeleteRecord")
    public void VerifySendForApprovalForDeleteNewRecord() throws Exception {
       	BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
       	BranchManagementPage.selectBranchManagementAuditTrailTab();
       	BranchManagementPage.selectRecord();
       	BranchManagementPage.sendForAprroval("sent");
        Assert.assertTrue(BranchManagementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
		
    @Test(priority=8,groups = { "Checker" },dependsOnMethods="VerifySendForApprovalForDeleteNewRecord")
    public void RejectforDeleteBranchManagementRecord() throws Exception{
	     BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
	     BranchManagementPage.clickonReject("Reject Deleted");
        Assert.assertFalse(BranchManagementPage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(BranchManagementPage.verifyReviewAuditTrail("Rejected","Reject Deleted"));
    }
    
    @Test(priority=9,groups = { "Checker" },dependsOnMethods = "RejectforDeleteBranchManagementRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(1);
	    BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementDelete(BranchManagementDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
	
	@Test(groups= {"Maker"},priority=10)
	public void DeleteBranchManagementRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
	     BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
	     BranchManagementPage.DeleteBranchManagementRecord(BranchManagementDetails);
        Assert.assertEquals(BranchManagementPage.getSuccessMessage(), "Record Deleted Successfully");
	}
	
	@Test(priority=11,groups= {"Maker"},dependsOnMethods="DeleteBranchManagementRecord")
    public void VerifyAuditTrialReportForDelete() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
	     BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementDelete(BranchManagementDetails,"MakerDelete"));
    }
	
	@Test(priority=12,groups = { "Maker" })//,dependsOnMethods="DeleteBranchManagementRecord")
    public void VerifyAuditTrailDataForDeleteBranchManagementRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
	     BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
	     BranchManagementPage.selectBranchManagementAuditTrailTab();
	     Assert.assertTrue(BranchManagementPage.verifyAuditTrailDelete(BranchManagementDetails, "MakerDelete", "New"), "Audit trail details failed");
    }

	@Test(groups = { "Maker" },priority=13,dependsOnMethods="VerifyAuditTrailDataForDeleteBranchManagementRecord")
    public void VerifySendForApprovalForDeleteRecord() throws Exception {
       	BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
       	BranchManagementPage.selectBranchManagementAuditTrailTab();
       	BranchManagementPage.selectRecord();
       	BranchManagementPage.sendForAprroval("sent");
        Assert.assertTrue(BranchManagementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	@Test(priority=14,groups = { "Maker" },dependsOnMethods = "VerifySendForApprovalForDeleteRecord")
    public void VerifyAuditTrailReportForSendForApprove() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
	     BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
	     HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMReportsPage();
	     OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	     String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	     Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	     ReportDetails reportDetails= new ReportDetails(map1);
	     ocmReportsPage.showReport(reportDetails);
	     Assert.assertTrue(ocmReportsPage.verifyBranchManagementDelete(BranchManagementDetails, "MakerSendToApproval"),"Audit Trail report assertion failed");
    }
	
	@Test(priority=15,groups = { "Checker" })
    public void ApproveforDeleteBranchManagementRecord() throws Exception{
	     BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
	     BranchManagementPage.clickonApprove("Approve Deleted");
	     Assert.assertTrue(BranchManagementPage.verifyMessage(),"Approve record assertion failed");
	     Assert.assertTrue(BranchManagementPage.verifyReviewAuditTrail("Approved","Approve Deleted"));
    }
	
	@Test(priority=16,groups = { "Checker" },dependsOnMethods = "ApproveforDeleteBranchManagementRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
	     BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
	     HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMReportsPage();
	     OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	     String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	     Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	     ReportDetails reportDetails= new ReportDetails(map1);
	     ocmReportsPage.showReport(reportDetails);
	     Assert.assertTrue(ocmReportsPage.verifyBranchManagementDelete(BranchManagementDetails, "CheckerApprove"),"Audit Trail report assertion failed");
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
