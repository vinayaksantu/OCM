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

import com.tetherfi.model.user.UserDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.RoleBasedAccessManagementPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class RoleBasedAccessManagementCreateTest {
	
	protected WebDriver driver;
	@BeforeMethod
    public void NavigateToNewRoleBasedAccessManagementPage(Method method) throws Exception {
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
        ocmHomePage.navigateToRoleBasedAccessManagementPage();
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        Assert.assertTrue(roleBasedAccessManagementPage.isRoleBasedAccessManagementPageDisplayed(), "Role Based Access Management page assertion failed");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	
	@Test(groups= {"Maker"},priority=1)
    public void VerifyAddCancelButton() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        Assert.assertTrue(roleBasedAccessManagementPage.addCancelButton(userDetails), "Add cancel button assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=2)
    public void VerifyAddRecordWithoutRole() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        roleBasedAccessManagementPage.addwithoutRole(userDetails);
        Assert.assertEquals(roleBasedAccessManagementPage.getErrorMsg(),userDetails.getErrorMsg1());
    }   
    
	@Test(groups = { "Maker" },priority=3)
    public void AddRevertRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        roleBasedAccessManagementPage.addRoleBasedAccessManagementRecord(userDetails);
        Assert.assertEquals(roleBasedAccessManagementPage.getSuccessMessage(), "Record Created Successfully");
       }
	
	@Test(groups = { "Maker" },priority=4,dependsOnMethods="AddRevertRecord")
    public void VerifyRevertForAddNewRecord() throws Exception {
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
       	roleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
       	roleBasedAccessManagementPage.selectRecord();
       	roleBasedAccessManagementPage.Revert("revert");
        Assert.assertTrue(roleBasedAccessManagementPage.verifyStatus("Reverted"),"approal status details failed");
    }
	
	@Test(groups= {"Maker"},priority=5,dependsOnMethods="VerifyRevertForAddNewRecord")
	public void VerifyApprovedDataSectionWithoutApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        Assert.assertTrue(roleBasedAccessManagementPage.verifyApprovedSectionData(userDetails));
	}
	
	@Test(groups = { "Maker" },priority=6)
    public void VerifyAuditTrailReportForRevert() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementCreate(userDetails, "MakerReverted"),"Audit Trail report assertion failed");
    }
	
	@Test(groups = { "Maker" },priority=7)
    public void AddRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        roleBasedAccessManagementPage.addRoleBasedAccessManagementRecord(userDetails);
        Assert.assertEquals(roleBasedAccessManagementPage.getSuccessMessage(), "Record Created Successfully");
       }
	
	@Test(groups = { "Maker" },priority=8,dependsOnMethods="AddRejectRecord")
    public void VerifySendForApprovalForAddNewRecord() throws Exception {
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
       	roleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
       	roleBasedAccessManagementPage.selectRecord();
       	roleBasedAccessManagementPage.sendForAprroval("sent");
        Assert.assertTrue(roleBasedAccessManagementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Checker" },priority=9,dependsOnMethods="VerifySendForApprovalForAddNewRecord")
    public void RejectforAddNewNewRoleBasedAccessManagementRecord() throws Exception{
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        roleBasedAccessManagementPage.clickonReject("Reject Created");
        Assert.assertFalse(roleBasedAccessManagementPage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(roleBasedAccessManagementPage.verifyReviewAuditTrail("Rejected","Reject Created"));
    }
    
    @Test(groups = { "Checker" },priority=10,dependsOnMethods = "RejectforAddNewNewRoleBasedAccessManagementRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementCreate(userDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Maker"},priority=11,dependsOnMethods="VerifyAuditTrailReportForReject")
    public void VerifyRecordAfterRejection() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        Assert.assertTrue(roleBasedAccessManagementPage.verifyApprovedSectionData(userDetails));
    }
	
    @Test(groups = { "Maker" },priority=12)
    public void AddNewNewRoleBasedAccessManagementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        roleBasedAccessManagementPage.addRoleBasedAccessManagementRecord(userDetails);
        Assert.assertEquals(roleBasedAccessManagementPage.getSuccessMessage(), "Record Created Successfully");
    }
	
	@Test(groups = { "Maker" },priority=13,dependsOnMethods = "AddNewNewRoleBasedAccessManagementRecord")
    public void VerifyAuditTrailReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementCreate(userDetails, "MakerCreate"),"Audit Trail report assertion failed");
    }
    
    @Test(groups = { "Maker" },priority=14)//,dependsOnMethods="AddNewNewRoleBasedAccessManagementRecord")
    public void VerifyAuditTrailDataForAddNewNewRoleBasedAccessManagementRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        roleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        Assert.assertTrue(roleBasedAccessManagementPage.verifyAuditTrail(userDetails, "MakerCreate", "New"), "Audit trail details failed");
    }
    
    @Test(groups = { "Maker" },priority=15)
    public void VerifySendForApprovalForAddNewNewRoleBasedAccessManagementRecord() throws Exception {
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
       	roleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
       	roleBasedAccessManagementPage.selectRecord();
       	roleBasedAccessManagementPage.sendForAprroval("sent");
        Assert.assertTrue(roleBasedAccessManagementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Maker" },priority=16,dependsOnMethods = "VerifySendForApprovalForAddNewNewRoleBasedAccessManagementRecord")
    public void VerifyAuditTrailReportForSendForApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementCreate(userDetails, "MakerSendToApproval"),"Audit Trail report assertion failed");
    }
    
	@Test(groups = { "Checker" },priority=17)
    public void ApproveforAddNewNewRoleBasedAccessManagementRecord() throws Exception{
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
       	roleBasedAccessManagementPage.clickonApprove("Approve Create");
        Assert.assertTrue(roleBasedAccessManagementPage.verifyMessage());
        Assert.assertTrue(roleBasedAccessManagementPage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    
    @Test(groups = { "Checker" },priority=18,dependsOnMethods = "ApproveforAddNewNewRoleBasedAccessManagementRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementCreate(userDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
    
    @Test(groups= {"Checker"},priority=19,dependsOnMethods="ApproveforAddNewNewRoleBasedAccessManagementRecord")
    public void VerifyRecordApprovedDataSectionafterApproval()throws Exception{
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        Assert.assertTrue(roleBasedAccessManagementPage.verifyApprovedSectionDataafterapproval(userDetails));
    }
    
    @Test(groups = { "Maker" },priority=20,enabled=true)//,dependsOnMethods="AddNewNewRoleBasedAccessManagementRecord")
    public void AddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        roleBasedAccessManagementPage.addRoleBasedAccessManagementRecord(userDetails);
        Assert.assertEquals(roleBasedAccessManagementPage.getErrorMsg(),"Duplicate RoleName","Duplicate assetion failed");
    }
   
    @AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("RoleBasedAccessManagementCreateTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }

}
