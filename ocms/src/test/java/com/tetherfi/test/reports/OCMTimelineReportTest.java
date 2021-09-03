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

	@Test(priority=0,description="To verify Show Report for Single Date")
	public void ShowOCMTimelineReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");      
	}  

	@Test(priority=1,description="To verify Show Report in New Tab for Single Date")
	public void ShowOcmTimelineReportInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}

	@Test(priority=2,description="To verify OCM TimeLine report UI data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimeLineReportPage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimeLineReportPage.verifyDatabase(reportDetails.getQuery(),reportDetails),"Main report data mismatch");
		System.out.println("Main Report Data Match Successfull");	    
		List<String> agentList = new ArrayList<>();
		agentList=TimeLineReportPage.getAgents();
		System.out.println(agentList); 
		int k=0;
		for (int i=0;i<agentList.size();i++) {
			if(k==10) {
				TimeLineReportPage.goToNextPage();
				k=k-10;
			}
			TimeLineReportPage.clickOnAgentIdRowOnMainReport(k);
			Assert.assertTrue(TimeLineReportPage.verifyDatabaseDrillGridOne(reportDetails.getQueryDrillGridOne(), reportDetails, agentList.get(i)),"Drill Grid One data mismatch for Agent Id " + agentList.get(i));
			System.out.println("Drill Grid One data match successfull for Agent Id " + agentList.get(i));
			k++;
			Thread.sleep(1000);			
		}
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
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	} 

	@Test(priority=7,description="To verify Show Report for Date Range")
	public void ShowOcmTimelineReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 

	@Test(priority=8,description="To verify Show Report for Date Range in New Tab")
	public void ShowOcmTimelineReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}  

	@Test(priority=9,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportOcmTimelineReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=10,description="To verify Export Scheduler on OCM Reports Page for Date Range")
	public void ScheduleOcmTimelineReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=11,dependsOnMethods ="ExportOcmTimelineReportDateRange",description="To verification of exported excel in Report downloads for Date Range")
	public void ViewDownloadedOcmTimelineReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	} 

	@Test(priority=13,description="Delete record in Reports Download without Delete reason")
	public void DeleteWithoutDeleteReasonRecordinReportsDownload() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();   	
		ocmReportsPage.deleteWithoutDeleteReason(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
	}

	@Test(priority=14,description="Cancel Button in Reports Download Delete Button")
	public void VerifyCancelBtnAtReportsDownload() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}

	@Test(priority=15,description="Delete Record at Reports download Button")
	public void DeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Report Deleted","Delete record assertion failed");
	}

	@Test(priority=16,description="To verfiy clear all button")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}

	@Test(priority=17,description="To Verify OCM Window Maximize minimize")
	public void OCMWindow() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);       
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);               
		Assert.assertTrue(timelinePage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMTimelineReport","Maximize");
		Assert.assertTrue(timelinePage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMTimelineReport","Minimize");	
	}	

	@Test(priority=18,description="To Verify Dropdown for All the Columns")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);	
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	} 

	@Test(priority=19,description="To Verify Columns Headers are Enabled")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);	
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}  

	@Test(priority=20,description="To Verify Columns Headers are Disabled")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertFalse(timelinePage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	} 

	@Test(priority=21,description="To Verify Arrow move for Previous and Next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception { 
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	} 

	@Test(priority=22,description="To Verify Arrow move for first and last page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}  

	@Test(priority=23,description="To Verify Total Number of Items Per Page Details")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}  

	@Test(priority=24,description="To Verify Number of Items Per Page Selection")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	} 

	@Test(priority=25,description="To Verify Export Page Button")
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

	@Test(priority=26,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
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

	@Test(priority=27,description="To Verify Schedule Report button on Main Page")
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

	@Test(priority=28,description="To Verify Export Excel button on Main Page")
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

	@Test(priority=29,dependsOnMethods ="ExportToExcelForTimelineReport",description="To Verify View Download button on Main Page")
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

	@Test(priority=31,description="To verify search by feature")
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

	@Test(priority=32,description="To verify search equals")
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

	@Test(priority=33,description="To verify search without providing data in searchbox")
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

	@Test(priority=34,description="To verify search Is Not Equal")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}   

	@Test(priority=35,description="To verify search Contains")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifySearchContains(reportDetails.getSearchStr()));
	}  

	@Test(priority=36,description="To verify search Does Not Contains")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}

	@Test(priority=37,description="To verify search StartsWith")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=38,description="To verify search EndsWith")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(timelinePage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=39,description="To verify search Clear Search")
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		timelinePage.verifySearchClear(reportDetails);    	
	}

	@Test(priority=40,description="To verify Advance Search Equals")
	public void verifyAdvancedSearchinreportpage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMTimelineReportPage timelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);        
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(timelinePage.verifyAdvanceSearch(reportDetails));            
	}

	@Test(priority=41,description="To Verify Clear advanced filters")
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(ocmReportsPage.ClearAdvFilters(reportDetails));
	} 

	@Test(priority=42,description="To verify GroupBy functionality")
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

	@Test(priority=43, description="To Verify Adavanced Search AND Criteria")
	public void verifyAdvancedSearchAndCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(TimelinePage.advancedSearchAndCriteria(reportDetails));   	
	}

	@Test(priority=44, description="To verify Adavanced search OR Criteria")
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(TimelinePage.advancedSearchORCriteria(reportDetails));   	
	}

	@Test(priority=45,description="To verify Advance Search Not Equal To")
	public void verifyAdvancedSearchinreportpageSearchNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyAdvanceSearchNotEqualsTo(reportDetails)); 
	}

	@Test(priority=46,description="To verify Advance Search Contains")
	public void verifyAdvancedSearchinreportpageSearchcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyAdvanceSearchContains(reportDetails)); 
	}

	@Test(priority=47,description="To verify Advance Search DoesNot Contains")
	public void verifyAdvancedSearchinreportpageSearchDoesnotcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyAdvanceSearchDoesNotContains(reportDetails)); 
	}

	@Test(priority=48,description="To verify Advance Search Starts With")
	public void verifyAdvancedSearchinreportpageSearchStartswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyAdvanceSearchStartsWith(reportDetails)); 
	}

	@Test(priority=49,description="To verify Advance Search Ends With")
	public void verifyAdvancedSearchinreportpageSearchEndswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyAdvanceSearchEndsWith(reportDetails)); 
	}

	@Test(priority=50,enabled=false,description="To Verify Arrow move for Previous and Next page for Drill Down One")
	public void VerifyArrowMoveForPreviousAndNextPageForDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyArrowMoveForPreviousAndNextPageForDrillDownOne(reportDetails),"arrow move for previous and next page assertion failed");
	} 

	@Test(priority=51,enabled=false,description="To Verify Arrow move for first and last page for Drill Down One")
	public void VerifyArrowMoveForFirstAndLastPageFoDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyArrowMoveForFirstAndLastPageForDrillDownOne(reportDetails),"arrow move for first and last page assertion failed");
	} 

	@Test(priority=52,enabled=false,description="To Verify Total Number of Items Per Page Details for Drill Down One")
	public void VerifyTotalNumberOfItemsPerPageDetailsForDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifyTotalNumberOfItemsPerPageDetailsForDrillDownOne(),"item per page assertion failed");
	} 

	@Test(priority=53,description="To Verify Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMTimelineReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTimelineReportPage TimelinePage=PageFactory.createPageInstance(driver,OCMTimelineReportPage.class);
		Assert.assertTrue(TimelinePage.verifySorting(),"item per page assertion failed");
	}

	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("OCMTimeLineReportV3.3.11.6",method.getName());
		driver.navigate().refresh();
	}

}