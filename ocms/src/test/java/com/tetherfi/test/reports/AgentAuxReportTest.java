
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
import com.tetherfi.pages.AgentAuxReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class AgentAuxReportTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
	public void NavigateToOcmReportsPage() {
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOcmIconImg();
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());
	}

	@Test(priority=1)
	public void ShowAgentAuxReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");       
	}         

	@Test(priority=2, dependsOnMethods ="ShowAgentAuxReport")
	public void ShowOcmAgentAuxReportInNewTab() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}  

	@Test(priority=3)
	public void ScheduleAgentAuxReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=4,dependsOnMethods ="ShowOcmAgentAuxReportInNewTab")
	public void ExportOcmAgentAuxReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	} 
	
	@Test(priority=5,dependsOnMethods ="ExportOcmAgentAuxReport")
	public void ViewDownloadedOcmAgentAuxReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}     
	
	@Test(priority=6)
	public void ShowOcmAgentAuxReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	}   
	
	@Test(priority=7,dependsOnMethods ="ShowOcmAgentAuxReportForDateRange")
	public void ShowOcmAgentAuxReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}   
	
	@Test(priority=8,dependsOnMethods ="ShowOcmAgentAuxReportInNewTabDateRange")
	public void ExportOcmAgentAuxReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}    
	
	@Test(priority=9,dependsOnMethods ="ExportOcmAgentAuxReportDateRange")
	public void ViewDownloadedOcmAgentAuxReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
	}  
	
	@Test(priority=10)
	public void ScheduleAgentAuxReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
	
	@Test(priority=11)
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
	}
	
	@Test(priority=12)
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);                
		Assert.assertTrue(agntScrptPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("AgentAuxReport","Maximize");
		Assert.assertTrue(agntScrptPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("AgentAuxReport","Minimize");	
	}	
	
	@Test(priority=13)
	public void VerifyDropdownForAllTheColumns() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		Assert.assertTrue(agntScrptPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}  
	
	@Test(priority=14)
	public void VerifyColumnsHeaderEnable() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		Assert.assertTrue(agntScrptPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}  
	
	@Test(priority=15)
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		Assert.assertFalse(agntScrptPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}
	
	@Test(priority=16)
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {  
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		Assert.assertTrue(agntScrptPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}    
	
	@Test(priority=17)
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		Assert.assertTrue(agntScrptPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}   
	
	@Test(priority=18)
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		Assert.assertTrue(agntScrptPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	} 
	
	@Test(priority=19)
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		Assert.assertTrue(agntScrptPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}   
	
	@Test(priority=20)
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\";
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		Assert.assertTrue(agntScrptPage.verifyExportToExcel(filePath1));
	}        
	
	@Test(priority=21)
	public void SortingByAscending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		agntScrptPage.SortByAscending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentAuxReport (1).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(agntScrptPage.verifyexportToExcelSheet(maplist));
	}
	
	@Test(priority=22)
	public void SortingByDescending() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);       
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		agntScrptPage.SortByDescending();
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentAuxReport (2).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		Assert.assertTrue(agntScrptPage.verifyexportToExcelSheet(maplist));
	} 
	
	@Test(priority=23)
	public void SchedulereportinAgentAuxReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);           
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		agntScrptPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(agntScrptPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
		screenshot.captureScreen("AgentAuxReportTest","ExportSchedulerPage");    	
	}    
	
	@Test(priority=24)
	public void ExportToExcelForAgentAuxReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		agntScrptPage.exportToExcel();
		Assert.assertTrue(agntScrptPage.verifyReportExported(),"export report assertion failed");
	}
	
	@Test(priority=25,dependsOnMethods ="ExportToExcelForAgentAuxReport")
	public void ViewDownloadedOcmAgentAuxReportInReportsDownloadPageinAgentAuxPg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		agntScrptPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
	}   
	
	@Test(priority=26)
	public void VerifySearchByFeatureForAgentAuxReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(ocmReportsPage.verifySearchByTextbox());
	}   
	
	@Test(priority=27)
	public void VerifySearchFeatureForAgentAuxReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(ocmReportsPage.verifySearchByColumnValue());
	}    
	
	@Test(priority=28)
	public void VerifySearchContainsForAgentAuxReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(ocmReportsPage.verifySearchContainsColumnValue());
	}   
	
	@Test(priority=29)
	public void verifyAdvancedSearchinreportpage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		Assert.assertTrue(agntScrptPage.verifyAdvanceSearch(reportDetails));
	}
	
	@Test(priority=30)
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		agntScrptPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(agntScrptPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}    
	
	@Test(priority=32)
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		Assert.assertTrue(agntScrptPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}   
	
	@Test(priority=33)
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		Assert.assertTrue(agntScrptPage.verifySearchContains(reportDetails.getSearchStr()));
	}   
	
	@Test(priority=34)
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentAuxReportPage agentAuxReportPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		Assert.assertTrue(agentAuxReportPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}    
	
	@Test(priority=35)
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		Assert.assertTrue(agntScrptPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}
	
	@Test(priority=36)
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		Assert.assertTrue(agntScrptPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}
	
	@Test(priority=37)
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentAuxReportPage agntScrptPage=PageFactory.createPageInstance(driver,AgentAuxReportPage.class);
		agntScrptPage.verifySearchClear(reportDetails);    	
	} */  

	/*@AfterMethod
	public void afterEachMethod(Method method) {
		screenshot.captureScreen(driver, "AgentAuxReportTest", method.getName());
	}*/

	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("AgentAuxReportTest",method.getName());
		driver.navigate().refresh();
	}
	

}

