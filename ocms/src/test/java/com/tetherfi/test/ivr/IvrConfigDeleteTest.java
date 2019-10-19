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
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrConfigPage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class IvrConfigDeleteTest {
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
	
	/*@Test(groups= {"Maker"},priority=1)
	public void DeleteCancelIvrConfigRecord() throws Exception {
	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
	Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
	IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
	IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
	Assert.assertTrue(ivrConfigPage.DeleteCancel(IvrConfigDetails), "Delete Cancel Assertion Failed");		
	}
	
	
	@Test(priority=2,groups= {"Maker"})
	public void DeleteWithoutModifyReasonRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.DeleteRecordWithoutModifyReason(IvrConfigDetails);
		Assert.assertFalse(ivrConfigPage.getErrorMsg(),"Invalid Record Assertion Failed");		
	}
	
	@Test(priority=3,groups= {"Maker"})
	public void DeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.deleteIvrConfigRecord(IvrConfigDetails);
		Assert.assertEquals(ivrConfigPage.getSuccessMessage(),"Record Deleted Successfully");		
	}*/
	
	@Test(priority=4,groups= {"Maker"})
	public void VerifyRevertForDeleteRecord() throws Exception {
	IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
	ivrConfigPage.selectIvrConfigAuditTrailTab();
	ivrConfigPage.selectRecord();
	ivrConfigPage.Revert("Reverted");
	Assert.assertTrue(ivrConfigPage.verifyStatus("Reverted"),"Approval Status Details Failed");		
	}
	
	/*@Test(priority=5,groups= {"Maker"})
	public void VerifyAuditTrialReportForRevertDelete() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIvrConfigdelete(IvrConfigDetails, "MakerReverted"));		
	}
	
	@Test(priority=6,groups= {"Maker"})
	public void RejectDeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.deleteIvrConfigRecord(IvrConfigDetails);
		Assert.assertEquals(ivrConfigPage.getSuccessMessage(),"Record Deleted Successfully");	
	}
	
	@Test(priority=7,groups= {"Maker"})
	public void VerifySendForApprovalForDeleteNewRecord() throws Exception {
		IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectRecord();
		ivrConfigPage.sendForAprroval("Sent");
		Assert.assertTrue(ivrConfigPage.verifyStatus("Approval Pending"),"Approval Status Details Failed");	
	}
	
	@Test(priority=8,groups= {"Checker"})
	public void RejectforDeleteIvrConfigRecord() throws Exception {
		IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.clickOnReject("Reject Deleted");
		Assert.assertFalse(ivrConfigPage.getErrorMsg(),"Reject Assertion Failed");
		Assert.assertTrue(ivrConfigPage.verifyReviewAuditTrail("Rejected","Reject Deleted"));	
	}
	
	@Test(priority=9, groups= {"Checker"})
	public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);	
        Assert.assertTrue(ocmReportsPage.verifyIvrConfigdelete(IvrConfigDetails, "Checker Reject"),"Audit Trail Report Assertion Failed");	
	}
	
	@Test(priority=10,groups= {"Maker"})
	public void DeleteIvrConfigRecord() throws Exception {
    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
    Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
    IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
    IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
    ivrConfigPage.deleteIvrConfigRecord(IvrConfigDetails);
    Assert.assertEquals(ivrConfigPage.getSuccessMessage(),"Record Deleted Successfully");	
	}
	
	@Test(priority=11,groups= {"Maker"})
	public void VerifyAuditTrialReportForDelete() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);	
        Assert.assertTrue(ocmReportsPage.verifyIvrConfigdelete(IvrConfigDetails, "Maker Delete"));			
	}
	
	@Test(priority=12, groups= {"Checker"})
	public void ApproveForDeleteIvrConfigRecord() throws Exception {
    IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);	
	ivrConfigPage.clickOnApprove("Approve Deleted");
	Assert.assertEquals(ivrConfigPage.getSuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
    Assert.assertTrue(ivrConfigPage.verifyReviewAuditTrail("Approve", "Approve Deleted")); 
	}
	
	@Test(priority=13, groups= {"Maker"})
	public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);		
		Assert.assertTrue(ocmReportsPage.verifyIvrConfigdelete(IvrConfigDetails, "CheckerApprove"),"Audit Trail report assertion failed");
	}*/
	
	@AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("IvrConfigDeleteTest",method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }	
	
}
	
	