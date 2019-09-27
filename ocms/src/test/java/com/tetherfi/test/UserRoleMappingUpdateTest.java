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

public class UserRoleMappingUpdateTest {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateNewUserRoleMappingPage(Method method) throws Exception {
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
        ocmHomePage.navigateToUserRoleMappingPage();
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        Assert.assertTrue(NewUserRoleMappingPage.isUserRoleMappingPageDisplayed(), "Branch Management page assertion failed");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	@Test(groups= {"Maker"}, priority=1)
	public void EditCancelUserRoleMappingRecord() throws Exception {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        Assert.assertTrue(NewUserRoleMappingPage.EditCancel(UserRoleMappingDetails), "Edit Cancel assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=2)
	public void EditRevertUserRoleMappingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.editUserRoleMappingRecord(UserRoleMappingDetails);
        Assert.assertEquals(NewUserRoleMappingPage.getSuccessMessage(), "Record Updated Successfully");
	}
	
	@Test(groups = { "Maker" },priority=3,dependsOnMethods="EditRevertUserRoleMappingRecord")
    public void VerifyRevertForEditRecord() throws Exception {
       	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
       	NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
       	NewUserRoleMappingPage.selectRecord();
       	NewUserRoleMappingPage.Revert("revert");
        Assert.assertTrue(NewUserRoleMappingPage.verifyStatus("Reverted"),"approval status details failed");
    }
	
	@Test(groups= {"Maker"},priority=4,dependsOnMethods="VerifyRevertForEditRecord")
    public void VerifyAuditTrialReportForRevertUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingUpdate(UserRoleMappingDetails,"MakerReverted"));
    }
	
	@Test(groups= {"Maker"},priority=5)
	public void EditRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.editUserRoleMappingRecord(UserRoleMappingDetails);
        Assert.assertEquals(NewUserRoleMappingPage.getSuccessMessage(), "Record Updated Successfully");
	}
	
	@Test(groups = { "Maker" },priority=6,dependsOnMethods="EditRejectRecord")
    public void VerifySendForApprovalForEditRejectRecord() throws Exception {
       	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
       	NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
       	NewUserRoleMappingPage.selectRecord();
       	NewUserRoleMappingPage.sendForAprroval("sent");
        Assert.assertTrue(NewUserRoleMappingPage.verifyStatus("Approval Pending"),"approal status details failed");
    }

	@Test(groups = { "Checker" },priority=7,dependsOnMethods="VerifySendForApprovalForEditRejectRecord")
    public void RejectforEditUserRoleMappingRecord() throws Exception{
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.clickonReject("Reject Updated");
        Assert.assertFalse(NewUserRoleMappingPage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(NewUserRoleMappingPage.verifyReviewAuditTrail("Rejected","Reject Updated"));
    }
    
    @Test(groups = { "Checker" },priority=8,dependsOnMethods = "RejectforEditUserRoleMappingRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingUpdate(UserRoleMappingDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
	@Test(groups= {"Maker"},priority=9)
	public void EditUserRoleMappingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.editUserRoleMappingRecord(UserRoleMappingDetails);
        Assert.assertEquals(NewUserRoleMappingPage.getSuccessMessage(), "Record Updated Successfully");
	}
	
	@Test(groups = { "Maker" },priority=10,dependsOnMethods="EditUserRoleMappingRecord")
    public void VerifyAuditTrailDataForEditUserRoleMappingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        Assert.assertTrue(NewUserRoleMappingPage.verifyAuditTrailUpdate(UserRoleMappingDetails, "MakerUpdate", "New"), "Audit trail details failed");
    }
	
	@Test(groups= {"Maker"},priority=11,dependsOnMethods="EditUserRoleMappingRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingUpdate(UserRoleMappingDetails,"MakerUpdate"));
    }
    
	@Test(groups = { "Maker" },priority=12)//,dependsOnMethods="VerifyAuditTrialReportForUpdate")
    public void VerifySendForApprovalForEditRecord() throws Exception {
       	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
       	NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
       	NewUserRoleMappingPage.selectRecord();
       	NewUserRoleMappingPage.sendForAprroval("sent");
        Assert.assertTrue(NewUserRoleMappingPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	@Test(groups= {"Maker"},priority=13)
    public void VerifyAuditTrialReportForSendForApprovalUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingUpdate(UserRoleMappingDetails,"MakerSendToApproval"));
    }
    
    @Test(groups = { "Checker" },priority=14)
    public void ApproveforEditUserRoleMappingRecord() throws Exception{
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.clickonApprove("Approve Edited");
        Assert.assertTrue(NewUserRoleMappingPage.verifyMessage());
        Assert.assertTrue(NewUserRoleMappingPage.verifyReviewAuditTrail("Approved","Approve Edited"));
    }
	
	@Test(groups = { "Checker" },priority=15,dependsOnMethods = "ApproveforEditUserRoleMappingRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);	    
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingUpdate(UserRoleMappingDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" })
    public void EditRecordWithoutModifyReason() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.EditRecordWithoutModifyReason(UserRoleMappingDetails);
        Assert.assertFalse(NewUserRoleMappingPage.getErrorMsg(),"Invalid Record Assertion failed");
    }
   
    @AfterMethod
	    public void afterEachMethod(Method method){
	        Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("UserRoleMappingUpdateTest",method.getName());
	        driver.navigate().refresh();
	        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	        homePage.userLogout();
	        driver.close();
	        System.out.println("Completed Executing : "+method.getName());
	    }
}