package com.tetherfi.test.ivr;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.ivr.IntroMessageAnnouncementDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IntroMessageAnnouncementPage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.IntroMessageAnnouncementPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class IntroMessageAnnouncementUpdateTest {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateIntroMessageAnnouncementPage(Method method) throws Exception {
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
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "IVR page assertion failed");
        ivrPage.navigateToIntroMessageAnnouncementPage();
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.isIntroMessageAnnouncementPageDisplayed(), "Branch Management page assertion failed");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test(groups= {"Maker"},priority=1)
	public void EditCancelIntroMessageAnnouncementRecord() throws Exception {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.EditCancel(IntroMessageAnnouncementDetails), "Edit Cancel assertion Failed");
	}
	
	
    @Test(groups = { "Maker" },priority=2)
    public void EditRecordWithoutModifyReason() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.EditRecordWithoutModifyReason(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg(),"Invalid Record Assertion failed");
    }
    
    @Test(groups= {"Maker"},priority=3)
	public void EditRevertIntroMessageAnnouncementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.editIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails);
        Assert.assertEquals(IntroMessageAnnouncementPage.getSuccessMessage(), "Record updated successfully");
	}
	
	@Test(groups = { "Maker" },priority=4,dependsOnMethods="EditRevertIntroMessageAnnouncementRecord")
    public void VerifyRevertForEditRecord() throws Exception {
       	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
       	IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
       	IntroMessageAnnouncementPage.selectRecord();
       	IntroMessageAnnouncementPage.Revert("revert");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyStatus("Reverted"),"approval status details failed");
    }
	
	@Test(groups= {"Maker"},priority=5,dependsOnMethods="VerifyRevertForEditRecord")
    public void VerifyAuditTrialReportForRevertUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementUpdate(IntroMessageAnnouncementDetails,"MakerReverted"));
    }
	
	@Test(groups= {"Maker"},priority=6)
	public void EditRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.editIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails);
        Assert.assertEquals(IntroMessageAnnouncementPage.getSuccessMessage(), "Record updated successfully");
	}
	
	@Test(groups = { "Maker" },priority=7,dependsOnMethods="EditRejectRecord")
    public void VerifySendForApprovalForEditRejectRecord() throws Exception {
       	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
       	IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
       	IntroMessageAnnouncementPage.selectRecord();
       	IntroMessageAnnouncementPage.sendForAprroval("sent");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }

	@Test(groups = { "Checker" },priority=8,dependsOnMethods="VerifySendForApprovalForEditRejectRecord")
    public void RejectforEditIntroMessageAnnouncementRecord() throws Exception{
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.clickonReject("Reject Updated");
        Assert.assertFalse(IntroMessageAnnouncementPage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyReviewAuditTrail("Rejected","Reject Updated"));
    }
    
    @Test(groups = { "Checker" },priority=9,dependsOnMethods = "RejectforEditIntroMessageAnnouncementRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementUpdate(IntroMessageAnnouncementDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
	@Test(groups= {"Maker"},priority=10)
	public void EditIntroMessageAnnouncementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.editIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails);
        Assert.assertEquals(IntroMessageAnnouncementPage.getSuccessMessage(), "Record updated successfully");
	}
	
	@Test(groups = { "Maker" },priority=11,dependsOnMethods="EditIntroMessageAnnouncementRecord")
    public void VerifyAuditTrailDataForEditIntroMessageAnnouncementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyAuditTrailUpdate(IntroMessageAnnouncementDetails, "MakerUpdate", "New"), "Audit trail details failed");
    }
	

	@Test(groups= {"Maker"},priority=12,dependsOnMethods="EditIntroMessageAnnouncementRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementUpdate(IntroMessageAnnouncementDetails,"MakerUpdate"));
    }
    
	@Test(groups = { "Maker" },priority=13)//,dependsOnMethods="VerifyAuditTrialReportForUpdate")
    public void VerifySendForApprovalForRecord() throws Exception {
       	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
       	IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
       	IntroMessageAnnouncementPage.selectRecord();
       	IntroMessageAnnouncementPage.sendForAprroval("sent");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	@Test(groups= {"Maker"},priority=14)//,dependsOnMethods="VerifySendForApprovalForRecord")
    public void VerifyAuditTrialReportForSendForApprovalUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementUpdate(IntroMessageAnnouncementDetails,"MakerSendToApproval"));
    }
    
    @Test(groups = { "Checker" },priority=15)//,dependsOnMethods="VerifyAuditTrialReportForSendForApprovalUpdate")
    public void ApproveforEditIntroMessageAnnouncementRecord() throws Exception{
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.clickonApprove("Approve Edited");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyMessage());
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyReviewAuditTrail("Approved","Approve Edited"));
    }
	
	@Test(groups = { "Checker" },priority=16,dependsOnMethods = "ApproveforEditIntroMessageAnnouncementRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);	    
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementUpdate(IntroMessageAnnouncementDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
   
    @AfterMethod
	    public void afterEachMethod(Method method){
	        Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("IntroMessageAnnouncementUpdateTest",method.getName());
	        driver.navigate().refresh();
	        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	        homePage.userLogout();
	        driver.close();
	        System.out.println("Completed Executing : "+method.getName());
	    }
}
