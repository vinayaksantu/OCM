
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
import com.tetherfi.pages.OCMFrequentlyHitThresholdReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMFrequentlyHitThresholdReport extends BaseTest {
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
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");        
	}

	@Test(priority=2,description="To verify Show Report in New Tab for Single Date")
	public void ShowReportInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}

	@Test(priority=3, description="To verify OCM Frequently Hit Threshold report UI data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFrequentlyHitThresholdReportPage FrequentlyHitThresholdReportPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(FrequentlyHitThresholdReportPage.verifyDatabase(reportDetails.getQuery(),reportDetails),"Main report data mismatch");
		System.out.println("Main Report Data Match Successfull"); 
		List<String> AgentList = new ArrayList<>();
		AgentList=FrequentlyHitThresholdReportPage.getagentList();
		int k=0;
		for (int i=0;i<AgentList.size();i++) {
			if(k==10) {
				FrequentlyHitThresholdReportPage.goToNextPage();
				k=k-10;
			}	
			FrequentlyHitThresholdReportPage.clickOnAgentIdRowOnMainReport(k);
			Thread.sleep(2000);
			Assert.assertTrue(FrequentlyHitThresholdReportPage.verifyDatabaseDrillGridOne(reportDetails.getQueryDrillGridOne(), reportDetails, AgentList.get(i)),"Drill Grid One data mismatch for Agent" +" " + AgentList.get(i));
			System.out.println("Drill Grid One data match successfull for Agent ID" + AgentList.get(i));
			k++;
			Thread.sleep(1000);	
		}    		
	}

	@Test(priority=4,description="To Verify Total Number of Items Per Page Details for Drill Down One")
	public void VerifyTotalNumberOfItemsPerPageDetailsFoDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(freqhitPage.verifyTotalNumberOfItemsPerPageDetailsForDrillDownOne(),"item per page assertion failed");
	}

	@Test(priority=5,description="To verify Export Scheduler on OCM Reports Page")
	public void ScheduleOCMFrequentlyHitThresholdReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	} 

	@Test(priority=6,description="To verify Export Report on OCM Reports Page")
	public void ExportOcmFrequentlyHitThresholdReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=7,dependsOnMethods ="ExportOcmFrequentlyHitThresholdReport",description="To verify view download in Report downloads")
	public void ViewDownloadedOcmFrequentlyHitThresholdReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}  

	@Test(priority=8,description="To verify Show Report for Date Range")
	public void ShowReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	}

	@Test(priority=9,description="To verify Show Report for Date Range in New Page")
	public void ShowReportInNewPageForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	} 

	@Test(priority=10,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportOcmFrequentlyHitThresholdReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	} 

	@Test(priority=11,dependsOnMethods ="ExportOcmFrequentlyHitThresholdReportDateRange",description="To verify the view download reports for date range ")
	public void ViewDownloadedOcmFrequentlyHitThresholdReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}

	@Test(priority=12,description="To verify Export Scheduler on OCM Reports Page for Date Range")
	public void ScheduleOcmFrequentlyHitThresholdReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=13,description="To verify clear All functionality in OCM reports Manager page")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}

	@Test(priority=14,description="Maximize, minimize window")
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);        
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);             
		Assert.assertTrue(freqhitPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMFrequentlyHitThresholdReportReport","Maximize");
		Assert.assertTrue(freqhitPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMFrequentlyHitThresholdReportReport","Minimize");	
	}

	@Test(priority=15,description="Verify dropdown of all the coulnm headers")
	public void VerifyDropdownForAllTheColumns() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);	
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(freqhitPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}   

	@Test(priority=16,description="Verify column header Enable")
	public void VerifyColumnsHeaderEnable() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);	
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(freqhitPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}   

	@Test(priority=17,description="Verify column header disable")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertFalse(freqhitPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	} 

	@Test(priority=18,description="Verify Pagination, Move to previous and next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(freqhitPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}    

	@Test(priority=19,description="Verify Pagination, Move to First and Last Page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(freqhitPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}  

	@Test(priority=20,description="Verify total number of items per page")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(freqhitPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}

	@Test(priority=21,description="Verify number of items selected per page")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(freqhitPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	} 

	@Test(priority=22,description="To Verify Export Page Button")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(freqhitPage.verifyExportToExcel(filePath1));
	}  

	@Test(priority=23,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMFrequentlyHitThresholdReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(freqhitPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=24,description="To verify sort by Asending order")
	public void SortingByAscending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		freqhitPage.SortByAscending();
		String filePath1= System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMFrequentlyHitThresholdReport (1).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(freqhitPage.verifyexportToExcelSheet(maplist));
	}  

	@Test(priority=25,description="To verify sort by descending order")
	public void SortingByDescending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);       
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		freqhitPage.SortByDescending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMFrequentlyHitThresholdReport (2).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(freqhitPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=26,description="Scheduled report button in Frequently hit threshold report page")
	public void SchedulereportinFrequentlyhitReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);           
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		freqhitPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(freqhitPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
	} 

	@Test(priority=27,description="Export to excel button in Frequently hit threshold Report page")
	public void ExportToExcelForfrequentlyHitThresholdReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		freqhitPage.exportToExcel();
		Assert.assertTrue(freqhitPage.verifyReportExported(),"export report assertion failed");
	}   

	@Test(priority=28,dependsOnMethods ="ExportToExcelForfrequentlyHitThresholdReport",description="Verify view downloaded report in FrequentlyHitThresholdReportPg")
	public void ViewDownloadedReportInFrequentlyHitThresholdReportPg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		freqhitPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");  
	}
	
	@Test(priority=29,description="Delete record in Reports Download without Delete reason for date range")
	public void DeleteWithoutDeleteReasonRecordinReportsDownloadforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		Assert.assertTrue(ocmReportsPage.deleteWithoutDeleteReason(reportDetails),"empty delete reason record assertion failed");
	}

	@Test(priority=30,description="Cancel Button in Reports Download Delete Button")
	public void VerifyCancelBtnAtReportsDownloadDeleteBtnForDateRange() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}

	@Test(priority=31,description="Delete Record at Reports download Button")
	public void DeleteRecordAtReportsDownload() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails),"Delete record assertion failed");
	}

	@Test(priority=32,description="Search by feature")
	public void VerifySearchByFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(freqhitPage.verifySearchByTextbox(reportDetails));
	}

	@Test(priority=33,description="To verify the search Is equal to criteria")
	public void VerifySearchIsEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(freqhitPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}

	@Test(priority=34,description="To verify search without search text")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		freqhitPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(freqhitPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}       

	@Test(priority=35,description="To verify Search Is not equal to criteria")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(freqhitPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}   

	@Test(priority=36,description="To verify Search contains criteria")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(freqhitPage.verifySearchContains(reportDetails.getSearchStr()));
	} 

	@Test(priority=37,description="To verify Search  Does not contain criteria")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(freqhitPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}   

	@Test(priority=38,description="To verify Search Starts with Criteria")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(freqhitPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=39,description="To verify Search Ends with Criteria")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(freqhitPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=40,description="To verify Clear search Button")
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(freqhitPage.verifySearchClear(reportDetails));    	
	}

	@Test(priority=41,description="To verify Advance search with Is equal to criteria on reports page")
	public void verifyAdvancedSearchInReportManagerPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);        
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(freqhitPage.verifyAdvanceSearch(reportDetails));            
	}

	@Test(priority=42,description="To verify Clear filters for Advance search")
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(ocmReportsPage.ClearAdvFilters(reportDetails));
	}

	@Test(priority=43,description="Advance search with And Condition")
	public void verifyAdvancedSearchANDCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(freqhitPage.advancedSearchANDCriteria(reportDetails));   	
	}

	@Test(priority=44,description="Advance search with OR Condition")
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(freqhitPage.advancedSearchORCriteria(reportDetails));
	}

	@Test(priority=45,description="To verify the group by functionality")
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequentlyHitThresholdReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);   	
		ocmReportsPage.showReport(reportDetails);
		OCMFrequentlyHitThresholdReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequentlyHitThresholdReportPage.class);
		Assert.assertTrue(freqhitPage.groupby());
		screenshot.captureScreen("OCMFrequentlyHitReport", "GroupBy");
		Assert.assertTrue(freqhitPage.groupby());
		screenshot.captureScreen("OCMFrequentlyHitReport", "AlreadyGroupBy");
	}

	@AfterMethod
	public void afterEachMethod(Method method) {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("OCMFrequentlyHitReport", method.getName());
		driver.navigate().refresh();
	}
}
