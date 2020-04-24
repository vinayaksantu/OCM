package com.tetherfi.test.tmac;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.pages.AgentSettingsPageWMC;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class AgentSettingsTest extends BaseTest{
	
	@BeforeMethod
	public void NavigateToAgentSettingsPage() throws Exception {
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage = PageFactory.createPageInstance(driver,TmacPage.class);
		Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"TMAC page assertion failed");
		tmacPage.navigateToAgentSettingsPage();
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver,AgentSettingsPageWMC.class);
		Assert.assertTrue(agentSettingsPagewmc.isAgentSettingsPageDisplayed(),"Agent Settings page assertion failed");	
	}

	/*@Test(priority=1)
	public void VerifyAgentSettingsPage() {
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver,AgentSettingsPageWMC.class);
		Assert.assertTrue(agentSettingsPagewmc.VerifyLogo(), "Agent Settings logo assertion failed");
		Assert.assertTrue(agentSettingsPagewmc.maximizeWindow(), "Window maximize assertion failed");
		Assert.assertTrue(agentSettingsPagewmc.minimizeWindow(), "Window minimize assertion failed");		
	}
	
	@Test(priority=2)
	public void VerifyDropdownForAlltheColumns() {
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver,AgentSettingsPageWMC.class);
		Assert.assertTrue(agentSettingsPagewmc.verifyDropDownOfAllHeaders(), "Columns dropdown Assertion Failed");		
	}

	@Test(priority=3)
	public void VerifyColumnsHeaderEnabled() {
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver,AgentSettingsPageWMC.class);
		Assert.assertTrue(agentSettingsPagewmc.verifycolumnsHeaderEnabled(), "Columns header assertion failed");
	}

	@Test(priority=4)
	public void VerifyColumnsHeaderDisabled() {
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
		Assert.assertTrue(agentSettingsPagewmc.verifycolumnsHeaderDisabled(), "Columns heade assertion failed");			
	}*/

	@Test(priority=5)
	public void VerifyAddNewAgentSettingsRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(5);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
		agentSettingsPagewmc.addNewAgentSettingsRecord(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPagewmc.getSuccessMessage(), "Record Created Successfully","Record Creation Assertion Failed");		
	}

	@Test(priority=6)
	public void VerifyAuditTrailReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(5);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAuditTrailReportDisplayed(agentSettingsDetails, "Create"), "Audit Trail Report assertion failed");		
	}

	/*@Test(priority=7)
	public void VerifyDuplicateLanIDCreation() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
		agentSettingsPagewmc.addNewAgentSettingsRecord(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPagewmc.getSuccessMessage(),"Duplicate Avaya Login ID", "Add Duplicate record assertion failed");
	}

	@Test(priority=8)
	public void VerifyDuplicateAvayaLoginIDCreation() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(1);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
		agentSettingsPagewmc.addNewAgentSettingsRecord(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPagewmc.getSuccessMessage(),"Duplicate Avaya Login ID", "Add Duplicate record assertion failed");	
	}
	
	@Test(priority=9)
	public void VerifyAddEmptyRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(1);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
		agentSettingsPagewmc.addEmptyRecord();
		Assert.assertEquals(agentSettingsPagewmc.getSuccessMessage(), "Please Provide Lan ID, Avaya Login ID, First Name, Last Name, Org. Unit, Profile, Supervisor, Access Role", "Empty Record Creation Failed");
		}

	@Test(priority=10)
	public void AddRecordWithoutFirstAndLastName() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(2);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);	
		agentSettingsPagewmc.AddRecordWithoutFirstAndLastName(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPagewmc.getSuccessMessage(), "Please Provide First Name, Last Name");	
	}
	
	@Test(priority=11)
	public void VerifyCancelBtnAtAddNewRecord() {
	AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);	
	agentSettingsPagewmc.clickOnAddRecord();
	agentSettingsPagewmc.clickOnCancel();
	Assert.assertTrue(agentSettingsPagewmc.verifyEditFormContainer(), "Cancel Button At Add Record Assertion Failed");	
}
	
	@Test(priority=12)
	public void EditAgentSettingsRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(2);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
		agentSettingsPagewmc.editAgentSettingsRecord(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPagewmc.getSuccessMessage(), "Record Updated Successfully", "Edit Record Assertion Failed");
	}

	@Test(priority=13)
	public void VerifyAuditTrailReportForUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(2);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAgentSettingsUpdate(agentSettingsDetails, "Update"), "Audit Trail Report assertion failed");		
	}

	/*@Test(priority=14)
	public void EditRecordWithoutModifyReason() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(2);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
		agentSettingsPagewmc.editAgentSettingsRecordWithoutModifyReason(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPagewmc.getSuccessMessage(),"Please enter the modify reason", "Edit Record without Modify reason failed");
	}

	@Test(priority=15)
	public void VerifyCancelButtonAtEditRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(2);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);	
		agentSettingsPagewmc.ClickOnEditButton(agentSettingsDetails);
		agentSettingsPagewmc.clickOnCancel();
		Assert.assertTrue(agentSettingsPagewmc.verifyEditFormContainer(), "Cancel Button At edit record Assertion failed");
	}
	
	@Test(priority=16)
	public void SearchPage() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(5);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
		Assert.assertFalse(agentSettingsPagewmc.clearAll(agentSettingsDetails), "clear all assertion failed");
		Assert.assertTrue(agentSettingsPagewmc.verifyclose());	
		}
	
	@Test(priority=17)
	public void searchwithoutSearchTextbox() {
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
		agentSettingsPagewmc.searchwithoutextsearch();
		Assert.assertEquals(agentSettingsPagewmc.getSuccessMessage(), "Please enter the text to search or remove the filter", "Add invalid record assertion failed");	
	}

	@Test(priority=18)
	public void ExportToExcel() {
		 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
		agentSettingsPagewmc.verifyExportToExcel();		
	}
	
	@Test(priority=19)
	public void ExportToExcelData() throws Exception {
		 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Settings.xlsx";
		 List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
		Assert.assertTrue(agentSettingsPagewmc.verifyexportToExcelSheet(maplist));		
	}
	
	@Test(priority=20)
	public void VerifyDeleteRecordWithoutDeleteReason() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);	
		agentSettingsPagewmc.DeleteAgentSettingsRecordWithoutDeleteReason(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPagewmc.getSuccessMessage(),"Please enter the delete reason", "Delete Record without delete Reason Assertion failed");
	}

	@Test(priority=21)
	public void verifyCancelButtonAtDeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(2);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);	
		agentSettingsPagewmc.clickOnDeleteButton(agentSettingsDetails);
		agentSettingsPagewmc.clickOnDeleteCancelBtn();
		Assert.assertTrue(agentSettingsPagewmc.verifyDeleteContainer(), "Delete Record Assertion Failed");
	}

    @Test(priority=22)
    public void DeleteAgentSettingsRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(2);
    	AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
    	AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);	
    	agentSettingsPagewmc.DeleteAgentSettingsRecord(agentSettingsDetails);
    	Assert.assertEquals(agentSettingsPagewmc.getSuccessMessage(), "Record Deleted Successfully", "Delete Record Assertion failed");		
    }

    @Test(priority=23)
	public void VerifyAuditTrailReportForDelete() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(2);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAgentSettingsDelete(agentSettingsDetails, "Delete"),"Audit Trail Report Assertion Failed");
	}
	
    /*@Test(priority=24)
    public void SearchClearSearch() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(1);
    	AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
    	AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);		
    	Assert.assertTrue(agentSettingsPagewmc.verifyinvalidsearchwithwrongdata(agentSettingsDetails),"Invalid Record Assertion Failed");
    }
	
    @Test(priority=25)
    public void verifyExportToExcelWithoutData() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(2);
    	AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
    	AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);	
    	Assert.assertTrue(agentSettingsPagewmc.VerifyExportToExcelWithoutData(agentSettingsDetails));
    }
    
    @Test(priority=26)
    public void VerifiySortByAscending() throws IOException {
    	AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
    	agentSettingsPagewmc.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Settings (1).xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	Assert.assertTrue(agentSettingsPagewmc.verifyexportToExcelSheet(maplist));
    }

    @Test(priority=27)
    public void VerifiySortByDescending() throws IOException {
    	AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
    	agentSettingsPagewmc.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Settings (1).xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	Assert.assertTrue(agentSettingsPagewmc.verifyexportToExcelSheet(maplist));
    }
   
	@Test(priority=28)
	public void GroupBy() {
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
		Assert.assertTrue(agentSettingsPagewmc.GroupBy(), "Group By Assertion Failed");
	}

	@Test(priority=29)
	public void VerifyArrowMoveForPreviousAndNextPage() {
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
		Assert.assertTrue(agentSettingsPagewmc.verifyArrowMoveForPreviousAndNextPage(), "Arrow move between Previous and next page Assertion failed");
	}
   
	@Test(priority=30)
	public void VerifyArrowMoveForFirstAndLastPage() {
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
		Assert.assertTrue(agentSettingsPagewmc.verifyArrowMoveForFirstAndLastPage(), "Arrow move between Previous and next page Assertion failed");
	}
	
	@Test(priority=31)
	public void VerifyNumberOfItemsPerPageSelection() {
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
		Assert.assertTrue(agentSettingsPagewmc.verifyNumberOfItemsPerPage(), "Number of Items Per Page Assertion Failed");
	}
	
	@Test(priority=32)
	public void VerifyTotalNumberOfItemsPerPageDetails() {
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
		Assert.assertTrue(agentSettingsPagewmc.verifyTotalNumberOfItemsPerPageDetails(), "Total Number of items per page assertion failed");	
	}
	
	 @Test(priority=33)
	 public void VerifySearchIsNotEqualTo() throws Exception {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(3);
	    AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
	    AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
	    Assert.assertTrue(agentSettingsPagewmc.verifySearchIsNotEqualTo(agentSettingsDetails.getFirstname()));
	 }
	
	 @Test(priority=34)
	 public void VerifySearchContains() throws Exception {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(4);
	    AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
	    AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
	    Assert.assertTrue(agentSettingsPagewmc.verifySearchContains(agentSettingsDetails.getFirstname()));
	 }
	 
	 @Test(priority=35)
	 public void VerifySearchDoesNotContains() throws Exception {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(5);
	    AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
	    AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
	    Assert.assertTrue(agentSettingsPagewmc.verifySearchDoesNotContains(agentSettingsDetails.getFirstname()));
	 }
	 
	 @Test(priority=36)
	 public void VerifySearchStartsWith() throws Exception {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(6);
	    AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
	    AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
	    Assert.assertTrue(agentSettingsPagewmc.verifySearchStartsWith(agentSettingsDetails.getFirstname()));
	 }
	 
	 @Test(priority=37)
	 public void VerifySearchEndsWith() throws Exception {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(7);
	    AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
	    AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver, AgentSettingsPageWMC.class);
	    Assert.assertTrue(agentSettingsPagewmc.verifySearchEndsWith(agentSettingsDetails.getFirstname()));
	 }*/
	  
	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("AgentSettingsTest",method.getName());
		driver.navigate().refresh();
	}	
}



