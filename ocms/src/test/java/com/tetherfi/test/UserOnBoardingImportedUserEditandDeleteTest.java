package com.tetherfi.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
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

public class UserOnBoardingImportedUserEditandDeleteTest {

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

	@Test(groups= {"Maker"},priority=1,description="Verify Import Valid UserDetails Channel Count and Features File to REVERT ")
	public void VerifyImportValidUserDetailsChannelCountandFeaturesFiletoREVERT() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(2);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import Valid UserDetails ChannelCount and Features File t oREVERT Assertion Failed");
	}

	@Test(groups = { "Maker" },priority=2)//,dependsOnMethods="VerifyImportValidUserDetailsChannelCountandFeaturesFiletoREVERT",description="To Verify Revert Imported Record")
	public void VerifyRevertImportedRecord() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectRecord();
		userOnBoardingPage.Revert("revert");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Reverted"),"Approval Status Details Failed");
	}

	@Test(groups= {"Maker"},priority=3,description="Verify Import Valid UserDetails ChannelCount and Features to REJECT")
	public void VerifyImportValidUserDetailsChannelCountandFeaturestoREJECT() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(2);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import Valid UserDetails ChannelCount and Features to REJECT Assertion Failed");
	}   

	@Test(groups = { "Maker" },priority=4,dependsOnMethods="VerifyImportValidUserDetailsChannelCountandFeaturestoREJECT",description="To Verify Send Record for Approval")
	public void VerifySendForApprovalForImportedUserOnBoardingRecord() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectRecord();
		userOnBoardingPage.sendForAprroval("sent");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Approval Pending"),"approval status details failed");
	}

	@Test(groups = { "Checker" },priority=5)//,dependsOnMethods="VerifySendForApprovalForImportedUserOnBoardingRecord",description="Reject Imported UserOnBoarding Record")
	public void RejectImportedUserOnBoardingRecord() throws Exception{
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.clickonReject("Reject Created");
		Assert.assertFalse(userOnBoardingPage.verifyMessage(),"Reject record assertion failed");
		Assert.assertTrue(userOnBoardingPage.verifyReviewAuditTrail("Rejected","Reject Created"));
	}

	@Test(groups = { "Maker" }, priority=6,description="Verify Import Valid UserDetails ChannelCount and Features To APPROVE")
	public void VerifyImportValidUserDetailsChannelCountandFeaturesToAPPROVE() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(2);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import Valid UserDetails ChannelCount and Features To APPROVE Assertion Failed");
	}
	
    @Test(groups = { "Maker" },priority=7,dependsOnMethods = "VerifyImportValidUserDetailsChannelCountandFeaturesToAPPROVE",description="To Verify AuditTrail Report for Record Create")//Bug:trxn should be MakerImport
	public void VerifyAuditTrailReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingCreate(userOnBoardingDetails, "MakerImport"),"Audit Trail report assertion failed");
	}
    
	@Test(groups = { "Maker" }, priority=8,dependsOnMethods="VerifyImportValidUserDetailsChannelCountandFeaturesToAPPROVE",description="To Verify AuditTrail Data for AddNewUserOnBoardingRecord ")
	public void VerifyAuditTrailDataForImportedUserOnBoardingRecord() throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		Assert.assertTrue(userOnBoardingPage.verifyAuditTrail(userOnBoardingDetails, "MakerImport", "New"), "Audit trail details failed");
	}

	@Test( groups = { "Maker" },priority=9,dependsOnMethods="VerifyAuditTrailDataForImportedUserOnBoardingRecord",description="To Verify SendForApproval for Imported UserOnBoarding ")
	public void VerifySendForApprovalForImportrdserOnBoarding() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectRecord();
		userOnBoardingPage.sendForAprroval("sent");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Approval Pending"),"approval status details failed");
	}
	
	@Test(groups = { "Checker" },priority=10,dependsOnMethods="VerifySendForApprovalForImportrdserOnBoarding",description="To Verify Checker Approve for Imported UserOnBoardingRecord")
	public void ApproveforImortedUserOnBoardingRecord() throws Exception{
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.clickonApprove("Approve Create");
		Assert.assertTrue(userOnBoardingPage.verifyMessage());
		Assert.assertTrue(userOnBoardingPage.verifyReviewAuditTrail("Approved","Approve Create"));
	}
	
	@Test(groups = { "Maker" },priority=11,description="To Verify Export to Excel button")
    public void ExportToExcelImportedRecord() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
        Assert.assertTrue(userOnBoardingPage.verifyExportToExcelForImportedRecord(UserOnBoardingDetails,filePath1));
    }
	
	@Test(groups = { "Maker" },priority=12,description="To Verify Exported data  ")
    public void VerifyExportToExcelData() throws Exception{	
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Onboarding.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
        Assert.assertTrue(userOnBoardingPage.verifyexportToExcelSheetforImportedRecord(UserOnBoardingDetails,maplist));	
    }

	@Test(groups = { "Maker" }, priority=13,description="Edited Record to Verify Revert")
	public void EditImportedUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.editUserOnBoardingRecord(UserOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.verifySuccessMessage(), "Record Updated Successfully");
	}

	@Test(groups = { "Maker" },priority=14)//,dependsOnMethods="EditUserOnBoardingRecord",description="To Verify AuditTrailData for Edited UserOnBoardingRecord ")
	public void VerifyAuditTrailDataForEditUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		Assert.assertTrue(userOnBoardingPage.verifyAuditTrailUpdate(UserOnBoardingDetails, "MakerUpdate", "New"), "Audit trail details failed");
	}

	@Test(groups = { "Maker" }, priority=15,dependsOnMethods="VerifyAuditTrailDataForEditUserOnBoardingRecord",description="To Verify VerifySendForApprovalForEditRejectRecordforApprove")
	public void VerifySendForApprovalForEditedRecordforApprove() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectRecord();
		userOnBoardingPage.sendForAprroval("sent");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Approval Pending"),"approal status details failed");
	}

	@Test(groups = { "Checker" },priority=16,dependsOnMethods="VerifySendForApprovalForEditedRecordforApprove",description="To Verify ApproveforEditedUserOnBoardingRecord")
	public void ApproveforEditedUserOnBoardingRecord() throws Exception{
		UserOnBoardingPage userOnBoardingPage=PageFactory.createPageInstance(driver,UserOnBoardingPage.class);
		userOnBoardingPage.clickonApprove("Approve Edit");
		Assert.assertTrue(userOnBoardingPage.verifyMessage(),"Approve record assertion failed");
		Assert.assertTrue(userOnBoardingPage.verifyReviewAuditTrail("Approved","Approve Edit"));
	}

	@Test(groups = { "Maker" }, priority=17,description="To Verify DeleteUserOnBoardingRecord ")
	public void DeleteUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(1);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails (map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.deleteUserOnBoardingRecord(UserOnBoardingDetails.getLanID(), UserOnBoardingDetails.getDeleteReason());
		Assert.assertEquals(userOnBoardingPage.getSuccessMessage(), "Record Deleted Successfully");
	}

	@Test(groups = { "Maker" },priority=18, dependsOnMethods="DeleteUserOnBoardingRecord",description="To VerifyAuditTrailDataForDeleteUserOnBoardingRecord ")
	public void VerifyAuditTrailDataForDeleteUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(1);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails (map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		Assert.assertTrue(userOnBoardingPage.verifyAuditTrailDelete(UserOnBoardingDetails, "MakerDelete", "New"), "Audit trail details failed");
	}

	@Test(groups = { "Maker" }, priority=19,dependsOnMethods="VerifyAuditTrailDataForDeleteUserOnBoardingRecord",description="To VerifySendForApprovalForDeleteRecord ")
	public void VerifySendForApprovalForDeleteRecord() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectRecord();
		userOnBoardingPage.sendForAprroval("sent");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Approval Pending"),"approval status details failed");
	}

	@Test(groups = { "Checker" },priority=20,dependsOnMethods="VerifySendForApprovalForDeleteRecord",description="To Verify ApproveforDeleteUserOnBoardingRecord ")
	public void ApproveforDeleteUserOnBoardingRecord() throws Exception{
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.clickonApprove("Approve Deleted");
		Assert.assertTrue(userOnBoardingPage.verifyMessage(),"Approve record assertion failed");
		Assert.assertTrue(userOnBoardingPage.verifyReviewAuditTrail("Approved","Approve Deleted"));
	}

	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("UserOnBoarrdingImportedUserEditandDeleteTest",method.getName());
		driver.navigate().refresh();
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.userLogout();
		driver.close();
		System.out.println("Completed Executing : "+method.getName());
	}
}
