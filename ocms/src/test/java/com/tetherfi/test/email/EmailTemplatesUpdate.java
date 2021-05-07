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
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.EmailTemplatesPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class EmailTemplatesUpdate {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateEmailTemplatesPage(Method method) throws Exception {
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
            map= new  ExcelReader(filePath,"Login").getTestData().get(0);
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
	
	@Test(groups= {"Maker"}, priority=1,description="Verify cancel button at edit record")
	public void EditCancelEmailTemplatesRecord() throws Exception {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
        EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        Assert.assertTrue(EmailTemplatesPage.EditCancel(detailspage), "Edit Cancel assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=2,description="Edit a record to perform Revert action")
	public void EditRevertEmailTemplatesRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
        EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.editEmailTemplatesRecord(detailspage);
        Assert.assertEquals(EmailTemplatesPage.getSuccessMessage(), "Record Updated Successfully");
	}
	
	@Test(groups = { "Maker" },priority=3,dependsOnMethods="EditRevertEmailTemplatesRecord",description="verify the status after reverting edit")
    public void VerifyRevertForEditRecord() throws Exception {
       	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
       	EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
       	EmailTemplatesPage.selectRecord();
       	EmailTemplatesPage.Revert("revert");
        Assert.assertTrue(EmailTemplatesPage.verifyStatus("Reverted"),"approval status details failed");
    }
	
	@Test(groups= {"Maker"},priority=4,dependsOnMethods="VerifyRevertForEditRecord",description="Verify the audit trial report for revert update")
    public void VerifyAuditTrialReportForRevertUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails=new  ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyEmailTemplatesUpdate(detailspage,"MakerReverted"));
    }
	
	@Test(groups= {"Maker"},priority=5,description="Edit a record to perform the reject action")
	public void EditRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
        EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.editEmailTemplatesRecord(detailspage);
        Assert.assertEquals(EmailTemplatesPage.getSuccessMessage(), "Record Updated Successfully");
	}
	
	@Test(groups = { "Maker" },priority=6,dependsOnMethods="EditRejectRecord",description="Verify the status in audit trial tab")
    public void VerifySendForApprovalForEditRejectRecord() throws Exception {
       	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
       	EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
       	EmailTemplatesPage.selectRecord();
       	EmailTemplatesPage.sendForAprroval("sent");
        Assert.assertTrue(EmailTemplatesPage.verifyStatus("Approval Pending"),"approval status details failed");
    }

	@Test(groups = { "Checker" },priority=7,dependsOnMethods="VerifySendForApprovalForEditRejectRecord",description="Login as a checker, perform the reject action, ")
    public void RejectforEditEmailTemplatesRecord() throws Exception{
        EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.clickonReject("Reject Updated");
        Assert.assertFalse(EmailTemplatesPage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(EmailTemplatesPage.verifyReviewAuditTrail("Rejected","Reject Updated"));
    }
    
    @Test(groups = { "Checker" },priority=8,dependsOnMethods = "RejectforEditEmailTemplatesRecord",description="Verify the Audit trail report for checker action")
    public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyEmailTemplatesUpdate(detailspage, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
    	@Test(groups= {"Maker"},priority=9,description="edit a record to perform the approval action")
	public void EditEmailTemplatesRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
        EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.editEmailTemplatesRecord(detailspage);
        Assert.assertEquals(EmailTemplatesPage.getSuccessMessage(), "Record Updated Successfully");
	}
	
	@Test(groups = { "Maker" },priority=10,dependsOnMethods="EditEmailTemplatesRecord",description="verify the audit trail tab for edit action")
    public void VerifyAuditTrailDataForEditEmailTemplatesRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
        EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
        Assert.assertTrue(EmailTemplatesPage.verifyAuditTrailUpdate(detailspage, "MakerUpdate", "New"), "Audit trail details failed");
    }
	
	@Test(groups= {"Maker"},priority=11,dependsOnMethods="EditEmailTemplatesRecord",description="verify the audit trial report for edit action")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyEmailTemplatesUpdate(detailspage,"MakerUpdate"));
    }
 
	
	@Test(groups = { "Maker" },priority=12,description="Verify the status of a record sent for approval")
    public void VerifySendForApprovalForEditRecord() throws Exception {
       	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
       	EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
       	EmailTemplatesPage.selectRecord();
       	EmailTemplatesPage.sendForAprroval("sent");
        Assert.assertTrue(EmailTemplatesPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	@Test(groups= {"Maker"},priority=13,description="Verify the audit trial Report for a record sent for approval")
    public void VerifyAuditTrialReportForSendForApprovalUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyEmailTemplatesUpdate(detailspage,"MakerSendToApproval"));
    }
    
    @Test(groups = { "Checker" },priority=14,description="Verify the approve action and audit trail tab for a checker")
    public void ApproveforEditEmailTemplatesRecord() throws Exception{
        EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.clickonApprove("Approve Edited");
        Assert.assertTrue(EmailTemplatesPage.verifyMessage());
        Assert.assertTrue(EmailTemplatesPage.verifyReviewAuditTrail("Approved","Approve Edited"));
    }
	
	@Test(groups = { "Checker" },priority=15,dependsOnMethods = "ApproveforEditEmailTemplatesRecord",description="Verify the checker approved action in audit trial report")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
	    Map<String, String> map =  new ExcelReader(filePath,"Edit").getTestData().get(0);
        EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);	    
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyEmailTemplatesUpdate(detailspage, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
	
    @Test(groups = { "Maker" },priority=16,description="Verify the edit record without modify reason")
    public void EditRecordWithoutModifyReason() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
        EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.EditRecordWithoutModifyReason(detailspage);
        Assert.assertEquals(EmailTemplatesPage.getErrorMsg(),"Please enter the modify reason", "Invalid Assertion Failed");
    }
   
    @AfterMethod
	    public void afterEachMethod(Method method){
    	Screenshot screenshot=new Screenshot(driver);
    	screenshot.captureScreen("EmailTemplatesUpdateTest",method.getName());
    	driver.navigate().refresh();
    	HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
    	homePage.userLogout();
    	driver.close();
    	System.out.println("Completed Executing : "+method.getName());
	    }
    
}

