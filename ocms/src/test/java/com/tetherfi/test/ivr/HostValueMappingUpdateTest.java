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

public class HostValueMappingUpdateTest {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateHostValueMappingPage(Method method) throws Exception {
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
        ivrPage.navigateToHostValueMappingPage();
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertTrue(HostValueMappingPage.isHostValueMappingPageDisplayed(), "Branch Management page assertion failed");
    }
	
	@Test(groups= {"Maker"})
	public void EditCancelHostValueMappingRecord() throws Exception {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertTrue(HostValueMappingPage.EditCancel(HostValueMappingDetails), "Edit Cancel assertion Failed");
	}
	
	@Test(groups= {"Maker"})
	public void EditHostValueMappingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.EditHostValueMappingRecord(HostValueMappingDetails);
        Assert.assertEquals(HostValueMappingPage.getSuccessMessage(), "Record updated successfully");
	}
	
	@Test(groups= {"Maker"},dependsOnMethods="EditHostValueMappingRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyHostValueMappingUpdate(HostValueMappingDetails,"MakerUpdate"));
    }
	
	@Test(groups = { "Maker" },dependsOnMethods="EditHostValueMappingRecord")
    public void VerifyAuditTrailDataForEditHostValueMappingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.selectHostValueMappingAuditTrailTab();
        Assert.assertTrue(HostValueMappingPage.verifyAuditTrailUpdate(HostValueMappingDetails, "MakerUpdate", "New"), "Audit trail details failed");
        HostValueMappingPage.selectMakeHostValueMappingChanges();
        Assert.assertTrue(HostValueMappingPage.verifyTaskCompleteEnabled(), "Task complete button not enabled");
    }
	
	@Test(groups = { "Maker" },dependsOnMethods="VerifyAuditTrailDataForEditHostValueMappingRecord")
    public void VerifyTaskCompleteActionForEditHostValueMappingRecord() throws Exception {
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.selectHostValueMappingAuditTrailTab();
        HostValueMappingPage.taskCompleteAction("Task Complete for Edit");
        Assert.assertTrue(HostValueMappingPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(HostValueMappingPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	@Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForEditHostValueMappingRecord")
    public void ApproveforEditHostValueMappingRecord() throws Exception{
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.clickonApprove("Approve Edited");
        Assert.assertEquals(HostValueMappingPage.getSuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(HostValueMappingPage.verifyReviewAuditTrail("Approved","Approve Edited"));
    }
	
	@Test(groups = { "Checker" },dependsOnMethods = "ApproveforEditHostValueMappingRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyHostValueMappingUpdate(HostValueMappingDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
	@Test(groups = { "Maker" })
    public void EditRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.EditHostValueMappingRecord(HostValueMappingDetails);
        Assert.assertEquals(HostValueMappingPage.getSuccessMessage(), "Record updated successfully");
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="EditRecord")
    public void VerifyMakeHostValueMappingButtonafterTaskComplete() throws Exception {
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.selectHostValueMappingAuditTrailTab();
        HostValueMappingPage.taskCompleteAction("Task Complete for Update");
        Assert.assertFalse(HostValueMappingPage.VerifyMakeHostValueMappingChangeButton());
    }
    
    @Test(groups = { "Checker" })//,dependsOnMethods="VerifyMakeHostValueMappingButtonafterTaskComplete")
    public void RejectforEditHostValueMappingRecord() throws Exception{
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.clickonReject("Reject Updated");
        Assert.assertFalse(HostValueMappingPage.getErrorMsg(),"Reject record assertion failed");
        Assert.assertTrue(HostValueMappingPage.verifyReviewAuditTrail("Rejected","Reject Updated"));
    }
    
    @Test(groups = { "Checker" },dependsOnMethods = "RejectforEditHostValueMappingRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(1);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyHostValueMappingUpdate(HostValueMappingDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" })
    public void EditRecordWithoutModifyReason() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.EditRecordWithoutModifyReason(HostValueMappingDetails);
        Assert.assertFalse(HostValueMappingPage.getErrorMsg(),"Invalid Record Assertion failed");
    }
   
    @AfterMethod
	    public void afterEachMethod(Method method){
	        Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("HostValueMappingUpdateTest",method.getName());
	        driver.navigate().refresh();
	        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	        homePage.userLogout();
	        driver.close();
	        System.out.println("Completed Executing : "+method.getName());
	    }
}

