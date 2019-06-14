package com.tetherfi.test.ivr;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.ivr.HostValueMappingDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HostValueMappingPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class HostValueMappingDeleteTest {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateToHostValueMappingPage(Method method) throws Exception {
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
            map= new ExcelReader(filePath,"Login").getTestData().get(2);
        else
            map= new ExcelReader(filePath,"Login").getTestData().get(0);
        try{driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}catch (TimeoutException e){e.printStackTrace();driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}
        if(map.get("LoginType").equals("Custom")){
            LoginPage loginPage=PageFactory.createPageInstance(driver,LoginPage.class);
            Assert.assertTrue(loginPage.isLoginPageDisplayed(),"Login page not loaded");
            loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
            Thread.sleep(5000);
        }
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "IVR page assertion failed");
        ivrPage.navigateToHostValueMappingPage();
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertTrue(HostValueMappingPage.isHostValueMappingPageDisplayed(), "Branch Management page assertion failed");
    }
	
	@Test(groups= {"Maker"}, priority=1)
	public void DeleteCancelHostValueMappingRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
	     Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	     HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
	     HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
	     Assert.assertTrue(HostValueMappingPage.DeleteCancel(HostValueMappingDetails), "Delete Cancel assertion Failed");
	}
	
	@Test(groups = { "Maker" }, priority=2)
    public void DeleteWithoutModifyReasonRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
	     HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
	     HostValueMappingPage.DeleteRecordWithoutModifyReason(HostValueMappingDetails);
	     Assert.assertFalse(HostValueMappingPage.getErrorMsg(),"Invalid Record Assertion failed");
    }
	
	@Test(groups = { "Maker" },priority=3)
    public void DeleteRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
	     HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
	     HostValueMappingPage.DeleteHostValueMappingRecord(HostValueMappingDetails);
        Assert.assertEquals(HostValueMappingPage.getSuccessMessage(), "Record deleted successfully");
     }
        
    @Test(priority=4,groups = { "Maker" })//,dependsOnMethods="DeleteRecord")
    public void VerifyTaskCompleteActionForDeleteRecord() throws Exception {
	     HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
	     HostValueMappingPage.selectHostValueMappingAuditTrailTab();
	     HostValueMappingPage.taskCompleteAction("Task Complete for Delete");
        Assert.assertTrue(HostValueMappingPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(HostValueMappingPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(priority=5,groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForDeleteRecord")
    public void RejectforDeleteHostValueMappingRecord() throws Exception{
	     HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
	     HostValueMappingPage.clickonReject("Reject Deleted");
        Assert.assertFalse(HostValueMappingPage.getErrorMsg(),"Reject record assertion failed");
        Assert.assertTrue(HostValueMappingPage.verifyReviewAuditTrail("Rejected","Reject Deleted"));
    }
    
    @Test(priority=6,groups = { "Checker" },dependsOnMethods = "RejectforDeleteHostValueMappingRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(1);
	     HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyHostValueMappingDelete(HostValueMappingDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
	
	@Test(groups= {"Maker"},priority=7)
	public void DeleteHostValueMappingRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
	     HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
	     HostValueMappingPage.DeleteHostValueMappingRecord(HostValueMappingDetails);
	     Assert.assertEquals(HostValueMappingPage.getSuccessMessage(), "Record deleted successfully");
	}
	
	@Test(priority=8,groups= {"Maker"},dependsOnMethods="DeleteHostValueMappingRecord")
    public void VerifyAuditTrialReportForDelete() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
	     HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyHostValueMappingDelete(HostValueMappingDetails,"MakerDelete"));
    }
	
	@Test(priority=9,groups = { "Maker" })//,dependsOnMethods="DeleteHostValueMappingRecord")
    public void VerifyAuditTrailDataForDeleteHostValueMappingRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	     HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
	     HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
	     HostValueMappingPage.selectHostValueMappingAuditTrailTab();
	     Assert.assertTrue(HostValueMappingPage.verifyAuditTrailDelete(HostValueMappingDetails, "MakerDelete", "New"), "Audit trail details failed");
	     HostValueMappingPage.selectMakeHostValueMappingChanges();
	     Assert.assertTrue(HostValueMappingPage.verifyTaskCompleteEnabled(), "Task complete button not enabled");
    }
	
	@Test(priority=10,groups = { "Maker" },dependsOnMethods="VerifyAuditTrailDataForDeleteHostValueMappingRecord")
    public void VerifyTaskCompleteActionForDeleteHostValueMappingRecord() throws Exception {
	     HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
	     HostValueMappingPage.selectHostValueMappingAuditTrailTab();
	     HostValueMappingPage.taskCompleteAction("Task Complete for Delete");
	     Assert.assertTrue(HostValueMappingPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
	     Assert.assertTrue(HostValueMappingPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	@Test(priority=11,groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForDeleteHostValueMappingRecord")
    public void ApproveforDeleteHostValueMappingRecord() throws Exception{
	     HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
	     HostValueMappingPage.clickonApprove("Approve Deleted");
	     Assert.assertEquals(HostValueMappingPage.getSuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
	     Assert.assertTrue(HostValueMappingPage.verifyReviewAuditTrail("Approved","Approve Deleted"));
    }
	
	@Test(priority=12,groups = { "Checker" },dependsOnMethods = "ApproveforDeleteHostValueMappingRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
	     HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
	     HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMReportsPage();
	     OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	     String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	     Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	     ReportDetails reportDetails= new ReportDetails(map1);
	     ocmReportsPage.showReport(reportDetails);
	     Assert.assertTrue(ocmReportsPage.verifyHostValueMappingDelete(HostValueMappingDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
	
	
    
	@AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("HostValueMappingDeleteTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}
