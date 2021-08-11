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

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AgentSettingsEditTest {
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
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@Test(groups = { "Maker" },priority=1)
	public void EditCancelSupervisorRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		Assert.assertTrue(agentSettingsPage.EditCancel(agentSettingsDetails), "Edit Cancel assertion Failed");
	}

	@Test(groups= {"Maker"},priority=2)
	public void EditRevertSupervisorRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.editAgentSettingsRecord(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPage.verifySuccessMessage(), "Record Updated Successfully");
	}

	@Test(groups = { "Maker" },priority=3)//,dependsOnMethods="EditRevertSupervisorRecord")
	public void VerifyRevertForEditRecord() throws Exception {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectRecord();
		agentSettingsPage.Revert("revert");
		Assert.assertTrue(agentSettingsPage.verifyStatus("Reverted"),"approval status details failed");
	}

	@Test(groups= {"Maker"},priority=4,dependsOnMethods="VerifyRevertForEditRecord")
	public void VerifyAuditTrialReportForRevertUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAgentSettingsUpdate(agentSettingsDetails,"MakerReverted"));
	}

	@Test(groups= {"Maker"},priority=5)
	public void EditRejectRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.editAgentSettingsRecord(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPage.verifySuccessMessage(), "Record Updated Successfully");
	}

	@Test(groups = { "Maker"} ,priority=6,dependsOnMethods="EditRejectRecord")
	public void VerifySendForApprovalForEditRejectRecord() throws Exception {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectRecord();
		agentSettingsPage.sendForAprroval("sent");
		Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approval status details failed");
	}

	@Test(groups = { "Checker" },priority=7,dependsOnMethods="VerifySendForApprovalForEditRejectRecord")
	public void RejectforEditAgentSettingsRecord() throws Exception{
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.clickonReject("Reject Updated");
		Assert.assertFalse(agentSettingsPage.verifyMessage(),"Reject record assertion failed");
		Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Rejected","Reject Updated"));
	}

	@Test(groups = { "Checker" },priority=8,dependsOnMethods = "RejectforEditAgentSettingsRecord")
	public void VerifyAuditTrailReportForReject() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAgentSettingsUpdate(agentSettingsDetails, "CheckerReject"),"Audit Trail report assertion failed");
	}

	@Test(groups= {"Maker"},priority=9)
	public void EditAgentSettingsRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.editAgentSettingsRecord(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPage.verifySuccessMessage(), "Record Updated Successfully");
	}

	@Test(groups = { "Maker" },priority=10,dependsOnMethods="EditAgentSettingsRecord")
	public void VerifyAuditTrailDataForEditAgentSettinsRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		Assert.assertTrue(agentSettingsPage.verifyAuditTrailUpdate(agentSettingsDetails, "MakerUpdate", "New"), "Audit trail details failed");
	}

	@Test(groups = { "Maker" },priority=11,dependsOnMethods = "EditAgentSettingsRecord")
	public void VerifyAuditTrailReportForEdit() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAgentSettingsUpdate(agentSettingsDetails,"MakerUpdate"),"Audit Trail report assertion failed");
	}

	@Test(groups = { "Maker" },priority=12)//,dependsOnMethods="EditAgentSettingsRecord")
	public void VerifySendForApprovalForEditRejectRecord1() throws Exception {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectRecord();
		agentSettingsPage.sendForAprroval("sent");
		Assert.assertTrue(agentSettingsPage.verifyStatus("Approval Pending"),"approal status details failed");
	}

	@Test(groups= {"Maker"},priority=13,dependsOnMethods="VerifySendForApprovalForEditRejectRecord1")
	public void VerifyAuditTrialReportForSendForApprovalUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAgentSettingsUpdate(agentSettingsDetails,"MakerSendToApproval"));
	}

	@Test(groups = { "Checker" },priority=14)//,dependsOnMethods="VerifySendForApprovalForEditRejectRecord1")
	public void ApproveforEditSupervisorRecord() throws Exception{
		AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
		agentSettingsPage.clickonApprove("Approve Edit");
		Assert.assertTrue(agentSettingsPage.verifyMessage(),"Approve record assertion failed");
		Assert.assertTrue(agentSettingsPage.verifyReviewAuditTrail("Approved","Approve Edit"));
	}

	@Test(groups = { "Checker" },priority=15,dependsOnMethods = "ApproveforEditSupervisorRecord")
	public void VerifyAuditTrailReportForApprove() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);		    
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAgentSettingsUpdate(agentSettingsDetails, "CheckerApprove"),"Audit Trail report assertion failed");
	}

	@Test(groups = { "Maker" },priority=16)
	public void EditRecordWithoutModifyReason() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
		agentSettingsPage.EditRecordWithoutModifyReason(agentSettingsDetails);
		Assert.assertFalse(agentSettingsPage.getErrorMsg(),"Invalid Record Assertion failed");
	}	 


	//@Test(groups = { "Maker" },priority=17)//,dependsOnMethods = "EditAgentSettingsRecord")
	public void VerifyProfileSelectionAgentAtTeamLevel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		agentSettingsPage.searchAgentSettingsRecord(agentSettingsDetails.getUsername());
		agentSettingsPage.clickonTopmostEditButton();
		Assert.assertTrue(agentSettingsPage.verifyProfileSelection(), "profile selection assertion failed");
	}
	@Test(groups = { "Maker" },priority=18)//,dependsOnMethods = "EditAgentSettingsRecord")
	public void VerifyProfileSelectionAgentAtCountryDivisionDepartmentLevel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		agentSettingsPage.searchAgentSettingsRecord(agentSettingsDetails.getUsername());
		agentSettingsPage.clickonTopmostEditButton();
		Assert.assertFalse(agentSettingsPage.verifyProfileSelectionAtCountryDivisionDepartmentLevel(agentSettingsDetails.getTeamName()), "agent profile selection at country, division, department assertion failed");
	}

	@Test(groups = { "Maker" },priority=19)
	public void DeleteSupervisorRecordWhenAgentAssigned() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(1);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.deleteSupervisorRecordWhenAssignedToAgent(agentSettingsDetails.getUsername());
		Assert.assertTrue(agentSettingsPage.verifyRetagSupervisorPopupDisplayed(), "delete supervisor when agent assigned assertion failed");
	}

	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen( "AgentSettingsEditTest",method.getName());
		driver.navigate().refresh();
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.userLogout();
		driver.close();
		System.out.println("Completed Executing : "+method.getName());
	}
}