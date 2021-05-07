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
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.EmailPage;
import com.tetherfi.pages.EmailTemplatesPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class EmailTemplatesCreate {

	protected WebDriver driver;
	@BeforeMethod
	public void NavigateToNewEmailTemplatesPage(Method method) throws Exception {
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

	@Test(groups= {"Maker"},priority=1,description="Verify the cancel button at add record")
	public void VerifyAddCancelButton() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		Assert.assertTrue(EmailTemplatesPage.addCancelButton(detailspage), "Add cancel button assertion failed");
	}

	@Test(groups = { "Maker" },priority=2,description="Add an empty record")
	public void VerifyAddforEmptyRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.addEmptyRecord(detailspage);
		Assert.assertEquals(EmailTemplatesPage.getErrorMsg(),"Please provide input for Template Name, Subject, Enabled, Type");
	}  
	
	@Test(groups = { "Maker" },priority=3,description="Add record without tempate name")
	public void VerifyAddRecordWithoutTemplateName() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.addRecordwithoutTemplateName(detailspage);
		Assert.assertEquals(EmailTemplatesPage.getErrorMsg(),"Please provide input for Template Name");
	}  
	
	@Test(groups = { "Maker" },priority=4,description="Add record without subject")
	public void VerifyAddRecordWithoutsubject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.addRecordwithoutSubject(detailspage);
		Assert.assertEquals(EmailTemplatesPage.getErrorMsg(),"Please provide input for Subject");
	}  
	
	@Test(groups = { "Maker" },priority=5,description="Add record without enabled Field")
	public void VerifyAddRecordWithoutEnabledField() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.addRecordwithoutEnabledfield(detailspage);
		Assert.assertEquals(EmailTemplatesPage.getErrorMsg(),"Please provide input for Enabled");
	}
	
	@Test(groups = { "Maker" },priority=6,description="Add record without Type Field")
	public void VerifyAddRecordWithoutTypeField() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.addRecordwithoutTypefield(detailspage);
		Assert.assertEquals(EmailTemplatesPage.getErrorMsg(),"Please provide input for Type");
	}
	@Test(groups = { "Maker" },priority=7,description="Add record only with template name")
	public void VerifyAddRecordonlywithtemplatename() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.addRecordTemplateName(detailspage);
		Assert.assertEquals(EmailTemplatesPage.getErrorMsg(),"Please provide input for Subject, Enabled, Type");
	}
		
	@Test(groups = { "Maker" },priority=8,description="Add new email templates record")
	public void AddRevertRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.addRecord(detailspage);
		Assert.assertEquals(EmailTemplatesPage.getSuccessMessage(), "Record Created Successfully");
	}

	@Test(groups = { "Maker" },priority=9,dependsOnMethods="AddRevertRecord",description="Add a record to verify add revert")
	public void VerifyRevertForAddNewRecord() throws Exception {
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
		EmailTemplatesPage.selectRecord();
		EmailTemplatesPage.Revert("revert");
		Assert.assertTrue(EmailTemplatesPage.verifyStatus("Reverted"),"approal status details failed");
	}

	@Test(groups= {"Maker"},priority=10,dependsOnMethods="VerifyRevertForAddNewRecord",description="verify the approved data section to revert an added record")
	public void VerifyApprovedDataSectionWithoutApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		Assert.assertTrue(EmailTemplatesPage.verifyApprovedSectionData(detailspage));
	}

	@Test(groups = { "Maker" },priority=11,dependsOnMethods = "VerifyApprovedDataSectionWithoutApproval",description="verify the Audit trial report")
	public void VerifyAuditTrailReportForRevert() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyEmailTemplatesCreate(detailspage, "MakerReverted"),"Audit Trail report assertion failed");
	}

	@Test(groups = { "Maker" },priority=12,description="Create a record  for reject functionaity")
	public void AddRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.addRecord(detailspage);
		Assert.assertEquals(EmailTemplatesPage.getSuccessMessage(), "Record Created Successfully");
	}

	@Test(groups = { "Maker" },priority=13,dependsOnMethods="AddRejectRecord",description="Verify Send for approval")
	public void VerifySendForApprovalForAddNewRecord() throws Exception {
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
		EmailTemplatesPage.selectRecord();
		EmailTemplatesPage.sendForAprroval("sent");
		Assert.assertTrue(EmailTemplatesPage.verifyStatus("Approval Pending"),"approval status details failed");
	}

	@Test(groups = { "Checker" },priority=14,dependsOnMethods="VerifySendForApprovalForAddNewRecord",description="Verify the rejected record in audit trail")
	public void RejectforAddNewNewEmailTemplatesRecord() throws Exception{
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.clickonReject("Reject Created");
		Assert.assertFalse(EmailTemplatesPage.verifyMessage(),"Reject record assertion failed");
		Assert.assertTrue(EmailTemplatesPage.verifyReviewAuditTrail("Rejected","Reject Created"));
	}

	@Test(groups = { "Checker" },priority=15,dependsOnMethods = "RejectforAddNewNewEmailTemplatesRecord",description="Verify the reject action in checker module")
	public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyEmailTemplatesCreate(detailspage, "CheckerReject"),"Audit Trail report assertion failed");
	}

	@Test(groups= {"Maker"},priority=16,dependsOnMethods="VerifyAuditTrailReportForReject",description="verify the approved section for rejected record")
	public void VerifyRecordAfterRejection() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		Assert.assertTrue(EmailTemplatesPage.verifyApprovedSectionData(detailspage));
	}

	@Test(groups = { "Maker" },priority=17,description="Create a record")
	public void AddNewEmailTemplatesRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.addRecord(detailspage);
		Assert.assertEquals(EmailTemplatesPage.getSuccessMessage(), "Record Created Successfully");
	}

	@Test(groups = { "Maker" },priority=18,dependsOnMethods = "AddNewEmailTemplatesRecord")
	public void VerifyAuditTrailReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyEmailTemplatesCreate(detailspage, "MakerCreate"),"Audit Trail report assertion failed");
	}

	@Test(groups = { "Maker" },priority=19,dependsOnMethods="AddNewEmailTemplatesRecord",description="Verify Audit trial for create")
	public void VerifyAuditTrailDataForAddNewEmailTemplatesRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
		Assert.assertTrue(EmailTemplatesPage.verifyAuditTrail(detailspage, "MakerCreate", "New"), "Audit trail details failed");
	}

	@Test(groups = { "Maker" },priority=20)
	public void VerifySendForApprovalForAddNewEmailTemplatesRecord() throws Exception {
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
		EmailTemplatesPage.selectRecord();
		EmailTemplatesPage.sendForAprroval("sent");
		Assert.assertTrue(EmailTemplatesPage.verifyStatus("Approval Pending"),"approal status details failed");
	}

	@Test(groups = { "Maker" },priority=21,dependsOnMethods = "VerifySendForApprovalForAddNewEmailTemplatesRecord",description="verify the Audit trail report for record creation")
	public void VerifyAuditTrailReportForSendForApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyEmailTemplatesCreate(detailspage, "MakerSendToApproval"),"Audit Trail report assertion failed");
	}

	@Test(groups = { "Checker" },priority=22,description="Verify Audit trial tab for approved record")
	public void ApproveforAddNewEmailTemplatesRecord() throws Exception{
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.clickonApprove("Approve Create");
		Assert.assertTrue(EmailTemplatesPage.verifyMessage());
		Assert.assertTrue(EmailTemplatesPage.verifyReviewAuditTrail("Approved","Approve Create"));
	}

	@Test(groups = { "Checker" },priority=23,dependsOnMethods = "ApproveforAddNewEmailTemplatesRecord",description="Verify the Audit trial report for approved record")
	public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyEmailTemplatesCreate(detailspage, "CheckerApprove"),"Audit Trail report assertion failed");
	}

	@Test(groups= {"Checker"},priority=24)//,dependsOnMethods="ApproveforAddNewEmailTemplatesRecord",description="Verify the approved data section after approval")
	public void VerifyRecordApprovedDataSectionafterApproval()throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		Assert.assertTrue(EmailTemplatesPage.verifyApprovedSectionDataafterapproval(detailspage));
	}

	@Test(groups = { "Maker" },priority=25,dependsOnMethods="AddNewEmailTemplatesRecord",description="Verify duplicate record creation")
	public void AddDuplicateRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.addRecord(detailspage);
		Assert.assertEquals(EmailTemplatesPage.getErrorMsg(),"Duplicate Template Name","Duplicate assetion failed");
	}
	@Test(groups = { "Maker" },priority=26,description="Add record only with Subject")
	public void VerifyAddRecordonlywithsubject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.addRecordwithSubjectOnly(detailspage);
		Assert.assertEquals(EmailTemplatesPage.getErrorMsg(),"Please provide input for Template Name, Enabled, Type");
	}
	@Test(groups = { "Maker" },priority=27,description="Add record only with Enabled field")
	public void VerifyAddRecordonlywithEnabled() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.addRecordwithEnableOnly(detailspage);
		Assert.assertEquals(EmailTemplatesPage.getErrorMsg(),"Please provide input for Template Name, Subject, Type");
	}
	@Test(groups = { "Maker" },priority=28,description="Add record only with type field")
	public void VerifyAddRecordonlywithType() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.addRecordwithTypeOnly(detailspage);
		Assert.assertEquals(EmailTemplatesPage.getErrorMsg(),"Please provide input for Template Name, Subject, Enabled");
	}
	

	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("EmailTemplatesCreateTest",method.getName());
		driver.navigate().refresh();
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.userLogout();
		driver.close();
		System.out.println("Completed Executing : "+method.getName());
	}

}
