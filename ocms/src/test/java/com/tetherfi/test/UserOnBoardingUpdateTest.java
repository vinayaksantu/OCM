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

public class UserOnBoardingUpdateTest {

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
		try{driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}catch (TimeoutException e){e.printStackTrace();driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}
		if(map.get("LoginType").equals("Custom")){
			LoginPage loginPage=PageFactory.createPageInstance(driver,LoginPage.class);
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
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}


	/*@Test(groups= {"Maker"},priority=1,description="Edited Record to Verify Revert")
	public void EditRevertUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.editUserOnBoardingRecord(UserOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.verifySuccessMessage(), "Record Updated Successfully");
	}

	@Test(groups = { "Maker" },priority=2,dependsOnMethods="EditRevertUserOnBoardingRecord",description="To Verify Revert for Edited Record ")
	public void VerifyRevertForEditRecord() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectRecord();
		userOnBoardingPage.Revert("revert");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Reverted"),"approval status details failed");
	}

	@Test(groups= {"Maker"},priority=3,dependsOnMethods="VerifyRevertForEditRecord",description="To Verify AuditTrial Report for RevertUpdate ")
	public void VerifyAuditTrialReportForRevertUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingUpdate(UserOnBoardingDetails,"MakerReverted"));
	}

	@Test(groups= {"Maker"},priority=4,description="Edited Record to Verify Reject ")
	public void EditRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.editUserOnBoardingRecord(UserOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.verifySuccessMessage(), "Record Updated Successfully");
	}

	@Test(groups = { "Maker"} ,priority=5,dependsOnMethods="EditRejectRecord",description="To VerifySendForApprovalForEditRejectRecord ")
	public void VerifySendForApprovalForEditRejectRecord() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectRecord();
		userOnBoardingPage.sendForAprroval("sent");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Approval Pending"),"approval status details failed");
	}

	@Test(groups = { "Checker" },priority=6,dependsOnMethods="VerifySendForApprovalForEditRejectRecord",description="To Verify RejectforEditUserOnBoardingRecord ")
	public void RejectforEditUserOnBoardingRecord() throws Exception{
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.clickonReject("Reject Updated");
		Assert.assertFalse(userOnBoardingPage.verifyMessage(),"Reject record assertion failed");
		Assert.assertTrue(userOnBoardingPage.verifyReviewAuditTrail("Rejected","Reject Updated"));
	}

	@Test(groups = { "Checker" },priority=7,dependsOnMethods = "RejectforEditUserOnBoardingRecord",description="To Verify AuditTrail Report for Reject ")
	public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingUpdate(UserOnBoardingDetails, "CheckerReject"),"Audit Trail report assertion failed");
	}*/

	@Test(groups= {"Maker"},priority=8,description="To Verify Edit UserOnBoarding Record ")
	public void EditUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.editUserOnBoardingRecord(UserOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.verifySuccessMessage(), "Record Updated Successfully");
	}

	@Test(groups = { "Maker" },priority=9,dependsOnMethods="EditUserOnBoardingRecord",description="To Verify AuditTrailData for Edited UserOnBoardingRecord ")
	public void VerifyAuditTrailDataForEditUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		Assert.assertTrue(userOnBoardingPage.verifyAuditTrailUpdate(UserOnBoardingDetails, "MakerUpdate", "New"), "Audit trail details failed");
	}

	@Test(groups = { "Maker" },priority=10,dependsOnMethods = "EditUserOnBoardingRecord",description="To Verify AuditTrailReportForEdit ")
	public void VerifyAuditTrailReportForEdit() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingUpdate(UserOnBoardingDetails,"MakerUpdate"),"Audit Trail report assertion failed");
	}

	@Test(groups = { "Maker" },priority=11,dependsOnMethods="EditUserOnBoardingRecord",description="To Verify VerifySendForApprovalForEditRejectRecordforApprove")
	public void VerifySendForApprovalForEditedRecordforApprove() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectRecord();
		userOnBoardingPage.sendForAprroval("sent");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Approval Pending"),"approal status details failed");
	}

	@Test(groups= {"Maker"},priority=12,dependsOnMethods="VerifySendForApprovalForEditedRecordforApprove")
	public void VerifyAuditTrialReportForSendForApprovalUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingUpdate(UserOnBoardingDetails,"MakerSendToApproval"));
	}

	@Test(groups = { "Checker" },priority=13,dependsOnMethods="VerifySendForApprovalForEditedRecordforApprove",description="To Verify ApproveforEditedUserOnBoardingRecord")
	public void ApproveforEditedUserOnBoardingRecord() throws Exception{
		UserOnBoardingPage userOnBoardingPage=PageFactory.createPageInstance(driver,UserOnBoardingPage.class);
		userOnBoardingPage.clickonApprove("Approve Edit");
		Assert.assertTrue(userOnBoardingPage.verifyMessage(),"Approve record assertion failed");
		Assert.assertTrue(userOnBoardingPage.verifyReviewAuditTrail("Approved","Approve Edit"));
	}

	@Test(groups = { "Checker" },priority=14,dependsOnMethods = "ApproveforEditedUserOnBoardingRecord",description=" To VerifyAuditTrailReportForApprove ")
	public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);		    
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingUpdate(UserOnBoardingDetails, "CheckerApprove"),"Audit Trail report assertion failed");
	}

	//@Test(groups = { "Maker" },priority=15,description="To Verify EditRecordWithoutModifyReason ")
	public void EditRecordWithoutModifyReason() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage=PageFactory.createPageInstance(driver,UserOnBoardingPage.class);
		userOnBoardingPage.EditRecordWithoutModifyReason(UserOnBoardingDetails);
		Assert.assertFalse(userOnBoardingPage.getErrorMsg(),"Invalid Record Assertion failed");
	}

	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen( "UserOnBoardingUpdate",method.getName());
		driver.navigate().refresh();
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.userLogout();
		driver.close();
		System.out.println("Completed Executing : "+method.getName());
	}



}
