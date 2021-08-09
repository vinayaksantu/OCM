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
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.UserOnBoardingPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class UserOnBoardingCountryLevelUserCreateTest {

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

	/*@Test(groups= {"Maker"},priority=1,description="Add Record to Verify Revert ")
	public void VerifyAddRevertRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.addNewDifferentLevelUserOnBoardingRecordWithAllValidData(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.VerifyMessage(), "Record Created Successfully","Record Creation Assertion failed");
	}

	@Test(groups = { "Maker" },priority=2)//,dependsOnMethods="VerifyAddRevertRecord",description="To Verify Revert Functionality   ")
	public void VerifyRevertForAddNewRecord() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectRecord();
		userOnBoardingPage.Revert("revert");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Reverted"),"Approval Status Details Failed");
	}

	@Test(groups = { "Maker" },priority=3)//,dependsOnMethods = "VerifyRevertForAddNewRecord",description="To Verify AuditTrailReport for Revert Transaction ")
	public void VerifyAuditTrailReportForRevert() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingCreate(userOnBoardingDetails, "MakerReverted"),"Audit Trail report assertion failed");
	}

	@Test(groups = { "Maker" },priority=4)//,description="Add Record to Verify Reject ")
	public void AddRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.addNewDifferentLevelUserOnBoardingRecordWithAllValidData(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.VerifyMessage(), "Record Created Successfully","Record Creation Assertion failed");
	}   

	@Test(groups = { "Maker" },priority=5)//,dependsOnMethods="AddRejectRecord",description="To Verify Send Record for Approval")
	public void VerifySendForApprovalForAddNewUserOnBoardingRecord() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectRecord();
		userOnBoardingPage.sendForAprroval("sent");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Approval Pending"),"approval status details failed");
	}

	@Test(groups = { "Checker" },priority=6)//,dependsOnMethods="VerifySendForApprovalForAddNewUserOnBoardingRecord",description="To Verify Checker Reject Functionality")
	public void RejectForAddNewUserOnBoardingRecord() throws Exception{
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.clickonReject("Reject Created");
		Assert.assertFalse(userOnBoardingPage.verifyMessage(),"Reject record assertion failed");
		Assert.assertTrue(userOnBoardingPage.verifyReviewAuditTrail("Rejected","Reject Created"));
	}

	@Test(groups = { "Checker" },priority=7)//,dependsOnMethods = "RejectForAddNewUserOnBoardingRecord",description="To Verify Audit Trail Report for Reject")
	public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingCreate(userOnBoardingDetails, "CheckerReject"),"Audit Trail report assertion failed");
	}*/

	@Test(groups = { "Maker" },priority=8)//,description="To Verify Add Reord to Approve ")
	public void AddNewUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.addNewDifferentLevelUserOnBoardingRecordWithAllValidData(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.VerifyMessage(), "Record Created Successfully","Record Creation Assertion failed");
	}

	/*@Test(groups = { "Maker" },priority=9)//,dependsOnMethods = "AddNewUserOnBoardingRecord",description="To Verify AuditTrail Report for Record Create")
	public void VerifyAuditTrailReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingCreate(userOnBoardingDetails, "MakerCreate"),"Audit Trail report assertion failed");
	}

	@Test(groups = { "Maker" },priority=10)//,dependsOnMethods="AddNewUserOnBoardingRecord",description="To Verify AuditTrail Data for AddNewUserOnBoardingRecord ")
	public void VerifyAuditTrailDataForAddNewUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		Assert.assertTrue(userOnBoardingPage.verifyAuditTrail(userOnBoardingDetails, "MakerCreate", "New"), "Audit trail details failed");
	}*/

	@Test(groups = { "Maker" },priority=11)//,dependsOnMethods="VerifyAuditTrailDataForAddNewUserOnBoardingRecord",description="To Verify SendForApproval for AddNewUserOnBoarding ")
	public void VerifySendForApprovalForAddNewUserOnBoarding() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectRecord();
		userOnBoardingPage.sendForAprroval("sent");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Approval Pending"),"approval status details failed");
	}

	//@Test(groups = { "Maker" },priority=12)//,dependsOnMethods = "VerifySendForApprovalForAddNewUserOnBoarding",description="VerifyAuditTrail Report for SendForApproval ")
	public void VerifyAuditTrailReportForSendForApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingCreate(userOnBoardingDetails, "MakerSendToApproval"),"Audit Trail report assertion failed");
	}

	@Test(groups = { "Checker" },priority=13)//,dependsOnMethods="VerifyAuditTrailReportForSendForApproval",description="To Verify Checker ApproveforAddNewUserOnBoardingRecord")
	public void ApproveforAddNewUserOnBoardingRecord() throws Exception{
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.clickonApprove("Approve Create");
		Assert.assertTrue(userOnBoardingPage.verifyMessage());
		Assert.assertTrue(userOnBoardingPage.verifyReviewAuditTrail("Approved","Approve Create"));
	}


	/*@Test(groups = { "Checker" },priority=14)//,dependsOnMethods = "ApproveforAddNewUserOnBoardingRecord",description="To VerifyAuditTrailReportForApproved Record")
	public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingCreate(userOnBoardingDetails, "CheckerApprove"),"Audit Trail report assertion failed");
	}
	
	@Test(groups = { "Maker" },priority=15,description="To Verify Add Duplicate Record")
	public void AddDuplicateUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.addNewDifferentLevelUserOnBoardingRecordWithAllValidData(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.VerifyMessage(), "Record Creation Failed, Already Exist","Record Creation Assertion failed");
	}*/






	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("UserOnBoardingCountryLevelUserCreateTest",method.getName());
		driver.navigate().refresh();
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.userLogout();
		driver.close();
		System.out.println("Completed Executing : "+method.getName());
	}
}
