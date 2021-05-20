
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
import com.tetherfi.pages.OCMAnalysisCountReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMAnalysisCountReportTest extends BaseTest {

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
	public void ShowOCMAnalysisCountReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
	} 

	@Test(priority=2,description="To verify Show Report in New Tab for Single Date")
	public void ShowOCMAnalysisCountReportInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		OCMReportsPage.switchBackToParentWindow();
	}

	@Test(priority=3,description="To verify IVR Menu Analysis report UI data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAnalysisCountReportPage AnalysisCountReportPage=PageFactory.createPageInstance(driver, OCMAnalysisCountReportPage.class); 
		AnalysisCountReportPage.sortAscByMenu();
		Assert.assertTrue(AnalysisCountReportPage.verifyDatabase(reportDetails.getQuery(), reportDetails), "Main report Data Mismatch");   		
		System.out.println("Database Validation Completed Succesfully" +" : "+"UI and Database data is matched"); 	
	}

	@Test(priority=4,description="To verify Export Scheduler on OCM Reports Page")
	public void ScheduleOCMAnalysisCountReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=5,description="Advance search with OR Condition")
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(OCMAnalysisCountReportPage.advancedSearchORCriteria(reportDetails));
	}

	@Test(priority=6,description="Advance search with And Condition")
	public void verifyAdvancedSearchANDCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMAnalysisCountReportPage AnalysisCountReportPage=PageFactory.createPageInstance(driver, OCMAnalysisCountReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(AnalysisCountReportPage.advancedSearchANDCriteria(reportDetails));   	
	}

	@Test(priority=7,description="Advance search on reports page for Is equal to Criteria")
	public void verifyAdvancedSearchinreportpageSearchEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifyAdvanceSearchIsEqualTo(reportDetails));
	}

	@Test(priority=8,description="Advance search on reports page for Is not equal to Criteria")
	public void verifyAdvancedSearchinreportpageSearchNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifyAdvanceSearchIsNotEqualTo(reportDetails));
	}

	@Test(priority=9,description="Advance search on reports page for Contains Criteria")
	public void verifyAdvancedSearchinreportpageSearchcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class); 
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifyAdvanceSearchContains(reportDetails));    	
	}

	@Test(priority=10,description="Advance search on reports page for Does not Contain Criteria")
	public void verifyAdvancedSearchinreportpageSearchDoesnotcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifyAdvanceSearchDoesNotContains(reportDetails));   
	}

	@Test(priority=11,description="Advance search on reports page for Starts with Criteria")
	public void verifyAdvancedSearchStartswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class); 
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifyAdvanceSearchStartsWith(reportDetails)); 	
	}

	@Test(priority=12,description="Advance search on reports page for Ends with Criteria")
	public void verifyAdvancedSearchEndswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifyAdvanceSearchEndsWith(reportDetails));
	}

	@Test(priority=13,description="Search by feature")
	public void VerifySearchByFeatureForAnalysisCountReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifySearchByTextbox(reportDetails));
	}

	@Test(priority=14,description="Verify the search Is equal to criteria")
	public void VerifySearchIsEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(OCMAnalysisCountReportPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}

	@Test(priority=15,description="Verify the search Is not equal to criteria")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}

	@Test(priority=16,description="Verify the search contains criteria")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifySearchContains(reportDetails.getSearchStr()));
	}

	@Test(priority=17,description="Verify the Does not contain criteria")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}

	@Test(priority=18,description="Verify the search starts with criteria")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=19,description="Verify the search Ends with criteria")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=20,description="Search without search text")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		OCMAnalysisCountReportPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(OCMAnalysisCountReportPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}

	@Test(priority=21,description="Clear search functionality")
	public void SearchClear() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifySearchClear(reportDetails));    	
	}

	@Test(priority=22,description="To verify Export Report on OCM Reports Page")
	public void ExportOCMAnalysisCountReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=23,dependsOnMethods ="ExportOCMAnalysisCountReport",description="To verif View downloaded report on Report page")
	public void ViewDownloadedReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		OCMReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}  

	@Test(priority=24,description="To verify Show Report for Date Range")
	public void ShowOCMAnalysisCountReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 

	@Test(priority=25,description="To verify Show Report for Date Range in New Tab")
	public void ShowOCMAnalysisCountReportInNewPageForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		OCMReportsPage.switchBackToParentWindow();
	}

	@Test(priority=26,description="Schedule report on reports page")
	public void ScheduleOCMAnalysisCountReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=27,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportOCMAnalysisCountReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=28,dependsOnMethods ="ExportOCMAnalysisCountReportDateRange",description="To verification of exported excel in Report downloads for Date Range")
	public void ViewDownloadedOcmAnalysisCountReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}

	@Test(priority=29,description="Delete record in Reports Download without Delete reason for date range")
	public void DeleteWithoutDeleteReasonRecordinReportsDownloadforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		Assert.assertTrue(ocmReportsPage.deleteWithoutDeleteReason(reportDetails),"empty delete reason record assertion failed");		
	}

	@Test(priority=30,description="To verify Cancel Button in Report downloads")
	public void VerifyDeleteCancelInReportDownloads() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}

	@Test(priority=31,description="Delete Record at Reports download Button")
	public void DeleteRecordAtReportsDownload() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails),"Delete record assertion failed");		
	}

	@Test(priority=32,description="Report page clear All button ")	
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(OCMReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}

	@Test(priority=33,description="Maximize, minimize window")
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);    
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);             
		Assert.assertTrue(OCMAnalysisCountReportPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMAnalysisCountReport","Maximize");
		Assert.assertTrue(OCMAnalysisCountReportPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMAnalysisCountReport","Minimize");	
	}

	@Test(priority=34,description="Verify dropdown of all the coulnm headers")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);		
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}

	@Test(priority=35,description="Verify column header Enable")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);	
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}

	@Test(priority=36,description="Verify column header disable")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertFalse(OCMAnalysisCountReportPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}

	@Test(priority=37,description="Verify Pagination, Move to previous and next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	} 

	@Test(priority=38,description="Verify Pagination, Move to First and Last Page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}

	@Test(priority=39,description="Verify total number of items per page")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAnalysisCountReportPage AnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(AnalysisCountReportPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}

	@Test(priority=40,enabled=false,description="Verify number of items selected per page")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}

	@Test(priority=41,description="To Verify Export Page Button")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifyExportToExcel(filePath1));
	}

	@Test(priority=42,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMIvrMenuAnalysisReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(OCMAnalysisCountReportPage.verifyexportToExcelSheet(maplist));
	}	

	@Test(priority=43,description="Scheduled report button in OCMAnalysis Count report page")
	public void SchedulereportinOCMAnalysisCountReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);       
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		OCMAnalysisCountReportPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(OCMAnalysisCountReportPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
	}

	@Test(priority=44,description="Export to excel button in OCMAnalysis Count Report page")
	public void ExportToExcel() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		OCMAnalysisCountReportPage.exportToExcel();
		Assert.assertTrue(OCMAnalysisCountReportPage.verifyReportExported(),"export report assertion failed");
	} 

	@Test(priority=45,dependsOnMethods ="ExportToExcel",description="Verify the view Downloaded report in OCMAnalysisCount report page")
	public void ViewDownloadedOcmAnalysisCountReportInReportsDownloadPageinAnalysisCountPg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		OCMAnalysisCountReportPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");    
	}

	@Test(priority=46,description="Clear filters for Advance search")
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(OCMReportsPage.ClearAdvFilters(reportDetails));
	}

	@Test(priority=47,description="Group By functionality")
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAnalysisCountReportPage OCMAnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(OCMAnalysisCountReportPage.groupby());
		screenshot.captureScreen("OCMAnalysisCountReport", "GroupBy");
		Assert.assertTrue(OCMAnalysisCountReportPage.groupby());
		screenshot.captureScreen("OCMAnalysisCountReport", "AlreadyGroupBy");
	}

	@Test(priority=48,description="To Verify Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAnalysisCountReportPage AnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(AnalysisCountReportPage.verifySorting(),"Sorting assertion failed");
	}

	@Test(priority=49,description="To Verify View Summary Button")
	public void VerifyViewSummary() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAnalysisCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAnalysisCountReportPage AnalysisCountReportPage=PageFactory.createPageInstance(driver,OCMAnalysisCountReportPage.class);
		Assert.assertTrue(AnalysisCountReportPage.verifyViewSummary(),"view summary error");
	}

	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("OCMAnalysisCountReportProduct",method.getName());
		driver.navigate().refresh();
	}


}

