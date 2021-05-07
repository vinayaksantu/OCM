package com.tetherfi.test.sms;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

public class SmsResponseTemplateUpdateTest {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateSmsResponseTemplatePage(Method method) throws Exception {
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
        Assert.assertTrue(smsPage.isSMSPageDisplayed(), "IVR page assertion failed");
        smsPage.navigateToSMSResponseTemplatePage();
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        Assert.assertTrue(SmsResponseTemplatePage.isSMSResponseTemplatePageDisplayed(), "SMS Response Template page assertion failed");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	
	/*@Test(groups= {"Maker"},priority=1)
	public void EditCancelSmsResponseTemplateRecord() throws Exception {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        Assert.assertTrue(SmsResponseTemplatePage.EditCancel(SmsResponseTemplateDetails), "Edit Cancel assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=2)
	public void EditRevertSmsResponseTemplateRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.EditSmsResponseTemplateRecord(SmsResponseTemplateDetails);
        Assert.assertEquals(SmsResponseTemplatePage.getSuccessMessage(), "Record updated successfully");
	}
	
	@Test(groups = { "Maker" },priority=3,dependsOnMethods="EditRevertSmsResponseTemplateRecord")
    public void VerifyRevertForEditRecord() throws Exception {
       	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
       	SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
       	SmsResponseTemplatePage.selectRecord();
       	SmsResponseTemplatePage.Revert("revert");
        Assert.assertTrue(SmsResponseTemplatePage.verifyStatus("Reverted"),"approval status details failed");
    }
	
	//@Test(groups= {"Maker"},priority=4,dependsOnMethods="VerifyRevertForEditRecord")
    public void VerifyAuditTrialReportForRevertUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateUpdate(SmsResponseTemplateDetails,"MakerReverted"));
    }
	
	@Test(groups= {"Maker"},priority=5)
	public void EditRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.EditSmsResponseTemplateRecord(SmsResponseTemplateDetails);
        Assert.assertEquals(SmsResponseTemplatePage.getSuccessMessage(), "Record updated successfully");
	}
	
	@Test(groups = { "Maker" },priority=6,dependsOnMethods="EditRejectRecord")
    public void VerifySendForApprovalForEditRejectRecord() throws Exception {
       	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
       	SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
       	SmsResponseTemplatePage.selectRecord();
       	SmsResponseTemplatePage.sendForAprroval("sent");
        Assert.assertTrue(SmsResponseTemplatePage.verifyStatus("Approval Pending"),"approal status details failed");
    }

	@Test(groups = { "Checker" },priority=7,dependsOnMethods="VerifySendForApprovalForEditRejectRecord")
    public void RejectforEditSmsResponseTemplateRecord() throws Exception{
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.clickonReject("Reject Updated");
        Assert.assertFalse(SmsResponseTemplatePage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(SmsResponseTemplatePage.verifyReviewAuditTrail("Rejected","Reject Updated"));
    }
    
    //@Test(groups = { "Checker" },priority=8,dependsOnMethods = "RejectforEditSmsResponseTemplateRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateUpdate(SmsResponseTemplateDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    */
	@Test(groups= {"Maker"},priority=9)
	public void EditSmsResponseTemplateRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.EditSmsResponseTemplateRecord(SmsResponseTemplateDetails);
        Assert.assertEquals(SmsResponseTemplatePage.getSuccessMessage(), "Record updated successfully");
	}
	
	@Test(groups = { "Maker" },priority=10,dependsOnMethods="EditSmsResponseTemplateRecord")
    public void VerifyAuditTrailDataForEditSmsResponseTemplateRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        Assert.assertTrue(SmsResponseTemplatePage.verifyAuditTrailUpdate(SmsResponseTemplateDetails, "MakerUpdate", "New"), "Audit trail details failed");
    }
	

	@Test(groups= {"Maker"},priority=11,dependsOnMethods="EditSmsResponseTemplateRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateUpdate(SmsResponseTemplateDetails,"MakerUpdate"));
    }
    
	@Test(groups = { "Maker" },priority=12,dependsOnMethods="VerifyAuditTrialReportForUpdate")
    public void VerifySendForApprovalForRejectRecord() throws Exception {
       	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
       	SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
       	SmsResponseTemplatePage.selectRecord();
       	SmsResponseTemplatePage.sendForAprroval("sent");
        Assert.assertTrue(SmsResponseTemplatePage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	@Test(groups= {"Maker"},priority=13,dependsOnMethods="VerifySendForApprovalForRejectRecord")
    public void VerifyAuditTrialReportForSendForApprovalUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateUpdate(SmsResponseTemplateDetails,"MakerSendToApproval"));
    }
    
    @Test(groups = { "Checker" },priority=14,dependsOnMethods="VerifyAuditTrialReportForSendForApprovalUpdate")
    public void ApproveforEditSmsResponseTemplateRecord() throws Exception{
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.clickonApprove("Approve Edited");
        Assert.assertTrue(SmsResponseTemplatePage.verifyMessage());
        Assert.assertTrue(SmsResponseTemplatePage.verifyReviewAuditTrail("Approved","Approve Edited"));
    }
	
	@Test(groups = { "Checker" },priority=15,dependsOnMethods = "ApproveforEditSmsResponseTemplateRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);	    
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateUpdate(SmsResponseTemplateDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=16)
    public void EditRecordWithoutModifyReason() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.EditRecordWithoutModifyReason(SmsResponseTemplateDetails);
        Assert.assertFalse(SmsResponseTemplatePage.getErrorMsg(),"Invalid Record Assertion failed");
    }
   
    @AfterMethod
	    public void afterEachMethod(Method method){
	        Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("SmsResponseTemplateUpdateTest",method.getName());
	        driver.navigate().refresh();
	        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	        homePage.userLogout();
	        driver.close();
	        System.out.println("Completed Executing : "+method.getName());
	    }
}