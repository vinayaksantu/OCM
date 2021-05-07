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
import com.tetherfi.pages.OCMFaxSentDetailsReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMFaxSentDetailsReportTest extends BaseTest {
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
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
	}
	
	@Test(priority=2,description="To verify Show Report in New Page for Single Date")
	public void ShowReportInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}
	
	@Test(priority=3, description="To verify fax sent details report UI data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifyDatabase(reportDetails.getQuery(),reportDetails,reportDetails.getOrgUnitID()),"Main report data mismatch");	
		System.out.println("Main Report Data Match Successfull");
		List<String> senderNumberList = new ArrayList<>();
		senderNumberList=faxSentDetailsReportPage.getsenderNumberList();
		System.out.println(senderNumberList);
		int k=0;
		for (int i=0;i<senderNumberList.size();i++) {
			if(k==10) {
				faxSentDetailsReportPage.goToNextPage();
				k=k-10;
			}
			faxSentDetailsReportPage.clickOnFaxLineRowOnMainReport(k);
			Assert.assertTrue(faxSentDetailsReportPage.verifyDatabaseDrillGridOne(reportDetails.getQueryDrillGridOne(),reportDetails, reportDetails.getOrgUnitID(), senderNumberList.get(i)),"Drill Grid One data mismatch for Sender Number" + senderNumberList.get(i));
			System.out.println("Drill Grid One data match successfull for Sender Number" + senderNumberList.get(i));
			k++;
			Thread.sleep(1000);	
		}
	}
	
	@Test(priority=4,description="To verify search by feature")
	public void VerifySearchByFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");     
		Assert.assertTrue(faxSentDetailsReportPage.verifySearchByTextbox(reportDetails),"Search report assertion failed");
	} 
	
	@Test(priority=5,description="To verify search functionality using IsEqualTo")
	public void VerifySearchEqualsToFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(faxSentDetailsReportPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}     

	@Test(priority=6,description="To verify search without providing data in searchbox")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		faxSentDetailsReportPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(faxSentDetailsReportPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}  

	@Test(priority=7,description="To verify search functionality using Is Not Equal To")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}   

	@Test(priority=8,description="To verify search functionality using Contains")
	public void VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifySearchContains(reportDetails.getSearchStr()));
	}   

	@Test(priority=9,description="To verify search functionality using Does not contain")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}   

	@Test(priority=10,description="To verify search functionality using StartsWith")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}
	
	@Test(priority=11,description="To verify search functionality using EndsWith")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=12,description="To verify search Clear Search")
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifySearchClear(reportDetails));    	
	}
	
	@Test(priority=13,description="To verify Advanced Search Equals")
	public void verifyAdvancedSearchEquals() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);        
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxSentDetailsReportPage.verifyAdvanceSearch(reportDetails));            
	}
	
	@Test(priority=14,description="To verify advanced search AND criteria")
	public void verifyAdvancedSearchANDCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver, OCMFaxSentDetailsReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxSentDetailsReportPage.advancedSearchANDCriteria(reportDetails));
	}

	@Test(priority=15, description="To verify advanced search OR criteria")
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver, OCMFaxSentDetailsReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxSentDetailsReportPage.advancedSearchORCriteria(reportDetails));
	}
	
	@Test(priority=16,description="To clear advanced search filters" )
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(ocmReportsPage.ClearAdvFilters(reportDetails));
	}

	@Test(priority=17,description="To verfiy clear all button")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}
	
	@Test(priority=18,description="To Verify OCM Window Maximize minimize")
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);             
		Assert.assertTrue(faxSentDetailsReportPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMFaxSentDetailsReportPage","Maximize");
		Assert.assertTrue(faxSentDetailsReportPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMFaxSentDetailsReportPage","Minimize");	
	} 
	
	@Test(priority=19,description="To Verify Dropdown for All the Columns")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}  
	
	@Test(priority=20,description="To Verify Columns Headers are Enabled")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}  
	
	@Test(priority=21,description="To Verify Columns Headers are Disabled")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertFalse(faxSentDetailsReportPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	} 
	
	@Test(priority=22,description="To Verify Arrow move for Previous and Next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	} 
	
	@Test(priority=23,description="Verify Pagination, Move to First and Last Page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}
	
	@Test(priority=24,description="To Verify Total Number of Items Per Page Details")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	} 
	
	@Test(priority=25,description="To Verify Number of Items Per Page Selection")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}
	
	@Test(priority=26,enabled=false,description="To Verify Arrow move for Previous and Next page for Drill Down One")
	public void VerifyArrowMoveForPreviousAndNextPageForDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifyArrowMoveForPreviousAndNextPageForDrillDownOne(reportDetails),"arrow move for previous and next page assertion failed");
	} 

	@Test(priority=27,enabled=false,description="To Verify Arrow move for first and last page for Drill Down One")
	public void VerifyArrowMoveForFirstAndLastPageForDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifyArrowMoveForFirstAndLastPageForDrillDownOne(reportDetails),"arrow move for first and last page assertion failed");
	} 
	
	@Test(priority=28,enabled=false,description="To Verify Total Number of Items Per Page Details for Drill Down One")
	public void VerifyTotalNumberOfItemsPerPageDetailsForDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifyTotalNumberOfItemsPerPageDetailsForDrillDownOne(),"item per page assertion failed");
	}

	@Test(priority=29,description="To verify Show Report for Date Range")
	public void ShowReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 

	@Test(priority=30,description="To verify Show Report for Date Range in New Page")
	public void ShowReportInNewPageForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}
	
	@Test(priority=31,description="To verify Export Scheduler on OCM Reports Page")
	public void ShowExportSchedulerOCMFaxSentDetailsReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=32,description="To verify Export Report on OCM Reports Page")
	public void ExportReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=33,dependsOnMethods ="ExportReport",description="To verify view download in Report downloads")
	public void ViewDownloadedReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
	} 

