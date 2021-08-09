package com.tetherfi.test.tmac;

import java.lang.reflect.Method;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.AgentTemplateDetails;
import com.tetherfi.pages.AgentTemplatePage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class Templates_OpHoursTab extends BaseTest{

	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod
	public void NavigateToAgentTemplatePage() throws InterruptedException, Exception {
		HomePage homePage=PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage=PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HomePage Assertion Failed");
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver, TmacPage.class);
		Assert.assertTrue(tmacPage.isTMACPageDisplayed(), "Tmac Page Assertion Failed");
		tmacPage.navigateToAgentTemplatePage();
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.isAgentemplatePageIsDisplayed(), "AgentTemplatePage Assertion  failed");
	}

	@Test(priority=1,description="Verify the save button at Add new template record")
	public void AddnewtemplateRecord() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TemplateTab").getTestData().get(3);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.addnewTemplateRecord(AgentTemplateDetails);
		Assert.assertEquals(agentTemplatePage.VerifyMessage(), "Record inserted Successfully"); 	 
	} 

	@Test(priority=2,description="Verify add row in drill down without from time")
	public void verifysaveWithoutFromTime() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"OpHoursTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.verifyaddRowwithoutFromTime(AgentTemplateDetails,4),"From time assertion failed");    	 	 
	}

	@Test(priority=3,description="Verify add row in drill down without To time")
	public void verifysaveWithoutToTime() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"OpHoursTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.verifyaddRowwithoutToTime(AgentTemplateDetails,4),"To time assertion failed");    	 	 
	}

	@Test(priority=4,description="Verify add row in drill down without To time")
	public void verifysaveWithoutWeekday() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"OpHoursTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.verifyaddRowwithoutWeekDay(AgentTemplateDetails,4),"Weeek day assertion failed");    	 	 
	}

	@Test(priority=5,description="Verify the drill down Template name")
	public void verifyDrillDownForRecordCreate() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"OpHoursTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.verifyDrillDownForRecordCreate(AgentTemplateDetails,4);   
		Assert.assertEquals(agentTemplatePage.VerifyMessage(), "Record Created Successfully"); 
	}

	@Test(priority=6)//,dependsOnMethods="verifyDrillDownForRecordCreate")
	public void verifyAuditTrailReportForCreate() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String>map=new ExcelReader(filePath,"OpHoursTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map2);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyOperatingHoursTabCreate(AgentTemplateDetails, "Create"), "Create Audit Trail Assertion Failed"); 
	}

	@Test(priority=7,description="Verify the drill down Template name")
	public void verifyDrillDownForRecordUpdate() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"OpHoursTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.VerifyDrillDownRecordUpdate(AgentTemplateDetails,4);   
		Assert.assertEquals(agentTemplatePage.VerifyMessage(), "Record Updated Successfully"); 
	}

	@Test(priority=8)//,dependsOnMethods="verifyDrillDownForRecordUpdate")
	public void verifyAuditTrailReportForUpdate() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String>map=new ExcelReader(filePath,"OpHoursTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map2);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyOperatingHoursTabUpdate(AgentTemplateDetails, "Update"), "Create Audit Trail Assertion Failed"); 
	}

	@Test(priority=9,description="verify the cancel button at delete record")
	public void VerifyCancelBtnatDeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"OpHoursTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.DeleteCancelOPtab(AgentTemplateDetails,4), "Cancel Btn at Edit record assertion failed");
	}

	@Test(priority=10,description="Verify the drill down Template name")
	public void verifyDeleteActioninDrillDownForrecord() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"OpHoursTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.verifyDeleteforDrillDown(AgentTemplateDetails,4),"Delete Btn assertion failed");   	 	 
	}

	@Test(priority=11)//,dependsOnMethods="verifyDeleteActioninDrillDownForrecord")
	public void verifyAuditTrailReportForDelete() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String>map=new ExcelReader(filePath,"OpHoursTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map2);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyOperatingHoursTabDelete(AgentTemplateDetails, "Delete"), "Create Audit Trail Assertion Failed"); 
	}

	@Test(priority=12,description="verify the delete button")
	public void DeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TemplateTab").getTestData().get(3);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.deleteRecord(AgentTemplateDetails,0);
		Assert.assertEquals(agentTemplatePage.VerifyMessage(),"Record Deleted Successfully","Delete record assertion failed");
	}

	@Test(priority=13,description="To verify template screen UI data against DB")
	public void verifyDatabase() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(2);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class); 	
		agentTemplatePage.NavigateToTemplateOpHoursTab();
		agentTemplatePage.sorting();
		Assert.assertTrue(agentTemplatePage.verifyDatabase1(AgentTemplateDetails.getQuery(), 4));
	}



	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("agntOPHoursTab", method.getName());
		driver.navigate().refresh();
	}

}
