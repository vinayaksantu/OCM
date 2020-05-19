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
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.AgentHistoricalReportPage;
import com.tetherfi.pages.CepEventMappingPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class AgentHistoricalReportTest extends BaseTest {
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
    public void ShowOCMAgentHistoricalReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
    }

    @Test(priority=2,description="To verify Show Report in New Tab for Single Date")
    public void ShowOcmAgentHistoricalReportInNewTab() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReportInNewPage(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
        ocmReportsPage.switchBackToParentWindow();
    }

    @Test(priority=3,description="To verify Export Scheduler on OCM Reports Page")
    public void ScheduleOCMAgentHistoricalReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.scheduleReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    }  

	@Test(priority=4,description="To verify Export Report on OCM Reports Page")
	public void ExportOcmAgentHistoricalReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=5,dependsOnMethods ="ExportOcmAgentHistoricalReport",description="To verify view download in Report downloads")
	public void ViewDownloadedOcmAgentHistoricalReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	} 

	@Test(priority=6,dependsOnMethods ="ViewDownloadedOcmAgentHistoricalReportInReportsDownloadPage",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOcmAgentHistoricalReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Agent Historical Repor"));	
	}  

	@Test(priority=7,description="To verify Show Report for Date Range")
	public void ShowOcmAgentHistoricalReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 
	
	@Test(priority=8,description="To verify Show Report for Date Range in New Tab")
	public void ShowOcmAgentHistoricalReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}   
	@Test(priority=9,description="To verify Export Scheduler on OCM Reports Page for Date Range")
	public void ScheduleOcmAgentHistoricalReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	@Test(priority=10,description="To verify Export Report on OCM Reports Page for Date Range")
	public void ExportOcmAgentHistoricalReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	} 
	@Test(priority=11,dependsOnMethods ="ExportOcmAgentHistoricalReportDateRange",description="To verification of exported excel in Report downloads for Date Range")
	public void ViewDownloadedOcmAgentHistoricalReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}
	
	@Test(priority=12,dependsOnMethods ="ViewDownloadedOcmAgentHistoricalReportInReportsDownloadPageDateRange",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOcmAgentHistoricalReportInReportsDownloadDateRangePage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Agent Historical Repor"));	
	} 
	
	@Test(priority=13,description="To verfiy clear all button")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
	}
	
	@Test(priority=14,description="To Verify OCM Window Maximize minimize")
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);             
		Assert.assertTrue(agentHistoricalPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMAgentHistoricalReport","Maximize");
		Assert.assertTrue(agentHistoricalPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMAgentHistoricalReport","Minimize");	
	} 
	@Test(priority=15,description="To Verify Dropdown for All the Columns")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}   
	@Test(priority=16,description="To Verify Columns Headers are Enabled")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}  
	@Test(priority=17,description="To Verify Columns Headers are Disabled")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertFalse(agentHistoricalPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	} 
	@Test(priority=18,description="To Verify Arrow move for Previous and Next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}    
	@Test(priority=19,description="To Verify Arrow move for first and last page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}  
	@Test(priority=20,description="To Verify Total Number of Items Per Page Details")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}  
	@Test(priority=21,description="To Verify Number of Items Per Page Selection")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	} 
	
	@Test(priority=22,description="To Verify Export Page Button")
	public void ExportPage() throws Exception
	{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifyExportToExcel(filePath1));
	}
	
	@Test(priority=23,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception
	{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentHistoricalReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifyexportToExcelSheet(maplist));
	}
	
	@Test(priority=24,dependsOnMethods="VerifyExportedPage",description="To Verify Sort By Ascending")
	public void SortingByAscending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		agentHistoricalPage.SortByAscending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentHistoricalReport (1).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(agentHistoricalPage.verifyexportToExcelSheet(maplist));
	}    
	
	@Test(priority=25,dependsOnMethods="VerifyExportedPage",description="To Verify Sort By Descending")
	public void SortingByDescending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);        
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		agentHistoricalPage.SortByDescending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentHistoricalReport (2).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(agentHistoricalPage.verifyexportToExcelSheet(maplist));
	}
	
	@Test(priority=26,description="To Verify Schedule Report button on Main Page")
	public void SchedulereportinagentHistoricalPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);       
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		agentHistoricalPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(agentHistoricalPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
	}
	
	@Test(priority=27,description="To Verify Export Excel button on Main Page")
	public void ExportToExcelForAgentHistoricalReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		agentHistoricalPage.exportToExcel();
		Assert.assertTrue(agentHistoricalPage.verifyReportExported(),"export report assertion failed");
	} 
	
	@Test(priority=28,dependsOnMethods ="ExportToExcelForAgentHistoricalReport",description="To Verify View Download button on Main Page")
	public void ViewDownloadedOcmAgentHistoricalReportInReportsDownloadPageinAgentHistoricalPg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		agentHistoricalPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");    
	} 
	
	@Test(priority=29,dependsOnMethods ="ViewDownloadedOcmAgentHistoricalReportInReportsDownloadPageinAgentHistoricalPg",description="Verification of exported excel from main page in Report downloads")
	public void VerifyViewDownloadedOcmAgentHistoricalReportInReportsDownload() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Agent Historical Repor"));	
	}
	
	@Test(priority=30,description="To verify search by feature")
	public void VerifySearchByFeatureForAgentHistoricalReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");     
		Assert.assertTrue(agentHistoricalPage.verifySearchByTextbox(reportDetails));
	}
	
	@Test(priority=31,description="To verify search equals")
	public void VerifySearchEqualsToFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(agentHistoricalPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}     
	
	@Test(priority=32,description="To verify search without providing data in searchbox")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		agentHistoricalPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(agentHistoricalPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}  
	
	@Test(priority=33,description="To verify search IsNotEquals")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}   
	
	@Test(priority=34,description="To verify search Contains")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifySearchContains(reportDetails.getSearchStr()));
	}   
	
	@Test(priority=35,description="To verify search doesnotContains")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}    
	
	@Test(priority=36,description="To verify search StartsWith")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}
	@Test(priority=37,description="To verify search EndsWith")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}
	
	@Test(priority=38,description="To verify search Clear Search")
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		agentHistoricalPage.verifySearchClear(reportDetails);    	
	}
	
	@Test(priority=39,description="To verify Advance Search Equals")
	public void verifyAdvancedSearchinreportpage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);        
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(agentHistoricalPage.verifyAdvanceSearch(reportDetails));            
	}
	
	@Test(priority=40)
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		ocmReportsPage.ClearAdvFilters(reportDetails);
	}

	@Test(priority=41)
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.groupby());
		screenshot.captureScreen("OCMAgentHistoricalReport", "GroupBy");
		Assert.assertTrue(agentHistoricalPage.groupby());
		screenshot.captureScreen("OCMAgentHistoricalReport", "AlreadyGroupBy");
	}
	
	@Test(priority=42,description="To verify Advance Search Not Equal To")
    public void verifyAdvancedSearchinreportpageSearchNotEqualTo() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
    	OCMReportsPage.showReport(reportDetails);
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifyAdvanceSearchNotEqualsTo(reportDetails)); 
    }
    
    @Test(priority=43,description="To verify Advance Search Contains")
    public void verifyAdvancedSearchinreportpageSearchcontains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
    	OCMReportsPage.showReport(reportDetails);
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifyAdvanceSearchContains(reportDetails)); 
    }
    
    @Test(priority=44,description="To verify Advance Search DoesNot Contains")
    public void verifyAdvancedSearchinreportpageSearchDoesnotcontains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
    	OCMReportsPage.showReport(reportDetails);
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifyAdvanceSearchDoesNotContains(reportDetails)); 
     }

    @Test(priority=45,description="To verify Advance Search Starts With")
    public void verifyAdvancedSearchinreportpageSearchStartswith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
    	OCMReportsPage.showReport(reportDetails);
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifyAdvanceSearchStartsWith(reportDetails)); 
     }
    
    @Test(priority=46)
    public void verifyAdvancedSearchinreportpageSearchEndswith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
    	OCMReportsPage.showReport(reportDetails);
		AgentHistoricalReportPage agentHistoricalPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agentHistoricalPage.verifyAdvanceSearchEndsWith(reportDetails)); 
     }

    @Test(priority=59, description="To verify report data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
   		AgentHistoricalReportPage AgentHistoricalReport=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
   		AgentHistoricalReport.sortAscAgentName();;
		Assert.assertTrue(AgentHistoricalReport.verifyDatabase(reportDetails.getQuery(), reportDetails),"Main Report Data Mismatch");
		System.out.println("Main Report Data Match Successfull");
		List<String> agentList = new ArrayList<>();
		agentList = AgentHistoricalReport.getAgents();
		System.out.println(agentList);
		int k=0;
		for(int i=0;i<agentList.size();i++) {
			if(k==10){
				AgentHistoricalReport.goToNextPage();
				k=k-10;
			}
			AgentHistoricalReport.clickOnAgentIdRowOnMainReport(k);
			Assert.assertTrue(AgentHistoricalReport.verifyDatabaseDrillGridOne(reportDetails.getQueryDrillGridOne(), reportDetails, agentList.get(i)),"Drill Grid One data mismatch for Agent Id " + agentList.get(i));
			System.out.println("Drill Grid One data match successfull for Agent Id " + agentList.get(i));
			k++;
			Thread.sleep(1000);
		}
		/*List<String> skillDates = new ArrayList<>();
		for(int i=0;i<skillList.size();i++) {
			AgentHistoricalReport.clickOnSkillIdRowOnMainReport(i);
			Thread.sleep(1000);
			skillDates = AgentHistoricalReport.getSkillDates();
			//System.out.println(skillDates);
			k=0;
			for(int j=0;j<skillDates.size();j++) {
				if(k==10){
					AgentHistoricalReport.goToNextPageDrillOne();
					k=k-10;
				}
				AgentHistoricalReport.clickOnDateRowOnDrillOneReport(k);
				Assert.assertTrue(AgentHistoricalReport.verifyDatabaseDrillGridTwo(reportDetails.getQueryDrillGridTwo(), reportDetails, skillDates.get(j), skillList.get(i)),"Drill Grid Two data mismatch for Skill Id " + skillList.get(i) + " and Date " + skillDates.get(j));
				System.out.println("Drill Grid Two data match successfull for Skill Id " + skillList.get(i) + " and Date " + skillDates.get(j));
				k++;
				Thread.sleep(1000);
			}
			AgentHistoricalReport.closeDrillOneReport();
			Thread.sleep(1000);
		}*/
	}

	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("OCMAgentHistoricalReport",method.getName());
		driver.navigate().refresh();
	}

}
