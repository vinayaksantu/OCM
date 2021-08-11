package com.tetherfi.test.tmac;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.pages.*;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AgentSettingsDeleteTest {
	protected WebDriver driver;
	@BeforeMethod
	public void NavigateToAgentSettingsPage(Method method) throws Exception {
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
		Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage = PageFactory.createPageInstance(driver,TmacPage.class);
		Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"TMAC page assertion failed");
		tmacPage.navigateToAgentSettingsPage();
		AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
		Assert.assertTrue(agentSettingsPage.isAgentSettingsPageDisplayed(),"Agent Settings page assertion failed");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test(groups = { "Maker" },priority=1)
	public void DeleteCancelAgentSettingsRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		Assert.assertTrue(agentSettingsPage.deleteCancel(agentSettingsDetails), "delete record assertion failed");
	}

	@Test(groups = { "Maker" },priority=2)
	public void DeleteRevertAgentSettingsRecord() throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.deleteAgentSettingsRecord(agentSettingsDetails.getUsername(), agentSettingsDetails.getDeleteReason());
		Assert.assertEquals(agentSettingsPage.getSuccessMessage(), "Record Deleted Successfully");
	}     

	@Test(groups = { "Maker" },priority=3)//,dependsOnMethods="DeleteRevertAgentSettingsRecord")
	public void VerifyRevertForDeleteRecord() throws Exception {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectRecord();
		agentSettingsPage.Revert("revert");
		Assert.assertTrue(agentSettingsPage.verifyStatus("Reverted"),"approval status details failed");
	}

	@Test(groups= {"Maker"},priority=4)//,dependsOnMethods="VerifyRevertForDeleteRecord")
	public void VerifyAuditTrialReportForRevertDelete() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAgentSettingsDelete(agentSettingsDetails,"MakerReverted"));
	}

	@Test(groups = { "Maker" },priority=5)
	public void RejectDeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails (map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.deleteAgentSettingsRecord(agentSettingsDetails.getUsername(), agentSettingsDetails.getDeleteReason());
		Assert.assertEquals(agentSettingsPage.getSuccessMessage(), "Record Deleted Successfully");
	}

	@Test(groups = { "Maker" },priority=6,dependsOnMethods="RejectDeleteRecord")
	public void VerifySendForApprovalForDeleteNewRecord() throws Exception {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectRecord();
		agentSettingsPage.sendForAprroval("sent");
		Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approval status details failed");
	}

	@Test(priority=7,groups = { "Checker" },dependsOnMethods="VerifySendForApprovalForDeleteNewRecord")
	public void RejectforDeleteAgentSettingsRecord() throws Exception{
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.clickonReject("Reject Deleted");
		Assert.assertFalse(agentSettingsPage.verifyMessage(),"Reject record assertion failed");
		Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Rejected","Reject Deleted"));
	}

	@Test(priority=8,groups = { "Checker" })//,dependsOnMethods = "RejectforDeleteAgentSettingsRecord")
	public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails (map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAgentSettingsDelete(agentSettingsDetails, "CheckerReject"),"Audit Trail report assertion failed");
	}

	@Test(groups= {"Maker"},priority=9)
	public void DeleteAgentSettingsRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails (map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.deleteAgentSettingsRecord(agentSettingsDetails.getUsername(), agentSettingsDetails.getDeleteReason());
		Assert.assertEquals(agentSettingsPage.getSuccessMessage(), "Record Deleted Successfully");
	}

	@Test(priority=10,groups= {"Maker"},dependsOnMethods="DeleteAgentSettingsRecord")
	public void VerifyAuditTrialReportForDelete() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails (map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAgentSettingsDelete(agentSettingsDetails,"MakerDelete"));
	}

	@Test(priority=11,groups = { "Maker" },dependsOnMethods="DeleteAgentSettingsRecord")
	public void VerifyAuditTrailDataForDeleteAgentSettingsRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails (map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		Assert.assertTrue(agentSettingsPage.verifyAuditTrailDelete(agentSettingsDetails, "MakerDelete", "New"), "Audit trail details failed");
	}

	@Test(groups = { "Maker" },priority=12,dependsOnMethods="VerifyAuditTrailDataForDeleteAgentSettingsRecord")
	public void VerifySendForApprovalForDeleteRecord() throws Exception {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectRecord();
		agentSettingsPage.sendForAprroval("sent");
		Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approval status details failed");
	}

	@Test(priority=13,groups = { "Maker" },dependsOnMethods = "VerifySendForApprovalForDeleteRecord")
	public void VerifyAuditTrailReportForSendForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails (map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAgentSettingsDelete(agentSettingsDetails, "MakerSendToApproval"),"Audit Trail report assertion failed");
	}


	@Test(priority=14,groups = { "Checker" })
	public void ApproveforDeleteAgentSettingsRecord() throws Exception{
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.clickonApprove("Approve Deleted");
		Assert.assertTrue(agentSettingsPage.verifyMessage(),"Approve record assertion failed");
		Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Approved","Approve Deleted"));
	}

	@Test(priority=15,groups = { "Checker" },dependsOnMethods = "ApproveforDeleteAgentSettingsRecord")
	public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAgentSettingsDelete(agentSettingsDetails, "CheckerApprove"),"Audit Trail report assertion failed");
	}

	@Test(groups= {"Maker"},priority=16)
	public void DeleteAgentSettingsSupervisorRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(1);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails (map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.deleteAgentSettingsRecord(agentSettingsDetails.getUsername(), agentSettingsDetails.getDeleteReason());
		Assert.assertEquals(agentSettingsPage.getSuccessMessage(), "Record Deleted Successfully");
	}

	@Test(groups = { "Maker" },priority=17)
	public void VerifySendForApprovalForDeleteSupervisorRecord() throws Exception {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectRecord();
		agentSettingsPage.sendForAprroval("sent");
		Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approval status details failed");
	}
	@Test(priority=18,groups = { "Checker" })
	public void ApproveforDeleteAgentSettingsSupervisorRecord() throws Exception{
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.clickonApprove("Approve Deleted");
		Assert.assertTrue(agentSettingsPage.verifyMessage(),"Approve record assertion failed");
		Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Approved","Approve Deleted"));
	}

	@Test(priority=19,groups = { "Checker" })
	public void VerifyAuditTrailReportForSupervisorApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(1);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAgentSettingsDelete(agentSettingsDetails, "CheckerApprove"),"Audit Trail report assertion failed");
	}


	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen( "AgentSettingsDeleteTest",method.getName());
		driver.navigate().refresh();
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.userLogout();
		driver.close();
		System.out.println("Completed Executing : "+method.getName());
	}
}
