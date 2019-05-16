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


import com.tetherfi.model.ivr.BranchManagementDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.BranchManagementPage;

import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class BranchManagementCreateTest {
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateToBranchManagementPage(Method method) throws IOException, InterruptedException {
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
        ivrPage.navigateToBranchManagementPage();
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        Assert.assertTrue(branchManagementPage.isBranchManagementPageDisplayed(), "Branch Management page assertion failed");
    }
	
	@Test(groups= {"Maker"})
    public void VerifyAddCancelButton() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        Assert.assertTrue(branchManagementPage.addCancelButton(branchManagementDetails), "Add cancel button assertion failed");
    }
	
	@Test(groups = { "Maker" })
    public void AddNewBranchManagementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addNewBranchManagementRecord(branchManagementDetails);
        Assert.assertEquals(branchManagementPage.getSuccessMessage(), "Record Created Successfully");
    }
	
	@Test(groups = { "Maker" },dependsOnMethods = "AddNewBranchManagementRecord")
    public void VerifyAuditTrailReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementCreate(branchManagementDetails, "MakerCreate"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="AddNewBranchManagementRecord")
    public void VerifyAuditTrailDataForAddNewBranchManagementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.selectBranchManagementAuditTrailTab();
        Assert.assertTrue(branchManagementPage.verifyAuditTrail(branchManagementDetails, "MakerCreate", "New"), "Audit trail details failed");
        branchManagementPage.selectMakeBranchManagementChanges();
        Assert.assertTrue(branchManagementPage.verifyTaskCompleteEnabled(), "Task complete button not enabled");
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="VerifyAuditTrailDataForAddNewBranchManagementRecord")
    public void VerifyTaskCompleteActionForAddNewBranchManagementRecord() {
       	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
       	branchManagementPage.selectBranchManagementAuditTrailTab();
       	branchManagementPage.taskCompleteAction("Task Complete for Create");
        Assert.assertTrue(branchManagementPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(branchManagementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Checker" },dependsOnMethods="VerifyTaskCompleteActionForAddNewBranchManagementRecord")
    public void ApproveforAddNewBranchManagementRecord(){
       	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
       	branchManagementPage.clickonApprove("Approve Create");
        Assert.assertEquals(branchManagementPage.getSuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(branchManagementPage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    
    @Test(groups = { "Checker" },dependsOnMethods = "ApproveforAddNewBranchManagementRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementCreate(branchManagementDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Checker"},dependsOnMethods="ApproveforAddNewBranchManagementRecord")
    public void VerifyRecordApprovedDataSectionafterApproval()throws Exception{
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        Assert.assertTrue(branchManagementPage.verifyApprovedSectionDataafterapproval(branchManagementDetails));
    }
    
    @Test(groups = { "Maker" })//,dependsOnMethods="AddNewBranchManagementRecord")
    public void AddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addNewBranchManagementRecord(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg(),"Duplicate assetion failed");
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordwithoutMainLines() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutMainLines(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutSubLines() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutSubLines(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutLocation() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutLocation(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutBranchType() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutBranchType(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutBranchName() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutBranchName(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutAddress() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutAddress(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutLineOrder() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutLineOrder(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutStatus() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutStatus(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" })
    public void VerifyAddRecordWithoutLanguage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addwithoutLanguage(branchManagementDetails);
        Assert.assertFalse(branchManagementPage.getErrorMsg());
    }
    
	@Test(groups = { "Maker" })
    public void AddRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.addNewBranchManagementRecord(branchManagementDetails);
        Assert.assertEquals(branchManagementPage.getSuccessMessage(), "Record Created Successfully");
       }
	
	@Test(groups = { "Maker" },dependsOnMethods="AddRecord")
    public void VerifyTaskCompleteActionForAddRecord() {
       	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
       	branchManagementPage.selectBranchManagementAuditTrailTab();
       	branchManagementPage.taskCompleteAction("Task Complete for Create");
        Assert.assertTrue(branchManagementPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(branchManagementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
	
	@Test(groups= {"Maker"}, dependsOnMethods="AddRecord")
	public void VerifyApprovedDataSectionWithoutApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        Assert.assertTrue(branchManagementPage.verifyApprovedSectionData(branchManagementDetails));
	}
    
    @Test(groups = { "Checker" },dependsOnMethods="VerifyApprovedDataSectionWithoutApproval")
    public void RejectforAddNewBranchManagementRecord(){
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.clickonReject("Reject Created");
        Assert.assertFalse(branchManagementPage.getErrorMsg(),"Reject record assertion failed");
        Assert.assertTrue(branchManagementPage.verifyReviewAuditTrail("Rejected","Reject Created"));
    }
    
    @Test(groups = { "Maker" },dependsOnMethods="RejectforAddNewBranchManagementRecord")
    public void VerifyMakeBranchManagementButtonafterRejection() {
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.selectBranchManagementAuditTrailTab();
        Assert.assertTrue(branchManagementPage.VerifyMakeBranchManagementChangeButton());
    }
    
    @Test(groups = { "Checker" },dependsOnMethods = "VerifyMakeBranchManagementButtonafterRejection")
    public void VerifyAuditTrailReportForReject() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementCreate(branchManagementDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Maker"},dependsOnMethods="VerifyAuditTrailReportForReject")
    public void VerifyRecordAfterRejection() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        Assert.assertTrue(branchManagementPage.verifyApprovedSectionData(branchManagementDetails));
    }
    
    @AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("FaxTemplateTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}
