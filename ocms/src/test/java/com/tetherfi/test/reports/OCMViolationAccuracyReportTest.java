package com.tetherfi.test.reports;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMViolationAccuracyReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.FTPServer;
import com.tetherfi.utility.JSONReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMViolationAccuracyReportTest extends BaseTest{
	
	String destinationFilePath = "C:/Users/Admin/git/scripts/ocms/src/test/resources/DownloadedFiles/Json/OCMViolationAccuracyReport.json";
	String remoteFilePath = "\\\\172.16.2.61\\c$\\Products\\OCM\\UI\\Configurations\\Reports\\OCMViolationAccuracyReport.json";
	
	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
	public void NavigateToOcmReportsPage() {
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMIconImg();
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());
		FTPServer ftp=new FTPServer();
		ftp.transferFileFromRemote(remoteFilePath,destinationFilePath);
	}
	
	@Test(priority=1,description="To verify Show Report for Single Date")
	public void ShowReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
	} 

	//@Test(priority=2,description="To verify Show Report in New Tab for Single Date")
	public void ShowReportInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		OCMReportsPage.switchBackToParentWindow();
	}
	
	//@Test(priority=4,description="To verify Advanced search Is equal to Criteria")
	public void verifyAdvancedSearchIsEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifyAdvanceSearchIsEqualTo(reportDetails));
	}

	//@Test(priority=5,description="To verify Advanced search Is not equal to search Criteria") 
	public void verifyAdvancedSearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifyAdvanceSearchIsNotEqualTo(reportDetails));
	}

	@Test(priority=6,description="To verify Advanced search Contains search Criteria")
	public void verifyAdvancedSearchcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class); 
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifyAdvanceSearchContains(reportDetails));    	
	}

	@Test(priority=7,description="To verify Advanced search Does not Contain Criteria")
	public void verifyAdvancedSearchDoesnotcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifyAdvanceSearchDoesNotContains(reportDetails));   
	}

	@Test(priority=8,description="To verify Advanced search Starts with Criteria")
	public void verifyAdvancedSearchStartswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class); 
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifyAdvanceSearchStartsWith(reportDetails)); 	
	}

	@Test(priority=9,description="To verify Advanced search Ends with Criteria")
	public void verifyAdvancedSearchEndswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifyAdvanceSearchEndsWith(reportDetails));
	}

	@Test(priority=10,description="To verify Advanced search with AND Search Criteria")
	public void verifyAdvancedSearchANDCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(OCMViolationAccuracyReportPage.advancedSearchANDCriteria(reportDetails));   	
	}

	@Test(priority=11,description="To verify Advanced search with OR Search Criteria")
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(OCMViolationAccuracyReportPage.advancedSearchORCriteria(reportDetails));
	}
	
	@Test(priority=12,description="Clear filters for Advance search")
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(OCMReportsPage.ClearAdvFilters(reportDetails));
	}

	@Test(priority=13,description="Report page clear All button ")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(OCMReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}
	
	@Test(priority=14,description="To verify Maximize and minimize OCM Screen")
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);    
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);           
		Assert.assertTrue(OCMViolationAccuracyReportPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMViolationAccuracyReport","Maximize");
		Assert.assertTrue(OCMViolationAccuracyReportPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMViolationAccuracyReport","Minimize");	
	}
	
	@Test(priority=15,description="Verify dropdown of all the column headers")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);		
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}

	@Test(priority=16,description="Verify column header Enable")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);	
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}

	@Test(priority=17,description="Verify column header disable")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertFalse(OCMViolationAccuracyReportPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}
	
	@Test(priority=18,description="Verify Pagination, Move to previous and next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	} 

	@Test(priority=19,description="Verify Pagination, Move to First and Last Page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}

	@Test(priority=20,description="Verify total number of items per page")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}

	@Test(priority=21,enabled=true,description="Verify number of items selected per page")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}
	
	@Test(priority=22,description="To verify Search by feature using Agent Name")
	public void VerifySearchByFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifySearchByTextbox(reportDetails));
	}
	
	@Test(priority=23,description="To Verify the search Is equal to criteria")
	public void VerifySearchIsEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}

	@Test(priority=24,description="To Verify search Is not equal to criteria")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}

	@Test(priority=25,description="Verify the search contains criteria")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifySearchContains(reportDetails.getSearchStr()));
	}

	@Test(priority=26,description="Verify the Does not contain criteria")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}

	@Test(priority=27,description="Verify the search starts with criteria")
	public void VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=28,description="Verify the search Ends with criteria")
	public void VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=29,description="Search without search text")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);;
		OCMViolationAccuracyReportPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(OCMViolationAccuracyReportPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}

	@Test(priority=30,description="Clear search functionality")
	public void SearchClear() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifySearchClear(reportDetails));    	
	}

	@Test(priority=31,description="Group By functionality")
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.groupby());
		screenshot.captureScreen("OCMViolationAccuracyReport", "GroupBy");
		Assert.assertTrue(OCMViolationAccuracyReportPage.groupby());
		screenshot.captureScreen("OCMViolationAccuracyReport", "AlreadyGroupBy");
	}
	
	@Test(priority=32,description="To verify Export Scheduler on OCM Reports Page")
	public void ScheduleOCMViolationAccuracyReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	
	@Test(priority=33,description="To verify Export Report on OCM Reports Page")
	public void ExportOCMViolationAccuracyReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=34,dependsOnMethods ="ExportOCMViolationAccuracyReport")
	public void ViewDownloadedOCMViolationAccuracyReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		OCMReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
	}  

	@Test(priority=35,dependsOnMethods ="ViewDownloadedOCMViolationAccuracyReportInReportsDownloadPage",description="To verify exported excel data")
	public void VerifyViewDownloadedOCMViolationAccuracyReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Violation Accuracy Rep"));	
	}

	@Test(priority=36,description="Delete record in Reports Download without Delete reason")
	public void DeleteWithoutDeleteReasonRecordinReportsDownloadforSingleDate() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deleteWithoutDeleteReason(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");	
	}

	@Test(priority=37,description="Cancel Button in Reports Download Delete Button")
	public void VerifyCancelBtnAtReportsDownloadforSingleDate() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();		
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}

	@Test(priority=38,description="Delete Record at Reports download Button")
	public void DeleteRecordforSingleDate() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Report Deleted","Delete record assertion failed");
	}

	@Test(priority=39,enabled=false,description="To verify Show Report for Date Range")
	public void ShowReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 

	@Test(priority=40,enabled=false,description="To verify Show Report for Date Range in New Page")
	public void ShowReportForDateRangeInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		OCMReportsPage.switchBackToParentWindow();
	}

	@Test(priority=41)
	public void ScheduleReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=42,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=43,dependsOnMethods ="ExportReportForDateRange",description="To verification of exported excel in Report downloads for Date Range")
	public void ViewDownloadedOCMViolationAccuracyReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
	}

	@Test(priority=44,dependsOnMethods ="ViewDownloadedOCMViolationAccuracyReportInReportsDownloadPageDateRange",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Violation Accuracy Rep"));		
	}

	@Test(priority=45,description="Delete record in Reports Download without Delete reason for date range")
	public void DeleteRecordWithoutDeleteReasoninReportsDownloadforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deleteWithoutDeleteReason(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
	}

	@Test(priority=46,description="Cancel Button in Reports Download Delete Button")
	public void VerifyDeleteCancelInReportsDownload() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}

	@Test(priority=47,description="Delete Record at Reports download Button")
	public void DeleteRecordAtReportsDownload() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails),"Record deletion failed");
	}
	
	@Test(priority=48,description="To Verify Export Page Button")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifyExportToExcel(filePath1));
	}

	@Test(priority=49,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMViolationAccuracyReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifyexportToExcelSheet(maplist));
	}	

	@Test(priority=50,description="Scheduled report button in Violation Accuracy page")
	public void SchedulereportinOCMViolationAccuracyReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);       
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		OCMViolationAccuracyReportPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(OCMViolationAccuracyReportPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
	}

	@Test(priority=51,description="Export to excel button in Violation Accuracy page")
	public void ExportToExcelForViolationAccuracyReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		//Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		OCMViolationAccuracyReportPage.exportToExcel();
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifyReportExported(),"export report assertion failed");
	} 

	@Test(priority=52,dependsOnMethods ="ExportToExcelForViolationAccuracyReport",description="Verify the view Downloaded report in Agent Violation Accuracy page")
	public void ViewDownloadedOCMViolationAccuracyReportInReportsDownloadPageinViolationAccuracyPg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		OCMViolationAccuracyReportPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");    
	}

	@Test(priority=53,dependsOnMethods ="ViewDownloadedOCMViolationAccuracyReportInReportsDownloadPageinViolationAccuracyPg",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedinOCMViolationAccuracyReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Violation Accuracy Rep"));		
	}
	
	@Test(priority=54,enabled=true,description="Verify the column headers against the Json File ")
	public void VerifyJsonDataForColumnNames() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		OCMViolationAccuracyReportPage.verifycolumnsHeaderEnabled();
		JSONReader json= new JSONReader(destinationFilePath);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifyJsonDataForgridColumnHidden(json.getJsonGridColumnTitleKeyDataForReports("Hidden")),"JSON data grid column hidden assertion failed");  	
	}

	//@Test(priority=55,description="To Verify Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMViolationAccuracyReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMViolationAccuracyReportPage OCMViolationAccuracyReportPage=PageFactory.createPageInstance(driver,OCMViolationAccuracyReportPage.class);
		Assert.assertTrue(OCMViolationAccuracyReportPage.verifySorting(),"Sorting assertion failed");
	}
	
	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("OCMViolationAccuracyReportTest",method.getName());
		driver.navigate().refresh();
	}

}
