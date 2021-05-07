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
import com.tetherfi.pages.OCMFaxReceivedDetailsReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;


public class OCMFaxReceivedDetailsReportTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
	public void NavigateToOcmReportsPage() {
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMIconImg();
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());
	}

	/*@Test(priority=1,description="To verify Show Report for Single Date")
	public void ShowOCMFaxReceivedDetailsReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
	}
 
	@Test(priority=2,description="To verify Show Report in New Page for Single Date")
	public void ShowOCMFaxReceivedDetailsReportInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}
 
	@Test(priority=3,description="To verify Show Report for Date Range")
	public void ShowOCMFaxReceivedDetailsReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 

	@Test(priority=4,description="To verify Show Report for Date Range in New page")
	public void ShowOCMFaxReceivedDetailsReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}
 
	@Test(priority=5,description="To verify Export Scheduler in OCM Reports manager Page")
	public void ShowExportSchedulerOCMFaxReceivedDetailsReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}  

	@Test(priority=6,description="To verify Export Report in OCM Report Manager Page")
	public void ExportOCMFaxReceivedDetailsReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=7,dependsOnMethods ="ExportOCMFaxReceivedDetailsReport",description="To verify downloaded report in Report downloads")
	public void ViewDownloadedReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
	} 
   
	@Test(priority=8,enabled=false,dependsOnMethods ="ViewDownloadedOCMFaxReceivedDetailsReportInReportsDownloadPage",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOCMFaxReceivedDetailsReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","Fax Received Detail Report"));	
	}

	@Test(priority=9,description="To verify Export Scheduler in Reports Manager Page for Date Range")
	public void ScheduleOCMFaxReceivedDetailsReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
 
	@Test(priority=10,description="To verify Export Report in Reports Manager Page for Date Range")
	public void ExportOCMFaxReceivedDetailsReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	} 
 
	@Test(priority=11,dependsOnMethods ="ExportOCMFaxReceivedDetailsReportDateRange",description="To verify downloaded report in Report downloads page")
	public void ViewDownloadedReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
	}
 
	@Test(priority=12,description="To verify clear all button in Reports Manager Page")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}
 
	@Test(priority=13,description="To Verify Maximize and minimize window functionality")
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);             
		Assert.assertTrue(faxReceivedDetailsReportPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMFaxReceivedDetailsReportPage","Maximize");
		Assert.assertTrue(faxReceivedDetailsReportPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMFaxReceivedDetailsReportPage","Minimize");	
	} 
 
	@Test(priority=14,description="To Verify Dropdown for All the Columns")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}  
 
	@Test(priority=15,description="To Verify Columns Headers are Enabled")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}  
 
	@Test(priority=16,description="To Verify Columns Headers are Disabled")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertFalse(faxReceivedDetailsReportPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	} 
 
	@Test(priority=17,description="To Verify Arrow move for Previous and Next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	} 
 
	@Test(priority=18,description="To Verify Total Number of Items Per Page Details")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	} 
 
	@Test(priority=19,description="To Verify Number of Items Per Page Selection")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}
 
	@Test(priority=20,description="To export the data using Export Page Button")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyExportToExcel(filePath1));
	}
	
	@Test(priority=21,dependsOnMethods="ExportPage",description="To Verify Exported Page data Against UI Data")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMFaxReceivedDetailsReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyexportToExcelSheet(maplist));
	}
	
	@Test(priority=22,dependsOnMethods="VerifyExportedPage",description="To Verify Sort By Descending")
	public void SortingByDescending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);        
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		faxReceivedDetailsReportPage.SortByDescending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMFaxReceivedDetailsReport (1).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyexportToExcelSheet(maplist));
	}
 
	@Test(priority=23,description="To Verify Schedule Report button on  Fax Received Report Main Page")
	public void SchedulereportinOCMFaxReceivedDetailsReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);       
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		faxReceivedDetailsReportPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(faxReceivedDetailsReportPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
	}
 
	@Test(priority=24,description="To Verify Export Excel button on Fax Received Report Page")
	public void ExportToExcelForOCMFaxReceivedDetailsReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		faxReceivedDetailsReportPage.exportToExcel();
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyReportExported(),"export report assertion failed");
	} 
 
	@Test(priority=25,dependsOnMethods ="ExportToExcelForOCMFaxReceivedDetailsReport",description="To Verify View Download button on Main Page")
	public void ViewDownloadedOCMFaxReceivedDetailsReportInReportsDownloadPageinFaxReceivedDetailsPg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		faxReceivedDetailsReportPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");    
	} 
	
	@Test(priority=26,description="To verify search by feature")
	public void VerifySearchByFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");     
		Assert.assertTrue(faxReceivedDetailsReportPage.verifySearchByTextbox(reportDetails),"Search report assertion failed");
	} 
 
	@Test(priority=27,description="To verify search equals")
	public void VerifySearchEqualsToFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(faxReceivedDetailsReportPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}     

	@Test(priority=28,description="To verify search without providing data in searchbox")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		faxReceivedDetailsReportPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(faxReceivedDetailsReportPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}  

	@Test(priority=29,description="To verify search IsNotEquals")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}   

	@Test(priority=30,description="To verify search Contains")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifySearchContains(reportDetails.getSearchStr()));
	}   

	@Test(priority=31,description="To verify search doesnotContains")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}    

	@Test(priority=32,description="To verify search StartsWith")
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}
 
	@Test(priority=33,description="To verify search EndsWith")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=34,description="To verify search Clear Search")
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifySearchClear(reportDetails));    	
	}
 
	@Test(priority=35,description="To verify Advance Search using Equals parameter")
	public void verifyAdvancedSearchEquals() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);        
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyAdvanceSearch(reportDetails));            
	}
 
	@Test(priority=36,description="To verify Advance Search using NotEquals parameter")
	public void verifyAdvancedSearchNotEquals() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);        
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyAdvanceSearchNotEqualsTo(reportDetails.getSearchStr()));  

	}
 
	@Test(priority=37,description="To verify Advance Search using Contains parameter")
	public void verifyAdvancedSearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);        
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyAdvanceSearchContains(reportDetails));            
	}
 
	@Test(priority=38,description="To verify Advance Search using Does Not contains parameter")
	public void verifyAdvancedSearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);        
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyAdvanceSearchDoesNotContains(reportDetails));            
	}
 
	@Test(priority=39,description="To verify Advance Search using StartsWith search parameter")
	public void verifyAdvancedSearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);        
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyAdvanceSearchStartsWith(reportDetails));            
	}
 
	@Test(priority=40,description="To verify Advance Search using EndsWith")
	public void verifyAdvancedSearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);        
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyAdvanceSearchEndsWith(reportDetails));            
	}
	
	@Test(priority=41,description="To verify Advance search using AND Condition")				   
	public void verifyAdvancedSearchANDCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver, OCMFaxReceivedDetailsReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxReceivedDetailsReportPage.advancedSearchANDCriteria(reportDetails));
	}

	@Test(priority=42,description="To Verify Advance search with OR Condition")
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver, OCMFaxReceivedDetailsReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(faxReceivedDetailsReportPage.advancedSearchORCriteria(reportDetails));
	}
	
	@Test(priority=43,description="To verify Clear filters in Advance search")				   
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(ocmReportsPage.ClearAdvFilters(reportDetails));
	}
	
	@Test(priority=44,description="To Verify Group By using Column headers")				   
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.groupby());
		screenshot.captureScreen("faxReceivedDetailsReport", "GroupBy");
		Assert.assertTrue(faxReceivedDetailsReportPage.groupby());
		screenshot.captureScreen("faxReceivedDetailsReport", "AlreadyGroupBy");
	}

	@Test(priority=45,description="To Verify Arrow move for Previous and Next page in Drill Down page")
	public void VerifyArrowMoveForPreviousAndNextPageForDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyDrillDownOne(reportDetails),"arrow move for previous and next page assertion failed");
	} 
	
	@Test(priority=46,description="To Verify Drill DownGrid for faxline")
	public void VerifyDrillDownforFaxLine() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.FaxLineDrillGrid(reportDetails),"Drill down error");
	} 

	@Test(priority=47,description="To Verify Arrow move for first and last page for Drill Down One")
	public void VerifyArrowMoveForFirstAndLastPageFoDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyArrowMoveForFirstAndLastPageForDrillDownOne(reportDetails),"arrow move for first and last page assertion failed");
	} 
 
	@Test(priority=48,description="To Verify Total Number of Items Per Page Details for Drill Down One")
	public void VerifyTotalNumberOfItemsPerPageDetailsFoDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyTotalNumberOfItemsPerPageDetailsForDrillDownOne(),"item per page assertion failed");
	}
 
	@Test(priority=49,description="To Verify Bulk print button without selecting a row data")
	public void VerifyBulkPrintButtoninDrilldownwithoutRow() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyBulkPrintButtonwithoutData(reportDetails),"BulkPrint error message");
	} 
 
	@Test(priority=50,description="To Verify Bulk print button with row data")
	public void VerifyBulkPrintinDrilldownReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyBulkPrintButtonwithrowsData(reportDetails),"BulkPrint with data error message");
	}
	
	@Test(priority=51,description="To Verify the functionality of Preview Original in drill down grid")
	public void VerifyPreviewOriginalButtoninDrillDown() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyPreviewOrignalButton(reportDetails),"Preview Assertion failed");
	}

	@Test(priority=52,description="To Verify the functionality of Preview Annotated in drill down grid")
	public void VerifyPreviewAnnotatedButtoninDrillDown() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyPreviewAnnotatedButton(reportDetails),"Preview annotated error message");
	}
 
	@Test(priority=53,description="To Verify Print functionality in drill down report")
	public void VerifyPrintButtoninDrillDown() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyPrintButton(reportDetails),"Print error message");
	} 
 
	@Test(priority=54,description="To Cancel Print using Print cancel button")
	public void VerifyPrintCancel() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyAlertCancelButton(reportDetails),"Cancelbutton assertion error message");
	} 
 
	@Test(priority=55,description="To Verify Delete record in Reports Download without Delete reason for date range")
	public void DeleteWithoutDeleteReasonRecordinReportsDownloadforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		Assert.assertTrue(ocmReportsPage.deleteWithoutDeleteReason(reportDetails),"empty delete reason record assertion failed");		
	}

	@Test(priority=56,description="To verify Delete Cancel Button in Reports Downloads")
	public void VerifyCancelBtnInReportsDownload() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}
	
	@Test(priority=57,description="To Delete Downloaded Report record in Reports download page")
	public void DeleteRecordAtReportsDownload() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();															   
		Assert.assertTrue(ocmReportsPage.deleteWithoutDeleteReason(reportDetails),"empty delete reason record assertion failed");		
	}*/
	
	@Test(priority=58, description="To verify fax received details report UI data against DB")
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxReceivedDetailsReport.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	ocmReportsPage.showReport(reportDetails);
		OCMFaxReceivedDetailsReportPage faxReceivedDetailsReportPage=PageFactory.createPageInstance(driver,OCMFaxReceivedDetailsReportPage.class);
		Assert.assertTrue(faxReceivedDetailsReportPage.verifyDatabase(reportDetails.getQuery(),reportDetails,reportDetails.getOrgUnitID()),"Main report data mismatch");
		System.out.println("Main Report Data Match Successfull");
		List<String> faxLineList = new ArrayList<>();
		faxLineList=faxReceivedDetailsReportPage.getFaxLineList();
		System.out.println(faxLineList);
		int k=0;
    	for (int i=0;i<faxLineList.size();i++) {
    		if(k==10) {
    			faxReceivedDetailsReportPage.goToNextPage();
    			k=k-10;
    		}
    		faxReceivedDetailsReportPage.clickOnFaxLineRowOnMainReport(k);
    		Assert.assertTrue(faxReceivedDetailsReportPage.verifyDatabaseDrillGridOne(reportDetails.getQueryDrillGridOne(), reportDetails, faxLineList.get(i)),"Drill Grid One data mismatch for Fax Line" + faxLineList.get(i));
    		System.out.println("Drill Grid One data match successfull for Fax Line" + faxLineList.get(i));
    		k++;
    		Thread.sleep(1000);	
    	}
	}
	
	
	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("OCMFaxReceivedDetailsReport",method.getName());
		driver.navigate().refresh();
	}


}
