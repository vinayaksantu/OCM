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
import com.tetherfi.pages.OCMAgentSummaryReportPage;
import com.tetherfi.pages.OCMFaxReceivedDetailsReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

import com.tetherfi.test.BaseTest;

public class OCMFaxReceivedDetailsReportTest extends BaseTest {
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
	public void ShowOCMFaxReceivedDetailsReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
	}
	@Test(priority=2,description="To verify Show Report in New Tab for Single Date")
	public void ShowOCMFaxReceivedDetailsReportInNewTab() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}
	@Test(priority=3,description="To verify Show Report for Date Range")
	public void ShowOCMFaxReceivedDetailsReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 

	@Test(priority=4,description="To verify Show Report for Date Range in New Tab")
	public void ShowOCMFaxReceivedDetailsReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}
	@Test(priority=5,description="To verify Export Scheduler on OCM Reports Page")
	public void ShowExportSchedulerOCMFaxReceivedDetailsReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}  

	@Test(priority=6,description="To verify Export Report on OCM Reports Page")
	public void ExportOCMFaxReceivedDetailsReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=7,dependsOnMethods ="ExportOCMFaxReceivedDetailsReport",description="To verify view download in Report downloads")
	public void ViewDownloadedOCMFaxReceivedDetailsReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
	} 
	/*
   //non automable
	@Test(priority=8,dependsOnMethods ="ViewDownloadedOCMFaxReceivedDetailsReportInReportsDownloadPage",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOCMFaxReceivedDetailsReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","Fax Received Detail Report"));	
	} */

	@Test(priority=9,description="To verify Export Scheduler on OCM Reports Page for Date Range")
	public void ScheduleOCMFaxReceivedDetailsReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	@Test(priority=10,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportOCMFaxReceivedDetailsReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	} 
	@Test(priority=11,dependsOnMethods ="ExportOCMFaxReceivedDetailsReportDateRange",description="To verify view download in Report downloads")
	public void ViewDownloadedOcmAgentHistoricalReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
	}
	@Test(priority=12,description="To verfiy clear all button")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");

	}
	@Test(priority=13,description="To Verify OCM Window Maximize minimize")
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);             
		Assert.assertTrue(faxReceivedDetailsReportPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMFaxReceivedDetailsReportPage","Maximize");
		Assert.assertTrue(faxReceivedDetailsReportPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMFaxReceivedDetailsReportPage","Minimize");	
	} 
	@Test(priority=14,description="To Verify Dropdown for All the Columns")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}  
	@Test(priority=15,description="To Verify Columns Headers are Enabled")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}  
	@Test(priority=16,description="To Verify Columns Headers are Disabled")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertFalse(faxReceivedDetailsReportPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	} 
	@Test(priority=17,description="To Verify Arrow move for Previous and Next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"PreviousAndNextPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	} 
	@Test(priority=18,description="To Verify Total Number of Items Per Page Details")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"PreviousAndNextPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	} 
	@Test(priority=19,description="To Verify Number of Items Per Page Selection")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"PreviousAndNextPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	} 
	@Test(priority=20,description="To Verify Export Page Button")
	public void ExportPage() throws Exception
	{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyExportToExcel(filePath1));
	}
	
	@Test(priority=21,dependsOnMethods="VerifyExportedPage",description="To Verify Sort By Descending")
	public void SortingByDescending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);        
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		faxReceivedDetailsReportPage.SortByDescending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMFaxReceivedDetailsReport (2).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyexportToExcelSheet(maplist));
	}
	@Test(priority=22,description="To Verify Schedule Report button on Main Page")
	public void SchedulereportinOCMFaxReceivedDetailsReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);       
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		faxReceivedDetailsReportPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(faxReceivedDetailsReportPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
	}
	@Test(priority=23,description="To Verify Export Excel button on Main Page")
	public void ExportToExcelForOCMFaxReceivedDetailsReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		faxReceivedDetailsReportPage.exportToExcel();
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyReportExported(),"export report assertion failed");
	} 
	@Test(priority=24,dependsOnMethods ="ExportToExcelForOCMFaxReceivedDetailsReport",description="To Verify View Download button on Main Page")
	public void ViewDownloadedOCMFaxReceivedDetailsReportInReportsDownloadPageinFaxReceivedDetailsPg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		faxReceivedDetailsReportPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");    
	} 
	
	@Test(priority=25,description="To verify search by feature")
	public void VerifySearchByFeatureForAgentHistoricalReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");     
		Assert.assertTrue(faxReceivedDetailsReportPage.verifySearchByTextbox(reportDetails),"Search report assertion failed");
	} 
	@Test(priority=26,description="To verify search equals")
	public void VerifySearchEqualsToFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(faxReceivedDetailsReportPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}     

	@Test(priority=27,description="To verify search without providing data in searchbox")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		faxReceivedDetailsReportPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(faxReceivedDetailsReportPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}  

	@Test(priority=28,description="To verify search IsNotEquals")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}   

	@Test(priority=29,description="To verify search Contains")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifySearchContains(reportDetails.getSearchStr()));
	}   

	@Test(priority=30,description="To verify search doesnotContains")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}    

	@Test(priority=31,description="To verify search StartsWith")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}
	@Test(priority=32,description="To verify search EndsWith")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=33,description="To verify search Clear Search")
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifySearchClear(reportDetails));    	
	}
	@Test(priority=34,description="To verify Advance Search Equals")
	public void verifyAdvancedSearchEquals() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);        
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyAdvanceSearch(reportDetails));            
	}
	@Test(priority=35,description="To verify Advance Search NotEquals")
	public void verifyAdvancedSearchNotEquals() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);        
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyAdvanceSearchNotEqualsTo(reportDetails.getSearchStr()));  

	}
	@Test(priority=36,description="To verify Advance Search Contains")
	public void verifyAdvancedSearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);        
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyAdvanceSearchContains(reportDetails));            
	}
	@Test(priority=37,description="To verify Advance Search Does Not contains")
	public void verifyAdvancedSearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);        
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyAdvanceSearchDoesNotContains(reportDetails));            
	}
	@Test(priority=38,description="To verify Advance Search StartsWith")
	public void verifyAdvancedSearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);        
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyAdvanceSearchStartsWith(reportDetails));            
	}
	@Test(priority=39,description="To verify Advance Search EndsWith")
	public void verifyAdvancedSearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);        
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyAdvanceSearchEndsWith(reportDetails));            
	}
	@Test(priority=40)
	public void verifyAdvancedSearchANDCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver, OCMFaxReceivedDetailsReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxReceivedDetailsReportPage.advancedSearchANDCriteria(reportDetails));
	}

	@Test(priority=41)
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver, OCMFaxReceivedDetailsReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxReceivedDetailsReportPage.advancedSearchORCriteria(reportDetails));
	}
	@Test(priority=42)
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(ocmReportsPage.ClearAdvFilters(reportDetails));
	}
	@Test(priority=43)
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.groupby());
		screenshot.captureScreen("faxReceivedDetailsReport", "GroupBy");
		Assert.assertTrue(faxReceivedDetailsReportPage.groupby());
		screenshot.captureScreen("faxReceivedDetailsReport", "AlreadyGroupBy");
	}

	@Test(priority=44,description="To Verify Arrow move for Previous and Next page for Drill Down One")
	public void VerifyArrowMoveForPreviousAndNextPageForDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyDrillDownOne(reportDetails),"arrow move for previous and next page assertion failed");
	} 

	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("OCMFaxReceivedDetailsReport",method.getName());
		driver.navigate().refresh();
	}


}
