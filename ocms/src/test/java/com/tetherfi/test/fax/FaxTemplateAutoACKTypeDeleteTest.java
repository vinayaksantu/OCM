package com.tetherfi.test.fax;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.fax.FaxTemplateDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.FaxTemplatePage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class FaxTemplateAutoACKTypeDeleteTest {

protected WebDriver driver;
	
	@BeforeMethod
	public void NavigateToFaxTemplatePage(Method method) throws Exception {
		try {
			PageFactory.reset();
			BrowserFactory browserFactory=new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		}catch(Exception e) {
			PageFactory.reset();
			driver.close();
			e.printStackTrace();
		}
		System.out.println("Started Executing :"+method.getName());
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		Test t=method.getAnnotation(Test.class);
		Map<String, String>map;
		if(t.groups()[0].equalsIgnoreCase("Checker"))
			map=new ExcelReader(filePath,"Login").getTestData().get(1);
		else
			map=new ExcelReader(filePath,"Login").getTestData().get(0);
		try {driver.get("https://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}catch (TimeoutException e){e.printStackTrace();driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}
		LoginPage loginPage=PageFactory.createPageInstance(driver,LoginPage.class);
        loginPage.overrideSecurityConcern();
		if(map.get("LoginType").equals("Custom")) {
            Assert.assertTrue(loginPage.isLoginPageDisplayed(),"Login page not loaded");
			loginPage.login(map.get("Username"),map.get("Password"),map.get("EmailId"));
            Thread.sleep(5000);
        }
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("FAX");
        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
        Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
        faxPage.navigateToFaxTemplatePage();
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        Assert.assertTrue(faxTemplatePage.isFaxTemplatePageDisplayed(), "FAX page assertion failed");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	 }
	
	@Test(groups= {"Maker"},priority=1)
	public void VerifyDeleteCancelAutoACKTypeFaxTemplateRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String, String> map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		Assert.assertTrue(faxTemplatePage.DeleteCancel(faxTemplateDetails), "Add cancel Assertion failed");
	}
	
	@Test(groups= {"Maker"},priority=2)
	public void VerifyDeleteAutoACKTypeFaxTemplateRecordWithOutDeleteReason() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String, String> map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.DeleteRecordWithoutDeleteReason(faxTemplateDetails);
		Assert.assertFalse(faxTemplatePage.getErrorMsg(), "Invalid Assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=3)
	public void VerifyDeleteAutoACKFaxTemplateRevertRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String, String> map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.DeleteFaxTemplateRecord(faxTemplateDetails);
		Assert.assertEquals(faxTemplatePage.getSuccessMessage(), "Record Deleted Successfully");
	}
	
	@Test(groups= {"Maker"},priority=4,dependsOnMethods="VerifyDeleteAutoACKFaxTemplateRevertRecord")
	public void VerifyRevertforDeleteRecord() throws Exception {
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectRecord();
		faxTemplatePage.Revert("revert");
		Assert.assertTrue(faxTemplatePage.verifyStatus("Reverted"), "Revert Assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=5)
	public void VerifyAuditTrailReportForDeleteRevertRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
	    Assert.assertTrue(ocmReportsPage.verifyFaxTemplateDelete(faxTemplateDetails, "MakerReverted"), "Audit Trail Assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=6)
	public void VerifyDeleteAutoACKTypeFaxTemplateRejectRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String, String> map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.DeleteFaxTemplateRecord(faxTemplateDetails);
		Assert.assertEquals(faxTemplatePage.getSuccessMessage(), "Record Deleted Successfully");
	}
	
	@Test(groups= {"Maker"},priority=7,dependsOnMethods="VerifyDeleteAutoACKTypeFaxTemplateRejectRecord")
	public void VerifySendForApprovalDeleteRejectRecord() throws Exception {
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectRecord();
		faxTemplatePage.sendForAprroval("sent");
		Assert.assertTrue(faxTemplatePage.verifyStatus("Approval Pending"), "Approve Assertion Failed");
	}
	
	@Test(groups= {"Checker"},priority=8,dependsOnMethods="VerifySendForApprovalDeleteRejectRecord")
	public void RejectForDeleteFaxTemplateRecord() throws Exception {
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.clickonReject("reject");
		Assert.assertFalse(faxTemplatePage.verifyMessage(), "Record Rejection Assertion failed");
		Assert.assertTrue(faxTemplatePage.verifyReviewAuditTrail("Rejected", "reject"), "Reject Assertion failed");
	}
	
	@Test(groups= {"Checker"},priority=9,dependsOnMethods="RejectForDeleteFaxTemplateRecord")
	public void VerifyAuditTrailReportForRejectRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
	    Assert.assertTrue(ocmReportsPage.verifyFaxTemplateDelete(faxTemplateDetails, "CheckerReject"), "Audit Trail Assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=10)
	public void VerifyDeleteAutoACKTypeFaxTemplateApproveRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String, String> map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.DeleteFaxTemplateRecord(faxTemplateDetails);
		Assert.assertEquals(faxTemplatePage.getSuccessMessage(), "Record Deleted Successfully");
	}
	
	@Test(groups= {"Maker"},priority=11)//,dependsOnMethods="VerifyDeleteAutoACKTypeFaxTemplateApproveRecord")
	public void VerifyAuditTrailReportForDeleteApproveRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
	    Assert.assertTrue(ocmReportsPage.verifyFaxTemplateDelete(faxTemplateDetails, "MakerDelete"), "Audit Trail Assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=12,dependsOnMethods="VerifyAuditTrailReportForDeleteApproveRecord")
	public void VerifyAuditTrailDataForApproveRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		Assert.assertTrue(faxTemplatePage.verifyAuditTrailDelete(faxTemplateDetails, "MakerDelete", "New"), "AuditTrail Assertion failed");
	}
	
	@Test(groups= {"Maker"},priority=13,dependsOnMethods="VerifyAuditTrailDataForApproveRecord")
	public void VerifySendForApprovalDeleteApproveRecord() throws Exception {
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectRecord();
		faxTemplatePage.sendForAprroval("sent");
		Assert.assertTrue(faxTemplatePage.verifyStatus("Approval Pending"), "Approve Assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=14,dependsOnMethods="VerifySendForApprovalDeleteApproveRecord")
    public void VerifyAuditTrailReportForSendForApproval() throws Exception {
	    String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyFaxTemplateDelete(faxTemplateDetails, "MakerSendToApproval"), "Audit Trail Assertion Failed");
	}
	
	@Test(groups= {"Checker"},priority=15,dependsOnMethods="VerifyAuditTrailReportForSendForApproval")
	public void ApproveForDeleteFaxTemplateRecord() throws Exception {
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.clickonApprove("Approve");
		Assert.assertTrue(faxTemplatePage.verifyMessage(), "Record Approve Assertion failed");
		Assert.assertTrue(faxTemplatePage.verifyReviewAuditTrail("Approved", "Approve"), "Approve Assertion failed");
	}
	
	@Test(groups= {"Checker"},priority=16,dependsOnMethods="ApproveForDeleteFaxTemplateRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
	    String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyFaxTemplateDelete(faxTemplateDetails, "CheckerApprove"), "Audit Trail Assertion Failed");
		}
	
	
	 @AfterMethod
	 public void afterEachMethod(Method method) {
		 Screenshot screenshot=new Screenshot(driver);
		 screenshot.captureScreen("FaxTemplateCreateTest", method.getName());
		 driver.navigate().refresh();
		 HomePage homepage=PageFactory.createPageInstance(driver, HomePage.class);
		 homepage.userLogout();
		 driver.close();
		 System.out.println("Completed Excecuting :"+method.getName());
	 }
}
