package com.tetherfi.test.reports;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.OCMUserListingReportPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.OCMRoleAccessReportPage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMUserListingReportTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
	public void NavigateToOcmReportsPage() {
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMIconImg();
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());
	}
	
	@Test(priority=1,description="To verify Show Report")
	public void ShowReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);	
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayedwithoutDateAndTime(reportDetails),"Show report assertion failed");     
	}

	@Test(priority=2,description="To verify Show Report in New Tab for Single Date")
	public void ShowReportInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage1(reportDetails);			
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayedwithoutDateAndTime(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}
	
	@Test(priority=3,enabled=false,description="To verify user listing report UI data against DB")//data issue 
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMUserListingReportPage UserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		Assert.assertTrue(UserListingReportPage.verifyDatabase(reportDetails.getQuery(),reportDetails),"Main report data mismatch");
		System.out.println("Main Report Data Match Successfull");
	}

	@Test(priority=4,description="To Verify OCM Window Maximize minimize")
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);    
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);             
		Assert.assertTrue(OCMUserListingReportPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("UserListingReport","Maximize");
		Assert.assertTrue(OCMUserListingReportPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("UserListingReport","Minimize");	
	} 

	@Test(priority=5,description="To Verify Dropdown for All the Columns")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);	
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);		
		Assert.assertTrue(OCMUserListingReportPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}  

	@Test(priority=6,description="To Verify Columns Headers are Enabled")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);	
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);
		Assert.assertTrue(OCMUserListingReportPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	} 

	@Test(priority=7,description="To Verify Columns Headers are Disabled")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);	
		Assert.assertFalse(OCMUserListingReportPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}
	
	@Test(priority=8,description="Verify Pagination, Move to First and Last Page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		Assert.assertTrue(RoleAccessReportPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}

	@Test(priority=9,description="To Verify Arrow move for Previous and Next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);		
		Assert.assertTrue(OCMUserListingReportPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	} 

	@Test(priority=10,description="To Verify Total Number of Items Per Page Details")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);		
		Assert.assertTrue(OCMUserListingReportPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	} 

	@Test(priority=11,description="To Verify Number of Items Per Page Selection")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);		
		Assert.assertTrue(OCMUserListingReportPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}

	@Test(priority=12,enabled=false,description="To Verify Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);		
		Assert.assertTrue(OCMUserListingReportPage.verifySorting(),"Sorting assertion failed");
	}

	@Test(priority=13,description="Report page clear All button ")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown1(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}
	
	@Test(priority=14,description="To verify search equals")
	public void VerifySearchEqualsToFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);			
		Assert.assertTrue(OCMUserListingReportPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}     

	@Test(priority=15,description="To verify search without providing data in searchbox")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);		
		OCMUserListingReportPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(OCMUserListingReportPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	} 

	@Test(priority=16,description="To verify search IsNotEquals")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);  
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);		
		Assert.assertTrue(OCMUserListingReportPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}   

	@Test(priority=17,description="To verify search Contains")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);  
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);		
		Assert.assertTrue(OCMUserListingReportPage.verifySearchContains(reportDetails.getSearchStr()));
	}   

	@Test(priority=18,description="To verify search doesnotContains")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);  
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);	
		Assert.assertTrue(OCMUserListingReportPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}    

	@Test(priority=19,description="To verify search StartsWith")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);  
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);
		Assert.assertTrue(OCMUserListingReportPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=20,description="To verify search EndsWith")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);  
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);		
		Assert.assertTrue(OCMUserListingReportPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=21,description="To verify search Clear Search")
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);  
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);
		Assert.assertTrue(OCMUserListingReportPage.verifySearchClear(reportDetails));    	
	}

	@Test(priority=22,description="To verify Group by functionality")
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);	
		Assert.assertTrue(OCMUserListingReportPage.groupby());
		screenshot.captureScreen("UserListingReport", "GroupBy");
		Assert.assertTrue(OCMUserListingReportPage.groupby());
		screenshot.captureScreen("UserListingReport", "AlreadyGroupBy");
	}
	
	@Test(priority=23,enabled=false,description="To export the data using Export to excel")//Export PDF No Export to excel 
	public void ExportToExcel() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMUserListingReportPage UserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);
		UserListingReportPage.exportToExcel();
		Assert.assertTrue(UserListingReportPage.verifyReportExportedToExcel(),"export report assertion failed");
	}

	@Test(priority=24,enabled=false,dependsOnMethods ="ExportToExcel",description="To verify view download in Report downloads")
	public void ViewDownloadedReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}

	@Test(priority=25,description="To verify Export Scheduler on OCM Reports Page")
	public void ScheduleReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.scheduleReport1(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	} 
	
	@Test(priority=26,enabled=true,description="Navigates to the Reports Download")
	public void ViewDownloadedInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMUserListingReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.viewDownloadedReportInReportDownloadsPage();	
		OCMUserListingReportPage OCMUserListingReportPage=PageFactory.createPageInstance(driver,OCMUserListingReportPage.class);
		Assert.assertTrue(OCMUserListingReportPage.verifyReportDownloadheader(),"Report Name assertion failed");
	}


	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("OCMUserListingReport_3.4.3.14",method.getName());
		driver.navigate().refresh();
	}

}
