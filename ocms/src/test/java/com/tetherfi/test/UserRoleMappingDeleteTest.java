package com.tetherfi.test;
import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.user.UserRoleMappingDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.NewUserRoleMappingPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class UserRoleMappingDeleteTest {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateToNewUserRoleMappingPage(Method method) throws Exception {
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
        ocmHomePage.navigateToUserRoleMappingPage();
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        Assert.assertTrue(NewUserRoleMappingPage.isUserRoleMappingPageDisplayed(), "SMS Response Template page assertion failed");
    }
	
	@Test(groups= {"Maker"}, priority=1)
	public void DeleteCancelUserRoleMappingRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
	     Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	     NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
	     Assert.assertTrue(NewUserRoleMappingPage.DeleteCancel(UserRoleMappingDetails), "Delete Cancel assertion Failed");
	}
	
	@Test(groups = { "Maker" }, priority=2)
    public void DeleteWithoutModifyReasonRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	     NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
	     NewUserRoleMappingPage.DeleteRecordWithoutModifyReason(UserRoleMappingDetails);
	     Assert.assertFalse(NewUserRoleMappingPage.getErrorMsg(),"Invalid Record Assertion failed");
    }
	
	@Test(groups = { "Maker" },priority=3)
    public void DeleteRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	     NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
	     NewUserRoleMappingPage.DeleteUserRoleMappingRecord(UserRoleMappingDetails);
        Assert.assertEquals(NewUserRoleMappingPage.getSuccessMessage(), "Record deleted successfully");
     }
        
	@Test(groups = { "Maker" },dependsOnMethods="DeleteRecord")
    public void VerifyRevertForDeleteRecord() throws Exception {
       	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
       	NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
       	NewUserRoleMappingPage.selectRecord();
       	NewUserRoleMappingPage.Revert("revert");
        Assert.assertTrue(NewUserRoleMappingPage.verifyStatus("Reverted"),"approval status details failed");
    }
    
	@Test(groups= {"Maker"},dependsOnMethods="VerifyRevertForEditRecord")
    public void VerifyAuditTrialReportForRevertDelete() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingDelete(UserRoleMappingDetails,"MakerReverted"));
    }
	
	@Test(groups = { "Maker" })//,priority=3)
    public void RejectDeleteRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	     NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
	     NewUserRoleMappingPage.DeleteUserRoleMappingRecord(UserRoleMappingDetails);
        Assert.assertEquals(NewUserRoleMappingPage.getSuccessMessage(), "Record deleted successfully");
     }
	
	@Test(groups = { "Maker" })//,priority=11,dependsOnMethods="AddRejectRecord")
    public void VerifySendForApprovalForDeleteNewRecord() throws Exception {
       	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
       	NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
       	NewUserRoleMappingPage.selectRecord();
       	NewUserRoleMappingPage.sendForAprroval("sent");
        Assert.assertTrue(NewUserRoleMappingPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
		
    @Test(priority=5,groups = { "Checker" })//,dependsOnMethods="VerifyTaskCompleteActionForDeleteRecord")
    public void RejectforDeleteUserRoleMappingRecord() throws Exception{
	     NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
	     NewUserRoleMappingPage.clickonReject("Reject Deleted");
        Assert.assertFalse(NewUserRoleMappingPage.getErrorMsg(),"Reject record assertion failed");
        Assert.assertTrue(NewUserRoleMappingPage.verifyReviewAuditTrail("Rejected","Reject Deleted"));
    }
    
    @Test(priority=6,groups = { "Checker" })//,dependsOnMethods = "RejectforDeleteUserRoleMappingRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(1);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingDelete(UserRoleMappingDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
	
	@Test(groups= {"Maker"},priority=7)
	public void DeleteUserRoleMappingRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	     NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
	     NewUserRoleMappingPage.DeleteUserRoleMappingRecord(UserRoleMappingDetails);
	     Assert.assertEquals(NewUserRoleMappingPage.getSuccessMessage(), "Record deleted successfully");
	}
	
	@Test(priority=8,groups= {"Maker"},dependsOnMethods="DeleteUserRoleMappingRecord")
    public void VerifyAuditTrialReportForDelete() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
	     UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingDelete(UserRoleMappingDetails,"MakerDelete"));
    }
	
	@Test(priority=9,groups = { "Maker" },dependsOnMethods="DeleteUserRoleMappingRecord")
    public void VerifyAuditTrailDataForDeleteUserRoleMappingRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	     NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
	     NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
	     Assert.assertTrue(NewUserRoleMappingPage.verifyAuditTrailDelete(UserRoleMappingDetails, "MakerDelete", "New"), "Audit trail details failed");
    }

	
	@Test(priority=11,groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForDeleteUserRoleMappingRecord")
    public void ApproveforDeleteUserRoleMappingRecord() throws Exception{
	     NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
	     NewUserRoleMappingPage.clickonApprove("Approve Deleted");
	     Assert.assertEquals(NewUserRoleMappingPage.getSuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
	     Assert.assertTrue(NewUserRoleMappingPage.verifyReviewAuditTrail("Approved","Approve Deleted"));
    }
	
	@Test(priority=12,groups = { "Checker" },dependsOnMethods = "ApproveforDeleteUserRoleMappingRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
	     UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	     HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMReportsPage();
	     OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	     String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	     Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	     ReportDetails reportDetails= new ReportDetails(map1);
	     ocmReportsPage.showReport(reportDetails);
	     Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingDelete(UserRoleMappingDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
	@AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("UserRoleMappingDeleteTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}