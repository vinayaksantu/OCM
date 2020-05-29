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
import com.tetherfi.pages.OCMFrequencyReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.OCMTimelineReportPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMTimelineReportTest extends BaseTest {
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
	public void ShowOCMTimelineReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");      
	}  

	@Test(priority=2,description="To verify Show Report in New Tab for Single Date")
	public void ShowOcmTimelineReportInNewTab() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}

	@Test(priority=3,description="To verify Export Report on OCM Reports Page")
	public void ExportOcmTimelineReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	} 

	@Test(priority=4,description="To verify Export Scheduler on OCM Reports Page")
	public void ScheduleOCMTimelineReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=5,dependsOnMethods ="ExportOcmTimelineReport",description="To verify view download in Report downloads")
	public void ViewDownloadedOcmTimelineReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	} 

	@Test(priority=6)//,dependsOnMethods ="ViewDownloadedOcmTimelineReportInReportsDownloadPage",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOcmTimeLineReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM TimeLine Report"));	
	}


	@Test(priority=7,description="Delete record in Reports Download without Delete reason")
	public void DeleteWithoutDeleteReasonRecordinReportsDownloadforSingleDate() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();   	
		ocmReportsPage.deleteWithoutDeleteReason(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
	}

	@Test(priority=8,description="Cancel Button in Reports Download Delete Button")
	public void VerifyCancelBtnAtReportsDownloadforSingleDate() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}

	@Test(priority=9,description="Delete Record at Reports download Button")
	public void DeleteRecordforSingleDate() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Report Deleted","Delete record assertion failed");
	}

	@Test(priority=10,description="To verify Show Report for Date Range")
	public void ShowOcmTimelineReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 

	@Test(priority=11,description="To verify Show Report for Date Range in New Tab")
	public void ShowOcmTimelineReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}   

	@Test(priority=12,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportOcmTimelineReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=13,description="To verify Export Scheduler on OCM Reports Page for Date Range")
	public void ScheduleOcmTimelineReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=14,dependsOnMethods ="ExportOcmTimelineReportDateRange",description="To verification of exported excel in Report downloads for Date Range")
	public void ViewDownloadedOcmTimelineReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}

	@Test(priority=15,dependsOnMethods ="ViewDownloadedOcmTimelineReportInReportsDownloadPageDateRange",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOcmTimelineReportInReportsDownloadDateRangePage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM TimeLine Report"));	
	} 

	@Test(priority=16,description="Delete Record at Reports download Button")
	public void DeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Report Deleted","Delete record assertion failed");
	}

	@Test(priority=17,description="To verfiy clear all button")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
	}

	@Test(priority=18,description="To Verify OCM Window Maximize minimize")
	public void OCMWindow() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);       
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);               
		Assert.assertTrue(timelinePage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen(driver,"OCMTimelineReport","Maximize");
		Assert.assertTrue(timelinePage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen(driver,"OCMTimelineReport","Minimize");	
	}	

	@Test(priority=19,description="To Verify Dropdown for All the Columns")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);	
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	} 

	@Test(priority=20,description="To Verify Columns Headers are Enabled")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);	
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}  

	@Test(priority=21,description="To Verify Columns Headers are Disabled")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertFalse(timelinePage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	} 

	@Test(priority=22,description="To Verify Arrow move for Previous and Next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception { 
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	} 

	@Test(priority=23,description="To Verify Arrow move for first and last page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}  

	@Test(priority=24,description="To Verify Total Number of Items Per Page Details")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}  

	@Test(priority=25,description="To Verify Number of Items Per Page Selection")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	} 

	@Test(priority=26,description="To Verify Export Page Button")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifyExportToExcel(filePath1));
	} 


	@Test(priority=27,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMTimeLineReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifyexportToExcelSheet(maplist));
	}


	@Test(priority=28,description="To verify Records Sort By Ascending")
	public void SortingByAscending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		timelinePage.SortByAscending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMTimeLineReport (1).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(timelinePage.verifyexportToExcelSheet(maplist));
	}  
	@Test(priority=29,description="To verify records Sort By Descending")
	public void SortingByDescending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);      
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		timelinePage.SortByDescending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMTimeLineReport (2).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(timelinePage.verifyexportToExcelSheet(maplist));
	}  

	@Test(priority=30,description="To Verify Schedule Report button on Main Page")
	public void SchedulereportinTimelinePage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);            
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		timelinePage.navigateToExportSchedulerPage();    
		Assert.assertTrue(timelinePage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
		screenshot.captureScreen("OCMTimelineReportTest","ExportSchedulerPage");
	}

	@Test(priority=31,description="To Verify Export Excel button on Main Page")
	public void ExportToExcelForTimelineReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMTimelineReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		agntloginlogoutPage.exportToExcel();
		Assert.assertTrue(agntloginlogoutPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=32,dependsOnMethods ="ExportToExcelForTimelineReport",description="To Verify View Download button on Main Page")
	public void ViewDownloadedOcmTimelineReportInReportsDownloadPageinTimelinePg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		timelinePage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");  
	}   

	@Test(priority=33,dependsOnMethods ="ViewDownloadedOcmTimelineReportInReportsDownloadPageinTimelinePg",description="Verification of exported excel from main page in Report downloads")
	public void VerifyViewDownloadedOcmTimeLineReportInReportsDownload() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM TimeLine Report"));	
	}

	@Test(priority=34,description="To verify search by feature")
	public void VerifySearchByFeatureForTimelineReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(timelinePage.verifySearchByTextbox(reportDetails));
	}

	@Test(priority=35,description="To verify search equals")
	public void VerifySearchEqualsFeatureForTimelineReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(timelinePage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	} 

	@Test(priority=36,description="To verify search without providing data in searchbox")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		timelinePage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(timelinePage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}  

	@Test(priority=37,description="To verify search IsNotEquals")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}   

	@Test(priority=38,description="To verify search Contains")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifySearchContains(reportDetails.getSearchStr()));
	}  

	@Test(priority=39,description="To verify search doesnotContains")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}

	@Test(priority=40,description="To verify search StartsWith")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=41,description="To verify search EndsWith")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=42,description="To verify search Clear Search")
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		timelinePage.verifySearchClear(reportDetails);    	
	}

	@Test(priority=43,description="To verify Advance Search Equals")
	public void verifyAdvancedSearchinreportpage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);        
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(timelinePage.verifyAdvanceSearch(reportDetails));            
	}
	@Test(priority=44)
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		ocmReportsPage.ClearAdvFilters(reportDetails);
	} 

	@Test(priority=45)
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);   	
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.groupby());
		screenshot.captureScreen("OCMTimeLineReport", "GroupBy");
		Assert.assertTrue(timelinePage.groupby());
		screenshot.captureScreen("OCMTimeLineReport", "AlreadyGroupBy");
	}

	@Test(priority=46)
	public void verifyAdvancedSearchAndCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(TimelinePage.advancedSearchAndCriteria(reportDetails));   	
	}


	@Test(priority=47)
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(TimelinePage.advancedSearchORCriteria(reportDetails));   	
	}

	@Test(priority=48,description="To verify Advance Search Not Equal To")
	public void verifyAdvancedSearchinreportpageSearchNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyAdvanceSearchNotEqualsTo(reportDetails)); 
	}

	@Test(priority=49,description="To verify Advance Search Contains")
	public void verifyAdvancedSearchinreportpageSearchcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyAdvanceSearchContains(reportDetails)); 
	}

	@Test(priority=50,description="To verify Advance Search DoesNot Contains")
	public void verifyAdvancedSearchinreportpageSearchDoesnotcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyAdvanceSearchDoesNotContains(reportDetails)); 
	}

	@Test(priority=51,description="To verify Advance Search Starts With")
	public void verifyAdvancedSearchinreportpageSearchStartswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyAdvanceSearchStartsWith(reportDetails)); 
	}

	@Test(priority=52,description="To verify Advance Search Ends With")
	public void verifyAdvancedSearchinreportpageSearchEndswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyAdvanceSearchEndsWith(reportDetails)); 
	}

	@Test(priority=53,description="To Verify Arrow move for Previous and Next page for Drill Down One")
	public void VerifyArrowMoveForPreviousAndNextPageForDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyArrowMoveForPreviousAndNextPageForDrillDownOne(reportDetails),"arrow move for previous and next page assertion failed");
	} 
	@Test(priority=54,description="To Verify Arrow move for first and last page for Drill Down One")
	public void VerifyArrowMoveForFirstAndLastPageFoDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyArrowMoveForFirstAndLastPageForDrillDownOne(reportDetails),"arrow move for first and last page assertion failed");
	} 

	@Test(priority=55,description="To Verify Total Number of Items Per Page Details for Drill Down One")
	public void VerifyTotalNumberOfItemsPerPageDetailsFoDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyTotalNumberOfItemsPerPageDetailsForDrillDownOne(),"item per page assertion failed");
	}  

	@Test(priority=56)
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyDatabase(reportDetails.getQuery()));
	}

	@AfterMethod
	public void afterEachMethod(Method method) {
		screenshot.captureScreen(driver, "", method.getName());
		driver.navigate().refresh();
	}
}