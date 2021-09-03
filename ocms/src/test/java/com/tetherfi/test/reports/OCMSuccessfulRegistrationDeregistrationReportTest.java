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
import com.tetherfi.pages.OCMSuccessfulRegistrationDeregistrationReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMSuccessfulRegistrationDeregistrationReportTest extends BaseTest {
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
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayedforSocialMedia(reportDetails),"Show report assertion failed");      
	}    
	
	@Test(priority=2,description="To verify Show Report in New Tab for Single Date")
	public void ShowReportInNewTab() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayedforSocialMedia(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	} 
	
	@Test(priority=3, description="To verify Registration/Deregistration UI data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMSuccessfulRegistrationDeregistrationReportPage SuccessfulRegistrationDeregistrationReportPage=PageFactory.createPageInstance(driver, OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(SuccessfulRegistrationDeregistrationReportPage.verifyDatabase(reportDetails.getQuery(), reportDetails),"Main report data mismatch");
		System.out.println("Registration/Deregistration UI data is matched against DB");
	}

	@Test(priority=4,description="Verify Pagination, Move to previous and next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {  
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}   
	
	@Test(priority=5,description="Verify Pagination, Move to First and Last Page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	} 
	
	@Test(priority=6,description="Verify total number of items per page")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}   
	
	@Test(priority=7,description="Verify number of items selected per page")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}
	
	@Test(priority=8,description="Group By fuctionality")
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.groupby());
		screenshot.captureScreen("RegDegReport", "GroupBy");
		Assert.assertTrue(regDeregPage.groupby());
		screenshot.captureScreen("RegDegReport", "AlreadyGroupBy");
	}
	
	@Test(priority=9,description="Search by feature")
	public void VerifySearchByFeatureForSuccessfulRegistrationDeregistrationReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifySearchByTextbox(reportDetails));
	}
	
	@Test(priority=10,description="Verify the search Is equal to criteria")
	public void VerifySearchFeatureForIsEqualtoReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifySearchEqualTo(reportDetails.getSearchStr()));				
	}
	
	@Test(priority=11,description="Verify the search Is not equal to criteria")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}   
	
	@Test(priority=12,description="Verify the search contains criteria")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifySearchContains(reportDetails.getSearchStr()));
	}   
	
	@Test(priority=13,description="Verify the Does not contain criteria")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}  
	
	@Test(priority=14,description="Verify the search starts with criteria")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}
	
	@Test(priority=15,description="Verify the search Ends with criteria")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}
	
	@Test(priority=16,description="Search without search text")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		regDeregPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(regDeregPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}
	
	@Test(priority=17,description="Clear search functionality")
	public void ClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifySearchClear(reportDetails));    	
	} 	

	@Test(priority=18,description="Advance search on reports page for Is equal to Criteria")
	public void verifyAdvancedSearchinreportpage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifyAdvanceSearch(reportDetails));
	}
	
	@Test(priority=19,description="Advance search with And Condition")
	public void verifyAdvancedSearchANDCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(regDeregPage.advancedSearchANDCriteria(reportDetails));   	
	}
	
	@Test(priority=20,description="Advance search with OR Condition")
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(regDeregPage.advancedSearchORCriteria(reportDetails));
	}
	
	@Test(priority=21,description="Clear filters for Advance search")
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(OCMReportsPage.ClearAdvFilters(reportDetails));
	}
	
	@Test(priority=22,description="To verify Export Scheduler on OCM Reports Page")
	public void ScheduleReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	
	@Test(priority=23,description="To verify Export Report on OCM Reports Page")
	public void ExportReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}
	
	@Test(priority=24,dependsOnMethods ="ExportReport",description="To verify view download in Report downloads")
	public void ViewDownloadedReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
	}  

	@Test(priority=25,dependsOnMethods ="ViewDownloadedReportInReportsDownloadPage",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Successful Registratio"));	
	}

	@Test(priority=26,description="To verify Show Report for Date Range")
	public void ShowReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayedforSocialmedia(reportDetails),"Show report assertion failed");
	}

	@Test(priority=27,description="To verify Show Report for Date Range in New Tab")
	public void ShowReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayedforSocialmedia(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	} 
	
	@Test(priority=28,description="To verify Export Scheduler on OCM Reports Page for Date Range")
	public void ScheduleReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	
	@Test(priority=29,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}    
	
	@Test(priority=30,dependsOnMethods ="ExportReportDateRange",description="To verification of exported excel in Report downloads for Date Range")
	public void ViewDownloadedReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
	}
	
	@Test(priority=31,dependsOnMethods ="ViewDownloadedReportInReportsDownloadPageDateRange",description="To verification of exported excel in Report downloads")
	public void VerifydownlodedReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Successful Registratio"));		
	}
	
	@Test(priority=32,description="Delete record in Reports Download without Delete reason for date range")
	public void DeleteWithoutDeleteReasonRecordinReportsDownloadforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deleteWithoutDeleteReason(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");	
	}
	
	@Test(priority=33,description="Cancel Button in Reports Download Delete Button")
	public void VerifyCancelBtnAtReportsDownloadDeleteBtnForDateRange() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}
	
	@Test(priority=34,description="Delete Record at Reports download Button")
	public void DeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails),"Delete record assertion failed");	
	}
	
	@Test(priority=35,description="To verify clear All button in OCM reports Manager page")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}
	
	@Test(priority=36,description="Maximize, minimize")
	public void OCMWindow() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);        
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);                   
		Assert.assertTrue(regDeregPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("RegDegReport","Maximize");
		Assert.assertTrue(regDeregPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("RegDegReport","Minimize");	
	}
	
	@Test(priority=37,description="Verify dropdown of all the coulnm headers")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}   
	
	@Test(priority=38,description="Verify column header Enable")
	public void VerifyColumnsHeaderEnable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}  
	
	@Test(priority=39,description="Verify column header disable")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertFalse(regDeregPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}  
	
	@Test(priority=40,description="To Verify Export Page Button")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifyExportToExcel(filePath1));
	}
	
	@Test(priority=41,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=42,description="Scheduled report button in SuccessfulRegistrationDeregistration report page")
	public void SchedulereportinSuccessfulRegistrationDeregistrationReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);            
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		regDeregPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(regDeregPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
	}
	
	@Test(priority=43,description="Export to excel button in SuccessfulRegistrationDeregistration Report page")
	public void ExportToExcel() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		regDeregPage.exportToExcel();
		Assert.assertTrue(regDeregPage.verifyReportExported(),"export report assertion failed");
	}
	
	@Test(priority=44,dependsOnMethods ="ExportToExcel")
	public void ViewDownloadedReportInReportsDownloadPageinSuccessfulRegistrationDeregistrationPg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		regDeregPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");   
	}       
	
	//known issue in sorting alphanumeric Values
	@Test(priority=45,enabled=false,description="To Verify Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSuccessfulRegistrationDeregistrationReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMSuccessfulRegistrationDeregistrationReportPage regDeregPage=PageFactory.createPageInstance(driver,OCMSuccessfulRegistrationDeregistrationReportPage.class);
		Assert.assertTrue(regDeregPage.verifySorting(),"Sorting assertion failed");
	}
	
	@AfterMethod
	public void afterEachMethod(Method method) {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("RegDegReport1", method.getName());
		driver.navigate().refresh();
	}
}
