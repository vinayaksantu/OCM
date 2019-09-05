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
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.HostValueMappingPage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class HostValueMappingCreateTest {
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
        HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertTrue(hostValueMappingPage.isHostValueMappingPageDisplayed(), "Branch Management page assertion failed");
    }
	
	/*@Test(groups= {"Maker"})
    public void VerifyAddCancelButton() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails hostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertTrue(hostValueMappingPage.addCancelButton(hostValueMappingDetails), "Add cancel button assertion failed");
    }
	
	@Test(groups = { "Maker" })
    public void AddNewHostValueMappingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails hostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        hostValueMappingPage.addNewHostValueMappingRecord(hostValueMappingDetails);
        Assert.assertEquals(hostValueMappingPage.getSuccessMessage(), "Record created successfully");
    }
	
	@Test(groups = { "Maker" })//,dependsOnMethods = "AddNewHostValueMappingRecord")
    public void VerifyAuditTrailReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    HostValueMappingDetails hostValueMappingDetails = new HostValueMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyHostValueMappingCreate(hostValueMappingDetails, "MakerCreate"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" })//,dependsOnMethods="AddNewHostValueMappingRecord")
    public void VerifyAuditTrailDataForAddNewHostValueMappingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails hostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        hostValueMappingPage.selectHostValueMappingAuditTrailTab();
        Assert.assertTrue(hostValueMappingPage.verifyAuditTrail(hostValueMappingDetails, "MakerCreate", "New"), "Audit trail details failed");
        hostValueMappingPage.selectMakeHostValueMappingChanges();
        Assert.assertTrue(hostValueMappingPage.verifyTaskCompleteEnabled(), "Task complete button not enabled");
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="VerifyAuditTrailDataForAddNewHostValueMappingRecord")
    public void VerifyTaskCompleteActionForAddNewHostValueMappingRecord() {
       	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
       	hostValueMappingPage.selectHostValueMappingAuditTrailTab();
       	hostValueMappingPage.taskCompleteAction("Task Complete for Create");
        Assert.assertTrue(hostValueMappingPage.verifyTaskCompleteEnabled(),"Task Complete record assertion failed");
        Assert.assertTrue(hostValueMappingPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForAddNewHostValueMappingRecord")
    public void ApproveforAddNewHostValueMappingRecord(){
       	HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
       	HostValueMappingPage.clickonApprove("Approve Create");
        Assert.assertEquals(HostValueMappingPage.getSuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(HostValueMappingPage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    
    @Test(groups = { "Checker" },dependsOnMethods = "ApproveforAddNewHostValueMappingRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyHostValueMappingCreate(HostValueMappingDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Checker"},dependsOnMethods="ApproveforAddNewHostValueMappingRecord")
    public void VerifyRecordApprovedDataSectionafterApproval()throws Exception{
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails hostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertTrue(hostValueMappingPage.verifyApprovedSectionDataafterapproval(hostValueMappingDetails));
    }
    
    @Test(groups = { "Maker" })//,dependsOnMethods="AddNewHostValueMappingRecord")
    public void AddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails hostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        hostValueMappingPage.addNewHostValueMappingRecord(hostValueMappingDetails);
        Assert.assertFalse(hostValueMappingPage.getErrorMsg(),"Duplicate assetion failed");
    }
    
   @Test(groups = { "Maker" })
    public void VerifyAddRecordwithoutFunctionality() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addwithoutFunctionality(HostValueMappingDetails);
        Assert.assertFalse(HostValueMappingPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutLanguage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addwithoutLanguage(HostValueMappingDetails);
        Assert.assertFalse(HostValueMappingPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutHostData() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addwithoutHostData(HostValueMappingDetails);
        Assert.assertFalse(HostValueMappingPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutStatus() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addwithoutStatus(HostValueMappingDetails);
        Assert.assertFalse(HostValueMappingPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutDescription() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addwithoutDescription(HostValueMappingDetails);
        Assert.assertFalse(HostValueMappingPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutWaveFile() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addwithoutWaveFile(HostValueMappingDetails);
        Assert.assertFalse(HostValueMappingPage.getErrorMsg());
    }
    */
   
    //@Test(groups = { "Maker" })
    public void AddRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.addNewHostValueMappingRecord(HostValueMappingDetails);
        Assert.assertEquals(HostValueMappingPage.getSuccessMessage(), "Record created successfully");
       }
	
	//@Test(groups = { "Maker" })//,dependsOnMethods="AddRecord")
    public void VerifyTaskCompleteActionForAddNewRecord() throws Exception {
       	HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
       	HostValueMappingPage.selectHostValueMappingAuditTrailTab();
       	HostValueMappingPage.taskCompleteAction("Task Complete for Create");
        Assert.assertTrue(HostValueMappingPage.verifyTaskCompleteEnabled(),"Task Complete record assertion failed");
        Assert.assertTrue(HostValueMappingPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	//@Test(groups= {"Maker"})//, dependsOnMethods="AddRecord")
	public void VerifyApprovedDataSectionWithoutApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertTrue(HostValueMappingPage.verifyApprovedSectionData(HostValueMappingDetails));
	}
    
    @Test(groups = { "Checker" })//,dependsOnMethods="VerifyApprovedDataSectionWithoutApproval")
    public void RejectforAddNewHostValueMappingRecord() throws Exception{
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.clickonReject("Reject Created");
        Assert.assertFalse(HostValueMappingPage.getErrorMsg(),"Reject record assertion failed");
        Assert.assertTrue(HostValueMappingPage.verifyReviewAuditTrail("Rejected","Reject Created"));
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="RejectforAddNewHostValueMappingRecord")
    public void VerifyMakeHostValueMappingButtonafterRejection() {
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        HostValueMappingPage.selectHostValueMappingAuditTrailTab();
        Assert.assertTrue(HostValueMappingPage.VerifyMakeHostValueMappingChangeButton());
    }
    
    @Test(groups = { "Checker" },dependsOnMethods = "VerifyMakeHostValueMappingButtonafterRejection")
    public void VerifyAuditTrailReportForReject() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyHostValueMappingCreate(HostValueMappingDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Maker"},dependsOnMethods="VerifyAuditTrailReportForReject")
    public void VerifyRecordAfterRejection() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    HostValueMappingDetails HostValueMappingDetails = new HostValueMappingDetails(map);
        HostValueMappingPage HostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertTrue(HostValueMappingPage.verifyApprovedSectionData(HostValueMappingDetails));
    }
    
    @AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("HostValueMappingCreateTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}
