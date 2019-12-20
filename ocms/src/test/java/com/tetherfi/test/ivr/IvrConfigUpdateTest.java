package com.tetherfi.test.ivr;

import static org.testng.Assert.assertTrue;

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

public class IvrConfigUpdateTest {
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
	public void EditCancelIvrConfigRecord() throws Exception {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        Assert.assertTrue(ivrConfigPage.EditCancel(IvrConfigDetails), "Edit Cancel assertion Failed");
	}
	
	@Test(priority=2, groups= {"Maker"})
	public void EditRevertIvrConfigRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.editIvrConfigRecord(IvrConfigDetails);
		Assert.assertEquals(ivrConfigPage.getSuccessMessage(), "Record Updated Successfully");				
	}
	
	@Test(priority=3,groups= {"Maker"})//,dependsOnMethods="EditRevertIvrConfigRecord")
	public void VerifyRevertForEditRecord() throws Exception {
		IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectRecord();
		ivrConfigPage.Revert("revert");
		Assert.assertTrue(ivrConfigPage.verifyStatus("Reverted"),"Apprval Status details failed");
	}
	
	@Test(priority=4,groups= {"Maker"})//, dependsOnMethods="VerifyRevertForEditRecord")
	public void VerifyAuditTrialReportForRevertUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIvrConfigUpdate(IvrConfigDetails,"MakerReverted"));		
	}
	
	 @Test(priority=5,groups= {"Maker"})
	 public void EditRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	    IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.editIvrConfigRecord(IvrConfigDetails);
		Assert.assertEquals(ivrConfigPage.getSuccessMessage(),"Record Updated Successfully");	    
	 }
	
	 @Test(priority=6,groups= {"Maker"})//,dependsOnMethods="EditRejectRecord")
	 public void VerifySendForApprovalForEditRejectRecord() throws Exception {
      IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
      ivrConfigPage.selectIvrConfigAuditTrailTab();
      ivrConfigPage.selectRecord();
      ivrConfigPage.sendForAprroval("Sent");
      Assert.assertTrue(ivrConfigPage.verifyStatus("Approval Pending"), "Approval Status Details Failed");		 
	 }
	
	  @Test(priority=7,groups= {"Checker"})//,dependsOnMethods="VerifySendForApprovalForEditRejectRecord")
	  public void RejectforEditIvrConfigRecord() throws Exception {
      IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
      ivrConfigPage.clickOnReject("Reject Updated");
      Assert.assertFalse(ivrConfigPage.verifyMessage(),"Reject Record Assertion Failed");
      Assert.assertTrue(ivrConfigPage.verifyReviewAuditTrail("Rejected", "Reject Updated"));	  
	  }
	 
	  @Test(priority=8, groups= {"Checker"})//,dependsOnMethods="RejectforEditIvrConfigRecord")
	  public void VerifyAuditTrailReportForReject() throws Exception {
	  String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";	  
	  Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	  IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);	  
	  HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
      homePage.navigateToOCMReportsPage();
      OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
      String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
      Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
      ReportDetails reportDetails= new ReportDetails(map1);
      ocmReportsPage.showReport(reportDetails);
      Assert.assertTrue(ocmReportsPage.verifyIvrConfigUpdate(IvrConfigDetails,"CheckerReject"), "Audit Trail Report Assertion Failed");  
	  }
	
	  @Test(priority=9,groups= {"Maker"})
	  public void EditIvrConfigRecord() throws Exception {
	  String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";	  	  
	  Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.editIvrConfigRecord(IvrConfigDetails);
		Assert.assertEquals(ivrConfigPage.getSuccessMessage(),"Record Updated Successfully");	
	  }
	  
	  @Test(priority=10,groups= {"Maker"})//,dependsOnMethods="EditIvrConfigRecord")
	  public void VerifyAuditTrailDataForEditIvrConfigRecord() throws Exception {
	  String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
	  Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	  IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
	  IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
	  ivrConfigPage.selectIvrConfigAuditTrailTab();
	  Assert.assertTrue(ivrConfigPage.verifyAuditTrailUpdate(IvrConfigDetails, "MakerUpdate", "New"),"Audit Trail Details Failed");			  
	  }
	  
	  @Test(groups= {"Maker"})//,priority=11,dependsOnMethods="EditIvrConfigRecord")
	  public void VerifyAuditTrialReportForUpdate() throws Exception {
	  String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";	  
	  Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
      IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);	  
	  HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
      homePage.navigateToOCMReportsPage();
	  OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	  String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
      Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
      ReportDetails reportDetails= new ReportDetails(map1);
      ocmReportsPage.showReport(reportDetails);
      Assert.assertTrue(ocmReportsPage.verifyIvrConfigUpdate(IvrConfigDetails, "MakerUpdate")); 
	  }
	  
	  @Test(priority=12,groups= {"Maker"})//,dependsOnMethods="VerifyAuditTrialReportForUpdate")
	  public void VerifySendForApprovalForUpdateEditIvrConfigRecord() throws Exception {
	  IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
	  ivrConfigPage.selectIvrConfigAuditTrailTab();
	  ivrConfigPage.selectRecord();
	  ivrConfigPage.sendForAprroval("Sent");
	  Assert.assertTrue(ivrConfigPage.verifyStatus("Approval Pending"), "Approval Status Details Failed");		  
	  }
	  
	  @Test(priority=13,groups= {"Maker"})//,dependsOnMethods="EditIvrConfigRecord")
	  public void VerifyAuditTrialReportForSendForApprovalUpdate() throws Exception {
		  String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";	  
		  Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	      IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);	  
		  HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      homePage.navigateToOCMReportsPage();
		  OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		  String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	      Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	      ReportDetails reportDetails= new ReportDetails(map1);
	      ocmReportsPage.showReport(reportDetails);	  
		  Assert.assertTrue(ocmReportsPage.verifyIvrConfigUpdate(IvrConfigDetails, "MakerSendToApproval"));		  
	  }
	  
	 @Test(priority=14,groups= {"Checker"})//,dependsOnMethods="VerifyAuditTrialReportForSendForApprovalUpdate")
	 public void ApproveForEditIvrConfigRecord() throws Exception {
     IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);	 
     ivrConfigPage.clickOnApprove("Approve Edited");
     Assert.assertTrue(ivrConfigPage.verifyMessage());
     Assert.assertTrue(ivrConfigPage.verifyReviewAuditTrail("Approved","Approve Edited"));	 
	 }
	 
	 @Test(priority=15,groups= {"Checker"})//,dependsOnMethods="ApproveForEditIvrConfigRecord")
	 public void VerifyAuditTrailReportForApprove() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";	  
		  Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	      IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);	  
		  HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	      homePage.navigateToOCMReportsPage();
		  OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		  String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	      Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	      ReportDetails reportDetails= new ReportDetails(map1);
	      ocmReportsPage.showReport(reportDetails);		 
		 Assert.assertTrue(ocmReportsPage.verifyIvrConfigUpdate(IvrConfigDetails,"Checker Approve"),"Audit Trail report assertion failed");
	 }
	 
	 @Test(groups = { "Maker" })
	    public void EditRecordWithoutModifyReason() throws Exception {
	        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
	        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);	
	        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);	
	        ivrConfigPage.EditRecordWithoutModifyReason(IvrConfigDetails);
	        Assert.assertFalse(ivrConfigPage.getErrorMsg(),"Invalid Record Assertion failed");
	    }
	  
	 
	 @AfterMethod
	    public void afterEachMethod(Method method){
	    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("TMACTransferListTest",method.getName());
	        driver.navigate().refresh();
	    }	 
}
