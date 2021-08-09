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

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.user.UserDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.RoleBasedAccessManagementPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class RoleBasedAccessManagementUpdateTest {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateRoleBasedAccessManagementPage(Method method) throws Exception {
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
            map= new  ExcelReader(filePath,"Login").getTestData().get(0);
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
        RoleBasedAccessManagementPage roleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        Assert.assertTrue(roleBasedAccessManagementPage.isRoleBasedAccessManagementPageDisplayed(), "Branch Management page assertion failed");
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	@Test(groups= {"Maker"}, priority=1)
	public void EditCancelRoleBasedAccessManagementRecord() throws Exception {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        Assert.assertTrue(RoleBasedAccessManagementPage.EditCancel(userDetails), "Edit Cancel assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=2)
	public void EditRevertRoleBasedAccessManagementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.editRoleBasedAccessManagementRecord(userDetails);
        Assert.assertEquals(RoleBasedAccessManagementPage.getSuccessMessage(), "Record Updated Successfully");
	}
	
	@Test(groups = { "Maker" },priority=3,dependsOnMethods="EditRevertRoleBasedAccessManagementRecord")
    public void VerifyRevertForEditRecord() throws Exception {
       	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
       	RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
       	RoleBasedAccessManagementPage.selectRecord();
       	RoleBasedAccessManagementPage.Revert("revert");
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyStatus("Reverted"),"approval status details failed");
    }
	
	@Test(groups= {"Maker"},priority=4,dependsOnMethods="VerifyRevertForEditRecord")
    public void VerifyAuditTrialReportForRevertUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        UserDetails userDetails=new UserDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails=new  ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementUpdate(userDetails,"MakerReverted"));
    }
	
	@Test(groups= {"Maker"},priority=5)
	public void EditRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.editRoleBasedAccessManagementRecord(userDetails);
        Assert.assertEquals(RoleBasedAccessManagementPage.getSuccessMessage(), "Record Updated Successfully");
	}
	
	@Test(groups = { "Maker" },priority=6,dependsOnMethods="EditRejectRecord")
    public void VerifySendForApprovalForEditRejectRecord() throws Exception {
       	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
       	RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
       	RoleBasedAccessManagementPage.selectRecord();
       	RoleBasedAccessManagementPage.sendForAprroval("sent");
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }

	@Test(groups = { "Checker" },priority=7,dependsOnMethods="VerifySendForApprovalForEditRejectRecord")
    public void RejectforEditRoleBasedAccessManagementRecord() throws Exception{
        RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.clickonReject("Reject Updated");
        Assert.assertFalse(RoleBasedAccessManagementPage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyReviewAuditTrail("Rejected","Reject Updated"));
    }
    
    @Test(groups = { "Checker" },priority=8,dependsOnMethods = "RejectforEditRoleBasedAccessManagementRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementUpdate(userDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
	@Test(groups= {"Maker"},priority=9)
	public void EditRoleBasedAccessManagementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.editRoleBasedAccessManagementRecord(userDetails);
        Assert.assertEquals(RoleBasedAccessManagementPage.getSuccessMessage(), "Record Updated Successfully");
	}
	
	@Test(groups = { "Maker" },priority=10,dependsOnMethods="EditRoleBasedAccessManagementRecord")
    public void VerifyAuditTrailDataForEditRoleBasedAccessManagementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyAuditTrailUpdate(userDetails, "MakerUpdate", "New"), "Audit trail details failed");
    }
	
	@Test(groups= {"Maker"},priority=11,dependsOnMethods="EditRoleBasedAccessManagementRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        UserDetails userDetails=new UserDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementUpdate(userDetails,"MakerUpdate"));
    }
    
	@Test(groups = { "Maker" },priority=12)//,dependsOnMethods="VerifyAuditTrialReportForUpdate")
    public void VerifySendForApprovalForEditRecord() throws Exception {
       	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
       	RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
       	RoleBasedAccessManagementPage.selectRecord();
       	RoleBasedAccessManagementPage.sendForAprroval("sent");
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	@Test(groups= {"Maker"},priority=13)
    public void VerifyAuditTrialReportForSendForApprovalUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        UserDetails userDetails=new UserDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementUpdate(userDetails,"MakerSendToApproval"));
    }
    
    @Test(groups = { "Checker" },priority=14)
    public void ApproveforEditRoleBasedAccessManagementRecord() throws Exception{
        RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.clickonApprove("Approve Edited");
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyMessage());
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyReviewAuditTrail("Approved","Approve Edited"));
    }
	
	@Test(groups = { "Checker" },priority=15,dependsOnMethods = "ApproveforEditRoleBasedAccessManagementRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
	    Map<String, String> map =  new ExcelReader(filePath,"Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);	    
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementUpdate(userDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=16)
    public void EditRecordWithoutModifyReason() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.EditRecordWithoutModifyReason(userDetails);
        Assert.assertEquals(RoleBasedAccessManagementPage.getErrorMsg(),"Please enter the modify reason", "Invalid Assertion Failed");
    }
   
    @AfterMethod
	    public void afterEachMethod(Method method){
	        Screenshot screenshot= new Screenshot(driver);
	        screenshot.captureScreen("RoleBasedAccessManagementUpdateTest",method.getName());
	        driver.navigate().refresh();
	        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	        homePage.userLogout();
	        driver.close();
	        System.out.println("Completed Executing : "+method.getName());
	    }
}
