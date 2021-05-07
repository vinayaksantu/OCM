package com.tetherfi.test.reports;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMIvrCallTraceReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
public class OCMIVRCallTraceReportTest extends BaseTest {
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
	public void ShowOCMIvrCallTraceReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
	} 

	@Test(priority=2,dependsOnMethods ="ShowOCMIvrCallTraceReport",description="To verify Show Report in New Tab for Single Date")
	public void ShowOCMIvrCallTraceReportInNewTab() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		OCMReportsPage.switchBackToParentWindow();
	} 

	@Test(priority=3,description="To verify Export Scheduler on OCM Reports Page")
	public void ScheduleOCMIvrCallTraceReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	} 

	@Test(priority=4,description="To verify Export Report on OCM Reports Page")
	public void ExportOCMIvrCallTraceReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=5,dependsOnMethods ="ExportOCMIvrCallTraceReport")
	public void ViewDownloadedOcmIvrCallTraceReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		OCMReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}  

	@Test(priority=6,dependsOnMethods ="ViewDownloadedOcmIvrCallTraceReportInReportsDownloadPage",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOcmIvrCallTraceReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM IVR Call Trace Report"));	
	}


	@Test(priority=7,description="To verify Show Report for Date Range")
	public void ShowOCMIvrCallTraceReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 

	@Test(priority=8,dependsOnMethods ="ShowOCMIvrCallTraceReportForDateRange",description="To verify Show Report for Date Range in New Tab")
	public void ShowOCMIvrCallTraceReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		OCMReportsPage.switchBackToParentWindow();
	}

	@Test(priority=9,description="Export scheduler button")
	public void ScheduleOCMIvrCallTraceReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	@Test(priority=10,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportOCMIvrCallTraceReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=11,dependsOnMethods ="ExportOCMIvrCallTraceReportDateRange",description="To verification of exported excel in Report downloads for Date Range")
	public void ViewDownloadedOcmIvrCallTraceReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}
	@Test(priority=12,dependsOnMethods ="ViewDownloadedOcmIvrCallTraceReportInReportsDownloadPageDateRange",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOcmIvrCallTraceReportInReportsDownloadDateRangePage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM IVR Call Trace Report"));		
	}
	@Test(priority=13,description="Delete record in Reports Download without Delete reason for date range")
	public void DeleteWithoutDeleteReasonRecordinReportsDownloadforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deleteWithoutDeleteReason(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
	}

	@Test(priority=14,description="Cancel Button in Reports Download Delete Button")
	public void VerifyCancelBtnAtReportsDownloadDeleteBtnForDateRange() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}
	@Test(priority=15,description="Delete Record at Reports download Button")
	public void DeleteRecordAtReportsDownload() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Report Deleted","Delete record assertion failed");
	}

	@Test(priority=16,description="Report page clear All button ")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(OCMReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}

	@Test(priority=17,description="Maximize, minimize")
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);    
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);             
		Assert.assertTrue(OCMIvrCallTraceReportPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMIvrCallTraceReport","Maximize");
		Assert.assertTrue(OCMIvrCallTraceReportPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMIvrCallTraceReport","Minimize");	
	}

	@Test(priority=18,description="Verify dropdown of all the coulnm headers")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);		
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}

	@Test(priority=19,description="Verify column header Enable")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);	
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}

	@Test(priority=20,description="Verify column header disable")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertFalse(OCMIvrCallTraceReportPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}

	@Test(priority=21,description="Verify Pagination, Move to previous and next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	} 

	@Test(priority=22,description="Verify Pagination, Move to First and Last Page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}

	@Test(priority=23,description="Verify total number of items per page")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage IvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(IvrCallTraceReportPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}

	@Test(priority=24,description="Verfiy number of items selected per page")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	} 

	@Test(priority=25,description="To Verify Export Page Button")
	public void ExportPage() throws Exception
	{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifyExportToExcel(filePath1));
	}

	@Test(priority=26,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception
	{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMIVRCallTraceReportData.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=27,description="Scheduled report button in IvrCallTrace report page")
	public void SchedulereportinOCMIvrCallTraceReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);       
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		OCMIvrCallTraceReportPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(OCMIvrCallTraceReportPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");		    	 
	}

	@Test(priority=28,description="Export to excel button in IvrCallTrace  Report page")
	public void ExportToExcelForIvrCallTraceReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		OCMIvrCallTraceReportPage.exportToExcel();
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifyReportExported(),"export report assertion failed");
	} 

	@Test(priority=29,dependsOnMethods ="ExportToExcelForIvrCallTraceReport",description="Verify the view Downloaded report in IvrCallTrace report page")
	public void ViewDownloadedOcmIvrCallTraceReportInReportsDownloadPageinIvrCallTracePg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		OCMIvrCallTraceReportPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");    
	}

	@Test(priority=30,dependsOnMethods ="ViewDownloadedOcmIvrCallTraceReportInReportsDownloadPageDateRange",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOcmIvrCallTraceReportInReportsDownloadIvrCallTracePg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM IVR Call Trace Report"));		
	}

	@Test(priority=31,description="To Verify Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifySorting(),"item per page assertion failed");
	}

	@Test(priority=32,description="Search by feature")
	public void VerifySearchByFeatureForIvrCallTraceReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");     
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifySearchByTextbox(reportDetails));
	}

	@Test(priority=33,description="Verify the search Is equal to criteria")
	public void VerifySearchFeatureForIvrCallTraceReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}
	@Test(priority=34,description="Verify the search Is not equal to criteria")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}
	@Test(priority=35,description="Verify the search contains criteria")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifySearchContains(reportDetails.getSearchStr()));
	}

	@Test(priority=36,description="Verify the Does not contain criteria")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}

	@Test(priority=37,description="Verify the search starts with criteria")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=38,description="Verify the search Ends with criteria")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=39,description="Search without search text")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		OCMIvrCallTraceReportPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(OCMIvrCallTraceReportPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}

	@Test(priority=40,description="Clear search functionality")
	public void SearchClear() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifySearchClear(reportDetails));    	
	}

	@Test(priority=41,description="Advance search on reports page for Is equal to Criteria")
	public void verifyAdvancedSearchinreportpageSearchEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifyAdvanceSearchIsEqualTo(reportDetails));
	}
	@Test(priority=47,description="Advance search with And Condition")
	public void verifyAdvancedSearchANDCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		//To select Report Channel,name,Type
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(OCMIvrCallTraceReportPage.advancedSearchANDCriteria(reportDetails));   	
	}

	@Test(priority=42,description="Advance search with OR Condition")
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		//To select Report Channel,name,Type
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(OCMIvrCallTraceReportPage.advancedSearchORCriteria(reportDetails));

	}
	@Test(priority=43,description="Clear filters for Advance search")
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(OCMReportsPage.ClearAdvFilters(reportDetails));
	} 
	@Test(priority=44,description="Group By fuctionality")
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.groupby());
		screenshot.captureScreen("OCMIvrCallTraceReport", "GroupBy");
		Assert.assertTrue(OCMIvrCallTraceReportPage.groupby());
		screenshot.captureScreen("OCMIvrCallTraceReport", "AlreadyGroupBy");
	}

	@Test(priority=45,description="To Verify Menu traversal in drill down grid")
	public void VerifyDrillDownMenuTraversal() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifyMenuTraversalButton(reportDetails),"menu traversal error");
	}
	@Test(priority=46,description="To Verify Call flow Diagram in drill down Page")
	public void VerifyCallFlowDiagram() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifyCallflowDiagram(reportDetails),"Call flow error");
	} 

	@Test(priority=47,description="To Verify Call flow Grid in drill down Page")
	public void VerifyCallFlowGrid() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifyCallflowGrid(reportDetails),"Call flow grid error");
	}

	@Test(priority=48, description="To verify IVR call Trace report UI data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver, OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifyDatabase(reportDetails.getQuery(), reportDetails),"Main Report Data Mismatch");    
		System.out.println("Main Report Data Match Successfull");
		List<String> UCIDList = new ArrayList<>();
		UCIDList=ivrCallTraceReportPage.getUCID();
//		System.out.println("List of UCID's is"+ UCIDList);
		int k=0;
		for (int i=0;i<UCIDList.size();i++) {
//			System.out.println(UCIDList.size());
			if(k==10) {
				ivrCallTraceReportPage.goToNextPage();
				k=k-10;			
			}
			ivrCallTraceReportPage.clickOnUCIDRowOnMainReport(k);
			Assert.assertTrue(ivrCallTraceReportPage.verifyDatabaseDrillGridOne(reportDetails.getQueryDrillGridOne(), reportDetails, UCIDList.get(i)),"Menu Traversal Grid data mismatch for UCID" + UCIDList.get(i));
			System.out.println("Menu Traversal Data match Successful for UCID" + " : "+ UCIDList.get(i));
		}
	}

	

	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("IVRCallTraceReportTest",method.getName());
		driver.navigate().refresh();
	}
}