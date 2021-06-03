package com.tetherfi.test.reports;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMPendingEmailAgeingReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMPendingEmailAgeingReportTest extends BaseTest{

	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod
	public void NavigateToOcmReportsPage() {        
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMIconImg();
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());       	
	}

	@Test(priority=1,description="To verify Show Report for Single Date")
	public void ShowReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");      
	}

	@Test(priority=2,description="To verify Show Report in New page for Single Date")
	public void ShowReportInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}

	@Test(priority=3,description="To Verify total number of items per page")
	public void VerifyTotalNumberOfItemsPerPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}   

	@Test(priority=4,enabled=false,description="To Verfiy number of items selected per page")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}

	@Test(priority=5,description="Verify Pagination, Move to previous and next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {  
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}

	@Test(priority=6,description="Verify Pagination, Move to First and Last Page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}

	@Test(priority=7,description="To verify Search by feature using Skill name")
	public void VerifySearchByFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifySearchByTextbox(reportDetails));
	}

	@Test(priority=8,description="To Verify the search Is equal to criteria")
	public void VerifySearchIsEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}

	@Test(priority=9,description="To Verify the search Is Not equal to criteria")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}

	@Test(priority=10,description="To Verify search contains criteria")
	public void VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifySearchContains(reportDetails.getSearchStr()));
	}

	@Test(priority=11,description="To Verify search does not contains search criteria")
	public void VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifySearchDoesNotContains(reportDetails.getSearchStr()));
	}

	@Test(priority=12,description="To Verify search starts with search criteria")
	public void VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=13, description="To verify search ends with search criteria")
	public void VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=14,description="To perform Search without entering search text")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		pendingEmailAgeingReportPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(pendingEmailAgeingReportPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Assertion failed");
	}

	@Test(priority=15,description="To perform search without selecting column name")
	public void searchwithoutColumnName() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		pendingEmailAgeingReportPage.searchWithoutColumn(reportDetails);
		Assert.assertEquals(pendingEmailAgeingReportPage.getSuccessMessage(),"Please select the column name", "Assertion failed");
	}

	@Test(priority=16,description="Clear search functionality")
	public void SearchClear() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifySearchClear(reportDetails));    	
	}

	@Test(priority=17,description="To verify Advance search using Is equal to Criteria")
	public void verifyAdvancedSearchIsEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifyAdvancedSearchIsEqualTo(reportDetails));
	}

	@Test(priority=18,description="To verify Advance search using search Is not equal to Condition")
	public void verifyAdvancedSearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifyAdvancedSearchIsNotEqualTo(reportDetails));
	}

	@Test(priority=19,description="To verify Advanced search using search contains criteria")
	public void verifyAdvancedSearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifyAdvancedSearchContains(reportDetails));
	}

	@Test(priority=20,description="To verify Advance search using search Is not equal to Condition")
	public void verifyAdvancedSearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifyAdvancedSearchDoesNotContains(reportDetails));
	}

	@Test(priority=21,description="To verify Advanced search using starts with criteria")
	public void verifyAdvancedSearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifyAdvancedSearchStartsWith(reportDetails));
	}

	@Test(priority=22,description="To verify Advanced search using ends with criteria")
	public void verifyAdvancedSearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifyAdvancedSearchEndsWith(reportDetails));
	}

	@Test(priority=23,description="To verify Advanced search OR search criteria")
	public void verifyAdvancedSearchORcriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(pendingEmailAgeingReportPage.advancedSearchORCriteria(reportDetails));
	}

	@Test(priority=24,description="To verify Advanced search OR search criteria")
	public void verifyAdvancedSearchANDcriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(pendingEmailAgeingReportPage.advancedSearchORCriteria(reportDetails));
	}

	@Test(priority=25,description="To clear advanced search filters in report manager page")
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(ocmReportsPage.ClearAdvFilters(reportDetails));
	}
	
	@Test(priority=25,description="To verify clear all the filters from report manager page")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(OCMReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}

	@Test(priority=27,description="To verify Group By functionality")
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.groupby());
		screenshot.captureScreen("EmailAgeingReport", "GroupBy");
		Assert.assertTrue(pendingEmailAgeingReportPage.groupby());
		screenshot.captureScreen("EmailAgeingReport", "AlreadyGroupBy");
	}
	
	@Test(priority=28,description="To verify minimize and maximizing of OCM window")
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);    
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("EmailAgeingReport","Maximize");
		Assert.assertTrue(pendingEmailAgeingReportPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("EmailAgeingReport","Minimize");	
	}

	@Test(priority=29,description="To Verify dropdown for all the coulnm headers")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);		
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}

	@Test(priority=30,description="To verify column headers disable functionality")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertFalse(pendingEmailAgeingReportPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}

	@Test(priority=31,description="To verify column header enable functionality")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);	
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}
	
	@Test(priority=32,description="To verify navigation to export scheduler page from reports manager")
	public void ScheduleReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=33,description="To verify Export Report on OCM Reports Page")
	public void ExportReportWithoutData() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyExportReportWithoutData(),"export report assertion failed");
	}

	@Test(priority=34,description="To verify Export Report on OCM Reports Page")
	public void ExportReportForSingleDate() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=35,dependsOnMethods ="ExportReportForSingleDate",description="To view downloaded report in report downloads Page")
	public void ViewDownloadedReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		OCMReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}
	
	@Test(priority=36,dependsOnMethods ="ViewDownloadedReportInReportsDownloadPage",description="To verify view downloaded report on OCM Reports Page")
	public void verifyDownloadedReportData() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","Pending Email Ageing  Repo"));	
	}

	@Test(priority=37,description="To verify Show Report for Date Range")
	public void ShowReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	}
	
	@Test(priority=38,description="To verify Show Report in new page for Date Range")
	public void ShowReportInNewPageForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	}
	
	@Test(priority=39,description="To verify Export scheduler button")
	public void ScheduleReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	
	@Test(priority=40,description="To Export the Report from OCM Report manager Page for Date Range")
	public void ExportReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=41,dependsOnMethods ="ExportReportForDateRange",description="To verify the downloaded report in Report Downloads")
	public void ViewDownloadedReportInReportDownloadsPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}
	
	@Test(priority=42,dependsOnMethods ="ViewDownloadedReportInReportDownloadsPageDateRange",description="To verification of exported excel in Report downloads")
	public void verifyDownloadedReportDataForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","Pending Email Ageing  Repo"));		
	}

	@Test(priority=43,description="To verify Delete record without delete reason")
	public void DeleteRecordWithoutDeleteReasonInReportsDownloadsPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		Assert.assertTrue(ocmReportsPage.deleteWithoutDeleteReason(reportDetails),"empty delete reason record assertion failed");		
	}
	
	@Test(priority=44,description="To verify delete cancel button")
	public void verifyDeleteCancelBtnInReportDownloadsPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}

	@Test(priority=45,dependsOnMethods="ViewDownloadedReportInReportDownloadsPageDateRange",description="To verify delete record from report downloads page")
	public void DeleteRecordInReportDownloadsPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails),"Delete record assertion failed");	
	}
	
	@Test(priority=46,description="To Export the data using export page functionality")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifyExportToExcel(filePath1));
	}

	@Test(priority=47,dependsOnMethods="ExportPage",description="To Verify Exported Page data Against UI data")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\PendingEmailAgeingReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifyexportToExcelSheet(maplist));
	}
	
	@Test(priority=48,description="To Navigate to export report scheduler page from pending email ageing report")
	public void SchedulereportReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);       
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");		    	 
	}
	
	@Test(priority=49,description="To export the report data using Export To Excel functionality")
	public void ExportToExcel() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		pendingEmailAgeingReportPage.exportToExcel();
		Assert.assertTrue(pendingEmailAgeingReportPage.verifyReportExported(),"export report assertion failed");
	} 

	@Test(priority=50,dependsOnMethods ="ExportToExcel",description="To view the Downloaded report in report downloads page")
	public void ViewExportedDataInReportDownloadsPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		pendingEmailAgeingReportPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");    
	}
	
	@Test(priority=51,dependsOnMethods ="ViewExportedDataInReportDownloadsPage",description="To verify view downloaded report on OCM Reports Page")
	public void verifyDownloadedExcelData() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","Pending Email Ageing  Repo"));	
	}
	
	@Test(priority=52,enabled=false,description="To Verify sorting of data using Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMPendingEmailAgeingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMPendingEmailAgeingReportPage pendingEmailAgeingReportPage=PageFactory.createPageInstance(driver,OCMPendingEmailAgeingReportPage.class);
		Assert.assertTrue(pendingEmailAgeingReportPage.verifySorting(),"Sorting failed");
	}

	
	
}
