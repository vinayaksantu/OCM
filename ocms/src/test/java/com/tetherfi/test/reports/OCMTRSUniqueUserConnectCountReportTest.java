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
import com.tetherfi.pages.OCMAgentLoginLogoutReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.OCMSystemEventReportPage;
import com.tetherfi.pages.OCMTRSUniqueUserConnectCountReportPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMTRSUniqueUserConnectCountReportTest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
	public void NavigateToOcmReportsPage() {
		HomePage homePage=PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMIconImg();
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());
	}

	@Test(priority=1,description="To verify Show Report for Single Date")
	public void OCMTRSUniqueUserConnectCountReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");      
	}

	@Test(priority=2,description="To verify Show Report in New Tab for Single Date")
	public void ShowOCMTRSUniqueUserConnectCountReportInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}
	//not executed
	/*@Test(priority=3,description="To verify OCM SystemEvent report UI data against DB")
		public void database() throws Exception {
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
			ReportDetails reportDetails= new ReportDetails(map);
			OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
			ocmReportsPage.showReport(reportDetails);
			OCMUniqueUserConnectCountReportPage UniqueUserConnectCountReportPage=PageFactory.createPageInstance(driver,OCMUniqueUserConnectCountReportPage.class);
			Assert.assertTrue(UniqueUserConnectCountReportPage.verifyDatabase(reportDetails.getQuery(),reportDetails),"Main report data mismatch");
			System.out.println("Main Report Data Match Successfull");	    
			List<String> agentList = new ArrayList<>();
			agentList=SystemEventReportPage.getAgents();
			System.out.println(agentList); 
			int k=0;
			for (int i=0;i<agentList.size();i++) {
				if(k==10) {
					SystemEventReportPage.goToNextPage();
					k=k-10;
				}
				SystemEventReportPage.clickOnAgentIdRowOnMainReport(k);
				Assert.assertTrue(SystemEventReportPage.verifyDatabaseDrillGridOne(reportDetails.getQueryDrillGridOne(), reportDetails, agentList.get(i)),"Drill Grid One data mismatch for Agent Id " + agentList.get(i));
				System.out.println("Drill Grid One data match successfull for Agent Id " + agentList.get(i));
				k++;
				Thread.sleep(1000);			
			}
		}*/

	@Test(priority=4,description="To verify Export Report on OCM Reports Page")
	public void ExportOCMTRSUniqueUserConnectCountReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	} 

	@Test(priority=5,description="To verify Export Scheduler on OCM Reports Page")
	public void ScheduleOCMTRSUniqueUserConnectCountReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	@Test(priority=6,dependsOnMethods ="ExportOCMTRSUniqueUserConnectCountReport",description="To verify view download in Report downloads")
	public void ViewDownloadedOCMTRSUniqueUserConnectCountReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	} 

	@Test(priority=7,description="To verify Show Report for Date Range")
	public void ShowOCMTRSUniqueUserConnectCountReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 

	@Test(priority=8,description="To verify Show Report for Date Range in New Tab")
	public void ShowOCMTRSUniqueUserConnectCountReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	} 
	@Test(priority=9,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportOCMTRSUniqueUserConnectCountReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}
	@Test(priority=10,description="To verify Export Scheduler on OCM Reports Page for Date Range")
	public void ScheduleOCMTRSUniqueUserConnectCountReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	@Test(priority=11,dependsOnMethods ="ExportOCMTRSUniqueUserConnectCountReportDateRange",description="To verification of exported excel in Report downloads for Date Range")
	public void ViewDownloadedOCMTRSUniqueUserConnectCountReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	} 
	@Test(priority=12,description="To verfiy clear all button")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}
	@Test(priority=13,description="To Verify OCM Window Maximize minimize")
	public void OCMWindow() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);       
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);               
		Assert.assertTrue(uniqueUserConnectCountPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMTRSUniqueUserConnectCountReport","Maximize");
		Assert.assertTrue(uniqueUserConnectCountPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMTRSUniqueUserConnectCountReport","Minimize");	  
	}
	@Test(priority=15,description="To Verify Arrow move for Previous and Next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception { 
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}
	@Test(priority=16,description="To Verify Arrow move for first and last page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}
	@Test(priority=17,description="To Verify Total Number of Items Per Page Details")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}
	@Test(priority=18,description="To Verify Number of Items Per Page Selection")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}
	@Test(priority=19,description="To Verify Export Page Button")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyExportToExcel(filePath1));
	}
	//not executed
	@Test(priority=26,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMTimeLineReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyexportToExcelSheet(maplist));
	}


	@Test(priority=21,description="To Verify Schedule Report button on Main Page")
	public void SchedulereportinUniqueUserConnectCountPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);            
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		uniqueUserConnectCountPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(uniqueUserConnectCountPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
		screenshot.captureScreen("OCMTRSUniqueUserConnectCountReportTest","ExportSchedulerPage");
	}
	@Test(priority=22,description="To Verify Dropdown for All the Columns")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);	
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	} 
	@Test(priority=23,description="Verify column header Enable")
	public void VerifyColumnsHeaderEnable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}

	@Test(priority=24,description="Verify column header disable")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertFalse(uniqueUserConnectCountPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}
	//not executed
	@Test(priority=25,description="To Verify Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifySorting(),"Sorting assertion failed");
	}

	@Test(priority=27,dependsOnMethods ="ExportOCMTRSUniqueUserConnectCountReport",description="To verify view download in Report downloads")
	public void ViewDownloadedReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}

	@Test(priority=28,description="To verify search by feature")
	public void VerifySearchByFeatureForSystemEventReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(uniqueUserConnectCountPage.verifySearchByTextbox(reportDetails));
	}

	@Test(priority=29,description="To verify search equals")
	public void VerifySearchEqualsFeatureForSystemEventReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(uniqueUserConnectCountPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}

	@Test(priority=30,description="To verify search without providing data in searchbox")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		uniqueUserConnectCountPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(uniqueUserConnectCountPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}

	@Test(priority=31,description="To verify search Is Not Equal")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}

	@Test(priority=32,description="To verify search Contains")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifySearchContains(reportDetails.getSearchStr()));
	} 
	@Test(priority=33,description="To verify search Does Not Contains")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}
	@Test(priority=34,description="To verify search StartsWith")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}
	@Test(priority=35,description="To verify search EndsWith")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(6);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}
	@Test(priority=36,description="To verify search Clear Search")
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		uniqueUserConnectCountPage.verifySearchClear(reportDetails);    	
	}
	@Test(priority=37,description="To verify Advance Search Equals")
	public void verifyAdvancedSearchinreportpage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);        
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyAdvanceSearch(reportDetails));            
	}

	@Test(priority=38,description="To Verify Clear advanced filters")
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(ocmReportsPage.ClearAdvFilters(reportDetails));
	}

	@Test(priority=39,description="To verify Advance Search Contains")
	public void verifyAdvancedSearchinreportpageSearchcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyAdvanceSearchContains(reportDetails)); 
	}
	@Test(priority=40,description="To verify Advance Search DoesNot Contains")
	public void verifyAdvancedSearchinreportpageSearchDoesnotcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyAdvanceSearchDoesNotContains(reportDetails)); 
	}

	@Test(priority=41,description="To verify Advance Search Starts With")
	public void verifyAdvancedSearchinreportpageSearchStartswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyAdvanceSearchStartsWith(reportDetails)); 
	}

	@Test(priority=43,description="To verify Advance Search Ends With")
	public void verifyAdvancedSearchinreportpageSearchEndswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyAdvanceSearchEndsWith(reportDetails)); 
	}
	@Test(priority=44, description="To Verify Adavanced Search AND Criteria")
	public void verifyAdvancedSearchAndCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(uniqueUserConnectCountPage.advancedSearchAndCriteria(reportDetails));   	
	}
	@Test(priority=45, description="To verify Adavanced search OR Criteria")
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(uniqueUserConnectCountPage.advancedSearchORCriteria(reportDetails));   	
	}

	//not executed
	@Test(priority=46,dependsOnMethods ="ViewDownloadedReportInReportsDownloadPage",description="To verification of exported excel in Report downloads")
	public void VerifyDownloadedReportData() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Unique User Connect Count Report-2021-12-02-11-08-36"));	
	}


	@Test(priority=47,description="To Verify Arrow move for Previous and Next page for Drill Down One")
	public void VerifyArrowMoveForPreviousAndNextPageForDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(7);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyArrowMoveForPreviousAndNextPageForDrillDownOne(reportDetails),"arrow move for previous and next page assertion failed");
	}

	@Test(priority=48,description="To Verify Arrow move for first and last page for Drill Down One")
	public void VerifyArrowMoveForFirstAndLastPageFoDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(7);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyArrowMoveForFirstAndLastPageForDrillDownOne(reportDetails),"arrow move for first and last page assertion failed");
	}


	//not executed
	@Test(priority=49,description="To Verify Total Number of Items Per Page Details for Drill Down two")
	public void VerifyTotalNumberOfItemsPerPageDetailsFoDrillDowntwo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyTotalNumberOfItemsPerPageDetailsForDrillDowntwo(),"item per page assertion failed");
	}

	//not executed
	@Test(priority=50,description="To verify DrillOne sorting")
	public void VerifyDrillOneSorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifySorting());
	} 

	//not executed
	@Test(priority=51,description="To verify DrillOne search equals")
	public void VerifyDrillOneSearchEqualsToFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(8);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(uniqueUserConnectCountPage.verifyDrillOneSearchIsEqualTo(reportDetails.getSearchStr1()));
	} 

	//not executed
	@Test(priority=52,description="To verify DrillOne search without providing data in searchbox")
	public void searchwithoutDrillOneSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(9);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		uniqueUserConnectCountPage.searchwithoutDrillOnetextsearch(reportDetails);
		Assert.assertEquals(uniqueUserConnectCountPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	} 

	//not executed
	@Test(priority=53,description="To verify DrillOnesearch IsNotEquals")
	public void VerifyDrillOneSearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(10);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyDrillOneSearchIsNotEqualTo(reportDetails.getSearchStr()));
	} 

	//not executed
	@Test(priority=54,description="To verify DrillOne search Contains")
	public void  VerifyDrillOneSearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(11);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyDrillOneSearchContains(reportDetails.getSearchStr()));
	} 

	//not executed
	@Test(priority=55,description="To verify DrillOne search doesnotContains")
	public void  VerifyDrillOneSearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(12);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyDrillOneSearchDoesNotContains(reportDetails.getSearchStr())); 
	} 

	//not executed
	@Test(priority=56,description="To verify drillOne search StartsWith")
	public void VerifyDrillOneSearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(13);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyDrillOneSearchStartsWith(reportDetails.getSearchStr()));
	}

	//not executed
	@Test(priority=57,description="To verify drillOne search EndsWith")
	public void VerifyDrillOneSearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(14);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyDrillOneSearchEndsWith(reportDetails.getSearchStr()));
	}

	//not executed
	@Test(priority=58,description="To verify drillOne search Clear Search")
	public void SearchDrillOneClearAllBackButton() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUniqueUserConnectCountReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMTRSUniqueUserConnectCountReportPage uniqueUserConnectCountPage=PageFactory.createPageInstance(driver,OCMTRSUniqueUserConnectCountReportPage.class);
		Assert.assertTrue(uniqueUserConnectCountPage.verifyDrillOneSearchClearBackButton(reportDetails));  
	}








	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("OCMTRSUniqueUserConnectCountReport",method.getName());
		driver.navigate().refresh();
	}



}
