package com.tetherfi.test.fax;

import java.io.IOException;
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

public class FaxTemplateUpdateTest {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateToFaxTemplatePage(Method method) throws Exception {
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
        ocmHomePage.navigateToTab("FAX");
        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
        Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
        faxPage.navigateToFaxTemplatePage();
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        Assert.assertTrue(faxTemplatePage.isFaxTemplatePageDisplayed(), "FAX page assertion failed");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
	
	//@Test(groups= {"Maker"},priority=1)
	public void EditCancelFaxTemplateRecord() throws Exception {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        Assert.assertTrue(faxTemplatePage.EditCancel(faxTemplateDetails), "Edit Cancel assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=2)
	public void EditFaxTemplateRecord() throws Exception {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.EditFaxTemplateRecord(faxTemplateDetails);
        Assert.assertEquals(faxTemplatePage.getSuccessMessage(), "Record Updated Successfully. Refresh/F5 page for preview");
	}
	
	@Test(priority=3,groups= {"Maker"},dependsOnMethods="EditFaxTemplateRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxTemplateUpdate(faxTemplateDetails,"MakerUpdate"));
    }
	
	@Test(priority=4,groups = { "Maker" },dependsOnMethods="EditFaxTemplateRecord")
    public void VerifyAuditTrailDataForEditFaxTemplateRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        Assert.assertTrue(faxTemplatePage.verifyAuditTrailUpdate(faxTemplateDetails, "MakerUpdate", "New"), "Audit trail details failed");
        faxTemplatePage.selectMakeFaxTemplateChanges();
        Assert.assertTrue(faxTemplatePage.verifyTaskCompleteEnabled(), "Task complete button not enabled");
    }
	
	@Test(priority=5,groups = { "Maker" },dependsOnMethods="VerifyAuditTrailDataForEditFaxTemplateRecord")
    public void VerifyTaskCompleteActionForEditFaxTemplateRecord() throws Exception {
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.taskCompleteAction("Task Complete for Edit");
        Assert.assertTrue(faxTemplatePage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(faxTemplatePage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	@Test(priority=6,groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForEditFaxTemplateRecord")
    public void ApproveforEditFaxTemplateRecord() throws Exception{
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.clickonApprove("Approve Edited");
        Assert.assertEquals(faxTemplatePage.getSuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(faxTemplatePage.verifyReviewAuditTrail("Approved","Approve Edited"));
    }
	
	@Test(priority=7,groups = { "Checker" },dependsOnMethods = "ApproveforEditFaxTemplateRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxTemplateUpdate(faxTemplateDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
    @Test(priority=8,groups = { "Maker" })
    public void EditRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
        FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.EditFaxTemplateRecordReject(faxTemplateDetails);
        Assert.assertEquals(faxTemplatePage.getSuccessMessage(), "Record Updated Successfully. Refresh/F5 page for preview");
    }
    
    @Test(priority=9,groups = { "Maker" },dependsOnMethods="EditRecord")
    public void VerifyMakeFaxTemplateButtonafterTaskComplete() throws Exception {
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.taskCompleteAction("Task Complete for Update");
        Assert.assertFalse(faxTemplatePage.VerifyMakefFaxTemplateChangeButton());
    }
    
    @Test(priority=10,groups = { "Checker" },dependsOnMethods="VerifyMakeFaxTemplateButtonafterTaskComplete")
    public void RejectforEditFaxTemplateRecord() throws Exception{
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.clickonReject("Reject Updated");
        Assert.assertFalse(faxTemplatePage.getErrorMsg(),"Reject record assertion failed");
        Assert.assertTrue(faxTemplatePage.verifyReviewAuditTrail("Rejected","Reject Updated"));
    }
    
    @Test(priority=11,groups = { "Checker" },dependsOnMethods = "RejectforEditFaxTemplateRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(1);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxTemplateUpdate(faxTemplateDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
    //@Test(groups = { "Maker" })
    public void EditInvalidRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
        FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.EditInvalidRecord(faxTemplateDetails);
        Assert.assertFalse(faxTemplatePage.getErrorMsg(),"Invalid Record Assertion failed");
    }
    
    @AfterMethod
	    public void afterEachMethod(Method method){
	        Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("FaxTemplateUpdateTest",method.getName());
	        driver.navigate().refresh();
	        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	        homePage.userLogout();
	        driver.close();
	        System.out.println("Completed Executing : "+method.getName());
	    }
	
}
