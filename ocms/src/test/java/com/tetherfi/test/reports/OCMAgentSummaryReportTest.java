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
import com.tetherfi.model.user.CepEventMappingDetails;
import com.tetherfi.pages.CepEventMappingPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMAgentSummaryReportPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMAgentSummaryReportTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToOcmReportsPage() {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOcmIconImg();
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());
    }
   
/*   @Test(priority=1)
    public void ShowOCMAgentSummaryReport() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);
    	Assert.assertTrue(OCMReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
    } 

    @Test(priority=2, dependsOnMethods ="ShowOCMAgentSummaryReport")
    public void ShowOCMAgentSummaryReportInNewTab() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReportInNewPage(reportDetails);
    	Assert.assertTrue(OCMReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
    	OCMReportsPage.switchBackToParentWindow();
    } 
    
   @Test(priority=3)
    public void ScheduleOCMAgentSummaryReport() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.scheduleReport(reportDetails);
    	Assert.assertTrue(OCMReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    } 
    
    @Test(priority=4)//,dependsOnMethods ="ShowOCMAgentSummaryReportInNewTab")
    public void ExportOCMAgentSummaryReport() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.exportReport(reportDetails);
    	Assert.assertTrue(OCMReportsPage.verifyReportExported(),"export report assertion failed");
    }
    
    @Test(priority=5,dependsOnMethods ="ExportOCMAgentSummaryReport")
    public void ViewDownloadedOcmAgentSummaryReportInReportsDownloadPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.exportReport(reportDetails);
    	OCMReportsPage.viewDownloadedReportInReportsDownloadsPage();
    	Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }  

    @Test(priority=6)
    public void ShowOCMAgentSummaryReportForDateRange() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);
    	Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
    } 
     
    @Test(priority=7,dependsOnMethods ="ShowOCMAgentSummaryReportForDateRange")
    public void ShowOCMAgentSummaryReportInNewTabDateRange() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReportInNewPage(reportDetails);
    	Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
    	OCMReportsPage.switchBackToParentWindow();
    }
    
    @Test(priority=8,dependsOnMethods ="ShowOCMAgentSummaryReportInNewTabDateRange")
    public void ExportOCMAgentSummaryReportDateRange() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.exportReport(reportDetails);
    	Assert.assertTrue(OCMReportsPage.verifyReportExported(),"export report assertion failed");
    }
     
    @Test(priority=9,dependsOnMethods ="ExportOCMAgentSummaryReportDateRange")
    public void ViewDownloadedOcmAgentSummaryReportInReportsDownloadPageDateRange() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.viewDownloadedReportInReportsDownloadsPage();
    	Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }*/
    
    
    
    
  /*  @Test(priority=10)
    public void ScheduleOCMAgentSummaryReportforDateRange() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.scheduleReport(reportDetails);
    	Assert.assertTrue(OCMReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    }
    
    @Test(priority=11)
    public void ClearAll() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.ClearHomepgDrpDown(reportDetails);
    }
    
    @Test(priority=12)
    public void OCMWindow() throws Exception {	
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);    
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);             
    	Assert.assertTrue(OCMAgentSummaryReportPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"OCMAgentSummaryReport","Maximize");
    	Assert.assertTrue(OCMAgentSummaryReportPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"OCMAgentSummaryReport","Minimize");	
    }
     
    @Test(priority=13)
    public void VerifyDropdownForAllTheColumns() throws Exception {		
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);		
    	OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);		
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
       
    @Test(priority=14)
    public void VerifyColumnsHeaderEnable() throws Exception {  	
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);		
    	OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);	
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
      
    @Test(priority=15)
    public void VerifyColumnsHeaderDisable() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertFalse(OCMAgentSummaryReportPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
     
    @Test(priority=16)
    public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    } 
       
    @Test(priority=17)
    public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
      
    @Test(priority=18)
    public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);
    	OCMAgentSummaryReportPage AgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(AgentSummaryReportPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
      
    @Test(priority=19)
    public void VerifyNumberOfItemsPerPageSelection() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
      
    @Test(priority=20)
    public void ExportPage() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);    	
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifyExportToExcel(filePath1));
    }
    
    @Test(priority=21)
    public void SortingByAscending() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	OCMAgentSummaryReportPage.SortByAscending();
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentSummaryReport (1).xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifyexportToExcelSheet(maplist));
    }
      
    @Test(priority=22)
    public void SortingByDescending() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);        
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	OCMAgentSummaryReportPage.SortByDescending();
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentSummaryReport (2).xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
    	screenshot.captureScreen(driver,"OCMAgentSummaryReport","Descending");
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=23)
    public void SchedulereportinOCMAgentSummaryReportPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);       
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	OCMAgentSummaryReportPage.navigateToExportSchedulerPage();    
    	Assert.assertTrue(OCMAgentSummaryReportPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
    	screenshot.captureScreen("OCMAgentSummaryReportTest","ExportSchedulerPage");    	 
    }
    
    @Test(priority=24)
    public void ExportToExcelForAgentSummaryReport() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);
    	Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
    	OCMAgentSummaryReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	agntloginlogoutPage.exportToExcel();
    	Assert.assertTrue(agntloginlogoutPage.verifyReportExported(),"export report assertion failed");
    } 

    @Test(priority=25,dependsOnMethods ="ExportToExcelForAgentSummaryReport")
    public void ViewDownloadedOcmAgentSummaryReportInReportsDownloadPageinAgentSummaryPg() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);  
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	OCMAgentSummaryReportPage.viewDownloadedReportInReportsDownloadsPage();
    	Assert.assertTrue(OCMReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");    
    }
      
    @Test(priority=26)
    public void VerifySearchByFeatureForAgentSummaryReport() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");     
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifySearchByTextbox(reportDetails));
    }
    
    @Test(priority=27)
    public void VerifySearchFeatureForAgentSummaryReport() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
    }
         
   @Test(priority=28)
    public void searchwithoutSearchTextbox() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	OCMAgentSummaryReportPage.searchwithoutextsearch(reportDetails);
    	Assert.assertEquals(OCMAgentSummaryReportPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
      
    @Test(priority=29)
    public void VerifySearchIsNotEqualTo() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);  
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
    }
    
   /*@Test(priority=30)
    public void  VerifySearchContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);  
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifySearchContains(reportDetails.getSearchStr()));
    }
    
    @Test(priority=31)
    public void  VerifySearchDoesNotContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);  
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
    }
        
    @Test(priority=32)
    public void  VerifySearchStartsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);  
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifySearchStartsWith(reportDetails.getSearchStr()));
    }
    
    @Test(priority=33)
    public void  VerifySearchEndsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);  
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifySearchEndsWith(reportDetails.getSearchStr()));
    }
    
    @Test(priority=34)
    public void SearchClearSearch() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);  
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	OCMAgentSummaryReportPage.verifySearchClear(reportDetails);    	
    }
    
    @Test(priority=35)
    public void verifyAdvancedSearchinreportpage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
    	OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
    	OCMReportsPage.showReport(reportDetails);
    	Assert.assertTrue(OCMReportsPage.verifyReportDisplayedNew(reportDetails),"Show report assertion failed");
    }
    
  /*  @Test(priority=36)
    public void ClearfiltersAdvSrch() throws Exception{ 	
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
    	OCMReportsPage.ClearAdvFilters(reportDetails);
    } 

    @Test(priority=37)
    public void GroupBy() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(OCMAgentSummaryReportPage.groupby());
    	screenshot.captureScreen("OCMAgentSummaryReport", "GroupBy");
    	Assert.assertTrue(OCMAgentSummaryReportPage.groupby());
    	screenshot.captureScreen("OCMAgentSummaryReport", "AlreadyGroupBy");
    }

 /* @Test(priority=38)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	OCMReportsPage.showReport(reportDetails);
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifyDatabase(reportDetails.getQuery()));
    }
    @Test(priority=39)
    public void VerifydownloadedReportDownloadPg() throws Exception{   	
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
    	OCMReportsPage.exportReport(reportDetails);
    	OCMReportsPage.viewDownloadedReportInReportsDownloadsPage();
    	Assert.assertTrue(OCMReportsPage.verifyDownloadedReportInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifyReportDownloadExcel(filePath1));
    	
    }
    
    @Test(priority=40)
    public void verifyDownloadedExcelfile() throws Exception{
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	String filePath2 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	//List<Map<String, String>> maplist = new ExcelReader(filePath2,"OCM Agent Summary Report").getTestData();	
       	OCMAgentSummaryReportPage.verifyReportDownloadExcelFileName(filePath2);
       	String filePath3 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMReportDownload.xlsx";
       	List<Map<String, String>> maplist = new ExcelReader(filePath3,"OCM Agent Summary Report").getTestData();
    	Assert.assertTrue(OCMAgentSummaryReportPage.verifyexportToExcelSheet(maplist));	  
    }*/
    
   /* @Test(priority=41)
    public void VerifyDeleteRecordinReportsDownload() throws Exception {
    	HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
    	homePage.navigateToOCMPage();
    	OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
    	Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
    	ocmHomePage.navigateToReportsDownloadPage();
    	OCMAgentSummaryReportPage OCMAgentSummaryReportPage=PageFactory.createPageInstance(driver,OCMAgentSummaryReportPage.class);
    	OCMAgentSummaryReportPage.clickOnDeleteButtonInReportDownloadsPage();
    	//CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	//Assert.assertTrue(CepEventMappingPage.isCepEventMappingPageDisplayed(),"Reports Download assertion failed");
    	
    	
    }
    @Test(priority=42)
    public void DeleteWithoutDeleteReasonRecordinReportsDownload() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
    	CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	CepEventMappingPage.deleteCEPEventWithoutDeleteReasonRecord(CepEventMappingDetails);
    	Assert.assertEquals(CepEventMappingPage.getMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
    }
    
    @Test(priority=43)
    public void VerifyCancelBtnAtDeleteCEPEventMappingRecord() throws Exception{
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	CepEventMappingPage.searchCepEventMapping("1919");
    	Thread.sleep(1000);
    	CepEventMappingPage.clickOnDeleteButton();
    	CepEventMappingPage.clickOnDeleteCancelBtn();
    	Assert.assertFalse(CepEventMappingPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }

    @Test(priority=28)
    public void DeleteRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
    	CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	CepEventMappingPage.deleteCEPEventMappingRecord(CepEventMappingDetails);
    	Assert.assertEquals(CepEventMappingPage.getSuccessMessage(),"Record Deleted Successfully","Delete record assertion failed");
    }

    @Test(priority=42)
    public void verifyAdvancedSearchinreportpageSearchNotEqualTo() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
    	OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
    	OCMReportsPage.showReport(reportDetails);
    	Assert.assertTrue(OCMReportsPage.verifyReportDisplayedNew(reportDetails),"Show report assertion failed");
    }
    
    @Test(priority=43)
    public void verifyAdvancedSearchinreportpageSearchcontains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
    	OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
    	OCMReportsPage.showReport(reportDetails);
    	Assert.assertTrue(OCMReportsPage.verifyReportDisplayedNew(reportDetails),"Show report assertion failed");
    }
    
   /* @Test(priority=44)
    public void verifyAdvancedSearchinreportpageSearchDoesnotcontains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
    	OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
    	OCMReportsPage.showReport(reportDetails);
    	Assert.assertTrue(OCMReportsPage.verifyReportDisplayedNew(reportDetails),"Show report assertion failed");
    }

    @Test(priority=45)
    public void verifyAdvancedSearchinreportpageSearchStartswith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(4);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
    	OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
    	OCMReportsPage.showReport(reportDetails);
    	Assert.assertTrue(OCMReportsPage.verifyReportDisplayedNew(reportDetails),"Show report assertion failed");
    }
    
    @Test(priority=46)
    public void verifyAdvancedSearchinreportpageSearchEndswith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
    	OCMReportsPage.chooseAdvancedSearchNew(reportDetails);  
    	OCMReportsPage.showReport(reportDetails);
//    	Assert.assertTrue(OCMReportsPage.verifyReportDisplayedNew(reportDetails),"Show report assertion failed");
    }
    
    @Test(priority=47)
    public void ReportChannelValidation() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
    	ReportDetails reportDetails= new ReportDetails(map); 	
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   		
    	Assert.assertTrue(OCMReportsPage.reportChannelValidation(),"Invalid channel message");   	    	
    }
    
   @Test(priority=48)
   public void ReportNameValidation() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
    	Assert.assertTrue(OCMReportsPage.reporNameValidation(reportDetails),"Invalid Reportname message");    	
    }
    
    @Test(priority=49)
    public void reportTypeValidation() throws Exception{
     	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
     	Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
     	ReportDetails reportDetails= new ReportDetails(map);
     	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
     	Assert.assertTrue(OCMReportsPage.reportTypeValidation(reportDetails),"Invalid ReportType message");    	
     }*/
    
    @Test(priority=50)
    public void reportSingleDateTypeValidation() throws Exception{
     	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentSummaryReportData.xlsx";
     	Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
     	ReportDetails reportDetails= new ReportDetails(map);
     	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
     	Assert.assertTrue(OCMReportsPage.reportSingleDateTypeValidation(reportDetails),"Invalid Date message");    	
     }
    
    
     
    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen(driver, "OCMAgentSummaryReportAdvsrchTest", method.getName());
    }


	
	
	/*@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("AgentSummaryReportTest",method.getName());
		driver.navigate().refresh();
	}*/

}
