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
	public void ShowAgentInteractionReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");      
	}      
	@Test(priority=2, dependsOnMethods ="ShowAgentInteractionReport",description="To verify Show Report in New Tab for Single Date")
	public void ShowOcmAgentInteractionReportInNewTab() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}  
	@Test(priority=3,description="To verify Export Scheduler on OCM Reports Page")
	public void ScheduleAgentInteractionReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	@Test(priority=4,description="To verify Export Report on OCM Reports Page")
	public void ExportOcmAgentInteractionReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}
	@Test(priority=5,dependsOnMethods ="ExportOcmAgentInteractionReport",description="To verify view download in Report downloads")
	public void ViewDownloadedOcmAgentInteractionReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
	}  
	
	@Test(priority=6,dependsOnMethods ="ViewDownloadedOcmAgentInteractionReportInReportsDownloadPage",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOcmAgentInteractionReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","Agent Interaction Report"));	
	}
	
	@Test(priority=7,description="Delete record in Reports Download without Delete reason")
    public void DeleteWithoutDeleteReasonRecordinReportsDownloadforSingleDate() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();   	
		ocmReportsPage.deleteWithoutDeleteReason(reportDetails);
    	Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
    }
    
    @Test(priority=8,description="Cancel Button in Reports Download Delete Button")
    public void VerifyCancelBtnAtReportsDownloadforSingleDate() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
    	//AgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
		ocmReportsPage.deletecancelButton(reportDetails);
    	Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    @Test(priority=9,description="Delete Record at Reports download Button")
    public void DeleteRecordforSingleDate() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
    	ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails);
    	Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Report Deleted","Delete record assertion failed");
    }
	
	@Test(priority=10,description="To verify Show Report for Date Range")
	public void ShowOcmAgentInteractionReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	}

	@Test(priority=11,dependsOnMethods ="ShowOcmAgentInteractionReportForDateRange",description="To verify Show Report for Date Range in New Tab")
	public void ShowOcmAgentInteractionReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	} 
	@Test(priority=12,description="To verify Export Scheduler on OCM Reports Page for Date Range")
	public void ScheduleAgentInteractionReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	@Test(priority=13,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportOcmAgentInteractionReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}    
	@Test(priority=14,dependsOnMethods ="ExportOcmAgentInteractionReportDateRange",description="To verification of exported excel in Report downloads for Date Range")
	public void ViewDownloadedOcmAgentInteractionReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
	}
	@Test(priority=15,dependsOnMethods ="ViewDownloadedOcmAgentInteractionReportInReportsDownloadPageDateRange",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOcmAgentInteractionReportInReportsDownloadDateRangePage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","Agent Interaction Report"));		
	}
	@Test(priority=16,description="Delete record in Reports Download without Delete reason for date range")
    public void DeleteWithoutDeleteReasonRecordinReportsDownloadforDateRange() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deleteWithoutDeleteReason(reportDetails);
    	Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
    }
    
    @Test(priority=17,description="Cancel Button in Reports Download Delete Button")
    public void VerifyCancelBtnAtReportsDownloadDeleteBtnForDateRange() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
    	Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    @Test(priority=18,description="Delete Record at Reports download Button")
    public void DeleteRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
    	ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails);
    	Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Report Deleted","Delete record assertion failed");
    }
	@Test(priority=19,description="Report page clear All button ")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}
	@Test(priority=20,description="Maximize, minimize")
	public void OCMWindow() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);        
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);                   
		Assert.assertTrue(agentInteractionPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen(driver,"OCMAgentInteractionComplete","Maximize");
		Assert.assertTrue(agentInteractionPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen(driver,"OCMAgentInteractionComplete","Minimize");	
	}
	@Test(priority=21,description="Verify dropdown of all the coulnm headers")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}   
	@Test(priority=22,description="Verify column header Enable")
	public void VerifyColumnsHeaderEnable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}  
	@Test(priority=23,description="Verify column header disable")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertFalse(agentInteractionPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}  
	@Test(priority=24,description="Verify Pagination, Move to previous and next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {  
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}   
	@Test(priority=25,description="Verify Pagination, Move to First and Last Page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	} 
	@Test(priority=26,description="Verify total number of items per page")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}   
	@Test(priority=27,description="Verfiy number of items selected per page")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}    
	@Test(priority=28,description="To Verify Export Page Button")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyExportToExcel(filePath1));
	}
	@Test(priority=29,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception
	{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentInteractionReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyexportToExcelSheet(maplist));
	}
	
	@Test(priority=30,dependsOnMethods="VerifyExportedPage",description="To Verify Sort By Ascending")
	public void SortingByAscending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		agentInteractionPage.SortByAscending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentInteractionReport (1).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(agentInteractionPage.verifyexportToExcelSheet(maplist));
	}  
	@Test(priority=31,dependsOnMethods="VerifyExportedPage",description="To Verify Sort By Descending")
	public void SortingByDescending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		agentInteractionPage.SortByDescending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentInteractionReport (2).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(agentInteractionPage.verifyexportToExcelSheet(maplist));
	}
	@Test(priority=32,description="Scheduled report button in Agent Interaction report page")
	public void SchedulereportinAgentInteractionReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);            
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		agentInteractionPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(agentInteractionPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
		screenshot.captureScreen("AgentInteractionReportTest","ExportSchedulerPage");
	}
	@Test(priority=33,description="Export to excel button in Report page")
	public void ExportToExcel() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		agentInteractionPage.exportToExcel();
		Assert.assertTrue(agentInteractionPage.verifyReportExported(),"export report assertion failed");
	}
	@Test(priority=34,dependsOnMethods ="ExportToExcel")
	public void ViewDownloadedOcmAgentInteractionReportInReportsDownloadPageinAgentInteractionPg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		agentInteractionPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");   
	}          
	@Test(priority=35,description="Search by feature")
	public void VerifySearchByFeatureForAgentInteractionReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifySearchByTextbox(reportDetails));
	}
	@Test(priority=36,description="Verify the search Is equal to criteria")
	public void VerifySearchFeatureForIsEqualtoReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));				
	}
	@Test(priority=37,description="Verify the search Is not equal to criteria")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}   
	@Test(priority=38,description="Verify the search contains criteria")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifySearchContains(reportDetails.getSearchStr()));
	}   
	@Test(priority=39,description="Verify the Does not contain criteria")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}  
	@Test(priority=40,description="Verify the search starts with criteria")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}
	@Test(priority=41,description="Verify the search Ends with criteria")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}
	@Test(priority=42,description="Searc without search text")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		agentInteractionPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(agentInteractionPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}
	@Test(priority=43,description="Clear search functionality")
	public void ClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifySearchClear(reportDetails));    	
	} 	
		
	@Test(priority=44,description="Advance search on reports page for Is equal to Criteria")
	public void verifyAdvancedSearchinreportpage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		Assert.assertTrue(agentInteractionPage.verifyAdvanceSearch(reportDetails));
	}
	@Test(priority=45,description="Advance search on reports page for Is not equal to Criteria")
    public void verifyAdvancedSearchinreportpageSearchNotEqualTo() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);     	
    	OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
    	OCMReportsPage.showReport(reportDetails);
    	OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
    	Assert.assertTrue(agentInteractionPage.verifyAdvanceSearchIsNotEqualTo(reportDetails));   	
    }
    
   @Test(priority=46,description="Advance search on reports page for Contains Criteria")
    public void verifyAdvancedSearchinreportpageSearchcontains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class); 
    	OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
    	OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
    	OCMReportsPage.showReport(reportDetails);
    	Assert.assertTrue(agentInteractionPage.verifyAdvanceSearchContains(reportDetails));    	
    }
    
    @Test(priority=47,description="Advance search on reports page for Does not Contain Criteria")
    public void verifyAdvancedSearchinreportpageSearchDoesnotcontains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
    	OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
    	OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
    	OCMReportsPage.showReport(reportDetails);
    	Assert.assertTrue(agentInteractionPage.verifyAdvanceSearchDoesNotContains(reportDetails));   
    }

  	@Test(priority=48,description="Advance search on reports page for Starts with Criteria")
    public void verifyAdvancedSearchinreportpageSearchStartswith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(4);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class); 
    	OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
    	OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
    	OCMReportsPage.showReport(reportDetails);
    	Assert.assertTrue(agentInteractionPage.verifyAdvanceSearchStartsWith(reportDetails)); 	
    }
    
    @Test(priority=49,description="Advance search on reports page for Ends with Criteria")
    public void verifyAdvancedSearchinreportpageSearchEndswith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
    	OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
    	OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
    	OCMReportsPage.showReport(reportDetails);
    	Assert.assertTrue(agentInteractionPage.verifyAdvanceSearchEndsWith(reportDetails));
    }
	@Test(priority=50,description="Advance search with And Condition")
    public void verifyAdvancedSearchANDCriteria() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
    	OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
    	ReportDetails reportDetails= new ReportDetails(map);
    	//To select Report Channel,name,Type
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
  	    ocmReportsPage.chooseReport(reportDetails);
    	Assert.assertTrue(agentInteractionPage.advancedSearchANDCriteria(reportDetails));   	
    }
    @Test(priority=51,description="Advance search with OR Condition")
    public void verifyAdvancedSearchORCriteria() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
    	OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
    	ReportDetails reportDetails= new ReportDetails(map);
    	//To select Report Channel,name,Type
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
  	    ocmReportsPage.chooseReport(reportDetails);
    	Assert.assertTrue(agentInteractionPage.advancedSearchORCriteria(reportDetails));    	
  }
    @Test(priority=52,description="Clear filters for Advance search")
    public void ClearfiltersAdvSrch() throws Exception{ 	
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
    	Assert.assertTrue(OCMReportsPage.ClearAdvFilters(reportDetails));
    } 
    
    @Test(priority=53)
    public void GroupBy() throws Exception{
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
	  Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
	  ReportDetails reportDetails= new ReportDetails(map);
	  OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
	  ocmReportsPage.showReport(reportDetails);
	  OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
  	  Assert.assertTrue(agentInteractionPage.groupby());
  	  screenshot.captureScreen("OCMAgentInteractionComplete", "GroupBy");
  	  Assert.assertTrue(agentInteractionPage.groupby());
      screenshot.captureScreen("OCMAgentInteractionComplete", "AlreadyGroupBy");
    }
	
    @Test(priority=54,description="Verify the column headers against the Json File ")
	public void VerifyJsonDataForColumnNames() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentInteractionReportPage agentInteractionPage=PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
		agentInteractionPage.verifycolumnsHeaderEnabled();
		JSONReader json= new JSONReader(destinationFilePath);
		Assert.assertTrue(agentInteractionPage.verifyJsonDataForgridColumnHidden(json.getJsonGridColumnTitleKeyDataForReports("Hidden")),"JSON data grid column hidden assertion failed");  	
	}

    
    @Test(priority=38)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentInteractionReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	ocmReportsPage.showReport(reportDetails);
    	OCMAgentInteractionReportPage AgentInteractionReportPage =PageFactory.createPageInstance(driver,OCMAgentInteractionReportPage.class);
    	Assert.assertTrue(AgentInteractionReportPage.verifyDatabase(reportDetails.getQuery(), reportDetails));
    }

    
    @AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("OCMAgentInteractionReportTest",method.getName());
		driver.navigate().refresh();
	}

}
