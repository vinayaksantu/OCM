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
import com.tetherfi.pages.OCMAgentPerformanceReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMAgentPerformanceReportTest extends BaseTest {
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
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayedforSocialMedia(reportDetails),"Show report assertion failed");      
	}    
	@Test(priority=2,description="To verify Show Report in New Tab for Single Date")
	public void ShowReportInNewTab() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayedforSocialMedia(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}  
	@Test(priority=3,description="To verify Export Scheduler on OCM Reports Page")
	public void ScheduleReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	@Test(priority=4,description="To verify Export Report on OCM Reports Page")
	public void ExportReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}
	@Test(priority=5,dependsOnMethods ="ExportReport",description="To verify view download in Report downloads")
	public void ViewDownloadedReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}  

	@Test(priority=6,dependsOnMethods ="ViewDownloadedReportInReportsDownloadPage",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM LINE Overall Statistic"));	
	}

	@Test(priority=7,description="To verify Show Report for Date Range")
	public void ShowReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayedforSocialmedia(reportDetails),"Show report assertion failed");
	}

	@Test(priority=8,description="To verify Show Report for Date Range in New Tab")
	public void ShowReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayedforSocialmedia(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	} 
	@Test(priority=9,description="To verify Export Scheduler on OCM Reports Page for Date Range")
	public void ScheduleReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	@Test(priority=10,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}    
	@Test(priority=11,dependsOnMethods ="ExportReportDateRange",description="To verification of exported excel in Report downloads for Date Range")
	public void ViewDownloadedReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}
	@Test(priority=12,dependsOnMethods ="ViewDownloadedReportInReportsDownloadPageDateRange",description="To verification of exported excel in Report downloads")
	public void VerifydownlodedReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM LINE Overall Statistic"));		
	}
	@Test(priority=13,description="Delete record in Reports Download without Delete reason for date range")
	public void DeleteWithoutDeleteReasonRecordinReportsDownloadforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		Assert.assertTrue(ocmReportsPage.deleteWithoutDeleteReason(reportDetails),"empty delete reason record assertion failed");

	}
	@Test(priority=14,description="Cancel Button in Reports Download Delete Button")
	public void VerifyCancelBtnAtReportsDownloadDeleteBtnForDateRange() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}
	@Test(priority=15,description="Delete Record at Reports download Button")
	public void DeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails),"Delete record assertion failed");

	}
	@Test(priority=16,description="Report page clear All button ")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}
	@Test(priority=17,description="Maximize, minimize")
	public void OCMWindow() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);        
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);                   
		Assert.assertTrue(agentPerformancePage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("RegDegReport","Maximize");
		Assert.assertTrue(agentPerformancePage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("RegDegReport","Minimize");	
	}
	@Test(priority=18,description="Verify dropdown of all the coulnm headers")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}   
	@Test(priority=19,description="Verify column header Enable")
	public void VerifyColumnsHeaderEnable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}  
	@Test(priority=20,description="Verify column header disable")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertFalse(agentPerformancePage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}  
	@Test(priority=21,description="Verify Pagination, Move to previous and next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {  
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}   
	@Test(priority=22,description="Verify Pagination, Move to First and Last Page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	} 
	@Test(priority=23,description="Verify total number of items per page")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}   
	@Test(priority=24,description="Verify number of items selected per page")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}    
	@Test(priority=25,description="To Verify Export Page Button")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifyExportToExcel(filePath1));
	}
	@Test(priority=26,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception
	{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentPerformanceReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=27,description="Scheduled report button in report page")
	public void SchedulereportinAgentPerformanceReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);            
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		agentPerformancePage.navigateToExportSchedulerPage();    
		Assert.assertTrue(agentPerformancePage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
		screenshot.captureScreen("AgentPerformanceReportTest","ExportSchedulerPage");
	}
	@Test(priority=28,description="Export to excel button in Report page")
	public void ExportToExcel() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayedforSocialmedia(reportDetails),"Show report assertion failed");
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		agentPerformancePage.exportToExcel();
		Assert.assertTrue(agentPerformancePage.verifyReportExported(),"export report assertion failed");
	}
	@Test(priority=29,dependsOnMethods ="ExportToExcel")
	public void ViewDownloadedButtonInAgentPerformancePg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		agentPerformancePage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");   
	}      

	@Test(priority=30,description="Search by feature")
	public void VerifySearchByFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayedforSocialmedia(reportDetails),"Show report assertion failed");
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifySearchByTextbox(reportDetails));
	}

	@Test(priority=31,description="Verify the search Is equal to criteria")
	public void VerifySearchFeatureForIsEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayedforSocialmedia(reportDetails),"Show report assertion failed");
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifySearchIsEqualTo(reportDetails.getSearchStr()));				
	}

	@Test(priority=32,description="Verify the search Is not equal to criteria")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}   

	@Test(priority=33,description="Verify the search contains criteria")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifySearchContains(reportDetails.getSearchStr()));
	}   

	@Test(priority=34,description="Verify the Does not contain criteria")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}  

	@Test(priority=35,description="Verify the search starts with criteria")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=36,description="Verify the search Ends with criteria")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}
	@Test(priority=37,description="Search without search text")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		agentPerformancePage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(agentPerformancePage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}
	@Test(priority=38,description="Clear search functionality")
	public void ClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifySearchClear(reportDetails));    	
	} 	

	@Test(priority=39,description="Advance search on reports page for Is equal to Criteria")
	public void verifyAdvancedSearchinreportpage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifyAdvanceSearchIsEqualTo(reportDetails));
	}
	@Test(priority=40,description="Advance search with And Condition")
	public void verifyAdvancedSearchANDCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		//To select Report Channel,name,Type
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(agentPerformancePage.advancedSearchANDCriteria(reportDetails));   	
	}
	@Test(priority=41,description="Advance search with OR Condition")
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		//To select Report Channel,name,Type
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(agentPerformancePage.advancedSearchORCriteria(reportDetails));

	}
	@Test(priority=42,description="Clear filters for Advance search")
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(OCMReportsPage.ClearAdvFilters(reportDetails));
	} 

	@Test(priority=43,description="Group By fuctionality")
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.groupby());
		screenshot.captureScreen("AgentPerformancePage", "GroupBy");
		Assert.assertTrue(agentPerformancePage.groupby());
		screenshot.captureScreen("AgentPerformancePage", "AlreadyGroupBy");
	}

	@Test(priority=44,description="To Verify Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentPerformanceReportPage agentPerformancePage=PageFactory.createPageInstance(driver,OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(agentPerformancePage.verifySorting(),"Sorting assertion failed");
	}	

	@Test(priority=45, description="To verify Campagin Agent performance report UI data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentPerformanceReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentPerformanceReportPage AgentPerformanceReportPage=PageFactory.createPageInstance(driver, OCMAgentPerformanceReportPage.class);
		Assert.assertTrue(AgentPerformanceReportPage.verifyDatabase(reportDetails.getQuery(), reportDetails), "Main report Data Mismatch");
		System.out.println("Main report data match successful");
		List<String> operatorList = new ArrayList<>();
		List<String> DateList = new ArrayList<>();
		operatorList=AgentPerformanceReportPage.getOperators();
		DateList=AgentPerformanceReportPage.getDates();
		System.out.println(operatorList);
		int k=0;
		for (int i=0, q=0;i<operatorList.size();i++) {
			if(k==10) {
				AgentPerformanceReportPage.goToNextPage();
				k=k-10;
			}
			AgentPerformanceReportPage.clickOnOperatorIdRowOnMainReport(k);
			Thread.sleep(3000);
			Assert.assertTrue(AgentPerformanceReportPage.verifyDatabaseDrillGridOne(reportDetails.getQueryDrillGridOne(), reportDetails, operatorList.get(i), DateList.get(q)), "Drill Grid One data mismatch for Agent Id " + operatorList.get(i));
			System.out.println("Drill Grid One data match successfull for Agent Id " + operatorList.get(i));
			k++;
			Thread.sleep(2000);
		}
	}


	@AfterMethod
	public void afterEachMethod(Method method) {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("AgentPerformance", method.getName());
		driver.navigate().refresh();
	}

}
