
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
import com.tetherfi.pages.OCMFrequencyReportPage;
import com.tetherfi.pages.OCMFrequencyReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.OCMThresholdReportPage;
import com.tetherfi.pages.OCMFrequencyReportPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMFrequencyThresholdTest extends BaseTest {
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
    public void ShowOCMFrequencyReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");        
    }
         
    @Test(priority=2, dependsOnMethods ="ShowOCMFrequencyReport")
    public void ShowOcmFrequencyReportInNewTab() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReportInNewPage(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
        ocmReportsPage.switchBackToParentWindow();
    }  
    @Test(priority=3)
    public void ScheduleOCMFrequencyReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.scheduleReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    } 
    @Test(priority=4,dependsOnMethods ="ShowOcmFrequencyReportInNewTab")
    public void ExportOcmFrequencyReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    }
  
   @Test(priority=5,dependsOnMethods ="ExportOcmFrequencyReport")
    public void ViewDownloadedOcmFrequencyReportInReportsDownloadPage() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }    
   @Test(priority=6)
   public void ShowOcmFrequencyReportForDateRange() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map);
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
       ocmReportsPage.showReport(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
   }
   
   @Test(priority=7,dependsOnMethods ="ShowOcmFrequencyReportForDateRange")
   public void ShowOcmFrequencyReportInNewTabDateRange() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map);
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
       ocmReportsPage.showReportInNewPage(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
       ocmReportsPage.switchBackToParentWindow();
   }   
    @Test(priority=8,dependsOnMethods ="ShowOcmFrequencyReportInNewTabDateRange")
    public void ExportOcmFrequencyReportDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    } 
    @Test(priority=9,dependsOnMethods ="ExportOcmFrequencyReportDateRange")
    public void ViewDownloadedOcmFrequencyReportInReportsDownloadPageDateRange() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }
    @Test(priority=10)
    public void ScheduleOcmThresholdReportforDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.scheduleReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    }	    
    @Test(priority=11)
    public void ClearAll() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.ClearHomepgDrpDown(reportDetails);
    }
    @Test(priority=12)
	public void OCMWindow() throws Exception {	
	    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);        
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);             
        Assert.assertTrue(freqhitPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"OCMFrequencyReport","Maximize");
    	Assert.assertTrue(freqhitPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"OCMFrequencyReport","Minimize");	
    }
	@Test(priority=13)
    public void VerifyDropdownForAllTheColumns() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);	
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
		Assert.assertTrue(freqhitPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }   
    @Test(priority=14)
    public void VerifyColumnsHeaderEnable() throws Exception {	
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);	
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
		Assert.assertTrue(freqhitPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }   
    @Test(priority=15)
    public void VerifyColumnsHeaderDisable() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
        Assert.assertFalse(freqhitPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    } 
    @Test(priority=16)
    public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
    	Assert.assertTrue(freqhitPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }    
   @Test(priority=17)
    public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
        Assert.assertTrue(freqhitPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }  
    @Test(priority=18)
    public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
        Assert.assertTrue(freqhitPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }  
    @Test(priority=19)
    public void VerifyNumberOfItemsPerPageSelection() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
        Assert.assertTrue(freqhitPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    } 
    @Test(priority=20)
    public void ExportPage() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);	
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
        Assert.assertTrue(freqhitPage.verifyExportToExcel(filePath1));
    }   
    @Test(priority=21)
    public void SortingByAscending() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
        freqhitPage.SortByAscending();
    	String filePath1= System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMFrequentlyHitThresholdReport (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
        Assert.assertTrue(freqhitPage.verifyexportToExcelSheet(maplist));
    }  
    @Test(priority=22)
    public void SortingByDescending() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);       
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
        freqhitPage.SortByDescending();
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMFrequentlyHitThresholdReport (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
        Assert.assertTrue(freqhitPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=23)
    public void SchedulereportinFrequencyPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);           
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
        freqhitPage.navigateToExportSchedulerPage();    
        Assert.assertTrue(freqhitPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
    	screenshot.captureScreen("OCMFrequencyReportTest","ExportSchedulerPage");
    }  
    @Test(priority=24)
    public void ExportToExcelForThresholdReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMThresholdReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        OCMFrequencyReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
        agntloginlogoutPage.exportToExcel();
        Assert.assertTrue(agntloginlogoutPage.verifyReportExported(),"export report assertion failed");
    }   
    @Test(priority=25,dependsOnMethods ="ExportToExcelForThresholdReport")
    public void ViewDownloadedOcmFrequencyReportInReportsDownloadPageinFrequencyPg() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);  
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
        freqhitPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");  
    }   
    
    @Test(priority=26)
    public void VerifySearchByFeatureForFrequencyReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(freqhitPage.verifySearchByTextbox(reportDetails));
    }
    @Test(priority=27)
    public void VerifySearchFeatureForFrequencyReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(freqhitPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
    }
    @Test(priority=28)
    public void searchwithoutSearchTextbox() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
      	OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);
      	OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
      	freqhitPage.searchwithoutextsearch(reportDetails);
        Assert.assertEquals(freqhitPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }       
    @Test(priority=29)
    public void VerifySearchIsNotEqualTo() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
    	Assert.assertTrue(freqhitPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
    }   
   @Test(priority=30)
    public void  VerifySearchContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
    	Assert.assertTrue(freqhitPage.verifySearchContains(reportDetails.getSearchStr()));
    }   
    @Test(priority=31)
    public void  VerifySearchDoesNotContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
    	Assert.assertTrue(freqhitPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
    	}    
    @Test(priority=32)
    public void  VerifySearchStartsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
    	Assert.assertTrue(freqhitPage.verifySearchStartsWith(reportDetails.getSearchStr()));
    }
  @Test(priority=33)
    public void  VerifySearchEndsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
    	Assert.assertTrue(freqhitPage.verifySearchEndsWith(reportDetails.getSearchStr()));
    }
  @Test(priority=34)
 	public void SearchClearSearch() throws Exception{
 		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
		freqhitPage.verifySearchClear(reportDetails);    	
  }
    @Test(priority=35)
    public void verifyAdvancedSearchinreportpage() throws Exception {
    	 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
         ReportDetails reportDetails= new ReportDetails(map);
         OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
         OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);        
         ocmReportsPage.showReport(reportDetails);
         Assert.assertTrue(freqhitPage.verifyAdvanceSearch(reportDetails));            
    }
    @Test(priority=36)
    public void ClearfiltersAdvSrch() throws Exception{ 	
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
        ocmReportsPage.ClearAdvFilters(reportDetails);
    }
    @Test(priority=37)
    public void GroupBy() throws Exception{
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
	   Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
	   ReportDetails reportDetails= new ReportDetails(map);
	   OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);   	
	   ocmReportsPage.showReport(reportDetails);
	   OCMFrequencyReportPage freqhitPage=PageFactory.createPageInstance(driver,OCMFrequencyReportPage.class);
	   Assert.assertTrue(freqhitPage.groupby());
	   screenshot.captureScreen("OCMFrequentlyHitReport", "GroupBy");
	   Assert.assertTrue(freqhitPage.groupby());
	   screenshot.captureScreen("OCMFrequentlyHitReport", "AlreadyGroupBy");
    }
  /*  @Test(priority=38)
    public void database() throws Exception {
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMThresholdReport.xlsx";
   		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
   		ReportDetails reportDetails= new ReportDetails(map);
   		OCMThresholdReportPage thresholdPage=PageFactory.createPageInstance(driver,OCMThresholdReportPage.class);
   		Assert.assertTrue(thresholdPage.verifyDatabase(reportDetails.getQuery()));
   */       
    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen(driver, "", method.getName());
    }
}
