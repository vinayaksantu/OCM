package com.tetherfi.test.ivr;

import java.lang.reflect.Method;
import java.util.Map;

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
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class IntroMessageAnnouncementDeleteTest {
	
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateToIntroMessageAnnouncementPage(Method method) throws Exception {
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
            loginPage.login(map.get("Username"),map.get("Password"));
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
    }
	
	@Test(groups= {"Maker"}, priority=1)
	public void DeleteCancelIntroMessageAnnouncementRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
	     Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
	     IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
	     Assert.assertTrue(IntroMessageAnnouncementPage.DeleteCancel(IntroMessageAnnouncementDetails), "Delete Cancel assertion Failed");
	}
	
	@Test(groups = { "Maker" }, priority=2)
    public void DeleteWithoutModifyReasonRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
	     IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
	     IntroMessageAnnouncementPage.DeleteRecordWithoutModifyReason(IntroMessageAnnouncementDetails);
	     Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg(),"Invalid Record Assertion failed");
    }
	
	
	@Test(groups = { "Maker" },priority=3)
    public void DeleteRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
	     IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
	     IntroMessageAnnouncementPage.DeleteIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails);
        Assert.assertEquals(IntroMessageAnnouncementPage.getSuccessMessage(), "Record deleted successfully");
     }
        
	@Test(groups = { "Maker" },priority=4,dependsOnMethods="DeleteRecord")
    public void VerifyRevertForDeleteRecord() throws Exception {
       	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
       	IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
       	IntroMessageAnnouncementPage.selectRecord();
       	IntroMessageAnnouncementPage.Revert("revert");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyStatus("Reverted"),"approval status details failed");
    }
    
	@Test(groups= {"Maker"},priority=5,dependsOnMethods="VerifyRevertForDeleteRecord")
    public void VerifyAuditTrialReportForRevertDelete() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementDelete(IntroMessageAnnouncementDetails,"MakerReverted"));
    }
	
	@Test(groups = { "Maker" },priority=6)
    public void RejectDeleteRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
	     IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
	     IntroMessageAnnouncementPage.DeleteIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails);
        Assert.assertEquals(IntroMessageAnnouncementPage.getSuccessMessage(), "Record deleted successfully");
     }
	
	@Test(groups = { "Maker" },priority=7,dependsOnMethods="RejectDeleteRecord")
    public void VerifySendForApprovalForDeleteNewRecord() throws Exception {
       	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
       	IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
       	IntroMessageAnnouncementPage.selectRecord();
       	IntroMessageAnnouncementPage.sendForAprroval("sent");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
		
    @Test(priority=8,groups = { "Checker" },dependsOnMethods="VerifySendForApprovalForDeleteNewRecord")
    public void RejectforDeleteIntroMessageAnnouncementRecord() throws Exception{
	     IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
	     IntroMessageAnnouncementPage.clickonReject("Reject Deleted");
        Assert.assertFalse(IntroMessageAnnouncementPage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyReviewAuditTrail("Rejected","Reject Deleted"));
    }
    
    @Test(priority=9,groups = { "Checker" },dependsOnMethods = "RejectforDeleteIntroMessageAnnouncementRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementDelete(IntroMessageAnnouncementDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
	
	@Test(groups= {"Maker"},priority=10)
	public void DeleteIntroMessageAnnouncementRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
	     IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
	     IntroMessageAnnouncementPage.DeleteIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails);
	     Assert.assertEquals(IntroMessageAnnouncementPage.getSuccessMessage(), "Record deleted successfully");
	}
	
	@Test(priority=11,groups= {"Maker"},dependsOnMethods="DeleteIntroMessageAnnouncementRecord")
    public void VerifyAuditTrialReportForDelete() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
	     IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementDelete(IntroMessageAnnouncementDetails,"MakerDelete"));
    }
	
	@Test(priority=12,groups = { "Maker" },dependsOnMethods="DeleteIntroMessageAnnouncementRecord")
    public void VerifyAuditTrailDataForDeleteIntroMessageAnnouncementRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
	     IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
	     IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
	     Assert.assertTrue(IntroMessageAnnouncementPage.verifyAuditTrailDelete(IntroMessageAnnouncementDetails, "MakerDelete", "New"), "Audit trail details failed");
    }

	@Test(groups = { "Maker" },priority=13,dependsOnMethods="VerifyAuditTrailDataForDeleteIntroMessageAnnouncementRecord")
    public void VerifySendForApprovalForDeleteRecord() throws Exception {
       	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
       	IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
       	IntroMessageAnnouncementPage.selectRecord();
       	IntroMessageAnnouncementPage.sendForAprroval("sent");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	@Test(priority=14,groups = { "Maker" },dependsOnMethods = "VerifySendForApprovalForDeleteRecord")
    public void VerifyAuditTrailReportForSendForApprove() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
	     IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
	     HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMReportsPage();
	     OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	     String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	     Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	     ReportDetails reportDetails= new ReportDetails(map1);
	     ocmReportsPage.showReport(reportDetails);
	     Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementDelete(IntroMessageAnnouncementDetails, "MakerSendToApproval"),"Audit Trail report assertion failed");
    }
	
	@Test(priority=15,groups = { "Checker" })
    public void ApproveforDeleteIntroMessageAnnouncementRecord() throws Exception{
	     IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
	     IntroMessageAnnouncementPage.clickonApprove("Approve Deleted");
	     Assert.assertTrue(IntroMessageAnnouncementPage.verifyMessage(),"Approve record assertion failed");
	     Assert.assertTrue(IntroMessageAnnouncementPage.verifyReviewAuditTrail("Approved","Approve Deleted"));
    }
	
	@Test(priority=16,groups = { "Checker" },dependsOnMethods = "ApproveforDeleteIntroMessageAnnouncementRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
	     IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
	     HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMReportsPage();
	     OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	     String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	     Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	     ReportDetails reportDetails= new ReportDetails(map1);
	     ocmReportsPage.showReport(reportDetails);
	     Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementDelete(IntroMessageAnnouncementDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
	
    
	@AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("IntroMessageAnnouncementDeleteTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }

}
