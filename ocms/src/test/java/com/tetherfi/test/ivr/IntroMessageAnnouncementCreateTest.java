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

import com.tetherfi.model.ivr.IntroMessageAnnouncementDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.IntroMessageAnnouncementPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class IntroMessageAnnouncementCreateTest {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateToIntroMessageAnnouncementPage(Method method) throws IOException, InterruptedException {
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
        ivrPage.navigateToIntroMessageAnnouncementPage();
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.isIntroMessageAnnouncementPageDisplayed(), "Branch Management page assertion failed");
    }
	
	//@Test(groups= {"Maker"})
    public void VerifyAddCancelButton() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.addCancelButton(IntroMessageAnnouncementDetails), "Add cancel button assertion failed");
    }
	
	@Test(groups = { "Maker" })
    public void AddNewIntroMessageAnnouncementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addNewIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails);
        Assert.assertEquals(IntroMessageAnnouncementPage.getSuccessMessage(), "Record created successfully");
    }
	
	//@Test(groups = { "Maker" },dependsOnMethods = "AddNewIntroMessageAnnouncementRecord")
    public void VerifyAuditTrailReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementCreate(IntroMessageAnnouncementDetails, "MakerCreate"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" })//,dependsOnMethods="AddNewIntroMessageAnnouncementRecord")
    public void VerifyAuditTrailDataForAddNewIntroMessageAnnouncementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyAuditTrail(IntroMessageAnnouncementDetails, "MakerCreate", "New"), "Audit trail details failed");
        IntroMessageAnnouncementPage.selectMakeIntroMessageAnnouncementChanges();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyTaskCompleteEnabled(), "Task complete button not enabled");
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="VerifyAuditTrailDataForAddNewIntroMessageAnnouncementRecord")
    public void VerifyTaskCompleteActionForAddNewIntroMessageAnnouncementRecord() {
       	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
       	IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
       	IntroMessageAnnouncementPage.taskCompleteAction("Task Complete for Create");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForAddNewIntroMessageAnnouncementRecord")
    public void ApproveforAddNewIntroMessageAnnouncementRecord(){
       	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
       	IntroMessageAnnouncementPage.clickonApprove("Approve Create");
        Assert.assertEquals(IntroMessageAnnouncementPage.getSuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    
    @Test(groups = { "Checker" },dependsOnMethods = "ApproveforAddNewIntroMessageAnnouncementRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementCreate(IntroMessageAnnouncementDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
   /* @Test(groups= {"Checker"},dependsOnMethods="ApproveforAddNewIntroMessageAnnouncementRecord")
    public void VerifyRecordApprovedDataSectionafterApproval()throws Exception{
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyApprovedSectionDataafterapproval(IntroMessageAnnouncementDetails));
    }
    
    @Test(groups = { "Maker" })//,dependsOnMethods="AddNewIntroMessageAnnouncementRecord")
    public void AddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addNewIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg(),"Duplicate assetion failed");
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordwithoutMainLines() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutMainLines(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutSubLines() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutSubLines(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutLocation() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutLocation(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutBranchType() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutBranchType(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutBranchName() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutBranchName(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutAddress() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutAddress(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutLineOrder() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutLineOrder(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutStatus() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutStatus(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutLanguage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutLanguage(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
	@Test(groups = { "Maker" })
    public void AddRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addNewIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails);
        Assert.assertEquals(IntroMessageAnnouncementPage.getSuccessMessage(), "Record Created Successfully");
       }
	
	@Test(groups = { "Maker" },dependsOnMethods="AddRecord")
    public void VerifyTaskCompleteActionForAddRecord() {
       	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
       	IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
       	IntroMessageAnnouncementPage.taskCompleteAction("Task Complete for Create");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	@Test(groups= {"Maker"}, dependsOnMethods="AddRecord")
	public void VerifyApprovedDataSectionWithoutApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyApprovedSectionData(IntroMessageAnnouncementDetails));
	}
    
    @Test(groups = { "Checker" },dependsOnMethods="VerifyApprovedDataSectionWithoutApproval")
    public void RejectforAddNewIntroMessageAnnouncementRecord(){
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.clickonReject("Reject Created");
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg(),"Reject record assertion failed");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyReviewAuditTrail("Rejected","Reject Created"));
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="RejectforAddNewIntroMessageAnnouncementRecord")
    public void VerifyMakeIntroMessageAnnouncementButtonafterRejection() {
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        Assert.assertTrue(IntroMessageAnnouncementPage.VerifyMakeIntroMessageAnnouncementChangeButton());
    }
    
    @Test(groups = { "Checker" },dependsOnMethods = "VerifyMakeIntroMessageAnnouncementButtonafterRejection")
    public void VerifyAuditTrailReportForReject() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementCreate(IntroMessageAnnouncementDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Maker"},dependsOnMethods="VerifyAuditTrailReportForReject")
    public void VerifyRecordAfterRejection() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyApprovedSectionData(IntroMessageAnnouncementDetails));
    }
    */
    @AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("IntroMessageAnnouncementTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}