//	Non automatable
	@Test(priority=34,enabled=false,dependsOnMethods ="ViewDownloadedOCMFaxSentDetailsReportInReportsDownloadPage",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOCMFaxSentDetailsReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","Fax Sent Detail Report"));	
	} 

	@Test(priority=35,description="To verify Export Scheduler on OCM Reports Page for Date Range")
	public void ScheduleOCMFaxSentDetailsReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	
	@Test(priority=36,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	} 
	
	@Test(priority=37,dependsOnMethods ="ExportReportForDateRange",description="To verify view download in Report downloads")
	public void ViewDownloadedReportInReportsDownloadPageForDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
	} 
	
	@Test(priority=38,description="To Verify Export Page Button")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifyExportToExcel(filePath1));
	}
	
	@Test(priority=39,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMFaxSentDetailsReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifyexportToExcelSheet(maplist));
	}
	
	@Test(priority=40,dependsOnMethods="VerifyExportedPage",description="To Verify Sort By Ascending")
	public void SortingByAscending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		faxSentDetailsReportPage.SortByAscending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMFaxSentDetailsReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(faxSentDetailsReportPage.verifyexportToExcelSheet(maplist));
	}   
	
	@Test(priority=41,dependsOnMethods="VerifyExportedPage",description="To Verify Sort By Descending")
	public void SortingByDescending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);        
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		faxSentDetailsReportPage.SortByDescending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMFaxSentDetailsReport (1).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(faxSentDetailsReportPage.verifyexportToExcelSheet(maplist));
	}
	
	@Test(priority=42,description="To Verify Schedule Report button on fax sent details Page")
	public void SchedulereportinOCMFaxSentDetailsReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);       
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		faxSentDetailsReportPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(faxSentDetailsReportPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
	}
	
	@Test(priority=43,description="To Verify Export Excel button on fax sent details Page")
	public void ExportToExcel() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		faxSentDetailsReportPage.exportToExcel();
		Assert.assertTrue(faxSentDetailsReportPage.verifyReportExported(),"export report assertion failed");
	} 
	
	@Test(priority=44,dependsOnMethods ="ExportToExcel",description="To Verify View Download button on Main Page")
	public void ViewDownloadedOCMFaxSentDetailsReportInReportsDownloadPageinFaxSentDetailsPg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		faxSentDetailsReportPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");    
	} 
			
	@Test(priority=45,description="Delete record in Reports Download without Delete reason for date range")
	public void DeleteWithoutDeleteReasonRecordinReportsDownloadforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		Assert.assertTrue(ocmReportsPage.deleteWithoutDeleteReason(reportDetails),"empty delete reason record assertion failed");			
	}

	@Test(priority=46,description="Cancel Button in Reports Download Delete Button")
	public void VerifyCancelBtnAtReportsDownloadDeleteBtnForDateRange() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}
	
	@Test(priority=47,description="Delete Record at Reports download Button")
	public void DeleteRecordAtReportsDownload() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails),"Delete record assertion failed");
	}
	
	@Test(priority=48,description="To Verify Preview in drill down report")
	public void VerifyPreviewButton() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.verifyPreviewButton(reportDetails),"Preview Original assertion failed");
	}

	@Test(priority=49)
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSentDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxSentDetailsReportPage faxSentDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxSentDetailsReportPage.class);
		Assert.assertTrue(faxSentDetailsReportPage.groupby());
		screenshot.captureScreen("faxSentDetailsReport", "GroupBy");
		Assert.assertTrue(faxSentDetailsReportPage.groupby());
		screenshot.captureScreen("faxSentDetailsReport", "AlreadyGroupBy");
	}

	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("OCMFaxSentDetailsReport",method.getName());
		driver.navigate().refresh();
	}


}


