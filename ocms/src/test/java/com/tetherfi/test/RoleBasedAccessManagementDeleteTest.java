package com.tetherfi.test;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.user.UserDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.RoleBasedAccessManagementPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class RoleBasedAccessManagementDeleteTest {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateToRoleBasedAccessManagementPage(Method method) throws Exception {
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
        ocmHomePage.navigateToRoleBasedAccessManagementPage();
        RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        Assert.assertTrue(RoleBasedAccessManagementPage.isRoleBasedAccessManagementPageDisplayed(), "RoleBased Access Management page assertion failed");
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	
	@Test(groups= {"Maker"}, priority=1)
	public void DeleteCancelRoleBasedAccessManagementRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
	     Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     UserDetails UserDetails = new UserDetails(map);
	     RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
	     Assert.assertTrue(RoleBasedAccessManagementPage.DeleteCancel(UserDetails), "Delete Cancel assertion Failed");
	}
	
	@Test(groups = { "Maker" }, priority=2)
    public void DeleteWithoutModifyReasonRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     UserDetails UserDetails = new UserDetails(map);
	     RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
	     RoleBasedAccessManagementPage.DeleteRecordWithoutModifyReason(UserDetails);
	     Assert.assertEquals(RoleBasedAccessManagementPage.getErrorMsg(),"Please enter the delete reason","Invalid Record Assertion failed");
    }
	
	@Test(groups = { "Maker" },priority=3)
    public void DeleteRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     UserDetails UserDetails = new UserDetails(map);
	     RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
	     RoleBasedAccessManagementPage.DeleteRoleBasedAccessManagementRecord(UserDetails);
        Assert.assertEquals(RoleBasedAccessManagementPage.getSuccessMessage(), "Record Deleted Successfully");
     }
        
	@Test(groups = { "Maker" },priority=4,dependsOnMethods="DeleteRecord")
    public void VerifyRevertForDeleteRecord() throws Exception {
       	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
       	RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
       	RoleBasedAccessManagementPage.selectRecord();
       	RoleBasedAccessManagementPage.Revert("revert");
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyStatus("Reverted"),"approval status details failed");
    }
    
	@Test(groups= {"Maker"},priority=5,dependsOnMethods="VerifyRevertForDeleteRecord")
    public void VerifyAuditTrialReportForRevertDelete() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
	    UserDetails UserDetails = new UserDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementDelete(UserDetails,"MakerReverted"));
    }
	
	@Test(groups = { "Maker" },priority=6)
    public void RejectDeleteRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     UserDetails UserDetails = new UserDetails(map);
	     RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
	     RoleBasedAccessManagementPage.DeleteRoleBasedAccessManagementRecord(UserDetails);
        Assert.assertEquals(RoleBasedAccessManagementPage.getSuccessMessage(), "Record Deleted Successfully");
     }
	
	@Test(groups = { "Maker" },priority=7,dependsOnMethods="RejectDeleteRecord")
    public void VerifySendForApprovalForRejectDeleteNewRecord() throws Exception {
       	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
       	RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
       	RoleBasedAccessManagementPage.selectRecord();
       	RoleBasedAccessManagementPage.sendForAprroval("sent");
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
		
    @Test(priority=8,groups = { "Checker" })//,dependsOnMethods="VerifySendForApprovalForDeleteNewRecord")
    public void RejectforDeleteRoleBasedAccessManagementRecord() throws Exception{
	     RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
	     RoleBasedAccessManagementPage.clickonReject("Reject Deleted");
        Assert.assertFalse(RoleBasedAccessManagementPage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyReviewAuditTrail("Rejected","Reject Deleted"));
    }
    
    @Test(priority=9,groups = { "Checker" },dependsOnMethods = "RejectforDeleteRoleBasedAccessManagementRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
	    UserDetails UserDetails = new UserDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementDelete(UserDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
	@Test(groups= {"Maker"},priority=10)
	public void DeleteRoleBasedAccessManagementRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     UserDetails UserDetails = new UserDetails(map);
	     RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
	     RoleBasedAccessManagementPage.DeleteRoleBasedAccessManagementRecord(UserDetails);
	     Assert.assertEquals(RoleBasedAccessManagementPage.getSuccessMessage(), "Record Deleted Successfully");
	}
	
	@Test(priority=11,groups= {"Maker"},dependsOnMethods="DeleteRoleBasedAccessManagementRecord")
    public void VerifyAuditTrialReportForDelete() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
	     UserDetails UserDetails = new UserDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementDelete(UserDetails,"MakerDelete"));
    }
	
	@Test(priority=12,groups = { "Maker" },dependsOnMethods="DeleteRoleBasedAccessManagementRecord")
    public void VerifyAuditTrailDataForDeleteRoleBasedAccessManagementRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     UserDetails UserDetails = new UserDetails(map);
	     RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
	     RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
	     Assert.assertTrue(RoleBasedAccessManagementPage.verifyAuditTrailDelete(UserDetails, "MakerDelete", "New"), "Audit trail details failed");
    }
	
	@Test(groups = { "Maker" },priority=13)//,dependsOnMethods="VerifyAuditTrailDataForDeleteRoleBasedAccessManagementRecord")
    public void VerifySendForApprovalForDeleteNewRecord() throws Exception {
       	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
       	RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
       	RoleBasedAccessManagementPage.selectRecord();
       	RoleBasedAccessManagementPage.sendForAprroval("sent");
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	@Test(priority=14,groups = { "Checker" },dependsOnMethods="VerifySendForApprovalForDeleteNewRecord")
    public void ApproveforDeleteRoleBasedAccessManagementRecord() throws Exception{
	     RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
	     RoleBasedAccessManagementPage.clickonApprove("Approve Deleted");
	     Assert.assertTrue(RoleBasedAccessManagementPage.verifyMessage(),"Approve record assertion failed");
	     Assert.assertTrue(RoleBasedAccessManagementPage.verifyReviewAuditTrail("Approved","Approve Deleted"));
    }
	
	@Test(priority=15,groups = { "Checker" },dependsOnMethods = "ApproveforDeleteRoleBasedAccessManagementRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
	     UserDetails UserDetails = new UserDetails(map);
	     HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMReportsPage();
	     OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	     String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	     Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	     ReportDetails reportDetails= new ReportDetails(map1);
	     ocmReportsPage.showReport(reportDetails);
	     Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementDelete(UserDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
	@AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("RoleBasedAccessManagementDeleteTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}