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
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "IVR page assertion failed");
        ivrPage.navigateToIntroMessageAnnouncementPage();
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.isIntroMessageAnnouncementPageDisplayed(), "Branch Management page assertion failed");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test(groups= {"Maker"},priority=1)
    public void VerifyAddCancelButton() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.addCancelButton(IntroMessageAnnouncementDetails), "Add cancel button assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=2)
    public void VerifyAddRecordwithoutFunctionality() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutFunctionality(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=3)
    public void VerifyAddRecordWithoutHotLine() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutHotLine(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=4)
    public void VerifyAddRecordWithoutStartDateTime() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutStartDateTime(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=5)
    public void VerifyAddRecordWithoutEndDateTime() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutEndDateTime(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=6)
    public void VerifyAddRecordWithoutLanguage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutLanguage(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=7)
    public void VerifyAddRecordWithoutWaveFile() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutWaveFile(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    //@Test(groups = { "Maker" },priority=8)
    public void VerifyAddRecordWithoutStatus() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutStatus(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    //@Test(groups = { "Maker" },priority=9)
    public void VerifyAddRecordWithoutInterrupt() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addwithoutInterrupt(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    @Test(groups = { "Maker" },priority=10)
    public void AddRevertRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addNewIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails);
        Assert.assertEquals(IntroMessageAnnouncementPage.getSuccessMessage(), "Record created successfully");
       }
	
	@Test(groups = { "Maker" },priority=11)//,dependsOnMethods="AddRevertRecord")
    public void VerifyRevertForAddNewRecord() throws Exception {
       	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
       	IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
       	IntroMessageAnnouncementPage.selectRecord();
       	IntroMessageAnnouncementPage.Revert("revert");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyStatus("Reverted"),"approal status details failed");
    }
	
	@Test(groups= {"Maker"},priority=12,dependsOnMethods="VerifyRevertForAddNewRecord")
	public void VerifyApprovedDataSectionWithoutApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyApprovedSectionData(IntroMessageAnnouncementDetails));
	}
	
	@Test(groups = { "Maker" },priority=13,dependsOnMethods = "VerifyApprovedDataSectionWithoutApproval")
    public void VerifyAuditTrailReportForRevert() throws Exception {
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
        Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementCreate(IntroMessageAnnouncementDetails, "MakerReverted"),"Audit Trail report assertion failed");
    }
	@Test(groups = { "Maker" },priority=14)
    public void AddRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addNewIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails);
        Assert.assertEquals(IntroMessageAnnouncementPage.getSuccessMessage(), "Record created successfully");
       }
	
	@Test(groups = { "Maker" },priority=15,dependsOnMethods="AddRejectRecord")
    public void VerifySendForApprovalForAddNewRecord() throws Exception {
       	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
       	IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
       	IntroMessageAnnouncementPage.selectRecord();
       	IntroMessageAnnouncementPage.sendForAprroval("sent");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Checker" },priority=16,dependsOnMethods="VerifySendForApprovalForAddNewRecord")
    public void RejectforAddNewIntroMessageAnnouncementRecord() throws Exception{
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.clickonReject("Reject Created");
        Assert.assertFalse(IntroMessageAnnouncementPage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyReviewAuditTrail("Rejected","Reject Created"));
    }
    
    @Test(groups = { "Checker" },priority=17,dependsOnMethods = "RejectforAddNewIntroMessageAnnouncementRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
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
        Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementCreate(IntroMessageAnnouncementDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Maker"},priority=18,dependsOnMethods="VerifyAuditTrailReportForReject")
    public void VerifyRecordAfterRejection() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyApprovedSectionData(IntroMessageAnnouncementDetails));
    }
    
	@Test(groups = { "Maker" },priority=19)
    public void AddNewIntroMessageAnnouncementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addNewIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails);
        Assert.assertEquals(IntroMessageAnnouncementPage.getSuccessMessage(), "Record created successfully");
    }
	
	@Test(groups = { "Maker" },priority=20)//,dependsOnMethods = "AddNewIntroMessageAnnouncementRecord")
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
    
    @Test(groups = { "Maker" },priority=21)//,dependsOnMethods="AddNewIntroMessageAnnouncementRecord")
    public void VerifyAuditTrailDataForAddNewIntroMessageAnnouncementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyAuditTrail(IntroMessageAnnouncementDetails, "MakerCreate", "New"), "Audit trail details failed");
    }
    
    @Test(groups = { "Maker" },priority=22)
    public void VerifySendForApprovalForAddNewIntroMessageAnnouncementRecord() throws Exception {
       	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
       	IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
       	IntroMessageAnnouncementPage.selectRecord();
       	IntroMessageAnnouncementPage.sendForAprroval("sent");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Maker" },priority=23)//,dependsOnMethods = "VerifySendForApprovalForAddNewIntroMessageAnnouncementRecord")
    public void VerifyAuditTrailReportForSendForApproval() throws Exception {
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
        Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementCreate(IntroMessageAnnouncementDetails, "MakerSendToApproval"),"Audit Trail report assertion failed");
    }
    
	@Test(groups = { "Checker" },priority=24)//,dependsOnMethods="VerifyAuditTrailReportForSendForApproval")
    public void ApproveforAddNewIntroMessageAnnouncementRecord() throws Exception{
       	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
       	IntroMessageAnnouncementPage.clickonApprove("Approve Create");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyMessage());
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    
    @Test(groups = { "Checker" },priority=25)//,dependsOnMethods = "ApproveforAddNewIntroMessageAnnouncementRecord")
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
    
    @Test(groups= {"Checker"},priority=26,dependsOnMethods="VerifyAuditTrailReportForApprove")
    public void VerifyRecordApprovedDataSectionafterApproval()throws Exception{
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyApprovedSectionDataafterapproval(IntroMessageAnnouncementDetails));
    }
    
    @Test(groups = { "Maker" },priority=27)//,dependsOnMethods="AddNewIntroMessageAnnouncementRecord")
    public void AddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.addNewIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails);
        Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg(),"Duplicate assetion failed");
       }
        
    
    @AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("IntroMessageAnnouncementCreateTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}
