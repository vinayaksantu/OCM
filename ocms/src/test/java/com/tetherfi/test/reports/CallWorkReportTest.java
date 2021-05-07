
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
import com.tetherfi.pages.CallWorkReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class CallWorkReportTest extends BaseTest {
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
	public void ShowOCMCallWorkReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
	}

	/*@Test(priority=2,description="To verify Show Report in New Tab for Single Date")
	public void ShowOCMCallWorkReportInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}

	@Test(priority=3, description="To verify Callwork report UI data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		CallWorkReportPage callWorkReportPage=PageFactory.createPageInstance(driver, CallWorkReportPage.class);
		callWorkReportPage.closeGroupBy();
		Assert.assertTrue(callWorkReportPage.verifyDatabase(reportDetails.getQuery(),reportDetails),"UI and Database data mismatch");
		System.out.println("Main report data match successful");
		List<String> workCodeList = new ArrayList<>();
		List<String> agentList=new ArrayList<>();
		workCodeList=callWorkReportPage.getWorkCodes();
		agentList=callWorkReportPage.getAgents();
		System.out.println(workCodeList);
		System.out.println(agentList);
		int k=0;
		for (int i=0, q=0;i<workCodeList.size() && q<agentList.size();i++,q++) {
			if(k==10) {
				callWorkReportPage.goToNextPage();
				k=k-10;
			}
			callWorkReportPage.clickOnWorkCodeRowOnMainReport(k);
			Thread.sleep(3000);
			callWorkReportPage.refreshDrillDownData();
			Assert.assertTrue(callWorkReportPage.verifyDatabaseDrillGridOne(reportDetails.getQueryDrillGridOne(), reportDetails, workCodeList.get(i), agentList.get(q)),"Drill Grid One data mismatch for work code" + workCodeList.get(i));
			System.out.println("Drill Grid One data match successfull for Agent Id " + workCodeList.get(i));
			k++;
			Thread.sleep(1000);
		}
	}

	@Test(priority=4,description="To Verify Arrow move for Previous and Next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(CallWorkReportPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	} 

	@Test(priority=5,description="To Verify Arrow move for Previous and Next page")
	public void verifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(CallWorkReportPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for previous and next page assertion failed");
	}

	@Test(priority=6,description="To Verify Total Number of Items Per Page Details")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(CallWorkReportPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	} 

	@Test(priority=7,enabled=false,description="To Verify Number of Items Per Page Selection")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(CallWorkReportPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}
	
	@Test(priority=8,description="To validate search by feature using WorkCode Desc")
	public void VerifySearchByFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(CallWorkReportPage.verifySearchByTextbox(reportDetails),"Search report assertion failed");
	} 
	
	@Test(priority=9,description="To verify search equals")
	public void VerifySearchEqualsToFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(CallWorkReportPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}     

	@Test(priority=10,description="To verify search without providing data in searchbox")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		CallWorkReportPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(CallWorkReportPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}  

	@Test(priority=11,description="To verify search IsNotEquals")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(CallWorkReportPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}   

	@Test(priority=12,description="To verify search Contains")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(CallWorkReportPage.verifySearchContains(reportDetails.getSearchStr()));
	}   

	@Test(priority=13,description="To verify search doesnotContains")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(CallWorkReportPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}    

	@Test(priority=14,description="To verify search StartsWith")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(CallWorkReportPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=15,description="To verify search EndsWith")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(CallWorkReportPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=16,description="To verify search Clear Search")
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(CallWorkReportPage.verifySearchClear(reportDetails));    	
	}

	@Test(priority=17,description="To verify Advance Search using IS Equal To search criteria")
	public void verifyAdvancedSearchEquals() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);        
		ocmReportsPage.showReport(reportDetails);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(CallWorkReportPage.verifyAdvanceSearchIsEqualTo(reportDetails));            
	}	

	@Test(priority=18,description="To verify the Boolean AND condition for Advance search ")
	public void verifyAdvancedSearchANDCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver, CallWorkReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(CallWorkReportPage.advancedSearchANDCriteria(reportDetails));
	}

	@Test(priority=19,description="To verify the Boolean OR condition for Advance search")
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver, CallWorkReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(CallWorkReportPage.advancedSearchORCriteria(reportDetails));
	}

	@Test(priority=20,description="To validate the Clear filters for Advance search")
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(ocmReportsPage.ClearAdvFilters(reportDetails));
	}

	@Test(priority=21,description="To verify the group by functionality")
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(CallWorkReportPage.groupby());
		screenshot.captureScreen("CallWorkReport", "GroupBy");
		Assert.assertTrue(CallWorkReportPage.groupby());
		screenshot.captureScreen("CallWorkReport", "AlreadyGroupBy");
	}
	
	@Test(priority=22,description="To verify Show Report for Date Range")
	public void ShowOCMCallWorkReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 

	@Test(priority=23,description="To verify Show Report for Date Range in New Tab")
	public void ShowOCMCallWorkReportInNewPageForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}

	@Test(priority=24,description="To verify Export Scheduler on OCM Reports Page")
	public void SchedulerOCMCallWorkReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	} 	
	
	@Test(priority=25,description="To verify Export Report on OCM Reports Page for single date")
	public void ExportOCMCallWorkReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}

//	PRDOCM-58588
	@Test(priority=26,dependsOnMethods ="ExportOCMCallWorkReport",description="To verify view download in Report downloads")
	public void ViewDownloadedOCMCallWorkReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
	} 

	@Test(priority=27,description="To verify Export Scheduler on OCM Reports Page for Date Range")
	public void ScheduleOCMCallWorkReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=28,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportOCMCallWorkReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	} 

//	PRDOCM-58588
	@Test(priority=29,dependsOnMethods ="ExportOCMCallWorkReportForDateRange",description="To verify view download in Report downloads")
	public void ViewDownloadedReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
	}

	@Test(priority=30,description="To verify clear all button")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}

	@Test(priority=31,description="To Verify OCM Window Maximize minimize")
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);             
		Assert.assertTrue(CallWorkReportPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("CallWorkReportPage","Maximize");
		Assert.assertTrue(CallWorkReportPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("CallWorkReportPage","Minimize");	
	} 

	@Test(priority=32,description="To Verify Dropdown for All the Columns")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(CallWorkReportPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}  

	@Test(priority=33,description="To Verify Columns Headers are Enabled")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(CallWorkReportPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}  

	@Test(priority=34,description="To Verify Columns Headers are Disabled")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertFalse(CallWorkReportPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	} 

	@Test(priority=35,description="To Verify Export Page Button")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		Assert.assertTrue(CallWorkReportPage.verifyExportToExcel(filePath1));
	}

	@Test(priority=36,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Workcode Report.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();	
		Assert.assertTrue(CallWorkReportPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=37,dependsOnMethods="VerifyExportedPage",description="To Verify Sort By Ascending")
	public void SortByAscending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		CallWorkReportPage.SortByAscending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Workcode Report (1).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(CallWorkReportPage.verifyexportToExcelSheet(maplist));
	}   

	@Test(priority=38,dependsOnMethods="VerifyExportedPage",description="To Verify Sort By Descending")
	public void SortByDescending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);        
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		CallWorkReportPage.SortByDescending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Workcode Report (2).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(CallWorkReportPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=39,description="To Verify Schedule Report button on Main Page")
	public void SchedulereportinCallWorkReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);       
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		CallWorkReportPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(CallWorkReportPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
	}

	@Test(priority=40,description="To Verify Export Excel button on Call work Page")
	public void ExportToExcelForOCMCallWorkReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.exportToExcel();
		Assert.assertTrue(CallWorkReportPage.verifyReportExported(),"export report assertion failed");
	} 

	@Test(priority=41,dependsOnMethods ="ExportToExcelForOCMCallWorkReport",description="To Verify View Download button on Main Page")
	public void ViewDownloadedOCMCallWorkReportInReportsDownloadPageinCallWorkPg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		CallWorkReportPage CallWorkReportPage=PageFactory.createPageInstance(driver,CallWorkReportPage.class);
		CallWorkReportPage.closeGroupBy();
		CallWorkReportPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");    
	} 

	@Test(priority=42,description="Delete record in Reports Download without Delete reason for date range")
	public void DeleteWithoutDeleteReasonRecordinReportsDownloadforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		Assert.assertTrue(ocmReportsPage.deleteWithoutDeleteReason(reportDetails),"empty delete reason record assertion failed");	
	}

	@Test(priority=43,description="Cancel Button in Reports Download Delete Button")
	public void VerifyCancelBtnAtReportsDownloadDeleteBtnForDateRange() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}

	@Test(priority=44,description="Delete Record at Reports download Button")
	public void DeleteRecordAtReportsDownload() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CallWorkReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails),"Delete record assertion failed");
	}*/

	@AfterMethod
	public void afterEachMethod(Method method) {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("CallworkReport_3.4.3.14",method.getName());
		driver.navigate().refresh();
	}

}
