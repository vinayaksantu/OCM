package com.tetherfi.test;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.user.UserRoleMappingDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.NewUserRoleMappingPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class UserRoleMappingCreateTest {
	
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateToNewUserRoleMappingPage(Method method) throws Exception {
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
        ocmHomePage.navigateToUserRoleMappingPage();
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        Assert.assertTrue(NewUserRoleMappingPage.isUserRoleMappingPageDisplayed(), "User Role Mapping page assertion failed");
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	
	@Test(groups= {"Maker"},priority=1)
    public void VerifyAddCancelButton() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        Assert.assertTrue(NewUserRoleMappingPage.addCancelButton(UserRoleMappingDetails), "Add cancel button assertion failed");
    }
	
	@Test(groups = { "Maker" },priority=2)
    public void VerifyAddRecordwithoutFirstName() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.addwithoutFirstName(UserRoleMappingDetails);
        Assert.assertEquals(NewUserRoleMappingPage.VerifyMessage(), "Please Provide First Name","Error Message Assertion failed");
    }
    
   @Test(groups = { "Maker" },priority=3)
    public void VerifyAddRecordWithoutLastName() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.addwithoutLastName(UserRoleMappingDetails);
        Assert.assertEquals(NewUserRoleMappingPage.VerifyMessage(), "Please Provide Last Name","Error Message Assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=4)
    public void VerifyAddRecordWithoutLanId() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.addwithoutLanID(UserRoleMappingDetails);
        Assert.assertEquals(NewUserRoleMappingPage.VerifyMessage(), "Please Provide Lan ID","Error Message Assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=5)
    public void VerifyAddRecordWithoutLoginID() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.addwithoutLoginID(UserRoleMappingDetails);
        Assert.assertEquals(NewUserRoleMappingPage.VerifyMessage(), "Please Provide Login ID","Error Message Assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=6)
    public void VerifyAddRecordWithoutOrgUnitANDProfileAndSupervisor() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.addwithoutOrgUnit(UserRoleMappingDetails);
        Assert.assertEquals(NewUserRoleMappingPage.VerifyMessage(), "Please Provide Org. Unit, Profile, Supervisor","Error Message Assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=7)
    public void VerifyAddRecordWithoutProfile() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.addwithoutProfile(UserRoleMappingDetails);
        Assert.assertEquals(NewUserRoleMappingPage.VerifyMessage(), "Please Provide Profile","Error Message Assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=8)
    public void VerifyAddRecordWithoutSupervisor() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.addwithoutSupervisor(UserRoleMappingDetails);
        Assert.assertEquals(NewUserRoleMappingPage.VerifyMessage(), "Please Provide Supervisor","Error Message Assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=9)
    public void VerifyAddRecordWithoutRole() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.addwithoutRole(UserRoleMappingDetails);
        Assert.assertEquals(NewUserRoleMappingPage.VerifyMessage(), "Please Provide Role","Error Message Assertion failed");
    }  
    
	@Test(groups = { "Maker" },priority=10)
    public void AddRevertRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.addNewUserRoleMappingRecord(UserRoleMappingDetails);
        //Assert.assertEquals(NewUserRoleMappingPage.getSuccessMessage(), "Record Created Successfully");
       }
	
	@Test(groups = { "Maker" },priority=11,dependsOnMethods="AddRevertRecord")
    public void VerifyRevertForAddNewRecord() throws Exception {
       	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
       	NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
       	NewUserRoleMappingPage.selectRecord();
       	NewUserRoleMappingPage.Revert("revert");
        Assert.assertTrue(NewUserRoleMappingPage.verifyStatus("Reverted"),"approal status details failed");
    }
	
	@Test(groups= {"Maker"},priority=12,dependsOnMethods="VerifyRevertForAddNewRecord")
	public void VerifyApprovedDataSectionWithoutApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        Assert.assertTrue(NewUserRoleMappingPage.verifyApprovedSectionData(UserRoleMappingDetails));
	}
	
	@Test(groups = { "Maker" },priority=13,dependsOnMethods = "VerifyApprovedDataSectionWithoutApproval")
    public void VerifyAuditTrailReportForRevert() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingCreate(UserRoleMappingDetails, "MakerReverted"),"Audit Trail report assertion failed");
    }
	
	@Test(groups = { "Maker" },priority=14)
    public void AddRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.addNewUserRoleMappingRecord(UserRoleMappingDetails);
       // Assert.assertEquals(NewUserRoleMappingPage.getSuccessMessage(), "Record Created Successfully");
       }
	
	@Test(groups = { "Maker" },priority=15,dependsOnMethods="AddRejectRecord")
    public void VerifySendForApprovalForAddNewRecord() throws Exception {
       	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
       	NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
       	NewUserRoleMappingPage.selectRecord();
       	NewUserRoleMappingPage.sendForAprroval("sent");
        Assert.assertTrue(NewUserRoleMappingPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Checker" },priority=16,dependsOnMethods="VerifySendForApprovalForAddNewRecord")
    public void RejectforAddNewNewUserRoleMappingRecord() throws Exception{
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.clickonReject("Reject Created");
        Assert.assertFalse(NewUserRoleMappingPage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(NewUserRoleMappingPage.verifyReviewAuditTrail("Rejected","Reject Created"));
    }
    
    @Test(groups = { "Checker" },priority=17,dependsOnMethods = "RejectforAddNewNewUserRoleMappingRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingCreate(UserRoleMappingDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Maker"},priority=18,dependsOnMethods="VerifyAuditTrailReportForReject")
    public void VerifyRecordAfterRejection() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        Assert.assertTrue(NewUserRoleMappingPage.verifyApprovedSectionData(UserRoleMappingDetails));
    }
	@Test(groups = { "Maker" },priority=19)
    public void AddNewNewUserRoleMappingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.addNewUserRoleMappingRecord(UserRoleMappingDetails);
        //Assert.assertEquals(NewUserRoleMappingPage.getSuccessMessage(), "Record Created Successfully");
    }
	
	@Test(groups = { "Maker" },priority=20,dependsOnMethods = "AddNewNewUserRoleMappingRecord")
    public void VerifyAuditTrailReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingCreate(UserRoleMappingDetails, "MakerCreate"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=21)//,dependsOnMethods="AddNewNewUserRoleMappingRecord")
    public void VerifyAuditTrailDataForAddNewNewUserRoleMappingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        Assert.assertTrue(NewUserRoleMappingPage.verifyAuditTrail(UserRoleMappingDetails, "MakerCreate", "New"), "Audit trail details failed");
    }
    
    @Test(groups = { "Maker" },priority=22,dependsOnMethods="VerifyAuditTrailDataForAddNewNewUserRoleMappingRecord")
    public void VerifySendForApprovalForAddNewNewUserRoleMappingRecord() throws Exception {
       	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
       	NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
       	NewUserRoleMappingPage.selectRecord();
       	NewUserRoleMappingPage.sendForAprroval("sent");
        Assert.assertTrue(NewUserRoleMappingPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Maker" },priority=23,dependsOnMethods = "VerifySendForApprovalForAddNewNewUserRoleMappingRecord")
    public void VerifyAuditTrailReportForSendForApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingCreate(UserRoleMappingDetails, "MakerSendToApproval"),"Audit Trail report assertion failed");
    }
    
	@Test(groups = { "Checker" },priority=24,dependsOnMethods="VerifyAuditTrailReportForSendForApproval")
    public void ApproveforAddNewNewUserRoleMappingRecord() throws Exception{
       	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
       	NewUserRoleMappingPage.clickonApprove("Approve Create");
        Assert.assertTrue(NewUserRoleMappingPage.verifyMessage());
        Assert.assertTrue(NewUserRoleMappingPage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    
    @Test(groups = { "Checker" },priority=25,dependsOnMethods = "ApproveforAddNewNewUserRoleMappingRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingCreate(UserRoleMappingDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Checker"},priority=26,dependsOnMethods="ApproveforAddNewNewUserRoleMappingRecord")
    public void VerifyRecordApprovedDataSectionafterApproval()throws Exception{
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        Assert.assertTrue(NewUserRoleMappingPage.verifyApprovedSectionDataafterapproval(UserRoleMappingDetails));
    }
    
    @Test(groups = { "Maker" },priority=27)//,dependsOnMethods="AddNewNewUserRoleMappingRecord")
    public void AddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.addNewUserRoleMappingRecord(UserRoleMappingDetails);
        Assert.assertFalse(NewUserRoleMappingPage.getErrorMsg(),"Duplicate assetion failed");
    }
   
    @AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("NewUserRoleMappingCreateTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }

}
