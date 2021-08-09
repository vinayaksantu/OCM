package com.tetherfi.test;

import java.io.IOException;
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
import com.tetherfi.model.user.UserOnBoardingDetails;
import com.tetherfi.pages.UserOnBoardingPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class UserOnBoardingTeamLevelUserDeleteTest {

	protected WebDriver driver;
	@BeforeMethod
    public void NavigateToUserOnBoardingPage(Method method) throws Exception {
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
		loginPage.overrideSecurityConcern();/*UsedFor https withaddvanced btn*/
        if(map.get("LoginType").equals("Custom")){
            Assert.assertTrue(loginPage.isLoginPageDisplayed(),"Login page not loaded");
            loginPage.login(map.get("Username"),map.get("Password"),map.get("EmailId"));
            Thread.sleep(5000);
        }
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToUserOnBoardingPage();
        UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
        Assert.assertTrue(userOnBoardingPage.isUserOnBoardingPageDisplayed(), "NavigateToNewUserOnBoardingPage Page assertion failed");
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	 
    @Test(groups = { "Maker" },priority=1,description="To Verify DeleteRecord to Revert ")
    public void DeleteRevertUserOnBoardingRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(1);
        UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
        UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
        userOnBoardingPage.deleteUserOnBoardingRecord(UserOnBoardingDetails.getLanID(), UserOnBoardingDetails.getDeleteReason());
	    Assert.assertEquals(userOnBoardingPage.getSuccessMessage(), "Record Deleted Successfully");
    }     
    
    @Test(groups = { "Maker" },priority=2,dependsOnMethods="DeleteRevertUserOnBoardingRecord",description="To VerifyRevertForDeleteRecord ")
    public void VerifyRevertForDeleteRecord() throws Exception {
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
    	userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
    	userOnBoardingPage.selectRecord();
    	userOnBoardingPage.Revert("revert");
        Assert.assertTrue(userOnBoardingPage.verifyStatus("Reverted"),"approval status details failed");
    }
    
    @Test(groups= {"Maker"},priority=3,dependsOnMethods="VerifyRevertForDeleteRecord",description="To Verify AuditTrialReport for RevertDelete ")
    public void VerifyAuditTrialReportForRevertDelete() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(1);	
        UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingDelete(UserOnBoardingDetails,"MakerReverted"));
    }
   
    @Test(groups = { "Maker" },priority=4,description="To Verify delete Record to Reject ")
    public void RejectDeleteRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(1);
	     UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails (map);
	     UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
	     userOnBoardingPage.deleteUserOnBoardingRecord(UserOnBoardingDetails.getLanID(), UserOnBoardingDetails.getDeleteReason());
        Assert.assertEquals(userOnBoardingPage.getSuccessMessage(), "Record Deleted Successfully");
     }
    
    @Test(groups = { "Maker" },priority=5,dependsOnMethods="RejectDeleteRecord",description="To VerifySendForApprovalForDeleted NewRecord ")
    public void VerifySendForApprovalForDeleteNewRecord() throws Exception {
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
    	userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
    	userOnBoardingPage.selectRecord();
    	userOnBoardingPage.sendForAprroval("sent");
        Assert.assertTrue(userOnBoardingPage.verifyStatus("Approval Pending"),"approval status details failed");
    }
    
    @Test(priority=6,groups = { "Checker" },dependsOnMethods="VerifySendForApprovalForDeleteNewRecord",description="to Verify RejectforDeleteUserOnBoardingRecord ")
    public void RejectforDeleteUserOnBoardingRecord() throws Exception{
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
    	userOnBoardingPage.clickonReject("Reject Deleted");
        Assert.assertFalse(userOnBoardingPage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(userOnBoardingPage.verifyReviewAuditTrail("Rejected","Reject Deleted"));
    }
        
    @Test(priority=7,groups = { "Checker" },dependsOnMethods = "RejectforDeleteUserOnBoardingRecord",description="To VerifyAuditTrailReport forReject ")
    public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(1);
	    UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails (map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingDelete(UserOnBoardingDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Maker"},priority=8,description="To Verify DeleteUserOnBoardingRecord ")
	public void DeleteUserOnBoardingRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(1);
		 UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails (map);
		 UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
	     userOnBoardingPage.deleteUserOnBoardingRecord(UserOnBoardingDetails.getLanID(), UserOnBoardingDetails.getDeleteReason());
	     Assert.assertEquals(userOnBoardingPage.getSuccessMessage(), "Record Deleted Successfully");
	}
    
    @Test(priority=9,groups= {"Maker"},dependsOnMethods="DeleteUserOnBoardingRecord",description="To  VerifyAuditTrialReportForDelete")
    public void VerifyAuditTrialReportForDelete() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(1);	
        UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails (map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingDelete(UserOnBoardingDetails,"MakerDelete"));
    }
    
    @Test(priority=10,groups = { "Maker" },dependsOnMethods="DeleteUserOnBoardingRecord",description="To VerifyAuditTrailDataForDeleteUserOnBoardingRecord ")
    public void VerifyAuditTrailDataForDeleteUserOnBoardingRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(1);
		 UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails (map);
		 UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		 userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		 Assert.assertTrue(userOnBoardingPage.verifyAuditTrailDelete(UserOnBoardingDetails, "MakerDelete", "New"), "Audit trail details failed");
    }
    
    @Test(groups = { "Maker" },priority=11,dependsOnMethods="VerifyAuditTrailDataForDeleteUserOnBoardingRecord",description="To VerifySendForApprovalForDeleteRecord ")
    public void VerifySendForApprovalForDeleteRecord() throws Exception {
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
    	userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
    	userOnBoardingPage.selectRecord();
    	userOnBoardingPage.sendForAprroval("sent");
        Assert.assertTrue(userOnBoardingPage.verifyStatus("Approval Pending"),"approval status details failed");
    }
    
	@Test(priority=12,groups = { "Maker" },dependsOnMethods = "VerifySendForApprovalForDeleteRecord",description="To VerifyAuditTrailReportForSendForApprove  ")
    public void VerifyAuditTrailReportForSendForApprove() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(1);
		 UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails (map);
	     HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMReportsPage();
	     OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	     String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	     Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	     ReportDetails reportDetails= new ReportDetails(map1);
	     ocmReportsPage.showReport(reportDetails);
	     Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingDelete(UserOnBoardingDetails, "MakerSendToApproval"),"Audit Trail report assertion failed");
    }
    
    
	@Test(priority=13,groups = { "Checker" },dependsOnMethods="VerifyAuditTrailReportForSendForApprove",description="To Verify ApproveforDeleteUserOnBoardingRecord ")
    public void ApproveforDeleteUserOnBoardingRecord() throws Exception{
		 UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		 userOnBoardingPage.clickonApprove("Approve Deleted");
	     Assert.assertTrue(userOnBoardingPage.verifyMessage(),"Approve record assertion failed");
	     Assert.assertTrue(userOnBoardingPage.verifyReviewAuditTrail("Approved","Approve Deleted"));
    }
	
	@Test(priority=14,groups = { "Checker" },dependsOnMethods = "ApproveforDeleteUserOnBoardingRecord",description="To VerifyAuditTrailReportForApprove")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(1);
		 UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
	     HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMReportsPage();
	     OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	     String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	     Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	     ReportDetails reportDetails= new ReportDetails(map1);
	     ocmReportsPage.showReport(reportDetails);
	     Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingDelete(UserOnBoardingDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
	
	
	@AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("UserOnBoardingTeamLevelUserDeleteTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}
