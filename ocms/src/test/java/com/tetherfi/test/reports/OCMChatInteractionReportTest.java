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
import com.tetherfi.pages.OCMChatInteractionReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;


public class OCMChatInteractionReportTest extends BaseTest {

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
	public void ShowChatInteractionReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");      
	}      
	
	@Test(priority=2, dependsOnMethods ="ShowChatInteractionReport",description="To verify Show Report in New Tab for Single Date")
	public void ShowOcmChatInteractionReportInNewTab() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}
	
	@Test(priority=3,description="Verify Pagination, Move to previous and next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {  
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}
	
	@Test(priority=4,description="Verify Pagination, Move to First and Last Page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	} 
	
	@Test(priority=5,description="Search by feature")
	public void VerifySearchByFeatureForChatInteractionReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifySearchByTextbox(reportDetails));
	}
	
	@Test(priority=6,description="Verify the search Is equal to criteria")
	public void VerifySearchFeatureForIsEqualtoReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));				
	}
	
	@Test(priority=7,description="Verify the search Is not equal to criteria")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}  
	
	@Test(priority=8,description="Verify the search contains criteria")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifySearchContains(reportDetails.getSearchStr()));
	} 
	
	@Test(priority=9,description="Verify the Does not contain criteria")
	public void VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}  
	
	@Test(priority=10,description="Verify the search starts with criteria")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}
	
	@Test(priority=11,description="Verify the search Ends with criteria")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}
	
	@Test(priority=12,description="Search without search text")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		ChatInteractionPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(ChatInteractionPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}
	
	@Test(priority=13,description="Clear search functionality")
	public void ClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifySearchClear(reportDetails));    	
	} 

	@Test(priority=14,description="Advance search on reports page for Is equal to Criteria")
	public void verifyAdvancedSearchinreportpage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifyAdvanceSearchIsEqualTo(reportDetails));
	}
	
	@Test(priority=15,description="Advance search on reports page for Is not equal to Criteria")
	public void verifyAdvancedSearchinreportpageSearchNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);     	 
		OCMReportsPage.showReport(reportDetails);
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifyAdvanceSearchIsNotEqualTo(reportDetails));   	
	}

	@Test(priority=16,description="Advance search on reports page for Contains Criteria")
	public void verifyAdvancedSearchinreportpageSearchcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class); 
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(ChatInteractionPage.verifyAdvanceSearchContains(reportDetails));    	
	}

	@Test(priority=17,description="Advance search on reports page for Does not Contain Criteria")
	public void verifyAdvancedSearchinreportpageSearchDoesnotcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(ChatInteractionPage.verifyAdvanceSearchDoesNotContains(reportDetails));   
	}

	@Test(priority=18,description="Advance search on reports page for Starts with Criteria")
	public void verifyAdvancedSearchinreportpageSearchStartswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class); 
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(ChatInteractionPage.verifyAdvanceSearchStartsWith(reportDetails)); 	
	}

	@Test(priority=19,description="Advance search on reports page for Ends with Criteria")
	public void verifyAdvancedSearchinreportpageSearchEndswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(ChatInteractionPage.verifyAdvanceSearchEndsWith(reportDetails));
	}
	
	@Test(priority=20,description="Advance search with And Condition")
	public void verifyAdvancedSearchANDCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(ChatInteractionPage.advancedSearchANDCriteria(reportDetails));   	
	}
	
	@Test(priority=21,description="Advance search with OR Condition")
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(ChatInteractionPage.advancedSearchORCriteria(reportDetails));    	
	}
	
	@Test(priority=22,description="Clear filters for Advance search")
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(OCMReportsPage.ClearAdvFilters(reportDetails));
	} 

	@Test(priority=23)
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.groupby());
		screenshot.captureScreen("ChatInterationReportComplete", "GroupBy");
		Assert.assertTrue(ChatInteractionPage.groupby());
		screenshot.captureScreen("ChatInterationReportComplete", "AlreadyGroupBy");
	}

	@Test(priority=24,description="To Verify Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifySorting(),"Sorting assertion failed");
	}
	
	@Test(priority=25,description="To verify the Chat Interaction Report UI data against DB")
    public void database() throws Exception {
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
   		Map<String, String> map = new ExcelReader(filePath, "Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map); 
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
   		OCMChatInteractionReportPage chatInteractionReportPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		chatInteractionReportPage.sortAscStartDateTime();	
   		Assert.assertTrue(chatInteractionReportPage.verifyDatabase(reportDetails.getQuery(),reportDetails),"UI and Database data mismatch");
   		System.out.println("Database Validation Completed Succesfully" +" : "+"UI and Database data is matched"); 
   }
	
	@Test(priority=26,description="To verify Export Scheduler on OCM Reports Page")
	public void ScheduleChatInteractionReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	
	@Test(priority=27,description="To verify Export Report on OCM Reports Page")
	public void ExportOcmChatInteractionReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}
	
	@Test(priority=28,dependsOnMethods ="ExportOcmChatInteractionReport",description="To verify view download in Report downloads")
	public void ViewDownloadedOcmChatInteractionReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}  

	@Test(priority=29,description="Delete record in Reports Download without Delete reason")
	public void DeleteWithoutDeleteReasonRecordinReportsDownloadforSingleDate() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();   	
		Assert.assertTrue(ocmReportsPage.deleteWithoutDeleteReason(reportDetails),"empty delete reason record assertion failed");
	}

	@Test(priority=30,description="Cancel Button in Reports Download Delete Button")
	public void VerifyCancelBtnAtReportsDownloadforSingleDate() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}
	
	@Test(priority=31,description="Delete Record at Reports download Button")
	public void DeleteRecordforSingleDate() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Report Deleted","Delete record assertion failed");
	}

	@Test(priority=32,description="To verify Show Report for Date Range")
	public void ShowOcmChatInteractionReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	}

	@Test(priority=33,dependsOnMethods ="ShowOcmChatInteractionReportForDateRange",description="To verify Show Report for Date Range in New Tab")
	public void ShowOcmChatInteractionReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}
	
	@Test(priority=34,description="To verify Export Scheduler on OCM Reports Page for Date Range")
	public void ScheduleChatInteractionReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	
	@Test(priority=35,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportOcmChatInteractionReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}    
	
	@Test(priority=36,dependsOnMethods ="ExportOcmChatInteractionReportDateRange",description="To verification of exported excel in Report downloads for Date Range")
	public void ViewDownloadedOcmChatInteractionReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}
		
	@Test(priority=37,description="Report page clear All button ")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}
	
	@Test(priority=38,description="Maximize, minimize")
	public void OCMWindow() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);        
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);                   
		Assert.assertTrue(ChatInteractionPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMChatInteraction","Maximize");
		Assert.assertTrue(ChatInteractionPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMChatInteraction","Minimize");	
	}
	
	@Test(priority=39,description="Verify dropdown of all the coulnm headers")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}  
	
	@Test(priority=40,description="Verify column header Enable")
	public void VerifyColumnsHeaderEnable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}  
	
	@Test(priority=41,description="Verify column header disable")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertFalse(ChatInteractionPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}	
	
	@Test(priority=42,description="Verify total number of items per page")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}   
	
	@Test(priority=43,description="Verfiy number of items selected per page")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}
	
	@Test(priority=44,description="To Verify Export Page Button")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifyExportToExcel(filePath1));
	}
	
	@Test(priority=45,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMChatInteractionReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(ChatInteractionPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=46,description="Scheduled report button in Chat Interaction report page")
	public void SchedulereportinChatInteractionReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);            
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		ChatInteractionPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(ChatInteractionPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
	}
	
	@Test(priority=47,description="Export to excel button in Report page")
	public void ExportToExcel() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		ChatInteractionPage.exportToExcel();
		Assert.assertTrue(ChatInteractionPage.verifyReportExported(),"export report assertion failed");
	}
	
	@Test(priority=48,dependsOnMethods ="ExportToExcel")
	public void ViewDownloadedOcmChatInteractionReportInReportsDownloadPageinChatInteractionPg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMChatInteractionReportPage ChatInteractionPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		ChatInteractionPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");   
	}
		
	@AfterMethod
	public void afterEachMethod(Method method) {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("ChatInterationReport_3.4.3.14", method.getName());
		driver.navigate().refresh();
	}
}



