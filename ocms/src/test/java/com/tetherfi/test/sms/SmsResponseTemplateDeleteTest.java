package com.tetherfi.test.sms;

import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.sms.SmsResponseTemplateDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.SmsResponseTemplatePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.SmsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class SmsResponseTemplateDeleteTest {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateToSmsResponseTemplatePage(Method method) throws Exception {
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
        ocmHomePage.navigateToTab("SMS");
        SmsPage smsPage = PageFactory.createPageInstance(driver, SmsPage.class);
        Assert.assertTrue(smsPage.isSMSPageDisplayed(), "SMS page assertion failed");
        smsPage.navigateToSMSResponseTemplatePage();
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        Assert.assertTrue(SmsResponseTemplatePage.isSMSResponseTemplatePageDisplayed(), "SMS Response Template page assertion failed");
    }
	
	@Test(groups= {"Maker"}, priority=1)
	public void DeleteCancelSmsResponseTemplateRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	     Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	     SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
	     Assert.assertTrue(SmsResponseTemplatePage.DeleteCancel(SmsResponseTemplateDetails), "Delete Cancel assertion Failed");
	}
	
	@Test(groups = { "Maker" }, priority=2)
    public void DeleteWithoutModifyReasonRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	     SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
	     SmsResponseTemplatePage.DeleteRecordWithoutModifyReason(SmsResponseTemplateDetails);
	     Assert.assertFalse(SmsResponseTemplatePage.getErrorMsg(),"Invalid Record Assertion failed");
    }
	
	@Test(groups = { "Maker" },priority=3)
    public void DeleteRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	     SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
	     SmsResponseTemplatePage.DeleteSmsResponseTemplateRecord(SmsResponseTemplateDetails);
        Assert.assertEquals(SmsResponseTemplatePage.getSuccessMessage(), "Record deleted successfully");
     }
        
	@Test(groups = { "Maker" },priority=4,dependsOnMethods="DeleteRecord")
    public void VerifyRevertForDeleteRecord() throws Exception {
       	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
       	SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
       	SmsResponseTemplatePage.selectRecord();
       	SmsResponseTemplatePage.Revert("revert");
        Assert.assertTrue(SmsResponseTemplatePage.verifyStatus("Reverted"),"approval status details failed");
    }
    
	@Test(groups= {"Maker"},priority=5,dependsOnMethods="VerifyRevertForDeleteRecord")
    public void VerifyAuditTrialReportForRevertDelete() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateDelete(SmsResponseTemplateDetails,"MakerReverted"));
    }
	
	@Test(groups = { "Maker" },priority=6)
    public void RejectDeleteRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	     SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
	     SmsResponseTemplatePage.DeleteSmsResponseTemplateRecord(SmsResponseTemplateDetails);
        Assert.assertEquals(SmsResponseTemplatePage.getSuccessMessage(), "Record deleted successfully");
     }
	
	@Test(groups = { "Maker" },priority=7,dependsOnMethods="RejectDeleteRecord")
    public void VerifySendForApprovalForDeleteNewRecord() throws Exception {
       	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
       	SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
       	SmsResponseTemplatePage.selectRecord();
       	SmsResponseTemplatePage.sendForAprroval("sent");
        Assert.assertTrue(SmsResponseTemplatePage.verifyStatus("Approval Pending"),"approal status details failed");
    }
		
    @Test(priority=8,groups = { "Checker" },dependsOnMethods="VerifySendForApprovalForDeleteNewRecord")
    public void RejectforDeleteSmsResponseTemplateRecord() throws Exception{
	     SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
	     SmsResponseTemplatePage.clickonReject("Reject Deleted");
        Assert.assertFalse(SmsResponseTemplatePage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(SmsResponseTemplatePage.verifyReviewAuditTrail("Rejected","Reject Deleted"));
    }
    
    @Test(priority=9,groups = { "Checker" },dependsOnMethods = "RejectforDeleteSmsResponseTemplateRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateDelete(SmsResponseTemplateDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
	
	@Test(groups= {"Maker"},priority=10)
	public void DeleteSmsResponseTemplateRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	     SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
	     SmsResponseTemplatePage.DeleteSmsResponseTemplateRecord(SmsResponseTemplateDetails);
	     Assert.assertEquals(SmsResponseTemplatePage.getSuccessMessage(), "Record deleted successfully");
	}
	
	@Test(priority=11,groups= {"Maker"},dependsOnMethods="DeleteSmsResponseTemplateRecord")
    public void VerifyAuditTrialReportForDelete() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
	     SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateDelete(SmsResponseTemplateDetails,"MakerDelete"));
    }
	
	@Test(priority=12,groups = { "Maker" },dependsOnMethods="DeleteSmsResponseTemplateRecord")
    public void VerifyAuditTrailDataForDeleteSmsResponseTemplateRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	     SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
	     SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
	     Assert.assertTrue(SmsResponseTemplatePage.verifyAuditTrailDelete(SmsResponseTemplateDetails, "MakerDelete", "New"), "Audit trail details failed");
    }

	@Test(groups = { "Maker" },priority=13)//,dependsOnMethods="VerifyAuditTrailDataForDeleteSmsResponseTemplateRecord")
    public void VerifySendForApprovalForDeleteRecord() throws Exception {
       	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
       	SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
       	SmsResponseTemplatePage.selectRecord();
       	SmsResponseTemplatePage.sendForAprroval("sent");
        Assert.assertTrue(SmsResponseTemplatePage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	@Test(priority=14,groups = { "Maker" })//,dependsOnMethods = "VerifySendForApprovalForDeleteRecord")
    public void VerifyAuditTrailReportForSendForApprove() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
	     SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	     HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMReportsPage();
	     OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	     String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	     Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	     ReportDetails reportDetails= new ReportDetails(map1);
	     ocmReportsPage.showReport(reportDetails);
	     Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateDelete(SmsResponseTemplateDetails, "MakerSendToApproval"),"Audit Trail report assertion failed");
    }
	
	@Test(priority=15,groups = { "Checker" },dependsOnMethods="VerifyAuditTrailReportForSendForApprove")
    public void ApproveforDeleteSmsResponseTemplateRecord() throws Exception{
	     SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
	     SmsResponseTemplatePage.clickonApprove("Approve Deleted");
	     Assert.assertTrue(SmsResponseTemplatePage.verifyMessage(),"Approve record assertion failed");
	     Assert.assertTrue(SmsResponseTemplatePage.verifyReviewAuditTrail("Approved","Approve Deleted"));
    }
	
	@Test(priority=16,groups = { "Checker" },dependsOnMethods = "ApproveforDeleteSmsResponseTemplateRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
	     SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	     HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMReportsPage();
	     OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	     String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	     Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	     ReportDetails reportDetails= new ReportDetails(map1);
	     ocmReportsPage.showReport(reportDetails);
	     Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateDelete(SmsResponseTemplateDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
	@AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("SmsResponseTemplateDeleteTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}