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
import com.tetherfi.pages.OCMInteractionActionReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMInteractionActionsReportTest extends BaseTest {
	
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
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
	} 

	@Test(priority=2,description="To verify Show Report in New page for Single Date")
	public void ShowReportInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		OCMReportsPage.switchBackToParentWindow();
	} 
	
//	 #PRDOCM-53186- To track data mismatch issue
	//	@Test(priority=46,retryAnalyzer=com.tetherfi.utility.RetryAnalyzer.class,description="To verify Interaction Actions Report report UI data against DB")
	@Test(priority=3, description="To verify Interaction Actions Report report UI data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMInteractionActionReportPage InteractionActionsReportPage=PageFactory.createPageInstance(driver, OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionsReportPage.verifyDatabase(reportDetails.getQuery(),reportDetails),"Main report data mismatch");
		System.out.println("Main Report Data Match Successfull");	
	}
	
	@Test(priority=4,description="Verify dropdown of all the coulmn headers")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);		
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionReportPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}

	@Test(priority=5,description="Verify column header Enable")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);	
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionReportPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}

	@Test(priority=6,description="Verify column header disable")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertFalse(InteractionActionReportPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}

	@Test(priority=7,description="Verify Pagination, Move to previous and next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionReportPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	} 

	@Test(priority=8,description="Verify Pagination, Move to First and Last Page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionReportPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}

	@Test(priority=9,description="Verify total number of items per page")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionReportPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}

	@Test(priority=10,description="Verify number of items selected per page")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionReportPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}
	
	@Test(priority=11,description="Search by feature")
	public void VerifySearchByFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);		     
		Assert.assertTrue(InteractionActionReportPage.verifySearchByTextbox(reportDetails));
	}
	
	@Test(priority=12,description="To Verify the search functionality using Is equal to criteria")
	public void verifySearchIsEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionReportPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}
	
	@Test(priority=13,description="Verify the search Is not equal to criteria")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMInteractionActionReportPage InteractionActionsReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionsReportPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}

	@Test(priority=14,description="Verify the search contains criteria")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionReportPage.verifySearchContains(reportDetails.getSearchStr()));
	}

	@Test(priority=15,description="Verify the Does not contain criteria")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionReportPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}

	@Test(priority=16,description="Verify the search starts with criteria")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionReportPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=17,description="Verify the search Ends with criteria")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionReportPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=18,description="Search without search text")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		InteractionActionReportPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(InteractionActionReportPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}

	@Test(priority=19,description="Clear search functionality")
	public void SearchClear() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionReportPage.verifySearchClear(reportDetails));    	
	}

	@Test(priority=20,description="Advance search on reports page for Is equal to Criteria")
	public void verifyAdvancedSearchinreportpageSearchEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(InteractionActionReportPage.verifyAdvanceSearchIsEqualTo(reportDetails));
	}
	
	@Test(priority=21,description="Advance search with OR Condition")
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);									  
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(InteractionActionReportPage.advancedSearchORCriteria(reportDetails));
	}
	
	@Test(priority=22,description="Clear filters for Advance search")
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(ocmReportsPage.ClearAdvFilters(reportDetails));
	}

	@Test(priority=23,description="To verify Export Scheduler on OCM Reports Page")
	public void ExportSchedulerinReportPageforSingleDate() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	} 

	@Test(priority=24,description="To verify Export Report on OCM Reports Page")
	public void ExportReportforSingleDate() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=25,dependsOnMethods ="ExportReportforSingleDate")
	public void ViewDownloadedReportforSingleDate() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}  

	@Test(priority=26,dependsOnMethods ="ViewDownloadedReportforSingleDate",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOcmInteractionActionReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Interaction Actions Re"));	
	}
	
	@Test(priority=27,description="To verify Show Report for Date Range")
	public void ShowOCMInteractionActionReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 

	@Test(priority=28,dependsOnMethods ="ShowOCMInteractionActionReportForDateRange",description="To verify Show Report for Date Range in New Tab")
	public void ShowOCMInteractionActionReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}

	@Test(priority=29,description="Export scheduler in reports page for Date range")
	public void ScheduleOCMInteractionActionReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	
	@Test(priority=30,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportOCMInteractionActionReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=31,dependsOnMethods ="ExportOCMInteractionActionReportDateRange",description="To verification of exported excel in Report downloads for Date Range")
	public void ViewDownloadedReportforDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}
	
	@Test(priority=32,dependsOnMethods ="ViewDownloadedReportforDateRange",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOcmInteractionActionReportInReportsDownloadDateRangePage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Interaction Actions Re"));		
	}
	
	@Test(priority=33,description="Delete record in Reports Download without entering Delete reason")
	public void DeleteWithoutDeleteReasonRecordinReportsDownloadforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deleteWithoutDeleteReason(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");		
	}

	@Test(priority=34,description="Cancel Button in Reports Download Delete Button")
	public void VerifyCancelBtnAtReportsDownloadDeleteBtnForDateRange() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}
	
	@Test(priority=35,description="Delete Record at Reports download Button")
	public void DeleteRecordAtReportsDownload() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Report Deleted","Delete record assertion failed");		
	}

	@Test(priority=36,description="Report page clear All button ")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}

	@Test(priority=37,description="Maximize, minimize")
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);    
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);             
		Assert.assertTrue(InteractionActionReportPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		//screenshot.captureScreen(driver,"OCMInteractionActionReport","Maximize");
		Assert.assertTrue(InteractionActionReportPage.minimizewindow(), "Restored Assertion Failed");
		///screenshot.captureScreen(driver,"OCMInteractionActionReport","Minimize");	
	}

	@Test(priority=38,description="To Verify Export Page Button")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionReportPage.verifyExportToExcel(filePath1));
	}

	@Test(priority=39,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMInteractionActionsReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionReportPage.verifyexportToExcelSheet(maplist));
	}
	
	//Has known issue in sorting alpha numeric values
	@Test(priority=40,dependsOnMethods="VerifyExportedPage",description="To Verify Sort By Ascending")
	public void SortingByAscending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		InteractionActionReportPage.SortByAscending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMInteractionActionsReport (1).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(InteractionActionReportPage.verifyexportToExcelSheet(maplist));
	}
	
	//Has known issue in sorting alpha numeric values
	@Test(priority=41,dependsOnMethods="VerifyExportedPage",description="To Verify Sort By Descending")
	public void SortingByDescending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);        
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		InteractionActionReportPage.SortByDescending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMInteractionActionsReport (2).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();    	
		Assert.assertTrue(InteractionActionReportPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=42,description="Scheduled report button in OCMInteractionAction report page")
	public void ScheduledReportinOCMInteractionActionReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);       
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		InteractionActionReportPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(InteractionActionReportPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");		    	 		
	}

	@Test(priority=43,description="Export to excel button in OCMInteractionAction Report page")
	public void ExportToExcelForInteractionActionReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		InteractionActionReportPage.exportToExcel();
		Assert.assertTrue(InteractionActionReportPage.verifyReportExported(),"export report assertion failed");
	} 

	@Test(priority=44,dependsOnMethods ="ExportToExcelForInteractionActionReport",description="Verify the view Downloaded report in OCMInteractionActions report page")
	public void ViewDownloadedReportInReportDownloadsPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		InteractionActionReportPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");    
	}
	
	@Test(priority=45,dependsOnMethods ="ViewDownloadedReportInReportDownloadsPage",description="To verification of exported excel in Report downloads")
	public void VerifyExporedExcelData() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Interaction Actions Re"));		
	} 

	@Test(priority=46,description="Group By functionality")
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMInteractionActionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMInteractionActionReportPage InteractionActionReportPage=PageFactory.createPageInstance(driver,OCMInteractionActionReportPage.class);
		Assert.assertTrue(InteractionActionReportPage.groupby());
		screenshot.captureScreen("OCMInteractionActionReport", "GroupBy");
		Assert.assertTrue(InteractionActionReportPage.groupby());
		screenshot.captureScreen("OCMInteractionActionReport", "AlreadyGroupBy");
	}



	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("InteractionActionReportResultsV3.4.3.14",method.getName());
		driver.navigate().refresh();
	}

}

