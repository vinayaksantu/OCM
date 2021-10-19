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
import com.tetherfi.pages.OCMAgentInteractionReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import com.tetherfi.utility.JSONReader;
import com.tetherfi.utility.FTPServer;

public class OCMAgentInteractionReportTest extends BaseTest {

	String destinationFilePath = "C:/Users/Admin/git/scripts/ocms/src/test/resources/DownloadedFiles/Json/OCMAgentInteractionReport.json";
	String remoteFilePath = "\\\\172.16.2.61\\c$\\Products\\OCM\\UI\\Configurations\\Reports\\OCMAgentInteractionReport.json";

	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
	public void NavigateToOcmReportsPage() {        
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMIconImg();
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());       
		FTPServer ftp=new FTPServer();
		ftp.transferFileFromRemote(remoteFilePath,destinationFilePath);
	}

	@Test(priority=1,description="To verify Show Report for Single Date")
	public void ShowReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");      
	}      

	@Test(priority=2,description="To verify Show Report in New Tab for Single Date")
	public void ShowReportInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}
	
	@Test(priority=3, description="To verify Agent Interaction Report UI data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage AgentInteractionReportPage =PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(AgentInteractionReportPage.verifyDatabase(reportDetails.getQuery(), reportDetails));
	}
	
	@Test(priority=4,description="Maximize, minimize")
	public void OCMWindow() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);        
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);                   
		Assert.assertTrue(agentInteractionPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMAgentInteractionReportTest","Maximize");
		Assert.assertTrue(agentInteractionPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMAgentInteractionReportTest","Minimize");	
	}

	@Test(priority=5,description="Verify dropdown of all the column headers")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}   

	@Test(priority=6,description="Verify column header Enable")
	public void VerifyColumnsHeaderEnable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}  

	@Test(priority=7,description="Verify column header disable")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertFalse(agentInteractionPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}  

	@Test(priority=8,description="Verify Pagination, Move to previous and next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {  
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}   

	@Test(priority=9,description="Verify Pagination, Move to First and Last Page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	} 

	@Test(priority=10,description="Verify total number of items per page")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	} 
	
	@Test(priority=11,enabled=true,description="Verify number of items selected per page")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}
	
	@Test(priority=12,description="Search by feature")
	public void VerifySearchByFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifySearchByTextbox(reportDetails));
	}
	
	@Test(priority=13,description="Verify the search Is equal to criteria")
	public void VerifySearchByIsEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));				
	}
	
	@Test(priority=14,description="Verify the search Is not equal to criteria")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}   
	
	@Test(priority=15,description="Verify the search contains criteria")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifySearchContains(reportDetails.getSearchStr()));
	}   
	
	@Test(priority=16,description="Verify the Does not contain criteria")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}  

	@Test(priority=17,description="Verify the search starts with criteria")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=18,description="Verify the search Ends with criteria")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=19,description="Search without search text")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		agentInteractionPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(agentInteractionPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}
	
	@Test(priority=20,description="Clear search functionality")
	public void ClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifySearchClear(reportDetails));    	
	}

	@Test(priority=21,description="Advance search on reports page for Is equal to Criteria")
	public void verifyAdvancedSearchinreportpage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyAdvanceSearchIsEqualTo(reportDetails));
	}
	
	@Test(priority=22,description="Advance search on reports page for Is not equal to Criteria")
	public void verifyAdvancedSearchNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);     	
		OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
		OCMReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyAdvanceSearchIsNotEqualTo(reportDetails));   	
	}

	@Test(priority=23,description="Advance search on reports page for Contains Criteria")
	public void verifyAdvancedSearchcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class); 
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(agentInteractionPage.verifyAdvancedSearchContains(reportDetails));    	
	}

	@Test(priority=24,description="Advance search on reports page for Does not Contain Criteria")
	public void verifyAdvancedSearchDoesnotcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(agentInteractionPage.verifyAdvanceSearchDoesNotContains(reportDetails));   
	}

	@Test(priority=25,description="Advance search on reports page for Starts with Criteria")
	public void verifyAdvancedSearchStartswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class); 
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(agentInteractionPage.verifyAdvanceSearchStartsWith(reportDetails)); 	
	}

	@Test(priority=26,description="Advance search on reports page for Ends with Criteria")
	public void verifyAdvancedSearchEndswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(agentInteractionPage.verifyAdvancedSearchEndsWith(reportDetails));
	}
	
	@Test(priority=27,description="Advance search with And Condition")
	public void verifyAdvancedSearchANDCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(agentInteractionPage.advancedSearchANDCriteria(reportDetails));   	
	}

	@Test(priority=28,description="Advance search with OR Condition")
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(agentInteractionPage.advancedSearchORCriteria(reportDetails));    	
	}
	
	@Test(priority=29,description="Clear filters for Advance search")
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(OCMReportsPage.ClearAdvFilters(reportDetails));
	}

	@Test(priority=30,description="To verify Export Scheduler on OCM Reports Page")
	public void ScheduleAgentInteractionReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=31,description="To verify Export Report on OCM Reports Page")
	public void ExportReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=32,dependsOnMethods ="ExportReport",description="To verify view download in Report downloads")
	public void ViewDownloadedReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}  

	//other data column is disabled still able to see the column in exported sheet
	@Test(priority=33,dependsOnMethods ="ViewDownloadedReportInReportsDownloadPage",description="To verification of exported excel in Report downloads")
	public void VerifyDownloadedReportData() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Agent Interaction Repo"));	
	}

	@Test(priority=34,description="Delete record in Reports Download without Delete reason")
	public void DeleteRecordWithoutDeleteReasonInReportsDownloadforSingleDate() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();   	
		ocmReportsPage.deleteWithoutDeleteReason(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");	
	}

	@Test(priority=35,description="Cancel Button in Reports Download Delete Button")
	public void VerifyCancelBtnAtReportsDownloadforSingleDate() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}

	@Test(priority=36,description="Delete Record at Reports download Button")
	public void DeleteRecordforSingleDate() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails),"Record deletion failed");
	}

	@Test(priority=37,description="To verify Show Report for Date Range")
	public void ShowReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	}

	@Test(priority=38,description="To verify Show Report for Date Range in New Tab")
	public void ShowReportInNewPageForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	} 

	@Test(priority=39,description="To verify Export Scheduler on OCM Reports Page for Date Range")
	public void ScheduleAgentInteractionReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=40,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}    

	@Test(priority=41,dependsOnMethods ="ExportReportForDateRange",description="To verification of exported excel in Report downloads for Date Range")
	public void ViewDownloadedReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}

	//other data column is disabled still able to see the column in exported sheet
	@Test(priority=42,dependsOnMethods ="ExportReportForDateRange",description="To verification of exported excel in Report downloads")
	public void VerifyDownloadedReportDataInReportDownloads() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Agent Interaction Repo"));		
	}

	@Test(priority=43,description="Delete record in Reports Download without Delete reason for date range")
	public void DeleteRecordWithoutDeleteReasonInReportsDownloadforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deleteWithoutDeleteReason(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");			
	}

	@Test(priority=44,description="Cancel Button in Reports Download Delete Button")
	public void VerifyDeleteCancelBtnInReportDownloadsPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}

	@Test(priority=45,description="Delete Record at Reports download Button")
	public void DeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails),"");
	}

	@Test(priority=46,description="Report page clear All button ")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}
	
	//defectid:58620			 
	@Test(priority=47,description="To Verify Export Page Button")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyExportToExcel(filePath1));
	}

	
	@Test(priority=48,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentInteractionReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=49,description="Scheduled report button in Agent Interaction report page")
	public void SchedulereportinAgentInteractionReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);            
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		agentInteractionPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(agentInteractionPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
		screenshot.captureScreen("OCMAgentInteractionReportTest","ExportSchedulerPage");
	}
	
	@Test(priority=50,description="Export to excel button in Report page")
	public void ExportToExcel() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		agentInteractionPage.exportToExcel();
		Assert.assertTrue(agentInteractionPage.verifyReportExported(),"export report assertion failed");
	}
	
	@Test(priority=51,dependsOnMethods ="ExportToExcel")
	public void viewExportedExcelDataInReportDownloadsPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		agentInteractionPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");   
	}           
	
	@Test(priority=52,dependsOnMethods ="viewExportedExcelDataInReportDownloadsPage",description="To verification of exported excel in Report downloads")
	public void VerifyExportedExcelData() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Agent Interaction Repo"));	
	}
	
	@Test(priority=53,description="Group by functionality")
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.groupby());
		screenshot.captureScreen("OCMAgentInteractionReportTest", "GroupBy");
		Assert.assertTrue(agentInteractionPage.groupby());
		screenshot.captureScreen("OCMAgentInteractionReportTest", "AlreadyGroupBy");
	}

	@Test(priority=54,enabled=false,description="Verify the column headers against the Json File ")
	public void VerifyJsonDataForColumnNames() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		JSONReader json= new JSONReader(destinationFilePath);
		Assert.assertTrue(agentInteractionPage.verifyJsonDataForgridColumnHidden(json.getJsonGridColumnTitleKeyDataForReports("Hidden")),"JSON data grid column hidden assertion failed");  	
	}

	@Test(priority=55,description="To Verify Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionReportPage =PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionReportPage.verifySorting(),"Sorting assertion failed");
	}
	
	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("OCMAgentInteractionReportTestSnaps",method.getName());
		driver.navigate().refresh();
	}

}
