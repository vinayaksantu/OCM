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
import com.tetherfi.pages.OCMEventTraceReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.OCMViolationBugReportPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMTRSViolationBugReportTest extends BaseTest {
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
	public void ShowOCMViolationBugeReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");      
	}   
	
	@Test(priority=2,description="To verify Show Report in New Tab for Single Date")
	public void ShowOCmViolationBugReportInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}
	
	@Test(priority=3,description="To verify Export Report on OCM Reports Page")
	public void ExportOCMViolationBugReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	} 
	

	@Test(priority=4,description="To verify Export Scheduler on OCM Reports Page")
	public void ScheduleOCMViolationBugReportInREportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	
	@Test(priority=5,dependsOnMethods ="ScheduleOCMViolationBugReportInREportPage",description="To verify view download in Report downloads")
	public void ViewDownloadedOcmViolationBugReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	} 
	
	@Test(priority=6,description="To verify Show Report for Date Range")
	public void ShowOcmViolationBugReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 
	
	@Test(priority=7,description="To verify Show Report for Date Range in New Tab")
	public void ShowOcmViolationBugReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	} 
	
	@Test(priority=8,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportOcmViolationBugReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map); 
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}
	
	@Test(priority=9,description="To verify Export Scheduler on OCM Reports Page for Date Range")
	public void ScheduleOcmViolationBugReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
 
	@Test(priority=10,dependsOnMethods ="ScheduleOcmViolationBugReportforDateRange",description="To verification of exported excel in Report downloads for Date Range")
	public void ViewDownloadedOcmViolationBugReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}  
	
	@Test(priority=11,description="Delete record in Reports Download without Delete reason")
	public void DeleteWithoutDeleteReasonRecordinReportsDownload() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();   	
		ocmReportsPage.deleteWithoutDeleteReason(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
	}

	@Test(priority=12,description="Cancel Button in Reports Download Delete Button")
	public void VerifyCancelBtnAtReportsDownload() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}
	
	@Test(priority=13,description="Delete Record at Reports download Button")
	public void DeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Report Deleted","Delete record assertion failed");
	}
	
	@Test(priority=14,description="To verfiy clear all button")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}
	
	@Test(priority=15,description="To Verify OCM Window Maximize minimize")
	public void OCMWindow() throws Exception { 
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMEventTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);       
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);               
		Assert.assertTrue(violatioBugPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMViolationBugReport","Maximize");
		Assert.assertTrue(violatioBugPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMViolationBugReport","Minimize");	
	}
	
	
	@Test(priority=16,description="To Verify Dropdown for All the Columns")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);	
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}  
	
	@Test(priority=17,description="To Verify Columns Headers are Enabled")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);	
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}    
	
	@Test(priority=18,description="To Verify Columns Headers are Disabled")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertFalse(violatioBugPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}  
	
	
	@Test(priority=19,description="To Verify Arrow move for Previous and Next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception { 
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	} 
	//test
	@Test(priority=20,description="To Verify Arrow move for first and last page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}  
	
	@Test(priority=21,description="To Verify Total Number of Items Per Page Details")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}  
	
	@Test(priority=22,description="To Verify Number of Items Per Page Selection")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	} 
 
	@Test(priority=23,description="To Verify Export Page Button")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifyExportToExcel(filePath1)); 
	} 
	
	@Test(priority=24,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0); 
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMTRSReportBugReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=25,description="To Verify Schedule Report button on Main Page")
	public void SchedulereportInViolationBugPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);            
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		violatioBugPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(violatioBugPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
		screenshot.captureScreen("OCMViolationBugReportTest","ExportSchedulerPage");
	} 
	
	@Test(priority=26,description="To Verify Export Excel button on Main Page")
	public void ExportToExcelForViolationBugReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		violatioBugPage.exportToExcel();  
		Assert.assertTrue(violatioBugPage.verifyReportExported(),"export report assertion failed");
	}
	
	@Test(priority=27,dependsOnMethods ="ExportToExcelForViolationBugReport",description="To Verify View Download button on Main Page")
	public void ViewDownloadedOcmViolationBugReportInReportsDownloadPageinEventTracePg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		violatioBugPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");  
	}   
	
	@Test(priority=28,description="To verify search by feature")
	public void VerifySearchByFeatureForEventTraceReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(violatioBugPage.verifySearchByTextbox(reportDetails));
	}  

	@Test(priority=29,description="To verify search equals")
	public void VerifySearchEqualsFeatureForEventTraceReport1() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(violatioBugPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	} 
	
	@Test(priority=30,description="To verify search without providing data in searchbox")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		violatioBugPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(violatioBugPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}  

	@Test(priority=31,description="To verify search Is Not Equal")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}   

	@Test(priority=32,description="To verify search Contains")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifySearchContains(reportDetails.getSearchStr()));
	}   

	@Test(priority=33,description="To verify search Does Not Contains")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}

	@Test(priority=34,description="To verify search StartsWith")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=35,description="To verify search EndsWith")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}
	
	
	@Test(priority=36,description="To verify search Clear Search")
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		violatioBugPage.verifySearchClear(reportDetails);    	
	}
	
	@Test(priority=37,description="To verify Advance Search Equals")
	public void verifyAdvancedSearchinreportpage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);        
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(violatioBugPage.verifyAdvanceSearch(reportDetails));            
	}

	@Test(priority=38,description="To Verify Clear advanced filters")
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(ocmReportsPage.ClearAdvFilters(reportDetails));
	}
	
	
	//hold
		@Test(priority=39,description="To verify GroupBy functionality")
		public void GroupBy() throws Exception{
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
			ReportDetails reportDetails= new ReportDetails(map);
			OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);   	
			ocmReportsPage.showReport(reportDetails);
			OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
			Assert.assertTrue(violatioBugPage.groupby());
			screenshot.captureScreen("OCMTRSReportBugReport", "GroupBy");
			Assert.assertTrue(violatioBugPage.groupby());
			screenshot.captureScreen("OCMTRSReportBugReport", "AlreadyGroupBy");
		}
		
		@Test(priority=40, description="To Verify Adavanced Search AND Criteria")
		public void verifyAdvancedSearchAndCriteria() throws Exception {
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
			OCMEventTraceReportPage eventtracePage=PageFactory.createPageInstance(driver,OCMEventTraceReportPage.class);
			ReportDetails reportDetails= new ReportDetails(map);
			OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
			ocmReportsPage.chooseReport(reportDetails);
			Assert.assertTrue(eventtracePage.advancedSearchAndCriteria(reportDetails));   	
		}
		
		@Test(priority=41, description="To verify Adavanced search OR Criteria")
		public void verifyAdvancedSearchORCriteria() throws Exception {
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
			OCMEventTraceReportPage eventtracePage=PageFactory.createPageInstance(driver,OCMEventTraceReportPage.class);
			ReportDetails reportDetails= new ReportDetails(map);
			OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
			ocmReportsPage.chooseReport(reportDetails);
			Assert.assertTrue(eventtracePage.advancedSearchORCriteria(reportDetails));   	
		}

		@Test(priority=42,description="To verify Advance Search Not Equal To")
		public void verifyAdvancedSearchinreportpageSearchNotEqualTo() throws Exception {
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
			ReportDetails reportDetails= new ReportDetails(map);
			OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
			OCMReportsPage.showReport(reportDetails);
			OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
			Assert.assertTrue(violatioBugPage.verifyAdvanceSearchNotEqualsTo(reportDetails)); 
		}
	
	@Test(priority=43,description="To verify Advance Search Contains")
	public void verifyAdvancedSearchinreportpageSearchcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifyAdvanceSearchContains(reportDetails)); 
	}
	
	
	@Test(priority=44,description="To verify Advance Search DoesNot Contains")
	public void verifyAdvancedSearchinreportpageSearchDoesnotcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifyAdvanceSearchDoesNotContains(reportDetails)); 
	}

	@Test(priority=45,description="To verify Advance Search Starts With")
	public void verifyAdvancedSearchinreportpageSearchStartswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifyAdvanceSearchStartsWith(reportDetails)); 
	}

	@Test(priority=46,description="To verify Advance Search Ends With")
	public void verifyAdvancedSearchinreportpageSearchEndswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifyAdvanceSearchEndsWith(reportDetails)); 
	}
	//hold
	@Test(priority=47,description="To Verify Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTRSViolationBugReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMViolationBugReportPage violatioBugPage=PageFactory.createPageInstance(driver,OCMViolationBugReportPage.class);
		Assert.assertTrue(violatioBugPage.verifySorting(),"item per page assertion failed");
	}
	
	
	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("OCMEventTraceReport",method.getName());
		driver.navigate().refresh();
	}



}
