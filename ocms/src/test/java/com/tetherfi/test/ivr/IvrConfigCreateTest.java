package com.tetherfi.test.ivr;

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

import com.tetherfi.model.ivr.IvrConfigDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.sms.SmsResponseTemplateDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrConfigPage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.SmsResponseTemplatePage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class IvrConfigCreateTest {
	protected WebDriver driver;
	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
	public void NavigateToIvrConfigPage(Method method) throws Exception {
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
        try{driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}
        catch (TimeoutException e)
        
        {e.printStackTrace();
        }
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
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "ivr page assertion failed");
        ivrPage.navigateToIvrConfigPage();
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        Assert.assertTrue(ivrConfigPage.isIvrConfigPageDisplayed(), "Ivr config page assertion failed");
        screenshot.captureScreen(driver,"IvrConfigPage","IvrConfigTest");
    	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	@Test(priority=1, groups= {"Maker"})
	public void VerifyAddCancelButton() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		 IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		 IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		 Assert.assertTrue(ivrConfigPage.addCancelButton(IvrConfigDetails), "Add cancel Button Assertion failed");		
	}
	
	@Test(priority=2, groups={"Maker"})
	public void VerifyAddRecordWithoutParameter() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		 IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		 IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		 ivrConfigPage.addRecordWithoutParameter(IvrConfigDetails);
		 Assert.assertFalse(ivrConfigPage.getErrorMsg(),"Cancel assertion failed");				
	}
	
	@Test(priority=3,groups={"Maker"})
	public void VerifyAddRecordWithoutValue() throws Exception{
		String filePath= System.getProperty("user.dir") +"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		 IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		 IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		 ivrConfigPage.addRecordWithoutValue(IvrConfigDetails);
		 Assert.assertFalse(ivrConfigPage.getErrorMsg(), "Cancel assertion failed");		
	}
	
	@Test(priority=4,groups= {"Maker"})
	public void VerifyAddRevertRecord() throws Exception {
		String filePath= System.getProperty("user.dir") +"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String,String> map=new ExcelReader(filePath,"Create").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		IvrConfigPage ivrConfigPage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.addNewIvrConfigRecord(IvrConfigDetails);
	    Assert.assertEquals(ivrConfigPage.getSuccessMessage(),"Record Created Successfully");		
	}
	
	@Test(priority=5,groups= {"Maker"})//,dependsOnMethods="VerifyAddRevertRecord")
	public void VerifyRevertForAddNewRecord() throws Exception{
		IvrConfigPage ivrConfigPage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectRecord();
		ivrConfigPage.Revert("Revert");
		Assert.assertTrue(ivrConfigPage.verifyStatus("Reverted"), "Approval status details failed");			
	}
 	
	@Test(priority=6,groups= {"Maker"})//,dependsOnMethods="VerifyRevertForAddNewRecord")
	public void VerifyApprovedDataSectionWithoutApproval() throws Exception {
		String filePath= System.getProperty("user.dir") +"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		 IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		 IvrConfigPage ivrConfigpage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
		Assert.assertTrue(ivrConfigpage.VerifyApprovedSectionData(IvrConfigDetails));	
	}
	
	@Test(priority=7,groups={"Maker"})//,dependsOnMethods="VerifyApprovedDataSectionWithoutApproval")
	public void VerifyAuditTrailReportForRevert() throws Exception {
		String filePath= System.getProperty("user.dir") +"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIvrConfigCreate(IvrConfigDetails, "MakerReverted"),"Audit Trail report assertion failed");		
	}
	
	@Test(priority=8,groups= {"Maker"})
	public void AddRejectRecord() throws Exception {
		String filePath= System.getProperty("user.dir") +"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		 IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		 IvrConfigPage ivrConfigpage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
		 ivrConfigpage.addNewIvrConfigRecord(IvrConfigDetails);
		 Assert.assertEquals(ivrConfigpage.getSuccessMessage(), "Record Created Successfully");		 
	}
    
	@Test(groups = { "Maker" })//,priority=9,dependsOnMethods="AddRejectRecord")
    public void VerifySendForApprovalForAddNewRecord() throws Exception {
		IvrConfigPage ivrConfigpage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
       	ivrConfigpage.selectIvrConfigAuditTrailTab();
       	ivrConfigpage.selectRecord();
       	ivrConfigpage.sendForAprroval("sent");
        Assert.assertTrue(ivrConfigpage.verifyStatus("Approval Pending"),"approval status details failed");
    }

	@Test(groups = { "Checker" })//,priority=10,dependsOnMethods="VerifySendForApprovalForAddNewRecord")
    public void RejectforAddNewIvrConfigRecord() throws Exception{
		IvrConfigPage ivrConfigpage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigpage.clickOnReject("Reject Created");
        Assert.assertFalse(ivrConfigpage.verifyMessage(),"Reject record assertion failed");
        Assert.assertTrue(ivrConfigpage.verifyReviewAuditTrail("Rejected","Reject Created"));
    }
	
	@Test(groups = { "Checker" })//,priority=11,dependsOnMethods = "RejectforAddNewIvrConfigRecord")
    public void VerifyAuditTrailReportForReject() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIvrConfigCreate(IvrConfigDetails, "CheckerReject"),"Audit Trail report assertion failed");
    }
	
	@Test(groups= {"Maker"})//,priority=12,dependsOnMethods="VerifyAuditTrailReportForReject")
    public void VerifyRecordAfterRejection() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
        IvrConfigPage ivrConfigpage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
        Assert.assertTrue(ivrConfigpage.VerifyApprovedSectionData(IvrConfigDetails));
    }
	
	@Test(groups = { "Maker" })//,priority=13)
    public void AddNewIvrConfigRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
        IvrConfigPage ivrConfigpage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigpage.addNewIvrConfigRecord(IvrConfigDetails);
        Assert.assertEquals(ivrConfigpage.getSuccessMessage(), "Record Created Successfully");
    }
	
	@Test(groups = { "Maker" })//,priority=14,dependsOnMethods = "AddNewIvrConfigRecord")
    public void VerifyAuditTrailReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIvrConfigCreate(IvrConfigDetails, "MakerCreate"),"Audit Trail report assertion failed");
    }
	
	@Test(groups = { "Maker" })//,priority=15,dependsOnMethods="AddNewIvrConfigRecord")
    public void VerifyAuditTrailDataForAddNewIvrConfigRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
        IvrConfigPage ivrConfigpage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigpage.selectIvrConfigAuditTrailTab();
        Assert.assertTrue(ivrConfigpage.verifyAuditTrail(IvrConfigDetails, "MakerCreate", "New"), "Audit trail details failed");
    }
	
	@Test(groups = { "Maker" })//,priority=16,dependsOnMethods="VerifyAuditTrailDataForAddNewIvrConfigRecord")
    public void VerifySendForApprovalForAddIvrConfigRecord() throws Exception {
		  IvrConfigPage ivrConfigpage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
       	ivrConfigpage.selectIvrConfigAuditTrailTab();
       	ivrConfigpage.selectRecord();
       	ivrConfigpage.sendForAprroval("sent");
        Assert.assertTrue(ivrConfigpage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
	@Test(groups = { "Maker" })//,priority=17,dependsOnMethods = "VerifySendForApprovalForAddIvrConfigRecord")
    public void VerifyAuditTrailReportForSendForApproval() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIvrConfigCreate(IvrConfigDetails, "MakerSendToApproval"),"Audit Trail report assertion failed");
    }
	
	@Test(groups = { "Checker" })//,priority=18,dependsOnMethods="VerifyAuditTrailReportForSendForApproval")
    public void ApproveforAddNewIvrConfigRecord() throws Exception{
       	IvrConfigPage ivrConfigpage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
       	ivrConfigpage.clickOnApprove("Approve Create");
        Assert.assertTrue(ivrConfigpage.verifyMessage());
        Assert.assertTrue(ivrConfigpage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
	
	@Test(groups = { "Checker" })//,priority=19,dependsOnMethods = "ApproveforAddNewIvrConfigRecord")
    public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIvrConfigCreate(IvrConfigDetails, "CheckerApprove"),"Audit Trail report assertion failed");
    }
	
	@Test(groups= {"Checker"})//,priority=20,dependsOnMethods="ApproveforAddNewIvrConfigRecord")
    public void VerifyRecordApprovedDataSectionafterApproval()throws Exception{
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
        IvrConfigPage ivrConfigpage=PageFactory.createPageInstance(driver,IvrConfigPage.class);
        Assert.assertTrue(ivrConfigpage.verifyApprovedSectionDataafterapproval(IvrConfigDetails));
    }
	
    @Test(groups = { "Maker" })//,priority=21,dependsOnMethods="AddNewIvrConfigRecord")
    public void AddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
        IvrConfigPage ivrConfigpage=PageFactory.createPageInstance(driver,IvrConfigPage.class);
        ivrConfigpage.addNewIvrConfigRecord(IvrConfigDetails);
        Assert.assertFalse(ivrConfigpage.getErrorMsg(),"Duplicate assetion failed");
    }
	
	@AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("IvrConfigCreateTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }	
}


        
     
