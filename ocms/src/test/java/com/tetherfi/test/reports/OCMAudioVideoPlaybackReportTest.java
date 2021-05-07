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
import com.tetherfi.pages.OCMAudioVideoPlaybackReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMAudioVideoPlaybackReportTest extends BaseTest {
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
	public void ShowOCMAudioVideoPlaybackReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
	} 

	@Test(priority=2,dependsOnMethods ="ShowOCMAudioVideoPlaybackReport",description="To verify Show Report in New Tab for Single Date")
	public void ShowOCMAudioVideoPlaybackReportInNewTab() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		OCMReportsPage.switchBackToParentWindow();
	} 

	@Test(priority=3,description="To verify Audio Video Playback report UI data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAudioVideoPlaybackReportPage AudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(AudioVideoPlaybackReportPage.verifyDatabase(reportDetails.getQuery(), reportDetails), "UI and DB data mismatch");
		System.out.println("Database Validation Completed Succesfully" +" : "+"UI and Database data is matched");
	}
	
	@Test(priority=4,description="To verify Export Scheduler on OCM Reports Page")
	public void ScheduleOCMAudioVideoPlaybackReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	} 

	@Test(priority=5,description="To verify Export Report on OCM Reports Page")
	public void ExportOCMAudioVideoPlaybackReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=6,dependsOnMethods ="ExportOCMAudioVideoPlaybackReport")
	public void ViewDownloadedOcmAudioVideoPlaybackReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		OCMReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}  

	@Test(priority=7,description="To verify Show Report for Date Range")
	public void ShowOCMAudioVideoPlaybackReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 

	@Test(priority=8,dependsOnMethods ="ShowOCMAudioVideoPlaybackReportForDateRange",description="To verify Show Report for Date Range in New Tab")
	public void ShowOCMAudioVideoPlaybackReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		OCMReportsPage.switchBackToParentWindow();
	}

	@Test(priority=9,description="Export scheduler button")
	public void ScheduleOCMAudioVideoPlaybackReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	
	@Test(priority=10,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportOCMAudioVideoPlaybackReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=11,dependsOnMethods ="ExportOCMAudioVideoPlaybackReportDateRange",description="To verification of exported excel in Report downloads for Date Range")
	public void ViewDownloadedOcmAudioVideoPlaybackReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}
	
	@Test(priority=12,description="Delete record in Reports Download without Delete reason for date range")
	public void DeleteWithoutDeleteReasonRecordinReportsDownloadforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		Assert.assertTrue(ocmReportsPage.deleteWithoutDeleteReason(reportDetails),"empty delete reason record assertion failed");
	}

	@Test(priority=13,description="Cancel Button in Reports Download Delete Button")
	public void VerifyCancelBtnAtReportsDownloadDeleteBtnForDateRange() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}
	
	@Test(priority=14,description="Delete Record at Reports download Button")
	public void DeleteRecordAtReportsDownload() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Report Deleted","Delete record assertion failed");
	}

	@Test(priority=15,description="Report page clear All button ")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(OCMReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}

	@Test(priority=16,description="Maximize, minimize")
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);    
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);             
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMAudioVideoPlaybackReport","Maximize");
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMAudioVideoPlaybackReport","Minimize");	
	}

	@Test(priority=17,description="Verify dropdown of all the column headers")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);		
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}

	@Test(priority=18,description="Verify column header Enable")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);	
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}

	@Test(priority=19,description="Verify column header disable")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertFalse(OCMAudioVideoPlaybackReportPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}

	@Test(priority=20,description="Verify Pagination, Move to previous and next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	} 

	@Test(priority=21,description="Verify Pagination, Move to First and Last Page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}

	@Test(priority=22,description="Verify total number of items per page")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAudioVideoPlaybackReportPage AudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(AudioVideoPlaybackReportPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}

	@Test(priority=23,enabled=false,description="Verify number of items selected per page")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	} 

	@Test(priority=24,description="To Verify Export Page Button")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifyExportToExcel(filePath1));
	}

	@Test(priority=25,description="Scheduled report button in AudiovideoPlayback report page")
	public void SchedulereportinOCMAudioVideoPlaybackReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);       
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		OCMAudioVideoPlaybackReportPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
	}

	@Test(priority=26,description="Export to excel button in AudiovideoPlayback Report page")
	public void ExportToExcelForAudioVideoPlaybackReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		OCMAudioVideoPlaybackReportPage.exportToExcel();
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifyReportExported(),"export report assertion failed");
	} 

	@Test(priority=27,dependsOnMethods ="ExportToExcelForAudioVideoPlaybackReport",description="Verify the view Downloaded report in Agent summary report page")
	public void ViewDownloadedOcmAudioVideoPlaybackReportInReportsDownloadPageinAudioVideoPlaybackPg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		OCMAudioVideoPlaybackReportPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");    
	}

	@Test(priority=28,description="To Verify Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifySorting(),"item per page assertion failed");
	}

	@Test(priority=29,description="Search by feature")
	public void VerifySearchByFeatureForAudioVideoPlaybackReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifySearchByTextbox(reportDetails));
	}

	@Test(priority=30,description="Verify the search Is equal to criteria")
	public void VerifySearchByFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}
	
	@Test(priority=31,description="Verify the search Is not equal to criteria")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}

	@Test(priority=32,description="Verify the search contains criteria")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifySearchContains(reportDetails.getSearchStr()));
	}

	@Test(priority=33,description="Verify the Does not contain criteria")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}

	@Test(priority=34,description="Verify the search starts with criteria")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=35,description="Verify the search Ends with criteria")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=36,description="Search without search text")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		OCMAudioVideoPlaybackReportPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(OCMAudioVideoPlaybackReportPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}

	@Test(priority=37,description="Clear search functionality")
	public void SearchClear() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifySearchClear(reportDetails));    	
	}

	@Test(priority=38,description="Advance search on reports page for Is equal to Criteria")
	public void verifyAdvancedSearchinreportpageSearchEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifyAdvanceSearchIsEqualTo(reportDetails));
	}
	
	@Test(priority=39,description="Advance search on reports page for Is not equal to Criteria")
	public void verifyAdvancedSearchinreportpageSearchNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifyAdvanceSearchIsNotEqualTo(reportDetails));
	}

	@Test(priority=40,description="Advance search on reports page for Contains Criteria")
	public void verifyAdvancedSearchinreportpageSearchcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class); 
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifyAdvanceSearchContains(reportDetails));    	
	}

	@Test(priority=41,description="Advance search on reports page for Does not Contain Criteria")
	public void verifyAdvancedSearchinreportpageSearchDoesnotcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifyAdvanceSearchDoesNotContains(reportDetails));   
	}

	@Test(priority=42,description="Advance search on reports page for Starts with Criteria")
	public void verifyAdvancedSearchSearchStartswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class); 
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifyAdvanceSearchStartsWith(reportDetails)); 	
	}

	@Test(priority=43,description="Advance search on reports page for Ends with Criteria")
	public void verifyAdvancedSearchSearchEndswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.verifyAdvanceSearchEndsWith(reportDetails));
	}
	
	@Test(priority=44,description="Advance search with And Condition")
	public void verifyAdvancedSearchANDCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.advancedSearchANDCriteria(reportDetails));   	
	}

	@Test(priority=45,description="Advance search with OR Condition")
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.advancedSearchORCriteria(reportDetails));
	}
	
	@Test(priority=46,description="Clear filters for Advance search")
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(OCMReportsPage.ClearAdvFilters(reportDetails));
	} 

	@Test(priority=47,description="Group By fuctionality")
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAudioVideoPlaybackReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAudioVideoPlaybackReportPage OCMAudioVideoPlaybackReportPage=PageFactory.createPageInstance(driver,OCMAudioVideoPlaybackReportPage.class);
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.groupby());
		screenshot.captureScreen("OCMAudioVideoPlaybackReport", "GroupBy");
		Assert.assertTrue(OCMAudioVideoPlaybackReportPage.groupby());
		screenshot.captureScreen("OCMAudioVideoPlaybackReport", "AlreadyGroupBy");
	}


	@AfterMethod
	public void afterEachMethod(Method method) {
		screenshot.captureScreen("OCMAudioVideoPlaybackReport", method.getName());
		driver.navigate().refresh();
	}
}
