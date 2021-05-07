package com.tetherfi.test.email;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.email.EmailTemplatesDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.EmailPage;
import com.tetherfi.pages.EmailTemplatesPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.EmailTemplatesPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class EmailTemplateDelete {
	protected WebDriver driver;
	@BeforeMethod
	public void NavigateToEmailTemplatesPage(Method method) throws Exception {
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
		LoginPage loginPage=PageFactory.createPageInstance(driver,LoginPage.class);
        loginPage.overrideSecurityConcern();
		if(map.get("LoginType").equals("Custom")){
			Assert.assertTrue(loginPage.isLoginPageDisplayed(),"Login page not loaded");
			loginPage.login(map.get("Username"),map.get("Password"),map.get("EmailId"));
			Thread.sleep(5000);
		}
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
		ocmHomePage.navigateToTab("Email");
		EmailPage EmailPage = PageFactory.createPageInstance(driver,EmailPage.class);
		Assert.assertTrue(EmailPage.isEmailPageDisplayed(),"Email page assertion failed");
		EmailPage.navigateToEmailTemplatesPage();
		EmailTemplatesPage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatesPage.class);
		Assert.assertTrue(EmailTemplatePage.isEmailTemplatesPageDisplayed(),"Email Templates page assertion failed");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@Test(groups= {"Maker"}, priority=1,description="Verify the Cancel button at delete record")
	public void DeleteCancelEmailTemplatesRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		EmailTemplatesDetails EmailTemplatesDetails = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		Assert.assertTrue(EmailTemplatesPage.DeleteCancel(EmailTemplatesDetails), "Delete Cancel assertion Failed");
	}

	@Test(groups = { "Maker" }, priority=2,description="Verify the reord deletion without delete reason")
	public void DeleteWithoutDeleteReasonRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		EmailTemplatesDetails EmailTemplatesDetails = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.DeleteRecordWithoutModifyReason(EmailTemplatesDetails);
		Assert.assertEquals(EmailTemplatesPage.getErrorMsg(),"Please enter the delete reason","Invalid Record Assertion failed");
	}

	@Test(groups = { "Maker" },priority=3,description="Delete record to perform revert action")
	public void DeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		EmailTemplatesDetails EmailTemplatesDetails = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.DeleteEmailTemplatesRecord(EmailTemplatesDetails);
		Assert.assertEquals(EmailTemplatesPage.getSuccessMessage(), "Record Deleted Successfully");
	}

	@Test(groups = { "Maker" },priority=4,dependsOnMethods="DeleteRecord",description="revert a deleted record and verify the status")
	public void VerifyRevertForDeleteRecord() throws Exception {
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
		EmailTemplatesPage.selectRecord();
		EmailTemplatesPage.Revert("revert");
		Assert.assertTrue(EmailTemplatesPage.verifyStatus("Reverted"),"approval status details failed");
	}

	@Test(groups= {"Maker"},priority=5,dependsOnMethods="VerifyRevertForDeleteRecord",description="Verify the action in the audit trail report")
	public void VerifyAuditTrialReportForRevertDelete() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
		EmailTemplatesDetails EmailTemplatesDetails = new EmailTemplatesDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyEmailTemplatesDelete(EmailTemplatesDetails,"MakerReverted"));
	}

	@Test(groups = { "Maker" },priority=6,description="Delete a record to perform the Delete Rejection")
	public void RejectDeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		EmailTemplatesDetails EmailTemplatesDetails = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.DeleteEmailTemplatesRecord(EmailTemplatesDetails);
		Assert.assertEquals(EmailTemplatesPage.getSuccessMessage(), "Record Deleted Successfully");
	}

	@Test(groups = { "Maker" },priority=7,dependsOnMethods="RejectDeleteRecord",description="Select a record in audi trail tab and send for approval")
	public void VerifySendForApprovalForRejectDeleteNewRecord() throws Exception {
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
		EmailTemplatesPage.selectRecord();
		EmailTemplatesPage.sendForAprroval("sent");
		Assert.assertTrue(EmailTemplatesPage.verifyStatus("Approval Pending"),"approal status details failed");
	}

	@Test(priority=8,groups = { "Checker" },dependsOnMethods="VerifySendForApprovalForRejectDeleteNewRecord",description="Perform reject action and verify the audit trial tab for a checker")
	public void RejectforDeleteEmailTemplatesRecord() throws Exception{
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.clickonReject("Reject Deleted");
		Assert.assertFalse(EmailTemplatesPage.verifyMessage(),"Reject record assertion failed");
		Assert.assertTrue(EmailTemplatesPage.verifyReviewAuditTrail("Rejected","Reject Deleted"));
	}

	@Test(priority=9,groups = { "Checker" },dependsOnMethods = "RejectforDeleteEmailTemplatesRecord",description="verify the action in audit trail report")
	public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
		EmailTemplatesDetails EmailTemplatesDetails = new EmailTemplatesDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyEmailTemplatesDelete(EmailTemplatesDetails, "CheckerReject"),"Audit Trail report assertion failed");
	}

	@Test(groups= {"Maker"},priority=10,description="Delete a record to perform the approval action")
	public void DeleteEmailTemplatesRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		EmailTemplatesDetails EmailTemplatesDetails = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.DeleteEmailTemplatesRecord(EmailTemplatesDetails);
		Assert.assertEquals(EmailTemplatesPage.getSuccessMessage(), "Record Deleted Successfully");
	}

	@Test(priority=11,groups= {"Maker"},dependsOnMethods="DeleteEmailTemplatesRecord",description="Verify the delete action in audit trail Report")
	public void VerifyAuditTrialReportForDelete() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
		EmailTemplatesDetails EmailTemplatesDetails = new EmailTemplatesDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyEmailTemplatesDelete(EmailTemplatesDetails,"MakerDelete"));
	}

	@Test(priority=12,groups = { "Maker" },dependsOnMethods="DeleteEmailTemplatesRecord",description="Verify the delete action in audit trial tab")
	public void VerifyAuditTrailDataForDeleteEmailTemplatesRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		EmailTemplatesDetails EmailTemplatesDetails = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
		Assert.assertTrue(EmailTemplatesPage.verifyAuditTrailDelete(EmailTemplatesDetails, "MakerDelete", "New"), "Audit trail details failed");
	}

	@Test(groups = { "Maker" },priority=13,description="Verify the status of the recod in audit trail tab")
	public void VerifySendForApprovalForDeleteNewRecord() throws Exception {
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
		EmailTemplatesPage.selectRecord();
		EmailTemplatesPage.sendForAprroval("sent");
		Assert.assertTrue(EmailTemplatesPage.verifyStatus("Approval Pending"),"approal status details failed");
	}

	@Test(priority=14,groups = { "Checker" },dependsOnMethods="VerifySendForApprovalForDeleteNewRecord",description="Perform the approve action and verify the audit trail tab for checker role")
	public void ApproveforDeleteEmailTemplatesRecord() throws Exception{
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.clickonApprove("Approve Deleted");
		Assert.assertTrue(EmailTemplatesPage.verifyMessage(),"Approve record assertion failed");
		Assert.assertTrue(EmailTemplatesPage.verifyReviewAuditTrail("Approved","Approve Deleted"));
	}

	@Test(priority=15,groups = { "Checker" },dependsOnMethods = "ApproveforDeleteEmailTemplatesRecord",description="Verify the action in audit trial report")
	public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
		EmailTemplatesDetails EmailTemplatesDetails = new EmailTemplatesDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyEmailTemplatesDelete(EmailTemplatesDetails, "CheckerApprove"),"Audit Trail report assertion failed");
	}

	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("EmailTemplatesDeleteTest",method.getName());
		driver.navigate().refresh();
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.userLogout();
		driver.close();
		System.out.println("Completed Executing : "+method.getName());
	}
}